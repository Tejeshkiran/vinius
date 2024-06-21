package testpac.Testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import programResources.ExtentReportGenaration;

import java.io.IOException;

public class Listners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReportGenaration.genarateReport();
    ThreadLocal<ExtentTest> threadTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        threadTest.set(test);//unique thread id(ErrorValidations)->test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        threadTest.get().log(Status.PASS, "Test pass yes yes");
        //replease test with threadTest.get() in above line
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            threadTest.get().fail(result.getThrowable());
            WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
            // Step 1 take a screenshot, Step 2 attach path to extent report
            String filepath = getScreenshort(result.getMethod().getMethodName(), driver);
            threadTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();

            //replace test to threadTest.get() this will have current test case thread
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Implement if needed
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Implement other methods of ITestListener if needed
}