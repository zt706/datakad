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
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Baiji {
	
		public static String search(String url){	
			String msg="";
			//HbaseUtil util = new HbaseUtil();
			CloseableHttpClient client = HttpClientBuilder.create().build();
			CloseableHttpResponse response =null;
			HttpGet get = new HttpGet(url);
			get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
			try {
						response = client.execute(get);
				 		String  content = EntityUtils.toString(response.getEntity());
						Parser parser = Parser.createParser(content, "gbk");
						HasAttributeFilter price = new HasAttributeFilter("id","ECS_SHOPPRICE");
				        HasAttributeFilter mark = new HasAttributeFilter("id","ECS_MARKETPRICE");                      
						NodeList nodes =null;
						/*nodes = parser.extractAllNodesThatMatch(mark);						
						Div div = (Div) nodes.elementAt(0);
						System.out.println("市场价格"+div.getStringText());
						parser.reset();*/
						nodes = parser.extractAllNodesThatMatch(price);
						String[] str = nodes.toHtml().split(">");
						msg = str[2].split("<")[0].replace("￥", "");
						System.out.println("销售价格"+msg);
						if(nodes.size()==0){
							client.close();
							return "0";
						}						
						client.close();
						return msg;				
			} catch (Exception e) {
				e.printStackTrace();
				try {
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return "0";
				}
				return "0";
			} 
			
		
			
								
		}
		
		public static void main(String[] args) throws IOException {
			Baiji jianke = new Baiji();
					jianke.search("http://www.baiji.com.cn/goods-7173.html");

		}
}
