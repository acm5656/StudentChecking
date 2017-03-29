package util;

import com.example.checkingsystem.entity.ResultObj;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by 那年.盛夏 on 2017/3/27.
 */

public class ChangeTypeUtil {
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static ResultObj<String> getResultObj(String obj)
    {
        ResultObj<String> resultObj = new ResultObj<>();

        try {
             resultObj = objectMapper.readValue(obj.getBytes(), new TypeReference<ResultObj<String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(resultObj.getData().toString());
        return resultObj;
    }

    public static String getJSONString(Object obj)
    {
        String value = null;

        try {
            value = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }
}
