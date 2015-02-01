<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<!-- include HEAD -->
<!-- BEGIN HEAD -->
<%@ include file="header.jsp" %>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
<body class="padTop53 " >

        
	<div id="wrap">
        <%@ include file="top.jsp" %>
        <!-- END HEADER SECTION -->



        <%@ include file="left.jsp" %>
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
        <p>&copy;  binarytheme &nbsp;2014 &nbsp;</p>
    </div>
    <!--END FOOTER -->



</body>

    <!-- END BODY -->
</html>
