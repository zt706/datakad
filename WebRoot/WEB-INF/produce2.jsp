<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<body class="  colorTipContainer"  onload="readly()">

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
             url: "/servlet/ProduceServer2?plat="+plat+"&startdate="+startdate+"&enddate="+enddate+"&type=1",
             dataType: "json",
             success: function(d){
             data =d;
             var d2="";
           
             for(i=0;i<d.act.length;i++){
             
             d2=d2+"<tr><td style=text-align:center >"+d.act[i].pid+"</td><td style=text-align:center >"+d.act[i].pname+"</td><td style=text-align:center >"+
             d.act[i].ordercount+"</td><td style=text-align:center >"+d.act[i].orderrate+"</td><td style=text-align:center >"+d.act[i].salecount+"</td><td style=text-align:center >"+
             d.act[i].saletatol+"</td></tr>";
              document.getElementById("tab").innerHTML = d2;
             }
              	
             //sortdata(1);
               document.getElementById("ordercount").innerHTML=d.ordercount;  
           document.getElementById("salestatol").innerHTML=d.salestatol; 
            document.getElementById("profit").innerHTML=d.profit;  
           document.getElementById("profitrate").innerHTML=d.profitrate; 
            //document.getElementById("ordercount").innerHTML=d.data[0].purnum;  
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
	$.ajax({
             type: "GET",
             url: "servlet/ProduceServer?proid="+id+"&startdate="+startdate+"&enddate="+enddate+"&type=2",
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
             url: "servlet/ProduceServer?proid="+goodsid+"&type=4&startdate="+startdate+"&plat="+platid+"&enddate="+enddate+"&type=4",
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
     "<td style=text-align:right>￥"+data[i].salestatol+"</td><td style=text-align:right>￥"+data[i].pur+"</td><td style=text-align:right>￥"+data[i].profit+"</td>"+
     "<td style=text-align:right>"+data[i].profitrate+"%</td><td style=text-align:right>"+data[i].node+"</td><td style=text-align:right>"+data[i].manager
     +"</td><td style=text-align:right>"+data[i].purmanager+"</td><td style=text-align:right>"+data[i].intro+"</td><td style=text-align:right>"+data[i].ave+"</td></tr>";
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
	
  
}

function sale(){
document.getElementById("COM0_1").style.display="none";
	document.getElementById("COM0_2").style.display="block";
  
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
	document.getElementById("d1").style.display="none";
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
 						   	<td width = 2%></td><td bgcolor="white" width = 10%><input style="border:0;width:120px;height:28px" value="请输入商品id查询" onblur = "javascript:if(this.value=='')this.value='请输入商品id查询';" onfocus="javascript:if(this.value=='请输入商品id查询')this.value='';" id = "proid" type="text"></td>
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
     	 		<div class="c-title">          <h4 class="c-title-left">总体销售概况</h4>          </div>
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
     	 				<!-- <table class="top-table">
     	 					<thead>
     	 						<tr ><th log="table" style="width: 5%;" class=""><span class="table-thead-label">ID</span></th>
     	 							<th log="table" skey="f0" stype="asc" class="table-thead-label" style="text-align: center; width: 15%;"><span class="table-thead-label">产品名称</span></th>
     	 							<th log="table" skey="f1" stype="desc" class="table-sort" style="text-align: right; width: 7%;"><span class="table-thead-label" onclick="sortdata(1)">订单量</span></th>
     	 							<th log="table" skey="f2" stype="desc" class="table-sort" style="text-align: right; width: 9%;"><span class="table-thead-label" onclick="sortdata(2)">销售额</span></th>
     	 							<th log="table" skey="f3" stype="desc"  style="text-align: right; width: 9%;"><span >采购成本</span></th>
     	 							<th log="table" skey="f4" stype="desc" class="table-sort" style="text-align: right; width: 9%;"><span class="table-thead-label" onclick="sortdata(3)">毛利</span></th>
     	 							<th log="table" skey="f5"  style="text-align: right; width: 7%;"><span >毛利率</span></th>
     	 						    <th log="table" skey="f6"  style="text-align: right; width: 7%;"><span >科室</span></th>
     	 							<th log="table" skey="f7"  style="text-align: right; width: 8%;"><span >商品经理</span></th>
     	 							<th log="table" skey="f8"  style="text-align: right; width: 8%;"><span >采购经理</span></th>
      	 							<th log="table" skey="f9"  style="text-align: right; width: 7%;"><span  onclick="sortdata(4)">引进人</span></th>
      	 							<th log="table" skey="f11" style="text-align: right; width: 7%;"><span  onclick="sortdata(5)">平均销售价</span></th>     	 							
     	 						</tr>
     	 					</thead>
     	 					<tbody id = "tab">
     	 						<tr><td style="text-align:undefined" class="">1</td>
     	 							<td style="text-align:center" class=""><label>
     	 							<a log="redirect" href="/s/app/basic/#rid:kw_searchword|rname:全网搜索关键词查询|tabsId:tabs2|query:手机" target="_blank">手机</a></label></td>
     	 							<td style="text-align:right" class="">615,258</td>
     	 							<td style="text-align:right" class="">1,601,102</td>
     	 							<td style="text-align:right" class="">423,073</td>
     	 							<td style="text-align:right" class="">11.73%</td>
     	 							<td style="text-align:right">25.72%</td>
     	 							<td style="text-align:right">2,190</td>
     	 							<td style="text-align:right">0.12%</td>
     	 							<td style="text-align:right">0.92</td>
     	 						</tr>
     	 			
     	 					</tbody>
     	 				</table> -->
     	 				 		<table class="board" width="100%" cellspacing="0" cellpadding="0" style="display: table;"><tbody>
     	 		<tr><td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">科室</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="nodename">0</div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">订单量</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="ordercount"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div ><span style="color:#479e0b"></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">销售额</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="salestatol"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">客单金额</td></tr>
     	 					<tr><td colspan="2"><div class="primary_value" id="lasttime"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div ><span style="color:red;"></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">毛利额</td></tr><tr><td colspan="2"><div class="primary_value" id="profit"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
     	 			<td>
     	 				<table class="board_inner" border="0" width="100%" cellspacing="0" cellpadding="0"><tbody>
     	 					<tr><td class="label">毛利率</td></tr><tr><td colspan="2"><div class="primary_value" id="profitrate"></div></td></tr>
     	 					<tr style="height:20px;"><td colspan="2"><div class="date_comparision"><div class="down"><span></span></div></div></td></tr></tbody>
     	 				</table>
     	 			</td>
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
     	 	<div class="c-title">          <h4 class="c-title-left">活动效果分析</h4>          </div>	
     	 	 <div id="COM0_1" com="c" class="c-node">
     	 	 <table class="top-table">
     	 					<thead>
     	 						<tr ><th log="table" style="width: 10%;" class=""><span class="table-thead-label">ID</span></th>
     	 							<th log="table" skey="f0" stype="asc" class="table-thead-label" style="text-align: center; width: 25%;"><span class="table-thead-label">活动名称</span></th>
     	 							<th log="table" skey="f1" stype="desc" class="table-sort" style="text-align: right; width: 15%;"><span class="table-thead-label" onclick="sortdata(1)">活动成单量</span></th>
     	 							<th log="table" skey="f2" stype="desc" class="table-sort" style="text-align: right; width: 15%;"><span class="table-thead-label" onclick="sortdata(2)">活动成单率</span></th>
     	 							<th log="table" skey="f3" stype="desc"  style="text-align: right; width: 15%;"><span >活动销售量</span></th>
     	 							<th log="table" skey="f4" stype="desc" class="table-sort" style="text-align: right; width: 15%;"><span class="table-thead-label" onclick="sortdata(3)">活动销售额</span></th>
     	 							   	 							
     	 						</tr>
     	 					</thead>
     	 					<tbody id = "tab">
     	 						<tr><td style="text-align:undefined" class="">1</td>
     	 							<td style="text-align:center" class=""><label>
     	 							<a log="redirect" href="/s/app/basic/#rid:kw_searchword|rname:全网搜索关键词查询|tabsId:tabs2|query:手机" target="_blank">手机</a></label></td>
     	 							<td style="text-align:right" class="">0</td>
     	 							<td style="text-align:right" class="">0</td>
     	 							<td style="text-align:right" class="">0</td>
     	 							<td style="text-align:right" class="">0%</td>
     	 							
     	 							
     	 						</tr>
     	 			
     	 					</tbody>
     	 				</table> 
     	 	 </div>
     		</div>    
     		
     		</div>	 
     	 	
     	</div>   
     		
    	 </div>   
   		</div>
 	 </div>    


<div id="foot" class="cb-f">Copyright 2014-<span class="copyrightEnd">2014</span>,版权所有data.360kad.com</div>
<script type="text/javascript" src="http://mofang.tbcdn.cn/js/loader.js?1414825415"></script>