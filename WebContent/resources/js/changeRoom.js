$(document).ready(function(){
		
	
	alert('roo m');
	
	$('#roomType').blur(function(){
		doesFreeRoomExists();
	});
	
	
	function doesFreeRoomExists(){
		
		alert("pozvan ajax");
		var $roomType = $('#roomType').val();
		
		$.ajax({
			type: 'POST',
			url: 'checkIfFreeRoomExists',
			data: JSON.stringify({"roomType": $roomType}),
			accept: 'application/json',
			dataType: 'json',
			success: success,
			error: error,
			contentType: 'application/json',
			dataType: 'json'
		});		
	};
	
	function success(data) {
		if(data.room != null){
			$('#roomStatus').text('A room is available. Click Send request to request transer.');
			$('#submitRoomRequest').prop('disabled', false);
		} else {
			$('#roomStatus').text('No such room available at the moment.');
			$('#submitRoomRequest').prop('disabled', true);
		}
	}
	
	function error(data) {
		alert('Something went wrong');
	}
	
	
});