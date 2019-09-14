<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>后台登录-X-admin1.1</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/admin/css/font.css">
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/admin/css/xadmin.css">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/admin/lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/admin/js/xadmin.js"></script>
<style>
.wrapper {
	position: relative;
	left: 300px;
	top: 100px;
}
</style>
</head>

<body>
	<!-- 中部开始 -->
	<div class="wrapper">
		<!-- 右侧主体开始 -->
		<div class="page-content">
			<div class="content">
				<s:form action="essayadmin" method="post"
					enctype="multipart/form-data">
					flag1：<input type="text" name="flag" class="layui-input"
						value="<s:property value="#essay.flag" />" />
					<br>
					id：<input type="text" name="essay_id" class="layui-input"
						value="<s:property value="#essay.essay_id" />" />
					<br>
					标题：<input type="text" name="title" class="layui-input"
						value="<s:property value="#essay.title" />" />
					<br>
					导语：<input type="text" name="introduction" class="layui-input"
						label="内容" value="<s:property value="#essay.introduction" />" />
					<br>
					图片：<s:if test="#essay.picsrc!=null">
						<img width="100px" name="img"
							src="http:///<s:property value="#essay.picsrc" />">
					</s:if>
					<input type="file" value="<s:property value="#essay.picsrc" />"
						name="pic" class="easyui-validatebox" data-options="required:true" />

					<div class="layui-form-item">
						<s:property value="flag" />
						<button class="layui-btn" style="">更新</button>
					</div>
				</s:form>
				<!-- 右侧内容框架，更改从这里结束 -->
			</div>
		</div>
		<!-- 右侧主体结束 -->
	</div>
	<!-- 中部结束 -->
	<script>
    //网站统计
   /* var _hmt = _hmt || [];
    (function() {
      var hm = document.createElement("script");
 		//每次加载都会进入这个路径
      hm.src = "${ pageContext.request.contextPath }/statistics/add?url=knowledge/001.jsp";
      var s = document.getElementsByTagName("script")[0]; 
     	s.parentNode.insertBefore(hm, s);
    })();*/
    </script>
</body>
</html>