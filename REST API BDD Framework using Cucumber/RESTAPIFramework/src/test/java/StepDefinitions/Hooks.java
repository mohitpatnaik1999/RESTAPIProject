package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

//This class is generally used to run the test independently without depending on other tests for data.
public class Hooks {
	
	//@Before annotation is used to run the prerequisites before the test case name passed as argument.
	@Before("@DeletePlace")
	public void beforeTest() throws IOException {
		StepDefinitions step=new StepDefinitions();
		if(StepDefinitions.place_id==null) {
			step.get_the_add_place_api_payload_with("Mohit", "India", "English");
			step.user_makes_a_call_to_with_http_request("AddPlaceAPI", "POST");
			step.verify_the_place_id_which_matches_with_by_using("Mohit", "GetPlaceAPI");
		}
	}
}
