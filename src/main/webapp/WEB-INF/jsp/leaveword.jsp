<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script async="" charset="utf-8" src="./吴亮亮留言板_files/getConfig"></script>
<title>吴亮亮个人博客网站—个人博客网站</title>
<meta name="keywords" content="yangqq留言板">
<meta name="description" content="yangqq留言板">
<link href="${ pageContext.request.contextPath }/css/base.css"
	rel="stylesheet">
<link href="${ pageContext.request.contextPath }/css/book.css"
	rel="stylesheet">
<!--[if lt IE 9]>
<script src="/skin/2014/js/modernizr.js"></script>
<![endif]-->
</head>
<body>
	<%@ include file="/public/header.jsp"%>
	<article class="aboutcon">
		<h1 class="t_nav">
			<span>你，生命中最重要的过客，之所以是过客，因为你未曾为我停留。</span> <a
				href="http://wll.kuvip.top/" class="n1">网站首页</a><a
				href="http://wll.kuvip.top/e/tool/gbook/?bid=1#" class="n2">留言版</a>
		</h1>
		<div class="book left">
			<!--高速版-->
			<!--PC版-->
			<div id="SOHUCS" sid="00101"></div>
			<script charset="utf-8" type="text/javascript"
				src="https://changyan.sohu.com/upload/changyan.js"></script>
			<script type="text/javascript">
				window.changyan.api.config({
				appid: 'cytmzVekL',
				conf: 'prod_ce79614696b4fc22a0f61cdccd5a03c3'
				});
				</script>
		</div>
		<aside class="right">
			<div class="about_c">
				<p>
					网名：<span>DanceSmile</span> | 即步非烟
				</p>
				<p>姓名：吴亮亮</p>
				<p>籍贯：湖南省</p>
				<p>现居：湘潭市—岳塘</p>
				<p>职业：学生</p>
				<!-- a target="_blank"
						href="http://wp.qq.com/wpa/qunwpa?idkey=d4d4a26952d46d564ee5bf7782743a70d5a8c405f4f9a33a60b0eec380743c64">
						<img src="http://pub.idqqimg.com/wpa/images/group.png"
						alt="吴亮亮个人博客网站" title="吴亮亮个人博客网站">
					</a> <a target="_blank"
						href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&amp;email=HHh9cn95b3F1cHVye1xtbTJ-c3E"><img
						src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_22.png"
						alt="吴亮亮个人博客网站"></a -->
			</div>
		</aside>
	</article>
	<%@ include file="/public/info.jsp"%>
</body>
</html>