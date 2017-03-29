package util;

import java.security.MessageDigest;

import Decoder.BASE64Encoder;

/**
 * Created by 那年.盛夏 on 2017/3/26.
 */

public class Md5Util {
    public static String EncoderByMd5(String str)  {
        //确定计算方法
        MessageDigest md5= null;
        String newstr = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            newstr =base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newstr;
    }
}
