package com.kad.task;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;

import kad.com.RMIService;

import com.kad.db.HbaseUtil;
import com.kad.server.SORecordSearch;
import com.kad.util.Queue;

public class RecordTesk extends TimerTask{

	@Override
	public void run() {	
		System.out.println("shoulu收录补录开始");
		int i = 0;
		int j = 0;
		SORecordSearch search =new SORecordSearch();
		RMIService service=getService();
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String d = format.format(date).substring(0, 10);
		HbaseUtil util = new HbaseUtil();	
		Iterator s = Queue.getBaidurecordmap().keySet().iterator();
		while(s.hasNext()){
			if(i==1000){
				break;
			}
			i++;
			String url = s.next().toString();
			String row = Queue.getBaidurecordmap().get("url")+"";
			Queue.getBaidurecordmap().remove(url);
			try {
				while(service==null){
					Thread.sleep(60000); 
					service=getService();	
				}
						String msg=service.exc("", url, 4);;						
					if(msg.equals("0")){
						util.saveRecordURL("baidu",row+"-"+d,url,"0");
						if(Queue.getBaidurecordmap().containsKey(url)){
							Queue.getBaidurecordmap().remove(url);
							System.out.println("baidu补漏减少1个");
						}						
						continue;
					}
					if(msg.equals("1")){
						util.saveRecordURL("baidu",row+"-"+d,url,"1");
						if(Queue.getBaidurecordmap().containsKey(url)){
							Queue.getBaidurecordmap().remove(url);
							System.out.println("baidu补漏减少1个");
						}
						continue;
					}
					
					if(msg.equals("-1")){
						if(!Queue.getBaidurecordmap().containsKey(url)){						
							Queue.getBaidurecordmap().put(url,row);
							System.out.println("badidu补漏添加1个");
						}					
						//System.out.println(msg+"tttttttt");
						util.saveRecordURL("baidu",row+"-"+d,url,"-1");
					}
								
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Iterator s2 = Queue.getSorecordmap().keySet().iterator();
		while(s2.hasNext()){
			if(i==1000){
				return;
			}
			j++;
			String url = s2.next().toString();
			String row = Queue.getSorecordmap().get(url)+"";
			Queue.getSorecordmap().remove(url);
			try {
				/*while(service==null){
					Thread.sleep(60000); 
					service=getService();	
				}*/
						//String msg=service.exc("", url, 5);		
						String msg = search.search("www.360kad.com", url);
					if(msg.equals("0")){
						util.saveRecordURL("baidu",row+"-"+d,url,"0");
						if(Queue.getBaidurecordmap().containsKey(url)){
							Queue.getBaidurecordmap().remove(url);
							System.out.println("360补漏减少1个");
						}						
						continue;
					}
					if(msg.equals("1")){
						util.saveRecordURL("baidu",row+"-"+d,url,"1");
						if(Queue.getBaidurecordmap().containsKey(url)){
							Queue.getBaidurecordmap().remove(url);
							System.out.println("360补漏减少1个");
						}
						continue;
					}
					
					if(msg.equals("-1")){
						if(!Queue.getBaidurecordmap().containsKey(url)){						
							Queue.getBaidurecordmap().put(url,row);
							System.out.println("360补漏添加1个");
						}					
						//System.out.println(msg+"tttttttt");
						util.saveRecordURL("baidu",row+"-"+d,url,"-1");
					}						
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}

	private RMIService getService() {
		try {
			//System.setSecurityManager(new java.rmi.RMISecurityManager());
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
