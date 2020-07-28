package com.romanceabroad.ui.mainClasses;

import com.romanceabroad.ui.locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MediaPage extends BaseActions implements FooterActions {
    public MediaPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public List<WebElement> getListOfTabLinks() {
        return driver.findElements(Locators.MEDIA_PAGE_TAB_LINKS);
    }

    public void clickOnTabLink(Enums.MediaPageTabLinks link) {
        driver.findElement(link.getLocator()).click();
    }

    public String getTextFromVideoTab() {
        return driver.findElement(By.xpath("//div[@class='g-flatty-block']")).getText();
    }

}
