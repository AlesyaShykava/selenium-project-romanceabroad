package com.romanceabroad.ui;

import com.aventstack.extentreports.Status;
import com.romanceabroad.ui.locators.Locators;
import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.reportUtil.Reports;
import com.romanceabroad.ui.testData.Data;
import com.romanceabroad.ui.testData.DataProviders;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Listeners(VideoListener.class)
public class SearchTests extends BaseUI {
    private String currentUrl;
    private Random random = new Random();
    public static final boolean TC801 = false;
    public static final boolean TC802 = false;
    public static final boolean TC803 = false;
    public static final boolean TC804 = false;
    public static final boolean TC805 = false;
    public static final boolean TC806 = false;
    public static final boolean TC807 = false;
    public static final boolean TC808 = false;
    public static final boolean TC809 = true;
    public static final boolean TC810 = false;

    @Video(name = "testSearchPageTC801")
    @Test(groups = {"smoke", "regression"}, enabled = TC801)
    public void testSearchPageTC801() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        currentUrl = searchPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlSearchPage);
        softAssert.assertTrue(Data.searchPageTitleExpected.equals(searchPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Search"));
        softAssert.assertAll();
    }

    @Video(name = "testSearchAndOrderTC802")
    @Test(groups = {"smoke", "regression"}, enabled = TC802, dataProviderClass = DataProviders.class, dataProvider = "minMaxAgeOrderUserInfoDataSet")
    public void testSearchAndOrderTC802(Integer minAge, Integer maxAge, String order, String womanSummary) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParametersByValue(minAge, maxAge);
        searchPage.setUpOrderByText(order);
        boolean expectedUserFromSearchDisplayed = searchPage.getListOfUserInfoNameAndAgeFirstPage().contains(womanSummary);
        Assert.assertTrue(expectedUserFromSearchDisplayed);
    }

    @Video(name = "checkPeopleFoundNumberTC803")
    @Test(groups = {"regression"}, enabled = TC803, dataProviderClass = DataProviders.class, dataProvider = "minMaxAgeDataSet")
    public void checkPeopleFoundNumberTC803(Integer minAge, Integer maxAge) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParametersByValue(minAge, maxAge);
        List<String> listWomanSummary = searchPage.getListOfUserInfoNameAndAgeAllPages();
        String peopleFoundTitle = searchPage.getPeopleFoundTitle();
        if(!peopleFoundTitle.contains(Integer.toString(listWomanSummary.size()))) {
            Assert.fail("The incorrect number of founded people is displayed on the title");
        }
    }

    @Video(name = "checkSearchParametersMinAgeValuesTC804")
    @Test(groups = {"regression"}, enabled = TC804)
    public void checkSearchParametersMinAgeValuesTC804(){
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
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

    @Video(name = "checkSummaryOnSearchPageCorrespondToInfoOnProfilePageTC805")
    @Test(groups = {"regression"}, enabled = TC805)
    public void checkSummaryOnSearchPageCorrespondToInfoOnProfilePageTC805(){
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        boolean isSearchNeeded = true;
        while (isSearchNeeded) {
            searchPage.selectRandomOptionFromDropDown(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN, "minAge");
            searchPage.clickOnSearchButton();
            isSearchNeeded = searchPage.getListOfUserInfoNameAndAgeFirstPage().isEmpty();
            if(isSearchNeeded) continue;

            int randomIndex = random.nextInt(searchPage.getListOfUserInfoNameAndAgeFirstPage().size());

            String womanSummary = searchPage.getUserInfoByIndex(randomIndex);
            String userNameSearchPage = womanSummary.substring(0, womanSummary.indexOf(", "));
            int ageSearchPage = Integer.parseInt(womanSummary.substring(womanSummary.length() - 2));

            searchPage.clickOnProfileLinkByIndex(randomIndex);
            String userNameProfilePage = userProfilePage.getUserName();
            int ageProfilePage = userProfilePage.getAge();

            Assert.assertEquals(userNameSearchPage, userNameProfilePage, String.format("Expected name: %s, actual name: ", userNameSearchPage, ageProfilePage));
            Assert.assertEquals(ageSearchPage, ageProfilePage, String.format("Expected age: %d, actual age: %d", ageSearchPage, ageProfilePage));
            break;
        }
    }

    @Video(name = "checkAllOrderOptionCanBeSelectedTC806")
    @Test(groups = {"regression"},  enabled = TC806)
    public void checkAllOrderOptionCanBeSelectedTC806() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        int sizeOrderDropDown = searchPage.getSizeDropDownList(Locators.SEARCH_PAGE_ORDER_DROPDOWN);
        for(int i = 0; i < sizeOrderDropDown; i++) {
            searchPage.selectFromDropDownListByIndex(Locators.SEARCH_PAGE_ORDER_DROPDOWN, i);
            boolean isOptionDisplayed = searchPage.isElementDisplayed(Locators.SEARCH_PAGE_ORDER_OPTIONS);
            Assert.assertTrue(isOptionDisplayed);
        }
    }

    @Video(name = "testScreenshotSearchPageTC807")
    @Test(groups = {"regression"},  enabled = TC807)
    public void testScreenshotSearchPageTC807() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(tempFile, new File("screenshots/searchPageScreenshot.png"));
        } catch (IOException e) {
            System.out.println("Screenshot was not saved");
        }
    }

    @Video(name = "checkAgeInSearchResultCorrespondToSelectedMinAndMaxAgesTC808")
    @Test(groups = {"regression"}, enabled = TC808, dataProviderClass = DataProviders.class, dataProvider = "minMaxAgeDataSet")
    public void checkAgeInSearchResultCorrespondToSelectedMinAndMaxAgesTC808(Integer minAge, Integer maxAge) {
        Reports.log(Status.INFO, String.format("Test Data: minAge - %d, maxAge - %d", minAge, minAge));
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParametersByValue(minAge, maxAge);
        List<String> listWomanSummary = searchPage.getListOfUserInfoNameAndAgeAllPages();
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

    @Video(name = "searchDifferentResultsTC809")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "minMaxAgeOrderDataSet", groups = {"regression"},  enabled = TC809)
    public void searchDifferentResultsTC809(String minAge, String maxAge, String searchOrder) {
        int minExpected = Integer.parseInt(minAge);
        int maxExpected = Integer.parseInt(maxAge);
        Reports.log(Status.INFO, String.format("Test Data: minAge - %d, maxAge - %d, searchOrder - %s", minExpected, maxExpected, searchOrder));

//        if(runningConfiguration.getName().equals("notSauceMobile")) homePage.clickMobileNavbarButton();

        homePage.clickMobileNavbarButton1(runningConfiguration.getName());
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);

        if(runningConfiguration.getName().equals("notSauceMobile")) searchPage.clickMobileSearchParametersButton();

        searchPage.selectMinAgeByValue(minExpected);
        searchPage.selectMaxAgeByValue(maxExpected);
        searchPage.setUpOrderByText(searchOrder);
        searchPage.clickOnSearchButton();
        List<String> listOfUserInfoAllFirstPage = searchPage.getListOfUserInfoAllFirstPage();

        for (int i = 0; i < listOfUserInfoAllFirstPage.size(); i++) {
            if(i % 2 == 0) {
                String info = listOfUserInfoAllFirstPage.get(i);
                String[] splitedPhase = info.split(", ");
                int ageActual = Integer.parseInt(splitedPhase[1]);

                if(ageActual >= minExpected && ageActual <= maxExpected) {
                    Reports.log(Status.INFO, "This age: " + ageActual + " is correct");
                } else {
                    Reports.log(Status.FAIL, "Wrong age: " + ageActual);
                }
            }
            searchPage.waitThreadSleepSec(1);
            listOfUserInfoAllFirstPage = searchPage.getListOfUserInfoAllFirstPage();
        }
    }

    @Video(name = "checkFooterLinksSearchPageTC810")
    @Test(groups = {"regression"}, enabled = TC810)
    public void checkFooterLinksTC810() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        FooterTestsMethods.checkFooterLinks(searchPage);
    }
}
