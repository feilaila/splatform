<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include.jsp"%>

<!-- BEGIN HEAD -->
<%@ include file="../main/header.jsp"%>
<style type="text/css">
	.role—area{
		border:1px solid #9FA0A3;
		line-height: 40px;
		background-color: #ffffff;
	}
	div.left {
	    float: left;
	    width: 250px;
	}
	div.zTreeDemoBackground {
	    text-align: left;
	    width: 250px;
	}
	#wrap ul{
		text-align: left;
	}
	.wrap-ul li{
		display:block;
	    float:left;
	    width:110px;
	}
	#wrap ul .wrap-li{
		transition-property: transform, opacity;
		transition-duration: 0.4s;
	}
</style>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<div id="wrap">
		<form class="_formm15" action="<%=path%>/doAddGroup.do" method="post" id="addForm" name="addForm">
								<input type="hidden" id="groupId" name="groupId"
											value="" />
								<table width="100%" border="0" align="center" cellpadding="4"
									cellspacing="4" bordercolor="#666666">
									<tr>
										<td width="100" align="left">组织名称：</td>
										<td><input type="text" id="groupName" name="groupName"
											value="" /></td>
									</tr>
									<tr>
										<td width="100" align="left">描述：</td>
										<td><input type="text" id="groupDesc" name="groupDesc"
											value="" /></td>
									</tr>
									<tr>
							          <td width="100" align="left">角色选择:</td>
							          <td width="400" class="role—area">
									   	<ul class="wrap-ul">
									   		<c:forEach items="${roleList}" var="role"
													varStatus="status">
									   			<c:choose>
									   				<c:when test="${role.checked==true }">
										   				<li class="wrap-li">
										   					<input name="roleName" type="checkbox" checked="checked">${role.roleName}</li>
										   			</c:when>
										   			<c:otherwise>
										   				<li class="wrap-li">
										   					<input name="roleName" type="checkbox" > ${role.roleName}</li>
										   			</c:otherwise>	
									   			</c:choose>					   			
									   		</c:forEach>
									   	</ul>
									  </td>
							        </tr>
								</table>
							</form>
	</div>
	<!--END MAIN WRAPPER -->
</body>

</html>