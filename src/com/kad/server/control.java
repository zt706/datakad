package com.kad.server;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kad.db.OracleUtil;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class control extends HttpServlet {
	 @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	
	       //  String s = req.getParameter("a");
		 System.out.println("bbb");
	       resp.setContentType("text/xml");
	      resp.setHeader("Cache-Control", "no-cache");
	        resp.getWriter().write("aaaaaaaaaa");
	     }
	 
	 @Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	
	       //  String s = req.getParameter("a");
	       resp.setContentType("text/xml");
	      resp.setHeader("Cache-Control", "no-cache");	       	        
				
	        JSONObject j =null;
			try {
				j = JSONObject.fromObject(new OracleUtil().bb());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        resp.getWriter().write(j.toString());
	       
	     }
}
