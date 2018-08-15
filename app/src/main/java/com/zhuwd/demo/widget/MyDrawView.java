package com.zhuwd.demo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zhuwd.demo.R;

/**
 * Created by Lenovo on 2018/8/14.
 */

public class MyDrawView extends View {

    private Paint mPaint = new Paint();
    private Paint mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Context mContext;
    private Path mPath = new Path();
    private Path mPath2 = new Path();

    public MyDrawView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//ROUND 画出来是圆形的点，SQUARE 或 BUTT 画出来是方形的点 默认BUTT 平头

        mPaint1.setStrokeWidth(5);
        mPaint1.setStyle(Paint.Style.STROKE);

//        canvas.drawCircle(150,150,100,mPaint);
//
//        canvas.drawCircle(400,150,100,mPaint1);
//
//        canvas.drawRect(50,300,250,550,mPaint1);

//        canvas.drawPoint(400,400,mPaint);
//
//        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
//        canvas.drawPoints(points,6,8,mPaint);
//
//        canvas.drawRoundRect(100,100,600,400,30,60,mPaint1);//rx 和 ry 是圆角的横向半径和纵向半径。
//
//        canvas.drawRoundRect(100,450,600,750,60,30,mPaint1);//rx 和 ry 是圆角的横向半径和纵向半径。
//
//        float[] points1 = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
//        canvas.drawLines(points1, mPaint1);

        //绘制弧形或者扇形  drawArc
//        canvas.drawArc(100,450,600,750,0,90,true,mPaint);
//
//        canvas.drawArc(100,450,600,750,-180,90,false,mPaint1);
//
//        canvas.drawArc(100,450,600,750,-90,90,false,mPaint);

//        //方法一 addXXX 画图形
//
//        mPath.addCircle(200,200,150,Path.Direction.CCW);
//        mPath2.addRect(50,50,350,350, Path.Direction.CW);//它只是在需要填充图形 (Paint.Style 为 FILL 或 FILL_AND_STROKE) ，并且图形出现自相交时，用于判断填充范围的
//        mPath.addPath(mPath2);
//
//        canvas.drawPath(mPath,mPaint);

//        //方法2 xxxTo()  画线
//        mPath.lineTo(300,300);
//        mPath.rLineTo(300,0);//relative相对上个点，往右画300px
//        canvas.drawPath(mPath,mPaint1);

        //quadTo(float x1, float y1, float x2, float y2) / rQuadTo(float dx1, float dy1, float dx2, float dy2) 画二次贝塞尔曲线

//        //moveTo(float x, float y) / rMoveTo(float x, float y) 移动到目标位置
//        mPath.rMoveTo(300,0);
//        mPath.rLineTo(0,300);
//        canvas.drawPath(mPath,mPaint1);

//        mPath.arcTo(300,300,600,600,90,90,true);//// 强制移动到弧形起点（无痕迹）
        mPath.addCircle(400,400,200, Path.Direction.CW);
        mPath.addCircle(550,400,200, Path.Direction.CW);
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        canvas.drawPath(mPath,mPaint);

        mPaint.setTextSize(100);//文字大小
        canvas.drawText("你好",400,800,mPaint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap,600,700,mPaint);
        //addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle) / addArc(RectF oval, float startAngle, float sweepAngle)
        //又是一个弧形的方法。一个叫 arcTo ，一个叫 addArc()，都是弧形，区别在哪里？其实很简单： addArc() 只是一个直接使用了 forceMoveTo = true 的简化版 arcTo() 。


    }
}
