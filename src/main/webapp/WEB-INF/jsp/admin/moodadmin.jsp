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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
function changePage(pageNum){
	//1 将页码的值放入对应表单隐藏域中
		$("#currentPageInput").val(pageNum);
	//2 提交表单
		$("#pageForm").submit();
};

function changePageSize(pageSize){
	//1 将页码的值放入对应表单隐藏域中
	$("#pageSizeInput").val(pageSize);
//2 提交表单
	$("#pageForm").submit();
};

function selectCustomer(cust_id,cust_name){
//获得添加页面的window对象
var win = window.opener;
//获得添加页面的document对象
var doc = win.document;
//获得隐藏域,和 文本框,并赋值
doc.getElementById("cust_id").value=cust_id;
doc.getElementById("cust_name").value=cust_name;
//关闭当前窗口
window.close();
};
</script>
</head>
<body>
	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
			<TR>
				<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
					<TR>
						<TD class=manageHead>当前位置：说说 &gt; 说说列表</TD>
					</TR>
					<TR>
						<TD height=2></TD>
					</TR>
				</TABLE>
				<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0 width="100%"
					align=center border=0>
					<TBODY>
						<TR>
							<TD height=25>
								<FORM id="pageForm" name="customerForm"
									action="${pageContext.request.contextPath }/admin/moodadmin.action"
									method=post>
									<!-- 隐藏域.当前页码 -->
									<input type="hidden" name="currentPage" id="currentPageInput"
										value="<s:property value="#pageBean.currentPage" />" />
									<!-- 隐藏域.每页显示条数 -->
									<input type="hidden" name="pageSize" id="pageSizeInput"
										value="<s:property value="#pageBean.pageSize" />" />
									<!-- 放置是否需要选择的标记参数 -->
									<input type="hidden" name="select"
										value="<s:property value="#parameters.select" />" />
									<TABLE cellSpacing=0 cellPadding=2 border=0>
										<TBODY>
											<TR>
												<TD>筛选：</TD>
												<TD><INPUT class=textbox id=sChannel2
													style="WIDTH: 80px" maxLength=50 name="mood_content"
													value="${param.cust_name}"></TD>
												<TD><INPUT class=button id=sButton2 type=submit
													value=" 筛选 " name=sButton2></TD>
											</TR>
										</TBODY>
									</TABLE>
								</FORM>
							</TD>
						</TR>

						<TR>
							<TD>

								<TABLE id=grid
									style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
									cellSpacing=1 cellPadding=2 rules=all border=0>
									<TBODY>
										<TR
											style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
											<TD>说说id</TD>
											<TD>说说内容</TD>
											<TD>说说time</TD>
											<TD>说说flag</TD>
											<TD>操作</TD>
										</TR>
										<s:iterator var="m" value="#pageBean.list" status="status">
											<TR
												style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
												<TD width="5%"><s:property value="#m.mood_id" /></TD>
												<TD width="30%"><input type="text" name="content"
													value="<s:property value="#m.content" />"></TD>
												<TD width="18%"><input type="text" name="time"
													value="<s:property value="#m.time" />"></TD>
												<TD width="18%"><s:property value="#m.flag" /></TD>
												<TD>
												<a href="${pageContext.request.contextPath}/admin/moodadmin.action?mood_id=<s:property value="#m.mood_id" />">
												修改</a>
												</TD>
											</TR>
										</s:iterator>

									</TBODY>
								</TABLE>

							</TD>
						</TR>

						<TR>
							<TD><SPAN id=pagelink>
									<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
										共[<B><s:property value="#pageBean.totalCount" /> </B>]条记录,[<B><s:property
												value="#pageBean.totalPage" /></B>]页 ,每页显示
										<%-- changePageSize($('#pageSizeSelect option').filter(':selected').val()) --%>
										<select name="pageSize"
											onchange="changePageSize($('#pageSizeSelect option:selected').val())"
											id="pageSizeSelect">
											<option value="10"
												<s:property value="#pageBean.pageSize==10?'selected':''" />>10</option>
											<option value="20"
												<s:property value="#pageBean.pageSize==20?'selected':''" />>20</option>
										</select> 条 [<A href="javaScript:void(0)"
											onclick="changePage(<s:property value='#pageBean.currentPage-1' />)">前一页</A>]
										<B><s:property value="#pageBean.currentPage" /></B> [<A
											href="javaScript:void(0)"
											onclick="changePage(<s:property value='#pageBean.currentPage+1' />)">后一页</A>]
										到 <input type="text" size="3" id="page" name="page"
											value="<s:property value="#pageBean.currentPage" />" /> 页 <input
											type="button" value="Go"
											onclick="changePage($('#page').val())" />
									</DIV>
							</SPAN></TD>
						</TR>
					</TBODY>
				</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	<s:debug></s:debug>
</body>
</html>