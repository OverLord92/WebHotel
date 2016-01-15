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
			$('#roomNumber').val(data.room.roomNumber);
//			$('#roomNumber').prop('disabled', true);
			$('#sumbtiRegistration').prop('disabled', false);
		} else {
			$('#roomNumber').val('no room available at the moment');
			$('#sumbtiRegistration').prop('disabled', true);
		}
	}
	
	function error(data) {
		alert('Something went wrong');
	}
	
	
});