package com.kad.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.catalina.websocket.MessageInbound;

public class InitServlet extends HttpServlet {  
  
   private static final long serialVersionUID = -3163557381361759907L;  
      
   private static List<MessageInbound> socketList;    
      
   public void init(ServletConfig config) throws ServletException {    
        InitServlet.socketList = new ArrayList<MessageInbound>();    
        super.init(config);    
        System.out.println("InitServlet.java Server start============");    
   }    
        
   public static List<MessageInbound> getSocketList() {    
       return InitServlet.socketList;    
    }    
}  

