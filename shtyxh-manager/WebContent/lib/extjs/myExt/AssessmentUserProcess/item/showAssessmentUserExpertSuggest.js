Ext.namespace('showAssessmentUserExpertSuggest');
showAssessmentUserExpertSuggest.showAssessmentUserExpertSuggestWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	showAssessmentUserExpertSuggest.showAssessmentUserExpertSuggestWindow.superclass.constructor.call(this);
};

Ext.extend(showAssessmentUserExpertSuggest.showAssessmentUserExpertSuggestWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_addorUpdateAdminAssessmentUserProcess";
	    	var record = me.record;
	    	var id =record.get("id");
	    	var parentStore = this.parentStore; 
	    
	       //=====================formpanel=========================================
	    	var rg = new Ext.form.RadioGroup({
	            fieldLabel : "是否通过",
	            items : [{  
                    boxLabel : "是",    
                    name : "pass",  
                    readOnly : true,
                    inputValue : "Y"  
                }, {  
                    boxLabel : "否", 
                    readOnly : true,
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
				          		fieldLabel:'专家审核意见',
								allowBlank:true,
								readOnly:true,
								name: 'expertSuggestion',
								id:mainId+"expertSuggestion"
				  			}
				  			]
		});
	     
			
//==============tabs========================================================
	    	
		Ext.apply(this, {
				title : '查看专家审核意见',
				layout:'fit',
				items : [formpanel],
				width : 400,
				height : 270,
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
				    		if(state =="50"){
				    			rg.setValue({pass: "Y"});
				    		}
				    		if(state =="60"){
				    			rg.setValue({pass: "N"});
				    		}
				    		if(expertSuggestion!=null&&expertSuggestion!="")
				    			Ext.getCmp(mainId+"expertSuggestion").setValue(expertSuggestion);
				    	}
					}
				}
			}
		);
		
	}

	
});





