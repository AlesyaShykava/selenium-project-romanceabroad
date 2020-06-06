import org.testng.Assert;
import org.testng.annotations.Test;

public class BlogTests extends BaseUI {
    private String currentUrlBlog;

    @Test
    public void testBlogPage() {
        driver.findElement(Locators.BLOG_LINK).click();
        currentUrlBlog = driver.getCurrentUrl();
        Assert.assertTrue(Data.blogPageTitleExpected.equals(driver.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Blog"));
        Assert.assertEquals(currentUrlBlog, Data.expectedUrlBlogPage);
    }
}
