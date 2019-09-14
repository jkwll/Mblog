var obj=null;
var As=document.getElementById('topnav').getElementsByTagName('a');
obj = As[0];
for(i=1;i<As.length;i++){if(window.location.href.indexOf(As[i].href)>=0)
obj=As[i];}
obj.id='topnav_current'


//ÓÒ²à¸¡¶¯
jQuery(document).ready(function() {   
    var a = $("#rnav_box").offset();   
    $(window).scroll(function() {   
        var b = $(window).scrollTop();   
        if (b > a.top) { 
           if((b + 100% + 200) < document.body.parentNode.scrollHeight)  
              $("#rnav").addClass("fixed");   
           else
              $("#rnav").addClass("fixed2");
        } else {   
            $("#rnav").removeClass("fixed");  
            $("#rnav").removeClass("fixed2"); 
        }   
    });   
});  