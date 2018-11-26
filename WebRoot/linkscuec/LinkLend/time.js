Date.prototype.format =function(format)
{
	var o = {
"M+" : this.getMonth()+1, //month
"d+" : this.getDate(), //day
"h+" : this.getHours(), //hour
"H+" : this.getHours()+1, //hour
"m+" : this.getMinutes(), //minute
}
if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
	(this.getFullYear()+"").substr(4- RegExp.$1.length));
	for(var k in o)if(new RegExp("("+ k +")").test(format))
		format = format.replace(RegExp.$1,
			RegExp.$1.length==1? o[k] :
			("00"+ o[k]).substr((""+ o[k]).length));
	return format;
}
document.getElementById("startTime").value = new Date().format('yyyy/MM/dd hh:00');
document.getElementById("overTime").value = new Date().format('yyyy/MM/dd HH:00');
var startTime = document.getElementById("startTime").value;
var overTime = document.getElementById("overTime").value;
// console.log(new Date().format('yyyy/MM/dd hh:mm'));
// console.log(overTime);
setInterval("gettime()", 50);
	function gettime() {
	var  date1 = new Date(  document.getElementById("startTime").value);
	// console.log(date1);
	var date2 = new Date(   document.getElementById("overTime").value);
	// console.log(date2);
	if (date1<new Date().format('yyyy/MM/dd hh:mm')) {
	// alert(date1);
	// alert("起始时间错误！");
	alert(new Date().format('yyyy/MM/dd hh:mm'));
	document.getElementById("startTime").value = new Date().format('yyyy/MM/dd hh:mm');;
	return false;			
	}
	else{
	// alert("正确");
	date1=document.getElementById("startTime").value;
	}
	if (date1 == date2) {
	alert("起始时间与结束时间相等！");
	}
	}
setInterval("getvalue()", 50);
function getvalue(){
 		var date1=document.getElementById("startTime").value;
		var date2=document.getElementById("overTime").value;
		if (date1<startTime ) {
		// alert(date1);
			// alert("起始时间错误！");
			// alert(startTime);
		document.getElementById("startTime").value = startTime;
			return false;			
		}
		else{
			// alert("正确");
		date1=document.getElementById("startTime").value;
		}
		if (date2<startTime) {
			// alert("结束时间错误！");
			date2 = new Date(date1);
			
			date2.setTime(date2.getTime()+1*60*60*1000);
			//date2 = date2.getFullYear()+"-" + (date2.getMonth()+1) + "-" + date2.getDate();
		   var a = date2.getMonth()+1;
			if(a<10){
				datechange = date2.getFullYear()+"/0"+(date2.getMonth()+1)+"/"+date2.getDate()+" "+date2.getHours()+":00";
			}else{
				datechange = date2.getFullYear()+"/"+(date2.getMonth()+1)+"/"+date2.getDate()+" "+date2.getHours()+":00";
			}
			
			document.getElementById("overTime").value= datechange;
			//alert(datechange);

			var date3=new Date(date2)-new Date(date1);
			// console.log(date3);
			var days=Math.floor(date3/(24*3600*1000));  
			var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数  
			var hours=Math.floor(leave1/(3600*1000)); 
			var date=days+"天"+hours+"小时";
			document.getElementById("allTime").value=date;


		
			return false;					
		}
		else{
		date2=document.getElementById("overTime").value;
	
		}
		

		// console.log(date1);
		// console.log(date2);
		var date3=new Date(date2)-new Date(date1);
		// console.log(date3);
		var days=Math.floor(date3/(24*3600*1000));  
		var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数  
		var hours=Math.floor(leave1/(3600*1000)); 
		var date=days+"天"+hours+"小时";
		
		//alert(date);
		if(startTime!="" && overTime !="" &&date1 >date2)  
		{  
	

			date2 = new Date(date1);
			
			date2.setTime(date2.getTime()+1*60*60*1000);
			//date2 = date2.getFullYear()+"-" + (date2.getMonth()+1) + "-" + date2.getDate();
		   var a = date2.getMonth()+1;
			if(a<10){
				datechange = date2.getFullYear()+"/0"+(date2.getMonth()+1)+"/"+date2.getDate()+" "+date2.getHours()+":00";
			}else{
				datechange = date2.getFullYear()+"/"+(date2.getMonth()+1)+"/"+date2.getDate()+" "+date2.getHours()+":00";
			}
			
			document.getElementById("overTime").value= datechange;
			//alert(datechange);

			var date3=new Date(date2)-new Date(date1);
			// console.log(date3);
			var days=Math.floor(date3/(24*3600*1000));  
			var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数  
			var hours=Math.floor(leave1/(3600*1000)); 
			var date=days+"天"+hours+"小时";
			document.getElementById("allTime").value=date;

			// alert("开始时间不能大于结束时间！"); 
			return false;  
		}
		else{
			document.getElementById("allTime").value=date;
		
		}		
}