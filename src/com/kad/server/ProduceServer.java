package com.kad.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kad.db.OracleUtil;
import com.kad.util.User;

import net.sf.json.JSONObject;

public class ProduceServer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProduceServer() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("date"+request.getParameter("type"));
		System.out.println("plat"+request.getParameter("plat"));
		System.out.println("id"+request.getParameter("proid"));
		Map map = new HashMap();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String start =request.getParameter("startdate").toString();
		String end = request.getParameter("enddate").toString();
		Long id = 0l;
		if(!request.getParameter("proid").toString().equals("")){
			id = Long.parseLong(request.getParameter("proid"));
		}
		Date d = new Date();
		String type = request.getParameter("type").toString();
		String plat = request.getParameter("plat").toString();
		User user = (User) request.getSession().getAttribute("u");
		System.out.println(user+"cc");
		end = end+" 00:00:00";
		start = start+" 00:00:00";
		if(type.equals("1")){					
			
			List list = new OracleUtil().getproduce(plat, start, end,id,user);
			System.out.println("9999999"+list.size());
			map.put("data", list);
		}
		if(type.equals("2")){
			
			List list = new OracleUtil().getproduceById(id);
			Map map2 = new OracleUtil().getstock(id);
			List list2 = new OracleUtil().getpurtable(id);
			System.out.println(list.size());
			//System.out.println(list);
			map.put("data", list);
			map.put("tabledata", list2);
			map.putAll(map2);
		
		}
		if(type.equals("3")){
			try {
				map = new OracleUtil().getTrafficdate(id,start,end,plat);
				map.put("proid", id);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(type.equals("4")){
			map = new OracleUtil().getsaledata(id,start,end,plat);
		}
		response.setContentType("text/hmtl;charset=utf-8");
		PrintWriter out = response.getWriter();
		//System.out.println(map.toString());
		if(map==null){
			return;
		}
		out.println(JSONObject.fromObject(map));
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
