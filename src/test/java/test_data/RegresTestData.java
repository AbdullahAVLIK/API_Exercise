package test_data;

import java.util.HashMap;
import java.util.Map;

public class RegresTestData {
    /*
    {
                "name": "morpheus",
                "job": "leader"
                }
     */
    public static Map<String,String> expectedDataMethod(String name,String job){
        Map<String,String> expectedDataMap =new HashMap<>();
        expectedDataMap.put("name",name);
        expectedDataMap.put("job",job);

        return expectedDataMap;
    }

    public String expectedDataInString(String name){
        String  jsonInString ="{\n" +
                "                \"name\": \""+name+"\"\n" +
                "               }";
        return jsonInString;
    }
}
