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

public class Kxr {
	
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
					
					HasAttributeFilter introduc = new HasAttributeFilter("class","introduc");
					HasParentFilter filter = new HasParentFilter(introduc);
					TagNameFilter p = new TagNameFilter("p");
					TagNameFilter li = new TagNameFilter("li");
					TagNameFilter span = new TagNameFilter("span");
					NodeFilter strfilter = new StringFilter("市 场 价");
					HasChildFilter span2 = new HasChildFilter(strfilter);
					HasParentFilter markfilter = new HasParentFilter(span2);
				
					AndFilter andfilter = new AndFilter(filter,li);		
			        HasAttributeFilter price = new HasAttributeFilter("id","RushWord");                      
					NodeList nodes =null;
					nodes = parser.extractAllNodesThatMatch(andfilter);
					System.out.println(nodes.elementAt(4).toHtml());
					//nodes = parser.extractAllNodesThatMatch(filter);
					//String[] str =nodes.elementAt(1).toHtml().split("\n");	
					//System.out.println(nodes.size());
					//System.out.println("商品名称"+str[1].trim());
					//System.out.println("批准号"+nodes.elementAt(10).toHtml().split("：")[1].replace("</li>", ""));
					//System.out.println("生产厂家"+nodes.elementAt(12).toHtml().split("：")[1].replace("</li>", ""));
					//parser.reset();
					//nodes = parser.extractAllNodesThatMatch(andfilter);
					//Span s = (Span) nodes.elementAt(0);
					/*if(s==null){
						return "0";
					}*/
					//msg = s.getStringText().trim();
					//System.out.println("市场价"+s.getStringText().trim());
					//parser.reset();
				/*	nodes = parser.extractAllNodesThatMatch(price);
					msg = nodes.toHtml().split("\n")[1].trim();
					System.out.println("销售价格"+nodes.toHtml().split("\n")[1].trim());*/
					/*parser.reset();
					nodes = parser.extractAllNodesThatMatch(renzeb);
					str = nodes.toHtml().split("\n");
					System.out.println("编号"+str[1].split("<")[0].trim());
					parser.reset();
					nodes = parser.extractAllNodesThatMatch(markFilter);
					str = nodes.toHtml().split("\n");
					System.out.println("市场价格"+str[2].trim().replace("<dd>", "").replace("</dd>", ""));
					parser.reset();
					nodes = parser.extractAllNodesThatMatch(price);
					str = nodes.toHtml().split("\n");
					System.out.println("销售价格"+str[2].trim().replace("<dd>", "").replace("</dd>", "").replace("<em>", "").replace("</em>", ""));*/
					
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
			Kxr jianke = new Kxr();
					jianke.search("http://www.360kxr.com/product/22418.html");

		}
}
