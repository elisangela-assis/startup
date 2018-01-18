<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
     <head>
     <title>Contato</title>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link rel="icon" href="../images/favicon.ico">
     <link rel="shortcut icon" href="../images/favicon.ico" />
     <link rel="stylesheet" href="../css/style.css">
     <link rel="stylesheet" href="../css/form.css">
     <script src="../js/jquery.js"></script>
     <script src="../js/jquery-migrate-1.1.1.js"></script>
     <script src="../js/superfish.js"></script>
     <script src="../js/jquery.easing.1.3.js"></script>
     <script src="../js/sForm.js"></script>
     <script src="../js/forms.js"></script>
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
                   <li><a href="cardapio.jsp">Card&aacute;pio</a></li>
                   <li class="current"><a href="contato.jsp">Contato</a></li>
                 </ul>
              </nav>
           <div class="clear"></div>
           </div>
           <div class="clear"></div>
      </div>
    </div>
</header>
<!--=======content================================-->

<div class="content"><div class="ic"></div>
  <div class="container_12">
    <div class="grid_6">
      <h2>Como chegar</h2>
            <div class="map">
            <figure class="img_inner">
                          <iframe src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAMI4tRziEMyXVnSw2FpTW0xytYdG_CAQk&q=Campinas,Sao+Paulo,SP,Brazil&attribution_source=Campinas,Sao+Paulo,SP,Brazil"></iframe> 
               </figure>
              <address>
                            <dl>
                                <dt><p>Startup Lanchonete<br>
                                    Campinas - SP</p>
                                </dt>
                                <dd><span>Telefone:</span>+55 0800 603 6035</dd>
                                <dd><span>FAX:</span>+55 0800 889 9898</dd>
                            </dl>
                         </address>
 
          </div>
    </div>
    <div class="grid_5 prefix_1">
      <h2>Entre em contato</h2>
      <form id="form">
      <div class="success_wrapper">
      <div class="success">Mensagem enviada com sucesso!<br>
      <strong>N&oacute;s responderemos em breve.</strong> </div></div>
      <fieldset>
      <label class="name">
      <input type="text" value="Nome:">
      <br class="clear">
      <span class="error error-empty">*Este n&atilde;o &eacute; um nome v&aacute;lido.</span><span class="empty error-empty">*Este campo &eacute; obrigat&oacute;rio.</span> </label>
      <label class="email">
      <input type="text" value="E-mail:">
      <br class="clear">
      <span class="error error-empty">*Este n&atilde;o &eacute; um e-mail v&aacute;lido.</span><span class="empty error-empty">*Este campo &eacute; obrigat&oacute;rio.</span> </label>
      <label class="phone">
      <input type="tel" value="Telefone:">
      <br class="clear">
      <span class="error error-empty">*Este n&atilde;o &eacute; um telefone v&aacute;lido.</span><span class="empty error-empty">*Este campo &eacute; obrigat&oacute;rio.</span> </label>
      <label class="message">
      <textarea>Mensagem:</textarea>
      <br class="clear">
      <span class="error">*A mensagem &eacute; muito curta.</span> <span class="empty">*Este campo &eacute; obrigat&oacute;rio.</span> </label>
      <div class="clear"></div>
      <div class="btns"><a data-type="reset" class="btn">limpar</a><a data-type="submit" class="btn">enviar</a>
      <div class="clear"></div>
      </div></fieldset></form>
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
                   <li><a href="cardapio.jsp">Card&aacute;pio</a></li>
                   <li class="current"><a href="contato.jsp">Contato</a></li>
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