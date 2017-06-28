package util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/3/16.
 */

/*
* 此类用来存储activity，可以一键全部回收，用来进行退出时候使用
*
* */
public class ActivityColectorUtil {
    public static List<Activity> activityList = new ArrayList<>();
    public static void addActivity(Activity activity)
    {
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity)
    {
        activityList.remove(activity);
    }
    public static void finishAll()
    {
        for(Activity activity:activityList)
        {
            if(!activity.isFinishing())
            {
                activity.finish();
            }
        }
    }

}
