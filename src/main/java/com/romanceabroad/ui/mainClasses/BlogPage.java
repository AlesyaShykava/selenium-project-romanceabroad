package com.romanceabroad.ui.mainClasses;

import com.romanceabroad.ui.locators.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BlogPage extends BaseActions {
    public BlogPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public List<WebElement> collectAllLinksOfArticles() {
        return driver.findElements(Locators.BLOG_LINK_OF_ARTICLES);
    }
}
