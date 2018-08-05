Ext.namespace('addorUpdateOffers');
addorUpdateOffers.addorUpdateOffersWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateOffers.addorUpdateOffersWindow.superclass.constructor.call(this);
};

//Ext.extend(addorUpdateOffers.addorUpdateOffersWindow, Ext.Window, {
Ext.extend(addorUpdateOffers.addorUpdateOffersWindow, Ext.Panel, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_Offerswindow";
	    	var type = me.type;
	    	var record = me.record;
	    	var id ="";
	    	if(record!=null){
	    		id= record.get("id");
	    	}
	    	var parentStore = this.parentStore; 
	    	var text = type=="update"?"更新":"添加";
	    	var isAdd =  type=="update"?false:true;
	    	var tabName = this.tabName;
	    	
	    	
	    	
	    
	       //=====================formpanel=========================================
	    	var formpanel = new Ext.FormPanel({
	    		  labelAlign: "right",
	    		  labelWidth :100,
			      frame: true,
			      width: '100%',
			      autoScroll:true,
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
				          		fieldLabel:'发布单位<font color="red">*</font>',
								allowBlank:false,
								name: 'publishunit',
								blankText:'必须填写',
								id:mainId+"publishunit",
					            maxLength:45  
				  			},
				  			{
				          		fieldLabel:'联系人<font color="red">*</font>',
								allowBlank:false,
								name: 'contactperson',
								blankText:'必须填写',
								id:mainId+"contactperson",
					            maxLength:45  
				  			},
				  			{
				          		fieldLabel:'联系电话<font color="red">*</font>',
								allowBlank:false,
								name: 'contacttel',
								blankText:'必须填写',
								id:mainId+"contacttel",
					            maxLength:45  
				  			},
				  			{
				          		fieldLabel:'薪资标准<font color="red">*</font>',
								allowBlank:false,
								name: 'salary',
								blankText:'必须填写',
								id:mainId+"salary",
					            maxLength:45  
				  			}
				  			
				  			]
		});
	     
			
//==============tabs========================================================
    	var offersTabs = new Ext.TabPanel({
			enableTabScroll : true,
			border : false,
			activeTab : 0,
			deferredRender:false,
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
				title : "主体内容",
				width : '100%',
				items : [ {
	                xtype: 'ueditor',
	                fieldLabel: '内  容',
	                id: mainId+"content",
	                //不要设置高度，否则滚动条出现后工具栏会消失
	                width: '100%'
	            } ],
				closable : false,
				listeners:{
					afterrender:function(){
						if(record!=null){
							var content= record.get("content");
				    		Ext.getCmp(mainId+"content").setValue(content);
				    	}
					}
				}
			}]
		});

		
		Ext.apply(this, {
				layout:'fit',
				items : [offersTabs],
				autoHeight:true,
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
						me.addorUpdateOffers(me,formpanel,mainId,parentStore,id,type,isAdd,tabName);
					}
			    },{
				  width:50,
					text : '取消',
					handler : function(button, event) {
//						me.close();
						tabs.remove(tabName);
					}
			    }],
				listeners:{
					afterRender:function(){
						if(record!=null){
				    		var publishunit= record.get("publishunit");
				    		var contactperson= record.get("contactperson");
				    		var contacttel= record.get("contacttel");
				    		var salary= record.get("salary");
				    		Ext.getCmp(mainId+"publishunit").setValue(publishunit);
				    		Ext.getCmp(mainId+"contactperson").setValue(contactperson);
				    		Ext.getCmp(mainId+"contacttel").setValue(contacttel);
				    		Ext.getCmp(mainId+"salary").setValue(salary);
				    	}
					}
				}
			}
		);
		
	},
	
	addorUpdateOffers : function(me,formpanel,mainId,parentStore,id,type,isAdd,tabName) {
		
		var publishunit =Ext.getCmp(mainId+"publishunit").getValue().trim();
		var contactperson =Ext.getCmp(mainId+"contactperson").getValue().trim();
		var contacttel =Ext.getCmp(mainId+"contacttel").getValue().trim();
		var salary =Ext.getCmp(mainId+"salary").getValue().trim();
		
		var content = Ext.getCmp(mainId+"content").getEditor().getContent();
		if(publishunit==""){
			Ext.getCmp(mainId+"publishunit").markInvalid("发布单位不能为空！");
			return;
		}
		if(contactperson==null){
			Ext.getCmp(mainId+"contactperson").markInvalid("联系人不能为空！");
			return;
		}
		if(contacttel==null){
			Ext.getCmp(mainId+"contacttel").markInvalid("联系电话不能为空！");
			return;
		}
		
		if(content==""){
			Ext.getCmp(mainId+"content").markInvalid("主体内容不能为空！");
			return;
		}
		
	
		
	
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/offers/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  publishunit:publishunit,
                	  contactperson:contactperson,
                	  contacttel:contacttel,
                	  content : content,
                	  id : id
                  }]),
                  success : function(response, options) {
                	  Ext.getBody().unmask();
                	  var responseArray = Ext.util.JSON.decode(response.responseText);
	                  if (responseArray.success == true) {
	                	    parentStore.reload();
//	                    	me.close();
	                	    tabs.remove(tabName);
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





