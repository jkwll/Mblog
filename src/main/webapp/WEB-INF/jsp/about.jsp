<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta charset="utf-8">
<title>吴亮亮个人博客网站—一个在二进制田野上耕耘的农民个人博客网站</title>
<meta name="keywords" content="个人博客,吴亮亮个人博客,个人博客模板,吴亮亮" />
<meta name="description" content="一个正在学习路上的程序员个人博客网站" />
<link href="${ pageContext.request.contextPath }/css/base.css"
	rel="stylesheet">
<link href="${ pageContext.request.contextPath }/css/about.css"
	rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
</head>
<body>
	<%@ include file="/public/header.jsp"%>
	<article class="aboutcon">
		<h1 class="t_nav">
			<span>像“草根”一样，紧贴着地面，低调的存在，冬去春来，枯荣无恙。</span><a href="/" class="n1">网站首页</a><a
				href="/" class="n2">关于我</a>
		</h1>
		<div class="about left">
			<h2>Just about me</h2>
			<ul>
				<p>关于我，从一个混迹网络多年的网瘾少年！到最后的沉迷于编程，无法自拔，日渐消瘦的程序猿。正努力走向架构师。</p>

<p>跟很多95后IT同龄人一样，喜欢折腾，喜欢研究。用技术解决实际需求，能够给我无比的快感。</p>
<p>其实很早就想单独开立自己的博客，但是由于各种原因。。所以。。。emmmmm</p>

<p>在大学里，从最初一个单词一个单词的敲击，到现在每日上百上千代码的输出。也算是一个不小的成长。</p>

<p>互联网领域的知识学无止境，我也是一步步各种新的技术，get各种新的技能来提升自身能力，丰富博客的内容。</p>

<p>不说了，写毕业论文去了。。</p>

<p>--寄语--：流年笑掷 未来可期</p>


			</ul>
			<h2>About my blog--博客系统还在测试中</h2>
			<p>
				域 名：www.wull.top  创建于2017年11月  <a href="https://wanwang.aliyun.com" class="blog_link"
					target="_blank">注册域名</a>
			</p>
			<p>
				服务器：阿里云服务器<a href="https://www.aliyun.com" class="blog_link" target="_blank">购买空间</a>
			</p>
		</div>
		<aside class="right">
			<div class="about_c">
				<p>
					网名：<span>吴小亮</span>
				</p>
				<p>姓名：吴亮亮</p>
				<p>生日：1996-10-19</p>
				<p>籍贯：湖南省</p>
				<p>现居：湘潭市—岳塘区</p>
				<p>爱好：编程、魔术、音乐</p>
				<!--  a target="_blank"
					href="http://wp.qq.com/wpa/qunwpa?idkey=d4d4a26952d46d564ee5bf7782743a70d5a8c405f4f9a33a60b0eec380743c64">
					<img src="http://pub.idqqimg.com/wpa/images/group.png"
					alt="吴亮亮个人博客网站" title="吴亮亮个人博客网站">
				</a> <a target="_blank"
					href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&amp;email=HHh9cn95b3F1cHVye1xtbTJ-c3E"><img
					src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_22.png"
					alt="吴亮亮个人博客网站"></a-->
			</div>
		</aside>
	</article>
	<%@ include file="/public/info.jsp"%>
	<script src="${ pageContext.request.contextPath }/js/silder.js"></script>
</body>
</html>