<?php
header("content-type=text/html;charset=utf-8");
require_once("connect.php");//为了避免重复加载文件
$sql="SELECT * FROM `message` ";
$ret=mysqli_query($conn,$sql);
$row=mysqli_fetch_array($ret);
var_dump($row);
echo json_encode($row);
