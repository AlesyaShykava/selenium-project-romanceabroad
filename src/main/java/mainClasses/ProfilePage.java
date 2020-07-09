package mainClasses;

import locators.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BaseActions {
    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getUserName() {
        return driver.findElement(Locators.PROFILE_PAGE_USER_NAME).getText();
    }

    public int getAge() {
        String ageString = driver.findElement(Locators.PROFILE_PAGE_AGE).getText();
        return Integer.parseInt(ageString.substring(0, 2));
    }
}
