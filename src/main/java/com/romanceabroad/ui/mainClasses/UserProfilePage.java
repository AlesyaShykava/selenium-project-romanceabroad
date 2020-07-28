package com.romanceabroad.ui.mainClasses;

import com.romanceabroad.ui.locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class UserProfilePage extends BaseActions implements FooterActions {
    public UserProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getUserName() {
        return driver.findElement(Locators.PROFILE_PAGE_USER_NAME).getText();
    }

    public int getAge() {
        String ageString = driver.findElement(Locators.PROFILE_PAGE_AGE).getText();
        return Integer.parseInt(ageString.substring(0, 2));
    }

    public String getActiveTab() {
        String activeTabName = null;
        List<WebElement> webElements = driver.findElements(Locators.PROFILE_PAGE_TABS_LIST);
        for(int i = 0; i < webElements.size(); i++) {
            String classAttribute = webElements.get(i).getAttribute("class");
            if(!classAttribute.isEmpty() && classAttribute.equals("active")) {
                TabLinks[] tabLinks = TabLinks.values();
                activeTabName = tabLinks[i].name();
                break;
            }
        }
        return activeTabName;
    }

    public void clickOnTab(TabLinks tabLink) {
        driver.findElement(tabLink.getLocator()).click();
    }

    public enum TabLinks {
        Wall(Locators.PROFILE_PAGE_TAB_LINK_WALL),
        Profile(Locators.PROFILE_PAGE_TAB_LINK_PROFILE),
        Gallery(Locators.PROFILE_PAGE_TAB_LINK_GALLERY);

        private By locator;

        public By getLocator() {
            return locator;
        }

        TabLinks(By locator) {
            this.locator = locator;
        }
    }
}
