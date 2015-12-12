package coord.convert


import groovy.json.JsonOutput


class HomeController {

	def convertService


	def convert() {
		def location = params.location
		def result = convertService.serviceMethod(location)
		def json = new JsonOutput().toJson(result)

		
		render json
	}

	def index() { }
}
