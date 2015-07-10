package com.kad.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.hadoop.hbase.generated.regionserver.regionserver_jsp;

/*
 *  从 10.0.22.106:1522 读出数据
 *  写入 10.0.22.104:11311 cache 服务器
 */

public class ImportKadSalesIntoCache 
{
	private static final String [] platform_name = {
			"quansi",
			"guanwang",
			"wap",
			"android",
			"tianmao",
			"jingdong",
			"ios",
			"zijian",
			"weishangcheng",
	};
	
	private static final String queryKadSalseSql_4 = 	
			  " select "
			+ " case when to_char(a.CreateDate,'yyyy-mm-dd') = to_char(sysdate - 1, 'yyyy-mm-dd') then 1 else 2 end as dayint, "
			+ " to_char(a.CreateDate,'hh24') as hourstr, "
			+ " a.OrderSource , " 
			+ " a.orderstatus ,"
			+ " sum(a.Netamt) sales "
			+ " from OM.OM_Order a "
			+ " where " 
            + " to_char(a.CreateDate, 'yyyy-mm-dd') >= to_char(sysdate - 1, 'yyyy-mm-dd') "
            + " and a.orderstatus not in (0,10,12) "
            + " group by case when to_char(a.CreateDate,'yyyy-mm-dd') = to_char(sysdate - 1, 'yyyy-mm-dd') then 1 else 2 end, "
            + " to_char(a.CreateDate,'hh24'), "
            + " a.OrderSource, a.orderstatus "
            + " order by case when to_char(a.CreateDate,'yyyy-mm-dd') = to_char(sysdate - 1, 'yyyy-mm-dd') then 1 else 2 end, "
            + " to_char(a.CreateDate,'hh24'), "
            + " a.OrderSource, a.orderstatus ";
	
	private void getKadSalse(Connection connection) throws SQLException
	{
		// HashMap<String, String> kadSalseMap = new HashMap<String, String>();
		
		PreparedStatement pState = connection.prepareStatement(queryKadSalseSql_4);
		ResultSet rs = pState.executeQuery();
		
		String info_str = "";
		
		KadSalse quansiKadSale = new KadSalse();
		KadSalse tianmaoKadSale = new KadSalse();
		KadSalse guanwangKadSale = new KadSalse();
		
		KadSalse wapKadSale = new KadSalse();
		KadSalse androidKadSale = new KadSalse();
		KadSalse iosKadSalse = new KadSalse();
		
		KadSalse jingdongKadSale = new KadSalse();
		KadSalse zijianKadSale = new KadSalse();
		
		KadSalse weishangKadSale = new KadSalse();
		
		while (rs.next()) 
		{
			int dayint = rs.getInt("dayint");
			
			String houtString = rs.getString("hourstr");
			
			int hour = Integer.parseInt(houtString);
			
			int orderstatus = rs.getInt("orderstatus");
			int ordersource = rs.getInt("ordersource");
			
			double ordersales = rs.getDouble("sales");				// 数据库中是 number(18, 4) 类型,不能用getFloat, 否则从json对象转为java对象时报错
			
			// 全司 
			setKadSalseObj(quansiKadSale, dayint, hour, orderstatus, ordersource, ordersales);
			
			// 官网
			if(ordersource == 1 || ordersource == 12)
			{
				setKadSalseObj(guanwangKadSale, dayint, hour, orderstatus, ordersource, ordersales);
			}
			
			// wap
			if (ordersource == 13)
			{
				setKadSalseObj(wapKadSale, dayint, hour, orderstatus, ordersource, ordersales);
			}
			
			// android
			if (ordersource == 14)
			{
				setKadSalseObj(androidKadSale, dayint, hour, orderstatus, ordersource, ordersales);
			}
			
			// 天猫
			if (ordersource == 4)
			{
				setKadSalseObj(tianmaoKadSale, dayint, hour, orderstatus, ordersource, ordersales);
			}
			
			// 京东
			if (ordersource == 16)
			{
				setKadSalseObj(jingdongKadSale, dayint, hour, orderstatus, ordersource, ordersales);
			}
			
			// ios
			if (ordersource == 15)
			{
				setKadSalseObj(iosKadSalse, dayint, hour, orderstatus, ordersource, ordersales);
			}
			
			// 自建
			if (ordersource == 1 || ordersource == 2 || ordersource == 12
				|| ordersource == 13 || ordersource == 14 || ordersource == 15)
			{
				setKadSalseObj(zijianKadSale, dayint, hour, orderstatus, ordersource, ordersales);
			}
			
			// 微商城
			if (ordersource == 21)
			{
				setKadSalseObj(weishangKadSale, dayint, hour, orderstatus, ordersource, ordersales);
			}
		}
		
		// 
		System.out.println(" >>> " );
		
		// 将对象写入缓存
		ImportSalseIntoCache(quansiKadSale, "quansi");
		ImportSalseIntoCache(guanwangKadSale, "guanwang");
		ImportSalseIntoCache(wapKadSale, "wap");
		
		ImportSalseIntoCache(androidKadSale, "android");
		ImportSalseIntoCache(tianmaoKadSale, "tianmao");		
		ImportSalseIntoCache(jingdongKadSale, "jingdong");
		
		ImportSalseIntoCache(iosKadSalse, "ios");
		ImportSalseIntoCache(zijianKadSale, "zijian");
		
		ImportSalseIntoCache(weishangKadSale, "weishangcheng");
		
		pState.close();
		connection.close();
		
	}
	
	// 将数据塞入对应的对象
	public void setKadSalseObj(KadSalse kadSalseObj, int dayint, int hour, int orderstatus, int ordersource, double ordersales)
	{
		int now_time = getNowHour();
		
		// 昨天
		if(dayint == 1)
		{
			// 4种状态 
			if( orderstatus == 1 ||
				orderstatus == 3 ||
				orderstatus == 4 ||
				orderstatus == 6 ||
				orderstatus == 7 ||
				orderstatus == 8 ||
				orderstatus == 9 ||
				orderstatus == 11
			 )
			{
				// 昨日累计
				kadSalseObj.addyestodayTotal_4(ordersales);
				
				if (hour <= now_time)
				{
					// 昨日同期累计
					kadSalseObj.addyestodaySameTime_4(ordersales);
				}
				
				// 昨日全天的数组
				kadSalseObj.addYesTodayEveryHour_4_values(hour, ordersales);
			}
			
			// 6 种状态
			if(
					orderstatus == 0 ||
					orderstatus == 1 ||
					orderstatus == 3 ||
					orderstatus == 4 ||
					orderstatus == 5 ||
					orderstatus == 6 ||
					orderstatus == 7 ||
					orderstatus == 8 ||
					orderstatus == 9 ||
					orderstatus == 11
					)
			{
				kadSalseObj.addyestodayTotal_6(ordersales);
				
				if (hour <= now_time)
				{
					kadSalseObj.addyestodaySameTime_6(ordersales);
				}
				
				kadSalseObj.addYesTodayEveryHour_6_values(hour, ordersales);
			}
		}
		else if(dayint == 2)
		{
			// 今天
			// 4种状态 
			if( orderstatus == 1 ||
				orderstatus == 3 ||
				orderstatus == 4 ||
				orderstatus == 6 ||
				orderstatus == 7 ||
				orderstatus == 8 ||
				orderstatus == 9 ||
				orderstatus == 11
			 )
			{
				kadSalseObj.addtodayTotal_4(ordersales);
				
				kadSalseObj.addTodayEveryHour_4_values(hour, ordersales);
			}
			
			// 6 种状态
			if(
				orderstatus == 0 ||
				orderstatus == 1 ||
				orderstatus == 3 ||
				orderstatus == 4 ||
				orderstatus == 5 ||
				orderstatus == 6 ||
				orderstatus == 7 ||
				orderstatus == 8 ||
				orderstatus == 9 ||
				orderstatus == 11
			)
			{
				kadSalseObj.addtodayTotal_6(ordersales);
				
				kadSalseObj.addTodayEveryHour_6_values(hour, ordersales);
			}
		}
	}
	
	// sourid  映射平台名字
	public static String sourceId2PlatformName(float order_source)
	{
		String platform = "";

		if (order_source == 4)
		{ 
			// tianmao
			platform = "tianmao";
			
		}
		else if(order_source == 1 || order_source == 12)
		{
			platform = "guanwang";
		}
		else if (order_source == 13)
		{
			platform = "wap";
		}
		else if (order_source == 14)
		{
			platform = "android";
		}
		else if (order_source == 16)
		{
			platform = "jingdong";
		}
		else if (order_source == 15)
		{
			platform = "ios";
		}
		else if (order_source == 1 || order_source == 2 || order_source == 12
				|| order_source == 13 || order_source == 14 || order_source == 15)
		{
			platform = "zijian";
		}
		else if (order_source == 21)
		{
			platform = "weishangcheng";
		}
		
		return platform;
	}
	
	// 获取当前时间点
	// cache key 中的时间点和当前时间点一定会同步
	public static int getNowHour()
	{
		String key = getCacheKey("");
		int hour = Integer.parseInt(key.split("-")[3].split("_")[0]);
		
		return hour;
	}
	
	// 生成cache key 
	public static String getCacheKey(String platform)
	{
		int y,m,d,h;    
		Calendar cal=Calendar.getInstance();    
		y=cal.get(Calendar.YEAR);    
		m=cal.get(Calendar.MONTH) + 1;    
		d=cal.get(Calendar.DATE);    
		h=cal.get(Calendar.HOUR_OF_DAY);
		
		String key = "";
		key = y + "-" + m + "-" + d + "-" + h + "_" + platform;
		
		return key;
	}
		
	// 需要删除的cache key
	// 就是上一个小时的 cache key
	public static String getCacheKeyToDel(String platform)
	{
		int y,m,d,h;    
		Calendar cal=Calendar.getInstance();    
		y=cal.get(Calendar.YEAR);    
		m=cal.get(Calendar.MONTH) - 1;    
		d=cal.get(Calendar.DATE);    
		h=cal.get(Calendar.HOUR_OF_DAY) - 1;
		
		String key_del = "";
		key_del = y + "-" + m + "-" + d + "-" + h + "_" + platform;
		
		return key_del;
	}
	
	
	private Connection getBIOracle106Conn() throws SQLException
	{
		String db_106_conn_str = "jdbc:oracle:thin:@10.0.22.106:1522:kaddw02";
		// String dbName = "kaddw02";
		
		String db_106_user_name = "bidev";
		String db_106_password = "bi2015dev";
		
		Connection connection = null;
		try
		{
			  if(connection == null || connection.isClosed())
			  {
				   // Class.forName("oracle.jdbc.driver.OracleDriver");
				   connection= DriverManager.getConnection(db_106_conn_str, db_106_user_name, db_106_password); 
			  }
		}
		catch (SQLException e) 
		{
		   e.printStackTrace();
		}
		  
		return connection;
	}
	
	// 设置从当前开始计算的过期时间
	private static final int EXPIRE_TIME = 3 * 3600 * 1000; 
	
	// 将 KadSale 对象存入缓存
	public void ImportSalseIntoCache(KadSalse kadObj, String platform) throws SQLException
	{
		String cache_key_del = getCacheKeyToDel(platform);
		
		String cache_key = getCacheKey(platform);
		
		JSONObject jsonObject = JSONObject.fromObject(kadObj);
		Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		
		System.out.println("缓存key == " + cache_key);
		System.out.println("huan cun value == " + jsonObject.toString());
		
		if(MemcachedConnector.mcc.get(cache_key) != null)
		{
			// 更新缓存服务器中的内容
			MemcachedConnector.mcc.replace(cache_key, jsonObject.toString(), expireDate);
		}
		else
		{
			// 添加新内容到缓存服务器中
			MemcachedConnector.mcc.add(cache_key, jsonObject.toString(), expireDate);
			
			//  删除上一批缓存
			// MemcachedConnector.mcc.delete(cache_key_del);
		}
	}
	
/*
	public void ImportFilterIdsIntoCache() throws SQLException
	{
		for (int i = 0; i < platform_name.length; i++)
		{
			String platform = platform_name[i];
			String cache_key = getCacheKey(platform);
			
			
			float todayTotal_4 = 0;
			float todayTotal_6 = 0;
			
			float yestodaySameTime_4 = 0;
			float yestodaySameTime_6 = 0;
			
			float yestodayTotal_4 = 0;
			float yestodayTotal_6 = 0;
			
			float [] todayEveryHour_4 = {0};
			float [] todayEveryHour_6 = {0};
			
			float [] yestodayEveryHour_4 = {0};
			float [] yestodayEveryHour_6 = {0};
			
			KadSalse kadObj = new KadSalse( todayTotal_4,
											todayTotal_6,
					
											yestodaySameTime_4,
											yestodaySameTime_6,
											
											yestodayTotal_4,
											yestodayTotal_6,
											
											todayEveryHour_4,
											todayEveryHour_6,
											
											yestodayEveryHour_4,
											yestodayEveryHour_6 
										  );
			
			JSONObject jsonObject = JSONObject.fromObject(kadObj); 
			Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			
			System.out.println("缓存key == " + cache_key);
			
			if(MemcachedConnector.mcc.get(cache_key) != null)
			{
				// 更新缓存服务器中的内容
				MemcachedConnector.mcc.replace(cache_key, jsonObject.toString(), expireDate);
			}
			else
			{
				// 添加内容到缓存服务器中
				MemcachedConnector.mcc.add(cache_key, jsonObject.toString(), expireDate);
			}
			
		}
	}
*/
	
	 public static void main(String[] args) throws SQLException
	 {
		 ImportKadSalesIntoCache cacheInfo = new ImportKadSalesIntoCache();
		 
		 
		 Connection conn = cacheInfo.getBIOracle106Conn();
		 cacheInfo.getKadSalse(conn);
		 
		 //cacheProductInfo.ImportFilterIdsIntoCache(ids_set);
	 }
}
