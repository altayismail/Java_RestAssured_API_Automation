package com.apitesting;

import org.junit.BeforeClass;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClass {

    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpec()
    {
        requestSpec = new RequestSpecBuilder()
                        .setBaseUri("http://api.zippopotam.us")
                        .build();
    }

    @BeforeClass
    public static void createResponseSpec()
    {
        responseSpec = new ResponseSpecBuilder()
                        .expectStatusCode(200)
                        .expectContentType(ContentType.JSON)
                        .build();
    }
}
