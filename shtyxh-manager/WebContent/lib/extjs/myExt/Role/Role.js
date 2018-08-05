Ext.namespace('Role');
Role.RolePanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	Role.RolePanel.superclass.constructor.call(this);
};


Ext.extend(Role.RolePanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;

	

		var store = new Ext.data.Store({
			pageSize:10,
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/role/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['roleId', 'roleCode','roleName','roleDescription']
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
					text : '添加角色',
					handler : function() {
						me.addRole(store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/edit.png',
					text : '修改角色',
					handler : function() {
						var records=getUpdateRecords(grid);
						if(records==-1)
							return;
						var record = records[0];
						me.editRole(record,store,mainId);
					}
				},'-',{
					icon : _basePath+'/resources/images/icon/cancel.png',
					text : '删除角色',
					handler : function() {
						var records=getDeleteRecords(grid);
						if(records==-1)
							return;
						me.deleteRole(records,store,mainId);
					}
				}],
	        columns: [
	            {header: "角色编码",  sortable: true,  dataIndex: 'roleCode',align:'center'},
	            {header: "角色名称",  sortable: true,  dataIndex: 'roleName',align:'center'},
	            {header: "角色描述",  sortable: true,  dataIndex: 'roleDescription',align:'center'}
	            
	        ],
	        width:'100%',
	        autoExpandColumn: 'roleDescription',
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

	addRole:function(store,mainId){
		var win = new addorUpdateRole.addorUpdateRoleWindow ({
			mainId:mainId,
			type:'add',
			record:null,
			parentStore:store
		});
		win.show();
	},
	editRole:function(record,store,mainId){
		var id = record.get("id");
		var win = new addorUpdateRole.addorUpdateRoleWindow ({
			mainId:mainId,
			type:'update',
			record:record,
			parentStore:store
		});
		win.show();
	},
	deleteRole:function(records,store,mainId){
		  Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
		  var linkobj = [];
		  var canSubmit = true;
		  for(var i=0;i<records.length;i++){
			  var record = records[i];
			  var roleId = record.get("roleId");
			  if(roleId==10003||roleId==10004||roleId==10006){
				  ExtAlert(record.get("roleName")+" 为基础角色，不允许删除！");
				  canSubmit=false;
				  break;
			  }
			  linkobj.push({"roleId":roleId});
		  }
		  
		  if(!canSubmit){
			  Ext.getBody().unmask();
			  return;
		  }
		
		  Ext.Msg.confirm('提示信息','确认要删除这些信息吗？',function(op){  
		        if(op == 'yes'){
		        	  Ext.Ajax.request({
		      			url : appName + '/admin/role/remove',
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

