<?php 
session_start();
/*if(!isset(_SESSION["uid"])){
    header("location:index.php");
}*/
?>

<!DOCTYPE html>
<?php 
include "db.php";
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Awesome Shopping Center</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/selector.js"></script>
        <style>
            table tr td {padding:10px;}
        </style>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fix-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand">Awesome Shopping Store</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="profile.php">Home</a></li>
                </ul>
            </div>
        </div>
        <p><br/></p>
        <p><br/></p>
        <p><br/></p>
        <div class="container-fluid">
            
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div class="panel panel-default">
                        <div class="panel-heading"></div>
                        <div class="panel-body">
                            <h1>Thanks for shopping with us</h1>
                            <h4>Customer Order details</h4>
                            <hr/>
                            <div class="row" id="order_detail">
<!--                                <div class="col-md-6"> 
                                    <img style="float:right;" src="product_image/Samsung_Galaxy_S8.JPG" class="img-thumbnail"/>
                                </div>
                                <div class="col-md-6">
                                    <table>
                                        <tr><td>Product Name</td><td><b>Sansung_Galaxy_S8</b></td></tr>
                                        <tr><td>Product Price</td><td><b>$500</b></td></tr>
                                        <tr><td>Quantity</td><td><b>3</b></td></tr>
                                        <tr><td>Payment</td><td><b>Completed</b></td></tr>
                                        <tr><td>Transaction Id</td><td><b>ABCDEFG12345678</b></td></tr>
                                    </table>                                    
                                </div>-->
<!--                                    <div class="row">
                                    <div class="col-md-8"></div>
                                    <div class="col-md-4">
                                        <b>Total $500000</b>
                                    </div>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                
                
        </div>
             
    </body>
</html>
