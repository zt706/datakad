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
import org.htmlparser.tags.LabelTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class NB {
	
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
					
					HasAttributeFilter pro = new HasAttributeFilter("class","promotionRightTop");
					//HasParentFilter filter = new HasParentFilter(introduc);
					TagNameFilter div = new TagNameFilter("div");
					TagNameFilter td = new TagNameFilter("td");
					NodeFilter strfilter = new StringFilter("市 场 价");
					HasParentFilter par = new HasParentFilter(td);
					AndFilter andfilter = new AndFilter(par,div);		          
					NodeList nodes =null;
					nodes = parser.extractAllNodesThatMatch(andfilter);					
					//String[] str =nodes.elementAt(1).toHtml().split("\n");	
					//msg = nodes.elementAt(13).toHtml();
				
					//System.out.println(nodes.elementAt(10).toHtml());
					Div d = (Div) nodes.elementAt(13);
					msg = d.getStringText();
					System.out.println(d.getStringText());
					client.close();
					return msg;		
					
			} catch (Exception e) {
				e.printStackTrace();
				try {
					client.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return "0";
			}
														
		}
		
		public static void main(String[] args) throws IOException {
			NB jianke = new NB();
					jianke.search("http://www.nbdyf.com/yaopinmulu/4269.html");

		}
}
