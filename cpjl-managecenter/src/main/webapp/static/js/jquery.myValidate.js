	      $.validator.addMethod("mobile"
	   		   ,function(value,element,params){
		   			var reg = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([0,1,6,7,]))|(18[0-2,5-9]))\d{8}$/; 
		   			var b = reg.test(value); 
		   			return b;   
	   			}
	   		   ,"手机号格式不正确");
	      $.validator.addMethod("qq"
		   		   ,function(value,element,params){

			   			var reg = /^\d{5,11}$/;  
			   			var b = reg.test(value); 
			   			return b;   
		   			}
		   		   ,"QQ号格式不正确");
	      
	       