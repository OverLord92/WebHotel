$(document).ready(function(){
		
	$('#idNumber').blur(function(){
		doesUserExists();
	});
	
	
	function doesUserExists(){
		
		var $idNumber = $('#idNumber').val();
		
		$.ajax({
			type: 'POST',
			url: 'checkIfUserExists',
			data: JSON.stringify({"idNumber": $idNumber}),
			accept: 'application/json',
			dataType: 'json',
			success: success,
			error: error,
			contentType: 'application/json',
			dataType: 'json'
		});		
	};
	
	function success(data) {
		if(data.user != null){
			$('#username').val(data.user.username);
			$('#username').prop('disabled', true);
			$('#password').val(data.user.password);
			$('#password').prop('disabled', true);
		} 
	}
	
	function error(data) {
		alert('Something went wrong');
	}
	
	
});