package week5Day1.Assignments;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TakeScreenShot {
	public void takeScreenShot(String fname, RemoteWebDriver driver) {
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		try {
			File file = new File("./Snaps/" + fname + ".png");
			FileHandler.copy(screenshotAs, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("repective folder is not available or may be some other issue in file path");
		}
	}
}
