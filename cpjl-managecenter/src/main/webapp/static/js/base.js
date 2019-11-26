// JavaScript Document
 
		
		/*var opts = {
			  submitType : "ajax",   //"ajax","normal"
			  formId : "form1",
			  btnId : "btn1",
			  fun : fun,            //ajax callbackFunction
			  
		   }
		*/	
		function Submit(opts){
			this.opts = opts;
			this.opts.$formId = $("#"+this.opts.formId); 
  		}
		
		Submit.prototype.start = function(){   
			if(this.opts.submitType=="ajax"){  
			   this.ajax();
			}else if(this.opts.submitType=="normal"){
			   this.normal();
			}
		}
		
		Submit.prototype.ajax = function(){  
			   var _this = this;  // alert(this.opts.$formId.attr("action"));
  			   $.ajax({
					beforeSend :  _this.opts.beforeFun ,
					cache: false,
					type: this.opts.$formId.attr("method"),
					url:  encodeURI(this.opts.$formId.attr("action")),
					data: this.serializeObj(),// your formid
					dataType : "json",
					async: false,
					timeout: 10000,
					error: function(xhr,err,errInfo) {	 //	4个err "timeout", "error", "notmodified", "parsererror"	
					     //   if(top.window.frames["main"].out!=undefined){ top.window.frames["main"].out.callback({"s":"2", "str":"出错"});  }
   					        $("#"+_this.opts.btnId).attr("disabled",false); 
							alert(xhr.responseText);
							
							//if(err=="parsererror"){ alert(xhr.responseText)  } else{ alert(errInfo) }
 					},
					success: this.opts.successFun
				});
		}	
		
		Submit.prototype.normal = function(){   
		     if(this.opts.beforeFun()==false){ return };    //before submit
			 var url = this.opts.$formId.attr("action");
			 var data = this.opts.$formId.serialize();  
			 if(url.indexOf("?")!=-1){ url = url + "&" + data ; } else { url = url + "?" + data }; 
			 location.href = url + "&rnd="+Math.random(); 
		}
		 
		
        Submit.prototype.clickOrEnter = function(){
			var _this = this; 
 		    $("#"+this.opts.btnId).bind("click", function(){ _this.start() });
			  
		 	this.opts.$formId.bind("submit",function(){  
			 		//var e = window.event||arguments.callee.arguments[0];  
			 		//if(e.keyCode==13){ _this.start(); return false; } 
					_this.start();
					return false;
		 	});
			
			 
 		//	$(document).bind("keydown",function(){
		//		      var e = window.event||arguments.callee.arguments[0];  
		//		      if(e.keyCode==13){  
 		//					_this.start();
		//			  }
		//	});
 		}
		
		Submit.prototype.serializeObj = function(){
//			 var arr = this.opts.$formId.serializeArray();
//			 var obj = {};
//			 for(var i=0;i<arr.length;i++){
// 				    obj[arr[i].name] = arr[i].value;
// 			 }
 			 obj =  this.opts.$formId.serialize() ;
			 return obj;
		}
		
		Submit.prototype.focusBlur = function (nodeID,darkcolor,lightcolor){
		        var node = $(nodeID);     
				for(var i=0;i<node.length;i++){
				    var nodei = node.eq(i);
					if(nodei.val()==""){  nodei.val(nodei.attr("default_data")); nodei.css("color",darkcolor);  }; 
				} 
				node.bind("focus",function(){ 
					if($(this).val()==$(this).attr("default_data")){ $(this).val(""); $(this).css("color",lightcolor); }
				});
				node.bind("blur",function(){ 
					if($(this).val()==""){ $(this).val($(this).attr("default_data")); $(this).css("color",darkcolor); }
				});   
		};
		
		
//------------------------------------------------------------------------------------------------		
		
		function Init(opts){
			this.opts = opts;
  		}
		
		Init.prototype.select1 = function(nodeId,json,curr){
			var node = $(nodeId), sel = "";  
 			for(var i in json){   
				if(curr==i){ sel = "selected" } else { sel = "" }
				node.append('<option value="'+i+'" '+sel+'>'+json[i]+'</option>');
 			}
 		}
		 
		
		Init.prototype.radio = function(nodeId,radioName,json,curr){ 
			var node = $(nodeId) ,sel = "";
			for(var i in json){
				if(i==curr){ sel = "checked" } else { sel = "" }
				node.append('<input type="radio" name="'+radioName+'" class="pr" value="'+i+'" '+sel+'>'+json[i]+'&nbsp;&nbsp');
			}
		} 
		
		 
		Init.prototype.field1 = function(nodeId,json,jsonIndex,nullInfo){
			var node = $(nodeId), nodei, str=""; 
			for(var i=0;i<node.length;i++){ 
				nodei = node.eq(i).html(); str = "";
				var arr = nodei.split(", ");
				for(var j=0;j<arr.length;j++){
					  if(j>0){ str = str + ", "; }
					  if(arr[j]!="0" && arr[j]!=""){
						 if(jsonIndex=="-1"){ str = str + json[arr[j]];  }else if(!isNaN(jsonIndex)){ str = str + json[arr[j]][jsonIndex];  }
					  }
				}
				if(str==""){ str = nullInfo; }
				node.eq(i).html(str);
			}
		};
		Init.prototype.field2 = function(nodeId1,nodeId1Val,nodeId2,nodeId2Val,json,jsonIndex,nullInfo){  
	        var node1 = $(nodeId1), node2 = $(nodeId2), str = "";   
			var arr;
 			for(var i=0;i<node1.length;i++){
			  str = ""; 
			  if(nodeId1Val=="val"){  arr = node1.eq(i).val().split(", ");  } else {  arr = node1.eq(i).html().split(", "); }
			 
			  for(var j=0;j<arr.length;j++){
			      if(j>0){ str = str + ", "; }
				  if(arr[j]!="0" && arr[j]!=""){
				     if(jsonIndex=="-1"){ str = str + json[arr[j]];  }else if(!isNaN(jsonIndex)){ str = str + json[arr[j]][jsonIndex];  }
				  }
			  }
			    
			  if(str==""){ str = nullInfo; }
			  if(nodeId2Val=="val"){  node2.eq(i).val(str);   } else {  node2.eq(i).html(str); }
			  
			}
		};
		
		Init.prototype.insertImage= function(id){ 
	          _editor.ready(function () { 
			      _editor.hide();
				  _editor.addListener('afterinsertimage', function (t, arg) {  
							 $(id).attr("value",  arg[0].src);
				  })
			  })
 			  
			  $(id+"Upload").on("click",function(){
			       var dialog = _editor.getDialog("insertimage");
				    dialog.open();
			  })
	  };
	  Init.prototype.insertFile = function(id){
	         _editor.ready(function () {
			      _editor.hide();
				  _editor.addListener('afterinsertfile', function (t, arg) { 
							 $(id).attr("value",  arg[0].url);
				  })
			  })
			  $(id+"Upload").on("click",function(){
			       var dialog = _editor.getDialog("attachment");
				    dialog.open();
			  })
	  };
	  Init.prototype.catIDStr_isShow = function(classname){ 
	      $(".catAttr").css("display","none"); 
  	      if(classname!=""){ $("."+classname).css("display",""); }  
		  $(".catAttrTable").val("");
		  $(".catAttrTable").val(classname);
	  };
	  
	  Init.prototype.area = function(nodeId,nodeId1,nodeId2,nodeId3,currVal){
			var $node = $(nodeId);
			var $node1 = $(nodeId1);
			var $node2 = $(nodeId2);
			var $node3 = $(nodeId3); 
			if(currVal.substring(0,2)>0){ var curr1 = currVal.substring(0,2)+"0000"; }else{ var curr1 = "" }
			if(currVal.substring(2,4)>0){ var curr2 = currVal.substring(0,4)+"00"; }else{ var curr2 = "" }
			if(currVal.substring(4,6)>0){ var curr3 = currVal; }else{ var curr3 = "" }
			var sel = "";
			 
			fun1("1",curr1); 
			fun2(curr1,curr2);
			fun3(curr2,curr3);
			$node1.bind("change",function(){
				fun2($(this).val(),""); 
				var val = $(this).val();  
				$node.val(val);   
			});
			$node2.bind("change",function(){
			   fun3($(this).val(),""); 
			   var val = $(this).val();  
			   if(val=="0"){ val = $node.val().substring(0,2)+"0000"; }
			   $node.val(val); 
			});
			$node3.bind("change",function(){
			   var val = $(this).val();  
			   if(val=="0"){ val = $node.val().substring(0,4)+"00"; }
			   $node.val(val); 
			});
			
			//省
			function fun1(pid,currId){
				for(var i in tdist_all){
					if(tdist_all[i][1]==pid){
					   if(currId==i){ sel = "selected" } else { sel = "" };
					   $node1.append('<option value="'+i+'" '+sel+'>'+tdist_all[i][0]+'</option>');
					}
				} 
			}
			
			//市
			function fun2(pid,currId){
				  var sel = ""; 
				  var option0 = $node2.children("option").eq(0);
				  $node2.empty();
				  $node2.append(option0);  
				  var option0 = $node3.children("option").eq(0);
				  $node3.empty();
				  $node3.append(option0);
				  for(var i in tdist_all){
						if(tdist_all[i][1]==pid){
						   
						   if(currId==i){ sel = "selected" } else { sel = "" };
						   $node2.append('<option value="'+i+'"  '+sel+'>'+tdist_all[i][0]+'</option>');
						}
				 } 
			}
			
			//县/区
			function fun3(pid,currId){
				  var option0 = $node3.children("option").eq(0);
				  $node3.empty();
				  $node3.append(option0);  
				  for(var i in tdist_all){
						if(tdist_all[i][1]==pid){
						   if(currId==i){ sel = "selected" } else { sel = "" };
						   $node3.append('<option value="'+i+'"  '+sel+'>'+tdist_all[i][0]+'</option>');
						}
				  } 
			}
			
			
		}
      Init.prototype.getAbsPoint = function (e){
		var x = e.offsetLeft; 
		var y = e.offsetTop;
		while(e = e.offsetParent){
			x += e.offsetLeft;
			y += e.offsetTop;
		}
		return {"x": x, "y": y};
    };
	Init.prototype.prev = function(){
	     var _this = this;
	     $(".prev").on("click",function(){   
	          var src = $.trim($(this).val());
			  if(src!=""){
				  var absPoint = _this.getAbsPoint($(this).get(0));
				  var nodeHeight = parseInt($(this).css("height")) + parseInt($(this).css("paddingTop"))*2 + parseInt($(this).css("borderLeftWidth"))*2; 
				  $(".prevImg").remove();
				  $("body").append("<img name=\""+$(this).attr("name")+"\" class=\"prevImg\" src=\""+$(this).val()+"\"   style=\"position:absolute; top:"+(absPoint.y+nodeHeight)+"px; left:"+absPoint.x+"px; width:100px; height:50px; z-index:1000; \">");  
			  }
 		 })
		 $(document).on("click",function(){
		       $(".prevImg").remove();
		 })
		 $(".prev").on("click",function(e){e.stopPropagation();})
 	 }
	 
	//列表页面的删除
	 Init.prototype.getId_arr = function(isBatch,checkNodes,aNode){  //state=0是批量，state=1是单个删除
			var Id_arr = [];
			if(isBatch){
				var a = 0;
				for(var i=0;i<checkNodes.length;i++){
						Id_arr.push(checkNodes.eq(i).val());
				} 
			}else{
				Id_arr.push(aNode.attr("value"));
			}	 
			return(Id_arr);
	};
	Init.prototype.toIds = function(id_arr){ 
		  var a = 0, ids = ""; 
		  for(var i=0;i<id_arr.length;i++){
				a = a + 1; if(a>1){ ids = ids + "&" };
				ids = ids + "id=" + id_arr[i];
		  }
		  return ids;
	};
	
	Init.prototype.doo = function(isBatch,checkNodes,aNode,url,tips,isRemove){
			 var id_arr = this.getId_arr(isBatch,checkNodes,aNode);
			 if(id_arr.length<1){ alert("请勾选要操作的记录。"); return; }
			 if(tips!=""){  if(!confirm(tips)){ return; }  } 
			 var ids = this.toIds(id_arr);
			 
			 if(url.indexOf("?")!=-1){ url = url + "&" + ids ; } else { url = url + "?" + ids }; 
 
			 $.ajax({
						cache: false,
						type: "get",
						url: url,
						dataType : "json",
						async: true,   //同步
						error: function(xhr,err,errInfo) {	 //	4个err "timeout", "error", "notmodified", "parsererror"	
								alert(xhr.responseText);
						},
						success: function(data){
							console.log(data);
							if(!data.result){ 
								alert(data.message);
								return;
							}
							if(isRemove){ 
								for(var i=0;i<id_arr.length;i++){  
									$("input[name='selId'][value='"+id_arr[i]+"']").parent().parent().remove() ;
								}
							} else{ location.reload();} 
						}
			});
	};
	
//-------------------------------------------------------------	
	function Select2(opts){ 
		 this.opts = opts;
		 this.opts.$select2 = $(opts.select2); 
	}
	Select2.prototype.appendDiv = function(){
	     var opts = this.opts;
 		 var absPoint = this.getAbsPoint(opts.$select2.get(0)),
		      nodeHeight = parseInt(opts.$select2.css("height")) + parseInt(opts.$select2.css("paddingTop"))*2 + parseInt(opts.$select2.css("borderLeftWidth"))*2; 
			 
		 $("body").append('<div id="'+opts.divId.replace("#","")+'" style="position:absolute; visibility:visible; top:'+(absPoint.y+nodeHeight)+'px; left:'+absPoint.x+'px;background-color:#fff; border:solid 1px #999;  padding:2px; z-index:30; line-height:30px; ">'+opts.tips+'</div>'); 
		 $(opts.divId).css(opts.divIdCss);
 	} 
	
	Select2.prototype.radio = function(){  
  			var _this = this, opts = this.opts;
			$(document).delegate(opts.select2,"click",function(){
				if($(opts.divId).length>0){ $(opts.divId).css("visibility","visible"); return false; }
				
				_this.appendDiv();    //展开的div，展开的divcss， 下拉框
				$(document).on("click",function(){ $(opts.divId).css("visibility","hidden")  }); 
				$(opts.divId).on("click",function(e){ e.stopPropagation();});
			    _this.appendRadio(); 
			 	_this.bindRadio();
 			});
			_this.field2();
    }
	
	Select2.prototype.appendRadio = function(){  
	        var opts = this.opts;
		    var str = "", sel2=0, sel3='', jsonStr;  
			str = str + '<div class="children" style=" width:800px; line-height:30px;  border-top:dotted 1px #ddd;">';
			str = str + '<div class="child" style="width:190px; height:30px; padding:0px 5px; float:left; color:#0033ff; overflow:hidden; cursor:pointer">'; 
			str = str + '[<span value="'+opts.defaultVal+'" chk="'+sel2+'"  '+sel3+'> '+opts.defaultStr+'</span> ]</div>';
 						  for(var k in opts.json){
 							     if(opts.curr.indexOf(k)!=-1){ sel2 = 1; sel3 = 'style="color:'+opts.hoverColor+'"'; }else{  sel2=0; sel3 = ""; }
							     str = str + '<div class="child" style="width:190px; height:30px; padding:0px 5px; float:left; overflow:hidden; cursor:pointer">'; 
								  
								 if(opts.jsonIndex=="-1"){  jsonStr = opts.json[k]  }else{ jsonStr = opts.json[k][opts.jsonIndex] }
								 str = str + '<span value="'+k+'" chk="'+sel2+'"  '+sel3+'>'+jsonStr+'</span></div>';
 						  }
			str = str + '</div>';  
			$(opts.divId).append(str);
			$(opts.divId+" .children").css(opts.childrenCss); 
			$(opts.divId+" .child").css(opts.childCss); 
    }
	Select2.prototype.bindRadio = function(){ 
		    var opts = this.opts, child = $(opts.divId+" .child");   
			child.on("click",function(){ 
 	               var thisSpan = $(this).children("span");
 				   if(thisSpan.attr("chk")=="0"){    
 					   child.children("span").attr("chk",0);  thisSpan.attr("chk",1);
					   child.children("span").css("color",""); thisSpan.css("color",opts.hoverColor);
					   $(opts.select2Val).val(thisSpan.attr("value"));
				       $(opts.select2Str).html(thisSpan.html());   
				   } 
				   opts.radioFun();
		  });
		  child.on("mouseover",function(){ 
		       $(this).css("background-color",opts.hoverBackgroundColor);
		  });
		  child.on("mouseout",function(){ 
		       $(this).css("background-color","");
		  });
	}
 	
	Select2.prototype.bindCheckbox = function(){ 
		    var opts = this.opts, child = $(opts.divId+" .child"); 
			child.on("click",function(){ 
 	               var thisSpan = $(this).children("span"),
				        val = $(opts.select2Val).val(), str = $(opts.select2Str).html(),
						str1, str2;
						
 				   if(thisSpan.attr("chk")=="0"){    
				       var len = 0; if(val!=opts.defaultVal){ len = val.split(", ").length; }
					   if(len>=opts.maxSelNum){ alert("最多选择"+opts.maxSelNum+"个"); return; }   
					   if(val!=opts.defaultVal){ str1 = val + ", "; str2 = str + ", "; } else{ str1 = ""; str2 = "" }
					   str1 = str1 + thisSpan.attr("value"); 
					   str2 = str2 + thisSpan.html(); 
					   
 					   thisSpan.attr("chk",1);   thisSpan.css("color",opts.hoverColor);
 				   } else{
						str1 = ", " + val;
						str2 = ", " + str;
					    str1 = str1.replace(", "+thisSpan.attr("value"),"").substring(2);
						str2 = str2.replace(", "+thisSpan.html(),"").substring(2);
						thisSpan.attr("chk",0); thisSpan.css("color","");
 				   }   
				   if(opts.hasCheckbox){
				       var thisCheckbox = $(this).children("input[type='checkbox']");
 					   thisCheckbox.attr("checked",!thisCheckbox.attr("checked")); 
				   }
				   if(str1==""){ str1 = opts.defaultVal }
				   if(str2==""){ str2 = opts.defaultStr }
				   $(opts.select2Val).val(str1);
				   $(opts.select2Str).html(str2);  
				   opts.radioFun();
		  });
		  child.on("mouseover",function(){ 
		       $(this).css("background-color",opts.hoverBackgroundColor);
		  });
		  child.on("mouseout",function(){ 
		       $(this).css("background-color","");
		  });
	}
	Select2.prototype.getAbsPoint = function (e){
		var x = e.offsetLeft; 
		var y = e.offsetTop;
		while(e = e.offsetParent){
			x += e.offsetLeft;
			y += e.offsetTop;
		}
		return {"x": x, "y": y};
    };
	Select2.prototype.field2 = function(){  
	        var opts = this.opts,
	             str = "", arr; 
 			  arr = $(opts.select2Val).val().split(", ");  
 			  for(var j=0;j<arr.length;j++){
			      if(j>0){ str = str + ", "; }
				  if(arr[j]!=opts.defaultVal){ 
				      if(opts.jsonIndex=="-1"){ str = str + opts.json[arr[j]];  }else if(!isNaN(opts.jsonIndex)){ str = str + opts.json[arr[j]][opts.jsonIndex];  }
				  } 
			  }
			  if(str==""){ str = opts.defaultStr; }
			  $(opts.select2Str).html(str); 
 	};
 
//-------------------------------------------------------------------
 
	
 
 
