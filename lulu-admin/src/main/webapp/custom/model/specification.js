(function() {
	'use strict';
	var commonObj = {
		addTitle : "添加规格",
		editTile : "编辑规格",
		addUrl: "../../specification/addSpecification.do",
		eidtUrl: "../../specification/updateSpecification.do",
		deleteUrl: "../../specification/deleteSpecification.do",
		getAllUrl: "../../specification/getAllSpecification.do",
		tableId: "specification_table",
		modalId: "specification_addModal",
		tableToolId: "specification_tool"
	}

	var specificationVm = new Vue({
		el : "#specification_app",
		data : {
			specification : {},
			specification_addOrEditTitle : commonObj.addTitle
		},
		methods : {
			specification_openAddModal : function() {
				this.specification_addOrEditTitle = commonObj.addTitle;
				openOrCloseModal(commonObj.modalId, true);
			},
			specification_openEditModal : function() {
				var rows = getTableSelection(commonObj.tableId);
				if (rows.length != 1) {
					showMsgInfo("请选择一条进行编辑", 3);
					return;
				}
				this.specification = rows[0];
				this.specification_addOrEditTitle = commonObj.editTile;
				openOrCloseModal(commonObj.modalId, true);
			},
			specification_addOrEdit : function() {
				var url = commonObj.addUrl;
				if (this.specification_addOrEditTitle != commonObj.addTitle) {
					url = commonObj.eidtUrl;
				}
				sendAjax(url, true, this.specification, this.updateTableWhenHandle);
			},
			specification_delete : function() {
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
					getAll();
					openOrCloseModal(commonObj.modalId, false);
					this.specification = {};
				} else {
					showMsgInfo(resultModel.msg);
				}
			},
			updateTableWhenDelete : function(resultModel) {
				if (resultModel.code == 202) {
					getAll();
				} else {
					showMsgInfo(resultModel.msg);
				}
			}
		},
		created : function() {
		}
	})
	
	function init() {
		initTable();
		getAll();
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
				title : '规格ID'
			}, {
				field : 'specName',
				title : '规格名称'
			}],
			pagination : true,
			sidePagination : "client",
			toolbar : '#' + commonObj.tableToolId,
			toolbarAlign : 'left',
			striped : !0,
			uniqueId : "id",
			showRefresh : false,
			sortOrder : "asc",
			search : true,
			cardView : false,// 是否显示详细视图
			showColumns : true
		// 是否显示所有的列
		});
	}

	function getAll() {
		sendAjax(commonObj.getAllUrl, true, {}, setTableList)
	}

	function setTableList(resultModel) {
		if (resultModel.data) {
			setTableData(commonObj.tableId, resultModel.data);
		}
	}
	
	init();
})();