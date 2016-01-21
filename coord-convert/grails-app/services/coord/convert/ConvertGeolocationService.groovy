package coord.convert


import grails.transaction.Transactional
import groovy.json.JsonSlurper


@Transactional
class ConvertGeolocationService {

	def serviceMethod(searchString) {
		def url = "http://o2.ossim.org/o2/twoFishesProxy/?responseIncludes=WKT_GEOMETRY_SIMPLIFIED&maxInterpretations=1&query=${searchString}"
		def text = new URL(url).getText()
		def json = new JsonSlurper().parseText(text)


		if (json.interpretations.size() > 0) {
			def feature = json.interpretations[0].feature

			def latitude = feature.geometry.center.lat as Double
			def longitude = feature.geometry.center.lng as Double
			def name = feature.displayName 	
		

			return [latitude: latitude, longitude: longitude, name: name]
		}
		else { 
			return null 
		}	
	}
}
