import mainClasses.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import testData.Data;

import java.util.List;

public class BlogTests extends BaseUI {
    private String currentUrl;

    @Test(groups = {"bvt", "regression"})
    public void testBlogPage() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.BLOG);
        currentUrl = blogPage.getCurrentUrl();
        softAssert.assertTrue(Data.blogPageTitleExpected.equals(blogPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Blog"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlBlogPage);
        softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void testArticlesAndTitles() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.BLOG);
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

}
