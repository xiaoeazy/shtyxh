Ext.namespace('addorUpdateDownload');
addorUpdateDownload.addorUpdateDownloadWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateDownload.addorUpdateDownloadWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateDownload.addorUpdateDownloadWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_Downloadwindow";
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
			      fileUpload:true,
			      bodyStyle:'margin: 0px auto',
			      defaults:{
		               xtype:"textfield",
		               width:'100%',
		               bodyStyle:'padding:10px 0px 10px 0px'
		            },
				  items : [ 
						  	{
					  			xtype:'label',
					  			text:''
				  			},
					  		{
								xtype:'container',
								fieldLabel : '上传',
								style:'padding:5px 0 5px 0',
								layout:'column',
								items:[{ 
									 columnWidth:.6,
					             	id:mainId+"filePath",
					                xtype:"textfield",  
					                readOnly:true,
									fieldLabel : '文件<font color="red">*</font>'
				                },{
				                	columnWidth:.2,
									xtype : 'button',
									style:'margin-left:10px',
									width : 100,
									text : '上传',
									handler:function(){
										var win = new uploadImageBase.uploadImageBaseWin({the_hidden_image_url:mainId+"filePath",the_image_show:null,type:'download'});
										win.show();
									}
							 	},{
							 		columnWidth:.2,
									xtype : 'button',
									style:'margin-left:10px',
									width : 100,
									text : '撤销',
									handler:function(){
										Ext.getCmp(mainId+"filePath").setValue("");
									}
							 	}]
							},{
				  				xtype:'textarea',
				          		fieldLabel:'下载文档简介',
								allowBlank:true,
								height:150,
								name: 'summary',
								id:mainId+"summary",
					            maxLength:200  
				  			},{
				  				xtype:'textfield',
				          		fieldLabel:'下载密码',
								allowBlank:true,
								name: 'password',
								id:mainId+"password",
					            maxLength:200  
				  			}
				  			],
				  buttonAlign : "center",
				  buttons:[{
					  width:50,
						text : text,
						handler : function(button, event) {
							me.addorUpdateDown(me,formpanel,mainId,parentStore,id,type,isAdd);
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
				title : text+'文档下载',
				layout:'fit',
				items : [formpanel],
				width : 450,
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
				    		var summary= record.get("summary");
				    		var filePath= record.get("filePath");
				    		var password= record.get("password");
				    		Ext.getCmp(mainId+"summary").setValue(summary);
				    		Ext.getCmp(mainId+"filePath").setValue(filePath);
				    		Ext.getCmp(mainId+"password").setValue(password);
				    	}
					}
				}
			}
		);
		
	},
	
	
	addorUpdateDown : function(me,formpanel,mainId,parentStore,id,type,isAdd) {
	 
		var filePath =Ext.getCmp(mainId+"filePath").getValue().trim();
		if(filePath==""){
			ExtError("请上传文件");
			return;
		}
		var summary = Ext.getCmp(mainId+"summary").getValue();
		var password = Ext.getCmp(mainId+"password").getValue();
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/download/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
	               	  summary : summary,
	               	  filePath:filePath,
	               	  password:password,
	               	  id : id
                  }]),
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





