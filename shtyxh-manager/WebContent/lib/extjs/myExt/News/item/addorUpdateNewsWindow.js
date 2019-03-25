Ext.namespace('addorUpdateNews');
addorUpdateNews.addorUpdateNewsWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateNews.addorUpdateNewsWindow.superclass.constructor.call(this);
};

//Ext.extend(addorUpdateNews.addorUpdateNewsWindow, Ext.Window, {
Ext.extend(addorUpdateNews.addorUpdateNewsWindow, Ext.Panel, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_Newswindow";
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
	    	
	    	
	    	
	    	
	    	var typeid_Combo_Store = new Ext.data.Store({
	    		pageSize:0,
	    		proxy: {
			        type: 'ajax',
			        url : appName+ '/admin/type/queryAllByParam',
			        reader: {
			        	root : "results",
						totalProperty: "totalProperty",
						successProperty:'success'
			        },
			        extraParams: {
			        	relatetype :  2 
			        }
			    },
			    autoLoad : true,
			    fields: ['id', 'typename'],
			    listeners:{
	            	load:function(){
	            		if(record!=null){
				    		var typeid = record.get("typeid");
				    		typeCombo.setValue(typeid);
	            		}
	            	}
			    }
			});
	    	
	       var typeCombo = new Ext.form.ComboBox({
	    	   		fieldLabel:'类型<font color="red">*</font>',
		    	    id:mainId+"typeid",
		            store : typeid_Combo_Store,  
		            valueField : "id",  
		            mode : 'remote',  
		            displayField : "typename",  
		            forceSelection : true,  
		            blankText : '请选择',  
		            emptyText : '请选择',  
		            editable : false,  
		            triggerAction : 'all',  
		            allowBlank : false,  
		            hiddenName : "typename",  
		            autoShow : true,  
		            selectOnFocus : true,  
		            name : "typeid",
		            listeners:{
		            	afterrender:function(comb){
//		            		 typeCombo.setValue(-1);
//		            	     typeCombo.setRawValue("所有");
		            	},
		            	select:function(combo, record, index){
//		            		var combovalue = combo.getValue();
//		            		Ext.apply(other_Combo_Store.baseParams, { repid:combovalue  });
//		            		other_Combo_Store.load();
		            	}
		            }
		        }); 
	       
	       //=====================source=========================================
	    	var sourceid_Combo_Store = new Ext.data.Store({
	    		pageSize:0,
	    		proxy: {
			        type: 'ajax',
			        url : appName+ '/admin/newssource/queryAll',
			        reader: {
			        	root : "results",
						totalProperty: "totalProperty",
						successProperty:'success'
			        }
			    },
			    autoLoad : true,
			    fields: ['id', 'sourcename'],
			    listeners:{
	            	load:function(){
	            		if(record!=null){
	            			var sourceid = record.get("sourceid");
				    		sourceCombo.setValue(sourceid);
	            		}
	            	}
			    }
			});
	    	
	       var sourceCombo = new Ext.form.ComboBox({
	    	   		fieldLabel:'来源<font color="red">*</font>',
		    	    id:mainId+"sourceid",
		            store : sourceid_Combo_Store,  
		            valueField : "id",  
		            mode : 'remote',  
		            displayField : "sourcename",  
		            forceSelection : true,  
		            blankText : '请选择',  
		            emptyText : '请选择',  
		            editable : false,  
		            triggerAction : 'all',  
		            allowBlank : false,  
		            hiddenName : "sourcename",  
		            autoShow : true,  
		            selectOnFocus : true,  
		            name : "sourceid",
		            listeners:{
		            	afterrender:function(comb){
		            	},
		            	select:function(combo, record, index){
		            	}
		            }
		        });  
	       //=====================attribute=========================================
	       var attribute_Combo_Store = new Ext.data.Store({
	    	   pageSize:0,
	    		proxy: {
			        type: 'ajax',
			        url : appName+ '/admin/newsattribute/queryAll',
			        reader: {
			        	root : "results",
						totalProperty: "totalProperty",
						successProperty:'success'
			        }
			    },
			    autoLoad : true,
			    fields: ['id', 'attributename'],
			    listeners:{
			    	load:function(){
			    		if(record==null){
			    			me.LoadingAttribute1(me,formpanel,attribute_Combo_Store,mainId);
			    		}else{
			    			me.LoadingAttribute2(me,record,formpanel,attribute_Combo_Store,mainId);
			    		}
			    	}
			    }
			});
	       //=====================是否前台显示=========================================
	       var indexShowCombo = new Ext.form.ComboBox({
   	   			fieldLabel:'是否前台展示图片<font color="red">*</font>',
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
				          		fieldLabel:'标题名<font color="red">*</font>',
								allowBlank:false,
								name: 'newstitle',
								blankText:'必须填写',
								id:mainId+"newstitle",
					            maxLength:200  
				  			},
				  			typeCombo,
				  			sourceCombo,
				  			{
		                        xtype: 'checkboxgroup',
		                        id: mainId+"attribute",
		                        name: 'attribute',
		                        columns: 4,
		                        fieldLabel: '自定义属性',
		                        labelWidth: 100,
		                        width: 750,
		                        align: 'left',
		                        border: true,
		                        anchor: '100%', flex: 1,
		                        listeners: {
		                            render: function (view, opt) {
		                            	
		                            }
		                        }
		                    },
		                    indexShowCombo,
				  			{
								xtype:'container',
								fieldLabel : '上传',
								style:'padding:0 0 5px 20px',
								items:[{
								 	width:470,
								 	height:315,
								 	fieldLabel : '显示',
									xtype : 'box',
									id :mainId+"showPict",
									autoEl : {
										width:470,
									 	height:315,
										tag : 'img',
										src :nonePic,
										style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
										complete : 'off'
									}
								},{
									xtype : 'button',
									width : 150,
									style : 'margin-left:10px',
									//name : 'imgupload',
									text : '上传（470*315）',
									handler:function(){
										var win = new uploadImageBase.uploadImageBaseWin({the_hidden_image_url:mainId+"imageUrl",the_image_show:mainId+"showPict",type:'news'});
										win.show();
									}
							 	},{
									xtype : 'button',
									width : 150,
									style : 'margin-left:10px',
									text : '撤销图片',
									handler:function(){
										Ext.getCmp(mainId+"imageUrl").setValue("");
							    		Ext.getCmp(mainId+"showPict").getEl().dom.src=nonePic;
									}
							 	}]
							},{ 
				             	id:mainId+"imageUrl",
				                xtype:"textfield",  
								fieldLabel : '缩略图',
				                hidden:true
			                },{
			                	xtype:'textarea',
				          		fieldLabel:'简介',
								allowBlank:true,
								name: 'summary',
								blankText:'必须填写',
								id:mainId+"summary",
								 maxLength:200  
				  			},
				  			{
				  				width:276,
				  				xtype:'numberfield',
				  				fieldLabel:'权重',
				  				name:'sequence',
				  				id:mainId+"sequence",
				  				minValue:0,
				  				value:0
				  			}
				  			
				  			]
		});
	     
			
//==============tabs========================================================
    	var newsTabs = new Ext.TabPanel({
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
				items : [{
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
//				title : text+'咨讯',
				layout:'fit',
				items : [newsTabs],
//				width : 800,
//				height : 570,
				autoHeight:true,
//				xtype : "window",
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
						me.addorUpdateLink(me,formpanel,mainId,parentStore,id,type,isAdd,tabName);
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
				    		var newstitle= record.get("newstitle");
				    		var typeid = record.get("typeid");
				    		var sourceid = record.get("sourceid");
				    		var thumbnail= record.get("thumbnail");
				    		var summary= record.get("summary");
				    		var sequence = record.get("sequence");
				    		var content= record.get("content");
				    		var indexshow = record.get("indexshow");
				    		Ext.getCmp(mainId+"newstitle").setValue(newstitle);
//				    		typeCombo.setValue(typeid);
//				    		sourceCombo.setValue(sourceid);
				    		
				    		Ext.getCmp(mainId+"imageUrl").setValue(thumbnail);
				    		if(thumbnail!=null&&thumbnail!=""){
				    			Ext.getCmp(mainId+"showPict").getEl().dom.src=fileAppName+thumbnail;
				    		}
				    		Ext.getCmp(mainId+"summary").setValue(summary);
				    		 Ext.getCmp(mainId+"sequence").setValue(sequence);
				    		 Ext.getCmp(mainId+"indexshow").setValue(indexshow);
//				    		Ext.getCmp(mainId+"content").setValue(content);
//				    		Ext.getCmp(mainId+"content").getEditor().setContent(content);
				    	}else{
				    		sourceCombo.setValue(1);
				    		indexShowCombo.setValue('N');
				    	}
					}
				}
			}
		);
		
	},
	LoadingAttribute1:function(me,formpanel,store,mainId){
		  var checkboxgroup = Ext.getCmp(mainId+"attribute");
		  for (var i = 0; i < store.getCount(); i++) {
			  var record = store.getAt(i);
			  var id = record.get("id");
			  var checkbox = new Ext.form.Checkbox(
		                 {
		                     boxLabel: record.get("attributename"),
//		                     name: record[i].OperationCode,
		                     inputValue:id,
		                     checked: false
		                 });
		          checkboxgroup.items.add(checkbox);
		  }
		  formpanel.doLayout();
		 
	},
	LoadingAttribute2:function(me,infoRecord,formpanel,store,mainId){
		  var checkboxgroup = Ext.getCmp(mainId+"attribute");
		  var attribute = infoRecord.get("attributeid");
		  var attributeArray = [];
		  if(attribute!=null){
			  attributeArray = attribute.split(",")
		  }
		  for (var i = 0; i < store.getCount(); i++) {
			  var record = store.getAt(i);
			  var id = record.get("id");
			  var inarray = attributeArray.in_array(id);
			  var checked = true;
			  if(!inarray){
				  checked = false;
			  }
			  var checkbox = new Ext.form.Checkbox(
		                 {
		                     boxLabel: record.get("attributename"),
//		                     name: record[i].OperationCode,
		                     inputValue:id,
		                     checked: checked
		                 });
		          checkboxgroup.items.add(checkbox);
		  }
		  formpanel.doLayout();
		 
	},
	
	addorUpdateLink : function(me,formpanel,mainId,parentStore,id,type,isAdd,tabName) {
		
		var attributeValue = Ext.getCmp(mainId+'attribute').getChecked();
		var attributeid="";
		Ext.Array.each(attributeValue, function(item){
//			attributeid +=  item.boxLabel+",";
			attributeid +=  item.inputValue+",";
		});
		if(attributeid!="")
			attributeid=attributeid.substring(0,attributeid.length-1);
		
		var newstitle =Ext.getCmp(mainId+"newstitle").getValue().trim();
		var typeid =Ext.getCmp(mainId+"typeid").getValue();
		var sourceid = Ext.getCmp(mainId+"sourceid").getValue();
		var summary =Ext.getCmp(mainId+"summary").getValue().trim();
		var thumbnail =Ext.getCmp(mainId+"imageUrl").getValue().trim();
		var content = Ext.getCmp(mainId+"content").getEditor().getContent();
		var sequence = Ext.getCmp(mainId+"sequence").getValue();
		var indexshow = Ext.getCmp(mainId+"indexshow").getValue();
		if(newstitle==""){
			Ext.getCmp(mainId+"newstitle").markInvalid("咨讯标题不能为空！");
			return;
		}
		if(typeid==null){
			Ext.getCmp(mainId+"typeid").markInvalid("类型不能为空！");
			return;
		}
		if(sourceid==null){
			Ext.getCmp(mainId+"sourceid").markInvalid("来源不能为空！");
			return;
		}
		
//		if(summary==""){
//			Ext.getCmp(mainId+"summary").markInvalid("咨讯简介不能为空！");
//			return;
//		}
		if(content==""){
			Ext.getCmp(mainId+"content").markInvalid("主体内容不能为空！");
			return;
		}
		
	
		
	
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/news/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  typeid:typeid,
                	  indexshow:indexshow,
                	  attributeid:attributeid,
                	  sourceid:sourceid,
                	  newstitle : newstitle,
                	  thumbnail : thumbnail,
                	  summary:summary,
                	  content:content,
                	  sequence:sequence,
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





