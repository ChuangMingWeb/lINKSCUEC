function backcon(){
    alert("1");
    $.ajax({
        type:'post',
        url:'http://localhost:8080/Link/SelectTime', //测试接口 陈婷给的测试接口

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

	
		$("#button").bind('click',function(){
			var date1=document.getElementById("startTime").value;
			var date2=document.getElementById("overTime").value;
			if ( date1 == date2) {
				alert("时间相等！");
				
			}else{
			var startTime=$("#startTime").val()
			var overTime=$("#overTime").val()
			window.location.href="../LinkRentShow/LinkRentShow.html?startTime="+startTime+"&overTime="+overTime;
			}
			
		 
		})
	