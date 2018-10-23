Ext.namespace('addorUpdateOffers');
addorUpdateOffers.addorUpdateOffersWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateOffers.addorUpdateOffersWindow.superclass.constructor.call(this);
};

//Ext.extend(addorUpdateOffers.addorUpdateOffersWindow, Ext.Window, {
Ext.extend(addorUpdateOffers.addorUpdateOffersWindow, Ext.Panel, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_Offerswindow";
	    	var type = me.type;
	    	var record = me.record;
	    	var id ="";
	    	if(record!=null){
	    		id= record.get("id");
	    	}
	    	var parentStore = this.parentStore; 
	    	var text = type=="update"?"更新":"添加";
	    	var isAdd =  type=="update"?false:true;
	    	var tabName = this.tabName;
	    	//==============================行政区==============================
	    	var canton_Combo_Store = new Ext.data.Store({
	    		pageSize:0,
	    		proxy: {
			        type: 'ajax',
			        url : appName+ '/admin/canton/queryAll',
			        reader: {
			        	root : "results",
						totalProperty: "totalProperty",
						successProperty:'success'
			        }
			    },
			    autoLoad : true,
			    fields: ['id', 'cantonname']
			});
	    	
	       var cantonCombo = new Ext.form.ComboBox({
	    	   		fieldLabel:'行政区<font color="red">*</font>',
		    	    id:mainId+"cantonid",
		            store : canton_Combo_Store,  
		            valueField : "id",  
		            mode : 'remote',  
		            displayField : "cantonname",  
		            forceSelection : true,  
		            blankText : '请选择',  
		            emptyText : '请选择',  
		            editable : false,  
		            triggerAction : 'all',  
		            allowBlank : false,  
		            hiddenName : "cantonname",  
		            autoShow : true,  
		            selectOnFocus : true,  
		            name : "cantonid",
		            listeners:{
		            	afterrender:function(comb){
		            	},
		            	select:function(combo, record, index){
		            	}
		            }
		        }); 
	       
	   	//================办园性质==============================
	    	var unitType_Combo_Store = new Ext.data.Store({
	    		pageSize:0,
	    		proxy: {
			        type: 'ajax',
			        url : appName+ '/admin/unittype/queryAll',
			        reader: {
			        	root : "results",
						totalProperty: "totalProperty",
						successProperty:'success'
			        }
			    },
			    autoLoad : true,
			    fields: ['id', 'unittypename']
			});
	    	
	       var unitTypeCombo = new Ext.form.ComboBox({
	    	   		fieldLabel:'办园性质<font color="red">*</font>',
		    	    id:mainId+"unittypeid",
		            store : unitType_Combo_Store,  
		            valueField : "id",  
		            mode : 'remote',  
		            displayField : "unittypename",  
		            forceSelection : true,  
		            blankText : '请选择',  
		            emptyText : '请选择',  
		            editable : false,  
		            triggerAction : 'all',  
		            allowBlank : false,  
		            hiddenName : "unittypename",  
		            autoShow : true,  
		            selectOnFocus : true,  
		            name : "unittypeid",
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
			      autoScroll:true,
			      bodyStyle:'margin: 0px auto',
			      defaults:{
		               xtype:"textfield",
		               width:'100%',
		               bodyStyle:'padding:10px 0px 10px 0px'
		            },
				  items : [ {
					  			xtype:'label',
					  			text:''
				  			},cantonCombo,
				  			{
				          		fieldLabel:'单位名称<font color="red">*</font>',
								allowBlank:false,
								name: 'unitname',
								blankText:'必须填写',
								id:mainId+"unitname",
					            maxLength:100  
				  			},unitTypeCombo,
				  			{
				          		fieldLabel:'岗位<font color="red">*</font>',
								allowBlank:false,
								name: 'offertitle',
								blankText:'必须填写',
								id:mainId+"offertitle",
					            maxLength:100  
				  			},
				  			{
				          		fieldLabel:'岗位数量<font color="red">*</font>',
								allowBlank:false,
								name: 'offercount',
								blankText:'必须填写',
								id:mainId+"offercount",
					            maxLength:45  
				  			},
				  			{
				  				width:276,
				  				xtype:'numberfield',
				  				fieldLabel:'岗位数量<font color="red">*</font>',
				  				name:'offercount',
				  				blankText:'必须填写',
				  				id:mainId+"offercount",
				  				minValue:1,
				  				value:1
				  			},
				  			{
				          		fieldLabel:'招聘人员要求<font color="red">*</font>',
								allowBlank:false,
								name: 'content',
								blankText:'必须填写',
								id:mainId+"content",
								xtype:'textarea'
				  			},
				  			{
				          		fieldLabel:'联系人<font color="red">*</font>',
								allowBlank:false,
								name: 'contactperson',
								blankText:'必须填写',
								id:mainId+"contactperson",
					            maxLength:45  
				  			},
				  			{
				          		fieldLabel:'联系地址',
								allowBlank:true,
								name: 'contactposition',
								blankText:'必须填写',
								id:mainId+"contactposition",
					            maxLength:255  
				  			},
				  			{
				          		fieldLabel:'邮编',
								allowBlank:true,
								name: 'zipcode',
								blankText:'必须填写',
								id:mainId+"zipcode",
								maxLength:50  
				  			},
				  			{
				          		fieldLabel:'联系电话<font color="red">*</font>',
								allowBlank:false,
								name: 'contacttel',
								blankText:'必须填写',
								id:mainId+"contacttel",
					            maxLength:255  
				  			}
				  			
				  			]
		});
	     
			
//==============tabs========================================================
//    	var offersTabs = new Ext.TabPanel({
//			enableTabScroll : true,
//			border : false,
//			activeTab : 0,
//			deferredRender:false,
//			items : [{
//				itemId : mainId+"_1",
//				layout : 'fit',
//				title : "基本信息",
//				width : '100%',
//				items : [formpanel ],
//				closable : false
//			},{
//				itemId : mainId+"_2",
//				layout : 'fit',
//				title : "主体内容",
//				width : '100%',
//				items : [ {
//	                xtype: 'ueditor',
//	                fieldLabel: '内  容',
//	                id: mainId+"content",
//	                //不要设置高度，否则滚动条出现后工具栏会消失
//	                width: '100%'
//	            } ],
//				closable : false,
//				listeners:{
//					afterrender:function(){
//						if(record!=null){
//							var content= record.get("content");
//				    		Ext.getCmp(mainId+"content").setValue(content);
//				    	}
//					}
//				}
//			}]
//		});

		
		Ext.apply(this, {
				layout:'fit',
				items : [formpanel],
				autoHeight:true,
				resizable : false,
				constrain:true,
				minimize : function() { 
		            this.hide(); 
		        },
				modal : true,
			    buttonAlign : "center",
			    buttons:[{
				  width:50,
					text : text,
					handler : function(button, event) {
						me.addorUpdateOffers(me,formpanel,mainId,parentStore,id,type,isAdd,tabName);
					}
			    },{
				  width:50,
					text : '取消',
					handler : function(button, event) {
//						me.close();
						tabs.remove(tabName);
					}
			    }],
				listeners:{
					afterRender:function(){
						if(record!=null){
				    		var cantonid= record.get("cantonid");
				    		var unitname= record.get("unitname");
				    		var unittypeid= record.get("unittypeid");
				    		var offertitle= record.get("offertitle");
				    		var offercount= record.get("offercount");
				    		var content= record.get("content");
				    		var contactperson= record.get("contactperson");
				    		var contactposition= record.get("contactposition");
				    		var zipcode= record.get("zipcode");
				    		var contacttel= record.get("contacttel");
				    		
				    		Ext.getCmp(mainId+"cantonid").setValue(cantonid);
				    		Ext.getCmp(mainId+"unittypeid").setValue(unittypeid);
				    		Ext.getCmp(mainId+"unitname").setValue(unitname);
				    		Ext.getCmp(mainId+"offertitle").setValue(offertitle);
				    		Ext.getCmp(mainId+"offercount").setValue(offercount);
				    		Ext.getCmp(mainId+"content").setValue(content);
				    		Ext.getCmp(mainId+"contactperson").setValue(contactperson);
				    		Ext.getCmp(mainId+"contactposition").setValue(contactposition);
				    		Ext.getCmp(mainId+"zipcode").setValue(zipcode);
				    		Ext.getCmp(mainId+"contacttel").setValue(contacttel);
				    	}
					}
				}
			}
		);
		
	},
	
	addorUpdateOffers : function(me,formpanel,mainId,parentStore,id,type,isAdd,tabName) {
		
		var cantonid=Ext.getCmp(mainId+"cantonid").getValue();
		var unitname=Ext.getCmp(mainId+"unitname").getValue();
		var unittypeid=Ext.getCmp(mainId+"unittypeid").getValue();
		var offertitle=Ext.getCmp(mainId+"offertitle").getValue();
		var offercount=Ext.getCmp(mainId+"offercount").getValue();
		var content=Ext.getCmp(mainId+"content").getValue();
		var contactperson=Ext.getCmp(mainId+"contactperson").getValue();
		var contactposition=Ext.getCmp(mainId+"contactposition").getValue();
		var zipcode=Ext.getCmp(mainId+"zipcode").getValue();
		var contacttel=Ext.getCmp(mainId+"contacttel").getValue();
		
		if(cantonid==""){
			Ext.getCmp(mainId+"cantonid").markInvalid("行政区不能为空");
			return;
		}
		if(unitname==""){
			Ext.getCmp(mainId+"unitname").markInvalid("单位名称不能为空！");
			return;
		}
		if(unittypeid==""){
			Ext.getCmp(mainId+"unittypeid").markInvalid("办园性质不能为空！");
			return;
		}
		if(offertitle==""){
			Ext.getCmp(mainId+"offertitle").markInvalid("岗位不能为空！");
			return;
		}
		if(offercount==""){
			Ext.getCmp(mainId+"offercount").markInvalid("岗位数量不能为空！");
			return;
		}
		if(content==""){
			Ext.getCmp(mainId+"content").markInvalid("招聘人员不能为空！");
			return;
		}
		if(contactperson==""){
			Ext.getCmp(mainId+"contactperson").markInvalid("联系人不能为空！");
			return;
		}
		if(contacttel==""){
			Ext.getCmp(mainId+"contacttel").markInvalid("联系电话不能为空！");
			return;
		}
		
	
		
	
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/offers/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  cantonid:cantonid,
                	  unitname:unitname,
                	  unittypeid:unittypeid,
                	  offertitle:offertitle,
                	  offercount : offercount,
                	  content:content,
                	  contactperson:contactperson,
                	  contactposition:contactposition,
                	  zipcode:zipcode,
                	  contacttel:contacttel,
                	  id : id
                  }]),
                  success : function(response, options) {
                	  Ext.getBody().unmask();
                	  var responseArray = Ext.util.JSON.decode(response.responseText);
	                  if (responseArray.success == true) {
	                	    parentStore.reload();
//	                    	me.close();
	                	    tabs.remove(tabName);
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





