(function() {
	'use strict';
	var brandObj = {
		addTitle : "添加品牌",
		editTile : "编辑品牌"
	}

	var brandVm = new Vue({
		el : "#brandApp",
		data : {
			brandList : [],
			brand : {},
			addOrEditBrandTitle : brandObj.addTitle
		},
		methods : {
			openAddBrandModal : function() {
				this.addOrEditBrandTitle = brandObj.addTitle;
				openOrCloseModal("addBrandModal", true);
			},
			openEditBrandModal : function() {
				var rows = getTableSelection("brandTable");
				if (rows.length != 1) {
					showMsgInfo("请选择一条进行编辑", 3);
					return;
				}
				this.brand = rows[0];
				this.addOrEditBrandTitle = brandObj.editTile;
				openOrCloseModal("addBrandModal", true);
			},
			addOrEditBrand : function() {
				var url = "../../brand/addBrand.do"
				if (this.addOrEditBrandTitle != brandObj.addTitle) {
					url = "../../brand/updateBrand.do"
				}
				sendAjax(url, true, this.brand, this.updateTableWhenHandle);
			},
			deleteBrands : function() {
				var ids = getTableSelectionIds("brandTable");
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
				sendAjax("../../brand/deleteBrand.do", true, data,
						this.updateTableWhenDelete);
			},
			updateTableWhenHandle : function(resultModel) {
				if (resultModel.code == 202) {
					refreshTalbe("brandTable");
					openOrCloseModal("addBrandModal", false);
					this.brand = {};
				} else {
					showMsgInfo(resultModel.msg);
				}
			},
			updateTableWhenDelete : function(resultModel) {
				if (resultModel.code == 202) {
					refreshTalbe("brandTable");
				} else {
					showMsgInfo(resultModel.msg);
				}
			}
		},
		created : function() {
		}
	})
	
	function initBrand() {
		initBrandTable();
	}
	

	function initBrandTable() {
		$('#brandTable').bootstrapTable({
			columns : [ {
				field : 'checkbox',
				title : 'checkbox',
				width : "4%",
				align : 'center',
				checkbox : true
			}, {
				field : 'id',
				title : 'ID',
				sortable : true
			}, {
				field : 'name',
				title : '品牌',
				sortable : true
			}, {
				field : 'firstChar',
				title : '首字母',
				sortable : true
			} ],
			url : "../../brand/queryBrand.do",
			pageNumber : 1,
			pageSize : 5,
			pageList : [ 5, 30, 50, 100, 'All' ],
			pagination : true,
			sidePagination : "server",
			toolbar : '#brandTool',
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
		});
	}
	
	initBrand();
})();