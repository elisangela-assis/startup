# startup-lanchonete
Startup Lanchonete online

desenvolvido por: assiselisangela@gmail.com


Instruções para executar
---------------------------------------

- Projeto desenvolvido com o Eclipse Luna e foi usado o servidor de aplicação Apache Tomcat 7.0 embarcado no Eclipse.
Entretanto é possível gerar o .war do projeto usando o Maven:
$ mvn package 
- O pacote gerado tem o nome startup.war e fica localizado no diretório startup/target.
- Quando o .war estiver dentro do servidor de aplicação e este estiver executando é possível acessar a aplicação através do link exemplo 
http://localhost:8080/startup/

- Os pedidos podem ser feitos ao selecionar o nome de um lanche na página inicial.


Nível de complexidade escolhido
---------------------------------------

- Foi escolhido o nível de complexidade Fácil. Entretanto, relato abaixo algumas peculiaridades do projeto:
  * Foi usado o Servlets 3.0.1, JSP, JQuery e o Ajax para implementar algumas funcionalidades do front-end. Acredita-se que a opção por estas tecnologias tornaram a implementação do front-end um pouco mais complexas. Uma vez que alguns frameworks ou tecnologias como o JSF e Struts poderiam ter facilitado o desenvolvimento. Esta decisão foi tomada porque acreditou-se que o uso dessas tecnologias não seriam permitidas (?) no desenvolvimento do projeto.
  * demais informações podem ser encontradas no arquivo pom.xml que está dentro do projeto.


Limitações conhecidas do projeto
---------------------------------------

- apenas uma única promoção pode ser aplicada ao lanche selecionado;
- não é possível pedir lanches diferentes no mesmo pedido, pode-se apenas alterar a quantidade de itens do lanche selecionado; 
