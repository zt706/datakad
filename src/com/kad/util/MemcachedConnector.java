package com.kad.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;


public class MemcachedConnector 
{
	static int f = 0;
	// 创建一个 memcached 客户端对象  
    public static MemCachedClient mcc = new MemCachedClient();  
  
    // 创建 memcached连接池  
    static  
    { 
    	// 指定memcached服务地址  
    	//String[] servers = { "192.168.8.11:11211"};  
    	String[] servers = { "10.0.22.104:11311"};
  
	    // 指定memcached服务器负载量  
	    Integer[] weights ={3};  
	  
	    // 从连接池获取一个连接实例  
	    SockIOPool pool = SockIOPool.getInstance();  
	  
	    // 设置服务器和服务器负载量  
	    pool.setServers( servers );  
	  
	    pool.setWeights( weights );  
	  
	    // 设置一些基本的参数  
	    //设置初始连接数5 最小连接数 5 最大连接数 250  
	    //设置一个连接最大空闲时间6小时  
	    pool.setInitConn( 5 );  
	    pool.setMinConn( 5 );  
	    pool.setMaxConn( 250 );  
	    pool.setMaxIdle( 1000 * 60 * 60 * 6 );  

	    // 设置主线程睡眠时间  
	    // 每隔30秒醒来 然后  
	    // 开始维护 连接数大小  
	    pool.setMaintSleep(30);  

	    // 设置tcp 相关的树形  
	    // 关闭nagle算法  
	    // 设置 读取 超时3秒钟 set the read timeout to 3 secs  
	    // 不设置连接超时  
	    pool.setNagle( false );  
	    pool.setSocketTO( 3000 );  
	    pool.setSocketConnectTO( 0 );  
	  
	    // 开始初始化 连接池  
	    pool.initialize();  
	  
	    // 设置压缩模式  
//	    //如果超过64k压缩数据  
//	    mcc.setCompressEnable( true );  
//	    mcc.setCompressThreshold( 64 * 1024 );  
  
    }  
  
    public static void main(String args[]) 
    {  
//        User u1 = new User();  
//        u1.setUserName("frady");  
//        u1.setEmail("weimengiq@126.com");  
//        mcc.add("fradyt", u1);  
//   
//        User u2 = (User) mcc.get("fradyt");  
//        System.out.println("email=" + u2.getEmail());  
//        u2.setEmail("weimengiq@gmail.com");  
//        mcc.replace("fradyt", u2);  
//   
//        u2 = (User) mcc.get("fradyt");  

    	/*String  str = (String) MemcachedConnector.mcc.get("PlatFormSale-20141014-h-13");
		System.out.println(str);
		JSONArray array = JSONArray.fromObject(str);
		ArrayList<PlatFormSales> collection = (ArrayList<PlatFormSales>)JSONArray.toCollection(array, PlatFormSales.class);
		
		for(PlatFormSales platFormSales :collection)
		{
			System.out.println(platFormSales.toString());
		}
    */
    	new MemcachedConnector().getdate();
    }
    
    public JSONObject getdate(){
    	String key = "";
    	if(f==0){
    		key = "PlatFormSale-20141015-h-14";
    		f=1;
    	}else{
    		f=0;
    		key = "PlatFormSale-20141015-h-15";
    	}
    	String  str = (String) MemcachedConnector.mcc.get(key);
		System.out.println(str);
		JSONArray array = JSONArray.fromObject(str);
		ArrayList<PlatFormSales> collection = (ArrayList<PlatFormSales>)JSONArray.toCollection(array, PlatFormSales.class);
		if(collection==null){
			return null;
		}
		Map map = new HashMap();
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		for(PlatFormSales platFormSales :collection)
		{
			list1.add(platFormSales.getOriginName());
			list2.add(platFormSales.getSales4Status());
			System.out.println(platFormSales.getOriginName()+"---"+platFormSales.getSales4Status());
			//System.out.println(platFormSales.toString());
		}
		map.put("name", list1);
		map.put("sale", list2);
		return JSONObject.fromObject(map);
    }
    
}