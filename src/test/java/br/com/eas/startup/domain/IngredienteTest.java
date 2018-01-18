/**
 * 
 */
package br.com.eas.startup.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author Elisangela de Assis da Silva
 */
public class IngredienteTest {

    /**
     * Testa a criação do ingrediente sem o nome
     */
    @Test
    public void testConstrutorIngredienteSemNome() {

	final List<String> nomesInvalidos = Arrays.asList(null, "");

	for (final String nome : nomesInvalidos) {
	    try {
		new Ingrediente(nome, BigDecimal.ONE);
	    } catch (final Exception e) {
		Assert.assertEquals("O nome do ingrediente é obrigatório!", e.getMessage());
	    }
	}
    }

    /**
     * Testa a criação do ingrediente com o valor inválido
     */
    @Test
    public void testConstrutorIngredienteValorInvalido() {

	final List<BigDecimal> valoresTestados = Arrays.asList(null, new BigDecimal(-0.4), BigDecimal.ZERO);

	for (final BigDecimal valor : valoresTestados) {
	    boolean lancouExcecao = false;
	    try {
		new Ingrediente("Alface", valor);
	    } catch (final Exception e) {
		lancouExcecao = true;
		Assert.assertEquals("O valor do ingrediente deve ser maior ou igual a zero!", e.getMessage());
	    }

	    if (!lancouExcecao) {
		Assert.assertEquals(BigDecimal.ZERO, valor);
	    }
	}
    }
}
