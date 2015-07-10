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

public class PriceTask extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		java.text.DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String s = format2.format(new Date());
        s = s.substring(0, s.length()-2);
		Map map = new HbaseUtil().findopponent();
		Iterator <String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			
			Map price = new HashMap();
			String row = iter.next();
			Map map2 = (Map) map.get(row);
			Iterator <String> iter2 = map2.keySet().iterator();
			while(iter2.hasNext()){
				String key = iter2.next();
				String value = map2.get(key)+"";
				String p ="";
				if(key.equals("jk")){
					p =new JianKe().search(value);
				}
				if(key.equals("kx")){
					//p=new Kxr().search(value);
				}
				if(key.equals("yh")){
					
				}
				if(key.equals("kd")){
					p=new Baiji().search(value);
				}
				if(key.equals("jy")){
					p=new J1().search(value);
				}
				if(key.equals("zk")){
					p=new ZK100().search(value);
				}
				if(key.equals("yf")){
					
				}
				if(key.equals("kzj")){
					p=new KZJ().search(value);
				}
				if(key.equals("nb")){
					p=new NB().search(value);
				}
				if(key.equals("fgt")){
					
				}
				if(key.equals("yk")){
					p=new YK().search(value);
				}
				if(key.equals("lbx")){
					p=new LBX().search(value);
				}
				if(key.equals("bj")){
					p=new BJ().search(value);
				}
				if(key.equals("kyj")){
					p=new YP().search(value);
				}
				if(key.equals("gk")){
					
				}
				if(key.equals("bzl")){
					p=new Baozhilin().search(value);
				}
				if(key.equals("jx")){
					p=new Jxdyf().search(value);
				}
				if(key.equals("jz")){
					
				}
				if(key.equals("lyk")){
					
				}
				if(key.equals("ht")){
					p=new Huatuo().search(value);
				}
				if(key.equals("kcd")){
					
				}
				if(key.equals("ss")){
	
				}
			price.put(key, value+";"+p);
			}
			try {
				new HbaseUtil().saveprice(s+"-"+row, price);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int i =0;
		String content ="<table><tr><td>商品名称</td><td>改变前价格</td><td>改变后价格</td><td>URL</td></tr>";
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
		}
		if(map2==null||map1==null){
			return ;
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
		//System.out.println(new HbaseUtil().findopponent());
	}

	public static void main(String[] args) throws IOException{
		
		//System.out.println(new HbaseUtil().getPriceName("8741"));
		int i =0;
		String content ="<table><tr><td>商品名称</td><td>改变后价格</td><td>改变前价格</td><td>URL</td></tr>";
		Map map2 = null;
		Map map1 = new HbaseUtil().findprice2(new Date());
		Map map3 = new HashMap();
		if(map1==null){
			Date d = new Date();
			d.setHours(d.getHours()-1);
			map1 = new HbaseUtil().findprice2(d);
			d.setHours(d.getHours()-1);
			map2 = new HbaseUtil().findprice2(d);
		}else{
			Date d = new Date();
			d.setHours(d.getHours()-1);
			map2=new HbaseUtil().findprice2(d);
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
