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

public class J1 {
	
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
					HasAttributeFilter price = new HasAttributeFilter("id","marketPrice");
					HasAttributeFilter ecPrice = new HasAttributeFilter("id","ecPrice");
					HasAttributeFilter tabledescr = new HasAttributeFilter("class","tabledescr");			
					HasAttributeFilter spe = new HasAttributeFilter("class","newformwrap");
					HasParentFilter filter = new HasParentFilter(spe);			
					TagNameFilter h1 = new TagNameFilter("h1");
				
					NodeList nodes =null;
					//nodes = parser.extractAllNodesThatMatch(h1);
					//String[] str =nodes.elementAt(1).toHtml().split("\n");	
					
					//System.out.println("市场价格"+nodes.toHtml().split("\n")[1].trim().replace("<h1>", "").replace("</h1>", ""));
					/*parser.reset();
					nodes = parser.extractAllNodesThatMatch(price);
					InputTag input = (InputTag) nodes.elementAt(0);
					input.getAttribute("value");
					System.out.println(input.getAttribute("value"));
					parser.reset();*/
					nodes = parser.extractAllNodesThatMatch(ecPrice);
					InputTag input = (InputTag) nodes.elementAt(0);
					msg = input.getAttribute("value");
					System.out.println(input.getAttribute("value"));
					//parser.reset();
					//nodes = parser.extractAllNodesThatMatch(tabledescr);
					//System.out.println("编号"+nodes.elementAt(12).toHtml());
					//System.out.println("生产厂家"+nodes.elementAt(13).toHtml());
					/*parser.reset();
					nodes = parser.extractAllNodesThatMatch(filter);
					System.out.println(nodes.toHtml());*/
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
			J1 jianke = new J1();
					jianke.search("http://www.j1.com/product/44016-44016.html");

		}
}
