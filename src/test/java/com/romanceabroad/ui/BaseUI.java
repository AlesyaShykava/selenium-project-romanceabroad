package com.romanceabroad.ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.romanceabroad.ui.mainClasses.*;
import com.google.common.io.Files;
import com.romanceabroad.ui.reportUtil.EventReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class BaseUI {
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    public ExtentTest extentTest;
    protected String mainURl = "https://romanceabroad.com/";
    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;
    protected SoftAssert softAssert = new SoftAssert();
    protected HomePage homePage;
    protected BlogPage blogPage;
    protected GiftsPage giftsPage;
    protected HowWeWorkPage howWeWorkPage;
    protected MediaPage mediaPage;
    protected RegistrationModal registrationModal;
    protected RestorePasswordPage restorePasswordPage;
    protected SignInModal signInModal;
    protected SearchPage searchPage;
    protected TourToUkrainePage tourToUkrainePage;
    protected LoginPage loginPage;
    protected UserProfilePage userProfilePage;
    protected ContactUsPage contactUsPage;

    @BeforeSuite(groups = {"smoke", "regression", "integration",  "negative"}, alwaysRun = true)
    public void beforeSuiteActions() {
        htmlReporter = new ExtentHtmlReporter("./reports/extent.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Reports");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.setSystemInfo("Organization", "Romanceabroad");
        extentReports.attachReporter(htmlReporter);
    }

    @BeforeMethod(groups = {"smoke", "regression", "integration", "negative"}, alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser, Method method){
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
            driver = new EventFiringWebDriver(new FirefoxDriver());
            driver.register(new EventReporter());
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
            driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
            driver.register(new EventReporter());
            //driver.get("chrome://settings/clearBrowserData");
        } else if (browser.equalsIgnoreCase("IE")) {
            System.setProperty("webdriver.ie.driver", "resources/IEDriverServer");
            driver = new EventFiringWebDriver(new InternetExplorerDriver());
            driver.register(new EventReporter());
            driver.manage().deleteAllCookies();
        } else {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
            driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
            driver.register(new EventReporter());
            //driver.get("chrome://settings/clearBrowserData");
        }

        extentTest = extentReports.createTest(method.getName());
        wait = new WebDriverWait(driver, 40);
        homePage = new HomePage(driver, wait);
        blogPage = new BlogPage(driver, wait);
        giftsPage = new GiftsPage(driver, wait);
        howWeWorkPage = new HowWeWorkPage(driver, wait);
        mediaPage = new MediaPage(driver, wait);
        registrationModal = new RegistrationModal(driver, wait);
        restorePasswordPage = new RestorePasswordPage(driver, wait);
        signInModal = new SignInModal(driver, wait);
        searchPage = new SearchPage(driver, wait, extentTest);
        tourToUkrainePage = new TourToUkrainePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        userProfilePage = new UserProfilePage(driver, wait);
        contactUsPage = new ContactUsPage(driver, wait);
        driver.manage().window().maximize();
        driver.get(mainURl);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodActions(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        if(ITestResult.FAILURE == result.getStatus()) {
            String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
            extentTest.fail("<details><summary><b><font color=red>Exception occurred, click for more details:"
                    + "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>")
                    + "</details> \n");
            try {
                takeScreenshotFile(methodName);
                extentTest.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64()).build());
            } catch (IOException e) {
                extentTest.fail("Test failed, cannot attach the screenshot");
            }
            String logText = "<b>Test Method " + methodName + " Failed</b>";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
            extentTest.log(Status.FAIL, markup);
        } else if(ITestResult.SKIP == result.getStatus()) {
            String logText = "<b>Test Method " + methodName + " Skipped</b>";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
            extentTest.log(Status.SKIP, markup);
        }else if(ITestResult.SUCCESS == result.getStatus()) {
            String logText = "<b>Test Method " + methodName + " Successful</b>";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            extentTest.log(Status.PASS, markup);
        }
        driver.quit();
    }

    public static String getScreenshotName(String methodName) {
        Date d = new Date();
        String fileName = methodName + "_" + d.toString().replaceAll(":", "_").replaceAll(" ", "_") + ".png";
        return fileName;
    }

    public String takeScreenshotBase64() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }

    public String takeScreenshotFile(String methodName) {
        String fileName = getScreenshotName(methodName);
        String directory = System.getProperty("user.dir") + "/screenshots/";
        String path = directory + fileName;
        File failScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.move(failScreenshot, new File(path));
        } catch (IOException e) {
            System.out.println("Screenshot was not saved");
        }
        return path;
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuiteActions() {
        System.out.println("After Suit");
        extentReports.flush();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        return chromeOptions;
    }
}
