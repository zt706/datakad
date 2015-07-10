package com.kad.server;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.kad.db.HbaseUtil;
import com.kad.util.Log;

public class BaiduSearch {

	public static String search(String siteurl,String keyword) throws IOException{
			Log log = new Log();
			//PropertyConfigurator.configure("/opt/tomcat/webapps/datakad/log4.properties"); 
			//PropertyConfigurator.configure("c:/log4j.properties"); 
			String msg="";
		//	HbaseUtil util = new HbaseUtil();
			//word = URLEncoder.encode(word, "utf-8");
			CloseableHttpClient client = HttpClientBuilder.create().build();		
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
			String url = "http://www.baidu.com/s?tn=sitehao123&ie=utf-8&f=8&wd="+URLEncoder.encode(keyword, "utf-8")+"&oq=qi&bs=%E5%A5%87%E4%B8%BD%E5%A5%87%E5%BF%83&rsv_bp=1";  
			CloseableHttpResponse response =null;
			HttpGet get = new HttpGet(url);
			get.setConfig(requestConfig);
			get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
			try {
				 response = client.execute(get);
			} catch (ClientProtocolException e) {
				log.log(keyword+"httpclient连接异常");
				log.log(e.toString());
				e.printStackTrace();
				client.close();
				return "0";
			} catch (IOException e) {
				log.log(keyword+"io连接异常");
				log.log(e.toString());
				e.printStackTrace();
				client.close();
				return "0";
			}
			String content = EntityUtils.toString(response.getEntity());
			//System.out.println("内容页:"+content);
			Parser parser = Parser.createParser(content, "utf-8");
			TagNameFilter filter1 = new TagNameFilter("div");
			HasAttributeFilter filter2 = new HasAttributeFilter("class","result c-container ");			
			AndFilter filter = new AndFilter(filter1,filter2);
			
			try {
				NodeList nodes = parser.extractAllNodesThatMatch(filter);
				System.out.println("找到节点数:"+nodes.size());
				if(nodes.size()==0){
					client.close();
					return "0";
				}
				for(int i=0;i<nodes.size();i++){
					parser = Parser.createParser(i+":"+nodes.elementAt(i).toHtml(), "utf-8");
					//System.out.println(nodes.elementAt(i).toHtml());
					TagNameFilter filter3 = new TagNameFilter("span");
					HasAttributeFilter filter4 = new HasAttributeFilter("class","g");
					AndFilter filter5 = new AndFilter(filter3,filter4);
					NodeList nodes2 = parser.extractAllNodesThatMatch(filter5);
					if(nodes2.elementAt(0)==null){
						continue;
					}
					if(nodes2.elementAt(0).toHtml().contains(siteurl)){
						System.out.println(nodes2.elementAt(0).toHtml());
						Div div = (Div) nodes.elementAt(i);
						String count = div.getAttribute("id");	
						Span span =  (Span) nodes2.elementAt(0);
							String[] str = span.getStringText().split(";");
							String site = str[0].replace("&nbsp", "");
							System.out.println("关键词:"+keyword+"百度排名:"+count+"网站地址:"+site);	
							if(msg.equals("")){
								msg =msg+site+","+count;
							}else{
								msg =msg+","+site+","+count;
							}
					}	
				} 
			} catch (ParserException e) {
				log.log(keyword+"parser解析连接异常");
				log.log(e.toString());
				e.printStackTrace();
				client.close();
				return "0";
			} 
			
			client.close();
			/*try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return msg;
			}  */
			return msg;
	}
		
}
