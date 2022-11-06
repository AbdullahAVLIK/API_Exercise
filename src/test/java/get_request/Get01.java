package get_request;

import base_url.GorestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import test_data.GorestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static test_data.GorestTestData.dataMapMethod;
import static test_data.GorestTestData.expectedDataMethods;

public class Get01 extends GorestBaseUrl {
 /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
   {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Brijesh Kocchar",
        "email": "kocchar_brijesh@prosacco.com",
        "gender": "female",
        "status": "active"
    }
}
*/

    @Test
    public void get01() {
        //Set the Url;
        spec.pathParams("first", "users", "second", 2986);
        // Get the expected Data
        Map<String,String> dataMap = dataMapMethod("Brijesh Kocchar","kocchar_brijesh@prosacco.com","female","active");
        System.out.println(dataMap);
        Map<String,Object>expectedData =expectedDataMethods(null,dataMap);
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPeek();
        //Do assertion
        // 1. yol;
        response.then().assertThat().statusCode(200);//Status Code should be 200
        response.then().body("data.name", equalTo("Brijesh Kocchar"),
                "data.email", equalTo("kocchar_brijesh@prosacco.com"),
                "data.gender", equalTo("female"),
                "data.status", equalTo("active"),
                "meta", equalTo(null));
        // 2. yol; JsonPath ile doğrulama
        JsonPath json = response.jsonPath();
        assertEquals(200,response.getStatusCode());
        assertNull(json.getString("meta"));
        assertEquals("Brijesh Kocchar",json.getString("data.name"));
        assertEquals("kocchar_brijesh@prosacco.com",json.getString("data.email"));
        assertEquals("female",json.getString("data.gender"));
        assertEquals("active",json.getString("data.status"));

        //3.yol; map  kullanarak expected ve actual data doğrulama
        Map actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("meta"),actualData.get("meta"));
        assertEquals(dataMap.get("name"),((Map)actualData.get("data")).get("name"));
        assertEquals(dataMap.get("email"),((Map)actualData.get("data")).get("email"));
        assertEquals(dataMap.get("gender"),((Map)actualData.get("data")).get("gender"));
        assertEquals(dataMap.get("status"),((Map)actualData.get("data")).get("status"));



    }
}
