<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/public/include.jsp"%>
<title>21321</title>
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
				<p><div align="center"><img src="http://img.baidu.com/hi/jx2/j_0028.gif"/></div>
 &nbsp;				 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;		这里是文章的内容
 &nbsp; &nbsp; &nbsp; &nbsp;						</p>
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