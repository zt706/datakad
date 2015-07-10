package com.kad.bid;

import java.io.IOException;
import java.net.URLEncoder;
import java.rmi.Naming;
import java.util.List;

import kad.com.RMIService;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class BJ {
	
		public static String search(String url){	
			String msg="";
			CloseableHttpClient client = HttpClientBuilder.create().build();
			//String url = "http://www.so.com/s?ie=utf-8&shb=1&q="+URLEncoder.encode(keyword, "utf-8");  
			CloseableHttpResponse response =null;
			HttpGet get = new HttpGet(url);
			get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
			try {
				 response = client.execute(get);
					String content = EntityUtils.toString(response.getEntity());
					Parser parser = Parser.createParser(content, "gbk");
					
					HasAttributeFilter pro = new HasAttributeFilter("class","clearfix");
					//HasParentFilter filter = new HasParentFilter(introduc);
					TagNameFilter li = new TagNameFilter("li");
					AndFilter andfilter = new AndFilter(li,pro);
					NodeList nodes =null;
					nodes = parser.extractAllNodesThatMatch(andfilter);
					//String[] str =nodes.elementAt(1).toHtml().split("\n");	
					System.out.println(nodes.size());
					msg = nodes.elementAt(8).toHtml().split("￥")[1].split("<")[0];
					System.out.println(nodes.elementAt(8).toHtml().split("￥")[1].split("<")[0]);
					
					client.close();
					return msg;			
			} catch (Exception e) {
				e.printStackTrace();
				try {
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return "0";
			}
								
		}
		
		public static void main(String[] args) throws IOException {
			BJ jianke = new BJ();
					jianke.search("http://www.bjypw.com/goods-4352.html");

		}
}
