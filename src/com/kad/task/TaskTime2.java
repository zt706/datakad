package com.kad.task;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.kad.db.HbaseUtil;
import com.kad.util.Queue;

public class TaskTime2 extends TimerTask implements ServletContextListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	Timer timer = new Timer();
		Date d = new Date();
		Date d2 = new Date();
		d.setMinutes(d.getMinutes()+1);
		d2.setMinutes(d.getMinutes()+10);
		timer.schedule(new getChannelTask(), new Date(), 1000*60*60*24);
		timer.schedule(new WordTesk(),new Date(), 1000*60*12);
		timer.schedule(new SoTask(), new Date(),1000*60*10);	
		timer.schedule(new BaiDuTask(), new Date(),1000*60*24);*/
		
		/*LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();
		list.put("cc", "cc");
		list.put("aa", "aa");
		list.put("bb", "bb");*/
		
		Map map = new HashMap();
		for(int i=0;i<1000000;i++){
			map.put(i+"", i+"");
			System.out.println(i);
		}
	
       
		//System.out.println(list);
		 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("cc \n");
		HbaseUtil util = new HbaseUtil();
		List list = util.getAllChannelId();
		//Queue.setVector(list);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("自动任务2销毁");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("自动任务2开始");
		Timer timer = new Timer();
		Date d = new Date();
		d.setMinutes(d.getMinutes()+1);
		Date d2 = new Date();
		d2.setHours(d.getHours()+15);
		//timer.schedule(new getChannelTask2(), d, 1000*60*60*24);
		
		//timer.schedule(new RecordSoTask(), d,1000*60*25);		
		//timer.schedule(new RecordBaiduTask(),d,1000*60*30);

		
	}

}
