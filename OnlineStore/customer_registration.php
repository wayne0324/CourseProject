<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Awesome Shopping Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <style type="text/css">.error{color:red;}</style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>
        <script src="js/additional-methods.js"></script>
        <script src="js/register.js"></script>

    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fix-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="index.php" class="navbar-brand">Awesome Shopping Center</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="index.php">Home</a></li>
                </ul>
            </div>
        </div>
        <p><br/></p>
        <p><br/></p>
        <div class="container-fluid">
            
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Customer SignUp Form</div>
                        <div class="panel-body">
                            <form method="POST" id="myform">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="f_name">First Name</label>
                                        <input type="text" id="f_name" name="f_name" class="form-control">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="l_name">Last Name</label>
                                        <input type="text" id="l_name" name="l_name" class="form-control">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="email">Email</label>
                                        <input type="email" id="email" name="email" class="form-control">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="password">Password</label>
                                        <input type="password" id="password" name="password" class="form-control">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="repassword">Re-enter password</label>
                                        <input type="password" id="repassword" name="repassword" class="form-control">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="mobile">Phone</label>
                                        <input type="text" id="mobile" name="mobile" class="form-control">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="mobile">Address</label>
                                        <input type="text" id="address1" name="address1" class="form-control">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="mobile">City and State</label>
                                        <input type="text" id="address2" name="address2" class="form-control">
                                    </div>
                                </div>
                                <p><br/></p>
                                <div class="row">
                                    <div class="col-md-12">
                                        <input style="float:right;" value="submit" type="button" id="signup_button" name="signup_button" class="btn btn-success btn-lg">
                                    </div>
                                </div>
                            </form>
                        </div>                        
                        <div class="panel-footer">
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-8" id="signup_msg">
                                    <!-- message goes here.-->
                                </div>
                                <div class="col-md-2"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>
