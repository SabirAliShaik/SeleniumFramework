package ElementSupporters;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownElement {
	
	public static void selectByVisibleText(WebElement dropDownElement, String valueToBeSelected) {
		Select select = new Select(dropDownElement);
		select.selectByVisibleText(valueToBeSelected);
	}
	public static void selectByIndex(WebElement dropDownElement, int index) {
		Select select = new Select(dropDownElement);
		select.selectByIndex(index);
		
	}
}
