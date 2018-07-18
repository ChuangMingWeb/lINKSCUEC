
	$.ajax({
		type: 'post',
		datatype: 'json',
		url: '../ceshi.php', //测试接口
		// data:jsondata,
		// data:JSON.parse(jsondata),
		success: function (result) {
			console.log(result);
			result = JSON.parse(result);
			document.getElementById('name').value = result["nameRent"];
		}
	});

function check(){
	var id = document.getElementById('name').value;
	var password = document.getElementById('password').value;

	var startTime = $("#startTime").val();
	var overTime = $("#overTime").val();
	var allTime = $("#allTime").val();
	var price = $("#price").val();

	var idreg = /^\d{12}$/;

	console.log(id);
	if (id.length == 0) alert("用户名不能为空");
	else if (!idreg.test(id)) alert("用户名格式不正确");
	else if (password.length == 0) alert("密码不能为空");

	else {

		//账号密码传参
		alert("逻辑正确");
		$.ajax({
			type: 'post',
			url: '../ceshi.php', //测试接口  实际接口RentOut.java

			data: {
				'name': id, 
				'password': password,
				'startTime': startTime, 
				'overTime': overTime,
				'allTime': allTime, 
				'price': price
			},
			dataType: 'json',
			success: function (result) {
				console.log(result);
				//var result = JSON.parse(result);         
				if (result.regStatus == true) {
					if (result.chuzuStatus == true) {
						alert("修改出租时段成功");
						window.location.href = "../LinkLendStatus/LinkLendNone.html?startTime="+startTime+"&overTime="+overTime; //
					
					} else {
						alert("修改出租时段失败");
						location.reload() ;//强制刷新
					}
				} else {
					alert("失去登录状态");
					window.location.href = "../LinkLogin/index.html";
		
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			},
		});


	}

}


