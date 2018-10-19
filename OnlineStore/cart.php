<?php 
session_start();
/*if(!isset(_SESSION["uid"])){
    header("location:index.php");
}*/
?>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Awesome Shopping Center</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/selector.js"></script>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fix-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand">Awesome Shopping Store</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="profile.php">Home</a></li>
                    <!--<li><a href="#"><span class="glyphicon glyphicon-modal-window"></span>Product</a></li>-->
                </ul>
            </div>
        </div>
        <p><br/></p>
        <p><br/></p>
        <p><br/></p>
        <div class="container-fluid">
            <div class ="row">
                <div class="col-md-2"></div>
                <div class="col-md-8" id="cart_msg">
                    <!--Cart Message-->
                </div>
                <div class="col-md-2"></div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Cart Checkout</div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-2"><b>Action</b></div>
                                <div class="col-md-2"><b>Product Image</b></div>
                                <div class="col-md-2"><b>Product Name</b></div>
                                <div class="col-md-2"><b>Quantity</b></div>
                                <div class="col-md-2"><b>Product Price</b></div>
                                <div class="col-md-2"><b>Price in $</b></div>
                            </div>
                            <div id="cart_checkout"></div>
                            <!--<div class="row">
                                <div class="col-md-2">
                                    <div class="btn-group">
                                        <a href="#" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a>
                                        <a href="#" class="btn btn-primary"><span class="glyphicon glyphicon-ok-sign"></span></a>
                                    </div>
                                   
                                </div>
                                <div class="col-md-2"><img src="product_image/imges.jpg"></div>
                                <div class="col-md-2">Product Name</div>
                                <div class="col-md-2"><input type="text" class="form-control" value=""></div>
                                <div class="col-md-2"><input type="text" class="form-control" value="1" disabled></div>
                                <div class="col-md-2"><input type="text" class="form-control" value="5000" disabled></div>
                            </div>-->
<!--                            <div class="row">
                                <div class="col-md-8"></div>
                                <div class="col-md-4">
                                    <b>Total $500000</b>
                                </div>
                            </div>-->
                            <div class="row">
                                <div class="col-md-12" >
                                    <a href="customer_order.php" class="btn btn-success btn-lg" style="float:right" id="btn_checkout">Place Order</a> 
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer"></div>
                    </div>
                </div>
                <div class="col-md-2"></div>
                
        </div>
    </body>
</html>