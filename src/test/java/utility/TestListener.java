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
    private ExtentSparkReporter spark;
    private static ExtentTest testcase;


    // ====== TEST ====== //

    /*
      Method to be executed on test start.
     */
    public void onStart(ITestContext test) {
        String reportPath = System.getProperty("user.dir") + "/reports/";
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.STANDARD);
        extent.attachReporter(spark);

    }

    /*
      Method to be executed on test finish.
     */
    public void onFinish(ITestContext test) {
        extent.flush();
    }


    // ===== TEST CASE  ====== //

    /*
      Call this method to create test report case on test start
     */
    public void onTestStart(ITestResult result) {
        testcase = extent.createTest(result.getName());
    }

    /**
     * Call this method to return a specified test case section.
     * @return desired test case section.
     */
    public static ExtentTest getTestCaseSection() {
        return testcase;
    }

    /*
     * Used to when the test has passed.
     */
    public void onTestSuccess(ITestResult result) {
        testcase.pass("Test Case Passed");
    }

    /*
     * Used to when the test has failed.
     */
    public void onTestFailure(ITestResult result) {
        testcase.fail("Test execution resulted in failure");
    }

    /*
     * Used to when the test case was skipped.
     */
    public void onTestSkipped(ITestResult result) {
        testcase.skip("Test execution was skipped");
    }

    /*
     * Test has failed, but within success percentage.
     */
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // DO NOTHING
    }

    /*
     * Test has failed due to timeout.
     */
    public void onTestFailedWithTimeout(ITestResult result) {
        String stackTraceMsg = result.getThrowable().getStackTrace().toString();
        testcase.fail(stackTraceMsg);
        this.onTestFailure(result);
    }

    /*
     * Used to attach screenshot of results.
     */
    private void attachScreenshot(String message) {
        String screenshot = ((TakesScreenshot) DriverUtil.getDriver()).getScreenshotAs(OutputType.BASE64);
        testcase.addScreenCaptureFromBase64String(screenshot, message);
    }
}
