<?php

header("content-type=textml;charset=utf-8");



$str = array
       (
   
       
        'hireStatus'=>false,    //租用状态

        'startTime' =>'2018/01/01-10:10',
        'overTime' =>  '2018/05/01-10:10',
        'weiRENT' => '1小时23分钟',
        'nameRent' =>'201621091077',
        'psdRent' => '{B}cXkxOTk4MTAyNw==' ,   
        
        'regStatus' => TRUE,   //登录状态
        'chuzuStatus' => true,	//出租状态 （要不要出租）
        'zuchuStatus' => false,   //	租出状态 （是否已经租给别人）

        'outStatus' => false    // 关闭校园网
       );

$jsonencode = json_encode($str);
echo $jsonencode;
	

?>
