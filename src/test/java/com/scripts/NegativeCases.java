package com.scripts;

import com.auth.ApiKey;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeCases {
    //1. Try to send unauthorized request
    //2. Try to search invalid id
    //3. Try to post with invalid body
    //4.

    @Test
    public void unauthorizedGetRequest() {
        String errorName = "AuthenticationError";

        ApiGETCollections.url();
        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .request(Method.GET);

        int statusCode = response.getStatusCode();

        System.out.println("HTTP Status Code: " + statusCode + " " + response.statusLine());
        System.out.println("Response Body: " + response.getBody().prettyPrint());

        Assert.assertEquals(statusCode, 401);
        Assert.assertEquals(response.path("error.name"), errorName);
    }

    @Test
    public void invalidSearch() {
        String id = "invalidId-147896";
        String errorName = "instanceNotFoundError";

        ApiGETCollections.url();
        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .header(ApiKey.keyName, ApiKey.keyValue)
                .request(Method.GET, id);

        int statusCode = response.getStatusCode();

        System.out.println("HTTP Status Code: " + statusCode + " " + response.statusLine());
        System.out.println("Response Body: " + response.getBody().prettyPrint());

        Assert.assertEquals(statusCode, 404);
        Assert.assertEquals(response.path("error.name"), errorName);
    }

    @Test
    public void postEmptyBody() {
        String errorName = "paramMissingError";

        ApiGETCollections.url();

        RequestSpecification request = RestAssured.given();
        Response response = request.given()
                .header("Content-Type", "application/json")
                .header(ApiKey.keyName, ApiKey.keyValue)
                .body("")
                .request(Method.POST);

        int statusCode = response.getStatusCode();

        System.out.println("HTTP Status Code: " + statusCode + " " + response.statusLine());
        System.out.println("Response Body: " + response.getBody().prettyPrint());

        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(response.path("error.name"), errorName);
    }
}
