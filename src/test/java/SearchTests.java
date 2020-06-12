import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testSearchPage() {
        homePage.clickOnLink(Locators.SEARCH_LINK);
        currentUrl = searchPage.getCurrentUrl();
        Assert.assertEquals(currentUrl, Data.expectedUrlSearchPage);
        Assert.assertTrue(Data.searchPageTitleExpected.equals(searchPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Search"));
    }

    @Test
    public void testSearchAndOrder() {
        homePage.clickOnLink(Locators.SEARCH_LINK);
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(Data.minAgeForSearch, Data.maxAgeForSearch);
        searchPage.setUpOrder(Data.orderValueDataCreated);
        boolean expectedUserFromSearchDisplayed = searchPage.isUserPresentInSearchResult(Locators.SEARCH_PAGE_USER_FROM_SEARCH);
        Assert.assertTrue(expectedUserFromSearchDisplayed);
    }

    @Test
    public void checkAgeInSearchResult() {
        homePage.clickOnLink(Locators.SEARCH_LINK);
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(Data.minAgeForSearch, Data.maxAgeForSearch);
        List<String> listWomanSummary = searchPage.getListOfWomenSummary();
        for (int i = 0; i < listWomanSummary.size(); i++) {
            String womanSummary = listWomanSummary.get(i);
            int ageOfWoman = Integer.parseInt(womanSummary.substring(womanSummary.length() - 2));
            boolean isAgeCorrect = ageOfWoman >= Integer.parseInt(Data.minAgeForSearch) && ageOfWoman <= Integer.parseInt(Data.maxAgeForSearch);
            Assert.assertTrue(isAgeCorrect);
        }
    }
}
