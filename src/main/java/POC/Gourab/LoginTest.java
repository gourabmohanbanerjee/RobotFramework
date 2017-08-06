package POC.Gourab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.Constants;
import utilities.ExcelUtility;
import utilities.Utility;

public class LoginTest {

	ArrayList<String> testStepList = new ArrayList<String>();
	String propName = "";
	String action = "";
	String tData = "";
	WebDriver driver;
	ExcelUtility excel = new ExcelUtility();
	Constants cons = new Constants();

	@BeforeClass
	public void beforeTest() throws IOException {

		System.out.println("Staring Automation Script Execution");
		driver = new ChromeDriver();
		System.setProperty(cons.getDriverName(),cons.getDriverValue());
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	@Test
	public void Test() {
		try {
			PrintWriter writer = new PrintWriter(cons.getAutoGeneratedScriptPath(), "UTF-8");
			writer.println("public void Test(){");
			writer.println(" ");
			writer.println("	driver = new ChromeDriver();");
			writer.println("	System.setProperty("+ "\"webdriver.chrome.driver\"" + ","+ "\"resources/chromedriver\"" + ");");
			testStepList = excel.readExcelFile();

			for (int i = 1; i < testStepList.size(); i++) {
				String[] row = testStepList.get(i).split(" --- ");
				propName = row[0].trim();
				action = row[1].trim();
				tData = row[2].trim();

				//Execute for URL
				if (action.equals("URL")) {
					driver.get(tData);
					writer.println("	driver.get(" + "\"" + tData + "\"" + ");");
				}
				//Execute for Button click, Submit, Radio Button click, CheckBox click, click links
				else if (action.equals("Click")) {
					driver.findElement(By.id(propName)).click();
					writer.println("	driver.findElement(By.id(" + "\""+ propName + "\"" + ")).click();");
				}
				//Execute for write text in input field
				else if (action.equals("Write")) {
					driver.findElement(By.id(propName)).sendKeys(tData);
					writer.println("	driver.findElement(By.id(" + "\""+ propName + "\"" + ")).sendKeys(" + "\"" + tData+ "\"" + ");");
				}
				//Execute for select item from Dropdown list
				else if (action.equals("Select")) {
					Select select = new Select(driver.findElement(By.id(propName)));
					select.selectByVisibleText(tData);
					writer.println("	Select select = new Select(driver.findElement(By.id(" + "\""+ propName + "\"" + ");");
					writer.println("	select.selectByVisibleText(" + "\"" + tData+ "\"" + ");");
				}
				//Execute for de-select item from Dropdown list
				else if (action.equals("Deselect")) {
					Select select = new Select(driver.findElement(By.id(propName)));
					select.deselectByVisibleText(tData);
					writer.println("	Select select = new Select(driver.findElement(By.id(" + "\""+ propName + "\"" + ");");
					writer.println("	select.deselectByVisibleText(" + "\"" + tData+ "\"" + ");");
				}
				//Execute for Mousehover action
				else if (action.equals("Mousehover")) {
					Actions action = new Actions(driver);
					action.moveToElement(driver.findElement(By.id(propName))).build();
                    writer.println("	Actions action = new Actions(driver);");
					writer.println("	action.moveToElement(driver.findElement(By.id(" + "\"" + propName+ "\"" + "))).build();");
				}
				

			}
			writer.println(" ");
			writer.println("}");
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void afterTest() {

		System.out.println("Automation Script Execution has completed");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(cons.getScreenshotPath()));
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
		driver.close();

	}
}
