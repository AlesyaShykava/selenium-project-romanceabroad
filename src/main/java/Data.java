import org.testng.annotations.DataProvider;

public class Data {

    //'Search' page
    public static final String searchPageTitleExpected = "Meet single Ukrainian women online: dating and marriage at Marry Ukrainian Lady";
    public static final String expectedUrlSearchPage = "https://romanceabroad.com/users/search";
    public static final String searchPageOrderDropDownValueDefault = "default";
    public static final String searchPageOrderDropDownValueName = "name";
    public static final String searchPageOrderDropDownValueViewsCount = "views_count";
    public static final String searchPageOrderDropDownValueDataCreated = "date_created";

    //test data for search page
    public static final int searchParametersMinAgeExpected = 18;
    public static final int searchParametersMaxAgeExpected = 80;

    @DataProvider(name = "minMaxAgeDataSet")
    public static Object[][] getMinMaxAgeDataSet() {
        Object[][] data = new Integer[3][2];
        data[0][0] = 20;    data[0][1] = 30;
        data[1][0] = 30;    data[1][1] = 40;
        data[2][0] = 40;    data[2][1] = 50;
        return data;
    }

    @DataProvider(name = "minMaxAgeOrderWomanSummaryDataSet")
    public static Object[][] getMinMaxAgeOrderWomanSummaryDataSet() {
        Object[][] data = new Object[3][4];
        data[0][0] = 20;    data[0][1] = 30;    data[0][2] = searchPageOrderDropDownValueName;            data[0][3] = "Tanya, 25";
        data[1][0] = 30;    data[1][1] = 40;    data[1][2] = searchPageOrderDropDownValueViewsCount;      data[1][3] = "Solomia Wyshnevetska, 31";
        data[2][0] = 40;    data[2][1] = 50;    data[2][2] = searchPageOrderDropDownValueDataCreated;     data[2][3] = "Sveta, 45";
        return data;
    }

    //'Home' page
    public static final String homePageTitleExpected = "The Best Free Ukrainian Dating Site| Romanceabroad.Com";
    public static final String expectedUrlHomePage = "https://romanceabroad.com/#";
    public static final int expectedNumberOfLinksInNavbar = 8;

    //'How It Works' page
    public static final String howItWorksPageTitleExpected = "Real Ukrainian women for marriage: legitimate dating site Marry Ukrainian Lady";
    public static final String expectedUrlHowItWorksPage = "https://romanceabroad.com/content/view/how-it-works";

    //'Media' page
    public static final String mediaPageTitleExpected = "Ukrainian women photos: ladies pictures at Marry Ukrainian Lady";
    public static final String expectedUrlMediaPage = "https://romanceabroad.com/media/index";

    //'Gifts' page
    public static final String giftsPageTitleExpected = "Pilot Group: Gift store - Categories";
    public static final String expectedUrlGiftsPage = "https://romanceabroad.com/store/category-sweets";

    //'Tour To Ukraine' page
    public static final String tourToUkrainePageTitleExpected = "Pilot Group: Gift store - Item";
    public static final String expectedUrlTourToUkrainePage = "https://romanceabroad.com/store/sweets/20-tour_to_ukraine";

    //'Blog' page
    public static final String blogPageTitleExpected = "Blog";
    public static final String expectedUrlBlogPage = "https://romanceabroad.com/content/view/blog";

    //'Restore Password' page
    public static final String expectedUrlRestorePage = "https://romanceabroad.com/users/restore";

    //'Login' page
    public static final String expectedUrlLoginPage = "https://romanceabroad.com/users/login_form";

    //'Registration' form
    public static final String registrationFormEmailIncorrectAlertMessageExpected = "Email is incorrect, please try again";
    public static final String registrationFormPasswordIncorrectAlertMessageExpected = "Please choose a password that is at least 6 characters long";

    //test data for registration flow
    public static final String incorrectTestData = "abc";
    public static final String correctEmail = "example@abc.abc";
    public static final String correctPassword = "123456";
    public static final String correctNickName = "nick";
    public static final String correctPhone = "123456789";
    public static final String day = "14";
    public static final String month = "May";
    public static final String year = "1990";
    public static final String location = "Sydney, State of New South Wales, Australia";
    public static final String locationCity = "Sydney";

    //'Sign In' form
    public static final String signInFormTitleExpected = "The Best Free Ukrainian Dating Site| Romanceabroad.Com";

    //'Contact Us' page
    public static final String contactUsReasonDropDownTextPreSales = "Pre-Sales Questions";
    public static final String contactUsReasonDropDownTextTechnicalSupport = "Technical support";
    public static final String contactUsReasonDropDownTextOther = "Other";

    //test data for contact us page
    public static final String email = "example@abc.abc";
    public static final String name = "nick";
    public static String subject = "Technical support";
    public static String message = "Technical support";

    //error messages for test
    public static final String incorrectTitleTestMessageFormat = "Incorrect title of '%s' Page";
}
