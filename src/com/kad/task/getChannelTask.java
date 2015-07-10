package com.kad.task;

import java.util.List;
import java.util.TimerTask;

import com.kad.db.HbaseUtil;
import com.kad.util.Queue;

public class getChannelTask extends TimerTask {

	@Override
	public void run() {
		Queue.getLinked().clear();
		HbaseUtil util = new HbaseUtil();
		List list = util.getAllChannelId();
		for(int i=0;i<list.size();i++){
			if(list.get(i).toString().subSequence(0, 1).equals("1")){
				Queue.getBaidulist().add(list.get(i)+"");
				Queue.getMobilebaidulist().add(list.get(i)+"");
			}
			if(list.get(i).toString().subSequence(0, 1).equals("2")){
				Queue.getSolist().add(list.get(i)+"");
			}
		/*	if(list.get(i).toString().subSequence(0, 1).equals("3")){
				Queue.getRecordbaidulist().add(list.get(i)+"");
				Queue.getRecordsolist().add(list.get(i)+"");
			}*/
			
		}
	
	/*	Queue.getBaidulist().add("1005");Queue.getBaidulist().add("1007");Queue.getBaidulist().add("1010");
		Queue.getSolist().add("2001");Queue.getSolist().add("2002");Queue.getSolist().add("2003");Queue.getSolist().add("2004");
		Queue.getSolist().add("2005");*/
		
		System.out.println("频道读取完成:baidu:"+Queue.getBaidulist()+"so:"+Queue.getSolist()+"移动baidu:"+Queue.getMobilebaidulist());
		
	}

}
