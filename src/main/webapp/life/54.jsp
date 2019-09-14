<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/public/include.jsp"%>
<title>小时候感觉很搞笑，长大以后却很心酸！</title>
</head>
<body>
	<%@ include file="/public/header.jsp"%>
	<article class="aboutcon">
		<%-- 头的下方--%>
		<%@ include file="/public/header2.jsp"%>
		<div class="index_about">
			<%-- 文章标题 --%>
			<%@ include file="/public/title.jsp"%>
			<div class="book left" style="width: 96.553%">
				<%-- 正文 --%>
				<p style="text-align: center;"><embed type="application/x-shockwave-flash" class="edui-faked-video" pluginspage="http://www.macromedia.com/go/getflashplayer" src="http://player.youku.com/player.php/sid/XMzM2NzUzNjYwOA==/v.swf" width="810" height="500" wmode="transparent" play="true" loop="false" menu="false" allowscriptaccess="never" allowfullscreen="true"/></p><p><span style="color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 36px; font-weight: bold; background-color: rgb(255, 255, 255);">论背景音乐的重要性！！</span></p>
				<%@ include file="/public/lower.jsp"%>
			</div>
		</div>
		<%@ include file="/public/life_right.jsp"%>
	</article>
	<%@ include file="/public/info.jsp"%>
	<script
		src="${ pageContext.request.contextPath }/knowledge/js/common.js"></script>
	<script
		src="${ pageContext.request.contextPath }/knowledge/js/silder.js"></script>
</body>
</html>