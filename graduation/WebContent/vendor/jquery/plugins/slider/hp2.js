var defaultOpts = { interval: 4000, fadeInTime: 300, fadeOutTime: 200 };
//Iterate over the current set of matched elements
	var _titles = $("ul.slide-txt li");
	var _titles_bg = $("ul.op li");
	var _bodies = $("ul.slide-pic li");
	var _count = _titles.length;
	var _current = 0;
	var _intervalID = null;
	var stop = function() { window.clearInterval(_intervalID); };
	var slide = function(opts) {
		if (opts) {
			_current = opts.current || 0;
		} else {
			_current = (_current >= (_count - 1)) ? 0 : (++_current);
		};
		_bodies.filter(":visible").fadeOut(defaultOpts.fadeOutTime, function() {
			_bodies.eq(_current).fadeIn(defaultOpts.fadeInTime);
			_bodies.removeClass("cur").eq(_current).addClass("cur");
		});
		_titles.removeClass("cur").eq(_current).addClass("cur");
		_titles_bg.removeClass("cur").eq(_current).addClass("cur");
	}; //endof slide
	var go = function() {
		stop();
		_intervalID = window.setInterval(function() { slide(); }, defaultOpts.interval);
	}; //endof go
	var itemMouseOver = function(target, items) {
		stop();
		var i = $.inArray(target, items);
		slide({ current: i });
	}; //endof itemMouseOver
	_titles.hover(function() { if($(this).attr('class')!='cur'){itemMouseOver(this, _titles); }else{stop();}}, go);
	//_titles_bg.hover(function() { itemMouseOver(this, _titles_bg); }, go);
	_bodies.hover(stop, go);
	//trigger the slidebox
	go();
	
var slideX={
	_this:$('.catalog .imgbox'),
	_btnLeft:$('.catalog .left'),
	_btnRight:$('.catalog .right'),
	init:function(){
		slideX._btnLeft.click(slideX.slideLeft);
		slideX._btnRight.click(slideX.slideRight);
		},
	slideLeft:function(){
		slideX._btnLeft.unbind('click',slideX.slideLeft);
		for(i=0;i<2;i++){
			slideX._this.find('li:last').prependTo(slideX._this);
			}
		slideX._this.css('marginLeft',-224);
		slideX._this.animate({'marginLeft':0},500,function(){
			slideX._btnLeft.bind('click',slideX.slideLeft);
			});
		return false;
		},
	slideRight:function(){
		slideX._btnRight.unbind('click',slideX.slideRight);
		slideX._this.animate({'marginLeft':-224},500,function(){
			slideX._this.css('marginLeft','0');
			for(i=0;i<2;i++){
				slideX._this.find('li:first').appendTo(slideX._this)
				}
			slideX._btnRight.bind('click',slideX.slideRight);
			});
		return false;
		}
	}
$(document).ready(function(){
	slideX.init();
})


function getCookie(name){
var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
if(arr != null) return unescape(arr[0]); return null;
}
var cookieVal=getCookie('mhp');


