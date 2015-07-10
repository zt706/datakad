<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html><head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>数据魔方 </title>
  <meta name="description" content="数据魔方 - 淘宝官方数据产品 - 让市场分析更简单！">
  <meta name="keywords" content="数据魔方,魔方,cube,mofang,市场分析,淘词,数据分析,数据运营,行情,趋势,行业分析,品牌分析,产品分析,热卖宝贝,店铺排行,品牌排行,标题诊断">
  
  <link href="/css/jquery-ui-themes.css"l_view2 type="text/css" rel="stylesheet"/>
   
    <link href="/css/styles.css" type="text/css" rel="stylesheet"/>
   <script src="/js/jquery-1.8.2.min.js"></script>
    <script src="/js/hcharts/highcharts.js"></script>
     <script src="/js/datepick/WdatePicker.js"></script>
  <link rel="shortcut icon" href="http://mofang.tbcdn.cn/img/favicon.ico">
  <link type="text/css" rel="stylesheet" href="http://mofang.tbcdn.cn/css/common.css?1414825415" media="screen">
  
<link type="text/css" rel="stylesheet" href="http://mofang.tbcdn.cn/css/app/basic.css?1414825415" media="screen">
<link href="/css/layout.css" type="text/css" rel="stylesheet"/>
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
<body class="  colorTipContainer" onload="readly()">

<style type="text/css"> 
.dropdown-select {
    padding-left: 6px;
}
.dropdown-select {
    position: relative;
    width: 100%;
    margin: 0px;
    padding: 6px 8px 6px 10px;
    height: 28px;
    line-height: 14px;
    font-size: 12px;
    color: #62717A;
    text-shadow: 0px 1px #FFF;
    background: none repeat scroll 0% 0% transparent !important;
    border: 0px none;
    border-radius: 0px;
}

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
var type=1;
var data ;
var datetype ="1";
var platid="0";
var goodsid ="";
function getdata(plat,startdate,enddate,proid){

$.ajax({
             type: "GET",
             url: "/servlet/ProduceServer?plat="+plat+"&startdate="+startdate+"&enddate="+enddate+"&proid="+proid+"&type=1",
             dataType: "json",
             success: function(d){
             data =d.data;
             // alert(d);
              
              	//alert(data);
             sortdata(1);
                      }
         });
}

function getdatabyid(id){
			if(document.getElementById("d11").value==""){
				alert("请输入开始时间");
				return;
			}
			if(document.getElementById("d12").value==""){
				alert("请输入结束时间");
				return;
			}
			var startdate = document.getElementById("d11").value;
			var enddate = document.getElementById("d12").value;
			var plat = document.getElementById("plat").value;
	$.ajax({
             type: "GET",
             url: "/servlet/ProduceServer?proid="+id+"&startdate="+startdate+"&enddate="+enddate+"&type=2&plat="+plat,
             dataType: "json",
             success: function(d){
              setprice(d);
              createtable2(d.tabledata);
          // alert(d.tabledata);
           document.getElementById("goodsid").innerHTML=d.data[0].id;  
           document.getElementById("goodname").innerHTML=d.data[0].name; 
            document.getElementById("firsttime").innerHTML=d.data[0].firsttime;  
           document.getElementById("lasttime").innerHTML=d.data[0].lasttime; 
            document.getElementById("purnum").innerHTML=d.data[0].purnum;  
               document.getElementById("period").innerHTML=d.data[0].period+"天";  
           document.getElementById("cnt").innerHTML=d.data[0].cnt; 
            document.getElementById("amount").innerHTML="￥"+d.data[0].amount;  
              document.getElementById("totalamount").innerHTML="￥"+d.data[0].totalamount;  
           document.getElementById("totalcnt").innerHTML=d.data[0].totalcnt; 
            document.getElementById("shelfcount").innerHTML=d.data[0].shelfcount;  
             document.getElementById("unshelfcount").innerHTML=d.data[0].unshelfcount;  
           document.getElementById("stockcount").innerHTML=d.data[0].stockcount;   
             
                      }
         });
}

function getdatabyid2(id){
			if(document.getElementById("d11").value==""){
				alert("请输入开始时间");
				return;
			}
			if(document.getElementById("d12").value==""){
				alert("请输入结束时间");
				return;
			}
			var startdate = document.getElementById("d11").value;
			var enddate = document.getElementById("d12").value;
	if(goodsid==""){
	alert("请先选择商品");
	return;
		}
	$.ajax({
             type: "GET",
             url: "/servlet/ProduceServer?proid="+goodsid+"&type=3&plat="+platid+"&startdate="+startdate+"&enddate="+enddate,
             dataType: "json",
             success: function(d){
              //setprice(d);
              if(d==null){
              		alert("查无数据");
              		return;
              	}
              createtable3(d);
          
           document.getElementById("uv").innerHTML=d.uv;  
           document.getElementById("exituv").innerHTML=d.exituv; 
           if(d.chanexituv<0){
             document.getElementById("chanexituv").className="down"; 
               document.getElementById("chanexituv").innerHTML="环比下降 </br> "+d.chanexituv;
           	}else{
           	 document.getElementById("chanexituv").innerHTML="环比上涨 </br>"+d.chanexituv;
           	}        
           
            document.getElementById("landuv").innerHTML=d.landuv;  
            if(d.chanlanduv<0){
              document.getElementById("chanlanduv").className="down"; 
                document.getElementById("chanlanduv").innerHTML="环比下降 </br>"+d.chanlanduv;
            	}else{
            	  document.getElementById("chanlanduv").innerHTML="环比上涨 </br>"+d.chanlanduv;
            	}
              document.getElementById("new").innerHTML=d.new1;  
              if(d.channew<0){
              document.getElementById("channew").className="down"; 
                document.getElementById("channew").innerHTML="环比下降 </br>"+d.channew;
            	}else{
            	  document.getElementById("channew").innerHTML="环比上涨 </br>"+d.channew;
            	}
            	 document.getElementById("old").innerHTML=d.old;  
              if(d.channew<0){
              document.getElementById("chanold").className="down"; 
                document.getElementById("chanold").innerHTML="环比下降 </br>"+d.chanold;
            	}else{
            	  document.getElementById("chanold").innerHTML="环比上涨 </br>"+d.chanold;
            	}
              document.getElementById("jumpuv").innerHTML=d.jumpuv;
              if(d.chanjumpuv<0){
               document.getElementById("chanjumpuv").className="down"; 
                 document.getElementById("chanjumpuv").innerHTML="环比下降 </br>"+d.chanjumpuv;
              	}else{
              	  document.getElementById("chanjumpuv").innerHTML="环比上涨 </br>"+d.chanjumpuv;
              	}
              
           		document.getElementById("jumprate").innerHTML=d.jumprate+"%";
           		
           		
           if(d.chanjumprate<0){
           document.getElementById("chanjumprate").className="down";
            document.getElementById("chanjumprate").innerHTML="环比下降 </br>"+d.chanjumprate; 
           }else{
            document.getElementById("chanjumprate").innerHTML="环比上涨 </br>"+d.chanjumprate; 
           }
            
            document.getElementById("sucorder").innerHTML=d.sucorder;
            if(d.chansucorder<0){
             document.getElementById("chansucorder").className="down";
             document.getElementById("chansucorder").innerHTML="环比下降 </br>"+d.chansucorder;
            }else{
            document.getElementById("chansucorder").innerHTML="环比上涨 </br>"+d.chansucorder;
            }
                
            document.getElementById("clinordercount").innerHTML=d.clinordercount;
            if(d.clinordercount<0){
             document.getElementById("chanclinordercount").className="down";
             document.getElementById("chanclinordercount").innerHTML="环比下降 </br>"+d.chanclinordercount;
            }else{
            document.getElementById("chanclinordercount").innerHTML="环比上涨 </br>"+d.chanclinordercount;
            }
              
             document.getElementById("cart").innerHTML=d.cart;
             if(d.sucrate<0){
             document.getElementById("sucrate").className="down";
             document.getElementById("chancart").innerHTML="环比下降 </br>"+d.chancart;
             }else{
             document.getElementById("chancart").innerHTML="环比上涨 </br>"+d.chancart;
             }
               
              document.getElementById("sucrate").innerHTML=d.sucrate;
              if(d.chansucrate<0){
               document.getElementById("chansucrate").className="down"; 
                  document.getElementById("chansucrate").innerHTML="环比下降 </br>"+d.chansucrate;
              	}else{
              	   document.getElementById("chansucrate").innerHTML="环比上涨 </br>"+d.chansucrate;
              	}
             
               document.getElementById("staytime").innerHTML=d.staytime;
               if(d.chanstaytime<0){
                document.getElementById("chanstaytime").className="down"; 
                 document.getElementById("chanstaytime").innerHTML="环比下降 </br>"+d.chanstaytime;
               }else{
                document.getElementById("chanstaytime").innerHTML="环比上涨 </br>"+d.chanstaytime;
               }
                
                document.getElementById("tq").innerHTML=d.tq;
                if(d.chantq<0){
                 document.getElementById("chantq").className="down"; 
                  document.getElementById("chantq").innerHTML="环比下降 </br>"+d.chantq;
                }else{
                 document.getElementById("chantq").innerHTML="环比上涨 </br>"+d.chantq;
                }
                 
                 document.getElementById("call").innerHTML=d.call;
                 if(d.chancall<0){
                  document.getElementById("chancall").className="down"; 
                   document.getElementById("chancall").innerHTML="环比下降 </br>"+d.chancall;
                 }else{
                  document.getElementById("chancall").innerHTML="环比上涨 </br>"+d.chancall;
                 }
                  
                  document.getElementById("proid2").innerHTML=d.gid;
                  document.getElementById("proname2").innerHTML=d.gname;
            //document.getElementById("purnum").innerHTML=d.data[0].purnum;  
           
             Highcharts.setOptions({ 
   			 colors: ['#ff9e00', '#3c9dff', '#004B97', '#24CBE5', '#64E572', '#FF9655', 
			'#FFF263', '#6AF9C4'] 
			}); 
			// alert(d);
		
				$('#content1').highcharts({
     		   chart: {
         	   plotBackgroundColor: null,
           		 plotBorderWidth: null,
           		 plotShadow: false
       		 },
     	 	  title: {
           	 text: ''
      		  },
      		  tooltip: {
    	   		 pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
     		   },
       		 plotOptions: {
        	    pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#2894FF',
                    connectorColor: '#FFFFFF',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                	}
          		  }
      	 	 },
        		series: [{
            		type: 'pie',
           			 name: 'Browser share',
          		  data: d.channel1
       			 }]
   		 });	
			     
    $('#content4').highcharts({
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
            categories: d.date
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
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
            }, */
             shared: true
        },
        spline: {
       			 lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
         
        series: [ {
       		 visible:true,
            name: '流量',
            data: d.uvlist
      	  }],
      	  credits:{
      	  text:'',
      	  href:''
      	  }
   		 });
				   
    $('#content2').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '' 
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['新访客',   d.new1],
                ['老访客',    d.old],
               
            	]
       		 }]
   			 });
              }
         }); 
}


function getdatabyid3(id){
if(document.getElementById("d11").value==""){
				alert("请输入开始时间");
				return;
			}
			if(document.getElementById("d12").value==""){
				alert("请输入结束时间");
				return;
			}
			var startdate = document.getElementById("d11").value;
			var enddate = document.getElementById("d12").value;
if(goodsid==""){
	alert("请先选择商品");
	return;
		}
	$.ajax({
             type: "GET",
             url: "/servlet/ProduceServer?proid="+goodsid+"&type=4&startdate="+startdate+"&plat="+platid+"&enddate="+enddate+"&type=4",
             dataType: "json",
             success: function(d){
              //setprice(d);
              createtable4(d.tab);
         // alert(d);
           document.getElementById("salecount2").innerHTML=d.salecount2;  
           document.getElementById("sales2").innerHTML="￥"+d.sales2; 
            document.getElementById("profit2").innerHTML="￥"+d.profit2;  
              document.getElementById("ordercount").innerHTML=d.ordercount;
          // document.getElementById("uvrate2").innerHTML=d.uvrate2+"%"; 
            document.getElementById("return2").innerHTML=d.return2;  
            document.getElementById("returnrate2").innerHTML=d.returnrate2; 
            if(d.chansalecount2>0){
            document.getElementById("chansalecount2").innerHTML="环比上涨 </br>"+d.chansalecount2;  
           
            }else{
              document.getElementById("chansalecount2").className = "down";
            document.getElementById("chansalecount2").innerHTML="环比下降 </br>"+d.chansalecount2;  
            }
                 if(d.chansales2>0){
                   document.getElementById("chansales2").innerHTML="环比上涨 </br>"+"￥"+d.chansales2; 
                 }else{
                  document.getElementById("chansales2").className ="down";
                   document.getElementById("chansales2").innerHTML="环比下降 </br> "+"￥"+d.chansales2; 
                 }
         	if(d.chanprofit2>0){
         	            document.getElementById("chanprofit2").innerHTML="环比上涨 "+"￥"+d.chanprofit2;  
         	
         	}else{
         	            document.getElementById("chanprofit2").innerHTML="环比下降 "+"￥"+d.chanprofit2;  
         	              document.getElementById("chanprofit2").className="down";
         	}
         		if(d.chanordercount>0){
         		document.getElementById("chanordercount").innerHTML="环比上涨 </br>"+d.chanordercount;
         		}else{
         		document.getElementById("chanordercount").innerHTML="环比下降 </br>"+d.chanordercount;
         		document.getElementById("chanordercount").className = "down";
         		}
             /*  if(d.chanuvrate2>0){
               document.getElementById("chanuvrate2").innerHTML="环比上涨 "+d.chanuvrate2+"%"; 
              }else{
               document.getElementById("chanuvrate2").innerHTML="环比下降 "+d.chanuvrate2+"%"; 
               document.getElementById("chanuvrate2").className = "down";
              } */
          		if(d.chanreturn2>0){
          		document.getElementById("chanreturn2").innerHTML="环比上涨 </br>"+d.chanreturn2;  
          		}else{
          			document.getElementById("chanreturn2").innerHTML="环比下降 </br>"+d.chanreturn2;  
          			document.getElementById("chanreturn2").className = "down";
          		}
            if(d.chanreturnrate2>0){
            document.getElementById("chanreturnrate2").innerHTML="环比上涨 </br>"+d.chanreturnrate2; 
            }else{
            document.getElementById("chanreturnrate2").innerHTML="环比下降  </br>"+d.chanreturnrate2; 
            document.getElementById("chanreturnrate2").className = "down";
            }
            
            
              document.getElementById("cuscount").innerHTML=d.cuscount; 
                document.getElementById("onecount").innerHTML=d.onecount; 
              document.getElementById("twicecount").innerHTML=d.twicecount; 
               document.getElementById("recount").innerHTML=d.recount; 
               document.getElementById("recountup").innerHTML=d.recountup; 
              document.getElementById("recountrate").innerHTML=d.recountrate+"%"; 
            
              document.getElementById("newavr").innerHTML=d.newavr; 
                document.getElementById("newordercount").innerHTML=d.newordercount; 
                  document.getElementById("newcount").innerHTML=d.newcount; 
                    document.getElementById("newre").innerHTML=d.newre; 
                      document.getElementById("oldavr").innerHTML=d.oldavr; 
                     document.getElementById("oldordercount").innerHTML=d.oldordercount; 
                     document.getElementById("oldcount").innerHTML=d.oldcount; 
                     document.getElementById("oldre").innerHTML=d.oldre; 
                     
                     if(d.channewavr>0){
                      document.getElementById("channewavr").innerHTML="环比上涨  </br>￥"+d.channewavr; 
                     }else{
                      document.getElementById("channewavr").innerHTML="环比下降  </br>￥"+d.channewavr;
                       document.getElementById("channewavr").className = "down"; 
                     }
                       if(d.channewordercount>0){
                        document.getElementById("channewordercount").innerHTML="环比上涨  <br/>"+d.channewordercount; 
                       }else{
                        document.getElementById("channewordercount").innerHTML="环比下降  <br/>"+d.channewordercount; 
                        document.getElementById("channewordercount").className = "down";
                       }
               			if(d.channewcount>0){
               		 	 document.getElementById("channewcount").innerHTML="环比上涨 </br> "+d.channewcount; 
               			}else{
               		 	 document.getElementById("channewcount").innerHTML="环比下降  </br>"+d.channewcount;
               		   document.getElementById("channewcount").className = "down";
               			}
                		if(d.channewre>0){
                	         document.getElementById("channewre").innerHTML="环比上涨  </br>"+d.channewre;                 	
                		}else{
                	         document.getElementById("channewre").innerHTML="环比下降  </br>"+d.channewre; 
            				 document.getElementById("channewre").className = "down";
                		}
                		
                    if(d.chanoldavr>0){
                     document.getElementById("chanoldavr").innerHTML="环比上涨</br>  ￥ "+d.chanoldavr;
                    }else{
                     document.getElementById("chanoldavr").innerHTML="环比下降 </br> ￥"+d.chanoldavr;
                      document.getElementById("chanoldavr").className = "down";
                    }
                      if(d.chanoldordercount>0){
                        document.getElementById("chanoldordercount").innerHTML="环比上涨  </br>"+d.chanoldordercount; 
                      }else{
                        document.getElementById("chanoldordercount").innerHTML="环比下降  </br>"+d.chanoldordercount; 
                          document.getElementById("chanoldordercount").className="down";
                      }
                   if(d.chanoldcount>0){
                   document.getElementById("chanoldcount").innerHTML="环比上涨  </br>"+d.chanoldcount;
                   }else{
                   document.getElementById("chanoldcount").innerHTML="环比下降 </br> "+d.chanoldcount;
                   document.getElementById("chanoldcount").className = "down";
                   }
                      if(d.chanoldre>0){
                        document.getElementById("chanoldre").innerHTML="环比上涨  </br>"+d.chanoldre; 
                      }else{
                        document.getElementById("chanoldre").innerHTML="环比下降  </br>"+d.chanoldre; 
                          document.getElementById("chanoldre").className = "down";
                      }
                    document.getElementById("newprice").innerHTML = d.newprice;
                    document.getElementById("oldprice").innerHTML = d.oldprice;
             Highcharts.setOptions({ 
   			 colors: ['#ff9e00', '#3c9dff', '#004B97', '#24CBE5', '#64E572', '#FF9655', 
			'#FFF263', '#6AF9C4'] 
			}); 
			// alert(d);
		
				$('#content6').highcharts({
     		   chart: {
         	   plotBackgroundColor: null,
           		 plotBorderWidth: null,
           		 plotShadow: false
       		 },
     	 	  title: {
           	 text: '一级渠道分析'
      		  },
      		  tooltip: {
    	   		 pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
     		   },
       		 plotOptions: {
        	    pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#2894FF',
                    connectorColor: '#FFFFFF',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                	}
          		  }
      	 	 },
        		series: [{
            		type: 'pie',
           			 name: 'Browser share',
          		  data: d.channel1
       			 }]
   		 });	
   		 
   		 	$('#content8').highcharts({
     		   chart: {
         	   plotBackgroundColor: null,
           		 plotBorderWidth: null,
           		 plotShadow: false
       		 },
     	 	  title: {
           	 text: '订单来源分析'
      		  },
      		  tooltip: {
    	   		 pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
     		   },
       		 plotOptions: {
        	    pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#2894FF',
                    connectorColor: '#FFFFFF',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                	}
          		  }
      	 	 },
        		series: [{
            		type: 'pie',
           			 name: 'Browser share',
          		  data: d.source
       			 }]
   		 });	
			     
    $('#content7').highcharts({
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
            categories: d.date
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
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
            }, */
             shared: true
        },
        spline: {
       			 lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
         
        series: [ {
       		 visible:true,
            name: '销售数量',
            data: d.uvlist
      	  }],
      	  credits:{
      	  text:'',
      	  href:''
      	  }
   		 });
			
	  $('#content8').highcharts({
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
            categories: d.date
        },
        yAxis: {
        	
            title: {
                text: ''
            }
        },
        tooltip: {
            enabled: true,
           /*  formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
            }, */
             shared: true
        },
        spline: {
       			 lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
         
        series: [ {
       		 visible:true,
            name: '毛利',
            data: d.grosslist
      	  }],
      	  credits:{
      	  text:'',
      	  href:''
      	  }
   		 });
    
    $('#content5').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '地域销售分析' 
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: d.province
       		 }]
   			 });
              }
         }); 
         
         
}

function setprice(d){
 $('#content3').highcharts({
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
            categories: d.date
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
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
            }, */
             shared: true
        },
        spline: {
       			 lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
         
        series: [{
       		 visible:true,
            name: '采购价格',
            data: d.price
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
            categories: d.date
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
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
            }, */
             shared: true
        },
        spline: {
       			 lineWidth: 3,
                marker: {
                    radius: 2,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            },
         
        series: [{
        	visible:true,
            name: '采购数量',
            data: d.count
        }],
        credits:{
        text:'',
        href:''
        }
    });
	}

	function sortdata(type){

	if(type==1){
			data.sort(function(a,b){
   			 return b.salescount - a.salescount; 
			});
		}
	
	if(type==2){
			data.sort(function(a,b){
   			 return b.salestatol - a.salestatol; 
			});
		}
		
	if(type==3){
			data.sort(function(a,b){
   			 return b.profit - a.profit; 
			});
		}
		
	if(type==4){
			data.sort(function(a,b){
   			 return b.uv - a.uv; 
			});
		}
		
	if(type==5){
			data.sort(function(a,b){
   			 return b.returncount - a.returncount; 
			});
		}
	createtab(data,0);	
	//alert(Math.round(data.length/20));
	setPage(document.getElementById("paging"),Math.round(data.length/20),1);
	}
function createtab(data,num){	
	var d ="";
	var len ;  
	if(data.length>num*20+20){
		len = num*20+20;
	}else{
		len = data.length;
	}		
	
	for(i=num*20;i<len;i++){
	d=d+"<tr><td style=text-align:undefined >"+data[i].id+"</td><td style=text-align:center ><label><a href='#' onclick =cg("+data[i].id+") log=redirect >"+
	data[i].name+"</a></label></td><td style=text-align:right >"+data[i].salescount+"</td>"+
     "<td style=text-align:right>"+data[i].salestatol+"</td><td style=text-align:right>"+data[i].ave+"</td><td style=text-align:right>"+data[i].profit+"</td>"+
     "<td style=text-align:right>"+data[i].profitrate+"%</td><td style=text-align:right>"+data[i].node+"</td><td style=text-align:right>"+data[i].manager
     "</td></tr>";
		}
		
		/* d=d+"<tr><td style=text-align:undefined >"+data[i].id+"</td><td style=text-align:center ><label><a onclick =cg("+data[i].id+") log=redirect >"+
	data[i].name+"</a></label></td><td style=text-align:right >"+data[i].salescount+"</td>"+
     "<td style=text-align:right>"+data[i].salestatol+"</td><td style=text-align:right>"+data[i].pur+"</td><td style=text-align:right>"+data[i].profit+"</td>"+
     "<td style=text-align:right>"+data[i].profitrate+"</td><td style=text-align:right>"+data[i].node+"</td><td style=text-align:right>"+data[i].manager
     +"</td><td style=text-align:right>"+data[i].purmanager+"</td><td style=text-align:right>"+data[i].uv+"</td><td style=text-align:right>"+data[i].trans+
     "</td><td style=text-align:right>"+data[i].returncount+"</td><td style=text-align:right>"+data[i].returntatol+"</td><td style=text-align:right>"+data[i].returnrate+"</td></tr>";
		} */
	document.getElementById("tab").innerHTML=d;
}
 var f = 0;
  var ws = null;  
  var data = null;

function hidetab(n){
type = n;
	if(n==1){
	document.getElementById("l_view").style.display="block";
	document.getElementById("l_view2").style.display="none";
	document.getElementById("l_view3").style.display="none";
	document.getElementById("l_view4").style.display="none";
	document.getElementById("btn1").className = "report-tab current-tab";
	document.getElementById("btn2").className = "report-tab";
	document.getElementById("btn3").className = "report-tab";
	document.getElementById("btn4").className = "report-tab";
		document.getElementById("d1").style.display="";
	document.getElementById("l_tab").style.display = "none";
	document.getElementById("proid").value = "请输入商品id查询";
	}
	if(n==2){
	if(goodsid==""){
	alert("请先选择商品");
	return;
		}
	document.getElementById("l_view").style.display="none";
	document.getElementById("l_view2").style.display="block";
	document.getElementById("l_view3").style.display="none";
	document.getElementById("l_view4").style.display="none";
	document.getElementById("btn2").className = "report-tab current-tab";
	document.getElementById("btn1").className = "report-tab";
	document.getElementById("btn3").className = "report-tab";
	document.getElementById("btn4").className = "report-tab";
	document.getElementById("proid").value =goodsid;
	getdatabyid(goodsid);
	Highcharts.setOptions({ 
    colors: ['#ff9e00', '#3c9dff', '#004B97', '#24CBE5', '#64E572', '#FF9655', 
	'#FFF263', '#6AF9C4'] 
	}); 
	$('#content1').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '各平台UV访问量'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#2894FF',
                    connectorColor: '#FFFFFF',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['官网',   1100],
                ['IOS',      880],
                {
                    name: 'Chrome',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['Android',    488],
                ['WAP',     622],
                ['Others',   99]
            ]
        }]
    });
    
    $('#content2').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'UV来源分析'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['cpc',   900],
                ['cps',      980],
                {
                    name: 'Chrome',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['dicute',    488],
                ['WAP',     222],
                ['Others',   199]
            ]
        }]
    });
	}
	if(n==3){
	if(goodsid==""){
	alert("请先选择商品");
	return;
		}
	document.getElementById("l_view").style.display="none";
	document.getElementById("l_view2").style.display="none";
	document.getElementById("l_view3").style.display="block";
	document.getElementById("l_view4").style.display="none";
	document.getElementById("btn3").className = "report-tab current-tab";
	document.getElementById("btn2").className = "report-tab";
	document.getElementById("btn1").className = "report-tab";
	document.getElementById("btn4").className = "report-tab";
	getdatabyid2(1979);
	}
	
	if(n==4){
	if(goodsid==""){
	alert("请先选择商品");
	return;
		}
	document.getElementById("l_view").style.display="none";
	document.getElementById("l_view2").style.display="none";
	document.getElementById("l_view3").style.display="none";
	document.getElementById("l_view4").style.display="block";
	document.getElementById("btn4").className = "report-tab current-tab";
	document.getElementById("btn2").className = "report-tab";
	document.getElementById("btn3").className = "report-tab";
	document.getElementById("btn1").className = "report-tab";
	getdatabyid3(1979);
	}
	
}


function adv(){
document.getElementById("COM0_1").style.display="none";
	document.getElementById("COM0_2").style.display="block";
	Highcharts.setOptions({ 
    colors: ['#ff9e00', '#3c9dff', '#004B97', '#24CBE5', '#64E572', '#FF9655', 
	'#FFF263', '#6AF9C4'] 
	}); 
	$('#content1').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '产品咨询分析'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#2894FF',
                    connectorColor: '#FFFFFF',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['SWT',   1100],
                ['电话回拨',      1880],
                {
                    name: 'Chrome',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['需求登记',    1488],
                ['自主下单',     622],
                ['回访',   1199]
            ]
        }]
    });
    
    $('#content2').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'UV来源分析'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
         series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['SWT',   1100],
                ['电话回拨',      1880],
                {
                    name: 'Chrome',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['需求登记',    1488],
                ['自主下单',     622],
                ['回访',   1199]
            ]
        }]
    });
}

function sale(){
document.getElementById("COM0_1").style.display="none";
	document.getElementById("COM0_2").style.display="block";
	Highcharts.setOptions({ 
    colors: ['#ff9e00', '#3c9dff', '#004B97', '#24CBE5', '#64E572', '#FF9655', 
	'#FFF263', '#6AF9C4'] 
	}); 
	$('#content1').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '销售地域分析'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#2894FF',
                    connectorColor: '#FFFFFF',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['广东',   1100],
                ['浙江',      1880],
                {
                    name: 'Chrome',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['北京',    1488],
                ['云南',     622],
                ['福建',   1199]
            ]
        }]
    });
    
    $('#content2').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '平台销售分析'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
         series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['IOS',   1100],
                ['官网',      1280],
                {
                    name: 'Chrome',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['android',    1488],
                ['WAP',     1622],
                ['其它',   1099]
            ]
        }]
    });
}

function plat(n){		
		if(n==0){
		document.getElementById("plat0").className="bc-filter-checked";
		document.getElementById("plat12").className="";
		document.getElementById("plat13").className="";
		document.getElementById("plat3").className="";
			}
			
			if(n==12){
		document.getElementById("plat0").className="";
		document.getElementById("plat12").className="bc-filter-checked";
		document.getElementById("plat3").className="";
		document.getElementById("plat13").className="";
			}
			
			if(n==13){
		document.getElementById("plat0").className="";
			document.getElementById("plat3").className="";
		document.getElementById("plat12").className="";
		document.getElementById("plat13").className="bc-filter-checked";
			}	
			
			if(n==3){
		document.getElementById("plat0").className="";
		document.getElementById("plat13").className="";
		document.getElementById("plat12").className="";
		document.getElementById("plat3").className="bc-filter-checked";
			}		
		 bydate();
	}
	
function bydate(){
	//var datetype ;
	for(var i=0;i<document.getElementsByName("COM1_12_metric").length;i++){ 

	if(document.getElementsByName("COM1_12_metric")[i].checked==true){
		datetype = document.getElementsByName("COM1_12_metric")[i].value;
			} 
	} 
	//alert(document.getElementById("plat1").className=="bc-filter-checked");
	if(document.getElementById("plat0").className=="bc-filter-checked"){
	platid =0 ;
		getdata(0,datetype);	
	}
	
	if(document.getElementById("plat3").className=="bc-filter-checked"){
	platid =3 ;
		getdata(3,datetype);	
	}
	if(document.getElementById("plat12").className=="bc-filter-checked"){
		platid =12 ;
		getdata(12,datetype);	
	}
	if(document.getElementById("plat13").className=="bc-filter-checked"){
		platid =13 ;
		getdata(13,datetype);	
	}			
	}
	
	function createtable2(data2){
	var d="";
	for(i=0;i<data2.length;i++){
	d=d+"<tr><td style=text-align:undefined >"+data2[i].time+"</td><td style=text-align:center ><label><a onclick =hidetab() log=redirect >"+
	data2[i].store+"</a></label></td><td style=text-align:right >"+data2[i].price+"</td>"+
     "<td style=text-align:right>"+data2[i].tatolprice+"</td></tr>";
		}
	document.getElementById("tab2").innerHTML=d;
	}
	
	function createtable3(tabdata){
	var data2 = tabdata.tab;
	//alert(tabdata["cpc"]);
	var d="";
	var d3 = "<table align=center width=95% style=display:none><tr><th style=width:100px;><span ></span></th>"+
									
     	 							"<th style=width:120px;><span class=table-thead-label>二级渠道</span></th>"+
     	 							"<th style=width:120px;><span class=table-thead-label >UV</span></th>"+
     	 							"<th style=width:120px;><span class=table-thead-label >退出</span></th>"+    	 						
     	 							"<th style=width:120px;><span class=table-thead-label>着陆UV</span></th>"+
     	 							"<th style=width:120px;><span class=table-thead-label >跳出UV</span></th>"+
     	 							"<th style=width:240px;><span ></span></th>"+
     	 							"<th style=width:120px;><span class=table-thead-label >跳出率</span></th>" +  
     	 							"<th style=width:100px;><span></span></th>"+
     	 						"</tr>";
     
     	
	for(i=0;i<data2.length;i++){
	var chan2 = tabdata[data2[i].channel1];
	var d2=d3;
		 for(k=0;k<chan2.length;k++){
     	 	d2=d2+"<tr><td></td><td>"+chan2[k].channel2+"</td><td>"+chan2[k].uv+"</td><td>"+chan2[k].exituv+"</td><td>"+chan2[k].landuv
     	 	+"</td><td>"+chan2[k].jumpuv+"</td><td><span style=height:10px;float:right;margin-top:4px;margin-right:6px;background:#139FEC;width:"+chan2[k].jumprate
     	 	+"px;></span></td><td>"+chan2[k].jumprate+"%</td><td></td></tr>";
     	 }
     	  d2=d2+"</table>";
		d=d+"<tr><td style=text-align:undefined >"+data2[i].channel1+"</td><td style=text-align:center >"+data2[i].uv+
		"</td>"+"<td style=text-align:center>"+data2[i].exituv+"</td>"+"<td style=text-align:center>"+data2[i].landuv+"</td>"+
    	 "<td style=text-align:center>"+data2[i].jumpuv+"<td style=text-align:center><span style=height:10px;float:right;margin-top:4px;margin-right:6px;background:#139FEC;width:"+data2[i].jumprate+
    	 "px;></span></td><td style=text-align:right>"+data2[i].jumprate+"%</td><td style=text-align:center onclick = showchannel(event)>展开+</td></tr><tr ><td align=center colspan=9 >"+d2+"</td></tr>";
		}
	document.getElementById("tab3").innerHTML=d;
	}
	
	function createtable4(data2){
	var d="";
	for(i=0;i<data2.length;i++){
	d=d+"<tr><td style=text-align:undefined >"+data2[i].channel1+"</td><td style=text-align:center ><label><a onclick =hidetab() log=redirect >"+
	data2[i].channel2+"</a></label></td><td style=text-align:right >"+data2[i].uv+"</td><td style=text-align:right>"+data2[i].salestotal2+"</td><td style=text-align:right>"+data2[i].maoli+"</td><td style=text-align:right>"+data2[i].returntotal+"</td></tr>";
		}
	document.getElementById("tab4").innerHTML=d;
	}
	
	function cg(id){
	//document.getElementById("l_view").style.display="block";
	//document.getElementById("d1").style.display="none";
	document.getElementById("l_tab").style.display = "block"; 
	goodsid =id;
	hidetab(2);
	}
	
	function showchannel(e){
	var src = e.target || window.event.srcElement;
		if(src.parentNode.nextSibling.firstChild.firstChild.style.display == "none"){
		src.parentNode.nextSibling.firstChild.firstChild.style.display ="block";
		src.innerHTML="收起-";
		}else{
		src.parentNode.nextSibling.firstChild.firstChild.style.display = "none";
		src.innerHTML="展开+";
		}
		
	}
	
	function platchange(e){
	var src = e.target || window.event.srcElement;
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	
	var str;
	if(m<10){
		str = y+"-0"+m;
	}else{
		str =y+"-"+m;
	}
	if(d<10){
		str = str+"-0"+d;
	}else{
		str = str+"-"+d;
	}

	}
	
	function datechange(e){
	var startdate = new Date();
	var enddate = new Date;
	var src = e.target || window.event.srcElement;
	//alert(src.selectedIndex);
	if(src.selectedIndex==0){
		enddate.setDate(startdate.getDate());
		startdate.setDate(startdate.getDate()-1);
	}
	if(src.selectedIndex==1){
		//enddate.setDate(startdate.getDate()-1);
		startdate.setDate(startdate.getDate()-7);
	}
	if(src.selectedIndex==2){
		startdate.setMonth(startdate.getMonth()-1);
	}
	if(src.selectedIndex==3){
		startdate.setMonth(startdate.getMonth()-1);
		startdate.setDate(1);
		enddate.setDate(0);
	}
	var y = startdate.getFullYear();
	var m = startdate.getMonth()+1;
	var d = startdate.getDate();
	var y2 = enddate.getFullYear();
	var m2 = enddate.getMonth()+1;
	var d2 = enddate.getDate();
	var str;
	
	if(m<10){
		str = y+"-0"+m;
	}else{
		str =y+"-"+m;
	}
	if(d<10){
		str = str+"-0"+d;
	}else{
		str = str+"-"+d;
	}
	
	var end;
	if(m2<10){
		end = y2+"-0"+m2;
	}else{
		end =y2+"-"+m2;
	}
	if(d2<10){
		end = end+"-0"+d2;
	}else{
		end = end+"-"+d2;
	}
	//alert(str+"---"+end);
	//alert(document.getElementById("d11"));
	document.getElementById("d11").value = str;
	document.getElementById("d12").value = end;
	}
	
	function query(){
			
			var platid = document.getElementById("plat").value ;
			var startdate="" ;
			var enddate="";
			var proid="";
			if(document.getElementById("d11").value==""){
				alert("请输入开始时间");
				return;
			}
			if(document.getElementById("d12").value==""){
				alert("请输入结束时间");
				return;
			}
			proid = document.getElementById("proid").value;
			if(document.getElementById("proid").value=="请输入商品id查询"){
				proid = "";
			}else{
			goodsid = proid;
			}
			startdate = document.getElementById("d11").value;
			enddate = document.getElementById("d12").value;
			if(type==1){
			getdata(platid,startdate,enddate,proid);
				}else{
				hidetab(type);
				}
			
			
		}
		
	function readly(){
	var startdate = new Date();
	var enddate = new Date;

		enddate.setDate(startdate.getDate());
		startdate.setDate(startdate.getDate()-1);
	
	var y = startdate.getFullYear();
	var m = startdate.getMonth()+1;
	var d = startdate.getDate();
	var y2 = enddate.getFullYear();
	var m2 = enddate.getMonth()+1;
	var d2 = enddate.getDate();
	var str;
	
	if(m<10){
		str = y+"-0"+m;
	}else{
		str =y+"-"+m;
	}
	if(d<10){
		str = str+"-0"+d;
	}else{
		str = str+"-"+d;
	}
	
	var end;
	if(m2<10){
		end = y2+"-0"+m2;
	}else{
		end =y2+"-"+m2;
	}
	if(d2<10){
		end = end+"-0"+d2;
	}else{
		end = end+"-"+d2;
	}
	//alert(str+"---"+end);
	//alert(document.getElementById("d11"));
	document.getElementById("d11").value = str;
	document.getElementById("d12").value = end;
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

<span class="colorTip yellow_tip" id="span1" style="z-index:2;top: 101px; left: 380px;position: fixed;">
指标说明：
<br/>1) 时间：按照下单时间 
<br/>2) 四种状态：2待财务确认，3待发货，4待确认收货，5交易完成（默认）
<br/>3) 六种状态：0待审核，1待付款，2待财务确认，3待发货，4待确认收货，5交易完成
<br/>4) 销售额：（商品单价 - 商品优惠） * 商品销售数量 + 订单运费 - 订单优惠
<br/>5) 刷新频率：每2分钟刷新一次</span>
<div id="head" class="cb-h">
  <div class="inner l_1180">
    <div class="cb-h-left" style="margin-left: 0px;margin-top: 8px">
 	<img width=115px src="http://192.168.8.12:8080/img/logo2.gif" alt="网上药店">
      <a  style="line-height: 60px"> <strong style="float: right;font-size: 26px;font-weight: 700;color:#ffffff;" >&nbsp; 数据平台</strong>   </a>
           
      <!--<div class="cb-h-nav"><a href="" class="nav-select">基础报表</a></div>-->
    </div>
  <!--   <strong style="float: left;font-size: 28px;font-weight: 700;color:#ffffff;margin-top:20" > 数据平台</strong> -->
    <div id="owl" hoverlog="owl"></div>
    <div class="cb-h-right">
      <ul class="cb-h-list">
        
      </ul>
      <div id="headlist" class="headlist">
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

<div id="body"  class="cb-b l_1180">
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
     						<li class="has-children">    <a onclick="getdata(1,1)" href="#" rname="产品热销排行" class="children-show">产品分析</a>
     							<ul class="c-menu-children">
     								<li class="">    <a href="/servlet/Target?type=1" rel="prod_hot" rname="产品热销排行">单品排行分析</a></li>
     								<li param="tabs6" class="">    <a href="/servlet/Target?type=2" rname="整体情况">总体概况</a></li>
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
     						<ul><li param="tabs3"><br>    </li></ul>
     							</ul>
     			</div>    </div>    
     			<div class="main-r">      <div id="l_report" class="l-report">
     			
     		<div class="l-report-border">   
     		     <div id="l_navigation" class="l-navigation" style="display: block;">
     		     	<a href="javascript:void(0)" style="margin-left: 0px;">商品排行榜</a>
     		     	<div class="report-help">  <span class="report-help-icon"></span> 
     		     	 		<div class="report-help-content-triangle"></div> 
     		     	 </div> 
     		     	  <div class="report-help-content"> 
     		     	  	   <div class="report-help-content-border"> 
     		     	  	   		   <div class="report-help-tip">  
     		     	  	   		   			  <div class="report-help-info">  
     		     	  	   		   			  		  <div class="report-help-info-content"> <h4>报表简介：</h4>  <p id="report-help-intro"></p>   
     		     	  	   		   			  		   </div>  
     		     	  	   		   			   </div>   
     		     	  	   		   			    <div class="report-help-question">    
     		     	  	   		   			    		<div class="report-help-question-content" id="report-help-right">  
     		     	  	   		   			  		    </div>  
     		     	  	   		   			   </div>  
     		     	  	   		   </div>
     		     	 	 </div> 
     		     	 </div>
     		  </div>      
     		  
     		   <div class="l-control">
						<div id="l_ctrl" class="l-control-border" style="display: block;">
							<!-- <div id="CTRL0_4" com="l" class="combo combo_#cate_filter cateFilter"></div> -->
							<table width = 900px>
							<tr width = 100%><td bgcolor="white" id="d1" width = 10%>
						    <select id = "plat" onchange="platchange(event)" class="dropdown-select">  
     						 <option value ="0">平台 : 官网</option>  
     							 <option value ="13">平台 : 安卓</option>  
     							 <option value="3">平台 : 天猫</option>  
    						  <option value="12">平台 : WAP</option>  
 						   	</select>  
 						   		</td><td width = 2%></td><td bgcolor="white" width = 10%>
 						   		<select onchange="datechange(event)" class="dropdown-select">  
     						 <option value ="1">昨天</option>  
     							 <option value ="2">最近7天</option>  
     							 <option value="3">最近1个月</option>  
    						  <option value="4">上个月</option>
    						   <option value="5">自定义</option>    
 						   	</select>  </td><td  width = 2%></td>
 						   	<td bgcolor="white" width = 10%><input id="d11" style="border:0;width:120px;height:28px" type="text" onClick="WdatePicker()"/></td>
 						   	<td  width = 2%>&nbsp至</td><td bgcolor="white" width = 10%><input id="d12" style="border:0;width:120px;height:28px" type="text" onClick="WdatePicker()"/></td>
 						   	<td width = 2%>&nbsp;&nbsp; <br></td><td bgcolor="white" width = 10%><input style="border:0;width:120px;height:28px" value="请输入商品id查询"  onblur = "javascript:if(this.value=='')this.value='请输入商品id查询';" onfocus="javascript:if(this.value=='请输入商品id查询')this.value='';" id = "proid" type="text"></td>
 						   	<td  width = 10%>&nbsp;&nbsp;&nbsp;<a href="#" onclick ="query()"><img src=/img/003.png  style="width:20px;height:20px;"></a></td></tr>
 						   	</table>
												
						<!--  <div id="CTRL1_4" com="f" class="c-node timetab" onchange= bydate()>
							<div class="metrics">
							<label><input class="chart_radio" value="1" type="radio" name="COM1_12_metric" checked="checked">最近7天</label>
							<label><input class="chart_radio" value="2" type="radio" name="COM1_12_metric">最近30天</label>
							<label><input class="chart_radio" value="3" type="radio" name="COM1_12_metric">上个月</label></div>
						</div>  -->
								    <div id="CTRL2_4" com="o" class="c-node">
								    <div class="bc-filter-tip">BC数据从2013-09-01生成</div>
								    <table class="bc-filter"><tbody><tr><td class="bc-filter-checked" log="A" param="A" class="" id="plat0" onclick="plat(0)">官网</td>
								    <td log="C" param="C" id="plat13" onclick="plat(13)" class="">Android</td>
								      <td log="C" param="C" id="plat12" onclick="plat(12)" class="">WAP</td>
								    <td log="B" param="B" class="" id="plat3" onclick="plat(3)">天猫</td></tr></tbody></table>
								    </div><span class="cf"></span></div>
     		</div>       
     	 <div id="l_tab" class="l-tab" style="display: none;">
     	 		<div id="btn1" class="report-tab current-tab"  onclick="hidetab(1)"><a style="position:relative" index="0" class="" href="#" rel="kw_top" rname="行业关键词热搜TOP榜" log="tab">产品排行榜</a></div>
     	 		 <div id="btn2"  class="report-tab" onclick="hidetab(2)"><a style="position:relative" index="1" class="" href="#" rel="kw_rise" rname="行业关键词热搜飙升榜" log="tab">采购库存分析</a></div>
     	 		<div id="btn3" onclick="hidetab(3)" class="report-tab "><a style="position:relative" index="1" class="" href="#" rel="kw_rise" rname="行业关键词热搜飙升榜" log="tab">产品流量分析</a></div>
     	 		<div id="btn4" onclick="hidetab(4)" class="report-tab "><a style="position:relative" index="1" class="" href="#" rel="kw_rise" rname="行业关键词热搜飙升榜" log="tab">产品销售分析</a></div>
     	 		     	 		
     	 </div>        
     	 <div id="l_loading" class="loading" style="display: none;"></div>        
     	 <div id="l_view" class="l-view" style="display: block;">
     	 		<div class="c-title">          <h4 class="c-title-left">产品排行榜</h4>          </div>
     	 <div id="COM0_1" com="c" class="c-node">
     	 	<!--	<div class="table-search-border">
     	 		
     	 				 <div class="table-search-border"><div class="table-dump">    
     	 						<div class="table-dump-btn" id="table_dump_COM0_1"></div>   
     	 				 </div>
     	 				 <div class="table-search">    <input type="text" value="输入ID搜索" name="search" id="table_search_COM0_1">   
     	 				 	 <div class="table-search-btn"></div>   
     	 				  </div> 
     	 		</div>-->
     	 		<div id="COM0_1_table">
     	 				<table class="top-table">
     	 					<thead>
     	 						<tr ><th log="table" style="width: 5%;" class=""><span class="table-thead-label">ID</span></th>
     	 							<th log="table" skey="f0" stype="asc" class="table-thead-label" style="text-align: center; width: 15%;"><span class="table-thead-label">产品名称</span></th>
     	 							<th log="table" skey="f1" stype="desc" class="table-sort" style="text-align: right; width: 7%;"><span class="table-thead-label" onclick="sortdata(1)">订单量</span></th>
     	 							<th log="table" skey="f2" stype="desc" class="table-sort" style="text-align: right; width: 9%;"><span class="table-thead-label" onclick="sortdata(2)">销售额(￥)</span></th>
     	 							<th log="table" skey="f3" stype="desc"  style="text-align: right; width: 9%;"><span >平均客单价(￥)</span></th>
     	 							<th log="table" skey="f4" stype="desc" class="table-sort" style="text-align: right; width: 9%;"><span class="table-thead-label" onclick="sortdata(3)">毛利(￥)</span></th>
     	 							<th log="table" skey="f5"  style="text-align: right; width: 7%;"><span >毛利率</span></th>
     	 						    <th log="table" skey="f6"  style="text-align: right; width: 7%;"><span >科室</span></th>
     	 							<th log="table" skey="f7"  style="text-align: right; width: 8%;"><span >商品经理</span></th>
     	 						</tr>
     	 					</thead>
     	 					<tbody id = "tab">
     	 						<tr><td style="text-align:undefined" class="">1</td>
     	 							<td style="text-align:center" class=""><label>
     	 							<a log="redirect" href="/s/app/basic/#rid:kw_searchword|rname:全网搜索关键词查询|tabsId:tabs2|query:手机" target="_blank">0</a></label></td>
     	 							<td style="text-align:right" class="">0</td>
     	 							<td style="text-align:right" class="">0</td>
     	 							<td style="text-align:right" class="">0</td>
     	 							<td style="text-align:right" class="">0</td>
     	 							<td style="text-align:right">0</td>
     	 							<td style="text-align:right">0</td>
     	 							<td style="text-align:right">0</td>
     	 							<td style="text-align:right">0</td>
     	 							<td style="text-align:right">0</td>
     	 							<td style="text-align:right">0</td>
     	 						</tr>
     	 			
     	 					</tbody>
     	 				</table>
     	 			</div>
     	 			<div class="table-tip-nodata" style="display: none;">暂无数据</div>    
     	 			<div class="loading" style="display: none;"></div>
     	 			<div class="table-page-border">   
     	 				
     	 				  <div id="pager_COM0_1" class="pager">
     	 				  
     	 						<div id="paging" class="page-control">  
     	 						<!-- <span class="page-previous" style="display: none;">   <a href="javascript:void(0)">上一页</a> </span>
     	 												      <span class="page-button-content"><a href="javascript:void(0)" class="page-button page-cur-button">1</a></span>
     	 													  <span class="page-next" style="display: none;">  <a href="javascript:void(0)">下一页</a> </span>      共
     	 							     					<span class="page-total">1</span>页，到第      <input type="text" value="" class="page-jump-to">页     <input type="button" value="确定" class="page-jump-btn">  -->
     	 						 </div>
     	 					</div>   
     	 		 	</div>
     	 		</div>
     	 		
     	 	
     		</div>    
     		
     		<div id="l_view2" class="l-view" style="display: none;">
     			<div class="c-title">          <h4 class="c-title-left">入库信息       </h4>   </div>
     				<div id="COM0_2" com="c" class="c-node" >
     	 		
     	 		<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 		<tr><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">商品ID</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="goodsid">1</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">商品名称</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="goodname"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div ><span style="color:#479e0b">离上一次采购23天</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">第一次入库时间</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="firsttime"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">最后一次入库时间</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="lasttime"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div ><span style="color:red;">大于平均采购周期</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">采购次数</td></tr><tr><td colspan="2"><div class="primary_value" id="purnum"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span>d</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			</tr>
     	 			</tbody>
     	 	</table>
     	 			
     	 			<!-- <div id="content1" style="width:45%;height:360px;float:left"></div>
     	 			<div id="content2" style="width:45%;height:360px;float:right"></div> -->
     	 		</div>
     	 		
     	 		     			<div class="c-title">          <h4 class="c-title-left">采购信息       </h4>   </div>
     				<div id="COM0_3" com="c" class="c-node" >
     	 		
     	 		<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 		<tr><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">平均采购数量</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="cnt">1</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">平均采购金额</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="amount"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">合计采购数量 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="totalcnt"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">合计采购金额</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="totalamount"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">周期</td></tr><tr><td colspan="2"><div class="primary_value" id="period"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div ><span style="color:red;">段于商品平均采购周期</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			</tr>
     	 			</tbody>
     	 	</table>
     	 			
     	 			<!-- <div id="content1" style="width:45%;height:360px;float:left"></div>
     	 			<div id="content2" style="width:45%;height:360px;float:right"></div> -->
     	 		</div>
     	 		
     	 				     			<div class="c-title">          <h4 class="c-title-left">库存信息       </h4>   </div>
     				<div id="COM0_4" com="c" class="c-node" >
     	 		
     	 		<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 		<tr><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">架上库存</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="shelfcount">1</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div ><span style="color:red">库存不足</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">架下库存</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="unshelfcount">460,072,831</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div ><span style="color:#479e0b">库存充足</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">库存量 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="stockcount"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			
     	 			</tr>
     	 			</tbody>
     	 	</table>
     	 			
     	 			<!-- <div id="content1" style="width:45%;height:360px;float:left"></div>
     	 			<div id="content2" style="width:45%;height:360px;float:right"></div> -->
     	 		</div>
     	 			<div class="c-title">          <h4 class="c-title-left">历史采购价格趋势       </h4>   </div>
     	 		<div id="COM0_5" com="c" class="c-node" ><div id="content3"></div></div>
     	 		<div class="c-title">          <h4 class="c-title-left">历史采购数量趋势       </h4>   </div>
     	 			<div id="COM0_13" com="c" class="c-node" ><div id="content13"></div></div>
     	 		<div class="c-title">          <h4 class="c-title-left">历史采购明细       </h4>   </div>
     	 		<div id="COM0_6" com="c" class="c-node" >
     	 		<table class="top-table">
     	 					<thead>
     	 						<tr><th log="table" style="width: 25%;" class=""><span class="table-thead-label">采购日期</span></th>
     	 							<th log="table" skey="f0" stype="asc" class="table-thead-label" style="text-align: center; width: 25%;"><span class="table-thead-label">采购数量</span></th>
     	 							<th log="table" skey="f1" stype="desc" class="table-sort" style="text-align: right; width: 25%;"><span class="table-thead-label" onclick="sortdata(1)">采购价格</span></th>
     	 							<th log="table" skey="f2" stype="desc" class="table-sort" style="text-align: right; width: 25%;"><span class="table-thead-label" onclick="sortdata(2)">采购总价</span></th>
     	 							
     	 						</tr>
     	 					</thead>
     	 					<tbody id = "tab2"></tbody>
     	 		</table>
     	 		</div>
     		</div>
     		
     		<div id="l_view3" class="l-view" style="display: none;">
     		<div class="c-title">          <h4 class="c-title-left">流量概况       </h4>   </div>
     			<div id="COM0_7" com="c" class="c-node" >
     	 		
     	 			<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 			<tr><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">ID</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="proid2"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="up"><span> </span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">商品名称</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="proname2"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="up"><span> </span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">UV</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="uv">1</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanuv" class="up"><span> </span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">退出UV</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="exituv"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id = "chanexituv" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">着陆UV </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="landuv"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanlanduv" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">跳出UV </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="jumpuv"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanjumpuv" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">跳出率</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="jumprate"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanjumprate" class="up"><span>高于平均</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			</tr>
     	 			</tbody>
     	 		</table>
     	 		
     	 		<div class="c-title">          <h4 class="c-title-left">成单分析       </h4>   </div>
     	 					<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 			<tr><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">加入购物车次数</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="cart"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chancart" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">预订单数</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="clinordercount"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanclinordercount" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">成单数</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="sucorder"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chansucorder" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">转化率</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="sucrate"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chansucrate" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			</tr>
     	 			</tbody>
     	 		</table>
     	 		
     	 			<!-- <div  style="width:48%;height:360px;float:left;border:solid 1px #c8c8c8;margin:20px 0px 20px 0px">
     	 			<div class="c-title">          <h4 class="c-title-left">一级渠道分析   </h4>   </div>
     	 			<div id="content1"></div></div>
     	 			<div style="width:48%;height:340px;float:right;border:solid 1px #c8c8c8;margin:20px 0px 20px 0px">
     	 			<div class="c-title">          <h4 class="c-title-left">新老访客占比 </h4>   </div>
     	 			 <div id="content2"></div>  -->
     	 					<!-- <table class="top-table">
     	 					<thead>
     	 						<tr><th log="table" style="width: 10%;" class=""><span class="table-thead-label">一级渠道</span></th>
     	 							<th log="table" skey="f1" stype="desc" class="table-sort" style="text-align: right; width: 10%;"><span class="table-thead-label" onclick="sortdata(1)">UV</span></th>
     	 							<th log="table" skey="f2" stype="desc" class="table-sort" style="text-align: right; width: 10%;"><span class="table-thead-label" onclick="sortdata(2)">退出</span></th>    	 						
     	 							<th log="table" skey="f3" stype="asc" class="table-thead-label" style="text-align: center; width: 10%;"><span class="table-thead-label">着陆UV</span></th>
     	 							<th log="table" skey="f4" stype="desc" class="table-sort" style="text-align: right; width: 10%;"><span class="table-thead-label" onclick="sortdata(1)">跳出UV</span></th>
     	 							<th log="table" skey="f5"  style="text-align: right; width: 10%;"><span ></span></th>
     	 							<th log="table" skey="f6" stype="desc" class="table-sort" style="text-align: right; width: 10%;"><span class="table-thead-label" onclick="sortdata(2)">跳出率</span></th>   
     	 						</tr>
     	 					</thead>
     	 					<tbody id = "tab11"></tbody>
     	 					</table> -->
     	 			</div> 
     	 			<div class="c-title">          <h4 class="c-title-left">产品流量表现  </h4>   </div>
     	 					<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 			<tr>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">新访客</tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="new"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="channew" class="up"><span> </span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">老访客</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="old"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanold" class="up"><span> </span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">停留时间</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="staytime"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanstaytime" class="up"><span> </span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">在线咨询</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="tq"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chantq" class="up"><span> </span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">电话回拨</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="call"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chancall" class="up"><span> </span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			</tr>
     	 			</tbody>
     	 		</table>
     	 			
     	 	<div id="COM0_8" com="c" class="c-node" >
     	 	<div class="c-title">          <h4 class="c-title-left">流量趋势图     </h4>   </div>
     	 			<div id="content4"></div> 
     	 	</div>
     	 	
     	 		<div id="COM0_7" com="c" class="c-node" >
     	 		<div class="c-title">          <h4 class="c-title-left">渠道来源分析    </h4>   </div>
     	 		<table class="top-table">
     	 					<thead>
     	 						<tr><th log="table" style="width: 10%;" class=""><span class="table-thead-label">一级渠道</span></th>
     	 							<th log="table" skey="f1" stype="desc" class="table-thead-label" style="text-align: center; width: 10%;"><span class="table-thead-label" onclick="sortdata(1)">UV</span></th>
     	 							<th log="table" skey="f2" stype="desc" class="table-thead-label" style="text-align: center; width: 10%;"><span class="table-thead-label" onclick="sortdata(2)">退出</span></th>    	 						
     	 							<th log="table" skey="f3" stype="asc" class="table-thead-label" style="text-align: center; width: 10%;"><span class="table-thead-label">着陆UV</span></th>
     	 							<th log="table" skey="f4" stype="desc" class="table-thead-label" style="text-align: center; width: 10%;"><span class="table-thead-label" onclick="sortdata(1)">跳出UV</span></th>
     	 							<th log="table" skey="f5"  style="text-align: right; width: 10%;"><span ></span></th>
     	 							<th log="table" skey="f6" stype="desc" class="table-thead-label" style="text-align: right; width: 10%;"><span class="table-thead-label" onclick="sortdata(2)">跳出率</span></th>   
     	 							<th log="table" skey="f5"  style="text-align: center; width: 10%;"><span >操作</span></th>
     	 						</tr>
     	 					</thead>
     	 					<tbody id = "tab3"></tbody>
     	 		</table>
     	 		</div>
     	 		
     	 	</div>     	 		
     	 			
      		<div id="l_view4" class="l-view" style="display: none;">
     		
     		<div class="c-title">          <h4 class="c-title-left">销售概况       </h4>   </div>
     		<div id="COM0_11" com="c" class="c-node">
     		    	 			<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 			<tr><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">销量</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="salecount2">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chansalecount2" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">销售额</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="sales2">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chansales2" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">毛利 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="profit2">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanprofit2" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">销售单数 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="ordercount">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanordercount" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<!-- <td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">转化率</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="uvrate2">7.52%</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanuvrate2" class="up"><span>高于平均</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td> -->
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">退货数</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="return2">0%</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanreturn2" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">退货率</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="returnrate2">0%</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanreturnrate2" class="up"><span>高于平均</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			</tr>
     	 			</tbody>
     	 		</table>
     		</div>
     		<div class="c-title">          <h4 class="c-title-left">新会员销售概况       </h4>   </div>
     			<div id="COM0_9" com="c" class="c-node">
     	 		
     	 			<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 			<tr><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">会员价</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="newprice">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="channewprice" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">销售均价</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="newavr">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="channewavr" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">订单量 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="newordercount">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="channewordercount" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">销量 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="newcount">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="channewcount" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">毛利</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="newre">0%</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="channewre" class="up"><span>高于平均</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			
     	 			</tr>
     	 			</tbody>
     	 		</table>
					
     	 	</div>     	 		
     	 			
     	 			     		<div class="c-title">          <h4 class="c-title-left">老会员销售概况       </h4>   </div>
     			<div id="COM0_9" com="c" class="c-node">
     	 		
     	 			<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 			<tr><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">会员价</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="oldprice">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div  class="date_comparision"><div id="chanoldprice" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">销售均价</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="oldavr">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanoldavr" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">订单量 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="oldordercount">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanoldordercount" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">销量 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="oldcount">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanoldcount" class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">毛利</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="oldre">0%</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div id="chanoldre" class="up"><span>高于平均</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			
     	 			</tr>
     	 			</tbody>
     	 		</table>
					
     	 	</div>   
     	 	
     	 	     	 			     		<div class="c-title">          <h4 class="c-title-left">商品粘性分析       </h4>   </div>
     			<div id="COM0_9" com="c" class="c-node">
     	 		
     	 			<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 			<tr><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">总顾客数</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="cuscount">226</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">购买一次顾客数</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="onecount">71</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="up"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">购买二次顾客数 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="twicecount">39</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">购买三次顾客数 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="recount">17</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">购买三次以上顾客数 </td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="recountup">17</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">复购率</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="recountrate">7.52%</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span>高于平均</span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			
     	 			</tr>
     	 			</tbody>
     	 		</table>
					<!-- <div id="content5" style="width:45%;height:360px;float:left"></div>
     	 			<div id="content6" style="width:45%;height:360px;float:right"></div> 
     	 			<div id="content8" style="width:45%;height:360px;float:left"></div>  -->
     	 	</div>
     	 	
     	 	
     	 	<div id="COM0_10" com="c" class="c-node">
     	 	<div class="c-title">          <h4 class="c-title-left">销售趋势图     </h4>   </div>
					<div id="content7"></div>
     	 	</div>
     	 		<div class="c-title">          <h4 class="c-title-left">毛利趋势图     </h4>   </div>
					<div id="content8"></div>
					 	 		<div id="COM0_12" com="c" class="c-node">
     	 		<div class="c-title">          <h4 class="c-title-left">二级渠道分析    </h4>   </div>
     	 		<table class="top-table">
     	 					<thead>
     	 						<tr><th log="table" style="width: 15%;" class=""><span class="table-thead-label">一级渠道</span></th>
     	 							<th log="table" skey="f0" stype="asc" class="table-thead-label" style="text-align: center; width: 15%;"><span class="table-thead-label">二级渠道</span></th>
     	 							<th log="table" skey="f1" stype="desc" class="table-sort" style="text-align: right; width: 15%;"><span class="table-thead-label" onclick="sortdata(1)">销量</span></th>
     	 							<th log="table" skey="f2" stype="desc" class="table-sort" style="text-align: right; width: 15%;"><span class="table-thead-label" onclick="sortdata(2)">销售额</span></th>    	 						
     	 							<th log="table" skey="f3" stype="asc" class="table-thead-label" style="text-align: center; width: 15%;"><span class="table-thead-label">毛利</span></th>
     	 							<th log="table" skey="f4" stype="desc" class="table-sort" style="text-align: right; width: 15%;"><span class="table-thead-label" onclick="sortdata(1)">退货</span></th>
     	 						</tr>
     	 					</thead>
							<tbody id="tab4"></tbody>
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

<div id="foot" class="cb-f">Copyright 2014-<span class="copyrightEnd">2014</span>,版权所有data.360kad.com</div>

<script type="text/javascript" src="http://mofang.tbcdn.cn/js/loader.js?1414825415"></script>
<script type="text/javascript">
//container 容器，count 总页数 pageindex 当前页数
function setPage(container, count, pageindex) {
var container = container;
var count = count;
var pageindex = pageindex;
createtab(data,pageindex-1);	
var a = [];
  //总页数少于10 全部显示,大于10 显示前3 后3 中间3 其余....
  if (pageindex == 1) {
    a[a.length] = "<a href=\"#\" class=\"prev unclick\">上一页 </a>";
  } else {
    a[a.length] = "<a href=\"#\" class=\"prev\">上一页 </a>";
  }
  function setPageList() {
    if (pageindex == i) {
      a[a.length] = "<a href=\"#\" class=\"on\">" + i + "</a>";
    } else {
      a[a.length] = "<a href=\"#\" class=\"page-button page-cur-button\">" + i + "</a>";
    }
  }
  //总页数小于10
  if (count <= 10) {
    for (var i = 1; i <= count; i++) {
      setPageList();
    }
  }
  //总页数大于10页
  else {
    if (pageindex <= 4) {
      for (var i = 1; i <= 5; i++) {
        setPageList();
      }
      a[a.length] = "...<a href=\"#\" class=\"page-button page-cur-button\">" + count + "</a>";
    } else if (pageindex >= count - 3) {
      a[a.length] = "<a href=\"#\">1</a>...";
      for (var i = count - 4; i <= count; i++) {
        setPageList();
      }
    }
    else { //当前页在中间部分
      a[a.length] = "<a href=\"#\" class=\"page-button page-cur-button\">1</a>...";
      for (var i = pageindex - 2; i <= pageindex + 2; i++) {
        setPageList();
      }
      a[a.length] = "...<a href=\"#\" class=\"page-button page-cur-button\" >" + count + "</a>";
    }
  }
  if (pageindex == count) {
    a[a.length] = "<a href=\"#\" class=\"next unclick\"> 下一页</a>";
  } else {
    a[a.length] = "<a href=\"#\" class=\"next\"> 下一页</a>";
  }
  container.innerHTML = a.join("");
  //事件点击
  var pageClick = function() {
   
    var oAlink = container.getElementsByTagName("a");
    var inx = pageindex; //初始的页码
    oAlink[0].onclick = function() { //点击上一页   
      if (inx == 1) {
        return false;
      }
      inx--;
      setPage(container, count, inx);
      return false;
    }
    for (var i = 1; i < oAlink.length - 1; i++) { //点击页码
      oAlink[i].onclick = function() {
        inx = parseInt(this.innerHTML);
        setPage(container, count, inx);
        return false;
      }
    }
    oAlink[oAlink.length - 1].onclick = function() { //点击下一页
      if (inx == count) {
        return false;
      }
      inx++;
      setPage(container, count, inx);
      return false;
    }
  } ()
}
setPage(document.getElementById("paging"),13,5);
</script>


