package mainClasses;

import locators.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BaseActions {
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void checkErrorBlockIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SIGN_IN_ALERT_POP_UP_DISPLAYED));
    }
}
