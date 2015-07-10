package com.kad.task;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import com.kad.db.HbaseUtil;
import com.kad.server.BaiduSearch;
import com.kad.server.SoSearch;
import com.kad.util.Queue;
import com.kad.util.Word;
//import com.sun.rowset.internal.Row;

public class MobileWordTesk extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("轮询补漏:"+Queue.getMobilelinked().size());
		if(Queue.getLinked().size()>0){
			java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = new Date();
			String d = format.format(date).substring(0, 10);
			HbaseUtil util = new HbaseUtil();
			Word word = Queue.getMobilelinked().get(0);
			Queue.getLinked().remove(0);
			String row = word.getChannel();
			
			if(row.substring(0, 1).equals("1")){
				System.out.println("轮询补漏:"+row);
				BaiduSearch search = new BaiduSearch();
				try {
					String url = word.getUrl();
						String msg = search.search(url, word.getWord()+"");
						if(msg.equals("0")){						
							Queue.getMobilelinked().add(word);
							return ;
						}
						if(msg.equals("")){
							util.saveKeyWord("mobile",row+"-"+d,word.getWord()+"","null,null");
							return ;
						}
						if(!msg.equals("")){
							util.saveKeyWord("mobile",row+"-"+d,word.getWord()+"",msg);
						}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(row.substring(0, 1).equals("2")){
				System.out.println("轮询补漏:"+row);
				SoSearch search = new SoSearch();
				try {
					String url = word.getUrl();
					String msg = search.search(url, word.getWord()+"");
						
						if(msg.equals("0")){
							Queue.getMobilelinked().add(word);
							return ;
						}
						if(msg.equals("")){
							util.saveKeyWord("mobile",row+"-"+d,word.getWord()+"","null,null");
							return ;
						}
						if(!msg.equals("")){
							util.saveKeyWord("mobile",row+"-"+d,word.getWord()+"",msg);
						}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
