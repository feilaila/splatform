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
	            <div class="">
	            	<!-- 信息显示 -->
	            	<form action="" name="addForm">
		            	<ul class="memlist fixwidth">
		            		<li><label>用户ID：</label> ${sysUser.uid }</li>
		            		<li><label>用户名：</label> ${sysUser.usercode }</li>
		            		<li><label>邮箱：</label> ${sysUser.email }</li>
		            		<li><label>手机号：</label> ${sysUser.terminalId }</li>
		            		<li><label>姓名：</label> ${sysUser.name }</li>
		            		<li><label>创建时间：</label> ${sysUser.createTime }</li>
		            		<li><label>所在组织：</label> ${sysUser.groupName }</li>
		            		<li><label>头像预览：</label>
		            			<input type="file" name="picpath" style="display:none;width:0px;">
								<input type="button" value="上传照片" class="btn btn-primary btn-sm" onclick="document.addForm.picpath.click()">
								<div id="img-priview">
									<img alt="" src="" id="userPic">
								</div>
		            		</li>
		            		 <div id="upload_preview" class="upload_div">
									图片预览：
									<div id="divFileProgressContainer">
										<ul id="pic_list" style="margin: 5px;"></ul>
										<div style="clear: both;"></div>
									</div>
									<!--需要js控制展示
									div id="prev_64740" class="prev_a left labelC" draggable="true" mid="64740">
										<div class="width100 labelC">
											<img class="prev" alt="" src="http://img5.bcyimg.com/party/expo/picture/f/541adae567533.png/w230" onload="updateH()">
										</div>
										<a href="javascript:void(0)" onclick="delOneTmpPic('#gfs_pic_ids','64740')">删除该图片</a>
									</div-->
								</div>

								<div class="upload_tag">
									<label class="left mgr5" for="etime">上传漫展封面</label>
									<div id="pic_upload" class="upload_btn_cs">
										<div id="pic_upload_button" class="upload_Button">
											<span id="spanButtonPlaceHolder"></span>
										</div>
									</div>

									<input type="hidden" name="faceImg" id="faceImg" value=""/>
								</div>
		            	</ul>
	            	</form>
	            </div>			
                 <!-- /row -->
                </div>
             </div>

	<div id="content">
	<form>
		<div
			style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;">
			<span id="spanButtonPlaceholder"></span>
			<input id="btnUpload" type="button" value="上  传"
				onclick="startUploadFile();" class="btn3_mouseout"
				/>
			<input id="btnCancel" type="button" value="取消所有上传"
				onclick="cancelUpload();" disabled="disabled" class="btn3_mouseout"
				/>
		</div>
	</form>
	<div id="divFileProgressContainer"></div>
	<div id="thumbnails">
		<table id="infoTable" border="0" width="530" style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;margin-top:8px;">
		</table>
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
		<script type="text/javascript" src="<%=path %>/static/js/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="<%=path %>/static/js/swfupload/handlers.js"></script>

		<script type="text/javascript" src="<%=path %>/static/js/swfupload/swfupload.js"></script>
		
		<script type="text/javascript">
			var swfu;
			window.onload = function () {
				swfu = new SWFUpload({
					upload_url: "<%=path%>/uploadImg.do",
					post_params: {"userId" : "${sysUser.uid}"},
					
					// File Upload Settings
					file_size_limit : "100 MB",	// 1000MB
					file_types : "*.*",
					file_types_description : "所有文件",
					file_upload_limit : "0",
									
					file_queue_error_handler : fileQueueError,
					file_dialog_complete_handler : fileDialogComplete,//选择好文件后提交
					file_queued_handler : fileQueued,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError,
					upload_success_handler : uploadSuccess,
					upload_complete_handler : uploadComplete,
	
					// Button Settings
					button_image_url : "<%=path %>/static/js/swfupload/images/SmallSpyGlassWithTransperancy_17x18.png",
					button_placeholder_id : "spanButtonPlaceholder",
					button_width: 180,
					button_height: 18,
					button_text : '<span class="button">选择图片 <span class="buttonSmall">(10 MB Max)</span></span>',
					button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
					button_text_top_padding: 0,
					button_text_left_padding: 18,
					button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor: SWFUpload.CURSOR.HAND,
					
					// Flash Settings
					flash_url : "<%=path%>/static/js/swfupload/swfupload.swf",
	
					custom_settings : {
						upload_target : "divFileProgressContainer",
						progressTarget : "fsUploadProgress",
						cancelButtonId : "btnCancel"
					},
					// Debug Settings
					debug: false  //是否显示调试窗口
				});
			};
			function fileQueued(){
				swfu.startUpload();
			} 
			
			function uploadSuccess(file, serverData){
				addImage(serverData);
				alert(serverData);
			}
			//添加图片
			function addImage(serverData){
				var result = new Array();
		    	result = eval('('+serverData+')');//序列化的json对象
				//alert(result.response);
				//alert(result.aid);
				//var newElement = "图片预览：<br><div style='width:172px;height:225px'><img src=\""+APP+"/"+data.savepath+data['savename']+"\" width=172 height=225/>"+data['savename']+"</div>";
				//alert('<{$aid}>');
				$("#pic_list").empty();
				$("#pic_list").append(result.newFileUrl);
				$("#faceImg").val(result.aid);
				$('.left .mgr5').html('重新上传图片');
				//$("img.button").last().bind("click", del);
			}
		</script>    

	
	


<iframe style="display: none" name="thisFrame"></iframe>
</html>
