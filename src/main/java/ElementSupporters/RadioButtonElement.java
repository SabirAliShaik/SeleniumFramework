package ElementSupporters;

import org.openqa.selenium.WebElement;

import Exceptions.RadioButtonException;

public class RadioButtonElement {
	
	/**
	 * Check if Radio Button is enabled
	 *
	 * @param radioButton
	 * @return boolean
	 * @throws RadioButtonException
	**/
	public static boolean isEnabled(WebElement radioButton) throws RadioButtonException{
		
		boolean enableStatus = false;
		try {
			if(null != radioButton) {
				if(radioButton.isDisplayed()) {
					if(radioButton.isEnabled()) {
						enableStatus = true;
					}
					else {
						
					}
				}
			}	
		}
		catch(Exception e) {
			throw new RadioButtonException("Radio Button doesnot exist");
		}
		
		return enableStatus;
		
	}
	
	/**
	 * Select the radio button
	 * 
	 * @param radioButton
	 * @return boolean
	 * @throws RadioButtonException
	 */
	public static boolean select(WebElement radioButton) throws RadioButtonException {
		
		boolean selected = false;
		try {
			if(radioButton != null) {
				if(radioButton.isDisplayed()){
					if(radioButton.isEnabled()) {
						if(!radioButton.isSelected()) {
							radioButton.click();
						}
						else {
							
						}
						selected = true;
					}
				}
			}
		}
		catch(Exception e) {
			throw new RadioButtonException("Radio Button doesnot exist");
		}
		return selected;
	}
	
	/**
	 * Deselect the radio button
	 * @param radioButton
	 * @return boolean
	 * @throws RadioButtonException
	 */
	public static boolean deSelect(WebElement radioButton) throws RadioButtonException {
			
			boolean deSelected = false;
			try {
				if(radioButton != null) {
					if(radioButton.isDisplayed()){
						if(radioButton.isEnabled()) {
							if(radioButton.isSelected()) {
								radioButton.click();
								deSelected = true;
							}
							else {
								
							}
						}
					}
				}
			}
			catch(Exception e) {
				throw new RadioButtonException("Radio Button doesnot exist");
			}
			return deSelected;
		}

	/**
	 * Check if Radio Button is selected
	 * @param radioButton
	 * @return
	 * @throws RadioButtonException
	 */
	public static boolean isSelected(WebElement radioButton) throws RadioButtonException{
		
		boolean selected = false;
		try {
			if(radioButton != null) {
				if(radioButton.isDisplayed()){
					if(radioButton.isEnabled()) {
						if(radioButton.isSelected()) {
							selected = true;
						}
						else {
							
						}
					}
				}
			}
		}
		catch(Exception e) {
			throw new RadioButtonException("Radio Button doesnot exist");
		}
		return selected;
	}

}
