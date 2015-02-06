<%@page import="com.sh.manage.constants.*"%>
<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String lpath=this.getServletContext().getContextPath();
%>
<style>
	#menu ul{
		padding:0;
	}
	._sec_menu{
		background-color: #EDEDED;
		width: 219px;
		padding: 5px 0 5px 15px;
		border-bottom:1px solid #99A;
	}
	.bg{
		background: #3083eb;
		height: 40px;
		left: 0;
		overflow: hidden;
		position: absolute;
		width: 3px;
	}
</style>
<!-- MENU SECTION -->
       <div id="left" >
            <div class="media user-media well-small">
                <a class="user-link" href="#">
                    <img id="l_face_img" class="media-object img-thumbnail user-img" width="64" height="64" alt="User Picture" src='<c:if test="${attachment.filepath == null}"><%=session.getAttribute("faceimgpath")%></c:if><c:if test="${attachment.filepath != null}">${attachment.filepath}</c:if>' />
                </a>
                <br />
                <div class="media-body">
                    <h5 class="media-heading"><%=session.getAttribute("name")%></h5>
                    <ul class="list-unstyled user-info">
                        <li>
                             <a class="btn btn-success btn-xs btn-circle" style="width: 10px;height: 12px;"></a> 在线
                        </li>
                    </ul>
                </div>
                <br />
            </div>

            <ul id="menu" class="collapse">
                 <%-- <li class="panel ">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle collapsed" data-target="#component-nav">
                        <i class="icon-tasks"> </i> 系统设置
                        <span class="pull-right">
                          <i class="icon-angle-left"></i>
                        </span>
                       &nbsp; <span class="label label-default">10</span>&nbsp;
                    </a>
                    <ul class="in" id="component-nav" style="height:auto">                       
                        <li class=""><a href="<%=lpath %>/gmanage.do"><i class="icon-angle-right"></i> 组织管理 </a></li>
                        <li class=""><a href="<%=lpath %>/romanage.do"><i class="icon-angle-right"></i> 角色管理 </a></li>
                        <li class=""><a href="<%=lpath %>/umanage.do"><i class="icon-angle-right"></i> 用户管理 </a></li>
                        <li class=""><a href="<%=lpath %>/aumanage.do"><i class="icon-angle-right"></i> 会员管理 </a></li>
                    </ul>
                </li> --%>
                
                
                <c:forEach items="${sessionScope.treeNodeList }" var="treeNode">
	                <li class="panel ">
		                <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle <c:if test='${treeNode.id != parentId}'>collapsed</c:if>" 
		                data-target="#form-${treeNode.code}-nav">
		                    <i class="${treeNode.iconTag }"></i> ${treeNode.name }
		                    <span class="pull-right">
		                        <i class="icon-angle-left"></i>
		                    </span>
		                    &nbsp; <span class="label label-success">5</span>&nbsp;
		                </a>
		                <ul class="<c:if test='${treeNode.id != parentId}'>collapse</c:if><c:if test='${treeNode.id == parentId}'>in</c:if>" id="form-${treeNode.code}-nav">
		                	<c:if test="${treeNode.hasChild == 1}">
		                		<!-- has child nodes -->
		                		<c:forEach items="${treeNode.children}" var="childNode">
		                			<li class="_sec_menu">
		                				<c:if test='${childNode.id == ownId}'><span class="bg"></span></c:if>
		                				<a href="<%=lpath %>/${childNode.menuUrl }?parentId=${childNode.parentId }&ownId=${childNode.id }"><i class="icon-angle-right"></i> ${childNode.name } </a>
		                			</li>
		                		</c:forEach>
		                	</c:if>
		                </ul>
		            </li>
                </c:forEach>
                
                
            </ul>

        </div>