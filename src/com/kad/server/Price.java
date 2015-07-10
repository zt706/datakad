package com.kad.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.kad.db.HbaseUtil;

public class Price extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Price() {
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
		response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		
		Map map = null;
		String type = request.getParameter("type");
		String row = request.getParameter("row");
		if(type.equals("1")){
		 map =new HbaseUtil().findprice();
		}
		if(type.equals("2")){
		map = new HbaseUtil().getPrice(row);
		}
		System.out.println(JSONObject.fromObject(map).toString());
		out.println(JSONObject.fromObject(map).toString());
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
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("gbk");
		response.setContentType("text/html");
		String type = request.getParameter("type");
		String row = request.getParameter("row");
		
		if(type.equals("3")){
			this.saveitem(request, response);
			}
		PrintWriter out = response.getWriter();
	
		out.println("0");
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

	public static void main(String[] args){
		new HbaseUtil().findprice();
	}
	
	public void saveitem(HttpServletRequest request, HttpServletResponse response){
		Map map = new HashMap();
		String item =  request.getParameter("item");
		//String row =  request.getParameter("row");
		String mail = request.getParameter("mail");
		String id = request.getParameter("row");
		//String name = request.getParameter("name");
		String jk = request.getParameter("jk");
		String kx = request.getParameter("kx");
		String kd= request.getParameter("kd");
		String jy= request.getParameter("jy");
		String zk= request.getParameter("zk");
		String kzj= request.getParameter("kzj");
		String nb= request.getParameter("nb");
		String yk= request.getParameter("yk");
		String lbx= request.getParameter("lbx");
		String bj= request.getParameter("bj");
		String kyj= request.getParameter("kyj");
		String bzl= request.getParameter("bzl");
		String jx= request.getParameter("jx");
		String ht= request.getParameter("ht");
		map.put("name", item);
		//map.put("mail", mail);
		//if(!jk.trim().equals("")){
			map.put("jk", jk);
		
		
		//if(!kx.trim().equals("")){
			map.put("kx", kx);
		
		//if(!kd.trim().equals("")){
			map.put("kd", kd);
		
		//if(!jy.trim().equals("")){
			map.put("jy", jy);
		
		//if(!zk.trim().equals("")){
			map.put("zk", zk);
		
		//if(!kzj.trim().equals("")){
			map.put("kzj", kzj);
		
		//if(!nb.trim().equals("")){
			map.put("nb", nb);
		
		//if(!yk.trim().equals("")){
			map.put("yk", yk);
		
		//if(!lbx.trim().equals("")){
			map.put("lbx", lbx);
		
		//if(!bj.trim().equals("")){
			map.put("bj", bj);
		
		//if(!kyj.trim().equals("")){
			map.put("kyj", kyj);
		
		//if(!bzl.trim().equals("")){
			map.put("bzl", bzl);
		
		//if(!jx.trim().equals("")){
			map.put("jx", jx);
		
		//if(!ht.trim().equals("")){
			map.put("ht", ht);
		
		System.out.println("aa"+map);
		try {
			new HbaseUtil().saveprice(id, map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
