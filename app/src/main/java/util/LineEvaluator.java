package util;

import android.animation.TypeEvaluator;

/**
 * Created by 那年.盛夏 on 2017/7/6.
 */

public class LineEvaluator implements TypeEvaluator {
    float h;
    public LineEvaluator(float h)
    {
        this.h = h;
    }
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float z = h * fraction;
        return z;
    }
}
