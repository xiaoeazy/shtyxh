Ext.namespace('addorUpdateExpertAssessmentUserProcess');
addorUpdateExpertAssessmentUserProcess.addorUpdateExpertAssessmentUserProcessWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateExpertAssessmentUserProcess.addorUpdateExpertAssessmentUserProcessWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateExpertAssessmentUserProcess.addorUpdateExpertAssessmentUserProcessWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_addorUpdateExpertAssessmentUserProcess";
	    	var type = me.type;
	    	var record = me.record;
	    	var id =record.get("id");
	    	var parentStore = this.parentStore; 
	    
	       //=====================formpanel=========================================
	    	var rg = new Ext.form.RadioGroup({
	            fieldLabel : "是否通过",
	            items : [{  
                    boxLabel : "是",    
                    name : "pass",  
                    inputValue : "Y"  
                }, {  
                    boxLabel : "否",  
                    name : "pass",  
                    inputValue : "N"  
                }]
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
				  			rg,
				  			{
				  				xtype:"textarea",
				          		fieldLabel:'审核意见',
								allowBlank:true,
								width:'100%',
								height:300,
								name: 'expertSuggestion',
								id:mainId+"expertSuggestion"
				  			}
				  			]
		});
	     
			
//==============tabs========================================================
	    	
		Ext.apply(this, {
				title : '专家评估',
				layout:'fit',
				items : [formpanel],
				width : 400,
				height : 470,
				autoHeight:true,
				xtype : "window",
				resizable : false,
				constrain:true,
				minimize : function() { 
		            this.hide(); 
		        },
				modal : true,
			    buttonAlign : "center",
			    buttons:[{
				  width:50,
					text : '提交',
					handler : function(button, event) {
						me.submit(me,formpanel,mainId,record,rg,type,parentStore);
					}
			    },{
				  width:50,
					text : '取消',
					handler : function(button, event) {
						me.close();
					}
			    }],
				listeners:{
					show:function(){
						if(record!=null){
				    		var expertSuggestion= record.get("expertSuggestion");
				    		var state = record.get("state");
				    		if(state>40){
				    			Ext.getCmp(mainId+"expertSuggestion").setValue(expertSuggestion);
				    			if(state==50)
				    				rg.setValue({pass: "Y"});
				    			else if(state==60)
				    				rg.setValue({pass: "N"});
				    		}
				    	}
					}
				}
			}
		);
		
	},
	
	submit : function(me,formpanel,mainId,record,rg,type,parentStore) {
		var id = record.get("id");
		var rgValue=rg.getValue().pass;
		if(rgValue==null){
			alert("请选定通过状态");
			return;
		}
		var expertSuggestion =Ext.getCmp(mainId+"expertSuggestion").getValue();
		var state = "";
		if(rgValue=="Y"){
			state ="50";
		}else{
			state ="60";
		}
		
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/assessment/activity/user/progress/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  expertSuggestion:expertSuggestion,
                	  state:state,
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





