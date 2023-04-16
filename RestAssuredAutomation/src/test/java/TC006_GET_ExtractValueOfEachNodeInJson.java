import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

 public class TC006_GET_ExtractValueOfEachNodeInJson {
		@Test
	void ValueofEachNodeInJSON() {
		
		// Specify base URI
		
		RestAssured.baseURI="https://dummy.restapiexample.com";       // here we write the domain part only
		
		//Request object
		  RequestSpecification httpRequest=RestAssured.given();
		
		//Response object
		  Response response=httpRequest.request(Method.GET,"/api/v1/employees"); // here we write the path and query parameter because we have written domain part already   
		                                                               //// response contains response body ,status code, headers lot of things.
		
		  //print response body in console window
		  
		  String responseBody=response.getBody().asString();   // get the response body from the response and change it from JSON to string format
		  System.out.println("Response Body is:" +responseBody);
		  
		String ename1=response.jsonPath().get("data[0].employee_name");  // Write This (data[0].employee_name) from JSON path finder
		  System.out.println(ename1);  
		  Assert.assertEquals(ename1, "Tiger Nixon");
		  
		  String ename2=response.jsonPath().get("data[1].employee_name"); 
		  System.out.println(ename2);  
		  Assert.assertEquals(ename2, "Garrett Winters");
		  
		  
	} 

}



	  
	  
	  
