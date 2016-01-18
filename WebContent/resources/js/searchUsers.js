$(document).ready(function(){
	
	$('#search').click(function(){
	
		var $usernameSearch = $('#usernameSearch').val();
		var $idNumberSearch = $('#idNumberSearch').val();
		
		$.ajax({
			type: 'POST',
			url: 'searchUsers',
			data: JSON.stringify({
				username: $usernameSearch,
				idNumber: $idNumberSearch
			}),
			accept: 'application/json',
			dataType: 'json',
			success: success,
			error: error,
			contentType: 'application/json',
			dataTyoe: 'json'
			
		});
	});
	
	function success(data){
		
		$("#filteredUsers").find("tr:gt(0)").remove();
		
		var users = data.users;
		var user;
		for(var i = 0; i < users.length; i++){
			user = users[i];
			$('#filteredUsers').append('<tr>' +
					'<td>' + user.username + '</td>' +
					'<td>' + user.checkInDate+ '</td>' +
					'<td>' + user.idNumber + '</td>' +
					'<td>' + user.name + '</td>' +
					'<td>' + user.lastName + '</td>' +
					'<td>' + user.room.roomNumber + '</td>' +
					'<td>' + user.services.id + '</td>' +
					'</tr>')
		}
	}
	
	function error(data){
		alert('An Error ocurred during the AJAX call.');
	}
});