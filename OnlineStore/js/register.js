 $(document).ready(function(){   
    $("#myform").validate({
        rules:{
            f_name:{
                required:true,
                minlength:2
            },
            l_name:{
                required:true,
                minlength:2
            },
            email:{
                required:true,
                email:true
            },
            password:{
                required:true,
                minlength:8
            },
            repassword:{
                required:true,
                minlength:8,
                equalTo:"#password"
            },
            mobile:{
                required:true,
                phoneUS:true

            }
        },
        messages:{
            f_name:"needs to be at least 2 characters",
            l_name:"needs to be at least 2 characters",
            email:"needs to be a valid email",
            password:"needs at least 8 characters",
            repassword:"needs to be same as password",
            mobile: "needs be in the fomrat (xxx)xxx-xxxx"        
        }
    });

    $("#signup_button").click(function(event){
        var isvalid = $("#myform").valid();
        if (isvalid) {
            event.preventDefault();
            $.ajax ({
                url: "signup_action.php",
                method: "POST",
                data: $("#myform").serialize(),
                success: function(data){
                   //$("#signup_msg").html(data);
                   if(data=="successful"){
                        window.location.href="profile.php";
                    }else{
                        $("#signup_msg").html(data);
                    }
                }
            });
            
        }
        
    });

});