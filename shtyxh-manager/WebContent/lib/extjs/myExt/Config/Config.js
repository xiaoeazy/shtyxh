Ext.namespace('Config');
Config.ConfigPanel= function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	Config.ConfigPanel.superclass.constructor.call(this);
};


Ext.extend(Config.ConfigPanel, Ext.Panel, {
	initUIComponents : function() {
	var me = this;
	var mainId = me.mainId;
	var isFirstLoad=true;

	
		var store = new Ext.data.Store({
			proxy: {
		        type: 'ajax',
		        url : appName+ '/admin/config/query',
		        reader: {
		        	root : "results",
					totalProperty: "totalProperty",
					successProperty:'success'
		        }
		    },
		    autoLoad : true,
		    fields: ['id', 'sysname','syskey','sysvalue'],
		    listeners:{
		    	  load: function(records, options, success){  
		    		    var webname = store.getAt(0).get('sysvalue');
						var uploadpath = store.getAt(1).get('sysvalue');
						var copyright = store.getAt(2).get('sysvalue');
						var keyword = store.getAt(3).get('sysvalue');
						var webdesc = store.getAt(4).get('sysvalue');
						var ICPlicense = store.getAt(5).get('sysvalue');
						var webLogo = store.getAt(6).get('sysvalue');
						var webIco = store.getAt(7).get('sysvalue');
						var ICPlicensePath = store.getAt(8).get('sysvalue');
						var webIp = store.getAt(9).get('sysvalue');
						var wx = store.getAt(10).get('sysvalue');
						var wb = store.getAt(11).get('sysvalue');
						Ext.getCmp(mainId+"webname").setValue(webname);
			    		Ext.getCmp(mainId+"uploadpath").setValue(uploadpath);
			    		Ext.getCmp(mainId+"copyright").setValue(copyright);
			    		Ext.getCmp(mainId+"keyword").setValue(keyword);
			    		Ext.getCmp(mainId+"webdesc").setValue(webdesc);
			    		Ext.getCmp(mainId+"ICPlicense").setValue(ICPlicense);
			    		Ext.getCmp(mainId+"webLogo").setValue(webLogo);
			    		Ext.getCmp(mainId+"webIco").setValue(webIco);
			    		Ext.getCmp(mainId+"ICPlicensePath").setValue(ICPlicensePath);
			    		Ext.getCmp(mainId+"webIp").setValue(webIp);
			    		Ext.getCmp(mainId+"wx").setValue(wx);
			    		Ext.getCmp(mainId+"wb").setValue(wb);
			    		if(webLogo!=null&&webLogo!=""){
			    			Ext.getCmp(mainId+"showWebLogoPict").getEl().dom.src=appName+webLogo;
			    		}
			    		if(webIco!=null&&webIco!=""){
			    			Ext.getCmp(mainId+"showWebIcoPict").getEl().dom.src=appName+webIco;
			    		}
			    		if(wx!=null&&wx!=""){
			    			Ext.getCmp(mainId+"showWXPict").getEl().dom.src=appName+wx;
			    		}
			    		if(wb!=null&&wb!=""){
			    			Ext.getCmp(mainId+"showWBPict").getEl().dom.src=appName+wb;
			    		}
		          }  
		    }
		});

    
		var formpanel = new Ext.FormPanel({
	  		  labelAlign: "right",
	  		  labelWidth :100,
	  		  height:500,
	  		  autoHeight:true,
		      frame: true,
		      width: '100%',
		      autoScroll:true,
		      style: 'margin:0 auto',
		      defaults:{
	               xtype:"textfield",
	               width:'500',
	               bodyStyle:'padding:10px 0px 10px 0px'
	            },
			  items : [ {
				  			xtype:'label',
				  			text:''
			  			},
			  			{
			          		fieldLabel:'网站名称',
							allowBlank:false,
							name: 'webname',
							blankText:'必须填写',
							id:mainId+"webname",
				            maxLength:200  
			  			},
			  			{
			          		fieldLabel:'图片上传默认路径',
							allowBlank:false,
							name: 'uploadpath',
							blankText:'必须填写',
							id:mainId+"uploadpath",
				            maxLength:200  
			  			},
			  			{
			          		fieldLabel:'网站版权信息',
							allowBlank:false,
							name: 'copyright',
							blankText:'必须填写',
							id:mainId+"copyright",
				            maxLength:200  
			  			},
			  			{
			          		fieldLabel:'站点默认关键字',
							allowBlank:false,
							name: 'keyword',
							blankText:'必须填写',
							id:mainId+"keyword",
				            maxLength:200  
			  			},
			  			{
			          		fieldLabel:'站点描述',
							allowBlank:false,
							name: 'webdesc',
							blankText:'必须填写',
							id:mainId+"webdesc",
				            maxLength:200  
			  			},
			  			{
			          		fieldLabel:'网站备案号',
							allowBlank:false,
							name: 'ICPlicense',
							blankText:'必须填写',
							id:mainId+"ICPlicense",
				            maxLength:200  
			  			},
			  			{
			          		fieldLabel:'网站备案信息地址',
							allowBlank:false,
							name: 'ICPlicensePath',
							blankText:'必须填写',
							id:mainId+"ICPlicensePath",
				            maxLength:200  
			  			},
			  			{
			          		fieldLabel:'网站地址',
							allowBlank:false,
							name: 'webIp',
							blankText:'网站地址',
							id:mainId+"webIp",
				            maxLength:200  
			  			},
			  			{
							xtype:'container',
							fieldLabel : '上传',
							style:'padding:0 0 5px 0px',
							items:[{
							 	width:100,
							 	height:100,
							 	fieldLabel : '显示',
								xtype : 'box',
								id :mainId+"showWebLogoPict",
								autoEl : {
									width:100,
								 	height:100,
									tag : 'img',
									src :nonePic,
									style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
									complete : 'off'
								}
							},{
								xtype : 'button',
								width : 150,
								style : 'margin-left:10px',
								//name : 'imgupload',
								text : '上传（100*100）',
								handler:function(){
									var win = new uploadImageBase.uploadImageBaseWin({the_hidden_image_url:mainId+"webLogo",the_image_show:mainId+"showWebLogoPict",type:'logo'});
									win.show();
								}
						 	},{ 
				             	id:mainId+"webLogo",
				                xtype:"textfield",  
								fieldLabel : 'webLogo',
				                hidden:true
			                }]
						},{
							xtype:'container',
							fieldLabel : '上传logo',
							style:'padding:20px 0 5px 0px',
							items:[{
							 	width:16,
							 	height:16,
							 	fieldLabel : '显示',
								xtype : 'box',
								id :mainId+"showWebIcoPict",
								autoEl : {
									width:16,
								 	height:16,
									tag : 'img',
									src :nonePic,
									style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
									complete : 'off'
								}
							},{
								xtype : 'button',
								width : 150,
								style : 'margin-left:10px',
								//name : 'imgupload',
								text : '上传ico（16*16）',
								handler:function(){
									var win = new uploadImageBase.uploadImageBaseWin({the_hidden_image_url:mainId+"webIco",the_image_show:mainId+"showWebIcoPict",type:'ico'});
									win.show();
								}
						 	},{ 
				             	id:mainId+"webIco",
				                xtype:"textfield",  
								fieldLabel : 'webIco',
				                hidden:true
			                }]
						},{
							xtype:'container',
							fieldLabel : '上传',
							style:'padding:20px 0 5px 0px',
							items:[{
							 	width:100,
							 	height:100,
							 	fieldLabel : '显示',
								xtype : 'box',
								id :mainId+"showWXPict",
								autoEl : {
									width:100,
								 	height:100,
									tag : 'img',
									src :nonePic,
									style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
									complete : 'off'
								}
							},{
								xtype : 'button',
								width : 200,
								style : 'margin-left:10px',
								//name : 'imgupload',
								text : '上传微信二维码（100*100）',
								handler:function(){
									var win = new uploadImageBase.uploadImageBaseWin({the_hidden_image_url:mainId+"wx",the_image_show:mainId+"showWXPict",type:'wx'});
									win.show();
								}
						 	},{ 
				             	id:mainId+"wx",
				                xtype:"textfield",  
								fieldLabel : 'wx',
				                hidden:true
			                },{
								xtype : 'button',
								width : 150,
								style : 'margin-left:10px',
								text : '撤销图片',
								handler:function(){
									Ext.getCmp(mainId+"wx").setValue("");
						    		Ext.getCmp(mainId+"showWXPict").getEl().dom.src=nonePic;
								}
						 	}]
						},{
							xtype:'container',
							fieldLabel : '上传',
							style:'padding:20px 0 5px 0px',
							items:[{
							 	width:100,
							 	height:100,
							 	fieldLabel : '显示',
								xtype : 'box',
								id :mainId+"showWBPict",
								autoEl : {
									width:100,
								 	height:100,
									tag : 'img',
									src :nonePic,
									style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
									complete : 'off'
								}
							},{
								xtype : 'button',
								width : 200,
								style : 'margin-left:10px',
								//name : 'imgupload',
								text : '上传微博二维码（100*100）',
								handler:function(){
									var win = new uploadImageBase.uploadImageBaseWin({the_hidden_image_url:mainId+"wb",the_image_show:mainId+"showWBPict",type:'wb'});
									win.show();
								}
						 	},{ 
				             	id:mainId+"wb",
				                xtype:"textfield",  
								fieldLabel : 'wb',
				                hidden:true
			                },{
								xtype : 'button',
								width : 150,
								style : 'margin-left:10px',
								text : '撤销图片',
								handler:function(){
									Ext.getCmp(mainId+"wb").setValue("");
						    		Ext.getCmp(mainId+"showWBPict").getEl().dom.src=nonePic;
								}
						 	}]
						}]
	});
   
		
	  
		
		
		Ext.apply(this, {
			width:'100%',
			items : [formpanel],
			frame:false,
			resizable : false,
			layout:'fit',
			buttonAlign:'center',
		    buttons:[{
			  width:50,
				text : '更新',
				handler : function(button, event) {
					me.submit(me,mainId);
				}
		    }],
			listeners:{
				show:function(){
				}
			}
		 });
	},
	submit:function(me,mainId){
		var webname = Ext.getCmp(mainId+"webname").getValue();
		var uploadpath = Ext.getCmp(mainId+"uploadpath").getValue();
		var copyright = Ext.getCmp(mainId+"copyright").getValue();
		var keyword = Ext.getCmp(mainId+"keyword").getValue();
		var webdesc = Ext.getCmp(mainId+"webdesc").getValue();
		var ICPlicense = Ext.getCmp(mainId+"ICPlicense").getValue();
		var webLogo  = Ext.getCmp(mainId+"webLogo").getValue();
		var webIco = Ext.getCmp(mainId+"webIco").getValue();
		var ICPlicensePath = Ext.getCmp(mainId+"ICPlicensePath").getValue();
		var webIp = Ext.getCmp(mainId+"webIp").getValue();
		var wx = Ext.getCmp(mainId+"wx").getValue();
		var wb = Ext.getCmp(mainId+"wb").getValue();
		
		
		
		var jsonArray = [];   
    	var obj1 = me.madeObj(1,webname)
        jsonArray.push(obj1); 
    	var obj2 = me.madeObj(2,uploadpath)
        jsonArray.push(obj2); 
    	var obj3 = me.madeObj(3,copyright)
        jsonArray.push(obj3); 
    	var obj4 = me.madeObj(4,keyword)
        jsonArray.push(obj4); 
    	var obj5 = me.madeObj(5,webdesc)
        jsonArray.push(obj5); 
    	var obj6 = me.madeObj(6,ICPlicense)
        jsonArray.push(obj6); 
    	var obj7 = me.madeObj(7,webLogo)
        jsonArray.push(obj7); 
    	var obj8 = me.madeObj(8,webIco)
        jsonArray.push(obj8); 
    	var obj9 = me.madeObj(9,ICPlicensePath)
        jsonArray.push(obj9); 
    	var obj10 = me.madeObj(10,webIp)
        jsonArray.push(obj10); 
    	var obj11 = me.madeObj(11,wx)
        jsonArray.push(obj11); 
    	var obj12 = me.madeObj(12,wb)
        jsonArray.push(obj12); 
    	
        Ext.Ajax.request({
			url : appName + '/admin/config/submit',
            method : 'post',
            headers: {'Content-Type':'application/json'},
            params : JSON.stringify(jsonArray),
            success : function(response, options) {
          	  Ext.getBody().unmask();
          	  var responseArray = Ext.util.JSON.decode(response.responseText);
                if (responseArray.success == true) {
              	    ExtAlert("成功");
//              	    store.reload();
              	 	store.sync();
                  }else{
                  	ExtError(responseArray.message);
                  }
            },
			failure : function() {
				Ext.getBody().unmask();
				ExtError();
			}
	    });
	},
	
	madeObj:function(id,sysvalue){
		var obj = {
				id : id,
				__status : "update",
				sysvalue: sysvalue
		};
		return obj;
	}
});

