package com.romanceabroad.ui.testData;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataProvidersSauceLabs {

    @DataProvider(name = "RegistrationDataSetWrongCredentials")
    public static Object[][] getRegistrationDataSetIncorrectEmailPassword() {
        return new Object[][] {
                {"@abc.abc,1234","1234"},
        };
    }

}
