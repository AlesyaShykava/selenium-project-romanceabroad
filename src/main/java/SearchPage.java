import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BaseActions {
    public SearchPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void performSearchBasedOnMinAndMaxAgeParameters(String minAgeForSearch, String maxAgeForSearch) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN));
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN), minAgeForSearch);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MAX_AGE_DROPDOWN));
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MAX_AGE_DROPDOWN), maxAgeForSearch);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }
        driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='autogen_loading_block'][contains(@style, 'display: none')]")));
    }

    public void setUpOrder(String orderValueDataCreated) {
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_ORDER_DROPDOWN), orderValueDataCreated);
    }

    public boolean isUserPresentInSearchResult(By searchPageUserFromSearch) {
        return driver.findElement(searchPageUserFromSearch).isDisplayed();
    }

    public boolean pagination_isNextButtonPresent() {
        return driver.findElements(Locators.SEARCH_PAGE_PAGINATION_NEXT_BUTTON).size() != 0;
    }

    public void pagination_clickOnNextButton() {
        driver.findElement(By.xpath("//li[@class='next']/a[@data-page]")).click();
    }

    public List<String> getListOfWomenSummary() {
        List<WebElement> listWomanSummary = driver.findElements(Locators.SEARCH_PAGE_SEARCH_RESULT_WOMAN_SUMMARY);
        if(pagination_isNextButtonPresent()) {
            pagination_clickOnNextButton();
            List<WebElement> listWomanSummaryPageResult = driver.findElements(Locators.SEARCH_PAGE_SEARCH_RESULT_WOMAN_SUMMARY);
            listWomanSummary.addAll(listWomanSummaryPageResult);
        }
        List<String> result = new ArrayList<>();
        for(int i = 0; i < listWomanSummary.size(); i++) {
            result.add(listWomanSummary.get(i).getText());
        }
        return result;
    }
}
