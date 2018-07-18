

var obj = (function() {
    var result;
    $.ajax({
        type:'POST',
        url:'MyCredit.java',
        dataType:"json",
        async:false,
        success:function(data){
            result = data;}
    });
     return result;
 })();

var cre = obj[0].creditStar;
var ul = document.getElementById('getul');
for (var i = cre - 1; i >= 0; i--) {
	var li = document.createElement("li");
    li.innerHTML = '<li><img src="../image/star.png"></li>';
    ul.appendChild(li);
}