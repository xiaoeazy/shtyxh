Ext.namespace('addorUpdateUser');
addorUpdateUser.addorUpdateUserWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateUser.addorUpdateUserWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateUser.addorUpdateUserWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_Userwindow";
	    	var type = me.type;
	    	var record = me.record;
	    	var id ="";
	    	if(record!=null){
	    		id= record.get("userId");
	    	}
	    	var parentStore = this.parentStore; 
	    	var text = type=="update"?"更新":"添加";
	    	var isAdd =  type=="update"?false:true;
	    	
	    	
	       
	       //=====================formpanel=========================================
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
				          		fieldLabel:'用户名(登录名)<font color="red">*</font>',
								allowBlank:false,
								name: 'userName',
								blankText:'必须填写',
								id:mainId+"userName",
					            maxLength:40  
				  			},{
				          		fieldLabel:'真实姓名',
								allowBlank:true,
								name: 'realName',
								blankText:'姓名',
								id:mainId+"realName",
					            maxLength:40  
				  			},{
				          		fieldLabel:'密码',
//								allowBlank:false,
								name: 'password',
//								blankText:'必须填写',
								id:mainId+"password",
								maxLength:8,
								regex : /^[\s\S]{0,8}$/,
			                    regexText : '密码长度不能超过8个字符'
				  			}, {
		                        id : mainId+'confirmPassword',
		                        name : 'confirmPassword',
		                        fieldLabel : '<font color="black">确认密码</font>',
		                        confirmPwd : {
		                            first : mainId+'password',
		                            second : mainId+'confirmPassword'
		                        },
		                        vtype : 'confirmPwd',
//		                        allowBlank : false,
//		                        blankText : '确认密码不能为空',
		                        maxLength:8,
		                        regex : /^[\s\S]{0,8}$/,
		                        regexText : '确认密码长度不能超过8个字符'
		                    },{
				          		fieldLabel:'email',
								name: 'email',
								id:mainId+"email",
								 maxLength:150  
				  			},{
				          		fieldLabel:'电话',
								name: 'phone',
								id:mainId+"phone",
								 maxLength:40  
				  			}
				  			
				  			]
		});
	     
	    	//=====================role=========================================
	        var dsFrom = Ext.create('Ext.data.ArrayStore', {
	        	pageSize:0,
	            proxy: {
	                type: 'ajax',
	                url : appName+ '/admin/role/queryNotHave',
	                reader: {
//			        	root : "results",
//						totalProperty: "totalProperty",
//						successProperty:'success'
			        },
			        extraParams: {
			        	 userId :  id 
			        }
	            },
	            autoLoad: true,
//	            sortInfo: {
//	                field: 'value',
//	                direction: 'ASC'
//	            },
	            fields: ['roleId', 'roleName']
	        });
	        
	        var dsTo = Ext.create('Ext.data.ArrayStore', {
	        	pageSize:0,
	            proxy: {
	                type: 'ajax',
	                url : appName+ '/admin/role/queryHave',
	                reader: {
			        },
			        extraParams: {
			        	 userId :  id 
			        }
	            },
	            baseParams: {  
	                userId :  id 
	            },  
	            autoLoad: true,
	            fields: ['roleId', 'roleName']
	        });
	    	var roleForm = new Ext.form.FormPanel({
//	            title: '角色',
	            bodyStyle: 'padding:10px;',
	            items:[{
	            	 xtype: 'itemselector',
	                 name: 'itemselector',
	                 id: mainId+"itemselector",
	                 anchor: '100%',
	                 height:220,
//	                 fieldLabel: 'ItemSelector',
	                 imagePath: '../ux/images/',
	                 store: dsFrom,
	                 tostore: dsTo,
	                 displayField: 'roleName',
	                 valueField: 'roleId',
	                 allowBlank: false,
	                 msgTarget: 'side',
	                 fromTitle: '未选角色',
	                 toTitle: '已选角色'
	            }]
//	            buttons: [{
//	                text: 'Save',
//	                handler: function(){
//	                    if(isForm.getForm().isValid()){
//	                        Ext.Msg.alert('Submitted Values', 'The following will be sent to the server: <br />'+
//	                            isForm.getForm().getValues(true));//这里是获得value的值，取值应该是使用request，名字是itemselector
//	                    }
//	                	alert(Ext.getCmp("itemselector-field").getValue());
//	                }
//	            }]
	        });

//==============tabs========================================================
    	var UserTabs = new Ext.TabPanel({
    		deferredRender:false,
			enableTabScroll : true,
			border : false,
			activeTab : 0,
			items : [{
				itemId : mainId+"_1",
				layout : 'fit',
				title : "基本信息",
				width : '100%',
				items : [formpanel ],
				closable : false
			},{
				itemId : mainId+"_2",
				layout : 'fit',
				title : "角色",
				width : '100%',
				items : [roleForm],
				closable : false,
				listeners:{
				}
			}]
		});

		
		Ext.apply(this, {
				title : text+'用户',
				layout:'fit',
				items : [UserTabs],
				width : 800,
				height : 370,
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
						me.addorUpdateUser(me,formpanel,mainId,parentStore,id,type,isAdd,dsTo);
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
				    		var userName= record.get("userName");
				    		var realName = record.get("realName");
				    		var email= record.get("email");
				    		var phone= record.get("phone");
				    		
				    		Ext.getCmp(mainId+"userName").setValue(userName);
				    		Ext.getCmp(mainId+"userName").setDisabled(true);
				    		
				    		Ext.getCmp(mainId+"realName").setValue(realName);
				    		Ext.getCmp(mainId+"email").setValue(email);
				    		Ext.getCmp(mainId+"phone").setValue(phone);
				    		
				    		
				    	}
					}
				}
			}
		);
		
	},

	addorUpdateUser : function(me,formpanel,mainId,parentStore,id,type,isAdd,dsTo) {
		var userRoleList = [];
		var itemselector = Ext.getCmp(mainId+"itemselector");
		var allRole = itemselector.getValue();
		allRole.forEach(function(ele,index){  
             userRoleList.push({
            	 roleId:allRole[index]
             })
         });  
//		for (var i = 0; i < dsTo.getCount(); i++) {
//			var record = dsTo.getAt(i);
//			alert(record.get("roleId"));
//	        userRoleList.push({
//	       	 	roleId:record.get("roleId")
//	        })
//		}
		if(allRole=="")
			userRoleList=null;
		var userName =Ext.getCmp(mainId+"userName").getValue().trim();
		var realName = Ext.getCmp(mainId+"realName").getValue().trim();
		var password =Ext.getCmp(mainId+"password").getValue();
		var confirmPassword = Ext.getCmp(mainId+"confirmPassword").getValue();
		var email = Ext.getCmp(mainId+"email").getValue();
		var phone =Ext.getCmp(mainId+"phone").getValue().trim();
		
		if(userName==""){
			Ext.getCmp(mainId+"userName").markInvalid("用户名不能为空！");
			return;
		}
		if(type=="add"){
			if(password==""){
				password="123456";
//				Ext.getCmp(mainId+"password").markInvalid("密码不能为空！");
//				return;
			}
			if(confirmPassword==""){
//				Ext.getCmp(mainId+"confirmPassword").markInvalid("确认密码不能为空！");
//				return;
			}
		}
		
		var addOrUpdateUserObj = {
          	  __status : type,
        	  userId:id,
        	  userName:userName,
        	  realName:realName,
        	  passwordEncrypted:password,
        	  email:email,
        	  phone : phone
          };
		
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/user/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify({
                	  user:addOrUpdateUserObj,
                	  userRoleList:userRoleList
                  }),
                  success : function(response, options) {
                	  Ext.getBody().unmask();
                	  var responseArray = Ext.util.JSON.decode(response.responseText);
	                  if (responseArray.success == true) {
//	                	    ExtAlert("成功");
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





