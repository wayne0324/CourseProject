$(document).ready(function(){
    cat();
    brand();
    product();
    cart_checkout();
    
    function cat(){
        $.ajax({
            url:"action.php",
            method:"POST",
            data:{category:1},
            success:function(data){
                $("#get_category").html(data);
            }
        });
    }
    
    function brand(){
        $.ajax({
            url:"action.php",
            method:"POST",
            data:{brand:1},
            success:function(data){
                $("#get_brand").html(data);
            }
        });
    }
    
    function product(){
        $.ajax({
            url:"action.php",
            method:"POST",
            data:{getProduct:1},
            success:function(data){
                $("#get_product").html(data);
            }
        });
    }
    
    $("body").delegate(".category","click",function(event){        
        event.preventDefault();
        var cid=$(this).attr('cid');
//        alert(cid);
        $.ajax({
            url:"action.php",
            method:"POST",
            data:{get_selected_Category:1,cat_id:cid},
            success:function(data){
                $("#get_product").html(data);
            }
        });     
    });
    
    $("body").delegate(".category","click",function(event){        
        event.preventDefault();
        var cid=$(this).attr('cid');
//        alert(cid);
        $.ajax({
            url: "action.php",
            method: "POST",
            data:{pageCategory:1,cat_id:cid},
            success: function(data){
                $("#pageno").html(data);
            }
        }); 
        
    });
    
    
         
    $("body").delegate(".selectBrand","click",function(event){        
        event.preventDefault();
        var c=bid=$(this).attr('bid');
        $.ajax({
            url:"action.php",
            method:"POST",
            data:{pageBrand:1,brand_id:bid},
            success:function(data){
                $("#pageno").html(data);
            }
        });
    });
    $("body").delegate(".selectBrand","click",function(event){        
        event.preventDefault();
        var c=bid=$(this).attr('bid');
        $.ajax({
            url:"action.php",
            method:"POST",
            data:{selectBrand:1,brand_id:bid},
            success:function(data){
                $("#get_product").html(data);
            }
        });
    });
    
    $("#search_btn").click(function(){
        var keyword=$("#search").val();
        var category=$("#search_cat").val();
        if(keyword!=""){
            $.ajax ({
                url: "action.php",
                method: "POST",
                data: {search:1,keyword:keyword,cat_id:category},
                success: function(data){
                    $("#get_product").html(data);
                }
            });
        }
    });

    $("body").delegate("#product","click",function(event){
        event.preventDefault();
        var p_id=$(this).attr("pid");
        $.ajax({
            url: "action.php",
            method: "POST",
            data:{addToProduct:1,proId:p_id},
            success: function(data){
            $("#product_msg").html(data); 
            }
        });
        cart_container();
    });
    
    cart_container();
    function cart_container(){
        $.ajax({
            url: "action.php",
            method: "POST",
            data:{get_cart_product:1},
            success: function(data){
            $("#cart_product").html(data); 
            }
        });
        cart_count();
        
    }
    
    function cart_count(){
        $.ajax({
            url: "action.php",
            method: "POST",
            data:{cart_count:1},
            success: function(data){
            $(".badge_amt").html(data); 
            }
        });
    }
//    $("#cart_container").click(function(event){
//        event.preventDefault();
//        $.ajax({
//            url: "action.php",
//            method: "POST",
//            data:{get_cart_product:1},
//            success: function(data){
//            $("#cart_product").html(data); 
//            }
//        })
//        
//    })
    
    $("body").delegate(".qty","keyup",function(){
//        alert(1);
        var pid = $(this).attr("pid");
        var qty = $("#qty-"+pid).val();
        var price = $("#price-"+pid).val();
        var total = qty * price;
        $("#total-"+pid).val(total);
    });
    
    function cart_checkout(){
//        alert(0);
        $.ajax({
            url: "action.php",
            method: "POST",
            data: {cart_checkout:1},
            success: function(data){
            $("#cart_checkout").html(data);
            }
        });
    }
    
    $("body").delegate(".remove","click",function(event){
        event.preventDefault();
        var pid = $(this).attr("remove_id");
        //alert(pid);
        $.ajax({
            url: "action.php",
            method: "POST",
            data: {removeFromCart:1, removeId:pid},
            success: function(data){
//                alert(data);
                  $("#cart_msg").html(data);
                  cart_checkout();
                
            }
        });
    });
    $("body").delegate(".update","click",function(event){
        event.preventDefault();
        var pid = $(this).attr("update_id");
//        alert(pid);
        var qty = $("#qty-"+pid).val();
        var price = $("#price-"+pid).val();
        var total = $("#total-"+pid).val();
        $.ajax({
            url: "action.php",
            method: "POST",
            data: {updateProduct:1,updateId:pid,qty:qty,price:price,total:total},
            success: function(data){
//                alert(data);
                $("#cart_msg").html(data);
                cart_checkout();
            }
        });
    });
    page();
    function page(){
        $.ajax({
            url: "action.php",
            method: "POST",
            data: {page:1},
            success: function(data){
//                alert(data);
                $("#pageno").html(data);
            }
        });
    }

    $("body").delegate("#page","click",function(){
        var pn = $(this).attr("page");
        $.ajax({
            url: "action.php",
            method: "POST",
            data: {getProduct:1, setPage:1, pageNumber:pn},
            success: function(data){
//                alert(data);
                $("#get_product").html(data);
            }
        });
        
    });
    
    $("body").delegate("#page_cat","click",function(){
        var pn = $(this).attr("page");
        var cid = $(this).attr("cid");
        $.ajax({
            url: "action.php",
            method: "POST",
            data: {get_selected_Category:1, setPage:1, pageNumber:pn, cat_id:cid},
            success: function(data){
                $("#get_product").html(data);
            }
        });
        
    });
    $("body").delegate("#page_brd","click",function(){
        var pn = $(this).attr("page");
        var bid = $(this).attr("bid");
        $.ajax({
            url: "action.php",
            method: "POST",
            data: {selectBrand:1, setPage:1, pageNumber:pn, brand_id:bid},
            success: function(data){
                $("#get_product").html(data);
            }
        });
        
    });
    
    
    order_detail();
    function order_detail(){
        $.ajax({
            url: "action.php",
            method: "POST",
            data: {createOrder:1},
            success: function(data){
//                alert(data);
                $("#order_detail").html(data);
            }
        });
    }
    
    
    
//    $("body").delegate("#btn_checkout","click",function(){
//        alert(0111);
//        $.ajax({
//            url: "action.php",
//            method: "POST",
//            data: {createOrder:1},
//            success: function(data){
//                alert(data);
//                $("#order_detail").html(data);
//            }
//        });
//        
//    });
    
    
   
    
    
});

