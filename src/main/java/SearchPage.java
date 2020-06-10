import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BaseActions {
    public SearchPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void performSearchBasedOnMinAndMaxAgeParameters(String minAgeForSearch, String maxAgeForSearch) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN));
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN), minAgeForSearch);
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MAX_AGE_DROPDOWN), maxAgeForSearch);
        driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_SEARCH_BUTTON).click();
    }

    public void setUpOrder(String orderValueDataCreated) {
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_ORDER_DROPDOWN), orderValueDataCreated);
    }

    public boolean isUserPresentInSearchResult(By searchPageUserFromSearch) {
        return driver.findElement(searchPageUserFromSearch).isDisplayed();
    }
}
