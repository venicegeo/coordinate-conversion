//= require jquery
//= require bootstrap


$(document).ready(function() { $("#location").focus(); });

function convert() {
	var location = $("#location").val();
	
	$.ajax({
		data: "location=" + location,
		dataType: "json",
		success: function(data) {
			$("#format").html(data.format || "");
			$("#dd").html(data.dd || "");
			$("#dms").html(data.dms || "");
			$("#error").html(data.error || "None");
			$("#latitude").html(data.latitude || "");
			$("#longitude").html(data.longitude || "");
			$("#mgrs").html(data.mgrs || "");
			$("#raw").html(JSON.stringify(data));
		},
		url: "/coord-convert/home/convert"
	});
}
