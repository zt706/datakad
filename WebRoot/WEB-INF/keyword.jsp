<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>   

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="/datakad/js/jquery-1.8.2.min.js"></script>
<script src="/datakad/js/hcharts/highcharts.js"></script>
<script src="/datakad/js/Bootstrap/js/bootstrap.min.js"></script>
<script src="/datakad/js/Bootstrap/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="/datakad/js/Bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/datakad/js/Bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/datakad/js/Bootstrap/css/bootstrap-datepicker.min.css">
<link href="/datakad/js/Bootstrap/css/bootstrap-combined.min.css" rel="stylesheet">
    
<style type="text/css"> 
 
body{ margin:0px;text-align:center;background-color:#EBEBEB}
#contner{ width:100%; margin:0px auto;}
#header{ height:50px; background-color:#FF751A ;text-align:left;margin-bottom:10px}
#leftnav{float:left; width:12%;border:1px;font-weight:bold;}
#rightif{float:left; width:88%; border:0px solid #999966 ;background-color:#FFFFFF ;}
#tab{margin-left:30px;margin-right:30px;border:1px solid #E8DFDF;margin-top:10px;margin-bottom:10px }
*{margin:0; padding:0}
#condition{margin-left:30px;margin-right:30px;border:1px solid #E8DFDF;margin-top:10px;margin-bottom:10px;background-color:#EBEBEB }
#tubiao{margin-left:30px;margin-right:30px;border:1px solid #E8DFDF;margin-top:10px;margin-bottom:10px;background-color:#EBEBEB;text-align:left;font-weight:bolder }
.biaoge{margin-left:30px;margin-right:30px;border:1px solid #E8DFDF;margin-top:10px;margin-bottom:10px;background-color:#EBEBEB;text-align:left;font-weight:bolder }

#container{margin-left:30px;margin-right:30px;border:1px solid #E8DFDF ;}
#nav{ width:150px; margin:0px auto; text-align:right;}
#nav h6{ cursor:pointer; line-height:50px; height:30px;margin-top:25px;font-weight:bolder;text-align:center}
#nav a{display:block; line-height:24px;color:#666666;text-align:center; }
#nav a:hover{background-color:#FAF3FA; color:#000;}
#nav div{display:none; border-top:none}

  </style>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>datakad��ҳ</title>
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
  var channels ="";
function showpro(){
		document.getElementById('addpro').style.display = "block";	
				document.getElementById('addword').style.display = "none";		
}
function showword(){
		document.getElementById('addword').style.display = "block";	
				document.getElementById('addpro').style.display = "none";	
}

function addchannel(){
	var type ="1";
	var cpc = document.getElementById('cpc1').value;
	var channel =document.getElementById('channel1').value;
	var site = document.getElementById('site').value;
	if(channel==""){
		alert("频道名称不能为空");
		return;
	}
	if(site==""){
		alert("URL不能为空");
		return;
	}
	$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "text",
             data:{'cpc':cpc,'channel':channel,'type':type,'site':site},
             success: function(data){
 				if(data==0){
 					alert("添加失败");
 					}
				if(data==2){
 					alert("频道已经存在");
 					}
 				if(data==1){
 					alert("添加成功");
 					}
                      }          
         });
	
	}
	
	function addkeyword(){
	var type ="2";
	var cpc = document.getElementById('cpc2').value;
	var channel =document.getElementById('channel2').value;
	var keyword = document.getElementById('keyword').value;
	
	if(keyword==""){
		alert("关键词不能为空");
		return ;
	}
	
	$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "text",
             data:{'cpc':cpc,'channel':channel,'type':type,"keyword":keyword},
             success: function(data){
            
 				if(data==0){
 					alert("添加失败");
 					}
				if(data==2){
 					alert("关键词已经存在");
 					}
 				if(data==1){
 					alert("添加成功");
 					}
                      }            
         });
	
	}
	
	function getword(){
	str =window.showModalDialog("word.html","dialogWidth=200px;dialogHeight=100px");
	var type ="3";
	var cpc = document.getElementById('cpc2').value;
	var channel =document.getElementById('channel2').value;
	var keyword = str;
	var site = document.getElementById('site').value;
	if(keyword==""){
		alert("关键词不能为空");
		return ;
	}
	
	document.getElementById('addview').style.display="block";
	$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "text",
             data:{'cpc':cpc,'channel':channel,'type':type,"keyword":keyword,"site":site},
             success: function(data){
             alert("成功添加"+data+"条");
 			document.getElementById('addview').style.display="none";
                    }            
         });
	
	}
	
	
	function menu(){
	var type ="4";
	$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "json",
             data:{'type':type},
             success: function(data){
              channels =data;
             	setmenu(data);
             	change();
                    }            
         });
	
		}
		
	function setmenu(data){
			var d1="";var d2="";var d3="";
			for(var o in data){ 
			if(o.substring(0, 1)=='1'){
			d1=d1+"<li><a onclick=query('"+o+"','"+data[o]+"')>"+data[o]+"</a></li>";
					}
			
			if(o.substring(0, 1)=='2'){
			d2=d2+"<li><a onclick=query('"+o+"','"+data[o]+"')>"+data[o]+"</a></li>";
					}
			
			if(o.substring(0, 1)=='3'){
			d3=d3+"<li><a onclick=query('"+o+"','"+data[o]+"')>"+data[o]+"</a></li>";
					}
				}
			document.getElementById('bd').innerHTML=d1;
			document.getElementById('360').innerHTML=d2;
			document.getElementById('record').innerHTML=d3;
		}
		
	function query(channel,channelname){
	document.getElementById('channelid').value=channel;
	document.getElementById('channelname').innerHTML=channelname;
	var row = channel;
	var starttime = "";
	var endtime = "";
		var type="5";
		$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "json",
             data:{'type':type,'row':row,'starttime':starttime,'endtime':endtime},
             success: function(data){ 
          count(data);
            table(data.data);
                    }            
         });
	}
	
	function change(){
	var id = document.getElementById('cpc2').value;
	var d="";
	if(id=="1"){
	
	for(var o in channels){ 
			if(o.substring(0, 1)=='1'){
				d=d+"<option value ="+o+">"+channels[o]+"</option>";		
			}
		}
		}
	
		
	if(id=="2"){
	for(var o in channels){ 
			if(o.substring(0, 1)=='2'){
				d=d+"<option value ="+o+">"+channels[o]+"</option>";		
			}
		}
	}
	
	if(id=="3"){
	for(var o in channels){ 
			if(o.substring(0, 1)=='3'){
				d=d+"<option value ="+o+">"+channels[o]+"</option>";		
			}
		}
	}
		document.getElementById('channel2').innerHTML = d;
	}
	
	
	function table(data){
	
	var d="";
		 for (var i = 0; i < data.length; i++) {
		 if(i%2==0){
		  d = d+"<tr class=success><td>"+i+"</td><td>"+data[i].word+"</td><td>"+data[i].rank+"</td><td style=word-wrap:break-word:  width = 15%>"+data[i].url+"</td><td>"+data[i].cpc+"</td><td>"+data[i].date+"</td></tr>";	
		 	}else{
		  d = d+"<tr class=danger><td>"+i+"</td><td>"+data[i].word+"</td><td>"+data[i].rank+"</td><td style=word-wrap:break-word: width = 15%>"+data[i].url+"</td><td>"+data[i].cpc+"</td><td>"+data[i].date+"</td></tr>";	
		 		}
		 }
	
		document.getElementById('tabtr').innerHTML = d;
	}
	
	function delChannel(){
	var row =document.getElementById('channelid').value;
	if(row==""){
	alert("请先选择频道");
	return ;
	}
		var type="6";
		$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "text",
             data:{'type':type,'row':row},
             success: function(data){
           if(data==1){
           	alert("删除成功");
           		}else{
           		 	alert("删除失败");
           		 }
                    }            
         });
	}
	
	function update(){
	var row =document.getElementById('channelid').value;
	var name = prompt("请输入新频道名");
	if(name==null){
	return;
		}
	if(row==""){
	alert("请先选择频道");
	return ;
	}
	if(name==""){
	alert("请输入频道名");
	return ;
	}
	var type="7";
		$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "text",
             data:{'type':type,'name':name,'row':row},
             success: function(data){
           if(data==1){
           	alert("修改成功");
           }else{
           	  	alert("修改失败");
           }
                    }            
         });
	}
	
	function upurl(){
	var row =document.getElementById('channelid').value;
	var url = prompt("请输入url");
	if(url==null){
	return;
		}
	
	if(row==""){
	alert("请先选择频道");
	return ;
	}
	if(url==""){
	alert("请输入url");
	return ;
	}
	var type="8";
		$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "text",
             data:{'type':type,'url':url,'row':row},
             success: function(data){
            
           if(data==1){
           	alert("修改成功");
           }else{
           	  alert("修改失败");
           }
                    }            
         });
	}
	
	function query2(){
	var channel = document.getElementById('channelid').value;
	var channelname=  document.getElementById('channelname').innerHTML;
	if(channel==""){
		alert("请选择频道");
		return ;
	}
		
	var row = channel;
	var starttime = document.getElementById('starttime').value;
	var endtime = document.getElementById('endtime').value;
		var type="5";
		$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "json",
             data:{'type':type,'row':row,'starttime':starttime,'endtime':endtime},
             success: function(data){
     		  count(data);
            table(data.data);
                    }            
         });
	}
	
	function cle(){
	var row =document.getElementById('channelid').value;
	if(row==""){
	alert("请先选择频道");
	return ;
	}
	var type="9";
		$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "text",
             data:{'type':type,'row':row},
             success: function(data){
            
           if(data==1){
           	alert("修改成功");
           }else{
           	  alert("修改失败");
           }
                    }            
         });
	}
	
	function getbychannel(){
	var row =document.getElementById('channelid').value;
	if(row==""){
	alert("请先选择频道");
	return ;
	}
	var type="10";
		$.ajax({ 
             type: "POST",
             url: "KeyWord",
             dataType: "text",
             data:{'type':type,'row':row},
             success: function(data){
            
           if(data==1){
           	alert("数据已经开始抓取");
           }else{
           	  alert("操作失败");
           }
               }            
         });
		}             
  function count(data){	
  			var d="关键词总数: "+data.sum+" 排名总数: "+data.num+"  排名占比: "+data.rate+"% 未爬取总数: "+data.nofind;
  			document.getElementById('count').innerHTML= d;
  		}
		
	
  </script>
  
 <body onload="menu()">

<div id="contner">
<div id="header"><h3 style="margin-left:4%;color:#FFFFFF" ><img src="img/logo.gif"  /> &nbsp; &nbsp;&nbsp;kad数据分析平台</h3>
</div>
<div id="mian">
<div id="leftnav">
<div id="nav">
</div>
  <div class="dropdown">
            <a id="dLabel" role="button" data-toggle="dropdown" class="btn btn-primary" data-target="#"
               href="javascript:;">
                 搜索引擎<span class="caret"></span>
            </a>
            <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
                <li class="dropdown-submenu"><a href="javascript:;">百度</a>
                <ul class="dropdown-menu" id="bd">
                        <li><a tabindex="-1" href="javascript:;">二级菜单</a></li>
                        <li><a href="javascript:;">二级菜单</a></li>
                    </ul>
                </li>
                <li class="dropdown-submenu"><a href="javascript:;">360</a>            
                <ul class="dropdown-menu" id="360">
                </ul>
                </li> 
                
                <li class="dropdown-submenu"><a href="javascript:;">收录情况</a>            
                <ul class="dropdown-menu" id="record">
                </ul>
                </li>                
            </ul>
        </div>
</div><!--这部分是左侧导航-->
<div id="rightif">
<div align="center">
<div class="biaoge"  style="min-width:600px;height:30px"><span onclick="showpro()">添加栏目</span>
<span onclick="showword()" style="margin-left:50px">添加关键词</span> </div>
    <div class="biaoge"  id = "addpro" style="min-width:600px;height:70px;display:none">添加栏目：<table><tr align = "center">
    <td width ="10%">搜索引擎 </td>
    <td width ="10%"> <select style="width:100%;" id="cpc1"><option value ="1">百度</option><option value ="2">360</option><option value ="3">收录情况</option></select></td>
	<td width ="10%">频道名称：</td>
	<td width ="10%"><input style="width:100%;height:30px" id="channel1" type="text"></td>
	<td width ="10%" align = "center" width = "10%">包含url</td>
	<td width ="10%"><input style="width:100%;height:30px" id="site" type="text"></td>
	<td width ="10%"><a role="button" onclick ="addchannel()" class="btn btn-primary" data-target="#" href="javascript:;">添加</a></td>
	</tr></table></div>

    <div class="biaoge"  id="addword" style="min-width:600px;height:70px;display:none">添加关键词：
    <table width="100%"><tr>
    <td align = "center" width="10%">搜索引擎 </td>
    <td align = "center" width="10%"><select id="cpc2" onchange = "change()" style="width:100%;"><option value ="1">百度</option><option value ="2">360</option><option value ="3">收录情况</option></select></td>
	<td align = "center" width = "10%">频道名称：</td>
	<td align = "center" width =10%><select id="channel2" style="width:100%;"></select></td>
	<td align = "center" width = "10%">关键词</td>
	<td align = "center" width = "10%"><input style="width:100%;height:30px" id="keyword" type="text"></td>
	<td align = "center" width = "10%"><a role="button" onclick ="addkeyword()" class="btn btn-primary" data-target="#" href="javascript:;">添加</a></td>
	<td align = "center" width = "10%"><a role="button" onclick ="getword()" class="btn btn-primary" data-target="#" href="javascript:;">批量导入</a></td>
	</tr></table></div>
<div class="biaoge"  style="min-width:600px;height:30px"><span>开始时间</span>
<input  class="span2"  style="height:25px;margin-top:5px" type="text"  value="" id="starttime" readonly>
<span onclick="showword()" style="margin-left:50px">结束时间</span>
<input type="text" class="span2" value="" width="100" style="height:25px;margin-top:5px"  id="endtime"  readonly >  
  <a role="button" class="btn btn-primary" data-target="#" onclick="query2()">查询</a><span style="margin-left:100px;color:red;display:none" id = "addview">正在添加关键词,请不要使用其他操作</span>
</div>

    <div id = "tab" style="min-width:600px;height:600px">
  <span style="float:left">频道名称:</span> <span id ="channelname" style="float:left;margin-left:15px;color:blue"></span>
   <input type="hidden" id="channelid"> <a role="button" onclick ="delChannel()" class="btn btn-primary" data-target="#" >删除</a>
  <a role="button" class="btn btn-primary" data-target="#" onclick="update()">修改栏目名</a>
  <a role="button" class="btn btn-primary" data-target="#" onclick="upurl()">修改url</a>
   <a role="button" class="btn btn-primary" data-target="#" onclick="cle()">清空栏目</a>
     <a role="button" class="btn btn-primary" data-target="#" onclick="getbychannel()">抓取数据</a>
  <a role="button" class="btn btn-primary" data-target="#" href="KeyWord">导出</a>
  <div id="count"></div>
    <table style="table-layout:fixed;word-wrap:break-word;word-break:break-all" width=100% class="table table-condensed">
                    <thead>
                          <tr>  <th width = "5%">#</th>  <th width = "15%">关键词</th><th width = "5%">排名</th><th width = "15%">指向URL地址</th><th width = "10%">搜索引擎</th><th width = "15%">检索日期</th></tr>  </thead>
                                <tbody id="tabtr">
                            <tr class="active"> <td>1</td>  <td>人参</td> <td>1</td><td>www.kad360.com</td><td>百度</td><td>2014-06-19</td> </tr>  
                              <tr class="success"> <td>2</td>   <td>萝卜</td>  <td>2</td> <td>www.kad360.com</td> <td>百度</td> <td>2014-06-19</td></tr>
                              <tr class="danger">    <td>4</td>    <td>菜鸟</td>  <td>3</td>   <td>www.kad360.com</td> <td>百度</td> <td>2014-06-19</td></tr> 
                               <tr >  <td class="success">5</td>      <td>二蛋</td>   <td>4</td><td>www.kad360.com</td><td>百度</td><td>2014-06-19</td></tr>
                               </tbody></table></div>
    </div>
</div>
</div>

</div>   
   </body>
   
</html>
<script type="text/javascript"> 
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
