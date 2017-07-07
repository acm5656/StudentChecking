package util;

import android.animation.TypeEvaluator;

/**
 * Created by 那年.盛夏 on 2017/7/5.
 */

public class HuEvaluator implements TypeEvaluator {
    private float h;
    int i = 1;
    public HuEvaluator(float h)
    {
        this.h = h;
    }

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = 150 * fraction+135;
        float y = 375-150 * fraction;
        if(fraction==1.0)
        {
            if(i%10==0) {
                i=0;
            }else {
                i++;
            }
        }

        float z = h * fraction;
        Point point = new Point(x, y, z);
        return point;
    }

}