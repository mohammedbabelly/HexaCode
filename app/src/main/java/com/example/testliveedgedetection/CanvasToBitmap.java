package com.example.testliveedgedetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class CanvasToBitmap extends View {

    Paint paint = new Paint();
    Rect mRect = new Rect();
    Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);

    public CanvasToBitmap(Context context ) {
        super(context);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
    }

    @Override
    public void onDraw(Canvas canvas) {

        mRect.set(0, 0, 200, 200);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(mRect, paint);
        canvas.setBitmap(bitmap);

        ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
        try{
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, mByteArrayOutputStream);

            bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(mByteArrayOutputStream.toByteArray()));
            mByteArrayOutputStream.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}