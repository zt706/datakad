package com.kad.mail;

import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendPrice {
	
	 private static String username = "zixia1988";   
	 private static String password = "lj8561543";   
	 private static String smtpServer = "smtp.163.com";   
	 private static String fromMailAddress = "zixia1988@163.com";   
	 private static String toMailAddress = "417975510@qq.com";   
	 private static String toMailAddress2 = "jl1988412@126.com";  
	  public static void main(String[] args) throws Exception {   
		  Properties props = System.getProperties();   
		  
		   props.put("mail.smtp.host",smtpServer);  
		   props.put("mail.smtp.auth", "true"); 
		   props.setProperty("mail.debug", "true");
		   Session session = Session.getInstance(props,new SimpleAuthenticator("zixia1988", "lj8561543"));
		   session.setDebug(true);//debug模式
		   
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(fromMailAddress));
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(toMailAddress));
		   message.setSubject("HelloJavaMail");
		   message.setText("Welcome to JavaMail");
		   Transport tran = session.getTransport("smtp");
		   tran.connect("smtp.163.com", "zixia1988@163.com", "lj8561543");
		  tran.send(message,new Address[]{new InternetAddress(toMailAddress)});
		  tran.close();
	  }	 

	  

	  public static void send(String content) throws Exception {   
		  Properties props = System.getProperties();   
		  
		 Address[] add = new Address[]{new InternetAddress("jl1988412@126.com"),new InternetAddress("417975510@qq.com"),new InternetAddress("614546563@qq.com"),
				  new InternetAddress("2574781519@qq.com"),new InternetAddress("1528145048@qq.com"),new InternetAddress("1178091645@qq.com"),new InternetAddress("710464756@qq.com"),
				  new InternetAddress("1076866431@qq.com"),new InternetAddress("763449550@qq.com"),new InternetAddress("925149010@qq.com"),new InternetAddress("2522814797@qq.com"),
		  			new InternetAddress("381624603@qq.com")};
		//  Address[] add =new Address[]{new InternetAddress("jl1988412@126.com"),new InternetAddress("zixia1988@163.com")};
		   props.put("mail.smtp.host",smtpServer);  
		   props.put("mail.smtp.auth", "true"); 
		   props.setProperty("mail.debug", "true");
		   Session session = Session.getInstance(props,new SimpleAuthenticator("zixia1988", "lj8561543"));
		   session.setDebug(true);//debug模式
		   
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(fromMailAddress));
		   for(int i=0;i<add.length;i++){
			   message.addRecipient(Message.RecipientType.TO,add[i]);
		   }
		 //  message.addRecipient(Message.RecipientType.TO,new InternetAddress("jl1988412@126.com"));
		   //message.addRecipient(Message.RecipientType.TO,new InternetAddress("zixia1988@163.com"));
		   message.setSubject("价格更改通知");
		   message.setContent(content, "text/html;charset=gb2312");
		  // message.setText(content);
		   Transport tran = session.getTransport("smtp");
		   tran.connect("smtp.163.com", "zixia1988@163.com", "lj8561543");
		  tran.send(message,add);
		 // tran.send(message,new Address[]{new InternetAddress(toMailAddress2)});
		  tran.close();
	  }	 
}

class SimpleAuthenticator extends Authenticator {  
    
    private String username;  
      
    private String password;  
      
    public SimpleAuthenticator(String username, String password) {  
        this.username = username;  
        this.password = password;  
    }  
  
    public PasswordAuthentication getPasswordAuthentication() {      
        return new PasswordAuthentication(this.username, this.password);  
  
    }  
      
}  
