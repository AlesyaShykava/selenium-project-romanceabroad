package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.*;
import com.romanceabroad.ui.utils.EventReporter;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;

public class BaseUI {
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
    protected ProfilePage profilePage;
    protected ContactUsPage contactUsPage;

    @BeforeMethod(groups = {"smoke", "regression", "integration"}, alwaysRun = true)
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
        profilePage = new ProfilePage(driver, wait);
        contactUsPage = new ContactUsPage(driver, wait);
        driver.manage().window().maximize();
        driver.get(mainURl);
    }

    @AfterMethod(alwaysRun = true)
    public void afterActions(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus()) {recordFail(result);}
        driver.quit();
    }

    public void recordFail(ITestResult result) {
        TakesScreenshot camera = (TakesScreenshot)driver;
        File failScreenshot = camera.getScreenshotAs(OutputType.FILE);
        try {
            Files.move(failScreenshot, new File("screenshots/" + result.getName() + ".png"));
        } catch (IOException e) {
            System.out.println("Screenshot was not saved");
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        return chromeOptions;
    }
}