	/*+++++ 拖曳效果 ++++++
	*   鼠标拖拽窗口
	*   by zhd
	*   整理于 2014-05-26         支持IE6+,firefox,google,opera
	*/

/*前提：
	 <style>
     *{ margin:0px;}
     body{ width:100%; height:100%;}
</style> 
<script type="text/javascript" src="static/js/jquery.min1.8.0.js"></script>
*/
	 var out = {};
	 
	 /*
	     s=1,正在保存； s=2,出现错误； s=3,保存成功。
	 */
	 
	 out.page_close = function (obj){ 
	                var _this = out.openWin_this;
				    _this.closeFun(_this);
					setTimeout(function(){ location.reload(); },0); //submit1.refreshPage();
  	 };
	 
    out.callback = function (obj){ 
	                var _this = out.openWin_this;
				    if(obj.s==1){ 
					 	_this.node.tips.html('<img src="../image/load.gif" style="position:absolute;top:'+(80-16)/2+'px;left:'+(140-16)/2+'px;">');
					 	_this.node.tips.slideDown(300);return;
  					}else if(obj.s==2){
						_this.node.tips.css("display","none"); 
 					}else if(obj.s==200){  
  					    _this.node.tips.html(obj.str); 
 					    setTimeout(function(){ _this.closeFun(_this);  },1000);
						setTimeout(function(){ location.reload(); },1100); //submit1.refreshPage();
 					} 
 	 };
 	
  	
	function openWin(opt){
 		      var defaults = {
				       document : document,
					   width : '80%',
					   height : '80%',
					   top : null,
					   left : null,
					   title : "添加内容",
					   titleHeight : 30,
					   closeBtnWidth : 50,
					   fullBtnWidth : 30,
					   tipsWidth : 140,
					   tipsHeight : 80,
					   border:"solid 1px #999",
					   src : "about:blank"
				}
			  
		      this.opts = $.extend({}, defaults, opt); 
			  
 			  
			   this.opts.cWidth = this.opts.document.documentElement.clientWidth;
			   this.opts.cHeight = this.opts.document.documentElement.clientHeight;   
	 		   if(this.opts.width.indexOf("%")!=-1){ this.opts.width = parseInt(this.opts.cWidth * parseFloat(this.opts.width.replace("%",''))/100); }
	 		   if(this.opts.height.indexOf("%")!=-1){ this.opts.height = parseInt(this.opts.cHeight * parseFloat(this.opts.height.replace("%",''))/100); }
			  
  		      
			  //窗口在屏幕中的位置  
			  this.opts.top = ( this.opts.top===null ? (this.opts.cHeight- this.opts.height)/2 :  this.opts.top);
			  this.opts.left = ( this.opts.left===null ? (this.opts.cWidth- this.opts.width)/2 :  this.opts.left);
			   
				  
			  this.opts.dragging = false;
			  this.opts.iX, this.opts.iY;
			  this.opts.cLeft =  - this.opts.width + 100;         //窗口距离左侧距离
			  this.opts.cTop =  0;
 			  this.opts.cRight =  this.opts.document.documentElement.clientWidth -100;
			  this.opts.cBottom =  this.opts.document.documentElement.clientHeight- this.opts.titleHeight;
				
			  this.node = {
				        $body : $("body",  this.opts.document),
			            $html : $("html",  this.opts.document), 
			            $document : $( this.opts.document)
			  }
				
  			  
			  var _this = this;
			  out.openWin_this = this;
			  this.start = function(){
  					  this.appendHtml();
					  this.node.iframeWin = $("#iframeWin", this.opts.document);
					  this.node.alpha = $("#alpha", this.opts.document); 
					  this.node.titleDiv = $("#title", this.node.iframeWin);   
					  this.node.closeBtn = $("#closeBtn", this.node.iframeWin);
					  this.node.fullBtn = $("#fullBtn", this.node.iframeWin);
					  this.node.tips = $("#tips", this.node.iframeWin);
					  this.node.content = $("#content", this.node.iframeWin);
					  this.node.iframeCover = $("#iframeCover", this.node.content);
					  this.node.iframe = $("#iframe", this.node.content);
					  this.appendHtmlCss(_this);
					 
					  this.mousedown(_this);
					  this.mousemove(_this);
					  this.mouseup(_this);
					  
					  this.node.closeBtn.click(  function(){  _this.closeFun(_this)  });
					  this.node.fullBtn.click( function(){  _this.fullFun(_this)  });
					  this.node.titleDiv.dblclick( function(){  _this.fullFun(_this)  });
 			  }
			  
			   
			  
			  
 			

	};
	
	openWin.prototype.appendHtml = function(){
		           this.node.$body.append('<div id="alpha" style=" position:fixed; _position:absolute;top:0px;left:0px;width:100%;height:100%;background-color:#CCCCCC;filter:alpha(opacity=50);-moz-opacity:0.5;opacity:0.5;z-index:1002"></div>');
				   this.node.$body.append('<div id="iframeWin"  style=" position:fixed; top: '+this.opts.top+'px;left: '+this.opts.left+'px;border:'+ this.opts.border+';background-color:#FFFFFF;width: '+this.opts.width+'px;height: '+this.opts.height+'px;z-index:1003;overflow:hidden"> <div id="title" style="position:absolute;top:0px;left:0px;width:100%;height:'+this.opts.titleHeight+'px;line-height: '+this.opts.titleHeight+'px;padding-left:20px;font-size:14px;background-color:#EDEDED;cursor:Default">'+ this.opts.title+'</div><div id="fullBtn" style="position:absolute;top:0px;left:'+( this.opts.width -  this.opts.closeBtnWidth -  this.opts.fullBtnWidth)+'px;width: '+this.opts.fullBtnWidth+'px;height: '+this.opts.titleHeight+'px;line-height:'+ this.opts.titleHeight+'px;color:#999999; font-size:18px;z-index:1004;text-align:center;cursor:pointer">O</div><div id="closeBtn" style="position:absolute;top:0px;left:'+( this.opts.width -  this.opts.closeBtnWidth)+'px;width: '+this.opts.closeBtnWidth+'px;height: '+this.opts.titleHeight+'px;line-height: '+this.opts.titleHeight+'px;color:#999999; font-size:18px;z-index:1004;text-align:center;cursor:pointer">X</div><div id="tips" style="position:absolute;top:0px;left:'+(( this.opts.width - this.opts.tipsWidth)/2)+'px;width: '+this.opts.tipsWidth+'px;height: '+this.opts.tipsHeight+'px;line-height:'+ this.opts.tipsHeight+'px;background-color:#00ff00;text-align:center;display:none;z-index:1004"></div><div id="content" style="position:absolute;top: '+this.opts.titleHeight+'px;left:0px;width:100%;height:'+( this.opts.height- this.opts.titleHeight)+'px;overflow:hidden"> <div id="iframeCover" style="position:absolute;top:0px;left:0px;width:100%;height:100%;background:none;display:none;z-index::1004"></div><iframe src="'+ this.opts.src+'" id="iframe" name="iframe" width="100%" height="100%" frameborder="0"></iframe></div> </div>'); 
	}
	openWin.prototype.appendHtmlCss = function(){  
	            this.node.$html.css({"overflow":"hidden"}); 
  			 	
 				this.node.fullBtn.hover(
					function(){ $(this).css({"color":"#ff0000"}) },
					function(){ $(this).css({"color":"#999999"}) }
		        );  
				this.node.closeBtn.hover(
					function(){ $(this).css({"color":"#ff0000"}) },
					function(){ $(this).css({"color":"#999999"}) }
		        ); 
 	}
	openWin.prototype.mousedown = function(_this){  
	          _this.node.titleDiv.on("mousedown", function (e){    
					    var e = e || window.event;
						_this.opts.dragging = true;   
						_this.opts.iX = e.clientX - this.parentNode.offsetLeft;  
						_this.opts.iY = e.clientY - this.parentNode.offsetTop;  
						if(this.setCapture){ this.setCapture();  }else if(window.captureEvents) { window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);   }
						_this.node.iframeCover.css({"display":"block"});    //为了兼容在firfox和360极速下，鼠标移到iframe上当前页面的鼠标监听失效问题
						return false;		
 			 });
	}
	 
	openWin.prototype.mousemove = function(_this){  
 	         _this.node.$document.on("mousemove", function(e) { 
 				if (_this.opts.dragging) {  
					var e = e || window.event;
					var oX = e.clientX - _this.opts.iX;
					var oY = e.clientY - _this.opts.iY;
					
					if(oX<_this.opts.cLeft){ oX = _this.opts.cLeft;} 
					if(oY<_this.opts.cTop){ oY = _this.opts.cTop; }
					if(oX>_this.opts.cRight){  oX=_this.opts.cRight;}
					if(oY>_this.opts.cBottom){  oY=_this.opts.cBottom;}
		
					_this.node.iframeWin.css({"left":oX + "px", "top":oY + "px"});
					return false;
				}
			 });
	
	}
	
	openWin.prototype.mouseup = function(_this){  
 	        _this.node.$document.on("mouseup", function(e) {
				var e = e || window.event;
				_this.opts.dragging = false; 
				_this.node.iframeCover.css({"display":"none"});
				_this.node.titleDiv.get(0).releaseCapture();
				e.cancelBubble = true;
			  });
 	}
   
   openWin.prototype.fullFun = function(_this){  
 				  var currWidth =  _this.opts.width,
				       currHeight =  _this.opts.height,
					   currFullBtn = "O";  
				  if(_this.opts.cWidth!=parseInt(_this.node.iframeWin.css("width"))){
					   currWidth = _this.opts.cWidth;
					   currHeight = _this.opts.cHeight;
					   currFullBtn = "o";
				  } 
				  currTop = (_this.opts.cHeight-currHeight)/2 ; 
				  currLeft = (_this.opts.cWidth-currWidth)/2 ; 
				  
				  _this.node.iframeWin.css({ "width":currWidth+"px","height":currHeight+"px","top":currTop+"px","left":currLeft+"px"}); 
				  _this.node.closeBtn.css({"left":(currWidth -  _this.opts.closeBtnWidth)+"px"}); 
				  _this.node.fullBtn.css({ "left":(currWidth -  _this.opts.closeBtnWidth -  _this.opts.fullBtnWidth)+"px"});
				  _this.node.content.css({ "height":(currHeight- _this.opts.titleHeight)+"px"}); 
				  _this.node.fullBtn.html(currFullBtn);
    }
	openWin.prototype.closeFun = function(_this){  
 				  _this.node.alpha.remove();
				  _this.node.iframeWin.remove();
				  _this.node.titleDiv.unbind("mousedown"); 
				  _this.node.$document.unbind("mousemove"); 
				  _this.node.$document.unbind("mouseup"); 
				  _this.node.closeBtn.unbind("click");
				  _this.node.fullBtn.unbind("click"); 
				  _this.node.titleDiv.unbind("dblclick"); 
				  _this.node.$html.css({"overflow":"auto"}); 
 	}
 