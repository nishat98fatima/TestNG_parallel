package Utilitiy;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.And;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestReport {
 public static void main(String args[]) throws Throwable {
	 
	 ExtentReports extent = new ExtentReports();
		String report_path = System.getProperty("user.dir") + ReadConfig.get_from_config("extent_report_path");
		System.out.println("report_path = " + report_path);
		ExtentSparkReporter spark = new ExtentSparkReporter(report_path);
		extent.attachReporter(spark);
		// feature
		ExtentTest feature = extent.createTest(Feature.class, "Refund item");

		// scenario
		ExtentTest scenario = feature.createNode(Scenario.class, "Jeff returns a faulty microwave");

		// steps
		scenario.createNode(Given.class, "Jeff has bought a microwave for $100").pass("pass");
		scenario.createNode(And.class, "he has a receipt").pass("pass");
		scenario.createNode(When.class, "he returns the microwave").pass("pass");
		scenario.createNode(Then.class, "Jeff should be refunded $100").fail("fail");

		extent.flush();
	 
 }
}
