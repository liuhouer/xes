/* ================================================================ 
This copyright notice must be kept untouched in the stylesheet at 
all times.

The original version of this script and the associated (x)html
is available at http://www.stunicholls.com/menu/pro_drop_1.html
Copyright (c) 2005-2007 Stu Nicholls. All rights reserved.
This script and the associated (x)html may be modified in any 
way to fit your requirements.
=================================================================== */
function getElementsByClassName (className) {  
    var all = document.all ? document.all : document.getElementsByTagName('ul');  
    var elements = new Array();  
    for (var e = 0; e < all.length; e++) {  
        if (all[e].className == className) {  
             elements[elements.length] = all[e];  
        }  
    }  
    return elements;
}
function stuHover() {
	//var getElm = document.getElementById("nav").getElementsByTagName("LI");
	var getElm = document.getElementById("nav").getElementsByTagName("LI");
	for (var i=0; i<getElm.length; i++) {
		getElm[i].onmouseover=function() {
			this.className+=" iehover";
		}
		getElm[i].onmouseout=function() {
			this.className=this.className.replace(new RegExp(" iehover\\b"), "");
		}
	}
}



