/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testliveedgedetection.hexacode;

//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.geom.GeneralPath;
//import java.awt.image.BufferedImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testliveedgedetection.GhCode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;

/**
 * @author Besher Rehawee
 */
public class Functionality {
    ViewGroup viewGroup;
    TextView newTextView;
    private Context context;
    private int width;
    private int height;
    private int xBase;
    private int yBase;

    public Functionality(ViewGroup viewgroup, Context context, TextView newTextView) {
        this.viewGroup = viewgroup;
        this.context = context;
        this.newTextView = newTextView;
    }

    public ConvertingDto ConvertDCtoHX(Integer dec, ArrayList<Integer> value, CodeCountDigite type) {
        ConvertingDto convertingDto = new ConvertingDto();
        convertingDto.setDecValue(dec);

        if (dec >= 0 && dec < 68719476735L) {

            while (convertingDto.getDecValue() != 0 && convertingDto.getHXValue() != null && convertingDto.getHXValue().trim().isEmpty()) {
                value.add((int) (dec % 64));
                convertingDto = ConvertDCtoHX(dec / 64, value, type);
                int val = convertingDto.getDecValue();
                val /= 64;
                convertingDto.setDecValue(val);
            }
        }
        ArrayList<Integer> vlaidateVlue = ValidateCodeDigit(value, type);
        ConvertingDto tempVar = new ConvertingDto();
        String sad = HexaCodeTable(vlaidateVlue);
        tempVar.setHXValue(sad);
        tempVar.setDecValue(dec);

        return tempVar;
    }

    public Code ConvertHXtoDC(Bitmap buffer) throws IOException {
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        String filePath = inputFile.getAbsolutePath();
//        Bitmap buffer = BitmapFactory.decodeFile(inputFile.getAbsolutePath(),bmOptions);
        int width = buffer.getWidth();
        int height = buffer.getHeight();
        int x = (width * 23) / 100;
        int y = (height * 17) / 100;
        int offsetCol = 50 * x / 100;

        int offset_X_First_Side = (int) (12.5 * x / 100);
        int offset_Y_FirstVal = (30 * y / 100);
        int offset_Y_SecondVal = (80 * y / 100);
        int offset_Y_ThirdVal = (110 * y / 100);
        int offset_Y_ForthVal = (55 * y / 100);

        int offset_X0_Row2 = x - (25 * x / 100);
        int offset_Y0_Row2 = y + (80 * y / 100);
        int offset_X0_Row3 = x;
        int offset_Y0_Row3 = offset_Y0_Row2 + (80 * y / 100);
        int offset_X0_Row4 = x + (25 * x / 100);
        int offset_Y0_Row4 = offset_Y0_Row3 + (80 * y / 100);
        int offset_X0_Row5 = x;
        int offset_Y0_Row5 = offset_Y0_Row4 + (80 * y / 100);

        String StandardizationHX = "",
                ItemHX = "", QualityHX = "",
                SectorHX = "", SectionHX = "",
                UnitHX = "", TimeStumpHX = "",
                EntityHX = "", ValidtyHX = "";
        String hexaCode = "";

        Area area = new Area();
        String nameOfArea = "1A";
        ArrayList<Integer> z = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> idsSub = new ArrayList<Integer>();

            int q1 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_FirstVal);
            if (BlackWhiteChecker(q1)) {
                idsSub.add(1);
            }
            int q2 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_ForthVal);

            if (BlackWhiteChecker(q2)) {
                idsSub.add(2);
            }
            int q3 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_SecondVal);
            if (BlackWhiteChecker(q3)) {
                idsSub.add(3);
            }
            int q4 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_SecondVal);
            if (BlackWhiteChecker(q4)) {
                idsSub.add(4);
            }
            int q5 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_ForthVal);
            if (BlackWhiteChecker(q5)) {
                idsSub.add(5);
            }
            int q6 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_FirstVal);
            if (BlackWhiteChecker(q6)) {
                idsSub.add(6);
            }
            Map<String, Area> areas = Areas(x, y);
            ArrayList<String> keys = new ArrayList<>();
            for (String key : areas.keySet()) {
                keys.add(key);
            }

            for (String key : keys) {
                area = areas.get(key);

                for (int j = 0; j < area.getSubAreas().size(); j++) {

                    for (int k = 0; k < idsSub.size(); k++) {

                        if (area.getSubAreas().get(j).getId() == idsSub.get(k)) {
                            z.add(idsSub.get(k));
                        }
                    }

                }
                if (z.size() == idsSub.size() && z.size() == area.getSubAreas().size()) {
                    nameOfArea = area.Name;
                    break;

                } else {
                    z.clear();
                }
            }
            z.clear();
            if (i < 3) {
                StandardizationHX += "_" + nameOfArea;

            } else {
                SectorHX += "_" + nameOfArea;
            }

            // Print and display the Rank and Name
            x += offsetCol;

        }
        x = offset_X0_Row2;
        y = offset_Y0_Row2;
        for (int i = 0; i < 7; i++) {
            ArrayList<Integer> idsSub = new ArrayList<Integer>();

            int q1 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_FirstVal);
            if (BlackWhiteChecker(q1)) {
                idsSub.add(1);
            }
            int q2 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_ForthVal);

            if (BlackWhiteChecker(q2)) {
                idsSub.add(2);
            }
            int q3 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_SecondVal);
            if (BlackWhiteChecker(q3)) {
                idsSub.add(3);
            }
            int q4 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_SecondVal);
            if (BlackWhiteChecker(q4)) {
                idsSub.add(4);
            }
            int q5 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_ForthVal);
            if (BlackWhiteChecker(q5)) {
                idsSub.add(5);
            }
            int q6 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_FirstVal);
            if (BlackWhiteChecker(q6)) {
                idsSub.add(6);
            }
            Map<String, Area> areas = Areas(x, y);
            ArrayList<String> keys = new ArrayList<>();
            for (String key : areas.keySet()) {
                keys.add(key);
            }

            for (String key : keys) {
                area = areas.get(key);

                for (int j = 0; j < area.getSubAreas().size(); j++) {

                    for (int k = 0; k < idsSub.size(); k++) {

                        if (area.getSubAreas().get(j).getId() == idsSub.get(k)) {
                            z.add(idsSub.get(k));
                        }
                    }
                }
                if (z.size() == idsSub.size() && z.size() == area.getSubAreas().size()) {
                    nameOfArea = area.Name;
                    break;

                } else {
                    z.clear();
                }
            }
            z.clear();
            if (i > 0 && i < 6) {
                EntityHX += "_" + nameOfArea;
            }

            // Print and display the Rank and Name
            x += offsetCol;

        }
        x = offset_X0_Row3;
        y = offset_Y0_Row3;
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> idsSub = new ArrayList<Integer>();

            int q1 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_FirstVal);
            if (BlackWhiteChecker(q1)) {
                idsSub.add(1);
            }
            int q2 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_ForthVal);

            if (BlackWhiteChecker(q2)) {
                idsSub.add(2);
            }
            int q3 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_SecondVal);
            if (BlackWhiteChecker(q3)) {
                idsSub.add(3);
            }
            int q4 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_SecondVal);
            if (BlackWhiteChecker(q4)) {
                idsSub.add(4);
            }
            int q5 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_ForthVal);
            if (BlackWhiteChecker(q5)) {
                idsSub.add(5);
            }
            int q6 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_FirstVal);
            if (BlackWhiteChecker(q6)) {
                idsSub.add(6);
            }
            Map<String, Area> areas = Areas(x, y);
            ArrayList<String> keys = new ArrayList<>();
            for (String key : areas.keySet()) {
                keys.add(key);
            }

            for (String key : keys) {
                area = areas.get(key);

                for (int j = 0; j < area.getSubAreas().size(); j++) {

                    for (int k = 0; k < idsSub.size(); k++) {

                        if (area.getSubAreas().get(j).getId() == idsSub.get(k)) {
                            z.add(idsSub.get(k));
                        }
                    }

                }
                if (z.size() == idsSub.size() && z.size() == area.getSubAreas().size()) {
                    nameOfArea = area.Name;
                    break;

                } else {
                    z.clear();
                }
            }
            z.clear();
            TimeStumpHX += "_" + nameOfArea;

            // Print and display the Rank and Name
            x += offsetCol;

        }
        x = offset_X0_Row4;
        y = offset_Y0_Row4;
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> idsSub = new ArrayList<Integer>();

            int q1 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_FirstVal);
            if (BlackWhiteChecker(q1)) {
                idsSub.add(1);
            }
            int q2 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_ForthVal);

            if (BlackWhiteChecker(q2)) {
                idsSub.add(2);
            }
            int q3 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_SecondVal);
            if (BlackWhiteChecker(q3)) {
                idsSub.add(3);
            }
            int q4 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_SecondVal);
            if (BlackWhiteChecker(q4)) {
                idsSub.add(4);
            }
            int q5 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_ForthVal);
            if (BlackWhiteChecker(q5)) {
                idsSub.add(5);
            }
            int q6 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_FirstVal);
            if (BlackWhiteChecker(q6)) {
                idsSub.add(6);
            }
            Map<String, Area> areas = Areas(x, y);
            ArrayList<String> keys = new ArrayList<>();
            for (String key : areas.keySet()) {
                keys.add(key);
            }

            for (String key : keys) {
                area = areas.get(key);

                for (int j = 0; j < area.getSubAreas().size(); j++) {

                    for (int k = 0; k < idsSub.size(); k++) {

                        if (area.getSubAreas().get(j).getId() == idsSub.get(k)) {
                            z.add(idsSub.get(k));
                        }
                    }

                }
                if (z.size() == idsSub.size() && z.size() == area.getSubAreas().size()) {
                    nameOfArea = area.Name;
                    break;

                } else {
                    z.clear();
                }
            }
            z.clear();
            if (i < 2) {
                SectionHX += "_" + nameOfArea;
            } else {
                if (i >= 2 && i < 5) {
                    ItemHX += "_" + nameOfArea;
                }
            }

            // Print and display the Rank and Name
            x += offsetCol;

        }
        x = offset_X0_Row5;
        y = offset_Y0_Row5;
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> idsSub = new ArrayList<Integer>();

            int q1 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_FirstVal);
            if (BlackWhiteChecker(q1)) {
                idsSub.add(1);
            }
            int q2 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_ForthVal);

            if (BlackWhiteChecker(q2)) {
                idsSub.add(2);
            }
            int q3 = buffer.getPixel(x + offset_X_First_Side, y + offset_Y_SecondVal);
            if (BlackWhiteChecker(q3)) {
                idsSub.add(3);
            }
            int q4 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_SecondVal);
            if (BlackWhiteChecker(q4)) {
                idsSub.add(4);
            }
            int q5 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_ForthVal);
            if (BlackWhiteChecker(q5)) {
                idsSub.add(5);
            }
            int q6 = buffer.getPixel(x - offset_X_First_Side, y + offset_Y_FirstVal);
            if (BlackWhiteChecker(q6)) {
                idsSub.add(6);
            }
            Map<String, Area> areas = Areas(x, y);
            ArrayList<String> keys = new ArrayList<>();
            for (String key : areas.keySet()) {
                keys.add(key);
            }

            for (String key : keys) {
                area = areas.get(key);

                for (int j = 0; j < area.getSubAreas().size(); j++) {

                    for (int k = 0; k < idsSub.size(); k++) {

                        if (area.getSubAreas().get(j).getId() == idsSub.get(k)) {
                            z.add(idsSub.get(k));
                        }
                    }

                }
                if (z.size() == idsSub.size() && z.size() == area.getSubAreas().size()) {
                    nameOfArea = area.Name;
                    break;

                } else {
                    z.clear();
                }
            }
            z.clear();
            if (i == 0) {
                QualityHX += "_" + nameOfArea;
            } else {
                if (i > 0 && i < 3) {
                    UnitHX += "_" + nameOfArea;
                } else {
                    ValidtyHX += "_" + nameOfArea;
                }
            }

            // Print and display the Rank and Name
            x += offsetCol;

        }

        Code s = new Code();
        s.StandardizationHX = StandardizationHX;
        s.Standardization = (int) DecimalTable(StandardizationHX);

        s.SectorHX = SectorHX;
        s.Sector = (int) DecimalTable(SectorHX);

        s.TimeStumpHX = TimeStumpHX;
        s.TimeStump = (int) DecimalTable(TimeStumpHX);

        s.SectionHX = SectionHX;
        s.Section = (int) DecimalTable(SectionHX);

        s.EntityHX = EntityHX;
        s.Entity = (int) DecimalTable(EntityHX);

        s.ItemHX = ItemHX;
        s.Item = (int) DecimalTable(ItemHX);

        s.QualityHX = QualityHX;
        s.Quality = (int) DecimalTable(QualityHX);

        s.UnitHX = UnitHX;
        s.Unit = (int) DecimalTable(UnitHX);

        s.ValidtyHX = ValidtyHX;
        s.Validty = (int) DecimalTable(ValidtyHX);
        return s;
    }

    public ArrayList<Area> ConvertCode(int x0, int y0, String StandardizationHX, String ItemHX, String QualityHX, String SectorHX, String SectionHX, String UnitHX, String TimeStumpHX, String EntityHX, String ValidtyHX) {
        //initilize
        this.width = x0;
        this.height = y0;
        int x = (x0 * 23) / 100;
        int y = (y0 * 17) / 100;

        xBase = x;
        yBase = y;
        int offset_X_First_Side = (25 * x / 100);
        int offset_X_Second_Side = (25 * x / 100);
        int offset_Y_FirstVal = (30 * y / 100);
        int offset_Y_SecondVal = (80 * y / 100);
        int offset_Y_ThirdVal = (110 * y / 100);
        int offset_Y_ForthVal = (55 * y / 100);
        //int x = 200;
        //int y = 100;

        int offset_X0_Row2 = x - (25 * x / 100);
        int offset_Y0_Row2 = y + (80 * y / 100);
        int offset_X0_Row3 = x;
        int offset_Y0_Row3 = offset_Y0_Row2 + (80 * y / 100);
        int offset_X0_Row4 = x + (25 * x / 100);
        int offset_Y0_Row4 = offset_Y0_Row3 + (80 * y / 100);
        int offset_X0_Row5 = x;
        int offset_Y0_Row5 = offset_Y0_Row4 + (80 * y / 100);
        ArrayList<Area> areas = new ArrayList<Area>();
        String[] standVal = StandardizationHX.split("_");
        for (int i = 1; i < 4; i++) {
            Map<String, Area> area = Areas(x, y);
            Area stand = GetPointsEdditing(area.get(standVal[i]), i - 1);
            areas.add(stand);

        }
        //endregion

        //region Draw SectorHX
        String[] sectorVal = SectorHX.split("_");
        for (int i = 1; i < 4; i++) {
            Map<String, Area> area = Areas(x, y);
            Area sector = GetPointsEdditing(area.get(sectorVal[i]), i + 2);
            areas.add(sector);

        }
        //endregion
        x = offset_X0_Row2;
        y = offset_Y0_Row2;

        //region Base
        Map<String, Area> area2 = Areas(x, y);
        areas.add(GetPointsEdditing(area2.get("4H"), 0));
        //endregion
        //region Draw EntityHX
        String[] EntityVal = EntityHX.split("_");
        for (int i = 1; i < 6; i++) {
            Map<String, Area> area = Areas(x, y);
            Area Entity = GetPointsEdditing(area.get(EntityVal[i]), i);
            areas.add(Entity);

        }
        //endregion
        // region Base
        Map<String, Area> areaO = Areas(x, y);
        areas.add(GetPointsEdditing(areaO.get("4H"), 6));
        //endregion
        x = offset_X0_Row3;
        y = offset_Y0_Row3;
        //  region Draw TimeStumpHX
        String[] TimeStumpVal = TimeStumpHX.split("_");
        for (int i = 1; i < 7; i++) {
            Map<String, Area> area = Areas(x, y);
            Area TimeStump = GetPointsEdditing(area.get(TimeStumpVal[i]), i - 1);
            areas.add(TimeStump);

        }

        //endregion
        x = offset_X0_Row4;
        y = offset_Y0_Row4;
        Map<String, Area> area4 = Areas(x, y);
        //region Draw SectionHX
        String[] SectionVal = SectionHX.split("_");
        for (int i = 1; i < 3; i++) {
            Map<String, Area> area = Areas(x, y);
            Area Section = GetPointsEdditing(area.get(SectionVal[i]), i - 1);
            areas.add(Section);
        }
        //endregion
        //region Draw ItemHX
        String[] ItemVal = ItemHX.split("_");
        for (int i = 1; i < 4; i++) {
            Map<String, Area> area = Areas(x, y);
            Area Item = GetPointsEdditing(area.get(ItemVal[i]), i + 1);
            areas.add(Item);
        }
        //endregion
        //region Base
        Area base3 = GetPointsEdditing(area4.get("4H"), 5);
        areas.add(base3);
        //endregion
        x = offset_X0_Row5;
        y = offset_Y0_Row5;
        Map<String, Area> area5 = Areas(x, y);
        //region Draw QualityHX
        String[] QualityVal = QualityHX.split("_");
        Area Quality1 = GetPointsEdditing(area5.get(QualityVal[1]), 0);
        areas.add(Quality1);
        //endregion
        //region Draw UnitHX
        String[] UnitVal = UnitHX.split("_");
        for (int i = 1; i < 3; i++) {
            Map<String, Area> area = Areas(x, y);
            Area Unit = GetPointsEdditing(area.get(UnitVal[i]), i);
            areas.add(Unit);

        }

        //endregion
        //region Draw ValidtyHX
        String[] ValidtyVal = ValidtyHX.split("_");
        for (int i = 1; i < 4; i++) {
            Map<String, Area> area = Areas(x, y);
            Area Validty = GetPointsEdditing(area.get(ValidtyVal[i]), i + 2);
            areas.add(Validty);

        }
        //endregion
        return areas;
    }

    public final boolean DrawCode(List<Area> areas) {

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
        ///#region Inithialize
        kParams params = new kParams();
        params.setX((width * 23) / 100);
        params.setY((height * 17) / 100);

        params.setOffsetCol(50 * params.getX() / 100);
        params.setOffset_X_First_Side((25 * params.getX() / 100));
        params.setOffset_X_Second_Side(25 * params.getX() / 100);
        params.setOffset_Y_FirstVal((30 * params.getY() / 100));
        params.setOffset_Y_SecondVal(80 * params.getY() / 100);
        params.setOffset_Y_ThirdVal(110 * params.getY() / 100);
        params.setOffset_Y_ForthVal(55 * params.getY() / 100);
        params.setOffset_X0_Row2(params.getX() - (25 * params.getX() / 100));
        params.setOffset_Y0_Row2(params.getY() + (80 * params.getY() / 100));
        params.setOffset_X0_Row3(params.getX());
        params.setOffset_Y0_Row3(params.getOffset_Y0_Row2() + (80 * params.getY() / 100));
        params.setOffset_X0_Row4(params.getX() + (25 * params.getX() / 100));
        params.setOffset_Y0_Row4(params.getOffset_Y0_Row3() + (80 * params.getY() / 100));
        params.setOffset_X0_Row5(params.getX());
        params.setOffset_Y0_Row5(params.getOffset_Y0_Row4() + (80 * params.getY() / 100));
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);


        GhCode ghCode = new GhCode(context, bitmap, params, areas);

//        newTextView.setText("New Text added");
//        viewGroup.addView(newTextView);
        viewGroup.addView(ghCode);

//        x = offset_X0_Row2;
//        y = offset_Y0_Row2;
//        for (int i = 0; i < 7; i++) {
//            Point point1 = new Point(x, y);
//            Point point2 = new Point(x + offset_X_First_Side, y + offset_Y_FirstVal);
//            Point point3 = new Point(x + offset_X_First_Side, y + offset_Y_SecondVal);
//            Point point4 = new Point(x, y + offset_Y_ThirdVal);
//            Point point5 = new Point(x - offset_X_Second_Side, y + offset_Y_SecondVal);
//            Point point6 = new Point(x - offset_X_Second_Side, y + offset_Y_FirstVal);
//            Point[] curvePoints
//                    = {
//                        point1,
//                        point2,
//                        point3,
//                        point4,
//                        point5,
//                        point6,
//                        point1,};
//
//            for (int j = 0; j < areas.get(i + 6).getSubAreas().size(); j++) {
//                if (areas.get(i + 6).getSubAreas().get(j).getColorStatus()) {
//                    ArrayList<Integer> xp = new ArrayList<Integer>();
//                    ArrayList<Integer> yp = new ArrayList<Integer>();
//                    for (int k = 0; k < areas.get(i + 6).getSubAreas().get(j).getPoints().length; k++) {
//                        Point point = areas.get(i + 6).getSubAreas().get(j).getPoints()[k];
//                        xp.add(point.x);
//                        yp.add(point.y);
//
//                    }
//                    Integer[] sd = xp.toArray(new Integer[0]);
//                    int[] p1 = new int[sd.length];
//                    for (int k = 0; k < sd.length; k++) {
//                        p1[k] = sd[k];
//                    }
//                    Integer[] qd = yp.toArray(new Integer[0]);
//                    int[] p2 = new int[qd.length];
//                    for (int k = 0; k < qd.length; k++) {
//                        p2[k] = qd[k];
//                    }
//                    double pointsw[][] = {{p1[0], p2[0]}, {p1[1], p2[1]}, {p1[2], p2[2]}, {p1[0], p2[0]}};
//
//                    GeneralPath star = new GeneralPath();
//                    star.moveTo(pointsw[0][0], pointsw[0][1]);
//                    for (int k = 1; k < pointsw.length; k++) {
//                        star.lineTo(pointsw[k][0], pointsw[k][1]);
//                    }
//
//                    star.closePath();
//                    ig2.fill(star);
//
//                }
//            }
//            x += offsetCol;
//
//        }
//
//        x = offset_X0_Row3;
//        y = offset_Y0_Row3;
//        for (int i = 0; i < 6; i++) {
//            Point point1 = new Point(x, y);
//            Point point2 = new Point(x + offset_X_First_Side, y + offset_Y_FirstVal);
//            Point point3 = new Point(x + offset_X_First_Side, y + offset_Y_SecondVal);
//            Point point4 = new Point(x, y + offset_Y_ThirdVal);
//            Point point5 = new Point(x - offset_X_Second_Side, y + offset_Y_SecondVal);
//            Point point6 = new Point(x - offset_X_Second_Side, y + offset_Y_FirstVal);
//            Point[] curvePoints
//                    = {
//                        point1,
//                        point2,
//                        point3,
//                        point4,
//                        point5,
//                        point6,
//                        point1,};
//
//            for (int j = 0; j < areas.get(i + 13).getSubAreas().size(); j++) {
//                if (areas.get(i + 13).getSubAreas().get(j).getColorStatus()) {
//                    ArrayList<Integer> xp = new ArrayList<Integer>();
//                    ArrayList<Integer> yp = new ArrayList<Integer>();
//                    for (int k = 0; k < areas.get(i + 13).getSubAreas().get(j).getPoints().length; k++) {
//                        Point point = areas.get(i + 13).getSubAreas().get(j).getPoints()[k];
//                        xp.add(point.x);
//                        yp.add(point.y);
//
//                    }
//                    Integer[] sd = xp.toArray(new Integer[0]);
//                    int[] p1 = new int[sd.length];
//                    for (int k = 0; k < sd.length; k++) {
//                        p1[k] = sd[k];
//                    }
//                    Integer[] qd = yp.toArray(new Integer[0]);
//                    int[] p2 = new int[qd.length];
//                    for (int k = 0; k < qd.length; k++) {
//                        p2[k] = qd[k];
//                    }
//                    double pointsw[][] = {{p1[0], p2[0]}, {p1[1], p2[1]}, {p1[2], p2[2]}, {p1[0], p2[0]}};
//
//                    GeneralPath star = new GeneralPath();
//                    star.moveTo(pointsw[0][0], pointsw[0][1]);
//                    for (int k = 1; k < pointsw.length; k++) {
//                        star.lineTo(pointsw[k][0], pointsw[k][1]);
//                    }
//
//                    star.closePath();
//                    ig2.fill(star);
//
//                }
//            }
//            x += offsetCol;
//        }
//
//        x = offset_X0_Row4;
//        y = offset_Y0_Row4;
//        for (int i = 0; i < 6; i++) {
//            Point point1 = new Point(x, y);
//            Point point2 = new Point(x + offset_X_First_Side, y + offset_Y_FirstVal);
//            Point point3 = new Point(x + offset_X_First_Side, y + offset_Y_SecondVal);
//            Point point4 = new Point(x, y + offset_Y_ThirdVal);
//            Point point5 = new Point(x - offset_X_Second_Side, y + offset_Y_SecondVal);
//            Point point6 = new Point(x - offset_X_Second_Side, y + offset_Y_FirstVal);
//            Point[] curvePoints
//                    = {
//                        point1,
//                        point2,
//                        point3,
//                        point4,
//                        point5,
//                        point6,
//                        point1,};
//
//            for (int j = 0; j < areas.get(i + 19).getSubAreas().size(); j++) {
//                if (areas.get(i + 19).getSubAreas().get(j).getColorStatus()) {
//                    ArrayList<Integer> xp = new ArrayList<Integer>();
//                    ArrayList<Integer> yp = new ArrayList<Integer>();
//                    for (int k = 0; k < areas.get(i + 19).getSubAreas().get(j).getPoints().length; k++) {
//                        Point point = areas.get(i + 19).getSubAreas().get(j).getPoints()[k];
//                        xp.add(point.x);
//                        yp.add(point.y);
//
//                    }
//                    Integer[] sd = xp.toArray(new Integer[0]);
//                    int[] p1 = new int[sd.length];
//                    for (int k = 0; k < sd.length; k++) {
//                        p1[k] = sd[k];
//                    }
//                    Integer[] qd = yp.toArray(new Integer[0]);
//                    int[] p2 = new int[qd.length];
//                    for (int k = 0; k < qd.length; k++) {
//                        p2[k] = qd[k];
//                    }
//                    double pointsw[][] = {{p1[0], p2[0]}, {p1[1], p2[1]}, {p1[2], p2[2]}, {p1[0], p2[0]}};
//
//                    GeneralPath star = new GeneralPath();
//                    star.moveTo(pointsw[0][0], pointsw[0][1]);
//                    for (int k = 1; k < pointsw.length; k++) {
//                        star.lineTo(pointsw[k][0], pointsw[k][1]);
//                    }
//
//                    star.closePath();
//                    ig2.fill(star);
//
//                }
//            }
//            x += offsetCol;
//        }
//
//        for (int i = 0; i < 6; i++) {
//            Point point1 = new Point(x, y);
//            Point point2 = new Point(x + offset_X_First_Side, y + offset_Y_FirstVal);
//            Point point3 = new Point(x + offset_X_First_Side, y + offset_Y_SecondVal);
//            Point point4 = new Point(x, y + offset_Y_ThirdVal);
//            Point point5 = new Point(x - offset_X_Second_Side, y + offset_Y_SecondVal);
//            Point point6 = new Point(x - offset_X_Second_Side, y + offset_Y_FirstVal);
//            Point[] curvePoints
//                    = {
//                        point1,
//                        point2,
//                        point3,
//                        point4,
//                        point5,
//                        point6,
//                        point1,};
//            x = offset_X0_Row5;
//            y = offset_Y0_Row5;
//
//            for (int j = 0; j < areas.get(i + 25).getSubAreas().size(); j++) {
//                if (areas.get(i + 25).getSubAreas().get(j).getColorStatus()) {
//                    ArrayList<Integer> xp = new ArrayList<Integer>();
//                    ArrayList<Integer> yp = new ArrayList<Integer>();
//                    for (int k = 0; k < areas.get(i + 25).getSubAreas().get(j).getPoints().length; k++) {
//                        Point point = areas.get(i + 25).getSubAreas().get(j).getPoints()[k];
//                        xp.add(point.x);
//                        yp.add(point.y);
//
//                    }
//                    Integer[] sd = xp.toArray(new Integer[0]);
//                    int[] p1 = new int[sd.length];
//                    for (int k = 0; k < sd.length; k++) {
//                        p1[k] = sd[k];
//                    }
//                    Integer[] qd = yp.toArray(new Integer[0]);
//                    int[] p2 = new int[qd.length];
//                    for (int k = 0; k < qd.length; k++) {
//                        p2[k] = qd[k];
//                    }
//                    double pointsw[][] = {{p1[0], p2[0]}, {p1[1], p2[1]}, {p1[2], p2[2]}, {p1[0], p2[0]}};
//
//                    GeneralPath star = new GeneralPath();
//                    star.moveTo(pointsw[0][0], pointsw[0][1]);
//                    for (int k = 1; k < pointsw.length; k++) {
//                        star.lineTo(pointsw[k][0], pointsw[k][1]);
//                    }
//
//                    star.closePath();
//                    ig2.fill(star);
//
//                }
//            }
//            x += offsetCol;
//        }
//
//        try {
//            File outFile = new File(path);
//            if (ImageIO.write(bi, "jpg", outFile)) {
//                System.out.println("-- saved");
//
//                ConvertHXtoDC(outFile);
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
        return true;
    }

    private String HexaCodeTable(ArrayList<Integer> value) {
        String hex = "";
        for (int i = 0; i < value.size(); i++) {
            if (value.get(i).compareTo(64) < 0) {
                HxTable key = HxTable.forValue(value.get(i));
                hex = key.toString() + hex;
            } else {
                int re = value.get(i) * 64;
                HxTable key = HxTable.forValue(re);
                hex = key.toString() + hex;

            }
        }
        return hex;
    }

    private double DecimalTable(String value) {
        String[] hex = value.split("_");
        double total = 0;
        int j = 0;
        for (int i = hex.length - 1; i >= 1; i--) {
            HxTable asd = HxTable.valueOf("_" + hex[i]);
            int e = asd.getValue();
            total += (int) e * Math.pow(64, j);
            j++;

        }
        return total;
    }

    public Map<String, Area> Areas(int x0, int y0) {

//        Map<String, Area> area = new Hashtable<String, Area>();
        Map<String, Area> area = Collections.synchronizedMap(
                new LinkedHashMap<String, Area>());
        Area area1 = new Area("1A", new ArrayList<SubArea>());
        Area area2 = new Area("1B", new ArrayList<SubArea>(Arrays.asList(new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)))));
        Area area3 = new Area("1C", new ArrayList<SubArea>(Arrays.asList(new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)))));
        Area area4 = new Area("1D", new ArrayList<SubArea>(Arrays.asList(new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)))));
        Area area5 = new Area("1E", new ArrayList<SubArea>(Arrays.asList(new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)))));
        Area area6 = new Area("1F", new ArrayList<SubArea>(Arrays.asList(new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)))));
        Area area7 = new Area("1G", new ArrayList<SubArea>(Arrays.asList(new SubArea(6, true, 50, 30, GetPoints(6, x0, y0)))));
        Area area8 = new Area("1H", new ArrayList<SubArea>(Arrays.asList(new SubArea(1, true, 50, 30, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0))
        )));
        Area area9 = new Area("2A", new ArrayList<SubArea>(Arrays.asList(new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0))
        )));
        Area area10 = new Area("2B", new ArrayList<SubArea>(Arrays.asList(new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0))
        )));
        Area area11 = new Area("2C", new ArrayList<SubArea>(Arrays.asList(new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area12 = new Area("2D", new ArrayList<SubArea>(Arrays.asList(new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area13 = new Area("2E", new ArrayList<SubArea>(Arrays.asList(new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area14 = new Area("2F", new ArrayList<SubArea>(Arrays.asList(new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0))
        )));

        Area area15 = new Area("2G", new ArrayList<SubArea>(Arrays.asList(new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0))
        )));
        Area area16 = new Area("2H", new ArrayList<SubArea>(Arrays.asList(new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));

        Area area17 = new Area("3A", new ArrayList<SubArea>(Arrays.asList(new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area18 = new Area("3B", new ArrayList<SubArea>(Arrays.asList(new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area19 = new Area("3C", new ArrayList<SubArea>(Arrays.asList(new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area20 = new Area("3D", new ArrayList<SubArea>(Arrays.asList(new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0))
        )));
        Area area21 = new Area("3E", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area22 = new Area("3F", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area23 = new Area("3G", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area24 = new Area("3H", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area25 = new Area("4A", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area26 = new Area("4B", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));

        Area area27 = new Area("4C", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area28 = new Area("4D", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area29 = new Area("4E", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area30 = new Area("4F", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));

        Area area31 = new Area("4G", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area32 = new Area("4H", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area33 = new Area("5A", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0))
        )));
        Area area34 = new Area("5B", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area35 = new Area("5C", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area36 = new Area("5D", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area37 = new Area("5E", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area38 = new Area("5F", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area39 = new Area("5G", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area40 = new Area("5H", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area41 = new Area("6A", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0))
        )));
        Area area42 = new Area("6B", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area43 = new Area("6C", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area44 = new Area("6D", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area45 = new Area("6E", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area46 = new Area("6F", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area47 = new Area("6G", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area48 = new Area("6H", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area49 = new Area("7A", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area50 = new Area("7B", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area51 = new Area("7C", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area52 = new Area("7D", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area53 = new Area("7E", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0))
        )));
        Area area54 = new Area("7F", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0))
        )));
        Area area55 = new Area("7G", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area56 = new Area("7H", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area57 = new Area("8A", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area58 = new Area("8B", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area59 = new Area("8C", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0))
        )));
        Area area60 = new Area("8D", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area61 = new Area("8E", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area62 = new Area("8F", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0))
        )));
        Area area63 = new Area("8G", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(2, true, 50, 30, GetPoints(2, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(5, true, 50, 80, GetPoints(5, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));
        Area area64 = new Area("8H", new ArrayList<SubArea>(Arrays.asList(
                new SubArea(1, true, 0, 0, GetPoints(1, x0, y0)),
                new SubArea(3, true, 50, 80, GetPoints(3, x0, y0)),
                new SubArea(4, true, 0, 110, GetPoints(4, x0, y0)),
                new SubArea(6, true, 50, 30, GetPoints(6, x0, y0))
        )));

        area.put("1A", area1);
        area.put("1B", area2);
        area.put("1C", area3);
        area.put("1D", area4);
        area.put("1E", area5);
        area.put("1F", area6);
        area.put("1G", area7);
        area.put("1H", area8);
        area.put("2A", area9);
        area.put("2B", area10);
        area.put("2C", area11);
        area.put("2D", area12);
        area.put("2E", area13);
        area.put("2F", area14);
        area.put("2G", area15);
        area.put("2H", area16);
        area.put("3A", area17);
        area.put("3B", area18);
        area.put("3C", area19);
        area.put("3D", area20);
        area.put("3E", area21);
        area.put("3F", area22);
        area.put("3G", area23);
        area.put("3H", area24);
        area.put("4A", area25);
        area.put("4B", area26);
        area.put("4C", area27);
        area.put("4D", area28);
        area.put("4E", area29);
        area.put("4F", area30);
        area.put("4G", area31);
        area.put("4H", area32);
        area.put("5A", area33);
        area.put("5B", area34);
        area.put("5C", area35);
        area.put("5D", area36);
        area.put("5E", area37);
        area.put("5F", area38);
        area.put("5G", area39);
        area.put("5H", area40);
        area.put("6A", area41);
        area.put("6B", area42);
        area.put("6C", area43);
        area.put("6D", area44);
        area.put("6E", area45);
        area.put("6F", area46);
        area.put("6G", area47);
        area.put("6H", area48);
        area.put("7A", area49);
        area.put("7B", area50);
        area.put("7C", area51);
        area.put("7D", area52);
        area.put("7E", area53);
        area.put("7F", area54);
        area.put("7G", area55);
        area.put("7H", area56);
        area.put("8A", area57);
        area.put("8B", area58);
        area.put("8C", area59);
        area.put("8D", area60);
        area.put("8E", area61);
        area.put("8F", area62);
        area.put("8G", area63);
        area.put("8H", area64);

        return area;

    }

    private Point[] GetPoints(int subareaId, int x0, int y0) {
        int offset_X_First_Side = (25 * xBase / 100);
        int offset_X_Second_Side = (25 * xBase / 100);
        int offset_Y_FirstVal = (30 * yBase / 100);
        int offset_Y_SecondVal = (80 * yBase / 100);
        int offset_Y_ThirdVal = (110 * yBase / 100);
        int offset_Y_ForthVal = (55 * yBase / 100);

        Point point1 = new Point(x0, y0);
        Point point2 = new Point(x0 + offset_X_First_Side, y0 + offset_Y_FirstVal);
        Point point3 = new Point(x0 + offset_X_First_Side, y0 + offset_Y_SecondVal);
        Point point4 = new Point(x0, y0 + offset_Y_ThirdVal);
        Point point5 = new Point(x0 - offset_X_Second_Side, y0 + offset_Y_SecondVal);
        Point point6 = new Point(x0 - offset_X_Second_Side, y0 + offset_Y_FirstVal);
        Point point7 = new Point(x0, y0 + offset_Y_ForthVal);

        //Point point1 = new Point(x0, y0);
        //Point point2 = new Point(x0 + 50, y0 + 30);
        //Point point3 = new Point(x0 + 50, y0 + 80);
        //Point point4 = new Point(x0, y0 + 110);
        //Point point5 = new Point(x0 - 50, y0 + 80);
        //Point point6 = new Point(x0 - 50, y0 + 30);
        //Point point7 = new Point(x0, y0 + 55);
        switch (subareaId) {
            case 1:
                Point[] Points1 = {point1, point2, point7, point1};
                return Points1;
            case 2:
                Point[] Points2 = {point2, point3, point7, point2};
                return Points2;
            case 3:
                Point[] Points3 = {point3, point4, point7, point3};
                return Points3;
            case 4:
                Point[] Points4 = {point4, point5, point7, point4};
                return Points4;
            case 5:
                Point[] Points5 = {point5, point6, point7, point5};
                return Points5;
            case 6:
                Point[] Points6 = {point6, point1, point7, point6};
                return Points6;
            default:
                Point[] Points7 = {};
                return Points7;
        }

    }

    private Area GetPointsEdditing(Area area, int num) {

        int x = (width * 23) / 100;
        int y = (height * 17) / 100;
        int offsetCol = 50 * x / 100;

        for (int i = 0; i < area.getSubAreas().size(); i++) {
            Point[] ww = area.getSubAreas().get(i).getPoints();
            for (int j = 0; j < area.getSubAreas().get(i).getPoints().length - 1; j++) {
                ww[j].x = (num * offsetCol) + ww[j].x;
            }
            area.getSubAreas().get(i).setPoints(ww);

        }

        return area;

    }

    private ArrayList<Integer> ValidateCodeDigit(ArrayList<Integer> value, CodeCountDigite codePartsName) {
        int countBase;
        countBase = value.size();
        switch (codePartsName) {
            case Standardization: {
                for (int i = 0; i < 3 - countBase; i++) {
                    value.add(0);
                }
            }
            break;
            case Sector: {
                for (int i = 0; i < 3 - countBase; i++) {
                    value.add(0);
                }
            }
            break;
            case Entity: {
                for (int i = 0; i < 5 - countBase; i++) {
                    value.add(0);
                }
            }
            break;
            case TimeStump: {
                for (int i = 0; i < 6 - countBase; i++) {
                    value.add(0);
                }
            }
            break;
            case Section: {
                for (int i = 0; i < 2 - countBase; i++) {
                    value.add(0);
                }
            }
            break;
            case Item: {
                for (int i = 0; i < 3 - countBase; i++) {
                    value.add(0);
                }
            }
            break;
            case Quality: {
                for (int i = 0; i < 1 - countBase; i++) {
                    value.add(0);
                }
            }
            break;
            case Unit: {
                for (int i = 0; i < 2 - countBase; i++) {
                    value.add(0);
                }
            }
            break;
            case Validty: {
                for (int i = 0; i < 3 - countBase; i++) {
                    value.add(0);
                }
            }
            break;
            default:
                break;
        }
        return value;
    }

    private boolean BlackWhiteChecker(int pixelVal) {

        boolean Black = false;
        boolean White = false;

        int a = (pixelVal >> 24) & 0xff;

        //get red
        int r = (pixelVal >> 16) & 0xff;

        //get green
        int g = (pixelVal >> 8) & 0xff;

        //get blue
        int b = pixelVal & 0xff;
        int grayScale = (int) ((r * 0.3) + (g * 0.59) + (b * 0.11));
        if (grayScale < 20) {
            Black = true;
            return Black;

        } else {
            White = true;
            return false;
        }

    }

}
