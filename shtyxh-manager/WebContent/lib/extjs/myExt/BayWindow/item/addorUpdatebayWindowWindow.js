Ext.namespace('addorUpdateBayWindow');
addorUpdateBayWindow.addorUpdateBayWindowWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateBayWindow.addorUpdateBayWindowWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateBayWindow.addorUpdateBayWindowWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
	    	var mainId=me.mainId+"_Baywindow";
	    	var type = me.type;
	    	var record = me.record;
	    	var id ="";
	    	if(record!=null){
	    		id= record.get("id");
	    	}
	    	var parentStore = this.parentStore; 
	    	var text = type=="update"?"更新":"添加";
	    	var isAdd =  type=="update"?false:true;
	    	
	    	

	    	//================关联活动============================================
	    	var assessment_Combo_Store = new Ext.data.Store({
	    		  autoLoad : true,
	    		pageSize:0,
	    		proxy: {
			        type: 'ajax',
			        url : appName+ '/admin/assessment/activity/queryAll',
			        reader: {
			        	root : "results",
						totalProperty: "totalProperty",
						successProperty:'success'
			        }
			    },
			    fields: ['id', 'assessmentActivityName'],
			    listeners:{
			    	'load': function(store, records, options) {
			    		 store.insert(0,{id:'-1',assessmentActivityName:'所有'});
			    		 if(record!=null){
			    			 var activityId= record.get("activityId");
					    	 assessmentCombo.setValue(activityId);
			    		 }
			    	}
			    }
			});
	    	
	       var assessmentCombo = new Ext.form.ComboBox({
	    	   		fieldLabel:'关联评估任务',
		    	    id:mainId+"activityId",
		            store : assessment_Combo_Store,  
		            valueField : "id",  
		            mode : 'remote',  
		            displayField : "assessmentActivityName",  
		            forceSelection : true,  
		            blankText : '请选择',  
		            emptyText : '请选择',  
		            allowBlank : false,  
		            editable : false,  
		            triggerAction : 'all', 
		            hiddenName : "assessmentActivityName",  
		            autoShow : true,  
		            selectOnFocus : true,  
		            name : "activityId",
		            listeners:{
		            	afterrender:function(comb){
		            	},
		            	select:function(combo, record, index){
		            	}
		            }
		        });  
	    	
	    	//================news store============================================
	    	var news_Combo_Store = new Ext.data.Store({
	    		pageSize:0,
	    		  autoLoad : true,
	    		proxy: {
			        type: 'ajax',
			        url : appName+ '/admin/news/queryAll',
			        reader: {
			        	root : "results",
						totalProperty: "totalProperty",
						successProperty:'success'
			        }
			    },
			    fields: ['id', 'newstitle'],
			    listeners:{
			    	'load': function(store, records, options) {
			    		 store.insert(0,{id:'-1',newstitle:'所有'});
			    		 if(record!=null){
			    			 var newsId= record.get("newsId");
			    			 newsCombo.setValue(newsId);
			    		 }
			    		 
			    	}
			    }
			});
	    	
	       var newsCombo = new Ext.form.ComboBox({
	    	   		fieldLabel:'关联咨讯',
		    	    id:mainId+"newsId",
		            store : news_Combo_Store,  
		            valueField : "id",  
		            mode : 'remote',  
		            displayField : "newstitle",  
		            forceSelection : true,  
		            allowBlank : false,  
		            blankText : '请选择',  
		            emptyText : '请选择',  
		            editable : false,  
		            triggerAction : 'all', 
		            hiddenName : "newstitle",  
		            autoShow : true,  
		            selectOnFocus : true,  
		            name : "newsId",
		            listeners:{
		            	afterrender:function(comb){
		            	},
		            	select:function(combo, record, index){
		            	}
		            }
		        });  
	       
	       
	       //=====================是否前台显示=========================================
	       var indexShowCombo = new Ext.form.ComboBox({
   	   			fieldLabel:'是否显示飘窗<font color="red">*</font>',
	    	    id:mainId+"indexshow",
	    	    model:'local',
	            store:new Ext.data.SimpleStore({  //填充的数据
                    fields : ['text', 'value'],
                    data : [['否', 'N'], ['是', 'Y']]
            	}),
	            valueField : "value",  
	            displayField : "text",  
	            forceSelection : true,  
	            blankText : '请选择',  
	            emptyText : '请选择',  
	            editable : false,  
	            triggerAction : 'all',  
	            allowBlank : false,  
	            hiddenName : "text",  
	            autoShow : true,  
	            selectOnFocus : true,  
	            name : "indexshow",
	            listeners:{
	            	afterrender:function(comb){
	            	},
	            	select:function(combo, record, index){
	            	}
	            }
	        });
	     //=====================form=======================================
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
							 	width:640,
							 	height:256,
							 	fieldLabel : '显示',
								xtype : 'box',
								id :mainId+"showPict",
								autoEl : {
									width:640,
								 	height:255,
									tag : 'img',
									src :nonePic,
									style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
									complete : 'off'
								}
							},{
								xtype:'container',
								fieldLabel : '上传',
								style:'padding:0 0 5px 0',
								items:[{
									xtype : 'button',
									width : 150,
									//name : 'imgupload',
//									text : '上传(1920*765)',
									text : '上传(700*475)',
									handler:function(){
										var win = new uploadImageBase.uploadImageBaseWin({the_hidden_image_url:mainId+"imageUrl",the_image_show:mainId+"showPict",type:'carousel'});
										win.show();
									}
							 	},{
									xtype : 'button',
									width : 150,
									text : '撤销文件',
									handler:function(){
										Ext.getCmp(mainId+"imageUrl").setValue("");
							    		Ext.getCmp(mainId+"showPict").getEl().dom.src=nonePic;
									}
							 	}]
							},
							{ 
				             	id:mainId+"imageUrl",
				                xtype:"textfield",  
								fieldLabel : '缩略图',
				                hidden:true
			                },{  
			                      fieldLabel : '是否激活使用',  
			                      xtype : 'radiogroup',  
			                      id : mainId+'activeds',
			                      columns : 3,  
			                      items : [{  
			                                  boxLabel : "关联地址",  
			                                  name : "actived",  
			                                  inputValue : "0" 
			                              }, {  
			                                  boxLabel : "关联新闻",
			                                  name : "actived",  
			                                  inputValue : "1"  
			                              }, {  
			                                  boxLabel : "关联评估任务", 
			                                  name : "actived",  
			                                  inputValue : "2"  
			                              }],
	                              listeners:{
	                                  'change':function(group,checked){
	                                      var obj = Ext.getCmp(mainId+'activeds').items.items;
	                                      for(var i in obj){
		           				    		   if(obj[i].checked){
		           				    			   var inputValue = obj[i].inputValue;
		           				    			   me.inputValueToSet(me,mainId,inputValue,newsCombo,assessmentCombo);
		           				    		   }
	           				    	      }
	                                  }
	                              }
			                },
				  			{
				          		fieldLabel:'关联网址',
								name: 'webUrl',
								allowBlank : false,  
								blankText:'必须填写',
								id:mainId+"webUrl",
					            maxLength:200  
				  			},
				  			newsCombo,
				  			assessmentCombo,
				  			indexShowCombo
				  			],
				  buttonAlign : "center",
				  buttons:[{
					  width:50,
						text : text,
						handler : function(button, event) {
							me.addorUpdateCarousel(me,formpanel,mainId,parentStore,id,type,isAdd);
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
				title : text+'轮播图',
				layout:'fit',
				items : [formpanel],
				width : 650,
				height : 580,
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
				    		var filePath= record.get("filePath");
				    		var webUrl= record.get("webUrl");
				    		var activityId= record.get("activityId");
				    		var newsId= record.get("newsId");
				    		var indexshow = record.get("indexshow");
				    		Ext.getCmp(mainId+"imageUrl").setValue(filePath);
				    		Ext.getCmp(mainId+"showPict").getEl().dom.src=fileAppName+filePath;
				    		
				    		var urltype= record.get("urltype");
				    		 var obj = Ext.getCmp(mainId+'activeds').items.items;
				             for(var i in obj){
		    	    			   var inputValue = obj[i].inputValue;
		    	    			   if(inputValue==urltype){
		    	    				   obj[i].setValue(true);
		    	    				   break;
		    	    			   }
				      	     }
				             me.inputValueToSet(me,mainId,urltype,newsCombo,assessmentCombo);
				             Ext.getCmp(mainId+"webUrl").setValue(webUrl);
				             Ext.getCmp(mainId+"indexshow").setValue(indexshow);
				    	}else{
				    		indexShowCombo.setValue('N');
				    		Ext.getCmp(mainId+'activeds').items.get(0).setValue(true);
				    		newsCombo.disable();
				    		assessmentCombo.disable();
				    	}
					}
				}
			}
		);
		
	},
	inputValueToSet:function(me,mainId,inputValue,newsCombo,assessmentCombo){
		 if(inputValue==0){
			 	me.emptyItem(mainId,newsCombo,assessmentCombo);
			    Ext.getCmp(mainId+"webUrl").enable();
			    newsCombo.disable();
    			assessmentCombo.disable();
    			
		   }else if(inputValue==1){
			    me.emptyItem(mainId,newsCombo,assessmentCombo);
			    Ext.getCmp(mainId+"webUrl").disable();
				newsCombo.enable();
    			assessmentCombo.disable();
    			
		   }else{
			    me.emptyItem(mainId,newsCombo,assessmentCombo);
				Ext.getCmp(mainId+"webUrl").disable();
				newsCombo.disable();
    			assessmentCombo.enable();
		   }
	},
	emptyItem :function (mainId,newsCombo,assessmentCombo){
		Ext.getCmp(mainId+"webUrl").setValue("");
		newsCombo.setValue(-1);
		assessmentCombo.setValue(-1);
	},
	addorUpdateCarousel : function(me,formpanel,mainId,parentStore,id,type,isAdd) {
		var filePath =Ext.getCmp(mainId+"imageUrl").getValue().trim();
		if(filePath==""){
			ExtError("请上传图片");
			return;
		}
		var indexshow = Ext.getCmp(mainId+"indexshow").getValue();
		var urltype = 0;
		var webUrl = Ext.getCmp(mainId+"webUrl").getValue();
		var activityId = Ext.getCmp(mainId+"activityId").getValue();
		var newsId = Ext.getCmp(mainId+"newsId").getValue();
		
		 var obj = Ext.getCmp(mainId+'activeds').items.items;
         for(var i in obj){
	    		   if(obj[i].checked){
	    			   var inputValue = obj[i].inputValue;
	    			   if(inputValue==0){
	    				   urltype=0;
	    			   }else if(inputValue==1){
	    				   urltype=1;
	    			   }else{
	    				   urltype=2;
	    			   }
	    		   }
  	     }
         if(urltype==1){
        	 if(newsId==-1){
        		 ExtError("请选择关联资讯");
     			return;
        	 }
         }else if(urltype==2){
        	 if(activityId==-1){
        		 ExtError("请选择关联评估任务");
     			return;
        	 }
         }
         
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/baywindow/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  filePath : filePath,
                	  indexshow:indexshow,
                	  urltype:urltype,
                	  webUrl:webUrl,
                	  activityId:activityId,
                	  newsId:newsId,
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





