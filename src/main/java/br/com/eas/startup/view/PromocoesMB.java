/**
 * 
 */
package br.com.eas.startup.view;

import java.util.ArrayList;
import java.util.List;

import br.com.eas.startup.domain.Promocao;
import br.com.eas.startup.shared.MassaDadosUtil;

/**
 * Bean de promoções
 * 
 * @author Elisangela de Assis da Silva
 */
public class PromocoesMB {

    /**
     * Identificador do parâmetro que possui a promoção selecionada
     */
    public static final String PROMOCAO_PARAMETER = "promocao";

    /**
     * Identificador do parâmetro que recuperará as promoções selecionadas no submit
     */
    public static final String VALIDAR_PROMOCAO_SUBMIT_PARAMETER = "validarPromocaoSubmit";

    private List<Promocao> promocoes;

    private String promocoesSelecionadas;

    private List<Promocao> listPromosSelecionadas;

    /**
     * 
     */
    public PromocoesMB() {
	super();
	promocoesSelecionadas = "";
    }

    /**
     * Lista de promoções selecionadas
     * 
     * @return the listPromosSelecionadas
     * @throws Exception
     * @throws NumberFormatException
     */
    public List<Promocao> getListPromosSelecionadas() throws NumberFormatException, Exception {
	listPromosSelecionadas = new ArrayList<Promocao>();

	for (final Promocao promocao : getPromocoes()) {
	    if (getPromocoesSelecionadas() != null && !getPromocoesSelecionadas().isEmpty()) {

		final String idsPromos[] = getPromocoesSelecionadas().split(",");

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
     * @return the promocoes
     * @throws Exception
     */
    public List<Promocao> getPromocoes() throws Exception {
	if (promocoes == null) {
	    promocoes = MassaDadosUtil.getPromocoes();
	}
	return promocoes;
    }

    /**
     * @return the promocoesSelecionadas
     */
    public String getPromocoesSelecionadas() {
	return promocoesSelecionadas;
    }

    /**
     * @param pListPromosSelecionadas
     *            the listPromosSelecionadas to set
     */
    public void setListPromosSelecionadas(final List<Promocao> pListPromosSelecionadas) {
	listPromosSelecionadas = pListPromosSelecionadas;
    }

    /**
     * @param pPromocoes
     *            the promocoes to set
     */
    public void setPromocoes(final List<Promocao> pPromocoes) {
	promocoes = pPromocoes;
    }

    /**
     * @param pPromocoesSelecionadas
     *            the promocoesSelecionadas to set
     */
    public void setPromocoesSelecionadas(final String pPromocoesSelecionadas) {
	promocoesSelecionadas = pPromocoesSelecionadas;
    }
}
