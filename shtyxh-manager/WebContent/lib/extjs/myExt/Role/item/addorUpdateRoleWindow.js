Ext.namespace('addorUpdateRole');
addorUpdateRole.addorUpdateRoleWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateRole.addorUpdateRoleWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateRole.addorUpdateRoleWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
	    	var mainId=me.mainId+"_Rolewindow";
	    	var type = me.type;
	    	var record = me.record;
	    	var id ="";
	    	if(record!=null){
	    		id= record.get("roleId");
	    	}
	    	var parentStore = this.parentStore; 
	    	var text = type=="update"?"更新":"添加";
	    	var isAdd =  type=="update"?false:true;
	    	
	    	var formpanel = new Ext.FormPanel({
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
				  items : [ {
					  			xtype:'label',
					  			text:''
				  			},
				  			{
				          		fieldLabel:'角色编码<font color="red">*</font>',
								allowBlank:false,
								name: 'roleCode',
								blankText:'必须填写',
								id:mainId+"roleCode",
					            maxLength:45  
				  			},	{
				          		fieldLabel:'角色名称<font color="red">*</font>',
								allowBlank:false,
								name: 'roleName',
								blankText:'必须填写',
								id:mainId+"roleName",
					            maxLength:45  
				  			},	{
				          		fieldLabel:'角色描述<font color="red">*</font>',
								allowBlank:false,
								name: 'roleDescription',
								blankText:'必须填写',
								id:mainId+"roleDescription",
					            maxLength:45  
				  			}
				  			]
		});
	     
	    //=====================role=========================================
	        var dsFrom = Ext.create('Ext.data.ArrayStore', {
	        	pageSize:0,
	            proxy: {
	                type: 'ajax',
	                url : appName+ '/admin/sys/func/queryNotHave',
	                reader: {},
			        extraParams: {
			        	roleId :  id 
			        }
	            },
	            autoLoad: true,
	            fields: ['id', 'funcName']
	        });
	        
	        var dsTo = Ext.create('Ext.data.ArrayStore', {
	        	pageSize:0,
	            proxy: {
	                type: 'ajax',
	                url : appName+ '/admin/sys/func/queryHave',
	                reader: {
			        },
			        extraParams: {
			        	roleId :  id 
			        }
	            },
	            autoLoad: true,
	            fields: ['id', 'funcName']
	        });
	    	var funcForm = new Ext.form.FormPanel({
	            bodyStyle: 'padding:10px;',
	            items:[{
	            	 xtype: 'itemselector',
	                 name: 'itemselector',
	                 height : 320,
	                 id: mainId+"itemselector",
	                 anchor: '100%',
	                 imagePath: '../ux/images/',
	                 store: dsFrom,
	                 tostore: dsTo,
	                 displayField: 'funcName',
	                 valueField: 'id',
	                 allowBlank: false,
	                 msgTarget: 'side',
	                 fromTitle: '未选功能点',
	                 toTitle: '已选功能点'
	            }]
	        });

	    	
	    var roleTabs = new Ext.TabPanel({
	    		deferredRender:false,
				enableTabScroll : true,
				border : false,
				activeTab : 0,
				items : [{
					itemId : mainId+"_1",
					layout : 'fit',
					title : "角色信息",
					width : '100%',
					items : [formpanel ],
					closable : false
				},{
					itemId : mainId+"_2",
					layout : 'fit',
					title : "功能点",
					width : '100%',
					items : [funcForm],
					closable : false,
					listeners:{
					}
				}]
			});
	        

		
		Ext.apply(this, {
				title : text+'角色',
				layout:'fit',
				items : [roleTabs],
				width : 600,
				height : 470,
				autoHeight:true,
				xtype : "window",
				resizable : false,
				constrain:true,
				minimize : function() { 
		            this.hide(); 
		        },
				modal : true,
				buttonAlign : "center",
			    buttons:[{
				  width:50,
					text : text,
					handler : function(button, event) {
						me.addorUpdateRole(me,formpanel,mainId,parentStore,id,type,isAdd);
					}
			    },{
				  width:50,
					text : '取消',
					handler : function(button, event) {
						me.close();
					}
			    }],
				listeners:{
					show:function(){
						if(record!=null){
				    		var roleCode= record.get("roleCode");
				    		Ext.getCmp(mainId+"roleCode").setValue(roleCode);
				    		Ext.getCmp(mainId+"roleCode").setDisabled(true);
				    		var roleName= record.get("roleName");
				    		Ext.getCmp(mainId+"roleName").setValue(roleName);
				    		
				    		var roleDescription= record.get("roleDescription");
				    		Ext.getCmp(mainId+"roleDescription").setValue(roleDescription);
				    	}
					}
				}
			}
		);
		
	},
	
	
	addorUpdateRole : function(me,formpanel,mainId,parentStore,id,type,isAdd) {
		var roleFuncList = [];
		
//		var itemselector = Ext.getCmp(mainId+"itemselector");
//		var allFunc = itemselector.getValue();
//		allFunc.forEach(function(ele,index){  
//			roleFuncList.push({
//            	 funcId:allFunc[index]
//             })
//         });  
		var list = Ext.getCmp(mainId+"itemselector").toField.boundList;
        var rightstore = list.getStore();
		for(var i =0;i<rightstore.getCount();i++){
			roleFuncList.push({
				funcId:rightstore.getAt(i).get("id")
            })
		}
		var roleCode =Ext.getCmp(mainId+"roleCode").getValue().trim();
		if(roleCode==""){
			Ext.getCmp(mainId+"roleCode").markInvalid("角色编码不能为空！");
			return;
		}
		var roleName =Ext.getCmp(mainId+"roleName").getValue().trim();
		if(roleName==""){
			Ext.getCmp(mainId+"roleName").markInvalid("角色名不能为空！");
			return;
		}
		var roleDescription =Ext.getCmp(mainId+"roleDescription").getValue().trim();
		
		
		var addOrUpdateRoleObj = {
			  __status : type,
           	  roleCode : roleCode,
           	  roleName : roleName,
           	  roleDescription: roleDescription,
           	  roleId : id
          };
		
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/role/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify({
                	  role:addOrUpdateRoleObj,
                	  roleFuncList:roleFuncList
                  }),
                  success : function(response, options) {
                	  Ext.getBody().unmask();
                	  var responseArray = Ext.util.JSON.decode(response.responseText);
	                  if (responseArray.success == true) {
	                	    parentStore.reload();
	                    	me.close();
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
		
	}
	
});





