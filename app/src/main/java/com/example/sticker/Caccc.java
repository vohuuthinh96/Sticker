package com.example.sticker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

/**
 * Created by : Thinhvh on 10/12/2021
 * Phone      : 0962890153 - 0398477967
 * Facebook   : https://www.facebook.com/thinh.super22
 * Skype      : https://join.skype.com/invite/fvfRTDLcGPJN
 * Mail       : thinhvhph04204@gmail.com
 */
public class Caccc extends android.view.View {
    private Bitmap mBitmap;
    Matrix matrix = new Matrix();
    PointF mPoint = new PointF();
    private int width;
    private int height;
    private Paint circlePaint= new Paint();
    Rect mRect = new Rect();

    public Caccc(Context context) {
        super(context);
        init(context);
    }

    public Caccc(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public Caccc(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public Caccc(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghh);
        mBitmap = getResizedBitmap(mBitmap, 500, 500);
        circlePaint.setColor(Color.RED);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPoint.x = w / 2;
        mPoint.y = h / 2;
        width = w;
        height = h;
        mRect.top = (int) (mPoint.y -50);
        mRect.bottom = (int) (mPoint.y+50);
        mRect.left = (int) (mPoint.x -50);
        mRect.right = (int) (mPoint.x +50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        matrix.postScale(0.5f, 0.5f);
//        matrix.postTranslate(mPoint.x - mBitmap.getWidth()/2, mPoint.y -mBitmap.getHeight()/2);
//        matrix.setScale(0.5f, 0.5f);
        canvas.drawBitmap(mBitmap, matrix, new Paint());

//        canvas.drawCircle(mPoint.x, mPoint.y,20,circlePaint);
        canvas.drawRect(mRect,circlePaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float downX = 0, downY = 0;
        float moveX, moveY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                matrix.reset();
                moveX = event.getX();
                moveY = event.getY();

//                matrix.postTranslate(100, 100);

                if (mRect.contains((int)moveX, (int)moveY)){
                    int newWith = mBitmap.getWidth()/2;
                    int newHeight = mBitmap.getHeight()/2;
                    float scale = 0.5f;

                    matrix.postScale(scale, scale);
                    // dchj chuyen den vi tri ma minh chi dinh.


                    matrix.postTranslate(mPoint.x - newWith/2, mPoint.y- newHeight/2);

//                    matrix.postTranslate(moveX - downX+newWith/2, moveY - downY +newHeight/2);
                }else {
                    matrix.postTranslate(moveX - downX - mBitmap.getWidth()/2, moveY - downY - mBitmap.getHeight()/2);
                }

                invalidate();
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }
}
