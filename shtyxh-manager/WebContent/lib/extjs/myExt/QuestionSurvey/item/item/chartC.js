Ext.namespace('chartC');
chartC.chartCPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	chartC.chartCPanel.superclass.constructor.call(this);
};


Ext.extend(chartC.chartCPanel, Ext.Panel, {
	initUIComponents : function() {
		var mainId = this.mainId;
		var qid = this.qid;
		

		var store = new Ext.data.Store({
			pageSize:10,
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/question/answer/queryC',
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
		    fields: ['id', 'content']
		});
		

    
	    var grid = new Ext.grid.GridPanel({
	  	  	frame:true,
	  	  	border:true,	
	  	  	enableHdMenu:false,
	        store: store,
	        loadMask:true,
	        forceFit :true ,  
	        selModel: {
	            type: 'spreadsheet',
	            checkboxSelect :true
	        },
	        bbar: new Ext.PagingToolbar({
			       
			        store: store,
			        displayInfo: true,
			        displayMsg: '当前 {0} 到 {1} 共 {2}',
			        emptyMsg: "没有数据返回"
	        }),
	        columns: [
	            {header: "评论内容",  sortable: true,  dataIndex: 'content',align:'center'}
	            
	        ],
	        width:'100%',
	        autoExpandColumn: 'content',
	        viewConfig:{forceFit: true}
	    });
		
		
		Ext.apply(this, {
			id:mainId,
			width:'100%',
			items : [grid],
			frame:false,
			resizable : false,
			layout:'fit',
			listeners:{
				beforeshow:function(){
				}
			}
		 }
		);
	}

	
});


