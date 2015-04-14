package com.up72.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



//import oa.io.extract.ExtractManager;

/**
 * General IO Stream manipulation.
 * <p>
 * This class provides  utility methods for input/output operations, particularly buffered
 * copying between sources (<code>InputStream</code>, <code>Reader</code>, <code>String</code> and
 * <code>byte[]</code>) and destinations (<code>OutputStream</code>, <code>Writer</code>,
 * <code>String</code> and <code>byte[]</code>).
 * </p>
 *
 * <p>Unless otherwise noted, these <code>copy</code> methods do <em>not</em> flush or close the
 * streams. Often, doing so would require making non-portable assumptions about the streams' origin
 * and further use. This means that both streams' <code>close()</code> methods must be called after
 * copying. if one omits this step, then the stream resources (sockets, file descriptors) are
 * released when the associated Stream is garbage-collected. It is not a good idea to rely on this
 * mechanism. For a good overview of the distinction between "memory management" and "resource
 * management", see <a href="http://www.unixreview.com/articles/1998/9804/9804ja/ja.htm">this
 * UnixReview article</a></p>
 *
 * <p>For each <code>copy</code> method, a variant is provided that allows the caller to specify the
 * buffer size (the default is 4k). As the buffer size can have a fairly large impact on speed, this
 * may be worth tweaking. Often "large buffer -&gt; faster" does not hold, even for large data
 * transfers.</p>
 *
 * <p>For byte-to-char methods, a <code>copy</code> variant allows the encoding to be selected
 * (otherwise the platform default is used).</p>
 *
 * <p>The <code>copy</code> methods use an internal buffer when copying. It is therefore advisable
 * <em>not</em> to deliberately wrap the stream arguments to the <code>copy</code> methods in
 * <code>Buffered*</code> streams. For example, don't do the
 * following:</p>
 *
 * <code>copy( new BufferedInputStream( in ), new BufferedOutputStream( out ) );</code>
 *
 * <p>The rationale is as follows:</p>
 *
 * <p>Imagine that an InputStream's read() is a very expensive operation, which would usually suggest
 * wrapping in a BufferedInputStream. The BufferedInputStream works by issuing infrequent
 * {@link java.io.InputStream#read(byte[] b, int off, int len)} requests on the underlying InputStream, to
 * fill an internal buffer, from which further <code>read</code> requests can inexpensively get
 * their data (until the buffer runs out).</p>
 * <p>However, the <code>copy</code> methods do the same thing, keeping an internal buffer,
 * populated by {@link InputStream#read(byte[] b, int off, int len)} requests. Having two buffers
 * (or three if the destination stream is also buffered) is pointless, and the unnecessary buffer
 * management hurts performance slightly (about 3%, according to some simple experiments).</p>
 */

/*
 * Behold, intrepid explorers; a map of this class:
 *
 *       Method      Input               Output          Dependency
 *       ------      -----               ------          -------
 * 1     copy        InputStream         OutputStream    (primitive)
 * 2     copy        Reader              Writer          (primitive)
 *
 * 3     copy        InputStream         Writer          2
 * 4     toString    InputStream         String          3
 * 5     toByteArray InputStream         byte[]          1
 *
 * 6     copy        Reader              OutputStream    2
 * 7     toString    Reader              String          2
 * 8     toByteArray Reader              byte[]          6
 *
 * 9     copy        String              OutputStream    2
 * 10    copy        String              Writer          (trivial)
 * 11    toByteArray String              byte[]          9
 *
 * 12    copy        byte[]              Writer          3
 * 13    toString    byte[]              String          12
 * 14    copy        byte[]              OutputStream    (trivial)
 *
 *
 * Note that only the first two methods shuffle bytes; the rest use these two, or (if possible) copy
 * using native Java copy methods. As there are method variants to specify buffer size and encoding,
 * each row may correspond to up to 4 methods.
 *
 */
public final class IOUtil {
    private  final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * Private constructor to prevent instantiation.
     */
    public IOUtil() {}

    /**
     * Unconditionally close an <code>Reader</code>.
         * Equivalent to {@link Reader#close()}, except any exceptions will be ignored.
     *
     * @param output A (possibly null) Reader
     */
    public  void shutdownReader(final Reader input) {
        if (null == input) {
            return;
        }

        try {
            input.close();
        }
        catch (final IOException ioe) {}
    }

    /**
     * Unconditionally close an <code>Writer</code>.
         * Equivalent to {@link Writer#close()}, except any exceptions will be ignored.
     *
     * @param output A (possibly null) Writer
     */
    public  void shutdownWriter(final Writer output) {
        if (null == output) {
            return;
        }

        try {
            output.close();
        }
        catch (final IOException ioe) {}
    }

    /**
     * Unconditionally close an <code>OutputStream</code>.
     * Equivalent to {@link OutputStream#close()}, except any exceptions will be ignored.
     * @param output A (possibly null) OutputStream
     */
    public  void shutdownStream(final OutputStream output) {
        if (null == output) {
            return;
        }

        try {
            output.close();
        }
        catch (final IOException ioe) {}
    }

    /**
     * Unconditionally close an <code>InputStream</code>.
     * Equivalent to {@link InputStream#close()}, except any exceptions will be ignored.
     * @param input A (possibly null) InputStream
     */
    public  void shutdownStream(final InputStream input) {
        if (null == input) {
            return;
        }

        try {
            input.close();
        }
        catch (final IOException ioe) {}
    }

    ///////////////////////////////////////////////////////////////
    // Core copy methods
    ///////////////////////////////////////////////////////////////

    /**
     * Copy bytes from an <code>InputStream</code> to an <code>OutputStream</code>.
     */
    public  void copy(final InputStream input, final OutputStream output) throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }

    /**
         * Copy bytes from an <code>InputStream</code> to an <code>OutputStream</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public  void copy(final InputStream input, final OutputStream output, final int bufferSize) throws IOException {
        final byte[] buffer = new byte[bufferSize];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
    }

    /**
     * Copy chars from a <code>Reader</code> to a <code>Writer</code>.
     */
    public  void copy(final Reader input, final Writer output) throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Copy chars from a <code>Reader</code> to a <code>Writer</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public  void copy(final Reader input, final Writer output, final int bufferSize) throws IOException {
        final char[] buffer = new char[bufferSize];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
    }

    ///////////////////////////////////////////////////////////////
    // Derived copy methods
    // InputStream -> *
    ///////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////
    // InputStream -> Writer

    /**
     * Copy and convert bytes from an <code>InputStream</code> to chars on a
     * <code>Writer</code>.
     * The platform's default encoding is used for the byte-to-char conversion.
     */
    public  void copy(final InputStream input, final Writer output) throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Copy and convert bytes from an <code>InputStream</code> to chars on a
     * <code>Writer</code>.
     * The platform's default encoding is used for the byte-to-char conversion.
     * @param bufferSize Size of internal buffer to use.
     */
    public  void copy(final InputStream input, final Writer output, final int bufferSize) throws IOException {
        final InputStreamReader in = new InputStreamReader(input);
        copy(in, output, bufferSize);
    }

    /**
     * Copy and convert bytes from an <code>InputStream</code> to chars on a
     * <code>Writer</code>, using the specified encoding.
     * @param encoding The name of a supported character encoding. See the
     * <a href="http://www.iana.org/assignments/character-sets">IANA
     * Charset Registry</a> for a list of valid encoding types.
     */
    public  void copy(final InputStream input, final Writer output, final String encoding) throws IOException {
        final InputStreamReader in = new InputStreamReader(input, encoding);
        copy(in, output);
    }

    /**
     * Copy and convert bytes from an <code>InputStream</code> to chars on a
     * <code>Writer</code>, using the specified encoding.
     * @param encoding The name of a supported character encoding. See the
     *        <a href="http://www.iana.org/assignments/character-sets">IANA
     *        Charset Registry</a> for a list of valid encoding types.
     * @param bufferSize Size of internal buffer to use.
     */
    public  void copy(final InputStream input, final Writer output, final String encoding, final int bufferSize) throws IOException {
        final InputStreamReader in = new InputStreamReader(input, encoding);
        copy(in, output, bufferSize);
    }

    ///////////////////////////////////////////////////////////////
    // InputStream -> String

    /**
     * Get the contents of an <code>InputStream</code> as a String.
     * The platform's default encoding is used for the byte-to-char conversion.
     */
    public  String toString(final InputStream input) throws IOException {
        return toString(input, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Get the contents of an <code>InputStream</code> as a String.
     * The platform's default encoding is used for the byte-to-char conversion.
     * @param bufferSize Size of internal buffer to use.
     */
    public  String toString(final InputStream input, final int bufferSize) throws IOException {
        final StringWriter sw = new StringWriter();
        copy(input, sw, bufferSize);
        return sw.toString();
    }

    /**
     * Get the contents of an <code>InputStream</code> as a String.
     * @param encoding The name of a supported character encoding. See the
     *    <a href="http://www.iana.org/assignments/character-sets">IANA
     *    Charset Registry</a> for a list of valid encoding types.
     */
    public  String toString(final InputStream input, final String encoding) throws IOException {
        return toString(input, encoding, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Get the contents of an <code>InputStream</code> as a String.
     * @param encoding The name of a supported character encoding. See the
     *   <a href="http://www.iana.org/assignments/character-sets">IANA
     *   Charset Registry</a> for a list of valid encoding types.
     * @param bufferSize Size of internal buffer to use.
     */
    public  String toString(final InputStream input, final String encoding, final int bufferSize) throws IOException {
        final StringWriter sw = new StringWriter();
        copy(input, sw, encoding, bufferSize);
        return sw.toString();
    }

    ///////////////////////////////////////////////////////////////
    // InputStream -> byte[]

    /**
     * Get the contents of an <code>InputStream</code> as a <code>byte[]</code>.
     */
    public  byte[] toByteArray(final InputStream input) throws IOException {
        return toByteArray(input, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Get the contents of an <code>InputStream</code> as a <code>byte[]</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public  byte[] toByteArray(final InputStream input, final int bufferSize) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output, bufferSize);
        return output.toByteArray();
    }

    ///////////////////////////////////////////////////////////////
    // Derived copy methods
    // Reader -> *
    ///////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////
    // Reader -> OutputStream
    /**
     * Serialize chars from a <code>Reader</code> to bytes on an <code>OutputStream</code>, and
     * flush the <code>OutputStream</code>.
     */
    public  void copy(final Reader input, final OutputStream output) throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Serialize chars from a <code>Reader</code> to bytes on an <code>OutputStream</code>, and
     * flush the <code>OutputStream</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public  void copy(final Reader input, final OutputStream output, final int bufferSize) throws IOException {
        final OutputStreamWriter out = new OutputStreamWriter(output);
        copy(input, out, bufferSize);
        // NOTE: Unless anyone is planning on rewriting OutputStreamWriter, we have to flush
        // here.
        out.flush();
    }

    ///////////////////////////////////////////////////////////////
    // Reader -> String
    /**
     * Get the contents of a <code>Reader</code> as a String.
     */
    public  String toString(final Reader input) throws IOException {
        return toString(input, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Get the contents of a <code>Reader</code> as a String.
     * @param bufferSize Size of internal buffer to use.
     */
    public  String toString(final Reader input, final int bufferSize) throws IOException {
        final StringWriter sw = new StringWriter();
        copy(input, sw, bufferSize);
        return sw.toString();
    }

    ///////////////////////////////////////////////////////////////
    // Reader -> byte[]
    /**
     * Get the contents of a <code>Reader</code> as a <code>byte[]</code>.
     */
    public  byte[] toByteArray(final Reader input) throws IOException {
        return toByteArray(input, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Get the contents of a <code>Reader</code> as a <code>byte[]</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public  byte[] toByteArray(final Reader input, final int bufferSize) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output, bufferSize);
        return output.toByteArray();
    }

    ///////////////////////////////////////////////////////////////
    // Derived copy methods
    // String -> *
    ///////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////
    // String -> OutputStream

    /**
     * Serialize chars from a <code>String</code> to bytes on an <code>OutputStream</code>, and
     * flush the <code>OutputStream</code>.
     */
    public  void copy(final String input, final OutputStream output) throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Serialize chars from a <code>String</code> to bytes on an <code>OutputStream</code>, and
     * flush the <code>OutputStream</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public  void copy(final String input, final OutputStream output, final int bufferSize) throws IOException {
        final StringReader in = new StringReader(input);
        final OutputStreamWriter out = new OutputStreamWriter(output);
        copy(in, out, bufferSize);
        // NOTE: Unless anyone is planning on rewriting OutputStreamWriter, we have to flush
        // here.
        out.flush();
    }

    ///////////////////////////////////////////////////////////////
    // String -> Writer

    /**
     * Copy chars from a <code>String</code> to a <code>Writer</code>.
     */
    public  void copy(final String input, final Writer output) throws IOException {
        output.write(input);
    }

    /**
     * Copy bytes from an <code>InputStream</code> to an
     * <code>OutputStream</code>, with buffering.
     * This is equivalent to passing a
     * {@link java.io.BufferedInputStream} and
     * {@link java.io.BufferedOutputStream} to {@link #copy(InputStream, OutputStream)},
     * and flushing the output stream afterwards. The streams are not closed
     * after the copy.
     * @deprecated Buffering streams is actively harmful! See the class description as to why. Use
     * {@link #copy(InputStream, OutputStream)} instead.
     */
    public  void bufferedCopy(final InputStream input, final OutputStream output) throws IOException {
        final BufferedInputStream in = new BufferedInputStream(input);
        final BufferedOutputStream out = new BufferedOutputStream(output);
        copy(in, out);
        out.flush();
    }

    ///////////////////////////////////////////////////////////////
    // String -> byte[]
    /**
     * Get the contents of a <code>String</code> as a <code>byte[]</code>.
     */
    public  byte[] toByteArray(final String input) throws IOException {
        return toByteArray(input, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Get the contents of a <code>String</code> as a <code>byte[]</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public  byte[] toByteArray(final String input, final int bufferSize) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output, bufferSize);
        return output.toByteArray();
    }

    ///////////////////////////////////////////////////////////////
    // Derived copy methods
    // byte[] -> *
    ///////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////
    // byte[] -> Writer

    /**
     * Copy and convert bytes from a <code>byte[]</code> to chars on a
     * <code>Writer</code>.
     * The platform's default encoding is used for the byte-to-char conversion.
     */
    public  void copy(final byte[] input, final Writer output) throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Copy and convert bytes from a <code>byte[]</code> to chars on a
     * <code>Writer</code>.
     * The platform's default encoding is used for the byte-to-char conversion.
     * @param bufferSize Size of internal buffer to use.
     */
    public  void copy(final byte[] input, final Writer output, final int bufferSize) throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output, bufferSize);
    }

    /**
     * Copy and convert bytes from a <code>byte[]</code> to chars on a
     * <code>Writer</code>, using the specified encoding.
     * @param encoding The name of a supported character encoding. See the
     * <a href="http://www.iana.org/assignments/character-sets">IANA
     * Charset Registry</a> for a list of valid encoding types.
     */
    public  void copy(final byte[] input, final Writer output, final String encoding) throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output, encoding);
    }

    /**
     * Copy and convert bytes from a <code>byte[]</code> to chars on a
     * <code>Writer</code>, using the specified encoding.
     * @param encoding The name of a supported character encoding. See the
     *        <a href="http://www.iana.org/assignments/character-sets">IANA
     *        Charset Registry</a> for a list of valid encoding types.
     * @param bufferSize Size of internal buffer to use.
     */
    public  void copy(final byte[] input, final Writer output, final String encoding, final int bufferSize) throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output, encoding, bufferSize);
    }

    ///////////////////////////////////////////////////////////////
    // byte[] -> String

    /**
     * Get the contents of a <code>byte[]</code> as a String.
     * The platform's default encoding is used for the byte-to-char conversion.
     */
    public  String toString(final byte[] input) throws IOException {
        return toString(input, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Get the contents of a <code>byte[]</code> as a String.
     * The platform's default encoding is used for the byte-to-char conversion.
     * @param bufferSize Size of internal buffer to use.
     */
    public  String toString(final byte[] input, final int bufferSize) throws IOException {
        final StringWriter sw = new StringWriter();
        copy(input, sw, bufferSize);
        return sw.toString();
    }

    /**
     * Get the contents of a <code>byte[]</code> as a String.
     * @param encoding The name of a supported character encoding. See the
     *    <a href="http://www.iana.org/assignments/character-sets">IANA
     *    Charset Registry</a> for a list of valid encoding types.
     */
    public  String toString(final byte[] input, final String encoding) throws IOException {
        return toString(input, encoding, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Get the contents of a <code>byte[]</code> as a String.
     * @param encoding The name of a supported character encoding. See the
     *   <a href="http://www.iana.org/assignments/character-sets">IANA
     *   Charset Registry</a> for a list of valid encoding types.
     * @param bufferSize Size of internal buffer to use.
     */
    public  String toString(final byte[] input, final String encoding, final int bufferSize) throws IOException {
        final StringWriter sw = new StringWriter();
        copy(input, sw, encoding, bufferSize);
        return sw.toString();
    }

    ///////////////////////////////////////////////////////////////
    // byte[] -> OutputStream

    /**
     * Copy bytes from a <code>byte[]</code> to an <code>OutputStream</code>.
     */
    public  void copy(final byte[] input, final OutputStream output) throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Copy bytes from a <code>byte[]</code> to an <code>OutputStream</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public  void copy(final byte[] input, final OutputStream output, final int bufferSize) throws IOException {
        output.write(input);
    }

    /**
     * Copy bytes from a <code>File</code> to an <code>File</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public  void copy(final File input, final File output) throws IOException {
        FileInputStream fis = new FileInputStream(input);
        FileOutputStream fos = new FileOutputStream(output);
        copy(fis, fos);
        fis.close();
        fos.flush();
        fos.close();
    }

    /**
     * ѹ��ĳ���ļ�(file)�������(output)��
     *
     * @param file Ҫѹ����ļ�
     * @param output Ҫѹ��������
     * @throws IOException
     */
    public  void zipFile(File file, OutputStream output) throws IOException {

        if (file == null || output == null)
            throw new IllegalArgumentException("Both 'file' and 'output' can't be null!");
        if (!file.exists())
            throw new IOException("File '" + file.getAbsolutePath() + "' doesn't exists!");
        if (!file.isFile())
            throw new IllegalArgumentException("'" + file.getAbsolutePath() + "' is not a file!");

        ZipOutputStream zos = new ZipOutputStream(output);
        String filePath = file.getAbsolutePath();
        String fileName = file.getName();
        File base = new File(filePath.substring(0, filePath.length() - fileName.length()));
        zipFile(base, file, zos);
        zos.flush();
        zos.close();
    }

    /**
     * ѹ��ĳһĿ¼(dir)�е�ȫ���ļ���ָ�������(output)��
     *
     * @param dir Ҫ����ѹ���Ŀ¼
     * @param output Ҫѹ��������
     * @throws IOException
     */
    public  void zipDirectory(File dir, OutputStream output) throws IOException {
        zipDirectory(dir, output, true);
    }

    /**
     * ѹ��ĳһĿ¼(dir)�е�ȫ���ļ���ָ�������(output)��
     *
     * @param dir Ҫ����ѹ���Ŀ¼
     * @param output Ҫѹ��������
     * @param includeBase ѹ���ļ����Ƿ���Ŀ¼���
     * @throws IOException
     */
    public  void zipDirectory(File dir, OutputStream output, boolean includeBase) throws IOException {

        if (dir == null || output == null)
            throw new IllegalArgumentException("Both 'dir' and 'output' can't be null!");
        if (!dir.exists())
            throw new IOException("Directory '" + dir.getAbsolutePath() + "' doesn't exists!");
        if (!dir.isDirectory())
            throw new IllegalArgumentException("'" + dir.getAbsolutePath() + "' is not a directory!");

        ZipOutputStream zos = new ZipOutputStream(output);

        if (includeBase) {
            zipDirectory(dir.getParentFile(), dir, zos);
        }
        else {
            zipDirectory(dir, dir, zos);
        }
        zos.flush();
        zos.close();
    }

    private  void zipDirectory(File base, File dir, ZipOutputStream zos) throws IOException {
        File files[] = dir.listFiles();
        File file;
        for (int i = 0; i < files.length; i++) {
            file = files[i];
            if (file.isDirectory()) {
                zipDirectory(base, file, zos);
            }
            else if (file.isFile()) {
                zipFile(base, file, zos);
            }
        }
    }

    private  void zipFile(File base, File file, ZipOutputStream zos) throws IOException {
        String basePath = base.getAbsolutePath();
        String filePath = file.getAbsolutePath();
        if (!filePath.startsWith(basePath))
            throw new IllegalArgumentException("Directory '" + basePath + "' is not the parent directory of file '" + filePath + "'");

        String entryName = filePath.substring(basePath.length());

        if (entryName.startsWith(File.separator))
            entryName = entryName.substring(1);

        ZipEntry entry = new ZipEntry(entryName);
        zos.putNextEntry(entry);
        FileInputStream fis = new FileInputStream(file);
        copy(fis, zos);
        fis.close();
    }

    /**
     * ��ĳ��ѹ���ļ�(zipFile)��ѹ��ָ��Ŀ¼(toDir)��
     *
     * @param src Ҫ��ѹ��ѹ���ļ�
     * @param toDir Ҫ��ѹ����Ŀ¼
     * @throws IOException
     */
    /*public  void unzip(File src, File dest) throws IOException {
       ExtractManager.extract(src,dest);
        
    }*/
    /**
     * ��ĳ��Ŀ¼(srcDir)�µ��ļ����ļ��и��Ƶ�ָ��Ŀ¼(destineDir)��
     *
     * @param srcDir Ҫ���Ƶ�Ŀ¼
     * @param destineDir Ҫճ���Ŀ¼
     * @throws IOException
     */
    public  void xcopy(File srcDir, File destineDir) throws IOException {
        try {
            if (!srcDir.exists() || srcDir.getCanonicalPath().equals(destineDir.getCanonicalPath()))
                return;
        }
        catch (IOException ioexception) {}
        if (!destineDir.exists()) {
            destineDir.mkdirs();
        }
        File chs[] = srcDir.listFiles();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i].isFile()) {
                File destineFile = new File(destineDir, chs[i].getName());
                copy(chs[i], destineFile);
            }
            else {
                File destine = new File(destineDir, chs[i].getName());
                destine.mkdirs();
                xcopy(chs[i], destine);
            }
        }
    }
}
