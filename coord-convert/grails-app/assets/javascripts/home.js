//= require jquery
//= require bootstrap


$(document).ready(function() { $("#location").focus(); });

function convert() {
	var location = $("#location").val();
	
	$.ajax({
		data: "location=" + location,
		dataType: "json",
		success: function(data) { console.dir(data);
			$("#format").html(data.format || "");
			$("#dd").html(data.dd || "");
			$("#dms").html(data.dms || "");
			$("#error").html(data.error || "None");
			$("#latitude").html(typeof data.latitude == "number" ?  data.latitude : "");
			$("#longitude").html(typeof data.longitude == "number" ? data.longitude : "");
			$("#mgrs").html(data.mgrs || "");
			$("#raw").html(JSON.stringify(data));
		},
		url: "/coord-convert/home/convert"
	});
}
