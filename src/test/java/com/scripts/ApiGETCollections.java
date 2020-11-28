package com.scripts;

import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.auth.*;


public class ApiGETCollections {
    int statusCode;
    String collectionId = "1f361abf-49c1-4765-a3f9-bf1addc64615";

    @BeforeMethod
    public static void url(){
        RestAssured.baseURI = "https://api.getpostman.com/collections/";
    }

    @Test
    public void getCollectionAll(){
        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .header(ApiKey.keyName, ApiKey.keyValue)
                .request(Method.GET);

        statusCode = response.getStatusCode();

        System.out.println("HTTP Status Code: " + statusCode + " " + response.statusLine());
        System.out.println("Response Body: " + response.getBody().prettyPrint());

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void getCollectionSingle(){
        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .header(ApiKey.keyName, ApiKey.keyValue)
                .request(Method.GET, collectionId);

        statusCode = response.getStatusCode();

        System.out.println("HTTP Status Code: " + statusCode + " " + response.statusLine());
        System.out.println("Response Body: " + response.getBody().prettyPrint());

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(response.path("collection.info._postman_id"), collectionId);
    }
}