#! /usr/bin/env groovy


import groovy.json.JsonOutput


def location = args.join(" ")
def result = convert(location)
println result

def convert(location) {
	def result = [:]

	def ddRegExp = /(\-?\d{1,2}[.]\d+)[^\d|^-]+(\-?\d{1,3}[.]\d+)/
	def dmsRegExp = /(?i)(\d{2})[^\w]*(\d{2})[^\w]*(\d{2}[.]?\d{0,2})([n|s])[^\w]*(\d{3})[^\w]*(\d{2})[^\w]*(\d{2}[.]?\d{0,2})[^\w]*([e|w])/
	def mgrsRegExp = /(?i)(\d{1,2})([a-z])[^\w]*([a-z])([a-z])[^\w]*(\d{5})[^\w]*(\d{5})/


	// load the various conversion methods
	def convertDdToDms = new GroovyShell().parse(new File("convertDdToDms.groovy").getText())
	def convertDdToMgrs = new GroovyShell().parse(new File("convertDdToMgrs.groovy").getText())
	def convertDmsToDd = new GroovyShell().parse(new File("convertDmsToDd.groovy").getText())
	def convertMgrsToDd = new GroovyShell().parse(new File("convertMgrsToDd.groovy").getText())

	
	// dd
	if (location ==~ ddRegExp) {
		result.put("format", "dd")

		def latitude
		def longitude
		location.find(ddRegExp) { 
			matcher, lat, lon -> 
			latitude = lat as Double
			longitude = lon as Double
		}

		// input error checking
		if (!"${latitude}".isNumber()) { result.put("error", "Please specify latitude as a number.") }
		else if (latitude < -90 || latitude > 90) { result.put("error", "Latitude value must be between -90 and 90.") }
		else if (!"${longitude}".isNumber()) { result.put("error", "Please specify longitude as a number.") }
		else if (longitude < -180 || longitude > 180) { result.put("error", "Longitude value must be between -180 and 180.") }
		else {
			result.put("dd", "${latitude.trunc(6)}, ${longitude.trunc(6)}")

			def dms = "${convertDdToDms.with { method(latitude, "latitude") } } ${convertDdToDms.with { method(longitude, "longitude") } }"
			result.put("dms", dms)

			result.put("latitude", latitude)
			result.put("longitude", longitude)

			def mgrs = convertDdToMgrs.with { method(latitude, longitude) }
			result.put("mgrs", mgrs)
		}
	}


	// dms
	else if (location ==~ dmsRegExp) {
		result.put("format", "dms")

		def dmsLatitude 
		def ldmsLngitude 
		location.find(dmsRegExp) {
			matcher, latDeg, latMin, latSec, latOri, lonDeg, lonMin, lonSec, lonOri ->
			dmsLatitude = [
				degrees: latDeg as Integer,
				minutes: latMin as Integer,
				seconds: latSec as Double,
				orientation: latOri
			]
			dmsLongitude = [
				degrees: lonDeg as Integer,
				minutes: lonMin as Integer,
				seconds: lonSec as Double,
				orientation: lonOri
			]
		}

		// input error checking
		if (dmsLatitude.degrees > 90) { result.put("error", "Latitude degrees must be less than or equal to 90.") }
		else if (dmsLatitude.minutes >= 60) { result.put("error", "Latitude minutes must be less than 60.") }
		else if (dmsLatitude.seconds >= 60) { result.put("error", "Latitude seconds must be less than 60.") } 
		else if (dmsLongitude.degrees > 180) { result.put("error", "Longitude degrees must be less than or equal to 180.") } 
		else if (dmsLongitude.minutes >= 60) { result.put("error", "Longitude minutes must be less than 60.") }
		else if (dmsLongitude.seconds >= 60) { result.put("error", "Longitude seconds must be less than 60.") }
		else {
			def latitude = convertDmsToDd.with { method(dmsLatitude.degrees, dmsLatitude.minutes, dmsLatitude.seconds, dmsLatitude.orientation) }
			def longitude = convertDmsToDd.with { method(dmsLongitude.degrees, dmsLongitude.minutes, dmsLongitude.seconds, dmsLongitude.orientation) }

			result.put("dd", "${latitude.trunc(6)}, ${longitude.trunc(6)}")

			def dms = "${convertDdToDms.with{ method(latitude, "latitude") } } ${convertDdToDms.with { method(longitude, "longitude") } }"
			result.put("dms", dms)

			result.put("latitude", latitude)	
			result.put("longitude", longitude)

			def mgrs = convertDdToMgrs.with { method(latitude, longitude) }
			result.put("mgrs", mgrs)
		}
	}

	
	// mgrs
	else if (location ==~ mgrsRegExp) {
		result.put("format", "mgrs")

		def components
		location.find(mgrsRegExp) {
			matcher, one, two, three, four, five, six ->
			components = [
				one: one as Integer,
				two: two, 
				three: three,
				four: four,
				five: five as Integer,
				six: six as Integer
			]
		}
	
		def latLon = convertMgrsToDd.with { method(components.one, components.two, components.three, components.four, components.five, components.six) }
		if (!latLon.error) {
			def latitude = latLon.latitude
			def longitude = latLon.longitude

			result.put("dd", "${latitude.trunc(6)}, ${longitude.trunc(6)}")
			def dms = "${convertDdToDms.with { method(latitude, "latitude") } } ${convertDdToDms.with { method(longitude, "longitude") } }"
			result.put("dms", dms)

			result.put("latitude", latitude)
			result.put("longitude", longitude)

			def mgrs = convertDdToMgrs.with { method(latitude.trunc(6), longitude.trunc(6)) }
			result.put("mgrs", mgrs)
		}
		else { result.put("error", latLon.error) }
	}
	else { result.put("error", "The location format could not be determined.") }

	def json = JsonOutput.toJson(result)


	return json
}
