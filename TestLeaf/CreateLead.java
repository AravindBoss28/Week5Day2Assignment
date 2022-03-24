package week5Day1.Assignments;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateLead extends LoginSteps {
	int column = 2;
	@DataProvider(name = "Test_Data")
	public String[][] newTestData() throws IOException{
	  CreateTestData test = new CreateTestData();
	  String[][] data = test.newTestData(3, 3);
	  return data;
	}
	
	int rand = (int)(Math.random() * 100);
	boolean selectProvince(String s) {
		boolean flag = false;
		/**
		 * create a select and pass locator for drop down get drop down options loop
		 * through options and when condition matches select the passed value
		 */
		Select states = new Select(driver.findElement(By.name("generalStateProvinceGeoId")));
		List<WebElement> options = states.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals(s)) {
				states.selectByVisibleText(s);
				flag = true;
			}
		}
		return flag;
	}
	

	void enterDetails(String companyName, String firstName, String lastName) throws Exception {
		boolean details = false;
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(companyName);
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(firstName);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lastName);
		driver.findElement(By.name("departmentName")).sendKeys("Automation Testing");
		driver.findElement(By.name("description")).sendKeys("hello entering description details");
		driver.findElement(By.id("createLeadForm_primaryEmail")).sendKeys("arboss@gmail.com");
		details = true;
		boolean res = selectProvince("New York");
		if (details && res) {
			driver.findElement(By.className("smallSubmit")).click();
			Thread.sleep(5000);
			String id = driver.findElement(By.id("viewLead_companyName_sp")).getText();		
			String[] s2 = id.split(" "); 
	        Pattern p = Pattern.compile("(\\d+)");
	        Matcher m = p.matcher(s2[1]);
	        String empID = "";
	        while(m.find()) {
	        	empID = m.group();
	        }
			DataProviderClass d = new DataProviderClass("./TestData/LeadData.xlsx");
			d.setCellData("LEAD", "ID", column++, empID);
			takeScreenShot("CreatedLead"+firstName, driver);
		} else {
			System.out.println("there is a issue");
		}
	}
	
	@Test(priority = 0, dataProvider = "Test_Data", groups = "CreateLead")
	void createLead(String companyName, String firstName, String lastName) throws Exception {
		enterDetails(companyName, firstName, lastName);
	}
}
