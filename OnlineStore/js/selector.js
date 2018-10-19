$(document).ready(function() {
  $('#search_cat').change(function(){
    $("#width_tmp_option").html($('#search_cat option:selected').text()); 
    $(this).width($("#width_tmp_select").width());  
  });
});