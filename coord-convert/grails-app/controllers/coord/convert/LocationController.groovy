package coord.convert


import groovy.json.JsonOutput


class LocationController {

	def convertService
	def grailsApplication


	def convert() {
		grailsApplication.config.app.count++	

		def location = params.find { true }.key
		def result = convertService.serviceMethod(location)
		def json = new JsonOutput().toJson(result)

		
		response.contentType = "application/json"		
		render json
	}

	def index() {}

	def ossim() {
		def sout = new StringBuffer(), serr = new StringBuffer()
		def proc = '../ossim-info'.execute()
		proc.consumeProcessOutput(sout, serr)
		proc.waitForOrKill(1000)
		render "out> $sout err> $serr"
	}
}
