Ext.namespace('News');
News.NewsPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	News.NewsPanel.superclass.constructor.call(this);
};


Ext.extend(News.NewsPanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;
	//===========================formpanel=========================
	
	var typeid_Combo_Store = new Ext.data.Store({
		pageSize:0,
		proxy: {
	        type: 'ajax',
	        url : appName+ '/admin/newstype/queryAll',
	        reader: {
	        	root : "results",
				totalProperty: "totalProperty",
				successProperty:'success'
	        }
	    },
	    fields: ['id', 'typename'],
	    listeners:{
	    	'load': function(store, records, options) {
	    		 store.insert(0,{id:'-1',typename:'所有'});
	    	}
	    }
	});
	
   var typeCombo = new Ext.form.ComboBox({
	   		fieldLabel:'类型',
    	    id:mainId+"typeid",
            store : typeid_Combo_Store,  
            valueField : "id",  
            mode : 'remote',  
            displayField : "typename",  
            forceSelection : true,  
            emptyText : '请选择',  
            editable : false,  
            triggerAction : 'all',  
            hiddenName : "typename",  
            autoShow : true,  
            selectOnFocus : true,  
            name : "typeid",
            listeners:{
            	afterrender:function(comb){
            	},
            	select:function(combo, record, index){
            	}
            }
        }); 
   //==========属性================================================
   var attribute_Combo_Store = new Ext.data.Store({
	   pageSize:0,
		proxy: {
	        type: 'ajax',
	        url : appName+ '/admin/newsattribute/queryAll',
	        reader: {
	        	root : "results",
				totalProperty: "totalProperty",
				successProperty:'success'
	        }
	    },
	    autoLoad : true,
	    fields: ['id', 'attributename'],
	    listeners:{
	    	load:function(){
	    			me.LoadingAttribute1(me,formpanel,attribute_Combo_Store,mainId);
	    	}
	    }
	});
   //=====================是否前台显示=========================================
   var indexShowCombo = new Ext.form.ComboBox({
  		fieldLabel:'是否前台展示图片',
	    id:mainId+"indexshow",
	    model:'local',
        store:new Ext.data.SimpleStore({  //填充的数据
            fields : ['text', 'value'],
            data : [['所有', '-1'],['否', 'N'], ['是', 'Y']]
    	}),
        valueField : "value",  
        displayField : "text",  
        forceSelection : true,  
        blankText : '请选择',  
        emptyText : '请选择',  
        editable : false,  
        triggerAction : 'all',  
        allowBlank : false,  
        hiddenName : "text",  
        autoShow : true,  
        selectOnFocus : true,  
        name : "indexshow",
        listeners:{
        }
    });  
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
		          		fieldLabel:'标题名',
						name: 'newstitle',
						id:mainId+"newstitle",
			            maxLength:45 ,
			            width:400
		  			},
		  			typeCombo,
		  			{
                        xtype: 'checkboxgroup',
                        id: mainId+"attribute",
                        name: 'attribute',
                        columns: 4,
                        fieldLabel: '自定义属性',
                        labelWidth: 100,
                        width: '200',
                        align: 'left',
                        border: true,
                        anchor: '100%', flex: 1,
                        listeners: {
                            render: function (view, opt) {
                            	
                            }
                        }
                    },
                    indexShowCombo
		  			],
		   buttons:[{
			    width:50,
				text : '查询',
				handler : function(button, event) {
					var newstitle = Ext.getCmp(mainId+"newstitle").getValue().trim();
					var typeid 	  = Ext.getCmp(mainId+"typeid").getValue();
					
					var attributeValue = Ext.getCmp(mainId+'attribute').getChecked();
					var attributeid="";
					Ext.Array.each(attributeValue, function(item){
						attributeid +=  item.inputValue+",";
					});
					if(attributeid!="")
						attributeid=attributeid.substring(0,attributeid.length-1);
					if(typeid==-1){
						typeid=null;
					}
					if(attributeid==""){
						attributeid=null;
					}
					var indexshow = Ext.getCmp(mainId+"indexshow").getValue();
					if(indexshow==-1){
						indexshow=null;
					}
					store.proxy.url = appName+ '/admin/news/query';
					store.proxy.extraParams={
							page:1,
							start:0,
							attributeid:attributeid,
							newstitle:newstitle,
							typeid:typeid,
							indexshow:indexshow
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
		        url : appName+ '/admin/news/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'newstitle']
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
					text : '添加咨讯',
					handler : function() {
						me.addNews(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '修改咨讯',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editNews(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除咨讯',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteNews(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "咨讯名称",  width:50,sortable: true,  dataIndex: 'newstitle',align:'center'},
	            {header: "咨讯类别",  width:50,sortable: true,  dataIndex: 'kgNewstype',align:'center',renderer:me.typeNameRender},
	            {header: "咨讯简介",  sortable: true,  dataIndex: 'summary',align:'center'},
	            {header: "权重",  width:50,sortable: true,  dataIndex: 'sequence',align:'center'},
	            {header: "预览",  width:50,sortable: true,  dataIndex: 'id',align:'center',renderer:me.buttonRender},
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
	LoadingAttribute1:function(me,formpanel,store,mainId){
		  var checkboxgroup = Ext.getCmp(mainId+"attribute");
		  for (var i = 0; i < store.getCount(); i++) {
			  var record = store.getAt(i);
			  var id = record.get("id");
			  var checkbox = new Ext.form.Checkbox(
		                 {
		                     boxLabel: record.get("attributename"),
		                     inputValue:id,
		                     checked: false
		                 });
		          checkboxgroup.items.add(checkbox);
		  }
		  formpanel.doLayout();
	},
	typeNameRender:function(value){
		return value.typename;
	},
	buttonRender:function(id){
		   return "<button  width=\"50px\" onclick=\"yulanNews('"+id+"')\">预览</button>";
		   
	},
	addNews:function(store,mainId){
//		var win = new addorUpdateNews.addorUpdateNewsWindow ({
//			mainId:mainId,
//			type:'add',
//			record:null,
//			parentStore:store
//		});
//		win.show();
		var tabName ="addNewsPanel" ;
		var panel = new addorUpdateNews.addorUpdateNewsWindow ({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store,
			tabName :tabName
		});
		addTabFuns(tabName,panel,'添加资讯');
	},
	editNews:function(record,store,mainId){
//		var id = record.get("id");
//		var win = new addorUpdateNews.addorUpdateNewsWindow ({
//			mainId:mainId,
//			type:'update',
//			record:record,
//			parentStore:store
//		});
//		win.show();
		var id = record.get("id");
		var tabName ="updateNewsPanel_"+id ;
		var panel = new addorUpdateNews.addorUpdateNewsWindow ({
			mainId:mainId+tabName,
			type:'update',
			record:record,
			parentStore:store,
			tabName :tabName
		});
		addTabFuns(tabName,panel,'修改资讯');
//		win.show();
	},
	deleteNews:function(records,store,mainId){
		 Ext.Msg.confirm('提示信息','确认要删除这些信息吗？',function(op){  
		        if(op == 'yes'){
						  Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
						  var linkobj = [];
						  for(var i=0;i<records.length;i++){
							  var record = records[i];
							  var id = record.get("id");
							  var thumbnail = record.get("thumbnail");
							  linkobj.push({"id":id,"thumbnail":thumbnail});
						  }
						  Ext.Ajax.request({
							url : appName + '/admin/news/remove',
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

function yulanNews(id){
	var url =appName+"/index/newsDetail?id="+id;
	window.open(url);
}

