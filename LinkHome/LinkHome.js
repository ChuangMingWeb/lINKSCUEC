
var obj = (function() {
    var result;
    $.ajax({
        type:'POST',
        url:'home.json',
        dataType:"json",
        async:false,
        success:function(data){
        	console.log(12312),
            result = data;}
    });
     return result;
 })();


var cre = obj[0].credit;
var ul = document.getElementById('getul');
for (var i = cre - 1; i >= 0; i--) {
	var li = document.createElement("li");
    li.innerHTML = '<img src="../image/star.png">';
    console.log(ul);
    ul.appendChild(li);
}

var na = obj[0].name;
var Nam = document.getElementById("name");
Nam.innerHTML = na;

var id = obj[0].IDcard;
var Id = document.getElementById("ID");
Id.innerHTML = id;
