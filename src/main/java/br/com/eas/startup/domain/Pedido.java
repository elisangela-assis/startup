/**
 * 
 */
package br.com.eas.startup.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import br.com.eas.startup.domain.factory.ItemPedidoFactory;
import br.com.eas.startup.shared.exception.StartupException;

/**
 * @author Elisangela de Assis da Silva
 */
public class Pedido {

    private static Long ID = 1L;

    private static Long getProximoId() {
	return ID++;
    }

    /**
     * Identificador único
     */
    private Long id;

    /**
     * Data do pedido
     */
    private Date dataPedido;

    private Set<AbstractItemPedido> itens = new HashSet<AbstractItemPedido>();

    /**
     * Esse campo retorna o valor do pedido
     */
    private BigDecimal valorPedido = BigDecimal.ZERO;

    /**
     * Esse campo retorna o valor do pedido
     */
    private BigDecimal valorTotalPedido = BigDecimal.ZERO;

    /**
     * Responsável pelo pedido
     */
    private String responsavel;

    /**
     * Construtor padrão
     */
    Pedido() {
	// Construtor padrão
    }

    /**
     * Cria um pedido para um responsável. Inicialmente o pedido não possui itens
     * 
     * @param pResponsavel
     */
    public Pedido(final String pResponsavel) {
	Validate.notEmpty(pResponsavel, "O responsável do pedido é obrigatório!");

	dataPedido = Calendar.getInstance().getTime();
	responsavel = pResponsavel;
	id = getProximoId();
    }

    /**
     * Adiciona um item ao pedido. Não é possível adicionar dois lanches com a mesma descrição num pedido, neste caso, deve-se alterar a quantidade do
     * item
     * 
     * @param pLanche
     * @param pQuantidade
     * @param pSolicitante
     * @return item pedido criado
     * @throws StartupException
     */
    public AbstractItemPedido adicionarItem(final Lanche pLanche, final Integer pQuantidade, final String pSolicitante)
	    throws StartupException {
	Validate.notEmpty(pSolicitante, "O solicitante é obrigatório!");
	validaObrigatoriedadeDaQuantidade(pQuantidade);
	validaObrigatoriedadeDoLanche(pLanche);

	final AbstractItemPedido itemPedido = (AbstractItemPedido) ItemPedidoFactory.getInstance().criarItemPedido(this, pLanche,
		pQuantidade);

	if (itens.contains(itemPedido)) {
	    throw new StartupException(
		    "O lanche em questão já foi adicionado a lista de itens do pedido! Basta alterar a quantidade. Verifique!");
	}
	itens.add(itemPedido);

	calcularValorPedidoEValorTotal();
	return itemPedido;
    }

    /**
     * Ajusta a quantidade do item de pedido existente para o lanche. O material deve corresponder a um item do pedido, caso contrário o método lança
     * {@link IllegalArgumentException}
     * 
     * @param pLanche
     *            lanche do item. Usado para localizar o mesmo no pedido
     * @param pNovaQtd
     *            Quantidade que será atribuida ao item.
     * @param pSolicitanteAlteracao
     *            Usuário que está realizando a alteração da quantidade
     * @return o Item de pedido com a quantidade alterada
     * @throws StartupException
     */
    public AbstractItemPedido ajustarQtdItem(final Lanche pLanche, final int pNovaQtd, final String pSolicitanteAlteracao)
	    throws StartupException {
	Validate.notNull(pLanche, "O lanche é obrigatório!");
	Validate.notEmpty(pSolicitanteAlteracao, "O solicitante é obrigatório!");
	validaObrigatoriedadeDaQuantidade(pNovaQtd);

	final AbstractItemPedido itemAlterado = getItem(pLanche);
	Validate.notNull(itemAlterado, "Não existe item nesse pedido com o material informado.");

	itemAlterado.setQuantidade(pNovaQtd);
	calcularValorPedidoEValorTotal();

	return itemAlterado;
    }

    /**
     * Calcula os valores do pedido e valor total de acordo com os itens
     */
    private void calcularValorPedidoEValorTotal() {
	valorPedido = BigDecimal.ZERO;
	valorTotalPedido = BigDecimal.ZERO;

	for (final IItemPedido item : itens) {
	    valorPedido = valorPedido.add(item.getValorTotalItem());
	}

	valorTotalPedido = valorTotalPedido.add(valorPedido);
	valorTotalPedido.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * @return the dataPedido
     */
    public Date getDataPedido() {
	return dataPedido;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * Retorna o item do pedido correspondente ao lanche recebido como parâmetro
     * 
     * @param pLanche
     *            lanche do item
     * @return o item correspondente ao lanche ou null se não encontrar
     */
    public AbstractItemPedido getItem(final Lanche pLanche) {
	AbstractItemPedido itemRetornado = null;

	for (final AbstractItemPedido item : this.getItens()) {
	    if (item.getLanche().equals(pLanche)) {
		itemRetornado = item;
		break;
	    }
	}
	return itemRetornado;
    }

    /**
     * @return the itens
     */
    public Set<AbstractItemPedido> getItens() {
	return Collections.unmodifiableSet(itens);
    }

    /**
     * @return the responsavel
     */
    public String getResponsavel() {
	return responsavel;
    }

    /**
     * @return the valorPedido
     */
    public BigDecimal getValorPedido() {
	return valorPedido;
    }

    /**
     * @return the valorTotalPedido
     */
    public BigDecimal getValorTotalPedido() {
	return valorTotalPedido;
    }

    /**
     * Valida a quantidade de itens que deve ser maior do que zero
     * 
     * @param pQuantidade
     * @throws StartupException
     */
    private void validaObrigatoriedadeDaQuantidade(final Integer pQuantidade) throws StartupException {
	if (pQuantidade == null || pQuantidade <= 0) {
	    throw new StartupException("A quantidade do item é obrigatória!");
	}
    }

    /**
     * Valida a obrigatoriedade do lanche
     * 
     * @param pLanche
     * @throws StartupException
     */
    private void validaObrigatoriedadeDoLanche(final Lanche pLanche) throws StartupException {
	if (pLanche == null) {
	    throw new StartupException("O lanche é obrigatório!");
	}
    }
}
