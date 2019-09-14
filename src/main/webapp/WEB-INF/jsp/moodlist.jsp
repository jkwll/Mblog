<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="utf-8">
<title>吴亮亮个人博客网站—个人博客网站</title>
<meta name="keywords" content="个人博客,吴亮亮个人博客,个人博客模板,吴亮亮" />
<meta name="description"
	content="吴亮亮个人博客，是一个站在web前端设计之路的男程序员个人网站，提供个人博客模板免费资源下载的个人原创网站。" />
<link href="${ pageContext.request.contextPath }/css/base.css"
	rel="stylesheet">
<link href="${ pageContext.request.contextPath }/css/mood.css"
	rel="stylesheet">
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
		action="${pageContext.request.contextPath}/mood.action" method=post>
		<!-- 隐藏域.当前页码 -->
		<input type="hidden" name="currentPage" id="currentPageInput"
			value="<s:property value="#pageBean.currentPage" />" />
	</form>
	<%@ include file="/public/header.jsp"%>
	<div class="moodlist">
		<h1 class="t_nav">
			<span>删删写写，回回忆忆，虽无法行云流水，却也可碎言碎语。</span><a href="/" class="n1">网站首页</a><a
				href="/" class="n2">碎言碎语</a>
		</h1>
		<div class="bloglist">
			<!-- 迭代遍历list -->
			<s:iterator var="m" value="#pageBean.list" status="status">
				<ul class="arrow_box">
					<div class="sy">
						<s:if test="#m.picsrc!=null">
							<a href="http://<s:property value="#m.hpicsrc" />">
								<img name="img" src="http://<s:property value="#m.picsrc" />">
							</a>
						</s:if>
						<p>
							<s:property value="#m.content" />
						</p>
					</div>
					<span class="dateview"><s:property value="#m.time" /></span>
				</ul>
			</s:iterator>

		</div>
		<%@ include file="/public/changepage.jsp"%>
	</div>
	<%@ include file="/public/info.jsp"%>

	<script src="${ pageContext.request.contextPath }/js/silder.js"></script>
</body>
</html>