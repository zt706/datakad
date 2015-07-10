<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html><head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>康爱多 - 数据平台</title>
  <meta name="description" content="数据魔方 - 淘宝官方数据产品 - 让市场分析更简单！">
  <meta name="keywords" content="数据魔方,魔方,cube,mofang,市场分析,淘词,数据分析,数据运营,行情,趋势,行业分析,品牌分析,产品分析,热卖宝贝,店铺排行,品牌排行,标题诊断">
  <link href="http://data.360kad.com//css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="http://data.360kad.com//css/styles.css" type="text/css" rel="stylesheet"/>
   <script src="/js/jquery-1.8.2.min.js"></script>
    <script src="/js/hcharts/highcharts.js"></script>
    
  <link rel="shortcut icon" href="http://mofang.tbcdn.cn/img/favicon.ico">
  <link type="text/css" rel="stylesheet" href="http://mofang.tbcdn.cn/css/common.css?1414825415" media="screen">
  
<link type="text/css" rel="stylesheet" href="http://mofang.tbcdn.cn/css/app/basic.css?1414825415" media="screen">

<script type="text/javascript" async="" charset="utf-8" src="http://c.cnzz.com/core.php?web_id=1000181376&amp;t=q"></script>
<script type="text/javascript" async="" id="tb-beacon-aplus" exparams="category=&amp;userid=&amp;aplus" src="http://a.tbcdn.cn/s/aplus_v2.js"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/util/event.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/view/view.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/util/cookie.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/util/class.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/ui/dialog.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/app/basic/p/config.js?1414825415"></script>
<script type="text/javascript" async="" src="http://a.tbcdn.cn/s/fdc/??spm.js,spmact.js?v=141029"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/app/index.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/view/menu.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/app/basic/report/live.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/data/livedata.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/view/column2.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/ctrl/clock.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/ctrl/cate_filter.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/ctrl/shop_filter.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/view/live_chart.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/view/live_info.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/view/live_bar.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/view/live_table.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/view/live_map.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/ui/delay_tip.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/view/chart.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/util/tip.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/view/combo.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/util/pinyin.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/com/data/category.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/util/cate_info.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/core/underscore-1.4.2.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/core/jquery.mousewheel.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/ui/jscrollpane.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/core/liveMap.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/core/raphael.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/core/swfobject.js?1414825415"></script>
<script type="text/javascript" async="" charset="utf-8" src="http://mofang.tbcdn.cn/js/lib/core/datav.js?1414825415"></script>
<script type="text/javascript" async="" src="http://g.tbcdn.cn/sd/data_sufei/1.1.8/aplus/index.js"></script></head>
<body class="bg_explorer colorTipContainer" onload="update()">
<style type="text/css"> 

.main-t {
width: 200px;
min-width: 200px;
}

.yellow_tip {
background-color: #f9edb9;
border: 1px solid #efc45e;
border-radius: 4px;
color: #333;
padding: 5px 10px;
}

.colorTip {
display: none;
position: absolute;
left: 40%;
padding: 6px;
background-color: white;
font-family: Arial,Helvetica,sans-serif;
font-size: 11px;
font-weight: normal;
line-height: 21px;
text-decoration: none;
text-align: left;
text-shadow: 0 0 1px white;
width: 703px;
}
 </style>
<script type="text/javascript">
var statu = 1;
 var f = 0;
  var ws = null;  
  var data = null;
$(function () {  
   if ('WebSocket' in window)  
        ws = new WebSocket("ws://data.360kad.com/websocket.ws");  
    else if ('MozWebSocket' in window)  
       ws = new MozWebSocket("ws://data.360kad.com/websocket.ws");  
    else  
       alert("not support");  
      
      
   ws.onmessage = function(evt) {  
       //alert(evt.data); 
       
       //alert( document.getElementById("f1").innerHTML); 
       var obj = JSON.parse(evt.data); 
         data = obj;
         if(statu == 0){
         setdata(obj.d1); 
        setdata2(obj.d1); 
         }else{
         setdata(obj.d2); 
        setdata2(obj.d2); 
         }
   		
       //data = evt.data;
   };  
       ws.onerror = function() {  
                               //è¿æ¥å¤±è´¥  
                        alert("连接错误");
                 }  ;
      
    ws.onclose = function(evt) {  
       
    };  
      
    ws.onopen = function(evt) {  
      // alert("open");  
       
    };   	
});  
 
function setdata2(obj){
     document.getElementById("f1").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.f1+"元</strong>";
         document.getElementById("f2").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.f2+"元</strong>";
           document.getElementById("f3").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.f4+"元</strong>";
         document.getElementById("f4").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.f5+"元</strong>";
           document.getElementById("f5").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.f6+"元</strong>";
         document.getElementById("f6").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.f7+"元</strong>";
           document.getElementById("f7").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.f8+"元</strong>";
         document.getElementById("f8").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.f9+"元</strong>";
           document.getElementById("f9").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.f10+"元</strong>";
         document.getElementById("f10").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.f11+"元</strong>";
           document.getElementById("f11").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.f12+"元</strong>";
         document.getElementById("f12").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.f13+"元</strong>";
         document.getElementById("f13").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.f14+"元</strong>";
         document.getElementById("f14").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.f15+"元</strong>";
 		document.getElementById("f15").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.f16+"元</strong>";
         document.getElementById("f16").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.f17+"元</strong>";
		 document.getElementById("y1").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.y1+"元</strong>";
         document.getElementById("y2").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.y2+"元</strong>";
           document.getElementById("y3").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.y3+"元</strong>";
         document.getElementById("y4").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.y4+"元</strong>";
           document.getElementById("y5").innerHTML =  "<strong style=font-size: 28px;font-weight: 700;color:##3c9dff>"+obj.y5+"元</strong>";
         document.getElementById("y6").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.y6+"元</strong>";
          document.getElementById("y7").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.y7+"元</strong>";
        document.getElementById("y8").innerHTML = "<strong style=font-size: 28px;font-weight: 700;color:#ff9e00>"+ obj.y8+"元</strong>";
         
}
 
 function update(){
 setInterval("sendMsg()",5000); 
 //setInterval("skip()",20000); 
 }
function sendMsg() { 
	if(f%30==0){
	
	 ws.send("");  
	}
	f=f+1;
   
} 

function setdata(data) {
	
var obj = data; 
Highcharts.setOptions({ 
    colors: ['#ff9e00', '#3c9dff', '#004B97', '#24CBE5', '#64E572', '#FF9655', 
'#FFF263', '#6AF9C4'] 
}); 
    $('#content1').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: obj.time2
        },
        yAxis: {
        	min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            enabled: true,
           /*  formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'Â°C';
            }, */
             shared: true
        },
        plotOptions: {
        spline: { 
        		lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
        
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
         
        series: [{
        	visible:true,
            name: '今天',
            data: obj.count
        }, {
       		 visible:true,
            name: '昨天',
            data: obj.count2
        }],
        credits:{
        text:'',
        href:''
        }
    });
    
     $('#content12').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: obj.time
        },
        yAxis: {
        	min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            enabled: true,
           /*  formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'Â°C';
            }, */
             shared: true
        },
        plotOptions: {
        spline: {
       			 lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
        
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        
        series: [{
        	visible:true,
            name: '今天',
            data: obj.count4
        },{
        	visible:true,
            name: '昨天',
            data: obj.count6
        }],
        credits:{
        text:'',
        href:''
        }
    });
    
    $('#content13').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: obj.time
        },
        yAxis: {
        	min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            enabled: true,
           /*  formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'Â°C';
            }, */
             shared: true
        },
        plotOptions: {
        spline: {
        		lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
        
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        
        series: [{
        	visible:true,
            name: '今天',
            data: obj.count5
        },{
        	visible:true,
            name: '昨天',
            data: obj.count7
        }],
        credits:{
        text:'',
        href:''
        }
    });
    
    $('#content14').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: obj.time
        },
        yAxis: {
        	min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            enabled: true,
           /*  formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'Â°C';
            }, */
             shared: true
        },
        plotOptions: {
        spline: {
        		lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
        
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        
        series: [{
        	visible:true,
            name: '今天',
            data: obj.count8
        },{
        	visible:true,
            name: '昨天',
            data: obj.count9
        }],
        credits:{
        text:'',
        href:''
        }
    });
    
    $('#content15').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: obj.time
        },
        yAxis: {
        	min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            enabled: true,
           /*  formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'Â°C';
            }, */
             shared: true
        },
        plotOptions: {
        spline: {
      		  lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
        
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        
        series: [{
        	visible:true,
            name: '今天',
            data: obj.count10
        },{
        	visible:true,
            name: '昨天',
            data: obj.count11
        }],
        credits:{
        text:'',
        href:''
        }
    });
    
    $('#content16').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: obj.time
        },
        yAxis: {
        	min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            enabled: true,
           /*  formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'Â°C';
            }, */
             shared: true
        },
        plotOptions: {
        spline: {
        		lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
        
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        
        series: [{
        	visible:true,
            name: '今天',
            data: obj.count12
        },{
        	visible:true,
            name: '昨天',
            data: obj.count13
        }],
        credits:{
        text:'',
        href:''
        }
    });
    
     $('#content17').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: obj.time
        },
        yAxis: {
        	min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            enabled: true,
           /*  formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'Â°C';
            }, */
             shared: true
        },
        plotOptions: {
        spline: {
        		lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
        
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        
        series: [{
        	visible:true,
            name: '今天',
            data: obj.count14
        },{
        	visible:true,
            name: '昨天',
            data: obj.count15
        }],
        credits:{
        text:'',
        href:''
        }
    });
    
      $('#content18').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: obj.time
        },
        yAxis: {
        	min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            enabled: true,
           /*  formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'Â°C';
            }, */
             shared: true
        },
        plotOptions: {
        spline: {
        		lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
        
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        
        series: [{
        	visible:true,
            name: '今天',
            data: obj.count16
        },{
        	visible:true,
            name: '昨天',
            data: obj.count17
        }],
        credits:{
        text:'',
        href:''
        }
    });
};


function change(type){
	if(type=='0'){
	statu = 0;
		setdata(data.d1);
		setdata2(data.d1);
	}
	if(type=='1'){
	statu =1;
	setdata(data.d2);
		setdata2(data.d2);
	}
}

function showspan(){
	//alert(document.getElementById("span1").style.display);
	document.getElementById("span1").style.display = "block";
}
function hidespan(){
	//alert(document.getElementById("span1").style.display);
	document.getElementById("span1").style.display = "none";
}
</script>
<script type="text/javascript">
(function (d) {
var t=d.createElement("script");t.type="text/javascript";t.async=true;t.id="tb-beacon-aplus";
t.setAttribute("exparams","category=&userid=&aplus");
t.src=("https:"==d.location.protocol?"https://s":"http://a")+".tbcdn.cn/s/aplus_v2.js";
d.getElementsByTagName("head")[0].appendChild(t);
})(document);
</script>

<span class="colorTip yellow_tip" id="span1" style="z-index:2;top: 101px; left: 520px;position: fixed;">
指标说明：
<br/>1) 时间：按照下单时间 
<br/>2) 四种状态：2待财务确认，3待发货，4待确认收货，5交易完成
<br/>3) 六种状态：0待审核，1待付款，2待财务确认，3待发货，4待确认收货，5交易完成（默认）
<br/>4) 销售额：（商品单价 - 商品优惠） * 商品销售数量 + 订单运费 - 订单优惠
<br/>5) 刷新频率：每2分钟刷新一次</span>
<div id="head" class="cb-h">
  <div class="inner l_1180">
    <div class="cb-h-left" style="margin-left: 0px;margin-top: 8px">
 	<img width=115px src="http://data.360kad.com//img/logo2.gif" alt="网上药店">
      <a  style="line-height: 60px"> <strong style="float: right;font-size: 26px;font-weight: 700;color:#ffffff;" >&nbsp; 数据平台</strong>   </a>
       
      
      <!--<div class="cb-h-nav"><a href="" class="nav-select">基础报表</a></div>-->
    </div>
  <!--   <strong style="float: left;font-size: 28px;font-weight: 700;color:#ffffff;margin-top:20" > 数据平台</strong> -->
    <div id="owl" hoverlog="owl"></div>
    <div class="cb-h-right">
      <ul class="cb-h-list">
        
      </ul>
      <div id="headlist" class="headlist">
        <span class="list-name">您好</span>
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
</div>

<div id="body" class="cb-b l_1180">
  <!-- layout,负责页面的框架 -->
  <div id="main"><div class="main-t">   
     <div id="l_sidebar" class="l-sidebar">
     	<ul class="c-menu"><li class="c-menu-seperator c-menu-seperator-none" style="border: none;"></li>
     						<li class="c-menu-select">    <a style="padding-top:20px;height:16px" href="#" rel="live" rname="第一时间">第一时间</a></li>
     						<li class="c-menu-seperator c-menu-seperator-none"></li>
     						<li param="tabs4" class="">    <a href="#" rname="整体情况"></a></li>
     						<li class="c-menu-seperator"></li>
     						<li class="has-children">    <a href="#" rname="热销品牌排行榜" class="children-show">销售分析</a>
     							<ul class="c-menu-children">
     								<li param="tabs8" class="">    <a href="#" rname="热销品牌排行榜"></a></li>
     								<li param="tabs5" class="">    <a href="#" rname="整体情况"></a></li>
     							</ul></li>
     						<li class="c-menu-seperator"></li>
     						<li class="has-children">    <a href="#" rname="产品热销排行" class="children-show">产品分析</a>
     							<ul class="c-menu-children">
     								<li class="">    <a href="#" rel="prod_hot" rname="产品热销排行"></a></li>
     								<li param="tabs6" class="">    <a href="#" rname="整体情况"></a></li>
     							</ul></li>
     						<li class="c-menu-seperator"></li>
     						<li class="has-children">    <a href="#" rname="属性组合排行" class="children-show">流量分析</a>
     							<ul class="c-menu-children">
     								<li class="">    <a href="#" rel="prop_hot_cat" rname="属性组合排行"></a></li>
     								<li class="">    <a href="#" rel="prop_hot" rname="属性热销排行"></a></li>
     								<li param="tabs10" class="">    <a href="#" rname="整体情况"></a></li>
     							</ul></li>
     						<li class="c-menu-seperator"></li>
     						<li class="has-children">    <a href="#" rname="行业关键词热搜TOP榜" class="children-show">热词分析</a>
     							<ul class="c-menu-children">
     								<li param="tabs1" class="">    <a href="#" rname="行业关键词热搜TOP榜"></a></li>
     								<li param="tabs2" class="">    <a href="#" rel="kw" rname="全网关键词查询"></a></li>
     								<li class="">    <a href="#" rel="kw_auct_title_index" rname="宝贝标题诊断"></a></li>
     							</ul></li>
     						<li class="c-menu-seperator"></li>
     						<li class="">    <a href="#" rel="lost_customer" rname="流失顾客分析"></a></li>
     						<li class="c-menu-seperator"></li>
     						<li param="tabs3" class="">    <a href="#" rname="整体状况"></a></li>
     							</ul>
     			</div>    </div>    
     			<div class="main-r">      <div id="l_report" class="l-report"><div class="l-report-border">       
     			 <div id="l_navigation" style="height:10px;" class="l-navigation"><a href="javascript:void(0)" style="margin-left: 0px;">销售快报</a>
     			 <div class="report-help">  <span  onmouseover="showspan()" onmouseout="hidespan()" class="report-help-icon"></span> 
     			  <div class="report-help-content-triangle"></div>  </div>  
     			  <div class="report-help-content">    <div class="report-help-content-border">  
     			    <div class="report-help-tip">    <div class="report-help-info">    <div class="report-help-info-content">   
     			       <h4>报表简介：</h4>      <p id="report-help-intro"></p>    </div>    </div>  
     			         <div class="report-help-question">    <div class="report-help-question-content" id="report-help-right">   
     			          </div>    </div>    </div>    </div>  </div></div>      
     			            
     			                         <div id="l_tab" class="l-tab" style="display: none;"></div>    
     			                             <div id="l_loading" class="loading" style="display: none;"></div>     
     			                  <div id="l_view" class="l-view">
     			                	  <div id="layout1_0" com="s" class="c-node live-layout">
     			                  		<div class="layout-column-padding0">   
     			                   			<div id="live_chart_parent_id" class="layout-column-border">
     			                 				 <div id="live_chart_0" class="comNode live-chart"> 
     			                   					<div class="live-content" style="width:940px;" >
     			                   						<div class="live_chart_total" style="width:930px;background:#353535">
     			                   							<div class="total_today"  style = "width: 200px">    <span>今日累计（元）</span><p id="f1"><strong></strong></p>   
     			                     						 </div>
     			                     						 <div class="total_today"  style = "width: 200px">    <span>昨日同期累计（元）</span><p id="y1"><strong></strong></p>   
     			                     						 </div>  
     			                     					 	 <div class="total_yestoday">    <span>昨日累计（元）</span>  <p id="f2"> <strong></strong></p>  
     			                      					 	</div>
     			                      					 	<strong style="font-size: 28px;font-weight: 700;color:#ff9e00">全司</strong>
     			                      					 </div> 
     			                        				<div class="live-chart-content" id="live_chart_0_content">
     			                       						<div class="chart c-border chart-collect" style="border: none;">     
     			                         						
     			                         						  <div class="metrics"><label><input onclick = "change('0')" class="chart_radio" param="0" type="radio" name="live_chart_0_content_metric" >4种状态</label><label>
     			                  								<input onclick = "change('1')" checked="checked" class="chart_radio" param="1" type="radio" name="live_chart_0_content_metric">6种状态</label></div> 
     			                  									
     			                  									<div id="" style="width: 100%; border: none;" class="chart">
     			                  										<div id="content1" style="width:100%;height:250px;">
     			                  										</div>
     			                  									</div>		
     			                  							</div>
     			                  						</div>
     			                  					</div>
     			                  				</div>
     			                  			</div>  
     			                  	  </div>
     			                  </div>			                  			     			                  	
     			                  <div class="clearfix"></div>
     			           </div>
     			           
     			           
     			                   			            <div id="l_view8" class="l-view">
     			                	  <div id="layout1_8" com="s" class="c-node live-layout">
     			                  		<div class="layout-column-padding0">   
     			                   			<div id="live_chart_parent_id6" class="layout-column-border">
     			                 				 <div id="live_chart_8" class="comNode live-chart"> 
     			                   					<div class="live-content" style="width:940px;" >
     			                   						<div class="live_chart_total" style="width:930px;background:#353535">
     			                   							<div class="total_today" style="width:200px;">    <span>今日累计（元）</span><p id="f15"><strong></strong></p>   
     			                     						 </div>  
     			                     						 <div class="total_today" style="width:200px;">    <span>昨日同期累计（元）</span><p id="y8"><strong></strong></p>   
     			                     						 </div>  
     			                     					 	 <div class="total_yestoday">    <span>昨日累计（元）</span>  <p id="f16"> <strong></strong></p>  
     			                      					 	</div>
     			                      					 	<strong style="font-size: 28px;font-weight: 700;color:#ff9e00">自建</strong>
     			                      					 </div> 
     			                        				<div class="live-chart-content" id="live_chart_0_content6">
     			                       						<div class="chart c-border chart-collect" style="border: none;">  
     			                       						   
     			                         						<!--  <div class="metrics"><label><input class="chart_radio" param="0" type="radio" name="live_chart_0_content_metric" checked="checked">成交金额</label><label>
     			                  								<input class="chart_radio" param="1" type="radio" name="live_chart_0_content_metric">成交笔数</label></div>	 -->
     			                  								
     			                  									<div id="" style="width: 100%; border: none;" class="chart">
     			                  										<div id="content18" style="width:100%;height:250px;">
     			                  										</div>
     			                  									</div>		
     			                  							</div>
     			                  						</div>
     			                  					</div>
     			                  				</div>
     			                  			</div>  
     			                  	  </div>
     			                  </div>			                  			     			                  	
     			                  <div class="clearfix"></div>
     			           </div>
     			           
     			           
     			           	       <div id="l_view3" class="l-view">
     			                	  <div id="layout1_3" com="s" class="c-node live-layout">
     			                  		<div class="layout-column-padding0">   
     			                   			<div id="live_chart_parent_id3" class="layout-column-border">
     			                 				 <div id="live_chart_3" class="comNode live-chart"> 
     			                   					<div class="live-content" style="width:940px;" >
     			                   						<div class="live_chart_total" style="width:930px;background:#353535">
     			                   							<div class="total_today" style="width:200px">    <span>今日累计（元）</span><p id="f5"><strong></strong></p>   
     			                     						 </div>  
     			                     						 <div class="total_today" style="width:200px">    <span>昨日同期累计（元）</span><p id="y3"><strong></strong></p>   
     			                     						 </div>
     			                     					 	 <div class="total_yestoday">    <span>昨日累计（元）</span>  <p id="f6"> <strong></strong></p>  
     			                      					 	</div>
     			                      					 	<strong style="font-size: 28px;font-weight: 700;color:#ff9e00">官网</strong>
     			                      					 </div> 
     			                        				<div class="live-chart-content" id="live_chart_0_content3">
     			                       						<div class="chart c-border chart-collect" style="border: none;">     
     			                         						
     			                         						<!--  <div class="metrics"><label><input class="chart_radio" param="0" type="radio" name="live_chart_0_content_metric" checked="checked">成交金额</label><label>
     			                  								<input class="chart_radio" param="1" type="radio" name="live_chart_0_content_metric">成交笔数</label></div>	 -->
     			                  								
     			                  									<div id="" style="width: 100%; border: none;" class="chart">
     			                  										<div id="content13" style="width:100%;height:250px;">
     			                  										</div>
     			                  									</div>		
     			                  							</div>
     			                  						</div>
     			                  					</div>
     			                  				</div>
     			                  			</div>  
     			                  	  </div>
     			                  </div>			                  			     			                  	
     			                  <div class="clearfix"></div>
     			           </div>
     			           
     			                <div id="l_view4" class="l-view">
     			                	  <div id="layout1_4" com="s" class="c-node live-layout">
     			                  		<div class="layout-column-padding0">   
     			                   			<div id="live_chart_parent_id4" class="layout-column-border">
     			                 				 <div id="live_chart_4" class="comNode live-chart"> 
     			                   					<div class="live-content" style="width:940px;" >
     			                   						<div class="live_chart_total" style="width:930px;background:#353535">
     			                   							<div class="total_today"  style="width:200px;">    <span>今日累计（元）</span><p id="f7"><strong></strong></p>   
     			                     						 </div>  
     			                     						 <div class="total_today"  style="width:200px;">    <span>昨日同期累计（元）</span><p id="y4"><strong></strong></p>   
     			                     						 </div>  
     			                     					 	 <div class="total_yestoday">    <span>昨日累计（元）</span>  <p id="f8"> <strong></strong></p>  
     			                      					 	</div>
     			                      					 	<strong style="font-size: 28px;font-weight: 700;color:#ff9e00">WAP</strong>
     			                      					 </div> 
     			                        				<div class="live-chart-content" id="live_chart_0_content4">
     			                       						<div class="chart c-border chart-collect" style="border: none;">     
     			                         						
     			                         					<!-- 	 <div class="metrics"><label><input class="chart_radio" param="0" type="radio" name="live_chart_0_content_metric" checked="checked">成交金额</label><label>
     			                  								<input class="chart_radio" param="1" type="radio" name="live_chart_0_content_metric">成交笔数</label></div> -->
     			                  									
     			                  									<div id="" style="width: 100%; border: none;" class="chart">
     			                  										<div id="content14" style="width:100%;height:250px;">
     			                  										</div>
     			                  									</div>		
     			                  							</div>
     			                  						</div>
     			                  					</div>
     			                  				</div>
     			                  			</div>  
     			                  	  </div>
     			                  </div>			                  			     			                  	
     			                  <div class="clearfix"></div>
     			           </div>
     			           
     			            <div id="l_view5" class="l-view">
     			                	  <div id="layout1_5" com="s" class="c-node live-layout">
     			                  		<div class="layout-column-padding0">   
     			                   			<div id="live_chart_parent_id5" class="layout-column-border">
     			                 				 <div id="live_chart_4" class="comNode live-chart"> 
     			                   					<div class="live-content" style="width:940px;" >
     			                   						<div class="live_chart_total" style="width:930px;background:#353535">
     			                   							<div class="total_today" style="width:200px;">    <span>今日累计（元）</span><p id="f9"><strong></strong></p>   
     			                     						 </div>  
     			                     						 <div class="total_today" style="width:200px;">    <span>昨日同期累计（元）</span><p id="y5"><strong></strong></p>   
     			                     						 </div>  
     			                     					 	 <div class="total_yestoday" style="width:350px;">    <span>昨日累计（元）</span>  <p id="f10"> <strong></strong></p>  
     			                      					 	</div>
     			                      					 <strong style="font-size: 28px;font-weight: 700;color:#ff9e00">Android</strong>
     			                      					 </div> 
     			                        				<div class="live-chart-content" id="live_chart_0_content5">
     			                       						<div class="chart c-border chart-collect" style="border: none;">  
     			                       						   
     			                         					<!-- 	 <div class="metrics"><label><input class="chart_radio" param="0" type="radio" name="live_chart_0_content_metric" checked="checked">成交金额</label><label>
     			                  								<input class="chart_radio" param="1" type="radio" name="live_chart_0_content_metric">成交笔数</label></div>	 -->
     			                  								
     			                  									<div id="" style="width: 100%; border: none;" class="chart">
     			                  										<div id="content15" style="width:100%;height:250px;">
     			                  										</div>
     			                  									</div>		
     			                  							</div>
     			                  						</div>
     			                  					</div>
     			                  				</div>
     			                  			</div>  
     			                  	  </div>
     			                  </div>			                  			     			                  	
     			                  <div class="clearfix"></div>
     			           </div>
     			           
     			                 <div id="l_view7" class="l-view">
     			                	  <div id="layout1_7" com="s" class="c-node live-layout">
     			                  		<div class="layout-column-padding0">   
     			                   			<div id="live_chart_parent_id7" class="layout-column-border">
     			                 				 <div id="live_chart_7" class="comNode live-chart"> 
     			                   					<div class="live-content" style="width:940px;" >
     			                   						<div class="live_chart_total" style="width:930px;background:#353535">
     			                   							<div class="total_today" style="width:200px;">    <span>今日累计（元）</span><p id="f13"><strong></strong></p>   
     			                     						 </div>  
     			                     						 <div class="total_today" style="width:200px;">    <span>昨日同期累计（元）</span><p id="y7"><strong></strong></p>   
     			                     						 </div>  
     			                     					 	 <div class="total_yestoday" style="width:350px;">    <span>昨日累计（元）</span>  <p id="f14"> <strong></strong></p>  
     			                      					 	</div>
     			                      					 <strong style="font-size: 28px;font-weight: 700;color:#ff9e00">IOS</strong>
     			                      					 </div> 
     			                        				<div class="live-chart-content" id="live_chart_0_content5">
     			                       						<div class="chart c-border chart-collect" style="border: none;">  
     			                       						   
     			                         					<!-- 	 <div class="metrics"><label><input class="chart_radio" param="0" type="radio" name="live_chart_0_content_metric" checked="checked">成交金额</label><label>
     			                  								<input class="chart_radio" param="1" type="radio" name="live_chart_0_content_metric">成交笔数</label></div>	 -->
     			                  								
     			                  									<div id="" style="width: 100%; border: none;" class="chart">
     			                  										<div id="content17" style="width:100%;height:250px;">
     			                  										</div>
     			                  									</div>		
     			                  							</div>
     			                  						</div>
     			                  					</div>
     			                  				</div>
     			                  			</div>  
     			                  	  </div>
     			                  </div>			                  			     			                  	
     			                  <div class="clearfix"></div>
     			           </div>
     			           
     			             <div id="l_view2" class="l-view">
     			                	  <div id="layout1_2" com="s" class="c-node live-layout">
     			                  		<div class="layout-column-padding0">   
     			                   			<div id="live_chart_parent_id2" class="layout-column-border">
     			                 				 <div id="live_chart_0" class="comNode live-chart"> 
     			                   					<div class="live-content" style="width:940px;" >
     			                   						<div class="live_chart_total" style="width:930px;background:#353535">
     			                   							<div class="total_today" style = "width: 200px">    <span>今日累计（元）</span><p id="f3"><strong></strong></p>   
     			                     						 </div> 
     			                     						 <div class="total_today" style = "width: 200px">    <span>昨日同期累计（元）</span><p id="y2"><strong></strong></p>   
     			                     						 </div>  
     			                     					 	 <div class="total_yestoday">    <span>昨日累计（元）</span>  <p id="f4"> <strong></strong></p>  
     			                      					 	</div>
     			                      					 	<strong style="font-size: 28px;font-weight: 700;color:#ff9e00">天猫</strong>
     			                      					 </div> 
     			                        				<div class="live-chart-content" id="live_chart_0_content2">
     			                       						<div class="chart c-border chart-collect" style="border: none;">     
     			                         						
     			                         						<!--  <div class="metrics"><label><input class="chart_radio" param="0" type="radio" name="live_chart_0_content_metric" checked="checked">成交金额</label><label>
     			                  								<input class="chart_radio" param="1" type="radio" name="live_chart_0_content_metric">成交笔数</label></div> -->	
     			                  								
     			                  									<div id="" style="width: 100%; border: none;" class="chart">
     			                  										<div id="content12" style="width:100%;height:250px;">
     			                  										</div>
     			                  									</div>		
     			                  							</div>
     			                  						</div>
     			                  					</div>
     			                  				</div>
     			                  			</div>  
     			                  	  </div>
     			                  </div>			                  			     			                  	
     			                  <div class="clearfix"></div>
     			           </div>
     			           
     			           
     			            <div id="l_view6" class="l-view">
     			                	  <div id="layout1_6" com="s" class="c-node live-layout">
     			                  		<div class="layout-column-padding0">   
     			                   			<div id="live_chart_parent_id6" class="layout-column-border">
     			                 				 <div id="live_chart_6" class="comNode live-chart"> 
     			                   					<div class="live-content" style="width:940px;" >
     			                   						<div class="live_chart_total" style="width:930px;background:#353535">
     			                   							<div class="total_today" style="width:200px;">    <span>今日累计（元）</span><p id="f11"><strong></strong></p>   
     			                     						 </div>  
     			                     						 <div class="total_today" style="width:200px;">    <span>昨日同期累计（元）</span><p id="y6"><strong></strong></p>   
     			                     						 </div>  
     			                     					 	 <div class="total_yestoday">    <span>昨日累计（元）</span>  <p id="f12"> <strong></strong></p>  
     			                      					 	</div>
     			                      					 	<strong style="font-size: 28px;font-weight: 700;color:#ff9e00">京东</strong>
     			                      					 </div> 
     			                        				<div class="live-chart-content" id="live_chart_0_content6">
     			                       						<div class="chart c-border chart-collect" style="border: none;">  
     			                       						   
     			                         						<!--  <div class="metrics"><label><input class="chart_radio" param="0" type="radio" name="live_chart_0_content_metric" checked="checked">成交金额</label><label>
     			                  								<input class="chart_radio" param="1" type="radio" name="live_chart_0_content_metric">成交笔数</label></div>	 -->
     			                  								
     			                  									<div id="" style="width: 100%; border: none;" class="chart">
     			                  										<div id="content16" style="width:100%;height:250px;">
     			                  										</div>
     			                  									</div>		
     			                  							</div>
     			                  						</div>
     			                  					</div>
     			                  				</div>
     			                  			</div>  
     			                  	  </div>
     			                  </div>			                  			     			                  	
     			                  <div class="clearfix"></div>
     			           </div>
     			           
 <!--     			                  		<div id="layout2_0" com="s" class="c-node live-layout">
     			                  			<div class="layout-column-unit">
     			                  				<div class="layout-column-padding0">  
     			                  		  			<div id="live_bar_parent_id" class="layout-column-border">
     			                  		  				<div id="live_bar_0" class="comNode">
     			                  		 					 <h4 class="live-title">天猫销售额实时走向图</h4> 
     			                  		 					  <div class="live-content"> 
     			                  		 							<div id="content3" style="width:100%;height:250px;">   			                  		  			                  		 	
     			                  									</div> 
     			                  		 					  </div>
     			                  		   				</div>
     			                  		 		  </div>   
     			                  		    	</div>
     			                  			 </div>
     			                  		 	<div class="layout-column-unit">
     			                  		 		<div class="layout-column-padding1">  
     			                  		  			 <div id="live_table_list_parent_id" class="layout-column-border">
     			                  		  			 	<div id="live_table_list_0" class="comNode">
     			                  						<h4 class="live-title">今日"手机"行业Top100店铺</h4>
     			                  							<div class="live-content">
     			                  								<div id="div_live_table_list_0" class="liveTable">
     			                  		  							<div class="liveTableHead"> 
     			                  		  							 <table><thead><tr><th style="width:10%;">&nbsp;</th>
     			                  		 							 <th style="width:55%;" class="td_left" index="1" log="table">店铺信息</th>
     			                  		 							 <th style="width:35%;" class="undefined" index="3" log="table">热卖指数</th></tr></thead>
     			                  		 							 </table> 
     			                  		  						 </div> 
     			                  		  						 <div class="liveTableBody scroll-pane" style="overflow: hidden; padding: 0px; width: 462px;">   
     			                  		    							<div class="jspContainer" style="width: 462px; height: 216px;">
     			                  											<div class="jspPane" style="padding: 0px; top: 0px; width: 462px;">
     			                  											<table><thead><tr><th style="width:10%;"></th>
     			                  											<th style="width:55%;" class="td_left" index="1" log="table"></th>
     			                  											<th style="width:35%;" class="undefined" index="3" log="table"></th></tr>
     			                  											</thead><tbody><tr param="1"><td>1</td><td class="td_left">
     			                  											<a href="http://shop1.taobao.com" target="_blank">魔方旗舰店</a></td><td class="">4,212</td></tr>
     			                  											<tr param="2"><td>2</td><td class="td_left">
     			                  											<a href="http://shop2.taobao.com" target="_blank">第一数码店</a></td><td class="">3,182</td></tr>
     			                  											<tr param="3"><td>3</td><td class="td_left">
     			                  											<a href="http://shop3.taobao.com" target="_blank">杭州摄像机专营店</a></td>
     			                  												<td class="">2,151</td></tr>
     			                  										<tr param="4"><td>4</td><td class="td_left"><a href="http://shop4.taobao.com" target="_blank">CDE床品期间</a></td><td class="">2,106</td></tr>
     			                  										<tr param="5"><td>5</td><td class="td_left"><a href="http://shop5.taobao.com" target="_blank">第一运动点</a></td>
     			                  										<td class="">2,091</td></tr>
     			                  										<tr param="6"><td>6</td><td class="td_left"><a href="http://shop6.taobao.com" target="_blank">杭州摄像机专营店</a>
     			                  										</td><td class="">2,072</td></tr></tbody>
     			                  										</table>
     			                  											</div>
     			                  										</div>
     			                  								</div> 
     			                  		 					</div>
     			                  		 			</div>
     			                  		 	</div>
     			                  		</div>   
     			                  	</div>     			                  		 
     			                  </div>
     			                  <div class="clearfix">
     			              		</div>
     			              </div> -->	
     			              
<!--      			              <div id="layout3_0" com="s" class="c-node live-layout">
     			                  <div class="layout-column-unit">
     			                 	 <div class="layout-column-padding0">   
     			                  		  <div id="live_table_cate_parent_id" class="layout-column-border">
     			                  		 		<div id="live_table_cate_0" class="comNode"><h4 class="live-title">官网销售额实时走向</h4>
     			                  		 			<div class="live-content">
     			                  		 				<div id="content4" class="liveTable" style="width:100%;height:250px;">
     			     			                  		  </div>
     			                  		  			</div>
     			                  		  		</div>
     			                  		  </div>  
     			                  	</div>
     			                 </div>
     			                 <div class="layout-column-unit">
     			                 	<div class="layout-column-padding1">   
     			                  		 <div id="live_table_shop_parent_id" class="layout-column-border">
     			                  		 		<div id="live_table_shop_0" class="comNode">
     			                  		 			<h4 class="live-title">今日店内卖的最火的50个宝贝</h4>
     			                  		 				<div class="live-content">
     			                  		 					<div id="div_live_table_shop_0" class="liveTable"> 
     			                  		 						 <div class="liveTableHead">  <table><thead><tr><th style="width:10%;">&nbsp;</th>
     			                  										 <th style="width:50%;" class="td_left breakword" index="1" log="table">宝贝</th>
     			                  										 <th style="width:10%;" class="undefined" index="3" log="table">价格</th>
     			                  										 <th style="width:15%;" class="undefined" index="4" log="table">今日笔数</th>
     			                  										 <th style="width:15%;" class="undefined" index="5" log="table">昨日笔数</th></tr></thead>
     			                  		 										</table>  
     			                  		 						</div>  
     			                  								<div class="liveTableBody" style="height: 270px;">
     			                  								  <table><thead><tr><th style="width:10%;"></th><th style="width:50%;" class="td_left breakword" index="1" log="table"></th>
     			                  								<th style="width:10%;" class="undefined" index="3" log="table"></th>
     			                  									<th style="width:15%;" class="undefined" index="4" log="table"></th>
     			                  								<th style="width:15%;" class="undefined" index="5" log="table">n</th></tr></thead><tbody>
     			                  								<tr param="0"><td>1</td><td class="td_left breakword">
     			                  								<div style="float:left;padding:10px 0px;"><a href="http://item.taobao.com/item.htm?id=0" target="_blank">    
     			                  								<img src="http://img03.taobaocdn.com/bao/uploaded/i3/T1j_qzXfVcXXbaEAo4_053553.jpg_sum.jpg" style="border:1px solid #D9D9D9" width="60px" height="60px">
     			                  							</a></div>
     			                  		    					<div style="text-align:left;margin:10px;margin-left:72px;"><a href="http://item.taobao.com/item.htm?id=0" target="_blank">3个包邮疯抢发热鼠标垫8.0折</a>
     			                  		    					</div>
     			                  		   						 </td><td class=""><span class="price">￥</span>8.8</td>
     			                  		    					<td class="">748</td><td class="">700</td></tr><tr param="0"><td>2</td><td class="td_left breakword">
     			                  		   						 <div style="float:left;padding:10px 0px;">
     			                  		   						 <a href="http://item.taobao.com/item.htm?id=0" target="_blank">   
     			                  		 							<img src="http://img01.taobaocdn.com/bao/uploaded/i1/T1dsyAXaVWXXa7HzQ8_101236.jpg_sum.jpg" style="border:1px solid #D9D9D9" width="60px" height="60px"></a>
     			                  		 						</div>    
     			                  							<div style="text-align:left;margin:10px;margin-left:72px;"><a href="http://item.taobao.com/item.htm?id=0" target="_blank">    包邮暖手宝，超级可爱</a>
     			                  							</div></td><td class="">
     			                  							<span class="price">￥</span>11.8</td><td class="">635</td><td class="">642</td></tr>
     			                  							<tr param="0"><td>3</td><td class="td_left breakword">
     			                  								<div style="float:left;padding:10px 0px;">
     			                  							<a href="http://item.taobao.com/item.htm?id=0" target="_blank"> 
     			                  		 					  <img src="http://img02.taobaocdn.com/bao/uploaded/i2/T1QLyGXgRfXXXkeTI8_072028.jpg_sum.jpg" style="border:1px solid #D9D9D9" width="60px" height="60px"></a>
     			                  		 					  </div>   
     			                  		  					  <div style="text-align:left;margin:10px;margin-left:72px;">
     			                  		  					 <a href="http://item.taobao.com/item.htm?id=0" target="_blank">    全民疯抢苹果手机套5折</a>
     			                  		   					</div></td><td class=""><span class="price">￥</span>32.06</td><td class="">620</td>
     			                  		  					 <td class="">618</td></tr></tbody></table> 
     			                  		  					  </div>
     			                  		  			  </div>
     			                  		  		</div>
     			                  		  </div>
     			                  	</div>   
     			                 </div>
     			            </div>
     			            <div class="clearfix"></div>
     			       </div> -->
     			       
     			   </div>    
     		 </div>
     	</div>    
   </div>
 </div>
</div>
<div id="foot" class="cb-f">Copyright 2014-<span class="copyrightEnd">2014</span>,版权所有data.360kad.com</div>

<script type="text/javascript" src="http://mofang.tbcdn.cn/js/loader.js?1414825415"></script>


