Ext.namespace('chartEmpty');
chartEmpty.chartEmptyPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	chartEmpty.chartEmptyPanel.superclass.constructor.call(this);
};


Ext.extend(chartEmpty.chartEmptyPanel, Ext.Panel, {
	initUIComponents : function() {
		var mainId = this.mainId;
		
		
		Ext.apply(this, {
			id:mainId,
			frame:false,
			rootVisible:false,
			resizable : false, 
			layout:'fit',
			listeners:{
				 
			}
		 }
		);
	}

	
});


