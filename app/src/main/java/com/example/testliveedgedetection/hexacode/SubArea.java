/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testliveedgedetection.hexacode;


import android.graphics.Point;

/**
 *
 * @author Besher Rehawee
 */
 public class SubArea
 {

    /**
     *
     * @param id
     * @param colorStatus
     * @param offsetX
     * @param offsetY
     * @param points
     */
    public SubArea(int id , boolean  colorStatus, int offsetX, int offsetY, Point[] points){
     Id=id;
     ColorStatus=colorStatus;
     OffsetX=offsetX;
     OffsetY=offsetY;
     Points=points;
     }
     
		private int Id;
		public final int getId()
		{
			return Id;
		}
		public final void setId(int value)
		{
			Id = value;
		}
		private boolean ColorStatus;
		public final boolean getColorStatus()
		{
			return ColorStatus;
		}
		public final void setColorStatus(boolean value)
		{
			ColorStatus = value;
		}
		private int OffsetX;
		public final int getOffsetX()
		{
			return OffsetX;
		}
		public final void setOffsetX(int value)
		{
			OffsetX = value;
		}
		private int OffsetY;
		public final int getOffsetY()
		{
			return OffsetY;
		}
		public final void setOffsetY(int value)
		{
			OffsetY = value;
		}
		private Point[] Points;
		public final Point[] getPoints()
		{
			return Points;
		}
		public final void setPoints(Point[] value)
		{
			Points = value;
		}
 }

