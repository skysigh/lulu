(function() {
	'use strict';
	var commonObj = {
		addTitle : "添加规格",
		editTile : "编辑规格",
		addUrl : "../../specification/addSpecification.do",
		eidtUrl : "../../specification/updateSpecification.do",
		deleteUrl : "../../specification/deleteSpecification.do",
		getAllUrl : "../../specification/getAllSpecification.do",
		getSpeOptionUrl : "../../specification/getSpeOption.do",
		tableId : "specification_table",
		modalId : "specification_addModal",
		tableToolId : "specification_tool",
		queryUrl : "../../specification/querySpecification.do"
	}

	var specificationVm = new Vue({
		el : "#specification_app",
		data : {
			specification : {},
			speOptionList : [],
			specification_addOrEditTitle : commonObj.addTitle
		},
		methods : {
			specification_openAddModal : function() {
				this.clearInfo();
				this.specification_addOrEditTitle = commonObj.addTitle;
				openOrCloseModal(commonObj.modalId, true);
			},
			specification_openEditModal : function() {
				var rows = getTableSelection(commonObj.tableId);
				if (rows.length != 1) {
					showMsgInfo("请选择一条进行编辑", 3);
					return;
				}
				var row = rows[0];
				this.specification = rows[0];
				sendAjax(commonObj.getSpeOptionUrl, true, {
					id : row.id
				}, this.handleWhenGetSpecOption);
			},
			specification_addOrEdit : function() {
				var data = this.getFillInfo();
				console.log(data)
				if (data == null) {
					return;
				}
				var url = commonObj.addUrl;
				if (this.specification_addOrEditTitle != commonObj.addTitle) {
					url = commonObj.eidtUrl;
				}
				sendAjax(url, true, data, this.updateTableWhenHandle);
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
					this.refreshTalbe();
					openOrCloseModal(commonObj.modalId, false);
					this.clearInfo();
				} else {
					showMsgInfo(resultModel.msg);
				}
			},
			updateTableWhenDelete : function(resultModel) {
				if (resultModel.code == 202) {
					this.refreshTalbe();
				} else {
					showMsgInfo(resultModel.msg);
				}
			},
			handleWhenGetSpecOption : function(resultModel) {
				if (resultModel.data) {
					this.speOptionList = resultModel.data;
					console.log(this.speOptionList);
					this.specification_addOrEditTitle = commonObj.editTile;
					openOrCloseModal(commonObj.modalId, true);
				}
			},
			delSpeOption : function(speOption) {
				console.log(speOption);
				var arr = [];
				console.log(this.speOptionList.length);
				for (var i = 0; i < this.speOptionList.length; i++) {
					console.log(this.speOptionList[i] != speOption);
					if (this.speOptionList[i] != speOption) {
						arr.push(this.speOptionList[i]);
					}
				}
				this.speOptionList = arr;
			},
			addNewSpecOption : function() {
				this.speOptionList.push({});
			},
			clearInfo : function() {
				this.specification = {};
				this.speOptionList = [];
			},
			getFillInfo : function() {
				var name = this.specification.specName;
				if (!checkNullOrEmpty(name, "名称不能为空")) {
					return null;
				}
				if (!this.checkSpecOption()) {
					return null;
				}
				var obj = {
					spec : this.specification,
					specOptions : this.speOptionList
				}
				var data = {
					specVoStr : JSON.stringify(obj)
				}
				return data;
			},
			checkSpecOption : function() {
				var arr = this.speOptionList;
				for (var i = 0; i < arr.length; i++) {
					if (!checkNullOrEmpty(arr[i].optionName, "请填写完整")) {
						return false;
					}
				}
				return true;
			},
			refreshTalbe: function() {
				$("#" + commonObj.tableId).bootstrapTable("refresh");
			}
		},
		created : function() {
		}
	})

	function init() {
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
				title : '规格ID',
				sortable : true
			}, {
				field : 'specName',
				title : '规格名称',
				sortable : true
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
			},
		// 是否显示所有的列
		});
	}

	init();
})();