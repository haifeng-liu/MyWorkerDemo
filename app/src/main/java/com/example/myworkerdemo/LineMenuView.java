package com.example.myworkerdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 创建人：Liuhaifeng
 * 时间：2021/1/27
 * 描述：
 */
public class LineMenuView extends SurfaceView {


    //棋子半径
    private int r = 20;
    //灵敏度
    private int l = 20;
    private Paint b_paint;
    private Paint r_paint;
    Canvas canvas;
    boolean flg=true;

    public LineMenuView(Context context) {
        super(context);
        init();
    }

    public LineMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    Paint linepaint;
    float[] lin_h = new float[6];
    float[] lin_v = new float[6];
    private List<Map<Float, Float>> dian = new ArrayList<>();
    private List<Map<Float, Float>> b_dian = new ArrayList<>();
    private List<Map<Float, Float>> r_dian = new ArrayList<>();


    public void init() {
        //棋盘线画笔
        linepaint = new Paint();
        linepaint.setColor(Color.BLACK);
        linepaint.setStyle(Paint.Style.STROKE);
        linepaint.setStrokeWidth(2);
        linepaint.setAntiAlias(true);

        //红方画笔
        r_paint = new Paint();
        r_paint.setColor(Color.RED);
        r_paint.setStyle(Paint.Style.FILL);
        r_paint.setStrokeWidth(2);
        r_paint.setAntiAlias(true);

        //黑方画笔
        b_paint = new Paint();
        b_paint.setColor(Color.BLACK);
        b_paint.setStyle(Paint.Style.FILL);
        b_paint.setStrokeWidth(2);
        b_paint.setAntiAlias(true);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int v_height = getMeasuredHeight();
        int v_wight = getMeasuredWidth();
        float jian = 0;
        if (v_height > v_wight) {
            jian = (v_wight - 14) / 6;
        } else {
            jian = (v_height - 14) / 6;
        }

        float no1_h = (v_height / 2) - (jian * 5 / 2);
        float no1_w = (v_wight / 2) - (jian * 5 / 2);

        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                lin_h[0] = no1_h;
            } else {
                lin_h[i] = lin_h[i - 1] + jian;
            }
        }
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                lin_v[0] = no1_w;
            } else {
                lin_v[i] = lin_v[i - 1] + jian;
            }
        }



        Log.d("tag", "lin-v:" + lin_v.toString());
        Log.d("tag", "lin-h:" + lin_h.toString());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getX() > (lin_v[0] - 20) && event.getX() < (lin_v[5] + 20) && event.getY() > (lin_h[0] - 20) && event.getY() < (lin_h[5] + 20)) {

                drawdian(event.getX(), event.getY());
            }
        }
        return true;

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
// 指定图片绘制区域(左上角的四分之一)
        Rect src = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        // 指定图片在屏幕上显示的区域(原图大小)
        Rect dst = new Rect((int) lin_v[0]-80,(int) lin_h[0]-80,(int) lin_v[5]+80,(int) lin_h[5]+80);
        canvas.drawBitmap(bitmap,src,dst,null);


        canvas.drawLine(lin_v[0], lin_h[0], lin_v[lin_h.length - 1], lin_h[0], linepaint);
        canvas.drawLine(lin_v[0], lin_h[1], lin_v[lin_h.length - 1], lin_h[1], linepaint);
        canvas.drawLine(lin_v[0], lin_h[2], lin_v[lin_h.length - 1], lin_h[2], linepaint);
        canvas.drawLine(lin_v[0], lin_h[3], lin_v[lin_h.length - 1], lin_h[3], linepaint);
        canvas.drawLine(lin_v[0], lin_h[4], lin_v[lin_h.length - 1], lin_h[4], linepaint);
        canvas.drawLine(lin_v[0], lin_h[5], lin_v[lin_h.length - 1], lin_h[5], linepaint);

        canvas.drawLine(lin_v[0], lin_h[0], lin_v[0], lin_h[lin_v.length - 1], linepaint);
        canvas.drawLine(lin_v[1], lin_h[0], lin_v[1], lin_h[lin_v.length - 1], linepaint);
        canvas.drawLine(lin_v[2], lin_h[0], lin_v[2], lin_h[lin_v.length - 1], linepaint);
        canvas.drawLine(lin_v[3], lin_h[0], lin_v[3], lin_h[lin_v.length - 1], linepaint);
        canvas.drawLine(lin_v[4], lin_h[0], lin_v[4], lin_h[lin_v.length - 1], linepaint);
        canvas.drawLine(lin_v[5], lin_h[0], lin_v[5], lin_h[lin_v.length - 1], linepaint);
        dian.clear();
        for (float v : lin_v) {
            for (float h : lin_h) {
                SetDian(v, h);
            }
        }
        if (b_dian.size() > 0) {
            for (Map d : b_dian) {
                Iterator<Map.Entry<Float, Float>> iter = d.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Float, Float> entry = iter.next();
                    float key = entry.getKey();
                    float value = entry.getValue();
                    Bitmap bitmaps= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_hei);
                    canvas.drawBitmap(bitmaps,key-(bitmaps.getWidth()/2),value-(bitmaps.getHeight()/2),null);
//                    canvas.drawBitmap(key,value,r,b_paint);
                }
            }
        }
        if (r_dian.size() > 0) {
            for (Map d : r_dian) {
                Iterator<Map.Entry<Float, Float>> iter = d.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Float, Float> entry = iter.next();
                    float key = entry.getKey();
                    float value = entry.getValue();
                    Bitmap bitmaps= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_bai);
                    canvas.drawBitmap(bitmaps,key-(bitmaps.getWidth()/2),value-(bitmaps.getHeight()/2),null);
//
                }
            }
        }




    }

    private void SetDian(float x, float y) {
        Map<Float, Float> map = new HashMap<>();
        map.put(x, y);
        dian.add(map);
    }

    private void drawdian(float x, float y) {
        for (Map d : dian) {
            Iterator<Map.Entry<Float, Float>> iter = d.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<Float, Float> entry = iter.next();
                float key = entry.getKey();
                float value = entry.getValue();
                if ((key - l) < x && x < (key + l) && (value - l) < y && (value + l) > y) {
                    Map<Float, Float> b = new HashMap<>();
                    b.put(key, value);
                    if ((key==lin_v[0]&&value==lin_h[0])
                           ||(key==lin_v[5]&&value==lin_h[0])
                           ||(key==lin_v[0]&&value==lin_h[5])
                            ||(key==lin_v[5]&&value==lin_h[5])){

                    }else{
                        if (flg){
                            if (r_dian.indexOf(b)<0&&b_dian.indexOf(b)<0){
                                r_dian.add(b);
                                flg=false;
                            }

                        }else{
                            if (r_dian.indexOf(b)<0&&b_dian.indexOf(b)<0){
                                b_dian.add(b);
                                flg=true;
                            }

                        }
                        invalidate();
                        return;
                    }

                }
            }


        }
    }
}
