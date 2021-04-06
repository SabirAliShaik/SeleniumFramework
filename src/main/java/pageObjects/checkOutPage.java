package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class checkOutPage {

public WebDriver driver;
	
	public checkOutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By product=By.cssSelector("p.product-name");
	
	public WebElement checkPage() {
		
		return driver.findElement(product);
		
	}
	
}
