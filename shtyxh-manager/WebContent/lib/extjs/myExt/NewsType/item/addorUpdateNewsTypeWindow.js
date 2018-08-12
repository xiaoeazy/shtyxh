Ext.namespace('addorUpdateNewsType');
addorUpdateNewsType.addorUpdateNewsTypeWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateNewsType.addorUpdateNewsTypeWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateNewsType.addorUpdateNewsTypeWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_NewsTypewindow";
	    	var type = me.type;
	    	var record = me.record;
	    	var id ="";
	    	if(record!=null){
	    		id= record.get("id");
	    	}
	    	var parentStore = this.parentStore; 
	    	var text = type=="update"?"更新":"添加";
	    	var isAdd =  type=="update"?false:true;
	    	
	    	//======================================================================
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
			        	relatetype :  0 
			        }
			    },
			    autoLoad : true,
			    fields: ['id', 'typename']
			});
	    	
	       var parentTypeCombo = new Ext.form.ComboBox({
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
	    	 //=====================是否前台显示=========================================
		       var showIndex_Combo_Store = new Ext.data.Store({
		    	   fields: [  
		    	         {name: 'key', type: 'string'},  
		    	         {name: 'value',  type: 'string'}
		    	     ],  
		    	     data : [  
		    	         {key: 'true',    value: '显示'},
		    	         {key: 'false',    value: '不显示'}
		    	     ]  
		    	});
		       
		       var showIndexCombo = new Ext.form.ComboBox({
		    	    style:'padding:5px',
		    	    columnWidth: .33  ,   
		      		fieldLabel:'是否前台显示<font color="red">*</font>',
		    	    id:mainId+"showindex",
		            store : showIndex_Combo_Store,  
		            valueField : "key",  
		            mode : 'remote',  
		            displayField : "value",  
		            forceSelection : true,  
		            emptyText : '请选择',  
		            editable : false,  
		            triggerAction : 'all',  
		            hiddenName : "value",  
		            autoShow : true,  
		            selectOnFocus : true,  
		            name : "showindex",
		            listeners:{
		           	afterrender:function(comb){
		           	},
		           	select:function(combo, record, index){
		           	}
		           }
		       }); 
		       
		       //=====================是否显示入口=========================================
		       var showentrance_Combo_Store = new Ext.data.Store({
		    	   fields: [  
		    	         {name: 'key', type: 'string'},  
		    	         {name: 'value',  type: 'string'}
		    	     ],  
		    	     data : [  
		    	         {key: 'true',    value: '显示'},
		    	         {key: 'false',    value: '不显示'}
		    	     ]  
		    	});
		       
		       var showEntranceCombo = new Ext.form.ComboBox({
		    	    style:'padding:5px',
		    	    columnWidth: .33  ,   
		      		fieldLabel:'是否前台显示<font color="red">*</font>',
		    	    id:mainId+"showentrance",
		            store : showentrance_Combo_Store,  
		            valueField : "key",  
		            mode : 'remote',  
		            displayField : "value",  
		            forceSelection : true,  
		            emptyText : '请选择',  
		            editable : false,  
		            triggerAction : 'all',  
		            hiddenName : "value",  
		            autoShow : true,  
		            selectOnFocus : true,  
		            name : "showentrance",
		            listeners:{
		           	afterrender:function(comb){
		           	},
		           	select:function(combo, record, index){
		           	}
		           }
		       }); 
	    	
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
				          		fieldLabel:'类型名<font color="red">*</font>',
								allowBlank:false,
								name: 'typename',
								blankText:'必须填写',
								id:mainId+"typename",
					            maxLength:45  
				  			},
				  			parentTypeCombo,
				  			showIndexCombo,
				  			showEntranceCombo,
				  			{
								xtype:'container',
								fieldLabel : '上传logo',
								style:'padding:20px 0 5px 0px',
								items:[{
								 	width:170,
								 	height:120,
								 	fieldLabel : '显示',
									xtype : 'box',
									id :mainId+"showEntranceImagePathPict",
									autoEl : {
										width:170,
									 	height:120,
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
									text : '上传入口图标（170*120）',
									handler:function(){
										var win = new uploadImageBase.uploadImageBaseWin({the_hidden_image_url:mainId+"entranceimagepath",the_image_show:mainId+"showEntranceImagePathPict",type:'entranceimage'});
										win.show();
									}
							 	},{ 
					             	id:mainId+"entranceimagepath",
					                xtype:"textfield",  
									fieldLabel : 'entranceimagepath',
					                hidden:true
				                }]
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
				    		var typename= record.get("typename");
				    		Ext.getCmp(mainId+"typename").setValue(typename);
				    		var showindex =  record.get("showindex");
				    		var showentrance =  record.get("showentrance");
				    		var parentid =  record.get("parentid");
				    		showIndexCombo.setValue(showindex);
				    		showEntranceCombo.setValue(showentrance);
				    		parentTypeCombo.setValue(parentid);
				    		var entranceimagepath= record.get("entranceimagepath");
				    		Ext.getCmp(mainId+"entranceimagepath").setValue(entranceimagepath);
				    		if(entranceimagepath!=null&&entranceimagepath!=""){
				    			Ext.getCmp(mainId+"showEntranceImagePathPict").getEl().dom.src=appName+entranceimagepath;
				    		}
				    	}else{
				    		showIndexCombo.setValue(false);
				    		showEntranceCombo.setValue(false);
				    	}
					}
				}
			}
		);
		
	},
	
	
	addorUpdateType : function(me,formpanel,mainId,parentStore,id,type,isAdd) {
		var typename =Ext.getCmp(mainId+"typename").getValue().trim();
		if(typename==""){
			Ext.getCmp(mainId+"typename").markInvalid("类型名不能为空！");
			return;
		}
		var showindex =Ext.getCmp(mainId+"showindex").getValue();
		var showentrance =Ext.getCmp(mainId+"showentrance").getValue();
		var parentid =  Ext.getCmp(mainId+"typeid").getValue();
		
		if(parentid==null){
			Ext.getCmp(mainId+"parentid").markInvalid("父标签不能为空！");
			return;
		}
		
		var entranceimagepath = Ext.getCmp(mainId+"entranceimagepath").getValue();
		
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/type/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  typename : typename,
                	  parentid:parentid,
                	  showindex:showindex,
                	  showentrance:showentrance,
                	  entranceimagepath:entranceimagepath,
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





