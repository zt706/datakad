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
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.kad.db.HbaseUtil;
import com.kad.util.Log;

public class MoveSoSearch {

	public static String search(String siteurl,String keyword) throws IOException{
			Log log = new Log();
			PropertyConfigurator.configure("c:/log4j.properties"); 
			String msg="";
			CloseableHttpClient client = HttpClientBuilder.create().build();		
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
			String url = "http://m.so.com/s?q="+URLEncoder.encode(keyword, "utf-8")+"&src=suggest";
				//String url = "http://m.baidu.com/from=381a/s?word="+URLEncoder.encode(keyword, "utf-8")+"&sa=tb&tn=iphone";
			
			CloseableHttpResponse response =null;
			HttpGet get = new HttpGet(url);
			get.setConfig(requestConfig);
			get.setHeader("User-Agent", "osName:iOS");
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
			System.out.println(content);
			Parser parser = Parser.createParser(content, "utf-8");
			HasAttributeFilter filter1 = new HasAttributeFilter("class","res-list");
			TagNameFilter divfilter = new TagNameFilter("div");
			TagNameFilter citefilter = new TagNameFilter("cite");
			
			AndFilter andfilter = new AndFilter(filter1,divfilter);
			try {
				NodeList nodes = parser.extractAllNodesThatMatch(filter1);
				//System.out.println(nodes.size());
				for(int i=0;i<nodes.size();i++){
					//System.out.println(i+"---"+nodes.elementAt(i).toHtml());
					Parser parser2 = Parser.createParser(nodes.elementAt(i).toHtml(), "utf-8");
				//	Tag tag = (Tag) parser2.extractAllNodesThatMatch(citefilter).elementAt(0);
					Div d= (Div) parser2.extractAllNodesThatMatch(citefilter).elementAt(0);
					//System.out.println(i+"---"+d);
					//System.out.println(i+"---"+d.getStringText());
					
				} 
			} catch (ParserException e) {
				log.log(keyword+"parser解析连接异常");
				log.log(e.toString());
				e.printStackTrace();
				client.close();
				return "0";
			} 			
			client.close();
			return msg;
	}
		
}
