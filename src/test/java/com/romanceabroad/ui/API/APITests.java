package com.romanceabroad.ui.API;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class APITests {
    @Test
    public void getCall() {
        Response response = given().
                param("page", 0).
                when().
                get("http://localhost:3000/posts").
                then().
                body("[0].id", equalTo(1)).
                body("[0].id", notNullValue()).
                body("[0].title", equalTo("json-server")).
                body("[0].author", equalTo("typicode")).
                statusCode(200).extract().response();

        response.getBody().prettyPrint();
    }

}
