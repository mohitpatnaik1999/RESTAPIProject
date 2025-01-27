package StepDefinitions;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestData;
import resources.Utils;

public class StepDefinitions extends Utils {
	RequestSpecification res;
	Response response;
	TestData testData=new TestData();
	static String place_id;
	
	@Given("Get the Add Place API payload with {string} {string} {string}")
	public void get_the_add_place_api_payload_with(String name, String address, String language) throws IOException {
		res=given().spec(requestSpecification()).body(testData.addPlacePayLoad(name,address,language));
	}
	@When("user makes a call to {string} with http {string} request")
	public void user_makes_a_call_to_with_http_request(String resource, String method) {
		APIResources apiRes=APIResources.valueOf(resource);
		if(method.equalsIgnoreCase("POST"))
			response=res.when().post(apiRes.getAPIResource());
		else if(method.equalsIgnoreCase("GET"))
			response=res.when().get(apiRes.getAPIResource());
	}
	@Then("user receives a response with status code {int}")
	public void user_receives_a_response_with_status_code(Integer int1) {
	   assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} is {string} in response body")
	public void is_in_response_body(String KeyValue, String expectedValue) {
	    assertEquals(getJsonPath(response,KeyValue),expectedValue);
	}
	@Then("verify the place_id which matches with {string} by using {string}")
	public void verify_the_place_id_which_matches_with_by_using(String expectedName, String resource) throws IOException {
		place_id=getJsonPath(response,"place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_makes_a_call_to_with_http_request(resource,"GET");
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
	    res=given().spec(requestSpecification()).body(testData.deletePlacePayload(place_id));
	}
}
