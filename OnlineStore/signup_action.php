<?php 
include "db.php";
session_start();

$f_name=$_POST["f_name"];
$l_name=$_POST["l_name"];
$email=$_POST["email"];
$password=$_POST["password"];
$repassword=$_POST["repassword"];
$mobile=$_POST["mobile"];
$address1=$_POST["address1"];
$address2=$_POST["address2"];


$sql="select user_id from user_infor where email='$email' limit 1";
$query=mysqli_query($con,$sql);
$count=mysqli_num_rows($query);
if($count>0){
	echo "This is email has been registered.";
	exit();
}else{
	$sql="INSERT INTO user_infor (user_id, first_name, last_name, email, password, mobile, address1, address2) VALUES (NULL, '$f_name', '$l_name', '$email', '$password', '$mobile', '$address1', '$address2')";
    $run_query=mysqli_query($con,$sql);
    if($run_query){
    	$sql="select * from user_infor where email='$email' limit 1";
    	$query=mysqli_query($con,$sql);
        $row=mysqli_fetch_array($query);
        $_SESSION["uid"]=$row["user_id"];
        $_SESSION["name"]=$row["first_name"];
    	echo "successful";
    }
}

?>
