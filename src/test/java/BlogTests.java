import org.testng.annotations.Test;

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
}
