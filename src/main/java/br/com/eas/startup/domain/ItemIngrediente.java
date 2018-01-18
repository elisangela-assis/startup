/**
 * 
 */
package br.com.eas.startup.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;

import br.com.eas.startup.shared.exception.StartupException;

/**
 * @author Elisangela de Assis da Silva
 */
public class ItemIngrediente {

    private static Long ID = 1L;

    private static Long getProximoId() {
	return ID++;
    }

    private Long id;

    /**
     * Valor unitário do ingrediente
     */
    private BigDecimal valorUnitario;

    /**
     * Valor total do ingrediente
     */
    private BigDecimal valorTotal;

    /**
     * Ingrediente relacionado ao item
     */
    private Ingrediente ingrediente;

    /**
     * Quantidade do ingrediente
     */
    private Integer quantidade;

    /**
     * Cria o item com o ingrediente e a sua quantidade de acordo com os parâmetros informados
     * 
     * @param pIngrediente
     * @param pQuantidade
     * 
     * @throws StartupException
     */
    public ItemIngrediente(final Ingrediente pIngrediente, final Integer pQuantidade) throws StartupException {
	Validate.notNull(pIngrediente, "O ingrediente é obrigatório!");
	validarObrigatoriedadeQuantidade(pQuantidade);

	ingrediente = pIngrediente;
	quantidade = pQuantidade;
	id = getProximoId();
	calcularValorTotalIngrediente(ingrediente, quantidade);
    }

    /**
     * Calcula o valor total do ingrediente de acordo com a quantidade
     * 
     * @param pQuantidade
     */
    private void calcularValorTotalIngrediente(final Ingrediente pIngrediente, final Integer pQuantidade) {
	if (pIngrediente != null) {
	    valorUnitario = pIngrediente.getValor();
	    valorTotal = valorUnitario.multiply(BigDecimal.valueOf(pQuantidade));
	}
    }

    @Override
    public boolean equals(final Object pObj) {
	if (this == pObj) {
	    return true;
	}
	if (pObj == null) {
	    return false;
	}
	if (getClass() != pObj.getClass()) {
	    return false;
	}
	final ItemIngrediente other = (ItemIngrediente) pObj;
	if (ingrediente == null) {
	    if (other.ingrediente != null) {
		return false;
	    }
	} else if (!ingrediente.equals(other.ingrediente)) {
	    return false;
	}
	return true;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @return the ingrediente
     */
    public Ingrediente getIngrediente() {
	return ingrediente;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
	return quantidade;
    }

    /**
     * @return the valorTotal
     */
    public BigDecimal getValorTotal() {
	return valorTotal;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValorUnitario() {
	return valorUnitario;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((ingrediente == null) ? 0 : ingrediente.hashCode());
	return result;
    }

    /**
     * @param pQuantidade
     *            the quantidade to set
     * @throws StartupException
     */
    public void setQuantidade(final Integer pQuantidade) throws StartupException {
	validarObrigatoriedadeQuantidade(pQuantidade);
	calcularValorTotalIngrediente(ingrediente, pQuantidade);
	quantidade = pQuantidade;
    }

    /**
     * Valida a obrigatoriedade da quantidade
     * 
     * @param pQuantidade
     * @throws StartupException
     */
    private void validarObrigatoriedadeQuantidade(final Integer pQuantidade) throws StartupException {

	if (pQuantidade == null || pQuantidade < 0) {
	    throw new StartupException("A quantidade do ingrediente deve ser um valor maior do que zero!");
	}
    }

}
