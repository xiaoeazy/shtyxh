Ext.namespace('showAnalysisWindow');
showAnalysisWindow.showAnalysisWindowWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	showAnalysisWindow.showAnalysisWindowWindow.superclass.constructor.call(this);
};

Ext.extend(showAnalysisWindow.showAnalysisWindowWindow, Ext.Window, {
	
	initUIComponents : function() {
		var me  = this ; 
		var mainId = this.mainId;   
		var sid = this.sid;
		    
	        
		var panel =new QuestionAnalysis.QuestionAnalysisPanel({
			mainId:mainId,
			sid:sid
		});
		
		
		Ext.apply(this, {
				title : '分析显示',
				layout:'fit',
				items : [panel],
				width : 850,
				height : 600,
				xtype : "window",
				resizable : false,
				constrain:true,
				minimize : function() { 
		            this.hide(); 
		        },
				modal : true,
				listeners:{
					show:function(){
					}
				}
			}
		);
		
	}
	
});





