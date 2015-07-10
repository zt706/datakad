package com.kad.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import com.kad.task.CusOrderTask;
import com.kad.util.MemcachedConnector;



public class ItemMessageInbound extends MessageInbound {  

    @Override  
    protected void onBinaryMessage(ByteBuffer arg0) throws IOException {  
        // TODO Auto-generated method stub  
          
   }  
  
    @Override  
   protected void onTextMessage(CharBuffer msg) throws IOException {  
        for (MessageInbound messageInbound : InitServlet.getSocketList()) { 
        	String str =new CusOrderTask().getData().toString();
        	if(str==null){
        		return;
        	}
            CharBuffer buffer = CharBuffer.wrap( str);  
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
