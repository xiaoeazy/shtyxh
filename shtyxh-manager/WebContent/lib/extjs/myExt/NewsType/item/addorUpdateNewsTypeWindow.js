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
	    	   		fieldLabel:'父类型<font color="red">*</font>',
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
		       var hidden_Combo_Store = new Ext.data.Store({
		    	   fields: [  
		    	         {name: 'key', type: 'string'},  
		    	         {name: 'value',  type: 'string'}
		    	     ],  
		    	     data : [  
		    	         {key: 'true',    value: '隐藏'},
		    	         {key: 'false',    value: '显示'}
		    	     ]  
		    	});
		       
		       var showHiddenCombo = new Ext.form.ComboBox({
		    	    style:'padding:5px',
		    	    columnWidth: .33  ,   
		      		fieldLabel:'是否导航栏显示<font color="red">*</font>',
		    	    id:mainId+"hidden",
		            store : hidden_Combo_Store,  
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
		            name : "showhidden",
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
				  			showHiddenCombo
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
				    		var hidden =  record.get("hidden");
				    		var showentrance =  record.get("showentrance");
				    		var parentid =  record.get("parentid");
				    		showHiddenCombo.setValue(hidden);
				    		parentTypeCombo.setValue(parentid);
				    	}else{
				    		showHiddenCombo.setValue(false);
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
		var hidden =Ext.getCmp(mainId+"hidden").getValue();
		var parentid =  Ext.getCmp(mainId+"typeid").getValue();
		
		if(parentid==null){
			Ext.getCmp(mainId+"parentid").markInvalid("父标签不能为空！");
			return;
		}
		
		
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
                	  hidden:hidden,
                	  relatetype:2,
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





