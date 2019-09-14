package top.wull.blog.admin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import top.wull.blog.antity.Mood;
import top.wull.blog.service.MoodService;
import top.wull.blog.util.FastDFSClient;
import top.wull.blog.util.PageBean;

@Controller("moodUploadAction")
@Scope("prototype")
public class MoodUploadAction extends ActionSupport
{	
	@Resource(name="moodService")
	MoodService  ms;
	Mood mood;
	private String picsrc;
	private Integer mood_id;
	private String content;
	// 封装上传文件域的属性 .tmp
	private File upload;
	// 封装上传文件类型的属性
	private String uploadContentType; //image/png
	// 封装上传文件名的属性
	private String uploadFileName;
	// 直接在struts.xml文件中配置的属性
	private String savePath;
	private Integer flag1;//下面转换成Integer类型
	private String flag;//只能用string接受，保存数据库是用Integer
	private Integer currentPage = 1;
	private Integer pageSize = 9;	
	private String mood_content;
	private String time;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMood_content() {
		return mood_content;
	}
	public void setMood_content(String mood_content) {
		this.mood_content = mood_content;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setContent(String content) {
		this.content = content;
	}
	// 接受struts.xml文件配置值的方法
	public void setSavePath(String value)
	{
		this.savePath = value;
	}
	// 获取上传文件的保存位置
	private String getSavePath() throws Exception
	{
		return ServletActionContext.getServletContext()
			.getRealPath(savePath);
	}


	public String getContent()
	{
		return (this.content);
	}

	// upload的setter和getter方法
	public void setUpload(File upload)
	{
		this.upload = upload;
	}
	public File getUpload()
	{
		return (this.upload);
	}

	
	public Integer getMood_id() {
		return mood_id;
	}
	public void setMood_id(Integer mood_id) {
		this.mood_id = mood_id;
	}
	// uploadContentType的setter和getter方法
	public void setUploadContentType(String uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}
	public String getUploadContentType()
	{
		return (this.uploadContentType);
	}

	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getFlag1() {
		return flag1;
	}
	public void setFlag1(Integer flag1) {
		this.flag1 = flag1;
	}
	// uploadFileName的setter和getter方法
	public void setUploadFileName(String uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}
	public String getUploadFileName()
	{
		return (this.uploadFileName);
	}


	public String getPicsrc() {
		return picsrc;
	}
	public void setPicsrc(String picsrc) {
		this.picsrc = picsrc;
	}
	@Override
	public String toString() {
		return "MoodUploadAction [ms=" + ms + ", mood=" + mood + ", picsrc=" + picsrc + ", content=" + content
				+ ", upload=" + upload + ", uploadContentType=" + uploadContentType + ", uploadFileName="
				+ uploadFileName + ", savePath=" + savePath + ", flag=" + flag + "]";
	}
	public String add() {
		return "moodadd";
	}
	public String Upload() {
		return "moodadd";
	}
	/**
	 * 上传说说（处理文字加图片）
	 */
	@Override
	public String execute() throws Exception{
		//picUrl[0] 表示hPicSrc 原图路径  ， picUrl[1] 表示luePicSrc 略缩图图
		String[] picUrl = null;
		if(this.uploadFileName!=null){
			//前端页面已经限制只能图片
			if( ! uploadContentType.substring(0,"image".length()).equals("image")){
				ActionContext.getContext().put("prompt_message", "发布失败，上传的文件不是图片！");
				return "moodAdd";
			}
			//使用fastDFS保存图片
			FastDFSClient fc = new FastDFSClient();			
			//1.创建配置文件并得到对象输入流
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("fastdfs-client.properties");
			//2.创建propetities
			Properties jdbc = new Properties();
			jdbc.load(is);
			//3. 通过key-value 的方式得到对应的值
			String ip = jdbc.getProperty("ip");
			//upload.renameTo(uploadchangename);
			//不知道怎么修改file path，下面解决upload的后缀名为tmp，改为保存原有的后缀名
			//临时文件输出地址，输出文件到硬盘上
			//上传图片
			picUrl = FastDFSClient.upLoadImage(getUploadFileName(),getUpload());
		}
		//是否在前台显示出来
		if(flag!=null&&flag.equals("on")){
			 flag1 = 1;
		}else{
			flag1=0;
		}
		
		mood = new Mood(null, content, new Date(),picUrl[1], picUrl[0],flag1);
		ms.addMood(mood);
		ActionContext.getContext().put("prompt_message", "发布成功！！");
		return "moodAdd";
	}
	void imageinit() throws IOException{
		FileOutputStream fos = new FileOutputStream(getUploadFileName());
		FileInputStream fis = new FileInputStream(getUpload());
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0){
			fos.write(buffer , 0 , len);
		}
		fis.close();
		fos.close();
	}
	/*
	 * 后台对说说的操作
	 */
	public String admin() throws FileNotFoundException, Exception{
		//搜索的时候
		if(mood_content!=null&&!mood_content.equals("")){
			selectmood();
			return "moodadmin";
		}
		//点击修改的时候，显示编辑页面
		if(mood_id!=null&&content==null){
			editmood();
			return "moodedit";
		}
		//点击更新的时候，更新数据库
		if(content!=null){
			updatemood();
		}
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Mood.class);
		dc.addOrder(Order.asc("time"));
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = ms.getPageBean(dc,currentPage,pageSize);
		ActionContext.getContext().put("pageBean", pb);
		return "moodadmin";
	}
	public void updatemood() throws FileNotFoundException, Exception{
		if(upload!=null&& ! uploadContentType.substring(0,"image".length()).equals("image")){
			ActionContext.getContext().put("prompt_message", "发布失败，上传的文件不是图片！");
		}
		Mood m = new Mood();
		m.setMood_id(mood_id);
		m.setContent(content);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//小写的mm表示的是分钟  
		Date date=sdf.parse(time);  
		m.setTime(date);
		if(flag==null){
			flag1 = 0;
		}else{
			flag1=Integer.valueOf(flag);
		}
		m.setFlag(flag1);
		//要更新图片的时候
		if(upload!=null){
			//上传图片
			String [] picUrl = FastDFSClient.upLoadImage(getUploadFileName(),getUpload());
			m.setHpicsrc(picUrl[0]);
			m.setPicsrc(picUrl[1]);
		}
		//make
		//这里有问题。。。！！！返回页面有问题
		ms.updateById(m);
		System.out.println("return moodlist");
	}
	/**
	 * 
	 * @param name
	 * @param file1
	 * @return String []  picUrl[0] 原图地址  picUrl[1] 略缩图地址  
	 * @throws Exception
	 */
	//修改文件名字，由于上传图片类型是。tmp ，将图片文件临时放到硬盘上类型为 原来的类型
	String [] upLoadImage(String name , File file1) throws Exception{
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
	public void selectmood(){
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Mood.class);
		dc.add(Restrictions.like("content","%"+mood_content+"%"));
		System.out.println(" mood_content" +  mood_content);
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = ms.getPageBean(dc,currentPage,pageSize);
		ActionContext.getContext().put("pageBean",pb);
	}
	

	public void editmood(){
		ActionContext.getContext().put("mood", ms.getById(mood_id));
	}
}