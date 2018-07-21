<?php
//链接数据库
$servername="localhost";
$username="root";
$password="";
$dbname="lend";
$conn=new mysqli($servername,$username,$password,$dbname);
if($conn->connect_errno){
    die("连接失败：".$conn->connect_error);
}
$start=$_POST["startTime"];
$endf=$_POST["overTime"];
//把数据插入数据库
    $insert1="INSERT INTO lend1 (start,endf) VALUES ('$start','$endf')";
    $insert2="INSERT INTO lend2 (endf) VALUES ('$endf')";
    if($conn->query($insert1)!=true){
        echo ("插入数据失败：".$conn->error);
        echo json_encode("插入失败");
    }
    if($conn->query($insert2)!=true){
        echo ("插入数据失败：".$conn->error);

    }
$conn->close();

?>