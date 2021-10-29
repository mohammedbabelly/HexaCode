/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testliveedgedetection.hexacode;

import java.util.HashMap;

/**
 *
 * @author Besher Rehawee
 */
 public enum HxTable
 {
		_1A(0,"_1A"),
		_1B(1,"_1B"),
		_1C(2,"_1C"),
		_1D(3,"_1D"),
		_1E(4,"_1E"),
		_1F(5,"_1F"),
		_1G(6,"_1G"),
		_1H(7,"_1H"),
		_2A(8,"_2A"),
		_2B(9,"_2B"),
		_2C(10,"_2C"),
		_2D(11,"_2D"),
		_2E(12,"_2E"),
		_2F(13,"_2F"),
		_2G(14,"_2G"),
		_2H(15,"_2H"),
		_3A(16,"_3A"),
		_3B(17,"_3B"),
		_3C(18,"_3C"),
		_3D(19,"_3D"),
		_3E(20,"_3E"),
		_3F(21,"_3F"),
		_3G(22,"_3G"),
		_3H(23,"_3H"),
		_4A(24,"_4A"),
		_4B(25,"_4B"),
		_4C(26,"_4C"),
		_4D(27,"_4D"),
		_4E(28,"_4E"),
		_4F(29,"_4F"),
		_4G(30,"_4G"),
		_4H(31,"_4H"),
		_5A(32,"_5A"),
		_5B(33,"_5B"),
		_5C(34,"_5C"),
		_5D(35,"_5D"),
		_5E(36,"_5E"),
		_5F(37,"_5F"),
		_5G(38,"_5G"),
		_5H(39,"_5H"),
		_6A(40,"_6A"),
                _6B(41,"_6B"),
		_6C(42,"_6C"),
		_6D(43,"_6D"),
		_6E(44,"_6E"),
		_6F(45,"_6F"),
		_6G(46,"_6G"),
		_6H(47,"_6H"),
		_7A(48,"_7A"),
		_7B(49,"_7B"),
		_7C(50,"_7C"),
		_7D(51,"_7D"),
		_7E(52,"_7E"),
		_7F(53,"_7F"),
		_7G(54,"_7G"),
		_7H(55,"_7H"),
		_8A(56,"_8A"),
		_8B(57,"_8B"),
		_8C(58,"_8C"),
		_8D(59,"_8D"),
		_8E(60,"_8E"),
		_8F(61,"_8F"),
		_8G(62,"_8G"),
		_8H(63,"_8H");


		public static final int SIZE = Integer.SIZE;

		private int intValue;
                private String kye;
		private static HashMap<Integer, HxTable> mappings;
                private static HashMap<String, HxTable> mappingsKey;

                
		private static HashMap<Integer, HxTable> getMappings()
		{
			if (mappings == null)
			{
				synchronized (HxTable.class)
				{
					if (mappings == null)
					{
						mappings = new HashMap<Integer, HxTable>();
					}
				}
			}
                        
			return mappings;
		}
                private static HashMap<String, HxTable> getmappingsKeys()
		{
			if (mappingsKey == null)
			{
				synchronized (HxTable.class)
				{
					if (mappingsKey == null)
					{
						mappingsKey = new HashMap<String, HxTable>();
					}
				}
			}
                        
			return mappingsKey;
		}

		private HxTable(int value,String key)
		{
			intValue = value;
			getMappings().put(value, this);
		}

		public int getValue()
		{
			return intValue;
		}
                public String getkey()
		{
			 return kye;
		}

		public static HxTable forValue(int value)
		{
			return getMappings().get(value);
		}
 }

