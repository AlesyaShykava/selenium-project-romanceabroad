package com.romanceabroad.ui.mainClasses;

import com.romanceabroad.ui.locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MediaPage extends BaseActions {
    public MediaPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public List<WebElement> getListOfTabLinks() {
        return driver.findElements(Locators.MEDIA_PAGE_TAB_LINKS);
    }

    public void clickOnTabLink(TabLinks link) {
        driver.findElement(link.getLocator()).click();
    }

    public enum TabLinks {
        All(Locators.MEDIA_PAGE_TAB_LINK_All),
        Photo(Locators.MEDIA_PAGE_TAB_LINK_PHOTO),
        Video(Locators.MEDIA_PAGE_TAB_LINK_VIDEO),
        Albums(Locators.MEDIA_PAGE_TAB_LINK_ALBUMS);

        private By locator;

        public By getLocator() {
            return locator;
        }

        TabLinks(By locator) {
                this.locator = locator;
            }
    }
}
