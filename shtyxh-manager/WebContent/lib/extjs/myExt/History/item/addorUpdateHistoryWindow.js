Ext.namespace('addorUpdateHistory');
addorUpdateHistory.addorUpdateHistoryWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateHistory.addorUpdateHistoryWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateHistory.addorUpdateHistoryWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_Historywindow";
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
				          		fieldLabel:'年份<font color="red">*</font>',
								allowBlank:false,
								name: 'historyyear',
								blankText:'必须填写',
								id:mainId+"historytime",
					            maxLength:45  
				  			},{
				          		fieldLabel:'日期<font color="red">*</font>',
								allowBlank:false,
								name: 'historytime',
								blankText:'必须填写',
								id:mainId+"historytime",
					            maxLength:45  
				  			},{
				  				fieldLabel:'内容<font color="red">*</font>',
				  				xtype:'textarea',
				  				width:'100%',
								height:300,
								allowBlank:false,
								name: 'content',
								blankText:'必须填写',
								id:mainId+"content",
					            maxLength:200 
				  			}
				  			],
				  buttonAlign : "center",
				  buttons:[{
					  width:50,
						text : text,
						handler : function(button, event) {
							me.addorUpdateHistory(me,formpanel,mainId,parentStore,id,type,isAdd);
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
				title : text+'历史沿革',
				layout:'fit',
				items : [formpanel],
				width : 390,
				height : 380,
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
							var historyyear= record.get("historyyear");
				    		var content= record.get("content");
				    		var historytime= record.get("historytime");
				    		
				    		Ext.getCmp(mainId+"historytime").setValue(historytime);
				    		Ext.getCmp(mainId+"historyyear").setValue(historyyear);
				    		Ext.getCmp(mainId+"content").setValue(content);
				    		
				    	}
					}
				}
			}
		);
		
	},
	
	
	addorUpdateHistory : function(me,formpanel,mainId,parentStore,id,type,isAdd) {
		var historyyear =Ext.getCmp(mainId+"historyyear").getValue().trim();
		var content =Ext.getCmp(mainId+"content").getValue().trim();
		var historytime =Ext.getCmp(mainId+"historytime").getValue().trim();
		
		if(historyyear==""){
			Ext.getCmp(mainId+"historyyear").markInvalid("年份不能为空！");
			return;
		}
		if(historytime==""){
			Ext.getCmp(mainId+"historytime").markInvalid("日期不能为空！");
			return;
		}
		
		if(content==""){
			Ext.getCmp(mainId+"content").markInvalid("内容不能为空！");
			return;
		}
		
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/history/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  historytime : historytime,
                	  historyyear:historyyear,
                	  content:content,
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





