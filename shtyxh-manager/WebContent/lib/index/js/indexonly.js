/*
* @Author: CCGP
* @Date:   2017-09-19 10:23:27
* @Last Modified by:   Marte
* @Last Modified time: 2018-02-27 09:36:47
*/
function dosearch(e){

if($("#dbselect option:selected").val()=="bidx"){
    $("#searchForm").attr("method", "get");
$("#searchForm").attr("action", "http://search.ccgp.gov.cn/bxsearch");
$("#searchForm").append("<input type=hidden name=bidSort value=0 /><input type=hidden name=pinMu value=0 /><input type=hidden name=bidType value=0 /><input type=hidden name=buyerName /><input type=hidden name=projectId /><input type=hidden name=displayZone /><input type=hidden name=zoneId /><input type=hidden name=agentName />");
}
if($("#dbselect option:selected").val()=="infox"){
    $("#searchForm").attr("method", "get");
$("#searchForm").attr("action", "http://search.ccgp.gov.cn/znzxsearch");
}
if($("#dbselect option:selected").val()=="zcfg"){
//$("#searchForm").append("<input type=hidden name=searchparam id=searchparam />");
$("#searchparam").val($("#kw").val());
$("#searchForm").attr("action", "http://search.ccgp.gov.cn/zcfgsearch");
$("#searchForm").attr("method", "post");
}
$("#searchtype").val(e);
document.searchForm.submit();
}

$("document").ready(function(){

  $("#slider4").responsiveSlides({
    auto: false,
    pager: false,
    nav: true,
    speed: 1000,
    namespace: "callbacks",
    before: function () {
      $('.events').append("<li>before</li>");
    },
    after: function () {
      $('.events').append("<li>after</li>");
    }
  });


//显示导航
var toShow=$("body").attr("id");
    if( toShow !==''){
        $("#ch_"+toShow).addClass('actv');
        //$("#nav-list-"+toShow+" > a").css("color","#fff");
    }

   
 if ($(".vF_title_bar2").length > 0) {
        $(".vF_title_bar2").each(function(){
            $(this).parent().parent().parent().children(".tab-con:first").show();
        });
    };
 if ($(".vF_title_bar_links").length > 0) {
        $(".vF_title_bar_links").each(function(){
            $(this).parent().parent().parent().children(".tab-con:first").show();
        });
    };
$(".vF_title_bar2 > h2").mouseover(function(e) {
         //   alert($(".vF-chm-titlebar").length);

                var tabs = $(this).parent().children("h2");
                var panels = $(this).parent().parent().parent().parent().children(".tab-con");
                var index = $.inArray(this, tabs);
                if (panels.eq(index)[0]) {
                    tabs.removeClass("current2")
                        .eq(index).addClass("current2");
                    panels.hide().eq(index).show();
                }

        });



//forsinglelinetitled_20171101
if($(".txtnews_topic").html().indexOf("<br>")<=0){
$(".txtnews_topic p").css("margin-top","35px");
}


});