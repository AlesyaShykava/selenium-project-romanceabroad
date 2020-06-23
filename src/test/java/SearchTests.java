import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class SearchTests extends BaseUI {
    private String currentUrl;
    Random random = new Random();

    @Test
    public void testSearchPage() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        currentUrl = searchPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlSearchPage);
        softAssert.assertTrue(Data.searchPageTitleExpected.equals(searchPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Search"));
        softAssert.assertAll();
    }

    @Test(dataProviderClass = Data.class, dataProvider = "minMaxAgeOrderWomanSummaryDataSet")
    public void testSearchAndOrder(Integer minAge, Integer maxAge, String order, String womanSummary) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(minAge, maxAge);
        searchPage.setUpOrder(order);
        boolean expectedUserFromSearchDisplayed = searchPage.getListOfWomenSummaryFirstPage().contains(womanSummary);
        Assert.assertTrue(expectedUserFromSearchDisplayed);
    }

    @Test(dataProviderClass = Data.class, dataProvider = "minMaxAgeDataSet")
    public void checkAgeInSearchResultCorrespondGivenParameters(Integer minAge, Integer maxAge) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(minAge, maxAge);
        List<String> listWomanSummary = searchPage.getListOfWomenSummaryAll();
        if(listWomanSummary.size() > 0) {
            for (int i = 0; i < listWomanSummary.size(); i++) {
                String womanSummary = listWomanSummary.get(i);
                int ageOfWoman = Integer.parseInt(womanSummary.substring(womanSummary.length() - 2));
                boolean isAgeCorrespondGivenParameters = ageOfWoman >= minAge && ageOfWoman <= maxAge;
                softAssert.assertTrue(isAgeCorrespondGivenParameters, String.format("Expected between: %d - %d, actual: %d", minAge, maxAge, ageOfWoman));
            }
        } else {
            System.out.println("Search result is empty, please select another data for test");
        }
        softAssert.assertAll();
    }

    @Test(dataProviderClass = Data.class, dataProvider = "minMaxAgeDataSet")
    public void checkPeopleFoundNumberOnTheTitleOfResultPage(Integer minAge, Integer maxAge) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(minAge, maxAge);
        List<String> listWomanSummary = searchPage.getListOfWomenSummaryAll();
        String peopleFoundTitle = searchPage.getPeopleFoundTitle();
        if(!peopleFoundTitle.contains(Integer.toString(listWomanSummary.size()))) {
            Assert.fail("The incorrect number of founded people is displayed on the title");
        }
    }

    @Test
    public void checkSearchParametersMinAgeValues(){
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        List<Integer> minAgeValues = searchPage.getMinAgeDropDownValues();
        int sizeOfListWithMinAgeValues = minAgeValues.size();
        boolean isNumberOfValuesEqualsToExpected = (sizeOfListWithMinAgeValues == Data.searchParametersMaxAgeExpected - Data.searchParametersMinAgeExpected + 1);
        Assert.assertTrue(isNumberOfValuesEqualsToExpected, "Number of options in min_age dropdown does not correspond to expected");
        softAssert.assertTrue(minAgeValues.get(0) == Data.searchParametersMinAgeExpected);
        softAssert.assertTrue(minAgeValues.get(sizeOfListWithMinAgeValues - 1) == Data.searchParametersMaxAgeExpected);
        for(int i = 0; i < sizeOfListWithMinAgeValues; i++) {
            softAssert.assertTrue(minAgeValues.get(i) == Data.searchParametersMinAgeExpected + i);
        }
        softAssert.assertAll();
    }

    @Test
    public void checkSummaryOnSearchPageCorrespondToInfoOnProfilePage(){
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        List<Integer> minAgeValues = searchPage.getMinAgeDropDownValues();
        boolean isSearchResultEmpty = true;
        while (isSearchResultEmpty) {
            int randomMinAge = minAgeValues.get(random.nextInt(minAgeValues.size()));
            searchPage.performSearchBasedOnMinAndMaxAgeParameters(randomMinAge, Data.searchParametersMaxAgeExpected);
            isSearchResultEmpty = searchPage.getListOfWomenSummaryFirstPage().isEmpty();
            if(isSearchResultEmpty) continue;

            int randomIndex = random.nextInt(searchPage.getListOfWomenSummaryFirstPage().size());

            String womanSummary = searchPage.getWomanSummaryByIndex(randomIndex);
            String userNameSearchPage = womanSummary.substring(0, womanSummary.indexOf(", "));
            int ageSearchPage = Integer.parseInt(womanSummary.substring(womanSummary.length() - 2));

            searchPage.clickOnProfileLinkByIndex(randomIndex);
            String userNameProfilePage = profilePage.getUserName();
            int ageProfilePage = profilePage.getAge();

            Assert.assertEquals(userNameSearchPage, userNameProfilePage, String.format("Expected name: %s, actual name: ", userNameSearchPage, ageProfilePage));
            Assert.assertEquals(ageSearchPage, ageProfilePage, String.format("Expected age: %d, actual age: %d", ageSearchPage, ageProfilePage));
            break;
        }
    }

    @Test
    public void getScreenshotSearchPage() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        File tempFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(tempFile, new File("screenshots/searchPageScreenshot.png"));
        } catch (IOException e) {
            System.out.println("Screenshot was not saved");
        }
    }
}
