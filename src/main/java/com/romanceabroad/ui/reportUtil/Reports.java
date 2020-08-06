package com.romanceabroad.ui.reportUtil;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class Reports {
    private static final boolean isJenkinsExecution = true;

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    private static String lastAction;

    private static String currentTestSuiteResultsPathDir;

    static {
        LocalDateTime ldt = LocalDateTime.now();
        String formattedDate = ldt.format(DateTimeFormatter.ofPattern("yyyy -MM-dd HH-mm-ss"));

        if (isJenkinsExecution) {
            currentTestSuiteResultsPathDir = "./reports/JenkinsReport/";
        } else {
            currentTestSuiteResultsPathDir = "./reports/Suite_" + formattedDate + "/";
            new File(currentTestSuiteResultsPathDir).mkdir();
        }

        htmlReporter = new ExtentHtmlReporter(currentTestSuiteResultsPathDir + "extent.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Reports");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.setSystemInfo("Organization", "Romanceabroad");
        extentReports.attachReporter(htmlReporter);
    }

    public static void startTest(String testName) {
        ExtentTest test = extentReports.createTest(testName);
        extentTestThreadLocal.set(test);
    }

    public static void stopReport() {
        extentReports.flush();
    }

    public static void log(Status status, String message) {
        extentTestThreadLocal.get().log(status, message);
        System.out.println(message);
        lastAction = message;
    }

    public static void failedTest(WebDriver driver, ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTestThreadLocal.get().fail("<details><summary><b><font color=red>Exception occurred, click for more details:"
                + "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>")
                + "</details> \n");
        try {
            takeScreenshotFile(driver, methodName);
            extentTestThreadLocal.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(driver)).build());
        } catch (IOException e) {
            extentTestThreadLocal.get().fail("Test failed, cannot attach the screenshot");
        }
        String logText = "<b>Test Method " + methodName + " Failed</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTestThreadLocal.get().log(Status.FAIL, markup);
    }

    public static void skippedTest(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>Test Method " + methodName + " Skipped</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTestThreadLocal.get().log(Status.SKIP, markup);
    }

    public static void passedTest(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>Test Method " + methodName + " Successful</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTestThreadLocal.get().log(Status.PASS, markup);
    }

    public static String getScreenshotName(String methodName) {
        Date d = new Date();
        String fileName = methodName + "_" + d.toString().replaceAll(":", "_").replaceAll(" ", "_") + ".png";
        return fileName;
    }

    public static String takeScreenshotBase64(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }

    public static String takeScreenshotFile(WebDriver driver, String methodName) {
        String fileName = getScreenshotName(methodName);
        String directory = currentTestSuiteResultsPathDir;
        String path = directory + fileName;
        File failScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.move(failScreenshot, new File(path));
        } catch (IOException e) {
            System.out.println("Screenshot was not saved");
        }
        return path;
    }

}
