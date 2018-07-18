// (function () {
//     var docEl = document.documentElement,
//     resizeEvt = 'onorientationchange' in window? 'orientationchange' : 'resize',
//     recalc = function () {
//         var clientheight = window.innerHeight;
//         var clientWidth = window.innerWidth;
//         console.log(clientheight);
//         if (!clientWidth) return;
//         docEl.style.fontSize = 40 * (clientWidth / 1080) + 'px';
//         document.getElementsByClassName("wrapper")[0].style.height = clientheight + 'px';
//     };
//     if (document.addEventListener === undefined) 
//     	return;
//         window.addEventListener(resizeEvt, recalc, false);
//         document.addEventListener('DOMContentLoaded', recalc, false)
// })();
// function viewflex(){
var dpr = window.devicePixelRatio;
  var meta = document.createElement('meta');

  // dpr
  meta.setAttribute('content', 'initial-scale=' + 1/dpr + ', maximum-scale=' + 1/dpr + ', minimum-scale=' + 1/dpr + ', user-scalable=no'); 
  document.getElementsByTagName('head')[0].appendChild(meta);

  // rem
  document.addEventListener('DOMContentLoaded', function (e) {
    document.getElementsByTagName('html')[0].style.fontSize = window.innerWidth / 30 + 'px';
    console.log(window.innerWidth);
    document.getElementsByClassName('wrapper')[0].style.height = window.innerHeight + 'PX';
  }, false);
// }


// $(function() {  
//     FastClick.attach(document.body);  
// });