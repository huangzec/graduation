$(function () {
		$("#tbgrade").change(function() {
			var data = [];
			var series;
			var $that 	= $(this);
			var grade 	= $that.val();
			if("" == grade) {
				return false;
			}
			$.getJSON(
					siteUrl + "user/opentopicinfo/situation.do",
					{grade: grade},
					function(resp) {
						if(false === resp.rs) {
							$("#pie-chart").text("暂无相关数据");
							
							return false;
						}
						if(typeof resp == "undefined" || typeof resp.data == "undefined" || 1 > resp.data.length) {
							$("#pie-chart").text("暂无相关数据");
							
							return;
						}
						var list 	= resp.data;
						series 		= list.length;
						for(var i = 0; i < list.length; i ++) {
							var d = list[i];
							data[i] = {label: d.lable, data: d.data}					
						}
						$.plot($("#pie-chart"), data, 
						{
							colors: ["#F90", "#3C4049", "#666", "#BBB"],
							series: {
								pie: { 
									show: true,
									label: {
										show: false,
										formatter: function(label, series){
											return '<div style="font-size:11px;text-align:center;padding:4px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
										},
										threshold: 0.1
									}
								}
							},
							legend: {
								show: true,
								noColumns: 1, // number of colums in legend table
								labelFormatter: null, // fn: string -> string
								labelBoxBorderColor: "#888", // border color for the little label boxes
								container: null, // container (as jQuery object) to put legend in, null means default on top of graph
								position: "ne", // position of default legend container within plot
								margin: [5, 10], // distance from grid edge to default legend container within plot
								backgroundOpacity: 0 // set to 0 to avoid background
							},
							grid: {
								hoverable: true,
								clickable: true
							}
						});
					}
			);
		});
		$("#grade").change(function() {
			var data = [];
			var series;
			var $that 	= $(this);
			var grade 	= $that.val();
			if("" == grade) {
				return false;
			}
			$.getJSON(
					siteUrl + "user/graduateinfo/situation.do",
					{grade: grade},
					function(resp) {
						if(false === resp.rs) {
							$("#finish-pie-chart").text("暂无相关数据");
							
							return false;
						}
						if(typeof resp == "undefined" || typeof resp.data == "undefined" || 1 > resp.data.length) {
							$("#finish-pie-chart").text("暂无相关数据");
							
							return;
						}
						var list 	= resp.data;
						series 		= list.length;
						for(var i = 0; i < list.length; i ++) {
							var d = list[i];
							data[i] = {label: d.lable, data: d.data}					
						}
						$.plot($("#finish-pie-chart"), data, 
						{
							colors: ["#F90", "#3C4049", "#666", "#BBB"],
							series: {
								pie: { 
									show: true,
									label: {
										show: false,
										formatter: function(label, series){
											return '<div style="font-size:11px;text-align:center;padding:4px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
										},
										threshold: 0.1
									}
								}
							},
							legend: {
								show: true,
								noColumns: 1, // number of colums in legend table
								labelFormatter: null, // fn: string -> string
								labelBoxBorderColor: "#888", // border color for the little label boxes
								container: null, // container (as jQuery object) to put legend in, null means default on top of graph
								position: "ne", // position of default legend container within plot
								margin: [5, 10], // distance from grid edge to default legend container within plot
								backgroundOpacity: 0 // set to 0 to avoid background
							},
							grid: {
								hoverable: true,
								clickable: true
							}
						});
					}
			);
		});
	});