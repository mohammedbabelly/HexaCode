package com.example.testliveedgedetection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyView extends View {

    Paint mPaint, otherPaint, outerPaint, mTextPaint;
    RectF mRectF;
    int mPadding;
    Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);

//    float arcLeft, arcTop, arcRight, arcBottom;

    Path mPath;


    public MyView(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
//
//
//        mTextPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
//        mTextPaint.setColor(Color.BLACK);
//        mTextPaint.setTextSize(pxFromDp(context, 24));

//        otherPaint = new Paint();
//
//        outerPaint = new Paint();
//        outerPaint.setStyle(Paint.Style.FILL);
//        outerPaint.setColor(Color.YELLOW);

//        mPadding = 100;


        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);


        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

//        arcLeft = pxFromDp(context, 20);
//        arcTop = pxFromDp(context, 20);
//        arcRight = pxFromDp(context, 100);
//        arcBottom = pxFromDp(context, 100);


        Point p1 = new Point((int) pxFromDp(context, 80) + (screenWidth / 2), (int) pxFromDp(context, 40));
        Point p2 = new Point((int) pxFromDp(context, 40) + (screenWidth / 2), (int) pxFromDp(context, 80));
        Point p3 = new Point((int) pxFromDp(context, 120) + (screenWidth / 2), (int) pxFromDp(context, 80));
        Point p0 = new Point(50, 80);
        mPath = new Path();
        mPath.moveTo(p1.x, p1.y);
        mPath.lineTo(p2.x, p2.y);
        mPath.lineTo(p3.x, p3.y);
        mPath.lineTo(p0.x, p0.y);
        mPath.close();

//        mRectF = new RectF(screenWidth / 4, screenHeight / 3, screenWidth / 6, screenHeight / 2);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //    canvas.drawRoundRect(mRectF, 10, 10, otherPaint);
        //  canvas.clipRect(mRectF, Region.Op.DIFFERENCE);
        //    canvas.drawPaint(outerPaint);

        //       canvas.drawLine(250, 250, 400, 400, mPaint);
        //      canvas.drawRect(mPadding, mPadding, getWidth() - mPadding, getHeight() - mPadding, mPaint);
//        canvas.drawArc(arcLeft, arcTop, arcRight, arcBottom, 75, 45, true, mPaint);


//        otherPaint.setColor(Color.GREEN);
//        otherPaint.setStyle(Paint.Style.FILL);

//        canvas.drawRect(
//                getLeft() + (getRight() - getLeft()) / 3,
//                getTop() + (getBottom() - getTop()) / 3,
//                getRight() - (getRight() - getLeft()) / 3,
//                getBottom() - (getBottom() - getTop()) / 3, otherPaint);
//
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawPath(mPath, mPaint);
        CanvasToBitmap canvasToBitmap = new CanvasToBitmap(getContext());
        Bitmap bitmap = canvasToBitmap.bitmap;
        System.out.println(bitmap.getByteCount());
        saveBitmap("s", bitmap);
//        canvas.setBitmap(bitmap);
        //   saveBitmap("Heu",bitmap);
        //   System.out.println(bitmap.getByteCount());
//        canvas.setBitmap(bitmap);
//        File f = convertToPNG(bitmap, "Image");
//        System.out.println(f.toString());
//        ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
//        try {
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, mByteArrayOutputStream);
//
//            bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(mByteArrayOutputStream.toByteArray()));
//            mByteArrayOutputStream.close();
//            System.out.println(bitmap.getByteCount());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        otherPaint.setColor(Color.BLACK);
        // canvas.drawCircle(getWidth() / 2, getHeight() / 2, arcLeft, otherPaint);

        //  canvas.drawText("Canvas basics", (float) (getWidth() * 0.3), (float) (getHeight() * 0.8), mTextPaint);

    }


    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static void saveBitmap(String bitName,
                                  Bitmap mBitmap) {//  ww  w.j  a va 2s.c  o  m

        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() , bitName + ".png");

        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
//    public static File convertToPNG(Bitmap image, String imageFileName) {
//
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File imageFile = null;
//        try {
//            imageFile = File.createTempFile(
//                    imageFileName,  /* prefix */
//                    ".png",         /* suffix */
//                    storageDir      /* directory */
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        FileOutputStream outStream = null;
////        try {
////            outStream = new FileOutputStream(imageFile);
////            image.compress(Bitmap.CompressFormat.PNG, 100, outStream);
////            outStream.flush();
////            outStream.close();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        //  return BitmapFactory.decodeFile(imageFile.getAbsolutePath());
//        return imageFile;
//    }
}