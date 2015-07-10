package com.kad.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.kad.db.HbaseUtil;
import com.kad.server.BaiduSearch;
import com.kad.server.SoSearch;

public class TxtRead {
	static Map map = new HashMap();
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HbaseUtil util = new HbaseUtil();
		SoSearch search = new SoSearch();
		File file=new File("c:/a.txt");
		  InputStreamReader read = new InputStreamReader(

                  new FileInputStream(file),"GBK");//考虑到编码格式

                  BufferedReader bufferedReader = new BufferedReader(read);

                  String lineTxt = null;
                  int i= 10000;
                  int code =0;
                  while((lineTxt = bufferedReader.readLine()) != null){
                	  i++;
                	//  util.addKeyWord("101", "101"+i, lineTxt);
                      System.out.println(i+"-----"+lineTxt);
                  String s = search.search("www.360kad.com/produ", lineTxt);
                     if(code==0){
                    	 map.put(lineTxt,"www.360kad.com/product" );
                     }
                  }
                  System.out.println(map.values().toString());
                  read.close();
	}

}
