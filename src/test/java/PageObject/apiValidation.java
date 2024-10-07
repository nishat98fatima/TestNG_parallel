//package PageObject;
//
//import static org.testng.Assert.assertEquals;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Map;
//
//import org.apache.commons.io.FileUtils;
//import org.json.JSONObject;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import Utilitiy.CommonMethod;
//import Utilitiy.GetDataFromExcel;
//import Utilitiy.ReadConfig;
//import Utilitiy.Rest_Assured;
//import Utilitiy.jsonread;
//import io.restassured.response.Response;
//
//public class apiValidation {
//	public CommonMethod cmnobj;
//	private BaseClass basobj;
//	public String req_body;
//	public Response api_response;
//
//	private Map<String, Object> map1;
//	private Map<String, Object> map2;
//
//	public apiValidation(BaseClass basobj) throws Throwable {
//		this.basobj = basobj;
//		//cmnobj = new CommonMethod(basobj);
//	}
//
//	public boolean validateapi() throws Throwable {
//		// return false;
//		boolean res = false;
//		try {
//			String req_body = jsonread.read_json(basobj.scenario_keyword);
//			basobj.map.put("Expected_Response_Body", req_body);
//
//			Response api_response = Rest_Assured.postCall(req_body, ReadConfig.get_from_config("post_api_endpoint"));
//			basobj.map.put("Actual_Response_Code", String.valueOf(api_response.statusCode()));
//			System.out.println("Expected_Result" + req_body);
//
//			System.out.println(api_response.statusCode());
//			String JSON = api_response.asPrettyString();
//			basobj.map.put("Actual_Response_Body", JSON);
//			System.out.println(JSON);
//			if (api_response.statusCode() == Integer
//					.parseInt(GetDataFromExcel.Readexcelfile(basobj.scenario_keyword, "Expected_Response_Code"))) {
//				basobj.map.put("Response_Code_Matched", "Yes");
//
//				cmnobj.step_log("Actual result is " + JSON + api_response.asString());
//				cmnobj.step_log(req_body);
//				res = true;
//			} else {
//				basobj.map.put("Response_Code_Matched", "No");
//
//			}
//		}
//
//		// }
//		/*
//		 * ObjectMapper objectMapper = new ObjectMapper(); map1 =
//		 * objectMapper.readValue(req_body.toString(), Map.class); map2 =
//		 * objectMapper.readValue(api_response.getBody().asString(), Map.class);
//		 * basobj.scenario.log("JSON Object 1: " + map1);
//		 * basobj.scenario.log("JSON Object 2: " + map2);
//		 */
//
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return res;
//
//	}
//
//	/*
//	 * public void i_compare_the_json_objects_field_by_field() { // Iterate through
//	 * the JSON fields in map1 and map2 for (String key : req_body.keySet()) {
//	 * Object value1 = map1.get(key); Object value2 = map2.get(key);
//	 * 
//	 * try { assertEquals(value1, value2); // Assert that values are equal
//	 * basobj.scenario.log("PASS: Field '" + key + "' matches. Value: " + value1); }
//	 * catch (AssertionError e) { // Log mismatch to the Cucumber report
//	 * basobj.scenario.log("FAIL: Field '" + key + "' mismatch. Value 1: " + value1
//	 * + ", Value 2: " + value2); } } }
//	 */
//
////	public boolean the_comparison_result_should_be_logged_in_the_report() throws Throwable {
////		boolean res = false;
////
////		try {
////			String decision=GetDataFromExcel.Readexcelfile(basobj.scenario_keyword, "Response_Schema_Validation_required");
////System.out.println(GetDataFromExcel.Readexcelfile(basobj.scenario_keyword, "Response_Schema_Validation_required"));
////			if (decision.equalsIgnoreCase("Yes")) {
////				
////				String req_body = jsonread.read_json(basobj.scenario_keyword);
////				Response api_response = Rest_Assured.postCall(req_body,
////						ReadConfig.get_from_config("post_api_endpoint"));
////				System.out.println("JSON comparison completed." + req_body.toString() + api_response.asString());
////				JSONObject js1 = new JSONObject(req_body);
////				JSONObject js2 = new JSONObject(api_response.asString());
////
////				// Log the final result (already logged in @When step)
////
////				if (js1.similar(js2)) {
////					//basobj.map.put("Schema_Matched", "Yes");
////
////					//basobj.scenario.log("JSON comparison completed." + "\n" + js1 + "\n" + js2);
////					res = true;
////				} else {
////					//basobj.map.put("Schema_Matched", "No");
////					//basobj.scenario.log("JSON comparison fail" + "\n" + js1 + "\n" + js2);
////				}
////
////			} else {
////				//basobj.scenario.log("JSON validation skipped");
////				res = true;
////			}
////
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		return res;
////	}
//
//}