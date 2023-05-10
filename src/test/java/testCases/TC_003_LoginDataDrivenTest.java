package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class) // Need to inform data provider class,
																				// because the data provider is in other class
	public void test_loginDDT(String email, String password, String expectResult) {
		try {
			logger.info("*** Starting TC_003_LoginDataDrivenTest ***");

			HomePage homePage = new HomePage(driver);
			LoginPage loginPage = new LoginPage(driver);
			MyAccountPage myAccountPage = new MyAccountPage(driver);

			// Home Page
			homePage.clickMyAccount();
			logger.info("Clicked on My Account link");
			homePage.clickLogin();
			logger.info("Clicked on Login link");

			// Login Page
			logger.info("Providing login data");
			loginPage.setEmail(email);
			loginPage.setPassword(password);
			loginPage.clickLogin();
			logger.info("Clicked on Login button");

			// Assertion
			// Thread.sleep(5000);
			// Assert.assertEquals(driver.getTitle(), "My Account");
			logger.info("Validating My Account Page exists");
			
			String result;
			
			if(myAccountPage.isMyAccountPageExists()) {
				result = "Valid";
				myAccountPage.clickLogout();
			}else {
				result = "Invalid";
			}
			
			assertEquals(result, expectResult);

		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail(e.getMessage());
		}

		logger.info("*** Finished TC_002_LoginTest ***");
	}
}
