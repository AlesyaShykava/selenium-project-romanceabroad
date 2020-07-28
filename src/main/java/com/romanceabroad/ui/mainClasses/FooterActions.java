package com.romanceabroad.ui.mainClasses;

import com.romanceabroad.ui.locators.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public interface FooterActions {
    default List<WebElement> getFooterLinks(WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(Locators.FOOTER_MENU_LINKS, 6));
        List<WebElement> links = driver.findElements(Locators.FOOTER_MENU_LINKS);
        return links.subList(0, links.size()-1);
    }

    default String clickOnFooterLink(WebElement element, WebDriverWait wait) {
        BaseActions.waitThreadSleepSec(1);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        String linkText = element.getText();
        element.click();
        BaseActions.waitThreadSleepSec(1);
        return linkText;
    }
}
