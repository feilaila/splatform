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
	.col-lg-12{margin: 20px 0 0 10px;}
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
	                        <h5>课程管理-课程编辑</h5>
	                </div>
	            </div>
	            <hr />
	            <div class="">
	            	<!-- 信息显示 -->
	            	<form action="" name="addForm">
		            	<ul class="memlist fixwidth">
		            		<li><label>课程ID：</label> ${course.id }</li>
		            		<li><label>课程名：</label> ${course.name }</li>
		            		<li><label>课程类型：</label> ${course.cTypeName }</li>
		            		<li><label>课程简介：</label> ${course.info }</li>
		            		
		            		<li><label>添加人：</label> ${course.userName }</li>
		            		<li><label>添加时间：</label> ${course.createTime }</li>
		            		<!-- <li><label>头像预览：</label>
		            			<input type="file" name="picpath" style="display:none;width:0px;">
								<input type="button" value="上传照片" class="btn btn-primary btn-sm" onclick="document.addForm.spanButtonPlaceHolder.click()">
								<div id="img-priview">
									<img alt="" src="" id="userPic">
								</div>
		            		</li> -->
		            		<li>
		            			<div id="upload_preview" class="upload_div">
									<label>截图预览：</label>
									<div id="divFileProgressContainer">
										<ul id="pic_list" style="margin: 5px;">
											<c:choose>
												<c:when test="${course.videoId > 0}">
													<img id="pic_img" width="64" height="64" src="${attachment.filepath }"></img>
												</c:when>
												<c:otherwise>
													<img id="pic_img" width="64" height="64" src="<%=path %>/static/images/default.jpg"></img>
												</c:otherwise>
											</c:choose>											
										</ul>
										<div style="clear: both;"></div>
									</div>
									<!-- 需要js控制展示 -->
									<!--div id="prev_64740" class="prev_a left labelC" draggable="true" mid="64740">
										<div class="width100 labelC">
											<img class="prev" alt="" src="http://img5.bcyimg.com/party/expo/picture/f/541adae567533.png/w230" onload="updateH()">
										</div>
										<a href="javascript:void(0)" onclick="delOneTmpPic('#gfs_pic_ids','64740')">删除该图片</a>
									</div-->
								</div>

								<div class="upload_tag">
									<label class="left mgr5" for="etime">上传视频</label>
									<div id="pic_upload" class="upload_btn_cs">
										<div id="pic_upload_button" class="upload_Button">
											<span name="spanButtonPlaceHolder" id="spanButtonPlaceHolder">X</span>
										</div>
										<input id="btnCancel" type="button" value=""
											onclick="cancelUpload();" disabled="disabled" class="display_none"/>
									</div>

									<input type="hidden" name="faceImg" id="faceImg" value=""/>
								</div>
		            		</li>
		            	</ul>
		            	
	            	</form>
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
    	
    	<script src="<%=path %>/static/js/swfupload/js/fileprogress.js" type="text/javascript"></script>
		<script src="<%=path %>/static/js/swfupload/js/handlers.js" type="text/javascript"></script>
		<script src="<%=path %>/static/js/swfupload/js/swfupload.queue.js" type="text/javascript"></script>
		<script src="<%=path %>/static/js/swfupload/swfupload/swfupload.js" type="text/javascript"></script>
    	<script type="text/javascript" >
	    	var swfu;
	
			window.onload = function() {
				var settings = {
					flash_url : "<%=path %>/static/js/swfupload/swfupload/swfupload.swf",
					upload_url: "<%=path%>/uploadImg.do",	// Relative to the SWF file
					post_params: {"jsessionid":"<%=request.getSession().getId()%>","courseId" : "${course.id}"},
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
    	</script>
<iframe style="display: none" name="thisFrame"></iframe>
</html>
