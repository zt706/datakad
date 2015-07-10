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

public class ZK100 {
	
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
					HasAttributeFilter price = new HasAttributeFilter("id","zaikangjiage");
					HasAttributeFilter licla = new HasAttributeFilter("class");
					TagNameFilter h1 = new TagNameFilter("h1");
					TagNameFilter h3 = new TagNameFilter("h3");
					TagNameFilter li = new TagNameFilter("li");
					AndFilter andfilter = new AndFilter(li,licla);			
					NodeList nodes =null;
					//nodes = parser.extractAllNodesThatMatch(h1);
					//String[] str =nodes.elementAt(1).toHtml().split("\n");					
					//System.out.println(nodes.toHtml().replace("<h1>", "").replace("<h1>", ""));
					//parser.reset();
					//nodes = parser.extractAllNodesThatMatch(h3);
					//System.out.println(nodes.elementAt(7).toHtml());
					//System.out.println("名称"+nodes.elementAt(8).toHtml().split("：")[1].replace("</h3>", "").trim());
					//System.out.println("生产企业"+nodes.elementAt(10).toHtml().split("：")[1].replace("</h3>", "").trim());
					//System.out.println(nodes.elementAt(12).toHtml().split("：")[2].replace("</h3>", "").trim());
					//parser.reset();
					nodes = parser.extractAllNodesThatMatch(price);
					Span s = (Span) nodes.elementAt(0);
					msg = s.getStringText();
					System.out.println("价格"+s.getStringText().replace("￥", ""));
					//parser.reset();
					//nodes = parser.extractAllNodesThatMatch(andfilter);			
					//System.out.println(nodes.toHtml());
					/*if(nodes.size()==0){				
						client.close();
						return "0";
					}	*/				
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
			ZK100 jianke = new ZK100();
					jianke.search("http://www.zk100.com/product/22777.htm");

		}
}
