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
	
	.memlist{ list-style:none; margin:7px 0 25px; padding-top:2px; }
	.memlist li{width: 30%; clear:both; overflow:hidden; zoom:1; *padding:1px 0 5px; line-height:160%;border-bottom: 1px dotted #999;}
	.memlist label{ float:left; width:120px; }
	.fixwidth{ width: 90%; }
	.display_none{display: none;}
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
	                        <h5>demo管理-邮件管理</h5>
	                </div>
	            </div>
	            <hr />
	            
				<div class="row">
                        <div class="col-lg-12">
                            <div class="box">
                                <header>
                                    <div class="icons"><i class="icon-th-large"></i></div>
                                    <h5>Basic Editor</h5>
                                    <ul class="nav pull-right">
                                        <li>
                                            <div class="btn-group">
                                                <a class="accordion-toggle btn btn-xs minimize-box" data-toggle="collapse"
                                                    href="#div-1">
                                                    <i class="icon-minus"></i>
                                                </a>
                                                 <button class="btn btn-danger btn-xs close-box">
                                                    <i
                                                        class="icon-remove"></i>
                                                </button>
                                            </div>
                                        </li>
                                    </ul>
                                </header>
                                <div id="div-1" class="body collapse in">
                                    <form>
                                        <textarea id="wysihtml5" class="form-control" rows="10"></textarea>

                                        <div class="form-actions">
                                            <br />
                                            <input type="submit" value="发送" class="btn btn-primary" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>			
                 <!-- /row -->
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
    	
    	<%-- <script src="<%=path %>/static/js/swfupload/js/fileprogress.js" type="text/javascript"></script>
		<script src="<%=path %>/static/js/swfupload/js/handlers.js" type="text/javascript"></script>
		<script src="<%=path %>/static/js/swfupload/js/swfupload.queue.js" type="text/javascript"></script>
		<script src="<%=path %>/static/js/swfupload/swfupload/swfupload.js" type="text/javascript"></script>
    	<script type="text/javascript" >
	    	var swfu;
	
			window.onload = function() {
				var settings = {
					flash_url : "<%=path %>/static/js/swfupload/swfupload/swfupload.swf",
					upload_url: "<%=path%>/uploadImg.do",	// Relative to the SWF file
					post_params: {"jsessionid":"<%=request.getSession().getId()%>","userId" : "${sysUser.uid}"},
					file_size_limit : "100 MB",
					file_types : "*.*",
					file_types_description : "All Files",
					file_upload_limit : 100,
					file_queue_limit : 0,
					custom_settings : {
						progressTarget : "fsUploadProgress",
						cancelButtonId : "btnCancel"
					},
					debug: false,
	
					// Button settings
					button_image_url: "<%=path %>/static/js/swfupload/images/TestImageNoText_65x29-2.png",	// Relative to the Flash file
					button_width: "65",
					button_height: "29",
					button_placeholder_id: "spanButtonPlaceHolder",
					button_text: '<span class="btn btn-primary btn-sm theFont">选择图片</span>',
					button_text_style: ".theFont { font-size: 13; }",
					button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor: SWFUpload.CURSOR.HAND,
					button_text_left_padding: 3,
					button_text_top_padding: 3,
	
					// The event handler functions are defined in handlers.js
					file_queued_handler : fileQueued,
					file_queue_error_handler : fileQueueError,
					file_dialog_complete_handler : fileDialogComplete,
					upload_start_handler : uploadStart,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError,
					upload_success_handler : uploadSuccess,
					upload_complete_handler : uploadComplete,
					queue_complete_handler : queueComplete	// Queue plugin event
				};
	
				swfu = new SWFUpload(settings);
		     };
	
		//预览区域设置
		function fileQueued(){
				swfu.startUpload();
		}
		//上传完成
		function uploadSuccess(file, serverData){
			addImage(serverData);
		}
		//添加图片
		function addImage(serverData){
	
			var result = new Array();
	    	result = eval('('+serverData+')');//序列化的json对象
			//alert(result.response);
			//alert(result.aid);
			//var newElement = "图片预览：<br><div style='width:172px;height:225px'><img src=\""+APP+"/"+data.savepath+data['savename']+"\" width=172 height=225/>"+data['savename']+"</div>";
			//alert('<{$aid}>');
			//$("#pic_list").empty();
			//$("#pic_list").append(result.newFileUrl);
			$("#pic_img").empty();
			$("#pic_img").attr("src",result.newFileUrl);
			$("#faceImg").val(result.aid);
			$('.left .mgr5').html('重新上传图片');
			
			$("#l_face_img").empty();
			$("#l_face_img").attr("src",result.newFileUrl);
			//$("img.button").last().bind("click", del);
		}
    	</script> --%>
    
		    

	
	


<iframe style="display: none" name="thisFrame"></iframe>
</html>
