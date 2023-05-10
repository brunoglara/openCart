package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups = {"Sanity", "Master"})
	public void test_login() throws InterruptedException {

		try {
			logger.info("*** Starting TC_002_LoginTest ***");

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
			loginPage.setEmail(rBundle.getString("email"));
			loginPage.setPassword(rBundle.getString("password"));
			loginPage.clickLogin();
			logger.info("Clicked on Login button");

			// Assertion
//		Thread.sleep(5000);
//		Assert.assertEquals(driver.getTitle(), "My Account");
			logger.info("Validating My Account Page exists");
			Assert.assertTrue(myAccountPage.isMyAccountPageExists(), "Test failed");

		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail(e.getMessage());
		}

		logger.info("*** Finished TC_002_LoginTest ***");
	}
}
