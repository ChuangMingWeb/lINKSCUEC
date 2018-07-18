(function () {
    var docEl = document.documentElement,
    resizeEvt = 'onorientationchange' in window? 'orientationchange' : 'resize',
    recalc = function () {
        var clientheight = docEl.clientheight;
        var clientWidth = docEl.clientWidth;
        if (!clientWidth) return;
        docEl.style.fontSize = 40 * (clientWidth / 1080) + 'px';
        document.getElementsByClassName("wrapper")[0].style.height = clientheight + 'px';
    };
    if (document.addEventListener === undefined) 
    	return;
        window.addEventListener(resizeEvt, recalc, false);
        document.addEventListener('DOMContentLoaded', recalc, false)
})();

console.log(clientheight);

//测试git