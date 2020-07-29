package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.*;
import com.romanceabroad.ui.testData.Data;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

import java.util.List;

@Listeners(VideoListener.class)
public class BlogTests extends BaseUI {
    private String currentUrl;

    @Video(name = "testBlogPage")
    @Test(groups = {"bvt", "regression"})
    public void testBlogPage() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.BLOG);
        currentUrl = blogPage.getCurrentUrl();
        softAssert.assertTrue(Data.blogPageTitleExpected.equals(blogPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Blog"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlBlogPage);
        softAssert.assertAll();
    }

    @Video(name = "testArticlesAndTitles")
    @Test(groups = {"regression"})
    public void testArticlesAndTitles() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.BLOG);
        List<WebElement> links = blogPage.collectAllLinksOfArticles();
        for(int i = 0; i < links.size(); i++) {
            WebElement link = links.get(i);
            String textOfLink = link.getText();
            if(Data.blogPageLinksSkipCheck.contains(textOfLink)) {
               continue;
            }
            link.click();
            String titleOfArticle = blogPage.getH1Title();
            softAssert.equals(titleOfArticle.toLowerCase().contains(titleOfArticle.toLowerCase()));
            links = blogPage.collectAllLinksOfArticles();
        }
        softAssert.assertAll();
    }

    @Video(name = "checkFooterLinksBlogPage")
    @Test(groups = {"regression"})
    public void checkFooterLinks() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.BLOG);
        FooterTestsMethods.checkFooterLinks(blogPage);
    }
}
