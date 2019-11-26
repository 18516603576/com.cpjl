/**
 * 
 */
      function UploadSelector(options){
			 var defaults = { 	
					 "isBatch":false 
			 }; 
		     var opts = $.extend({},defaults,options||{});  
		     this.opts = opts; 
	  };
	  
	  UploadSelector.prototype.init = function(){
		  this.appendHtml();
		  this.bindSubmit(); 
		  this.bindDel(); 
		  this.bindClear();   
	  };
	  
	  //添加html到页面
	   UploadSelector.prototype.appendHtml = function(){
		   var imageHtml = "<img  src='' style='display:none;'/>";
		   if(this.opts.imageInputVal!=undefined && this.opts.imageInputVal!=""){  
			   imageHtml = "<img  src='"+this.opts.imageUrlPrefix+this.opts.imageInputVal+"' />"; 
		   }    		   
		   var html = '';
		   html = html + '<div class="div-upload">';
		   html = html + '<div class="div-preview">';
		   html = html + imageHtml;
		   html = html + '</div>';
		   html = html + '<div class="div-selectFile">';
		   html = html + '<input type="file" name="selectFile" class="selectFile"  title="上传图片" accept="image/*">';
		   html = html + '</div>'; 
		   html = html + '<input type="hidden" class="hiddenInput" name="'+this.opts.imageInputName+'" id="'+this.opts.imageInputName+'" value="'+this.opts.imageInputVal+'" />';
		 
		   if(this.opts.isBatch){
			   if(this.opts.imageInputVal!=""){
				   clazz = "active";
			   } else{
				   clazz=""; 
			   } 
			   html = html + '<div class="div-del '+clazz+'"></div>'; 
		  }else{
			  html = html + '<div class="div-clear"></div>'; 
		  } 
		   html = html + '</div>';
		   
		   
		   this.opts.where.append(html);
	   };
	   
	  //上传图片到接口
	   UploadSelector.prototype.bindSubmit = function(){
		   var _this  = this; 
		   this.opts.where.find(".selectFile").unbind("change").bind("change", function(){
			     var formdata=new FormData();   
		         formdata.append('file',$(this).get(0).files[0]);
		         
		         var $divPreview = $(this).parent().parent().find(".div-preview img");  
		         var $hiddenInput = $(this).parent().parent().find(".hiddenInput");  
		         var $divDel = $(this).parent().parent().find(".div-del");  
	             var hiddenInputVal = $hiddenInput.val();
		         
		         $.ajax({
		             url:_this.opts.submitUrl,
		             type:'post',
		             contentType:false,
		             dataType:"json",       
		             data:formdata,
		             processData:false,
		             success:function(info){   
		            	 if(info.result){
		            		 $divPreview.show();
			            	 $divPreview.attr('src', info.model[0].url);
			            	 $hiddenInput.val(info.model[0].url);    
			            	 if(_this.opts.isBatch && hiddenInputVal==""){
			            		 $divDel.addClass("active"); 
			            		 _this.opts.addOne("");   
			            	 } 
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
	     
	   //清空图片 
 	  UploadSelector.prototype.bindClear = function(){ 
 		  var _this = this;
 		  this.opts.where.find(".div-clear").bind("click",function(){
 			     var $divPreview = $(this).parent().find(".div-preview img");  
 		         var $hiddenInput = $(this).parent().find(".hiddenInput"); 
 		         $divPreview.attr('src','');
 		         $divPreview.hide();
 		         $hiddenInput.val('');  
 		  }); 
  	   }; 
	   //删除图片 
		  UploadSelector.prototype.bindDel = function(){ 
			  this.opts.where.find(".div-del").bind("click",function(){
				     var $divUpload = $(this).parent(".div-upload");   
				     $divUpload.remove();      
			  });      
		   }; 