package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	//We are making req as static variable because we want to use the same instance of variable across all the test cases.
	//For e.g.if first test is running and req value is initialized,so by using static keyword another instance of variable req won't be initialized at the second run.
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		//PrintStream is used to let us know whether we need to log all the information in file,console or any other place.
		//To create a new file in java at runtime,we use FileOutputStream.
		
		//We are checking if req is null because we want to capture all the logs for all the test cases otherwise it overrides the logs and clear the previous test run logs.
		if(req==null) {
			PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
			req=new RequestSpecBuilder().setBaseUri(globalProperties("baseUrl")).addQueryParam("key", "qaclick123")
					
					//RequestLoggingFilter helps to enable request logging for the RequestSpecBuilder object to log all the information for the request.
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
		
	}
	
	public String globalProperties(String key) throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\globalData.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response,String key) {
		String responseString=response.asString();
		JsonPath js=new JsonPath(responseString);
		return js.get(key).toString();
	}
}
