package com.xylem.qa.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.xylem.qa.base.XylemBase;
import com.xylem.qa.pages.XylemHomePage;
import com.xylem.qa.pages.XylemProjectPage;

public class XylemSmokeTest extends XylemBase{
	XylemHomePage homePage;
	XylemProjectPage projectPage;
	
	public XylemSmokeTest(){
		super();
	}
	
	@BeforeTest
	public void setUp(){
		initialization();
		homePage = new XylemHomePage();	
		projectPage = new XylemProjectPage();	

	}
	
	@Test(priority=1)
	public void loginTest(){
		boolean atHome= homePage.isHomePage();
		Assert.assertEquals(true, atHome);
		homePage.navigateToIndiaHomePage();
		homePage.userLogin(prop.getProperty("xyuser"), prop.getProperty("xypassword"));
		Assert.assertEquals(projectPage.getLoggerInUser().contains("Hi"), true);
	}
	
	@Test(priority=2)
	public void pumpSearchTest(){
		String count=projectPage.getPumpModelQuantity(prop.getProperty("searchTag"),prop.getProperty("series"));
		Assert.assertEquals(count, "56");
	
	}
	
	@Test(priority=3)
	public void languageListTest(){
	List<String> Actuallist=projectPage.getLanguageList();
	//List<String> Explist = new ArrayList();

	String str[] = prop.getProperty("langlist").split(",");
//	for(int i=0;i<str.length;i++) {
//		Explist.add(str[i]);	
//	}
	List<String> Expectedlist = new ArrayList<String>(Arrays.asList(str));
	Assert.assertEquals(Actuallist, Expectedlist, "Equal");
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}
