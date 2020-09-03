package com.romanceabroad.ui;

import com.aventstack.extentreports.Status;
import com.romanceabroad.ui.mainClasses.*;
import com.romanceabroad.ui.reportUtil.EventReporter;
import com.romanceabroad.ui.reportUtil.Reports;
import com.romanceabroad.ui.testData.Data;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseUI {
    protected WebDriver driver;
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
    protected RunningConfiguration runningConfiguration;
    protected Browser testBrowser;

    protected enum RunningConfiguration {
        SAUCE("sauce"),
        NOT_SAUCE_WEB("notSauceWeb"),
        NOT_SAUCE_MOBILE("notSauceMobile");

        private String name;

        RunningConfiguration(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    protected enum Browser {
        CHROME, FIREFOX, IE, REMOTE_CHROME, REMOTE_FIREFOX
    }

    @BeforeMethod(groups = {"smoke", "regression", "integration", "negative"}, alwaysRun = true)
    @Parameters({"browser", "version", "platform", "configuration", "deviceName", "testEnv"})
    public void setup(@Optional("Chrome") String browser,
                      @Optional String version,
                      @Optional String platform,
                      @Optional("not_sauce_web") String configuration,
                      @Optional String deviceName,
                      @Optional("qa") String testEnv,
                      Method method) throws MalformedURLException {
        Reports.startTest(method.getDeclaringClass().getName() + " : " + method.getName());
        Reports.log(Status.INFO, "Starting execution of test case on the browser: " + browser);

        runningConfiguration = RunningConfiguration.valueOf(configuration.toUpperCase());
        if (runningConfiguration != RunningConfiguration.SAUCE) {
            testBrowser = Browser.valueOf(browser.toUpperCase());
        }

        switch (runningConfiguration) {

            case NOT_SAUCE_WEB:
                switch (testBrowser) {
                    case CHROME:
                        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
                        driver = new ChromeDriver(getChromeOptions());
                        //driver.get("chrome://settings/clearBrowserData");
                        break;
                    case FIREFOX:
                        System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
                        driver = new FirefoxDriver();
                        break;
                    case IE:
                        System.setProperty("webdriver.ie.driver", "resources/IEDriverServer");
                        driver = new InternetExplorerDriver();
                        driver.manage().deleteAllCookies();
                        break;
                    case REMOTE_CHROME:
                        System.out.println("Remote Chrome");
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless");
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
                        break;
                    case REMOTE_FIREFOX:
                        System.out.println("Remote Firefox");
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.addArguments("--headless");
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
                        break;
                }
                driver.manage().window().maximize();
                break;

            case NOT_SAUCE_MOBILE:
                System.out.println("Mobile Chrome");
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", deviceName);
                ChromeOptions chromeOptions = getChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
                driver = new ChromeDriver(chromeOptions);
                //driver.get("chrome://settings/clearBrowserData");
                break;

            case SAUCE:
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("username", "ASocolova");
                capabilities.setCapability("accessKey", "4a2d7600-9d2e-4ce6-ac84-574b28fb76ab");
                capabilities.setCapability("browserName", browser);
                capabilities.setCapability("platform", platform);
                capabilities.setCapability("version", version);
                capabilities.setCapability("name", method.getName());
                driver = new RemoteWebDriver(
                        new URL("http://" + System.getenv("SAUCE_USERNAME") + ":" + System.getenv("SAUCE_ACCESS_KEY") + "@ondemand.saucelabs.com:80/wd/hub"),
                        capabilities);
                break;
        }

        wait = new WebDriverWait(driver, 40);
        homePage = new HomePage(driver, wait);
        blogPage = new BlogPage(driver, wait);
        giftsPage = new GiftsPage(driver, wait);
        howWeWorkPage = new HowWeWorkPage(driver, wait);
        mediaPage = new MediaPage(driver, wait);
        registrationModal = new RegistrationModal(driver, wait);
        restorePasswordPage = new RestorePasswordPage(driver, wait);
        signInModal = new SignInModal(driver, wait);
        searchPage = new SearchPage(driver, wait);
        tourToUkrainePage = new TourToUkrainePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        userProfilePage = new UserProfilePage(driver, wait);
        contactUsPage = new ContactUsPage(driver, wait);

        PageFactory.initElements(driver, homePage);

        if(testEnv.equals("qa")) {
            driver.get(Data.qaURl);
        } else if (testEnv.equals("uat")) {
            driver.get(Data.uatURl);
        } else if (testEnv.equals("dev")) {
            driver.get(Data.devURl);
        } else if (testEnv.equals("prod")) {
            driver.get(Data.prodURl);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodActions(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus()) {
            Reports.failedTest(driver, result);
        } else if(ITestResult.SKIP == result.getStatus()) {
            Reports.skippedTest(result);
        }else if(ITestResult.SUCCESS == result.getStatus()) {
            Reports.passedTest(result);
        }

        if(runningConfiguration == RunningConfiguration.SAUCE) {
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        }

        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuiteActions() {
        Reports.stopReport();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        return chromeOptions;
    }
}
