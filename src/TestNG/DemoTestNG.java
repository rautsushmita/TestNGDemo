package TestNG;

import java.io.File;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DemoTestNG {
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeSuite
	public void beforesuite(){
		System.out.println("BeforeSuite Method");
	}
	
	@BeforeClass
	public void startReport(){
		System.out.println("Before Class");
		
		extent=new ExtentReports(System.getProperty("user.dir")+"/test-Output/STMExtentReport.html",true);
		extent.addSystemInfo("Host Name","Software Testing Material");
		extent.addSystemInfo("Environment", "Automation testing");
		extent.addSystemInfo("User Name","Sushmita");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
	
	@BeforeTest
	public void beforeTest(){
		System.out.println("Before Test");
	}
	
	@BeforeMethod
	public void beforeMethod(){
		System.out.println("Before Method");
	}
	
	@Test
	public void Test1(){
		logger=extent.startTest("Test1");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Test1 is Passed");
	}
	
	@Test
	public void Test2(){
		logger=extent.startTest("Test2");
		Assert.assertTrue(false);
		logger.log(LogStatus.FAIL, "Test 2 (failed) is Passed");
	}
	
	@Test
	public void Test3(){
		logger=extent.startTest("Skip Test3");
		throw new SkipException("Skipping Test 3");
	}
	
	@AfterMethod
	public void getResult(ITestResult result){
		//System.out.println("After Method");
		if(result.getStatus()==ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			 }else if(result.getStatus() == ITestResult.SKIP){
			 logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
	}
	
	@AfterTest
	public void AfterTest(){
		//System.out.println("AfterTest");
		extent.flush();
		extent.close();
	}
	
	@AfterClass
	public void AfterClass(){
		System.out.println("After Class");
	}
	
	@AfterSuite
	public void AfterSuite(){
		System.out.println("After Suite");
	}
}
