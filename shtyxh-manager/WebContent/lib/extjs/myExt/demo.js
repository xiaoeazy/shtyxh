var appName = _basePath;
var tabs;
var _panel2;
var _panel3;
var nonePic = _basePath +"/resources/images/none/none.jpg";

Ext.onReady(function() {
	
	var panel =new QuestionAnalysis.QuestionAnalysisPanel({
		mainId:"mainId",
		sid:15
	});
	
	
	new Ext.Viewport({
		layout : "border",
		height:500,
		items : [ {
			region : 'center',
			layout : 'fit',
			items : panel
		} ]
	});
})

