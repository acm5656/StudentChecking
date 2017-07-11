package util;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * Created by 那年.盛夏 on 2017/7/5.
 */

public class PointEvaluator implements TypeEvaluator {
    float r;
    public PointEvaluator(float r)
    {
        this.r = r;
    }

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = new Float(startPoint.getX() - Math.sin(fraction* Math.PI*2*20)*r);
        float y = new Float(startPoint.getY() - (r - Math.cos(fraction* Math.PI*2*20) * r) );
        Point point = new Point(x, y);
        return point;
    }

}