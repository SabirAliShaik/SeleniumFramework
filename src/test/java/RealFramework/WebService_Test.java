package RealFramework;

import java.lang.module.ModuleDescriptor.Exports;
import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import APIRequests.RESTUtilities;
import Pojo.StreetProcessor;
import Utilities.CommonHelpers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class WebService_Test {

	StreetProcessor streetprocessor = new StreetProcessor();
	
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeClass
	public void restSetUp() {
		reqSpec = RESTUtilities.getRequestSpecification();
		resSpec = RESTUtilities.getResponseSpecification();
	}
	
	@BeforeTest
	public void setUp() {
		extent = new ExtentReports();
		extent.attachReporter(getHTMLReporter());
	}
	
	public ExtentHtmlReporter getHTMLReporter() {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(System.getProperty("user.dir")
				+"/test-output/extent-reports/StreetProcessorReport"+System.currentTimeMillis()+".html");
		reporter.config().setDocumentTitle("AUTOMATION POC");
		reporter.config().setReportName("Street Processor Test report");
		reporter.config().setTheme(Theme.STANDARD);
		return reporter;
	}
	@Test
	public void validateStreetProcessor() {
		
		logger = extent.createTest("validate street processor");
		
		logger.log(Status.INFO, "reading Json from NotePad");
		String payLoad = streetprocessor.readJsonRequest();
		
		logger.log(Status.INFO, "Receive the reponse from WebService");
		Response response = RESTUtilities.getResponse(reqSpec, "post", payLoad);
		
		System.out.println("Response Data -> "+response.asString());
		
		logger.log(Status.INFO, "Send the Response to Pojo");
		streetprocessor.sendResponseJson(response);
		
		logger.log(Status.INFO, "Validate Response parameters");
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(streetprocessor.validateFirmId());
		//All the boolean return methods from streetProcessor
	}
	@AfterMethod
	public void GetResult(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel("Test Name: "+result.getName()+", Status: Failed", ExtentColor.RED));
			//logger.fail("F", MediaEntityBuilder.createScreenCaptureFromPath("src/test").build());  --> To attach screenshots
		}		else if(result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("--------------"+result.getName());
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Name: "+result.getName()+", Status: Passed", ExtentColor.GREEN));
		}
	}
	
	@AfterTest
	public void endReport() {
		extent.flush();
	}
	
	
}
