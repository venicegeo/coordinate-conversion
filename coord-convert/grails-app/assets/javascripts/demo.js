//= require jquery
//= require bootstrap


$(document).ready(function() { $("#location").focus(); });

function convert() {
	var location = $("#location").val();
	var url = document.origin + "/coord-convert/location/convert?location=" + location;	
	$("#url").html(url);

	$.ajax({
		dataType: "json",
		success: function(data) { $("#result").html("<pre>" + JSON.stringify(data, null, "\t") + "</pre>"); },
		url: url
	});
}
