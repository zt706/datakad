package com.kad.task;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import com.kad.db.HbaseUtil;
import com.kad.server.BaiduSearch;
import com.kad.server.MoveBaiduSearch;
import com.kad.server.SoSearch;
import com.kad.util.Queue;
import com.kad.util.Word;

public class MoveBaiDuTask extends TimerTask{

	@Override
	public void run() {
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String d = format.format(date).substring(0, 10);
		HbaseUtil util = new HbaseUtil();
		MoveBaiduSearch search = new MoveBaiduSearch();
		if(Queue.getMobilebaidulist().size()!=0){
			System.out.println("百度:"+Queue.getMobilebaidulist().get(0));
			String row = Queue.getMobilebaidulist().get(0)+"";
			Queue.getMobilebaidulist().remove(0);
			try {
				List list = util.getWordByRow(row);
				String url = util.getURLByRow(row);
				for(int i=0;i<list.size();i++){
					String msg = search.search(url, list.get(i)+"");
					System.out.println(row+"第几个:"+i+"关键词名:"+list.get(i)+": "+msg);					
					if(msg.equals("0")){
						Word word = new Word();
						word.setChannel(row);
						word.setUrl(url);
						word.setWord(list.get(i)+"");
						Queue.getMobilelinked().add(word);
						continue;
					}
					if(msg.equals("")){
						util.saveKeyWord("mobile",row+"-"+d,list.get(i)+"","null,null");
						continue;
					}
					if(!msg.equals("")){	
						util.saveKeyWord("mobile",row+"-"+d,list.get(i)+"",msg);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

}
