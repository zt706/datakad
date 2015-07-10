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
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class JianKe {
	
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
					HasAttributeFilter tongyong = new HasAttributeFilter("class","assort tongyong");
					HasAttributeFilter renzeb = new HasAttributeFilter("class","renzeb");
					HasAttributeFilter mark = new HasAttributeFilter("class","assort line_thr");
					HasAttributeFilter price = new HasAttributeFilter("class","assort bigPrice");
					HasAttributeFilter specif = new HasAttributeFilter("class","specif");
					HasParentFilter markFilter = new HasParentFilter(mark);
					HasParentFilter spec = new HasParentFilter(specif);
					TagNameFilter dd = new TagNameFilter("dd");
					HasParentFilter filter6 = new HasParentFilter(tongyong);
					AndFilter namefilter = new AndFilter(filter6,dd);
					
					NodeList nodes =null;
					/*nodes = parser.extractAllNodesThatMatch(namefilter);
					String[] str = nodes.toHtml().split("\n");				
					System.out.println("商品名称"+str[1].trim());
					parser.reset();
					nodes = parser.extractAllNodesThatMatch(renzeb);
					str = nodes.toHtml().split("\n");
					System.out.println("编号"+str[1].split("<")[0].trim());
					parser.reset();
					nodes = parser.extractAllNodesThatMatch(markFilter);
					str = nodes.toHtml().split("\n");
					System.out.println("市场价格"+str[2].trim().replace("<dd>", "").replace("</dd>", ""));
					parser.reset();*/
					nodes = parser.extractAllNodesThatMatch(price);
					String[] str = nodes.toHtml().split("\n");
					msg = str[2].trim().replace("<dd>", "").replace("</dd>", "").replace("<em>", "").replace("</em>", "").replace("￥", "");
					System.out.println("销售价格"+str[2].trim().replace("<dd>", "").replace("</dd>", "").replace("<em>", "").replace("</em>", ""));
					//parser.reset();
					//nodes = parser.extractAllNodesThatMatch(spec);
					//System.out.println(nodes.toHtml());
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
			JianKe jianke = new JianKe();
					jianke.search("http://www.jianke.com/product/12059.html");

		}
}
