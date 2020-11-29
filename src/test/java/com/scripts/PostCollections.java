package com.scripts;

import com.github.javafaker.Faker;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.auth.*;



public class PostCollections {
    @Test(testName="POST collections")
    public void postCollections() {
        //Faker for random generated data
        Faker faker = new Faker();

        GetCollections.url();
        String collectionName = " ";

        for (int i = 0; i < 3; i++) {
            //Each iteration will give collection a country name
            collectionName = faker.country().name();

            RequestSpecification request = RestAssured.given();
            Response response = request.given()
                    .header("Content-Type", "application/json")
                    .header(ApiKey.keyName, ApiKey.keyValue)
                    .body("{\n  \"collection\": {\n    \"variables\": [],\n    \"info\": {\n      \"name\": \"" + collectionName + "\",\n      \"description\": \"This is just a sample collection.\",\n      \"schema\": \"https://schema.getpostman.com/json/collection/v2.0.0/collection.json\"\n    },\n    \"item\": [\n      {\n        \"name\": \"This is a folder\",\n        \"description\": \"\",\n        \"item\": [\n          {\n            \"name\": \"Sample POST Request\",\n            \"request\": {\n              \"url\": \"echo.getpostman.com/post\",\n              \"method\": \"POST\",\n              \"header\": [\n                {\n                  \"key\": \"Content-Type\",\n                  \"value\": \"application/json\",\n                  \"description\": \"\"\n                }\n              ],\n              \"body\": {\n                \"mode\": \"raw\",\n                \"raw\": \"{\\n\\t\\\"data\\\": \\\"123\\\"\\n}\"\n              },\n              \"description\": \"This is a sample POST Request\"\n            },\n            \"response\": []\n          }\n        ]\n      },\n      {\n        \"name\": \"Sample GET Request\",\n        \"request\": {\n          \"url\": \"echo.getpostman.com/get\",\n          \"method\": \"GET\",\n          \"header\": [],\n          \"body\": {\n            \"mode\": \"formdata\",\n            \"formdata\": []\n          },\n          \"description\": \"This is a sample GET Request\"\n        },\n        \"response\": []\n      }\n    ]\n  }\n}")
                    .request(Method.POST);

            int statusCode = response.getStatusCode();
            System.out.println("Post Collection | HTTP Status Code: " + statusCode + " " + response.statusLine());

            Assert.assertEquals(statusCode, 200);
            Assert.assertEquals(response.path("collection.name"), collectionName);
        }
    }
}
