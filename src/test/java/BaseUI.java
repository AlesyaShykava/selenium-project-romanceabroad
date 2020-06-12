import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Collections;

public class BaseUI {
    protected String mainURl = "https://romanceabroad.com/";
    protected WebDriver driver;
    protected WebDriverWait wait;
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

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver(getChromeOptions());
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
        driver.manage().window().maximize();
        driver.get(mainURl);
    }

    @AfterMethod
    public void afterActions() {
        driver.quit();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        return chromeOptions;
    }
}
