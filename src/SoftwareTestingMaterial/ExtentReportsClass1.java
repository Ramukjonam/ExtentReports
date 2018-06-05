package SoftwareTestingMaterial;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportsClass1 {

	ExtentReports extent;
			
			ExtentTest logger;
		
			@BeforeTest
			public void starttest(){
				
				extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/STMExtentReport.html", true);
				extent
                .addSystemInfo("Host Name", "IndianNIC")
                .addSystemInfo("Environment", "Automation Testing By Admin")
                .addSystemInfo("User Name", "ManojKumar Rangineni");
                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
                //You could find the xml file below. Create xml file in your project and copy paste the code mentioned below
                extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	     }
			
			
		@Test
		public void PassTest(){
			logger = extent.startTest("passTest");
			Assert.assertTrue(true);
			//To generate the log when the test case is passed
			logger.log(LogStatus.PASS, "Test Case Passed is passTest");
			
		}
		
       @Test
         public void FailTest(){
	       logger = extent.startTest("FailTest");
	       Assert.assertTrue(true);
	       logger.log(LogStatus.PASS, "Test Case Failed in Failtest");
         }
       
       @Test
       public void close(){
	       logger = extent.startTest("CloseTest");
	       Assert.assertTrue(false);
	       logger.log(LogStatus.PASS, "Test Case Closed in close");
       }

		@AfterMethod
		public void getResult(ITestResult result){
			if(result.getStatus() == ITestResult.FAILURE){ 
				logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
				logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			}else if(result.getStatus() == ITestResult.SKIP){
				logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
			}
			// ending test
			//endTest(logger) : It ends the current test and prepares to create HTML report
			extent.endTest(logger);
		}
	@AfterTest
	public void endtest(){
		
		extent.flush();
		extent.close();
	
	}


	
}
