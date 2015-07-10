package com.kad.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kad.db.OracleUtil;
import com.kad.db.RealHbaseUtil;
import com.kad.util.KeyComparator;
import com.kad.util.ValueComparator;

public class RealCount extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RealCount() {
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
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
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
		response.setContentType("text/hmtl;charset=utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		JSONObject json = null;
		if(type.equals("1")){
			 json = this.producequery();
		}
		if(type.equals("2")){
			 json = this.ipquery();
		}if(type.equals("3")){
			 json = this.visitkeywordquery();
		}if(type.equals("4")){
			 json = this.sourcePage();
		}
		if(type.equals("5")){
			json = this.page();
		}
		
		System.out.println(json.toString());
		out.write(json.toString());
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

	public JSONObject producequery(){
		Map map2 = RealHbaseUtil.producequery();
		  ValueComparator bvc =  new ValueComparator(map2);  
		  TreeMap treemap = new TreeMap<String, String>(bvc);
		  treemap.putAll(map2);
		List list = new ArrayList();
		Iterator iterator = treemap.entrySet().iterator();	
		 //System.out.println(treemap.firstKey());
		 List proname = new ArrayList();
		 List procount = new ArrayList();
		 Map map3 = new HashMap();
		while(iterator.hasNext()){
			HashMap map = new HashMap();
			 Map.Entry<String, String> entry1=(Map.Entry<String, String>)iterator.next();
					 String key = entry1.getKey();
					 String value = entry1.getValue();
					 System.out.println(key+"---"+value);
			if(list.size()>20){
				break;
			}else{
				String str ="";
				//String str = OracleUtil.getProduceName(Integer.parseInt(key));
				String[] s = str.split(",");
				String name ="";
				String pic = "";
				if(s.length>1){
					name = str.split(",")[0];
					pic = str.split(",")[1];
				}			
				map.put("id", key);
				map.put("count", value);
				map.put("name", name);
				map.put("pic", pic);
				proname.add(name);
				procount.add(Integer.parseInt(value));
			}
			list.add(map);
		}
		map3.put("name", proname);
		map3.put("count", procount);
		map3.put("data", list);
		JSONObject json = JSONObject.fromObject(map3);
		return json;
	}
	
	public JSONObject ipquery(){
		Date date = new Date();
		Map<String,String> map = RealHbaseUtil.ipquery(date);
		Date date2 =new Date();
		date2.setHours(date.getHours()-24);
		Map<String,String> map2 = RealHbaseUtil.ipquery(date2);
		KeyComparator bvc =  new KeyComparator(map);  
		KeyComparator bvc2 =  new KeyComparator(map2);  
		  TreeMap<String,String> treemap = new TreeMap<String, String>(bvc);
		  TreeMap<String,String> treemap2 = new TreeMap<String, String>(bvc2);
		  treemap.putAll(map);
		  treemap2.putAll(map2);
		  
		List values = new ArrayList();
		List yesvalues = new ArrayList();
		List times = new ArrayList();
		for (String key : treemap.keySet()) {
			   System.out.println("key= "+ key + " and value= " + map.get(key));
			   times.add(key);
			   values.add(Double.parseDouble(map.get(key)));
			  }
		for (String key : treemap2.keySet()) {
			   System.out.println("key= "+ key + " and value= " + map2.get(key));
			   yesvalues.add(Double.parseDouble(map2.get(key)));
			  }
		
		Map<String,String> map4 = RealHbaseUtil.ipquery2(date);
		Map<String,String> map5 = RealHbaseUtil.ipquery2(date2);
		KeyComparator bvc3 =  new KeyComparator(map4);  
		KeyComparator bvc4 =  new KeyComparator(map5);  
		  TreeMap<String,String> treemap3 = new TreeMap<String, String>(bvc3);
		  TreeMap<String,String> treemap4 = new TreeMap<String, String>(bvc4);
		  treemap3.putAll(map4);
		  treemap4.putAll(map5);
		  
		List minvalues = new ArrayList();
		List minyesvalues = new ArrayList();
		List mintimes = new ArrayList();
		for (String key : treemap3.keySet()) {
			   System.out.println("bbbkey= "+ key + " and value= " + map4.get(key));
			   mintimes.add(key);
			   minvalues.add(Double.parseDouble(map4.get(key)));
			  }
		for (String key : treemap4.keySet()) {
			   System.out.println("bbbkey= "+ key + " and value= " + map5.get(key));
			   minyesvalues.add(Double.parseDouble(map5.get(key)));
			  }
		
		HashMap map3 = new HashMap();
		map3.put("times", times);
		map3.put("values", values);
		map3.put("yesvalues", yesvalues);
		map3.put("mintimes", mintimes);
		map3.put("minvalues", minvalues);
		map3.put("minyesvalues", minyesvalues);
		JSONObject json = JSONObject.fromObject(map3);
		return json;
	}
	
	public JSONObject visitkeywordquery(){
		Map map2 = RealHbaseUtil.visitkeyword();
		  ValueComparator bvc =  new ValueComparator(map2);  
		  TreeMap treemap = new TreeMap<String, String>(bvc);
		  treemap.putAll(map2);
		List list = new ArrayList();
		Iterator iterator = treemap.entrySet().iterator();	
		 //System.out.println(treemap.firstKey());
		 List proname = new ArrayList();
		 List procount = new ArrayList();
		 Map map3 = new HashMap();
		while(iterator.hasNext()){
			HashMap map = new HashMap();
			 Map.Entry<String, String> entry1=(Map.Entry<String, String>)iterator.next();
					 String key = entry1.getKey();
					 String value = entry1.getValue();
					 System.out.println(key+"---"+value);
						map.put("id", key);
						map.put("count", value);
						map.put("name", key);
						map.put("pic", "");
						proname.add(key);
						procount.add(Integer.parseInt(value));
			if(list.size()>20){
				break;
			}
			list.add(map);
		}
		map3.put("name", proname);
		map3.put("count", procount);
		map3.put("data", list);
		JSONObject json = JSONObject.fromObject(map3);
		return json;
	}
	
	public JSONObject sourcePage(){		
		Map map2 = RealHbaseUtil.pagesource();
		  ValueComparator bvc =  new ValueComparator(map2);  
		  TreeMap treemap = new TreeMap<String, String>(bvc);
		  treemap.putAll(map2);
		List list = new ArrayList();
		Iterator iterator = treemap.entrySet().iterator();	
		 Map map3 = new HashMap();
		while(iterator.hasNext()){
			HashMap map = new HashMap();
			 Map.Entry<String, String> entry1=(Map.Entry<String, String>)iterator.next();
					 String key = entry1.getKey();
					 String value = entry1.getValue();
					 System.out.println(key+"---"+value);
			if(list.size()>20){
				break;
			}else{
				String source =key.split(",")[0];
				String page = key.split(",")[1];			
				map.put("count", value);
				map.put("source", source);
				map.put("page",page);
			}
			list.add(map);
		}
		map3.put("data", list);
		JSONObject json = JSONObject.fromObject(map3);
		return json;		
	}
	
public JSONObject page(){	
		Map map2 = RealHbaseUtil.pagesource();
		List list = new ArrayList();
		Iterator iterator = map2.entrySet().iterator();	
		HashMap map = new HashMap();
		while(iterator.hasNext()){			
			 Map.Entry<String, String> entry1=(Map.Entry<String, String>)iterator.next();
					 String key = entry1.getKey();
					 String value = entry1.getValue();
					 System.out.println(key+"---"+value);				
				String page = key.split(",")[1];	
				if(map.get(page)==null){
					map.put(page, value);
				}else{
					map.put(page, (Integer.parseInt(map.get(page)+"")+Integer.parseInt(value))+"");
				}
		}
		 ValueComparator bvc =  new ValueComparator(map); 
		 TreeMap treemap = new TreeMap<String, String>(bvc);
		 treemap.putAll(map);
		 iterator = treemap.entrySet().iterator();
		 List list3 = new ArrayList();
		 while(iterator.hasNext()){
			List list2 = new ArrayList();
			 Map.Entry<String, String> entry2=(Map.Entry<String, String>)iterator.next();
		//	 map5.put(entry2.getKey(), Integer.parseInt(entry2.getValue()));
			 list2.add(entry2.getKey());
			 list2.add(Integer.parseInt(entry2.getValue()));
			list3.add(entry2.getKey());
			 list3.add(Integer.parseInt(entry2.getValue()));
			 list.add(list2);
		 }
		 Map map3 = new HashMap();	 
		map3.put("data", list);
		map3.put("data2", list3);
		JSONObject json = JSONObject.fromObject(map3);
		return json;
		
	}
}
