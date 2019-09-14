<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="newblog left">
	<s:iterator var="e" value="#pageBean.list" status="status">
		<h2>
			<b><s:property value="#e.title" /></b>
		</h2>
		<p class="dateview">
			<span>发布时间：<s:property value="#e.time" /></span><span>作者：<s:property
					value="#e.editor" /></span><span>分类：[
<%--因为地址风格是      	knowledge/**    导致life不能通用，暂时这里先不用超链接				<a href="${ pageContext.request.contextPath }/knowledge/select<s:property value="#e.essayType.url" />.action">
					</a>
					
 --%>				
 							<a><s:property value="#e.essayType.name" /></a>
					
						]
			</span>
		</p>
		<figure>
			<s:if test="#e.picsrc!=null">
				<img name="img" src="http://<s:property value="#e.picsrc" />">
			</s:if>
		</figure>
		<ul class="nlist">
			<p>
				<s:property value="#e.introduction" />
			</p>

			<a href="<s:property value="#e.url" />" class="readmore">详细信息>></a>
		</ul>
		<div class="line"></div>
	</s:iterator>
	<div class="blank"></div>
	<div class="ad">
		<!-- 可以放广告 -->
		<img src="">
	</div>

	<%@ include file="/public/changepage.jsp"%>
</div>