package ElementSupporters;

import org.openqa.selenium.WebElement;

import Exceptions.CheckBoxElementException;

public class CheckBoxElement {

	public static boolean check(WebElement checkBoxElement) throws CheckBoxElementException{
		boolean checkStatus = false;
		try {
			if(checkBoxElement != null) {
				if(checkBoxElement.isDisplayed()) {
					if(checkBoxElement.isEnabled()) {
						if(!checkBoxElement.isSelected()) {
							checkBoxElement.click();
							checkStatus = true;
						}
					}
				}
			}	
		}
		catch(Exception e) {
			throw new CheckBoxElementException("Check Box is not found");
		}
		
		return checkStatus;
	}
	
	public static boolean unCheck(WebElement checkBoxElement) throws CheckBoxElementException{
		boolean unCheckStatus = false;
		try {
			if(checkBoxElement != null) {
				if(checkBoxElement.isDisplayed()) {
					if(checkBoxElement.isEnabled()) {
						if(checkBoxElement.isSelected()) {
							checkBoxElement.click();
							unCheckStatus = true;
						}
					}
				}
			}	
		}
		catch(Exception e) {
			throw new CheckBoxElementException("Check Box is not found");
		}
		
		return unCheckStatus;
	}

	public static boolean isChecked(WebElement checkBoxElement) throws CheckBoxElementException {
		
		boolean checkStatus = false;
		try {
			if(checkBoxElement != null) {
				if(checkBoxElement.isDisplayed()) {
					if(checkBoxElement.isEnabled()) {
						if(checkBoxElement.isSelected()) {
							checkStatus = true;
						}
					}
				}
			}	
		}
		catch(Exception e) {
			throw new CheckBoxElementException("Check Box is not found");
		}
		return checkStatus;
	}

	public static boolean isUnChecked(WebElement checkBoxElement) throws CheckBoxElementException {
		
		boolean UnCheckStatus = false;
		try {
			if(checkBoxElement != null) {
				if(checkBoxElement.isDisplayed()) {
					if(checkBoxElement.isEnabled()) {
						if(!checkBoxElement.isSelected()) {
							UnCheckStatus = true;
						}
					}
				}
			}	
		}
		catch(Exception e) {
			throw new CheckBoxElementException("Check Box is not found");
		}
		return UnCheckStatus;
	}

	public static boolean isDisabled(WebElement checkBoxElement) throws CheckBoxElementException{
		boolean disableStatus = false;
		try {
			if(checkBoxElement != null) {
				if(checkBoxElement.isDisplayed()) {
					if(!checkBoxElement.isEnabled()) {
						disableStatus = true;
					}
				}
			}	
		}
		catch(Exception e) {
			throw new CheckBoxElementException("Check Box is not found");
		}
		
		return disableStatus;
	}
	
	public static boolean iEnabled(WebElement checkBoxElement) throws CheckBoxElementException{
		boolean enableStatus = false;
		try {
			if(checkBoxElement != null) {
				if(checkBoxElement.isDisplayed()) {
					if(checkBoxElement.isEnabled()) {
						enableStatus = true;
					}
				}
			}	
		}
		catch(Exception e) {
			throw new CheckBoxElementException("Check Box is not found");
		}
		
		return enableStatus;
	}

}
