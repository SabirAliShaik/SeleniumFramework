package ElementSupporters;

import org.openqa.selenium.WebElement;

import Exceptions.TextBoxElementException;

public class TextBoxElement {

	public static boolean enterText(WebElement textBoxElement, String textToBeEntered) throws TextBoxElementException{
		
		boolean enterStatus = false;
		try {
			if(textToBeEntered != null && !textToBeEntered.isEmpty()) {
				if(textBoxElement.isDisplayed()) {
					if(textBoxElement.isEnabled()) {
						textBoxElement.clear();
						textBoxElement.sendKeys(textToBeEntered);
						enterStatus = true;
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new TextBoxElementException("Text Box not found");
		}
		return enterStatus;
	}
	public static String getText(WebElement textBoxElement) throws TextBoxElementException{
		String textValue = null;
		try {
			if(textBoxElement != null) {
				if(textBoxElement.isDisplayed()) {
					if(textBoxElement.getText() != null && !textBoxElement.getText().isEmpty()) {
						textValue = textBoxElement.getText();
					}
					else if(null != textBoxElement.getAttribute("value") && !textBoxElement.getAttribute("value").isEmpty()) {
						textValue = textBoxElement.getAttribute("value");
					}
				}	
			}	
		}
		catch(Exception e) {
			throw new TextBoxElementException("Text Box not found");
		}
		return textValue;
	}
}
