(function (d) {
	YZY = {};

	YZYTOOL = {		
		distance2Bottom:function (a) {
			var c = d(document).scrollTop(), b = d(window).height(), f = d(document).height();
			return c + b + a >= f ? !0 : !1;
		},
		empty:function (a) {
			return void 0 === a || null === a || "" === a;
		},
		trim:function (a) {
			return a.replace(/(^\s*)|(\s*$)/g, "").replace(/(^\u3000*)|(\u3000*$)/g, "");
		}, 
		//获得绝对位置 
		getAbsoluteLocation : function (a) {
			if (arguments.length != 1 || a == null) {
				return null;
			}
			var c = d(a);
			var offset = c.offset();
			var top = offset.top;
			var left = offset.left;
			var height = c.height();
			var winHeight = d(window).height();
			var scrollTop = d(document).scrollTop();
			return {absoluteTop:top, absoluteLeft:left, isInView:top >= scrollTop && top <= scrollTop + winHeight, isLoad:top + height + 200 >= scrollTop && top - 400 <= scrollTop + winHeight};
		}
		
	};
	
})(jQuery);

