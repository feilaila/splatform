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
	.box_l_h_c li{
	    display:block;
	    float:left;
	    margin:20px 0 0 10px;
	}
	.display_none{
		display: none;
	}
	.fl{
		float: left;
	}
	.upload_div{
		
	}
	#pic_list li{
		margin:0;
	}
</style>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<div id="wrap">
					<form class="" method="post" id="addForm" name="addForm"
						action="<spring:url value='/doAddCourse.do' htmlEscape='true'/>" target="_self">
						<input type="hidden" name="uid" value="${loginUser.id}">
						<ul class="box_l_h_c">
							<li class="box_l_h_c_li">
								<select id="typeId" name="typeId" class="form-control"
									style="height:33px;width: 170px; background: none repeat scroll 0 0 #f5f5f5 !important;">
										<option value="0" selected>请选择类型</option>
										<c:forEach items="${courseTypeList}" var="type">
											<option value="${type.id }"
												onclick="setCoTypeId('${type.id }','${type.name}');"
											>${type.name}</option>
										</c:forEach>
								</select>
							</li>
							<li class="box_l_h_c_li">
								<input id="createDate" class="form-control span3" type="text"
									name="startDate" value="" placeholder="开始日期" readonly>
							</li>
							<li class="box_l_h_c_li"><input name="name" value="" type="text"
									placeholder="输入名称" class="form-control span3" id="name"
									autocomplete="off" /></li>
							
							<li class="box_l_h_c_li">
								<input name="title" id="title" value="" type="text"
									placeholder="输入标题" class="form-control span3" 
									autocomplete="off" />
							</li>
							
							<li class="box_l_h_c_li">
								<textarea rows="4" cols="47" name="info" placeholder="输入简介" class="form-control"></textarea>
							</li>
							
							<!-- <li class="box_l_h_c_li" style="clear:both;">
								<div class="upload_tag fl">
									<label class="left mgr5" for="etime">上传视频</label>
									<div id="pic_upload" class="upload_btn_cs">
										<div id="pic_upload_button" class="upload_Button">
											<span name="spanButtonPlaceHolder" id="spanButtonPlaceHolder">X</span>
										</div>
										<input id="btnCancel" type="button" value=""
											onclick="cancelUpload();" disabled="disabled" class="display_none"/>
									</div>

									<input type="hidden" name="videoId" id="videoId" value=""/>
								</div>
								<div id="upload_preview" class="upload_div">
									<label>视频资源：</label>
									<div id="divFileProgressContainer">
										<ul id="pic_list" style="">
											<li>视频1</li>	
											<li>视频2</li>										
										</ul>
										<div style="clear: both;"></div>
									</div>
								</div>
							</li> -->
						</ul>
						
					</form>
	</div>
	<!--END MAIN WRAPPER -->
</body>
<!-- GLOBAL SCRIPTS -->
<script src="<%=path%>/static/assets/plugins/jquery-2.0.3.min.js"></script>

<script type="text/javascript"	src="<%=path %>/static/js/date-time/bootstrap-datepicker.js"></script>

<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>
<script type="text/javascript">
				$('#createDate').datepicker({format:"yyyy-mm-dd"});
				
				//提交搜索
				var setCoTypeId = function(typeId,typeName){
					document.getElementById("typeId").value=typeId;
				}
				var setSuStatus = function(suStatus){
					document.getElementById("status").value=suStatus;
				}
				
</script>    
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
					post_params: {"jsessionid":"<%=request.getSession().getId()%>","userId" : "${loginUser.id}","type":"5"},
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
					button_text: '<span class="btn btn-primary btn-sm theFont">选择视频</span>',
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
			$("#videoId").val(result.aid);
			$('.left .mgr5').html('重新上传图片');
			
		}
    	</script>
</html>