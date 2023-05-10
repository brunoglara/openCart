package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	//Elements
	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement lblMyAccount;
	
	@FindBy(xpath = "(//a[normalize-space()='Logout'])[2]")
	WebElement lnkLogout;
	
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement btnContinue;
	
	
	public boolean isMyAccountPageExists() {
		try {
			return lblMyAccount.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		lnkLogout.click();
		btnContinue.click();
	}
}
