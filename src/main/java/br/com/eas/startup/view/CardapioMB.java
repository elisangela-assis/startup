/**
 * 
 */
package br.com.eas.startup.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.eas.startup.domain.Lanche;
import br.com.eas.startup.shared.MassaDadosUtil;

/**
 * Classe criada para o bean da página Home
 * 
 * @author Elisangela de Assis da Silva
 */
public class CardapioMB {

    private List<Lanche> cardapio;

    private String idLancheSelecionado;

    private Lanche lancheSelecionado;

    private Map<String, String> imagensLanches;

    private final String IMG_X_BACON = "./images/x-bacon.jpg";

    private final String IMG_X_BURGUER = "./images/x-burguer.jpg";

    private final String IMG_X_EGG = "./images/x-egg.jpg";

    private final String IMG_X_EGG_BACON = "./images/x-egg-bacon.jpg";

    /**
     * 
     */
    public CardapioMB() {
	super();
    }

    /**
     * @return the cardapio
     * @throws Exception
     */
    public List<Lanche> getCardapio() throws Exception {
	if (cardapio == null) {
	    cardapio = MassaDadosUtil.getLanchesCardapio();
	}

	return cardapio;
    }

    /**
     * @return the idLancheSelecionado
     */
    public String getIdLancheSelecionado() {
	return idLancheSelecionado;
    }

    /**
     * @return the imagensLanches
     * @throws Exception
     */
    public Map<String, String> getImagensLanches() throws Exception {
	if (imagensLanches == null) {
	    imagensLanches = new HashMap<String, String>();

	    imagensLanches.put(MassaDadosUtil.getxBacon().getNome(), IMG_X_BACON);
	    imagensLanches.put(MassaDadosUtil.getxBurguer().getNome(), IMG_X_BURGUER);
	    imagensLanches.put(MassaDadosUtil.getxEgg().getNome(), IMG_X_EGG);
	    imagensLanches.put(MassaDadosUtil.getxEggBacon().getNome(), IMG_X_EGG_BACON);
	}
	return imagensLanches;
    }

    /**
     * @return the lancheSelecionado
     * @throws Exception
     */
    public Lanche getLancheSelecionado() throws Exception {
	for (final Lanche lanche : getCardapio()) {
	    if (lanche.getId().equals(getIdLancheSelecionado())) {
		lancheSelecionado = lanche;
	    }
	}
	return lancheSelecionado;
    }

    /**
     * @param pCardapio
     *            the cardapio to set
     */
    public void setCardapio(final List<Lanche> pCardapio) {
	cardapio = pCardapio;
    }

    /**
     * @param pIdLancheSelecionado
     *            the idLancheSelecionado to set
     */
    public void setIdLancheSelecionado(final String pIdLancheSelecionado) {
	idLancheSelecionado = pIdLancheSelecionado;
    }

    /**
     * @param pImagensLanches
     *            the imagensLanches to set
     */
    public void setImagensLanches(final Map<String, String> pImagensLanches) {
	imagensLanches = pImagensLanches;
    }

    /**
     * @param pLancheSelecionado
     *            the lancheSelecionado to set
     */
    public void setLancheSelecionado(final Lanche pLancheSelecionado) {
	lancheSelecionado = pLancheSelecionado;
    }
}
