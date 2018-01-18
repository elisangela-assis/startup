<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
     <head>
     <title>Startup Lanchonete</title>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link rel="icon" href="images/favicon.ico">
     <link rel="shortcut icon" href="images/favicon.ico" />
     <link rel="stylesheet" href="css/style.css">
     <link rel="stylesheet" href="css/slider.css">
     <script src="js/jquery.js"></script>
     <script src="js/jquery-migrate-1.1.1.js"></script>
     <script src="js/jquery-ui.min.js"></script>
     <script src="js/jquery.min.js"></script>
     <script src="js/superfish.js"></script>
     <script src="js/jquery.easing.1.3.js"></script>
     <script src="js/sForm.js"></script>
     <script src="js/jquery.carouFredSel-6.1.0-packed.js"></script>
     <script src="js/tms-0.4.1.js"></script>
     
     <script>
      $(window).load(function(){
      $('.slider')._TMS({
              show:0,
              pauseOnHover:false,
              prevBu:'.prev',
              nextBu:'.next',
              playBu:false,
              duration:800,
              preset:'fade', 
              pagination:true,//'.pagination',true,'<ul></ul>'
              pagNums:false,
              slideshow:8000,
              numStatus:false,
              banners:false,
          waitBannerAnimation:false,
        progressBar:false
      })  
      });
      
     $(window).load (
    function(){$('.carousel1').carouFredSel({auto: false,prev: '.prev',next: '.next', width: 960, items: {
      visible : {min: 1,
       max: 4
},
height: 'auto',
 width: 240,

    }, responsive: false, 
    
    scroll: 1, 
    
    mousewheel: false,
    
    swipe: {onMouse: false, onTouch: false}});
    
    
    });     
     
     
     $(document).ready(function() {	

     	$('a[name=modal]').click(function(e) {
     		e.preventDefault();
     		
     		var id = $(this).attr('href');
     	
     		var maskHeight = $(document).height();
     		var maskWidth = $(window).width();
     	
     		$('#mask').css({'width':maskWidth,'height':maskHeight});

     		$('#mask').fadeIn(1000);	
     		$('#mask').fadeTo("slow",0.8);	
     	
     		//Get the window height and width
     		var winH = $(window).height();
     		var winW = $(window).width();
                   
     		$(id).css('top',  winH/2-$(id).height()/2);
     		$(id).css('left', winW/2-$(id).width()/2);
     	
     		$(id).fadeIn(2000); 
     	
     	});
     	
     	$('.window .close').click(function (e) {
     		e.preventDefault();
     		
     		$('#mask').hide();
     		$('.window').hide();
     	});		
     	
     	$('#mask').click(function () {
     		$(this).hide();
     		$('.window').hide();
     	});			
     	
     });
     
     </script>
     
     <!--[if lt IE 8]>
       <div style=' clear: both; text-align:center; position: relative;'>
         <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
           <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
         </a>
      </div>
    <![endif]-->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <link rel="stylesheet" media="screen" href="css/ie.css">

    <![endif]-->
     </head>
     
<body>
<div class="main">
<!--==============================header=================================-->
 <header> 
  <div class="container_12">
    <div class="grid_12">
    <h1><a href="index.jsp"><img src="images/logo.png" alt="EXTERIOR"></a> </h1>
    
         <div class="menu_block">
           <nav  class="" >
            <ul class="sf-menu">
                   <li class="current"><a href="index.jsp">Home</a></li>
                   <li><a href="jsp/cardapio.jsp">Card&aacute;pio</a></li>
                   <li><a href="jsp/contato.jsp">Contato</a></li>
                 </ul>
              </nav>
           <div class="clear"></div>
           </div>
           <div class="clear"></div>
      </div>
    </div>
    <jsp:useBean id="massa" class="br.com.eas.startup.view.CardapioMB" scope="session" >
    	<jsp:setProperty property="*" name="massa"/>
    </jsp:useBean>
</header>
 <div class="slider-relative">
    <div class="slider-block">
      <div class="slider">
        <ul class="items">
          <li><img src="images/slide.jpg" alt=""></li>
          <li><img src="images/slide1.jpg" alt=""></li>
          <li class="mb0"><img src="images/slide2.jpg" alt=""></li>
        </ul>
      </div>
    </div>
 </div>
<!--=======content================================-->

<div class="content page1"><div class="ic"></div>
  <div class="container_12">
      <div class="clear"></div>
      <div class="grid_12">
        <div class="car_wrap">
        <h2>Card&aacute;pio</h2>
        <a href="#" class="prev"></a><a href="#" class="next"></a>
        <form action="jsp/pedido/pedidoAdicionais.jsp" method="GET">
	        <ul class="carousel1">
				<jsp:setProperty name="massa" property="idLancheSelecionado" param="idLancheSelecionado" /> 
		        <c:forEach var="lanche" items="${massa.cardapio}">
		        	<li>
		        	<div>
			        	<img src="${massa.imagensLanches[lanche.nome]}" alt="">
			            <div class="col1 upp">
			            	<a href="jsp/pedido/pedidoAdicionais.jsp?idLancheSelecionado=${lanche.id}">${lanche.nome}</a>
			            </div>
			            <span style="display: inline-block; height: 28px;">${lanche.descricao}"</span>
			            <div class="price">
			            <fmt:setLocale value="pt-BR" /> 
			            <fmt:formatNumber type="currency" value="${lanche.valorTotal}"/>
			            </div>
		            </div>
		            </li>    
		        </c:forEach>
	        </ul>
        </form>
      </div>
      
    </div>
    <div class="clear"></div>
    <div class="bottom_block">
      <div class="grid_6">
        <h3>Siga-nos</h3>
        <div class="socials">
          <a href="#"></a>
          <a href="#"></a>
          <a href="#"></a>
        </div>
        <nav><ul>
                   <li class="current"><a href="index.jsp">Home</a></li>
                   <li><a href="jsp/cardapio.jsp">Card&aacute;pio</a></li>
                   <li><a href="jsp/contato.jsp">Contato</a></li>
                 </ul></nav>
      </div>
      
      </div>
      <div class="clear"></div>
    </div>
  </div>
</div>

<!--==============================footer=================================-->

<c:import url="rodape.jsp"/>

</body>
</html>