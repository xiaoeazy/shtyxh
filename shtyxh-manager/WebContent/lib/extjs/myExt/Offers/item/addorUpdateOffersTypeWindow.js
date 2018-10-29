Ext.namespace('addorUpdateOffersType');
addorUpdateOffersType.addorUpdateOffersTypeWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateOffersType.addorUpdateOffersTypeWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateOffersType.addorUpdateOffersTypeWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_OffersTypewindow";
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
				          		fieldLabel:'名称<font color="red">*</font>',
								allowBlank:false,
								name: 'offertypename',
								blankText:'必须填写',
								id:mainId+"offertypename",
					            maxLength:45  
				  			}
				  			],
				  buttonAlign : "center",
				  buttons:[{
					  width:50,
						text : text,
						handler : function(button, event) {
							me.addorUpdateType(me,formpanel,mainId,parentStore,id,type,isAdd);
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
				title : text+'类型',
				layout:'fit',
				items : [formpanel],
				width : 390,
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
				    		var offertypename= record.get("offertypename");
				    		Ext.getCmp(mainId+"offertypename").setValue(offertypename);
				    	
				    	}
					}
				}
			}
		);
		
	},
	
	
	addorUpdateType : function(me,formpanel,mainId,parentStore,id,type,isAdd) {
		var offertypename =Ext.getCmp(mainId+"offertypename").getValue().trim();
		if(offertypename==""){
			Ext.getCmp(mainId+"offertypename").markInvalid("名称不能为空！");
			return;
		}
	
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/offers/type/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  offertypename : offertypename,
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





