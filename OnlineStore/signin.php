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
        <script src="js/signin.js"></script>

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
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading" id="signin_error">User Sign In</div>
                        <div class="panel-body">
                            <form method="POST" id="signin_form">
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
                                <p><br/></p>
                                <div class="row">
                                    <div class="col-md-12">
                                        <input style="float:right;" value="Sign in" type="button" id="signin_button" name="signin_button" class="btn btn-success btn-lg">
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
                <div class="col-md-4"></div>
            </div>
        </div>
    </body>
</html>
