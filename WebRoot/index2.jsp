<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script src="./js/jquery-1.8.2.min.js"></script>
<script src="./js/hcharts/highcharts.js"></script>
<script src="./js/Bootstrap/js/bootstrap.min.js"></script>
<script src="./js/Bootstrap/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="./js/Bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="./js/Bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="./js/Bootstrap/css/bootstrap-datepicker.min.css">
<link href="./js/Bootstrap/css/bootstrap-combined.min.css" rel="stylesheet">
	
<style type="text/css"> 
 
body{ margin:0px;text-align:center;background-color:#F5F5F5;width:96%;margin-left:2%;font-size:12px;color:#666}
#contner{ width:100%; margin:0px auto;}
#header{ height:50px; background-color:#FF751A ;text-align:left;margin-bottom:2px}
#leftnav{float:left; width:12%;border:1px;background-color:#FFFFFF }
#rightif{float:left; width:88%; border:0px solid #999966 ; background-color:#F5F5F5;}
#tab{margin-left:30px;margin-right:1%;margin-top:10px;margin-bottom:1% }
*{margin:0; padding:0}
#condition{margin-left:1%;margin-right:1%; }
.tubiao{margin-left:1%;margin-right:1%;margin-bottom:1%;text-align:left;}
#biaoge{margin-left:1%;margin-right:1%;margin-bottom:1%;text-align:left;}


#container{margin-left:1%;margin-right:1%;background-color:#FFFFFF }
#nav{ width:150px; margin:0px auto; text-align:right;}
#nav h6{ cursor:pointer; line-height:50px; height:30px;margin-top:25px;font-weight:bolder;text-align:center}
#nav a{display:block; line-height:8px;color:#666666;text-align:center; }
#nav a:hover{background-color:#FAF3FA; color:#000;}
#nav div{ border-top:none}

a:link,a:visited,a:hover,.current,#info{ 
    border:0px solid #DDD; 
    background:#FFFFFF; 
    display:inline-block; 
    margin:1px; 
    text-decoration:none; 
    font-size:14px; 
    width:35px; 
    height:20px; 
    text-align:center; 
    line-height:15px; 
    color:#AAA; 
    padding:1px 2px; 
} 
a:hover{ 
    border:0px solid #E5E5E5; 
    background:#F9F9F9; 
} 
.current{ 
    border:0px solid #83E7E4; 
    background:#DFF9F8; 
    margin:1px; 
    color:#27CBC7; 
} 
#info{ 
    width:auto; 
} 


 </style>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>datakad首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <script>
  var cid2="";
  var n='0';
  var starttime ="";
  var endtime ="";
  var price = '0';
  var cid1 ="";
  var type = '0';
  var sort = '0';
  function sortfind(s){
  cpage = 1; 
  		sort = s;
  		 if(type=='1'){
	 type = '0';
	 	}
  		getdata();
  	}
  function query(){
  price ='0';
   cid1 ="";
   cid2 ="";
  type = '1';
  	  cpage = 1; 
  	  sort='2';
  	  getdata();
  }
  function find(e){
  alert(e);
  	}


function getdata(){

var s ="";
var c=document.getElementsByName("source");
for(var i=0;i<c.length;i++)		{
			if(c[i].checked==true){
	   s+=c[i].value+",";		
	   
	   			   	}	}
var key = document.getElementById('key').value;
if(type=='1'||type=='4'){
		s="";
	}
$.ajax({ 
             type: "POST",
             url: "search",
             dataType: "json",
             data:{'key':key,'source':s,'sort':sort,'page':cpage,'cid1':cid1,'cid2':cid2,'price':price,'type':type,'starttime':starttime,'endtime':endtime,'isnew':n},
             success: function(data){
            // alert(data.facet_counts.facet_pivot.sitecid1);           
           	setsitecid1(data.facet_counts.facet_pivot.sitecid1);
              //setdata(data.facet_counts.facet_fields.cid1);
            if(type=='1'||type=='4'){
              setsite(data.facet_counts.facet_fields.site);
              if(type=='4'){
              	type='3';
              	}
            	}
            setcid1(data.facet_counts.facet_fields.cid1);	
             setcid2(data.facet_counts.facet_fields.cid2);	
              setdata2(data.response.docs);
           // alert(data.facet_counts.facet_fields.cid1);
	  		setpage(data.response.numFound); 	
                      }
              
         });
}

function setdata(data){
	var d="";
	  for (var i = 0; i < data.length; i++) {
	  if(i%2==0){
	  d=d+'<a onclick=find(\''+data[i]+'\') style=font-size:10px>'+data[i];
	  	}else{
	  	d =d+'('+data[i]+')';
	  		d=d+'</a><br/>';
	  	}
	  		
	  		}
	  		
	  	document.getElementById('nav').innerHTML = d;
	}
	
	function setdata2(data2){
	
		var d="<table border-spacing=20 width = 100%>";
	  for (var i = 0; i < data2.length; i++) {
			if(data2[i].promotion=="none"){
				data2[i].promotion="";
				}
			if(data2[i].package=="none"){
				 data2[i].package="";
				}else{
			 data2[i].package = data2[i].package.replace(/<\/>/g,"</span> <span style='margin-left:10px;border:1px;background:#C4E1FF'>");
				}
			
	  	   	d=d+"<tr style='border-bottom: #B4E4FB 1px solid' height= 120px><td  align=center width = 30%><img onmouseover=aa(this) onmouseout=bb(this) onclick=opennew('"
	  	   	+data2[i].link+"') width=160 height=160 src="+data2[i].image+"></td><td width = 50% ><font onmouseover=aa(this) onmouseout=dd(this) onclick=opennew('"+data2[i].link+"') color=#000000>"
	  	   	+data2[i].pname+"</font><br/><font color=red>"+data2[i].promotion+"</font><br/>销售价:<font color=red>￥"
	  	   	+data2[i].salesPrice+"</font><br/>原价:<font color=red>￥"+data2[i].originalPrice+"</font></br><font><span style='border:1px;background:#C4E1FF'>套餐: "+data2[i].package+"</span></font></br><font color=#000000>"
	  	   	+data2[i].approvedStr+"</font></br><font color=#6C6C6C>"+data2[i].producer+"</font></br><font>评论数: "+data2[i].comments+"</font></td><td width = 20% >"+data2[i].site+"</td></tr>";	
	  		}
	  			d=d+"</table>";
	  			document.getElementById('container').innerHTML = d;
	}
	function querybysource(){
	 cid1 ="";
	 cid2="";
	 if(type=='1'){
	 type = '0';
	 	}
	
	cpage = 1; 
	sort = '2';
	getdata();
		}
		
	function setsite(data){
	var d = "<div style=float:left><B>网站 </B></div><br/><table style=font-size:12px;><tr height=30px width =100%>";
		for (var i = 0; i < data.length/2; i++) {
		var linkto = "";
		
		if(data[i*2]=="康之家药房网"){
			linkto = "www.kzj365.com";
			}
		if(data[i*2]=="健客网"){
			linkto = "www.jianke.com";
			}
		if(data[i*2]=="药房网"){
			linkto = "www.yaofang.cn";
			}
		if(data[i*2]=="壹药网"){
			linkto = "www.111.com.cn";
			}
		if(data[i*2]=="北京药品网"){
			linkto = "www.bjypw.com";
			}
		if(data[i*2]=="健一网"){
			linkto = "www.j1.com";
			}
		if(data[i*2]=="老百姓大药房"){
			linkto = "www.lbxcn.com";
			}
		if(data[i*2]=="康德乐大药房"){
			linkto = "www.baiji.com.cn";
			}	
		if(data[i*2]=="云开大药房"){
			linkto = "www.ykyao.com";
			}
		if(data[i*2]=="百德康药房"){
			linkto = "www.bdkyf.com";
			}	
		if(data[i*2]=="华佗药房"){
			linkto = "www.huatuoyf.com";
			}
		if(data[i*2]=="12药网"){
			linkto = "www.12yao.com";
			}
		if(data[i*2]=="开心人"){
			linkto = "www.360kxr.com";
			}
		if(data[i*2]=="800方"){
			linkto = "www.800pharm.com";
			}
		if(data[i*2]=="好药师"){
			linkto = "www.ehaoyao.com";
			}
		if(data[i*2]=="金象大药房"){
			linkto = "www.jxdyf.com";
			}
		if(data[i*2]=="海王星辰"){
			linkto = "www.star365.com";
			}
		if(data[i*2]=="818医药网"){
			linkto = "www.818.com";
			}				
				if((i+1)%6==0){
				d=d+"</tr><tr height=30px style ='margin-top:1px' width =100%>";
				}	
				 d =d+"<td width =12%><input onchange ='querybysource()' style='zoom:140%;margin-top:-1px' type='checkbox' name='source' value="+linkto+">"+data[i*2]+"(<font color=#999999>"+data[2*i+1]+"</font>)</td>";
					}
					d=d+ "</tr></table>";
				document.getElementById('site').innerHTML = d;		
				}
				
	function setcid1(data){
				var d ="";				
					d=d+"<div style=float:left><B>一级分类 </B></div><div onclick =showcid() id='show' onmouseover=aa(this) onmouseout=bb(this) style=float:right>展开+</div>";					
				 d =d+"<table cellspacing =0 cellpadding=0 width = 100% style=font-size:12px;>";		
				for (var i = 0; i < data.length/2; i++) {
				if((i)%7==0){
					if(i==14){
					d=d+"</table><table id ='discid' width = 100% cellspacing =0 cellpadding=0 style=font-size:12px;><tr height=30px width=100%;>";
						}else{
						d=d+"<tr height=30px style ='margin-top:0px' width =100%>";
						}
				}
				if(type!='1'){
					if(i==(data.length-1)/2){
					d=d+"</tr></table>";
					document.getElementById('cid1').innerHTML = d;	
					showcid();
					return;
					}
				}
				
				if(cid1!=""){
				d=d+"<td  width =14% onmouseover=aa(this) onmouseout=bb(this) onclick =back()>"+data[i*2]+"("+data[i*2+1]+")✖</td>";
				
				}else{
				d=d+"<td  width =14% onmouseover=aa(this) onmouseout=bb(this) onclick =querybycid1('"+data[i*2]+"')>"+data[i*2]+"(<font color=#999999>"+data[i*2+1]+"</font>)</td>";	
					}
				
						}
				d=d+"</tr></table>";
				document.getElementById('cid1').innerHTML = d;	
				showcid();
			}
			
	function setcid2(data){
				var d ="";				
					d=d+"<div style=float:left><B>二级分类 </B></div><div onclick =showcid2() id='show2' onmouseover=aa(this) onmouseout=bb(this) style=float:right>展开+</div>";					
				 d =d+"<table cellspacing =0 cellpadding=0 width = 100% style=font-size:12px;>";		
				for (var i = 0; i < data.length/2; i++) {
				if((i)%7==0){
					if(i==14){
					d=d+"</table><table id ='discid2' width = 100% cellspacing =0 cellpadding=0 style=font-size:12px;><tr height=30px width=100%;>";
						}else{
						d=d+"<tr height=30px style ='margin-top:0px' width =100%>";
						}
				}
				if(type!='1'){
					if(i==(data.length-1)/2){
					d=d+"</tr></table>";
					document.getElementById('cid2').innerHTML = d;	
					showcid2();
					return;
					}
				}
				
				if(cid2!=""){
				d=d+"<td  width =14% onmouseover=aa(this) onmouseout=bb(this) onclick =back2()>"+data[i*2]+"("+data[i*2+1]+")✖</td>";
				
				}else{
				d=d+"<td  width =14% onmouseover=aa(this) onmouseout=bb(this) onclick =querybycid2('"+data[i*2]+"')>"+data[i*2]+"(<font color=#999999>"+data[i*2+1]+"</font>)</td>";	
					}
				
						}
				d=d+"</tr></table>";
				document.getElementById('cid2').innerHTML = d;	
				showcid2();
			}
			
	function setsitecid1(data){
	var d = "";
			for (var i = 0; i < data.length; i++) {
			var linkto = "";
		
		if(data[i].value=="康之家药房网"){
			linkto = "www.kzj365.com";
			}
		if(data[i].value=="健客网"){
			linkto = "www.jianke.com";
			}
		if(data[i].value=="药房网"){
			linkto = "www.yaofang.cn";
			}
		if(data[i].value=="壹药网"){
			linkto = "www.111.com.cn";
			}
		if(data[i].value=="北京药品网"){
			linkto = "www.bjypw.com";
			}
		if(data[i].value=="健一网"){
			linkto = "www.j1.com";
			}
		if(data[i].value=="老百姓大药房"){
			linkto = "www.lbxcn.com";
			}
		if(data[i].value=="康德乐大药房"){
			linkto = "www.baiji.com.cn";
			}	
		if(data[i].value=="云开大药房"){
			linkto = "www.ykyao.com";
			}
		if(data[i].value=="百德康药房"){
			linkto = "www.bdkyf.com";
			}
		if(data[i].value=="华佗药房"){
			linkto = "www.huatuoyf.com";
			}
		if(data[i].value=="12药网"){
			linkto = "www.12yao.com";
			}
		if(data[i].value=="开心人"){
			linkto = "www.360kxr.com";
			}
		if(data[i].value=="800方"){
			linkto = "www.800pharm.com";
			}
		if(data[i].value=="好药师"){
			linkto = "www.ehaoyao.com";
			}
		if(data[i].value=="金象大药房"){
			linkto = "www.jxdyf.com";
			}
		if(data[i].value=="海王星辰"){
			linkto = "www.star365.com";
			}
		if(data[i].value=="818医药网"){
			linkto = "www.818.com";
			}						
					d=d+"<span><B>"+data[i].value+"</B>("+data[i].count+")</span><br/>";
					
					for(var j =0 ;j<data[i].pivot.length;j++){
							d=d+"<span style='margin-left:10px' onmouseover=aa(this) onmouseout=bb(this) onclick = querybysitecid1('"+linkto+"','"+data[i].pivot[j].value+"')>"+data[i].pivot[j].value+"(<font color=#999999>"+data[i].pivot[j].count+"</font>)</span><br/>";
						}
						d=d+"<br/>";
				}
				document.getElementById('sitecid1').innerHTML = d;	
			}
			
	function querybycid1(cid){	
	 var isnew = document.getElementById("isnew").checked;
   if(isnew){
   		n='1';
   	}
		cpage = 1;
		 sort='2';
		 if(type=='1'){
	 type = '0';
	 	}
			cid1 =cid;
			getdata();
		}
		
			
	function querybycid2(cid){	
	 var isnew = document.getElementById("isnew").checked;
   if(isnew){
   		n='1';
   	}
		cpage = 1;
		 sort='2';
		 if(type=='1'){
	 type = '0';
	 	}
			cid2 =cid;
			getdata();
		}
	
	function querybysitecid1(site,cid){	
	 var isnew = document.getElementById("isnew").checked;
   if(isnew){
   		n='1';
   	}
	 if(type=='1'){
	 type = '0';
	 	}
	 sort='2';
	cid1 =cid;
	var c=document.getElementsByName("source");
	for(var i=0;i<c.length;i++)		{
			if(c[i].value==site){
				c[i].checked=true;
			}else{
				c[i].checked=false;
				}
				}
				cpage = 1; 
			getdata();
		}
	function back(){
	sort='2';
	 if(type=='1'){
	 type = '0';
	 	}
		cid1="";
		cid2="";
		getdata();
	}
	
	function back2(){
	sort='2';
	 if(type=='1'){
	 type = '0';
	 	}
		cid2="";
		getdata();
	}
	
	function enterSumbit(){
		  if(event.keyCode == 13) {
		  	query();
    	 }  
		}
	
	function aa(e){
	e.style.color = "red";
	e.style.cursor="hand";
		}
		
	function bb(e){
	e.style.color = "#666";
	e.style.cursor="default";
	if(e.tagName=="SPAN"){
		if(price=='1'){
			document.getElementById("price1").style.color = "red";
			document.getElementById("price2").style.color = "#666";
			document.getElementById("price3").style.color = "#666";
			document.getElementById("price4").style.color = "#666";
			}
		if(price=='2'){
			document.getElementById("price1").style.color = "#666";
			document.getElementById("price2").style.color = "red";
			document.getElementById("price3").style.color = "#666";
			document.getElementById("price4").style.color = "#666";
			}
		if(price=='3'){
			document.getElementById("price1").style.color = "#666";
			document.getElementById("price2").style.color = "#666";
			document.getElementById("price3").style.color = "red";
			document.getElementById("price4").style.color = "#666";
			}
		if(price=='0'){
			document.getElementById("price1").style.color = "#666";
			document.getElementById("price2").style.color = "#666";
			document.getElementById("price3").style.color = "#666";
			document.getElementById("price4").style.color = "red";
			}
		
		}
	}
	
	function cc(e){
	e.style.color = "blue";
	e.style.cursor="default";
		}
	
	function dd(e){
	e.style.color = "#000000";
	e.style.cursor="default";
		}
	
	function showcid(){	
	var c=document.getElementById("discid");
	var s=document.getElementById("show");
	if(c==undefined){
		return;
		}
			if(c.style.display!="none"){
			c.style.display="none";
			s.innerHTML = "展开+";
			}else{
			c.style.display="block";
			s.innerHTML = "收起 -";
			}
		
	}
	
	function showcid2(){	
	var c=document.getElementById("discid2");
	var s=document.getElementById("show2");
	if(c==undefined){
		return;
		}
			if(c.style.display!="none"){
			c.style.display="none";
			s.innerHTML = "展开+";
			}else{
			c.style.display="block";
			s.innerHTML = "收起 -";
			}
		
	}
	
	function querybyprice(p){
	 var isnew = document.getElementById("isnew").checked;
   if(isnew){
   		n='1';
   	}
	price = p;
	getdata();
		}
		
	function newproquery(){
    n='1';
	 price = '0';
 	 cid1 ="";
 	 cid2 ="";
   type = '4';
   sort = '0';
   cpage = 1; 
   getdata();
  
		}
		
	function newpro(e){
	document.getElementById("qtime").style.display="none";
	if(e.checked){
	starttime="";
	endtime="";
	document.getElementById("qnew").style.display="block";
	newproquery();
	}else{
	document.getElementById("qnew").style.display="none";
		n='0';
	 price = '0';
 	 cid1 ="";
 	 cid2 ="";
   type = '1';
   sort = '0';
   cpage = 1; 
   getdata();
		}
	}
	
	function querybytime(){
	starttime = document.getElementById("starttime").value;
	endtime = document.getElementById("endtime").value;
	newproquery();
		}
		
	function showtime(){
	document.getElementById("qtime").style.display="block";
	}
	
	function yes(){
	
	document.getElementById("qtime").style.display="none";
	starttime ="";
	endtime="";
		newproquery();
	}
	
	function opennew(url){
		window.open(url,'_blank');
		}
	
	function week(){
	var myDate = new Date();var date = myDate.getDate();date = date - 7;	
	
	var month = myDate.getMonth()+1+"";
	var d = myDate.getDate()+"";
	
	if(month.length==1){
		month ="0"+month;
		
		}
	if(d.length==1){
		d="0"+d;
	}
	endtime = myDate.getFullYear()+"."+month+"."+d;
	
	myDate.setDate(date);
	 month = myDate.getMonth()+1+"";
	 d = myDate.getDate()+"";
	if(month.length==1){
		month ="0"+month;
		}
	if(d.length==1){
		d="0"+d;
	}
	starttime=myDate.getFullYear()+"."+month+"."+d;
	
	document.getElementById("qtime").style.display="none";
	newproquery();
	}
</script>
  
 <body onload="query()">

<div id="contner">
<div id="header"><br/><span style="margin-top:30px;font-size:30px;color:#FFFFFF"> &nbsp;数据分析平台</span>
</div>
<div id="mian">
<div id="leftnav">
<div id="nav">
<div id="sitecid1" style="text-align:left;margin-top:50px"></div>
<hr>
</div>
</div><!--这部分是左侧导航-->
<div id="rightif">
<div align="center">
<div id ="condition" style="min-width:600px;height:50px">
<input id="key" onkeypress="enterSumbit()" class="search-combobox-input"  type ="text" style="width:650px; height:40px" size="1024">
    <img  onmouseover="aa(this)" onmouseout="bb(this)" style="margin-top:-7px" onclick="query()" width="40" height="70" src="./img/ss.jpg" />
   </div>  
   <div class="tubiao" style="background-color:#FFFFFF;min-width:600px;padding:10px 5px 15px 20px;border:1px solid #DDD;">
   <input onclick ="newpro(this)" style='zoom:140%;margin-top:-1px' type='checkbox' id='isnew'><B>新品</B><br/>
 <div id="qnew" style="min-width:600px;height:30px;display:none;"><div  style="min-width:400px;margin-top:10px;float:left">
  <span  onmouseover="aa(this)" onmouseout="bb(this)" onclick ="yes()" >默认(昨日新品)</span>
   <span  onmouseover="aa(this)" onmouseout="bb(this)" onclick ="week()" style="margin-left:20%;">一周新品</span>   
 <span  onmouseover="aa(this)" onmouseout="bb(this)" onclick ="showtime()" style="margin-left:20%;">时段查询</span>   
 </div><div id="qtime" style="display:none;margin-left:5%;margin-top:-10px"> <input  class="span2"  style="height:30px;margin-top:5px" type="text"  value="" id="starttime" readonly>                       
     -- <input type="text" class="span2" value="" width="100" style="height:30px;margin-top:5px"  id="endtime"  readonly >  
     <input  style="background:#FF751A; margin-top:-5px" onclick="querybytime()" type ="button" value="确定">
 	</div>   </div>
      </div>
	<div id ="site" class="tubiao" style="background-color:#FFFFFF;min-width:600px;padding:10px 5px 15px 20px;border:1px solid #DDD; "></div>
	<div id ="cid1" class="tubiao" style="background-color:#FFFFFF;min-width:600px;padding:10px 5px 15px 20px;border:1px solid #DDD; "></div>
	<div id ="cid2" class="tubiao" style="background-color:#FFFFFF;min-width:600px;padding:10px 5px 15px 20px;border:1px solid #DDD; "></div>

	<div id ="price" class="tubiao" style="background-color:#FFFFFF;min-width:600px;padding:10px 5px 15px 20px;border:1px solid #DDD; ">	
	<div style="min-width:600px;height:30px"><B>价格</B></div>
	<span id="price1" onclick = "querybyprice('1')" onmouseover=aa(this) onmouseout=bb(this) style="margin-right:5%;">200以内</span>
	<span id="price2" onclick = "querybyprice('2')" onmouseover=aa(this) onmouseout=bb(this)  style="margin-right:5%">200到500</span>
	<span id="price3" onclick = "querybyprice('3')" onmouseover=aa(this) onmouseout=bb(this) style="margin-right:5%">500以上</span>
	<span id="price4" onclick = "querybyprice('0')" onmouseover=aa(this) onmouseout=bb(this) style="margin-right:5%">所有</span>
	
	</div>
	 
<div style="min-width:1100px;float:left;height: 39px;margin-left:35px">
 <div id="setpage" style="float:right;margin-right:8%;"></div>
<ul  style="list-style:none;float:left">
<li onclick="sortfind('2')" onmouseover=aa(this) onmouseout=bb(this) style="display:inline;margin-left:30px;font-size:12px">默认排序</li>
<li onclick="sortfind('0')" onmouseover=aa(this) onmouseout=bb(this) style="display:inline;margin-left:30px;font-size:12px">价格升序</li>
<li onclick="sortfind('1')" onmouseover=aa(this) onmouseout=bb(this) style="display:inline;margin-left:30px;font-size:12px">价格降序</li>
 
</ul></div>
	
    <div id="container" style="min-width:600px;height:1700px"></div>
    <div id="setpage" style="float:right;margin-right:50px;float:center"></div>
    
     </div>
    </div>
</div>
</div>

<div id="footer"><br/>&nbsp;<br/><br/>版权信息</div> 
   </body>
 </html>
 <script type="text/javascript"> 

var totalpage,pagesize,cpage,count,curcount,outstr; 
//初始化 
cpage = 1; 
totalpage = 56; 
pagesize = 20; 
outstr = ""; 
totalcount=0;
function gotopage(target) 
{     
    cpage = target;        //把页面计数定位到第几页 
    setpage(totalcount); 
    getdata();    //调用显示页面函数显示第几页,这个功能是用在页面内容用ajax载入的情况 
} 
function setpage(total) 
{ 
	totalcount = total;
	totalpage = parseInt(totalcount/20)+1;
    if(totalpage<=10){        //总页数小于十页 
        for (count=1;count<=totalpage;count++) 
        {    if(count!=cpage) 
            { 
                outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a>"; 
            }else{ 
                outstr = outstr + "<span class='current' >"+count+"</span>"; 
            } 
        } 
    } 
    if(totalpage>10){        //总页数大于十页 
        if(parseInt((cpage-1)/10) == 0) 
        {             
            for (count=1;count<=10;count++) 
            {    if(count!=cpage) 
                { 
                    outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a>"; 
                }else{ 
                    outstr = outstr + "<span class='current'>"+count+"</span>"; 
                } 
            } 
            outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+count+")'> next </a>"; 
        } 
        else if(parseInt((cpage-1)/10) == parseInt(totalpage/10)) 
        {     
            outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+(parseInt((cpage-1)/10)*10)+")'>pre</a>"; 
            for (count=parseInt(totalpage/10)*10+1;count<=totalpage;count++) 
            {    if(count!=cpage) 
                { 
                    outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a>"; 
                }else{ 
                    outstr = outstr + "<span class='current'>"+count+"</span>"; 
                } 
            } 
        } 
        else 
        {     
            outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+(parseInt((cpage-1)/10)*10)+")'>pre</a>"; 
            for (count=parseInt((cpage-1)/10)*10+1;count<=parseInt((cpage-1)/10)*10+10;count++) 
            {         
                if(count!=cpage) 
                { 
                    outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a>"; 
                }else{ 
                    outstr = outstr + "<span class='current'>"+count+"</span>"; 
                } 
            } 
            outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage("+count+")'> next </a>"; 
        } 
    }     
    document.getElementById("setpage").innerHTML = "<span id='info'>共"+totalpage+"页|第"+cpage+"页<\/span>" + outstr ; 
    outstr = ""; 
} 
  $('#starttime').datepicker({ 
   format: 'yyyy.mm.dd', 
      weekStart: 1,     
      autoclose: true,    
      todayBtn: 'linked',    
      language: 'zh-CN' }).on('changeDate',function(ev){ 
       var startTime = ev.date.valueOf();
       start = startTime;
          });
	$('#endtime').datepicker({ 
	 format: 'yyyy.mm.dd',      
	   weekStart: 1,    
   autoclose: true,    
       todayBtn: 'linked',       
        language: 'zh-CN' }).on('changeDate',function(ev){ 
         var endTime = ev.date.valueOf(); 
          end = endTime;
            if(end<start){  
           alert("“评估结束时间 ”不能早于“评估开始时间 ” ！");   
             }else{    
              } });
</script> 
 
