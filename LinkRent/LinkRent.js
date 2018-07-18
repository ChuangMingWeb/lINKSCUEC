//BY CQ
//test

//全局变量
var startTime;
var overTime;
var weiRENT;
var nameRent;
var psdRent;

//获取get参数
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

function checkback(){
    // var backparameter =  $('#backparameter').text();
    // alert(backparameter);
    // window.onload = function () {
       var backparameter = document.getElementById("backparameter").contentWindow.document.getElementsByTagName("body")[0];
         console.log(backparameter);   
    // }
       
}
//获取账户和密码
function connect() {

    alert("已连接校园网！");
    $('#hideconnect').empty();
    $('#hideconnect').hide();

    var html = '';
    html += '<form name="form2" action="http://10.231.192.37/include/auth_action.php" method="post" target="_iframe_in"><input type="hidden" name="action" value="login"><input type="hidden" name="username" value="' + nameRent + '"><input type="hidden" name="password" value="' + psdRent + '"><input type="hidden" name="ac_id" value="1"><input type="hidden" name="user_ip" value=""><input type="hidden" name="nas_ip" value=""><input type="hidden" name="user_mac" value=""><input type="hidden" name="ajax" value="1"><input type="hidden" name="nas_init_port" value="0"><input type="submit" id="btn" value="通过传递参数" /></form><iframe src="http://10.231.192.37/include/auth_action.php" name="_iframe_in" id="backparameter"  style="display:none;"></iframe>';

    $('#hideconnect').html(html);

    $("#btn").trigger("click");

    checkback();
    //if(checkback()){

    //}else{

   // }
    //$('#hideconnect').empty();

    //这里要写一个判断，如果他租的校园网不能够用了，提示没有租用页面，并告诉他资金退回，且会降低这个人的信用评级
    //现在直接跳到连接成功那里
    //window.location.href = "../LinkRent/LinkRent.html?rent=1&connect=1";
}

function breakconnect(){
    alert("断开校园网！");
    $('#hideconnect').empty();
    $('#hideconnect').hide();

    var html = '';
    html += '<form name="form3" action="http://10.231.192.37/include/auth_action.php"  method="post" target="_iframe_out"><input type="hidden" name="action" value="auto_logout"><input type="hidden" name="info" value=""><input type="hidden" name="user_ip" value=""><input type="hidden" name="username" value="'+nameRent+'"><input type="hidden" name="ajax" value="1"><input type="submit" id="btn" class="a a_demo_two" value="注销"></form><iframe src="http://10.231.192.37/include/auth_action.php" name="_iframe_out"  style="display: none;"></iframe>';

    $('#hideconnect').html(html);

    //$("#btn").trigger("click");
    //$('#hideconnect').empty();

    //这里要写一个判断，是否断网成功
    //现在直接跳到连接成功那里
    window.location.href = "../LinkRent/LinkRent.html?rent=1";

}
function tosearch() {
    //判断本人出租情况
    
    var startTime ;
    var overTime ;
    
    $.ajax({
        type: 'post',//请求方式接口文档没有写
        url: '../ceshi.php', //测试接口
        success: function (result) {
             var result = JSON.parse(result);
             console.log(result.regStatus);
             console.log(result.chuzuStatus);
             console.log(result.zuchuStatus);
            
             regStatus = result.regStatus; //登录状态
             chuzuStatus = result.chuzuStatus;//出租状态 （要不要出租）
             zuchuStatus = result.zuchuStatus; //租出状态 （是否已经租给别人）
             if(regStatus){
                 if(chuzuStatus){
                    if(zuchuStatus){
                        window.location.href="../LinkLendDetails/LinkLendDetails.html"; 
                        //跳租出详情
                    }else{
                        $.ajax({
                            type:'post',
                            url:'../ceshi.php', //测试接口  实际接口RentQuery.java
                            success:function(result){     
                                var result = JSON.parse(result);
                                startTime = result.startTime;
                                 overTime = result.overTime;
                                console.log(overTime);
                                alert('s0');
                                window.location.href="../LinkLendStatus/LinkLendNone.html?startTime="+startTime+"&overTime="+overTime;
                                },
                            error:function(XMLHttpRequest, textStatus, errorThrown){
                                    alert(errorThrown);        
                                },
                            }); 
                       
                        //跳修改出租信息
                    }
                 }else{
                    if(zuchuStatus){
                        window.location.href="../LinkLendDetails/LinkLendDetails.html"; 
                        //跳租出详情
                    }else{
                        window.location.href="../LinkLend/LinkLendFirst.html";
                        //出租（第一次出租的页面）
                    }
                 }

             }else{
                window.location.href="../LinkLogin/index.html";     
             }

            
            // result.password;

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        },
    });
}

function toselecttime() {
 
    window.location.href = " ../LinkRentSelectTime/LinkRentSelectTime.html";
}
function refresh() {
    location.reload();
}
$("#myhome").click(function(event){
    alert('sds');
    window.location.href="../LinkHome/LinkHome.html";  });
$(document).ready(function () {

    var Request = new Object();
    Request = GetRequest();
    var status = Request['rent'];
    var connect = Request['connect'];
    if (status == 1) {

        //查询当前用户正在租用的校园网的信息
        $.ajax({
            type: 'post',
            url: '../ceshi.php', //测试接口
            success: function (result) {
                console.log(result);
                var result = JSON.parse(result);
                startTime = result.startTime;
                overTime = result.overTime;
                weiRENT = result.weiRENT;
                nameRent = result.nameRent;
                psdRent = result.psdRent;
                console.log("起始时间：" + startTime);
                console.log("结束时间：" + overTime);
                console.log("时间：" + weiRENT);
                console.log("校园网账户：" + nameRent);
                console.log("校园网密码：" + psdRent);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            },
        });
        if (connect == 1) {
            $('#contain').empty();
            var html = '';
            html += '<div id="click"><img src="../image/planet3.png" id="ban"><img onclick="breakconnect()" src="../image/connecting.png" id="text"><p>租用中</p></div>';
            $('#contain').html(html);
        } else {
            //显示未连接页面
            $('#contain').empty();
            var html = '';
            html += '<div id="click"><img src="../image/planet2.png" id="ban"><img onclick="connect()" src="../image/click.png" id="text"><p>未连接</p></div>';
            $('#contain').html(html);
        }

    } else {
        //显示暂未借用页面
        $('#contain').empty();
        var html = '';
        html += '<div id="click"><img src="../image/planet-1.png" id="ban"><img onclick="toselecttime()" src="../image/rent.png" id="text"><p>暂未借用账号</p></div>';
        $('#contain').html(html);
    }


});


