package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class CommonHelpers {

	public static boolean clickbyJS(WebDriver driver, WebElement element) {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
		return true;
	}
	public static String formatDate(String string) {
		DateFormat outputDateFormat = new SimpleDateFormat("dd-MMM-YY");
		DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		String input = string;
		Date date = null;
		try {
			date = inputDateFormat.parse(input);
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		String outputText = outputDateFormat.format(date);
		return outputText;
		
	}
	public static Properties readPropertiesFile(String propFileName) {
		InputStream inputStream = null;
		Properties prop = new Properties();
		inputStream = CommonHelpers.class.getClassLoader().getResourceAsStream(propFileName);
		try {
			if(inputStream != null) {
				prop.load(inputStream);
			}
			else {
				throw new FileNotFoundException("property file: "+propFileName+" not found in the class path");
			}
		}
		catch(IOException e) {
			String error = e.getMessage();
			if(error != null) {
				System.out.println("File not found: "+error+" \nFileName "+propFileName);
			}
			try {
				inputStream.close();
			}
			catch(Exception e1) {
			}
			inputStream = null; 
		}
		return prop;
		
		
	}
	public static boolean exists(String strLogicalName,WebDriver driver, By by) {
		
		boolean exists = false;
		
		List<WebElement> element = waitForElements(by,driver, strLogicalName);
		try {
			if(element.size() != 0 ) {
				exists = true;
			}
			else {
				exists = false;
			}
		}
		catch(Exception e) {
			
		}
		return exists;
		
	}
	public static String getAlertText(WebDriver driver)
	{
		String text = null;
		try {
			text = driver.switchTo().alert().getText();			
		}
		catch(NoAlertPresentException e) {
		}
		return text;
	}
	public static boolean handleAlert(WebDriver driver) {
		try {
			waitUntil(driver,5,ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.getText();
			alert.accept();	
			return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
	}
	public static boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();	
			return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
	}
	public static boolean isFileExists(String filePath) {
		if(filePath != null && !filePath.isEmpty()) {
			File file = new File(filePath) ;
			if(file.isFile()) {
				if(file.exists()) {
					return true;
				}
			}
		}
		return false;
	} 
	public static Wait<WebDriver> fluentWait(WebDriver driver, long waiting) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(waiting, TimeUnit.SECONDS).pollingEvery(1000, TimeUnit.MILLISECONDS)
																										.ignoring(TimeoutException.class);
		return wait;
	}
	public static <T> T waitUntil(WebDriver driver, long timeOutInSeconds, final ExpectedCondition<T> expCondition) {
		Wait<WebDriver> wait = fluentWait(driver,timeOutInSeconds);
			T returnElement = wait.until(new Function<WebDriver, T>() {
												public T apply(WebDriver driver) { 
													try {
														return expCondition.apply(driver);
													}catch(Exception e){
														return null;
													}
												}
										});
		return returnElement;
	}
	private static List<WebElement> waitForElements(By locator, WebDriver driver, String strLogicalName) {
		List<WebElement> elements = new ArrayList<WebElement>();
		
		try {
			//Time should get from properties file
			elements = waitUntil(driver, 10000, ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		catch(Exception e){
			//Log
		}
		return elements;
	}
	public static String readFileAsString(String path) {
		String fileContent = null;
		try {
			fileContent = new String(Files.readAllBytes(Paths.get(path)));
		}
		catch(IOException e){
			System.out.println("File not found");
		}
		return fileContent;
	}
	public static boolean isFolderExists(String folderPath) {
		if(folderPath != null && !folderPath.isEmpty()) {
			File file = new File(folderPath);
			if(file.isDirectory()) {
				if(file.exists()) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean isObjectLocatorNull(By locator) {
		boolean isLocatorNull = true;
		if(locator != null) {
			isLocatorNull = false;
		}
		return isLocatorNull;
	}
	public static void renameRetyrLog(String dirPath, String methodName) {
		try {
			File logFile = new File(dirPath+"/"+methodName+".log");
			if(logFile.exists()) {
				File retryLog = new File(dirPath+"/"+methodName+"_retry.log");
				if(retryLog.exists()) {
					retryLog.delete();
				}
				logFile.delete();
			}	
		}
		catch(Exception e) {
			e.getMessage();
		}
		
	}
	public static void takeScreenshot(WebDriver driver, String outputDir, String methodName) {
		String fileName = null;
		String fileNameWithPath = null;
		File scrFile = null;
		try {
			if(driver != null) {
				try {
					scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);				
				}
				catch(ClassCastException e) {
					return;
				}
				fileName = methodName+".jpeg";
				fileNameWithPath = outputDir+"/Screenshots/"+fileName;
				
				File ScrShot = new File(fileNameWithPath);
				if(ScrShot.exists()) {
					if( null != ScrShot) {
						String retryFileName = outputDir+"/Screenshots/"+methodName+"_retry"+".jpeg";
						File failedScrShot = new File(retryFileName);
						if(failedScrShot.exists()) {
							failedScrShot.delete();
						}
						FileUtils.moveFile(scrFile, failedScrShot);
					}
				}
				
				FileUtils.copyFile(scrFile, ScrShot);
			}
		}
		catch(Exception e) {
			
		}
	}
	public static void waitInSeconds(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			//Log;
		}
	}
	public static String readNotepadContent(String reFilePath){
		String str_data = null;
		File file = new File(reFilePath);
		BufferedReader br;
		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String st;
			while((st = br.readLine()) != null) {
				str_data += st;
			}
			br.close();
		}
		catch(Exception e) {
		}
		return str_data.toString();

	}
	public static void switchToIFrame(WebDriver driver, String frameID) {
		driver.switchTo().frame(frameID);			
	}
	public static void existFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	public static void moveToElement(WebDriver driver, WebElement targetElement) {
		Actions action = new Actions(driver);
		action.moveToElement(targetElement).perform();;
	}
	public static void moveToElementAndClick(WebDriver driver, WebElement targetElement) {
		Actions action = new Actions(driver);
		action.moveToElement(targetElement).click().perform();
	}
	public static void switchToIFrame(WebDriver driver, WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	public static String[][] convertToStringArray(List<Map<String,Object>> object) {
		String[][] returnArray = null;
		int y = 0;
		
		//Adding Header
		Map<String, Object> map = object.get(0);
		for(String key : map.keySet()) {
			returnArray[0][y] = key.toString();
			y++;
		}
		
		//Adding Values
		for(int x= 0; x < object.size(); x++) {
			map = object.get(x);
			y = 0;
			for(String key: map.keySet()) {
				if(map.get(key) == null) {
					returnArray[x+1][y] = "(null)";
				}
				else {
					returnArray[x+1][y] = map.get(key).toString();
				}
				y++;
			}	
		}
		return returnArray;
	}
	public static String[][] convertToStringArray(Map<String,String> object) {
		String[][] returnArray;
		int y = 0;
		//Header and Values
		returnArray = new String[2][object.size()];
		for(String key : object.keySet()) {
			returnArray[0][y] = key;
			returnArray[1][y] = object.get(key);
			y++;
		}
		return returnArray;
	}
	public static void navigateToMenu(String navigation, WebDriver driver) throws InterruptedException {
		
		Actions action = new Actions(driver);
		
		String[] navArr = navigation.split("|");
		for(int i = 0; i < navArr.length; i++) {
			if(i == 0) {
				action.moveToElement(driver.findElement(By.xpath("//td[contains('text()',"+navArr[i]+")]"))).perform();
				Thread.sleep(5000);
			}
			else if( i == navArr.length-1) {
				driver.findElement(By.xpath("//li[contains('text',"+navArr[i]+")]")).click();
			}
			else {
				action.moveToElement(driver.findElement(By.xpath("//li[conatins('text()',"+navArr[i]+")]"))).perform();
				Thread.sleep(5000);
			}
		}
	}
	
}