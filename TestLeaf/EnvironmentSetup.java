package week5Day1.Assignments;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test(groups = "base")
public class EnvironmentSetup extends TakeScreenShot {
	public RemoteWebDriver driver;
	
	@Parameters({"URL","browser"})
	@BeforeClass
	public void initializeChrome(String url, String browser) {
		// it helps to avoid manual chrome driver path setup
        
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// initialize chrome
			driver = new ChromeDriver();
			
		} else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		// maximize screen
		driver.manage().window().maximize();
		/**
		 * login to application
		 */
		
		driver.get(url);
		String title = driver.getTitle();
		Assert.assertEquals(true, title.contains("Leaftaps"));
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		takeScreenShot("succesfullyLoggedIn", driver);
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
	}
	
	
	@AfterSuite
	public void tearDown() {	
	  driver.quit();	
	}
}
