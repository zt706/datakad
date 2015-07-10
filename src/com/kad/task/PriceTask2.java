package com.kad.task;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;

import com.kad.bid.BJ;
import com.kad.bid.Baiji;
import com.kad.bid.Baozhilin;
import com.kad.bid.Huatuo;
import com.kad.bid.J1;
import com.kad.bid.JianKe;
import com.kad.bid.Jxdyf;
import com.kad.bid.KZJ;
import com.kad.bid.Kxr;
import com.kad.bid.LBX;
import com.kad.bid.NB;
import com.kad.bid.YK;
import com.kad.bid.YP;
import com.kad.bid.ZK100;
import com.kad.db.HbaseUtil;
import com.kad.mail.SendPrice;

public class PriceTask2 extends TimerTask{

	@Override
	public void run() {
		int i =0;
		String content ="<table><tr><td>商品名称</td><td>改变后价格</td><td>改变前价格</td><td>URL</td></tr>";
		Map map2 = null;
		Map map1 = new HbaseUtil().findprice2(new Date());
		Map map3 = new HashMap();
		if(map1==null){
			Date d = new Date();
			d.setHours(d.getHours()-1);
			map1 = new HbaseUtil().findprice2(d);
			d.setHours(d.getHours()-24);
			map2 = new HbaseUtil().findprice2(d);
		}else{
			Date d = new Date();
			d.setHours(d.getHours()-24);
			map2=new HbaseUtil().findprice2(d);
			if(map2==null){
				return;
			}
		}
		if(map2==null||map1==null){
			return;
		}
		System.out.println(map1.size());
		System.out.println("-----------------------");
		System.out.println(map2.size());
		Iterator <String> iter3 = map1.keySet().iterator();
		while(iter3.hasNext()){
			String key = iter3.next();		
				
				String[] str1 = map1.get(key).toString().split(";");
				String[] str2 = map2.get(key).toString().split(";");
				if(str1[0].equals(str2[0])||str1[0].equals("0")||str2[0].equals("0")){
					
				}else{
					System.out.println("aaaaaaaaaa");
					System.out.println(str1[0].equals(str2[0]));
					i=1;
					System.out.println(str1[1].split("-")[1]);
					String name = new HbaseUtil().getPriceName(str1[1].split("-")[1]);
					map3.put(name,str1[0]+";"+str2[0]);
					content =content+"<tr><td>"+name+"</td><td>"+str1[0]+"</td><td>"+str2[0]+"</td><td>"+key+"</td></tr>";
				}
				
			
		}
		content=content+"</table>";
		System.out.println(map3);
		
		try {
			if(i==1){
				new SendPrice().send(content);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException{
		int i =0;
		String content ="<table><tr><td>商品名称</td><td>改变后价格</td><td>改变前价格</td><td>URL</td></tr>";
		Map map2 = null;
		Map map1 = new HbaseUtil().findprice2(new Date());
		Map map3 = new HashMap();
		if(map1==null){
			Date d = new Date();
			d.setHours(d.getHours()-1);
			map1 = new HbaseUtil().findprice2(d);
			d.setHours(d.getHours()-24);
			map2 = new HbaseUtil().findprice2(d);
		}else{
			Date d = new Date();
			d.setHours(d.getHours()-24);
			map2=new HbaseUtil().findprice2(d);
			if(map2==null){
				return;
			}
		}
		System.out.println(map1.size());
		System.out.println("-----------------------");
		System.out.println(map2.size());
		Iterator <String> iter3 = map1.keySet().iterator();
		while(iter3.hasNext()){
			String key = iter3.next();		
				
				String[] str1 = map1.get(key).toString().split(";");
				if(map2.get(key)==null){
					continue;
				}
				String[] str2 = map2.get(key).toString().split(";");
				if(str1[0].equals(str2[0])||str1[0].equals("0")||str2[0].equals("0")){
					
				}else{
					System.out.println("aaaaaaaaaa");
					System.out.println(str1[0].equals(str2[0]));
					i=1;
					System.out.println(str1[1].split("-")[1]);
					String name = new HbaseUtil().getPriceName(str1[1].split("-")[1]);
					map3.put(name,str1[0]+";"+str2[0]);
					content =content+"<tr><td>"+name+"</td><td>"+str1[0]+"</td><td>"+str2[0]+"</td><td>"+key+"</td></tr>";
				}
				
			
		}
		content=content+"</table>";
		System.out.println(map3);
		
		try {
			if(i==1){
				//new SendPrice().send(content);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
