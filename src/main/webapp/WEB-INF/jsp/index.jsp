<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="utf-8">
<title>吴亮亮个人博客-分享java技术的个人博客网站</title>
<meta name="keywords" content="个人博客,亮亮,吴亮亮,博客,技术,java" />
<meta name="description" content="吴亮亮个人博客-分享java技术的个人博客网站" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/base.css" type="text/css" />
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/index.css" type="text/css" />
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/my.css" type="text/css" />
<script>
var i = 0;
function mOver(){
	var cat = document.getElementById("cat");
	cat.src="./images/cat07.png";
}
function mOut()
{
	var cat = document.getElementById("cat");
	cat.src="./images/cat10.png";
}
k=1;
function no_off(){
	if(k==0){
		document.getElementById('music').pause();
		//alert("暂停了");
		//document.getElementById('imgkey').src="images/播放.png";
		k=1;
		return false;
	}else{
		document.getElementById('music').play();
		//alert("播放");
		//document.getElementById('imgkey').src="images/暂停.png";
		k=0;
		return false;
	}
}	
</script>
<%-- <script type="text/javascript" src="${ pageContext.request.contextPath }/js/music.js" />
 --%>

</head>
<body>

	<%@ include file="/public/header.jsp"%>
	
	<div class="banner">
		<section class="box">
			<ul class="texts">
				<p>梦想还是得有的，万一实现了呢！</p>
				<p>人若虚度光阴，跟咸鱼有什么区别</p>
				<p>没有舞台就自己搭一座</p>
			</ul>
			<div class="avatar">
				<a href="#"><span>吴亮亮</span></a>
			</div>
		</section>
	</div>
	<div class="template">
		<div class="box">
			<h3>
				<p>
					<span>最新</span>动态
				</p>
			</h3>
			<ul>
				<s:iterator var="e" value="#news" status="status">
					<li><a href="<s:property value="#e.url" />" target="_blank"><img
							src="http://<s:property value="#e.picsrc" />"></a><span><s:property
								value="#e.title" /></span></li>
				</s:iterator>
<!-- 				<li>
<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" width=298 height=52 src="//music.163.com/outchain/player?type=2&id=165237&auto=1&height=32"></iframe>				
				</li>
 -->		</div>
	</div>

	<div id="main">
		<div id="ccat">
			<a href="javascript:void(0)"> <img id="cat"
				src="${ pageContext.request.contextPath }/images/cat10.png"
				onmouseover="mOver(this)" onmouseout="mOut(this)" onclick="no_off();">
			</a>
		</div>
	</div>

	<article>
		<h2 class="title_tj">
			<p>
				文章<span>推荐</span>
			</p>
		</h2>
		<div class="bloglist left">
			<s:iterator var="e" value="#pageBean.list" status="status">
				<h3>
					<s:property value="#e.title" />
				</h3>
				<figure>
					<img src="http://<s:property value="#e.picsrc" />">
				</figure>
				<ul>
					<p>
						<s:property value="#e.introduction" />
						
					</p>
					<a href="<s:property value="#e.url" />" target="_blank"
						class="readmore">阅读全文>></a>
				</ul>
				<p class="dateview">
					<span>&nbsp;<s:property value="#e.time" />
					</span><span>作者：<s:property value="#e.editor" /></span><span>个人博客：[
						<a><s:property value="#e.essayType.name" /></a> ]
					</span>
				</p>
			</s:iterator>
			<%@ include file="/public/changepage.jsp"%>
		</div>
		<aside class="right">
			<div class="weather">
				<iframe width="250" scrolling="no" height="60" frameborder="0"
					allowtransparency="true"
					src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=1">
				</iframe>
			</div>
			<div class="news">

				<%@ include file="/public/right_essay.jsp"%>

				<h3 class="links">
					<p>
						友情<span>链接</span>
					</p>
				</h3>
				<ul class="website">
					<li><a href="/">个人博客123</a></li>
				</ul>
			</div>
			<!-- 
			分享外链
			Baidu Button BEGIN 
			<div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare">
				<a class="bds_tsina"></a><a class="bds_qzone"></a><a class="bds_tqq"></a><a
					class="bds_renren"></a><span class="bds_more"></span><a
					class="shareCount"></a>
			</div>
			-->
			<script type="text/javascript" id="bdshare_js"
				data="type=tools&amp;uid=6574585"></script>
			<script type="text/javascript" id="bdshell_js"></script>
			<script type="text/javascript">
				document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion="
						+ Math.ceil(new Date() / 3600000)
			</script>
			<!-- 微信Baidu Button END <a href="/" class="weixin"> </a>-->

		</aside>
	</article>
	<%@ include file="/public/info.jsp"%>
	<script src="${ pageContext.request.contextPath }/js/silder.js"></script>
	<audio id="music"  style="display:none"  src="<s:property value="#music" />" controls="controls">
	
</body>
</html>
