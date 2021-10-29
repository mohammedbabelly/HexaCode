/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testliveedgedetection.hexacode;

import java.util.ArrayList;

/**
 *
 * @author Besher Rehawee
 */

public class Area
{
	  public int Id;
          public String Name;
           public Area()
	   {
		   Name ="";
		   SubAreas = new  ArrayList<>();
	   }

	   public Area(String name, ArrayList<SubArea> subAreas)
	   {
		   Name = name;
		   SubAreas = subAreas;
	   }
           
           private ArrayList<SubArea> SubAreas;
		public final ArrayList<SubArea> getSubAreas()
		{
			return SubAreas;
		}
		public final void setSubAreas(ArrayList<SubArea> value)
		{
			SubAreas = value;
		}


}

