$("document").ready(function() {

					if ($("div[class='table']").length > 0) {
						$(".vF_detail_content").hide();
						$("div[class='table']").show();
						$("p[class='tc']")
								.append(
										"<span class='displayArti' id='displayGG'>【显示招聘正文】</span><span class='displayArti' id='hideGG'>【显示招聘概要】</span>");
						$("#hideGG").hide()
						$("#displayGG").click(function() {
							$("div[class='table']").hide();
							$(".vF_detail_content").show();
							$("#displayGG").hide();// text("显示公告概要")
							$("#hideGG").show();
						});
						$("#hideGG").click(function() {
							$("div[class='table']").show();
							$(".vF_detail_content").hide();
							$("#hideGG").hide();// text("显示公告概要")
							// $("#displayGG").show();
							$("#displayGG").show();
						});
					}//if

})