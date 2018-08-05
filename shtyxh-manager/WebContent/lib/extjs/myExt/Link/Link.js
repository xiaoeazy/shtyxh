Ext.namespace('Link');
Link.LinkPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	Link.LinkPanel.superclass.constructor.call(this);
};


Ext.extend(Link.LinkPanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;
	var isFirstLoad=true;
	var reader = new Ext.data.JsonReader(
				{
					root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
				}, 
				[{
					name : 'id',
					type : 'string',
					mapping : 'id'
				}, {
					name : 'linkName',
					type : 'string',
					mapping : 'linkName'
				}, {
					name : 'linkUrl',
					type : 'string',
					mapping : 'linkUrl'
				}]
		);
	
//		var store = new Ext.data.Store({
//					proxy: new Ext.data.HttpProxy({
//						url : appName+ '/admin/link/query'
//					}),
//					remoteSort: true, 
//					reader : reader,
//					autoLoad : true,
//					listeners:{
//						 exception:function(dataProxy, type, action, options, response, arg) {   
//					            var o = Ext.util.JSON.decode(response.responseText);  
//					            if(!o.success){ 
//					            	Ext.Msg.show({ 
//	        							title:'失败', 
//	        							msg:o.data.msg,
//	        							width:260,
//	        							buttons:Ext.Msg.OK,
//	        							icon:Ext.Msg.ERROR
//	        						});
//					            }  
//					        }  
//					}
//				});
	
		var store = new Ext.data.Store({
			pageSize:10,
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/link/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'linkName','linkUrl']
		});
		
//		var store =Ext.create(  
//            'Ext.data.Store',  
//            {   
//               fields:['id', 'linkName', 'linkUrl'],  
//               data:{'items':[  
//                  { 'id': 'Lisa',  "linkName":"lisa@simpsons.com",  "linkUrl":"555-111-1224"  },  
//                  { 'id': 'Bart',  "linkName":"bart@simpsons.com",  "linkUrl":"555-222-1234" },  
//                  { 'id': 'Homer', "linkName":"homer@simpsons.com",  "linkUrl":"555-222-1244"  },  
//                  { 'id': 'Marge', "linkName":"marge@simpsons.com", "linkUrl":"555-222-1254"  }  
//               ]},  
//               proxy: {  
//                  type: 'memory',  
//                  reader: {  
//                      type: 'json',  
//                      rootProperty: 'items'  
//                  }  
//              }  
//        })

//		var store =Ext.create(  
//                'Ext.data.Store',  
//                {   
//                   fields:['id', 'linkName', 'linkUrl'],  
//                   data:{'items':[  
//                      { 'id': 'Lisa',  "linkName":"lisa@simpsons.com",  "linkUrl":"555-111-1224"  },  
//                      { 'id': 'Bart',  "linkName":"bart@simpsons.com",  "linkUrl":"555-222-1234" },  
//                      { 'id': 'Homer', "linkName":"homer@simpsons.com",  "linkUrl":"555-222-1244"  },  
//                      { 'id': 'Marge', "linkName":"marge@simpsons.com", "linkUrl":"555-222-1254"  }  
//                   ]},  
//                   proxy: {  
//                      type: 'memory',  
//                      reader: {  
//                          type: 'json',  
//                          rootProperty: 'items'  
//                      }  
//                  }  
//            })
		
	
    
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
//	            rowSelect: true  // replaces click-to-sort on header

	        },
	        bbar: new Ext.PagingToolbar({
			       
			        store: store,
			        displayInfo: true,
			        displayMsg: '当前 {0} 到 {1} 共 {2}',
			        emptyMsg: "没有数据返回"
	        }),
	        tbar:[{
					icon : _basePath+'/resources/images/icon/add.png',
					text : '添加链接',
					handler : function() {
						me.addLink(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '修改链接',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editLink(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除链接',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteLink(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "关联名称",  sortable: true,  dataIndex: 'linkName',align:'center'},
	            {header: "关联地址",  sortable: true,  dataIndex: 'linkUrl',align:'center'}
	            
	        ],
	        width:'100%',
	        autoExpandColumn: 'linkUrl',
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
//					if(isFirstLoad){
//						  store.load({
//								params : {
//									start : 0,
//									limit : 10
//								}
//							});
//						  isFirstLoad=false;
//					}else{
//						 store.reload();
//					}
				}
			}
		 });
	},

	addLink:function(store,mainId){
		var win = new addorUpdateLink.addorUpdateLinkWindow({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store
		});
		win.show();
	},
	editLink:function(record,store,mainId){
		var id = record.get("id");
		var win = new addorUpdateLink.addorUpdateLinkWindow({
			mainId:mainId,
			type:'update',
			record:record,
			parentStore:store
		});
		win.show();
	},
	deleteLink:function(records,store,mainId){
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
			  			url : appName + '/admin/link/remove',
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

