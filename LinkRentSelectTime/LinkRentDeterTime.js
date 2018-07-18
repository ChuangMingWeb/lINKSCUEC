
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

function check() {
	setTimeout('getvalue()', 3);
}
function getvalue() {

	var Request = new Object();
	Request = GetRequest();
	var startTime = Request['startTime'];
	var overTime = Request['overTime'];
	var price = Request['price'];

	var date1 = document.getElementById("startTime").value;
	var date2 = document.getElementById("overTime").value;
	if (date1 < startTime || date1 > overTime) {
		// alert(date1);
		// alert("起始时间错误！");
		alert(startTime);
		document.getElementById("startTime").value = startTime;
		return false;
	}
	else {
		// alert("正确");
		date1 = document.getElementById("startTime").value;
	}
	if (date2 > overTime || date2 < startTime) {
		// alert("结束时间错误！");
		document.getElementById("overTime").value = overTime;
		return false;
	}
	else {
		date2 = document.getElementById("overTime").value;
	}
	if (date1 == date2) {
		alert("起始时间与结束时间相等！");
	}
	console.log(date1);
	console.log(date2);
	var date3 = new Date(date2) - new Date(date1);
	// console.log(date3);
	var days = Math.floor(date3 / (24 * 3600 * 1000));
	var leave1 = date3 % (24 * 3600 * 1000);    //计算天数后剩余的毫秒数  
	var hours = Math.floor(leave1 / (3600 * 1000));
	var date = days + "天" + hours + "小时";
	var sumprice = days * 24 * price + hours * price;
	sumprice = sumprice + "元";
	alert(date);
	if (startTime != "" && overTime != "" && date1 > date2) {
		document.getElementById("allTime").value = "0" + "天" + "0" + "小时";
		// alert("开始时间不能大于结束时间！"); 
		return false;
	}
	else {
		document.getElementById("allTime").value = date;
		document.getElementById("price").value = sumprice;
	}
}

function clickfut(){
	window.location.href="../LinkRent/LinkRent.html?rent=1";
}
window.onload = function () {

	var Request = new Object();
	Request = GetRequest();
	var startTime = Request['startTime'];
	var overTime = Request['overTime'];
	var price = Request['price'];

	console.log(startTime);
	console.log(overTime);

	document.getElementById("startTime").value = startTime;
	document.getElementById("overTime").value = overTime;

	console.log(startTime);
	console.log(overTime);

	var date1 = document.getElementById("startTime").value;
	var date2 = document.getElementById("overTime").value;
	var date3 = new Date(date2) - new Date(date1);
	// console.log(date3);
	var days = Math.floor(date3 / (24 * 3600 * 1000));
	var leave1 = date3 % (24 * 3600 * 1000);    //计算天数后剩余的毫秒数  
	var hours = Math.floor(leave1 / (3600 * 1000));
	var date = days + "天" + hours + "小时";
	var sumprice = days * 24 * price + hours * price;
	sumprice = sumprice + "元";

	document.getElementById("allTime").value = date;
	document.getElementById("price").value = sumprice;


	// $("#button").bind('click',function(){
	// 	var startTime=$("#startTime").val();
	// 	var overTime=$("#overTime").val();
	// 	var allTime=$("#allTime").val();
	// 	var price=$("#price").val();
	// 	alert(price);
	// 	// alert(start)
	// 	// alert(endf);
	// 	var url="../ceshi.php"
	// 	var data={'startTime':startTime,'overTime':overTime,
	// 			  'allTime':allTime,'price':price}
	// 	$.post(url,data,function(result){  
	// 	if(result.regStatus==true){

	// 	}    
	// 		if(result.hireStatus){
	// 			window.location.href="../link/link.html?rent=1&startTime="+startTime+"&overTime="+overTime+"&allTime="+allTime+"&price="+price;
	// 		}else{
	// 			//在说
	// 		}
	// 	},"json")  
	// })

}