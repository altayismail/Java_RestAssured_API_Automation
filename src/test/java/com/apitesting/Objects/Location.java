package com.apitesting.Objects;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"postCode", "country", "countryAbbreviation", "places"})
public class Location {
    private String postCode;
    @JsonProperty("post code")
    public String getPostCode() {
        return postCode;
    }
    @JsonProperty("post code")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    private String country;
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    private String countryAbbreviation;
    @JsonProperty("country abbreviation")
    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }
    @JsonProperty("country abbreviation")
    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    private List<Place> places;

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public Location()
    {
        this.postCode = postCode;
        this.country = country;
        this.countryAbbreviation = countryAbbreviation;

        Place place = new Place();

        List<Place> places = new ArrayList<>();
        places.add(place);

        this.places = places;
    }
}
