Ext.namespace('AssessmentType');
AssessmentType.AssessmentTypePanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	AssessmentType.AssessmentTypePanel.superclass.constructor.call(this);
};


Ext.extend(AssessmentType.AssessmentTypePanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;


		var store = new Ext.data.Store({
			pageSize:10,
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/assessment/type/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'assessmentTypeName']
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
					text : '添加任务类别',
					handler : function() {
						me.addAssessmentType(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '修改任务类别',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editAssessmentType(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除任务类别',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteAssessmentType(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "任务类型名称",  sortable: true,  dataIndex: 'assessmentTypeName',align:'center'}
	            
	        ],
	        width:'100%',
	        autoExpandColumn: 'assessmentTypeName',
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

	addAssessmentType:function(store,mainId){
		var win = new addorUpdateAssessmentType.addorUpdateAssessmentTypeWindow ({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store
		});
		win.show();
	},
	editAssessmentType:function(record,store,mainId){
		var id = record.get("id");
		var win = new addorUpdateAssessmentType.addorUpdateAssessmentTypeWindow ({
			mainId:mainId,
			type:'update',
			record:record,
			parentStore:store
		});
		win.show();
	},
	deleteAssessmentType:function(records,store,mainId){
		  Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
		  var linkobj = [];
		  for(var i=0;i<records.length;i++){
			  var record = records[i];
			  var id = record.get("id");
			  linkobj.push({"id":id});
		  }
		  
		  
		  
		  Ext.Msg.confirm('提示信息','确认要删除这些信息吗？',function(op){  
		        if(op == 'yes'){
		        	Ext.Ajax.request({
		    			url : appName + '/admin/assessment/type/remove',
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
		        }else{
		        	Ext.getBody().unmask();
		        }
		  })  
		  
	}
	
	
});

