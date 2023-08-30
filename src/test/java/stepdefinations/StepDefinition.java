package stepdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {
    static String placeId;
    Response response;
    TestDataBuild testData = new TestDataBuild();
    RequestSpecification givenRequest;
    RequestSpecification request;

    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        givenRequest = given().spec(resquestSpecifiaction()).
                body(testData.AddPlacePayload(name, language, address));


    }

    @When("user calls {string} API using http {string} method.")
    public void callApiRequest(String resource, String method) {
        APIResources resources = APIResources.valueOf(resource);
        System.out.println("resources is" + resources);
        String getResourceName = resources.getResource();
        System.out.println("resource name is" + getResourceName);

        if (method.equalsIgnoreCase("post")) {
            response = givenRequest.when().post(getResourceName)
                    .then().log().all().extract().response();
            System.out.println("Api response is " + response.asString());
        } else if (method.equalsIgnoreCase("get")) {
            response = givenRequest.when().get(getResourceName)
                    .then().log().all().extract().response();

        }
    }

    @Then("API call got successfully with status code {int}.")
    public void api_call_got_successfully_with_status_code(int string) {
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("correct status code");
    }

    @Then("In the response body {string} is {string}.")
    public void in_the_response_body_is(String key, String expected) {
        Assert.assertEquals(getJsonPath(response, key), expected);
        System.out.println("it is the correct value");

    }


    @And("verify the place id created maps to {string} using {string}.")
    public void verifyThePlaceIdCreatedMapsToUsing(String expectedName, String api) throws IOException {
        placeId = getJsonPath(response, "place_id");
        System.out.println("place is is" + placeId);

        givenRequest = given().spec(resquestSpecifiaction()).
                queryParam("place_id", placeId);
        callApiRequest(api, "get");
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(actualName, expectedName);
    }

    @Given("Delete place payload")
    public void deletePlacePayload() throws IOException {
        givenRequest = given().spec(resquestSpecifiaction()).
                body(testData.deletePlacePayload(placeId));

    }
}

