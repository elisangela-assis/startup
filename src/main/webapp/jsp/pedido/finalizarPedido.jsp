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
			var strUrl = "/startup/startupServlet";
			
			var spinner = $( '.spinner' ).spinner({
	    		min: 1,
	    			  
	    		spin: function(event, ui) {
	    			var qtdAux = ui.value;
	    			var paramValor = "alterarQuantidade";
	    			var parametros = {validarPedido: paramValor, quantidade: qtdAux};
	    			
	    			alterarQuantidadeServlet(parametros);
	    		},
	    	
	    		change: function( event, ui ) {
	    			var qtdAux = $( '.spinner' ).spinner( "value" );
	    			var paramValor = "alterarQuantidade";
	    			var parametros = {validarPedido: paramValor, quantidade: qtdAux};
	    			
	    			alterarQuantidadeServlet(parametros);
	    		}
	    	});
			
			function alterarQuantidadeServlet(parametros) {
				$.ajax({
		            url: strUrl,
		            type: "post",
		            data: parametros,
		            error:function(){
		                alert("Ocorreu um erro inesperado!!");
		            },
		            success:function(data){
		            	if ($.trim(data)) {
		            		// substitui o valor total do pedido pelo valor retornado pelo servlet
		            		$(".totalPedido").html(data);
		            	}
		            }
		         });
			}
		
	    	$( ".salvarPedido" ).on( "click", function() {
	    		var paramValor = "salvarPedido";
				
	    		$.ajax({
		            url: strUrl,
		            type: "post",
		            data: {validarPedido: paramValor},
		            error:function(){
		                alert("Ocorreu um erro inesperado!!");
		            },
		            success:function(data){
		            	if ($.trim(data)) {
			                alert(data);
			                location.href = "/startup/index.jsp";
		            	}
		            }
		        });
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
    <jsp:useBean id="promo" class="br.com.eas.startup.view.PromocoesMB" scope="session"/>
    <jsp:useBean id="pedidoMB" class="br.com.eas.startup.view.PedidoMB" scope="session"/>
    <jsp:setProperty property="*" name="ingred"/>
    <jsp:setProperty property="*" name="promo"/>
    <jsp:setProperty property="*" name="pedidoMB"/>
    <jsp:setProperty name="pedidoMB" property="idLancheSelecionado" value="${massa.idLancheSelecionado}" />
    <jsp:setProperty name="pedidoMB" property="idsItemIngredSelecionados" value="${ingred.idItemIngredSelecionado}" />
    <jsp:setProperty name="pedidoMB" property="idsPromocoesSelecionadas" value="${promo.promocoesSelecionadas}" />
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
      			<p>
					<fmt:setLocale value="pt-BR" />
	 				<fmt:formatNumber type="currency" value="${item.valorTotal}"/>
				</p>
      		</c:if>
      	</c:forEach>
      	<br>
  	
  		<c:if test="${not empty ingred.itensIngredsSelecionados}">
	      	<h4>Adicionais</h4>
			<table class="w3-table w3-bordered ingredsAdicionais" style="width: 100%;">
				<th style="padding-left: 2px;">Qtd.</th>
				<th style="padding-left: 2px;">Ingrediente</th>
				<th style="padding-left: 2px;">Vr. unit&aacute;rio</th>
				<th style="padding-left: 2px;">Vr. total</th>	    
				<c:forEach var="itensIngred" items="${ingred.itensIngredsSelecionados}">
					<tr>
						<td  style="vertical-align: middle; padding: 4px; width: 10%;">${itensIngred.quantidade}</td>
						<td  style="vertical-align: middle; padding: 4px; width: 50%;">${itensIngred.ingrediente.nome}</td>
						<td  style="vertical-align: middle; padding: 4px; width: 20%;">
							<fmt:setLocale value="pt-BR" />
							<fmt:formatNumber type="currency" value="${itensIngred.valorUnitario}" />
						</td>
						<td  style="vertical-align: middle; padding: 4px; width: 20%;">
							<fmt:setLocale value="pt-BR" />
							<fmt:formatNumber type="currency" value="${itensIngred.valorTotal}" />
						</td>
					</tr>
				</c:forEach>
			<jsp:setProperty name="ingred" property="itensIngredsSelecionados" param="itensIngredsSelecionados" /> 
			</table>
			<div class="clear"></div>
			<br>
		</c:if>
    
    	<c:if test="${not empty promo.listPromosSelecionadas}">
	    	<h4>Promo&ccedil;&otilde;es</h4>
			<table class="w3-table w3-bordered" style="width: 100%;">
				<th style="padding-left: 2px;">Nome</th>
				<th style="padding-left: 2px;">Descri&ccedil;&atilde;o</th>
				<c:forEach var="promocao" items="${promo.listPromosSelecionadas}">
						<tr>
							<td  style="vertical-align: middle; padding: 4px; width: 30%;">${promocao.nome}</td>
							<td  style="vertical-align: middle; padding: 4px; width: 70%;">${promocao.descricao}</td>
						</tr>
				</c:forEach>
			</table>
			<div class="clear"></div>
		</c:if>
    </div>
    
    <div class="grid_5 prefix_1" style="padding-left: 60px; width:450px;">
     	<h2>Seu Carrinho</h2>
     	<form method="POST">
			<table class="w3-table w3-bordered" style="width: 100%;">
				<th style="padding-left: 2px;">Qtd.</th>
				<th style="padding-left: 2px;">Lanche</th>
				<th style="padding-left: 2px;">Vr. unit&aacute;rio</th>
				<th style="padding-left: 2px;">Vr. total</th>
				<c:forEach var="itemPedido" items="${pedidoMB.pedido.itens}">
						<tr>
							<td  style="vertical-align: middle; padding: 4px; width: 20%;">
								<input class="spinner" maxlength="2" value="1" size="1">
							</td>
							<td  style="vertical-align: middle; padding: 4px; width: 35%;">${itemPedido.lanche.nome}</td>
							<td  style="vertical-align: middle; padding: 4px; width: 23%;">
								<fmt:setLocale value="pt-BR" />
								<fmt:formatNumber type="currency" value="${itemPedido.valorUnitario}" />
							</td>
							<td  style="vertical-align: middle; padding: 4px; width: 22%;" class="totalPedido">
								<fmt:setLocale value="pt-BR" />
								<fmt:formatNumber type="currency" value="${itemPedido.valorTotalItem}" />
							</td>
						</tr>
				</c:forEach>
				<jsp:setProperty name="pedidoMB" property="pedido" param="pedido" /> 
			</table>
			<br>

			<div style="text-align: center;">
				<a data-type="submit" class="w3-button w3-green salvarPedido" style="cursor:pointer;">Salvar</a>
			</div>
		</form>
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