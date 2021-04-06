package RealFramework;
import RealFramework.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.*;

import pageObjects.HomePage;

public class TestClass {
	
	public static void Search_items() throws Throwable {
		Base base = new Base();
		base.user_is_on_greencart_landing_page();
		base.user_searched_for_vegetables("Brinjal");
		base.something_results_is_displayed("Brinjal");
		base.close_driver();
	}
	public static void main(String[] args) throws Throwable {
		TestClass.Search_items();
	}
}
