<!DOCTYPE html>
<html>
	<title>PZSVC: Coordinate Conversion - Demo</title>
	<head>
		<asset:stylesheet src = "index.css"/>
	</head>
	<body>
		<h1>PZSVC: Coordinate Conversion - Demo</h1>
		<table id = "table" style = "position: absolute">
			<tr>
				<td>Location:</td>
				<td><input id = "location" onkeyup = convert() type = "text"></td>
			</tr>
			<tr>
				<td>URL:</td>
				<td><div id = "url"></div></td>
			</tr>
			<tr>
				<td>Result:</td>
				<td><div id = "result"></div></td>
			</tr>
		</table>
	
		<asset:javascript src = "index.js"/>	
		<g:javascript>
			$(document).ready(function() { $("#location").focus(); });

			function convert() {
				var location = $("#location").val();
				var url = document.origin + "${request.contextPath}/location/convert?location=" + location;
				$("#url").html(url);

				$.ajax({
					dataType: "json",
					success: function(data) { $("#result").html("<pre>" + JSON.stringify(data, null, "\t") + "</pre>"); },
					url: url
				});
			}
		</g:javascript>
	</body>
</html>
