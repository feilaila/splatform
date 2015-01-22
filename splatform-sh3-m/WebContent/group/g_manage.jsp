<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include.jsp"%>


<!-- include HEAD -->
<!-- BEGIN HEAD -->
<%@ include file="../main/header.jsp" %>
<!-- END HEAD -->



<!-- BEGIN BODY -->
<body class="padTop53 " >

        
	<div id="wrap">
        <%@ include file="../main/top.jsp" %>
        <!-- END HEADER SECTION -->



        <%@ include file="../main/left.jsp" %>
        <!--END MENU SECTION -->



        <!--PAGE CONTENT -->
        <div id="content">
            <div class="inner" style="min-height:1200px;">
                <div class="row">
                    <div class="col-lg-12">
                    	
                    	<div class="row">
                    <div class="col-lg-12">
	                        <h5>系统管理-组织管理</h5>
	                    </div>
	                </div>
                <hr />
                        <div class="table-responsive">
										<table id="sample-table-1"
											class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">序号</th>
													<th>组织名称</th>
													<th>描述</th>
													<th>添加时间</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${groupList}" var="sysGroup"
													varStatus="status">
													<tr>
														<td>${sysGroup.id}</td>
														<td>${sysGroup.groupName}</td>
														<td>${sysGroup.groupDesc}</td>
														<td>
															<fmt:parseDate value="${sysGroup.createTime}" pattern="yyyyMMddHHmmss" var="date"/>
															 <fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td>														
														<c:if test="${sysUser.status == 1}">
															<a data-toggle="modal" href="#suserEdit"
															onClick="editSuser('${sysUser.createTime}','${sysUser.username}','${sysUser.validTime}','${sysUser.terminalId}','${sysUser.email}','${sysUser.name}','${appUser.status}','${sysUser.uid}','${sysUser.roleId }');"
															class="btn btn-xs btn-primary"><i class="icon-edit"></i></a>
															<a data-toggle="modal" href="#suserDel"
																onClick="delSuser('${sysUser.uid}','${sysUser.username}','${sysUser.roleId }');"
																class="btn btn-xs btn-danger"><i class="icon-trash"></i></a>
														</c:if>
															
														<c:if test="${sysUser.status == 9}">
															<a data-toggle="modal" href="#"
															onClick=""
															class="btn btn-xs btn-gray"><i class="icon-edit"></i></a>
															<a data-toggle="modal" href="#"
																onClick=""
																class="btn btn-xs btn-gray"><i class="icon-trash"></i></a>
														</c:if>
														</td>
													</tr>
												</c:forEach>

												<c:if test="${page.totalRowNum>0}">
													<c:if test="${page.totalRowNum >= pageSize}">
														<tr class="page_c">
															<td colspan="9">${page.display}</td>
														</tr>
													</c:if>
												</c:if>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
                    </div>
                </div>
                
            </div>
        </div>
       <!--END PAGE CONTENT -->
	</div>
    <!--END MAIN WRAPPER -->
	
    <!-- FOOTER -->
    <div id="footer">
        <p>&copy;  binarytheme &nbsp;2014 &nbsp;</p>
    </div>
    <!--END FOOTER -->


    <!-- GLOBAL SCRIPTS -->
    <script src="<%=path%>/static/assets/plugins/jquery-2.0.3.min.js"></script>
    <script src="<%=path%>/static/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path%>/static/assets/plugins/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <!-- END GLOBAL SCRIPTS -->

    <!-- PAGE LEVEL SCRIPTS -->
    <script src="<%=path%>/static/assets/plugins/flot/jquery.flot.js"></script>
    <script src="<%=path%>/static/assets/plugins/flot/jquery.flot.resize.js"></script>
    <script src="<%=path%>/static/assets/plugins/flot/jquery.flot.time.js"></script>
    <script src="<%=path%>/static/assets/plugins/flot/jquery.flot.stack.js"></script>
    <script src="<%=path%>/static/assets/js/for_index.js"></script>
    <!-- END PAGE LEVEL SCRIPTS -->
</body>
    <!-- END BODY -->
</html>
