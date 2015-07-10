package com.kad.server;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import net.sf.json.JSONObject;

import com.kad.db.OracleUtil;

public class Search extends HttpServlet{
	 @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
		 String starttime = "";
		 String endtime = "";
		 String date = "";
		 resp.setContentType("text/html;charset=utf-8");
	       resp.setContentType("text/xml");
	      resp.setHeader("Cache-Control", "no-cache");
	      String key = req.getParameter("key");
	    if(key.equals("")){
	    	key ="*";
	    }
	    System.out.println("aa"+req.getParameter("starttime")+"-------"+req.getParameter("endtime")); 
	    if(req.getParameter("starttime").equals("")||req.getParameter("endtime").equals("")){
	    	date =URLEncoder.encode("firstIndexedDate:[NOW/DAY-1DAY TO *]","utf-8");
	    }else{    	
	    	  starttime = req.getParameter("starttime").replace(".", "-")+"T00:00:00Z";
	  	    endtime = req.getParameter("endtime").replace(".", "-")+"T00:00:00Z";
	  	date=  URLEncoder.encode("firstIndexedDate:{"+starttime+" TO "+endtime+"]","utf-8");
	    }
	   
	    System.out.println(starttime+"------"+endtime); 
	    
	    String source = req.getParameter("source");
	      String[] sources = source.split(",");
	      String fq = "";
	      for(int i=0;i<sources.length;i++){
	    	 fq = fq+"site:\""+sources[i]+"\""+"OR "; 
	      }
	    fq = fq.substring(0, fq.length()-3);
	    if(source.equals("")){
	    	fq ="";
	    }else{
	    	 fq = URLEncoder.encode(fq,"utf-8");
	    }
	   
	      key = URLEncoder.encode(key,"utf-8");
	     
			//key=new String(key.getBytes("8859_1"),"utf-8"); 
			//key = URLEncoder.encode(key);	
	      String sort = req.getParameter("sort");
	      int page = Integer.parseInt(req.getParameter("page"));
	      System.out.println("dd"+sort);
	      String cid1 = req.getParameter("cid1");
	      String cid2 = req.getParameter("cid2");
	      String price = req.getParameter("price");
	      if(sort.equals("1")){
	    	  sort = "&sort="+ URLEncoder.encode("salesPrice desc","utf-8");
	      }if(sort.equals("0")){
	    	  sort ="&sort="+ URLEncoder.encode("salesPrice asc","utf-8");
	      }if(sort.equals("2")){
	    	  sort = "";
	      }
	      String type = req.getParameter("type");
	      String isnew = req.getParameter("isnew");
	      //sort = URLEncoder.encode(sort,"utf-8");
			String url ="http://192.168.7.12:8080/solr/select?q="+key+sort+"&start="+(page-1)*20+"&rows=20&pt=none&d=10&fq="+fq+"&wt=json&facet=on&facet.field=site&facet.field=cid1&facet.field=cid2&facet.mincount=1&defType=edismax&mm=100%25&facet.pivot=site%2Ccid1&indent=true&stopwords=true&qf=text^0.5%20title^1%20pname^5%20promotion^1%20description^1";
			 if(!source.equals("")){
				url=url+"&facet.field=cid1";
				url=url+"&facet.field=cid2";
			 }
			 if(!cid1.equals("")){
				 cid1 = URLEncoder.encode(cid1,"utf-8");
				 url = url+"&fq=cid1:\""+cid1+"\"";
			 }
			 if(!cid2.equals("")){
				 cid2 = URLEncoder.encode(cid2,"utf-8");
				 url = url+"&fq=cid2:\""+cid2+"\"";
			 }
			 if(price.equals("1")){
				 //url =url+"&f.salesPrice.facet.range.start=0&f.salesPrice.facet.range.end=200";
				 url = url+"&fq=salesPrice:%5B0.0+TO+200.0%7D";
			 }
			 if(price.equals("2")){
				// url =url+"&f.salesPrice.facet.range.start=200&f.salesPrice.facet.range.end=500";
				 url = url+"&fq=salesPrice:%5B200.0+TO+500.0%7D";
 			}
 			if(price.equals("3")){
 				// url =url+"&f.salesPrice.facet.range.start=500&f.salesPrice.facet.range.end=90000";
 				 url = url+"&fq=salesPrice:%5B500.0+TO+90000.0%7D";
 			}
 			System.out.println("aaaaaaa"+type);
 			if(isnew.equals("1")){
 				url = url+"&fq="+date;
 				//url = url+"&fq="+URLEncoder.encode("firstIndexedDate:{"+starttime+" TO "+endtime+"]","utf-8");
 			}
			// 创建一个客户端，相当于打开一个浏览器
      HttpClient httpClient = new HttpClient();
      // 创建一个get方法，相当于在浏览器地址栏输入一个地址
		System.out.println(url);

      GetMethod getMethod = new GetMethod(url);
      // 回车，获取响应状态码
      int statusCode = httpClient.executeMethod(getMethod);
      // 查看命中情况，可以获得的东西还有很多，比如head、cookies等
      System.out.println(statusCode+"---"+getMethod.getResponseBodyAsString());	 
      JSONObject j= JSONObject.fromObject(getMethod.getResponseBodyAsString());
      System.out.println( j);	  
      getMethod.releaseConnection();
      //resp.getWriter().write( new String(j.toString().getBytes("utf-8"),"8859_1"));
       resp.getWriter().write(j.toString().replaceAll("www.j1.com", "健一网").replaceAll("www.jianke.com", "健客网")
    		   .replaceAll("www.baiji.com.cn", "康德乐大药房").replaceAll("www.bjypw.com", "北京药品网").replaceAll("www.yaofang.cn", "药房网")
    		   .replaceAll("www.111.com.cn", "壹药网") .replaceAll("www.lbxcn.com", "老百姓大药房").replaceAll("www.kzj365.com", "康之家药房网").replaceAll("site,cid1", "sitecid1")
    		   .replaceAll("www.ykyao.com", "云开大药房").replaceAll("www.bdkyf.com", "百德康药房").replaceAll("www.huatuoyf.com", "华佗药房")
    		   .replaceAll("www.12yao.com", "12药网").replaceAll("www.360kxr.com", "开心人").replaceAll("www.800pharm.com", "800方").replaceAll("www.818.com", "818医药网")
    		   .replaceAll("www.ehaoyao.com", "好药师").replaceAll("www.jxdyf.com", "金象大药房").replaceAll("www.star365.com", "海王星辰")
    		   .replaceAll("康之家药房网/", "www.kzj365.com/").replaceAll("健一网/", "www.j1.com/").replaceAll("药房网/", "www.yaofang.cn/").replaceAll("老百姓大药房/", "www.lbxcn.com/")
    		   .replaceAll("百德康药房/", "www.bdkyf.com/").replaceAll("壹药网/", "www.111.com.cn/").replaceAll("华佗药房/", "www.huatuoyf.com/").replaceAll("健客网/", "www.jianke.com/")
    		   .replaceAll("北京药品网/", "www.bjypw.com/").replaceAll("云开大药房/", "www.ykyao.com/").replaceAll("康德乐大药房/", "www.baiji.com.cn/")
    		   .replaceAll("12药网/", "www.12yao.com/").replaceAll("开心人/", "www.360kxr.com/").replaceAll("800方/", "www.800pharm.com/")
    		   .replaceAll("好药师/", "www.ehaoyao.com/").replaceAll("金象大药房/", "www.jxdyf.com/").replaceAll("海王星辰/", "www.star365.com/").replaceAll("818医药网/", "www.818.com/")
    		   );

	     }
	 
	 @Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
		 resp.setContentType("text/html;charset=utf-8");   
		 resp.setCharacterEncoding("utf-8");   
		 resp.setHeader("Charset", "utf-8");   

	       resp.setContentType("text/xml");
	      resp.setHeader("Cache-Control", "no-cache");	       	        
				String key = req.getParameter("key");
				String source = req.getParameter("source");
				System.out.println(source);

				//key=new String(key.getBytes("8859_1"),"utf-8"); 
				key = URLEncoder.encode(key);	
				System.out.println(key);
				String url ="http://192.168.7.12:8080/solr/select?q="+key+"&pt=none&d=10&fq=&wt=json&facet=on&facet.field=cid1&facet.mincount=1";
	      // 创建一个客户端，相当于打开一个浏览器
	        HttpClient httpClient = new HttpClient();
	        // 创建一个get方法，相当于在浏览器地址栏输入一个地址
			System.out.println(url);

	        GetMethod getMethod = new GetMethod(url);
	        // 回车，获取响应状态码
	        int statusCode = httpClient.executeMethod(getMethod);
	        // 查看命中情况，可以获得的东西还有很多，比如head、cookies等
	        System.out.println(getMethod.getResponseBodyAsString());	 
	        JSONObject j= JSONObject.fromObject(getMethod.getResponseBodyAsString());
	        System.out.println( j);	  
	        getMethod.releaseConnection();
	        
	         resp.getWriter().write(j.toString());
	       
	     }

	 public static void main(String[] args) throws HttpException, IOException{
			String s =  "http://192.168.7.12:8080/solr/select?q=*.*&start=0&rows=10&pt=none&d=20&fq=site:%22www.j1.com%22&wt=json&facet=on&facet.field=site&facet.field=cid1&facet.mincount=1&facet.range=salesPrice&f.salesPrice.facet.range.start=0&f.salesPrice.facet.range.end=60000&f.salesPrice.facet.range.gap=500ce&f.salesPrice.facet.range.start=0&f.salesPrice.facet.range.end=60000&f.salesPrice.facet.range.gap=500&fq=";
			
			String d = "firstIndexedDate:[NOW/DAY-1DAY TO *]";
			s=s+ URLEncoder.encode(d,"utf-8");
	      HttpClient httpClient = new HttpClient();
	      GetMethod getMethod = new GetMethod(s);
	      int statusCode = httpClient.executeMethod(getMethod);
	      System.out.println(statusCode+"---"+getMethod.getResponseBodyAsString());	 

		
		System.out.println(s);
	 }
}
