package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {
	
	public AddPlace addPlacePayLoad(String name, String address, String language) {
		AddPlace addPlace =new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("https://rahulshettyacademy.com");
		addPlace.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		addPlace.setTypes(myList);
		Location loc =new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);

		addPlace.setLocation(loc);
		return addPlace;
	}
	
	public String deletePlacePayload(String placeId) {
		String request="{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
		return request;
	}
}
