package coord.convert


import groovy.json.JsonOutput


class LocationController {

	def convertService
	def grailsApplication


	def convert() {
		grailsApplication.config.app.count++	

		def location = params.location
		def result = convertService.serviceMethod(location)
		def json = new JsonOutput().toJson(result)

		
		response.contentType = "application/json"		
		render json
	}

	def index() {}
}
