# coordinate-conversion
This service reads in location text and provides back its equivalent in various geospatial formats. 


## Requirements
* Groovy 2.3.7


## Example Usage
The general usage is as follow: <br>
`./convert.groovy <location input>` <br>
The result is a json as follows: <br>
```javascript
{
	"format": <detected input format> (string),
	"dd": <decimal degrees equivalent> (string),
	"dms": <dms equivalent> (string),
	"latitude": <latitude> (number),
	"longitude": <longitude> (number),
	"mgrs": <mgrs equivalent> (string)
}
```


Command Line: <br>
`./convert.groovy 12.345, -124.567` <br>
Result: <br>
`{"format":"dd","dd":"12.345, -124.567","dms":"122042.0N 1243401.2W","latitude":12.345,"longitude":-124.567,"mgrs":"10PCU2961865202"}`

Command Line: <br>
`./convert.groovy 123456N 1234517W` <br>
Result: <br>
`{"format":"dms","dd":"12.582222, -123.754723","dms":"123456.0N 1234517.0W","latitude":12.582222222255556,"longitude":-123.75472222222223,"mgrs":"10PDU1802091054"}`

Command Line: <br> 
`./convert.groovy 31NAA6602100000` <br>
Result: <br>
`{"format":"mgrs","dd":"0.0, -4.0E-6","dms":"000000.0N 0000000.01W","latitude":0.0,"longitude":-3.976393174348989E-6,"mgrs":"30NZF3397800000"}`


## Verification
A `verify.groovy` script is provided that generates random latitude and longitude values, converts them to DMS and MGRS and then back to DD. By comparing the difference (error) any changes made to the conversion scripts can quickly be evaluated simply by looking at the error values. 
