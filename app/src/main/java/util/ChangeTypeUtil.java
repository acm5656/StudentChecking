package util;

import com.example.checkingsystem.entity.ResultObj;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by 那年.盛夏 on 2017/3/27.
 */
    //此类用来进行数据转换，即序列化和反序列化
public class ChangeTypeUtil {
    public static ObjectMapper objectMapper = new ObjectMapper();
    //反序列化
    public static ResultObj getResultObj(String obj)
    {
        ResultObj<Object> resultObj = new ResultObj<>();

        try {
             resultObj = objectMapper.readValue(obj.getBytes(), new TypeReference<ResultObj<Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(resultObj.getData().toString());
        return resultObj;
    }
    //序列化
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
