<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
     <head>
     <title>Pedido</title>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link rel="icon" href="/startup/images/favicon.ico">
     <link rel="shortcut icon" href="/startup/images/favicon.ico" />
     <link rel="stylesheet" href="/startup/css/style.css">
     <link rel="stylesheet" href="/startup/css/w3_v4.css">
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
     <script src="/startup/js/jquery.js"></script>
     <script src="/startup/js/jquery-migrate-1.1.1.js"></script>
     <script src="/startup/js/superfish.js"></script>
     <script src="/startup/js/jquery.easing.1.3.js"></script>
     <script src="/startup/js/sForm.js"></script>
     <script src="/startup/js/jquery-ui.js"></script>
   
     
	<script>
		$(document).ready(function(){ 
	    	var spinner = $( '.spinner' ).spinner({
	    			  min: 0
	    		});

			$( ".getvalue" ).on( "click", function() {
				var lista = "";
				spinner.each(function(index) {
					if (Number($( this ).spinner( "value" )) > 0) {
						lista = lista + adicionarItemQtdIngredientes(index, $( this ).spinner( "value" ), lista);
					}
		    	});
				$("a[href]").attr('href', '/startup/startupServlet?idItemIngredSelecionado=' + lista);
			});
		});

		function adicionarItemQtdIngredientes(indice, quantidade, lista) {
			var str = "";
			if (lista !== null && lista.length > 0) {
				str = "," + indice + "=" + quantidade;
			}
			else {
				str = indice + "=" + quantidade;
			}
			return str;
		}
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
    <h1><a href="/startup/index.jsp"><img src="/startup/images/logo.png" alt="EXTERIOR"></a> </h1>
    
         <div class="menu_block">
           <nav  class="" >
            <ul class="sf-menu">
                   <li class="current"><a href="/startup/index.jsp">Home</a></li>
                   <li><a href="/startup/jsp/cardapio.jsp">Card&aacute;pio</a></li>
                   <li><a href="/startup/jsp/contato.jsp">Contato</a></li>
                 </ul>
              </nav>
           <div class="clear"></div>
           </div>
           <div class="clear"></div>
      </div>
    </div>
    <jsp:useBean id="ingred" class="br.com.eas.startup.view.IngredientesMB" scope="session"/>
    <jsp:setProperty property="*" name="ingred"/>
    <jsp:useBean id="massa" class="br.com.eas.startup.view.CardapioMB" scope="session"/>
   	<jsp:setProperty property="*" name="massa"/>
</header>
<!--=======content================================-->

<div class="content"></div>
  <div class="container_12">
  	<div class="grid_6">
      	<h2>Pedido</h2>
      	<p class="inn1" style="margin-bottom: inherit;"><b>Lanche selecionado:</b></p>
      	<c:forEach var="item" items="${massa.cardapio}">
      		<c:if test="${item.id eq massa.idLancheSelecionado}">
				<p style="margin-bottom: inherit;">${item.nome}</p>
      			<p style="margin-bottom: inherit;">${item.descricao}</p>
      		</c:if>
      	</c:forEach>
    </div>
  
  	<div class="clear"></div>
		<br>
  	
    <div class="grid_6">
      	<h2>Adicionais</h2>
		<form action="/startup/startupServlet" method="post">
			<table class="w3-table w3-bordered ingredsAdicionais" style="width: 100%;">
				<th>Qtd.</th>
				<th>Ingrediente</th>
				<th>Vr. unit&aacute;rio</th>
				<c:set var="validarPedido" scope="session" value="ingredientesAdicionais"/>
				<c:forEach var="itemIngred" items="${ingred.itensIngredientes}">
						<tr>
							<td  style="vertical-align: middle; padding: 4px; width: 20%;">
								<input class="spinner" maxlength="2" value="0" size="1">
							</td>
							<td  style="vertical-align: middle; padding: 4px; width: 60%;">${itemIngred.ingrediente.nome}</td>
							<td  style="vertical-align: middle; padding: 4px; width: 20%;">
								<fmt:setLocale value="pt-BR" />
								<fmt:formatNumber type="currency" value="${itemIngred.ingrediente.valor}" />
							</td>
						</tr>
				</c:forEach>
				<jsp:setProperty name="ingred" property="idItemIngredSelecionado" param="idItemIngredSelecionado"/> 
			</table>
			<div class="clear"></div>
			<br>

			<div style="text-align: center;">
				<a data-type="submit" href="#" class="w3-button w3-green getvalue" style="cursor:pointer;">Continuar</a>
			</div>
		</form>
      <div class="clear"></div>
    </div>
    
    
    
    <div class="grid_5 prefix_1">
     
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
        		   <li class="current"><a href="/startup/index.jsp">Home</a></li>
                   <li><a href="/startup/jsp/cardapio.jsp">Card&aacute;pio</a></li>
                   <li><a href="/startup/jsp/contato.jsp">Contato</a></li>
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