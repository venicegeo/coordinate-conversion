package coord.convert


import groovy.json.JsonOutput


class LocationController {

	def convertService


	def convert() {
		def location = params.location
		def result = convertService.serviceMethod(location)
		def json = new JsonOutput().toJson(result)

		
		render json
	}

	def index() { render(view: "demo.gsp") }
}
