#! /usr/bin/env groovy


import groovy.json.JsonSlurper


debug = false

def rand = new Random()
for (x in 1..100) {
	// Generate a random latitude
	def latitude = rand.nextInt(1000000) / 1000000 * 80
	if (rand.nextInt() < 0) { latitude *= -1 }
	if (debug) { println "Starting Latitude: ${latitude}" }

	// Generate a random longitude
	def longitude = rand.nextInt(1000000) / 1000000 * 180
	if (rand.nextInt() < 0) { longitude *= -1 }
	if (debug) { println "Starting Longtude: ${longitude}" }


	// dms error
	def dmsError = calculateDmsError(latitude, longitude)
	println "DMS Error: ${dmsError}"	

	// mgrs error
	def mgrsError = calculateMgrsError(latitude, longitude)
	println "MGRS Error: ${mgrsError}"

	println ""
}

def calculateDmsError(latitude, longitude) {
	// convert dd to dms
	if (debug) { println "Converting DD to DMS" }
	def command = "./convert.groovy ${latitude} ${longitude}"
	def json = executeCommand(command)

	// convert dms to dd
	if (debug) { println "Converting DMS to DD" }
	command = "./convert.groovy ${json.dms}"
	json = executeCommand(command)

	def newLatitude = json.latitude
	if (debug) { println "New Latitude: ${newLatitude}" }
	def newLongitude = json.longitude
	if (debug) { println "New Longitude: ${newLongitude}" }

	def latitudeError = Math.abs(newLatitude - latitude)
	if (debug) { println "Latitude Error: ${latitudeError}" }
	def longitudeError = Math.abs(newLongitude - longitude)
	if (debug) { "Longitude Error: ${longitudeError}" }


	return latitudeError + longitudeError
}

def calculateMgrsError(latitude, longitude) {
	// convert dd to mgrs
	if (debug) { println "Converting DD to MGRS" }
	def command = "./convert.groovy ${latitude} ${longitude}"
	def json = executeCommand(command)

	// convert mgrs to dd
	if (debug) { println "Converting MGRS to DD" }
	command = "./convert.groovy ${json.mgrs}"
	json = executeCommand(command)

	def newLatitude = json.latitude
	if (debug) { println "New Latitude: ${newLatitude}" }
	def newLongitude = json.longitude
	if (debug) { println "New Longitude: ${newLongitude}" }

	def latitudeError = Math.abs(newLatitude - latitude)
	if (debug) { println "Latitude Error: ${latitudeError}" }
	def longitudeError = Math.abs(newLongitude - longitude)
	if (debug) { "Longitude Error: ${longitudeError}" }


	return latitudeError + longitudeError
}

def executeCommand(commandString) {
	if (debug) { println commandString }
	def process = commandString.execute()
	process.waitFor()
	def result = process.getText()
	def json = new JsonSlurper().parseText(result)
	if (debug) { println json }


	return json
}
