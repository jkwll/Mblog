<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/public/include.jsp"%>
<title></title>
</head>
<body>
	<%@ include file="/public/header.jsp"%>
	<article class="aboutcon">
		<%-- 头的下方--%>
		<%@ include file="/public/header2.jsp"%>
		<div class="index_about">
			<%-- 文章标题 --%>
			<%@ include file="/public/title.jsp"%>
			<div class="book left" style="width: 96.553%;">
				<%-- 正文 --%>
				text123
				<%@ include file="/public/lower.jsp"%>
			</div>
		</div>
		<%@ include file="/public/knowledge_right.jsp"%>
	</article>
	<%@ include file="/public/info.jsp"%>
	<script
		src="${ pageContext.request.contextPath }/js/common.js"></script>
	<script
		src="${ pageContext.request.contextPath }/js/silder.js"></script>
</body>
</html>