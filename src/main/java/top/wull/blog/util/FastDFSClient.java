package top.wull.blog.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastDFSClient {
	
	/**
	 * 
	 * @param name 临时目录
	 * @param file1 文件
	 * @return String []  picUrl[0] 原图地址  picUrl[1] 略缩图地址  
	 * @throws Exception
	 */
	//修改文件名字，由于上传图片类型是。tmp ，将图片文件临时放到硬盘上类型为 原来的类型
	public static String [] upLoadImage(String name , File file1) throws Exception{
		FileOutputStream fos = new FileOutputStream(name);
		FileInputStream fis = new FileInputStream(file1);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0){
			fos.write(buffer , 0 , len);
		}
		fis.close();
		fos.close();
		File file = new File(name);
		String [] picUrl =  FastDFSClient.UploadImage(file);
		return picUrl;
	}
	
	/**
	 * 将传入的文件通过dfs上传到服务器
	 * @param file
	 * @return
	 * @throws Exception
	 */
	 public static String[] UploadFileByFastDFS(File file) throws Exception{  
//	      1、把FastDFS提供的jar包添加到工程中       
//	      2、初始化全局配置。加载一个配置文件。  
	        ClientGlobal.init("fdfs_client.conf");  
//	      3、创建一个TrackerClient对象。  
	        TrackerClient trackerClient =new TrackerClient();  
//	      4、创建一个TrackerServer对象。  
	        TrackerServer trackerServer=trackerClient.getConnection();  
//	      5、声明一个StorageServer对象，null。  
	        StorageServer storageServer=null;  
//	      6、获得StorageClient对象。  
	        StorageClient storageClient=new StorageClient(trackerServer,storageServer);  
//	      7、直接调用StorageClient对象方法上传文件即可。  
/*	        File file = new File("D://TIM图片1.jpg");
*/	        String filename = file.getName();
	        String prefix=filename.substring(filename.lastIndexOf(".")+1);
	        String[] strings=  storageClient.upload_file(File2byte(file), prefix, null);  
	       // String returnstr="";
/*	        for(String string:strings)  
	        {  
	        	// group1
	        	// M00/00/00/wKiRhlrc2rWAKDQ9AAAANxIGZP8172.txt
	            System.out.println(string);  
	        }  
*/	        return strings;
	  }
	 /**
	  * 上传图片，并且生成和保存压缩图片
	  * @return
	 * @throws Exception 
	  */
	 public static String[] UploadImage(File imgFile) throws Exception{
	 	//1.创建配置文件并得到对象输入流
		InputStream is = FastDFSClient.class.getClassLoader().getResourceAsStream("fastdfs-client.properties");
	 	//2.创建propetities
		Properties jdbc = new Properties();
		jdbc.load(is);
		//3. 通过key-value 的方式得到对应的值
		String ip = jdbc.getProperty("ip");
		System.out.println("file.getName() = " + imgFile.getName());
		//原图路径
		String []  hPicUrl= UploadFileByFastDFS(imgFile);
		//这里路径可能会写太死了。
		//原图图片真实路径Windows路径   ip /group1/M00/00/00/ wKiRhlrc2rWAKDQ9AAAANxIGZP8172.txt
		String hPicSrc = ip + "/"+hPicUrl[0]+"/"+hPicUrl[1];			
		//压缩图片
		File filelue = PictureChangeSize.compressImage2(imgFile, 500);		
		String []  luePicUrl= UploadFileByFastDFS(filelue);
		String luePicSrc = ip + "/"+luePicUrl[0]+"/"+luePicUrl[1];
		//高清，略缩图
		String [] picSrc = {hPicSrc,luePicSrc};
		return picSrc;
	 }
	 public static byte[] File2byte(File file)
	    {
	        byte[] buffer = null;
	        try
	        {
	            //File file = new File(filePath);
	            FileInputStream fis = new FileInputStream(file);
	            ByteArrayOutputStream bos = new ByteArrayOutputStream();
	            byte[] b = new byte[1024];
	            int n;
	            while ((n = fis.read(b)) != -1)
	            {
	                bos.write(b, 0, n);
	            }
	            fis.close();
	            bos.close();
	            buffer = bos.toByteArray();
	        }
	        catch (FileNotFoundException e)
	        {
	            e.printStackTrace();
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        return buffer;
	    }
}
