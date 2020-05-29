Ext.namespace('Notice');
Notice.CarouselPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	Notice.CarouselPanel.superclass.constructor.call(this);
};


Ext.extend(Notice.CarouselPanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;


		var store = new Ext.data.Store({
			pageSize:10,
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/notice/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['id','name','sequence','urltype','webUrl','activityId','newsId']
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
					text : '添加消息通知',
					handler : function() {
						me.addNotice(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '修改消息通知',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editNotice(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除消息通知',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteNotice(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "名称",  sortable: true,  dataIndex: 'name'},
	            {header: "权重",  sortable: true,  dataIndex: 'sequence',align:'center'}
	            
	        ],
	        width:'100%',
	        autoExpandColumn: 'name',
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

	addNotice:function(store,mainId){
		var win = new addorUpdateNotice.addorUpdateNoticeWindow ({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store
		});
		win.show();
	},
	editNotice:function(record,store,mainId){
		var id = record.get("id");
		var win = new addorUpdateNotice.addorUpdateNoticeWindow ({
			mainId:mainId,
			type:'update',
			record:record,
			parentStore:store
		});
		win.show();
	},
	deleteNotice:function(records,store,mainId){
		  Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
		  var linkobj = [];
		  for(var i=0;i<records.length;i++){
			  var record = records[i];
			  var id = record.get("id");
			  var filePath = record.get("filePath");
			  linkobj.push({"id":id,"filePath":filePath});
		  }
		  
		  
		  
		  Ext.Msg.confirm('提示信息','确认要删除这些信息吗？',function(op){  
		        if(op == 'yes'){
		        	Ext.Ajax.request({
		    			url : appName + '/admin/notice/remove',
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

