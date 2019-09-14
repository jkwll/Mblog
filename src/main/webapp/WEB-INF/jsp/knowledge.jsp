<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="utf-8">
<title>吴亮亮个人博客网站—个人博客网站</title>
<meta name="keywords" content="个人博客,吴亮亮个人博客,个人博客模板,吴亮亮" />
<meta name="description"
	content="吴亮亮个人博客，是一个站在web前端设计之路的男程序员个人网站。" />
<link href="${pageContext.request.contextPath }/css/base.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/learn.css"
	rel="stylesheet">
<%-- <link href="${pageContext.request.contextPath }/css/learn.css"
	rel="stylesheet">
 --%><%-- 搜索 --%>
<link href="${ pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet">
<%-- 换页 --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>

<script type="text/javascript">
	function changePage(pageNum){
		//1 将页码的值放入对应表单隐藏域中
			$("#currentPageInput").val(pageNum);			

		//2 提交表单
			$("#pageForm").submit();
	};
</script>
</head>
<body>
	<form id="pageForm" name="customerForm"
		action="${pageContext.request.contextPath}/knowledge1<s:property value="#changeurl" />"
		method=post>
		<!-- 隐藏域.当前页码 -->
		<input type="hidden" name="currentPage" id="currentPageInput"
			value="<s:property value="#pageBean.currentPage" />" />
	</form>
	<%@ include file="/public/header.jsp"%>
	<article class="blogs">
		<h1 class="t_nav">
			<span>我们长路漫漫，只因学无止境。 </span><a href="/" class="n1">网站首页</a><a
				href="/" class="n2">学无止境</a>
		</h1>

		<%@ include file="/public/essay_list.jsp"%>

		<%@ include file="/public/knowledge_right.jsp"%>
	</article>
	<%@ include file="/public/info.jsp"%>

	<script src="js/silder.js"></script>
</body>
</html>