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

public class Huatuo {
	
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
					StringFilter strfilter = new StringFilter("生产企业");
					StringFilter strfilter2 = new StringFilter("批准文号");
					StringFilter strfilter3 = new StringFilter("产品规格");			
					HasAttributeFilter filter2 = new HasAttributeFilter("class","sp-ComNum");
					HasAttributeFilter mark = new HasAttributeFilter("id","MarketPrice");    
			        HasAttributeFilter price = new HasAttributeFilter("id","SpecialPrice");                      
					NodeList nodes =null;
					//nodes = parser.extractAllNodesThatMatch(filter2);
					//String[] str =nodes.toHtml().split("\n");					
					//System.out.println("商品名称"+nodes.toHtml());
					//parser.reset();
					/*nodes = parser.extractAllNodesThatMatch(mark);
					//String[] str =nodes.toHtml().split("\n");	
					Span s = (Span) nodes.elementAt(0);
					System.out.println(s.getStringText());*/
					//parser.reset();
					nodes = parser.extractAllNodesThatMatch(price);
					//String[] str =nodes.elementAt(1).toHtml().split("\n");	
					Span s = (Span) nodes.elementAt(0);
					System.out.println(s.getStringText());
					/*parser.reset();
					nodes = parser.extractAllNodesThatMatch(strfilter);
					str =nodes.toHtml().split("：");
					System.out.println("市场价"+str[1]);
					msg = str[1];
					parser.reset();*/
					/*nodes = parser.extractAllNodesThatMatch(strfilter2);
					String[] str =nodes.toHtml().split("：");
					System.out.println("会员价"+str[1]);*/
					/*parser.reset();
					nodes = parser.extractAllNodesThatMatch(strfilter3);
					str =nodes.toHtml().split("：");
					System.out.println("规格"+str[1]);*/
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
			
			Huatuo jianke = new Huatuo();
					jianke.search("http://www.huatuoyf.com/product/10227.htm");

		}
}
