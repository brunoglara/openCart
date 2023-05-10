package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegitrationTest extends BaseClass {

	@Test(groups = {"Regression", "Master"})
	public void test_account_registration() {
		
		logger.debug("application logs..........");

		logger.info("*** Starting TC_001_AccountRegistrationTest ***");

		try {
			HomePage homePage = new HomePage(driver);
			AccountRegistrationPage registrationPage = new AccountRegistrationPage(driver);

			homePage.clickMyAccount();
			logger.info("Clicked on My Account link");
			homePage.clickRegister();
			logger.info("Clicked on Register link");

			logger.info("Providing customer data");
			registrationPage.setFirstName(randomString());
			registrationPage.setLastName(randomString());
			registrationPage.setEmail(randomAlphaNumetic() + "@test.com");
			registrationPage.setTelefone(randomNumber());
			registrationPage.setPassword("test123");
			registrationPage.setConfirmPassword("test123");
			registrationPage.checkAgree();
			registrationPage.clickContinue();
			logger.info("Clicked on Continue button");

			logger.info("Validating expected message");
			String confirmationMsg = registrationPage.getConfirmationMsg();

			Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail(e.getMessage());

		}

		logger.info("*** Finished TC_001_AccountRegistrationTest ***");

	}

}
