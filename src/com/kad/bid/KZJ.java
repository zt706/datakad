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

public class KZJ {
	
		public static String search(String url){	
			String msg="";		
			CloseableHttpClient client = HttpClientBuilder.create().build();
			CloseableHttpResponse response =null;
			System.out.println("aaaaa");
			HttpGet get = new HttpGet(url);
			get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
			String content="";
			try {
				response = client.execute(get);
				content = EntityUtils.toString(response.getEntity());
				Parser parser = Parser.createParser(content, "gbk");
				HasAttributeFilter price = new HasAttributeFilter("class","txt101");
				HasAttributeFilter sep = new HasAttributeFilter("class","radio radio_4");
				StringFilter strfilter = new StringFilter("￥");
				
				NodeList nodes =null;
				 response = client.execute(get);
				/* nodes = parser.extractAllNodesThatMatch(strfilter);
					System.out.println(nodes.toHtml());
					parser.reset();
					nodes = parser.extractAllNodesThatMatch(strong);
					parser.reset();
					nodes = parser.extractAllNodesThatMatch(sep);
					System.out.println(nodes.toHtml());
					parser.reset();*/
					nodes = parser.extractAllNodesThatMatch(strfilter);
					msg = nodes.elementAt(0).toHtml().replace("￥", "");
					System.out.println(nodes.elementAt(0).toHtml());
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
			
			KZJ jianke = new KZJ();
					jianke.search("http://www.kzj365.com/goods-1000027171.html");

		}
}
