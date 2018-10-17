Ext.namespace('AssessmentActivity');
AssessmentActivity.AssessmentActivityPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	AssessmentActivity.AssessmentActivityPanel.superclass.constructor.call(this);
};


Ext.extend(AssessmentActivity.AssessmentActivityPanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;
	//===========================formpanel=========================
	
	var typeid_Combo_Store = new Ext.data.Store({
		pageSize:0,
		proxy: {
	        type: 'ajax',
	        url : appName+ '/admin/assessment/type/queryAll',
	        reader: {
	        	root : "results",
				totalProperty: "totalProperty",
				successProperty:'success'
	        }
	    },
	    autoLoad : true,
	    fields: ['id', 'assessmentTypeName'],
        listeners:{
        	'load': function(store, records, options) {
	    		 store.insert(0,{id:'-1',assessmentTypeName:'所有'});
	    	}
        }
	});
	
   var typeCombo = new Ext.form.ComboBox({
	   		fieldLabel:'评估任务类型',
    	    id:mainId+"assessmentTypeId",
            store : typeid_Combo_Store,  
            valueField : "id",  
            mode : 'remote',  
            displayField : "assessmentTypeName",  
            forceSelection : true,  
            emptyText : '请选择',  
            editable : false,  
            triggerAction : 'all',  
            hiddenName : "assessmentTypeName",  
            autoShow : true,  
            selectOnFocus : true,  
            name : "assessmentTypeId"
        }); 
	
	var formpanel = new Ext.FormPanel({
		  region:'north',
		  labelAlign: "right",
		  labelWidth :100,
	      frame: true,
	      width: '100%',
	      bodyStyle:'margin: 0px auto',
	      defaults:{
             xtype:"textfield",
             width:'100%',
             bodyStyle:'padding:10px 0px 10px 0px'
          },
		  items : [
		  			{
		          		fieldLabel:'评估任务名',
						name: 'assessmentActivityName',
						id:mainId+"assessmentActivityName",
			            maxLength:45 ,
			            width:400
		  			},
		  			typeCombo
		  			],
		   buttons:[{
			    width:50,
				text : '查询',
				handler : function(button, event) {
					var assessmentActivityName = Ext.getCmp(mainId+"assessmentActivityName").getValue().trim();
					var assessmentTypeId 	  = Ext.getCmp(mainId+"assessmentTypeId").getValue();
					if(assessmentTypeId==-1){
						assessmentTypeId = null;
					}
					store.proxy.url = appName+ '/admin/assessment/activity/query';
					store.proxy.extraParams={
							page:1,
							start:0,
							assessmentActivityName:assessmentActivityName,
							assessmentTypeId:assessmentTypeId
					};
					store.load();  
				}
		    }]
	});

	
	
	//==========================grid====================
		var store = new Ext.data.Store({
			pageSize:10,
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/assessment/activity/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'assessmentActivityName']
		});
		

    
	    var grid = new Ext.grid.GridPanel({
	    	region:'center',
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
					text : '添加评估任务',
					handler : function() {
						me.addAssessmentActivity(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '修改评估咨讯',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editAssessmentActivity(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/start.png',
					text : '启用',
					handler : function() {
						var records=getRecords(grid);
						if(records==-1)
							return;
						me.assessmentActivityState(records,store,mainId,"start");
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/pause.png',
					text : '终止',
					handler : function() {
						var records=getRecords(grid);
						if(records==-1)
							return;
						me.assessmentActivityState(records,store,mainId,"pause");
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除评估',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteAssessmentActivity(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "评估任务名称",  width:50,sortable: true,  dataIndex: 'assessmentActivityName',align:'center'},
	            {header: "咨讯类别",  width:50,sortable: true,  dataIndex: 'kgAssessmentType',align:'center',renderer:me.showType},
	            {header: "状态",  width:50,sortable: true,  dataIndex: 'finished',align:'center',renderer:me.isFinished},
	            {header: "权重",  width:50,sortable: true,  dataIndex: 'sequence',align:'center'},
	            {header: "预览",  width:50,sortable: true,  dataIndex: 'id',align:'center',renderer:me.buttonRender}
	        ],
	        width:'100%',
	        autoExpandColumn: 'assessmentActivityName',
	        viewConfig:{forceFit: true}
	    });
		
	  
		
		
		Ext.apply(this, {
			width:'100%',
			items : [formpanel,grid],
			frame:false,
			resizable : false,
			layout:'border',
			listeners:{
				beforeshow:function(){
				}
			}
		 });
	},

	showType:function(value){
		return value.assessmentTypeName;
	},
	isFinished:function(value){
		if(value==0){
			return "<font color='red'>已结束</font>";
		}else{
			return "<font color='green'>进行中</font>";
		}
	},
	buttonRender:function(id){
		   return "<button  width=\"50px\" onclick=\"yulanAssessments('"+id+"')\">预览</button>";
	},
	assessmentActivityState:function(records,store,mainId,state){
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
		    			url : appName + '/admin/assessment/activity/submit',
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
	addAssessmentActivity:function(store,mainId){
		var tabName ="addAssessmentActivityPanel" ;
		var win = new addorUpdateAssessmentActivity.addorUpdateAssessmentActivityWindow ({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store,
			tabName :tabName
		});
//		win.show();
		addTabFuns(tabName,win,'添加评估任务');
	},
	editAssessmentActivity:function(record,store,mainId){
		var id = record.get("id");
		var tabName ="updateAssessmentActivityPanel_"+id ;
		var win = new addorUpdateAssessmentActivity.addorUpdateAssessmentActivityWindow ({
			mainId:mainId+tabName,
			type:'update',
			record:record,
			parentStore:store,
			tabName :tabName
		});
//		win.show();
		addTabFuns(tabName,win,'修改评估任务');
	},
	deleteAssessmentActivity:function(records,store,mainId){
		Ext.Msg.confirm('提示信息','删除活动会将参与活动用户的上传内容一并删除，确认要删除这些信息吗？',function(op){  
	        if(op == 'yes'){
	        	      Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
					  var linkobj = [];
					  for(var i=0;i<records.length;i++){
						  var record = records[i];
						  var id = record.get("id");
						  linkobj.push({"id":id});
					  }
					  Ext.Ajax.request({
						url : appName + '/admin/assessment/activity/remove',
			            method : 'post',
			            headers: {'Content-Type':'application/json'},
			            params : JSON.stringify(linkobj),
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
	        }
		})
	}
});

function yulanAssessments(id){
	var url =appName+"/index/assessmentDetail?id="+id;
	window.open(url);
}

