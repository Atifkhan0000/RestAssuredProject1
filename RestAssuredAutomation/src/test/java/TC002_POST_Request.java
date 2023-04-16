import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {

	
	@Test
	void CreateNEWRecord() {
		
		// Specify base URI
		
				RestAssured.baseURI="https://dummy.restapiexample.com";       // here we write the domain part only
				
				//Request object
				  RequestSpecification httpRequest=RestAssured.given();
				
				//Request payload sending along with post request
				  JSONObject requestparams= new JSONObject();  //JSONObject supports java.util.Map interface 
				                                               // hence we use put command to add things likewise in collection h.put(key,value)
				  requestparams.put("name","test");           
				  requestparams.put("salary","123");
				  requestparams.put("age","23");
				  
				  httpRequest.header("Content-Type","application/json"); // to show above data is in JSON format we use header here
				   
				  httpRequest.body(requestparams.toJSONString());   //// to attach above data to the request
				  
				//Response object
				  Response response=httpRequest.request(Method.POST,"/api/v1/create");
				  
				//print response body in console window
				  
				  String responseBody=response.getBody().asString();   // get the response body from the response and change it from JSON to string format
				  System.out.println("Response Body is:" +responseBody);
				  
				//status code validation
				  int statusCode=response.getStatusCode();  
				  System.out.println("Status code is: "+statusCode);
				  Assert.assertEquals(statusCode, 200);     //To validate something this part is necessary
				  
				//validation of status value from response body
				  
				String value=response.jsonPath().get("status");   // it captures the value of status and store it in a variable named value 
				System.out.println("Status value is: "+value);
				Assert.assertEquals(value,"success");               // success is the value of status
				  
	}
	
}
