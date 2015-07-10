package com.kad.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import com.kad.util.ImportKadSalesIntoCache;
import com.kad.util.KadSalse;
import com.kad.util.User;
//import com.sun.org.apache.xerces.internal.xs.StringList;

import com.kad.util.MemcachedConnector;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OracleUtil {
	 static Connection connection = null; 
	 static Connection connection_2 = null;
	 
	 public  Connection createConnection(){  
		
		  String url = "jdbc:oracle:thin:@10.0.22.100:1521:pkaddw01";
		  
		  String url_2 = "jdbc:oracle:thin:@10.0.22.106:1522:kaddw02";
	  try {
		  if(connection == null||connection.isClosed()){
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			   connection = DriverManager.getConnection(url, "bidev", "bikaddev");
			   
			   connection_2 = DriverManager.getConnection(url_2, "bidev", "bi2015dev"); 
		  }
	
	  } catch (ClassNotFoundException e) {
	   e.printStackTrace();
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  
	  // return connection;
	  return connection_2;
	 } 
	 
	 public  DataPacket getData(String sql,int curPage) throws ParseException {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			DataPacket packet = new DataPacket();
			packet.setCurPage(curPage);
			
			int num = 20;
			int startnum = (curPage-1)*num;
			int endnum = curPage*num;
			  Connection connection = null;
			  Statement statement = null;
			  ResultSet rs = null;
			  PreparedStatement pState;
			try {
				String pagsql = "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM (SELECT * FROM ODS_VIP_KAD_USER) A WHERE ROWNUM <= ? ) WHERE RN >= ?";
				//String sql = "select * from ODS_VIP_KAD_USER";
				//String sql = "select * from ODS_TRD_KAD_ORDER where to_char(CREATETIME,'yyyy-mm-dd hh24:mi:ss') BETWEEN '"+startDate+"' and '"+endDate+ "'";
				System.out.println(sql);
				pState = createConnection().prepareStatement(sql);
				//pState.setInt(0, x);
				  rs = pState.executeQuery();
				  
				 ResultSetMetaData rsmd = (ResultSetMetaData) pState.getMetaData();
				 for(int j=1 ;j<rsmd.getColumnCount()+1;j++){
					 System.out.println(rsmd.getColumnName(j)+":"+rsmd.getColumnType(j)+":"+rsmd.getColumnTypeName(j));
				 }
				 
				 /*  while(rs.next()){
					   
					  
					   for(int j=1 ;j<rsmd.getColumnCount()+1;j++){
						   if(rs.getString(j)==null||rs.getString(j).equals("")||rs.getString(j).equals("null")){
							   continue;
						   }
						   if(rsmd.getColumnType(j)==12){
							   empObj.put(rsmd.getColumnName(j),rs.getString(j));
						   }
						   if(rsmd.getColumnType(j)==2){
							   empObj.put(rsmd.getColumnName(j),rs.getLong(j));
						   }
						   if(rsmd.getColumnType(j)==91){
							   
							   empObj.put(rsmd.getColumnName(j),format.parseObject(rs.getString(j)));
						   }
						   if(rsmd.getColumnType(j)==1){
							   empObj.put(rsmd.getColumnName(j),rs.getCharacterStream(j));
						   }
						   if(rsmd.getColumnType(j)==2005){
							   empObj.put(rsmd.getColumnName(j),rs.getString(j));
						   }
					   	}
					  
				    			       
				       System.out.println("------------------------");
				   }*/
				   if(connection!=null){
					   connection.close();
				   }
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return packet;
		}
	public JSONObject test() throws ParseException{
		String sql = "select * from ODS_VIP_KAD_USER";
		this.getData(sql, 2);
		return null;
		
	}
	
	public JSONObject bb() throws SQLException{
		Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  PreparedStatement pState;
		String sql = "select CHANNEL2,to_char(DATA_DATE,'yyyy-mm-dd'),count(*)  from DW_LOG_CHANNEL_KEYWORD_DAILY where to_char(DATA_DATE,'yyyy-mm-dd') BETWEEN '2014-04-08' and '2014-04-09' GROUP BY CHANNEL2,to_char(DATA_DATE,'yyyy-mm-dd') ORDER BY to_char(DATA_DATE,'yyyy-mm-dd')";
		  pState = createConnection().prepareStatement(sql);
		  rs = pState.executeQuery();
		  List list1 = new ArrayList();
		 int count = rs.getMetaData().getColumnCount();
		  while (rs.next()){
			  List list2 = new ArrayList();
			  for (int i = 0;i<count;i++){
				  list2.add(rs.getString(i+1));
			  }
			  list1.add(list2);
		  }
		  System.out.println("aa"+list1);
		  JSONArray.fromObject(list1);
		  Map map = new HashMap();
		  map.put("data", list1);
		  return JSONObject.fromObject(map);
	}
	
	
	/*
	public static void main(String[] args) throws HttpException, IOException{
		  Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  PreparedStatement pState;
		try {
			String sql = "select * from V_HOUR_ORDER_MERCHANDISER ";
		
			pState = createConnection().prepareStatement(sql);
			//pState.setInt(1, 169661);
			  rs = pState.executeQuery();	
			  
			  while(rs.next()){
				  System.out.println(rs.getString(1));	
				  System.out.println(rs.getString(2));
				  System.out.println(rs.getString(3));
				  System.out.println(rs.getString(4));
				  System.out.println(rs.getString(5));
				  System.out.println(rs.getString(6));
				  System.out.println(rs.getString(7));
				  System.out.println(rs.getString(8));
				  System.out.println(rs.getString(9));
			  }
				 		 
			   if(connection!=null){
				   connection.close();
			   }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}*/
	public  String getProduceName(int num){
		  Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  String name ="";
		  String pic ="";
		  PreparedStatement pState;
		try {
			String sql = "select PCL.TITLE,PCT.MULTIPLEPHOTO,PCT.PRODUCTNAME,PCT.PRODUCTTHUMB from ODS_PRD_PE_COMMONPRODUCT PCT,"+ 
		"ODS_PRD_PE_U_PRODUCT upt,ODS_TRD_KAD_GOODS kg,ODS_PRD_PE_COMMONMODEL pcl where PCT.PRODUCTID = UPT.ID and UPT.GOODSID = KG.GOODSID and PCT.ENABLESALE = 1 and PCL.ITEMID = PCT.PRODUCTID and PCL.STATUS = 99 AND PCL.GENERALID = ?";		
			pState = createConnection().prepareStatement(sql);
			pState.setInt(1, num);
			  rs = pState.executeQuery();
			  //System.out.println("aa"+rs.getString(1));
			if(rs.next()){
				if(rs.getString(1)==null||rs.getString(1).equals("")){
					 name = " ";
					 pic = " ";
				}else{
					 name = rs.getString(1);
					 pic = rs.getString(4);
				}
				
			}
			 
			  		
			 /*  if(connection!=null){
				   connection.close();
			   }*/
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return name+","+pic;
		}
		return name+","+pic;
	}
	
	public Map getCusOrder(){
		 Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  PreparedStatement pState;
		  List  list = new ArrayList();
		  List  list2 = new ArrayList();
		  List  list3 = new ArrayList();
		  List  list4 = new ArrayList();
		  List  list5 = new ArrayList();
		  List  list6 = new ArrayList();
		  Map data = new HashMap();
		  try {
			String sql = "select * from V_HOUR_ORDER_MERCHANDISER order by RANK_DAY";
		
			pState = createConnection().prepareStatement(sql);
			//pState.setInt(1, 169661);
			  rs = pState.executeQuery();	
			 
			  while(rs.next()){
				  list2.add( rs.getString("ROLENAME")+ rs.getString("MERCHANDISER"));
				  list3.add(new DecimalFormat( ".00" ).format(rs.getDouble("ORDER_SALES_6S")));
				  list4.add(new DecimalFormat( ".00" ).format(rs.getDouble("TARGET_MONEY_DAY")));
				  list5.add(new DecimalFormat( ".00" ).format(rs.getDouble("COMPLETE_RATE_DAY")));
				  list6.add(100-rs.getDouble("COMPLETE_RATE_DAY"));
				  Map map = new HashMap();
				  
				  map.put("name", rs.getString("ROLENAME"));
				  map.put("group",  rs.getString("MERCHANDISER"));
				  map.put("count", rs.getString(5));
				  map.put("sales", new DecimalFormat( ".00" ).format(rs.getDouble("ORDER_SALES_6S")));
				  map.put("target", new DecimalFormat( ".00" ).format(rs.getDouble("TARGET_MONEY_DAY")));
				  map.put("rate", new DecimalFormat( ".00" ).format(rs.getDouble("COMPLETE_RATE_DAY")));
				 list.add(map);
			  }
				 		 
			   if(connection!=null){
				   connection.close();
			   }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  //Collections.sort(list5);
		  //Collections.reverse(list5);
		  //Collections.sort(list6);
		  data.put("name", list2);
		  data.put("sale", list3);
		  data.put("target", list4);
		  data.put("table", list);
		  data.put("rate", list5);
		  data.put("rate2", list6);
		return data;
	}
	
	public JSONObject getOrderData(){
		Date d = new Date();
		Map map = new HashMap();
		Map map1 = new HashMap();
		Map map2 = new HashMap();
		float[] yArr = new float[24];
		float[] yArr2 = new float[24];
		float[] yArr3 = new float[24];
		float[] yArr4 = new float[24];
		float[] yArr6 = new float[24];
		float[] yArr5 = new float[24];
		float[] yArr7 = new float[24];
		float[] yArr8 = new float[24];
		float[] yArr9 = new float[24];
		float[] yArr10 = new float[24];
		float[] yArr11 = new float[24];
		float[] yArr12 = new float[24];
		float[] yArr13 = new float[24];
		float[] yArr14 = new float[24];
		float[] yArr15 = new float[24];
		float[] yArr16 = new float[24];
		float[] yArr17 = new float[24];
		float[] y2Arr = new float[24];
		float[] y2Arr2 = new float[24];
		float[] y2Arr3 = new float[24];
		float[] y2Arr4 = new float[24];
		float[] y2Arr6 = new float[24];
		float[] y2Arr5 = new float[24];
		float[] y2Arr7 = new float[24];
		float[] y2Arr8 = new float[24];
		float[] y2Arr9 = new float[24];
		float[] y2Arr10 = new float[24];
		float[] y2Arr11 = new float[24];
		float[] y2Arr12 = new float[24];
		float[] y2Arr13 = new float[24];
		float[] y2Arr14 = new float[24];
		float[] y2Arr15 = new float[24];
		float[] y2Arr16 = new float[24];
		float[] y2Arr17 = new float[24];
		float f1 = 0.0f;
		float f2 = 0.0f;
		float f3 = 0.0f;
		float f4 = 0.0f;
		float f5 = 0.0f;
		float f6 = 0.0f;
		float f7 = 0.0f;
		float f8 = 0.0f;
		float f9 = 0.0f;
		float f10 = 0.0f;
		float f11= 0.0f;
		float f12= 0.0f;
		float f13= 0.0f;
		float f14= 0.0f;
		float f15= 0.0f;
		float f16= 0.0f;
		float f17= 0.0f;
		float f21 = 0.0f;
		float f22 = 0.0f;
		float f23 = 0.0f;
		float f24 = 0.0f;
		float f25 = 0.0f;
		float f26 = 0.0f;
		float f27 = 0.0f;
		float f28 = 0.0f;
		float f29 = 0.0f;
		float f210 = 0.0f;
		float f211= 0.0f;
		float f212= 0.0f;
		float f213= 0.0f;
		float f214= 0.0f;
		float f215= 0.0f;
		float f216= 0.0f;
		float f217= 0.0f;
		float y1= 0.0f;
		float y2= 0.0f;
		float y3= 0.0f;
		float y4= 0.0f;
		float y5= 0.0f;
		float y6= 0.0f;
		float y7= 0.0f;
		float y8= 0.0f;
		float y21= 0.0f;
		float y22= 0.0f;
		float y23= 0.0f;
		float y24= 0.0f;
		float y25= 0.0f;
		float y26= 0.0f;
		float y27= 0.0f;
		float y28= 0.0f;
		int[] xArr = new int[24];
		int[] xArr2 = new int[24];
		for(int i=0;i<24;i++){
			yArr[i] = 0;
			yArr2[i] = 0;
			yArr3[i] = 0;
			xArr2[i] = i;
			xArr[i] = i;
			yArr4[i] = 0;
			yArr6[i] = 0;
			yArr5[i] = 0;
			yArr7[i] = 0;
			yArr8[i] = 0;
			yArr9[i] = 0;
			yArr10[i] = 0;
			yArr11[i] = 0;
			yArr12[i] = 0;
			yArr13[i] = 0;
			yArr14[i] = 0;
			yArr15[i] = 0;
			y2Arr[i] = 0;
			y2Arr2[i] = 0;
			y2Arr3[i] = 0;
			y2Arr4[i] = 0;
			y2Arr6[i] = 0;
			y2Arr5[i] = 0;
			y2Arr7[i] = 0;
			y2Arr8[i] = 0;
			y2Arr9[i] = 0;
			y2Arr10[i] = 0;
			y2Arr11[i] = 0;
			y2Arr12[i] = 0;
			y2Arr13[i] = 0;		
			y2Arr14[i] = 0;	
			y2Arr15[i] = 0;		
		}
		//List list1 = new ArrayList();
		//List list2 = new ArrayList();
		 Connection connection =  createConnection();
		  Statement statement = null;
		  ResultSet rs = null;
		  PreparedStatement pState;
		
//		  
//		String sql = "Select ORDER_HOUR,"+
//	" sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//	" sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//	" sum(ORDER_NUM_6s) as hour_order_num6s,"+
//	" sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//	" sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//	" sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//	" sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//	" sum(sum(ORDER_sales_6S)) over("+
//	" order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//	" From rpt_order_5mins"+
//	" Where Order_Date = TO_CHAR(SYSDATE, 'yyyymmdd')"+
//	
//	" group by ORDER_HOUR";
//	
//		
//	/*
//	String sql = "select to_char(a.ordertime,'hh24') time, "
//	        + "sum(a.sales_quantity*(a.sales_price-a.goods_concession)-a.order_concession+a.goods_freight)   sales1 "
//	        + "from bidev.dw_kad_orderitem a "
//	        + "where a.origin = 16 "
//	        + " and   a.ordertime>= to_date('2015-07-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss') "
//	        + " and a.ordertime< to_date('2015-07-02 00:00:00', 'yyyy-mm-dd hh24:mi:ss') "
//	        + " group by to_char(a.ordertime,'hh24') "
//	        + " order by to_char(a.ordertime,'hh24') ";
//	
//	*/
//		try {
//			pState = connection.prepareStatement(sql);
//			  rs = pState.executeQuery();	
//			  while(rs.next())
//			  {
//				  //System.out.println(" >>>>>> " + rs.getFloat(2));
//				  
//				  
//				  if(rs.getFloat(7)>f1){
//					  f1 = rs.getFloat(7);
//				  }
//				  if(rs.getFloat(9)>f1){
//					  f21 = rs.getFloat(9);
//				  }
//						
//					  yArr[rs.getInt(1)] = rs.getFloat(6);	
//					  y2Arr[rs.getInt(1)] = rs.getFloat(8);
//				 
//				}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		sql = "Select ORDER_HOUR,"+
//		" sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//		" sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//		" sum(ORDER_NUM_6s) as hour_order_num6s,"+
//		" sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//		" sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//		" sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//		" sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//		" sum(sum(ORDER_sales_6S)) over("+
//		" order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//		" From rpt_order_5mins"+
//		" Where Order_Date = TO_CHAR(SYSDATE - 1 , 'yyyymmdd')"+
//		
//		" group by ORDER_HOUR";
//		try {
//			pState = connection.prepareStatement(sql);
//			  rs = pState.executeQuery();	
//			  while(rs.next()){			
//					  yArr2[rs.getInt(1)] = rs.getFloat(6);
//					  y2Arr2[rs.getInt(1)] = rs.getFloat(8);
//				  if(rs.getFloat(7)>f2){
//					  f2 = rs.getFloat(7);
//				  }
//				  if(rs.getFloat(9)>f22){
//					  f22 = rs.getFloat(9);
//				  }
//				  
//				  if(rs.getInt(1)<=d.getHours()){
//						y1 = rs.getFloat(7);
//					}
//				  if(rs.getInt(1)<=d.getHours()){
//						y21 = rs.getFloat(9);
//					}
//				 // list1.add(rs.getFloat(1));
//				  //list2.add(rs.getFloat(2));
//					//System.out.println("cc"+rs.getFloat(1));
//				}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		
//		/*sql = "Select ORDER_HOUR,"+
//				" sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//				" sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//				" sum(ORDER_NUM_6s) as hour_order_num6s,"+
//				" sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//				" sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//				" sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//				" sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//				" sum(sum(ORDER_sales_6S)) over("+
//				" order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_6s"+
//				" From dw_order_hours"+
//				" Where Order_Date = TO_CHAR(SYSDATE - 7 , 'yyyymmdd')"+
//				" AND originname in ('当当','Android','京东', '天猫', 'Wap', '网站', '客服','苏宁','818平台','亚马逊')"+
//				" group by ORDER_HOUR";
//				try {
//					pState = connection.prepareStatement(sql);
//					  rs = pState.executeQuery();	
//					  while(rs.next()){
//						 
//							  yArr3[rs.getInt(rs.getInt(1))] = rs.getFloat(8);
//						  
//						  
//						  if(rs.getFloat(9)>f3){
//							  f3 = rs.getFloat(9);
//						  }
//						 // list1.add(rs.getFloat(1));
//						  //list2.add(rs.getFloat(2));
//							//System.out.println("cc"+rs.getFloat(1));
//						}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				*/
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE , 'yyyymmdd')"+
//					 " AND originname = '天猫'"+
//					 " group by ORDER_HOUR";
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					 
//						  yArr4[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr4[rs.getInt(1)] = rs.getFloat(8);
//					 
//					  if(rs.getFloat(7)>f4){
//						  f4 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f24){
//						  f24 = rs.getFloat(9);
//					  }
//					
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE -1 , 'yyyymmdd')"+
//					 " AND originname = '天猫'"+
//					 " group by ORDER_HOUR";
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					 
//						  yArr6[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr6[rs.getInt(1)] = rs.getFloat(8);
//						  if(rs.getInt(1)<=d.getHours()){
//								y2 = rs.getFloat(7);
//								y22 = rs.getFloat(9);
//							}
//					  if(rs.getFloat(7)>f5){
//						  f5 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f25){
//						  f25 = rs.getFloat(7);
//					  }
//					
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE  , 'yyyymmdd')"+
//					 " AND originname = '官网'"+
//					 " group by ORDER_HOUR";
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr5[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr5[rs.getInt(1)] = rs.getFloat(8);
//					 
//					  if(rs.getFloat(7)>f6){
//						  f6 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f26){
//						  f26 = rs.getFloat(9);
//					  }
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE -1 , 'yyyymmdd')"+
//					 " AND originname = '官网'"+
//					 " group by ORDER_HOUR";
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr7[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr7[rs.getInt(1)] = rs.getFloat(8);
//						  if(rs.getInt(1)<=d.getHours()){
//								y3 = rs.getFloat(7);
//								y23=rs.getFloat(9);
//							}
//					  if(rs.getFloat(7)>f7){
//						  f7 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f27){
//						  f27 = rs.getFloat(9);
//					  }
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE , 'yyyymmdd')"+
//					 " AND originname = 'Wap'"+
//					 " group by ORDER_HOUR";
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr8[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr8[rs.getInt(1)] = rs.getFloat(8);
//				
//					  if(rs.getFloat(7)>f8){
//						  f8 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f28){
//						  f28 = rs.getFloat(9);
//					  }
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE -1 , 'yyyymmdd')"+
//					 " AND originname = 'Wap'"+
//					 " group by ORDER_HOUR";
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr9[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr9[rs.getInt(1)] = rs.getFloat(8);
//						  if(rs.getInt(1)<=d.getHours()){
//								y4 = rs.getFloat(7);
//								y24 = rs.getFloat(9);
//							}
//					  if(rs.getFloat(7)>f9){
//						  f9 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f29){
//						  f29 = rs.getFloat(9);
//					  }
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE , 'yyyymmdd')"+
//					 " AND originname = 'Android'"+
//					 " group by ORDER_HOUR";
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr10[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr10[rs.getInt(1)] = rs.getFloat(8);
//					 
//					  if(rs.getFloat(7)>f10){
//						  f10 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f210){
//						  f210 = rs.getFloat(9);
//					  }
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE-1 , 'yyyymmdd')"+
//					 " AND originname = 'Android'"+
//					 " group by ORDER_HOUR";
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr11[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr11[rs.getInt(1)] = rs.getFloat(8);
//						  if(rs.getInt(1)<=d.getHours()){
//								y5 = rs.getFloat(7);
//								y25 = rs.getFloat(9);
//							}
//						  
//					  if(rs.getFloat(7)>f11){
//						  f11 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f211){
//						  f211 = rs.getFloat(9);
//					  }
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE , 'yyyymmdd')"+
//					 " AND originname = 'IPhone'"+
//					 " group by ORDER_HOUR";
//			
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr14[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr14[rs.getInt(1)] = rs.getFloat(8);
//					  
//					  if(rs.getFloat(7)>f14){
//						  f14 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f214){
//						  f214 = rs.getFloat(9);
//					  }
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE -1, 'yyyymmdd')"+
//					 " AND originname = 'IPhone'"+
//					 " group by ORDER_HOUR";
//			
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr15[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr15[rs.getInt(1)] = rs.getFloat(8);
//						  
//						  if(rs.getInt(1)<=d.getHours()){
//								y7 = rs.getFloat(7);
//								y27 = rs.getFloat(9);
//							}
//					  if(rs.getFloat(7)>f15){
//						  f15 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f215){
//						  f215 = rs.getFloat(9);
//					  }
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			
//			sql =	"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE , 'yyyymmdd')"+
//					 " AND originname = '京东'"+
//					 " group by ORDER_HOUR";
//			
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr12[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr12[rs.getInt(1)] = rs.getFloat(8);
//					  
//					  if(rs.getFloat(7)>f12){
//						  f12 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f212){
//						  f212 = rs.getFloat(9);
//					  }
//					
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			
//			sql =		"Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					 " sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					 " sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					 " sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					 " sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					 " From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE-1 , 'yyyymmdd')"+
//					 " AND originname = '京东'"+
//					 " group by ORDER_HOUR";
//			
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr13[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr13[rs.getInt(1)] = rs.getFloat(8);
//						  if(rs.getInt(1)<=d.getHours()){
//								y6 = rs.getFloat(7);
//								y26 = rs.getFloat(9);
//							}
//					  if(rs.getFloat(7)>f13){
//						  f13 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f213){
//						  f213 = rs.getFloat(9);
//					  }
//					}
//				 
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			//自建平台汇总
//		sql = "Select ORDER_HOUR,"+
//	 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//	" sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//	" sum(ORDER_NUM_6s) as hour_order_num6s,"+
//	" sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//	 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//	" sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//	 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//	 " sum(sum(ORDER_sales_6S)) over("+
//	 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//	" From rpt_order_5mins"+
//	 " Where Order_Date = TO_CHAR(SYSDATE, 'yyyymmdd')"+
//	" AND originname  in ('Android', 'Wap', '官网','IPhone')"+
//	 " group by ORDER_HOUR";
//			try {
//				pState = connection.prepareStatement(sql);
//				  rs = pState.executeQuery();	
//				  while(rs.next()){
//					  
//						  yArr16[rs.getInt(1)] = rs.getFloat(6);
//						  y2Arr16[rs.getInt(1)] = rs.getFloat(8);
//						  
//					  if(rs.getFloat(7)>f16){
//						  f16 = rs.getFloat(7);
//					  }
//					  if(rs.getFloat(9)>f216){
//						  f216 = rs.getFloat(9);
//					  }
//					}
//				 
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//			
//			sql = "Select ORDER_HOUR,"+
//					 " sum(ORDER_NUM_4S) as hour_order_num_4s,"+
//					" sum(sum(ORDER_NUM_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_4s,"+
//					" sum(ORDER_NUM_6s) as hour_order_num6s,"+
//					" sum(sum(ORDER_NUM_6S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_num_6s,"+
//					 " sum(ORDER_sales_4S) as hour_order_sales_4s,"+
//					" sum(sum(ORDER_sales_4S)) over(order by ORDER_HOUR rows between unbounded preceding and current row) current_order_sales_4s,"+
//					 " sum(ORDER_sales_6S) as hour_order_sales_6s,"+
//					 " sum(sum(ORDER_sales_6S)) over("+
//					 " order by to_number(ORDER_HOUR) rows between unbounded preceding and current row) current_order_sales_6s"+
//					" From rpt_order_5mins"+
//					 " Where Order_Date = TO_CHAR(SYSDATE -1, 'yyyymmdd')"+
//					" AND originname  in ('Android', 'Wap', '官网','IPhone')"+
//					 " group by ORDER_HOUR";
//							try {
//								pState = connection.prepareStatement(sql);
//								  rs = pState.executeQuery();	
//								  while(rs.next()){
//									  
//										  yArr17[rs.getInt(1)] = rs.getFloat(6);
//										  y2Arr17[rs.getInt(1)] = rs.getFloat(8);
//										  if(rs.getInt(1)<=d.getHours()){
//												y8 = rs.getFloat(7);
//												y28 = rs.getFloat(9);
//											}
//									  if(rs.getFloat(7)>f17){
//										  f17 = rs.getFloat(7);
//									  }
//									  if(rs.getFloat(9)>f217){
//										  f217 = rs.getFloat(9);
//									  }
//									}
//								  rs.close();
//								  pState.close();
//								  connection.close();
//							} catch (SQLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//								return null;
//							}
			
			
			DecimalFormat f=new DecimalFormat(",###");
			String sf1 = f.format(f1);
			String sf2 = f.format(f2);
			//String sf1 = f.format(f1);
			String sf4 = f.format(f4);
			String sf5 = f.format(f5);
			String sf6 = f.format(f6);
			String sf7 = f.format(f7);
			String sf8 = f.format(f8);
			String sf9 = f.format(f9);
			String sf10 = f.format(f10);
			String sf11 = f.format(f11);
			String sf12 = f.format(f12);
			String sf13 = f.format(f13);
			String sf14 = f.format(f14);
			String sf15 = f.format(f15);
			String sf16 = f.format(f16);
			String sf17 = f.format(f17);
			String sf21 = f.format(f21);
			String sf22 = f.format(f22);
			//String sf1 = f.format(f1);
			String sf24 = f.format(f24);
			String sf25 = f.format(f25);
			String sf26 = f.format(f26);
			String sf27 = f.format(f27);
			String sf28 = f.format(f28);
			String sf29 = f.format(f29);
			String sf210 = f.format(f210);
			String sf211 = f.format(f211);
			String sf212 = f.format(f212);
			String sf213 = f.format(f213);
			String sf214 = f.format(f214);
			String sf215 = f.format(f215);
			String sf216 = f.format(f216);
			String sf217 = f.format(f217);
			String sy1 = f.format(y1);
			String sy2 = f.format(y2);
			String sy3 = f.format(y3);
			String sy4 = f.format(y4);
			String sy5 = f.format(y5);
			String sy6 = f.format(y6);
			String sy7 = f.format(y7);
			String sy8 = f.format(y8);
			String sy21 = f.format(y21);
			String sy22 = f.format(y22);
			String sy23 = f.format(y23);
			String sy24 = f.format(y24);
			String sy25 = f.format(y25);
			String sy26 = f.format(y26);
			String sy27 = f.format(y27);
			String sy28 = f.format(y28);
			map1.put("time",xArr );
			map1.put("time2",xArr2 );
		  map1.put("count",yArr);
		  map1.put("count2",yArr2);
		  map1.put("count3",yArr3);
		  map1.put("count4",yArr4);
		  map1.put("count5",yArr5);
		  map1.put("count6",yArr6);
		  map1.put("count7",yArr7);
		  map1.put("count8",yArr8);
		  map1.put("count9",yArr9);
		  map1.put("count10",yArr10);
		  map1.put("count11",yArr11);
		  map1.put("count12",yArr12);
		  map1.put("count13",yArr13);
		  map1.put("count14",yArr14);
		  map1.put("count15",yArr15);
		  map1.put("count16",yArr16);
		  map1.put("count17",yArr17);
		  map1.put("f1", sf1);
		  map1.put("f2", sf2);
		  //map.put("f3", f3);
		  map1.put("f4", sf4);
		  map1.put("f5", sf5);
		  map1.put("f6", sf6);
		  map1.put("f7", sf7);
		  map1.put("f8", sf8);
		  map1.put("f9", sf9);		
		  map1.put("f10", sf10);
		  map1.put("f11", sf11);
		  map1.put("f12", sf12);
		  map1.put("f13", sf13);
		  map1.put("f14", sf14);
		  map1.put("f15", sf15);
		  map1.put("f16", sf16);
		  map1.put("f17", sf17);
		  map1.put("y1", sy1);
		  map1.put("y2", sy2);
		  map1.put("y3", sy3);
		  map1.put("y4", sy4);
		  map1.put("y5", sy5);
		  map1.put("y6", sy6);
		  map1.put("y7", sy7);
		  map1.put("y8", sy8);
		  
		  
		  map2.put("time",xArr );
		  map2.put("time2",xArr2 );
		  map2.put("count",y2Arr);
		  map2.put("count2",y2Arr2);
		  map2.put("count3",y2Arr3);
		  map2.put("count4",y2Arr4);
		  map2.put("count5",y2Arr5);
		  map2.put("count6",y2Arr6);
		  map2.put("count7",y2Arr7);
		  map2.put("count8",y2Arr8);
		  map2.put("count9",y2Arr9);
		  map2.put("count10",y2Arr10);
		  map2.put("count11",y2Arr11);
		  map2.put("count12",y2Arr12);
		  map2.put("count13",y2Arr13);
		  map2.put("count14",y2Arr14);
		  map2.put("count15",y2Arr15);
		  map2.put("count16",y2Arr16);
		  map2.put("count17",y2Arr17);
		  map2.put("f1", sf21);
		  map2.put("f2", sf22);
		  //map.put("f3", f3);
		  map2.put("f4", sf24);
		  map2.put("f5", sf25);
		  map2.put("f6", sf26);
		  map2.put("f7", sf27);
		  map2.put("f8", sf28);
		  map2.put("f9", sf29);		
		  map2.put("f10", sf210);
		  map2.put("f11", sf211);
		  map2.put("f12", sf212);
		  map2.put("f13", sf213);
		  map2.put("f14", sf214);
		  map2.put("f15", sf215);
		  map2.put("f16", sf216);
		  map2.put("f17", sf217);
		  map2.put("y1", sy21);
		  map2.put("y2", sy22);
		  map2.put("y3", sy23);
		  map2.put("y4", sy24);
		  map2.put("y5", sy25);
		  map2.put("y6", sy26);
		  map2.put("y7", sy27);
		  map2.put("y8", sy28);
		  map.put("d1", map1);
		  map.put("d2", map2);
		  System.out.println("cc"+map);
		  
		  
		  map1.put("f16", 56);
		  map1.put("f17", 78);
		  map1.put("y8", 89);
		  
		  int [] xx = {1, 2, 3, 4, 5, 6, 7, 8,9,10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
		  map1.put("count16",	xx);
		  
		  
		  int [] yy = {1, 2, 3, 4, 5, 6, 7, 8,9,10, 1, 2, 3, 4, 5, 6, 7, 8,9,10, 21, 22, 23, 24};
		  map1.put("count17", yy);
		  
		  System.out.println("==================");
		  for(Object key:map.keySet())
		  {
			  System.out.println(map.get(key));
		  }
		  
		return JSONObject.fromObject(map);
				 
	}
	
	
	//////////////////////////////////////////////////////
	// 2.0 平台销售数据统计
	/////////////////////////////////////////////////////
	
	public JSONObject getOrderDataNew(){
		Date d = new Date();
		Map map = new HashMap();
		Map map1 = new HashMap();
		Map map2 = new HashMap();
		double[] yArr = new double[24];
		double[] yArr2 = new double[24];
		double[] yArr3 = new double[24];
		double[] yArr4 = new double[24];
		double[] yArr6 = new double[24];
		double[] yArr5 = new double[24];
		double[] yArr7 = new double[24];
		double[] yArr8 = new double[24];
		double[] yArr9 = new double[24];
		double[] yArr10 = new double[24];
		double[] yArr11 = new double[24];
		double[] yArr12 = new double[24];
		double[] yArr13 = new double[24];
		double[] yArr14 = new double[24];
		double[] yArr15 = new double[24];
		double[] yArr16 = new double[24];
		double[] yArr17 = new double[24];
		double[] yArr18 = new double[24];
		double[] yArr19 = new double[24];
		
		float f1 = 0.0f;
		float f2 = 0.0f;
		float f3 = 0.0f;
		float f4 = 0.0f;
		float f5 = 0.0f;
		float f6 = 0.0f;
		float f7 = 0.0f;
		float f8 = 0.0f;
		float f9 = 0.0f;
		float f10 = 0.0f;
		float f11= 0.0f;
		float f12= 0.0f;
		float f13= 0.0f;
		float f14= 0.0f;
		float f15= 0.0f;
		float f16= 0.0f;
		float f17= 0.0f;
		float f18= 0.0f;
		float f19= 0.0f;
		
		float y1= 0.0f;
		float y2= 0.0f;
		float y3= 0.0f;
		float y4= 0.0f;
		float y5= 0.0f;
		float y6= 0.0f;
		float y7= 0.0f;
		float y8= 0.0f;
		float y9= 0.0f;
		
		int[] xArr = new int[24];
		int[] xArr2 = new int[24];
		for(int i=0;i<24;i++)
		{
			xArr2[i] = i;
			xArr[i] = i;
			
			yArr[i] = 0;
			yArr2[i] = 0;
			yArr3[i] = 0;
			yArr4[i] = 0;
			yArr6[i] = 0;
			yArr5[i] = 0;
			yArr7[i] = 0;
			yArr8[i] = 0;
			yArr9[i] = 0;
			yArr10[i] = 0;
			yArr11[i] = 0;
			yArr12[i] = 0;
			yArr13[i] = 0;
			yArr14[i] = 0;
			yArr15[i] = 0;
			yArr16[i] = 0;
			yArr17[i] = 0;
			yArr18[i] = 0;
			yArr19[i] = 0;
		}

		// Connection connection =  createConnection();
		
		// 全司
		String cache_key = getCacheKey("quansi");
		KadSalse kadsaleObj = getKadSalseObj(cache_key);
		// 今日累计
		f1 = (float)kadsaleObj.gettodayTotal_4();
		// 昨日同期累计
		y1 = (float)kadsaleObj.getyestodaySameTime_4();
		// 昨日累计
		f2 = (float)kadsaleObj.getyestodayTotal_4();
		// 今日曲线
		yArr = kadsaleObj.gettodayEveryHour_4();
		// 昨日曲线
		yArr2 = kadsaleObj.getyestodayEveryHour_4();
		
		// 天猫
		cache_key = getCacheKey("tianmao");
		kadsaleObj = getKadSalseObj(cache_key);
		// 今日累计
		f4 = (float)kadsaleObj.gettodayTotal_4();
		// 昨日同期累计
		y2 = (float)kadsaleObj.getyestodaySameTime_4();
		// 昨日累计
		f5 = (float)kadsaleObj.getyestodayTotal_4();
		// 今日曲线
		yArr4 = kadsaleObj.gettodayEveryHour_4();
		// 昨日曲线
		yArr6 = kadsaleObj.getyestodayEveryHour_4();
		
		// 官网
		cache_key = getCacheKey("guanwang");
		kadsaleObj = getKadSalseObj(cache_key);
		// 今日累计
		f6 = (float)kadsaleObj.gettodayTotal_4();
		// 昨日同期累计
		y3 = (float)kadsaleObj.getyestodaySameTime_4();
		// 昨日累计
		f7 = (float)kadsaleObj.getyestodayTotal_4();
		// 今日曲线
		yArr5 = kadsaleObj.gettodayEveryHour_4();
		// 昨日曲线
		yArr7 = kadsaleObj.getyestodayEveryHour_4();
		
		// wap
		cache_key = getCacheKey("wap");
		kadsaleObj = getKadSalseObj(cache_key);
		// 今日累计
		f8 = (float)kadsaleObj.gettodayTotal_4();
		// 昨日同期累计
		y4 = (float)kadsaleObj.getyestodaySameTime_4();
		// 昨日累计
		f9 = (float)kadsaleObj.getyestodayTotal_4();
		// 今日曲线
		yArr8 = kadsaleObj.gettodayEveryHour_4();
		// 昨日曲线
		yArr9 = kadsaleObj.getyestodayEveryHour_4();
		
		// Android
		cache_key = getCacheKey("android");
		kadsaleObj = getKadSalseObj(cache_key);
		// 今日累计
		f10 = (float)kadsaleObj.gettodayTotal_4();
		// 昨日同期累计
		y5 = (float)kadsaleObj.getyestodaySameTime_4();
		// 昨日累计
		f11 = (float)kadsaleObj.getyestodayTotal_4();
		// 今日曲线
		yArr10 = kadsaleObj.gettodayEveryHour_4();
		// 昨日曲线
		yArr11 = kadsaleObj.getyestodayEveryHour_4();
		
		// 京东
		cache_key = getCacheKey("jingdong");
		kadsaleObj = getKadSalseObj(cache_key);
		// 今日累计
		f12 = (float)kadsaleObj.gettodayTotal_4();
		// 昨日同期累计
		y6 = (float)kadsaleObj.getyestodaySameTime_4();
		// 昨日累计
		f13 = (float)kadsaleObj.getyestodayTotal_4();
		// 今日曲线
		yArr12 = kadsaleObj.gettodayEveryHour_4();
		// 昨日曲线
		yArr13 = kadsaleObj.getyestodayEveryHour_4();
		
		// ios
		cache_key = getCacheKey("ios");
		kadsaleObj = getKadSalseObj(cache_key);
		// 今日累计
		f14 = (float)kadsaleObj.gettodayTotal_4();
		// 昨日同期累计
		y7 = (float)kadsaleObj.getyestodaySameTime_4();
		// 昨日累计
		f15 = (float)kadsaleObj.getyestodayTotal_4();
		// 今日曲线
		yArr14 = kadsaleObj.gettodayEveryHour_4();
		// 昨日曲线
		yArr15 = kadsaleObj.getyestodayEveryHour_4();
		
		// 自建
		cache_key = getCacheKey("zijian");
		kadsaleObj = getKadSalseObj(cache_key);
		// 今日累计
		f16 = (float)kadsaleObj.gettodayTotal_4();
		// 昨日同期累计
		y8 = (float)kadsaleObj.getyestodaySameTime_4();
		// 昨日累计
		f17 = (float)kadsaleObj.getyestodayTotal_4();
		// 今日曲线
		yArr16 = kadsaleObj.gettodayEveryHour_4();
		// 昨日曲线
		yArr17 = kadsaleObj.getyestodayEveryHour_4();
		
		
		// 微商城
		cache_key = getCacheKey("weishangcheng");
		kadsaleObj = getKadSalseObj(cache_key);
		// 今日累计
		f18 = (float)kadsaleObj.gettodayTotal_4();
		// 昨日同期累计
		y9 = (float)kadsaleObj.getyestodaySameTime_4();
		// 昨日累计
		f19 = (float)kadsaleObj.getyestodayTotal_4();
		// 今日曲线
		yArr18 = kadsaleObj.gettodayEveryHour_4();
		// 昨日曲线
		yArr19 = kadsaleObj.getyestodayEveryHour_4();
		
/*
		// 今日累计-全司
		f1 = getTodayToatal(connection, "", yArr);
		// 昨日同期累计-全司
		 y1 = getYesTheTimeTotal(connection, "");
		// 昨日累计-全司
		 f2 = getYestodayToatal(connection, "", yArr2);
		 
		// 今日累计-天猫
		f4 = getTodayToatal(connection, "4", yArr4);
		// 昨日同期累计-天猫
		y2 = getYesTheTimeTotal(connection, "4");
		// 昨日累计-天猫
		f5 = getYestodayToatal(connection, "4", yArr6);
		
		// 今日累计-官网
		f6 = getTodayToatal(connection, "1,12", yArr5);
		// 昨日同期累计-官网
		y3 = getYesTheTimeTotal(connection, "1,12");
		// 昨日累计-官网
		f7 = getYestodayToatal(connection, "1,12", yArr7);
		
		// 今日累计-wap
		f8 = getTodayToatal(connection, "13", yArr8);
		// 昨日同期累计-wap
		y4 = getYesTheTimeTotal(connection, "13");
		// 昨日累计-wap
		f9 = getYestodayToatal(connection, "13", yArr9);
		
		// 今日累计-Android
		f10 = getTodayToatal(connection, "14", yArr10);
		// 昨日同期累计-Android
		y5 = getYesTheTimeTotal(connection, "14");
		// 昨日累计-Android
		f11 = getYestodayToatal(connection, "14", yArr11);
		  
		// 今日累计-京东
		f12 = getTodayToatal(connection, "16", yArr12);
		// 昨日同期累计-京东
		y6 = getYesTheTimeTotal(connection, "16");
		// 昨日累计-京东
		f13 = getYestodayToatal(connection, "16", yArr13);
		  
		// 今日累计-IOS
		f14 = getTodayToatal(connection, "15", yArr14);
		// 昨日同期累计-IOS
		y7 = getYesTheTimeTotal(connection, "15");
		// 昨日累计-IOS
		f15 = getYestodayToatal(connection, "15", yArr15);
		
		// 今日累计-自建
		f16 = getTodayToatal(connection, "1,2,12,13,14,15", yArr16);
		// 昨日同期累计-自建
		y8 = getYesTheTimeTotal(connection, "1,2,12,13,14,15");
		// 昨日累计-自建
		f17 = getYestodayToatal(connection, "1,2,12,13,14,15", yArr17);
		
		// 今日累计-微商
		f18 = getTodayToatal(connection, "21", yArr18);
		// 昨日同期累计-微商
		y9 = getYesTheTimeTotal(connection, "21");
		// 昨日累计-微商
		f19 = getYestodayToatal(connection, "21", yArr19);
*/		
		DecimalFormat f =new DecimalFormat(",###");
		String sf1 = f.format(f1);
		String sf2 = f.format(f2);
		//String sf1 = f.format(f1);
		String sf4 = f.format(f4);
		String sf5 = f.format(f5);
		String sf6 = f.format(f6);
		String sf7 = f.format(f7);
		String sf8 = f.format(f8);
		String sf9 = f.format(f9);
		String sf10 = f.format(f10);
		String sf11 = f.format(f11);
		String sf12 = f.format(f12);
		String sf13 = f.format(f13);
		String sf14 = f.format(f14);
		String sf15 = f.format(f15);
		String sf16 = f.format(f16);
		String sf17 = f.format(f17);
		String sf18 = f.format(f18);
		String sf19 = f.format(f19);
		
		String sy1 = f.format(y1);
		String sy2 = f.format(y2);
		String sy3 = f.format(y3);
		String sy4 = f.format(y4);
		String sy5 = f.format(y5);
		String sy6 = f.format(y6);
		String sy7 = f.format(y7);
		String sy8 = f.format(y8);
		String sy9 = f.format(y9);
		
		map1.put("time",xArr );
		map1.put("time2",xArr2 );
		
		map1.put("count",yArr);
		map1.put("count2",yArr2);
		map1.put("count3",yArr3);
		map1.put("count4",yArr4);
		map1.put("count5",yArr5);
		map1.put("count6",yArr6);
		map1.put("count7",yArr7);
		map1.put("count8",yArr8);
		map1.put("count9",yArr9);
		map1.put("count10",yArr10);
		map1.put("count11",yArr11);
		map1.put("count12",yArr12);
		map1.put("count13",yArr13);
		map1.put("count14",yArr14);
		map1.put("count15",yArr15);
		map1.put("count16",yArr16);
		map1.put("count17",yArr17);
		map1.put("count18",yArr18);
		map1.put("count19",yArr19);
		  
		map1.put("f1", sf1);
		map1.put("f2", sf2);
		//map.put("f3", f3);
		map1.put("f4", sf4);
		map1.put("f5", sf5);
		map1.put("f6", sf6);
		map1.put("f7", sf7);
		map1.put("f8", sf8);
		map1.put("f9", sf9);		
		map1.put("f10", sf10);
		map1.put("f11", sf11);
		map1.put("f12", sf12);
		map1.put("f13", sf13);
		map1.put("f14", sf14);
		map1.put("f15", sf15);
		map1.put("f16", sf16);
		map1.put("f17", sf17);
		map1.put("f18", sf18);
		map1.put("f19", sf19);
		
		map1.put("y1", sy1);
		map1.put("y2", sy2);
		map1.put("y3", sy3);
		map1.put("y4", sy4);
		map1.put("y5", sy5);
		map1.put("y6", sy6);
		map1.put("y7", sy7);
		map1.put("y8", sy8);
		map1.put("y9", sy9);
		  
		map.put("d1", map1);
		  
		return JSONObject.fromObject(map);
	}
	
	// 生成cache key
	public String getCacheKey(String platform)
	{
		/*  规定的平台名字
		 *  "quansi",
		 *	"guanwang",
		 *	"wap",
		 *	"android",
		 *	"tianmao",
		 *	"jingdong",
		 *	"ios",
		 *	"zijian",
		 *	"weishangcheng",
		*/
		
		String key = "";
		key = ImportKadSalesIntoCache.getCacheKey(platform);
		
		return key;
	}
	
	// 从cache中取出数据并转化为java对象
	public KadSalse getKadSalseObj(String cacheKey)
	{
		System.out.println(" cong cache zhong  qu chu ==== " + cacheKey );
		
		KadSalse kadSalseObj = new KadSalse();
		
		// if (cacheKey.equals("2015-6-9-16_tianmao"))
			// System.out.println("key == " + cacheKey + " value == " + MemcachedConnector.mcc.get(cacheKey));
		
		if(MemcachedConnector.mcc.get(cacheKey) != null)
		{
			String str = (String) MemcachedConnector.mcc.get(cacheKey);
			
			JSONObject jsonObject = JSONObject.fromObject(str);
			
			kadSalseObj = (KadSalse)JSONObject.toBean(jsonObject, KadSalse.class);
		}
		
		return kadSalseObj;
	}
	
	// 计算今日累计
	public float getTodayToatal(Connection connection, String platformCode, float [] YArr)
	{
		System.out.println("查询今日累计 ==== " + platformCode);
		
		String sql = " select "
		        + " to_char(a.CreateDate,'yyyy-mm-dd hh24')  time, "   
		        + " sum(a.Netamt)   sales1 "
		        + " from OM.OM_Order a "
		        + " where  "
		        + " to_char(a.CreateDate, 'yyyy-mm-dd') = to_char(SYSDATE, 'yyyy-mm-dd') "
		        + " and a.orderstatus not in (0,10,12) "
		        + " %s "
		        + " group by to_char(a.CreateDate,'yyyy-mm-dd hh24') "
		        + " order by to_char(a.CreateDate,'yyyy-mm-dd hh24') ";
		
		String plat_str = "";
		if ( !platformCode.equals(""))
		{
			plat_str = "and a.OrderSource in( %s )";
			plat_str = String.format(plat_str, platformCode);
		}
		
		sql = String.format(sql, plat_str);
		
		float f = 0;
		
		try
		{
			  PreparedStatement pState = connection.prepareStatement(sql);
			  ResultSet rs = pState.executeQuery();	
			  while(rs.next())
			  {
				  f += rs.getFloat(2);
				  String date_str = rs.getString(1).toString();
				  
				  int clock = Integer.parseInt(date_str.split(" ")[1]);
				  YArr[clock] = rs.getFloat(2);
			  }
			  
			  pState.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
		
		return f;
	}
	
	
	// 计算昨日同期累计
	public float getYesTheTimeTotal(Connection connection, String platformCode)
	{
		System.out.println("查询昨日同期累计 ==== " + platformCode);
		
		String sql = " select "
				+ " to_char(a.CreateDate,'yyyy-mm-dd hh24')  time, "
				+ " sum(a.Netamt)  sales1 "
				+ " from OM.OM_Order a "
				+ " where  "
	            + " to_char(a.CreateDate, 'yyyy-mm-dd hh24') >= to_char(sysdate - 2,'yyyy-mm-dd')||' 24:00:00' " 
	            + " and "
	            + " to_char(a.CreateDate, 'yyyy-mm-dd hh24') < to_char(sysdate - 1,'yyyy-mm-dd hh24') "
	            + " and a.orderstatus not in (0,10,12) "
	            + " %s "
	            + " group by to_char(a.CreateDate,'yyyy-mm-dd hh24') "
	            + " order by to_char(a.CreateDate,'yyyy-mm-dd hh24') ";	
			
		String plat_str = "";
		if ( !platformCode.equals(""))
		{
			plat_str = "and a.OrderSource in( %s )";
			plat_str = String.format(plat_str, platformCode);
		}
		
		sql = String.format(sql, plat_str);
		
		float y = 0;
		
		try
		{
			PreparedStatement pState = connection.prepareStatement(sql);
			ResultSet rs = pState.executeQuery();
			
			while(rs.next())
			{
				y += rs.getFloat(2);
			}
			
			pState.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return 0;
		}
			
		return y;
	}
	
	// 计算昨日累计
	public float getYestodayToatal(Connection connection, String platformCode, float [] YArr)
	{
		System.out.println("查询昨日累计 ==== " + platformCode);
		
		String sql =" select " 
				 + " to_char(a.CreateDate,'yyyy-mm-dd hh24')  time, " 
				 + " sum(a.Netamt)   sales1 " 
			     + " from OM.OM_Order a " 
			     + " where " 
			     + " to_char(a.CreateDate, 'yyyy-mm-dd hh24') >= to_char(sysdate - 2,'yyyy-mm-dd')||' 24:00:00' "  
			     + " and to_char(a.CreateDate, 'yyyy-mm-dd hh24') < to_char(sysdate - 1,'yyyy-mm-dd')||' 24:00:00' " 
			     + " and a.orderstatus not in (0,10,12) " 
			     + " %s "
			     + " group by to_char(a.CreateDate,'yyyy-mm-dd hh24') " 
			     + " order by to_char(a.CreateDate,'yyyy-mm-dd hh24') ";
		
		String plat_str = "";
		if ( !platformCode.equals(""))
		{
			plat_str = "and a.OrderSource in( %s )";
			plat_str = String.format(plat_str, platformCode);
		}
		
		sql = String.format(sql, plat_str);
		
		float f = 0;
		
		try
		{
			  PreparedStatement pState = connection.prepareStatement(sql);
			  ResultSet rs = pState.executeQuery();	
			  while(rs.next())
			  {
				  f += rs.getFloat(2);
				  String date_str = rs.getString(1).toString();
				  
				  int clock = Integer.parseInt(date_str.split(" ")[1]);
				  YArr[clock] = rs.getFloat(2);
			  }
			  
			  pState.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
		
		return f;
	}
	
/////////////////////////////////////////////////////
	
	public List getproduce(String plat,String start,String end,Long id,User user){
		DecimalFormat df = new DecimalFormat("#.00");
		Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  String name ="";
		  String pic ="";
		  PreparedStatement pState;
		  List list = new ArrayList();
		try {
			String sql = "select GOODSID ,GOODSNAME,sum(ORDER_NUM),SUM(SALES_AMOUNT) ,SUM(COST_AMOUNT),SUM(GROSS_PROFIT),NODENAME,MANAGER,INTRODUCE,SUM(UV),SUM(RETURN_COUNT),SUM(RETURN_AMOUNT),ORIGIN,ORIGINNAME,PURCHASINGLEADER,sum(ORDER_NUM)"+ 
					" from rpt_product_performance_day where DATA_DATE BETWEEN TO_DATE('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('"+end+"', 'yyyy-mm-dd hh24:mi:ss') and ORIGIN= " +plat;
					//sql = sql +" AND MANAGER = ? ";
					if(user.getLev().equals("2")){
						sql = sql+" AND NODENAME = '"+user.getNode()+"' ";
					}
					if(user.getLev().equals("1")){
						sql = sql+" AND MANAGER= '"+user.getRealname()+"' ";
					}
					if(id!=0){
						sql = sql +" AND GOODSID = "+id;
					}					
					sql = sql +" GROUP BY GOODSID ,GOODSNAME ,NODENAME,MANAGER,INTRODUCE,ORIGIN,ORIGINNAME,PURCHASINGLEADER"+
					" ORDER BY sum(SALES_COUNT) DESC";
					System.out.println("tt"+sql);
			pState = createConnection().prepareStatement(sql);
			//pState.setString(1, "蒋鹤丁");
			  rs = pState.executeQuery();
			  System.out.println("aa"+sql);			 
			while(rs.next()){
				Map map = new HashMap();				
				map.put("id",rs.getLong(1));
				map.put("name",rs.getString(2));
				map.put("salescount",rs.getLong(16));
				map.put("salestatol",df.format(rs.getFloat(4)));
				map.put("pur",df.format(rs.getFloat(5)));
				map.put("profit",df.format(rs.getFloat(6)));
				if(rs.getFloat(4)==0){
					map.put("profitrate",0);
				}else{
					map.put("profitrate",df.format(rs.getFloat(6)/rs.getFloat(4)*100));
				}
				
				map.put("node",rs.getString(7));
				map.put("manager",rs.getString(8));
				map.put("purmanager",rs.getString(9));
				map.put("uv",rs.getLong(10));
				map.put("trans",rs.getLong(10));
				map.put("returncount",rs.getLong(11));
				map.put("returntatol",rs.getFloat(12));
				map.put("intro",rs.getString(15));
				if(rs.getFloat(4)==0){
					map.put("ave", 0);

				}else{
					map.put("ave", df.format(rs.getFloat(4)/rs.getLong(3)));
				}
				
				if(rs.getLong(3)==0){
					map.put("returnrate",0);

				}else{
					map.put("returnrate",rs.getLong(11)/rs.getLong(3));
				}
				list.add(map);
			}
			 
			 /*  if(connection!=null){
				   connection.close();
			   }*/
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return list;
		
	}
	
	public List getproduceById(long id){
		Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  String name ="";
		  String pic ="";
		  PreparedStatement pState;
		  List list = new ArrayList();
		try {
			String sql ="select * from  rpt_purchase_performance where GOODSID = "+id;
			pState = createConnection().prepareStatement(sql);
			  rs = pState.executeQuery();
			  //System.out.println("aa"+rs.getString(1));			 
			if(rs.next()){
				Map map = new HashMap();				
				map.put("id",rs.getLong(2));
				map.put("name",rs.getString(3));
				map.put("firsttime",rs.getString(4).substring(0,10));
				map.put("lasttime",rs.getString(5).substring(0, 10));
				map.put("purnum",rs.getLong(6));
				map.put("period",rs.getLong(7));
				
				
				map.put("cnt",rs.getLong(8));
				map.put("amount",rs.getFloat(9));
				map.put("totalcnt",rs.getLong(10));
				map.put("totalamount",rs.getLong(11));
				map.put("shelfcount",rs.getLong(12));
				map.put("unshelfcount",rs.getLong(13));
				map.put("stockcount",rs.getFloat(14));
				
				list.add(map);
			}
			 
			 /*  if(connection!=null){
				   connection.close();
			   }*/
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return list;
		
	}
	
	public Map getstock(long id){
		Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  String name ="";
		  String pic ="";
		  PreparedStatement pState;
		  List datelist = new ArrayList();
		  List countlist = new ArrayList();
		  List pricelist = new ArrayList();
		try {
			String sql ="select SUBSTR(TO_CHAR(CREATETIME,'yyyy-mm-dd hh24:mi:ss'),0,7), sum(STORECOUNT) ,max(UNITPRICE)  from ods_trd_kad_goods_stockitem  where GOODSID = " +id+
					" and STORECOUNT >0 and UNITPRICE > 0 GROUP BY SUBSTR(TO_CHAR(CREATETIME,'yyyy-mm-dd hh24:mi:ss'),0,7) ORDER BY SUBSTR(TO_CHAR(CREATETIME,'yyyy-mm-dd hh24:mi:ss'),0,7) ";
			pState = createConnection().prepareStatement(sql);
			  rs = pState.executeQuery();
			  //System.out.println("aa"+rs.getString(1));			 
			while(rs.next()){
				datelist.add(rs.getString(1));
				countlist.add(rs.getLong(2));
				pricelist.add(rs.getFloat(3));
			}
			 
			 /*  if(connection!=null){
				   connection.close();
			   }*/
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		Map map = new HashMap();
		if(datelist.size()>15){
			int i = datelist.size();
			map.put("date", datelist.subList(i-15, i));
			map.put("count", countlist.subList(i-15, i));
			map.put("price", pricelist.subList(i-15, i));
		}else{
			map.put("date", datelist);
			map.put("count", countlist);
			map.put("price", pricelist);
		}
		
		return map;
		
	}

	public List getpurtable(Long id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  String name ="";
		  String pic ="";
		  PreparedStatement pState;
		  List list = new ArrayList();
		try {
			String sql ="select GOODSID ,CREATETIME, STORECOUNT ,UNITPRICE ,STORECOUNT*UNITPRICE from ods_trd_kad_goods_stockitem  where GOODSID = " +id+
					" and STORECOUNT >0 and UNITPRICE > 0";

			pState = createConnection().prepareStatement(sql);
			  rs = pState.executeQuery();
			  //System.out.println("aa"+rs.getString(1));			 
			while(rs.next()){
				Map map = new HashMap();				
			
				map.put("time",rs.getString(2).substring(0,10));
				map.put("store",rs.getLong(3));
				map.put("price",rs.getFloat(4));
				map.put("tatolprice",rs.getFloat(5));
				
				list.add(map);
			}
			 
			 /*  if(connection!=null){
				   connection.close();
			   }*/
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return list;
		
	}

	
	public Map getTrafficdate(Long id,String start,String end,String plat) throws ParseException{
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat df = new DecimalFormat(".00");
		Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  Map map = new HashMap();
		  String name ="";
		  String pic ="";
		  PreparedStatement pState;
		  List channel1list = new ArrayList();
		  List datelist = new ArrayList();
		  List uvlist = new ArrayList();
		  List tablist = new ArrayList();
		try {
			String sql ="select ORIGIN ,sum(UV),sum(UV_EXIT),sum(UV_LANDING),sum(UV_JUMPOFF),sum(LANDING_VISITS_NEW),sum(LANDING_VISITS_OLD),sum(ADD_TO_CART),sum(CLINCH_ORDER_NUM),sum(P_VISITTIME),sum(TQ_NUM),sum(CALL_NUM),GOODSID,GOODSNAME,sum(CLINCH_ORDER_NUM) " +
					"from rpt_uv_performance_details where DATA_DATE BETWEEN TO_DATE('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('"+end+"', 'yyyy-mm-dd hh24:mi:ss') AND GOODSID = " + id+
					" and ORIGIN = " +plat +" GROUP BY ORIGIN,GOODSID,GOODSNAME";
			pState = createConnection().prepareStatement(sql);
			System.out.println("bbb"+sql);
			  rs = pState.executeQuery();
			 // System.out.println("aa"+rs.getString(1));			 
			if(rs.next()){
				map.put("uv", rs.getLong(2));
				map.put("exituv", rs.getLong(3));
				map.put("landuv", rs.getLong(4));
				map.put("jumpuv", rs.getLong(5));
				map.put("new1",rs.getLong(6));
				map.put("old", rs.getLong(7));
				map.put("cart",rs.getLong(8));
				map.put("sucorder", rs.getLong(9));
				map.put("staytime", rs.getLong(10));
				map.put("tq", rs.getLong(11));
				map.put("call", rs.getLong(12));
				Float rate = rs.getFloat(5)/ rs.getFloat(2)*100;
				Float orderrate = rs.getFloat(9)/ rs.getFloat(2)*100;
				map.put("jumprate",df.format(rate));
				map.put("sucrate",df.format(orderrate));
				map.put("gid", rs.getString(13));
				map.put("gname", rs.getString(14));
				map.put("clinordercount", rs.getLong(15));
				System.out.println("aa"+map.get("sucrate").toString());
			}
			Date d = new Date();
			d.setDate(format.parse(end).getDate()-format.parse(end).getDate() - format.parse(start).getDate()); 
			String start2 = format.format(d);
			
			sql ="select ORIGIN ,sum(UV),sum(UV_EXIT),sum(UV_LANDING),sum(UV_JUMPOFF),sum(LANDING_VISITS_NEW),sum(LANDING_VISITS_OLD),sum(ADD_TO_CART),sum(CLINCH_ORDER_NUM),sum(P_VISITTIME),sum(TQ_NUM),sum(CALL_NUM),GOODSID,GOODSNAME,sum(CLINCH_ORDER_NUM) " +
					"from rpt_uv_performance_details where DATA_DATE BETWEEN TO_DATE('"+start2+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('"+start+"', 'yyyy-mm-dd hh24:mi:ss') AND GOODSID = "
			+id+" and ORIGIN = "+plat+" GROUP BY ORIGIN,GOODSID,GOODSNAME";
			pState = createConnection().prepareStatement(sql);
			  System.out.println("aa"+sql);		
			  rs = pState.executeQuery();
				 
			if(rs.next()){
				if(map.get("uv")==null){
					map.put("chanuv", Long.parseLong(map.get("uv")+""));
					map.put("chanexituv", Long.parseLong(map.get("exituv")+""));
					map.put("chanlanduv", Long.parseLong(map.get("landuv")+""));
					map.put("channew", Long.parseLong(map.get("new1")+""));
					map.put("chanold", Long.parseLong(map.get("old")+""));
					map.put("chanjumpuv",Long.parseLong(map.get("jumpuv")+""));			
					map.put("chancart",Long.parseLong(map.get("cart")+""));
					map.put("chansucorder", Long.parseLong(map.get("sucorder")+""));
					map.put("chanstaytime",Long.parseLong(map.get("staytime")+""));
					map.put("chantq", Long.parseLong(map.get("tq")+""));
					map.put("chancall", Long.parseLong(map.get("call")+""));
					Float chanrate = rs.getFloat(5)/ rs.getFloat(2)*100;
					Float chanorderrate = rs.getFloat(9)/ rs.getFloat(2)*100;
					map.put("chanjumprate",map.get("jumprate").toString());
					map.put("chansucrate",map.get("sucrate").toString());
					map.put("chanclinordercount", map.get("clinordercount"));
					System.out.println("bb"+map.get("sucrate").toString());
				}else{
					map.put("chanuv", Long.parseLong(map.get("uv")+"")-rs.getLong(2));
					map.put("chanexituv", Long.parseLong(map.get("exituv")+"")-rs.getLong(3));
					map.put("chanlanduv", Long.parseLong(map.get("landuv")+"")-rs.getLong(4));
					map.put("chanjumpuv",Long.parseLong(map.get("jumpuv")+"")- rs.getLong(5));	
					map.put("channew", Long.parseLong(map.get("new1")+"")-rs.getLong(6));
					map.put("chanold",Long.parseLong(map.get("old")+"")- rs.getLong(7));	
					map.put("chancart",Long.parseLong(map.get("cart")+"")-rs.getLong(8));
					map.put("chansucorder", Long.parseLong(map.get("sucorder")+"")-rs.getLong(9));
					map.put("chanstaytime",Long.parseLong(map.get("staytime")+"")- rs.getLong(10));
					map.put("chantq", Long.parseLong(map.get("tq")+"")-rs.getLong(11));
					map.put("chancall", Long.parseLong(map.get("call")+"")-rs.getLong(12));
					Float chanrate = rs.getFloat(5)/ rs.getFloat(2)*100;
					Float chanorderrate = rs.getFloat(9)/ rs.getFloat(2)*100;
					map.put("chanjumprate",Float.parseFloat(map.get("jumprate").toString())-Float.parseFloat(df.format(chanrate)));
					map.put("chansucrate",Float.parseFloat(map.get("sucrate").toString())-Float.parseFloat(df.format(chanorderrate)));
					map.put("chanclinordercount", Long.parseLong(map.get("clinordercount")+"")-rs.getLong(15));
					System.out.println("cc"+chanorderrate);
				}
			}else{
				map.put("chanuv", Long.parseLong(map.get("uv")+""));
				map.put("chanexituv", Long.parseLong(map.get("exituv")+""));
				map.put("chanlanduv", Long.parseLong(map.get("landuv")+""));
				map.put("channew", Long.parseLong(map.get("new1")+""));
				map.put("chanold", Long.parseLong(map.get("old")+""));
				map.put("chanjumpuv",Long.parseLong(map.get("jumpuv")+""));			
				map.put("chancart",Long.parseLong(map.get("cart")+""));
				map.put("chansucorder", Long.parseLong(map.get("sucorder")+""));
				map.put("chanstaytime",Long.parseLong(map.get("staytime")+""));
				map.put("chantq", Long.parseLong(map.get("tq")+""));
				map.put("chancall", Long.parseLong(map.get("call")+""));
				//Float chanrate = rs.getFloat(5)/ rs.getFloat(2)*100;
				//Float chanorderrate = rs.getFloat(9)/ rs.getFloat(2)*100;
				map.put("chanjumprate",map.get("jumprate").toString());
				map.put("chansucrate",map.get("sucrate").toString());
				map.put("chanclinordercount", map.get("clinordercount"));
			}
			
			sql ="select CHANNEL1 ,sum(UV) from rpt_uv_performance_details where DATA_DATE BETWEEN TO_DATE('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('"+end+"', 'yyyy-mm-dd hh24:mi:ss') AND GOODSID =" 
			+id +" and ORIGIN = "+plat+" GROUP BY CHANNEL1";
			pState = createConnection().prepareStatement(sql);
			  rs = pState.executeQuery();
			 while(rs.next()){
				 List list2 = new ArrayList();
				 Map map2 = new HashMap();
				 list2.add(rs.getString(1));
				 list2.add( rs.getLong(2));
				 map2.put(rs.getString(1), rs.getLong(2));
				 channel1list.add(list2);
			 }
							
			 
					 sql ="select CHANNEL1 ,sum(UV),sum(UV_EXIT),sum(uv_landing),sum(UV_JUMPOFF) from rpt_uv_performance_details where DATA_DATE BETWEEN TO_DATE('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('"+end+"', 'yyyy-mm-dd hh24:mi:ss') AND GOODSID = " + 
					 		id +" and ORIGIN = "+plat+" GROUP BY CHANNEL1";
						pState = createConnection().prepareStatement(sql);
						  rs = pState.executeQuery();
						 while(rs.next()){
							 Map map3 = new HashMap();
							 map3.put("channel1", rs.getString(1));
							// map3.put("channel2", rs.getString(2));
							 map3.put("uv", rs.getLong(2));
							 map3.put("exituv", rs.getLong(3));
							 map3.put("landuv", rs.getLong(4));
							 map3.put("jumpuv", rs.getLong(5));
							 if(rs.getFloat(5)==0){
								 map3.put("jumprate", 0);
							 }else{
								 Float rate =rs.getFloat(5)/rs.getFloat(2)*100;
								 map3.put("jumprate", df.format(rate));
							 }
							map.put(rs.getString(1), new ArrayList());
							 tablist.add(map3);
						 }
			
						 sql ="select CHANNEL1 ,CHANNEL2 ,sum(UV),sum(UV_EXIT),sum(uv_landing),sum(UV_JUMPOFF) from " +
						 		"rpt_uv_performance_details where DATA_DATE BETWEEN TO_DATE('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('"+end+"', 'yyyy-mm-dd hh24:mi:ss') AND GOODSID = "+
								 id+" and ORIGIN ="+plat+" GROUP BY CHANNEL1,CHANNEL2";
							pState = createConnection().prepareStatement(sql);
							  rs = pState.executeQuery();
							 while(rs.next()){
								 Map map7 = new HashMap();
								 map7.put("channel1", rs.getString(1));
								 map7.put("channel2", rs.getString(2));
								 map7.put("uv", rs.getLong(3));
								 map7.put("exituv", rs.getLong(4));
								 map7.put("landuv", rs.getLong(5));
								 map7.put("jumpuv", rs.getLong(6));
								 if(rs.getFloat(6)==0){
									 map7.put("jumprate", 0);
								 }else{
									 Float rate =rs.getFloat(6)/rs.getFloat(3)*100;
									 map7.put("jumprate", df.format(rate));
								 }
								
								 List channellist = (List) map.get(rs.getString(1));
								 channellist.add(map7);
								 map.put(rs.getString(1),channellist);
							 }
				
				 sql ="select ORIGIN ,SUBSTR(TO_CHAR(DATA_DATE,'yyyy-mm-dd hh24:mi:ss'),0,10),sum(UV) "+ 
							" from rpt_uv_performance_details "+
							" where GOODSID = "+id+" and ORIGIN = "+plat+" and DATA_DATE BETWEEN TO_DATE('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('"+end+"', 'yyyy-mm-dd hh24:mi:ss') "+
							" GROUP BY ORIGIN,SUBSTR(TO_CHAR(DATA_DATE,'yyyy-mm-dd hh24:mi:ss'),0,10) ORDER BY SUBSTR(TO_CHAR(DATA_DATE,'yyyy-mm-dd hh24:mi:ss'),0,10)";
						pState = createConnection().prepareStatement(sql);
						  rs = pState.executeQuery();
						 while(rs.next()){
							 datelist.add(rs.getString(2));
							 uvlist.add(rs.getLong(3));
						 }
				 
				/* sql ="select CHANNEL1 ,CHANNEL2 ,sum(UV),sum(UV_EXIT),sum(uv_landing),sum(UV_JUMPOFF) from BITEST.rpt_uv_performance_details where GOODSID = 47488 and ORIGIN =12 GROUP BY CHANNEL1,CHANNEL2";
					pState = createConnection().prepareStatement(sql);
					  rs = pState.executeQuery();
					 while(rs.next()){
						 Map map3 = new HashMap();
						 map3.put("channel1", rs.getString(1));
						 map3.put("channel2", rs.getString(2));
						 map3.put("uv", rs.getLong(3));
						 map3.put("exituv", rs.getLong(4));
						 map3.put("landuv", rs.getLong(5));
						 map3.put("jumpuv", rs.getLong(6));
						 if(rs.getFloat(6)==0){
							 map3.put("jumprate", 0);
						 }else{
							 Float rate =rs.getFloat(6)/rs.getFloat(3)*100;
							 map3.put("jumprate", df.format(rate));
						 }
						
						
						 tablist.add(map3);
					 }*/
				
			 /*  if(connection!=null){
				   connection.close();
			   }*/
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	
		map.put("channel1", channel1list);
		map.put("date", datelist);
		map.put("uvlist", uvlist);
		//System.out.println("cccccc"+tablist.size());
		map.put("tab", tablist);
		
		return map;
		
	}
	
	
	public Map getsaledata(Long id,String start,String end,String plat){
		DecimalFormat df = new DecimalFormat("#.00");
		Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date d = new Date();
			try {
				d.setDate(format.parse(end).getDate()-format.parse(end).getDate() - format.parse(start).getDate());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			String start2 = format.format(d);
		  Map map = new HashMap();
		  String name ="";
		  String pic ="";
		  PreparedStatement pState;
		  List channel1list = new ArrayList();
		  List datelist = new ArrayList();
		  List provincelist = new ArrayList();
		  List sourcelist = new ArrayList();
		  List uvlist = new ArrayList();
		  List grosslist = new ArrayList();
		  List tablist = new ArrayList();
		try {
			String sql ="select sum(SALES_COUNT),sum(SALES_AMOUNT),sum(COST_AMOUNT),count(*),sum(RETURN_COUNT)," +
					"sum(NEW_ORDERS_NUM),sum(NEW_SALES_COUNT),sum(NEW_SALES_AMOUNT),sum(NEW_GROSS_PROFIT),sum(OLD_ORDERS_NUM),sum(OLD_SALES_COUNT),sum(OLD_SALES_AMOUNT),sum(OLD_GROSS_PROFIT),sum(ORDER_NUM) ,max(PRICE_MEMBER) " +
					"from rpt_product_performance_day where GOODSID = " +
			id+		" and ORIGIN = "+plat+" and DATA_DATE BETWEEN TO_DATE('" +start+
					"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('" +end+
					"', 'yyyy-mm-dd hh24:mi:ss')";
			System.out.println("aa"+sql);	 
			pState = createConnection().prepareStatement(sql);
			  rs = pState.executeQuery();
			  
			if(rs.next()){
				map.put("salecount2", rs.getLong(1));
				map.put("sales2", rs.getLong(2));
				map.put("profit2", rs.getLong(2)-rs.getLong(3));
								
				map.put("return2",rs.getLong(5));
				if(rs.getFloat(5)==0){
					map.put("returnrate2",0);
				}else{
					Float rate = rs.getFloat(5)/ rs.getFloat(2)*100;
					map.put("returnrate2",df.format(rate));
				}
				System.out.println(rs.getFloat(8)+"---"+rs.getLong(14)+"cc");
				map.put("newavr",rs.getFloat(8)/rs.getLong(7));
				map.put("newordercount",rs.getLong(6));
				map.put("newcount",rs.getLong(7));
				map.put("newre",rs.getLong(9));
				if(rs.getLong(11)==0){
					map.put("oldavr",rs.getLong(12));
				}else{
					map.put("oldavr",rs.getLong(12)/rs.getLong(11));
				}
				map.put("oldordercount",rs.getLong(10));
				map.put("oldcount",rs.getLong(11));
				map.put("oldre",rs.getLong(13));
				map.put("ordercount", rs.getLong(14));
				map.put("newprice",rs.getString(15));
				map.put("oldprice",rs.getString(15));
			}
			
			sql ="select sum(SALES_COUNT),sum(SALES_AMOUNT),sum(COST_AMOUNT),count(*),sum(RETURN_COUNT)," +
					"sum(NEW_ORDERS_NUM),sum(NEW_SALES_COUNT),sum(NEW_SALES_AMOUNT),sum(NEW_GROSS_PROFIT),sum(OLD_ORDERS_NUM),sum(OLD_SALES_COUNT),sum(OLD_SALES_AMOUNT),sum(OLD_GROSS_PROFIT),sum(ORDER_NUM) " +
					"from rpt_product_performance_day where GOODSID = " +
			id+		" and ORIGIN= "+plat+" and DATA_DATE BETWEEN TO_DATE('" +start2+
					"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('" +start+
					"', 'yyyy-mm-dd hh24:mi:ss')";
			pState = createConnection().prepareStatement(sql);
			  rs = pState.executeQuery();
			 // System.out.println("aa"+rs.getString(1));			 
			if(rs.next()){
				map.put("chansalecount2", Long.parseLong(map.get("salecount2").toString()) -rs.getLong(1));
				map.put("chansales2", Long.parseLong(map.get("salecount2").toString()) -rs.getLong(2));
				map.put("chanprofit2", Long.parseLong(map.get("profit2").toString()) -(rs.getLong(2)-rs.getLong(3)));
								
				map.put("chanreturn2",Long.parseLong(map.get("return2").toString()) -rs.getLong(5));
				if(rs.getFloat(5)==0){
					map.put("chanreturnrate2",map.get("returnrate2"));
				}else{
					Float rate =Float.parseFloat(map.get("returnrate2").toString())- rs.getFloat(5)/ rs.getFloat(2)*100;
					map.put("chanreturnrate2",df.format(rate));
				}
				if(rs.getLong(7)==0){
					map.put("channewavr",map.get("newavr"));
				}else{
					map.put("channewavr",df.format(Float.parseFloat(map.get("newavr").toString()) - rs.getFloat(8)/rs.getLong(7)));
				}
				map.put("channewordercount",Long.parseLong(map.get("newordercount").toString()) - rs.getLong(6));
				map.put("channewcount",Long.parseLong(map.get("newcount").toString()) -rs.getLong(7));
				map.put("channewre",Long.parseLong(map.get("newre").toString()) -rs.getLong(9));
				if(rs.getLong(11)==0){
					map.put("chanoldavr", map.get("oldavr"));
				}else{
					map.put("chanoldavr",df.format(Float.parseFloat(map.get("oldavr").toString()) -(rs.getFloat(12)/rs.getLong(11))));
				}
				 //System.out.println(("bb"+rs.getFloat(12)+"----"+rs.getLong(11)));
				  //System.out.println(Float.parseFloat(map.get("oldavr").toString()) -(rs.getFloat(12)/rs.getLong(11)));			 

				map.put("chanoldordercount",Long.parseLong(map.get("oldordercount").toString()) -rs.getLong(10));
				map.put("chanoldcount",Long.parseLong(map.get("oldcount").toString()) -rs.getLong(11));
				map.put("chanoldre",Long.parseLong(map.get("oldre").toString()) -rs.getLong(13));
				map.put("chanordercount",Long.parseLong(map.get("ordercount").toString()) -rs.getLong(14));
				
			}else{
				map.put("channewavr",Long.parseLong(map.get("salecount2").toString()) );
				map.put("channewordercount",Long.parseLong(map.get("newordercount").toString()) );
				map.put("channewcount",Long.parseLong(map.get("newcount").toString()) );
				map.put("channewre",Long.parseLong(map.get("newre").toString()) );
				map.put("chanoldavr",Long.parseLong(map.get("oldavr").toString()) );
				map.put("chanoldordercount",Long.parseLong(map.get("oldordercount").toString()) );
				map.put("chanoldcount",Long.parseLong(map.get("oldcount").toString()) );
				map.put("chanoldre",Long.parseLong(map.get("oldre").toString()));
				map.put("chanordercount",Long.parseLong(map.get("ordercount").toString()));
			}
			
			sql ="select BUY_ONCE_NUM ,BUY_TWICE_NUM,BUY_THRICE_NUM,BUT_THRICE_UP_NUM,BACK_PURCHASE_RATE from rpt_sales_performance where GOODSID ="+id;
					pState = createConnection().prepareStatement(sql);
					  rs = pState.executeQuery();
					if(rs.next()){
						map.put("cuscount", rs.getLong(1)+rs.getLong(2)+rs.getLong(3)+rs.getLong(4));
						map.put("onecount", rs.getLong(1));
						map.put("twicecount", rs.getLong(2));
						map.put("recount", rs.getLong(3));
										
						map.put("recountup",rs.getLong(4));
						map.put("recountrate",rs.getLong(5));
						
					}
			
			//System.out.println(map+"bb");
			sql ="select CHANNEL1,sum(sales_quantity),count(*) from dw_kad_orderitem where goodsid = " +
				id+	" and ORDERTIME BETWEEN TO_DATE('" +start+
						"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('" +end+
						"', 'yyyy-mm-dd hh24:mi:ss') group by CHANNEL1";
			System.out.println(sql);
			pState = createConnection().prepareStatement(sql);
			  rs = pState.executeQuery();
			  int ordercount = 0;
			 while(rs.next()){
				 List list2 = new ArrayList();
				 Map map2 = new HashMap();
				 if(rs.getString(1)==null||rs.getString(1).equals("")||rs.getString(1).equals("null")){
					 list2.add("other");
				 }else{
					 list2.add(rs.getString(1));
				 }
				
				 list2.add( rs.getLong(2));
				 map2.put(rs.getString(1), rs.getLong(2));
				 channel1list.add(list2);
				ordercount=ordercount+rs.getInt(3);
			 }
			// map.put("ordercount", ordercount);
			
			/* sql ="select sum(uv) from BITEST.rpt_product_performance_day where GOODSID = " + id +
			 		" and DATA_DATE BETWEEN TO_DATE('" +start+
			 		"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('" +end+
			 		"', 'yyyy-mm-dd hh24:mi:ss')";
				pState = createConnection().prepareStatement(sql);
				  rs = pState.executeQuery();
				 if(rs.next()){
					 Float rate = ordercount/ rs.getFloat(1)*100; 					 
						map.put("uvrate2",df.format(rate));
				 }*/
			 
			/* sql =" select PROVINCE_NAME,count(*) from BITEST.dw_kad_orderitem where goodsid = " + id +
			 		" and ORDERTIME BETWEEN TO_DATE('" +start+
			 		"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('" +end+
			 		"', 'yyyy-mm-dd hh24:mi:ss') group by PROVINCE_NAME";
				pState = createConnection().prepareStatement(sql);
				  rs = pState.executeQuery();
				 while(rs.next()){
					 List list3 = new ArrayList();
					 Map map3 = new HashMap();
					 if(rs.getString(1)==null||rs.getString(1).equals("")||rs.getString(1).equals("null")){
						 list3.add("other");
					 }else{
						 list3.add(rs.getString(1));
					 }
					
					 list3.add( rs.getLong(2));
					 map3.put(rs.getString(1), rs.getLong(2));
					 provincelist.add(list3);
				 }*/
				
				/* sql =" select ORIGIN,count(*) from BITEST.dw_kad_orderitem where goodsid = " + id +
				 		" and ORDERTIME BETWEEN TO_DATE('" +start+
				 		"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('" +end+
				 		"', 'yyyy-mm-dd hh24:mi:ss') group by ORIGIN";
					pState = createConnection().prepareStatement(sql);
					  rs = pState.executeQuery();
					 while(rs.next()){
						 List list4 = new ArrayList();
						 Map map4 = new HashMap();
						 list4.add(rs.getString(1));
						 list4.add( rs.getLong(2));
						 map4.put(rs.getString(1), rs.getLong(2));
						 sourcelist.add(list4);
					 }*/
					 
						 sql ="select SUBSTR(TO_CHAR(DATA_DATE, 'yyyy-mm-dd hh24:mi:ss'),0,10),sum(SALES_COUNT), sum(GROSS_PROFIT) from rpt_product_performance_day where goodsid = " + id +
						 		"  and DATA_DATE BETWEEN TO_DATE('" +start+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('" +end+
						 		"', 'yyyy-mm-dd hh24:mi:ss') group by SUBSTR(TO_CHAR(DATA_DATE, 'yyyy-mm-dd hh24:mi:ss'),0,10) ORDER BY SUBSTR(TO_CHAR(DATA_DATE, 'yyyy-mm-dd hh24:mi:ss'),0,10)";
							pState = createConnection().prepareStatement(sql);
							  rs = pState.executeQuery();
							 while(rs.next()){
								 datelist.add(rs.getString(1));
								 uvlist.add(rs.getLong(2));
								 grosslist.add(rs.getFloat(3));
							 }
						 
						 sql =" select CHANNEL1,channel2,sum(sales_quantity) ,sum((sales_price-goods_Concession)*sales_quantity-Order_Concession+goods_Freight), sum(sales_quantity*last_pur_price),sum(Return_quantity) " +
						 		"from dw_kad_orderitem where goodsid = " +
						 		id + " and ORDERTIME BETWEEN TO_DATE('" +start+
						 				"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('" +end+
						 				"', 'yyyy-mm-dd hh24:mi:ss') group by CHANNEL1,CHANNEL2";
							pState = createConnection().prepareStatement(sql);
							  rs = pState.executeQuery();
							 while(rs.next()){
								 Map map3 = new HashMap();
								 if( rs.getString(1)==null){
									 map3.put("channel1","other");
								 }else{
									 map3.put("channel1", rs.getString(1));
								 }
								 if( rs.getString(2)==null){
									 map3.put("channel2","other");
								 }else{
									 map3.put("channel2", rs.getString(1));
								 }
								
								 map3.put("uv", rs.getLong(3));
								 map3.put("salestotal2", df.format(rs.getFloat(4)));
								 map3.put("maoli", df.format(rs.getFloat(4)-rs.getFloat(5)));
								 map3.put("returntotal", rs.getFloat(6));
								/* if(rs.getFloat(6)==0){
									 map3.put("jumprate", 0);
								 }else{
									 Float rate =rs.getFloat(6)/rs.getFloat(3)*100;
									 map3.put("jumprate", df.format(rate));
								 }*/
								
								
								 tablist.add(map3);
							 }
			 /*  if(connection!=null){
				   connection.close();
			   }*/
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	
		map.put("channel1", channel1list);
		if(datelist.size()>15){
			int i = datelist.size();
			map.put("date", datelist.subList(i-15, i));
			map.put("uvlist", uvlist.subList(i-15, i));
			map.put("gross", grosslist.subList(i-15, i));
		}else{
			map.put("date", datelist);
			map.put("uvlist", uvlist);
			map.put("grosslist", grosslist);
		}
		
		map.put("tab", tablist);
		map.put("province",provincelist);
		map.put("source", sourcelist);
		System.out.println(map);
		return map;
		
	}
	
	public User getUser(String username,String pass){
		Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		
		  PreparedStatement pState;
		  User user = new User();
				try {
					String sql ="select * from RPT_WEBSYS_USER where USERNAME = '"+username+"' AND PASSWD = '" +pass+"'";
					pState = createConnection().prepareStatement(sql);
					 rs = pState.executeQuery();
					
					 
					 if(rs.next()){
						
						user.setUserid(rs.getString(1));
						user.setUsername(rs.getString(2));
						user.setPasswd(rs.getString(3));
						user.setRealname(rs.getString(4));
						user.setLev(rs.getString(5));
						user.setNode(rs.getString(6));
					 }else{
						 return null;
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
		return user;
		
		
	}

	public Map getsalesbynode(String plat, String start, String end,
			String node) {
		node ="男科";
		DecimalFormat df = new DecimalFormat("#.00");
		Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  String name ="";
		  String pic ="";
		  PreparedStatement pState;
		  Map map = new HashMap();		
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date d = new Date();
			try {
				d.setDate(format.parse(end).getDate()-format.parse(end).getDate() - format.parse(start).getDate());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			String start2 = format.format(d);
		 // List list = new ArrayList();
		try {
			String sql = "select sum(ORDER_NUM),SUM(SALES_AMOUNT) ,SUM(COST_AMOUNT),SUM(GROSS_PROFIT),NODENAME,SUM(SALES_COUNT),SUM(RETURN_AMOUNT),ORIGIN,ORIGINNAME"+ 
					" from rpt_product_performance_day where DATA_DATE BETWEEN TO_DATE('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('"+end+"', 'yyyy-mm-dd hh24:mi:ss') and ORIGIN= " +plat;
					sql = sql +" AND MANAGER = '蒋鹤丁'";
						sql = sql +" AND NODENAME = '男科'";
					sql = sql +" GROUP BY NODENAME,ORIGIN,ORIGINNAME"+
					" ORDER BY sum(SALES_COUNT) DESC";
			pState = createConnection().prepareStatement(sql);
			//pState.setString(1, "蒋鹤丁");
			  rs = pState.executeQuery();
			  System.out.println("aa"+sql);		
			 	
			while(rs.next()){
					
				map.put("ordercount",rs.getLong(1));
				map.put("salestatol",df.format(rs.getFloat(2)));
				map.put("pur",df.format(rs.getFloat(3)));
				map.put("profit",df.format(rs.getFloat(4)));
				if(rs.getFloat(4)==0){
					map.put("profitrate",0);
				}else{
					map.put("profitrate",df.format(rs.getFloat(4)/rs.getFloat(6)*100));
				}	
				map.put("nodename",rs.getString(5));
			
				if(rs.getFloat(4)==0){
					map.put("ave", 0);

				}else{
					map.put("ave", df.format(rs.getFloat(4)/rs.getLong(3)));
				}
				//list.add(map);
			}
			
			sql = "select sum(ORDER_NUM),SUM(SALES_AMOUNT) ,SUM(COST_AMOUNT),SUM(GROSS_PROFIT),NODENAME,SUM(SALES_COUNT),SUM(RETURN_AMOUNT),ORIGIN,ORIGINNAME"+ 
					" from rpt_product_performance_day where DATA_DATE BETWEEN TO_DATE('"+start2+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and ORIGIN= " +plat;
					sql = sql +" AND MANAGER = '蒋鹤丁'";
						sql = sql +" AND NODENAME = '男科'";
					sql = sql +" GROUP BY NODENAME,ORIGIN,ORIGINNAME"+
					" ORDER BY sum(SALES_COUNT) DESC";
			pState = createConnection().prepareStatement(sql);
			//pState.setString(1, "蒋鹤丁");
			  rs = pState.executeQuery();
			  System.out.println("aa"+sql);		
			 	
			if(rs.next()){
					
				map.put("chanordercount",Long.parseLong(map.get("ordercount")+"")-rs.getLong(1));
				map.put("chansalestatol",df.format(Float.parseFloat(map.get("salestatol")+"")-rs.getFloat(2)));
				//map.put("chanpur",df.format(rs.getFloat(3)));
				map.put("chanprofit",df.format(Float.parseFloat(map.get("profit")+"")-rs.getFloat(4)));
				if(rs.getFloat(4)==0){
					map.put("profitrate",0);
				}else{
					map.put("profitrate",df.format(rs.getFloat(4)/rs.getFloat(6)*100));
				}	
				map.put("nodename",rs.getString(5));
			
				if(rs.getFloat(4)==0){
					map.put("ave", 0);

				}else{
					map.put("ave", df.format(rs.getFloat(4)/rs.getLong(3)));
				}
				//list.add(map);
			}else{
				map.put("chanordercount",map.get("ordercount"));
				map.put("chansalestatol",map.get("salestatol"));
				//map.put("pur",df.format(rs.getFloat(3)));
				map.put("profit",map.get("profit"));
				
				//map.put("nodename",map.get);
			
				
			}
			 
			
			sql = "select * from rpt_activity_performance where BEGINTIME"+ 
					" BETWEEN TO_DATE('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and TO_DATE('"+end+"', 'yyyy-mm-dd hh24:mi:ss')";
					
			pState = createConnection().prepareStatement(sql);
			//pState.setString(1, "蒋鹤丁");
			  rs = pState.executeQuery();
			  List list  = new ArrayList();
			  while(rs.next()){
				  Map map2 = new HashMap();
				  map2.put("pid", rs.getString(1));
				  map2.put("pname",rs.getString(2));
				  map2.put("ordercount",rs.getLong(3));
				  map2.put("orderrate", rs.getFloat(4));
				  map2.put("salecount", rs.getLong(5));
				  map2.put("saletatol", rs.getFloat(6));
				  list.add(map2);
			  }
			  map.put("act", list);
			 /*  if(connection!=null){
				   connection.close();
			   }*/
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return map;
		
	}
	
	
	public Map getTmail(){
		Connection connection = null;
		  Statement statement = null;
		  ResultSet rs = null;
		
		  PreparedStatement pState;
		  Map map = new HashMap();
		  Map map1 = new HashMap();
		  Map map2 = new HashMap();
		  List list2  = new ArrayList();
		  List list3  = new ArrayList();
				try {
					String sql ="select PROID,PRONAME,KID,ORIPRICE,SALEPRICE,SUBSTR(CREATETIME, 0, 13) from RPT_TMAIL_PRICE where CREATETIME > '2015-03-10 00:26:09' and PROID = '14144742219' GROUP BY ORIPRICE,SALEPRICE,PROID,PRONAME,KID ,SUBSTR(CREATETIME, 0, 13) ORDER BY SUBSTR(CREATETIME, 0, 13)";
					pState = createConnection().prepareStatement(sql);
					 rs = pState.executeQuery();
					 while(rs.next()){
						 list3.add(rs.getString(6));
						 List list  = new ArrayList();
						 map.put("name", rs.getString(2));
						String id = rs.getString(3);
						if(map1.get(id)==null){
							list.add(Float.parseFloat(rs.getString(5)));
						}else{
							list = (List) map1.get(id);
							list.add(Float.parseFloat(rs.getString(5)));
						}
						//System.out.println("aa"+list.size());
						map1.put(id, list);
						
					 }
					 
					 sql ="select PROID,PRONAME,KID,ORIPRICE,SALEPRICE,CREATETIME from RPT_TMAIL_PRICE where CREATETIME > '2015-03-10 00:26:09' and PROID = '14144742219'  ORDER BY CREATETIME";
						pState = createConnection().prepareStatement(sql);
						 rs = pState.executeQuery();
						 String price ="";
						 Map pricemap = new HashMap();
						 while(rs.next()){
							 //System.out.println(map2);
							 String id = rs.getString(3);
							 if(map2.get(id)==null){
									map2.put(id, rs.getString(5));
								}else{
									
									if(map2.get(id).toString().equals(rs.getString(5))){
										continue;
									}else{
										price = rs.getString(6)+";"+map2.get(id).toString()+";"+rs.getString(5)+";"+id;
										list2.add(price);
										map2.put(id, rs.getString(5));
									}			
								}
						 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		map.put("data1", map1);		 
		map.put("data2", list2);
		map.put("date", list3);
		return map;
		
		
	}
	
	public Map getTraffic(){
		Connection connection = null;
		 // Statement statement = null;
		  ResultSet rs = null;
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  PreparedStatement pState = null;
		  Map map = new HashMap();
		  Map map1 = new HashMap();
		  Map map2 = new HashMap();
		  List cpc1  = new ArrayList();
		  List cps1  = new ArrayList();
		  List refer1  = new ArrayList();
		  List seo1  = new ArrayList();
		  List sms1  = new ArrayList();
		  List cpc2  = new ArrayList();
		  List cps2  = new ArrayList();
		  List refer2  = new ArrayList();
		  List seo2  = new ArrayList();
		  List sms2  = new ArrayList();
		  Date d = new Date();
		  String date = format.format(d);
		  date = date.substring(0,10);
				try {
					String sql ="select TO_CHAR(DATA_DATE, 'hh24') as houtstr,CHANNEL1,sum(PV) as PV from DW_HOUR_CHANNEL_TRAFFIC where "+
" TO_CHAR(DATA_DATE, 'yyyy-mm-dd') = '"+date+"'"+
" AND SITE_ID = 1 GROUP BY TO_CHAR(DATA_DATE, 'hh24'),CHANNEL1 ORDER BY TO_CHAR(DATA_DATE, 'hh24'),CHANNEL1";
					
					System.out.print("aa \n"+sql);
					pState = createConnection().prepareStatement(sql);
					 rs = pState.executeQuery();
					 while(rs.next()){
						if(rs.getString(2).equals("cpc")){
							while(rs.getLong(1)-cpc1.size()>0){
								cpc1.add(0);
							}
							cpc1.add(rs.getLong(3));
						}
						if(rs.getString(2).equals("cps")){
							while(rs.getLong(1)-cps1.size()>1){
								cps1.add(0);
							}
							cps1.add(rs.getLong(3));
						}
						if(rs.getString(2).equals("seo")){
						
							while(rs.getLong(1)-seo1.size()>1){
								seo1.add(0);
							}
							seo1.add(rs.getLong(3));
						}
						if(rs.getString(2).equals("sms")){
							
							if(rs.getLong(1)-sms1.size()>1){
								sms1.add(0);
							}
							sms1.add(rs.getLong(3));
						}
						if(rs.getString(2).equals("refer")){
							
							if(rs.getLong(1)-refer1.size()>1){
								refer1.add(0);
							}
							refer1.add(rs.getLong(3));
						}

					 }
					 
					  sql ="select TO_CHAR(DATA_DATE, 'hh24') as houtstr,CHANNEL1,sum(PV) as PV from DW_HOUR_CHANNEL_TRAFFIC where "+
							 " TO_CHAR(DATA_DATE, 'yyyy-mm-dd') = '"+date+"'"+
							 " AND SITE_ID = 10 GROUP BY TO_CHAR(DATA_DATE, 'hh24'),CHANNEL1 ORDER BY TO_CHAR(DATA_DATE, 'hh24'),CHANNEL1";
					 pState = createConnection().prepareStatement(sql);
						 rs = pState.executeQuery();
						 String price ="";
						 Map pricemap = new HashMap();
						 while(rs.next()){
							 if(rs.getString(2).equals("cpc")){
									
									if(rs.getLong(1)-cpc2.size()>1){
										cpc2.add(0);
									}
									cpc2.add(rs.getLong(3));
								}
								if(rs.getString(2).equals("cps")){
									
									if(rs.getLong(1)-cps2.size()>1){
										cps2.add(0);
									}
									cps2.add(rs.getLong(3));
								}
								if(rs.getString(2).equals("seo")){
									
									if(rs.getLong(1)-seo2.size()>1){
										seo2.add(0);
									}
									seo2.add(rs.getLong(3));
								}
								if(rs.getString(2).equals("sms")){
								
									if(rs.getLong(1)-sms2.size()>1){
										sms2.add(0);
									}
									sms2.add(rs.getLong(3));
								}
								if(rs.getString(2).equals("refer")){
									
									if(rs.getLong(1)-refer2.size()>1){
										refer2.add(0);
									}
									refer2.add(rs.getLong(3));
								}
								
						 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int j = 24-seo1.size();
				for(int i=0;i<j;i++){
					seo1.add(0);
					cps1.add(0);
					cpc1.add(0);
					refer1.add(0);
					sms1.add(0);
					seo2.add(0);
					cps2.add(0);
					cpc2.add(0);
					refer2.add(0);
					sms2.add(0);
				}
				List date2 = new ArrayList();
				for(int k=0;k<24;k++){
					date2.add(k);
				}
		map.put("data1", map1);	
		map.put("date", date2);	
		map.put("seo1", seo1);
		map.put("cps1", cps1);
		map.put("cpc1", cpc1);
		map.put("refer1", refer1);
		map.put("sms1", sms1);
		map.put("seo2", seo2);
		map.put("cps2", cps2);
		map.put("cpc2", cpc2);
		map.put("refer2", refer2);
		map.put("sms2", sms2);
		try {
			rs.close();
			pState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
		
		
	}
}
