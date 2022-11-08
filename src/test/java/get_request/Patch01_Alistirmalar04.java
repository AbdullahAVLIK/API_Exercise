package get_request;

import base_url.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.RegresTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01_Alistirmalar04 extends RegresBaseUrl {
    //4: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test
    public void patch01_Map_ObjectMapper() throws IOException {
        //set the Url
        spec.pathParams("first", "users","second",2);
        //Set the expected Data
       /* String  jsonInString ="{\n" +
                "                \"name\": \"neo\"\n" +
                "               }";*/
        RegresTestData obj =new RegresTestData();
        String jsonInString =obj.expectedDataInString("neo");
        Map expectedData = new ObjectMapper().readValue(jsonInString, HashMap.class);
        System.out.println("expectedData = " + expectedData);
        //send the request and get the response
        Response response = given().spec(spec).when().contentType(ContentType.JSON).body(expectedData).patch("/{first}/{second}");
        response.prettyPrint();
        //Do assertions
        Map actualData =new ObjectMapper().readValue(response.asString(),HashMap.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));






    }
}
