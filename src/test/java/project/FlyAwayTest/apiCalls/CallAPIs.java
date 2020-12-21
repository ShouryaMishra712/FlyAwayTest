package project.FlyAwayTest.apiCalls;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;

public class CallAPIs {
	
	public static void main(String[] args)
	{
		CallAPIs apiTest = new CallAPIs();
		String UN = "shm71298@gmail.com";
		String pass = "12345";
		apiTest.loginAPI(UN, pass);
		String Src = "1";
		String Dest = "4";
		System.out.println(apiTest.searchAPI(Src, Dest));
		
		String UN1 = "abcdec1@gmail.com";
		String pswrd1 = "12345";
		String pswrd2 = "12345";
		String name = "abcdec";
		String address = "abcdec";
		String city = "abcdec";
		
		apiTest.signupAPI(UN1, pswrd1, pswrd2, name, address, city);
	}
	
	String BaseURL = "http://localhost:8080/FlyAway";
	RequestSpecification httpRequest;
	
	public String loginAPI(String UN,String pass) 
	{
		RestAssured.baseURI = BaseURL;
		httpRequest = RestAssured.given();
		
		Response resp_Login;
		JsonPath path_Login;
		
		String nav_Login = "/loginaction?email_id="+UN+"&pwd="+pass;
		resp_Login = httpRequest.post(nav_Login);
		path_Login = resp_Login.jsonPath();
		ResponseBody body_Login = resp_Login.getBody();


		boolean contains = body_Login.asString().contains("Login failed");
		
		if(contains)
		{
			return "Login Failed";
		}
		else
		{
			return "Login Passed";
		}

	}


	
	public String searchAPI (String Src, String Dest)
	{
		RestAssured.baseURI = BaseURL;
		httpRequest = RestAssured.given();
		
		Response resp_Search;
		JsonPath path_Search;

		String nav_Search = "/home?source="+Src+"&destination="+Dest;
		resp_Search = httpRequest.get(nav_Search);
		path_Search = resp_Search.jsonPath();
		ResponseBody body_Search = resp_Search.getBody();

		//	  Signup
		boolean contains = body_Search.asString().contains("Book Flight");

		if(contains)
		{
			return "Search Passed";
		}
		else
		{
			return "Search Failed";
		}
		
	}

	public String signupAPI (String UN, String pswrd1, String pswrd2, String name, String address, String city)
	{
		RestAssured.baseURI = BaseURL;
		httpRequest = RestAssured.given();
		
		Response resp_Signup;
		JsonPath path_Signup;

		String nav_Signup = "/signupaction?email_id="+UN+"&pwd="+pswrd1+"&pwd2="+pswrd2+"&name="+name+"&address="+address+"&city="+city;
		resp_Signup = httpRequest.post(nav_Signup);
		path_Signup = resp_Signup.jsonPath();
		ResponseBody body_Signup = resp_Signup.getBody();


		int statusCode = resp_Signup.getStatusCode();

		if(statusCode==302)
		{
			return "Signup Passed";
		}
		else
		{
			return "Signup Failed";
		}
	}
}
