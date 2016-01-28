# coordinate-conversion
This service reads in location text and provides back its equivalent in various geospatial formats. 


## Requirements
* Vagrant 1.8.1


## Example Usage
The general usage is `http://localhost:8080/coord-convert/location/convert?<location input>` <br>
The result is a JSON as follows:
```javascript
{
	"error": <any errors encountered> (string),
	"format": <detected input format> (string),
	"dd": <decimal degrees equivalent> (string),
	"dms": <dms equivalent> (string),
	"latitude": <latitude> (number),
	"longitude": <longitude> (number),
	"mgrs": <mgrs equivalent> (string),
	"name": <place name of geo-location, if available> (string)
}
```


## Live Demo
1. `vagrant up`.
2. `vagrant ssh`.
3. `cd sync/coord-convert`. 
4. `grails run-app`.
5. Go to `http://localhost:8080/coord-convert/location`.
6. The service will automatically evaluate the location input while typing.


## Geo-Location Service
This application is coded such that an external "Geo-Coder" can be used in the event that place name interpretation is necessary. Modify the `grails-app-services/coord/convert/ConvertGeolcationService.groovy` file as necessary. Note: This feature is commented out by default.

