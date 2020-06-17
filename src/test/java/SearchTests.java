import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testSearchPage() {
        homePage.clickOnSearchLink();
        currentUrl = searchPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlSearchPage);
        softAssert.assertTrue(Data.searchPageTitleExpected.equals(searchPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Search"));
        softAssert.assertAll();
    }

    @Test
    public void testSearchAndOrder() {
        homePage.clickOnSearchLink();
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(Data.minAgeForSearch, Data.maxAgeForSearch);
        searchPage.setUpOrder(Data.orderValueDataCreated);
        boolean expectedUserFromSearchDisplayed = searchPage.isUserPresentInSearchResult(Locators.SEARCH_PAGE_USER_FROM_SEARCH);
        Assert.assertTrue(expectedUserFromSearchDisplayed);
    }

    @Test
    public void checkAgeInSearchResultCorrespondGivenParameters() {
        homePage.clickOnSearchLink();
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(Data.minAgeForSearch, Data.maxAgeForSearch);
        List<String> listWomanSummary = searchPage.getSearchResultListOfWomenSummary();
        if(listWomanSummary.size() > 0) {
            for (int i = 0; i < listWomanSummary.size(); i++) {
                String womanSummary = listWomanSummary.get(i);
                int ageOfWoman = Integer.parseInt(womanSummary.substring(womanSummary.length() - 2));
                boolean isAgeCorrespondGivenParameters = ageOfWoman >= Integer.parseInt(Data.minAgeForSearch) && ageOfWoman <= Integer.parseInt(Data.maxAgeForSearch);
                softAssert.assertTrue(isAgeCorrespondGivenParameters);
            }
        } else {
            System.out.println("Search result is empty, please select another data for test");
        }
        softAssert.assertAll();
    }

    @Test
    public void checkPeopleFoundNumberOnTheTitleOfResultPage() {
        homePage.clickOnSearchLink();
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(Data.minAgeForSearch, Data.maxAgeForSearch);
        List<String> listWomanSummary = searchPage.getSearchResultListOfWomenSummary();
        String peopleFoundTitle = searchPage.getPeopleFoundTitle();
        if(!peopleFoundTitle.contains(Integer.toString(listWomanSummary.size()))) {
            Assert.fail("The incorrect number of founded people is displayed on the title");
        }
    }

    @Test
    public void checkSearchParametersMinAgeValues(){
        homePage.clickOnSearchLink();
        List<Integer> minAgeValues = searchPage.getMinAgeDropDownValues();
        int sizeOfListWithMinAgeValues = minAgeValues.size();
        boolean isNumberOfValuesEqualsToExpected = (sizeOfListWithMinAgeValues == Data.searchParametersMaxAgeExpected - Data.searchParametersMinAgeExpected + 1);
        Assert.assertTrue(isNumberOfValuesEqualsToExpected, "Number of options in min_age dropdown does not correspond to expected");
        softAssert.assertTrue(minAgeValues.get(0) == Data.searchParametersMinAgeExpected, String.format("Min age value is not equals to expected %s", Data.searchParametersMinAgeExpected));
        softAssert.assertTrue(minAgeValues.get(sizeOfListWithMinAgeValues - 1) == Data.searchParametersMaxAgeExpected, String.format("Max age value is not equals to expected %s", Data.searchParametersMaxAgeExpected));
        for(int i = 0; i < sizeOfListWithMinAgeValues; i++) {
            softAssert.assertTrue(minAgeValues.get(i) == Data.searchParametersMinAgeExpected + i);
        }
        softAssert.assertAll();
    }
}
