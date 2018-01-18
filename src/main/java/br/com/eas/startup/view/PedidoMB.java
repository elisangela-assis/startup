/**
 * 
 */
package br.com.eas.startup.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.eas.startup.domain.ItemIngrediente;
import br.com.eas.startup.domain.Lanche;
import br.com.eas.startup.domain.Pedido;
import br.com.eas.startup.domain.Promocao;
import br.com.eas.startup.shared.MassaDadosUtil;
import br.com.eas.startup.shared.exception.StartupException;

/**
 * @author Elisangela de Assis da Silva
 */
public class PedidoMB {

    /**
     * Parâmetro que indica ao servlet que o pedido está sendo salvo.
     */
    public static final String SALVAR_PEDIDO = "salvarPedido";

    /**
     * Parâmetro que indica ao servlet que a quantidade de lanches foi alterada
     */
    public static final String ALTERAR_QUANTIDADE = "alterarQuantidade";

    private String idLancheSelecionado;

    private String idsItemIngredSelecionados;

    private String idsPromocoesSelecionadas;

    private Pedido pedido;

    private Integer quantidade;

    /**
     * 
     */
    public PedidoMB() {
	super();
    }

    /**
     * @return the idLancheSelecionado
     */
    public String getIdLancheSelecionado() {
	return idLancheSelecionado;
    }

    /**
     * @return the idsItemIngredSelecionados
     */
    public String getIdsItemIngredSelecionados() {
	return idsItemIngredSelecionados;
    }

    /**
     * @return the idsPromocoesSelecionadas
     */
    public String getIdsPromocoesSelecionadas() {
	return idsPromocoesSelecionadas;
    }

    /**
     * @return the itensIngredsSelecionados
     * @throws StartupException
     */
    public List<ItemIngrediente> getItensIngredsSelecionados() throws StartupException {
	final List<ItemIngrediente> itensIngredsSelecionados = new ArrayList<ItemIngrediente>();

	if (getIdsItemIngredSelecionados() != null && !getIdsItemIngredSelecionados().isEmpty()) {
	    final String idsQtds[] = getIdsItemIngredSelecionados().split(",");
	    for (final String idQtd : idsQtds) {
		final String itens[] = idQtd.split("=");
		if (itens != null && itens.length > 0) {
		    final ItemIngrediente itemIngred = MassaDadosUtil.getItensIngredientes().get(Integer.valueOf(itens[0]));
		    itensIngredsSelecionados.add(new ItemIngrediente(itemIngred.getIngrediente(), Integer.valueOf(itens[1])));
		}
	    }
	}
	return itensIngredsSelecionados;
    }

    /**
     * @return the lancheSelecionado
     * @throws StartupException
     */
    private Lanche getLancheSelecionado() throws StartupException {
	Lanche lancheSelecionado = null;
	if (getIdLancheSelecionado() != null && !getIdLancheSelecionado().isEmpty()) {
	    for (final Lanche lanche : MassaDadosUtil.getLanchesCardapio()) {

		if (lanche.getId().equals(Long.valueOf(getIdLancheSelecionado()))) {

		    final Set<ItemIngrediente> listaIngredientes = new HashSet<ItemIngrediente>();

		    for (final ItemIngrediente item : lanche.getIngredientes()) {
			listaIngredientes.add(new ItemIngrediente(item.getIngrediente(), item.getQuantidade()));
		    }
		    lancheSelecionado = new Lanche(listaIngredientes, lanche.getPersonalizado());
		    lancheSelecionado.setNome(lanche.getNome());
		    lancheSelecionado.setDescricao(lanche.getDescricao());
		    break;
		}
	    }
	}

	if (lancheSelecionado != null) {
	    // incluo ingredientes adicionais
	    for (final ItemIngrediente itemIngred : getItensIngredsSelecionados()) {
		lancheSelecionado.adicionarItemIngrediente(itemIngred, itemIngred.getQuantidade());
	    }

	    // TODO: aqui cabe melhoria, pois, hoje o lanche aceita apenas uma promoção
	    // associo a promoção ajustando a quantidade do ingrediente
	    for (final Promocao promocao : getPromocoesSelecionadas()) {
		lancheSelecionado.setPromocao(promocao);
	    }
	}
	return lancheSelecionado;
    }

    /**
     * @return the pedido
     * @throws StartupException
     */
    public Pedido getPedido() throws StartupException {
	pedido = new Pedido(MassaDadosUtil.getSolicitante());

	final Lanche lancheSelecionado = getLancheSelecionado();

	if (lancheSelecionado != null) {
	    quantidade = 1;
	    pedido.adicionarItem(lancheSelecionado, quantidade, MassaDadosUtil.getSolicitante());
	}
	return pedido;
    }

    /**
     * Lista de promoções selecionadas
     * 
     * @return the listPromosSelecionadas
     * @throws StartupException
     * @throws NumberFormatException
     */
    private List<Promocao> getPromocoesSelecionadas() throws NumberFormatException, StartupException {
	final List<Promocao> listPromosSelecionadas = new ArrayList<Promocao>();

	for (final Promocao promocao : MassaDadosUtil.getPromocoes()) {
	    if (getIdsPromocoesSelecionadas() != null && !getIdsPromocoesSelecionadas().isEmpty()) {
		final String idsPromos[] = getIdsPromocoesSelecionadas().split(",");
		for (final String id : idsPromos) {
		    if (promocao.getId().equals(Long.valueOf(id))) {
			listPromosSelecionadas.add(promocao);
		    }
		}
	    }
	}
	return listPromosSelecionadas;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
	return quantidade;
    }

    /**
     * @param pIdLancheSelecionado
     *            the idLancheSelecionado to set
     */
    public void setIdLancheSelecionado(final String pIdLancheSelecionado) {
	idLancheSelecionado = pIdLancheSelecionado;
    }

    /**
     * @param pIdsItemIngredSelecionados
     *            the idsItemIngredSelecionados to set
     */
    public void setIdsItemIngredSelecionados(final String pIdsItemIngredSelecionados) {
	idsItemIngredSelecionados = pIdsItemIngredSelecionados;
    }

    /**
     * @param pIdsPromocoesSelecionadas
     *            the idsPromocoesSelecionadas to set
     */
    public void setIdsPromocoesSelecionadas(final String pIdsPromocoesSelecionadas) {
	idsPromocoesSelecionadas = pIdsPromocoesSelecionadas;
    }

    /**
     * @param pPedido
     *            the pedido to set
     */
    public void setPedido(final Pedido pPedido) {
	pedido = pPedido;
    }

    /**
     * @param pQuantidade
     *            the quantidade to set
     */
    public void setQuantidade(final Integer pQuantidade) {
	quantidade = pQuantidade;
    }
}
