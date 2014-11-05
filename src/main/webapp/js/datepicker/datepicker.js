$(window).load(
		function() {
			$('#datetimepicker').datetimepicker(
				{
					lang: 'ru',
					format:'Y-m-d H:i',
					mask:true,
					step: 10,
					validateOnBlur:false
				}
			);
		});
