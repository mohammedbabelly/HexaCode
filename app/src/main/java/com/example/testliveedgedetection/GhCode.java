package com.example.testliveedgedetection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.testliveedgedetection.hexacode.Area;
import com.example.testliveedgedetection.hexacode.kParams;

import java.util.ArrayList;
import java.util.List;

public class GhCode extends View {
    Bitmap bitmap;
    List<Area> areas;
    kParams kParams;
    Paint mPaint;
    int screenWidth;
    int screenHeight;

    public GhCode(Context context, Bitmap bitmap, kParams kParams, List<Area> areas) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);


        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        Canvas canvas = new Canvas(bitmap);
        this.kParams = kParams;
        this.areas = areas;
        this.bitmap = bitmap;
        canvas.drawColor(0);
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, screenWidth, screenHeight, mPaint);
        for (int i = 0; i < 6; i++) {
            List<Point> pointList = new ArrayList<>();
            Point point1 = new Point(kParams.getX(), kParams.getY());
            Point point2 = new Point(kParams.getX() + kParams.getOffset_X_First_Side(), kParams.getY() + kParams.getOffset_Y_FirstVal());
            Point point3 = new Point(kParams.getX() + kParams.getOffset_X_First_Side(), kParams.getY() + kParams.getOffset_Y_SecondVal());
            Point point4 = new Point(kParams.getX(), kParams.getY() + kParams.getOffset_Y_ThirdVal());
            Point point5 = new Point(kParams.getX() - kParams.getOffset_X_Second_Side(), kParams.getY() + kParams.getOffset_Y_SecondVal());
            Point point6 = new Point(kParams.getX() - kParams.getOffset_X_Second_Side(), kParams.getY() + kParams.getOffset_Y_FirstVal());
            pointList.add(point1);
            pointList.add(point2);
            pointList.add(point3);
            pointList.add(point4);
            pointList.add(point5);
            pointList.add(point6);
            pointList.add(point1);
//            Path polygonPath = new Path();
//            polygonPath.moveTo(point1.x, point1.y);
//            for (int j = 1; j < 7; ++j)
//                polygonPath.lineTo(pointList.get(j).x, pointList.get(j).y);
//
//            canvas.drawPath(polygonPath, mPaint);


            for (int j = 0; j < areas.get(i).getSubAreas().size(); j++) {
                if (areas.get(i).getSubAreas().get(j).getColorStatus()) {
                    Path filedSubAreaPath = new Path();
                    Point[] filedSubAreaPoint = areas.get(i).getSubAreas().get(j).getPoints();

                    filedSubAreaPath.moveTo(filedSubAreaPoint[0].x, filedSubAreaPoint[0].y);
                    for (int k = 1; k < filedSubAreaPoint.length; ++k)
                        filedSubAreaPath.lineTo(filedSubAreaPoint[k].x, filedSubAreaPoint[k].y);

                    Paint filedSubAreaPaint = new Paint();
                    filedSubAreaPaint.setColor(Color.BLACK);
                    filedSubAreaPaint.setStyle(Paint.Style.FILL);

                    canvas.drawPath(filedSubAreaPath, filedSubAreaPaint);

                }
            }

            kParams.setX(kParams.getX() + kParams.getOffsetCol());
        }
        kParams.setX(kParams.getOffset_X0_Row2());
        kParams.setY(kParams.getOffset_Y0_Row2());
        for (int i = 0; i < 7; i++) {
            List<Point> pointList = new ArrayList<>();
            Point point1 = new Point(kParams.getX(), kParams.getY());
            Point point2 = new Point(kParams.getX() + kParams.getOffset_X_First_Side(), kParams.getY() + kParams.getOffset_Y_FirstVal());
            Point point3 = new Point(kParams.getX() + kParams.getOffset_X_First_Side(), kParams.getY() + kParams.getOffset_Y_SecondVal());
            Point point4 = new Point(kParams.getX(), kParams.getY() + kParams.getOffset_Y_ThirdVal());
            Point point5 = new Point(kParams.getX() - kParams.getOffset_X_Second_Side(), kParams.getY() + kParams.getOffset_Y_SecondVal());
            Point point6 = new Point(kParams.getX() - kParams.getOffset_X_Second_Side(), kParams.getY() + kParams.getOffset_Y_FirstVal());
            pointList.add(point1);
            pointList.add(point2);
            pointList.add(point3);
            pointList.add(point4);
            pointList.add(point5);
            pointList.add(point6);
            pointList.add(point1);
            Path polygonPath = new Path();
//            polygonPath.moveTo(point1.x, point1.y);
//            for (int j = 1; j < 7; ++j)
//                polygonPath.lineTo(pointList.get(j).x, pointList.get(j).y);
//
//            canvas.drawPath(polygonPath, mPaint);


            for (int j = 0; j < areas.get(i + 6).getSubAreas().size(); j++) {
                if (areas.get(i + 6).getSubAreas().get(j).getColorStatus()) {
                    Path filedSubAreaPath = new Path();
                    Point[] filedSubAreaPoint = areas.get(i + 6).getSubAreas().get(j).getPoints();

                    filedSubAreaPath.moveTo(filedSubAreaPoint[0].x, filedSubAreaPoint[0].y);
                    for (int k = 1; k < filedSubAreaPoint.length; ++k)
                        filedSubAreaPath.lineTo(filedSubAreaPoint[k].x, filedSubAreaPoint[k].y);

                    Paint filedSubAreaPaint = new Paint();
                    filedSubAreaPaint.setColor(Color.BLACK);
                    filedSubAreaPaint.setStyle(Paint.Style.FILL);

                    canvas.drawPath(filedSubAreaPath, filedSubAreaPaint);

                }
            }

            kParams.setX(kParams.getX() + kParams.getOffsetCol());
        }
        kParams.setX(kParams.getOffset_X0_Row3());
        kParams.setY(kParams.getOffset_Y0_Row3());
        for (int i = 0; i < 6; i++) {
            List<Point> pointList = new ArrayList<>();
            Point point1 = new Point(kParams.getX(), kParams.getY());
            Point point2 = new Point(kParams.getX() + kParams.getOffset_X_First_Side(), kParams.getY() + kParams.getOffset_Y_FirstVal());
            Point point3 = new Point(kParams.getX() + kParams.getOffset_X_First_Side(), kParams.getY() + kParams.getOffset_Y_SecondVal());
            Point point4 = new Point(kParams.getX(), kParams.getY() + kParams.getOffset_Y_ThirdVal());
            Point point5 = new Point(kParams.getX() - kParams.getOffset_X_Second_Side(), kParams.getY() + kParams.getOffset_Y_SecondVal());
            Point point6 = new Point(kParams.getX() - kParams.getOffset_X_Second_Side(), kParams.getY() + kParams.getOffset_Y_FirstVal());
            pointList.add(point1);
            pointList.add(point2);
            pointList.add(point3);
            pointList.add(point4);
            pointList.add(point5);
            pointList.add(point6);
            pointList.add(point1);
//            Path polygonPath = new Path();
//            polygonPath.moveTo(point1.x, point1.y);
//            for (int j = 1; j < 7; ++j)
//                polygonPath.lineTo(pointList.get(j).x, pointList.get(j).y);
//
//            canvas.drawPath(polygonPath, mPaint);


            for (int j = 0; j < areas.get(i + 13).getSubAreas().size(); j++) {
                if (areas.get(i + 13).getSubAreas().get(j).getColorStatus()) {
                    Path filedSubAreaPath = new Path();
                    Point[] filedSubAreaPoint = areas.get(i + 13).getSubAreas().get(j).getPoints();

                    filedSubAreaPath.moveTo(filedSubAreaPoint[0].x, filedSubAreaPoint[0].y);
                    for (int k = 1; k < filedSubAreaPoint.length; ++k)
                        filedSubAreaPath.lineTo(filedSubAreaPoint[k].x, filedSubAreaPoint[k].y);

                    Paint filedSubAreaPaint = new Paint();
                    filedSubAreaPaint.setColor(Color.BLACK);
                    filedSubAreaPaint.setStyle(Paint.Style.FILL);

                    canvas.drawPath(filedSubAreaPath, filedSubAreaPaint);

                }
            }

            kParams.setX(kParams.getX() + kParams.getOffsetCol());
        }
        kParams.setX(kParams.getOffset_X0_Row4());
        kParams.setY(kParams.getOffset_Y0_Row4());
        for (int i = 0; i < 6; i++) {
            List<Point> pointList = new ArrayList<>();
            Point point1 = new Point(kParams.getX(), kParams.getY());
            Point point2 = new Point(kParams.getX() + kParams.getOffset_X_First_Side(), kParams.getY() + kParams.getOffset_Y_FirstVal());
            Point point3 = new Point(kParams.getX() + kParams.getOffset_X_First_Side(), kParams.getY() + kParams.getOffset_Y_SecondVal());
            Point point4 = new Point(kParams.getX(), kParams.getY() + kParams.getOffset_Y_ThirdVal());
            Point point5 = new Point(kParams.getX() - kParams.getOffset_X_Second_Side(), kParams.getY() + kParams.getOffset_Y_SecondVal());
            Point point6 = new Point(kParams.getX() - kParams.getOffset_X_Second_Side(), kParams.getY() + kParams.getOffset_Y_FirstVal());
            pointList.add(point1);
            pointList.add(point2);
            pointList.add(point3);
            pointList.add(point4);
            pointList.add(point5);
            pointList.add(point6);
            pointList.add(point1);
//            Path polygonPath = new Path();
//            polygonPath.moveTo(point1.x, point1.y);
//            for (int j = 1; j < 7; ++j)
//                polygonPath.lineTo(pointList.get(j).x, pointList.get(j).y);
//
//            canvas.drawPath(polygonPath, mPaint);


            for (int j = 0; j < areas.get(i + 19).getSubAreas().size(); j++) {
                if (areas.get(i + 19).getSubAreas().get(j).getColorStatus()) {
                    Path filedSubAreaPath = new Path();
                    Point[] filedSubAreaPoint = areas.get(i + 19).getSubAreas().get(j).getPoints();

                    filedSubAreaPath.moveTo(filedSubAreaPoint[0].x, filedSubAreaPoint[0].y);
                    for (int k = 1; k < filedSubAreaPoint.length; ++k)
                        filedSubAreaPath.lineTo(filedSubAreaPoint[k].x, filedSubAreaPoint[k].y);

                    Paint filedSubAreaPaint = new Paint();
                    filedSubAreaPaint.setColor(Color.BLACK);
                    filedSubAreaPaint.setStyle(Paint.Style.FILL);

                    canvas.drawPath(filedSubAreaPath, filedSubAreaPaint);

                }
            }


            kParams.setX(kParams.getX() + kParams.getOffsetCol());
        }
        kParams.setX(kParams.getOffset_X0_Row5());
        kParams.setY(kParams.getOffset_Y0_Row5());
        for (int i = 0; i < 6; i++) {
            List<Point> pointList = new ArrayList<>();
            Point point1 = new Point(kParams.getX(), kParams.getY());
            Point point2 = new Point(kParams.getX() + kParams.getOffset_X_First_Side(), kParams.getY() + kParams.getOffset_Y_FirstVal());
            Point point3 = new Point(kParams.getX() + kParams.getOffset_X_First_Side(), kParams.getY() + kParams.getOffset_Y_SecondVal());
            Point point4 = new Point(kParams.getX(), kParams.getY() + kParams.getOffset_Y_ThirdVal());
            Point point5 = new Point(kParams.getX() - kParams.getOffset_X_Second_Side(), kParams.getY() + kParams.getOffset_Y_SecondVal());
            Point point6 = new Point(kParams.getX() - kParams.getOffset_X_Second_Side(), kParams.getY() + kParams.getOffset_Y_FirstVal());
            pointList.add(point1);
            pointList.add(point2);
            pointList.add(point3);
            pointList.add(point4);
            pointList.add(point5);
            pointList.add(point6);
            pointList.add(point1);
//            Path polygonPath = new Path();
//            polygonPath.moveTo(point1.x, point1.y);
//            for (int j = 1; j < 7; ++j)
//                polygonPath.lineTo(pointList.get(j).x, pointList.get(j).y);
//
//            canvas.drawPath(polygonPath, mPaint);


            for (int j = 0; j < areas.get(i + 25).getSubAreas().size(); j++) {
                if (areas.get(i + 25).getSubAreas().get(j).getColorStatus()) {
                    Path filedSubAreaPath = new Path();
                    Point[] filedSubAreaPoint = areas.get(i + 25).getSubAreas().get(j).getPoints();

                    filedSubAreaPath.moveTo(filedSubAreaPoint[0].x, filedSubAreaPoint[0].y);
                    for (int k = 1; k < filedSubAreaPoint.length; ++k)
                        filedSubAreaPath.lineTo(filedSubAreaPoint[k].x, filedSubAreaPoint[k].y);

                    Paint filedSubAreaPaint = new Paint();
                    filedSubAreaPaint.setColor(Color.BLACK);
                    filedSubAreaPaint.setStyle(Paint.Style.FILL);

                    canvas.drawPath(filedSubAreaPath, filedSubAreaPaint);

                }
            }

            kParams.setX(kParams.getX() + kParams.getOffsetCol());
        }

    }
}
