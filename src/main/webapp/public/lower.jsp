<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="keybq">
	<p>
		<span>关键字词： </span>
		<s:property value="#lookEssay.keywords" />
	</p>
</div>
<%--    <div class="nextinfo">
		<p>上一篇：<a href='/life/photos/59.html'>帝都·天桥夜色</a></p>
		<p>下一篇：<a href='/life/photos/'>返回列表</a></p>
    </div>
     --%>
<div class="otherlink">
	<h2>相关文章</h2>
	<ul>无相关信息
	</ul>
</div>

<div class="news_pl">
      <h2>文章评论</h2>
      				<!--PC版-->
			<div id="SOHUCS" sid="<s:property value="#lookEssay.essay_id" />"> </div>
			<script charset="utf-8" type="text/javascript"
				src="https://changyan.sohu.com/upload/changyan.js"></script>
			<script type="text/javascript">
				window.changyan.api.config({
				appid: 'cytmzVekL',
				conf: 'prod_ce79614696b4fc22a0f61cdccd5a03c3'
				});
				</script>
</div>