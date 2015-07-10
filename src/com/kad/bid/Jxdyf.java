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
import org.htmlparser.tags.Div;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Jxdyf {
	
		public static String search(String url){	
			String msg="";		
			CloseableHttpClient client = HttpClientBuilder.create().build();
			CloseableHttpResponse response =null;
			
			HttpGet get = new HttpGet(url);
			get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
			try {
				 response = client.execute(get);
				 String content = EntityUtils.toString(response.getEntity());
					Parser parser = Parser.createParser(content, "gbk");
					TagNameFilter h1 = new TagNameFilter("h1");		
			        HasAttributeFilter price = new HasAttributeFilter("id","price"); 
			        HasAttributeFilter guige = new HasAttributeFilter("id","liguige");
					NodeList nodes =null;
					//nodes = parser.extractAllNodesThatMatch(h1);
					//String[] str =nodes.toHtml().split("\n");					
					//System.out.println("商品名称"+nodes.toHtml());
					//parser.reset();
					nodes = parser.extractAllNodesThatMatch(price);
					Span s = (Span) nodes.elementAt(0);
					msg = s.getStringText();
					System.out.println("价格"+msg);
					//parser.reset();
					//nodes = parser.extractAllNodesThatMatch(guige);
					//System.out.println("规格"+nodes.toHtml());
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
			
			Jxdyf jianke = new Jxdyf();
					jianke.search("http://www.jxdyf.com/product/26072.html");

		}
}
