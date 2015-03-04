<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include.jsp"%>


<!-- include HEAD -->
<!-- BEGIN HEAD -->
<%@ include file="../main/header.jsp" %>
<style type="text/css">
	.form-control{
		background-color: #fff;
	    border: 1px solid #ccc;
	    border-radius: 4px;
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	    color: #555;
	    font-size: 14px;
	    height: 34px;
	    line-height: 1.42857;
	    padding: 6px 12px;
	    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
	    vertical-align: middle;
	    display: inline;
	    width:15%;
	}
	.mr0{
		margin: 0px 0px 0px 0px;
	}
	.col-lg-12{
		margin: 20px 0 0 10px;
	}
</style>
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
	                        <h5>课程管理-课程管理</h5>
	                </div>
	            </div>
	            <hr />
	                <div class="page-header mr0">
						<form id="suserSearchForm" name="suserSearchForm"
							action="<spring:url value='/coursemanage.do' htmlEscape='true'/>"
							method="post" target="_self">
							<input type="hidden" id="parentId" name="parentId" value="${parentId }" />
							<input type="hidden" id="ownId" name="ownId" value="${ownId }" />
							
							<i class="icon-hand-right"></i><span>搜索</span> 
							<input type="text" placeholder="输入课程名" class="form-control" 
									id="search" name="name" value="${name }"
								autocomplete="off" /> 
							<input id="startDate" class="form-control span2"
								type="text" name="startDate" value="${startDate }" placeholder="上传日期" readonly>
							
							<button class="btn btn-default" type="button" onClick="submitSearchForm()">
												<i class="icon-search"></i>
							</button>
						</form>
				</div>
                
                
                        <div class="table-responsive">
										<table id="sample-table-1"
											class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">序号</th>
													<th>课程名</th>
													<th>标题</th>
													<th>课程类型</th>
													<th>有效性</th>
													<th>添加时间</th>
													<th>教程截图</th>
													<th>添加人员</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${courseList}" var="course"
													varStatus="status">
													<tr>
														<td>${course.id}</td>
														<td>${course.name}</td>
														<td>${course.title}</td>
														<td>${course.cTypeName}</td>
														<td>
															<c:if test="${course.status ==0 }">待审核</c:if>
														</td>

														<td>${course.createTime}</td>
														<td>${course.img}</td>
														<td>${course.userName}</td>
														<td>														
														<c:if test="${course.id > 0}">
															<a data-toggle="modal" href="#suserEdit"
															onClick="editCourse('${course.id}');"
															class="btn btn-xs btn-primary"><i class="icon-edit"></i></a>
															<a data-toggle="modal" href="#suserDel"
																onClick="delSuser('${course.id}','${course.name}');"
																class="btn btn-xs btn-danger"><i class="icon-trash"></i></a>
														</c:if>
															
														<%-- <c:if test="${sysUser.status == 9}">
															<a data-toggle="modal" href="#"
															onClick=""
															class="btn btn-xs btn-gray"><i class="icon-edit"></i></a>
															<a data-toggle="modal" href="#"
																onClick=""
																class="btn btn-xs btn-gray"><i class="icon-trash"></i></a>
														</c:if> --%>
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
								<a onclick="addCourse();" href="javascript:;" class="btn btn-success btn-sm">添加</a>
							</div>
							<div class="hr hr-18 dotted hr-double"></div>
                    </div>
                </div>
                
            </div>
       <!--END PAGE CONTENT -->
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
    
    
    <script type="text/javascript"	src="<%=path %>/static/js/date-time/bootstrap-datepicker.js"></script>
</body>
    <!-- END BODY -->
    
    <script type="text/javascript">
    

  	//课程新增
    var addCourse = function(){
    		var diag = new zDialog();
    		diag.Height = 380;
        	diag.Title = "课程管理-课程新增";
        	diag.URL = "<%=path %>/toAddCourse.do";
        	diag.OKEvent = function(){
        		//参数校验
        		var title = diag.innerDoc.getElementById("title").value;
        		var name = diag.innerDoc.getElementById("name").value;
        		if(title == '' || name == ''){
        			alert('请输入课程名和姓名');
        			//$("input[type='text'][name='usercode']").focus();
        			return;
        		}
        		
        		//提交表单
        		diag.innerDoc.getElementById('addForm').submit();
        		diag.submited=true;
        	};//点击确定后调用的方法
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
    //课程编辑
    var editCourse = function(id){
    	$('#edit-courseId').val(id);
    	document.getElementById('editForm').submit();
    }
    
    
    //课程删除
    var delCourse= function(id,name){
    	$('#del-userId').val(id);
    	zDialog.confirm('警告：您确认要删除课程['+name+']吗？',function(){
    		document.getElementById('delForm').submit();diag.close();
    	});
    }
    </script>



<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>
<script type="text/javascript">
				$('#startDate').datepicker({format:"yyyy-mm-dd"});
				//提交搜索
				
				var submitSearchForm = function(){
					document.getElementById("suserSearchForm").submit();
				}
</script>    

<form id="delForm" name="delForm" method="post" action="doDelGroup.do" target="thisFrame">
	<input type="hidden" id="del-userId" name="userId">
</form>
<form id="editForm" name="editForm" method="post" action="toEditCourse.do" target="_self">
	<input type="hidden" id="edit-courseId" name="courseId">
	<input type="hidden" id="edit-parentId" name="parentId" value="${parentId }">
	<input type="hidden" id="edit-ownId" name="ownId" value="${ownId }">
</form>
<iframe style="display: none" name="thisFrame"></iframe>
</html>
