package Utilitiy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class driverManager {
	
	public static WebDriver CreateWebDriver(String BrowserName )throws Throwable{
		WebDriver driver=null;
		try {
		switch(BrowserName.toLowerCase()) {
		case "chrome":
			//System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
			driver = new ChromeDriver();
			break;
		case "firefox":
			//System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
			driver = new FirefoxDriver();
			break;
		case "edge":
			//System.setProperty("webdriver.edge.driver", "path/to/edgedriver");
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid browser name: " + BrowserName);
		}
		driver.manage().window().maximize();
		

	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return driver;
}

public static void  closeDriver(WebDriver driver_close) throws Throwable {

	try {
		driver_close.quit();
	} catch (Exception e) {

		e.printStackTrace();
	}
}
}






