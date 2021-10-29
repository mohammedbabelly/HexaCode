/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testliveedgedetection.hexacode;

/**
 *
 * @author Besher Rehawee
 */
 public class ConvertingDto
 {
     public ConvertingDto(){
     
     HXValue="";
     DecValue=0;
     }
		private String HXValue;
		public final String getHXValue()
		{
			return HXValue;
		}
		public final void setHXValue(String value)
		{
			HXValue = value;
		}
		private int DecValue;
		public final int getDecValue()
		{
			return DecValue;
		}
		public final void setDecValue(int value)
		{
			DecValue = value;
		}


 }
