Ext.namespace('showUrlWindow');
showUrlWindow.showUrlWindowWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	showUrlWindow.showUrlWindowWindow.superclass.constructor.call(this);
};

Ext.extend(showUrlWindow.showUrlWindowWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
	    	var mainId=me.mainId+"_showUrlWindow";
	    	var id = me.id;
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
				  				xtype:'textarea',
				          		fieldLabel:'地址名称',
								id:mainId+"url"
				  			}
				  			],
				  buttonAlign : "center",
				  buttons:[{
					  width:50,
						text : '取消',
						handler : function(button, event) {
							me.close();
						}
				  }]
		});
	     
			

	        

		
		Ext.apply(this, {
				title : '显示地址',
				layout:'fit',
				items : [formpanel],
				width : 350,
				height : 180,
				xtype : "window",
				resizable : false,
				constrain:true,
				minimize : function() { 
		            this.hide(); 
		        },
				modal : true,
				listeners:{
					show:function(){
						if(id!=null){
							 var thisDLoc   =   window.document.location.href;
							 var index = thisDLoc.indexOf(appName);
							 var path = thisDLoc.substring(0,index);
				    		Ext.getCmp(mainId+"url").setValue(path+appName+"/index/questionsurvey/query?id="+id);
				    	}
					}
				}
			}
		);
		
	}
});





