package com.example.testliveedgedetection.hexacode;

public class kParams {
    private int width;
    private int height;
    private int x = (width * 23) / 100;
    private int y = (height * 17) / 100;
    private int offsetCol = 50 * x / 100;
    private int offset_X_First_Side = (25 * x / 100);
    private int offset_X_Second_Side = (25 * x / 100);
    private int offset_Y_FirstVal = (30 * y / 100);
    private int offset_Y_SecondVal = (80 * y / 100);
    private int offset_Y_ThirdVal = (110 * y / 100);
    private int offset_Y_ForthVal = (55 * y / 100);

    private int offset_X0_Row2 = x - (25 * x / 100);
    private int offset_Y0_Row2 = y + (80 * y / 100);
    private int offset_X0_Row3 = x;
    private int offset_Y0_Row3 = offset_Y0_Row2 + (80 * y / 100);
    private int offset_X0_Row4 = x + (25 * x / 100);
    private int offset_Y0_Row4 = offset_Y0_Row3 + (80 * y / 100);
    private int offset_X0_Row5 = x;
    private int offset_Y0_Row5 = offset_Y0_Row4 + (80 * y / 100);

    public kParams(){}
    public kParams(int width, int height, int x, int y, int offsetCol, int offset_X_First_Side, int offset_X_Second_Side, int offset_Y_FirstVal, int offset_Y_SecondVal, int offset_Y_ThirdVal, int offset_Y_ForthVal, int offset_X0_Row2, int offset_Y0_Row2, int offset_X0_Row3, int offset_Y0_Row3, int offset_X0_Row4, int offset_Y0_Row4, int offset_X0_Row5, int offset_Y0_Row5) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.offsetCol = offsetCol;
        this.offset_X_First_Side = offset_X_First_Side;
        this.offset_X_Second_Side = offset_X_Second_Side;
        this.offset_Y_FirstVal = offset_Y_FirstVal;
        this.offset_Y_SecondVal = offset_Y_SecondVal;
        this.offset_Y_ThirdVal = offset_Y_ThirdVal;
        this.offset_Y_ForthVal = offset_Y_ForthVal;
        this.offset_X0_Row2 = offset_X0_Row2;
        this.offset_Y0_Row2 = offset_Y0_Row2;
        this.offset_X0_Row3 = offset_X0_Row3;
        this.offset_Y0_Row3 = offset_Y0_Row3;
        this.offset_X0_Row4 = offset_X0_Row4;
        this.offset_Y0_Row4 = offset_Y0_Row4;
        this.offset_X0_Row5 = offset_X0_Row5;
        this.offset_Y0_Row5 = offset_Y0_Row5;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOffsetCol() {
        return offsetCol;
    }

    public void setOffsetCol(int offsetCol) {
        this.offsetCol = offsetCol;
    }

    public int getOffset_X_First_Side() {
        return offset_X_First_Side;
    }

    public void setOffset_X_First_Side(int offset_X_First_Side) {
        this.offset_X_First_Side = offset_X_First_Side;
    }

    public int getOffset_X_Second_Side() {
        return offset_X_Second_Side;
    }

    public void setOffset_X_Second_Side(int offset_X_Second_Side) {
        this.offset_X_Second_Side = offset_X_Second_Side;
    }

    public int getOffset_Y_FirstVal() {
        return offset_Y_FirstVal;
    }

    public void setOffset_Y_FirstVal(int offset_Y_FirstVal) {
        this.offset_Y_FirstVal = offset_Y_FirstVal;
    }

    public int getOffset_Y_SecondVal() {
        return offset_Y_SecondVal;
    }

    public void setOffset_Y_SecondVal(int offset_Y_SecondVal) {
        this.offset_Y_SecondVal = offset_Y_SecondVal;
    }

    public int getOffset_Y_ThirdVal() {
        return offset_Y_ThirdVal;
    }

    public void setOffset_Y_ThirdVal(int offset_Y_ThirdVal) {
        this.offset_Y_ThirdVal = offset_Y_ThirdVal;
    }

    public int getOffset_Y_ForthVal() {
        return offset_Y_ForthVal;
    }

    public void setOffset_Y_ForthVal(int offset_Y_ForthVal) {
        this.offset_Y_ForthVal = offset_Y_ForthVal;
    }

    public int getOffset_X0_Row2() {
        return offset_X0_Row2;
    }

    public void setOffset_X0_Row2(int offset_X0_Row2) {
        this.offset_X0_Row2 = offset_X0_Row2;
    }

    public int getOffset_Y0_Row2() {
        return offset_Y0_Row2;
    }

    public void setOffset_Y0_Row2(int offset_Y0_Row2) {
        this.offset_Y0_Row2 = offset_Y0_Row2;
    }

    public int getOffset_X0_Row3() {
        return offset_X0_Row3;
    }

    public void setOffset_X0_Row3(int offset_X0_Row3) {
        this.offset_X0_Row3 = offset_X0_Row3;
    }

    public int getOffset_Y0_Row3() {
        return offset_Y0_Row3;
    }

    public void setOffset_Y0_Row3(int offset_Y0_Row3) {
        this.offset_Y0_Row3 = offset_Y0_Row3;
    }

    public int getOffset_X0_Row4() {
        return offset_X0_Row4;
    }

    public void setOffset_X0_Row4(int offset_X0_Row4) {
        this.offset_X0_Row4 = offset_X0_Row4;
    }

    public int getOffset_Y0_Row4() {
        return offset_Y0_Row4;
    }

    public void setOffset_Y0_Row4(int offset_Y0_Row4) {
        this.offset_Y0_Row4 = offset_Y0_Row4;
    }

    public int getOffset_X0_Row5() {
        return offset_X0_Row5;
    }

    public void setOffset_X0_Row5(int offset_X0_Row5) {
        this.offset_X0_Row5 = offset_X0_Row5;
    }

    public int getOffset_Y0_Row5() {
        return offset_Y0_Row5;
    }

    public void setOffset_Y0_Row5(int offset_Y0_Row5) {
        this.offset_Y0_Row5 = offset_Y0_Row5;
    }


}
