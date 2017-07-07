package util;

import android.animation.TypeEvaluator;

/**
 * Created by 那年.盛夏 on 2017/7/5.
 */

public class ShanEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = 360 * fraction*100;
        float y = 360 * fraction*100;
        Point point = new Point(x, y);
        return point;
    }

}