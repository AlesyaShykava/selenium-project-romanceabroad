import org.testng.annotations.Test;

public class BlogTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testBlogPage() {
        homePage.clickOnLink(Locators.BLOG_LINK);
        currentUrl = blogPage.getCurrentUrl();
        softAssert.assertTrue(Data.blogPageTitleExpected.equals(blogPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Blog"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlBlogPage);
        softAssert.assertAll();
    }
}
