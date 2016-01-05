<!DOCTYPE html>
<html>
	<head>
		<asset:stylesheet src = "demo.css"/>
	</head>
	<body>
		<h1>Coordinate Conversion</h1>
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
	
		<asset:javascript src = "demo.js"/>
	</body>
</html>
