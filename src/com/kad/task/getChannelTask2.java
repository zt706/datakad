package com.kad.task;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import kad.com.RMIService;

import com.kad.db.HbaseUtil;
import com.kad.server.SORecordSearch;
import com.kad.util.Queue;

public class getChannelTask2 extends TimerTask {

	@Override
	public void run() {
		HbaseUtil util = new HbaseUtil();
		Calendar cal = Calendar.getInstance();
		Date d = new Date();
		cal.setTime(d);
		  int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		  if(w==5){
			 this.searchByChannel("3001");
			 this.searchByChannel("3002");
			 this.searchByChannel("3003");
							
		  }
		  
		  if(w==6){
			  this.searchByChannel("3004");
				 this.searchByChannel("3005");
				 this.searchByChannel("3006");				
		  }
		  
		  if(w==0){
			  this.searchByChannel("3007");
			this.searchByChannel("3008");
			this.searchByChannel("3009");
				
		  }
		
	}

	public static void main(String[] args){
		//new RecordTesk().run();
		Calendar cal = Calendar.getInstance();
		Date d = new Date();
		d.setHours(d.getHours()+0);
		cal.setTime(d);
		  int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		  System.out.print(w);
	}

	
	private void searchByChannel(String row) {
		SORecordSearch search =new SORecordSearch();
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String d = format.format(date).substring(0, 10);
		HbaseUtil util = new HbaseUtil();
	
		try {
			List list = util.getWordByRow(row);
			//String url = util.getURLByRow(row);
			String msg = "";
			for(int i=0;i<list.size();i++){
				Queue.getBaidurecordmap().put(list.get(i)+"", row);
			}
			for(int i=0;i<list.size();i++){
				Queue.getSorecordmap().put(list.get(i)+"", row);
			}
			System.out.println("添加多少个"+Queue.getBaidurecordmap().size());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
}
