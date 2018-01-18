/**
 * 
 */
package br.com.eas.startup.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import br.com.eas.startup.shared.MassaDadosUtil;
import br.com.eas.startup.shared.SimNao;

/**
 * @author Elisangela de Assis da Silva
 */
public class LancheTest {

    /**
     * Dado que eu crie um lanche X-Bacon do cardápio com apenas um ingrediente<br>
     * Quando eu buscar o seu valor<br>
     * Então o resultado será o valor desse único ingrediente<br>
     * 
     * @throws Exception
     */
    @Test
    public void testConstrutorLancheCardapioUmIngrediente() throws Exception {
	System.out.println("Dado que eu tenha um lanche X-Bacon do cardápio com apenas um ingrediente");

	final Set<ItemIngrediente> itensIngredientes = new HashSet<ItemIngrediente>();
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getBacon(), 1));

	final Lanche xBacon = new Lanche(itensIngredientes, SimNao.N);

	System.out.println("Quando eu buscar o seu valor");
	System.out.println("Então o resultado será o valor desse único ingrediente");
	Assert.assertEquals(MassaDadosUtil.getBacon().getValor(), xBacon.getValorTotal());
    }

    /**
     * Dado que eu crie um lanche X-Bacon do cardápio com vários ingredientes<br>
     * Quando eu buscar o seu valor<br>
     * Então o resultado será a soma de todos os valores dos ingredientes<br>
     * 
     * @throws Exception
     */
    @Test
    public void testConstrutorLancheCardapioVariosIngredientes() throws Exception {
	System.out.println("Dado que eu crie um lanche X-Bacon do cardápio com vários ingredientes");

	final Set<ItemIngrediente> itensIngredientes = new HashSet<ItemIngrediente>();
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getBacon(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getHamburguerCarne(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getQueijo(), 1));

	final Lanche xBacon = new Lanche(itensIngredientes, SimNao.N);

	System.out.println("Quando eu buscar o seu valor");
	BigDecimal valorLanche = BigDecimal.ZERO;

	for (final ItemIngrediente ingr : itensIngredientes) {
	    valorLanche = valorLanche.add(ingr.getValorTotal());
	}

	System.out.println("Então o resultado será a soma de todos os valores dos ingredientes");
	Assert.assertEquals(valorLanche, xBacon.getValorTotal());
    }

    /**
     * Dado que eu construa um lanche com uma lista de ingredientes nula ou vazia<br>
     * Então não será possível e uma exceção será lançada<br>
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testConstrutorLancheIngredientesInvalidos() {
	System.out.println("Dado que eu construa um lanche com uma lista de ingredientes nula ou vazia");
	System.out.println("Então não será possível e uma exceção será lançada");

	final List<HashSet<ItemIngrediente>> ingrList = Arrays.asList(null, new HashSet<ItemIngrediente>());

	for (final Set<ItemIngrediente> ingrTree : ingrList) {
	    try {
		new Lanche(ingrTree, SimNao.N);
	    } catch (final Exception e) {
		Assert.assertEquals("Os ingredientes são obrigatórios!", e.getMessage());
	    }
	}
    }

    /**
     * Dado que eu crie um lanche personalizado com vários ingredientes<br>
     * Quando eu buscar o seu valor<br>
     * Então o resultado será a soma de todos os valores dos ingredientes<br>
     * 
     * @throws Exception
     */
    @Test
    public void testConstrutorLanchePersonalizadoVariosIngredientes() throws Exception {
	System.out.println("Dado que eu crie um lanche personalizado com vários ingredientes");

	final Set<ItemIngrediente> itensIngredientes = new HashSet<ItemIngrediente>();
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getAlface(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getHamburguerCarne(), 1));
	itensIngredientes.add(new ItemIngrediente(MassaDadosUtil.getQueijo(), 1));

	final Lanche lanchePersonalizado = new Lanche(itensIngredientes, SimNao.N);

	System.out.println("Quando eu buscar o seu valor");
	BigDecimal valorLanche = BigDecimal.ZERO;

	for (final ItemIngrediente ingr : itensIngredientes) {
	    valorLanche = valorLanche.add(ingr.getValorTotal());
	}

	System.out.println("Então o resultado será a soma de todos os valores dos ingredientes");
	Assert.assertEquals(valorLanche, lanchePersonalizado.getValorTotal());
    }
}
