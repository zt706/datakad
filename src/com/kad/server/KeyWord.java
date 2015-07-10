package com.kad.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kad.com.RMIService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.kad.db.HbaseUtil;
import com.kad.util.Queue;
import com.kad.util.Test;
import com.kad.util.Word;

public class KeyWord extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public KeyWord() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session  = request.getSession();
		if(session.getAttribute("msg")==null){
			return ;
		}
		JSONObject msg  = JSONObject.fromObject(session.getAttribute("msg").toString());
		List list = (List) msg.get("data");
		  response.setContentType("application/vnd.ms-excel");  
		        response.setHeader("Content-disposition", "attachment;filename=student.xls");  
		        String[] excelHeader = { "关键词", "排名", "URL","搜索引擎","日期"};  
				 HSSFWorkbook wb = new HSSFWorkbook();  
				       HSSFSheet sheet = wb.createSheet("Campaign");  
				       HSSFRow row = sheet.createRow((int) 0);  
				      HSSFCellStyle style = wb.createCellStyle();  
				       style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
				       style.setWrapText(true);  
				        for (int i = 0; i < excelHeader.length; i++) {  
				           HSSFCell cell = row.createCell(i);  
				           cell.setCellValue(excelHeader[i]);  
				           cell.setCellStyle(style);  				          
				           sheet.autoSizeColumn(i);  
				       }  
				
				        for (int i = 0; i < list.size(); i++) {  
				    		
				        	Map map = (Map) list.get(i);
				            row = sheet.createRow(i + 1);  
				          //  System.out.println(map);
				           // System.out.println(map.get("url"));
				            row.createCell(0).setCellValue(map.get("word")+"");
				           row.createCell(1).setCellValue(map.get("rank")+"");
				           HSSFCell cell2 =  row.createCell(2);
				           cell2.setCellStyle(style);
				           cell2.setCellValue(map.get("url")+"");  				         
				           row.createCell(3).setCellValue(map.get("cpc")+"");  
				           row.createCell(4).setCellValue(map.get("date")+"");  
				        }
				        for (int i = 0; i < excelHeader.length; i++) {  
					           sheet.autoSizeColumn(i);  
					       }  

		          OutputStream ouputStream = response.getOutputStream();  
		          wb.write(ouputStream);  
		          ouputStream.flush();  
		          ouputStream.close();  

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
			{
		
				response.setCharacterEncoding("utf-8");   
				String type =request.getParameter("type");
				PrintWriter out = response.getWriter();
				int statu = 0;
				if(type.equals("1")){
					statu =this.addChannel(request);
					out.println(statu);
				}
				if(type.equals("2")){
					statu = this.addKeyWord(request);
					out.println(statu);
				}
				if(type.equals("3")){
					statu = this.addBatchWord(request);
					out.println(statu);
				}
				
					if(type.equals("4")){
						HbaseUtil util = new HbaseUtil();
						String msg = util.getAllChannel();
						//System.out.println(msg);
						out.println(msg);
					}
					if(type.equals("5")){
						String row =request.getParameter("row");
						//System.out.println(row+"dddddddd"); 
						String starttime =request.getParameter("starttime");
						String endtime =request.getParameter("endtime");
						HbaseUtil util = new HbaseUtil();
						String msg="";
						if(row.substring(0, 1).equals("3")){
							msg = util.getRecordByChannelDate(row, starttime, endtime).toString();
						}else{
							msg = util.getWordByChannelDate(row, starttime, endtime).toString();
						}
					HttpSession session  = request.getSession();
					session.setAttribute("msg", msg);
					//System.out.println(msg); 
					//System.out.println(new String(msg.getBytes("gbk"),"utf-8")); 
					out.println(msg);
						//System.out.println(util.getWordByChannelDate("204","2014-07-03","2014-07-05"));
						 
						
					}
					if(type.equals("6")){
						statu = this.delByRow(request);
						out.println(statu);
					}
					if(type.equals("7")){
						statu = this.upChanelName(request);
						out.println(statu);
					}
					if(type.equals("8")){
						statu = this.upChanelURL(request);
						out.println(statu);
					}
					if(type.equals("9")){
						String row = request.getParameter("row");	
						HbaseUtil util = new HbaseUtil();
						statu = util.clearRow(row);
						out.println(statu);
					}
					if(type.equals("10")){
						String row = request.getParameter("row");	
						out.println(1);
						if(row.substring(0, 1).equals("3")){
							statu = this.searchByChannel2(row);
						}else{
							statu = this.searchByChannel(row);
						}
						
					}
					out.flush();
					out.close();			
		}

	private int searchByChannel2(String row) {
		SORecordSearch search =new SORecordSearch();
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String d = format.format(date).substring(0, 10);
		HbaseUtil util = new HbaseUtil();
		RMIService service=getService();
		try {
			List list = util.getWordByRow(row);
			//String url = util.getURLByRow(row);
			String msg = "";
			for(int i=0;i<list.size();i++){
				Queue.getBaidurecordmap().put(list.get(i)+"", row);
			}
			for(int i=0;i<list.size();i++){
				Queue.getSorecordmap().put(list.get(i)+"", row);
			}
			System.out.println("添加多少个"+Queue.getBaidurecordmap().size());
			/*for(int i=0;i<list.size();i++){
				while(service==null){
					Thread.sleep(60000); 
					service=getService();	
				}				
			
						 msg = service.exc("www.360kad.com", list.get(i)+"", 4);
						// util.saveRecordURL("baidu",row+"-"+d,list.get(i)+"",msg);
						 if(msg.equals("0")){
								util.saveRecordURL("baidu",row+"-"+d,list.get(i)+"","0");
								if(Queue.getBaidurecordmap().containsKey(list.get(i)+"")){
									Queue.getBaidurecordmap().remove(list.get(i)+"");
									System.out.println("百度补漏减少1个");
								}						
								continue;
							}
							if(msg.equals("1")){
								util.saveRecordURL("baidu",row+"-"+d,list.get(i)+"","1");
								if(Queue.getBaidurecordmap().containsKey(list.get(i)+"")){
									Queue.getBaidurecordmap().remove(list.get(i)+"");
									System.out.println("百度补漏减少1个");
								}
								continue;
							}
							
							if(msg.equals("-1")){
								if(!Queue.getBaidurecordmap().containsKey(list.get(i)+"")){							
									Queue.getBaidurecordmap().put(list.get(i)+"",row);
									System.out.println("百度补漏加1个");
								}					
								//System.out.println(msg+"tttttttt");
								util.saveRecordURL("baidu",row+"-"+d,list.get(i)+"","-1");
							}
					}	*/					
			
			/*for(int i=0;i<list.size();i++){						
				 msg = search.search("www.360kad.com", list.get(i)+"");
						// util.saveRecordURL("so",row+"-"+d,list.get(i)+"",msg);
						 if(msg.equals("0")){
								util.saveRecordURL("so",row+"-"+d,list.get(i)+"","0");
								if(Queue.getSorecordmap().containsKey(list.get(i)+"")){
									Queue.getSorecordmap().remove(list.get(i)+"");
									System.out.println("360补漏减少1个");
								}						
								continue;
							}
							if(msg.equals("1")){
								util.saveRecordURL("so",row+"-"+d,list.get(i)+"","1");
								if(Queue.getSorecordmap().containsKey(list.get(i)+"")){
									Queue.getSorecordmap().remove(list.get(i)+"");
									System.out.println("360补漏减少1个");
								}
								continue;
							}
							
							if(msg.equals("-1")){
								if(!Queue.getSorecordmap().containsKey(list.get(i)+"")){									
									Queue.getSorecordmap().put(list.get(i)+"",row);
									System.out.println("360补漏加1个");
								}					
								//System.out.println(msg+"tttttttt");
								util.saveRecordURL("so",row+"-"+d,list.get(i)+"","-1");
							}
										
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return 0;
	}

	private int searchByChannel(String row) {
		SoSearch search = new SoSearch();
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String d = format.format(date).substring(0, 10);
		HbaseUtil util = new HbaseUtil();
		RMIService service=getService();
		try {
			List list = util.getWordByRow(row);
			String url = util.getURLByRow(row);
			String msg = "";
			for(int i=0;i<list.size();i++){
				while(service==null){
					Thread.sleep(60000); 
					service=getService();	
				}				
					if(row.substring(0, 1).equals("1")){	
						if(row.equals("1010")||row.equals("1013")){
							 msg = service.exc(url, list.get(i)+"", 3);
						}else{
							 msg = service.exc(url, list.get(i)+"", 1);
						}
					}
					if(row.substring(0, 1).equals("2")){
						// msg = service.exc(url, list.get(i)+"", 2);
						msg = search.search(url, list.get(i)+"");
					}			
			//	System.out.println(row+"第几个:"+i+"关键词名:"+list.get(i)+": "+msg);					
				if(msg.equals("0")){
					Word word = new Word();
					word.setChannel(row);
					word.setUrl(url);
					word.setWord(list.get(i)+"");
					Queue.getLinked().add(word);
					continue;
				}
				if(msg.equals("")){
					util.saveKeyWord("pc",row+"-"+d,list.get(i)+"","null,null");
					continue;
				}
				if(!msg.equals("")){	
					util.saveKeyWord("pc",row+"-"+d,list.get(i)+"",msg);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return 0;
	}

	private int upChanelURL(HttpServletRequest request) {
		String row = request.getParameter("row");	
		String url = request.getParameter("url");	
		HbaseUtil util = new HbaseUtil();
		return util.upChannelURL(row, url);
	}

	private int upChanelName(HttpServletRequest request) {
		String row = request.getParameter("row");	
		String name = request.getParameter("name");	
		HbaseUtil util = new HbaseUtil();
		return util.upChannelName(row, name);

	}

	private int delByRow(HttpServletRequest request) {
		String row = request.getParameter("row");	
		HbaseUtil util = new HbaseUtil();
		return util.delByRow(row);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	public int addChannel(HttpServletRequest request){
		
		String pre = request.getParameter("cpc");	
		String channel =  request.getParameter("channel");
		String url =  request.getParameter("site");
		HbaseUtil util = new HbaseUtil();
		String code="";
		try {
			int i = util.getChannel(pre,channel);
			if(i==0){
				return 2;	
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return 0;
		}
		try {
			code = util.getChannelId(pre);
			if(code.equals("")){
				code=pre+"000";
			}
			util.addChannel((Integer.parseInt(code)+1)+"", channel,url);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
		return 1;
	}
	
	
	public int addKeyWord(HttpServletRequest request){
		HbaseUtil util = new HbaseUtil();
		String pre = request.getParameter("cpc");
		String keyword =  request.getParameter("keyword").trim();
		List words =new ArrayList();
		words.add(keyword);
		//String site =  request.getParameter("site");
		String channel =  request.getParameter("channel");
		if(keyword.equals("")){
			return 0;
		}
			try {
				List list =util.getKeyWord(channel);
				if(list.contains(keyword)){				
					return 2;
				}		
				String code = util.getKeywordId(channel);
				if(code.equals("")){
					code = "100000";
				}
				util.addKeyWord(channel, (Integer.parseInt(code)+1)+"", words);	
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}			
			
		return 1;
	}
	
	
	public int addBatchWord(HttpServletRequest request){
		HbaseUtil util = new HbaseUtil();
		String pre = request.getParameter("cpc");
		String keyword =  request.getParameter("keyword");
		//String site =  request.getParameter("site");
		String channel =  request.getParameter("channel");
		String[] word = keyword.split("\n");
		List list = new ArrayList();
		List words =null;
		try {
			words = util.getKeyWord(channel);
			
			for(int i=0;i<word.length;i++){
				//System.out.println("插入"+keyword);
				keyword = word[i].trim();
					if(keyword.equals("")){
						continue;
					}
				if(!words.contains(keyword)){
					list.add(keyword);
				}
					} 	
				String code = util.getKeywordId(channel);
				if(code.equals("")){
					code = "100000";
				}
				util.addKeyWord(channel, (Integer.parseInt(code)+1)+"", list);			
				return list.size();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return 0;
		}
	}
	
	
	private RMIService getService() {
		// TODO Auto-generated method stub
		try {
			//System.setSecurityManager(new java.rmi.RMISecurityManager());
			//return (RMIService)Naming.lookup("rmi://121.40.137.62:6600/PersonService");
			return (RMIService)Naming.lookup("rmi://121.40.137.62:6600/JmiService");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;  
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;  
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;  
		}
		
	}
}
