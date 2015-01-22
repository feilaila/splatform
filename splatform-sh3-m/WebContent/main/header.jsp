<%
String hpath=this.getServletContext().getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html>
<html>
      <head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <!-- GLOBAL STYLES -->
    <link rel="stylesheet" href="<%=hpath %>/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=hpath %>/static/assets/css/main.css" />
    <link rel="stylesheet" href="<%=hpath %>/static/assets/css/theme.css" />
    <link rel="stylesheet" href="<%=hpath %>/static/assets/css/MoneAdmin.css" />
    <link rel="stylesheet" href="<%=hpath %>/static/assets/plugins/Font-Awesome/css/font-awesome.css" />
    <!--END GLOBAL STYLES -->

    <!-- PAGE LEVEL STYLES -->
    <link rel="stylesheet" href="<%=hpath %>/static/assets/css/layout2.css" />
    <link rel="stylesheet" href="<%=hpath %>/static/assets/plugins/flot/examples/examples.css" />
    <link rel="stylesheet" href="<%=hpath %>/static/assets/plugins/timeline/timeline.css" />
    

	<jsp:include page="../static/js/js_inc.jsp"></jsp:include>
	
	<!-- basic styles -->
	<link rel="stylesheet" href="<%=hpath %>/static/assets/css/font-awesome.min.css" />
	<link rel="stylesheet" href="<%=hpath %>/static/css/bootstrap-select.css">
	<link rel="stylesheet" href="<%=hpath %>/static/assets/css/font-awesome.min.css" />
	<link rel="stylesheet" href="<%=hpath %>/static/assets/css/datepicker.css" />
    
    <style type="text/css">
    	.navbar-brand:hover{
    		color:#36BEF1;
    	}
    </style>
    
    <!-- END PAGE LEVEL  STYLES -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    </head>
