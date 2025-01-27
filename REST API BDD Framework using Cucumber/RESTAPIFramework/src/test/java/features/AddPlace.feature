Feature: Add Place API validation

@AddPlace
Scenario Outline: To verify whether the place is added or not
	Given Get the Add Place API payload with "<name>" "<address>" "<language>"
	When user makes a call to "AddPlaceAPI" with http "post" request
	Then user receives a response with status code 200
	And "status" is "OK" in response body
	And verify the place_id which matches with "<name>" by using "GetPlaceAPI"
	
Examples:
			|		name			 |		address		| language	|
			|Mohit House   |Berhampur	    |English	  |
#			|Ramesh House  |Berhampur	    |French	    |

@DeletePlace
Scenario: To verify the Delete Place API
	Given Delete Place Payload
	When user makes a call to "DeletePlaceAPI" with http "post" request
	Then user receives a response with status code 200
	And "status" is "OK" in response body