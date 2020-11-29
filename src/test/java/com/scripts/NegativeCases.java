package com.scripts;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.auth.*;

public class NegativeCases {

    int statusCode;
    String errorName;

    @Test(testName="Unauthorized request")
    public void unauthorizedGetRequest() {  //Sending request without API key
        errorName = "AuthenticationError";

        GetCollections.url();
        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .request(Method.GET);

        statusCode = response.getStatusCode();

        System.out.println("Unauthorized Request | HTTP Status Code: " + statusCode + " " + response.statusLine());

        Assert.assertEquals(statusCode, 401);
        Assert.assertEquals(response.path("error.name"), errorName);
    }

    @Test(testName="Invalid id search")
    public void invalidSearch() {   //Sendin invalid collection id request
        String invalidId = "invalidId-147896";
        errorName = "instanceNotFoundError";

        GetCollections.url();
        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .header(ApiKey.keyName, ApiKey.keyValue)
                .request(Method.GET, invalidId);

        statusCode = response.getStatusCode();

        System.out.println("Invalid Search | HTTP Status Code: " + statusCode + " " + response.statusLine());

        Assert.assertEquals(statusCode, 404);
        Assert.assertEquals(response.path("error.name"), errorName);
    }

    @Test(testName="Empty body POST request")
    public void postEmptyBody() {   //Creating collection with empty body
        errorName = "paramMissingError";

        GetCollections.url();

        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .header(ApiKey.keyName, ApiKey.keyValue)
                .body("")
                .request(Method.POST);

        statusCode = response.getStatusCode();

        System.out.println("Empty Body HTTP | Status Code: " + statusCode + " " + response.statusLine());

        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(response.path("error.name"), errorName);
    }
}
