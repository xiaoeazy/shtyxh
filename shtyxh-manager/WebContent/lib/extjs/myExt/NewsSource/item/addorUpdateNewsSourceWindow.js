Ext.namespace('addorUpdateNewsSource');
addorUpdateNewsSource.addorUpdateNewsSourceWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateNewsSource.addorUpdateNewsSourceWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateNewsSource.addorUpdateNewsSourceWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_NewsSourcewindow";
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
				          		fieldLabel:'来源名',
								allowBlank:false,
								name: 'sourcename',
								blankText:'必须填写',
								id:mainId+"sourcename",
					            maxLength:45  
				  			}
				  			],
				  buttonAlign : "center",
				  buttons:[{
					  width:50,
						text : text,
						handler : function(button, event) {
							me.addorUpdateSource(me,formpanel,mainId,parentStore,id,type,isAdd);
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
				title : text+'来源',
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
				    		var sourcename= record.get("sourcename");
				    		Ext.getCmp(mainId+"sourcename").setValue(sourcename);
				    	}
					}
				}
			}
		);
		
	},
	
	
	addorUpdateSource : function(me,formpanel,mainId,parentStore,id,type,isAdd) {
		var sourcename =Ext.getCmp(mainId+"sourcename").getValue().trim();
		if(sourcename==""){
			Ext.getCmp(mainId+"sourcename").markInvalid("来源名不能为空！");
			return;
		}
	
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/newssource/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  sourcename : sourcename,
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





