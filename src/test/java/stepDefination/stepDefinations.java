package stepDefination;

import POJO.AddPlace;
import POJO.Location;

import static org.junit.Assert.*;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class stepDefinations extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    static String placeid;
    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));


    }

    @When("user calls {string} with {string} hhtps resquest")
    public void user_calls_with_post_hhtp_resquest(String resource, String method) {
        String resourceAPI = APIResources.valueOf(resource).Getresource();

        if (method.equalsIgnoreCase("post")) {
            response = res.when().post(resourceAPI);
        } else if (method.equalsIgnoreCase("get")) {
            response = res.when().get(resourceAPI);
        } else if (method.equalsIgnoreCase("put")) {
            response = res.when().put(resourceAPI);
        } else if (method.equalsIgnoreCase("delete")) {
            response = res.when().delete(resourceAPI);
        }


    }

    @Then("then API call got success with status code {int}")
    public void then_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(), 200);


    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyvalue, String Expectedvalue) {

        assertEquals(getJsonPath(response,keyvalue), Expectedvalue);


    }

    @Then("verify the added {string} have the same {string} with {string} for {string}")
    public void verify_the_added_have_the_same_with_for(String keyvalue, String expectedname, String resource, String method) throws IOException {
         placeid = getJsonPath(response, keyvalue);
        res=given().spec(requestSpecification()).queryParam(keyvalue,placeid);
        user_calls_with_post_hhtp_resquest(resource, method);
        String actualname = getJsonPath(response, "name");
        assertEquals(actualname,expectedname);
    }



    @Given("Delete place payload")
    public void delete_place_payload() throws IOException {

       res= given().spec(requestSpecification()).body(data.deletePlacepayload(placeid));


    }
    }

