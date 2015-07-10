package com.kad.server;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.kad.db.HbaseUtil;
import com.kad.util.Log;

public class SoSearch {
	
		public static String search(String siteurl,String keyword) throws IOException{
			Log log = new Log();
		
			String msg="";
			//HbaseUtil util = new HbaseUtil();
			//String col = "2014-07-01360"+siteurl;
			//word = URLEncoder.encode(word, "utf-8");
			CloseableHttpClient client = HttpClientBuilder.create().build();
			String url = "http://www.so.com/s?ie=utf-8&shb=1&q="+URLEncoder.encode(keyword, "utf-8");  
			CloseableHttpResponse response =null;
			HttpGet get = new HttpGet(url);
			get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
			try {
				 response = client.execute(get);
			} catch (ClientProtocolException e) {
				log.log(keyword+"httpclient连接异常");
				log.log(e.toString());
				
				e.printStackTrace();
				client.close();
				return "0";
			} catch (IOException e) {
				log.log(keyword+"IO异常");
				log.log(e.toString());
				e.printStackTrace();
				client.close();
				return "0";
			}
			System.out.println("aaa");
			String content = EntityUtils.toString(response.getEntity());
			System.out.println("bbb");
			Parser parser = Parser.createParser(content, "utf-8");
			TagNameFilter filter1 = new TagNameFilter("a");
			HasAttributeFilter filter2 = new HasAttributeFilter("data-pos");
			TagNameFilter filter3 = new TagNameFilter("h3");
			HasParentFilter filter4 = new HasParentFilter(filter3);
			AndFilter filter = new AndFilter(filter1,filter2);
			NodeList nodes =null;
			try {
				nodes = parser.extractAllNodesThatMatch(filter4).extractAllNodesThatMatch(filter);
			} catch (ParserException e) {
				log.log(keyword+"parser解析连接异常");
				log.log(e.toString());
				e.printStackTrace();
				client.close();
				return "0";
			} 
			System.out.println("共有节点:"+nodes.size());
			for(int i=0;i<nodes.size();i++){
				LinkTag linknode = (LinkTag)nodes.elementAt(i);
				System.out.println(i+":"+linknode.getAttribute("href"));
				if(linknode.getAttribute("href").contains(siteurl)){
					String count = linknode.getAttribute("data-pos");
					System.out.println("关键词:"+keyword+"SO排名:"+count+"网站地址:"+linknode.getAttribute("href"));
					//util.saveKeyWord(col, keyword, (count+1)+"");
					String site = linknode.getAttribute("href");
					if(msg.equals("")){
						msg =msg+site+","+count;
					}else{
						msg =msg+","+site+","+count;
					}
				}	
							
			}
					
			client.close();
			return msg;									
		}
		
}
