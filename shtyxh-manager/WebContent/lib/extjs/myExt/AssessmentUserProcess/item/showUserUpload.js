Ext.namespace('showUserUpload');
showUserUpload.showUserUploadWindow = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	showUserUpload.showUserUploadWindow.superclass.constructor.call(this);
};

Ext
		.extend(
				showUserUpload.showUserUploadWindow,
				Ext.Window,
				{

					initUIComponents : function() {
						var me = this;

						var mainId = me.mainId + "_showUserUpload";
						var type = me.type;
						var record = me.record;
						var id = record.get("id");
						var uploadUserId = record.get("uploadUserId");
						var parentStore = this.parentStore;

						// ==========================grid====================
						var store = new Ext.data.Store(
								{
									proxy : {
										type : 'ajax',
										url : appName
												+ '/admin/assessment/activity/user/upload/query',
										reader : {
											root : "results",
											totalProperty : "totalProperty",
											successProperty : 'success'
										},
										extraParams : {
											progressId : id,
											uploadUserId : uploadUserId
										}
									},

									autoLoad : true,
									fields : [ 'id', 'fileName' ]
								});

						var grid = new Ext.grid.GridPanel(
								{
									region : 'center',
									frame : true,
									border : true,
									enableHdMenu : false,
									store : store,
									loadMask : true,
									forceFit : true,
									selModel : {
										type : 'spreadsheet',
										checkboxSelect : true
									},
									tbar : [ {
										text : '打包下载',
										handler : function() {
											me.zipdownload(id, uploadUserId);
										}
									} ],
									columns : [
											{
												header : "文件名",
												width : 50,
												sortable : true,
												dataIndex : 'fileName',
												align : 'center'
											},
											{
												header : "下载",
												sortable : true,
												align : 'center',
												dataIndex : 'filePath',
												renderer : function(value,
														record) {
													var name = record.record.data.fileName;
													if (value != null
															&& value != "") {
														return "<input  type=\"button\" onclick=\"download('"
																+ value
																+ "','"
																+ name
																+ "')\" value=\"下载\" />";
													}
												}
											} ],
									width : '100%',
									autoExpandColumn : 'fileName',
									viewConfig : {
										forceFit : true
									}
								});

						// ==============tabs========================================================

						Ext.apply(this, {
							title : '下载上传文件',
							layout : 'fit',
							items : [ grid ],
							width : 400,
							height : 270,
							autoHeight : true,
							xtype : "window",
							resizable : false,
							constrain : true,
							minimize : function() {
								this.hide();
							},
							modal : true,
							buttonAlign : "center",
							buttons : [ {
								width : 50,
								text : '取消',
								handler : function(button, event) {
									me.close();
								}
							} ],
							listeners : {
								show : function() {
									if (record != null) {
									}
								}
							}
						});

					},
					zipdownload : function(progressId, uploadUserId) {
						Ext.Ajax
								.request({
									url : appName
											+ "/admin/assessment/activity/user/upload/loadDownloadZipInfo?progressId="
											+ progressId + "&uploadUserId="
											+ uploadUserId,
									method : 'post',
									headers : {
										'Content-Type' : 'application/json'
									},
									params : '',
									success : function(response, options) {
										Ext.getBody().unmask();
										var responseArray = Ext.util.JSON
												.decode(response.responseText);
										if (responseArray.success == true) {
											var list = responseArray.list;
											var json = JSON.stringify(list);
											downloadZip(json);
											
										} else {
											ExtError(responseArray.message);
										}
									},
									failure : function() {
										Ext.getBody().unmask();
										ExtError();
									}
								});
					}

				});

function download(path, name) {
	var filePath = encodeURI(encodeURI(path));
	var fileName = encodeURI(encodeURI(name));
	window.location = fileAppName + "/fileDownload?filePath=" + filePath
			+ "&fileName=" + fileName;
}

function downloadZip(json){

	// window.location=fileAppName+"/fileDownloadZip?json="+json;
	var URL = fileAppName+ "/fileDownloadZip";
	var temp = document.createElement("form");
	temp.action = URL;
	temp.method = "post";
	temp.style.display = "none";

	var opt = document.createElement("input");
	opt.name = "json";
	opt.value =json;
	temp.appendChild(opt);
	
	document.body.appendChild(temp);
    temp.submit();
}
