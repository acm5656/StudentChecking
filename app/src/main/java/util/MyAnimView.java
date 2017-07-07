package util;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 那年.盛夏 on 2017/7/5.
 */

public class MyAnimView extends View {

    public static final float RADIUS = 10f;

    private Point currentPoint;

    private Paint mPaint;

    float X ;
    float Y ;
    float R ;

    private float d;


    public MyAnimView(Context context, AttributeSet attrs) {

        super(context, attrs);
        Log.e("view","-------------构造函数");
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(4l);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        X = (getLeft()+getRight())/2;
        Y = (getTop()+getBottom())/2;
        R = Math.min(X,Y);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#DFE6EB"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        RectF oval2 = new RectF(X-R+10,Y-R+10,X+R-10,Y+R-10);//画外围环
        Log.e("test"," X="+X + "  Y=" +Y);
        Log.e("test"," r="+R);
        canvas.drawArc(oval2,135,270, false,paint);
        drawHuTest(canvas);
//        drawShanTest(canvas);
    }

    public void drawShanTest(Canvas canvas)
    {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawShan(canvas);
            startAnimation2();
        } else {
            drawShan(canvas);
        }
    }

    public void drawHuTest(Canvas canvas)
    {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawHu(canvas,currentPoint.getX());
            drawHu(canvas,currentPoint.getY());
            drawLine(canvas,d);
            startAnimation3();
            startAnimation4();
        } else {
            drawHu(canvas,currentPoint.getX());
            drawHu(canvas,currentPoint.getY());
            drawLine(canvas,d);
        }
    }


    private void drawLine(Canvas canvas,float z) {
//        canvas.drawCircle(x, y, RADIUS, mPaint);
        float rd = Math.abs(z-(R-20));
        float hd = (float) Math.sqrt(new Double((R-20)*(R-20)-rd*rd));
        float x1 = X-hd;
        float x2 = X+hd;
        float y1 = Y-(R-20)+z;
        float y2 = Y-(R-20)+z;
        canvas.drawLine(x1,y1,x2,y2,mPaint);
    }

    public void drawShan(Canvas canvas)
    {
                /*
         * 方法 说明 drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
         * drawLine 绘制直线 drawPoin 绘制点
         */
        // 创建画笔
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);//设置填满
        Matrix matrix = new Matrix();
        matrix.setRotate((getLeft()+getRight())/2,(getTop()+getBottom())/2,Math.min((getLeft()+getRight())/2,(getTop()+getBottom())/2));//加上旋转还是很有必要的，每次最右边总是有一部分多余了,不太美观,也可以不加

        Shader mShader = new SweepGradient((getLeft()+getRight())/2, (getLeft()+getRight())/2,
                new int[] {
                        Color.parseColor("#2299CC33"),Color.parseColor("#4499CC33"),
                },null); // 一个材质,打造出一个线性梯度沿著一条线。
//        mShader.setLocalMatrix(matrix);
        p.setShader(mShader);
        // p.setColor(Color.BLUE);
        RectF oval2 = new RectF(
                        new Float((getLeft()+getRight())/2-Math.min((getLeft()+getRight())/2,(getTop()+getBottom())/2)),
                        new Float(((getTop()+getBottom())/2)-Math.min((getLeft()+getRight())/2,(getTop()+getBottom())/2)),
                        new Float((getLeft()+getRight())/2+Math.min((getLeft()+getRight())/2,(getTop()+getBottom())/2)),
                        new Float((getTop()+getBottom())/2+Math.min((getLeft()+getRight())/2,(getTop()+getBottom())/2)));// 设置个新的长方形，扫描测量
        canvas.drawArc(oval2,currentPoint.getX(), 60, true, p);
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
    }
    public void drawHu(Canvas canvas,float begin)
    {
                /*
         * 方法 说明 drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
         * drawLine 绘制直线 drawPoin 绘制点
         */
        // 创建画笔
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);//设置填满
        p.setStrokeWidth(10);
        p.setColor(Color.parseColor("#000000"));
        /* 设置渐变色 这个正方形的颜色是改变的 */
//        mShader.setLocalMatrix(matrix);
        Shader mShader = new SweepGradient((getLeft()+getRight())/2, (getLeft()+getRight())/2,
                new int[] {
                        Color.parseColor("#2299CC33"),Color.parseColor("#4499CC33"),
                },null); // 一个材质,打造出一个线性梯度沿著一条线。
//        p.setShader(mShader);
        // p.setColor(Color.BLUE);
        RectF oval2 = new RectF(X-R+10,Y-R+10,X+R-10,Y+R-10);// 设置个新的长方形，扫描测量
        canvas.drawArc(oval2,begin,30, false, p);
    }





    public void startAnimation1() {

        Point startPoint = new Point((getLeft()+getRight())/2, (getBottom()+getTop())/2+Math.min(((getRight()-getLeft()))/2,(getBottom()-getTop())/2));
        Point endPoint = startPoint;
        Log.e("myAnim","x="+(getLeft()+getRight())/2+"    y="+(getTop()+getBottom())/2);
        Log.e("myAnim","left="+getLeft()+"   right="+getRight()+"    top="+getTop()+"   botton"+getBottom());

        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(Math.min(((getRight()-getLeft()))/2,(getBottom()-getTop())/2)), startPoint, endPoint);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(1000);
        anim.setDuration(40000);
        anim.start();
    }
    public void startAnimation2() {

        Point startPoint = new Point((getLeft()+getRight())/2, (getBottom()+getTop())/2+Math.min(((getRight()-getLeft()))/2,(getBottom()-getTop())/2));
        Point endPoint = startPoint;
        Log.e("myAnim","x="+(getLeft()+getRight())/2+"    y="+(getTop()+getBottom())/2);
        Log.e("myAnim","left="+getLeft()+"   right="+getRight()+"    top="+getTop()+"   botton"+getBottom());

        ValueAnimator anim = ValueAnimator.ofObject(new ShanEvaluator(),startPoint, endPoint);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(1);
        anim.setDuration(100000);
        anim.start();
    }

    public void startAnimation3() {

        Point startPoint = new Point((getLeft()+getRight())/2, (getBottom()+getTop())/2+Math.min(((getRight()-getLeft()))/2,(getBottom()-getTop())/2));
        Point endPoint = startPoint;
        Log.e("myAnim","x="+(getLeft()+getRight())/2+"    y="+(getTop()+getBottom())/2);
        Log.e("myAnim","left="+getLeft()+"   right="+getRight()+"    top="+getTop()+"   botton"+getBottom());

        ValueAnimator anim = ValueAnimator.ofObject(new HuEvaluator(2*(R-20)),startPoint, endPoint);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(200);
        anim.setDuration(800);
        anim.start();

    }

    public void startAnimation4() {
        Point startPoint = new Point((getLeft()+getRight())/2, (getBottom()+getTop())/2+Math.min(((getRight()-getLeft()))/2,(getBottom()-getTop())/2));
        Point endPoint = startPoint;
        ValueAnimator anim = ValueAnimator.ofObject(new LineEvaluator(2*(R-20)),startPoint,endPoint);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                d = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(200);
        anim.setDuration(4000);
        anim.start();

    }



}