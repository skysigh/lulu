(function() {
	'use strict';
	var commonObj = {
		addTitle : "添加模板",
		editTile : "编辑模板",
		addUrl : "../../typeTemplate/addTypeTemplate.do",
		eidtUrl : "../../typeTemplate/updateTypeTemplate.do",
		deleteUrl : "../../typeTemplate/deleteTypeTemplate.do",
		getAllUrl : "../../typeTemplate/getAllTypeTemplate.do",
		tableId : "typeTemplate_table",
		modalId : "typeTemplate_addModal",
		tableToolId : "typeTemplate_tool",
		brandSelUrl : "../../brand/getAllBrand.do",
		specSelUrl : "../../specification/getAllSpecification.do",
		queryUrl: "../../typeTemplate/queryTypeTemplate.do"
	}

	var typeTemplateVm = new Vue({
		el : "#typeTemplate_app",
		data : {
			customSpecList : [],
			typeTemplate : {},
			brandList : [],
			specList : [],
			brandSelVal : [],
			specSelVal : [],
			typeTemplate_addOrEditTitle : commonObj.addTitle
		},
		methods : {
			typeTemplate_openAddModal : function() {
				this.typeTemplate_addOrEditTitle = commonObj.addTitle;
				openOrCloseModal(commonObj.modalId, true);
			},
			typeTemplate_openEditModal : function() {
				var rows = getTableSelection(commonObj.tableId);
				if (rows.length != 1) {
					showMsgInfo("请选择一条进行编辑", 3);
					return;
				}
				this.typeTemplate = rows[0];

				var specIdsStr = this.typeTemplate.specIds;
				var specIdArr = parseJSON(specIdsStr);
				var specSelArr = [];
				for (var i = 0; i < specIdArr.length; i++) {
					specSelArr.push(toJSONString(specIdArr[i]));
				}

				var brandIdsStr = this.typeTemplate.brandIds;
				var brandIdArr = parseJSON(brandIdsStr);
				var brandSelArr = [];
				for (var i = 0; i < brandIdArr.length; i++) {
					brandSelArr.push(toJSONString(brandIdArr[i]));
				}
				$('#brandSel').selectpicker('val', brandSelArr);
				$('#specSel').selectpicker('val', specSelArr);

				this.typeTemplate_addOrEditTitle = commonObj.editTile;
				openOrCloseModal(commonObj.modalId, true);
			},
			typeTemplate_addOrEdit : function() {

				var name = this.typeTemplate.name;
				var brandArr = $("#brandSel").val();
				var brands = [];
				for (var i = 0; i < brandArr.length; i++) {
					var brandStr = brandArr[i];
					var brand = JSON.parse(brandStr);
					brands.push(brand);
				}

				var specArr = $("#specSel").val()
				var specs = [];

				for (var i = 0; i < specArr.length; i++) {
					var specStr = specArr[i];
					var spec = parseJSON(specStr);
					specs.push(spec);
				}
				var customSpecs = this.customSpecList;
				var addObj = {
					name : name,
					brandIds : toJSONString(brands),
					specIds : toJSONString(specs),
					customAttributeItems : toJSONString(customSpecs)
				}
				var url = commonObj.addUrl;
				if (this.typeTemplate_addOrEditTitle != commonObj.addTitle) {
					url = commonObj.eidtUrl;
					addObj.id = this.typeTemplate.id;
				}
				var data = {
					typeTemplateStr : toJSONString(addObj)
				}
				sendAjax(url, true, data, this.updateTableWhenHandle);
			},
			typeTemplate_delete : function() {
				var ids = getTableSelectionIds(commonObj.tableId);
				if (ids.length == 0) {
					showMsgInfo("请至少选择一条数据", 3);
					return;
				}
				if (!confirm("确定要删除吗?")) {
					return;
				}
				var data = {
					idsStr : ids.join(",")
				}
				sendAjax(commonObj.deleteUrl, true, data,
						this.updateTableWhenDelete);
			},
			updateTableWhenHandle : function(resultModel) {
				if (resultModel.code == 202) {
					refreshTable(commonObj.tableId);
					openOrCloseModal(commonObj.modalId, false);
					this.typeTemplate = {};
				} else {
					showMsgInfo(resultModel.msg);
				}
			},
			updateTableWhenDelete : function(resultModel) {
				if (resultModel.code == 202) {
					refreshTable(commonObj.tableId);
				} else {
					showMsgInfo(resultModel.msg);
				}
			},
			setBrandSel : function(resultModel) {
				if (resultModel.code == 202) {
					this.brandList = [];
					for (var i = 0; i < resultModel.data.length; i++) {
						var obj = resultModel.data[i];
						var sel = {
							id : obj.id,
							text : obj.name
						}
						this.brandList.push(sel);
					}
				}
			},
			setSpecSel : function(resultModel) {
				if (resultModel.code == 202) {
					this.specList = [];
					for (var i = 0; i < resultModel.data.length; i++) {
						var obj = resultModel.data[i];
						var sel = {
							id : obj.id,
							text : obj.specName
						}
						this.specList.push(sel);
					}
				}
			},
			toJsonString : function(d) {
				return JSON.stringify(d);
			},
			deleteCustomSpec : function(d) {
				var newData = [];
				for (var i = 0; i < this.customSpecList.length; i++) {
					if (d != this.customSpecList[i]) {
						newData.push(this.customSpecList[i]);
					}
				}
				this.customSpecList = newData;
			},
			addCustomSpec : function() {
				this.customSpecList.push({});
			}
		},
		created : function() {
		}
	})

	function getAllBrand() {
		sendAjax(commonObj.brandSelUrl, true, {}, typeTemplateVm.setBrandSel)
	}

	function getAllSpec() {
		sendAjax(commonObj.specSelUrl, true, {}, typeTemplateVm.setSpecSel)
	}

	function init() {
		getAllBrand();
		getAllSpec();
		initTable();
	}

	function initTable() {
		$('#' + commonObj.tableId).bootstrapTable({
			columns : [ {
				field : 'checkbox',
				title : 'checkbox',
				width : "4%",
				align : 'center',
				checkbox : true
			}, {
				field : 'id',
				title : '模板ID',
				sortable : true
			}, {
				field : 'name',
				title : '模板名称',
				sortable : true
			}, {
				field : 'brandIds',
				title : '关联品牌',
				formatter : nameFormatter
			}, {
				field : 'specIds',
				title : '关联规格',
				formatter : nameFormatter
			}, {
				field : 'customAttributeItems',
				title : '扩展属性',
				formatter : nameFormatter
			} ],
			url : commonObj.queryUrl,
			pageNumber : 1,
			pageSize : 5,
			pageList : [ 5, 30, 50, 100, 'All' ],
			pagination : true,
			sidePagination : "server",
			toolbar : '#' + commonObj.tableToolId,
			toolbarAlign : 'left',
			striped : !0,
			uniqueId : "id",
			showRefresh : false,
			sortOrder : "asc",
			search : true,
			cardView : false,// 是否显示详细视图
			showColumns : true,
			queryParams : function(params) {
				return params;
			},
			responseHandler : function(res) {
				var data = res.data.data;
				var allSize = res.data.allSize;
				return {
					total : allSize,
					rows : data
				};
			}
		// 是否显示所有的列
		});
	}

	function nameFormatter(value, row, index, field) {
		var aa = JSON.parse(value);
		var text = [];
		for (var i = 0; i < aa.length; i++) {
			text.push(aa[i].text);
		}
		if (text.length == 0) {
			return "-";
		}
		return text.join(",");
	}

	init();
	/*
	 * $(function() { $('select').selectpicker(); });
	 */
})();