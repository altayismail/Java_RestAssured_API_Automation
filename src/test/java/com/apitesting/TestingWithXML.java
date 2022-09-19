package com.apitesting;

import org.junit.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class TestingWithXML{
    static String URI = "https://randomuser.me/api/?format=xml";

    //Example for XML file
    @Test
    public void checkName()
    {
        RestAssured
        .given()
        .when()
            .get(URI)
        .then()
            .assertThat()
            .body("user.info.version", equalTo("1.4"));
    }
}
