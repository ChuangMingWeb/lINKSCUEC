//获取get参数
function GetRequest() {
    var url = location.search;
    var theRequest = new Object();

    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}


//ajax接收后端的信息
 var obj = (function() {

    var Request = new Object();
    Request = GetRequest();
	var startTime = Request['startTime'];
	var overTime = Request['overTime'];

	//console.log(startTime);
	//console.log(overTime);

    $.ajax({
        type:'POST',
		url:'show.json',
		data:{
			'startTime':startTime,
			'overTime':overTime,
		},
        dataType:"json",
        async:false,
        success:function(data){

            result = data;}
    });
     return result;
 })();


//冒泡价格排序
function bubbleSort_1(arr){
      for(var i=0;i<arr.length-1;i++){
          for(var j=0;j<arr.length-i-1;j++){
              if(arr[j].price>arr[j+1].price){
                  var temp=arr[j];
                  arr[j]=arr[j+1];
                  arr[j+1]=temp;
              }
          }
      }
      return arr;
}

//冒泡信用排序
function bubbleSort_2(arr){
      for(var i=0;i<arr.length-1;i++){
          for(var j=0;j<arr.length-i-1;j++){
              if(arr[j].credit<arr[j+1].credit){
                  var temp=arr[j];
                  arr[j]=arr[j+1];
                  arr[j+1]=temp;
              }
          }
      }
      return arr;
}

//插入信息
function InsertMessage(){
	var contain = document.getElementsByClassName('contain')[0];
	for (var i = 0; i <= obj.length - 1; i++) {
		//插入message这个div
		var message = document.createElement('div');
		message.className = 'message';
		message.id = obj[i].id;
		//插入detail这个div
		var detail = document.createElement('div');
		detail.className = 'detail';
		detail.innerHTML = '<img src="../image/1.png">';
		//插入time这个div
		var time = document.createElement('div');
		time.className = 'time';
		detail.appendChild(time);
		//插入icon这个div
		var icon_1 = document.createElement('div');
		icon_1.className = 'icon_1';
		icon_1.innerHTML = '<img src="../image/start.png"><p>'+obj[i].startTime+'</p>';
		time.appendChild(icon_1);

		var icon_2 = document.createElement('div');
		icon_2.className = 'icon_2';
		icon_2.innerHTML = '<img src="../image/finish.png"><p>'+obj[i].overTime+'</p>';
		time.appendChild(icon_2);

		var p = document.createElement('p');
		p.innerHTML = '信用：'+obj[i].creditStar+'';
		time.appendChild(p);

		message.appendChild(detail);
		//插入cost这个div
		var cost = document.createElement('div');
		cost.className = 'cost';
		cost.innerHTML = '<p>出租时长：<span class="alltime">'+obj[i].allTime+'h</span></p><p>每小时：<span class="price">￥'+obj[i].price+'</span></p>'
		message.appendChild(cost);

		contain.appendChild(message);
	}
}
console.log(obj);
bubbleSort_1(obj);
InsertMessage();

function ChangeCss_1(){
	document.getElementById("FirstPrice").style.fontWeight = 'bold';
	document.getElementById("FirstCredit").style.fontWeight = 'normal';
	document.getElementById("FirstPrice").getElementsByTagName("img")[0].style.display= "block";	
	document.getElementsByClassName('contain')[0].style.display = "none";
	document.getElementById("FirstCredit").getElementsByTagName("img")[0].removeAttribute("style");
	document.getElementsByClassName('contain')[0].innerHTML = " ";
	bubbleSort_1(obj);
	InsertMessage();
	document.getElementsByClassName('contain')[0].removeAttribute("style");
}

function ChangeCss_2(){
	document.getElementById("FirstCredit").style.fontWeight = 'bold';
	document.getElementById("FirstPrice").style.fontWeight = 'normal';
	document.getElementById("FirstCredit").getElementsByTagName("img")[0].style.display= "block";
	document.getElementById("FirstPrice").getElementsByTagName("img")[0].removeAttribute("style");
	document.getElementsByClassName('contain')[0].style.display = "none";
	document.getElementsByClassName('contain')[0].innerHTML = " ";
	bubbleSort_2(obj);
	InsertMessage();
	document.getElementsByClassName('contain')[0].removeAttribute("style");
}


$(".message").bind('click',function(){
	var ID = $(this).attr('id');	
	console.log(ID)
	var startTime=obj[ID].startTime;
	console.log(startTime);
	var overTime=obj[ID].overTime;
	console.log(overTime);
	var price = obj[ID].price;
	window.location.href="../LinkRentSelectTime/LinkRentDeterTime.html?startTime="+startTime+"&overTime="+overTime+"&price="+price;
})