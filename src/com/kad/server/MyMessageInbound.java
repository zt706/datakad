package com.kad.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

import net.sf.json.JSONObject;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import com.kad.db.OracleUtil;
import com.kad.task.CusOrderTask;
import com.kad.util.MemcachedConnector;



public class MyMessageInbound extends MessageInbound {  
	static Date date =null;
    @Override  
    protected void onBinaryMessage(ByteBuffer arg0) throws IOException {  
        // TODO Auto-generated method stub  
          
   }  
  
    @Override  
   protected void onTextMessage(CharBuffer msg) throws IOException {
    	/*Date date2 = new Date();
    	if(date==null){
    		date =new Date();
    	}
    	if(date2.getMinutes()-date.getMinutes()>2){
    		date =date2;
    	}else{
    		return;
    	}*/
    	Collection<MessageInbound> users =  new CopyOnWriteArrayList<MessageInbound>();
    	users =  InitServlet.getSocketList();
    	
    	System.out.println(" MyMessageInbound.java >>>><<<<<<<<< 连接用户数 " + users.size());
    	System.out.println();
    	
        for (MessageInbound messageInbound : users) { 
        	//String str =new CusOrderTask().getData().toString();
        	
        	// JSONObject o =new OracleUtil().getOrderData();
        	
        	// 2.0 系统kad 数据平台 新接口
        	//users.remove(messageInbound);						// 将查询数据库的socket链接移除，保证在查询数据库过程中刷新页面也能正常
        	JSONObject o =new OracleUtil().getOrderDataNew();
        	
        	if(o==null){
        		return;
        	}
        	String str =o.toString();
            CharBuffer buffer = CharBuffer.wrap( str);  
            
            //System.out.println(" buffer ===  " + str);
            
            WsOutbound outbound = messageInbound.getWsOutbound(); 
            outbound.writeTextMessage(buffer);  
            outbound.flush();  
        }  
          
    }  
  
    @Override  
    protected void onClose(int status) {  
        InitServlet.getSocketList().remove(this);  
        super.onClose(status);  
    }  
  
   @Override  
   protected void onOpen(WsOutbound outbound) {  
        super.onOpen(outbound);  
        InitServlet.getSocketList().add(this);  
    }  
      
      
  
}  
