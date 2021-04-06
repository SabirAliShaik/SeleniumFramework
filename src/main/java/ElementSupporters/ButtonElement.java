package ElementSupporters;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Exceptions.ButtonElementException;
import Utilities.CommonHelpers;

public class ButtonElement {

	public static final String exceptionMessage = "Button element not found";
	public static boolean isDisplayed(WebElement buttonElement, String strLogicalName) throws ButtonElementException{
		boolean status = false;
		try {
			if(buttonElement != null) {
				if(buttonElement.isDisplayed()) {
					status = true;
				}	
			}
		}
		catch(Exception e) {
			throw new ButtonElementException(strLogicalName + " button element not found");
		}
		return status;
	}
	
	public static boolean isDisplayed(WebElement buttonElement) throws ButtonElementException{
		boolean status = false;
		try {
			if(buttonElement != null) {
				if(buttonElement.isDisplayed()) {
					status = true;
				}
			}
		}
		catch(Exception e) {
			throw new ButtonElementException("Button element not found");
		}
		return status;
	}
	
	public static boolean isEnabled(WebElement buttonElement, String strLogicalName) throws ButtonElementException{
		
		boolean buttonEnable = false;
		try {
			if(buttonElement != null) {
				if(buttonElement.isEnabled()) {
					buttonEnable= true;
				}
				else {
					buttonEnable= false;
					Arrays.asList("\"" + strLogicalName, "\"Button not enabled", "1");
				}
			}
			else {
				buttonEnable = false;
				Arrays.asList("\""+strLogicalName, "\"Button element is NULL", "1");
			}	
		}
		catch(Exception e) {
			Arrays.asList("\""+ strLogicalName, "\"Button not found","1");
			throw new ButtonElementException(strLogicalName +" --> "+ exceptionMessage);
		}
		return buttonEnable;
	}
	
	public static boolean isEnabled(WebElement buttonElement) throws ButtonElementException{
			
		boolean buttonEnable = false;
		try {
			if(buttonElement != null) {
				if(buttonElement.isEnabled()) {
					buttonEnable= true;
				}
				
			}
		}
		catch(Exception e) {
			throw new ButtonElementException("Button Element is not found");
		}
		return buttonEnable;
	}

	public static boolean click(WebDriver driver, String strLogicalName, By objectLocator) throws ButtonElementException {
		boolean clickStatus = false;
		objectLocator.toString();
		WebElement element = null;
		List<WebElement> elements = driver.findElements(objectLocator);
		try {
			if(!elements.isEmpty()) {
				element = elements.get(0);
				if(isDisplayed(element, strLogicalName) && isEnabled(element, strLogicalName)) {
					element.click();
					clickStatus = true;
				}
			}
			else {
				Arrays.asList(strLogicalName,"Unable to click \""+strLogicalName+"\" as button not found","1" );
			}
		}
		catch(Exception e) {
			Arrays.asList("\""+strLogicalName, "\" Button not found","1");
			throw new ButtonElementException(strLogicalName+" --> Button not found");
		}
		return clickStatus;
	}

	public static boolean click(WebDriver driver, String strLogicalName, WebElement element) throws ButtonElementException {
		
		boolean clickStatus = false;
		List<String> arrResult = null;
		try {
			if(element != null) {
				
				//Wait for 60 seconds for button to be clickable
				CommonHelpers.waitUntil(driver, 60, ExpectedConditions.elementToBeClickable(element));
				
				if(isDisplayed(element, strLogicalName) && isEnabled(element, strLogicalName)) {
					element.click();
					clickStatus = true;
				}
			}
			else {
				arrResult = Arrays.asList(strLogicalName,"Unable to click \""+strLogicalName+"\" as button not found","1" );
			}
		}
		catch(Exception e) {
			try {
				CommonHelpers.waitInSeconds(5);
				CommonHelpers.clickbyJS(driver, element);
				clickStatus = true;
			}
			catch(Exception e1) {
				arrResult = Arrays.asList("\""+strLogicalName, "\" Button not found -->" +e1);
				throw new ButtonElementException(strLogicalName + " --> Button not found --> " +e1);	
			}
		}
		return clickStatus;
	}

	public static boolean click(WebDriver driver, By objLocator) throws ButtonElementException {
			
		boolean clickStatus = false;
		WebElement element = driver.findElement(objLocator);
		try {
			if(isDisplayed(element) && isEnabled(element)) {
				element.click();
				clickStatus = true;
			}
		}
		catch(Exception e) {
			throw new ButtonElementException(exceptionMessage);
		}
		return clickStatus;
	}

	public static boolean verifyButtonName(WebElement buttonElement, String nameTobeMatched) throws ButtonElementException{
		
		boolean nameMatched = false;
		String buttonName = null;
		
		try {
			if(buttonElement.isDisplayed()) {
				buttonName = buttonElement.getText();
				if(buttonName.isEmpty()) {
					buttonName = buttonElement.getAttribute("value");
				}
				if(!buttonName.isEmpty()) {
					if(buttonName.compareTo(nameTobeMatched) == 0) {
						nameMatched = true;
					}
				}
			}	
		}
		catch(Exception e) {
			throw new ButtonElementException(exceptionMessage);
		}
		
		return nameMatched;
	}


}