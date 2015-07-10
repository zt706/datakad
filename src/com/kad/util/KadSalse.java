package com.kad.util;

import java.io.Serializable;
import java.util.List;

public class KadSalse  implements Serializable
{
	/**
	 * 
	 */

	private double todayTotal_4;
	private double todayTotal_6;
	
	private double yestodaySameTime_4;
	private double yestodaySameTime_6;
	
	private double yestodayTotal_4;
	private double yestodayTotal_6;
	
	private double [] todayEveryHour_4;
	private double [] todayEveryHour_6;
	
	private double [] yestodayEveryHour_4;
	private double [] yestodayEveryHour_6;
	
	public KadSalse() 
	{
		this.todayTotal_4 = 0.0f;
		this.todayTotal_6 = 0.0f;
		
		this.yestodaySameTime_4 = 0.0f;
		this.yestodaySameTime_6 = 0.0f;
		
		this.yestodayTotal_4 = 0.0f;
		this.yestodayTotal_6 = 0.0f;
		
		this.todayEveryHour_4 = new double[24];
		this.todayEveryHour_6 = new double[24];
		
		this.yestodayEveryHour_4 = new double[24];
		this.yestodayEveryHour_6 = new double[24];
		
		for(int i = 0; i < 24; i++)
		{
			this.todayEveryHour_4[i] =  (double) 0.0;
			this.todayEveryHour_6[i] =  (double) 0.0;
			
			this.yestodayEveryHour_4[i] =  (double) 0.0;
			this.yestodayEveryHour_6[i] =  (double) 0.0;
		}
	}
	

	//
	public double gettodayTotal_4()
	{
		return todayTotal_4;
	}
	public double gettodayTotal_6()
	{
		return todayTotal_6;
	}
	
	//
	public double getyestodaySameTime_4()
	{
		return yestodaySameTime_4;
	}	
	public double getyestodaySameTime_6()
	{
		return yestodaySameTime_6;
	}
	
	//
	public double getyestodayTotal_4()
	{
		return yestodayTotal_4;
	}
	public double getyestodayTotal_6()
	{
		return yestodayTotal_6;
	}
	
	//
	public double [] gettodayEveryHour_4()
	{
		return todayEveryHour_4;
	}
	public double [] gettodayEveryHour_6()
	{
		return todayEveryHour_6;
	}
	
	//
	public double [] getyestodayEveryHour_4()
	{
		return yestodayEveryHour_4;
	}
	public double [] getyestodayEveryHour_6()
	{
		return yestodayEveryHour_6;
	}
	
	///////////////// get end ///////////////////////
	
	
	//////////// set 与 add 成对
	public void settodayTotal_4(double today_total_4)
	{
		this.todayTotal_4 = today_total_4;
	}
	public void addtodayTotal_4(double today_total_4)
	{
		//  累加 到今日累计
		this.todayTotal_4 += today_total_4;
	}
	
	/////////// 
	public void settodayTotal_6(double today_total_6)
	{
		this.todayTotal_6 = today_total_6;
	}
	public void addtodayTotal_6(double today_total_6)
	{
		this.todayTotal_6 += today_total_6;
	}
	
	/////////// 
	public void setyestodaySameTime_4(double yestoday_same_time_4)
	{
		this.yestodaySameTime_4 = yestoday_same_time_4;
	}
	public void addyestodaySameTime_4(double yestoday_same_time_4)
	{
		this.yestodaySameTime_4 += yestoday_same_time_4;
	}
	
	////////// 
	public void setyestodaySameTime_6(double yestoday_same_time_6)
	{
		this.yestodaySameTime_6 = yestoday_same_time_6;
	}
	public void addyestodaySameTime_6(double yestoday_same_time_6)
	{
		this.yestodaySameTime_6 += yestoday_same_time_6;
	}
	
	//////////
	public void setyestodayTotal_4(double yestoday_total_4)
	{
		this.yestodayTotal_4 = yestoday_total_4;
	}
	public void addyestodayTotal_4(double yestoday_total_4)
	{
		this.yestodayTotal_4 += yestoday_total_4;
	}
	
	//////////
	public void setyestodayTotal_6(double yestoday_total_6)
	{
		this.yestodayTotal_6 = yestoday_total_6;
	}
	public void addyestodayTotal_6(double yestoday_total_6)
	{
		this.yestodayTotal_6 += yestoday_total_6;
	}
	
	//////////
	public void settodayEveryHour_4(double [] today_every_hour_arr_4)
	{
		this.todayEveryHour_4 = today_every_hour_arr_4;
	}
	public void addTodayEveryHour_4_values(int hour_idx, double value)
	{
		this.todayEveryHour_4[hour_idx] += value;
	}
	
	///////////
	public void settodayEveryHour_6(double [] today_every_hour_arr_6)
	{
		this.todayEveryHour_6 = today_every_hour_arr_6;
	}
	public void addTodayEveryHour_6_values(int hour_idx, double value)
	{
		this.todayEveryHour_6[hour_idx] += value;
	}
	
	/////////// 
	public void setyestodayEveryHour_4(double [] yestoday_every_hour_arr_4)
	{
		this.yestodayEveryHour_4 = yestoday_every_hour_arr_4;
	}
	public void addYesTodayEveryHour_4_values(int hour_idx, double value)
	{
		this.yestodayEveryHour_4[hour_idx] += value;
	}

	////////
	public void setyestodayEveryHour_6(double [] yestoday_every_hour_arr_6)
	{
		this.yestodayEveryHour_6 = yestoday_every_hour_arr_6;
	}
	public void addYesTodayEveryHour_6_values(int hour_idx, double value)
	{
		this.yestodayEveryHour_6[hour_idx] += value;
	}
	
	
	//////////////////////// set end /////////////////////
	
	public KadSalse(
					double todayTotal_4,
					double todayTotal_6,
					
					double yestodaySameTime_4,
					double yestodaySameTime_6,
					
					double yestodayTotal_4,
					double yestodayTotal_6,
					
					double [] todayEveryHour_4,
					double [] todayEveryHour_6,
					
					double [] yestodayEveryHour_4,
					double [] yestodayEveryHour_6
					) 
	{
		 this.todayTotal_4 = todayTotal_4;
		 this.todayTotal_6 = todayTotal_6;
		 
		 this.yestodaySameTime_4 = yestodaySameTime_4;
		 this.yestodaySameTime_6 = yestodaySameTime_6;
		 
		 this.yestodayTotal_4 = yestodayTotal_4;
		 this.yestodayTotal_6 = yestodayTotal_6;
		 
		 this.todayEveryHour_4 = todayEveryHour_4;
		 this.todayEveryHour_6 = todayEveryHour_6;
		 
		 this.yestodayEveryHour_4 = yestodayEveryHour_4;
		 this.yestodayEveryHour_6 = yestodayEveryHour_6;
	}
}
