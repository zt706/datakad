package com.kad.db;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

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

public class HbaseUtil {
	 //private static Configuration conf = null;
	 
	  private static HTablePool tablePool; 
	  static HBaseAdmin admin;
	  static Configuration HBASE_CONFIG = new Configuration(); 
	  static {		   
	      //与hbase/conf/hbase-site.xml中hbase.zookeeper.quorum配置的值相同   
	        HBASE_CONFIG.set("hbase.zookeeper.quorum", "192.168.7.10,192.168.7.11,192.168.7.12");  
	        //与hbase/conf/hbase-site.xml中hbase.zookeeper.property.clientPort配置的值相同  
	        HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");  
	        HBASE_CONFIG.set("hbase.master", "192.168.7.10:600000"); 

	      //  conf = HBaseConfiguration.create(HBASE_CONFIG);  
	    
	        tablePool = new HTablePool(HBASE_CONFIG, 1000);  
	  }
	 
	 public static void main(String[] args) throws IOException{
	
			/* 
			 Configuration HBASE_CONFIG = new Configuration();  
		      //与hbase/conf/hbase-site.xml中hbase.zookeeper.quorum配置的值相同   
		        HBASE_CONFIG.set("hbase.zookeeper.quorum", "192.168.8.10,192.168.8.11,192.168.8.12");  
		        //与hbase/conf/hbase-site.xml中hbase.zookeeper.property.clientPort配置的值相同  
		        HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");  
		        HBASE_CONFIG.set("hbase.master", "192.168.8.10:600000"); 

		      //  conf = HBaseConfiguration.create(HBASE_CONFIG);  
		        HBaseAdmin admin;
		        tablePool = new HTablePool(HBASE_CONFIG, 1000);  

*/		
				try {
				    admin = new HBaseAdmin(HBASE_CONFIG);
				 HTableDescriptor tableDesc = new HTableDescriptor("price");
				  tableDesc.addFamily(new HColumnDescriptor("info"));
		        admin.createTable(tableDesc); 
		      
		      
			} catch (MasterNotRunningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ZooKeeperConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//final String TABLE_NAME = "bb";
			//HTableInterface table = tablePool.getTable("urlrecord");
		 // HTableDescriptor  tableDesc = new HTableDescriptor("urlrecord");  
				 
			   //tableDesc.addFamily(new HColumnDescriptor("so"));
		/*  Put put = new Put("11".getBytes());  
		  put.add("baidu".getBytes(), "url".getBytes(),"Hello HBase".getBytes());  
		    put.add("so".getBytes(), "url".getBytes(),"Hello HBase".getBytes()); */
			 /*final String ROW_KEY = "101";  
					                    //+ ":"  
					                      //+ String.valueOf(Long.MAX_VALUE  
					                        //      - System.currentTimeMillis());  
			    Put put = new Put(ROW_KEY.getBytes());  
			    put.add("info1".getBytes(), "he".getBytes(),"Hello HBase".getBytes());  
			    put.add("info2".getBytes(), "he".getBytes(),"Hello HBase".getBytes());  
			    	          						
			    	           try {
			    	        		table.put(put);
								table.flushCommits();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
			    	          */
								//tablePool.putTable(table);
					         System.out.println("create table  ok."); 

	 }
	 
	 public void saveKeyWord(String family,String col,String keyword,String rank) throws IOException{
		 String TABLE_NAME = "keyword";
		 HTableInterface table = tablePool.getTable(TABLE_NAME);
		    Put put = new Put(col.getBytes());  
		    put.add(family.getBytes(),keyword.getBytes(),rank.getBytes());  
		    table.put(put);
		    table.flushCommits();
	 }
	 
	 public void addChannel(String code,String channel,String url) throws IOException{
			final String TABLE_NAME = "channel";
			 HTableInterface table = tablePool.getTable(TABLE_NAME);
			   Put put = new Put(code.getBytes());  
			   put.add("info".getBytes(), "name".getBytes(),channel.getBytes());  
			   put.add("info".getBytes(), "url".getBytes(),url.getBytes());  
			   table.put(put);
			    table.flushCommits();
	 }
	 
	 public void addKeyWord(String channel,String num,List list) throws IOException{
			String TABLE_NAME = "channel";
			 HTableInterface table = tablePool.getTable(TABLE_NAME);
			    Put put = new Put(channel.getBytes()); 
			    for(int i=0;i<list.size();i++){
			    	int n =Integer.parseInt(num)+i;
				    put.add("info".getBytes(), (n+"").getBytes(),list.get(i).toString().getBytes());  
			    }
			    table.put(put);
			    table.flushCommits();
	 }
	 
	 public static void getAllRecord () {  
		        try{  
		        	 HTableInterface table = tablePool.getTable("channel");
		              Scan s = new Scan();  
		              ResultScanner ss = table.getScanner(s);  
		              for(Result r:ss){  
		                  for(KeyValue kv : r.raw()){  
		                     System.out.print(new String(kv.getRow()) + " ");  
		                     System.out.print(new String(kv.getFamily()) + ":");  
		                     System.out.print(new String(kv.getQualifier()) + " ");  
		                     System.out.print(kv.getTimestamp() + " ");  
		                     System.out.println(new String(kv.getValue()));  
		                  }  
		              }  
		         } catch (IOException e){  
		             e.printStackTrace();  
		         }  
		     }  

	 
	 public String getChannelId(String pre) throws IOException{
		 HTableInterface table = tablePool.getTable("channel");
         Scan s = new Scan(); 
         s.setFilter(new PrefixFilter(pre.getBytes()));
         ResultScanner rs = table.getScanner(s);  
         String id ="";
         for(Result r:rs){
        	KeyValue[] kv = r.raw();
        	id=new String(r.getRow());
         }
         return id;
	 }
	 
	 public String getKeywordId(String row) throws IOException{
		 HTableInterface table = tablePool.getTable("channel");
		 Get g = new Get(row.getBytes());
		 Result rs = table.get(g);
         String id ="";
         for(KeyValue kv:rs.raw()){
        	  String key =kv.toStringMap().get("qualifier")+"";
        	if(key.matches("[0-9]+")){
        	//	System.out.println(key);
        		id=key;
        	}
         }
         return id;
	 }
	 
	 public int getChannel(String pre,String channel) throws IOException{
		 HTableInterface table = tablePool.getTable("channel");
		 FilterList filterList = new FilterList();  
		     Scan s1 = new Scan();  
		     filterList.addFilter(new SingleColumnValueFilter("info".getBytes(),"name".getBytes(),CompareOp.EQUAL,channel.getBytes()));
		     filterList.addFilter(new PrefixFilter(pre.getBytes()));
		     s1.setFilter(filterList);
		     s1.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));  
		     ResultScanner rs = table.getScanner(s1);
		     if(rs.next()==null){
			    	return 1;
			    }else{
			    	 for(Result r:rs){  
		                 for(KeyValue kv : r.raw()){  
		                  //  System.out.print(new String(kv.getRow()) + " ");  
		                   // System.out.print(new String(kv.getFamily()) + ":");  
		                   // System.out.print(new String(kv.getQualifier()) + " ");  
		                   // System.out.print(kv.getTimestamp() + " ");  
		                   // System.out.println(new String(kv.getValue()));  
		                 }  
		             }  
				    
			    	return 0;
			    }
		    		     
	 }
	 
	 public List getKeyWord(String row) throws IOException{
		 HTableInterface table = tablePool.getTable("channel");
		 Get g = new Get(row.getBytes());
		   List list = new ArrayList();
		     g.addFamily(Bytes.toBytes("info"));  
		     Result rs = table.get(g);
		     for(KeyValue kv:rs.raw()){
		        	//System.out.println(new String(kv.getValue()));
		        	String value = new String(kv.getValue(),"gbk");
		        	list.add(value);
		         }
		 return list;   
		    		     
	 }

	 public String getAllChannel() throws IOException{
		 HTableInterface table = tablePool.getTable("channel");
		     Scan s1 = new Scan();  		    
		     s1.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));  
		     Map map = new HashMap();
		     ResultScanner rs = table.getScanner(s1);  
			    	 for(Result r:rs){  
		                 for(KeyValue kv : r.raw()){  
		                   // System.out.print(new String(kv.getRow()) + "");  
		                   // System.out.print(new String(kv.getFamily()) + ":");  
		                   // System.out.print(new String(kv.getQualifier(),"gbk") + "");  
		                   // System.out.print(kv.getTimestamp() + " ");  
		                   // System.out.println(new String(kv.getValue()));  
		                    map.put(new String(kv.getRow()), new String(kv.getValue()));
		                    
		                 }   			    
			    }
			    	 return  JSONObject.fromObject(map).toString();
	 }
	
	 public JSONObject getWordByChannelDate(String channel,String startDate,String endDate){
		 System.out.println(startDate+"------"+endDate);
		 HTableInterface table = tablePool.getTable("keyword");
	     Scan scan = new Scan();  
	     SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd"); 
	     if(startDate.equals("")||endDate.equals("")){
	    	 Date date = new Date();
	    	//date.setHours(date.getHours()-24);
	    	 startDate = df.format(date);
	    	 endDate = df.format(date);
	    	 
	     }else{
	    	 startDate = startDate.replace(".", "-");
	    	 endDate = endDate.replace(".", "-");
	    	 System.out.println(startDate+"------"+endDate);
	     }
	     
	     String startkey = channel+"-"+startDate ;
	    String endkey =  channel+"-"+endDate;
	    System.out.println(startkey+"------"+endkey);
	    scan.setStartRow(Bytes.toBytes(startkey));
	    scan.setStopRow(Bytes.toBytes(endkey));
	   // Filter filter3 = new RowFilter(CompareOp.EQUAL,new SubstringComparator("5"));
		//scan.setFilter(filter3);
	    List list = new ArrayList();
	    int sum =0;
    	int nonum = 0;
    	int nofind = 0;
	    try {    	
			ResultScanner rs = table.getScanner(scan);
			for(Result r:rs){  
				sum =r.list().size();
				   for(KeyValue kv:r.list()){  
					
					               String row = new String(kv.getRow());  
					              String column = new String(kv.getQualifier(),"utf-8");  
					              String value = new String(kv.getValue(),"utf-8");  				        
					             
					              String s[] = value.split(",");
					              for(int i=0;i<s.length/2;i++){
					            	   Map map = new HashMap();
					            	  map.put("word",column);
					            	  if(row.substring(0,1).equals("1")){
						            	  map.put("cpc", "百度");
						              }else{
						            	  map.put("cpc", "360");
						              }
					            	  map.put("date", row.substring(5,15));
					            	  map.put("rank", s[i*2+1]);
					            	  if(s[i*2+1].equals("null")){
					            		  nonum++ ;
					            	  }
					            	  if(s[i*2+1].equals("-1")){
					            		  nofind++ ;
					            	  }
						              map.put("url", s[i*2]);
						              list.add(map);  
					              }        	
					             }  	   				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    DecimalFormat df2 = new DecimalFormat("#.00");
	    Map map2 = new HashMap();
	    map2.put("sum", sum);
	    map2.put("num", sum-nonum);
	    map2.put("nofind", nofind+"");
	    map2.put("rate", df2.format(Double.parseDouble(sum-nonum+"")/sum*100)+"");
	    map2.put("data",list);
	   System.out.println("aaaaa"+list.size());
	    return JSONObject.fromObject(map2);
	 }
	 
	 public void deleteRow(String tablename, String rowkey) throws IOException {
		 HTableInterface table = tablePool.getTable("channel");
		      List list = new ArrayList();
		      Delete d1 = new Delete(rowkey.getBytes());
		      list.add(d1);
		      table.delete(list);
		      System.out.println("删除行成功！");
		  }
	 
	 public List getWordByRow(String row) throws IOException{
		 HTableInterface table = tablePool.getTable("channel");
		 Get g = new Get(row.getBytes());
		  List list = new ArrayList(); 
		     g.addFamily(Bytes.toBytes("info"));  
		     Result rs = table.get(g);
		     for(KeyValue kv:rs.raw()){
		        	String qualifier = new String(kv.getQualifier());
		        	if(qualifier.equals("url")||qualifier.equals("name")){
		        		continue;
		        	}
		        	//System.out.println(new String(kv.getValue(),"utf-8"));
		        	//System.out.println(new String(kv.getValue()));
		        	String value = new String(kv.getValue(),"utf-8");
		        	list.add(value);
		         }
		 return list;   
	 }
	 
	 public String getURLByRow(String row) throws IOException{
		 HTableInterface table = tablePool.getTable("channel");
		 Get g = new Get(row.getBytes());
		  List list = new ArrayList(); 
		     g.addFamily(Bytes.toBytes("info"));  
		     Result rs = table.get(g);
		     String url="";
		   
		     for(KeyValue kv:rs.raw()){
		    	 String qualifier = new String(kv.getQualifier());
		    	if(qualifier.equals("url")){
		    		 url =new String(kv.getValue());
		    	}	        	 
	         }
		 return url;
	 }
	 
	 public int delByRow(String row){
		 HTableInterface table = tablePool.getTable("channel");
		 Delete d = new Delete(row.getBytes());
		 try {
			table.delete(d);
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		 
	 }
	 
	 public int upChannelName(String row,String name){
		 HTableInterface table = tablePool.getTable("channel");
		 Put put = new Put(row.getBytes());  
		   put.add("info".getBytes(), "name".getBytes(),name.getBytes());  
		   try {
			table.put(put);
			table.flushCommits();
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		    
	 }

	public int upChannelURL(String row, String url) {
		// TODO Auto-generated method stub
		 HTableInterface table = tablePool.getTable("channel");
		 Put put = new Put(row.getBytes());  
		   put.add("info".getBytes(), "url".getBytes(),url.getBytes());  
		   try {
			table.put(put);
			table.flushCommits();
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	 public List getAllChannelId() {  
		 List list = new ArrayList();
	        try{  
	        	 HTableInterface table = tablePool.getTable("channel");
	              Scan s = new Scan();  
	              ResultScanner ss = table.getScanner(s);  
	              for(Result r:ss){  
	                 list.add(new String(r.getRow()));
	              }  
	         } catch (IOException e){  
	             e.printStackTrace();  
	         }  
	        return list;
	     }  
	 
	 public int clearRow(String row){
		 HTableInterface table = tablePool.getTable("channel");
		 Get g = new Get(row.getBytes());
		  List list = new ArrayList(); 
		     g.addFamily(Bytes.toBytes("info"));  
		     Result rs = null;
			try {
				rs = table.get(g);
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
		     String url="";
		   String name ="";
		     for(KeyValue kv:rs.raw()){
		    	 String qualifier = new String(kv.getQualifier());
		    	if(qualifier.equals("url")){
		    		 url =new String(kv.getValue());
		    	}	
		    	if(qualifier.equals("name")){
		    		 name =new String(kv.getValue());
		    	}
	         }
		      List list2 = new ArrayList();
		      Delete d1 = new Delete(row.getBytes());
		      list2.add(d1);
		      try {
				table.delete(list2);
				  table.flushCommits();
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			} 
		      Put put = new Put(row.getBytes());  
			   put.add("info".getBytes(), "name".getBytes(),name.getBytes());  
			   put.add("info".getBytes(), "url".getBytes(),url.getBytes());  
			   try {
				table.put(put);
				  table.flushCommits();
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
			return 1;
			  
	 }
	 
	 public void saveRecordURL(String family,String col,String url,String rank) throws IOException{
		 String TABLE_NAME = "urlrecord";
		 HTableInterface table = tablePool.getTable(TABLE_NAME);
		    Put put = new Put(col.getBytes());
		   // System.out.println(rank+"bbbbbbbbbbb");
		    put.add(family.getBytes(),url.getBytes(),rank.getBytes());  
		    table.put(put);
		    table.flushCommits();
	 }
	 
	 
	 public JSONObject getRecordByChannelDate(String channel,String startDate,String endDate){
		
		 HTableInterface table = tablePool.getTable("urlrecord");
	     Scan scan = new Scan();  
	     SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd"); 
	     if(startDate.equals("")||endDate.equals("")){
	    	 Date date = new Date();
	    	//date.setHours(date.getHours()-24);
	    	 startDate = df.format(date);
	    	 endDate = df.format(date);
	    	 
	     }else{
	    	 startDate = startDate.replace(".", "-");
	    	 endDate = endDate.replace(".", "-");
	    	 System.out.println(startDate+"------"+endDate);
	     }
	     
	     String startkey = channel+"-"+startDate ;
	    String endkey =  channel+"-"+endDate;
	    System.out.println(startkey+"------"+endkey);
	    scan.setStartRow(Bytes.toBytes(startkey));
	    scan.setStopRow(Bytes.toBytes(endkey));
	   // Filter filter3 = new RowFilter(CompareOp.EQUAL,new SubstringComparator("5"));
		//scan.setFilter(filter3);
	    List list = new ArrayList();
	    int sum =0;
    	int nonum = 0;
    	int nofind = 0;
	    try {    	
			ResultScanner rs = table.getScanner(scan);
			for(Result r:rs){  
				sum =r.list().size();
				 System.out.println("bbbbbbbbbbbb"+r.list().size());
				   for(KeyValue kv:r.list()){  			
					               String row = new String(kv.getRow());  
					              String column = new String(kv.getQualifier(),"utf-8");  
					              String value = new String(kv.getValue(),"utf-8");  				        
					              String cpc = new String (kv.getFamily(),"utf-8");					           					             
					            	   Map map = new HashMap();
					            	  map.put("word",column);					            	
						            	  map.put("cpc", cpc);						              
					            	  map.put("date", row.substring(5,15));
					            	  map.put("rank", value);
					            	  if(value.equals("0")){
					            		  nonum++ ;
					            	  }
					            	  if(value.equals("-1")){
					            		  nofind++ ;
					            	  }						             
						              list.add(map);				                      	
					             }  	   				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    DecimalFormat df2 = new DecimalFormat("#.00");
	    Map map2 = new HashMap();
	    map2.put("sum", sum);
	    map2.put("num", sum-nonum);
	    map2.put("nofind", nofind+"");
	    map2.put("rate", df2.format(Double.parseDouble(sum-nonum+"")/sum*100)+"");
	    map2.put("data",list);
	   System.out.println("aaaaa"+list.size());
	    return JSONObject.fromObject(map2);
	 }
	 
	 public void saveopponent(Map map){
		 HTableInterface table = tablePool.getTable("opponent");
		 Put put = new Put(map.get("id").toString().getBytes());
		 Iterator iterator = map.keySet().iterator();
		 while(iterator.hasNext()){
			String key = iterator.next().toString();
			String value = map.get(key).toString().trim();
			if(!key.equals("id")){
				if(!value.equals("")){
					 put.add("info".getBytes(), key.getBytes(),value.getBytes());  	
				}
			}
		 }
		  
		   try {
			table.put(put);
			table.flushCommits();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		    
	 }
	 
	 public Map findopponent(){
		 HTableInterface table = tablePool.getTable("opponent");
	     Scan scan = new Scan();  
	     Map map = new HashMap();
	     try {
			ResultScanner rs = table.getScanner(scan);
			for(Result r:rs){  
				  System.out.println("--------------");
				  Map map2 = new HashMap();
				  for(KeyValue kv:r.list()){  	
					  String row = new String(kv.getRow());  
		              String column = new String(kv.getQualifier(),"utf-8");  
		              String value = new String(kv.getValue(),"utf-8");  	
		              map2.put(column, value);
		              //String cpc = new String (kv.getFamily(),"utf-8");	
		              System.out.println(row+"---"+column+"---"+value);
				  }
				  map.put(new String(r.getRow()), map2);
			}
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			return map;
		}
	 }
	 
	 public void saveprice(String row,Map map) throws IOException{
		 String TABLE_NAME = "opponent";
		 HTableInterface table = tablePool.getTable(TABLE_NAME);
		    Put put = new Put(row.getBytes());  
		    
		    Iterator <String> iter = map.keySet().iterator();
		    while(iter.hasNext()){
		    	String key = iter.next();
		    	String value = map.get(key)+"";
		    	  put.add("info".getBytes(),key.getBytes(),value.getBytes()); 
		    }
		   
		    table.put(put);
		    table.flushCommits();
	 }
	 
	 public Map findprice(){
		 Date date = new Date();
		 HTableInterface table = tablePool.getTable("price");
	     Scan scan = new Scan();  
	 	java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String s = format.format(date);
        s=s.substring(0,s.length()-4);
        scan.setFilter(new PrefixFilter(s.getBytes()));	
	     Map map = new HashMap();
	     try {
			ResultScanner rs = table.getScanner(scan);
			if(rs.next()==null){
				date.setHours(date.getHours()-1);
				s = format.format(date);
		        s=s.substring(0,s.length()-4);
		        scan.setFilter(new PrefixFilter(s.getBytes()));	
		        rs = table.getScanner(scan);
			}
			for(Result r:rs){  
				  System.out.println("--------------");
				  Map map2 = new HashMap();
				  for(KeyValue kv:r.list()){  	
					  String row = new String(kv.getRow(),"utf-8");  
		              String column = new String(kv.getQualifier(),"utf-8");  
		              String value = new String(kv.getValue(),"utf-8");  	
		              map2.put(column, value);
		              //String cpc = new String (kv.getFamily(),"utf-8");	
		              System.out.println(row+"---"+column+"---"+value);
				  }
				  map.put(new String(r.getRow()), map2);
			}
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			return map;
		}
	 }

	 
	 public Map findprice2(Date date){
		 HTableInterface table = tablePool.getTable("price");
	     Scan scan = new Scan();  
	 	java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String s = format.format(date);
        s=s.substring(0,s.length()-4);
        scan.setFilter(new PrefixFilter(s.getBytes()));	
	     Map map = new HashMap();
	     try {
			ResultScanner rs = table.getScanner(scan);
			if(rs.next()==null){
				return null;
			}
			for(Result r:rs){  
				  System.out.println("--------------");				
				  for(KeyValue kv:r.list()){  	
					  String row = new String(kv.getRow(),"utf-8");  
		             // String column = new String(kv.getQualifier(),"utf-8");  
		              String value = new String(kv.getValue(),"utf-8"); 
		              System.out.println(value+"==="+row);  
		             
		            	  String[] str = value.split(";");
		            	  if(str.length>1){
		            		  map.put(str[0], str[1]+";"+row);
				              //String cpc = new String (kv.getFamily(),"utf-8");	
				             // System.out.println(str[0]+"---"+str[1]);  
		            	  }		            
				  }				  
			}
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			return map;
		}
	 }
	 
	 public String getPriceName(String row){
		 HTableInterface table = tablePool.getTable("opponent");
		 Get get = new Get(row.getBytes());
		
		 try {
			 Result r = table.get(get);
			 for(KeyValue kv:r.list()){
				  String column = new String(kv.getQualifier(),"utf-8"); 
				  String value = new String(kv.getValue(),"utf-8"); 
				  if(column.equals("name")){
					  return value;
				  }
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
	 
	 public Map getPrice(String row){
		 HTableInterface table = tablePool.getTable("opponent");
		 Get get = new Get(row.getBytes());
		Map map = new HashMap();
		 try {
			 Result r = table.get(get);
			 if(r.size()==0){
				 return null;
			 }
			 for(KeyValue kv:r.list()){
				  String column = new String(kv.getQualifier(),"utf-8"); 
				  String value = new String(kv.getValue(),"utf-8"); 
				  map.put(column,value);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	 }
}
