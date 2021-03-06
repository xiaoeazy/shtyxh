Ext.namespace('Offers');
Offers.OffersPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	Offers.OffersPanel.superclass.constructor.call(this);
};


Ext.extend(Offers.OffersPanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;
   //===================================================================
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
		          		fieldLabel:'发布单位',
						name: 'publishunit',
						id:mainId+"publishunit",
			            maxLength:45 ,
			            width:400
		  			}
		  			],
		   buttons:[{
			    width:50,
				text : '查询',
				handler : function(button, event) {
					var publishunit = Ext.getCmp(mainId+"publishunit").getValue().trim();
					if(publishunit=="")
						publishunit=null;
					store.proxy.url = appName+ '/admin/offers/query';
					store.proxy.extraParams={
							page:1,
							start:0,
							publishunit:publishunit
					};
					store.load();  
				}
		    }]
	});

	
	
	//==========================grid====================
		var store = new Ext.data.Store({
			pageSize:10,
//			remoteSort: true,
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/offers/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'publishunit']
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
					text : '添加招聘',
					handler : function() {
						me.addOffers(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '修改招聘',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editOffers(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除招聘',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteOffers(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "发布单位",  width:50,sortable: true,  dataIndex: 'publishunit',align:'center'},
	            {header: "标题",  width:50,sortable: true,  dataIndex: 'title',align:'center'},
	            {header: "联系人",  width:50,sortable: true,  dataIndex: 'contactperson',align:'center'},
	            {header: "联系电话",  sortable: true,  dataIndex: 'contacttel',align:'center'},
	            {header: "薪资标准",  width:50,sortable: true,  dataIndex: 'salary',align:'center'},
	            {header: "发布日期",  width:50,sortable: true,  dataIndex: 'publishdate',align:'center'},
	        ],
	        width:'100%',
	        autoExpandColumn: 'summary',
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

	buttonRender:function(id){
		   return "<button  width=\"50px\" onclick=\"yulanOffers('"+id+"')\">预览</button>";
		   
	},
	addOffers:function(store,mainId){
//		var win = new addorUpdateNews.addorUpdateNewsWindow ({
//			mainId:mainId,
//			type:'add',
//			record:null,
//			parentStore:store
//		});
//		win.show();
		var tabName ="addOffersPanel" ;
		var panel = new addorUpdateOffers.addorUpdateOffersWindow ({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store,
			tabName :tabName
		});
		addTabFuns(tabName,panel,'添加招聘');
	},
	editOffers:function(record,store,mainId){
//		var id = record.get("id");
//		var win = new addorUpdateNews.addorUpdateNewsWindow ({
//			mainId:mainId,
//			type:'update',
//			record:record,
//			parentStore:store
//		});
//		win.show();
		var id = record.get("id");
		var tabName ="updateOffersPanel_"+id ;
		var panel = new addorUpdateOffers.addorUpdateOffersWindow ({
			mainId:mainId+tabName,
			type:'update',
			record:record,
			parentStore:store,
			tabName :tabName
		});
		addTabFuns(tabName,panel,'修改招聘');
//		win.show();
	},
	deleteOffers:function(records,store,mainId){
		 Ext.Msg.confirm('提示信息','确认要删除这些信息吗？',function(op){  
		        if(op == 'yes'){
						  Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
						  var linkobj = [];
						  for(var i=0;i<records.length;i++){
							  var record = records[i];
							  var id = record.get("id");
							  linkobj.push({"id":id});
						  }
						  Ext.Ajax.request({
							url : appName + '/admin/offers/remove',
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

function yulanOffers(id){
	var url =appName+"/index/offers/newsDetail?id="+id;
	window.open(url);
}

