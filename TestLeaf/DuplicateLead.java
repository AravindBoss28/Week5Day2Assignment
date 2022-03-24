package week5Day1.Assignments;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DuplicateLead extends EnvironmentSetup {

	//	@Test(priority = -1, dataProvider = "Test_Data")
	//	void duplicateLead(String companyName, String firstName, String lastName, String departmentName) {
	//			driver.findElement(By.linkText("Create Lead")).click();
	//			//enterDetails(companyName, firstName, lastName, departmentName);
	//		
	//		try {
	//			Thread.sleep(3000);
	//		} catch (InterruptedException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		driver.findElement(By.linkText("Duplicate Lead")).click();
	//		driver.findElement(By.id("createLeadForm_companyName")).clear();
	//		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("TEST123");
	//		driver.findElement(By.id("createLeadForm_firstName")).clear();
	//		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Aravind124");
	//		String dup = "Duplicate Lead | opentaps CRM";
	//		String actualTitleDup = driver.getTitle();
	//		dup.equals(actualTitleDup);
	//		
	//		//modify data and click update
	//		driver.findElement(By.className("smallSubmit")).click();
	//		String actualTitle = driver.getTitle();
	//		String expTitle = "View Lead | opentaps CRM"; 
	//        actualTitle.equals(expTitle);
	//        System.out.println("Duplicate account created successfully");
	//        System.out.println("Scenario Passed");
	//        tearDown("Aravind124");
	//	}
    
	@DataProvider(name = "getLeadId")
    public String[] getId() throws Exception {
    	DataProviderClass d = new DataProviderClass("./TestData/LeadData.xlsx");
    	String[] id = d.getColValue("LEAD", "ID");
    	for (int i = 0; i < id.length; i++) {
		  System.out.println(id[i]);	
		}
    	return id;
    }
	
	
	@Test(priority = -1, dataProvider = "getLeadId", groups = "DuplicateLead")
	void duplicateLead(String leadId) {
		driver.findElement(By.linkText("Find Leads")).click();
		String xpathforLeadId = "//div[@class='x-panel-bwrap']//label[text()='Lead ID:']/following::input[@name='id']";
		driver.findElement(By.xpath(xpathforLeadId)).sendKeys(leadId);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/div/a")).click();
		driver.findElement(By.linkText("Duplicate Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).clear();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("TEST123");
		driver.findElement(By.id("createLeadForm_firstName")).clear();
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Aravind124");
		String dup = "Duplicate Lead | opentaps CRM";
		String actualTitleDup = driver.getTitle();
		dup.equals(actualTitleDup);

		//modify data and click update
		driver.findElement(By.className("smallSubmit")).click();
		String actualTitle = driver.getTitle();
		String expTitle = "View Lead | opentaps CRM"; 
		actualTitle.equals(expTitle);
		System.out.println("Duplicate account created successfully");
		System.out.println("Scenario Passed");		
	}

}
