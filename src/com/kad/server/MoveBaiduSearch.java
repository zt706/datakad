package com.kad.server;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.kad.db.HbaseUtil;
import com.kad.util.Log;

public class MoveBaiduSearch {

	public static String search(String siteurl,String keyword) throws IOException{
			Log log = new Log();
			//PropertyConfigurator.configure("/opt/tomcat/webapps/datakad/log4.properties"); 
			String msg="";
			CloseableHttpClient client = HttpClientBuilder.create().build();		
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
			//String url= "http://m.baidu.com/bd_page_type=1/uid=0/pu=ta%40middle___3_537%2Csz%40224_220%2Cusm%400/s?ref=wap_colorful&prest=102141&rn=10&st=102141&tn=&word="+URLEncoder.encode(keyword, "utf-8");
			String url = "http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/s?word="+URLEncoder.encode(keyword, "utf-8")+"&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=ta%40middle___3_537%2Cusm%400%2Csz%40224_220";
			
			CloseableHttpResponse response =null;
			HttpGet get = new HttpGet(url);
			get.setConfig(requestConfig);
			get.setHeader("User-Agent", "osName:iOS");
			String content="";
			try {
				 response = client.execute(get);
				 content = EntityUtils.toString(response.getEntity());
			} catch (ClientProtocolException e) {
				log.log(keyword+"httpclient连接异常");
				log.log(e.toString());
				e.printStackTrace();
				client.close();
				return "0";
			} catch (IOException e) {
				log.log(keyword+"io连接异常");
				log.log(e.toString());
				e.printStackTrace();
				client.close();
				return "0";
			}

			Parser parser = Parser.createParser(content, "utf-8");
			HasAttributeFilter filter2 = new HasAttributeFilter("class","resitem");
			HasAttributeFilter filter3 = new HasAttributeFilter("class","site");
			TagNameFilter afilter = new TagNameFilter("a");
			TagNameFilter pfilter = new TagNameFilter("p");
			TagNameFilter divfilter = new TagNameFilter("div");
			TagNameFilter spanfilter = new TagNameFilter("span");
			AndFilter andfilter = new AndFilter(divfilter,filter2);
			AndFilter andfilter2 = new AndFilter(spanfilter,filter3);
			//OrFilter orfilter = new OrFilter(divfilter,filter2);
			try {
				NodeList nodes = parser.extractAllNodesThatMatch(andfilter);
				System.out.println(nodes.size());
				for(int i=0;i<nodes.size();i++){
					String count ="";
					String site="";
					String content2 = nodes.elementAt(i).toHtml();
					Parser parser2 = Parser.createParser(content2, "utf-8");
					
							Node node =parser2.extractAllNodesThatMatch(andfilter2).elementAt(0);
							if(node==null){
								continue;
							}
							Span spannode = (Span)node;
					if(!spannode.getStringText().contains(siteurl)){
						continue;
					}else{
						site=spannode.getStringText();
						parser2.reset();
						LinkTag link = (LinkTag) parser2.extractAllNodesThatMatch(afilter).elementAt(0);
						String l = link.getLink();
						 String[] str = l.split("&");
						 for(int j=0;j<str.length;j++){
							 if(str[j].contains("order")){
								 String s[] = str[j].split("=");
								 count =s[1];
								 System.out.println("排名:"+s[1]);
							 }
						 }
					}
				
						 if(!count.equals("")&&(!site.equals(""))){
							 if(msg.equals("")){
									msg =msg+site+","+count;
								}else{
									msg =msg+","+site+","+count;
								}
						 }
					}
				
			} catch (ParserException e) {
				log.log(keyword+"parser解析连接异常");
				log.log(e.toString());
				e.printStackTrace();
				client.close();
				return "0";
			} 			
			client.close();
			return msg;
	}
		
}
