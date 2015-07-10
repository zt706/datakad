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

public class Youjk {
	
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
					StringFilter strfilter = new StringFilter("市场价");
					StringFilter strfilter2 = new StringFilter("会员价");		
					TagNameFilter h4 = new TagNameFilter("h4");
					TagNameFilter a = new TagNameFilter("a");
					TagNameFilter li = new TagNameFilter("li");
					TagNameFilter h6 = new TagNameFilter("h6");
					HasAttributeFilter onclick = new HasAttributeFilter("onclick");
					
					HasChildFilter filter5 = new HasChildFilter(h6);
					//HasParentFilter filter2 = new HasParentFilter(filter);
					AndFilter andfilter = new AndFilter(a,onclick);
					AndFilter andfilter2 = new AndFilter(filter5,li);
					NodeList nodes =null;
					//nodes = parser.extractAllNodesThatMatch(h4);
					//String[] str =nodes.toHtml().split("\n");					
					//System.out.println("商品名称"+str[1].trim());
					//parser.reset();
					//nodes = parser.extractAllNodesThatMatch(andfilter);
					//String[] str =nodes.elementAt(1).toHtml().split("\n");					
					//System.out.println(nodes.toHtml());
					//parser.reset();
					//nodes = parser.extractAllNodesThatMatch(strfilter);
					//str =nodes.toHtml().split("：");
					//System.out.println("市场价"+str[1]);
					//parser.reset();
					nodes = parser.extractAllNodesThatMatch(strfilter2);
					String[] str =nodes.toHtml().split("：");
					msg = str[1];
					System.out.println("会员价"+str[1]);
				//	parser.reset();
					//nodes = parser.extractAllNodesThatMatch(andfilter2);
				
					//System.out.println("会员价"+nodes.elementAt(1).toHtml());
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
			
			Youjk jianke = new Youjk();
					jianke.search("http://www.youjk.com/merchandiseView_12308334.html");

		}
}
