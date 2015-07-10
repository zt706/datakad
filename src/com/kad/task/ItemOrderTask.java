package com.kad.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kad.db.OracleUtil;
import com.kad.util.MemcachedConnector;

public class ItemOrderTask {
	public static void main(String[] args){
		Map map = new OracleUtil().getCusOrder();
		Date expireDate = new Date(System.currentTimeMillis() + 1000*60*3);
		System.out.println(map);
		
		if (MemcachedConnector.mcc.get("cusOrder") != null)
		{
			MemcachedConnector.mcc.replace("cusOrder", JSONObject.fromObject(map).toString(), expireDate);
		}
		else{
			MemcachedConnector.mcc.add("cusOrder",JSONObject.fromObject(map).toString(), expireDate);
		}
		System.out.println(MemcachedConnector.mcc.get("cusOrder"));
	}
	
	public static String getData(){
		Map map = new OracleUtil().getCusOrder();
		Date expireDate = new Date(System.currentTimeMillis() + 1000*60*3);
		System.out.println(map);
		
		if (MemcachedConnector.mcc.get("cusOrder") != null)
		{
			MemcachedConnector.mcc.replace("cusOrder", JSONObject.fromObject(map).toString(), expireDate);
		}
		else{
			MemcachedConnector.mcc.add("cusOrder",JSONObject.fromObject(map).toString(), expireDate);
		}
		
		return MemcachedConnector.mcc.get("cusOrder").toString();
	}
}
