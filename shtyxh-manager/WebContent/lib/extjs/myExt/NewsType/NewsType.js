Ext.namespace('NewsType');
NewsType.NewsTypePanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	NewsType.NewsTypePanel.superclass.constructor.call(this);
};


Ext.extend(NewsType.NewsTypePanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;


		var store = new Ext.data.Store({
			pageSize:10,
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/type/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        },
		        extraParams: {
		        	relatetype :  2 
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'typename','hidden']
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
					text : '添加类型',
					handler : function() {
						me.addNewsType(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '修改类型',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editNewsType(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除类型',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteNewsType(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "类型名称",  sortable: true,  dataIndex: 'typename',align:'center'},
	            {header: "是否导航栏显示",  sortable: true,  dataIndex: 'hidden',align:'center',renderer:me.showIndex}
	            
	        ],
	        width:'100%',
	        autoExpandColumn: 'typename',
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
	showIndex:function(val){
		if(val==true){
			return "隐藏";
		}else{
			return "显示";
		}
		
	},
	addNewsType:function(store,mainId){
		var win = new addorUpdateNewsType.addorUpdateNewsTypeWindow ({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store
		});
		win.show();
	},
	editNewsType:function(record,store,mainId){
		var id = record.get("id");
		var win = new addorUpdateNewsType.addorUpdateNewsTypeWindow ({
			mainId:mainId,
			type:'update',
			record:record,
			parentStore:store
		});
		win.show();
	},
	deleteNewsType:function(records,store,mainId){
		  Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
		  var linkobj = [];
		  var canSubmit = true;
		  for(var i=0;i<records.length;i++){
			  var record = records[i];
			  var id = record.get("id");
			  if(id==1||id==2||id==3||id==4){
				  ExtAlert(record.get("typename")+" 为基础类型，不允许删除！");
				  canSubmit=false;
				  break;
			  }
			  
			  linkobj.push({"id":id});
		  }
		  
		  if(!canSubmit){
			  Ext.getBody().unmask();
			  return;
		  }
		  
		  
		  Ext.Msg.confirm('提示信息','确认要删除这些信息吗？',function(op){  
		        if(op == 'yes'){
		        	Ext.Ajax.request({
		    			url : appName + '/admin/type/remove',
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

