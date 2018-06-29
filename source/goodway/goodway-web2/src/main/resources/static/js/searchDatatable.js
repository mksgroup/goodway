$(document).ready(function(){
	$('input[type=search]').on('keyup'), function(){
		var value = $(this).val().toLowerCase();
		$('#resultTable tbody tr').filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
});

$(document).ready(function(){
	$('input[type=search]').on('keyup'), function(){
		var value = $(this).val().toLowerCase();
		$('#selectTable tbody tr').filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
});