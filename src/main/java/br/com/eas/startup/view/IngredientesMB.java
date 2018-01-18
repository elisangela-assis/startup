/**
 * 
 */
package br.com.eas.startup.view;

import java.util.ArrayList;
import java.util.List;

import br.com.eas.startup.domain.ItemIngrediente;
import br.com.eas.startup.shared.MassaDadosUtil;
import br.com.eas.startup.shared.exception.StartupException;

/**
 * @author Elisangela de Assis da Silva
 */
public class IngredientesMB {

    /**
     * Parâmetro que indica ao servlet que a ação desejada refere-se aos ingredientes adicionais
     */
    public static final String INGREDIENTES_ADICIONAIS = "ingredientesAdicionais";

    private List<ItemIngrediente> itensIngredientes;

    private String idItemIngredSelecionado;

    private List<ItemIngrediente> itensIngredsSelecionados;

    /**
     * 
     */
    public IngredientesMB() {
	super();
	idItemIngredSelecionado = "";
    }

    /**
     * @return the idItemIngredSelecionado
     */
    public String getIdItemIngredSelecionado() {
	return idItemIngredSelecionado;
    }

    /**
     * @return the itensIngredientes
     * @throws StartupException
     */
    public List<ItemIngrediente> getItensIngredientes() throws StartupException {
	if (itensIngredientes == null || itensIngredientes.isEmpty()) {
	    itensIngredientes = MassaDadosUtil.getItensIngredientes();
	}
	return itensIngredientes;
    }

    /**
     * @return the itensIngredsSelecionados
     * @throws StartupException
     */
    public List<ItemIngrediente> getItensIngredsSelecionados() throws StartupException {

	itensIngredsSelecionados = new ArrayList<ItemIngrediente>();

	if (getIdItemIngredSelecionado() != null && !getIdItemIngredSelecionado().isEmpty()) {
	    final String idsQtds[] = getIdItemIngredSelecionado().split(",");
	    for (final String idQtd : idsQtds) {
		final String itens[] = idQtd.split("=");
		if (itens != null && itens.length > 0) {
		    final ItemIngrediente itemIngred = getItensIngredientes().get(Integer.valueOf(itens[0]));
		    itensIngredsSelecionados.add(new ItemIngrediente(itemIngred.getIngrediente(), Integer.valueOf(itens[1])));
		}
	    }
	}
	return itensIngredsSelecionados;
    }

    /**
     * @param pIdItemIngredSelecionado
     *            the idItemIngredSelecionado to set
     */
    public void setIdItemIngredSelecionado(final String pIdItemIngredSelecionado) {
	idItemIngredSelecionado = pIdItemIngredSelecionado;
    }

    /**
     * @param pItensIngredientes
     *            the itensIngredientes to set
     */
    public void setItensIngredientes(final List<ItemIngrediente> pItensIngredientes) {
	itensIngredientes = pItensIngredientes;
    }

    /**
     * @param pItensIngredsSelecionados
     *            the itensIngredsSelecionados to set
     */
    public void setItensIngredsSelecionados(final List<ItemIngrediente> pItensIngredsSelecionados) {
	itensIngredsSelecionados = pItensIngredsSelecionados;
    }
}
