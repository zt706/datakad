package com.kad.server;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class WebSerach {
	public static String search(String url,String keyword,String type) throws IOException{

	    final String path = "http://119.29.40.47:8080/crawler/servlet/Search?type="+type+"&url="+url+"&keyword=+"+URLEncoder.encode(keyword, "gbk");
	    CloseableHttpClient client = HttpClientBuilder.create().build();		
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
		CloseableHttpResponse response =null;
		HttpGet get = new HttpGet(path);
		get.setConfig(requestConfig);
		try {
			 response = client.execute(get);
		}catch (Exception e) {
			e.printStackTrace();
			client.close();
			
		}
		if(response==null){
			return "0";
		}
		String content = EntityUtils.toString(response.getEntity());
		//System.out.println("aa"+content.equals("1")+"aa");
		return content;
		} 
	
}
