package week5Day1.Assignments;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class EditLead extends EnvironmentSetup {

	@Test(dataProvider = "getLeadId", dataProviderClass = DuplicateLead.class, groups = "ModifyDelete")
	void modifyDescription(String leadId) throws InterruptedException {	
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
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys("Modify description details");
		takeScreenShot("editleadforAravind124", driver);
		String editExpectedRes = "opentaps CRM";
		String actualEditRes = driver.getTitle();
		editExpectedRes.equals(actualEditRes);
		System.out.println("Verify before updating");
		driver.findElement(By.className("smallSubmit")).click();
		takeScreenShot("editleadModifiedforAravind124", driver);
		System.out.println("Verify after updating");
	}

	@Test(priority = 2, dataProvider = "getLeadId", dataProviderClass = DuplicateLead.class, groups = "ModifyDelete")
	public void tearDown(String leadId) {
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
		driver.findElement(By.className("subMenuButtonDangerous")).click();
		takeScreenShot("DeletingLead"+ leadId, driver);
	}
}
