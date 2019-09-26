function sendAjax(url, schny, data, success) {
	$.ajax({
		url : url,
		type : "post",
		data : data,
		success : function(resultModelStr) {
			var resultModel = JSON.parse(resultModelStr);
			if (resultModel.code == 307) {
				window.location.href = resultModel.url;
				return;
			} else if (resultModel.code == 404) {
				showMsg(2, resultModel.msg);
				return;
			}
			success(resultModel);
		}
	})
}

function checkNullOrEmpty(v, errorMsg) {
	if (!v || "" == v) {
		showMsg(2, errorMsg);
		return false;
	}
	return true;
}

/*
 * type: 1 info 2 error
 */
function showMsg(type, msg) {
	alert(msg);
}
// type 1 success 2 info 3error
function showMsgInfo(msg, type) {
	alert(msg);
}

/*
 * bootstrap table
 */
function setTableData(tableId, data) {
	if (!data) {
		return;
	}
	$('#' + tableId).bootstrapTable('load', data);
}

function getTableSelection(tableId) {
	return $('#' + tableId).bootstrapTable('getSelections')
}

function getTableSelectionIds(tableId) {
	var rows = getTableSelection(tableId);
	var length = rows.length;
	var ids = [];
	for (var i = 0; i < length; i++) {
		ids.push(rows[i].id);
	}
	return ids;
}

function openOrCloseModal(modalId, open) {
	$("#" + modalId).modal("toggle");
}

function toJSONString(obj) {
	return JSON.stringify(obj);
}

function parseJSON(str) {
	return JSON.parse(str);
}

function refreshTalbe(tableId) {
	$("#" + tableId).bootstrapTable("refresh");
}
