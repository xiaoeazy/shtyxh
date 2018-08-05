Ext.namespace('addorUpdateQuestionSurvey');
addorUpdateQuestionSurvey.addorUpdateQuestionSurveyWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateQuestionSurvey.addorUpdateQuestionSurveyWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateQuestionSurvey.addorUpdateQuestionSurveyWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_QuestionSurveywindow";
	    	var type = me.type;
	    	var record = me.record;
	    	var parentStore = this.parentStore; 
	    	var text = type=="update"?"更新":"添加";
	    	var postUrl = appName+ '/lib/question/question.htm';
		    var url ='<iframe style="overflow:auto;width:100%; height:100%;" src="'+postUrl+'" frameborder="0"></iframe>';
	    	Ext.apply(this, {
				title : text+'问卷调查',
				 html : url,
				width : 1100,
				height : 650,
				xtype : "window",
				resizable : false,
				constrain:true,
				minimize : function() { 
		            this.hide(); 
		        },
				modal : true,
				listeners:{
					'close':function(){
						parentStore.reload();
					}
				}
			}
		);
		
	}
	
});





