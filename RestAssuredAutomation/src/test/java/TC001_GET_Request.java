import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {
	
	
	@Test
	void getEmployeeData() {
		
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
		  
		//status code validation
		  int statusCode=response.getStatusCode();     // it captures the value of status code and store it in a variable named statusCode 
		  System.out.println("Status code is: "+statusCode);          
		  Assert.assertEquals(statusCode, 200);     //To validate something this part is necessary
		  
		//status line validation
		  String statusLine=response.getStatusLine();
		  System.out.println("Status line is:"+statusLine);
		  Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");     //(HTTP/1.1 200 OK) This is already written in the test case document. capture it from there.  
		  
		 // Note-: if one of the validations fail then the test will considered as fail.

		  
		  
		  
		  
	} 

}
