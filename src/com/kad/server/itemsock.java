package com.kad.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

public class itemsock extends WebSocketServlet{

	 private static final long serialVersionUID = -4853540828121130946L;
	    private static ArrayList mmiList = new ArrayList();

	    @Override
	    protected StreamInbound createWebSocketInbound(String str, HttpServletRequest request) {
	    	System.out.println("aa");
	    	return new MyMessageInbound();  
	    }

	   
	    


}
