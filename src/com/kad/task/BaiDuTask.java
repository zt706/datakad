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
import com.kad.server.SoSearch;
import com.kad.server.WebSerach;
import com.kad.util.Queue;
import com.kad.util.Word;

public class BaiDuTask extends TimerTask{
	static int f=0;
	@Override
	public void run() {
		
		System.out.println("tttttttttttttttttt开始抓取");
		RMIService service=getService();
		
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String d = format.format(date).substring(0, 10);
		HbaseUtil util = new HbaseUtil();
		WebSerach search = new WebSerach();
		//BaiduSearch search = new BaiduSearch();
		//MoveBaiduSearch search2 = new MoveBaiduSearch();
		if(Queue.getBaidulist().size()!=0){
			System.out.println("百度:"+Queue.getBaidulist().get(0));
			String row = Queue.getBaidulist().get(0)+"";
			Queue.getBaidulist().remove(0);
			try {
				while(service==null){
					Thread.sleep(60000); 
					service=getService();	
				}
				List list = util.getWordByRow(row);
				String url = util.getURLByRow(row);
				//System.out.println("ttttttttttttttttt:"+url);
				String msg = "";
				for(int i=0;i<list.size();i++){
					Word word = new Word();
					word.setChannel(row);
					word.setUrl(url);
					word.setWord(list.get(i)+"");
					if(row.equals("1009")){
						 //msg = search2.search(url, list.get(i)+"");
						if(f==0){
							msg =service.exc(url,list.get(i)+"" , 3);
						}else{
							msg =search.search(url, list.get(i)+"", "3").trim();
						}
						 //System.out.println("tttttttttttttttttt"+list.get(i)+"------"+msg);
					}else{
						// msg = search.search(url, list.get(i)+"");
						if(f==0){
							msg =service.exc(url,list.get(i)+"" , 1);
						}else{
							msg= search.search(url, list.get(i)+"", "1").trim();
						}
					}
					
					System.out.println(row+"第几个:"+i+"关键词名:"+list.get(i));					
					if(msg.equals("0")){
						if(f==0){
							f=1;
						}else{
							f=0;
						}
						util.saveKeyWord("pc",row+"-"+d,list.get(i)+"","-1,-1");
						if(!Queue.getLinked().contains(word)){
							Queue.getLinked().add(word);
							System.out.println("百度补漏添加1个");
						}					
						continue;
					}
					if(msg.equals("")){
						util.saveKeyWord("pc",row+"-"+d,list.get(i)+"","null,null");
						if(Queue.getLinked().contains(word)){
							Queue.getLinked().remove(word);
							System.out.println("百度补漏减少1个");
						}	
						continue;
					}
					if(!msg.equals("")){	
						util.saveKeyWord("pc",row+"-"+d,list.get(i)+"",msg);
						if(Queue.getLinked().contains(word)){
							Queue.getLinked().remove(word);
							System.out.println("百度补漏减少1个");
						}	
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

	private RMIService getService() {
		// TODO Auto-generated method stub
		try {
			return (RMIService)Naming.lookup("rmi://121.40.137.62:6600/JmiService");
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
