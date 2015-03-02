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
	                        <h5>系统管理-用户管理</h5>
	                </div>
	            </div>
	            <hr />
	                <div class="page-header mr0">
						<form id="suserSearchForm" name="suserSearchForm"
							action="<spring:url value='/umanage.do' htmlEscape='true'/>"
							method="post" target="_self">
							<input type="hidden" id="parentId" name="parentId" value="${parentId }" />
							<input type="hidden" id="ownId" name="ownId" value="${ownId }" />
							
							<i class="icon-hand-right"></i><span>搜索</span> 
							<input type="text" placeholder="输入用户名" class="form-control" 
									id="search" name="usercode" value="${usercode }"
								autocomplete="off" /> 
							<input id="startDate" class="form-control span2"
								type="text" name="startDate" value="${startDate }" placeholder="开始日期" readonly>
							<input id="endDate" class="form-control span2" type="text" name="endDate"
								value="${endDate }" placeholder="结束日期" readonly> 
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
													<th>用户名</th>
													<th>姓名</th>
													<th>组织</th>
													<th>有效性</th>
													<th>添加时间</th>
													<th>上次登录时间</th>
													<th>上次访问IP</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${sysUserList}" var="sysUser"
													varStatus="status">
													<tr>
														<td>${sysUser.uid}</td>
														<td>${sysUser['usercode']}</td>
														<td><a href="<%=path%>/suserView.do?parentId=${parentId }&uid=${sysUser.uid}&ownUid=${ownUid}">${sysUser.name}</a></td>
														<td>${sysUser.groupName}</td>
														<td>
															<c:if test="${sysUser.status == 1}">有效</c:if>
															<c:if test="${sysUser.status == 9}">失效</c:if>
														</td>
														<td>
															<fmt:parseDate value="${sysUser.createTime}" pattern="yyyyMMdd" var="cdate"/>
															<fmt:formatDate value="${cdate}" pattern="yyyy-MM-dd"/>
														</td>
														<td>
															<fmt:parseDate value="${sysUser.lastLoginTime}" pattern="yyyyMMddHHmmss" var="ldate"/>
															<fmt:formatDate value="${ldate}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td>${sysUser.lastLoginIP}</td>
														<td>														
														<c:if test="${sysUser.status == 1}">
															<a data-toggle="modal" href="#suserEdit"
															onClick="editSuser('${sysUser.uid}');"
															class="btn btn-xs btn-primary"><i class="icon-edit"></i></a>
															<a data-toggle="modal" href="#suserDel"
																onClick="delSuser('${sysUser.uid}','${parentId}','${sysUser.name}');"
																class="btn btn-xs btn-danger"><i class="icon-trash"></i></a>
														
															<a data-toggle="modal" href="#" onClick="initPwd('${sysUser.uid}','${parentId}','${sysUser.name}');"
																class="btn btn-warning btn-xs">初始化密码</a>
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
								<!-- /row -->
							<div
								style="display: inline-block; background-repeat: no-repeat; border-width: 4px; font-size: 13px; line-height: 1.39; padding: 4px 9px;">
								<a onclick="addSysUser();" href="javascript:;" class="btn btn-success btn-sm">添加</a>
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
    
    
    <script type="text/javascript"	src="<%=path %>/static/js/date-time/bootstrap-datepicker.js"></script>
</body>
    <!-- END BODY -->
    
    <script type="text/javascript">
    

  	//组织新增
    var addSysUser = function(){
    		var diag = new zDialog();
    		diag.Height = 360;
        	diag.Title = "系统管理-用户新增";
        	diag.URL = "<%=path %>/toAddSysUser.do?parentId=${parentId}";
        	diag.OKEvent = function(){
        		//参数校验
        		var usercode = diag.innerDoc.getElementById("usercode").value;
        		var name = diag.innerDoc.getElementById("name").value;
        		if(usercode == '' || name == ''){
        			alert('请输入用户名和姓名');
        			//$("input[type='text'][name='usercode']").focus();
        			return;
        		}
        		//密码确认校验
        		var pwd = diag.innerDoc.getElementById("password").value;
        		var rePwd = diag.innerDoc.getElementById("repassword").value;
        		if(pwd != rePwd ){
        			alert('两次输入密码不一致,请确认');
        			//$("input[type='text'][name='password']").focus();
        			return;
        		}
        		if(pwd == '' || rePwd ==''){
        			alert('请输入密码和确认密码');
        			//$("input[type='text'][name='password']").focus();
        			return;
        		}

        		//邮箱校验
        		//邮箱正则
        		var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        		var v_email = diag.innerDoc.getElementById("email").value;
        		if(v_email !=''){
        			if(!emailReg.test(v_email)){
        				alert('请输入有效的E_mail！');
        		        //$("input[type='text'][name='email']").focus();
        		        return;
        			}
        		}else{
        			alert('请输入E_mail！');
        	        //$("input[type='text'][name='email']").focus();
        	        return;
        		}
        		
        		//手机号校验
        		var v_ter = diag.innerDoc.getElementById("terminalId").value;
        		if(v_ter==""||v_ter.length<11){
        			alert("请输入正确的手机号码!");
        			//$("input[type='text'][name='terminalId']").focus();
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
    //用户编辑
    var editSuser = function(id){
    		var diag = new zDialog();
    		diag.Height = 400;
    		diag.Title = "系统管理-用户编辑";
        	diag.URL = "<%=path %>/toEditSysUser.do?parentId=${parentId}&uid="+id;
        	diag.OKEvent = function(){
        		diag.innerDoc.getElementById("uid").value = id;
        		//参数校验
        		var usercode = diag.innerDoc.getElementById("usercode").value;
        		var name = diag.innerDoc.getElementById("name").value;
        		if(usercode == '' || name == ''){
        			alert('请输入用户名和姓名');
        			//$("input[type='text'][name='usercode']").focus();
        			return;
        		}
        		
        		//邮箱校验
        		//邮箱正则
        		var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        		var v_email = diag.innerDoc.getElementById("email").value;
        		if(v_email !=''){
        			if(!emailReg.test(v_email)){
        				alert('请输入有效的E_mail！');
        		        //$("input[type='text'][name='email']").focus();
        		        return;
        			}
        		}else{
        			alert('请输入E_mail！');
        	        //$("input[type='text'][name='email']").focus();
        	        return;
        		}
        		
        		//手机号校验
        		var v_ter = diag.innerDoc.getElementById("terminalId").value;
        		if(v_ter==""||v_ter.length<11){
        			alert("请输入正确的手机号码!");
        			//$("input[type='text'][name='terminalId']").focus();
        			return;
        		}
        		/**var groupName = diag.innerDoc.getElementById('groupName').value;
        		var groupDesc = diag.innerDoc.getElementById('groupDesc').value;
        		if(groupDesc=='' || groupName == ''){
        			zDialog.alert('请填写组织名称和对应描述!');
        			return;
        		}**/
        		
        		
        		//alert(roleStr);
        		
        		diag.innerDoc.getElementById('editForm').submit();
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
    
    
    //用户删除
    var delSuser= function(id,parentId,userName){
    	$('#del-userId').val(id);
    	$('#del-parentId').val(parentId);
    	zDialog.confirm('警告：您确认要删除用户['+userName+']吗？',function(){
    		document.getElementById('delForm').submit();diag.close();
    	});
    }
    
  	//用户密码初始化
    var initPwd= function(id,parentId,userName){
    	$('#init-userId').val(id);
    	$('#init-parentId').val(parentId);
    	zDialog.confirm('警告：您确认要初始化用户['+userName+']的密码吗？',function(){
    		document.getElementById('initPwdForm').submit();diag.close();
    	});
    }
    </script>



<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>
<script type="text/javascript">
				$('#startDate').datepicker({format:"yyyy-mm-dd"});
				$('#endDate').datepicker({format:"yyyy-mm-dd"});
				$('#createDate').datepicker({format:"yyyy-mm-dd"});
				$('#validDate').datepicker({format:"yyyy-mm-dd"});
				$('#createDateEdit').datepicker({format:"yyyy-mm-dd"});
				$('#validDateEdit').datepicker({format:"yyyy-mm-dd"});
				//提交搜索
				
				var submitSearchForm = function(){
					document.getElementById("suserSearchForm").submit();
				}
</script>    

<form id="delForm" name="delForm" method="post" action="doDelSysUser.do" target="thisFrame">
	<input type="hidden" id="del-userId" name="uid">
	<input type="hidden" id="del-parentId" name="parentId">
</form>
<form id="initPwdForm" name="initPwdForm" method="post" action="doInitPwd.do" target="thisFrame">
	<input type="hidden" id="init-userId" name="uid">
	<input type="hidden" id="init-parentId" name="parentId">
</form>
<iframe style="display: none" name="thisFrame"></iframe>
</html>
