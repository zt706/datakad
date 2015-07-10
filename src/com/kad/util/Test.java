package com.kad.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kad.db.HbaseUtil;
import com.kad.server.BaiduSearch;
import com.kad.server.MoveBaiduSearch;
import com.kad.server.MoveSoSearch;
import com.kad.server.SoSearch;
import com.kad.server.WebSerach;

public class Test extends TimerTask {
	public static void main3(String[] args) throws IOException{
		MoveBaiduSearch search = new MoveBaiduSearch();
		//BaiduSearch search = new BaiduSearch();
		//search.search("www.360kad.com/product", "切诺胶囊");
		//Log log = new Log();
		//log.log();
		//SoSearch search2 = new SoSearch();
		//HbaseUtil util = new HbaseUtil();
		//util.upChannelName("2006", "减肥");
		//List list = util.getWordByRow("1001");
		//String url = util.getURLByRow("1001");
		
		//new Test().aa();
		//util.getWordByChannelDate("1001", "2014-07-001", "2014-07-16");
		//System.out.println(util.getWordByChannelDate("1002", "2014-07-01", "2014-07-16"));
		Map map = new HashMap();
		String s = search.search("360kad.com", "玄麦甘桔颗粒");
		System.out.println(s);
		
	//util.upChannelName("1004", new String("用药问答".getBytes(),"iso-8859-1"));
	
		/*for(int i=0;i<list.size();i++){
			
			int count = search.search(url, list.get(i)+"");
			System.out.println(list.get(i)+": "+count);
			if(count!=0&&(count!=2)){
				util.saveKeyWord("1001-2014-07-07",list.get(i)+"",SoSearch.site+","+count);
			}
			if(count==0){
				map.put(list.get(i), url);
			}
			if(count==2){
				util.saveKeyWord("1005-2014-07-05",list.get(i)+"","未知,未知");
			}
		}*/
		
		//Timer timer = new Timer();
		//timer.schedule(new Test(), new Date(), 1000);
		//Date date = new Date();
		//System.out.println(date);
		//date.setHours(0);date.setMinutes(0);date.setSeconds(0);date.setDate(date.getDay()-1);
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("aaaaaaaaaaaa");
	}
	public void aa(){
		HbaseUtil util = new HbaseUtil();
		System.out.println(util.getWordByChannelDate("204", "2014-07-03", "2014-07-05"));
	}
	

public static void main(String[] args) throws IOException{ 
	InputStream is = new FileInputStream("d:\\b.xlsx");

	 XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);

    
    	 XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
    	   for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
    		   Map map = new HashMap();
    		   XSSFRow hssfRow = hssfSheet.getRow(rowNum);
    		   XSSFCell id = hssfRow.getCell(1);
    		   if(!(Math.round(id.getNumericCellValue())+"").trim().equals("")){
    			   map.put("id", (Math.round(id.getNumericCellValue())+"").trim());
    		   }
    		   XSSFCell name = hssfRow.getCell(2);
    		   if(!name.getStringCellValue().trim().equals("")){
    			   map.put("name",name.getStringCellValue().trim());
    		   }
    		   XSSFCell jk = hssfRow.getCell(3);
    		   if(jk!=null&&(!jk.getStringCellValue().trim().equals(""))){
    			   map.put("jk",jk.getStringCellValue().trim());
    		   }
    		   XSSFCell kx = hssfRow.getCell(4);
    		  // System.out.println(hssfRow.getLastCellNum()+"aaa"+hssfRow.getCell(5));
    		   if(kx!=null&&(!kx.getStringCellValue().equals(""))){
    			   map.put("kx",kx.getStringCellValue() );
    		   }
    		   XSSFCell yh = hssfRow.getCell(5);
    		   if(yh!=null&&(!yh.getStringCellValue().equals(""))){
    			   map.put("yh",yh.getStringCellValue() );
    		   }
    		   XSSFCell kd = hssfRow.getCell(6);
    		   if(kd!=null&&(!kd.getStringCellValue().equals(""))){
    			   map.put("kd",kd.getStringCellValue() );
    		   }
    		   XSSFCell jy = hssfRow.getCell(7);
    		   if(jy!=null&&(!jy.getStringCellValue().equals(""))){
    			   map.put("jy",jy.getStringCellValue() );
    		   }
    		   XSSFCell zk = hssfRow.getCell(8);
    		   if(zk!=null&&(!zk.getStringCellValue().equals(""))){
    			   map.put("zk",zk.getStringCellValue() );
    		   }
    		   XSSFCell yf = hssfRow.getCell(9);
    		   if(yf!=null&&(!yf.getStringCellValue().equals(""))){
    			   map.put("yf",yf.getStringCellValue() );
    		   }
    		   XSSFCell kzj = hssfRow.getCell(10);
    		   if(kzj!=null&&(!kzj.getStringCellValue().equals(""))){
    			   map.put("kzj",kzj.getStringCellValue() );
    		   }
    		   XSSFCell nb = hssfRow.getCell(11);
    		   if(nb!=null&&(!nb.getStringCellValue().equals(""))){
    			   map.put("nb",nb.getStringCellValue() );
    		   }
    		   XSSFCell fgt = hssfRow.getCell(12);
    		   if(fgt!=null&&(!fgt.getStringCellValue().equals(""))){
    			   map.put("fgt",fgt.getStringCellValue() );
    		   }
    		   XSSFCell yk = hssfRow.getCell(13);
    		   if(yk!=null&&(!yk.getStringCellValue().equals(""))){
    			   map.put("yk",yk.getStringCellValue() );
    		   }
    		   XSSFCell lbx = hssfRow.getCell(14);
    		   if(lbx!=null&&(!lbx.getStringCellValue().equals(""))){
    			   map.put("lbx",lbx.getStringCellValue() );
    		   }
    		   XSSFCell bj = hssfRow.getCell(15);
    		   if(bj!=null&&(!bj.getStringCellValue().equals(""))){
    			   map.put("bj",bj.getStringCellValue() );
    		   }
    		   XSSFCell kyj = hssfRow.getCell(16);
    		   if(kyj!=null&&(!kyj.getStringCellValue().equals(""))){
    			   map.put("kyj",kyj.getStringCellValue() );
    		   }
    		   XSSFCell gk = hssfRow.getCell(17);
    		   if(gk!=null&&(!gk.getStringCellValue().equals(""))){
    			   map.put("gk",gk.getStringCellValue() );
    		   }
    		   XSSFCell bzl = hssfRow.getCell(18);
    		   if(bzl!=null&&(!bzl.getStringCellValue().equals(""))){
    			   map.put("bzl",bzl.getStringCellValue() );
    		   }
    		   XSSFCell jx = hssfRow.getCell(19);
    		   if(jx!=null&&(!jx.getStringCellValue().equals(""))){
    			   map.put("jx",jx.getStringCellValue() );
    		   }
    		   XSSFCell jz = hssfRow.getCell(20);
    		   if(jz!=null&&(!jz.getStringCellValue().equals(""))){
    			   map.put("jz",jz.getStringCellValue() );
    		   }
    		   XSSFCell lyk = hssfRow.getCell(21);
    		   if(lyk!=null&&(!lyk.getStringCellValue().equals(""))){
    			   map.put("lyk",lyk.getStringCellValue() );
    		   }
    		   XSSFCell ht = hssfRow.getCell(22);
    		   if(ht!=null&&(!ht.getStringCellValue().equals(""))){
    			   map.put("ht",ht.getStringCellValue() );
    		   }
    		   XSSFCell kcd = hssfRow.getCell(23);
    		   if(kcd!=null&&(!kcd.getStringCellValue().equals(""))){
    			   map.put("kcd",kcd.getStringCellValue() );
    		   }
    		   XSSFCell ss = hssfRow.getCell(24);
    		   if(ss!=null&&(!ss.getStringCellValue().equals(""))){
    			   map.put("ss",ss.getStringCellValue() );
    		   }
    		  
    		 new HbaseUtil().saveopponent(map);
    		 
    		  // System.out.println(ss.getStringCellValue().trim().equals(""));
    	   }
     
	//WebSerach websearch = new WebSerach();
	//System.out.println(websearch.search("www.360kad.com/Categor", "http://www.360kad.com/fsgk/100075.shtml ", "5"));
    /*final String path = "http://182.254.197.201:8080/crawler/servlet/Search?type=1&url=www.360kad.com&keyword=+"+URLEncoder.encode("人参", "gbk");
    CloseableHttpClient client = HttpClientBuilder.create().build();		
	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
	CloseableHttpResponse response =null;
	HttpGet get = new HttpGet(path);
	get.setConfig(requestConfig);
	try {
		 response = client.execute(get);
	}catch (Exception e) {
		e.printStackTrace();
		client.close();
		
	}
	String content = EntityUtils.toString(response.getEntity());
	System.out.println(content);*/
	} 
}
