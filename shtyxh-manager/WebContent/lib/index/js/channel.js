/*
* @Author: CCGP
* @Date:   2017-09-19 10:23:27
* @Last Modified by:   Marte
* @Last Modified time: 2017-10-28 11:17:56
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
    $("#searchForm").append("<input type=hidden name=searchparam id=searchparam />");
$("#searchparam").val($("#kw").val());
$("#searchForm").attr("action", "http://search.ccgp.gov.cn/zcfgsearch");
$("#searchForm").attr("method", "post");
}
$("#searchtype").val(e);
document.searchForm.submit();
}


$("document").ready(function(){

if ($(".vF_title_bar2").length > 0) {
        $(".vF_title_bar2").each(function(){
            $(this).parent().parent().parent().children(".tab-con:first").show();
        });
    };
 if ($(".vF_title_bar3").length > 0) {
        $(".vF_title_bar3").each(function(){
            $(this).parent().parent().parent().children(".tab-con:first").show();
        });
    };
 if ($(".vF_title_bar_links").length > 0) {
        $(".vF_title_bar_links").each(function(){
            $(this).parent().parent().parent().children(".tab-con:first").show();
        });
    };

//显示导航
var toShow=$("body").attr("id");
    if( toShow !==''){
        $("#ch_"+toShow).addClass('actv');
        //$("#nav-list-"+toShow+" > a").css("color","#fff");
    }


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

$(".vF_title_bar3 > h2").mouseover(function(e) {
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

$(".vF_title_bar_links > h2").mouseover(function(e) {
         //   alert($(".vF-chm-titlebar").length);

                var tabs = $(this).parent().children("h2");
                var panels = $(this).parent().parent().parent().parent().children(".tab-con");
                var index = $.inArray(this, tabs);
                if (panels.eq(index)[0]) {
                    tabs.removeClass("current3")
                        .eq(index).addClass("current3");
                    panels.hide().eq(index).show();
                }

        });

if (toShow == 'zcfg'){
        $(".vT-ch-search").hide();
        $(".vT-chs-section-content-list li:contains('类')").addClass('sorthead');
    }

/*forREALdel*/
// if($(".c_list > li  > a").length>0){
// $(".c_list > li  > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('shtml')<=0){
// var newlonkadd=linkadd.replace("htm","shtml");
// $(this).attr("href",newlonkadd);}
// });}

// if($(".vT-chs-section-content-list > li  > a").length>0){
// $(".vT-chs-section-content-list > li  > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('shtml')<=0){
// var newlonkadd=linkadd.replace("htm","shtml");
// $(this).attr("href",newlonkadd);}
// });}
// if($(".xw-more-list > li  > a").length>0){
// $(".xw-more-list > li  > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('shtml')<=0){
// var newlonkadd=linkadd.replace("htm","shtml");
// $(this).attr("href",newlonkadd);}
// });}


// if($(".vT-cht-section-picnews > a").length>0){
// $(".vT-cht-section-picnews > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('shtml')<=0){
// var newlonkadd=linkadd.replace("htm","shtml");
// $(this).attr("href",newlonkadd);}
// });}

// if($(".vT-cht-xw-topic > h1 > a").length>0){
// $(".vT-cht-xw-topic > h1 > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('shtml')<=0){
// var newlonkadd=linkadd.replace("htm","shtml");
// $(this).attr("href",newlonkadd);}
// });}

// if($(".vT-cht-xw-topic > p > a").length>0){
// $(".vT-cht-xw-topic > p > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('shtml')<=0){
// var newlonkadd=linkadd.replace("htm","shtml");
// $(this).attr("href",newlonkadd);}
// });}

// if($(".vT-zcfg-cgf-t-pic > a").length>0){
// $(".vT-zcfg-cgf-t-pic > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('shtml')<=0){
// var newlonkadd=linkadd.replace("htm","shtml");
// $(this).attr("href",newlonkadd);}
// });}

// if($("body#zcfg .vT-chs-section-content-list > li >a").length>0){
// $("body#zcfg .vT-chs-section-content-list > li >a").each(function(){
// var loc=$(this).attr("href");
// var newloc=loc+"main.htm";
// $(this).attr("href",newloc);
// });}

// if($(".xw-more-list > li > span > a").length>0){
// $(".xw-more-list > li > span > a").each(function(){
// var loc=$(this).attr("href");
// var newloc=loc+"main.htm";
// $(this).attr("href",newloc);
// });}


// if($("#ppp").length>0){
//    // alert($("#bid_lst").length);

// $(".c_list > li  > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('zygg')>0){
// var newlonkadd=linkadd.replace("zygg","zcgg/zygg");
// $(this).attr("href",newlonkadd);
// }else{
//     if(linkadd.indexOf('dfgg')>0){
// var newlonkaddd=linkadd.replace("dfgg","zcgg/dfgg");
// $(this).attr("href",newlonkaddd);}
// }
// });

// }

// if($(".gpsr_xmxx_lst").length>0){
//    // alert($("#bid_lst").length);

// $(".gpsr_xmxx_lst > li  > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('zygg')>0){
// var newlinkadd=linkadd.replace("zygg","zcgg/zygg");
// if(newlinkadd.indexOf('shtml')<=0){
// newlinkadd=newlinkadd.replace("htm","shtml");
// }
// $(this).attr("href",newlinkadd);
// }
// });

// $(".gpsr_xmxx_lst > li  > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('dfgg')>0){
// var newlinkadd=linkadd.replace("dfgg","zcgg/dfgg");
// if(newlinkadd.indexOf('shtml')<=0){
// newlinkadd=newlinkadd.replace("htm","shtml");
// }
// $(this).attr("href",newlonkadd);
// }
// });

// }



// if($(".xxgg_bid_lst").length>0){
//    // alert($("#bid_lst").length);

// $(".xxgg_bid_lst > li  > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('zygg')>0){
// var newlinkadd=linkadd.replace("zygg","zcgg/zygg");
// if(newlinkadd.indexOf('shtml')<=0){
// newlinkadd=newlinkadd.replace("htm","shtml");
// }
// $(this).attr("href",newlinkadd);
// }
// });

// $(".xxgg_bid_lst > li  > a").each(function(){
// var linkadd=$(this).attr("href");
// if(linkadd.indexOf('dfgg')>0){
// var newlinkadd=linkadd.replace("dfgg","zcgg/dfgg");
// if(newlinkadd.indexOf('shtml')<=0){
// newlinkadd=newlinkadd.replace("htm","shtml");
// }
// $(this).attr("href",newlinkadd);
// }
// });

// }


/*forREALdel*/


});