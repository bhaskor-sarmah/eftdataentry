var drag = false;
var image_count = 0;
function startDrag(e) {
    // determine event object
    if (!e) {
        var e = window.event;
    }

    // IE uses srcElement, others use target
    var targ = e.target ? e.target : e.srcElement;

    if (targ.className != 'dragme') {
        return
    }
    ;
    // calculate event X, Y coordinates
    offsetX = e.clientX;
    offsetY = e.clientY;

    // assign default values for top and left properties
    if (!targ.style.left) {
        targ.style.left = '0px'
    }
    ;
    if (!targ.style.top) {
        targ.style.top = '0px'
    }
    ;

    // calculate integer values for top and left 
    // properties
    coordX = parseInt(targ.style.left);
    coordY = parseInt(targ.style.top);
    drag = true;

    // move div element
    document.onmousemove = dragDiv;
    return false;

}
function dragDiv(e) {
    if (!drag) {
        return
    }
    ;
    if (!e) {
        var e = window.event
    }
    ;
    var targ = e.target ? e.target : e.srcElement;
    // move div element
    targ.style.left = coordX + e.clientX - offsetX + 'px';
    targ.style.top = coordY + e.clientY - offsetY + 'px';
    return false;
}
function stopDrag() {
    drag = false;
}

function showImage() {
//    alert(encodeURIComponent(image_arr[image_count].Image_Name));
    var str = ""
            + " <img tabindex=\"-1\" id=\"im\" "
            + "class=\"dragme\" src=\"GetImage?img=" + imageList[image_count].path + "\" width=\"1050px\" style=\"margin-left: 10px;\"/>\n\
\n\
<script type=\"text/javascript\">var myimage = document.getElementById(\"im\");"
            + " if (myimage.addEventListener) {"
            // IE9, Chrome, Safari, Opera
            + " 	myimage.addEventListener(\"mousewheel\", MouseWheelHandler, false);"
            // Firefox
            + " 	myimage.addEventListener(\"DOMMouseScroll\", MouseWheelHandler, false);"
            + " }"
// IE 6/7/8
            + " else myimage.attachEvent(\"onmousewheel\", MouseWheelHandler);"

            + " function MouseWheelHandler(e) {"

            // cross-browser wheel delta
            + " 	var e = window.event || e;" // old IE support
            + " 	var delta = Math.max(-1, Math.min(1, (e.wheelDelta || -e.detail)));"
            + "         myimage.style.width = Math.max(50, Math.min(3000, myimage.width + (30 * delta))) + \"px\";"

            + " 	return false;"
            + " }</script>";

    $("#imageDiv").html(str);
}

function prevImage() {
//    $(".value").html("0 %");
    if (image_count > 0) {
        image_count--;
        showImage();
    } else {
        alert("First Image");
    }
}
function nextImage() {
//    $(".value").html("0 %");
    if (image_count < imageList.length-1) {
        image_count++;
        showImage();
    } else {
        alert("Last Image");
    }
}