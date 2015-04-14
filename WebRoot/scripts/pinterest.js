
(function (b) {
	YZY.Wall_Loader = function (g) {
		var i = {container_class:"imagewall", feed_class:"i_w_f", loading_id:"iw_loading", loading_html:"<div class=\"clr\" style=\"text-align:center;\" id=\"iw_loading\"><img src=\"/yizhanyou/images/ajax-loader.gif\"></div>", sort_interval:120, check_interval:1200, iwff_html:"", ajax_url:"/app/ajax", end_callback:null, image_cols:null};
		if (YZYPROFILE.ajaxUrl) {
			i.ajax_url = YZYPROFILE.ajaxUrl;
		}
		var a = b.extend(i, g);
		var fg1 = "<div class=\"display w100 border_bottom lfloat\"></div>";
        var fg2 = "<div class=\"display w100 border_top lfloat m2\"></div>";
		if (b("." + a.container_class).size() != 0) {
			var offset = YZYPROFILE.offset, order = YZYPROFILE.order, split = YZYPROFILE.split, range = YZYPROFILE.range;
			var p = 0, k = !1, h = null, q = !1, f = [], r = !1, l = "";
			if (!Array.indexOf) {
				Array.prototype.indexOf = function (a) {
					for (var b = 0; b < this.length; b++) {
						if (this[b] == a) {
							return b;
						}
					}
					return -1;
				};
			}
			if (null == a.image_cols) {
				a.image_cols = b("." + a.container_class).find(".col");
			}
			for (var m = parseInt(a.image_cols.size()), s = [], d = [], e = null, g = 0; g < m; g++) {
				e = b(a.image_cols.get(g)), s.push(e), d.push(e.height() + e.offset().top);
			}
			var t = function () {
				if (a.iwff_html != "" && false) {
					d = [];
					for (var c = 0; c < m; c++) {
						e = b(a.image_cols.get(c)), d.push(e.height() + e.offset().top);
					}
					for (var v = Math.max.apply(Math, d) + 26, c = 0; c < d.length; c++) {
						b(a.image_cols.get(c)).append(a.iwff_html.replace(/{h}/, v - d[c]));
					}
				}
			};
			if (YZYPROFILE.noajaxUrl) {
				setTimeout(t, 1200);
			} else {
				var w = function () {
					var c = b("." + a.container_class).attr("id");
					p == 3 && b("#lb_login").size() == 0;
					k = !0;
					b("#" + a.loading_id).size() == 0 && b("#" + c).after(a.loading_html);
					b.ajax({url:a.ajax_url, type:"POST", timeout:60000, data:{range:range, offset:offset, split:split}, dataType:"json", success:function (a) {
						if (a != null && a.status == 1001) {
							p++, split--, offset = offset + range, b("#" + i.loading_id).remove(), f = f.concat(a.result), k = !1, (r = a.is_end) ? (l = a.result, q = !0, clearTimeout(h)) : n();
						}
					}});
				}, n = function () {
					if (!k && !q) {
						clearTimeout(h);
						var maxH = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
						var mind = maxH - Math.min.apply(Math, d);
						var c = mind - YZYTOOL.getAbsoluteLocation(b("." + a.container_class)[0]).absoluteTop;
						YZYTOOL.distance2Bottom(c + 1200) && f.length < 5 ? w() : h = setTimeout(n, 1200);
					}
				}, u = function () {
					if (r && f.length == 0) {
						b(".image_wall_more").show();
						setTimeout(t, 500);
						YZYPROFILE.page_tpl && (b(".page_num input").blur(function () {
						}), b(".page_num a").click(function () {
							var a = b(".page_num input").val();
							if (a != "" && !isNaN(a)) {
								a = YZYPROFILE.page_tpl.replace(/{page}/g, (a - 1) * (YZYPROFILE.split + 1) * YZYPROFILE.range).replace(/&amp;/g, "&"), location.href = a;
							} else {
								alert("\u8bf7\u8f93\u5165\u6570\u5b57");
							}
						}));
					} else {
						if (f.length > 0) {
							var c = b(decodeURIComponent(f.shift())), e = d.indexOf(Math.min.apply(Math, d));
							s[e].append(fg1).append(fg2).append(c);
							d[e] += c.height();
							var $item = c.find("span[stoptime]");
							if(null != $item){
								innerendtime($item.attr("id").replace("times",""),$item.attr("stopTime"));
							}
						}
						setTimeout(u, a.sort_interval);
					}
				};
				setTimeout(u, a.sort_interval);
				h = setTimeout(n, a.check_interval);
			}
		}
	};
	b(".imagewall").size() != 0 ? (YZY.Wall_Loader()) : setTimeout(function () {
		b(".imagewall").size() != 0 && (YZY.Wall_Loader());
	}, 3000);
})(jQuery);

