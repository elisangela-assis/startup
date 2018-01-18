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
public class Ingrediente {

    private static Long ID = 1L;

    private static Long getProximoId() {
	return ID++;
    }

    private Long id;
    private String nome;

    /**
     * Valor unitário do ingrediente
     */
    private BigDecimal valor;

    /**
     * Construtor padrão
     */
    Ingrediente() {
	// construtor padrão
    }

    /**
     * Cria o ingrediente com os parâmetros informados
     * 
     * @param pNome
     * @param pValor
     * @throws StartupException
     */
    public Ingrediente(final String pNome, final BigDecimal pValor) throws StartupException {
	Validate.notEmpty(pNome, "O nome do ingrediente é obrigatório!");
	validarObrigatoriedadeValor(pValor);

	nome = pNome;
	valor = pValor;
	id = getProximoId();
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
	final Ingrediente other = (Ingrediente) pObj;
	if (id == null) {
	    if (other.id != null) {
		return false;
	    }
	} else if (!id.equals(other.id)) {
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
     * @return the nome
     */
    public String getNome() {
	return nome;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
	return valor;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    /**
     * Valida o valor informado e se ele for nulo ou menor do que zero lança uma exceção
     * 
     * @param pValor
     * @throws StartupException
     */
    private void validarObrigatoriedadeValor(final BigDecimal pValor) throws StartupException {

	if (pValor == null || pValor.compareTo(BigDecimal.ZERO) < 0) {
	    throw new StartupException("O valor do ingrediente deve ser maior ou igual a zero!");
	}
    }
}
