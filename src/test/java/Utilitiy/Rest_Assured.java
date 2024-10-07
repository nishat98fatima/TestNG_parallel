package Utilitiy;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Rest_Assured {
	public static Response postCall(String requestbody,String post_api_call)throws Throwable {
		Response response=null;
		System.out.println("check"+
		requestbody);
		try {
			 response = RestAssured
					.given().header("Content-Type","application/json")
					.body(requestbody)
					.when()
					.post(post_api_call)
					.then()
					.extract().response();
					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
