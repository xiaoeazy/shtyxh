Ext.namespace('assessmentUserToExpert');
assessmentUserToExpert.assessmentUserToExpertWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	assessmentUserToExpert.assessmentUserToExpertWindow.superclass.constructor.call(this);
};

Ext.extend(assessmentUserToExpert.assessmentUserToExpertWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
	    	var mainId=me.mainId+"_assessmentUserToExpert";
	    	var type = me.type;
	    	var records = me.records;
	    	var parentStore = this.parentStore; 
	    
	    	//=====================userid=========================================
	    	var sourceid_Combo_Store = new Ext.data.Store({
	    		pageSize:0,
	    		proxy: {
			        type: 'ajax',
			        url : appName+ '/admin/user/queryAllWithRole?roleId=10006',
			        reader: {
			        	root : "results",
						totalProperty: "totalProperty",
						successProperty:'success'
			        }
			    },
			    autoLoad : true,
			    fields: ['userId', 'realName']
			});
	    	
	       var userCombo = new Ext.form.ComboBox({
	    	   		fieldLabel:'转送专家',
		    	    id:mainId+"expertUserId",
		            store : sourceid_Combo_Store,  
		            valueField : "userId",  
		            mode : 'remote',  
		            displayField : "realName",  
		            forceSelection : true,  
		            blankText : '请选择',  
		            emptyText : '请选择',  
		            editable : false,  
		            triggerAction : 'all',  
		            allowBlank : false,  
		            hiddenName : "realName",  
		            autoShow : true,  
		            selectOnFocus : true,  
		            name : "expertUserId",
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
			      bodyStyle:'margin: 0px auto',
			      defaults:{
		               xtype:"textfield",
		               width:'100%',
		               bodyStyle:'padding:10px 0px 10px 0px'
		            },
				  items : [ {
					  			xtype:'label',
					  			text:''
				  			},userCombo]
		});
	     
			
//==============tabs========================================================
	    	
		Ext.apply(this, {
				title : '推送',
				layout:'fit',
				items : [formpanel],
				width : 400,
				height : 120,
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
						me.submit(me,formpanel,mainId,records,type,parentStore);
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
						if(records!=null){
//				    		var adminSuggestion= record.get("adminSuggestion");
//				    		if(adminSuggestion!=null&&adminSuggestion!="")
//				    			Ext.getCmp(mainId+"adminSuggestion").setValue(adminSuggestion);
//				    		else{
//				    			Ext.getCmp(mainId+"adminSuggestion").setValue("通过");
//				    			rg.setValue({pass: "Y"});
//				    		}
				    	}
					}
				}
			}
		);
		
	},
	
	submit : function(me,formpanel,mainId,records,type,parentStore) {
		var state = "40";
		var expertUserId  = Ext.getCmp(mainId+"expertUserId").getValue();
		var postArray =[];
		for(var i=0;i<records.length;i++){
			var id = records[i].get("id");
			var obj = {
				  id : id,
				  __status : type,
				  state:state,
				  expertUserId:expertUserId
			};
			postArray.push(obj);
		}
		
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/assessment/activity/user/progress/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify(postArray),
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





