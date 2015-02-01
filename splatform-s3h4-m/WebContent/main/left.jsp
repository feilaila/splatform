<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String lpath=this.getServletContext().getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!-- MENU SECTION -->
       <div id="left" >
            <div class="media user-media well-small">
                <a class="user-link" href="#">
                    <img class="media-object img-thumbnail user-img" alt="User Picture" src="<%=lpath %>/static/assets/img/user1.jpg" />
                </a>
                <br />
                <div class="media-body">
                    <h5 class="media-heading"> 超级管理员</h5>
                    <ul class="list-unstyled user-info">
                        <li>
                             <a class="btn btn-success btn-xs btn-circle" style="width: 10px;height: 12px;"></a> 在线
                        </li>
                    </ul>
                </div>
                <br />
            </div>

            <ul id="menu" class="collapse">                
                <%-- <li class="panel active">
                    <a href="<%=lpath %>/unite/index" >
                        <i class="icon-table"></i> 管理面板
                    </a>                   
                </li> --%>

                <li class="panel ">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle collapsed" data-target="#component-nav">
                        <i class="icon-tasks"> </i> 系统设置
                        <span class="pull-right">
                          <i class="icon-angle-left"></i>
                        </span>
                       &nbsp; <span class="label label-default">10</span>&nbsp;
                    </a>
                    <ul class="in" id="component-nav" style="height:auto">                       
                        <li class=""><a href="<%=lpath %>/gmanage.do"><i class="icon-angle-right"></i> 组织管理 </a></li>
                        <li class=""><a href="<%=lpath %>/rmanage.do"><i class="icon-angle-right"></i> 角色管理 </a></li>
                        <li class=""><a href="<%=lpath %>/umanage.do"><i class="icon-angle-right"></i> 用户管理 </a></li>
                        <li class=""><a href="<%=lpath %>/aumanage.do"><i class="icon-angle-right"></i> 会员管理 </a></li>
                    </ul>
                </li>
                
                <li class="panel ">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle collapsed" data-target="#form-nav">
                        <i class="icon-pencil"></i> 信息维护
                        <span class="pull-right">
                            <i class="icon-angle-left"></i>
                        </span>
                          &nbsp; <span class="label label-success">5</span>&nbsp;
                    </a>
                    <ul class="collapse" id="form-nav">
                        <li class=""><a href="forms_general.html"><i class="icon-angle-right"></i> General </a></li>
                        <li class=""><a href="forms_advance.html"><i class="icon-angle-right"></i> Advance </a></li>
                        <li class=""><a href="forms_validation.html"><i class="icon-angle-right"></i> Validation </a></li>
                        <li class=""><a href="forms_fileupload.html"><i class="icon-angle-right"></i> FileUpload </a></li>
                        <li class=""><a href="forms_editors.html"><i class="icon-angle-right"></i> WYSIWYG / Editor </a></li>
                    </ul>
                </li>
                
            </ul>

        </div>