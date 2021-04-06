package ElementSupporters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Exceptions.ElementException;
import Utilities.CommonHelpers;

public class Element {

	public static String getAttribute(WebElement element, String Attribute) throws Exception{
		
		String attributeValue = null;
		try {
			if(Attribute != null) {
				if(element != null) {
					if(element.isDisplayed()) {
						if(element.isEnabled()) {
							attributeValue = element.getAttribute(Attribute);
						}
						else {
							
						}
					}
				}
			}	
		}
		catch(Exception e) {
			e.getMessage();
		}
		return attributeValue;
	}

	public static List<WebElement> getElements(String strLogicalName, WebDriver driver,  By objectLocator) throws ElementException{
		
		List<WebElement> elements = null;
		
		try {
			if(!CommonHelpers.isObjectLocatorNull(objectLocator)) {
				if(CommonHelpers.exists(strLogicalName, driver, objectLocator)) {
					elements = driver.findElements(objectLocator);
				}
				else {
					throw new ElementException(strLogicalName + "--> WebElement doesnot exist");
				}
			}
			else {
				throw new ElementException(strLogicalName +"--> Element ObjectLocator is null");
			}	
		}
		catch(Exception e) {
			throw new ElementException(strLogicalName +"--> WebElement doesnot exist");
		}
		
		return elements;
	}

}
