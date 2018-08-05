Ext.namespace('chartB');
chartB.chartBPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	chartB.chartBPanel.superclass.constructor.call(this);
};


Ext.extend(chartB.chartBPanel, Ext.Panel, {
	initUIComponents : function() {
	    var qid = this.qid ;
	    var mainId = this.mainId;
		

		var myDataStore = new Ext.data.Store({
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/question/answer/queryB',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        },
		        extraParams:{
		        	qid:qid
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'name','value', 'size', 'percent']
		});
		
		
		Ext.apply(this, {
			width:'100%',
			id:mainId,
			border:false,
			items : [{
	            xtype: 'chart',
	            width: '100%',
	            height: 410,
	            padding: '10 0 0 0',
	            style: 'background: #fff',
	            animate: true,
	            shadow: false,
	            store: myDataStore,
	            insetPadding: 40,
	            items: [{
	                type  : 'text',
	                text  : '条线图',
	                font  : '22px Helvetica',
	                width : 100,
	                height: 30,
	                x : 40, //the sprite x position
	                y : 12  //the sprite y position
	            }, {
	                type: 'text',
	                text: '',
	                font: '10px Helvetica',
	                x: 12,
	                y: 380
	            }, {
	                type: 'text',
	                text: '',
	                font: '10px Helvetica',
	                x: 12,
	                y: 390
	            }],
	            axes: [{
	                type: 'Numeric',
	                position: 'bottom',
//	                fields: ['percent'],
	                maximum: 100,
	                label: {
	                    renderer: function(v) { return v + '%'; }
	                },
	                grid: true,
	                minimum: 0
	            }, {
	                type: 'Category',
	                position: 'left',
	                fields: ['value'],
	                grid: true
	            }],
	            series: [{
	                type: 'bar',
	                axis: 'bottom',
	                xField: 'value',
	                yField: 'percent',
	                style: {
	                    opacity: 0.80
	                },
	                highlight: {
	                    fill: '#000',
	                    'stroke-width': 2,
	                    stroke: '#fff'
	                },
	                tips: {
	                    trackMouse: true,
	                    style: 'background: #FFF',
	                    height: 20,
	                    renderer: function(storeItem, item) {
	                        this.setTitle(storeItem.get('value') + ': ' + storeItem.get('percent') + '%'+ " ,共有"+storeItem.get('size') + '票');
	                    }
	                }
	            }]
	        }],
			frame:false,
			resizable : false,
			layout:'fit',
			listeners: {  
	            'render': function () {        
	            }  
	        } 
		 }
		

		);
	}

	
});

