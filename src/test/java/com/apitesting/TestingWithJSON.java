package com.apitesting;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.testng.Assert;

import com.apitesting.Objects.Location;
import com.apitesting.Objects.Place;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(DataProviderRunner.class)
public class TestingWithJSON extends BaseClass
{
    //Create different cases with different data for tests
    @DataProvider
    public static Object[][] zipCodesAndPlaces()
    {
        return new Object[][] 
        {
            { "tr" , "35150" , "İzmir"},
            { "us" , "90210" , "California"},
            { "us" , "12345" , "New York"},
            { "ca" , "B2R" , "Nova Scotia"}
        };
    }

    static String URI = "http://api.zippopotam.us/TR/35180";

    //Check status code in response
    @Test
    public void checkStatusCodeIsOk()
    {
        RestAssured
        .given()
            .spec(requestSpec)
        .when()
            .get("/tr/35150")
        .then()
            .spec(responseSpec)
            .assertThat()
            .statusCode(200);
    }

    //Check content type of the data
    @Test
    public void checkContentType()
    {
        RestAssured
        .given()
            .spec(requestSpec)
        .when()
            .get("/tr/35150")
        .then()
            .spec(responseSpec)
            .log().all()
            .assertThat()
            .contentType(ContentType.JSON);
    }

    //How to use the data provider
    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void useParameters(String countryCode, String zipCode, String exceptedPlace)
    {
        RestAssured
        .given()
            .spec(requestSpec)
        .when()
            .get("/" + countryCode + "/" + zipCode)
        .then()
            .spec(responseSpec)
            .log().all()
            .assertThat()
            .body("places[0].state", equalTo(exceptedPlace));
    }

    //How to deserialize a object from json, check the class for the object
    @Test
    public void deserializeObject()
    {
        Location location = RestAssured
                            .given()
                            .when()
                                .get(URI)
                                .as(Location.class);
        Assert.assertEquals(location.getPlaces().get(0).getState(), "İzmir");
    }

    //How to serialize and post the body
    @Test
    public void serializeObject()
    {
        Location location = new Location();
        location.setCountry("Turkey");
        location.setCountryAbbreviation("TR");
        location.setPostCode("35151");
        
        List<Place> places = new ArrayList<Place>();
        Place place = new Place();
        place.setLatitude("35");
        place.setLongitude("35.5");
        place.setPlaceName("Bostanlı Mahallesi");
        place.setState("İzmir");
        place.setStateAbbreviation("IZ");
        places.add(place);
        location.setPlaces(places);

        RestAssured
        .given()
            .contentType(ContentType.JSON)
            .body(location)
            .log().body()
        .when()
            .post("https://api.zippopotam.us/create")
        .then()
            .assertThat()
            .statusCode(200);
    }
}
