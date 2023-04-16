import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_Request_Authorization {
@Test
void authorization() {
		
		// Specify base URI
		
		RestAssured.baseURI="https://postman-echo.com/basic-auth";       // here we write the domain part only
		
		// basic authentication
		
		PreemptiveBasicAuthScheme authscheme=new PreemptiveBasicAuthScheme();
		authscheme.setUserName("postman");
		authscheme.setPassword("password");
		
		RestAssured.authentication=authscheme;   // assign or add the object(authscheme) to the RestAssured.authentication 
		
		
		//Request object
		  RequestSpecification httpRequest=RestAssured.given();
		
		//Response object
		  Response response=httpRequest.request(Method.GET,"/"); // because we don't have any path or query parameter here hence we write only /
		                                                              
		
		  //print response body in console window
		  
		  String responseBody=response.getBody().asString();  
		  System.out.println("Response Body is:" +responseBody);
		  
		//status code validation
		  int statusCode=response.getStatusCode();     
		  System.out.println("Status code is: "+statusCode);          
		  Assert.assertEquals(statusCode, 200);     
		  
		
   } 

}

	
	
	
	

