/**
 * 
 */
      function UploadVideoSelector(options){
			 var defaults = { 	
					 "isBatch":false 
			 }; 
		     var opts = $.extend({},defaults,options||{});  
		     this.opts = opts; 
	  };
	  
	  UploadVideoSelector.prototype.init = function(){
		  this.appendHtml();
		  this.bindSubmit();  
		  this.bindClear();   
	  };
	  
	  //添加html到页面  
	   UploadVideoSelector.prototype.appendHtml = function(){
		   var videoHtml = "<video width='200px' height='200px' controls autoplay style='background-color:#000;display:none;'><source src=''  type='video/mp4'></video>";
		   if(this.opts.videoInputVal!=undefined && this.opts.videoInputVal!=""){ 
			   videoHtml = "<video width='200px' height='200px' controls autoplay style='background-color:#000'><source src='"+this.opts.videoUrlPrefix+this.opts.videoInputVal+"'  type='video/mp4'></video>"; 
		   }    		   
		   var html = '';
		   html = html + '<div class="div-uploadVideo">';
		   html = html + '<div class="div-preview">';
		   html = html + videoHtml;
		   html = html + '<div class="progressOuterBar"></div>';
		   html = html + '<div class="progressInnerBar"></div>';
		   html = html + '</div>';
		   html = html + '<div class="div-selectFile">';
		   html = html + '<input type="file" name="selectFile" class="selectFile"  title="上传视频" accept="video/mp4">';
		   html = html + '</div>'; 
		   html = html + '<input type="hidden" class="hiddenInput" name="'+this.opts.videoInputName+'" id="'+this.opts.videoInputName+'" value="'+this.opts.videoInputVal+'" />';
		 
		   
		   html = html + '<div class="div-clear"></div>'; 
		  
		   html = html + '</div>';
		   
		   
		   this.opts.where.append(html);
	   };
	   
	  //上传图片到接口
	   UploadVideoSelector.prototype.bindSubmit = function(){
		   var _this  = this; 
		   var videoProgressInterval = null;
		   this.opts.where.find(".selectFile").unbind("change").bind("change", function(){
			   
			     var formdata=new FormData();   
		         formdata.append('file',$(this).get(0).files[0]);
		         
		         var $divPreview = $(this).parent().parent().find(".div-preview video");  
		         var $hiddenInput = $(this).parent().parent().find(".hiddenInput");  
		       
	             var hiddenInputVal = $hiddenInput.val();
		          
		         $.ajax({
		             url:_this.opts.submitUrl,
		             type:'post',
		             contentType:false,
		             dataType:"json",       
		             data:formdata,
		             processData:false,
		             beforeSend : function(){
		            	 if(videoProgressInterval!=null){
		            		 self.clearInterval(videoProgressInterval);
		            	 } 
		            	 videoProgressInterval=self.setInterval(function(){
		      			      _this.videoProgress();
		      			 },1500);  
		             },
		             success:function(info){   
		            	 if(info.result){
		            		 $divPreview.show();
			            	 $divPreview.find("source").attr('src', ' '+info.model.url);
			            	 $hiddenInput.val(info.model.url);    
			            	 $divPreview.load();
			            	 
			            	  _this.videoProgress();
							  self.clearInterval(videoProgressInterval); 
		            	 }else{
		            		 alert(info.message);
		            	 }
		             },
		             error:function(xhr,err,errInfo){
		            	 alert(xhr.responseText);
		             }
		         }); 
		   });  
	     };
	     
	     
	     
	     UploadVideoSelector.prototype.videoProgress = function(){  
			   var _this = this;
			   $.ajax({
		             url:_this.opts.videoProgressUrl,
		             type:'get',
		             contentType:false,  
		             dataType:'json',
		             processData:false,
		             success:function(info){
		            	 var pct = info.model.transferredBytes/info.model.totalBytes * 100;
		            	 if(isNaN(pct)){
		            		 pct = 0;
		            	 }
		            	 console.log(pct );
		            	// alert(pct);
		            	$('.div-uploadVideo .div-preview .progressInnerBar').animate({width: pct+"%"});
		            	$('.div-uploadVideo .div-preview .progressInnerBar').html(parseInt(pct)+"%&nbsp;");
		            	//  $('.uploadImg .previewImg .progressInnerBar').css("width",pct+"%");
		             },
		             error:function(err){
		                 console.log(err)
		             }
		         });
		   }
	     
	   //清空图片 
 	  UploadVideoSelector.prototype.bindClear = function(){ 
 		  var _this = this;
 		  this.opts.where.find(".div-clear").bind("click",function(){
 			     var $divPreview = $(this).parent().find(".div-preview video");  
 		         var $hiddenInput = $(this).parent().find(".hiddenInput"); 
 		         $divPreview.hide();
 		         $divPreview.find("source").attr("src","");
 		         $hiddenInput.val('');   
 		  }); 
  	   }; 
	  