$(document).ready(function(){

    $("#signin_form").validate({
        rules:{
            email:{
                required:true,
                email:true
            },
            password:{
                required:true,
                minlength:8
            }
        },
        messages:{
            email:"needs to be a valid email",
            password:"needs at least 8 characters"      
        }
    });
    
    $("#signin_button").click(function(event){
        var isvalid = $("#signin_form").valid();
        if (isvalid) {
            event.preventDefault();
            var email=$("#email").val();
            var pass=$("#password").val();
            $.ajax({
                url:"login_action.php",
                method:"POST",
                data:{userSignin:1,userEmail:email,userPassword:pass},
                success:function(data){
                    if(data=="successful"){
                        window.location.href="profile.php";
                    }else{
                        $("#signin_error").html("email or password error!");
                    }
                }
            });
        }
            
        });
});