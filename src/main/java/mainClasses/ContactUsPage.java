package mainClasses;

import locators.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsPage extends BaseActions {
    public ContactUsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void selectReasonByText(String visibleText) {
        selectFromDropDownListByText(Locators.CONTACT_US_PAGE_REASON_DROPDOWN, visibleText);
    }

    public void fillInEmail(String correctEmail) {
        driver.findElement(Locators.CONTACT_US_PAGE_USER_EMAIL_INPUT).sendKeys(correctEmail);
    }

    public void fillInName(String userName) {
        driver.findElement(Locators.CONTACT_US_PAGE_USER_NAME_INPUT).sendKeys(userName);
    }

    public void fillInSubject(String subject) {
        driver.findElement(Locators.CONTACT_US_PAGE_SUBJECT_INPUT).sendKeys(subject);
    }

    public void fillInMessage(String message) {
        driver.findElement(Locators.CONTACT_US_PAGE_MESSAGE_TEXTAREA).sendKeys(message);
    }


}
