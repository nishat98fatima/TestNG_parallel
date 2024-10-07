package testng_parallel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import PageObject.*;
import Utilitiy.*;
import io.restassured.response.Response;

public class testng_parallel {
	public ArrayList<HashMap<String, String>> list_map = new ArrayList<HashMap<String, String>>();
	

	@BeforeSuite
	public void beforeTest() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		System.out.println("Test Start Time: " + timeStamp);
	}
	
	public void repeated_steps(String scenario_keyword, HashMap<String, String> map) throws Throwable {
		
		ApiValidate api_validate_obj = new ApiValidate();
		Response api_resp = api_validate_obj.ValidateApi(map, scenario_keyword);
		boolean res = api_validate_obj.the_comparison_result_should_be_logged_in_the_report(map, scenario_keyword, api_resp);
		list_map.add(map);
		
	}

	@Test
	public void test1() throws Throwable {
		try {
			System.out.println("Running test1 -> " + Thread.currentThread().getId());
			String scenario_keyword = "TC_01";
			HashMap<String, String> map = new HashMap<>();
			repeated_steps(scenario_keyword, map);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() throws Throwable {
		try {
			System.out.println("Running test2 -> " + Thread.currentThread().getId());
			String scenario_keyword = "TC_02";
			HashMap<String, String> map = new HashMap<>();
			repeated_steps(scenario_keyword, map);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() throws Throwable {
		try {
			System.out.println("Running test3 -> " + Thread.currentThread().getId());
			String scenario_keyword = "TC_04";
			HashMap<String, String> map = new HashMap<>();
			repeated_steps(scenario_keyword, map);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void afterTest() throws Throwable {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		System.out.println("Test End Time: " + timeStamp);
		GetDataFromExcel.write_list_map_to_excel(list_map);
	}
}
