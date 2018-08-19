Ext.namespace('addorUpdateMemberUnits');
addorUpdateMemberUnits.addorUpdateMemberUnitsWindowWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	addorUpdateMemberUnits.addorUpdateMemberUnitsWindowWindow.superclass.constructor.call(this);
};

Ext.extend(addorUpdateMemberUnits.addorUpdateMemberUnitsWindowWindow, Ext.Window, {
	
	initUIComponents : function() {
		    var me  = this ; 
		    
		    
	    	var mainId=me.mainId+"_memberUnitswindow";
	    	var type = me.type;
	    	var record = me.record;
	    	var id ="";
	    	if(record!=null){
	    		id= record.get("id");
	    	}
	    	var parentStore = this.parentStore; 
	    	var text = type=="update"?"更新":"添加";
	    	var isAdd =  type=="update"?false:true;
	    	
	    	
	    	//行政区
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
//		            		 typeCombo.setValue(-1);
//		            	     typeCombo.setRawValue("所有");
		            	},
		            	select:function(combo, record, index){
//		            		var combovalue = combo.getValue();
//		            		Ext.apply(other_Combo_Store.baseParams, { repid:combovalue  });
//		            		other_Combo_Store.load();
		            	}
		            }
		        }); 
	    	
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
				          		fieldLabel:'编号<font color="red">*</font>',
								allowBlank:false,
								name: 'memberno',
								blankText:'必须填写',
								id:mainId+"memberno",
					            maxLength:45  
				  			},cantonCombo,{
				          		fieldLabel:'园所名称<font color="red">*</font>',
								allowBlank:false,
								name: 'kindergartenname',
								id:mainId+"kindergartenname",
					            maxLength:200  
				  			},{
				          		fieldLabel:'园所地址<font color="red">*</font>',
								allowBlank:false,
								name: 'kindergartensite',
								id:mainId+"kindergartensite",
					            maxLength:200  
				  			},{
				          		fieldLabel:'编码<font color="red">*</font>',
								allowBlank:false,
								name: 'zipcode',
								id:mainId+"zipcode",
					            maxLength:200  
				  			},{
				          		fieldLabel:'联系电话<font color="red">*</font>',
								allowBlank:false,
								name: 'telphone',
								id:mainId+"telphone",
					            maxLength:200  
				  			},{
				          		fieldLabel:'等级<font color="red">*</font>',
								allowBlank:false,
								name: 'level',
								id:mainId+"level",
					            maxLength:200  
				  			},{
				          		fieldLabel:'性质<font color="red">*</font>',
								allowBlank:false,
								name: 'nature',
								id:mainId+"nature",
					            maxLength:200  
				  			},{
				  			   xtype: "datefield",
				  			   name: "date",
				  			   id:mainId+'admissiontime',
				  			   fieldLabel: "入会时间",
				  			   editable: false,
				  			   emptyText: "--请选择--",
				  			   format: "Y-m-d",//日期的格式
				  			}
				  			],
				  buttonAlign : "center",
				  buttons:[{
					  width:50,
						text : text,
						handler : function(button, event) {
							me.addorUpdateMemberUnitsWindow(me,formpanel,mainId,parentStore,id,type,isAdd);
						}
				  },{
					  width:50,
						text : '取消',
						handler : function(button, event) {
							me.close();
						}
				  }]
		});
	     
			

	        

		
		Ext.apply(this, {
				title : text+'会员',
				layout:'fit',
				items : [formpanel],
				width : 350,
				height : 380,
				xtype : "window",
				resizable : false,
				constrain:true,
				minimize : function() { 
		            this.hide(); 
		        },
				modal : true,
				listeners:{
					show:function(){
						if(record!=null){
							var memberno = record.get("memberno");
							var cantonid=record.get("cantonid");
							var kindergartenname=record.get("kindergartenname");
							var kindergartensite=record.get("kindergartensite");
							var zipcode=record.get("zipcode");
							var telphone=record.get("telphone");
							var level=record.get("level");
							var nature=record.get("nature");
							var admissiontime = record.get("admissiontime");
							var date  =convertDateFromString(admissiontime);
				    		Ext.getCmp(mainId+"memberno").setValue(memberno);
				    		cantonCombo.setValue(cantonid);
				    		Ext.getCmp(mainId+"kindergartenname").setValue(kindergartenname);
				    		Ext.getCmp(mainId+"kindergartensite").setValue(kindergartensite);
				    		Ext.getCmp(mainId+"zipcode").setValue(zipcode);
				    		Ext.getCmp(mainId+"telphone").setValue(telphone);
				    		Ext.getCmp(mainId+"level").setValue(level);
				    		Ext.getCmp(mainId+"nature").setValue(nature);
				    		Ext.getCmp(mainId+"admissiontime").setValue(date);
				    	}
					}
				}
			}
		);
		
	},
	
	
	addorUpdateMemberUnitsWindow : function(me,formpanel,mainId,parentStore,id,type,isAdd) {
		var memberno =Ext.getCmp(mainId+"memberno").getValue().trim();
		var cantonid=Ext.getCmp(mainId+"cantonid").getValue();
		var kindergartenname=Ext.getCmp(mainId+"kindergartenname").getValue().trim();
		var kindergartensite=Ext.getCmp(mainId+"kindergartensite").getValue().trim();
		var zipcode=Ext.getCmp(mainId+"zipcode").getValue().trim();
		var telphone=Ext.getCmp(mainId+"telphone").getValue().trim();
		var level=Ext.getCmp(mainId+"level").getValue().trim();
		var nature=Ext.getCmp(mainId+"nature").getValue().trim();
		var admissiontime = Ext.util.Format.date(Ext.getCmp(mainId+"admissiontime").getValue(), 'Y-m-d');
		
		if(memberno==""){
			Ext.getCmp(mainId+"memberno").markInvalid("编号不能为空！");
			return;
		}
		if(cantonid==null){
			Ext.getCmp(mainId+"cantonid").markInvalid("行政区不能为空！");
			return;
		}
		if(kindergartenname==""){
			Ext.getCmp(mainId+"kindergartenname").markInvalid("园所名称不能为空！");
			return;
		}
		if(kindergartensite==""){
			Ext.getCmp(mainId+"kindergartensite").markInvalid("园所地址不能为空！");
			return;
		}
		if(zipcode==""){
			Ext.getCmp(mainId+"zipcode").markInvalid("邮编不能为空！");
			return;
		}
		if(telphone==""){
			Ext.getCmp(mainId+"telphone").markInvalid("联系电话不能为空！");
			return;
		}
		if(level==""){
			Ext.getCmp(mainId+"level").markInvalid("等级不能为空！");
			return;
		}
		if(nature==""){
			Ext.getCmp(mainId+"nature").markInvalid("性质不能为空！");
			return;
		}
		if(admissiontime==""){
			Ext.getCmp(mainId+"admissiontime").markInvalid("日期不能为空！");
			return;
		}
	
		if( formpanel.getForm().isValid()){
			Ext.getBody().mask("数据提交中，请耐心等候...","x-mask-loading");
			  Ext.Ajax.request({
            	  url : appName + '/admin/member/submit',
                  method : 'post',
                  headers: {'Content-Type':'application/json'},
                  params : JSON.stringify([{
                	  __status : type,
                	  memberno : memberno,
                	  cantonid : cantonid,
                	  kindergartenname : kindergartenname,
                	  kindergartensite : kindergartensite,
                	  zipcode : zipcode,
                	  telphone : telphone,
                	  level : level,
                	  nature : nature,
                	  admissiontime:admissiontime,
                	  id : id
                  }]),
                  success : function(response, options) {
                	  Ext.getBody().unmask();
                	  var responseArray = Ext.util.JSON.decode(response.responseText);
	                  if (responseArray.success == true) {
//	                	    ExtAlert("成功");
	                	    parentStore.reload();
	                    	me.close();
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


function convertDateFromString(dateString) { 
	if (dateString) { 
		var arr1 = dateString.split(" "); 
		var sdate = arr1[0].split('-'); 
		var date = new Date(sdate[0], sdate[1]-1, sdate[2]); 
		return date;
	} 
}





