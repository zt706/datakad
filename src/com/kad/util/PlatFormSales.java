package com.kad.util;

import java.io.Serializable;

public class PlatFormSales  implements Serializable
{
	/**
	 * 
	 */
String dateStr;
	
	int hour;
	
	String originName;
	
	int orderNum4Status;
	
	float sales4Status;
	
	int orderNum6Status;
	
	float sales6Status;
	
	public PlatFormSales() {
	}
	
	public PlatFormSales(String dateStr, int hour, String originName, int orderNum4Status, float sales4Status, int orderNum6Status, float sales6Status) 
	{
		this.dateStr = dateStr;
		
		this.hour = hour;
		
		this.originName = originName;
		
		this.orderNum4Status = orderNum4Status;
		
		this.sales4Status = sales4Status;
		
		this.orderNum6Status = orderNum6Status;
		
		this.sales6Status = sales6Status;
	}
	
	public String getDateStr()
	{
		return dateStr;
	}
	
	public int getHour()
	{
		return hour;
	}
	
	public String getOriginName()
	{
		return originName;
	}
	
	public int getOrderNum4Status()
	{
		return orderNum4Status;
	}
	
	public float getSales4Status()
	{
		return sales4Status;
	}
	
	public int getOrderNum6Status()
	{
		return orderNum6Status;
	}
	
	public float getSales6Status()
	{
		return sales6Status;
	}
	
	public void setDateStr(String dateStr)
	{
		this.dateStr = dateStr;
	}
	
	public void setHour(int hour)
	{
		this.hour = hour;
	}
	
	public void setOriginName(String originName)
	{
		this.originName = originName;
	}
	
	public void setOrderNum4Status(int orderNum4Status)
	{
		this.orderNum4Status = orderNum4Status;
	}
	
	public void setSales4Status(float sales4Status)
	{
		this.sales4Status = sales4Status;
	}
	
	public void setOrderNum6Status(int orderNum6Status)
	{
		this.orderNum6Status = orderNum6Status;
	}
	
	public void setSales6Status(float sales6Status)
	{
		this.sales6Status = sales6Status;
	}
	
	public String toString()
	{
		return dateStr + "\t" + hour + "\t" + originName + "\t" + orderNum4Status + "\t" + sales4Status + "\t" + orderNum6Status + "\t" + sales6Status;
	}
}
