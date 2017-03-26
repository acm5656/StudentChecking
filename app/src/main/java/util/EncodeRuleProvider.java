package util;

import java.sql.Timestamp;

/**
 * Created by eggyer on 2017/3/18.
 */
public class EncodeRuleProvider {
    public static String getRuleByTimestamp(Timestamp timestamp){
        return String.valueOf(timestamp.getTime());
    }
}
