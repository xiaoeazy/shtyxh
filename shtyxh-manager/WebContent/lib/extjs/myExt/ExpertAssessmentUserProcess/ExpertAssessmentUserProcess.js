Ext.namespace('ExpertAssessmentUserProcess');
ExpertAssessmentUserProcess.ExpertAssessmentUserProcessPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	ExpertAssessmentUserProcess.ExpertAssessmentUserProcessPanel.superclass.constructor.call(this);
};


Ext.extend(ExpertAssessmentUserProcess.ExpertAssessmentUserProcessPanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;
	//===========================formpanel=========================
	
	var assessmentActivity_Combo_Store = new Ext.data.Store({
		pageSize:0,
		proxy: {
	        type: 'ajax',
	        url : appName+ '/admin/assessment/activity/queryAll',
	        reader: {
	        	root : "results",
				totalProperty: "totalProperty",
				successProperty:'success'
	        }
	    },
	    autoLoad : true,
	    fields: ['id', 'assessmentActivityName'],
	    listeners:{
        	'load': function(store, records, options) {
        		assessmentActivity_Combo_Store.insert(0,{id:'-1',assessmentActivityName:'所有'});
	    	}
        }
	});
	
   var assessmentActivityCombo = new Ext.form.ComboBox({
	   style:'padding:5px',
	   		columnWidth: .33  ,
	   		fieldLabel:'评估任务',
    	    id:mainId+"assessmentActivityId",
            store : assessmentActivity_Combo_Store,  
            valueField : "id",  
            mode : 'remote',  
            displayField : "assessmentActivityName",  
            forceSelection : true,  
            emptyText : '请选择',  
            editable : false,  
            triggerAction : 'all',  
            hiddenName : "assessmentActivityName",  
            autoShow : true,  
            selectOnFocus : true,  
            name : "assessmentActivityId",
            listeners:{
            	afterrender:function(comb){
            	},
            	select:function(combo, record, index){
            	}
            }
        }); 
   
   //======user=====
	var user_Combo_Store = new Ext.data.Store({
		pageSize:0,
		proxy: {
	        type: 'ajax',
	        url : appName+ '/admin/user/queryAll',
	        reader: {
	        	root : "results",
				totalProperty: "totalProperty",
				successProperty:'success'
	        }
	    },
	    autoLoad : true,
	    fields: ['userId','realName'],
	    listeners:{
        	'load': function(store, records, options) {
        		user_Combo_Store.insert(0,{userId:'-1',realName:'所有'});
	    	}
        }
	});
	
   var uploadUserCombo = new Ext.form.ComboBox({
	   style:'padding:5px',
	   		columnWidth: .33  ,
	   		fieldLabel:'上传用户',
    	    id:mainId+"uploadUserId",
            store : user_Combo_Store,  
            valueField : "userId",  
            mode : 'remote',  
            displayField : "realName",  
            forceSelection : true,  
            emptyText : '请选择',  
            editable : false,  
            triggerAction : 'all',  
            hiddenName : "realName",  
            autoShow : true,  
            selectOnFocus : true,  
            name : "uploadUserId",
            listeners:{
            	afterrender:function(comb){
            	},
            	select:function(combo, record, index){
            	}
            }
        }); 
   //=================审核状态==============================
   
   var state_Combo_Store = new Ext.data.Store({
	   fields: [  
	         {name: 'key', type: 'string'},  
	         {name: 'value',  type: 'string'}
	     ],  
	     data : [  
	         {key: '-1',   value: '所有'},  
	         {key: '50',    value: '通过'},
	         {key: '60',    value: '未通过'}
	     ]  
	});
   
   var stateCombo = new Ext.form.ComboBox({
	    style:'padding:5px',
	    columnWidth: .33  ,   
  		fieldLabel:'审核状态',
	    id:mainId+"state",
        store : state_Combo_Store,  
        valueField : "key",  
        mode : 'remote',  
        displayField : "value",  
        forceSelection : true,  
        emptyText : '请选择',  
        editable : false,  
        triggerAction : 'all',  
        hiddenName : "value",  
        autoShow : true,  
        selectOnFocus : true,  
        name : "state",
        listeners:{
       	afterrender:function(comb){
       	},
       	select:function(combo, record, index){
       	}
       }
   }); 
	var formpanel = new Ext.FormPanel({
		  region:'north',
		  labelAlign: "right",
		  labelWidth :100,
	      frame: true,
	      width: '100%',
	      style:'margin: 0px 0px 5px 0px',
	      defaults:{
             width:'100%'
          },
          layout:'column',
		  items : [
			  assessmentActivityCombo,
			  uploadUserCombo,
			  stateCombo
		  			],
		   buttons:[{
			    width:50,
				text : '查询',
				handler : function(button, event) {
					var uploadUserId = Ext.getCmp(mainId+"uploadUserId").getValue();
					var assessmentActivityId 	  = Ext.getCmp(mainId+"assessmentActivityId").getValue();
					var state 	  = Ext.getCmp(mainId+"state").getValue();
					if(assessmentActivityId==-1){
						assessmentActivityId=null;
					}
					if(uploadUserId==-1){
						uploadUserId=null;
					}
					if(state==-1){
						state=null;
					}
					store.proxy.url = appName+ '/admin/assessment/activity/user/progress/query';
					store.proxy.extraParams={
							page:1,
							start:0,
							assessmentActivityId:assessmentActivityId,
							uploadUserId:uploadUserId,
							expertUserId:theLoginUserId,
							state:state
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
		        url : appName+ '/admin/assessment/activity/user/progress/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        },
		        extraParams: {
		        	expertUserId :  theLoginUserId 
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'assessmentActivityId', 'uploadUserId', 'adminSuggestion', 'expertUserId', 'expertSuggestion', 'state']
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
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '建议',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editExpertAssessmentUserProcess(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/showUpload.png',
					text : '查看上传文件',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.showUserUpload(record,store,mainId);
					}
				}],
	        columns: [
	            {header: "评估任务名称",  width:50,sortable: true,  dataIndex: 'kgAssessmentActivity',align:'center',renderer:me.assessmentNameRender},
	            {header: "上传用户",  width:50,sortable: true,  dataIndex: 'uploadUserId',align:'center'},
	            {header: "审核状态",  width:50,sortable: true,  dataIndex: 'state',align:'center',renderer:me.stateRender},
	            {header: "预览",  width:50,sortable: true,  dataIndex: 'assessmentActivityId',align:'center',renderer:me.buttonRender}
	        ],
	        width:'100%',
	        autoExpandColumn: 'AssessmentUserProcessName',
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
	assessmentNameRender:function(value){
		return value.assessmentActivityName;
	},
	buttonRender:function(id){
		   return "<button  width=\"50px\" onclick=\"yulanAssessments('"+id+"')\">预览</button>";
	},
	stateRender:function(value){
		if(value==10){
			return "已上传";
		}else if(value==20){
			return "管理员通过";
		}else if(value==30){
			return "管理员未通过";
		}else if(value==40){
			return "已转移专家";
		}else if(value==50){
			return "通过";
		}else if(value==60){
			return "未通过";
		}
	},
	editExpertAssessmentUserProcess:function(record,store,mainId){
		var state = record.get("state");
		if(state>=40){
			var win = new addorUpdateExpertAssessmentUserProcess.addorUpdateExpertAssessmentUserProcessWindow ({
				mainId:mainId,
				type:'update',
				record:record,
				parentStore:store
			});
		}else{
			ExtError("还未经管理员审核!");
		}
		win.show();
	},
	showUserUpload:function(record,store,mainId){
		var win = new showUserUpload.showUserUploadWindow ({
			mainId:mainId,
			record:record,
			parentStore:store
		});
		win.show();
	}
	

});

