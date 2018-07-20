function clickfunt(){
    $(document).ready(function(){
    $("button").click(function(){
        // $("#alert").fadeToggle();
        $(function () {  
    setTimeout(function () {  
         $("#alert").fadeIn(3000);
        // $("#alert").show();  
    }, 1000); });
        // setTimeout("$(this).hide()",1000);
        // $("#alert").fadeToggle("slow");
        // $("#alert").fadeToggle(3000);
    });
    });
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
    if(name.length == 0) {
        $('#alert').empty();
         var html = '';
         html += '<div id="p1" style="height:2rem;line-height:2rem;background-color:#E51C23;font-size:1rem;">未填写账号</div>'
        $('#alert').html(html);
         console.log(html);
    // document.getElementById('name').innerHTML='<img src="../image/alert.png" />';
    // alert("用户名不能为空");
}
    else if(!idreg.test(name)) 
    // document.getElementById("nameinfo").innerHTML = "姓名不能为空";  
{
    $('#alert').empty();
         var html = '';
         html += '<div id="p1" style="height:2rem;line-height:2rem;background-color:#E51C23;font-size:1rem;">账号格式不正确</div>'
        $('#alert').html(html);
         console.log(html);
}
        // alert("用户名格式不正确");
    else if(password.length == 0) 
{
    $('#alert').empty();
         var html = '';
         html += '<div id="p1" style="height:2rem;line-height:2rem;background-color:#E51C23;font-size:1rem;">未填写密码</div>'
        $('#alert').html(html);
         console.log(html);
}
        // alert("密码不能为空");
    else if(price.length == 0)
    {
        $('#alert').empty();
         var html = '';
         html += '<div id="p1" style="height:2rem;line-height:2rem;background-color:#E51C23;font-size:1rem;">未填写价钱</div>'
        $('#alert').html(html);
         console.log(html);
    } 
    // alert("价钱不能为空");
    else if(!preg.test(price))
{
    $('#alert').empty();
         var html = '';
         html += '<div id="p1" style="height:2rem;line-height:2rem;background-color:#E51C23;font-size:1rem;">价钱格式不正确或价钱不能超过10元</div>'
        $('#alert').html(html);
         console.log(html);
}
        // alert("价钱格式不正确或价钱不能超过10元");
    else if(accept == false) 
{
    $('#alert').empty();
         var html = '';
         html += '<div id="p1" style="height:2rem;line-height:2rem;background-color:#E51C23;font-size:1rem;">您需要仔细阅读并同意接收用户使用协议！</div>'
        $('#alert').html(html);
         console.log(html);
}
        // alert("您需要仔细阅读并同意接收用户使用协议！");
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
                           
                            // alert("出租成功！");
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
    