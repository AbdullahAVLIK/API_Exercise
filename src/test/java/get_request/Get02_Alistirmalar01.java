package get_request;

import base_url.AutomationExerciseBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class Get02_Alistirmalar01 extends AutomationExerciseBaseUrl {
    /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/

    @Test
    public void get02() {
        //set the Url;
        spec.pathParam("first", "brandsList");
        //set the expected Data;
        //Send the request and get the response
        Response response = given().spec(spec).when().contentType(ContentType.JSON).get("/{first}");
        JsonPath json = response.jsonPath();
        json.prettyPrint();

        //Do Assertion
        response.then().assertThat().
                statusCode(200).//HTTP Status Code should be 200
                contentType("text/html; charset=utf-8").//Content Type should be "text/html; charset=utf-8"
                statusLine("HTTP/1.1 200 OK");//Status Line should be HTTP/1.1 200 OK

        List<String> HMList = json.getList("brands.findAll{it.brand=='H&M'}.id");
        System.out.println("HMList = " + HMList);
        List<String> PoloList = json.getList("brands.findAll{it.brand=='Polo'}.id");
        System.out.println("PoloList = " + PoloList);
        /*,
        HMList = [2, 6, 28, 31, 35]
        PoloList = [1, 8, 29, 30, 33, 37]
         */
        assertNotEquals(HMList.size(), PoloList.size());
        int PoloNumber=0;
        int HMNumber  =0;
        List<String> brandsList = json.getList("brands.brand");
        for (String w : brandsList ){
            if (w.equalsIgnoreCase("Polo")){
                PoloNumber++;
            }
            if (w.equalsIgnoreCase("H&M")){
                HMNumber++;
            }
        }
        System.out.println("HMNumber = " + HMNumber);
        System.out.println("PoloNumber = " + PoloNumber);
        assertNotEquals(PoloNumber,HMNumber);
    }
}
