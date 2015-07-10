package com.kad.task;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import kad.com.RMIService;

import com.kad.db.HbaseUtil;
import com.kad.server.BaiduSearch;
import com.kad.server.MoveBaiduSearch;
import com.kad.server.MoveSoSearch;
import com.kad.server.SoSearch;
import com.kad.server.WebSerach;
import com.kad.util.Queue;
import com.kad.util.Word;
//import com.sun.rowset.internal.Row;

public class WordTesk extends TimerTask{
	static int f = 0;
	@Override
	public void run() {	
		RMIService service=getService();
		WebSerach websearch = new WebSerach();
		int i=0;
		while(Queue.getLinked().size()>0){
			System.out.println("还需要补数量:"+Queue.getLinked().size());
			i++;
			if(i==500){
				i=0;
				return;
			}
			java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = new Date();
			String d = format.format(date).substring(0, 10);
			HbaseUtil util = new HbaseUtil();
			Word word = Queue.getLinked().get(0);
			Queue.getLinked().remove(0);
			String row = word.getChannel();
			
			if(row.substring(0, 1).equals("1")){
				System.out.println("轮询补漏:"+word);
				BaiduSearch search = new BaiduSearch();
				MoveBaiduSearch search2 = new MoveBaiduSearch();
				String msg ="";
				try {
					String url = word.getUrl();
					while(service==null){
						Thread.sleep(60000); 
						service=getService();	
					}
					if(row.equals("1009")){
						// msg = search2.search(url, word.getWord()+"");
						 msg = service.exc(url, word.getWord()+"", 3);
					}else{
					// msg = search.search(url, word.getWord()+"");
						if(f==0){
							msg = service.exc(url, word.getWord()+"", 1);
						}else{
							websearch.search(url,  word.getWord()+"", "1").trim();
						}
						 
					}
						if(msg.equals("0")){						
							Queue.getLinked().add(word);
							System.out.println("添加1个"+Queue.getLinked().size());
							if(f==0){
								f=1;
							}else{
								f=0;
							}
						}
						if(msg.equals("")){
							util.saveKeyWord("pc",row+"-"+d,word.getWord()+"","null,null");
							System.out.println("百度补漏减少1个");
						}
						if(!msg.equals("")){
							util.saveKeyWord("pc",row+"-"+d,word.getWord()+"",msg);
							System.out.println("百度补漏减少1个");
						}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(row.substring(0, 1).equals("2")){
				System.out.println("轮询补漏:"+word);
				String msg = "";
				SoSearch search = new SoSearch();
				MoveSoSearch search2 = new MoveSoSearch();
				try {
					while(service==null){
						Thread.sleep(60000); 
						service=getService();	
					}
					String url = word.getUrl();
					//这里随时需要改动
					if(url.equals("m.360kad.com")){
						 msg = search2.search(url, word.getWord()+"");
					}else{
					//msg = search.search(url, word.getWord()+"");
						if(f==0){
							 msg = service.exc(url, word.getWord()+"", 2);
						}else{
							 msg = websearch.search(url,  word.getWord()+"", "2").trim();
						}
					}
						
						if(msg.equals("0")){
							if(f==0){
								f=1;
							}else{
								f=0;
							}
							Queue.getLinked().add(word);
							System.out.println("添加1个"+Queue.getLinked().size());
						}
						if(msg.equals("")){
							util.saveKeyWord("pc",row+"-"+d,word.getWord()+"","null,null");
							System.out.println("360补漏减少1个");
						}
						if(!msg.equals("")){
							util.saveKeyWord("pc",row+"-"+d,word.getWord()+"",msg);
							System.out.println("360补漏减少1个");
						}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private RMIService getService() {
		// TODO Auto-generated method stub
		try {
			return (RMIService)Naming.lookup("rmi://121.40.137.62:6600/JmiServices");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;  
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;  
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;  
		}
		
	}
}
