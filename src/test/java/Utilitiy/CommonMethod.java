//package Utilitiy;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//
//import com.aventstack.extentreports.GherkinKeyword;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//
//import PageObject_File.BaseClass;
//
//public class CommonMethod {
//	
//	private BaseClass baseobj;
//
//	public CommonMethod(BaseClass baseobj) throws Throwable {
//		try {
//			this.baseobj = baseobj;
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//	public void LaunchDriver() throws Throwable{
//		try {
//		baseobj.driver=driverManager.CreateWebDriver(ReadConfig.get_from_config("Browsername"));
//		}
//		catch(Exception e)
//	{
//			e.printStackTrace();
//	}
//		
//	}
//	public void step_log(String log_msg) {
//		baseobj.scenario.log(log_msg);
//	}
//	
//	public void capture_screenshot() {
//		byte[] screenshot = ((TakesScreenshot) baseobj.driver).getScreenshotAs(OutputType.BYTES);
//		baseobj.scenario.attach(screenshot, "image/png", "screenshot");
//	}
//	public void assertion_method(String step_name, boolean var) throws Throwable {
//		try {
//			
//			String screenshot = ((TakesScreenshot) baseobj.driver).getScreenshotAs(OutputType.BASE64);
//			if(var == true) {
//				baseobj.extent_test_obj.createNode(new GherkinKeyword("Given"), step_name).log(Status.PASS, 
//						"Step_pass", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
//			}
//			else {
//				baseobj.extent_test_obj.createNode(new GherkinKeyword("Given"), step_name).log(Status.FAIL, "Step_fail", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public void assertion_method(String step_name, boolean var, String log_msg) throws Throwable {
//		try {
//			
//			String screenshot = ((TakesScreenshot) baseobj.driver).getScreenshotAs(OutputType.BASE64);
//			if(var == true) {
//				baseobj.extent_test_obj.createNode(new GherkinKeyword("Given"), step_name).log(Status.PASS, 
//						log_msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
//			}
//			else {
//				baseobj.extent_test_obj.createNode(new GherkinKeyword("Given"), step_name).log(Status.FAIL, log_msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//}
