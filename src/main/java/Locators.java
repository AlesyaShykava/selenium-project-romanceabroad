import org.openqa.selenium.By;

public class Locators {

    //navbar elements on the 'Home' page
    public static final By NAVBAR = By.xpath("//ul[@class='navbar-nav']/li");
    public static final By SEARCH_LINK = By.xpath("//a[@href='https://romanceabroad.com/users/search']");
    public static final By HOME_LINK = By.xpath("//a[@href='#']");
    public static final By HOW_IT_WORKS_LINK = By.xpath("//a[@href='https://romanceabroad.com/content/view/how-it-works']");
    public static final By MEDIA_LINK = By.xpath("//a[@href='https://romanceabroad.com/media/index']");
    public static final By GIFTS_LINK = By.xpath("//a[@href='https://romanceabroad.com/store/category-sweets']");
    public static final By TOUR_TO_UKRAINE_LINK = By.xpath("//a[@href='https://romanceabroad.com/store/sweets/20-tour_to_ukraine']");
    public static final By BLOG_LINK = By.xpath("//a[@href='https://romanceabroad.com/content/view/blog']");
    public static final By SIGN_IN_LINK = By.xpath("//a[@href='https://romanceabroad.com/users/login_form']");
    public static final By JOIN_FOR_FREE_NOW_LINK = By.cssSelector("#show-registration-block");

    //other element on the 'Home' page
    public static final By FRAME_WITH_YOUTUBE_VIDEO = By.xpath("//iframe[@src='https://www.youtube.com/embed/RRECuJzm3IY?start=85']");
    public static final By YOUTUBE_VIDEO_PLAY_BUTTON = By.cssSelector(".ytp-large-play-button.ytp-button");

    //elements on the 'Sign In' page
    public static final By SIGN_IN_PAGE_EMAIL_FIELD = By.cssSelector("input[id='email'][type='text']");
    public static final By SIGN_IN_PAGE_PASSWORD_FIELD = By.cssSelector("input[id='password'][name='password']");
    public static final By SIGN_IN_PAGE_SUBMIT_BUTTON = By.cssSelector("button[type='submit']");
    public static final By SIGN_IN_PAGE_FORGOT_PASSWORD_LINK = By.xpath("//a[contains(text(), 'Forgot password')]");
    public static final By SIGN_IN_PAGE_ALERT_POP_UP_DISPLAYED = By.xpath("//div[@id='autogen_error_block'][not(contains(@style, 'display: none'))]");

    //elements on the 'Restore' page
    public static final By RESTORE_PAGE_EMAIL_FIELD = By.cssSelector("#email");
    public static final By RESTORE_PAGE_SUBMIT_BUTTON = By.cssSelector("input[type='submit']");
    public static final By RESTORE_PAGE_ALERT_POP_UP_DISPLAYED = By.xpath("//div[@id='autogen_error_block'][not(contains(@style, 'display: none'))]");

    //elements on the 'Registration' page
    public static final By REGISTRATION_FORM_EMAIL_FIELD = By.xpath("//input[@id='email']");
    public static final By REGISTRATION_FORM_PASSWORD_FIELD = By.xpath("//input[@id='password']");
    public static final By REGISTRATION_FORM_PAGINATION = By.xpath("//div[@class='paginator']//i[@data-page='3']");
    public static final By REGISTRATION_FORM_INCORRECT_EMAIL_ALERT = By.xpath("//input[@id='email']//following-sibling::div[@class='tooltip']");
    public static final By REGISTRATION_FORM_INCORRECT_PASSWORD_ALERT = By.xpath("//input[@id='password']//following-sibling::div[@class='tooltip']");
    public static final By REGISTRATION_FORM_NEXT_BUTTON = By.xpath("//button[@data-action='next-page'][text()='Next']");
    public static final By REGISTRATION_FORM_NICKNAME_TEXT_FIELD = By.xpath("//input[@id='nickname']");
    public static final By REGISTRATION_FORM_PHONE_TEXT_FIELD = By.xpath("//input[@name='data[phone]']");
    public static final By REGISTRATION_FORM_DAY_SELECT_LINK = By.xpath("//div[@id='daySelect']/a");
    public static final By REGISTRATION_FORM_DAY_14 = By.xpath("//a[@data-action='change-datepicker'][@data-value='14']");
    public static final By REGISTRATION_FORM_MONTH_SELECTION_CARET = By.xpath("//div[@id='monthSelect']//span[@class='caret']");
    public static final By REGISTRATION_FORM_MONTH_DECEMBER = By.xpath("//li[@data-handler='selectMonth'][@data-value='11']/a");
    public static final By REGISTRATION_FORM_YEAR_SELECTION_CARET = By.xpath("//div[@id='yearSelect']//span[@class='caret']");
    public static final By REGISTRATION_FORM_YEAR_1990 = By.xpath("//li[@data-handler='selectYear'][@data-value='1990']/a");
    public static final By REGISTRATION_FORM_TERMS_AND_CONDITIONS_CHECKBOX = By.xpath("//input[@id='confirmation']");

    //elements on 'Search' page
    public static final By SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN = By.xpath("//select[@id='age_min']");
    public static final By SEARCH_PAGE_SEARCH_PARAMETERS_MAX_AGE_DROPDOWN = By.xpath("//select[@id='age_max']");
    public static final By SEARCH_PAGE_SEARCH_PARAMETERS_SEARCH_BUTTON = By.xpath("//input[@id='main_search_button_user_advanced']");
    public static final By SEARCH_PAGE_ORDER_DROPDOWN = By.cssSelector("select[id*='sorter-select']");
    public static final By SEARCH_PAGE_USER_FROM_SEARCH = By.xpath("//a[@href='https://romanceabroad.com/users/view/330/profile'][@class='g-users-gallery__name']");
    public static final By SEARCH_PAGE_SEARCH_RESULT_WOMAN_SUMMARY = By.xpath("//div[@class='text-overflow'][a]");
    public static final By SEARCH_PAGE_PAGINATION_NEXT_BUTTON = By.xpath("//li[@class='next']/a[@data-page]");

    //elements with common locators on different pages

}
