//BY CQ
 
//刷新函数
function refresh(){
    location.reload();
}
//过滤函数
function validate(){
  
    var id = document.getElementById('id').value;
    var password = document.getElementById('password').value;
    var accept = document.getElementById('accept').checked;
    var idreg =/^\d{12}$/;
    
    if(id.length == 0) alert("用户名不能为空");
    else if(!idreg.test(id)) alert("用户名格式不正确");
    else if(password.length == 0) alert("密码不能为空");
    else if(accept == false) alert("您需要仔细阅读并同意接收用户使用协议！");
    else{
    
            //账号密码传参
            alert("逻辑正确");
            $.ajax({
            type:'post',
            url:'../ceshi.php', //测试接口
            
            data:{
                'name':id,
                'password':password,
            },
            dataType:'json',
            success:function(result){     
                    console.log(result);   
                    //var result = JSON.parse(result);         
                    if (result.regStatus == true ){
                        if(result.hireStatus==true){
                            alert("表示有正在租用的网，跳转到“未连接”的页面");
                            window.location.href="../LinkRent/LinkRent.html?rent=1"; //rent参数为跳转下一个页面后做判断
                        }else{
                            alert("跳转到“暂未借用账号”页面");
                            window.location.href="../LinkRent/LinkRent.html?rent=0"; 
                        }
                    }else{
                        alert("校园卡账户或密码错误");
                        
                        setTimeout(refresh(),1000);
                    }
                },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert(errorThrown);        
                },
            }); 
            
    
    } 
    
}
$(document).ready(function (){
        //判断登录状态
  
});
