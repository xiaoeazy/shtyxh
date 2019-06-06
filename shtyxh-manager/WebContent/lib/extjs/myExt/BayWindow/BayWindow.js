Ext.namespace('BayWindow');
BayWindow.BayWindowPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	BayWindow.BayWindowPanel.superclass.constructor.call(this);
};


Ext.extend(BayWindow.BayWindowPanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;


		var store = new Ext.data.Store({
			pageSize:10,
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/baywindow/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['id','filePath','sequence','urltype','webUrl','activityId','newsId']
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
					text : '添加飘窗',
					handler : function() {
						me.addBayWindow(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '修改飘窗',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editBayWindow(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除飘窗',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteBayWindow(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "飘窗图",  sortable: true,  dataIndex: 'filePath',align:'center',renderer:showBayWindow},
	            {header: "是否显示",  sortable: true,  dataIndex: 'indexshow',align:'center',renderer:me.showindexRender}
	            
	        ],
	        width:'100%',
	        autoExpandColumn: 'indexshow',
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
	showindexRender:function(value){
		if(value=='N')
			return "否";
		else if(value=='Y')
			return "是"
		else
			return value;
	},

	addBayWindow:function(store,mainId){
		var win = new addorUpdateBayWindow.addorUpdateBayWindowWindow ({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store
		});
		win.show();
	},
	editBayWindow:function(record,store,mainId){
		var id = record.get("id");
		var win = new addorUpdateBayWindow.addorUpdateBayWindowWindow ({
			mainId:mainId,
			type:'update',
			record:record,
			parentStore:store
		});
		win.show();
	},
	deleteBayWindow:function(records,store,mainId){
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
		    			url : appName + '/admin/baywindow/remove',
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

function showBayWindow(path){
        return '<img  width="640" height="256" src='+fileAppName+path+'/>';
}

