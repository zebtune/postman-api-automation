package com.scripts;

import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.auth.*;

public class GetCollections {

    int statusCode;

    @BeforeMethod
    public static void url(){
        //setting a base method that will be used in different classes
        RestAssured.baseURI = "https://api.getpostman.com/collections/";
    }

    @Test(testName="GET all collections")
    public void getCollectionAll() {
        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .header(ApiKey.keyName, ApiKey.keyValue)
                .request(Method.GET);

        statusCode = response.getStatusCode();

        System.out.println("Get All Collections | HTTP Status Code: " + statusCode + " " + response.statusLine());

        Assert.assertEquals(statusCode, 200);
    }

    @Test(testName="GET single collection")
    public void getCollectionSingle() {
        //using method for extracting id from response
        String collectionId = getCollectionId();

        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .header(ApiKey.keyName, ApiKey.keyValue)
                .request(Method.GET, collectionId);

        statusCode = response.getStatusCode();

        System.out.println("Get Single Collection | HTTP Status Code: " + statusCode + " " + response.statusLine());

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(response.path("collection.info._postman_id"), collectionId);
    }

    public String getCollectionId() {
        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .header(ApiKey.keyName, ApiKey.keyValue)
                .request(Method.GET);

        String collectionId = response.jsonPath().getString("collections[0].id");

        return collectionId;
    }
}