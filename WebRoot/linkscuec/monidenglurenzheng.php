<?php
$login_url = "http://id.scuec.edu.cn/authserver/login?service=http%3A%2F%2Fehall.scuec.edu.cn%2Flogin%3Fservice%3Dhttp%3A%2F%2Fehall.scuec.edu.cn%2Fnew%2Findex.html";
$cookie_file = tempnam('./temp', 'cookie');
//get获得cookie
$ch=curl_init($login_url); //初始化一个新的会话，返回一个cURL句柄，供curl_setopt(), curl_exec()和curl_close() 函数使用。
curl_setopt($ch,CURLOPT_HEADER,0);//curl_setopt(),为给定的cURL会话句柄设置一个选项。CURLOPT_HEADER指的是启用时会将头文件的信息作为数据流输出。
curl_setopt($ch,CURLOPT_RETURNTRANSFER,1);//将curl_exec()获取的信息以文件流的形式返回，而不是直接输出。
curl_setopt($ch,CURLOPT_COOKIEJAR,$cookie_file); //存储提交后得到的cookie数据
echo '<br>',curl_getinfo($ch,CURLINFO_HTTP_CODE); //输出http头文件信息
$pass=curl_exec($ch);//执行请求
preg_match_all('|value="(.*)"|isU',$pass,$arr); //正则取lt参数；
$lt=$arr[1][2];
curl_close($ch);

//////
$login_url = "http://id.scuec.edu.cn/authserver/login?service=http%3A%2F%2Fehall.scuec.edu.cn%2Flogin%3Fservice%3Dhttp%3A%2F%2Fehall.scuec.edu.cn%2Fnew%2Findex.html";
$post_fields = "username=201621093024
&password=113910&lt=$lt&dllt=userNamePasswordLogin&execution=e1s1&_eventId=submit&rmShown=1";
///////
//post请求，带上数据及cookie进行访问 
$ch=curl_init($login_url);
curl_setopt($ch,CURLOPT_HEADER,0);
curl_setopt($ch,CURLOPT_RETURNTRANSFER,1);
curl_setopt($ch,CURLOPT_POST,1);//post方法
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/x-www-form-urlencoded'));//在http报文中写入Content-Type
curl_setopt ( $ch, CURLOPT_POSTFIELDS, $post_fields); //所传post参数 
curl_setopt($ch,CURLOPT_COOKIEFILE,$cookie_file);//所携带cookie
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);//允许跳转
echo '<br>',curl_getinfo($ch,CURLINFO_HTTP_CODE); //打印返回的报文信息
curl_setopt($ch,CURLOPT_COOKIEJAR,$cookie_file);//保存cookie
echo curl_exec ( $ch);  
curl_close ( $ch );  




/*$url="http://ehall.scuec.edu.cn/new/index.html";
$ch=curl_init($url);
curl_setopt($ch,CURLOPT_HEADER,0);
curl_setopt($ch,CURLOPT_RETURNTRANSFER,1);
curl_setopt($ch,CURLOPT_COOKIEFILE,$cookie_file); //使用提交后得到的cookie数据做参数
echo curl_exec($ch);
curl_close($ch);*/
//@unlik($cookie_file); 
?>
