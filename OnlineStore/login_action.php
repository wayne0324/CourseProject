<?php 

/**
This php is to response the jquery ajax of user signin.
It will start a session with the user id and name.
*/
include "db.php";
session_start();

if(isset($_POST["userSignin"])){
    $email=$_POST["userEmail"];
    $password=$_POST["userPassword"];
    $sql="select * from user_infor where email='$email' and password='$password'";
    $run_query=mysqli_query($con,$sql);
    $count=mysqli_num_rows($run_query);
    if($count==1){
        $row=mysqli_fetch_array($run_query);
        $_SESSION["uid"]=$row["user_id"];
        $_SESSION["name"]=$row["first_name"];
        echo "successful";
    }else{
    	echo "fail";
    }
}

?>