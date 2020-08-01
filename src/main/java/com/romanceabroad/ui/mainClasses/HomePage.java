package com.romanceabroad.ui.mainClasses;

import com.aventstack.extentreports.Status;
import com.romanceabroad.ui.locators.Locators;
import com.romanceabroad.ui.reportUtil.Reports;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BaseActions {

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public int getNumberOfLinksInNavbar() {
        return driver.findElements(Locators.NAVBAR).size();
    }

    public void clickOnLink(Enums.HomePageLinksOnHomePage link) {
        Reports.log(Status.INFO, String.format("Click on link on Home page: %s", link.toString()));
        driver.findElement(link.getLocator()).click();
        switch (link) {
            case SIGN_IN:
                wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SIGN_IN_EMAIL_FIELD));
                break;
            case SEARCH:
                wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN));
                break;
            case BLOG:
                wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.BLOG_LINK_OF_ARTICLES));
                break;
            case MEDIA:
                wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.MEDIA_PAGE_TAB_LINK_All));
                break;
        }
    }

    public void playYouTubeVideo() {
        scrollToElementUsingJS(Locators.FRAME_WITH_YOUTUBE_VIDEO);
        WebElement frame = driver.findElement(Locators.FRAME_WITH_YOUTUBE_VIDEO);
        driver.switchTo().frame(frame);
        driver.findElement(Locators.YOUTUBE_VIDEO_PLAY_BUTTON);
    }

}
