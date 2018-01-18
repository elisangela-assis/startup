/**
 * 
 */
package br.com.eas.startup.domain;

import br.com.eas.startup.shared.exception.StartupException;

/**
 * @author Elisangela de Assis da Silva
 */
public class ItemPedidoPersonalizado extends AbstractItemPedido {

    /**
     * Construtor padrão
     */
    public ItemPedidoPersonalizado() {
	// Construtor padrão
    }

    /**
     * Construtor com campos obrigatórios
     * 
     * @param pPedido
     * @param pLanche
     * @param pQuantidade
     * @throws StartupException
     */
    public ItemPedidoPersonalizado(final Pedido pPedido, final Lanche pLanche, final Integer pQuantidade) throws StartupException {
	super(pPedido, pLanche, pQuantidade);
    }

    /**
     * Retorna o tipo do item
     */
    public String getTipo() {
	return TIPO_ITEM_PERSONALIZADO;
    }

}
