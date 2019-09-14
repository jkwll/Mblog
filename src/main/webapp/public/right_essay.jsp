<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h3>

	<p>
		最新<span>文章</span>
	</p>
</h3>
<ul class="rank">
	<s:iterator var="e" value="#newEssaylist" status="status">
		<li><a href="${pageContext.request.contextPath}/<s:property value="#e.url" />"
			title="<s:property value="#e.title" />" target="_blank"> <s:property
					value="#e.title" />
		</a></li>
	</s:iterator>
</ul>
<h3 class="ph">
	<p>
		点击<span>排行</span>
	</p>
</h3>
<ul class="paih">
	<s:iterator var="m" value="#showMaxCountEssay" status="status">
		<li><a href="${pageContext.request.contextPath}/<s:property value="#m.url" />"
			title="<s:property value="#m.content" />" target="_blank"><s:property
					value="#m.title" /></a></li>
	</s:iterator>
</ul>