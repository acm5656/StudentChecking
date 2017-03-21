package util;

import android.content.Context;
import android.util.Log;

import com.qcloud.cos.exception.AbstractCosException;
import com.qcloud.cos.sign.Credentials;
import com.tencent.cos.COSClient;
import com.tencent.cos.COSClientConfig;
import com.tencent.cos.common.COSEndPoint;
import com.tencent.cos.model.COSRequest;
import com.tencent.cos.model.COSResult;
import com.tencent.cos.model.PutObjectRequest;
import com.tencent.cos.model.PutObjectResult;
import com.tencent.cos.task.listener.IUploadTaskListener;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;

import java.net.URLEncoder;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by 那年.盛夏 on 2017/3/18.
 */

public class CosUtil {

    public static String appID = "1252388599";
    public static String peristenceID = null;
    public static String secretID = "AKIDxjHbhYKeqoX2HGHThfePvBCyX2Vw3VS9";
    public static String secretKey = "bx40hEXbPyIPIxObaKq8C1cz83kCQQBL";
    public static String bucket = "checkingsystem";
    public static String cosPath = "UserImage";
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String PATH_DELIMITER = "/";


    public static void upLoad(String path, String srcName, Context context)
    {
        COSClientConfig cosClientConfig = new COSClientConfig();
        cosClientConfig.setEndPoint(COSEndPoint.COS_TJ);


        COSClient cosClient = new COSClient(context,appID,cosClientConfig,peristenceID);

        String srcPath = path;
        String sign = null;
        try {
            sign = getSign();
        } catch (AbstractCosException e) {
            e.printStackTrace();
        }


        PutObjectRequest putObjectRequest = new PutObjectRequest();
        putObjectRequest.setBucket(bucket);
        putObjectRequest.setCosPath(cosPath+"/"+srcName);
        putObjectRequest.setSrcPath(srcPath+"/"+srcName);
        putObjectRequest.setSign(sign);
        putObjectRequest.setSign(sign);
        putObjectRequest.setListener(new IUploadTaskListener() {
            @Override
            public void onProgress(COSRequest cosRequest, long l, long l1) {

            }

            @Override
            public void onCancel(COSRequest cosRequest, COSResult cosResult) {

            }

            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                PutObjectResult result = (PutObjectResult) cosResult;
                Log.e("test","----------3");
                if(result != null){
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(" 上传结果： ret=" + result.code + "; msg =" +result.msg + "n");
                    stringBuilder.append(" access_url= " + result.access_url == null ? "null" :result.access_url + "n");
                    stringBuilder.append(" resource_path= " + result.resource_path == null ? "null" :result.resource_path + "n");
                    stringBuilder.append(" url= " + result.url == null ? "null" :result.url);
                    Log.e("TEST",stringBuilder.toString());
                }
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                Log.e("TEST","上传出错： ret =" +cosResult.code + "; msg =" + cosResult.msg);
            }
        });
        PutObjectResult result = cosClient.putObject(putObjectRequest);
        Log.e("test","----------2");

    }

    public static String getSign() throws AbstractCosException {
        Credentials credentials = new Credentials(new Long(appID),secretID,secretKey);
        String sign = getPeriodEffectiveSign(bucket,"",credentials, System.currentTimeMillis()/1000+12000);
        System.out.println(sign);
        return sign;

    }

    private static String getPeriodEffectiveSign(String bucket, String s, Credentials credentials, long l) throws AbstractCosException {
        return appSignatureBase(credentials,bucket,s,l,true);
    }

    private static String appSignatureBase(Credentials cred, String bucketName, String cosPath, long expired,
                                           boolean uploadFlag) throws AbstractCosException {
        long appId = cred.getAppId();
        String secretId = cred.getSecretId();
        String secretKey = cred.getSecretKey();
        long now = System.currentTimeMillis() / 1000;
        int rdm = Math.abs(new Random().nextInt());
        String fileId = null;
        if (uploadFlag) {
            fileId = String.format("/%d/%s%s", appId, bucketName, cosPath);
            System.out.println(fileId);
        } else {
            fileId = cosPath;
        }
        fileId = encodeRemotePath(fileId);
        String plainText = String.format("a=%s&k=%s&e=%d&t=%d&r=%d&f=%s&b=%s", appId, secretId, expired, now, rdm,
                fileId, bucketName);

        byte[] hmacDigest = "".getBytes();

        try {
            hmacDigest =HmacSha1(plainText.getBytes(), secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] signContent = new byte[hmacDigest.length + plainText.getBytes().length];
        System.arraycopy(hmacDigest, 0, signContent, 0, hmacDigest.length);
        System.arraycopy(plainText.getBytes(), 0, signContent, hmacDigest.length, plainText.getBytes().length);

        return Base64Encode(signContent);
    }

    public static byte[] HmacSha1(byte[] binaryData, String key) throws Exception {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA1);
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1);
            mac.init(secretKey);
            byte[] HmacSha1Digest = mac.doFinal(binaryData);
            return HmacSha1Digest;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "".getBytes();
    }

    public static String Base64Encode(byte[] binaryData) {

        String encodedstr = new String(Base64.encodeBase64(binaryData, false), Charsets.UTF_8);
        return encodedstr;
    }

    public static String encodeRemotePath(String urlPath) throws AbstractCosException {
        StringBuilder pathBuilder = new StringBuilder();
        String[] pathSegmentsArr = urlPath.split(PATH_DELIMITER);

        for (String pathSegment : pathSegmentsArr) {
            if (!pathSegment.isEmpty()) {
                try {
                    pathBuilder.append(PATH_DELIMITER).append(URLEncoder.encode(pathSegment, "UTF-8").replace("+", "%20"));
                } catch (Exception e) {
                    String errMsg = "Unsupported ecnode exception:" + e.toString();
                    e.printStackTrace();
                }
            }
        }
        if (urlPath.endsWith(PATH_DELIMITER)) {
            pathBuilder.append(PATH_DELIMITER);
        }
        return pathBuilder.toString();
    }





}
