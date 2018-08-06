var appName = _basePath;
var tabs;
var _panel2;
var _panel3;
var nonePic = _basePath +"/resources/images/none/none.jpg";
var newsRoleNum=0;
var newsAssessmentNum=0;
var newsOtherNum=0;
var newsQuestionNum=0;
var newsSysNum=0;

Ext.onReady(function() {
		Ext.QuickTips.init();//QuickTips的作用是读取标签中的ext:qtip属性，并为它赋予显示提示的动作。
//			Ext.BLANK_IMAGE_URL = appName+'/js/ext3/resources/images/default/s.gif';//获取路径
		Ext.getDoc().on("contextmenu", function(e) {
			e.stopEvent(); //禁止整个页面的右键
		});
		
		Ext.Ajax.on('requestcomplete',checkUserSessionStatus, this);//使用Ajax完成异步请求的交互，注册requestcomplete时间，每个ajax请求成功后首先
		function checkUserSessionStatus(conn,response,options) {   
			try {
				var sessionStatus = response.getResponseHeader("sessionstatus");   
		        if(typeof(sessionStatus) != "undefined"){
		        	sessiontimeout = true;
		    		Ext.Msg.alert('提示', '会话超时，请重新登录!', function(btn, text){     
		                if (btn == 'ok'){    
		                    var redirect = appName+'/admin/login';     
		                    window.location = redirect;     
		                }     
		            });	
		         }       
			} catch (e) {
				// TODO: handle exception
			}       
		}

	var msgTip = Ext.MessageBox.show({
		title : '提示',
		msg : '页面初始化中,请耐心等待。。。'
	});
	var funcs = userFunc;
	function initTwo() {
		msgTip.hide();
		tabs = new Ext.TabPanel({
			enableTabScroll : true,
			border : false,
			activeTab : 0,
			items : [ {
				headerCfg : {
					style : 'display:none'
				},
				itemId : 'shouye',
				title : "首页",
				html : ''
			} ]
		});
		new Ext.Viewport({
			layout : "border",
			items : [ new Header.HeaderPanel(), {
				region : 'west',
				id : 'west-panel',
				title : '导航',
				split : true,
				width : 200,
				minSize : 175,
				maxSize : 200,
				collapsible : true,
				margins : '0 0 0 5',
				layout : 'accordion',
				layoutConfig : {
					animate : true
				},
				items : [ {
					title : '协会概况',
					xtype:'panel',
					id:'main_xhgk',
					border : false,
					iconCls : 'nav',
					html : getXHGK(funcs)
				}, {
					title : '资讯中心',
					xtype:'panel',
					id:'main_zxzx',
					border : false,
					iconCls : 'nav',
					html : getNews(funcs)
				},{
					title : '培训与鉴定',
					id:'main_pgrw',
					border : false,
					iconCls : 'nav',
					html : getAssessment(funcs)
				},{
					title : '其他',
					id:'main_qt',
					border : false,
					iconCls : 'nav',
					html : getOthers(funcs)
				},{
					title : '调查问卷',
					id:'main_dcwj',
					border : false,
					iconCls : 'nav',
					html : getQuestion(funcs)
				},{
					title : '系统',
					id:'main_xt',
					border : false,
					iconCls : 'nav',
					html : getConfig(funcs)
				} ]
			}, {
				region : 'center',
				layout : 'fit',
				items : tabs
			} ],
			listeners:{
				afterRender:function(){
					if(newsRoleNum==0)
						Ext.getCmp("main_zxzx").hide();
					if(newsAssessmentNum==0)
						Ext.getCmp("main_pgrw").hide();
					if(newsOtherNum==0)
						Ext.getCmp("main_qt").hide();
					if(newsQuestionNum==0)
						Ext.getCmp("main_dcwj").hide();
					if(newsSysNum==0)
						Ext.getCmp("main_xt").hide();
				}
			}
		});

	}

	initTwo();

})

function isHaveFunc(func,value){
	var flag = false;
	for(var i =0;i<func.length;i++){
		if(func[i].id==value){
			flag=true;
			break;
		}
	}
	return flag;
}

function addFuncStr(func){
	if(func!=""){
		var str = "";
		str += "<div style='width:100%;height:100%;overflow-y:auto;overflow-x:auto;'>";
		str += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
		
		str += func;
		
		str += '</table></div>';
		return str;
	}
}


function getQuestion(funcs) {
	var str = "";
	
	if(isHaveFunc(funcs,16))
		str +=addFunc('questionSurvey1.png','questionSurvey2.png','questionSurvey');
	if(str!="")
		newsQuestionNum=1;
	return addFuncStr(str);
}

function getXHGK(funcs){
	var str = "";
	str +=addFunc('introduction1.png','introduction2.png','introduction');
	str +=addFunc('companyFramework1.png','companyFramework2.png','companyFramework');
	str +=addFunc('companyDirector1.png','companyDirector2.png','companyDirector');
	str +=addFunc('history1.png','history2.png','history');

	str +=addFunc('member1.png','member2.png','memberUnits');
	str +=addFunc('contact1.png','contact2.png','contact');
	return addFuncStr(str);
}
function getNews(funcs){
	var str = "";
	if(isHaveFunc(funcs,6))
		str +=addFunc('news1.png','news2.png','news');
	if(isHaveFunc(funcs,7))
		str +=addFunc('type1.png','type2.png','newsType');
	if(isHaveFunc(funcs,8))
		str +=addFunc('source1.png','source2.png','newsSource');
	if(str!="")
		newsRoleNum = 1;
	str +=addFunc('offers1.png','offers2.png','offers');
	return addFuncStr(str);
}

function getAssessment(funcs){
	var str = "";
	if(isHaveFunc(funcs,3))
		str +=addFunc('assessmentActivity1.png','assessmentActivity2.png','assessmentActivity');
	if(isHaveFunc(funcs,4))
		str +=addFunc('assessmentUserProgress1.png','assessmentUserProgress2.png','assessmentUserProgress');
	if(isHaveFunc(funcs,17))
		str +=addFunc('expertAssessment1.png','expertAssessment2.png','expertAssessment');
	if(isHaveFunc(funcs,2))
		str +=addFunc('assessmentType1.png','assessmentType2.png','assessmentType');
	if(str!="")
		newsAssessmentNum=1;
	return addFuncStr(str);
}


function getOthers(funcs){
	var str = "";
	if(isHaveFunc(funcs,5))
		str +=addFunc('download1.png','download2.png','download');
	if(isHaveFunc(funcs,14))
		str +=addFunc('carousel1.png','carousel2.png','carousel');
	if(str!="")
		newsOtherNum=1;
	return addFuncStr(str);
}



function getConfig(funcs){
	var str = "";
	if(isHaveFunc(funcs,9))
		str +=addFunc('link1.png','link2.png','link');
	if(isHaveFunc(funcs,10))
		str +=addFunc('config1.png','config2.png','config');
	if(isHaveFunc(funcs,11))
		str +=addFunc('attribute1.png','attribute2.png','newsAttribute');
	if(isHaveFunc(funcs,12))
		str +=addFunc('user1.png','user2.png','user');
	if(isHaveFunc(funcs,13))
		str +=addFunc('role1.png','role2.png','role');
	if(str!="")
		newsSysNum=1;
	
	return addFuncStr(str);
}

function addFunc(png1,png2,tabName){
	str = '<tr><td  align="center"><img src="'
		+ _basePath
		+ '/resources/images/leftImg/'+png1+'" onmouseover="this.src=\''
		+ _basePath
		+ '/resources/images/leftImg/'+png2+'\';this.style.cursor=\'pointer\'" onmouseout="this.src=\''
		+ _basePath
		+ '/resources/images/leftImg/'+png1+'\'" onclick="addtabs(\''+tabName+'\')"></td></tr>';
	return str;
}

function addtabs(us) {
	if (us == "link") {
		var panel=  new Link.LinkPanel({
			mainId: us
		}) ;
		addTabFuns(us,panel,'相关链接');
	}else if (us == "introduction") {
		var panel=  new Introduction.IntroductionPanel({
			mainId: us
		});
		addTabFuns(us,panel,'协会简介');
	}else if (us == "newsType") {
		var panel=  new NewsType.NewsTypePanel({
			mainId: us
		});
		addTabFuns(us,panel,'资讯类型配置');
	}else if (us == "newsSource") {
		var panel=  new NewsSource.NewsSourcePanel({
			mainId: us
		});
		addTabFuns(us,panel,'资讯来源配置');
	}else if (us == "newsAttribute") {
		var panel=  new NewsAttribute.NewsAttributePanel({
			mainId: us
		});
		addTabFuns(us,panel,'自定义属性配置');
	}else if (us == "news") {
		var panel=  new News.NewsPanel({
			mainId: us
		});
		addTabFuns(us,panel,'咨讯中心');
	}else if (us == "config") {
		var panel=  new Config.ConfigPanel({
			mainId: us
		});
		addTabFuns(us,panel,'系统配置');
	}else if (us == "user") {
		var panel=  new User.UserPanel({
			mainId: us
		});
		addTabFuns(us,panel,'用户管理');
	}else if (us == "role") {
		var panel=  new Role.RolePanel({
			mainId: us
		});
		addTabFuns(us,panel,'角色配置');
	}else if (us == "assessmentType") {
		var panel=  new AssessmentType.AssessmentTypePanel({
			mainId: us
		});
		addTabFuns(us,panel,'评估任务类别');
	}else if (us == "assessmentActivity") {
		var panel=  new AssessmentActivity.AssessmentActivityPanel({
			mainId: us
		});
		addTabFuns(us,panel,'评估任务');
	}else if (us == "assessmentUserProgress") {
		var panel=  new AssessmentUserProcess.AssessmentUserProcessPanel({
			mainId: us
		});
		addTabFuns(us,panel,'评估任务进度');
	}else if (us == "carousel") {
		var panel=  new Carousel.CarouselPanel({
			mainId: us
		});
		addTabFuns(us,panel,'轮播图');
	}else if (us == "download") {
		var panel=  new Download.DownloadPanel({
			mainId: us
		});
		addTabFuns(us,panel,'文档下载');
	}else if (us == "contact"){
		var panel=  new Contact.ContactPanel({
			mainId: us
		});
		addTabFuns(us,panel,'联系我们');
	}else if (us == "questionSurvey"){
		var panel=  new QuestionSurvey.QuestionSurveyPanel({
			mainId: us
		});
		addTabFuns(us,panel,'问卷调查');
	}else if(us == "expertAssessment"){
		var panel=  new ExpertAssessmentUserProcess.ExpertAssessmentUserProcessPanel({
			mainId: us
		});
		addTabFuns(us,panel,'专家审核');
	}else if(us == "memberUnits"){
		var panel=  new MemberUnits.MemberUnitsPanel({
			mainId: us
		});
		addTabFuns(us,panel,'会员单位');
	}else if(us == "offers"){
		var panel=  new Offers.OffersPanel({
			mainId: us
		});
		addTabFuns(us,panel,'园所招聘');
	}else if(us == "history"){
		var panel=  new History.HistoryPanel({
			mainId: us
		});
		addTabFuns(us,panel,'历史沿革');
	}else if(us == "companyFramework"){
		var panel=  new CompanyFramework.CompanyFrameworkPanel({
			mainId: us
		});
		addTabFuns(us,panel,'协会架构图');
	}else if(us == "companyDirector"){
		var panel=  new CompanyDirector.CompanyDirectorPanel({
			mainId: us
		});
		addTabFuns(us,panel,'历届理事');
	}
}

function addTabFuns(us,itemObj,title){
	var tab = tabs.getComponent(us);
	if (tab==null) {
		tabs.add({
			itemId : us,
			layout : 'fit',
			title : title,
			iconCls : 'introduction',
			width : '100%',
			items : [itemObj ],
			closable : true
		}).show();
	} else {
		tab.show();
	}
}
