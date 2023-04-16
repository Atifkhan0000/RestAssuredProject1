package datadriventesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class DataDrivenTest_AddnewEmployees {

	@Test(dataProvider="EmpDataProvider")
	void addnewEmployees(String ename,String esalary,String eage) {
		
		// Specify base URI
		
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";       // here we write the domain part only
		
		//Request object
		  RequestSpecification httpRequest=RestAssured.given();
		
		//Request payload sending along with post request
		  JSONObject requestparams= new JSONObject();  //JSONObject supports java.util.Map interface 
		                                               // hence we use put command to add things likewise in collection h.put(key,value)
		  requestparams.put("name",ename);           
		  requestparams.put("salary",esalary);
		  requestparams.put("age",eage);
		  
		  httpRequest.header("Content-Type","application/json"); // to show above data is in JSON format we use header here
		   
		  httpRequest.body(requestparams.toJSONString());   //// to attach above data to the request
		  
		//Response object
		  Response response=httpRequest.request(Method.POST,"/create");
		  
		//print response body in console window
		  
		  String responseBody=response.getBody().asString();   // get the response body from the response and change it from JSON to string format
		  System.out.println("Response Body is:" +responseBody);
		  
		  //Validations
		  Assert.assertEquals(responseBody.contains(ename),true);
		  Assert.assertEquals(responseBody.contains(esalary),true);
		  Assert.assertEquals(responseBody.contains(eage),true);
		  
	}
		  
		
        @DataProvider(name="EmpDataProvider")
		public Object[][] dataprovider(){

			Object[][] credentials = {{"rohit","25000","24"},{"pooja","24000","25"},{"sachin","26000","26"}};
			
			return credentials;
		}

}






/*

public class DataDrivenTest_AddnewEmployees {

	@Test(dataProvider="EmpDataProvider")
	void addnewEmployees(String ename,String esalary,String eage) {
		
		// Specify base URI
		
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";       // here we write the domain part only
		
		//Request object
		  RequestSpecification httpRequest=RestAssured.given();
		
		//Request payload sending along with post request
		  JSONObject requestparams= new JSONObject();  //JSONObject supports java.util.Map interface 
		                                               // hence we use put command to add things likewise in collection h.put(key,value)
		  requestparams.put("name",ename);           
		  requestparams.put("salary",esalary);
		  requestparams.put("age",eage);
		  
		  httpRequest.header("Content-Type","application/json"); // to show above data is in JSON format we use header here
		   
		  httpRequest.body(requestparams.toJSONString());   //// to attach above data to the request
		  
		//Response object
		  Response response=httpRequest.request(Method.POST,"/create");
		  
		//print response body in console window
		  
		  String responseBody=response.getBody().asString();   // get the response body from the response and change it from JSON to string format
		  System.out.println("Response Body is:" +responseBody);
		  
		//Validations
		  Assert.assertEquals(responseBody.contains(ename),true);
		  Assert.assertEquals(responseBody.contains(esalary),true);
		  Assert.assertEquals(responseBody.contains(eage),true); 
		  
	}
		  
        @DataProvider(name="EmpDataProvider")
		public Object[][] dataprovider() throws IOException{
        	// read the data from excel
        	String path=System.getProperty("user.dir")+"/src/test/java/datadriventesting/empdata.xlsx";  // path of desired excel file

        	int rowcount=XLUtils.getRowCount(path, "Sheet1");   // excel mein sheet means page of excel
        	int colcount=XLUtils.getCellCount(path,"Sheet1",1); // 1 means phli row //no of columns to same hi rahenge chahe 2nd row lelo ya 3rd.
        	
			Object[][] credentials = new Object[rowcount][colcount];
			
			for (int i=1; i<=rowcount; i++) {       // i=1 kyuki excel sheet m column data start with 1 index.kyuki 0 index pr header hote hain
				for (int j=0; j<colcount; j++) {    // j=0 kyuki excel sheet m row data start with 0 index 
					credentials[i-1][j]=XLUtils.getCellData(path,"Sheet1", i, j);
				}
				
			}
			
			return credentials;
		}

}            // error aane ki wajah excel file ke data ka format ho skta h baaqi dekh lena 
*/              
