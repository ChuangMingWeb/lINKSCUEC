

	
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
	