package tests;

import base.APITestBase;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APISmoke extends APITestBase {

    @Test(priority = 1,
            description = "[POST] Client can register a user")
    public void PBTW_T237() {

        String payload = generateNewUserPayload();

        LOG("Send POST Request ");
        Response response = RestAssured.given()
                .contentType("Application/json")
                .body(payload)
                .post("/user/register");

        LOG("With the following payload:");
        JSON(payload);
        LOG("We got the response");

        LOG("Verify that response status code is 201 Created");
        SoftAssert softAssert = new SoftAssert();
        int actualStatusCode = response.statusCode();
        softAssert.assertEquals(actualStatusCode, 201);

        LOG("Response body contains following payload:");
        JSON(response.getBody().asString());
        LOG("Verify the response body contains id for new user");
        String responsePayload = response.getBody().asString();
        setToken(extractData(responsePayload, "$.token"));
        String id = extractData(responsePayload, "$.user._id");
        softAssert.assertNotNull(id);
        softAssert.assertAll();
    }


    @Test(priority = 2,
            description = "[POST] Client can login a registered user")
    public void PBTW_T240() {
        String payload = getCredentialPayload();

        LOG("Send POST login request");
        Response response = RestAssured.given()
                .contentType("Application/json")
                .body(payload)
                .post("/user/login");

        LOG("Request payload:");
        JSON(payload);

        LOG("Verify that response status code is 200 OK");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200);
        LOG("Verify the response body contains user session token");
        String responsePayload = response.getBody().asString();

        JSON(responsePayload);
        String token = extractData(responsePayload, "$.token");
        setSessionToken(token);
        softAssert.assertNotNull(token);
        softAssert.assertAll();
    }


    @Test(priority = 3)
    public void PBTW_T262() {

        String payload = "{\"description\":\"take out a trash\"}";

        LOG("Sending the post add single to-do task reqeust ");
        RequestSpecification spec = RestAssured.given();
        spec.header("Authorization", "Bearer " + getSessionToken());
        spec.contentType("Application/json");
        spec.body(payload);
        Response response = spec.post("/task");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 201);
        String responseBody = response.getBody().asString();
        LOG("We got the response from the server");
        JSON(responseBody);

        String actualDescipt = JsonPath.read(responseBody, "$.data.description");
        softAssert.assertEquals(actualDescipt, "take out a trash");
        softAssert.assertAll();
    }


    @Test(priority = 3,
            description = "[GET] Client can get user profile information of currently logged user")
    public void PBTW_T258() {
        LOG("Send GET user info request");
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + getSessionToken())
                .accept("Application/json")
                .get("/user/me");

        LOG("Verify that response status code is 200 OK");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200);

        LOG("Verify that response body contains correct Name, Email, Age");
        String responsePayload = response.getBody().asString();
        JSON(responsePayload);
        String actualName = extractData(responsePayload, "$.name");
        String actualEmail = extractData(responsePayload, "$.email");
        softAssert.assertEquals(actualName, getName());
        softAssert.assertEquals(actualEmail, getUserEmail());
        softAssert.assertAll();
    }


    @Test(priority = 4,
            description = "[POST] Client can logout a user in session")
    public void PBTW_T242() {
        LOG("Send POST logout request");
        Response response =
                RestAssured.given()
                        .header("Authorization", "Bearer " + getSessionToken())
                        .accept("Application/json")
                        .post("/user/logout");

        LOG("Verify response status code 200 OK");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200);
        LOG("Verify the response body message for successful logout ");
        String responsePayload = response.getBody().asString();
        JSON(responsePayload);
        softAssert.assertTrue(responsePayload.contains("true"));
        softAssert.assertAll();
    }


    @Test(priority = 5, description = "[PUT] Client can update user profile info")
    public void PBTW_T238() {

        String payload = "{\"age\":28}";

        LOG("Sending PUT update profile request to the server");
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + getToken())
                .contentType("Application/json")
                .body(payload)
                .put("/user/me");

        String responseBody = response.getBody().asString();
        LOG("Response received:");
        JSON(responseBody);

        SoftAssert softAssert = new SoftAssert();
        LOG("Asserting the response");
        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertTrue(responseBody.contains("true"));
        int actualAge = JsonPath.read(responseBody, "$.data.age");
        softAssert.assertEquals(actualAge, 28);
        softAssert.assertAll();
    }


    @Test(priority = 6, description = "[DELETE] Client can delete a user")
    public void PBTW_T260() {
        LOG("Send the DELETE request with following request specification");
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + getToken())
                .accept("Application/json")
                .delete("/user/me");
        LOG("Verify that response status code is 200 OK");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200);
        LOG("Verify that response body contains correct name");
        String responsePayload = response.getBody().asString();
        JSON(responsePayload);
        String actualName = extractData(responsePayload, "$.name");
        softAssert.assertEquals(actualName, getName());
        softAssert.assertAll();
    }

}
