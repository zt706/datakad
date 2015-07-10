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

public class YK {
	
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
					
					HasAttributeFilter pro = new HasAttributeFilter("class","promotionRightTop");
					//HasParentFilter filter = new HasParentFilter(introduc);
					TagNameFilter li = new TagNameFilter("li");
					
					NodeList nodes =null;
					nodes = parser.extractAllNodesThatMatch(pro);
					//String[] str =nodes.elementAt(1).toHtml().split("\n");	
					parser = Parser.createParser(nodes.toHtml(), "gbk");
					nodes = parser.extractAllNodesThatMatch(li);
					System.out.println(nodes.elementAt(4).toHtml().split("<dd>")[1].split("&")[0]);
					String[] str = nodes.elementAt(1).toHtml().split(">");
					msg = str[4].replace("</b", "");
					System.out.println(str[4].replace("</b", ""));
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
			YK jianke = new YK();
					jianke.search("http://www.ykyao.com/product/1677.html");

		}
}
