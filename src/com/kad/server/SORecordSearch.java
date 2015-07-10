package com.kad.server;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class SORecordSearch {
	
	public static String search(String siteurl,String keyword) throws IOException{	
		String msg="0";
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
			e.printStackTrace();
			client.close();
			return "-1";
		} catch (IOException e) {
			e.printStackTrace();
			client.close();
			return "-1";
		}
		String content = EntityUtils.toString(response.getEntity());
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
			e.printStackTrace();
			client.close();
			return "-1";
		} 
		System.out.println("共有节点:"+nodes.size());
		if(nodes.size()==0){
			content = new String(content.getBytes("ISO-8859-1"), "utf8");
			System.out.println("找到0节点数:"+content);
			client.close();
			if(content.contains("建议")){
				return "0";
			}else{
				return "-1";
			}
		
		}
		for(int i=0;i<nodes.size();i++){
			LinkTag linknode = (LinkTag)nodes.elementAt(i);
			System.out.println(i+":"+linknode.getAttribute("href"));
			if(linknode.getAttribute("href").startsWith(siteurl)){
				String count = linknode.getAttribute("data-pos");
				System.out.println("关键词:"+keyword+"SO排名:"+count+"网站地址:"+linknode.getAttribute("href"));
				client.close();
				return "1";
				
				/*String site = linknode.getAttribute("href");
				if(msg.equals("")){
					msg =msg+site+","+count;
				}else{
					msg =msg+","+site+","+count;
				}*/
			}	
						
		}
				
		client.close();
		return msg;									
	}
	
	public static void main(String[] args) {
		 
		try {
			String msg = search("www.360kad.com","http://www.360kad.com/fsgk/100075.shtml");
		System.out.print(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
