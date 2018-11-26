function backcon(){
    alert("1");
    $.ajax({
        type:'post',
        url:'http://localhost:8080/Link/StatusOpinion', //测试接口 陈婷给的测试接口

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

function clickfunt(){
var date1=document.getElementById("startTime").value;
    var date2=document.getElementById("overTime").value;
    var name=$("#name").val();
    var password=$("#password").val();
    var idreg =/^\d{12}$/;
    var preg =/^\d{1}$/;
    var startTime=$("#startTime").val();
    var overTime=$("#overTime").val();
    var allTime=$("#allTime").val();
    var price=$("#price").val();
    var accept = document.getElementById('accept').checked;
    if(name.length == 0) alert("用户名不能为空");
    else if(!idreg.test(name)) alert("用户名格式不正确");
    else if(password.length == 0) alert("密码不能为空");
    else if(price.length == 0) alert("价钱不能为空");
    else if(!preg.test(price)) alert("价钱格式不正确或价钱不能超过10元");
    else if(accept == false) alert("您需要仔细阅读并同意接收用户使用协议！");
    else{
        $.ajax({
            type:'post',
            url:'http://localhost:8080/Link/RentOut', //测试接口  实际接口RentOut.java
            data:{
                'name':name,
                'password':password,
                'startTime':startTime,
                'overTime':overTime,
                'allTime':allTime,
                'price':price,
            },
            dataType:'json',
            xhrFields:{
                withCredentials:true
            },
            crossDomain:true,
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
    