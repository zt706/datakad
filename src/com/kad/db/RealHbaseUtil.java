package com.kad.db;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import com.kad.util.ValueComparator;

public class RealHbaseUtil {
	 //private static Configuration conf = null;
	 
	  private static HTablePool tablePool; 
	  static HBaseAdmin admin;
	  static Configuration HBASE_CONFIG = new Configuration(); 
	  static {		   
	      //与hbase/conf/hbase-site.xml中hbase.zookeeper.quorum配置的值相同   
	        HBASE_CONFIG.set("hbase.zookeeper.quorum", "10.0.22.103");  
	        //与hbase/conf/hbase-site.xml中hbase.zookeeper.property.clientPort配置的值相同  
	        HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");  
	        HBASE_CONFIG.set("hbase.master", "10.0.22.103:60000"); 

	      //  conf = HBaseConfiguration.create(HBASE_CONFIG);  
	    
	        tablePool = new HTablePool(HBASE_CONFIG, 1000);  
	  }
	 
	  public static Map producequery(){
		  java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			 System.out.println(df.format(new Date()));
			 HTableInterface table = tablePool.getTable("produce");
		     Scan scan = new Scan();  
		     Date start = new Date();
		     Date end = new Date();
		     start.setMinutes(start.getMinutes()-5);
		     String startkey = df.format(start);
		     String endkey = df.format(end);
		     scan.setStartRow(Bytes.toBytes(startkey));
			  scan.setStopRow(Bytes.toBytes(endkey));
			  HashMap<String,String> map = new HashMap<String,String>();  
			  try {
				ResultScanner rs = table.getScanner(scan);
				for(Result r:rs){  
				
						for(KeyValue kv:r.list()){  						
						String row = new String(kv.getRow());  
						String column = new String(kv.getQualifier(),"utf-8");  
						String value = new String(kv.getValue(),"utf-8");  	
						map.put(column, value);
					System.out.println(row+"---"+column+"---"+value);		     	             
						 }  	   				
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  return map;
			
		  }
		   
		 
	 
	  public static void main(String[] args){
		 java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		 System.out.println(df.format(new Date()));
		 HTableInterface table = tablePool.getTable("pagesource");
	     Scan scan = new Scan();  
	     Date start = new Date();
	     Date end = new Date();
	     start.setMinutes(start.getMinutes()-3);
	     String startkey = df.format(start);
	     String endkey = df.format(end);
	     scan.setStartRow(Bytes.toBytes(startkey));
		  scan.setStopRow(Bytes.toBytes(endkey));
		  HashMap<String,String> map = new HashMap<String,String>();  
		  try {
			ResultScanner rs = table.getScanner(scan);
			for(Result r:rs){  
			
					for(KeyValue kv:r.list()){  						
					String row = new String(kv.getRow());  
					String column = new String(kv.getQualifier(),"utf-8");  
					String value = new String(kv.getValue(),"utf-8");  	
					map.put(column, value);
				System.out.println(row+"---"+column+"---"+value);		     	             
					 }  	   				
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  ValueComparator bvc =  new ValueComparator(map);  
		  TreeMap treemap = new TreeMap<String, String>(bvc);
		  treemap.putAll(map);
		  System.out.println(treemap);
	  }



	public static Map ipquery(Date date) {
		 java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		 System.out.println(df.format(new Date()));
		 HTableInterface table = tablePool.getTable("visitcount");
	     Scan scan = new Scan();  
	     Date start =date;
	     Date end = date;
	     String startkey = df.format(start).substring(0,8)+"000000";
	     String endkey = df.format(end).substring(0,8)+"235959";
	     scan.setStartRow(Bytes.toBytes(startkey));
		  scan.setStopRow(Bytes.toBytes(endkey));
		  HashMap<String,String> map = new HashMap<String,String>();  
		  try {
				ResultScanner rs = table.getScanner(scan);
				for(Result r:rs){  			
						for(KeyValue kv:r.list()){  						
						String row = new String(kv.getRow());  
						String column = new String(kv.getQualifier(),"utf-8");  
						String value = new String(kv.getValue(),"utf-8"); 
						String time = row.substring(8, 10);
						if(map.get(time)==null){
							map.put(time, value);
						}else{
							int count = Integer.parseInt(map.get(time))+Integer.parseInt(value);
							map.put(time, count+"");
						}
						
						for(int i=0;i<24;i++){
							String time2 ="";
							if(i<10){
								 time2 = "0"+i;
							}else{
								 time2 = i+"";
							}
							if(map.get(time2)==null){
								map.put(time2, "0");
							}
						}
					System.out.println(row+"---"+column+"---"+value);		     	             
						 }  	   				
					}
		  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return map;
	}
	
	 public static Map visitkeyword(){
		  java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			 System.out.println(df.format(new Date()));
			 HTableInterface table = tablePool.getTable("visitkeyword");
		     Scan scan = new Scan();  
		     Date start = new Date();
		     Date end = new Date();
		     start.setMinutes(start.getMinutes()-5);
		     String startkey = df.format(start);
		     String endkey = df.format(end);
		     scan.setStartRow(Bytes.toBytes(startkey));
			  scan.setStopRow(Bytes.toBytes(endkey));
			  HashMap<String,String> map = new HashMap<String,String>();  
			  try {
				ResultScanner rs = table.getScanner(scan);
				for(Result r:rs){  
				
						for(KeyValue kv:r.list()){  						
						String row = new String(kv.getRow());  
						String column = new String(kv.getQualifier(),"utf-8");  
						String value = new String(kv.getValue(),"utf-8");  	
						map.put(URLDecoder.decode(column,"utf-8"), value);
					System.out.println(row+"---"+column+"---"+value);		     	             
						 }  	   				
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  return map;
			
		  }
	 
	 public static Map ipquery2(Date date) {
		 java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		
		 HTableInterface table = tablePool.getTable("visitcount");
	     Scan scan = new Scan();  
	     Date start= new Date();
	     start.setTime(date.getTime());
	     start.setHours(date.getHours()-2);
	    
	     String startkey = df.format(start);
	     String endkey = df.format(date);
	     System.out.println("bbb"+startkey+"---"+endkey);
	     scan.setStartRow(Bytes.toBytes(startkey));
		  scan.setStopRow(Bytes.toBytes(endkey));
		  HashMap<String,String> map = new HashMap<String,String>();  
		  try {
				ResultScanner rs = table.getScanner(scan);
				System.out.println("bbb"+rs);
				for(Result r:rs){  			
						for(KeyValue kv:r.list()){  						
						String row = new String(kv.getRow());  
						String column = new String(kv.getQualifier(),"utf-8");  
						String value = new String(kv.getValue(),"utf-8"); 
						String time = row.substring(10, 12);					
							map.put(time, value);						
						
					System.out.println(row+"---"+column+"---"+value);		     	             
						 }  	   				
					}
		  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return map;
	}
	
	 public static Map pagesource(){
		 java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		 System.out.println(df.format(new Date()));
		 HTableInterface table = tablePool.getTable("pagesource");
	     Scan scan = new Scan();  
	     Date start = new Date();
	     Date end = new Date();
	     start.setMinutes(start.getMinutes()-5);
	     String startkey = df.format(start);
	     String endkey = df.format(end);
	     scan.setStartRow(Bytes.toBytes(startkey));
		  scan.setStopRow(Bytes.toBytes(endkey));
		  HashMap<String,String> map = new HashMap<String,String>();  
		  try {
			ResultScanner rs = table.getScanner(scan);
			for(Result r:rs){  
			
					for(KeyValue kv:r.list()){  						
					String row = new String(kv.getRow());  
					String column = new String(kv.getQualifier(),"utf-8");  
					String value = new String(kv.getValue(),"utf-8");  	
					map.put(column, value);
				System.out.println(row+"---"+column+"---"+value);		     	             
					 }  	   				
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  return map;		
	 }
}
	
