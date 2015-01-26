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
													<th>角色</th>
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
															<c:forEach items="${sysGroup.roles}" var="role"
													varStatus="status">
																${role.roleName}
															</c:forEach>
														</td>
														<td>														
															<a 
															onClick="editSgroup('${sysGroup.id}');"
															class="btn btn-xs btn-primary"><i class="icon-edit"></i></a>
															<a 
																onClick="delSgroup('${sysGroup.id}','${sysGroup.groupName}');"
																class="btn btn-xs btn-danger"><i class="icon-trash"></i></a>			
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
								<!-- /row -->
							<div
								style="display: inline-block; background-repeat: no-repeat; border-width: 4px; font-size: 13px; line-height: 1.39; padding: 4px 9px;">
								<a onclick="addSgroup();" href="javascript:;" class="btn btn-success btn-sm">添加</a>
							</div>
							<div class="hr hr-18 dotted hr-double"></div>
                    </div>
                </div>
                
            </div>
        </div>
       <!--END PAGE CONTENT -->
	</div>
    <!--END MAIN WRAPPER -->
	
    <!-- FOOTER -->
    <div id="footer">
        <p>&copy;  splatform-h5 &nbsp;2015 &nbsp;</p>
    </div>
    <!--END FOOTER -->


    <!-- GLOBAL SCRIPTS -->
    <script src="<%=path%>/static/assets/plugins/jquery-2.0.3.min.js"></script>
    <script src="<%=path%>/static/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path%>/static/assets/plugins/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <!-- END GLOBAL SCRIPTS -->
    
    <!-- zDialog -->
	<script src="<%=path %>/static/js/zdialog/zDialog.js"></script>
	<script src="<%=path %>/static/js/zdialog/zDrag.js"></script>
    <!-- zDialog-->
</body>
    <!-- END BODY -->
    
    <script type="text/javascript">
    
  	//组织新增
    var addSgroup = function(){
    		var diag = new zDialog();
    		diag.Height = 400;
        	diag.Title = "系统管理-组织新增";
        	diag.URL = "<%=path %>/toAddGroup.do";
        	diag.OKEvent = function(){diag.innerDoc.getElementById('addForm').submit();diag.submited=true;};//点击确定后调用的方法
        	diag.OnLoad=function(){
        		if(diag.submited){
        			diag.openerWindow.location.reload();
                    try{
        				diag.close();
                    }catch(e){}
        		}
        	};
        	diag.CancelEvent = function(){diag.close();};
        	diag.show();
    }
    
    //在父页面提交iframe中的表单
    //组织编辑
    var editSgroup = function(id){
    		var diag = new zDialog();
    		diag.Height = 400;
    		diag.Title = "系统管理-组织编辑";
        	diag.URL = "<%=path %>/toEditGroup.do?gid="+id;
        	diag.OKEvent = function(){diag.innerDoc.getElementById('editForm').submit();diag.submited=true;};//点击确定后调用的方法
        	diag.OnLoad=function(){
        		if(diag.submited){
        			diag.openerWindow.location.reload();
                    try{
        				diag.close();
                    }catch(e){}
        		}
        	};
        	diag.CancelEvent = function(){diag.close();};
        	diag.show();
    }
    
    
    //组织删除
    var delSgroup= function(id,groupName){
    	$('#del-groupId').val(id);
    	zDialog.confirm('警告：您确认要删除组织['+groupName+']吗？',function(){
    		document.getElementById('delForm').submit();diag.close();
    	});
    }
    </script>
    
<form id="delForm" name="delForm" method="post" action="doDelGroup.do" target="thisFrame">
	<input type="hidden" id="del-groupId" name="groupId">
</form>
<iframe style="display: none" name="thisFrame"></iframe>
</html>
