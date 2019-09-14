package top.wull.blog.admin.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import top.wull.blog.antity.Essay;
import top.wull.blog.antity.EssayDesc;
import top.wull.blog.antity.EssayType;
import top.wull.blog.antity.Mood;
import top.wull.blog.antity.Statistics;
import top.wull.blog.service.EssayDescService;
import top.wull.blog.service.EssayService;
import top.wull.blog.service.EssayTypeService;
import top.wull.blog.service.StatisticsService;
import top.wull.blog.util.FastDFSClient;
import top.wull.blog.util.PageBean;


@Controller("essayUploadAction")
@Scope("prototype")
public class EssayUploadAction extends ActionSupport
{	
	@Resource(name="essayService")
	EssayService  es ;
	@Resource(name="essayDescService")
	EssayDescService  eds ;
	@Resource(name="essayTypeService")
	EssayTypeService  ets ;
	@Resource(name="statisticsService")
	StatisticsService  ss ;
	Essay essay ;
	Statistics statistics;
	
	//获得Struts2 jsp表单中传来的属性
	private String title;
	private String editor;
	private Integer essay_id;
	private String introduction;
	private String picsrc;
	private String jspFilesrc;
	private String essayType;
	private String sqlurl;//在sql中保存的路径
	private String picsavePath;//同上
	private String jspName;
	// 封装图片文件类型的属性
	private String htmlFileContentType;
	// 封装图片文件名的属性
	private String htmlFileFileName;
	private String keywords;
	private Integer flag;
	private String content;
	// 封装图片文件域的属性
	private File pic;
	// 封装图片文件类型的属性
	private String picContentType;
	// 封装图片文件名的属性
	private String picFileName;	
	// html文本
	private String htmltext;	
	private Integer currentPage = 1;
	private Integer pageSize = 9;	

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
	public String getHtmltext() {
		return htmltext;
	}
	public void setHtmltext(String htmltext) {
		this.htmltext = htmltext;
	}
	public String getJspName() {
		return jspName;
	}
	public void setJspName(String jspName) {
		this.jspName = jspName;
	}
	public String getHtmlFileContentType() {
		return htmlFileContentType;
	}
	public void setHtmlFileContentType(String htmlFileContentType) {
		this.htmlFileContentType = htmlFileContentType;
	}
	public String getHtmlFileFileName() {
		return htmlFileFileName;
	}
	public void setHtmlFileFileName(String htmlFileFileName) {
		this.htmlFileFileName = htmlFileFileName;
	}
	public EssayService getEs() {
		return es;
	}
	public void setEs(EssayService es) {
		this.es = es;
	}
	public Essay getEssay() {
		return essay;
	}
	public void setEssay(Essay essay) {
		this.essay = essay;
	}
	public String getPicsrc() {
		return picsrc;
	}
	public void setPicsrc(String picsrc) {
		this.picsrc = picsrc;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
	public String getEssayType() {
		return essayType;
	}
	public void setEssayType(String essayType) {
		this.essayType = essayType;
	}
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getTitle() {
		return title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return (this.content);
	}

	public void setPic(File pic)
	{
		this.pic = pic;
	}
	public File getPic()
	{
		return (this.pic);
	}

	public Integer getEssay_id() {
		return essay_id;
	}
	public void setEssay_id(Integer essay_id) {
		this.essay_id = essay_id;
	}
	// uploadContentType的setter和getter方法
	public void setPicContentType(String uploadContentType)
	{
		this.picContentType = uploadContentType;
	}
	public String getPicContentType()
	{
		return (this.picContentType);
	}

	// uploadFileName的setter和getter方法
	public void setPicFileName(String picFileName)
	{
		this.picFileName = picFileName;
	}
	public String getPicFileName()
	{
		return (this.picFileName);
	}

	@Override
	//默认执行这个方法
	public String execute() throws Exception
	{
		Date date = new Date();
        if (this.flag == 1) {
            this.sqlurl = "knowledge/";
            this.picsavePath = String.valueOf(ServletActionContext.getServletContext().getRealPath("/")) + "knowledge/static/images";
        } else if (this.flag == 2) {
            this.sqlurl = "life/";
            this.picsavePath = String.valueOf(ServletActionContext.getServletContext().getRealPath("/")) + "life/static/images";
        }
		//保存图片${pageContext.request.contextPath }
        String [] picUrl = {};
		if(this.picFileName!=null){
		/*	FileOutputStream fos = new FileOutputStream(picsavePath
				+ "/" + getPicFileName());
			FileInputStream fis = new FileInputStream(getPic());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0)
			{
				fos.write(buffer , 0 , len);
			}
			fos.close();		
			picsrc = sqlurl+"static/images/"+getPicFileName();*/
			//上传图片
			picUrl = FastDFSClient.upLoadImage(picFileName,pic);
		}
		String str = getContent();	
		content =retrunjsp(str,sqlurl);
		EssayType et  = ets.getById(Integer.parseInt(this.essayType));
		Integer maxId = 0 ;
		maxId  =es.getMaxEssayId();
		EssayDesc ed = new EssayDesc();
		ed.setEssay_id(maxId+1);
		ed.setContent(content);		
		eds.saveEssayDesc(ed);
		
		essay = new Essay( maxId+1,  title,  date,  editor,  introduction,  et,
				picUrl[1],  sqlurl+(maxId+1),  keywords,  flag, null,  0);
        this.essay.setEssayType(et);
        this.es.addEssay(this.essay);
        System.out.println("\n" + this.toString() + "\n\n" + this.essay.toString());
		return "essayAdd";
	}
	//返回jsp文件的代码 保存在数据库。此方法不通用，待进一步开发
	public String retrunjsp(String str,String sqlurl) throws IOException{
		//自动生成jsp
		 InputStreamReader isr ;
		 if(sqlurl.equals("knowledge/")){
			 isr = new InputStreamReader(new FileInputStream(ServletActionContext.getServletContext().getRealPath("/")+"knowledge/knowledgebase.jsp"), "utf-8");			 
		 }else{
			 isr = new InputStreamReader(new FileInputStream(ServletActionContext.getServletContext().getRealPath("/")+"life/lifebase.jsp"), "utf-8");			 
		 }
		char[] cbuf = new char[10];
		int hasRead = 0;
		String wstr = new String();
		//读取base.jsp文件
		while ( (hasRead = isr.read(cbuf) )> 0) // 这里一次读取32个字符char，每次运行fr.read(cbuf)，起点就会后移32个字符
		{
			wstr = wstr + new String(cbuf , 0 , hasRead );
		}
		//设置jsp页面的title
		int n = wstr.indexOf("</title>");
		wstr = wstr.substring(0, n) + title + wstr.substring(n, wstr.length());
		//初始化输入
		String url = ServletActionContext.getServletContext().getRealPath("/")+sqlurl+jspName+".jsp";
		/*File jspfile = new File(url);
		FileOutputStream jspfos = new FileOutputStream(jspfile);
		OutputStreamWriter  osw = new OutputStreamWriter(jspfos,"UTF-8");//初始化输出流
		if (!jspfile.exists()) {
			jspfile.createNewFile();
		}*/
		///处理页面传来的 str   保证能够找到图片，如果有值，就在前面添加${ pageContext.request.contextPath }/
		String[] s = str.split("ueditor/jsp/upload");
		str = "";
		n = s.length;
		for(int i = 0 ; i < s.length-1; i++){
			s[i]=s[i]+"${ pageContext.request.contextPath }/ueditor/jsp/upload";
			str =str+s[i];
		}		
		str = str +s[s.length-1];
		//让<img> 居中
		n = str.indexOf("<img src");
		while(n!=-1){
			int m = str.indexOf(">", n);
			str = str.substring(0, n)
			+"<div align=\"center\">" + str.substring(n, m+1) 
			+ "</div>" + str.substring(m+1,str.length());			
			n = str.indexOf("<img src",m+1);
		}
		n = wstr.indexOf("text123");
		wstr = wstr.substring(0, n)+str+wstr.substring(n+"text123".length(), wstr.length());
		return wstr;
	}
/**
 * 弃用
 * @param str 文章的html内容
 * @param sqlurl 保存在数据库中的url
 * @return
 * @throws Exception 
	 */
	public String admin() throws Exception{
		//后台查询的时候
		if(title!=null&&!title.equals("")&&introduction==null){
			selectessay();
			return "essayadmin";
		}
		//点击修改的时候
		if(essay_id!=null&&introduction==null){
			editessay();
			return "essayedit";
		}
		//更新
		if(introduction!=null){
			updateEssay();
		}
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Essay.class);
		dc.addOrder(Order.asc("time"));
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = es.getPageBean(dc,currentPage,pageSize);
		ActionContext.getContext().put("pageBean", pb);
		return "essayadmin";
	}
	public void selectessay(){
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Essay.class);
		dc.add(Restrictions.like("title","%"+title+"%"));
		//1 调用Service查询分页数据(PageBean)
		System.out.println(currentPage);
		System.out.println(pageSize);
		PageBean pb = es.getPageBean(dc,currentPage,pageSize);
		ActionContext.getContext().put("pageBean",pb);
	}
	//更新
	public void updateEssay() throws Exception{
		Essay e = new Essay();
		e.setEssay_id(essay_id);
		e.setIntroduction(introduction);
		e.setTitle(title);
		if(flag==null){
			flag = 0;
		}
		e.setFlag(flag);
		//要更新图片的时候
		if(pic!=null){
			// 以服务器的文件保存地址和原文件名建立上传文件输出流
/*			String picsrc = ServletActionContext.getServletContext().getRealPath("/webfile/images");

			FileOutputStream fos = new FileOutputStream(picsrc+
					 "\\" + getPicFileName());
			FileInputStream fis = new FileInputStream(getPic());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0)
			{
				fos.write(buffer , 0 , len);
			}
			fos.close();
			picsrc = "webfile/images"+"/"+getPicFileName();
*/
			//上传图片
			String [] picUrl = FastDFSClient.upLoadImage(getPicFileName(),getPic());

			e.setPicsrc(picUrl[1]);
		}
		es.updateById(e);
	}
	public void editessay(){
		ActionContext.getContext().put("essay", es.getById(essay_id));
	}
	/**
	 * essayadd.jsp页面加载，用到的方法
	 * @return
	 */
	public String add(){
		List<EssayType> etlist  = ets.getAllEssayType();
		
		for (EssayType essayType : etlist) {			
			System.out.println("essayType.getName()" + essayType.getName());
		}
		ActionContext.getContext().put("etList", etlist);
		return "essayadd";
	}
/**
 * test
 * @return
 */
		public String add2(){
		List<EssayType> etlist  = ets.getAllEssayType();
		
		for (EssayType essayType : etlist) {			
			System.out.println("essayType.getName()" + essayType.getName());
		}
		ActionContext.getContext().put("etList", etlist);
		return "essayadd2";
	}
	
}