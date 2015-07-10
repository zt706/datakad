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
<script language="javascript" type="text/javascript" src="http://skin.360kad.com/skin/kadjs/jquery-1.7.2.min.js"></script>	

  <link rel="shortcut icon" href="http://mofang.tbcdn.cn/img/favicon.ico">
  <link type="text/css" rel="stylesheet" href="http://mofang.tbcdn.cn/css/common.css?1414825415" media="screen">
<link type="text/css" rel="stylesheet" href="http://mofang.tbcdn.cn/css/app/basic.css?1414825415" media="screen">
<script type="text/javascript" async="" src="http://g.tbcdn.cn/sd/data_sufei/1.1.8/aplus/index.js"></script></head>
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


#container{margin-left:0%;margin-right:0%;background-color:#FFFFFF }
#nav{ width:150px; margin:0px auto; text-align:right;}
#nav h6{ cursor:pointer; line-height:50px; height:30px;margin-top:25px;font-weight:bolder;text-align:center}
#nav a{display:block; line-height:8px;color:#666666;text-align:center; }
#nav a:hover{background-color:#FAF3FA; color:#000;}
#nav div{ border-top:none}

.cube-login-form {
border: 1px solid #eee;
width: 340px;
max-height: 300px;
position: fixed; 
margin-top: 250px;
margin-left: 36%;
-moz-border-radius-topright: 10px;
-moz-border-radius-bottomright: 10px;
-webkit-border-top-left-radius: 10px;
-webkit-border-bottom-left-radius: 10px;
-webkit-border-top-right-radius: 10px;
-webkit-border-bottom-right-radius: 10px;
}

.thumbnail {
cursor:pointer;
height:150px;
display: block;
padding: 4px;
margin-bottom: 20px;
line-height: 1.42857143;
background-color: #4CCCC3;
border: 1px solid #ddd;
border-radius: 4px;
-webkit-transition: all .2s ease-in-out;
-o-transition: all .2s ease-in-out;
transition: all .2s ease-in-out;


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
        /*   $(function () {
            jQuery.ajax({
                type: "Get",
                url: "http://manage.360kad.com/AjaxLogin/GetAdminInfo",
                cache: false,
                dataType: "jsonp",
                jsonp: "callback",
                success: function (data) {
                //alert(data.AdminInfo.LoginName);
           
             if(data.AdminInfo.LoginName!=''){
              $("#password").hide();
                      $("#loginName").hide();
                       $("#pwd").hide();
                       $("#btn").hide();
                       $("#user").val(data.AdminInfo.LoginName);
                        $("#username").html("当前用户:"+data.AdminInfo.LoginName);
                        // alert($("#logout"));
                          $("#logout").show();
                           $("#pwd").val("");
             }
                   // $("#lbLoginName").text(data.LoginName);
                }
            });
        });  

 */
        //登录接口
        function Login() {
       
           // alert($("#loginName").val() + " " + $("#pwd").val());
            jQuery.ajax({
                type: "Get",
                url: "http://manage.360kad.com/Login/AjaxIndex?loginName=" + $("#loginName").val() + "&pwd=" + $("#pwd").val(),
                cache: false,
                dataType: "jsonp",
                jsonp: "callback",
                success: function (data) {            
                    if (data == "登录成功！") {
                   
                     //alert(data);
                     $("#password").hide();
                      $("#loginName").hide();
                       $("#pwd").hide();
                       $("#btn").hide();
                       $("#user").val($("#loginName").val());
                        $("#username").html("当前用户:"+$("#loginName").val());
                        // alert($("#logout"));
                          $("#logout").show();
                           $("#pwd").val("");
                        //location.href = "http://www.360kad.com"; //跳转页
                       //  window.location.href='<%= basePath%>'+"servlet/Target?type=1";
                    }
                    else {
                        //alert(data);
                        return;
                    }
                }
            });
              window.setTimeout("denglu()",2000);       
         
        }
        
        function denglu(){
          jQuery.ajax({
                type: "Get",
                url: "http://manage.360kad.com/AjaxLogin/GetAdminInfo",
                cache: false,
                dataType: "jsonp",
                jsonp: "callback",
                success: function (data) {
                var ids =data.RoleIds;
                   
                	   for (var i=0 ; i< ids.length ; i++){    
                	   if(ids[i]=='633'){
                	    window.location.href='<%= basePath%>'+"servlet/Target?type=1";
                	    return;
                	   	}
                	   } 	
                	   Logout();
                alert("无权限访问,请联系管理员");
                
                return;
                   // $("#lbLoginName").text(data.LoginName);
                }
            });
        }

        //退出接口
        function Logout() {
            jQuery.ajax({
                type: "Get",
                url: "http://manage.360kad.com/AjaxLogin/Logout",
                cache: false,
                dataType: "jsonp",
                jsonp: "callback",
                success: function (data) {
                    //alert("退出成功！");
                      $("#password").show();
                      $("#loginName").show();
                       $("#pwd").show();
                       $("#btn").show();
                      
                       $("#logout").hide();
                       //$("#user").val($("#loginName").val());
                      $("#username").html("用户名:");
                    //location.href = "http://192.168.3.171:819/Login"; //跳转页
                }
            });
        }

        //获取管理员信息接口
         function GetAdminInfo() {
            jQuery.ajax({
                type: "Get",
                url: "http://manage.360kad.com/AjaxLogin/GetAdminInfo",
                cache: false,
                dataType: "jsonp",
                jsonp: "callback",
                success: function (data) {
               // alert(data.AdminInfo.LoginName);
             data.AdminInfo.LoginName;
                   // $("#lbLoginName").text(data.LoginName);
                }
            });
        } 
        
    function tosys(type){
    if(type=='3'){
    	alert("未开放");
    	return;
    }
       if($("#user").val()==""){
    alert("请先登陆");
    }else{
  			jQuery.ajax({
                type: "Get",
                url: "http://manage.360kad.com/AjaxLogin/GetAdminInfo",
                cache: false,
                dataType: "jsonp",
                jsonp: "callback",
                success: function (data) {
                var ids =data.RoleIds;
                   	if(type=='1'){
                	   for (var i=0 ; i< ids.length ; i++){    
                	   if(ids[i]=='617'){
                	    window.location.href='<%= basePath%>'+"servlet/Target?type="+type;
                	    return;
                	   	}
                	   }
                	}
                	if(type=='2'){                	
                	   for (var i=0 ; i< ids.length ; i++){ 
                	   if(ids[i]=='618'){
                	    window.location.href='<%= basePath%>'+"servlet/Target?type="+type;
                	  // window.open('<%= basePath%>'+"servlet/Target?type="+type,'_blank','fullscreen');
                	   
                	    return;
                	   	}   
                	   }
                	}
                	
                alert("无权限访问,请联系管理员");
                return;
                   // $("#lbLoginName").text(data.LoginName);
                }
            });
    	}
    
   
    }
    
    function login2(){
    		//alert(document.getElementById('myform').action);
    		
    		document.getElementById('myform').submit();
    		
    		
    	}
    </script>
 <body >
<!--  	<div id="head" class="cb-h" style="width:1295px;heigth:60;float:left">
  <div class="inner l_1180">
    <div class="cb-h-left" style="margin-left: -55px;margin-top: 4px">
 	<img width=115px src="http://192.168.8.154:8080/datakad/img/logo2.gif" alt="网上药店">
      <a  style="line-height: 60px"> <strong style="float: right;font-size: 26px;font-weight: 700;color:#ffffff;" >&nbsp;&nbsp; 数据平台</strong>   </a>
       
      
      <div class="cb-h-nav"><a href="" class="nav-select">基础报表</a></div>
    </div>
    <strong style="float: left;font-size: 28px;font-weight: 700;color:#ffffff;margin-top:20" > 数据平台</strong>
    <div id="owl" hoverlog="owl"></div>
    <div class="cb-h-right">
      <ul class="cb-h-list">
        
      </ul>
      <div id="headlist" class="headlist">
        <span class="list-name">您好，数据魔方KAD版！</span>
      <ul class="headlist"><li class="list-seperator"></li><li class="list-item" id="profile" hoverlog="profile"><div class="profile-head">
      <a class="profile-head-title" href="/profile" target="_blank">账户</a></div><div class="profile-content"><ul><li>
      <a href="/profile#submitOrder" target="_blank" title="订购续费">订购续费</a></li></ul>
      <a href="/p/logout" class="profile-logout">退出</a></div></li><li class="list-seperator"></li><li class="list-item" id="help" log="help">
      <div class="help-head"><span class="help-head-title">帮助</span></div><div class="help-content"><ul><li>
      <a href="javascript:void(0);" id="newbie_guider" title="新手引导" log="newbie_guider">新手引导</a></li><li>
      <a href="http://mofang.taobao.com/index/help" target="_blank" title="魔方学堂" log="help_more">魔方学堂</a></li></ul></div></li>
      <li class="list-seperator"></li></ul></div>
    </div>
  </div>
</div> -->
<input type="text" name="user" id="user" value=""  style="display:none;"/>
<div id="contner">

	<!-- <div id="header"><br/><span style="margin-top:30px;font-size:30px;color:#FFFFFF"> &nbsp;数据分析平台</span></div> -->
	

	
	<div id="mian">
		<form id="myform" action="<%= basePath%>/servlet/Target" method="post">
		<div id="login" name="login" class="cube-login-form" style="filter: Alpha(Opacity=10);height:100%;background-color:#333333">
			<div style="font-family: 'microsoft yahei';font-size: 18px;margin: 10px 10px 10px 20px;float:left">欢迎来到数据平台</div></br></br></br>
      			<div class="form-inline">
      			<span id = "username" name="username2" style="margin-left:30px;float:left;font-size:15px;">用户名：</span>
                <input id="loginName" name="username" style="height:32px;width: 156px" class="span3" type="text" placeholder="">
                <br/><br/><span id="password" name="password2" style="margin-left:40px;float:left;font-size:15px;">密码 ： </span>
                <input  id="pwd" name="passwd" style="height:32px;;width: 156px" type="password" class="span3" placeholder=""></br>
              <div style= "margin: 20px 40px 20px 40px"><button id="btn" name="btn" onclick="login2()" class="btn btn-warning btn-block" href="#">登陆</button></div>
              <div id="logout" name="logout" style= "margin: 20px 40px 20px 40px;display:none"><button  onclick="Logout()" class="btn btn-warning btn-block" href="#">退出</button></div>
               
                </div>
   		 </div>
   		 </form>
    	<div id="container" style="width:85%;height:98%;margin-left: auto;margin-right: auto;background-size:cover;background-image:url('./img/brg.jpg')" >
    		<div class="container">
			<!-- 	<div class="row" style= "margin-top:130px;">
					<div class="span3">
					<div class="thumbnail" onmouseover="this.style.backgroundColor='#33CCFF'" onmouseout="this.style.backgroundColor='#3399CC'" onclick ="tosys('1')" style="background-color:#1FA67A;font-size:22px;"><br/>竞争对手商品系统</div>
					</div>
					<div class="span3">
					<div class="thumbnail" onmouseover="this.style.backgroundColor='#00FF33'" onmouseout="this.style.backgroundColor='#00CC66'" style="font-size:22px;" onclick ="tosys('2')"><br/>SEO系统</div>
					</div>
					<div class="span3">
					<div class="thumbnail" onmouseover="this.style.backgroundColor='#00CCFF'" onmouseout="this.style.backgroundColor='#0066FF'" onclick ="tosys('3')" style="background-color:#0066FF;font-size:22px;"><br/>商品价格监控系统</div>
					</div>
				</div> -->
			</div>
    	
    	</div>
	</div>	
</div>
   </body>
 </html>

