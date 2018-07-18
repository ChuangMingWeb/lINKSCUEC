var obj = (function() {
    var result;
    $.ajax({
        type:'POST',
        url:'../ceshi.php',   //RentDetial.java
        dataType:"json",
        async:false,
        success:function(data){
            result = data;}
    });
     return result;
 })();

// function ATime(){
	var Rrent = 0;
	var Nrent = 0;
	for  (var i = 0; i <= obj.length - 1; i++){
		if (obj[i].zuchuStatus == true) {
			console.log(obj[i].allTime);
			Rrent += obj[i].allTime;

		}else{
			Nrent += obj[i].allTime;
		}
	}

	document.getElementById('Rrent').innerHTML = '已租出:'+Rrent+'小时';
	document.getElementById('Nrent').innerHTML = '未租出:'+Nrent+'小时';
// }



for (var i = 0; i <= obj.length - 1; i++) {

	var yetRent = document.getElementById('yetRent');
	var noRent = document.getElementById('noRent');

	var message = document.createElement('div');
	message.className = 'message';
	
	//插入time这个div
	var time = document.createElement('div');
	time.className = 'time';
	
	//插入icon这个div
	var icon = document.createElement('div');
	icon.className = 'icon';
	icon.innerHTML = '<img src="../image/start.png"><p>'+obj[i].startTime+'</p>';
	time.appendChild(icon);

	var icon = document.createElement('div');
	icon.className = 'icon';
	icon.innerHTML = '<img src="../image/finish.png"><p>'+obj[i].overTime+'</p>';
	time.appendChild(icon);

	message.appendChild(time);

	var time = document.createElement('div');
	time.className = 'time';

	var blank2 = document.createElement('div');
	blank2.className = 'blank2';
	time.appendChild(blank2);

	var alltime = document.createElement('div');
	alltime.className = 'alltime';
	alltime.innerHTML = '<p>出租时长：<span class="AllTime">'+obj[i].allTime+'h</span></p>'
	time.appendChild(alltime);

	message.appendChild(time);


	if (obj[i].zuchuStatus == true) {
		yetRent.appendChild(message);
		//sconsole.log(obj[i].status);
	}
	else{
		noRent.appendChild(message);
		message.id = i;
	}
}

$(".message").bind('click',function(){
	var ID = $(this).attr('id');
	if (ID != '') {	
	var startTime=obj[ID].startTime;
	console.log(startTime);
	var overTime=obj[ID].overTime;
	console.log(overTime);
	window.location.href="../LinkLendStatus/LinkLendYet.html?startTime="+startTime+"&overTime="+overTime;
	}
})