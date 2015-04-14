package com.up72.util;

import static com.up72.common.CommonUtils.dateUtils;
import static com.up72.common.CommonUtils.fileUtil;
import static com.up72.common.CommonUtils.ioUtil;
import static com.up72.common.CommonUtils.stringUtil;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.up72.common.CommonConstants;
import com.up72.common.image.ImageTools;
import com.up72.common.image.deal.ZoomDeal;
import com.up72.common.upload.UploadConstant;
import com.up72.exception.UtilException;

/**
 * 文件操作工具类，提供路径获取，文件操作，读取写入等操作
 * 
 * @author wqtan
 */
public final class FileUtil {
	private static final boolean DEBUG = false;

	private static final Map<String, String> extTypeMap = new HashMap<String, String>();

	/**
	 * 获得指定类所在绝对路径
	 * 
	 * @author wqtan
	 */
	@SuppressWarnings("unchecked")
	public  String getClassPath(Class className) {
		return className.getResource("").getPath();
	}

	public  String getExtType(String ext) {
		String type = extTypeMap.get(ext);
		if (null != type) {
			return type;
		}
		return extTypeMap.get(".*");
	}

	/**
	 * 获得指定类所在web路径，到WebRoot
	 * 
	 * @author wqtan
	 */
	@SuppressWarnings("unchecked")
	public  String getWebPath(Class className) {
		String classPath = getClassPath(className);
		String result = classPath.substring(0, classPath.indexOf("WEB-INF"));
		return result;
	}

	/**
	 * 获得文件所在路径
	 * 
	 * @param fileName
	 *            文件名
	 * @author wqtan
	 */
	public  String getPath(String fileName) {
		String result = fileName.substring(0, fileName.lastIndexOf("/") + 1);
		return result;
	}

	/**
	 * 根据创建给定文件路径
	 * 
	 * @param path
	 * @return 成功(true)或失败(false)
	 * @author wqtan
	 */
	public  boolean mkdirs(String path) {
		boolean result = false;
		File file = new File(path);
		// 如果文件存在直接返回
		if (file.exists()) {
			result = true;
		}
		// 否则创建路径
		else {
			result = file.mkdirs();
		}
		return result;
	}

	/**
	 * 根据文件名创建文件
	 * 
	 * @param fileName
	 *            文件名称，需要传入全路径
	 * @return 成功(true)或失败(false)
	 * @author wqtan
	 */
	public  boolean createFile(String fileName) {
		boolean result = false;
		File file = new File(fileName);
		// 文件存在，直接返回
		if (file.exists()) {
			result = true;
		} else {
			// 创建路径
			mkdirs(getPath(fileName));
			// 创建文件
			try {
				file.createNewFile();
			} catch (IOException e) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 向指定路径的指定文件写入指定内容
	 * 
	 * @param filePath
	 *            路径名
	 * @param fileName
	 *            文件名
	 * @param content
	 *            内容
	 * @author wqtan
	 * @return map
	 * @throws IOException
	 * 
	 */
	public  void createFile(String filePath, String fileName,
			String content) throws IOException {
		if (null == fileName || fileName.trim().equals("")) {
			throw new IOException("文件名为空");
		} else {
			// 创建文件目录
			mkdirs(filePath);
			// 拼接文件完整路径
			if (filePath.endsWith("/")) {
				fileName = filePath + fileName;
			} else {
				fileName = filePath + "/" + fileName;
			}
			// 建立一个新文件
			OutputStreamWriter out = null;
			Writer writer = null;
			try {
				this.createFile(fileName);
				// 将内容content写入文件
				out = new OutputStreamWriter(new FileOutputStream(new File(
						fileName)), "UTF-8");
				writer = new BufferedWriter(out);
				out.write(content);
				writer.close();
				out.close();
			} catch (IOException e) {
				throw new IOException(e.getMessage());
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (writer != null) {
						writer.close();
					}
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 读取文件
	 * 
	 * @param filePathAndName
	 * @return
	 */
	public  String readFileByLines(String filePathAndName) {
		final StringBuffer sb = new StringBuffer();
		try {
			File f = new File(filePathAndName);
			if (f.isFile() && f.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(f), "UTF-8");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}
				read.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	/**
	 * 判断指定的文件或路径是否存在
	 * 
	 * @param 路径或者文件名
	 * @return true(存在) / false(不存在)
	 * @author wqtan
	 */
	public  boolean exists(String fileName) {
		boolean result = false;
		if (null != fileName && !fileName.equals("")) {
			File file = new File(fileName);
			return file.exists();
		}
		return result;
	}

	/**
	 * 获得指定文件的后缀名
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 后缀 ".xx"
	 */
	public  String getSuffix(String fileName) {
		String result = "";
		if (null != fileName && !fileName.trim().equals("")
				&& fileName.indexOf('.') != -1) {
			result = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
		}
		return result;
	}

	/**
	 * 获得指定文件的文件名称，不包含后缀和路径
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 后缀 ".xx"
	 */
	public  String getNameWithoutSuffix(String fileName) {
		String result = null;
		if (null != fileName && !fileName.trim().equals("")
				&& fileName.indexOf('.') != -1) {
			result = fileName.substring(0, fileName.lastIndexOf('.'));
		}
		return result;
	}

	/**
	 * 保存文件
	 * 
	 * @author wqtan
	 * 
	 * @param inputStream
	 *            输入流
	 * @param fileName
	 *            保存的文件名
	 * @param buffSize
	 *            文件读写的缓存大小
	 * @return void
	 * 
	 * @throws IOException
	 */
	public  void saveFile(InputStream inputStream, String fileName,
			int buffSize) throws IOException {
		if (buffSize <= 0) {
			buffSize = 1024;
		}

		FileOutputStream fos = new FileOutputStream(new File(fileName));

		byte[] buff = new byte[buffSize];
		int flag = 0;

		while ((flag = inputStream.read(buff, 0, buff.length)) != -1) {
			fos.write(buff, 0, flag);
		}

		fos.close();
	}

	/**
	 * 上传业务处理
	 * 
	 * @author wqtan
	 * @return 上传的文件信息
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public  Map<String, String> uploadFile(InputStream inputStream,
			String fileName) throws FileNotFoundException, IOException {
		// 获得当前时间的long值
		// 保存文件名
		String saveFile = UUID.randomUUID().toString().replace("-", "")
				+ fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
		// 保存文件路径
		String webSaveFile = getSavePath(saveFile);
		
		this.saveFile(inputStream, webSaveFile, 1024 * 4);

		Map<String, String> result = new HashMap<String, String>();
		// 图片缩放
		File file = new File(webSaveFile);
		if(null!=file && file.isFile() && file.exists()
				&& isImage(webSaveFile)){
			Image image = ImageTools.readImage(webSaveFile);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			if(width > CommonConstants.image.MAX_WIDTH){
				String newFile = UUID.randomUUID().toString().replace("-", "")
				+ fileName.substring(fileName.indexOf('.')).toLowerCase();
				String newWebSaveFile = getSavePath(newFile);
				
				int maxHeight = (int)((CommonConstants.image.MAX_WIDTH+0.0) / (width+0.0) * (height+0.0));
				try {
					ZoomDeal deal = new ZoomDeal(CommonConstants.image.MAX_WIDTH,maxHeight);
					image = deal.dealImage(image);
					ImageTools.writeImage(newWebSaveFile, image);
					file.delete();
					webSaveFile = newWebSaveFile;
				} catch (UtilException e) {}
			}else if(file.length() > CommonConstants.image.UPLOAD_MAXSIZE){
				String newFile = UUID.randomUUID().toString().replace("-", "")
				+ fileName.substring(fileName.indexOf('.')).toLowerCase();
				webSaveFile = getSavePath(newFile);
				
				try {
					ImageTools.writeImage(webSaveFile, image);
					file.delete();
				} catch (UtilException e) {}
			}
			result.put("width", image.getWidth(null)+"");
			result.put("height", image.getHeight(null)+"");
		}

		String savePath = webSaveFile;
		if (webSaveFile.startsWith(CommonConstants.ROOTPATH)) {
			savePath = stringUtil.parseToPath(
					"/"+webSaveFile.substring(CommonConstants.ROOTPATH.length()));
		}
		result.put("saveFile", saveFile);
		result.put("savePath", savePath);
		result.put("fileName", java.net.URLEncoder.encode(fileName, "UTF-8"));
		result.put("uploadName", java.net.URLEncoder.encode(fileName, "UTF-8"));
		result.put("uploadTime", Long.toString(new Date().getTime()));
		return result;
	}
	
	/**
	 * 判断指定的文件名是否是图片
	 * update wgf 2012-12-13
	 * @return
	 */
	public boolean isImage(String filePath){
		boolean result = false;
		String ext = fileUtil.getSuffix(filePath);
		if(null!=ext && (ext.equals(".jpg") 
				|| ext.equals(".png")
				|| ext.equals(".ico")
				|| ext.equals(".gif")
				|| ext.equals(".jpeg"))){
			result = true;
		}
		return result;
	}

	private  String getSavePath(String saveFile) {
		String webSavePath = stringUtil.parseToPath(
				CommonConstants.ROOTPATH + "/" 
				+ UploadConstant.UPLOAD_SAVE_PATH 
				+ "/" + dateUtils.getStrDate());

		// 创建保存路径
		File file = new File(webSavePath);
		file.mkdirs();

		String result = stringUtil.parseToPath(webSavePath + "/" + saveFile);
		return result;
	}

	/**
	 * 获得指定路径下指定扩展名的非隐藏文件列表
	 * 
	 * @param filePath
	 *            路径
	 * @param ext
	 *            扩展名
	 */
	public  File[] listFiles(String filePath, final String ext) {
		File file = new File(filePath);
		File[] result = null;
		if (null == ext || ext.trim().equals("")) {
			result = file.listFiles(new FileFilter() {
				public boolean accept(File file) {
					boolean innerresult = false;
					if (file.isFile() && !file.isHidden()) {
						innerresult = true;
					}
					return innerresult;
				}
			});
		} else {
			result = file.listFiles(new FileFilter() {
				public boolean accept(File file) {
					boolean innerresult = false;
					if (file.isFile() && !file.isHidden()) {
						String fileName = file.getName();
						fileName = fileName
								.substring(fileName.lastIndexOf('.'));
						if (file.getName().toLowerCase().endsWith(
								ext.toLowerCase())) {
							innerresult = true;
						}
					}
					return innerresult;
				}
			});
		}
		return result;
	}

	/**
	 * 获得指定路径下非隐藏的文件路径
	 * 
	 * @param filePath
	 *            路径
	 */
	public  File[] listFilePaths(String filePath) {
		File file = new File(filePath);
		File[] result = file.listFiles(new FileFilter() {
			public boolean accept(File pathName) {
				boolean innerresult = false;
				if (pathName.isDirectory() && !pathName.isHidden()) {
					innerresult = true;
				}
				return innerresult;
			}
		});
		return result;
	}

	/**
	 * 转换文件的大小，成为可读的K,M,G形式
	 * 
	 * @param filesize
	 * @return String
	 */
	public  String convertFileSize(File file) {
		long filesize = file.length();

		final int GSIZE = 1024 * 1024 * 1024;
		final int MSIZE = 1024 * 1024;
		final int KSIZE = 1024;
		final int SCALE = 2;

		String strUnit = null;
		int intDivisor = 0;

		// G级
		if (filesize >= GSIZE) {
			strUnit = "GB";
			intDivisor = GSIZE;
		}
		// M级
		else if (filesize >= MSIZE) {
			strUnit = "MB";
			intDivisor = MSIZE;
		}
		// K级
		else if (filesize >= KSIZE) {
			strUnit = "KB";
			intDivisor = KSIZE;
		}
		// B级
		else {
			strUnit = "B";
			intDivisor = 1;
		}

		if (intDivisor == 1) {
			return filesize + strUnit;
		}

		String result = new BigDecimal(filesize / (intDivisor + 0.0)).setScale(
				SCALE, BigDecimal.ROUND_HALF_EVEN).toString();
		return result + strUnit;
	}

	/**
	 * 转换文件的大小，成为可读的K,M,G形式
	 * 
	 * @param filesize
	 * @return String
	 */
	public  String convertFileSize(long filesize) {

		final int GSIZE = 1024 * 1024 * 1024;
		final int MSIZE = 1024 * 1024;
		final int KSIZE = 1024;
		final int SCALE = 2;

		String strUnit = null;
		int intDivisor = 0;

		// G级
		if (filesize >= GSIZE) {
			strUnit = "GB";
			intDivisor = GSIZE;
		}
		// M级
		else if (filesize >= MSIZE) {
			strUnit = "MB";
			intDivisor = MSIZE;
		}
		// K级
		else if (filesize >= KSIZE) {
			strUnit = "KB";
			intDivisor = KSIZE;
		}
		// B级
		else {
			strUnit = "B";
			intDivisor = 1;
		}

		if (intDivisor == 1) {
			return filesize + strUnit;
		}

		String result = new BigDecimal(filesize / (intDivisor + 0.0)).setScale(
				SCALE, BigDecimal.ROUND_HALF_EVEN).toString();
		return result + strUnit;
	}

	/**
	 * 删除指定文件
	 * 
	 * @param file
	 * @return 删除成功或不成功
	 */
	public  boolean deleteFile(String fileName) {
		File file = new File(fileName);
		boolean result = file.delete();
		return result;
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 * @return
	 */
	public  boolean deleteFile(File file) {
		return file.delete();
	}

	/**
	 * 获得文件的内容
	 * 
	 * @return
	 */
	public  String getContent(String fileName) {
		StringBuffer result = null;
		FileReader fr = null;
		try {
			result = new StringBuffer();
			fr = new FileReader(new File(fileName));
			char[] buff = new char[1024 * 4];
			int flag = 0;
			while ((flag = fr.read(buff, 0, buff.length)) != -1) {
				result.append(new String(buff, 0, flag));
			}
		} catch (FileNotFoundException e) {
			if (DEBUG) {
				e.printStackTrace();
			}
			result = null;
		} catch (IOException e) {
			if (DEBUG) {
				e.printStackTrace();
			}
			result = null;
		} finally {
			if (null != fr) {
				try {
					fr.close();
				} catch (IOException e) {
				}
			}
		}
		return (null == result ? null : result.toString());
	}

	/**
	 * 保存文本到文件
	 * 
	 * @author wqtan
	 * @param fileName
	 *            文件名
	 * @param content
	 *            内容
	 */
	public  void saveFile(String fileName, String content) {
		createFile(fileName);
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(fileName));
			fw.write(content);
		} catch (IOException e) {
			if (DEBUG) {
				e.printStackTrace();
			}
		} finally {
			if (null != fw) {
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
		}

	}

	/**
	 * 保存文本到文件
	 * 
	 * @author tlliu
	 * @param fileName
	 *            文件名
	 * @param content
	 *            内容
	 */
	public  boolean saveFileByCharset(String fileName, String content,
			String charset) {
		if (createFile(fileName)) {
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(fileName), charset));
				out.write(content);
				return true;
			} catch (IOException e) {
				if (DEBUG) {
					e.printStackTrace();
				}
			} finally {
				if (null != out) {
					try {
						out.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return false;

	}

	/**
	 * 根据编码读取文件
	 * 
	 * @author tlliu
	 * @param filePathAndName
	 * @return
	 */
	public  String readFileByLines(File f, String charset) {
		final StringBuffer sb = new StringBuffer();
		try {
			if (f.isFile() && f.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(f), charset);
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}
				read.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	/**
	 * 获得文件的字符编码
	 * 
	 * @author tlliu
	 */
	public  String getCharset(java.io.File file) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		try {
			boolean checked = false;
			;
			java.io.BufferedInputStream bis = new java.io.BufferedInputStream(
					new java.io.FileInputStream(file));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1)
				return charset;
			if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8";
				checked = true;
			}
			bis.reset();
			if (!checked) {
				int loc = 0;
				while ((read = bis.read()) != -1) {
					loc++;
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
															// (0x80
							// - 0xBF),也可能在GB编码内
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charset;
	}

	/**
	 * 根据给定的path，和root拼接绝对的路径，如果path已经为绝对路径，则直接返回
	 * 
	 * @param root
	 * @param path
	 * @return String
	 */
	public  String getAbsolutePath(String root, String path) {
		String result = path;
		File file = new File(result);
		// 如果是绝对路径
		if (file.isAbsolute()) {
			// 如果文件不存在但是相对项目根目录存在
			if (!file.exists() && path.startsWith("/")) {
				String tempPath = stringUtil.parseToPath(root + "/" + path);
				File temp = new File(tempPath);
				if (temp.exists()) {
					result = tempPath;
				}
			}
		}
		// 如果不是绝对路径
		else {
			result = stringUtil.parseToPath(root + "/" + path);
		}

		return result;
	}

	/**
	 * 文件重命名
	 * 
	 * @author tlliu
	 * @param sourceFileName
	 * @param newFileName
	 * @return 1成功 2文件名重复 3源文件不存在 0 未知错误
	 */
	public  Integer renameFile(String sourceFileName, String newFileName) {
		File sourceFile = new File(sourceFileName);
		if (sourceFile.exists()) {
			File newFile = new File(newFileName);
			if (!newFile.exists()) {
				boolean flag = sourceFile.renameTo(newFile);
				if (flag) {
					return 1;
				}
			} else {
				return 2;
			}
		} else {
			return 3;
		}
		return 0;
	}

	/**
	 * 是否目录
	 * 
	 * @author tlliu
	 * @param fileName
	 * @return
	 */
	public  boolean isFirectory(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isDirectory()) {
			return true;
		}
		return false;
	}

	public  void main(String[] args) {
		System.out.println(renameFile("C:/2.txt", "3.txt"));
	}
	

    public  boolean contentEquals(final File file1, final File file2) throws IOException {
        final boolean file1Exists = file1.exists();
        if (file1Exists != file2.exists()) {
            return false;
        }

        if (!file1Exists) {
            // two not existing files are equal
            return true;
        }

        if (file1.isDirectory() || file2.isDirectory()) {
            // don't want to compare directory contents
            return false;
        }

        InputStream input1 = null;
        InputStream input2 = null;
        try {
            input1 = new FileInputStream(file1);
            input2 = new FileInputStream(file2);
            final InputStream bufferedInput1 = new BufferedInputStream(input1);
            final InputStream bufferedInput2 = new BufferedInputStream(input2);

            int ch = bufferedInput1.read();
            while (ch != -1) {
                if (ch != bufferedInput2.read()) {
                    return false;
                }
                ch = bufferedInput1.read();
            }
            if (-1 != bufferedInput2.read()) {
                return false;
            }
            return true;
        }
        finally {
            ioUtil.shutdownStream(input1);
            ioUtil.shutdownStream(input2);
        }
    }
    

    public  File toFile(final URL url) {
        if (url.getProtocol().equals("file") == false) {
            return null;
        }
        else {
            final String filename = url.getFile().replace('/', File.separatorChar);
            return new File(filename);
        }
    }
    
	/**
	 * 去掉文件后缀
	 * @param filename
	 * @return
	 */
    public  String removeExtension(final String filename) {
        final int index = filename.lastIndexOf('.');

        if (-1 == index) {
            return filename;
        }
        else {
            return filename.substring(0, index);
        }
    }
    /**
     * 获得文件后缀
     * @param filename
     * @return
     */
    public  String getExtension(final String filename) {
        final int index = filename.lastIndexOf('.');

        if (-1 == index) {
            return "";
        }
        else {
            return filename.substring(index + 1);
        }
    }
    

    public  String removePath(final String filepath) {
        return removePath(filepath, File.separatorChar);
    }
    

    public String removePath(final String filepath, final char fileSeparatorChar) {
        final int index = filepath.lastIndexOf(fileSeparatorChar);

        if (-1 == index) {
            return filepath;
        }
        else {
            return filepath.substring(index + 1);
        }
    }
    

    public void copyFileToDirectory(final String source, final String destinationDirectory) throws IOException {
        copyFileToDirectory(new File(source), new File(destinationDirectory));
    }

    public void copyFileToDirectory(final File source, final File destinationDirectory) throws IOException {
        if (destinationDirectory.exists() && !destinationDirectory.isDirectory()) {
            throw new IllegalArgumentException("Destination is not a directory");
        }

        copyFile(source, new File(destinationDirectory, source.getName()));
    }
    
    public void copyFile(final File source, final File destination) throws IOException {
        //check source exists
        if (!source.exists()) {
            final String message = "File " + source + " does not exist";
            throw new IOException(message);
        }

        //does destinations directory exist ?
        if (destination.getParentFile() != null && !destination.getParentFile().exists()) {
            destination.mkdirs();
        }

        //make sure we can write to destination
        if (destination.exists() && !destination.canWrite()) {
            final String message = "Unable to open file " + destination + " for writing.";
            throw new IOException(message);
        }

        final FileInputStream input = new FileInputStream(source);
        final FileOutputStream output = new FileOutputStream(destination);
        ioUtil.copy(input, output);
        ioUtil.shutdownStream(input);
        ioUtil.shutdownStream(output);

        if (source.length() != destination.length()) {
            final String message = "Failed to copy full contents from " + source + " to " + destination;
            throw new IOException(message);
        }
    }
    
    /**
     * Delete a file. If file is directory delete it and all sub-directories.
     */
    public  void forceDelete(final String file) throws IOException {
        forceDelete(new File(file));
    }

    /**
     * Delete a file. If file is directory delete it and all sub-directories.
     */
    public  void forceDelete(final File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        }
        else {
            if (false == file.delete()) {
                final String message = "File " + file + " unable to be deleted.";
                throw new IOException(message);
            }
        }
    }

    /**
     * Recursively delete a directory.
     */
    public  void deleteDirectory(final String directory) throws IOException {
        deleteDirectory(new File(directory));
    }

    /**
     * Recursively delete a directory.
     */
    public  void deleteDirectory(final File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }

        cleanDirectory(directory);
        if (false == directory.delete()) {
            throw new IOException("Directory " + directory + " unable to be deleted.");
        }
    }

    /**
     * Clean a directory without deleting it.
     */
    public  void cleanDirectory(final String directory) throws IOException {
        cleanDirectory(new File(directory));
    }

    /**
     * Clean a directory without deleting it.
     */
    public  void cleanDirectory(final File directory) throws IOException {
        if (!directory.exists()) {
            final String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            final String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        final File[] files = directory.listFiles();

        for (int i = 0; i < files.length; i++) {
            final File file = files[i];

            this.forceDelete(file);
        }
    }

    /**
     * Recursively count size of a directory.
     *
     * @return size of directory in bytes.
     */
    public  long sizeOfDirectory(final String directory) {
        return sizeOfDirectory(new File(directory));
    }

    /**
     * Recursively count size of a directory.
     *
     * @return size of directory in bytes.
     */
    public  long sizeOfDirectory(final File directory) {
        if (!directory.exists()) {
            final String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            final String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        long size = 0;

        final File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            final File file = files[i];

            if (file.isDirectory()) {
                size += sizeOfDirectory(file);
            }
            else {
                size += file.length();
            }
        }

        return size;
    }

    /**
     * Get an unique path file
     * @param basePath base path
     * @param subPath sub directory name
     * @return an unique path file that not exists
     */
    public  File getUniquePath(String basePath, String subPath) {
        File resultPath = new File(basePath, subPath);
        int counter = 1;
        while (resultPath.exists()) {
            resultPath = new File(basePath, subPath + counter);
            counter++;
        }

        return resultPath;
    }

    /**
     * Get an unique file
     * @param dir the path where the file in
     * @param originFile origin file name
     * @return an unique file that not exists
     */
    public  File getUniqueFile(String dir, String originFile) {
        String originFileName = removeExtension(originFile);
        String originFileExt = getExtension(originFile);
        File resultFile = new File(dir, originFile);
        int counter = 1;
        while (resultFile.exists()) {
            resultFile = new File(dir, new StringBuffer(originFileName).append(counter).append(".").append(originFileExt).toString());
            counter++;
        }
        return resultFile;
    }
    
    /**
     * @author windy 
     */
    public  void saveToFile(String content, String pathName, String fileName)
        throws Exception
    {
        try
        {
            createDir(pathName);
            FileOutputStream fileoutputstream = new FileOutputStream(pathName + fileName);
            byte abyte0[] = content.getBytes();
            fileoutputstream.write(abyte0);
            fileoutputstream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * �����µ�Ŀ¼
     * @param String pathName
     * @author windy
     */
    public  void createDir(String pathName)
    {
        File file = new File(pathName);
        if(!file.exists())
            file.mkdirs();
    }
    public void copyURLToFile(final URL source, final File destination) throws IOException {
        //does destination directory exist ?
        if (destination.getParentFile() != null && !destination.getParentFile().exists()) {
            destination.mkdirs();
        }

        //make sure we can write to destination
        if (destination.exists() && !destination.canWrite()) {
            final String message = "Unable to open file " + destination + " for writing.";
            throw new IOException(message);
        }

        final InputStream input = source.openStream();
        final FileOutputStream output = new FileOutputStream(destination);
        ioUtil.copy(input, output);
        ioUtil.shutdownStream(input);
        ioUtil.shutdownStream(output);
    }
    
    /**
     * Normalize a path.
     * Eliminates "/../" and "/./" in a string. Returns <code>null</code> if the ..'s went past the
     * root.
     * Eg:
     * <pre>
     * /foo//               -->     /foo/
     * /foo/./              -->     /foo/
     * /foo/../bar          -->     /bar
     * /foo/../bar/         -->     /bar/
     * /foo/../bar/../baz   -->     /baz
     * //foo//./bar         -->     /foo/bar
     * /../                 -->     null
     * </pre>
     *
     * @param path the path to normalize
     * @return the normalized String, or <code>null</code> if too many ..'s.
     */
    public  String normalize(final String path) {
        String normalized = path;
        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) + normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) + normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0)
                break;
            if (index == 0)
                return null; // Trying to go outside our context
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
        }

        // Return the normalized path that we have completed
        return normalized;
    }
    
	/**
	 * 下载文档后缀名对应头格式
	 * 
	 * @author tlliu
	 */
	 {
		String[][] extContentType = { { ".chm", "application/octet-stream" },
				{ ".ppt", "application/vnd.ms-powerpoint" },
				{ ".xls", "application/vnd.ms-excel" },
				{ ".doc", "application/msword" },
				{ ".exe", "application/octet-stream" },
				{ ".rar", "application/octet-stream" },
				{ ".js", "javascrīpt/js" }, { ".css", "text/css" },
				{ ".hqx", "application/mac-binhex40" },
				{ ".bin", "application/octet-stream" },
				{ ".oda", "application/oda" }, { ".pdf", "application/pdf" },
				{ ".ai", "application/postsrcipt" },
				{ ".eps", "application/postsrcipt" },
				{ ".es", "application/postsrcipt" },
				{ ".rtf", "application/rtf" }, { ".mif", "application/x-mif" },
				{ ".csh", "application/x-csh" },
				{ ".dvi", "application/x-dvi" },
				{ ".hdf", "application/x-hdf" },
				{ ".nc", "application/x-netcdf" },
				{ ".cdf", "application/x-netcdf" },
				{ ".latex", "application/x-latex" },
				{ ".ts", "application/x-troll-ts" },
				{ ".src", "application/x-wais-source" },
				{ ".zip", "application/zip" },
				{ ".bcpio", "application/x-bcpio" },
				{ ".cpio", "application/x-cpio" },
				{ ".gtar", "application/x-gtar" },
				{ ".shar", "application/x-shar" },
				{ ".sv4cpio", "application/x-sv4cpio" },
				{ ".sv4crc", "application/x-sv4crc" },
				{ ".tar", "application/x-tar" },
				{ ".ustar", "application/x-ustar" },
				{ ".man", "application/x-troff-man" },
				{ ".sh", "application/x-sh" }, { ".tcl", "application/x-tcl" },
				{ ".tex", "application/x-tex" },
				{ ".texi", "application/x-texinfo" },
				{ ".texinfo", "application/x-texinfo" },
				{ ".t", "application/x-troff" },
				{ ".tr", "application/x-troff" },
				{ ".roff", "application/x-troff" },
				{ ".shar", "application/x-shar" },
				{ ".me", "application/x-troll-me" },
				{ ".ts", "application/x-troll-ts" }, { ".gif", "image/gif" },
				{ ".jpeg", "image/pjpeg" }, { ".jpg", "image/pjpeg" },
				{ ".jpe", "image/pjpeg" }, { ".ras", "image/x-cmu-raster" },
				{ ".pbm", "image/x-portable-bitmap" },
				{ ".ppm", "image/x-portable-pixmap" },
				{ ".xbm", "image/x-xbitmap" },
				{ ".xwd", "image/x-xwindowdump" }, { ".ief", "image/ief" },
				{ ".tif", "image/tiff" }, { ".tiff", "image/tiff" },
				{ ".pnm", "image/x-portable-anymap" },
				{ ".pgm", "image/x-portable-graymap" },
				{ ".rgb", "image/x-rgb" }, { ".xpm", "image/x-xpixmap" },
				{ ".txt", "text/plain" }, { ".c", "text/plain" },
				{ ".cc", "text/plain" }, { ".h", "text/plain" },
				{ ".html", "text/html" }, { ".htm", "text/html" },
				{ ".htl", "text/html" }, { ".rtx", "text/richtext" },
				{ ".etx", "text/x-setext" },
				{ ".tsv", "text/tab-separated-values" },
				{ ".mpeg", "video/mpeg" }, { ".mpg", "video/mpeg" },
				{ ".mpe", "video/mpeg" }, { ".avi", "video/x-msvideo" },
				{ ".qt", "video/quicktime" }, { ".mov", "video/quicktime" },
				{ ".moov", "video/quicktime" },
				{ ".movie", "video/x-sgi-movie" }, { ".au", "audio/basic" },
				{ ".snd", "audio/basic" }, { ".wav", "audio/x-wav" },
				{ ".aif", "audio/x-aiff" }, { ".aiff", "audio/x-aiff" },
				{ ".aifc", "audio/x-aiff" },
				{ ".swf", "application/x-shockwave-flash" },
				{ ".myz", "application/myz" },
				{ ".*", "application/octet-stream" },
				{ ".001", "application/x-001" },
				{ ".301", "application/x-301" }, { ".323", "text/h323" },
				{ ".906", "application/x-906" }, { ".907", "drawing/907" },
				{ ".a11", "application/x-a11" }, { ".acp", "audio/x-mei-aac" },
				{ ".ai", "application/postscript" }, { ".aif", "audio/aiff" },
				{ ".aifc", "audio/aiff" }, { ".aiff", "audio/aiff" },
				{ ".anv", "application/x-anv" }, { ".asa", "text/asa" },
				{ ".asf", "video/x-ms-asf" }, { ".asp", "text/asp" },
				{ ".asx", "video/x-ms-asf" }, { ".au", "audio/basic" },
				{ ".avi", "video/avi" },
				{ ".awf", "application/vnd.adobe.workflow" },
				{ ".biz", "text/xml" }, { ".bmp", "application/x-bmp" },
				{ ".bot", "application/x-bot" },
				{ ".c4t", "application/x-c4t" },
				{ ".c90", "application/x-c90" },
				{ ".cal", "application/x-cals" },
				{ ".cat", "application/vnd.ms-pki.seccat" },
				{ ".cdf", "application/x-netcdf" },
				{ ".cdr", "application/x-cdr" },
				{ ".cel", "application/x-cel" },
				{ ".cer", "application/x-x509-ca-cert" },
				{ ".cg4", "application/x-g4" },
				{ ".cgm", "application/x-cgm" },
				{ ".cit", "application/x-cit" }, { ".class", "java/*" },
				{ ".cml", "text/xml" }, { ".cmp", "application/x-cmp" },
				{ ".cmx", "application/x-cmx" },
				{ ".cot", "application/x-cot" },
				{ ".crl", "application/pkix-crl" },
				{ ".crt", "application/x-x509-ca-cert" },
				{ ".csi", "application/x-csi" }, { ".css", "text/css" },
				{ ".cut", "application/x-cut" },
				{ ".dbf", "application/x-dbf" },
				{ ".dbm", "application/x-dbm" },
				{ ".dbx", "application/x-dbx" }, { ".dcd", "text/xml" },
				{ ".dcx", "application/x-dcx" },
				{ ".der", "application/x-x509-ca-cert" },
				{ ".dgn", "application/x-dgn" },
				{ ".dib", "application/x-dib" },
				{ ".dll", "application/x-msdownload" },
				{ ".doc", "application/msword" },
				{ ".dot", "application/msword" },
				{ ".drw", "application/x-drw" }, { ".dtd", "text/xml" },
				{ ".dwf", "Model/vnd.dwf" }, { ".dwf", "application/x-dwf" },
				{ ".dwg", "application/x-dwg" },
				{ ".dxb", "application/x-dxb" },
				{ ".dxf", "application/x-dxf" },
				{ ".edn", "application/vnd.adobe.edn" },
				{ ".emf", "application/x-emf" }, { ".eml", "message/rfc822" },
				{ ".ent", "text/xml" }, { ".epi", "application/x-epi" },
				{ ".eps", "application/x-ps" },
				{ ".eps", "application/postscript" },
				{ ".etd", "application/x-ebx" },
				{ ".exe", "application/x-msdownload" },
				{ ".fax", "image/fax" }, { ".fdf", "application/vnd.fdf" },
				{ ".fif", "application/fractals" }, { ".fo", "text/xml" },
				{ ".frm", "application/x-frm" }, { ".g4", "application/x-g4" },
				{ ".gbr", "application/x-gbr" },
				{ ".gcd", "application/x-gcd" }, { ".gif", "image/gif" },
				{ ".gl2", "application/x-gl2" },
				{ ".gp4", "application/x-gp4" },
				{ ".hgl", "application/x-hgl" },
				{ ".hmr", "application/x-hmr" },
				{ ".hpg", "application/x-hpgl" },
				{ ".hpl", "application/x-hpl" },
				{ ".hqx", "application/mac-binhex40" },
				{ ".hrf", "application/x-hrf" }, { ".hta", "application/hta" },
				{ ".htc", "text/x-component" }, { ".htm", "text/html" },
				{ ".html", "text/html" }, { ".htt", "text/webviewhtml" },
				{ ".htx", "text/html" }, { ".icb", "application/x-icb" },
				{ ".ico", "image/x-icon" }, { ".ico", "application/x-ico" },
				{ ".iff", "application/x-iff" },
				{ ".ig4", "application/x-g4" },
				{ ".igs", "application/x-igs" },
				{ ".iii", "application/x-iphone" },
				{ ".img", "application/x-img" },
				{ ".ins", "application/x-internet-signup" },
				{ ".isp", "application/x-internet-signup" },
				{ ".ivf", "video/x-ivf" }, { ".java", "java/*" },
				{ ".jfif", "image/jpeg" }, { ".jpe", "image/jpeg" },
				{ ".jpe", "application/x-jpe" }, { ".jpeg", "image/jpeg" },
				{ ".jpg", "image/jpeg" }, { ".jpg", "application/x-jpg" },
				{ ".js", "application/x-javascript" }, { ".jsp", "text/html" },
				{ ".la1", "audio/x-liquid-file" },
				{ ".lar", "application/x-laplayer-reg" },
				{ ".latex", "application/x-latex" },
				{ ".lavs", "audio/x-liquid-secure" },
				{ ".lbm", "application/x-lbm" },
				{ ".lmsff", "audio/x-la-lms" },
				{ ".ls", "application/x-javascript" },
				{ ".ltr", "application/x-ltr" }, { ".m1v", "video/x-mpeg" },
				{ ".m2v", "video/x-mpeg" }, { ".m3u", "audio/mpegurl" },
				{ ".m4e", "video/mpeg4" }, { ".mac", "application/x-mac" },
				{ ".man", "application/x-troff-man" }, { ".math", "text/xml" },
				{ ".mdb", "application/msaccess" },
				{ ".mdb", "application/x-mdb" },
				{ ".mfp", "application/x-shockwave-flash" },
				{ ".mht", "message/rfc822" }, { ".mhtml", "message/rfc822" },
				{ ".mi", "application/x-mi" }, { ".mid", "audio/mid" },
				{ ".midi", "audio/mid" }, { ".mil", "application/x-mil" },
				{ ".mml", "text/xml" },
				{ ".mnd", "audio/x-musicnet-download" },
				{ ".mns", "audio/x-musicnet-stream" },
				{ ".mocha", "application/x-javascript" },
				{ ".movie", "video/x-sgi-movie" }, { ".mp1", "audio/mp1" },
				{ ".mp2", "audio/mp2" }, { ".mp2v", "video/mpeg" },
				{ ".mp3", "audio/mp3" }, { ".mp4", "video/mpeg4" },
				{ ".mpa", "video/x-mpg" },
				{ ".mpd", "application/vnd.ms-project" },
				{ ".mpe", "video/x-mpeg" }, { ".mpeg", "video/mpg" },
				{ ".mpg", "video/mpg" }, { ".mpga", "audio/rn-mpeg" },
				{ ".mpp", "application/vnd.ms-project" },
				{ ".mps", "video/x-mpeg" },
				{ ".mpt", "application/vnd.ms-project" },
				{ ".mpv", "video/mpg" }, { ".mpv2", "video/mpeg" },
				{ ".mpw", "application/vnd.ms-project" },
				{ ".mpx", "application/vnd.ms-project" },
				{ ".mtx", "text/xml" }, { ".mxp", "application/x-mmxp" },
				{ ".net", "image/pnetvue" }, { ".nrf", "application/x-nrf" },
				{ ".nws", "message/rfc822" }, { ".odc", "text/x-ms-odc" },
				{ ".out", "application/x-out" },
				{ ".p10", "application/pkcs10" },
				{ ".p12", "application/x-pkcs12" },
				{ ".p7b", "application/x-pkcs7-certificates" },
				{ ".p7c", "application/pkcs7-mime" },
				{ ".p7m", "application/pkcs7-mime" },
				{ ".p7r", "application/x-pkcs7-certreqresp" },
				{ ".p7s", "application/pkcs7-signature" },
				{ ".pc5", "application/x-pc5" },
				{ ".pci", "application/x-pci" },
				{ ".pcl", "application/x-pcl" },
				{ ".pcx", "application/x-pcx" }, { ".pdf", "application/pdf" },
				{ ".pdf", "application/pdf" },
				{ ".pdx", "application/vnd.adobe.pdx" },
				{ ".pfx", "application/x-pkcs12" },
				{ ".pgl", "application/x-pgl" },
				{ ".pic", "application/x-pic" },
				{ ".pko", "application/vnd.ms-pki.pko" },
				{ ".pl", "application/x-perl" }, { ".plg", "text/html" },
				{ ".pls", "audio/scpls" }, { ".plt", "application/x-plt" },
				{ ".png", "image/png" }, { ".png", "application/x-png" },
				{ ".pot", "application/vnd.ms-powerpoint" },
				{ ".ppa", "application/vnd.ms-powerpoint" },
				{ ".ppm", "application/x-ppm" },
				{ ".pps", "application/vnd.ms-powerpoint" },
				{ ".ppt", "application/vnd.ms-powerpoint" },
				{ ".ppt", "application/x-ppt" }, { ".pr", "application/x-pr" },
				{ ".prf", "application/pics-rules" },
				{ ".prn", "application/x-prn" },
				{ ".prt", "application/x-prt" }, { ".ps", "application/x-ps" },
				{ ".ps", "application/postscript" },
				{ ".ptn", "application/x-ptn" },
				{ ".pwz", "application/vnd.ms-powerpoint" },
				{ ".r3t", "text/vnd.rn-realtext3d" },
				{ ".ra", "audio/vnd.rn-realaudio" },
				{ ".ram", "audio/x-pn-realaudio" },
				{ ".ras", "application/x-ras" },
				{ ".rat", "application/rat-file" }, { ".rdf", "text/xml" },
				{ ".rec", "application/vnd.rn-recording" },
				{ ".red", "application/x-red" },
				{ ".rgb", "application/x-rgb" },
				{ ".rjs", "application/vnd.rn-realsystem-rjs" },
				{ ".rjt", "application/vnd.rn-realsystem-rjt" },
				{ ".rlc", "application/x-rlc" },
				{ ".rle", "application/x-rle" },
				{ ".rm", "application/vnd.rn-realmedia" },
				{ ".rmf", "application/vnd.adobe.rmf" },
				{ ".rmi", "audio/mid" },
				{ ".rmj", "application/vnd.rn-realsystem-rmj" },
				{ ".rmm", "audio/x-pn-realaudio" },
				{ ".rmp", "application/vnd.rn-rn_music_package" },
				{ ".rms", "application/vnd.rn-realmedia-secure" },
				{ ".rmvb", "application/vnd.rn-realmedia-vbr" },
				{ ".rmx", "application/vnd.rn-realsystem-rmx" },
				{ ".rnx", "application/vnd.rn-realplayer" },
				{ ".rp", "image/vnd.rn-realpix" },
				{ ".rpm", "audio/x-pn-realaudio-plugin" },
				{ ".rsml", "application/vnd.rn-rsml" },
				{ ".rt", "text/vnd.rn-realtext" },
				{ ".rtf", "application/msword" },
				{ ".rtf", "application/x-rtf" },
				{ ".rv", "video/vnd.rn-realvideo" },
				{ ".sam", "application/x-sam" },
				{ ".sat", "application/x-sat" }, { ".sdp", "application/sdp" },
				{ ".sdw", "application/x-sdw" },
				{ ".sit", "application/x-stuffit" },
				{ ".slb", "application/x-slb" },
				{ ".sld", "application/x-sld" }, { ".slk", "drawing/x-slk" },
				{ ".smi", "application/smil" },
				{ ".smil", "application/smil" },
				{ ".smk", "application/x-smk" }, { ".snd", "audio/basic" },
				{ ".sol", "text/plain" }, { ".sor", "text/plain" },
				{ ".spc", "application/x-pkcs7-certificates" },
				{ ".spl", "application/futuresplash" }, { ".spp", "text/xml" },
				{ ".ssm", "application/streamingmedia" },
				{ ".sst", "application/vnd.ms-pki.certstore" },
				{ ".stl", "application/vnd.ms-pki.stl" },
				{ ".stm", "text/html" }, { ".sty", "application/x-sty" },
				{ ".svg", "text/xml" },
				{ ".swf", "application/x-shockwave-flash" },
				{ ".tdf", "application/x-tdf" },
				{ ".tg4", "application/x-tg4" },
				{ ".tga", "application/x-tga" }, { ".tif", "image/tiff" },
				{ ".tif", "application/x-tif" }, { ".tiff", "image/tiff" },
				{ ".tld", "text/xml" }, { ".top", "drawing/x-top" },
				{ ".torrent", "application/x-bittorrent" },
				{ ".tsd", "text/xml" }, { ".txt", "text/plain" },
				{ ".uin", "application/x-icq" }, { ".uls", "text/iuls" },
				{ ".vcf", "text/x-vcard" }, { ".vda", "application/x-vda" },
				{ ".vdx", "application/vnd.visio" }, { ".vml", "text/xml" },
				{ ".vpg", "application/x-vpeg005" },
				{ ".vsd", "application/vnd.visio" },
				{ ".vsd", "application/x-vsd" },
				{ ".vss", "application/vnd.visio" },
				{ ".vst", "application/vnd.visio" },
				{ ".vst", "application/x-vst" },
				{ ".vsw", "application/vnd.visio" },
				{ ".vsx", "application/vnd.visio" },
				{ ".vtx", "application/vnd.visio" }, { ".vxml", "text/xml" },
				{ ".wav", "audio/wav" }, { ".wax", "audio/x-ms-wax" },
				{ ".wb1", "application/x-wb1" },
				{ ".wb2", "application/x-wb2" },
				{ ".wb3", "application/x-wb3" },
				{ ".wbmp", "image/vnd.wap.wbmp" },
				{ ".wiz", "application/msword" },
				{ ".wk3", "application/x-wk3" },
				{ ".wk4", "application/x-wk4" },
				{ ".wkq", "application/x-wkq" },
				{ ".wks", "application/x-wks" }, { ".wm", "video/x-ms-wm" },
				{ ".wma", "audio/x-ms-wma" },
				{ ".wmd", "application/x-ms-wmd" },
				{ ".wmf", "application/x-wmf" },
				{ ".wml", "text/vnd.wap.wml" }, { ".wmv", "video/x-ms-wmv" },
				{ ".wmx", "video/x-ms-wmx" },
				{ ".wmz", "application/x-ms-wmz" },
				{ ".wp6", "application/x-wp6" },
				{ ".wpd", "application/x-wpd" },
				{ ".wpg", "application/x-wpg" },
				{ ".wpl", "application/vnd.ms-wpl" },
				{ ".wq1", "application/x-wq1" },
				{ ".wr1", "application/x-wr1" },
				{ ".wri", "application/x-wri" },
				{ ".wrk", "application/x-wrk" }, { ".ws", "application/x-ws" },
				{ ".ws2", "application/x-ws" }, { ".wsc", "text/scriptlet" },
				{ ".wsdl", "text/xml" }, { ".wvx", "video/x-ms-wvx" },
				{ ".xdp", "application/vnd.adobe.xdp" },
				{ ".xdr", "text/xml" },
				{ ".xfd", "application/vnd.adobe.xfd" },
				{ ".xfdf", "application/vnd.adobe.xfdf" },
				{ ".xhtml", "text/html" },
				{ ".xls", "application/vnd.ms-excel" },
				{ ".xls", "application/x-xls" },
				{ ".xlw", "application/x-xlw" }, { ".xml", "text/xml" },
				{ ".xpl", "audio/scpls" }, { ".xq", "text/xml" },
				{ ".xql", "text/xml" }, { ".xquery", "text/xml" },
				{ ".xsd", "text/xml" }, { ".xsl", "text/xml" },
				{ ".xslt", "text/xml" }, { ".xwd", "application/x-xwd" },
				{ ".x_b", "application/x-x_b" },
				{ ".x_t", "application/x-x_t" }, { ".myz", "application/myz" } };
		for (int i = 0; i < extContentType.length; i++) {
			extTypeMap.put(extContentType[i][0], extContentType[i][1]);
		}
	}
}
