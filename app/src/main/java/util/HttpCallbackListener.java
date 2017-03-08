package util;

/**
 * Created by 那年.盛夏 on 2017/3/7.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);

}
