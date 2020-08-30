package com.xylem.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.xylem.qa.base.XylemBase;

public class XylemProjectPage extends XylemBase {

	@FindBy(xpath = "//input[@id='HeaderBtnOpts']")
	WebElement btnHeader;

	@FindBy(xpath = "//span[@class='HiName_text']")
	WebElement lblLoggedInUser;
	
	@FindBy(xpath = "//input[@id='quicksearch_inpproduct']")
	WebElement txtModelSearch;
	
	@FindBy(xpath = "//span[@class='wichtig']")
	WebElement lblProductCount;

	@FindBy(xpath = "//select[@id='TP__LGG']")
	WebElement listLanguage;
	
	@FindBy(xpath = "//p[contains(text(),'Preferences')]")
	WebElement OptPreference;
	
	@SuppressWarnings("rawtypes")
	Wait wait = new WebDriverWait(driver, 20);


	// Initializing the Page Objects:
	public XylemProjectPage() {
		PageFactory.initElements(driver, this);

	}
	
	
	@SuppressWarnings("unchecked")
	public String getLoggerInUser(){
		wait.until(ExpectedConditions.visibilityOf(this.lblLoggedInUser));
		String lblUser=this.lblLoggedInUser.getText();	
		return lblUser;	
	}


	@SuppressWarnings("unchecked")
	public boolean isProjectPage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(this.btnHeader));
		    return true;
		    } catch (Exception e) {
		      System.out.println("User is not at Project Page");
		      return false;
		    }
		  }


	@SuppressWarnings("unchecked")
	public String getPumpModelQuantity(String tag,String series) {
		wait.until(ExpectedConditions.visibilityOf(this.txtModelSearch));
		this.txtModelSearch.sendKeys(tag);
		driver.findElement(By.xpath("//a[contains(text(),'"+series+"')]")).click();
		wait.until(ExpectedConditions.visibilityOf(this.lblProductCount));
		String productdetails=this.lblProductCount.getText();
		String arrProd[]=productdetails.split(" ");
		return arrProd[0];
				
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getLanguageList() {
		this.btnHeader.click();
		this.OptPreference.click();
        Select select =new Select(this.listLanguage);
        List<WebElement> options = select.getOptions();
        List<String> arrLangList=new ArrayList();
        for(WebElement item:options) 
        {
        	arrLangList.add(item.getAttribute("value"));
        }
		return arrLangList;
	}

}
