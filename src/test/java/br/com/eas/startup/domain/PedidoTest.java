/**
 * 
 */
package br.com.eas.startup.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import br.com.eas.startup.shared.MassaDadosUtil;
import br.com.eas.startup.shared.SimNao;
import br.com.eas.startup.shared.exception.StartupException;

/**
 * Testa a criação do pedido, a inclusão de itens e o cálculo correto do lanche
 * 
 * @author Elisangela de Assis da Silva
 */
public class PedidoTest {

    private static final String SOLICITANTE = "EAS";

    /**
     * Dado que eu construa um pedido com um responsável nulo ou vazio<br>
     * Então não será possível e uma exceção será lançada<br>
     */
    @Test
    public void testConstrutorPedidoSemItensResponsavelInvalido() {

	System.out.println("Dado que eu construa um pedido com um responsável nulo ou vazio");
	System.out.println("Então não será possível e uma exceção será lançada");

	final List<String> responsaveis = Arrays.asList(null, "");

	for (final String resp : responsaveis) {
	    try {
		new Pedido(resp);
	    } catch (final Exception e) {
		Assert.assertEquals("O responsável do pedido é obrigatório!", e.getMessage());
	    }
	}
    }

    /**
     * Dado que eu tenha um pedido<br>
     * Quando eu adicionar 2 lanches do cardápio 'X-Egg Bacon' que têm 6 porções de queijo e aplicar a promoção 'Muito queijo'<br>
     * Então será possível e o pedido ficará com o valor correto e a quantidade correta de porções de queijo<br>
     * 
     * @throws Exception
     */
    @Test
    public void testPedidoAdicionaDoisItensLancheCardapioAplicarPromoMuitoQueijoSucesso() throws Exception {

	System.out.println("Dado que eu tenha um pedido");

	final Pedido pedido = new Pedido(SOLICITANTE);

	Assert.assertEquals(SOLICITANTE, pedido.getResponsavel());
	Assert.assertNotNull(pedido.getDataPedido());

	System.out
		.println("Quando eu adicionar 2 lanches do cardápio 'X-Egg Bacon' que têm 6 porções de queijo e aplicar a promoção 'Muito queijo'");

	final Ingrediente ingredQueijo = MassaDadosUtil.getQueijo();
	Integer qtdAIncluir = 0;
	Integer qtdExistente = 0;
	final Integer qtdPorcoesDesejada = 6;
	final Lanche xEggBacon = MassaDadosUtil.getxEggBacon();

	// Deixa a quantidade do item como 1 pq será usado apenas para comparação do ingrediente
	final ItemIngrediente itemIngredQueijo = new ItemIngrediente(ingredQueijo, 1);

	// Como se deseja 6 porções deve-se calcular a quantidade de ingredientes a ser adicionada no item
	qtdExistente = xEggBacon.getQtdDoItemIngrediente(itemIngredQueijo);
	qtdAIncluir = qtdPorcoesDesejada - qtdExistente;
	final Integer qtdTotal = qtdAIncluir + qtdExistente;

	xEggBacon.adicionarItemIngrediente(itemIngredQueijo, qtdAIncluir);
	Assert.assertEquals(qtdPorcoesDesejada, xEggBacon.getQtdDoItemIngrediente(itemIngredQueijo));

	// associa a promoção e a quantidade do ingrediente deve estar correta
	xEggBacon.setPromocao(MassaDadosUtil.getMuitoQueijo());
	Assert.assertEquals(qtdPorcoesDesejada, xEggBacon.getQtdDoItemIngrediente(itemIngredQueijo));

	System.out
		.println("Então será possível e o pedido ficará com o valor correto e a quantidade correta de porções de queijo");
	final Integer qtdLanches = 2;
	pedido.adicionarItem(xEggBacon, qtdLanches, SOLICITANTE);

	final BigDecimal valorItem = xEggBacon.getValorTotal();

	Assert.assertEquals(valorItem.multiply(BigDecimal.valueOf(qtdLanches)), pedido.getValorTotalPedido());
	Assert.assertEquals(qtdTotal, xEggBacon.getQtdDoItemIngrediente(itemIngredQueijo));
    }

    /**
     * Dado que eu tenha um pedido<br>
     * Quando eu adicionar um lanche 'X-Egg Bacon' do cardápio<br>
     * Então será possível e o valor do lanche será calculado corretamente<br>
     * 
     * @throws Exception
     */
    @Test
    public void testPedidoAdicionaItemLancheCardapio() throws Exception {

	System.out.println("Dado que eu tenha um pedido");

	final Pedido pedido = new Pedido(SOLICITANTE);

	System.out.println("Quando eu adicionar um lanche 'X-Egg Bacon' do cardápio");

	pedido.adicionarItem(MassaDadosUtil.getxEggBacon(), 1, SOLICITANTE);

	System.out.println("Então será possível e o valor do lanche será calculado corretamente");
	final BigDecimal valorItem = MassaDadosUtil.getxEggBacon().getValorTotal();

	Assert.assertEquals(valorItem, pedido.getValorTotalPedido());
    }

    /**
     * Dado que eu tenha um pedido<br>
     * Quando eu adicionar um lanche do cardápio 'X-Egg Bacon' que têm 3 porções de carne e aplicar a promoção 'Muita carne'<br>
     * Então será possível e o lanche terá o desconto correto e quantidade correta de porções de carne<br>
     * 
     * @throws Exception
     */
    @Test
    public void testPedidoAdicionaItemLancheCardapioAplicarPromoMuitaCarneSucesso() throws Exception {

	System.out.println("Dado que eu tenha um pedido");

	final Pedido pedido = new Pedido(SOLICITANTE);

	Assert.assertEquals(SOLICITANTE, pedido.getResponsavel());
	Assert.assertNotNull(pedido.getDataPedido());

	System.out
		.println("Quando eu adicionar um lanche do cardápio 'X-Egg Bacon' que têm 3 porções de carne e aplicar a promoção 'Muita carne'");

	final Lanche xEggBacon = MassaDadosUtil.getxEggBacon();

	final Ingrediente ingredHamburguerCarne = MassaDadosUtil.getHamburguerCarne();
	final Integer qtdAIncluir = 2;
	final Integer qtdExistente = 1;
	final Integer qtdTotal = qtdAIncluir + qtdExistente;

	final ItemIngrediente itemIngredHamburguerCarne = new ItemIngrediente(ingredHamburguerCarne, qtdExistente);
	// xEggBacon.adicionarItemIngrediente(itemIngredHamburguerCarne, qtdAIncluir);
	xEggBacon.setPromocao(MassaDadosUtil.getMuitaCarne());

	System.out.println("Então será possível e o lanche terá o desconto correto e quantidade correta de porções de carne");
	pedido.adicionarItem(xEggBacon, 1, SOLICITANTE);

	final BigDecimal valorItem = xEggBacon.getValorTotal();

	Assert.assertEquals(valorItem, pedido.getValorTotalPedido());
	Assert.assertEquals(qtdTotal, xEggBacon.getQtdDoItemIngrediente(itemIngredHamburguerCarne));
    }

    /**
     * Dado que eu tenha um pedido<br>
     * Quando eu adicionar um lanche do cardápio 'X-Egg Bacon' que têm 6 porções de queijo e aplicar a promoção 'Muito queijo'<br>
     * Então será possível e o lanche terá o desconto correto e quantidade correta de porções de queijo<br>
     * 
     * @throws Exception
     */
    @Test
    public void testPedidoAdicionaItemLancheCardapioAplicarPromoMuitoQueijoSucesso() throws Exception {

	System.out.println("Dado que eu tenha um pedido");

	final Pedido pedido = new Pedido(SOLICITANTE);

	Assert.assertEquals(SOLICITANTE, pedido.getResponsavel());
	Assert.assertNotNull(pedido.getDataPedido());

	System.out
		.println("Quando eu adicionar um lanche do cardápio 'X-Egg Bacon' que têm 6 porções de queijo e aplicar a promoção 'Muito queijo'");

	final Ingrediente ingredQueijo = MassaDadosUtil.getQueijo();
	Integer qtdAIncluir = 0;
	Integer qtdExistente = 0;
	final Integer qtdPorcoesDesejada = 6;
	final Lanche xEggBacon = MassaDadosUtil.getxEggBacon();

	// Deixa a quantidade do item como 1 pq será usado apenas para comparação do ingrediente
	final ItemIngrediente itemIngredQueijo = new ItemIngrediente(ingredQueijo, 1);

	// Como se deseja 6 porções deve-se calcular a quantidade de ingredientes a ser adicionada no item
	qtdExistente = xEggBacon.getQtdDoItemIngrediente(itemIngredQueijo);
	qtdAIncluir = qtdPorcoesDesejada - qtdExistente;
	final Integer qtdTotal = qtdAIncluir + qtdExistente;

	xEggBacon.adicionarItemIngrediente(itemIngredQueijo, qtdAIncluir);
	Assert.assertEquals(qtdPorcoesDesejada, xEggBacon.getQtdDoItemIngrediente(itemIngredQueijo));

	// associa a promoção e a quantidade do ingrediente deve estar correta
	xEggBacon.setPromocao(MassaDadosUtil.getMuitoQueijo());
	Assert.assertEquals(qtdPorcoesDesejada, xEggBacon.getQtdDoItemIngrediente(itemIngredQueijo));

	System.out.println("Então será possível e o lanche terá o desconto correto e quantidade correta de porções de queijo");
	pedido.adicionarItem(xEggBacon, 1, SOLICITANTE);

	final BigDecimal valorItem = xEggBacon.getValorTotal();

	Assert.assertEquals(valorItem, pedido.getValorTotalPedido());
	Assert.assertEquals(qtdTotal, xEggBacon.getQtdDoItemIngrediente(itemIngredQueijo));
    }

    /**
     * Dado que eu tenha um pedido<br>
     * Quando eu adicionar um lanche personalizado com alface e sem bacon e tentar aplicar a promoção 'Light'<br>
     * Então será possível e o valor do lanche terá o percentual de desconto correto<br>
     * 
     * @throws Exception
     */
    @Test
    public void testPedidoAdicionaItemLanchePersonalizadoAlfaceAplicarPromoLightSucesso() throws Exception {

	System.out.println("Dado que eu tenha um pedido");

	final Pedido pedido = new Pedido(SOLICITANTE);

	Assert.assertEquals(SOLICITANTE, pedido.getResponsavel());
	Assert.assertNotNull(pedido.getDataPedido());

	System.out
		.println("Quando eu adicionar um lanche personalizado com alface e sem bacon e tentar aplicar a promoção 'Light'");

	final Set<ItemIngrediente> itensIngredientes = new HashSet<ItemIngrediente>();

	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getAlface(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getHamburguerCarne(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getQueijo(), 1));

	final Lanche lanchePersonalizado = new Lanche(itensIngredientes, SimNao.S);
	lanchePersonalizado.setPromocao(MassaDadosUtil.getLight());

	System.out.println("Então será possível e o valor do lanche terá o percentual de desconto correto");
	pedido.adicionarItem(lanchePersonalizado, 1, SOLICITANTE);

	final BigDecimal valorItem = lanchePersonalizado.getValorTotal();

	Assert.assertEquals(valorItem, pedido.getValorTotalPedido());
    }

    /**
     * Dado que eu tenha um pedido<br>
     * Quando eu adicionar um lanche personalizado com bacon e tentar aplicar a promoção 'Light'<br>
     * Então não será possível e uma exceção será lançada<br>
     * 
     * @throws Exception
     */
    @Test
    public void testPedidoAdicionaItemLanchePersonalizadoBaconAplicarPromoLightSemSucesso() throws Exception {

	System.out.println("Dado que eu tenha um pedido");

	System.out.println("Quando eu adicionar um lanche personalizado com bacon e tentar aplicar a promoção 'Light'");

	final Set<ItemIngrediente> itensIngredientes = new HashSet<ItemIngrediente>();

	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getBacon(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getHamburguerCarne(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getAlface(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getQueijo(), 1));

	final Lanche lanchePersonalizado = new Lanche(itensIngredientes, SimNao.S);
	System.out.println("Então não será possível e uma exceção será lançada");

	try {
	    lanchePersonalizado.setPromocao(MassaDadosUtil.getLight());
	} catch (final StartupException e) {
	    Assert.assertEquals(
		    "Não é possível aplicar esta promoção porque o lanche possui um ingrediente não permitido pela promoção!",
		    e.getMessage());
	}
    }

    /**
     * Dado que eu tenha um pedido com um lanche personalizado e com a promoção 'Light'<br>
     * Quando eu tentar adicionar mais um item igual<br>
     * Então não será possível e uma exceção será lançada, pois, deve-se alterar a quantidade do item<br>
     * 
     * @throws Exception
     */
    @Test
    public void testPedidoAdicionaItensExistenteSemAlterarQuantidade() throws Exception {

	System.out.println("Dado que eu tenha um pedido com um lanche personalizado e com a promoção 'Light'");

	final Pedido pedido = new Pedido(SOLICITANTE);

	Assert.assertEquals(SOLICITANTE, pedido.getResponsavel());
	Assert.assertNotNull(pedido.getDataPedido());

	final Set<ItemIngrediente> itensIngredientes = new HashSet<ItemIngrediente>();

	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getAlface(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getHamburguerCarne(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getQueijo(), 1));

	final Lanche lanchePersonalizado = new Lanche(itensIngredientes, SimNao.S);
	lanchePersonalizado.setPromocao(MassaDadosUtil.getLight());

	System.out.println("Quando eu tentar adicionar mais um item igual");

	final Integer qtdLanches = 1;
	pedido.adicionarItem(lanchePersonalizado, qtdLanches, SOLICITANTE);

	System.out.println("Então não será possível e uma exceção será lançada, pois, deve-se alterar a quantidade do item");
	try {
	    pedido.adicionarItem(lanchePersonalizado, qtdLanches, SOLICITANTE);
	} catch (final Exception e) {
	    Assert.assertEquals(
		    "O lanche em questão já foi adicionado a lista de itens do pedido! Basta alterar a quantidade. Verifique!",
		    e.getMessage());
	}
    }

    /**
     * Dado que eu tenha um pedido<br>
     * Quando eu adicionar um lanche do cardápio 'X-Burger' e um lanche personalizado 'X-Egg Bacon' que têm 3 porções de carne e aplicar a promoção
     * 'Muita carne'<br>
     * Então será possível e o lanche da promoção terá o desconto correto e quantidade correta de porções de carne<br>
     * 
     * @throws Exception
     */
    @Test
    public void testPedidoAdicionaLanchesDiversosComPromocaoComSucesso() throws Exception {

	System.out.println("Dado que eu tenha um pedido");

	final Pedido pedido = new Pedido(SOLICITANTE);

	Assert.assertEquals(SOLICITANTE, pedido.getResponsavel());
	Assert.assertNotNull(pedido.getDataPedido());

	System.out
		.println("Quando eu adicionar um lanche do cardápio 'X-Burger' e um lanche personalizado 'X-Egg Bacon' que têm 3 porções de carne e aplicar a promoção 'Muita carne'");

	// Lanche X-Burger cardápio
	final Lanche xBurger = MassaDadosUtil.getxBurguer();

	pedido.adicionarItem(xBurger, 1, SOLICITANTE);

	// Lanche X-Egg Bacon com muita carne
	final Ingrediente ingredHamburguerCarne = MassaDadosUtil.getHamburguerCarne();
	final Integer qtdAIncluir = 0;
	final Integer qtdExistente = 3;
	final Integer qtdTotal = qtdAIncluir + qtdExistente;
	final Lanche xEggBacon = MassaDadosUtil.getxEggBacon();

	final ItemIngrediente itemIngredHamburguerCarne = new ItemIngrediente(ingredHamburguerCarne, qtdExistente);
	// xEggBacon.adicionarItemIngrediente(itemIngredHamburguerCarne, qtdAIncluir);
	xEggBacon.setPromocao(MassaDadosUtil.getMuitaCarne());

	System.out
		.println("Então será possível e o lanche da promoção terá o desconto correto e quantidade correta de porções de carne");
	pedido.adicionarItem(xEggBacon, 1, SOLICITANTE);

	final BigDecimal valorXEggBacon = xEggBacon.getValorTotal();
	final BigDecimal valorXBurger = xBurger.getValorTotal();

	Assert.assertEquals(valorXEggBacon.add(valorXBurger), pedido.getValorTotalPedido());
	Assert.assertEquals(qtdTotal, xEggBacon.getQtdDoItemIngrediente(itemIngredHamburguerCarne));
    }

    /**
     * Dado que eu tenha um pedido<br>
     * Quando eu adicionar 3 lanches personalizados com alface e sem bacon e tentar aplicar a promoção 'Light'<br>
     * Então será possível e cada lanche terá o percentual de desconto correto<br>
     * 
     * @throws Exception
     */
    @Test
    public void testPedidoAdicionaTresItensLanchePersonalizadoAlfaceAplicarPromoLightSucesso() throws Exception {

	System.out.println("Dado que eu tenha um pedido");

	final Pedido pedido = new Pedido(SOLICITANTE);

	Assert.assertEquals(SOLICITANTE, pedido.getResponsavel());
	Assert.assertNotNull(pedido.getDataPedido());

	System.out
		.println("Quando eu adicionar 3 lanches personalizados com alface e sem bacon e tentar aplicar a promoção 'Light'");

	final Set<ItemIngrediente> itensIngredientes = new HashSet<ItemIngrediente>();

	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getAlface(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getHamburguerCarne(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getQueijo(), 1));

	final Lanche lanchePersonalizado = new Lanche(itensIngredientes, SimNao.S);
	lanchePersonalizado.setPromocao(MassaDadosUtil.getLight());

	System.out.println("Então será possível e cada lanche terá o percentual de desconto correto");
	final Integer qtdLanches = 3;
	pedido.adicionarItem(lanchePersonalizado, qtdLanches, SOLICITANTE);

	final BigDecimal valorItem = lanchePersonalizado.getValorTotal();

	Assert.assertEquals(valorItem.multiply(BigDecimal.valueOf(qtdLanches)), pedido.getValorTotalPedido());
    }
}
