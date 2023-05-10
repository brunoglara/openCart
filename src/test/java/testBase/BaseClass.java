package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;

	public Logger logger;

	// Import to use properties
	public static ResourceBundle rBundle;

	@BeforeClass(groups = {"Master", "Regression", "Sanity"})
	@Parameters("browser")
	public void setup(String browser) {

		logger = LogManager.getLogger(this.getClass());

		rBundle = ResourceBundle.getBundle("config"); // Load config.properties file

		// *** Remove automation mode on browser ***//
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

//		driver = WebDriverManager.chromedriver().capabilities(options).create();

		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}

		if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}

		if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}

	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//		driver.get("https://demo.opencart.com/");
		driver.get(rBundle.getString("appURL"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"Master", "Regression", "Sanity"})
	public void tearDown() {
		driver.quit();
	}
	
	

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		String generateNumberString = RandomStringUtils.randomNumeric(10);
		return generateNumberString;
	}

	public String randomAlphaNumetic() {
		String generateAlphaNumericString = RandomStringUtils.randomAlphanumeric(10);
		return generateAlphaNumericString;
	}

	public String captureScreen(String testName) throws IOException {
		String timeStamp = new SimpleDateFormat("ddMMyyyhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(sourceFile, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}

		return destination;
	}

}
