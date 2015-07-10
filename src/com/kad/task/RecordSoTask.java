package com.kad.task;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import kad.com.RMIService;

import com.kad.db.HbaseUtil;
import com.kad.server.SORecordSearch;
import com.kad.server.WebSerach;
import com.kad.util.Queue;

public class RecordSoTask extends TimerTask{
	static int f = 0;
	@Override
	public void run() {
		System.out.println("开始收录-----共有几个:"+Queue.getSorecordmap().size());
		
		int i =0;
		String msg ="";
		WebSerach websearch = new WebSerach();
		RMIService service=getService();
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		if(date.getHours()<9){
			return;
		}
		String d = format.format(date).substring(0, 10);
		HbaseUtil util = new HbaseUtil();	
		List list = new ArrayList();
		Iterator s = Queue.getSorecordmap().keySet().iterator();
		while(s.hasNext()){
			
			if(i==2000){
				i=0;
				for(int j=0;j<list.size();j++){			
					Queue.getSorecordmap().remove(list.get(j)+"");
					System.out.println(j);
				}
				return;
			}
			i++;
			String url = s.next().toString();
			String row = Queue.getSorecordmap().get(url)+"";
			try {
				while(service==null){
					Thread.sleep(60000); 
					service=getService();	
				}
				if(f==0){
					 msg=service.exc("", url, 5).trim();		
				}else{
					 msg=websearch.search("", url, "5").trim();
				}
							
					if(msg.equals("0")){
						util.saveRecordURL("so",row+"-"+d,url,"0");
						list.add(url);					
						continue;
					}
					if(msg.equals("1")){
						list.add(url);
						util.saveRecordURL("so",row+"-"+d,url,"1");
						continue;
					}
					
					if(msg.equals("-1")){
						if(f==0){
							f=1;
						}else{
							f=0;
						}				
				System.out.println(msg);
				util.saveRecordURL("so",row+"-"+d,url,"-1");
					}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j=0;j<list.size();j++){			
			Queue.getSorecordmap().remove(list.get(j)+"");
			System.out.println(j);
		}
	}
	
	public void run2() {
		SORecordSearch search =new SORecordSearch();
		RMIService service=getService();
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String d = format.format(date).substring(0, 10);
		HbaseUtil util = new HbaseUtil();
		if(Queue.getRecordsolist().size()!=0){
			System.out.println("SO:"+Queue.getRecordsolist().get(0));
			String row = Queue.getRecordsolist().get(0)+"";
			Queue.getRecordsolist().remove(0);
			try {
				/*while(service==null){
					Thread.sleep(60000); 
					service=getService();	
				}*/
				List list = util.getWordByRow(row);
					for(int i=0;i<list.size();i++){
						String url = list.get(i).toString();
						System.out.println(row+"第几个:"+i+"关键词名："+list.get(i));
						//String msg=service.exc("", url, 5);;
						String msg = search.search("www.360kad.com", url);
						System.out.println("aaaaaaaa"+msg);
					if(msg.equals("0")){
						util.saveRecordURL("so",row+"-"+d,list.get(i)+"","0");
						if(Queue.getSorecordmap().containsKey(url)){
							Queue.getSorecordmap().remove(url);
							System.out.println("360补漏添加1个");
						}						
						continue;
					}
					if(msg.equals("1")){
						util.saveRecordURL("so",row+"-"+d,list.get(i)+"","1");
						if(Queue.getSorecordmap().containsKey(url)){
							Queue.getSorecordmap().remove(url);
							System.out.println("360补漏减少1个");
						}
						continue;
					}
					
					if(msg.equals("-1")){
						if(!Queue.getSorecordmap().containsKey(url)){
							
							Queue.getSorecordmap().put(url,row);
							System.out.println("360补漏减少1个");
						}					
						//System.out.println(msg+"tttttttt");
						util.saveRecordURL("so",row+"-"+d,list.get(i)+"","-1");
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
