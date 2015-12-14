# coordinate-conversion
This service reads in location text and provides back its equivalent in various geospatial formats. 


## Requirements
* Groovy 2.3.7
* Grails 2.5.0


## Example Usage
The general usage is `http://localhost:8080/coord-convert/location/convert?location=<location input>` <br>
The result is a JSON as follows:
```javascript
{
	"error": <any errors encountered> (string),
	"format": <detected input format> (string),
	"dd": <decimal degrees equivalent> (string),
	"dms": <dms equivalent> (string),
	"latitude": <latitude> (number),
	"longitude": <longitude> (number),
	"mgrs": <mgrs equivalent> (string)
}
```


## Live Demo
1. Start the grails application by running `grails run-app`.
2. Go to `http://localhost:8080/coord-convert/location`.
3. The service will automatically evaluate the location input while typing.


## Standalone Service
1. `grails compile`
2. `grails prod build-standalone`
3. `java -jar coord-convert/target/standalone.jar context=coord-convert`
