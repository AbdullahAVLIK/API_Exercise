package get_request;

import base_url.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RegresPojo;
import test_data.RegresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get03_Alistirmalar02 extends RegresBaseUrl {

    //2:  Map ve Pojo Class ile ayrı ayrı yapınız.
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
*/

    @Test
    public void post02_Map() {
        //set the Url
        spec.pathParam("first","users");
        //set the expected Data
        Map<String,String> expectedData = RegresTestData.expectedDataMethod("morpheus","leader");
        //Send the request and get the response
        Response response = given().spec(spec).when().contentType(ContentType.JSON).body(expectedData).post("/{first}");
        response.prettyPrint();
        //Do Assertions
        response.then().assertThat().statusCode(201);
        Map actualData =response.as(HashMap.class);
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));

    }

    @Test
    public void post02_Pojo() {
        //set the Url
        spec.pathParam("first", "users");
        //set the expected Data
        RegresPojo expectedData = new RegresPojo("morpheus","leader");
        //Send the request and get the response
        Response response = given().spec(spec).when().contentType(ContentType.JSON).body(expectedData).post("/{first}");
        response.prettyPrint();
        //Do assertion
        RegresPojo actualData = response.as(RegresPojo.class);
        assertEquals("Uncorrect StatusCode ",201,response.getStatusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(),actualData.getJob());



    }
}
