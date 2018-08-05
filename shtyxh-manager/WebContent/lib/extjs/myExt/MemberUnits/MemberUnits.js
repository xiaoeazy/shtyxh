Ext.namespace('MemberUnits');
MemberUnits.MemberUnitsPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	MemberUnits.MemberUnitsPanel.superclass.constructor.call(this);
};


Ext.extend(MemberUnits.MemberUnitsPanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;
	//===========================formpanel=========================
 	//行政区
	var canton_Combo_Store = new Ext.data.Store({
		pageSize:0,
		proxy: {
	        type: 'ajax',
	        url : appName+ '/admin/canton/queryAll',
	        reader: {
	        	root : "results",
				totalProperty: "totalProperty",
				successProperty:'success'
	        }
	    },
	    autoLoad : true,
	    fields: ['id', 'cantonname']
	});
	
   var cantonCombo = new Ext.form.ComboBox({
	   		fieldLabel:'行政区',
    	    id:mainId+"cantonid",
            store : canton_Combo_Store,  
            valueField : "id",  
            mode : 'remote',  
            displayField : "cantonname",  
            forceSelection : true,  
            blankText : '请选择',  
            emptyText : '请选择',  
            editable : false,  
            triggerAction : 'all',  
            allowBlank : false,  
            hiddenName : "cantonname",  
            autoShow : true,  
            selectOnFocus : true,  
            name : "cantonid",
            listeners:{
            	'load': function(store, records, options) {
            		canton_Combo_Store.insert(0,{id:'-1',cantonname:'所有'});
            	 }
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
		          		fieldLabel:'编号',
						name: 'memberno',
						id:mainId+"memberno",
			            maxLength:45 ,
			            width:400
		  			},
		  			cantonCombo,
		  			{
		          		fieldLabel:'园所名称',
						name: 'kindergartenname',
						id:mainId+"kindergartenname",
			            maxLength:45 ,
			            width:400
		  			},{
		          		fieldLabel:'园所地址',
						name: 'kindergartensite',
						id:mainId+"kindergartensite",
			            maxLength:45 ,
			            width:400
		  			}
		  			],
		   buttons:[{
			    width:50,
				text : '查询',
				handler : function(button, event) {
					var memberno = Ext.getCmp(mainId+"memberno").getValue().trim();
					var cantonid 	  = Ext.getCmp(mainId+"cantonid").getValue();
					var kindergartenname = Ext.getCmp(mainId+"kindergartenname").getValue().trim();
					var kindergartensite = Ext.getCmp(mainId+"kindergartensite").getValue().trim();
					
					if(cantonid==-1){
						cantonid=null;
					}
					store.proxy.url = appName+ '/admin/member/query';
					store.proxy.extraParams={
							page:1,
							start:0,
							memberno:memberno,
							cantonid:cantonid,
							kindergartenname:kindergartenname,
							kindergartensite:kindergartensite
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
		        url : appName+ '/admin/member/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'memberno']
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
					text : '添加会员',
					handler : function() {
						me.addMemberUnits(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '修改会员',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editMemberUnits(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除会员',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteMemberUnits(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "编号",  width:50,sortable: true,  dataIndex: 'memberno',align:'center'},
	            {header: "行政区",  width:50,sortable: true, dataIndex: 'kgcanton',align:'center',renderer:me.cantonNameRender},
	            {header: "园所名称",  sortable: true,  dataIndex: 'kindergartenname',align:'center'},
	            {header: "园所地址",  width:50,sortable: true,  dataIndex: 'kindergartensite',align:'center'},
	            {header: "邮编",  width:50,sortable: true,  dataIndex: 'zipcode',align:'center'},
	            {header: "联系电话",  width:50,sortable: true,  dataIndex: 'telphone',align:'center'},
	            {header: "等级",  width:50,sortable: true,  dataIndex: 'level',align:'center'},
	            {header: "性质",  width:50,sortable: true,  dataIndex: 'nature',align:'center'},
	            {header: "入会时间",  width:50,sortable: true,  dataIndex: 'admissiontime',align:'center'},
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
	cantonNameRender:function(value){
		return value.cantonname;
	},
	buttonRender:function(id){
		   return "<button  width=\"50px\" onclick=\"yulanMemberUnits('"+id+"')\">预览</button>";
		   
	},
	addMemberUnits:function(store,mainId){
		var win = new addorUpdateMemberUnits.addorUpdateMemberUnitsWindowWindow ({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store
		});
		win.show();
	},
	editMemberUnits:function(record,store,mainId){
		var id = record.get("id");
		var win = new addorUpdateMemberUnits.addorUpdateMemberUnitsWindowWindow ({
			mainId:mainId,
			type:'update',
			record:record,
			parentStore:store
		});
		win.show();
	},
	deleteMemberUnits:function(records,store,mainId){
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
							url : appName + '/admin/member/remove',
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

function yulanMemberUnits(id){
//	var url =appName+"/index/newsDetail?id="+id;
//	window.open(url);
}

