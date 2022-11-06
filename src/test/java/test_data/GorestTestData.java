package test_data;

import java.util.HashMap;
import java.util.Map;

public class GorestTestData {
    public static Map<String,String> dataMapMethod(String name,String email,String gender,String status){
        Map<String,String>dataMap =new HashMap<>();
        dataMap.put("name",name);
        dataMap.put("email",email);
        dataMap.put("gender",gender);
        dataMap.put("status",status);

        return dataMap;
    }
    public static Map<String,Object> expectedDataMethods(Object meta,Map<String,String> dataMap){
        Map<String,Object>expectedDataMap= new HashMap<>();
        expectedDataMap.put("meta",meta);
        expectedDataMap.put("data",dataMap);

        return expectedDataMap;
    }

}
