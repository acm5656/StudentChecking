package util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by 那年.盛夏 on 2017/3/13.
 */

public class SurfaceViewCircle extends SurfaceView {

    public SurfaceViewCircle(Context context) {
        super(context);
    }

    public SurfaceViewCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SurfaceViewCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SurfaceViewCircle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        //设置裁剪的圆心，半径
//        path.addCircle(height / 2, height / 2, height / 2, Path.Direction.CCW);
                path.addCircle((getLeft()+getRight())/2, (getTop()+getBottom())/2, Math.min((getLeft()+getRight())/2,(getTop()+getBottom())/2), Path.Direction.CCW);
        //裁剪画布，并设置其填充方式
        canvas.clipPath(path, Region.Op.REPLACE);
        super.draw(canvas);

    }


}
