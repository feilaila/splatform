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
                <div class="page-content">
						<h3>欢迎<%=session.getAttribute("usercode") %>登陆管理系统，现在您可以维护本平台下您拥有的功能了!</h3>
					</div>
				<!-- /.page-content -->
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
    <script src="<%=path%>/static/js/jquery-1.7.2.min.js"></script>
	<script src="<%=path %>/static/js/zdialog/zDialog.js"></script>
	<script src="<%=path %>/static/js/zdialog/zDrag.js"></script>
    <!-- zDialog-->
</body>
    <!-- END BODY -->
    
    <script type="text/javascript">
    
    var submitSearchForm = function(){
		document.getElementById("groupSearchForm").submit();
	}
    
  	//组织新增
    var addSgroup = function(){
    		var diag = new zDialog();
    		diag.Height = 400;
        	diag.Title = "系统管理-组织新增";
        	diag.URL = "<%=path %>/toAddGroup.do";
        	diag.OKEvent = function(){
        		//参数校验
        		var groupName = diag.innerDoc.getElementById('groupName').value;
        		var groupDesc = diag.innerDoc.getElementById('groupDesc').value;
        		if(groupDesc=='' || groupName == ''){
        			zDialog.alert('请填写组织名称和对应描述!');
        			return;
        		}
        		
        		//处理角色选择
        		var roleArr = new Array();
        		//alert(e.value);
        		//alert(e.checked);
        		//传入后台的字符串
        		var roleStr = diag.innerDoc.getElementById("roleStr").value;
        		
        		roleStr="";
        		roleArr=diag.innerDoc.getElementsByName("gRole");
        		for(var i=0;i<roleArr.length;i++){
        			if(roleArr[i].checked){
        				roleStr+= roleArr[i].value+",";  
        			}
        		}
        		//去掉最后一个逗号
        		if(roleStr.charAt(roleStr.length - 1)==","){
        			roleStr=roleStr.substring(0,roleStr.length-1);
        		}
        		//设置以及选择的角色id
        		if(diag.innerDoc.getElementById("roleStr").value==""){										
        			diag.innerDoc.getElementById("roleStr").value = roleStr;
        		}else{
        			//先清空表单的值
        			diag.innerDoc.getElementById("roleStr").value="";
        		}
        		diag.innerDoc.getElementById("roleStr").value = roleStr;
        		
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
    //组织编辑
    var editSgroup = function(id){
    		var diag = new zDialog();
    		diag.Height = 400;
    		diag.Title = "系统管理-组织编辑";
        	diag.URL = "<%=path %>/toEditGroup.do?gid="+id;
        	diag.OKEvent = function(){
        		
        		//参数校验
        		var groupName = diag.innerDoc.getElementById('groupName').value;
        		var groupDesc = diag.innerDoc.getElementById('groupDesc').value;
        		if(groupDesc=='' || groupName == ''){
        			zDialog.alert('请填写组织名称和对应描述!');
        			return;
        		}
        		
        		//处理角色选择
        		var roleArr = new Array();
        		//alert(e.value);
        		//alert(e.checked);
        		//传入后台的字符串
        		var roleStr = diag.innerDoc.getElementById("roleStr").value;
        		
        		roleStr="";
        		roleArr=diag.innerDoc.getElementsByName("gRole");
        		for(var i=0;i<roleArr.length;i++){
        			if(roleArr[i].checked){
        				roleStr+= roleArr[i].value+",";  
        			}
        		}
        		//去掉最后一个逗号
        		if(roleStr.charAt(roleStr.length - 1)==","){
        			roleStr=roleStr.substring(0,roleStr.length-1);
        		}
        		//设置以及选择的角色id
        		if(diag.innerDoc.getElementById("roleStr").value==""){										
        			diag.innerDoc.getElementById("roleStr").value = roleStr;
        		}else{
        			//先清空表单的值
        			diag.innerDoc.getElementById("roleStr").value="";
        		}
        		diag.innerDoc.getElementById("roleStr").value = roleStr;
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
