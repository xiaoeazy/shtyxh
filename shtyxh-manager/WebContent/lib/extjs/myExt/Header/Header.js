Ext.namespace('Header.Header');
Header.HeaderPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	Header.HeaderPanel.superclass.constructor.call(this);
};


Ext.extend(Header.HeaderPanel, Ext.form.FormPanel , {
	initUIComponents : function() {
		var version=document.getElementById("theVersion").value;
		var mainId="Header_";
		var nowTime = this.nowTime();
		Ext.apply(this, {
			width:'100%',
			region : 'north',
			height: 87,
			border : 0,
			frame:false,
			defaults: {
				border : false
			},
			items : [{
				layout:'column',
				border : 0,
				height: 55,
				defaults: {
					height: 55,
					border : false
				},
				items: [{
					width: 1020,
					xtype: 'panel',
					bodyStyle:'background-image:url('+_basePath+'/resources/images/header/logoMain.png)'
				}, {
					columnWidth: 1,
					xtype: 'panel',
					bodyStyle:'background-image:url('+_basePath+'/resources/images/header/top1.png)',
					html: '<font color="#ffffff" style="font-size: 9pt;"><span id="version_id">'+version+'</span></font>'
				}]
			}, {
				layout:'column',
				border : false,
				height: 32,
				defaults: {
					height: 32,
					border : false
				},
				items: [{
					xtype: 'panel',
					bodyStyle:'background-image:url('+_basePath+'/resources/images/header/top2.png)',
					width: 324,
					layout:'column',
					defaults: {
						height: 32,
						border : false
					},
					items: [{
						xtype: 'container',
						style: 'padding:2px 0px 0px 0px',
						html: '&nbsp;&nbsp;&nbsp;<img src="'+_basePath+'/resources/images/header/progress.gif" />'
					}, {
						xtype: 'container',
						style : 'padding:4px 0px 0px 0px',
						html: '<font color="#ffffff" style="font-size:9pt;"><span>'+nowTime+'</span></font>'
					}]
				}, {
					xtype: 'panel',
					bodyStyle:'background-image:url('+_basePath+'/resources/images/header/top3.png)',
					width: 52
				}, {
					xtype: 'panel',
					bodyStyle:'background-image:url('+_basePath+'/resources/images/header/top4.png)',
					columnWidth: 1,
					items: [{
						xtype: 'container',
						height: 32,
						border : false,
						style: 'padding:7px 0px 0px 0px',
						html: '<font color="#ffffff" style="font-size:9pt;"><span>'+' [ <a href="javascript:currentUserUpdate()" style="color:#fff000">'+theLoginUserName+'</a> ]   '+'</span>'+
						//' [ <span><a href="javascript:getKSDLM()" style="color:#fff000">快速登录码</a> ]  </span>'+
						'</font>'
					}]
				}, {
					xtype: 'panel',
					bodyStyle:'background-image:url('+_basePath+'/resources/images/header/top5.png)',
					width: 160,
					layout:'column',
					defaults: {
						height: 32,
						border : false
					},
					items: [{
						xtype: 'container',
						style: 'padding:3px 0px 0px 32px',
						html: ''
					},{
						xtype: 'container',
						style: 'padding:3px 0px 0px 32px',
						html: ''
					},{
						xtype: 'container',
						style: 'padding:3px 0px 0px 22px',
						html: '<a href="javascript:toIndex()"><img width="23px" height="23px" src="'+_basePath+'/resources/images/header/house.png" alt="前台" title="前台"/></a>'
					},{
						xtype: 'container',
						style: 'padding:3px 0px 0px 10px',
						html: '<a href="javascript:loginOut()"><img width="23px" height="23px" src="'+_basePath+'/resources/images/header/loginout.png" alt="退出" title="退出"/></a>'
					}]
				}]
			}]
			
		 });
	},

	nowTime : function (){
		var newDateObj = new Date();
		var year=newDateObj.getFullYear();
		var month=newDateObj.getMonth()+1;
		var day=newDateObj.getDate();
		var x=newDateObj.getDay(); 
		var j;
		if(x==1) j="星期一";
		else if(x==2)j="星期二";
		else if(x==3)j="星期三";
		else if(x==4)j="星期四";
		else if(x==5)j="星期五";
		else if(x==6)j="星期六";
		else j="星期天";
		var s=year+"年"+month+"月"+day+"日 " +j;
		return s;
	}



	
});


function loginOut(){
	  Ext.MessageBox.confirm("提示","是否要退出",function(btn){
		  if (btn == 'yes')  {
			  window.location.href=appName+"/admin/logout";
	   }
	  });
		 
}
function toIndex(){
	window.open(appName+"/");
}
function currentUserUpdate(){
	  var win =new updateCurrentUser.updateCurrentUserWin();
	  win.show();

}

function getKSDLM(){  //获取快速登陆码
	  var win =new getKSDLM.getKSDLMWin();
	  win.show();

}

function createxmlHttp(){
	var xmlhttp;
	if(window.ActiveXObject){
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if(window.XMLHttpRequest){
		xmlhttp=new XMLHttpRequest();
	}
	return xmlhttp;
}


function download(){
	var xmlhttp=createxmlHttp();
	var url=appName+"/pc/download.action";
	xmlhttp.onreadystatechange=function(){downloadReturn(xmlhttp)};
	xmlhttp.open("POST",url,true);
	xmlhttp.send(null);
}
function downloadReturn(xmlhttp){
	if(xmlhttp.readyState==4){
		if(xmlhttp.status==200){
			var json=eval("(" + xmlhttp.responseText + ")");
			if(json.success){
				 self.location   = appName+"/pc/download2.action";  
			}else{
				alert(json.msg);
			}
			
		}else{
			alert("无法获取信息，请联系管理员！");
			return;
		}
	}
}

