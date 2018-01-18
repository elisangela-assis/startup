/**
 * 
 */
package br.com.eas.startup.domain.factory;

import br.com.eas.startup.domain.IItemPedido;
import br.com.eas.startup.domain.ItemPedidoCardapio;
import br.com.eas.startup.domain.ItemPedidoPersonalizado;
import br.com.eas.startup.domain.Lanche;
import br.com.eas.startup.domain.Pedido;
import br.com.eas.startup.shared.SimNao;
import br.com.eas.startup.shared.exception.StartupException;

/**
 * Fabrica da Instancia do IItemPedido que retorna a instancia correta do item do pedido, baseando-se no tipo do lanche
 * 
 * @author
 * @version
 */
public class ItemPedidoFactory {

    /**
     * atributo com a instancia da classe
     */
    private static ItemPedidoFactory instance = new ItemPedidoFactory();

    /**
     * Retorna instancia da Factory.
     * 
     * @return factory
     */
    public static ItemPedidoFactory getInstance() {
	return instance;
    }

    private ItemPedidoFactory() {
	super();
    }

    /**
     * Retorna a instancia correta do item do pedido, baseando-se no tipo do lanche
     * 
     * @param pPedido
     * @param pLanche
     * @param pQuantidade
     * 
     * @return Instancia IItemPedido
     * @throws StartupException
     */
    public IItemPedido criarItemPedido(final Pedido pPedido, final Lanche pLanche, final Integer pQuantidade)
	    throws StartupException {

	IItemPedido retorno = null;

	if (pLanche != null && SimNao.S == pLanche.getPersonalizado()) {
	    retorno = new ItemPedidoPersonalizado(pPedido, pLanche, pQuantidade);
	} else {
	    retorno = new ItemPedidoCardapio(pPedido, pLanche, pQuantidade);
	}
	return retorno;
    }

}
