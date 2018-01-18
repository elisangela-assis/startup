/**
 * 
 */
package br.com.eas.startup.domain;

import br.com.eas.startup.shared.exception.StartupException;

/**
 * @author Elisangela de Assis da Silva
 */
public class ItemPedidoPromocao extends AbstractItemPedido {

    /**
     * Construtor padrão
     */
    public ItemPedidoPromocao() {
	// construtor padrão
    }

    /**
     * Construtor com campos obrigatórios
     * 
     * @param pPedido
     * @param pLanche
     * @param pQuantidade
     * @throws StartupException
     */
    public ItemPedidoPromocao(final Pedido pPedido, final Lanche pLanche, final Integer pQuantidade) throws StartupException {
	super(pPedido, pLanche, pQuantidade);
    }

    public String getTipo() {
	return TIPO_ITEM_PROMOCAO;
    }
}
