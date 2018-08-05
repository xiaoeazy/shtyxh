Ext.namespace('analysisTree');
analysisTree.analysisTreePanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	analysisTree.analysisTreePanel.superclass.constructor.call(this);
};

Ext.extend(analysisTree.analysisTreePanel, Ext.tree.Panel, {
	
	initUIComponents : function() {
		
		var mainId = this.mainId;
		var centerMainId  = this.centerMainId;
		var sid = this.sid;
		var _panel2 =this._panel2;
		var leftTreeSign=mainId+'leftTree_';

		  var model = Ext.define("TreeModel", { // 定义树节点数据模型
              extend : "Ext.data.Model",
              fields : [{name : "id",type : "string"},
                  {name : "text",type : "string"},
                  {name : "iconCls",type : "string"},
                  {name : "leaf",type : "boolean"}]
          });

          var store = Ext.create('Ext.data.TreeStore', {
              model : model,//定义当前store对象的Model数据模型
              autoLoad : true,
              proxy : {
                  type : 'ajax',
                  url : appName+'/admin/questionmainitem/query',//请求
                  reader : {
                      type : 'json',
//                      root : 'data'//数据
                  },
                  extraParams: {
                	  sid : sid 
		         }
              },
              root : {//定义根节点，此配置是必须的
                  text : '管理菜单',
                  id:'-1',
                  expanded : true
              }

          });
	  
	   
	   
		Ext.apply(this, {
			width:400,
			height:500,
			store:store,
			frame:false,
			rootVisible:false,
			resizable : false, 
			layout:'fit',
			region:'west',
			listeners:{
				 'itemclick':function(view,record,item,index,e,eOpts){
//					 alert(record.data.id+":"+record.data.text);
					 var iconCls = record.data.iconCls;
					 var qid = record.data.id;
					 var centerPanel = Ext.getCmp(centerMainId);
					 var newCenterPanel=null;
		    		 if(centerPanel!=null){
		    			 _panel2.remove(centerPanel);
		    			 _panel2.doLayout();
		    			 if(iconCls=='A'){
		    				 newCenterPanel = new chartA.chartAPanel({mainId:centerMainId,qid:qid});
		    			 }
		    			 if(iconCls=='B'){
		    				 newCenterPanel = new chartB.chartBPanel({mainId:centerMainId,qid:qid});
		    			 }
		    			 if(iconCls=='C'){
		    				 newCenterPanel = new chartC.chartCPanel({mainId:centerMainId,qid:qid});
		    			 }
		    			
		    			 _panel2.add(newCenterPanel);
		    			 _panel2.doLayout();
		    		 }
				 }
			}
		 }
		);
	}
});

