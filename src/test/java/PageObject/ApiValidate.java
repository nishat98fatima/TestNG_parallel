package PageObject;

import java.util.HashMap;

import org.json.JSONObject;

import Utilitiy.GetDataFromExcel;
import Utilitiy.ReadConfig;
import Utilitiy.Rest_Assured;
import Utilitiy.jsonread;
import io.restassured.response.Response;

public class ApiValidate {
	
	
	public Response ValidateApi(HashMap<String, String> map, String scenario_keyword) throws Throwable {
		Response api_response = null;
		try {
			String req_body = jsonread.read_json(scenario_keyword);
			map.put("Expected_Response_Body", req_body);

			api_response = Rest_Assured.postCall(req_body, ReadConfig.get_from_config("post_api_endpoint"));
			map.put("Actual_Response_Code", String.valueOf(api_response.statusCode()));
			System.out.println("Expected_Result" + req_body);

			System.out.println(api_response.statusCode());
			String JSON = api_response.asPrettyString();
			map.put("Actual_Response_Body", JSON);
			System.out.println(JSON);
			if (api_response.statusCode() == Integer
					.parseInt(GetDataFromExcel.Readexcelfile(scenario_keyword, "Expected_Response_Code"))) {
				map.put("Response_Code_Matched", "Yes");
			} else {
				map.put("Response_Code_Matched", "No");

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return api_response;

	}

	public boolean the_comparison_result_should_be_logged_in_the_report(HashMap<String, String> map,
			String scenario_keyword, Response api_response) throws Throwable {
		boolean res = false;

		try {
			String decision = GetDataFromExcel.Readexcelfile(scenario_keyword, "Response_Schema_Validation_required");
			System.out.println(GetDataFromExcel.Readexcelfile(scenario_keyword, "Response_Schema_Validation_required"));
			map.put("scenario_keyword", scenario_keyword);
			if (decision.equalsIgnoreCase("Yes")) {
				

				String req_body = jsonread.read_json(scenario_keyword);
//				Response api_response = Rest_Assured.postCall(req_body,
//						ReadConfig.get_from_config("post_api_endpoint"));
				System.out.println("JSON comparison completed." + req_body.toString() + api_response.asString());
				JSONObject js1 = new JSONObject(req_body);
				JSONObject js2 = new JSONObject(api_response.asString());

				// Log the final result (already logged in @When step)

				if (js1.similar(js2)) {
					map.put("Schema_Matched", "Yes");

					// basobj.scenario.log("JSON comparison completed." + "\n" + js1 + "\n" + js2);
					res = true;
				} else {
					map.put("Schema_Matched", "No");
					// basobj.scenario.log("JSON comparison fail" + "\n" + js1 + "\n" + js2);
				}

			} else {
				// basobj.scenario.log("JSON validation skipped");
				res = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
