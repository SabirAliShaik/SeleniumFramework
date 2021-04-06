package RealFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import pageObjects.HomePage;

public class Base {
	HomePage h;
	public static WebDriver driver;
	public static Properties pro;
	
	public static WebDriver getdriver() throws IOException {
		pro=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\S K SABIR ALI\\Java\\workspace\\Automation\\src\\test\\java\\cucumber\\Automation\\global.properties");
		pro.load(fis);
		driver=new ChromeDriver();
		driver.get(pro.getProperty("url"));
		return driver;
	}
	
    public void user_is_on_greencart_landing_page() throws Throwable {
		driver=Base.getdriver();
		driver.manage().window().maximize();
    }
	
    public void user_searched_for_vegetables(String name) throws Throwable {
    	h = new HomePage(driver);
    	h.searchPage().sendKeys(name);
    	Thread.sleep(3000);
    }
    
    public void something_results_is_displayed(String strArg1) throws Throwable {
    	String result =  driver.findElement(By.cssSelector("h4.product-name")).getText();
    	System.out.println(result);
    	Assert.assertTrue(result.contains(strArg1));
    }
    
    public void added_items_to_cart() throws Throwable {
        driver.findElement(By.cssSelector("a.increment")).click();
        driver.findElement(By.xpath("//button[contains(text(),'ADD TO CART')]")).click();
    }

    public void user_proceeded_to_checkout_page_for_purchase() throws Throwable {
        driver.findElement(By.xpath("//a[@class='cart-icon']//img[contains(@class, '')]")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
        Thread.sleep(2000);
    }
	
    public void close_driver() {
    	driver.close();
    }
}
