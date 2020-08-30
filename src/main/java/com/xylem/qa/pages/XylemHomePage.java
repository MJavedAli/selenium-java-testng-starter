package com.xylem.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.xylem.qa.base.XylemBase;
import com.xylem.qa.util.TestUtil;

public class XylemHomePage extends XylemBase {

	@FindBy(xpath = "//img[@id='all_logos']")
	@CacheLookup
	WebElement allLogo;

	@FindBy(xpath = "//a[@id='xyl-privacy-accept']")
	WebElement btnPrivacyAccept;
	
	@FindBy(xpath = "//a[@id='xyl-cookies-accept']")
	WebElement btnCookieAccept;
	
	@FindBy(xpath = "//div[@id='xyl-consent']")
	WebElement consentBox;

	@FindBy(xpath = "//input[@id='inpage_login_user']")
	WebElement txtUsername;
	
	@FindBy(xpath = "//input[@id='inpage_login_passwd']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//a[@id='inpage_login_submit']")
	WebElement btnLogon;
	
	@FindBy(xpath = "//a[contains(text(),'India')]")
	WebElement lnkIndia;
	
	@FindBy(xpath = "//input[@id='HeaderBtnOpts']")
	WebElement btnHeader;

	@SuppressWarnings("rawtypes")
	Wait wait = new WebDriverWait(driver, 20);

	// Initializing the Page Objects:
	public XylemHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToIndiaHomePage() {
		this.lnkIndia.click();
		this.handlePrivacyPopUp();
	}
	
	@SuppressWarnings("unchecked")
	private void handlePrivacyPopUp() {
		wait.until(ExpectedConditions.visibilityOf(this.btnPrivacyAccept));
		this.btnPrivacyAccept.click();
		this.btnCookieAccept.click();
	}
	
	public void userLogin(String username,String password) {
		TestUtil.waitForPageLoadComplete(driver, 20000);
		this.txtUsername.sendKeys(username);
		this.txtPassword.sendKeys(password);
		this.btnLogon.click();
		TestUtil.waitForPageLoadComplete(driver, 20000);
		
	}
	public boolean isHomePage(){
		testUtil.waitForUrlContains("Xylect.dll", driver, 20000);
		try {
		if(this.allLogo.isDisplayed()) {
			return true;
		}
	}catch(Exception e) {
		}
		return false;
	}

}
