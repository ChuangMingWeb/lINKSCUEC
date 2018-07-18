function backcon(){
	$.ajax({
		type:'post',
		url:'../ceshi.php', //测试接口 陈婷给的测试接口 还没给

		success:function(result){  
			result = JSON.parse(result);   
				console.log(result); 
				alert(1);           
				if (result.regStatus == true ){
					if(result.hireStatus==true){
						alert("表示有正在租用的网，跳转到“未连接”的页面");
						window.location.href="../LinkRent/LinkRent.html?rent=1"; //rent参数为跳转下一个页面后做判断
					}else{
						alert("跳转到“暂未借用账号”页面");
						window.location.href="../LinkRent/LinkRent.html?rent=0"; 
					}
				}else{			
					//登录状态失效
					window.location.href="../LinkLogin/index.html";
				}
			},
		error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(errorThrown);        
			},
		}); 
}
$.ajax({
		type:'post',
		datatype:'json',
    	url:'../ceshi.php',    //测试接口
    	success:function(result){ 
            console.log(result);
    		result = JSON.parse(result);
    		console.log(result);
    		$("#startTime").val(result["startTime"]);
    		$("#overTime").val(result["overTime"]);
var  date1 = new Date(  document.getElementById("startTime").value);
var date2 = new Date(   document.getElementById("overTime").value);
var date3=new Date(date2)-new Date(date1);
            // console.log(date3);
            var days=Math.floor(date3/(24*3600*1000));  
            var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数  
            var hours=Math.floor(leave1/(3600*1000)); 
            var date=days+"天"+hours+"小时";
            document.getElementById("allTime").value=date;

        }, 
    });
