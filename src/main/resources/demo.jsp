<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>

<head>
<title>ueditor demo</title>
<script type="text/javascript">
        function fun1(){
        	alret("11111");
        }
    </script>

</head>
<body>
	<div style="width: 70%">
		<form action="${ pageContext.request.contextPath }/Update"
			method="post">
			<!-- 加载编辑器的容器 -->
			<script id="container" name="content" type="text/plain">
            这里写你的初始化内容，无关紧要的修改，test github 123
        </script>
			<input type="submit" onclick="fun1()" value="提交">
		</form>
	</div>
	<!-- 配置文件 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/admin/js/UEditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/admin/js/UEditor/ueditor.all.js"></script>
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
        var editor = UE.getEditor('container');
    </script>
</body>
</html>