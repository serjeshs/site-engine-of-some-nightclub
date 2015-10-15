$(window).load(
		function() {
			$('#datetimepicker1').datetimepicker(
					{
						lang: 'ru',
						format:'Y-m-d H:i',
						mask:true,
						step: 10,
						validateOnBlur:false
					}
				);
			$('#datetimepicker2').datetimepicker(
					{
						lang: 'ru',
						format:'Y-m-d H:i',
						mask:true,
						step: 10,
						validateOnBlur:false
					}
				);
		});
