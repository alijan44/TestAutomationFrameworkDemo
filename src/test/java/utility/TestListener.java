package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private ExtentReports extent;
    private ExtentTest testcase;
    private ExtentSparkReporter spark;


    // ====== TEST ====== //

    public void onStart(ITestContext test) {
        String reportPath = System.getProperty("user.dir") + "/reports/";
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.STANDARD);
        extent.attachReporter(spark);

    }

    public void onFinish(ITestContext test) {
        extent.flush();
    }



    // ===== TEST CASE  ====== //

    public void onTestStart(ITestResult result) {
        testcase = extent.createTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        testcase.pass("Test Case Passed");
    }

    public void onTestFailure(ITestResult result) {
        testcase.fail("Test execution resulted in failure");
    }

    public void onTestSkipped(ITestResult result) {
        testcase.skip("Test execution was skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // DO NOTHING
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        String stackTraceMsg = result.getThrowable().getStackTrace().toString();
        testcase.fail(stackTraceMsg);
        this.onTestFailure(result);
    }

    private void attachScreenshot(String message) {
        String screenshot = ((TakesScreenshot) DriverUtil.getDriver()).getScreenshotAs(OutputType.BASE64);
        testcase.addScreenCaptureFromBase64String(screenshot, message);
    }









}
