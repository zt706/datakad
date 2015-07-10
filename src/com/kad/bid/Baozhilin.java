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

public class Baozhilin {
	
		public static String search(String url){	
			String msg="";		
			CloseableHttpClient client = HttpClientBuilder.create().build();
			CloseableHttpResponse response =null;		
			HttpGet get = new HttpGet(url);
			get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
			try {
				 response = client.execute(get);
				 String content = EntityUtils.toString(response.getEntity());
					content = new String(content.getBytes("ISO-8859-1"),"gbk");
					Parser parser = Parser.createParser(content, "gbk");		
					TagNameFilter td = new TagNameFilter("td");
					HasParentFilter filter3 = new HasParentFilter(td);			
					NodeList nodes =null;
					/*nodes = parser.extractAllNodesThatMatch(h2);
					System.out.println("商品名称"+nodes.toHtml());
					parser.reset();*/
					nodes = parser.extractAllNodesThatMatch(filter3);
					Span s= (Span) nodes.elementAt(9);
					System.out.println("价格"+s.getStringText().replace("￥", "").replace("元", ""));
					msg = s.getStringText().replace("￥", "").replace("元", "");
					client.close();
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
			
			return msg;									
		}
		
		public static void main(String[] args) throws IOException {
			
			Baozhilin jianke = new Baozhilin();
					jianke.search("http://www.gzbaozhilin.com/Html/urop/2162.html");

		}
}
