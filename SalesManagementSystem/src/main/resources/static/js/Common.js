function searchBtnClick() {
	var createDateFrom = document.querySelector('#createDateFrom');
	var createDateTo = document.querySelector('#createDateTo');
	var updateDateFrom = document.querySelector('#updateDateFrom');
	var updateDateTo = document.querySelector('#updateDateTo');
	var message = document.querySelector('.messageDiv');

	if (createDateFrom.value != "" && createDateTo.value != ""
		&& createDateFrom.value > createDateTo.value) {
		message.innerHTML = "登録日の選択日が不正です。";
		message.style.color = "red";
		createDateFrom.focus();
		return false;
	}


	if (updateDateFrom.value != "" && updateDateTo.value != ""
		&& updateDateFrom.value > updateDateTo.value) {
		message.innerHTML = "更新日の選択日が不正です。";
		message.style.color = "red";
		updateDateFrom.focus();
		return false;
	}

	return true;
}

function searchStockBtnClick() {
	var inventoryDateFrom = document.querySelector('#inventoryDateFrom');
	var inventoryDateTo = document.querySelector('#inventoryDateTo');
	var message = document.querySelector('.messageDiv');
	
	if(inventoryDateFrom != "" && inventoryDateTo != "" 
		&& inventoryDateFrom.value > inventoryDateTo.value) {
			message.innerHTML = "棚卸日の選択日が不正です。";
			message.style.color = "red";
			inventoryDateFrom.focus();
			return false;
		}
	
 	return searchBtnClick();
}
