package com.example.checkingsystem;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.io.IOException;

import Decoder.BASE64Decoder;
import util.CryptoGram;
import util.SimpleCrypto;
import util.SymmetricEncoderUtil;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void aesTest()
    {
        String seed = "1490430630617";
        String content = "8bYQELarI9FI1Qkg3WtXvV328H+j24ifPVM6Nc4csVbL9nM85LvS8uyepeOB2ToR\"";
//        String base64 = new String(Base64.decodeBase64(content), Charsets.UTF_8);
        String result = null;
        try {
            result = SimpleCrypto.decrypt(seed,content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);

//        result = SymmetricEncoderUtil.AESEncode(seed,result);
//        System.out.println(result);

    }
}