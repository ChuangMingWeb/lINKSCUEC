function clickfunt(){
var date1=document.getElementById("startTime").value;
    var date2=document.getElementById("overTime").value;
    var name=$("#name").val();
    var password=$("#password").val();
    var idreg =/^\d{12}$/;
    var startTime=$("#startTime").val();
    var overTime=$("#overTime").val();
    var allTime=$("#allTime").val();
    var price=$("#price").val();
    var accept = document.getElementById('accept').checked;
    if(name.length == 0) alert("用户名不能为空");
    else if(!idreg.test(name)) alert("用户名格式不正确");
    else if(password.length == 0) alert("密码不能为空");
    else if ( date1 == date2) {
        alert("时间相等！");    
    }
    else if(accept == false) alert("您需要仔细阅读并同意接收用户使用协议！");
    else{
        $.ajax({
            type:'post',
            url:'../ceshi.php', //测试接口  实际接口RentOut.java
            data:{
                'name':name,
                'password':password,
                'startTime':startTime,
                'overTime':overTime,
                'allTime':allTime,
                'price':price,
            },
            dataType:'json',
            success:function(result){     
                console.log(result);            
                    if (result.regStatus){//登陆状
                        alert("登录状态在线");
                        if(result.chuzuStatus){
                            alert("出租成功！");
                            window.location.href="../LinkLendStatus/LinkLendNone.html?startTime="+startTime+"&overTime="+overTime+"&allTime="+allTime;
                        }else{
                            alert("出租失败！");
                            location.reload() ;
                        }
                        
                    }else{
                        alert("失去登录状态，跳转到“登录”页面");
                        window.location.href="../LinkLogin/index.html"; 
                    }
                }  
            })
        }  
 
}
    