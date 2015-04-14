<%@ page contentType="text/html;charset=UTF-8" %>
<link href="<c:url value="/scripts/macdock/macdock.css"/>" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]>
<style>
    /* Inline block fix */
    #dock ul {
        display: inline;
        zoom: 1;
    }

    #dock li, #dock li a {
        display: inline;
        zoom: 1;
    }

    /* Image quality fix */
    img {
        -ms-interpolation-mode: bicubic;
    }

    #dock li a span {
        filter: alpha(opacity=40);
    }
</style>
<![endif]-->
<script>
	//library
	function distance(x0, y0, x1, y1) {
		var xDiff = x1-x0;
		var yDiff = y1-y0;
		
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}
	
	$(document).ready(function() {
		var proximity = 100;
		var iconSmall = 48, iconLarge = 90; //css also needs changing to compensate with size
		var iconDiff = (iconLarge - iconSmall);
		var mouseX, mouseY;
		var dock = $("#dock");
		var animating = false, redrawReady = false;
		
		$(document.body).removeClass("no_js");
		
		//below are methods for maintaining a constant 60fps redraw for the dock without flushing
		$(document).bind("mousemove", function(e) {
			if (dock.is(":visible")) {
				mouseX = e.pageX;
				mouseY = e.pageY;
			
				redrawReady = true;
				registerConstantCheck();
			}
		});
		
		function registerConstantCheck() {
			if (!animating) {
				animating = true;
				
				window.setTimeout(callCheck, 15);
			}
		}
		
		function callCheck() {
			sizeDockIcons();
			
			animating = false;
		
			if (redrawReady) {
				redrawReady = false;
				registerConstantCheck();
			}
		}
		
		//do the maths and resize each icon
		function sizeDockIcons() {
			dock.find("li").each(function() {
				//find the distance from the center of each icon
				var centerX = $(this).offset().left + ($(this).outerWidth()/2.0);
				var centerY = $(this).offset().top + ($(this).outerHeight()/2.0);
				
				var dist = distance(centerX, centerY, mouseX, mouseY);
				
				//determine the new sizes of the icons from the mouse distance from their centres
				var newSize =  (1 - Math.min(1, Math.max(0, dist/proximity))) * iconDiff + iconSmall;
				$(this).find("a").css({width: newSize});
			});
		}
	});
</script>
        
<div class="up72_dofooter skin_dofooter" id="footer_product">
    <div class="up72_dofcopyright">          
        <div class="up72_navl" id="dock">
            <ul>
                <jsp:include page="/admin/menu/nav?AUTH_PRODUCT_ID=${AUTH_PRODUCT.id}" flush="true" />
            </ul>
        </div>        
        <div class="up72_copbg"></div>
    </div>
</div>
