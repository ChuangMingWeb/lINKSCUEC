// $.ajax({
// 		type:'post',
// 		datatype:'json',
//     	url:'../ceshi.php', 
//     	success:function(result){ 
//     		result = JSON.parse(result);
//     		$("#startTime").val(result["startTime"]);
//     		$("#overTime").val(result["overTime"]);
//     		$("#allTime").val(result["allTime"]);
//     });
function backcon(){
    alert("1");
	$.ajax({
		type:'post',
		url:'../ceshi.php', //测试接口 陈婷给的测试接口

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
function changestatus_open(){
    //开启出租校园网

    var outStatus
    $.ajax({
        type:'post',
        url:'../ceshi.php', //测试接口  实际接口为IsRentOut.java
 
        success:function(result){     
            var result = JSON.parse(result);
            console.log(result.outStatus);
            outStatus = result.outStatus;
            },
        error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(errorThrown);        
            },
        }); 
        if(outStatus){
            alert("开启出租校园网成功！");
            $('#_rent').empty();
            var html = '';
            html += '<img onclick="changestatus()" src="../image/open.png">';
            $('#_rent').html(html);
        }else{
            alert("开启出租校园网失败！");
        }
}
function changestatus(){

    var outStatus
    //不出租此校园网
    $.ajax({
        type:'post',
        url:'../ceshi.php', //测试接口  实际接口为IsRentOut.java
 
        success:function(result){     
            var result = JSON.parse(result);
            console.log(result.outStatus);
            outStatus = result.outStatus;
            },
        error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(errorThrown);        
            },
        }); 
        if(outStatus){
            alert("修改出租校园网状态失败！");
        }else{
            alert("已关闭出租校园网");
            $('#_rent').empty();
             var html = '';
            html += '<img onclick="changestatus_open()" src="../image/close.png">';
            $('#_rent').html(html);
        }
}
window.onload = function(){

var Request = new Object();
Request = GetRequest();

var startTime = Request['startTime'];
var overTime = Request['overTime'];


console.log(startTime);
this.console.log(overTime);

document.getElementById('startTime').value = startTime;
document.getElementById('overTime').value = overTime;
}

