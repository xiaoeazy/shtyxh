$("document").ready(function(){
$(".rh_printer").css("background","url(http://pub.ccgp.gov.cn/common/images/rh_printericon.gif)");
validate();
$("#btn_print").css("cursor","pointer");
$("#qrshow").css("cursor","pointer");



$("#imgqrshow").css("cursor","pointer");
$(".rh_printer").css("cursor","pointer");


$("#sideprinter").mouseover(function(){
    $(".rh_printer").css("background","url(http://pub.ccgp.gov.cn/common/images/rh_printericon_h.gif)");
});
$("#sideprinter").mouseout(function(){
    $(".rh_printer").css("background","url(http://pub.ccgp.gov.cn/common/images/rh_printericon.gif)");
});
$("#sideprinter").click(function(){
    window.print();
});

var urlstring= window.location.href;

$("#qrcode").qrcode(
{
    render: "table", //table方式canvas
    width: 165, //宽度
    height:165, //高度
   correctLevel:2,
    text:location.href
});

$("#sourceName").text("中国政府采购网");
$("#ins1").each(function(){
    var bidtype = $(this).text();
    if( bidtype == "1"){$(this).text("全国人大")}
    if( bidtype == "2"){$(this).text("国务院")}
    if( bidtype == "3"){$(this).text("财政部")}
    if( bidtype == "4"){$(this).text("其他部委")}
    if( bidtype == "5"){$(this).text("集采中心")}
    if( bidtype == "6"){$(this).text("地方财政")}
    if( bidtype == "7"){$(this).text("国务院办公厅")}
});
if($("#ins2").html().indexOf(",")>0){
var ty=$("#ins2").html().split(",");
var tylength=ty.length;
$("#ins2").html("");
for(i=0;i<tylength;i++){
    if( ty[i] == "1000"){$("#ins2").append("采购主体类，");}
    if( ty[i] == "1001"){$("#ins2").append("采购中心，")}
    if( ty[i] == "1002"){$("#ins2").append("代理机构，")}
    if( ty[i] == "1003"){$("#ins2").append("评审专家，")}
    if( ty[i] == "1004"){$("#ins2").append("供应商，")}
    if( ty[i] == "2000"){$("#ins2").append("政策功能类，")}
    if( ty[i] == "2001"){$("#ins2").append("节能环保，")}
    if( ty[i] == "2002"){$("#ins2").append("中小企业，")}
    if( ty[i] == "2099"){$("#ins2").append("政策功能类其他，")}
    if( ty[i] == "3000"){$("#ins2").append("交易系统类，")}
    if( ty[i] == "3001"){$("#ins2").append("采购计划，")}
    if( ty[i] == "3002"){$("#ins2").append("信息统计，")}
    if( ty[i] == "3003"){$("#ins2").append("项目采购，")}
    if( ty[i] == "3099"){$("#ins2").append("交易系统类其他，")}
    if( ty[i] == "4000"){$("#ins2").append("采购方式类，")}
    if( ty[i] == "4001"){$("#ins2").append("公开招标，")}
    if( ty[i] == "4002"){$("#ins2").append("竞争性谈判，")}
    if( ty[i] == "4003"){$("#ins2").append("单一来源，")}
    if( ty[i] == "4004"){$("#ins2").append("非招标采购，")}
    if( ty[i] == "4099"){$("#ins2").append("采购方式类其他，")}
    if( ty[i] == "5000"){$("#ins2").append("其他文件类，")}
    if( ty[i] == "5001"){$("#ins2").append("信息公开，")}
    if( ty[i] == "5002"){$("#ins2").append("行政处罚，")}
    if( ty[i] == "5003"){$("#ins2").append("公共资源，")}
    if( ty[i] == "5004"){$("#ins2").append("政府和社会资本合作，")}
    if( ty[i] == "5005"){$("#ins2").append("会议培训，")}
    if( ty[i] == "5006"){$("#ins2").append("采购目录，")}
    if( ty[i] == "5007"){$("#ins2").append("信息化建设，")}
    if( ty[i] == "5008"){$("#ins2").append("政府购买服务，")}
    if( ty[i] == "5009"){$("#ins2").append("网站建设，")}
    if( ty[i] == "5099"){$("#ins2").append("其他，")}

    //if(i!==tylength-1){$("#ins2").append("，")}

}
var s=$("#ins2").text().substring(0, $("#ins2").text().lastIndexOf('，'));

$("#ins2").text(s);
}
$("#ins2").each(function(){
    var bidtype = $(this).text();
    if( bidtype == "1000"){$(this).text("采购主体类")}
    if( bidtype == "1001"){$(this).text("采购中心")}
    if( bidtype == "1002"){$(this).text("代理机构")}
    if( bidtype == "1003"){$(this).text("评审专家")}
    if( bidtype == "1004"){$(this).text("供应商")}
    if( bidtype == "2000"){$(this).text("政策功能类")}
    if( bidtype == "2001"){$(this).text("节能环保")}
    if( bidtype == "2002"){$(this).text("中小企业")}
    if( bidtype == "2099"){$(this).text("政策功能类其他")}
    if( bidtype == "3000"){$(this).text("交易系统类")}
    if( bidtype == "3001"){$(this).text("采购计划")}
    if( bidtype == "3002"){$(this).text("信息统计")}
    if( bidtype == "3003"){$(this).text("项目采购")}
    if( bidtype == "3099"){$(this).text("交易系统类其他")}
    if( bidtype == "4000"){$(this).text("采购方式类")}
    if( bidtype == "4001"){$(this).text("公开招标")}
    if( bidtype == "4002"){$(this).text("竞争性谈判")}
    if( bidtype == "4003"){$(this).text("单一来源")}
    if( bidtype == "4004"){$(this).text("非招标采购")}
    if( bidtype == "4099"){$(this).text("采购方式类其他")}
    if( bidtype == "5000"){$(this).text("其他文件类")}
    if( bidtype == "5001"){$(this).text("信息公开")}
    if( bidtype == "5002"){$(this).text("行政处罚")}
    if( bidtype == "5003"){$(this).text("公共资源")}
    if( bidtype == "5004"){$(this).text("政府和社会资本合作")}
    if( bidtype == "5005"){$(this).text("会议培训")}
    if( bidtype == "5006"){$(this).text("采购目录")}
    if( bidtype == "5007"){$(this).text("信息化建设")}
    if( bidtype == "5008"){$(this).text("政府购买服务")}
    if( bidtype == "5009"){$(this).text("网站建设")}
    if( bidtype == "5099"){$(this).text("其他")}
});


   });
