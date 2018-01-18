/**
 * 
 */
package br.com.eas.startup.domain;

import java.math.BigDecimal;

import br.com.eas.startup.shared.exception.StartupException;

/**
 * Essa interface existe para que a factory de item retorne qualquer instância que a implemente
 *
 * @author Elisangela de Assis da Silva
 */
public interface IItemPedido {

    /*
     * O que torna um item de pedido igual a outro é o pedido a ele associado e o lanche
     */
    public abstract boolean equals(final Object obj);

    /**
     * @return the id
     */
    public abstract Long getId();

    /**
     * 
     * @return the lanche
     */
    public abstract Lanche getLanche();

    /**
     * @return the pedido
     */
    public abstract Pedido getPedido();

    /**
     * @return the quantidade
     */
    public abstract Integer getQuantidade();

    /**
     * 
     * @return o tipo do item
     */
    public abstract String getTipo();

    /**
     * @return the valorTotalItem
     */
    public abstract BigDecimal getValorTotalItem();

    /**
     * @return the valorUnitario
     */
    public abstract BigDecimal getValorUnitario();

    public abstract int hashCode();

    /**
     * @param pOutraEntidade
     * @return true/false
     */
    public abstract boolean mesmoIdQue(final IItemPedido pOutraEntidade);

    /**
     * @param pQuantidade
     *            the quantidade to set
     * @throws StartupException
     */
    public abstract void setQuantidade(final Integer pQuantidade) throws StartupException;
}
