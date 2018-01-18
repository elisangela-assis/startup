<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
     
     <script type="text/javascript">

	   $(document).ready(function() {
	        $(".getPromocoes").click(function(){
	            var lista = [];
	            $.each($("input[name='promocoesRadio']:checked"), function(){            
	            	lista.push($(this).val());
	            });
	            $("a[href]").attr('href', '/startup/startupServlet?validarPedido=validarPromocaoSubmit&promocoesSelecionadas=' + lista.join(","));
	        });
	   });

		$.ajaxSetup ({
			cache: false
			});
 
	   	$(document).ready(function() {
		   $("input[name='promocoesRadio']").click(function() {
				if ($(this).is(':checked')) {
					var paramValor = "promocao";
					var strUrl = "/startup/startupServlet";
					var inputChecked = $(this);
					
					$.ajax({
			            url: strUrl,
			            type: "post",
			            data: {validarPedido: paramValor, promocoesSelecionadas: $(this).val()},
			            error:function(){
			                alert("Ocorreu um erro inesperado!!");
			                return false;
			            },
			            success:function(data){
			            	if ($.trim(data)) {
				             	// desmarca o checkbox porque um erro ocorreu e também exibe a mensagem na tela
				             	var valorSelec = inputChecked.val();
				             	$("input[value='" + valorSelec + "']" ). prop('checked', false);
				                alert(data);
				                return false;
			            	}
			            }
			         });

				}
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
    <jsp:useBean id="promo" class="br.com.eas.startup.view.PromocoesMB" scope="session"/>
	<jsp:setProperty property="*" name="promo"/>
	<jsp:useBean id="ingred" class="br.com.eas.startup.view.IngredientesMB" scope="session"/>
    <jsp:setProperty property="*" name="ingred"/>
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
      	
      	<br>
      	<c:if test="${not empty ingred.idItemIngredSelecionado}">
	      	<p style="margin-bottom: inherit;"><b>Ingredientes adicionais:</b></p>
	      	<c:set var="itensIngred" value="${ingred.itensIngredientes}" />
	      	<c:forTokens items="${ingred.idItemIngredSelecionado}" delims="," var="item">
		    	<c:set var="itemsQtds" value="${fn:split(item, '=')}" />
				<p style="margin-bottom: inherit;">${itemsQtds[1]} - ${itensIngred[itemsQtds[0]].ingrediente.nome}</p>
		    </c:forTokens>
	    </c:if>
	    
	  	<br>
  	
      	<h2>Promo&ccedil;&otilde;es</h2>
      	<p style="margin-bottom: inherit;"><b>Observação:</b> a promoção selecionada poderá adicionar ingredientes ao lanche caso seja necessário</p>
		<form id="formPromocoes">
			<table class="w3-table w3-bordered" style="width: 100%;">
				<th></th>
				<th>Nome</th>
				<th>Descri&ccedil;&atilde;o</th>
				<c:forEach var="promocao" items="${promo.promocoes}">
						<tr>
							<td style="vertical-align: middle; padding: 4px; width: 2%;">
								<input type="radio" name="promocoesRadio" value="${promocao.id}" />
							</td>
							<td  style="vertical-align: middle; padding: 4px; width: 30%;">${promocao.nome}</td>
							<td  style="vertical-align: middle; padding: 4px; width: 68%;">${promocao.descricao}</td>
						</tr>
				</c:forEach>
				<jsp:setProperty name="promo" property="promocoesSelecionadas" param="promocoesSelecionadas" /> 
			</table>
			<div class="clear"></div>
			<br>

			<div style="text-align: center;">
				<a data-type="submit" href="#" class="w3-button w3-green getPromocoes" style="cursor:pointer;">Continuar</a>
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