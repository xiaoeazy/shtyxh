Ext.namespace('addorUpdateLink');
addorUpdateLink.addorUpdateLinkWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateLink.addorUpdateLinkWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateLink.addorUpdateLinkWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_linkwindow";
	    	var type = me.type;
	    	var record = me.record;
	    	var id ="";
	    	if(record!=null){
	    		id= record.get("id");
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
				          		fieldLabel:'网址名<font color="red">*</font>',
								allowBlank:false,
								name: 'linkName',
								blankText:'必须填写',
								id:mainId+"linkName",
					            maxLength:45  
				  			},{
				          		fieldLabel:'网址地址',
								allowBlank:true,
								name: 'linkUrl',
								id:mainId+"linkUrl",
					            maxLength:200  
				  			}
				  			],
				  buttonAlign : "center",
				  buttons:[{
					  width:50,
						text : text,
						handler : function(button, event) {
							me.addorUpdateLink(me,formpanel,mainId,parentStore,id,type,isAdd);
						}
				  },{
					  width:50,
						text : '取消',
						handler : function(button, event) {
							me.close();
						}
				  }]
		});
	     
			

	        

		
		Ext.apply(this, {
				title : text+'链接',
				layout:'fit',
				items : [formpanel],
				width : 350,
				height : 180,
				xtype : "window",
				resizable : false,
				constrain:true,
				minimize : function() { 
		            this.hide(); 
		        },
				modal : true,
				listeners:{
					show:function(){
						if(record!=null){
				    		var linkName= record.get("linkName");
				    		Ext.getCmp(mainId+"linkName").setValue(linkName);
				    		var linkUrl= record.get("linkUrl");
				    		Ext.getCmp(mainId+"linkUrl").setValue(linkUrl);
				    	}
					}
				}
			}
		);
		
	},
	
	
	addorUpdateLink : function(me,formpanel,mainId,parentStore,id,type,isAdd) {
		var linkName =Ext.getCmp(mainId+"linkName").getValue().trim();
		var linkUrl=Ext.getCmp(mainId+"linkUrl").getValue().trim();
		if(linkName==""){
			Ext.getCmp(mainId+"linkName").markInvalid("网址名不能为空！");
			return;
		}
	
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/link/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  linkName : linkName,
                	  linkUrl : linkUrl,
                	  id : id
                  }]),
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





