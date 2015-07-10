package com.kad.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class sockclient  {

	static WebSocketConnection wsc; 
	  public static void main(String[] args) {  
		         System.out.println("开始连接websocket///");  
		   
		         try {  
		        	 wsc = new WebSocketConnection();  
		             wsc.connect("ws://localhost:8080/datakad/websocket.ws",  new WebSocketHandler() {  
		                  
		                        @Override  
		                       public void onBinaryMessage(byte[] payload) {  
		                            System.out.println("onBinaryMessage size="  
		                                     + payload.length);  
		                         }  
		   
		                        @Override  
		                       public void onClose(int code, String reason) {  
		                           System.out.println("onClose reason=" + reason);  
		                         }  
		   
		                         @Override  
		                         public void onOpen() {  
		                             System.out.println("onOpen");  
		                             // showtext("连接成功");  
		                              // wsc.sendTextMessage("Hello!");  
		                             // wsc.disconnect();  
		                         }  
		                        @Override  
		                        public void onRawTextMessage(byte[] payload) {  
		                             System.out.println("onRawTextMessage size="  
		                                    + payload.length);  
		                        }  
		   
		                         @Override  
		                       public void onTextMessage(String payload) {  
	                            System.out.println("onTextMessage" + payload);  
		                           // showtext(payload);  
		                          }  
		  
		                     });  
		         } catch (WebSocketException e) {  
		             // TODO Auto-generated catch block  
		              e.printStackTrace();  
		         }  
		 
		      }  



}
