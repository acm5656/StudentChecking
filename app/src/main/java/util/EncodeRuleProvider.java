package util;

import java.sql.Timestamp;

/**
 * Created by eggyer on 2017/3/18.
 */

//用来解析token传来数据的秘钥
public class EncodeRuleProvider {
    public static String getRuleByTimestamp(Timestamp timestamp){
        String ruleStr = String.valueOf(timestamp.getTime());
        if(ruleStr.length()<16)
        {
            int shortLength = 16 - ruleStr.length();
            byte []shortBytes = new byte[shortLength];
            for(int i = 0 ; i<shortLength ;i++)
                shortBytes[i] = '0';
            ruleStr+=new String(shortBytes);
        }
        if(ruleStr.length()>16)
        {
            ruleStr = ruleStr.substring(0,16);
        }
        return ruleStr;
    }
}
