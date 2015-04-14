package com.up72.common.version;
import static com.up72.common.CommonUtils.fileUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * 序列化对象
 * @author liangyanjing
 *
 */
public final class SerializeUtil {

	/**
	 * 读取序列化文件
	 * @param path 文件路径
	 * @return 返回反序列后得对象
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @author LiangYingJing
	 */
	public static Object readObject(String path){
		if(!fileUtil.exists(path)){
			return null;
		}
		Object result = null;
		FileInputStream fs= null;
		ObjectInputStream os= null;
		try{
			fs = new FileInputStream(path);
			os = new ObjectInputStream(fs);
			result = os.readObject();
		}catch (IOException e) {
			throw new SerializeException(e);
		} catch (ClassNotFoundException e) {
			throw new SerializeException(e);
		}finally{
			try{
				if(null != os){
					os.close();
				}
			}catch (IOException e) {
				throw new SerializeException(e);
			}
			try{
				if(null != fs){
					fs.close();
				}
			}catch (IOException e) {
				throw new SerializeException(e);
			}
		}
		return result;
	}

	/**
	 * 序列化生成文件
	 * @param path 生成文件得路径名字
	 * @param obj 需要序列化得对象
	 * @return 生成成功(true)或失败(false)
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @author LiangYangJing
	 */
	public static boolean writeObject(String path, Object obj) throws SerializeException {
		boolean result = false;
		if(!(obj instanceof Serializable)){
			throw new SerializeException("对象不支持序列化");
		}
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		try {
			fileUtil.createFile(path);
			fs = new FileOutputStream(path);
			os = new ObjectOutputStream(fs);
			os.writeObject(obj);
			result = true;
		} catch (IOException e) {
			throw new SerializeException(e);
		}  finally{
			try {
				if(null != os){
					os.close();
				}
			}catch (IOException e) {
				throw new SerializeException(e);
			}
			try {
				if(null != fs){
					fs.close();
				}
			}catch (IOException e) {
				throw new SerializeException(e);
			}
		}
		return result;
	}

}
