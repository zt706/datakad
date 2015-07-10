package com.kad.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kad.db.OracleUtil;
import com.kad.util.User;

public class Target extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Target() {
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

		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
				response.setCharacterEncoding("GBK");
				 response.setContentType("text/html;charset=GB2312");
		//PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		//request.setCharacterEncoding("GBK");
		//response.setCharacterEncoding("GBK");
		// response.setContentType("text/html;charset=GB2312");
		System.out.println("aa Target.java == "+request.getHeader("referer"));
	
		if(request.getHeader("referer")==null){
			request.getRequestDispatcher("/").forward(request,response);
			return;
		}
		if(type.equals("1")){
			request.getRequestDispatcher("/WEB-INF/mode2.jsp").forward(request,response);
		}
		if(type.equals("2")){
			request.getRequestDispatcher("/WEB-INF/produce.jsp").forward(request,response);
			return;
		}
		if(type.equals("3")){
			request.getRequestDispatcher("/WEB-INF/traffic.jsp").forward(request,response);
			return;
		}
		if(type.equals("4")){
			request.getRequestDispatcher("/WEB-INF/keyword.jsp").forward(request,response);
			return;
		}
		if(type.equals("5")){
			request.getRequestDispatcher("/WEB-INF/produce.jsp").forward(request,response);
			return;
		}
		
		//out.println("哈aa哈");
		//out.flush();
		//out.close();
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
		request.setCharacterEncoding("GBK");
				response.setCharacterEncoding("GBK");
				 response.setContentType("text/html;charset=GB2312");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		System.out.println(username+"--------"+passwd);
		User user = new OracleUtil().getUser(username,passwd);
		
		if(user==null){
			response.sendRedirect("http://127.0.0.1:8080");
			return;
		}else{
			System.out.println(user.getRealname()+"bb");
			request.getSession().setAttribute("u", user);
			request.getRequestDispatcher("/WEB-INF/mode2.jsp").forward(request,response);

		}
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
