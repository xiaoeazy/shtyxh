Ext.namespace('addorUpdateAssessmentActivity');
addorUpdateAssessmentActivity.addorUpdateAssessmentActivityWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateAssessmentActivity.addorUpdateAssessmentActivityWindow.superclass.constructor.call(this);
};

//Ext.extend(addorUpdateAssessmentActivity.addorUpdateAssessmentActivityWindow, Ext.Window, {
Ext.extend(addorUpdateAssessmentActivity.addorUpdateAssessmentActivityWindow, Ext.Panel, {
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_AssessmentActivitywindow";
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
			        url : appName+ '/admin/assessment/type/queryAll',
			        reader: {
			        	root : "results",
						totalProperty: "totalProperty",
						successProperty:'success'
			        }
			    },
			    autoLoad : true,
			    fields: ['id', 'assessmentTypeName'],
			    listeners:{
			    	'load': function(store, records, options) {
			    		 store.insert(0,{id:'-1',assessmentTypeName:'请选择'});
			    		 if(record!=null){
			    			var assessmentTypeId = record.get("assessmentTypeId");
				    		typeCombo.setValue(assessmentTypeId);
			    		 }
			    	}
			    }
			});
	    	
	       var typeCombo = new Ext.form.ComboBox({
	    	   allowBlank : false,
	    	   		fieldLabel:'类型<font color="red">*</font>',
		    	    id:mainId+"assessmentTypeId",
		            store : typeid_Combo_Store,  
		            valueField : "id",  
		            mode : 'remote',  
		            displayField : "assessmentTypeName",  
		            forceSelection : true,  
		            blankText : '请选择',  
		            emptyText : '请选择',  
		            editable : false,  
		            triggerAction : 'all',  
		            allowBlank : false,  
		            hiddenName : "assessmentTypeName",  
		            autoShow : true,  
		            selectOnFocus : true,  
		            name : "assessmentTypeId",
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
	       
	       //=====================是否结束=========================================
	       var finished_Combo_Store = new Ext.data.Store({
	    	   fields: [  
	    	         {name: 'key', type: 'string'},  
	    	         {name: 'value',  type: 'string'}
	    	     ],  
	    	     data : [  
	    	         {key: 'true',    value: '未结束'},
	    	         {key: 'false',    value: '已结束'}
	    	     ]  
	    	});
	       
	       var finishedCombo = new Ext.form.ComboBox({
	    	    style:'padding:5px',
	    	    columnWidth: .33  ,   
	      		fieldLabel:'任务状态<font color="red">*</font>',
	    	    id:mainId+"finished",
	            store : finished_Combo_Store,  
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
	            name : "finished",
	            listeners:{
	           	afterrender:function(comb){
	           	},
	           	select:function(combo, record, index){
	           	}
	           }
	       }); 
	       //=====================formpanel=========================================
	    	var formpanel = new Ext.FormPanel({
	    		  region:'north',
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
				          		fieldLabel:'评估任务名称<font color="red">*</font>',
								allowBlank:false,
								name: 'assessmentActivityName',
								blankText:'必须填写',
								id:mainId+"assessmentActivityName",
					            maxLength:45  
				  			},
				  			typeCombo,
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
		                    finishedCombo,
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
	    	
		Ext.apply(this, {
//				title : text+'评估任务',
				layout:'border',
				items : [formpanel,{
					region:'center',
					title : "主体内容",
					width : '100%',
					items : [{
		                xtype: 'ueditor',
		                fieldLabel: '内  容<font color="red">*</font>',
		                id: mainId+"assessmentActivityContent",
		                //不要设置高度，否则滚动条出现后工具栏会消失
		                width: '100%'
		            } ],
					closable : false,
					listeners:{
						afterrender:function(){
							if(record!=null){
								var assessmentActivityContent= record.get("assessmentActivityContent");
					    		Ext.getCmp(mainId+"assessmentActivityContent").setValue(assessmentActivityContent);
					    	}
						}
					}
				}],
//				width : 800,
//				height : 670,
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
						me.addorUpdateAssessmentActivity(me,formpanel,mainId,parentStore,id,type,isAdd,tabName);
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
				    		var assessmentActivityName= record.get("assessmentActivityName");
				    		var assessmentTypeId = record.get("assessmentTypeId");
				    		var finished = record.get("finished");
				    		Ext.getCmp(mainId+"assessmentActivityName").setValue(assessmentActivityName);
				    		typeCombo.setValue(assessmentTypeId);
				    		finishedCombo.setValue(finished);
				    		var sequence = record.get("sequence");
				    		 Ext.getCmp(mainId+"sequence").setValue(sequence);
				    	}else{
				    		finishedCombo.setValue(true);
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
	
	
	addorUpdateAssessmentActivity : function(me,formpanel,mainId,parentStore,id,type,isAdd,tabName) {
		var attributeValue = Ext.getCmp(mainId+'attribute').getChecked();
		var attributeid="";
		Ext.Array.each(attributeValue, function(item){
//			attributeid +=  item.boxLabel+",";
			attributeid +=  item.inputValue+",";
		});
		if(attributeid!="")
			attributeid=attributeid.substring(0,attributeid.length-1);
		
		var assessmentActivityName =Ext.getCmp(mainId+"assessmentActivityName").getValue().trim();
		var assessmentTypeId =Ext.getCmp(mainId+"assessmentTypeId").getValue();
		var assessmentActivityContent = Ext.getCmp(mainId+"assessmentActivityContent").getEditor().getContent();
		if(assessmentActivityName==""){
			Ext.getCmp(mainId+"assessmentActivityName").markInvalid("评估标题不能为空！");
			return;
		}
		
		if(assessmentActivityContent==""){
			Ext.getCmp(mainId+"assessmentActivityContent").markInvalid("主体内容不能为空！");
			return;
		}
		
		var finished =Ext.getCmp(mainId+"finished").getValue();
		var sequence = Ext.getCmp(mainId+"sequence").getValue();
		
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/assessment/activity/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  attributeid:attributeid,
                	  finished:finished,
                	  assessmentActivityName:assessmentActivityName,
                	  assessmentTypeId:assessmentTypeId,
                	  assessmentActivityContent:assessmentActivityContent,
                	  sequence:sequence,
                	  id : id
                  }]),
                  success : function(response, options) {
                	  Ext.getBody().unmask();
                	  var responseArray = Ext.util.JSON.decode(response.responseText);
	                  if (responseArray.success == true) {
//	                	    ExtAlert("成功");
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





