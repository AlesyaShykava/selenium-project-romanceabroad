package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.BaseActions;
import com.romanceabroad.ui.mainClasses.FooterActions;
import com.romanceabroad.ui.testData.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class FooterTestsMethods {

    public static void checkFooterLinks(FooterActions page) {
        WebDriver driver = ((BaseActions)page).getDriver();
        WebDriverWait wait = ((BaseActions)page).getWait();
        List<WebElement> links = page.getFooterLinks(driver, wait);
        String pageUrl = driver.getCurrentUrl();
        String currentTitle;
        for (int i = 0; i < links.size(); i++) {
            String linkText = page.clickOnFooterLink(links.get(i), wait);
            if(linkText.contains("Contact us")) {
                currentTitle = ((BaseActions)page).getH1Title();
                Assert.assertTrue(Data.contactUsH1TitleExpected.equals(currentTitle), String.format("Expected Title: %s, but found: %s", Data.contactUsH1TitleExpected, currentTitle));
            } else if(linkText.contains("Sitemap")) {
                currentTitle = ((BaseActions)page).getH1Title();
                Assert.assertTrue(Data.siteMapH1TitleExpected.equals(currentTitle), String.format("Expected Title: %s, but found: %s", Data.siteMapH1TitleExpected, currentTitle));
            } else if(linkText.contains("How it works")) {
                currentTitle = ((BaseActions)page).getH1Title();
                Assert.assertTrue(Data.howItWorksPageH1TitleExpected.equals(currentTitle), String.format("Expected Title: %s, but found: %s", Data.howItWorksPageH1TitleExpected, currentTitle));
            } else if(linkText.contains("Privacy")) {
                currentTitle = ((BaseActions)page).getH1Title();
                Assert.assertTrue(Data.privacyH1TitleExpected.equals(currentTitle), String.format("Expected Title: %s, but found: %s", Data.privacyH1TitleExpected, currentTitle));
            } else if(linkText.contains("Terms of use")) {
                currentTitle = ((BaseActions)page).getH1Title();
                Assert.assertTrue(Data.termsOfUseH1TitleExpected.equals(currentTitle), String.format("Expected Title: %s, but found: %s", Data.termsOfUseH1TitleExpected, currentTitle));
            }
            driver.get(pageUrl);
            links = page.getFooterLinks(driver, wait);
        }
    }
}
