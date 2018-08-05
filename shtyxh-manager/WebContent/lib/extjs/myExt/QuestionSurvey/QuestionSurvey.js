Ext.namespace('QuestionSurvey');
QuestionSurvey.QuestionSurveyPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	QuestionSurvey.QuestionSurveyPanel.superclass.constructor.call(this);
};


Ext.extend(QuestionSurvey.QuestionSurveyPanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;


		var store = new Ext.data.Store({
			pageSize:10,
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/questionsurvey/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'surveyName', 'surveyDesc','finished']
		});
		

    
	    var grid = new Ext.grid.GridPanel({
	  	  	frame:true,
	  	  	border:true,	
	  	  	enableHdMenu:false,
	        store: store,
	        loadMask:true,
	        forceFit :true ,  
	        selModel: {
	            type: 'spreadsheet',
	            checkboxSelect :true
	        },
	        bbar: new Ext.PagingToolbar({
			       
			        store: store,
			        displayInfo: true,
			        displayMsg: '当前 {0} 到 {1} 共 {2}',
			        emptyMsg: "没有数据返回"
	        }),
	        tbar:[{
					icon : _basePath+'/resources/images/icon/add.png',
					text : '添加问卷调查',
					handler : function() {
						me.addQuestionSurvey(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/start.png',
					text : '启用',
					handler : function() {
						var records=getRecords(grid);
						if(records==-1)
							return;
						me.questionSurveryState(records,store,mainId,"start");
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/pause.png',
					text : '终止',
					handler : function() {
						var records=getRecords(grid);
						if(records==-1)
							return;
						me.questionSurveryState(records,store,mainId,"pause");
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除问卷调查',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteQuestionSurvey(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "问卷调查名称",  sortable: true,  dataIndex: 'surveyName',align:'center'},
	            {header: "状态",dataIndex: 'finished',  align:'center',renderer:me.showState},
	            {header: "查看",dataIndex: 'id',  align:'center',renderer:me.buttonRender}
	            
	            
	        ],
	        width:'100%',
	        autoExpandColumn: 'surveyName',
	        viewConfig:{forceFit: true}
	    });
		
	  
		
		
		Ext.apply(this, {
			width:'100%',
			items : [grid],
			frame:false,
			resizable : false,
			layout:'fit',
			listeners:{
				beforeshow:function(){
				}
			}
		 });
	},
	showState:function(value){
		if(value){
			return "<font color='green'>启用</font>";
		}else{
			return "<font color='red'>终止</font>";
		}
		 
	},
	questionSurveryState:function(records,store,mainId,state){
		  Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
		  var qsobj = [];
		  var finished = true;
		  if (state=="pause"){
			  finished = false;
		  }
		  for(var i=0;i<records.length;i++){
			  var record = records[i];
			  var id = record.get("id");
			  qsobj.push({"id":id,"finished":finished,"__status":"update"});
		  }
		  Ext.Msg.confirm('提示信息','确认要改变状态？',function(op){  
		        if(op == 'yes'){
		        	Ext.Ajax.request({
		    			url : appName + '/admin/questionsurvey/update',
		                method : 'post',
		                headers: {'Content-Type':'application/json'},
		                params : JSON.stringify(qsobj),
		                success : function(response, options) {
		              	  Ext.getBody().unmask();
		              	  var responseArray = Ext.util.JSON.decode(response.responseText);
		                    if (responseArray.success == true) {
//		                  	    ExtAlert("成功");
		                  	    store.reload();
		                      }else{
		                      	ExtError(responseArray.message);
		                      }
		                },
		    			failure : function() {
		    				Ext.getBody().unmask();
		    				ExtError();
		    			}
		    		  });
		        }else{
		        	Ext.getBody().unmask();
		        }
		  })  
	},
	addQuestionSurvey:function(store,mainId){
		var win = new addorUpdateQuestionSurvey.addorUpdateQuestionSurveyWindow ({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store
		});
		win.show();
	},
	buttonRender:function(id){
		   return "<button  width=\"50px\" onclick=\"yulanQuestion('"+id+"')\">预览</button>&nbsp;&nbsp;" +
		   		"<button  width=\"50px\" onclick=\"yulanAnalysis('"+id+"')\">分析</button>&nbsp;&nbsp;" +
		   		"<button  width=\"50px\" onclick=\"getQuestionUrl('"+id+"')\">获取地址</button>&nbsp;&nbsp;";
	},
	deleteQuestionSurvey:function(records,store,mainId){
		  Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
		  var qsobj = [];
		  for(var i=0;i<records.length;i++){
			  var record = records[i];
			  var id = record.get("id");
			  qsobj.push({"id":id});
		  }
		  
		  
		  
		  Ext.Msg.confirm('提示信息','确认要删除这些信息吗？',function(op){  
		        if(op == 'yes'){
		        	Ext.Ajax.request({
		    			url : appName + '/admin/QuestionSurvey/remove',
		                method : 'post',
		                headers: {'Content-Type':'application/json'},
		                params : JSON.stringify(qsobj),
		                success : function(response, options) {
		              	  Ext.getBody().unmask();
		              	  var responseArray = Ext.util.JSON.decode(response.responseText);
		                    if (responseArray.success == true) {
		                  	    ExtAlert("成功");
		                  	    store.reload();
		                      }else{
		                      	ExtError(responseArray.message);
		                      }
		                },
		    			failure : function() {
		    				Ext.getBody().unmask();
		    				ExtError();
		    			}
		    		  });
		        }else{
		        	Ext.getBody().unmask();
		        }
		  })  
		  
	}
	
	
});

function getQuestionUrl(id){
	var mainId = "showUrl_";
	var win = new showUrlWindow.showUrlWindowWindow({
		id:id,
		mainId:mainId
	});
	win.show();
}

function yulanQuestion(id){
	var url =appName+"/index/questionsurvey/query?id="+id;
	window.open(url);
}

function yulanAnalysis(sid){
	var mainId = "analysis_";
	var win = new showAnalysisWindow.showAnalysisWindowWindow({
		sid:sid,
		mainId:mainId
	});
	win.show();
}

