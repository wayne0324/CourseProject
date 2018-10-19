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
                            <h1>Thank you!</h1>
                            <hr/>
                            <p>Hello<?php echo $_SESSION["name"]; ?>,Your Payment process is successfully completed you and your Transaction id is xxxxx-xx-x<br/></p>
                            
                            <a href="index.php" class="btn btn-success btn-lg">Continue Shopping</a>
                        </div>
                    </div>
                </div>
            </div>
                
                
        </div>
             
    </body>
</html>
