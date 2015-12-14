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
				<td>Format:</td>
				<td><div id = "format"></div></td>
			</tr>
			<tr>
				<td>DD:</td>
				<td><div id = "dd"></div></td>
			</tr>
			<tr>
				<td>DMS</td>
				<td><div id = "dms"></div></td>
			</tr>
			<tr>
				<td>Latitude:</td>
				<td><div id = "latitude"></div></td>
			</tr>
			<tr>
				<td>Longitude:</td>
				<td><div id = "longitude"></div></td>
			</tr>
			<tr>
				<td>MGRS:</td>
				<td><div id = "mgrs"></div></td>
			</tr>
			<tr>
				<td>Raw:</td>
				<td><div id = "raw"></div></td>
			</tr>
			<tr>
				<td>Error:</td>
				<td><div id = "error"></div></td>
			</tr>
		</table>
	
		<asset:javascript src = "demo.js"/>
	</body>
</html>
