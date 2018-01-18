<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
     <head>
     <title>Card&aacute;pio</title>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link rel="icon" href="../images/favicon.ico">
     <link rel="shortcut icon" href="../images/favicon.ico" />
     <link rel="stylesheet" href="../css/style.css">
     <script src="../js/jquery.js"></script>
     <script src="../js/jquery-migrate-1.1.1.js"></script>
     <script src="../js/superfish.js"></script>
     <script src="../js/jquery.easing.1.3.js"></script>
     <script src="../js/sForm.js"></script>
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
    <h1><a href="../index.jsp"><img src="../images/logo.png" alt="EXTERIOR"></a> </h1>
    
         <div class="menu_block">
           <nav  class="" >
            <ul class="sf-menu">
                   <li><a href="../index.jsp">Home</a></li>
                   <li class="current"><a href="cardapio.jsp">Card&aacute;pio</a></li>
                   <li><a href="contato.jsp">Contato</a></li>
                 </ul>
              </nav>
           <div class="clear"></div>
           </div>
           <div class="clear"></div>
      </div>
    </div>
    <jsp:useBean id="massa" class="br.com.eas.startup.view.CardapioMB" scope="page"/>
</header>
<!--=======content================================-->

<div class="content"><div class="ic"></div>
  <div class="container_12">
    <div class="grid_6">
      <h2>Nosso card&aacute;pio</h2>
      <p class="col2 inn1">Ingredientes selecionados</p>
      <p>Os lanches s&atilde;o feitos com ingredientes selecionados, fresquinhos e com muito capricho</p>
      
      <div class="menu">
      
      <c:forEach var="lanche" items="${massa.cardapio}">
      	<c:choose>
			<c:when test="${lanche.id % 2 == 0}">
				<div class="grid_3 omega">
					<img src=".${massa.imagensLanches[lanche.nome]}" alt="" class="img_inner">
				    <h3><a href="#">${lanche.nome}</a></h3>
		      	</div>
			</c:when>
			<c:otherwise>
				<div class="grid_3 alpha">
					<img src=".${massa.imagensLanches[lanche.nome]}" alt="" class="img_inner">
				    <h3><a href="#">${lanche.nome}</a></h3>
		      	</div>
			</c:otherwise>
		</c:choose>
       </c:forEach>
        
      </div>
      <div class="clear"></div>
    </div>
    <div class="grid_5 prefix_1">
      <h2 class="head2">Segredos do chefe</h2>
      <ul class="list l1">
      	<li><a href="https://comidasebebidas.uol.com.br/album/2015/02/13/faca-em-casa-um-hamburguer-igual-ao-do-holy-burger.htm?mode=list&foto=1"> Fa&ccedil;a em casa hamb&uacute;rguer artesanal </a></li>
        <li class="mb0"><a href="http://www.cozinhadovirgulino.com.br/2013/04/parada-obrigatoria-os-segredos-de-um.html"> Os segredos de um lanche profissional </a></li>
      </ul>
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
        		   <li><a href="../index.jsp">Home</a></li>
                   <li class="current"><a href="cardapio.jsp">Card&aacute;pio</a></li>
                   <li><a href="contato.jsp">Contato</a></li>
                 </ul></nav>
      </div>
      
      <div class="grid_6">
        <h3>Atualiza&ccedil;&otilde;es por e-mail</h3>
        <p class="col1">Junte-se &agrave; nossa lista de e-mail digital para receber novas <br> promo&ccedil;&otilde;es e seja o primeiro a saber sobre os eventos.</p>
        <form id="newsletter">
                  <div class="success">Sua inscri&ccedil;&atilde;o foi enviada com sucesso!</div>
                  <label class="email">
                       <input type="email" value="Informe o e-mail" >
                       <a href="#" class="btn" data-type="submit">enviar</a> 
                        <span class="error">*Este n&atilde;o &eacute; um endere&ccedil;o de e-mail v&aacute;lido.</span>
                  </label> 
              </form> 
      </div>
          
      </div>
      <div class="clear"></div>
    </div>
  </div>
</div>
<!--==============================footer=================================-->
<c:import url="/rodape.jsp"/>

</body>
</html>