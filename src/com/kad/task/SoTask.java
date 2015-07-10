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

import org.apache.poi.hslf.model.MovieShape;

import com.kad.db.HbaseUtil;
import com.kad.server.MoveSoSearch;
import com.kad.server.SoSearch;
import com.kad.server.WebSerach;
import com.kad.util.Queue;
import com.kad.util.Word;

public class SoTask extends TimerTask{
	static int f=0;
	@Override
	public void run() {
		WebSerach websearch = new WebSerach();
		RMIService service=getService();
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String d = format.format(date).substring(0, 10);
		//SoSearch search = new SoSearch();
		//MoveSoSearch search2 = new MoveSoSearch();
		HbaseUtil util = new HbaseUtil();
		if(Queue.getSolist().size()!=0){
			System.out.println("SO:"+Queue.getSolist().get(0));
			String row = Queue.getSolist().get(0)+"";
			Queue.getSolist().remove(0);
			try {
				while(service==null){
					Thread.sleep(60000); 
					service=getService();	
				}
				List list = util.getWordByRow(row);
				String url = util.getURLByRow(row);
				System.out.println("TTTTTTTTTTTT:"+url);
					for(int i=0;i<list.size();i++){
						Word word = new Word();
						word.setChannel(row);
						word.setUrl(url);
						word.setWord(list.get(i)+"");
						System.out.println(row+"第几个:"+i+"关键词名："+list.get(i));
						String msg="";
						if(url.equals("m.360kad.com")){
							//msg = search2.search(url, list.get(i)+"");
						}else{
							if(f==0){
								msg =service.exc(url, list.get(i)+"", 2);
							}else{
								msg = websearch.search(url, list.get(i)+"","2").trim();
							}
							//msg = search.search(url, list.get(i)+"");
							//msg =service.exc(url, list.get(i)+"", 2);														
						}
					if(msg.equals("0")){
						if(f==0){
							f=1;
						}else{
							f=0;
						}
						util.saveKeyWord("pc",row+"-"+d,list.get(i)+"","-1,-1");
						if(!Queue.getLinked().contains(word)){
							Queue.getLinked().add(word);
							System.out.println("360补漏添加1个");
						}						
						continue;
					}
					if(msg.equals("")){
						util.saveKeyWord("pc",row+"-"+d,list.get(i)+"","null,null");
						if(Queue.getLinked().contains(word)){
							Queue.getLinked().remove(word);
							System.out.println("360补漏减少1个");
						}
						continue;
					}
					
					if(!msg.equals("")){
						if(Queue.getLinked().contains(word)){
							Queue.getLinked().remove(word);
							System.out.println("360补漏减少1个");
						}					
						//System.out.println(msg+"tttttttt");
						util.saveKeyWord("pc",row+"-"+d,list.get(i)+"",msg);
					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private RMIService getService() {
		try {
			return (RMIService)Naming.lookup("rmi://121.40.137.62:6600/JmiService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;  
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;  
		} catch (NotBoundException e) {
			
			e.printStackTrace();
			return null;  
		}
		
	}
}
