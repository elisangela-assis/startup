/**
 * 
 */
package br.com.eas.startup.shared;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.eas.startup.domain.Ingrediente;
import br.com.eas.startup.domain.ItemIngrediente;
import br.com.eas.startup.domain.Lanche;
import br.com.eas.startup.domain.Promocao;
import br.com.eas.startup.shared.exception.StartupException;

/**
 * @author Elisangela de Assis da Silva
 */
public class MassaDadosUtil {

    private static final String SOLICITANTE = "UserTeste";

    /**
     * ***********************************************************
     * 
     * Ingredientes
     * 
     * ***********************************************************
     */
    private static Ingrediente alface;

    private static Ingrediente bacon;

    private static Ingrediente hamburguerCarne;

    private static Ingrediente ovo;

    private static Ingrediente queijo;

    private static ItemIngrediente itemIngredAlface;

    private static ItemIngrediente itemIngredBacon;

    private static ItemIngrediente itemIngredHambuguerCarne;

    private static ItemIngrediente itemIngredOvo;

    private static ItemIngrediente itemIngredQueijo;

    private static List<ItemIngrediente> itensIngredientes;

    /**
     * ***********************************************************
     * 
     * Lanches do cardápio
     * 
     * ***********************************************************
     */

    private static List<Lanche> lanchesCardapio;

    /**
     * X-Bacon<br>
     * Bacon, hambúrguer de carne e queijo<br>
     */
    private static Lanche xBacon;

    /**
     * X-Burger<br>
     * Hambúrguer de carne e queijo<br>
     */
    private static Lanche xBurguer;

    /**
     * X-Egg<br>
     * Ovo, hambúrguer de carne e queijo<br>
     */
    private static Lanche xEgg;

    /**
     * X-Egg Bacon<br>
     * Ovo, bacon, hambúrguer de carne e queijo<br>
     */
    private static Lanche xEggBacon;

    /**
     * Se o lanche tem alface e não tem bacon, ganha 10% de desconto.
     */
    private static Promocao light;

    /**
     * A cada 3 porções de carne o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante...
     */
    private static Promocao muitaCarne;

    /**
     * A cada 3 porções de queijo o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante...
     */
    private static Promocao muitoQueijo;

    /**
     * ***********************************************************
     * 
     * Promoções
     * 
     * ***********************************************************
     */

    private static List<Promocao> promocoes;

    /**
     * @return the alface
     * @throws StartupException
     */
    public static Ingrediente getAlface() throws StartupException {
	if (alface == null) {
	    alface = new Ingrediente("Alface", new BigDecimal(0.4));
	}
	return alface;
    }

    /**
     * @return the bacon
     * @throws StartupException
     */
    public static Ingrediente getBacon() throws StartupException {
	if (bacon == null) {
	    bacon = new Ingrediente("Bacon", new BigDecimal(2.0));
	}
	return bacon;
    }

    /**
     * @return the hamburguerCarne
     * @throws StartupException
     */
    public static Ingrediente getHamburguerCarne() throws StartupException {
	if (hamburguerCarne == null) {
	    hamburguerCarne = new Ingrediente("Hambúrguer de carne", new BigDecimal(3.0));
	}
	return hamburguerCarne;
    }

    /**
     * @return the itemIngredAlface
     * @throws StartupException
     */
    public static ItemIngrediente getItemIngredAlface() throws StartupException {
	if (itemIngredAlface == null) {
	    itemIngredAlface = new ItemIngrediente(getAlface(), 0);
	}
	return itemIngredAlface;
    }

    /**
     * @return the itemIngredBacon
     * @throws StartupException
     */
    public static ItemIngrediente getItemIngredBacon() throws StartupException {
	if (itemIngredBacon == null) {
	    itemIngredBacon = new ItemIngrediente(getBacon(), 0);
	}
	return itemIngredBacon;
    }

    /**
     * @return the itemIngredHambuguerCarne
     * @throws StartupException
     */
    public static ItemIngrediente getItemIngredHambuguerCarne() throws StartupException {
	if (itemIngredHambuguerCarne == null) {
	    itemIngredHambuguerCarne = new ItemIngrediente(getHamburguerCarne(), 0);
	}
	return itemIngredHambuguerCarne;
    }

    /**
     * @return the itemIngredOvo
     * @throws StartupException
     */
    public static ItemIngrediente getItemIngredOvo() throws StartupException {
	if (itemIngredOvo == null) {
	    itemIngredOvo = new ItemIngrediente(getOvo(), 0);
	}
	return itemIngredOvo;
    }

    /**
     * @return the itemIngredQueijo
     * @throws StartupException
     */
    public static ItemIngrediente getItemIngredQueijo() throws StartupException {
	if (itemIngredQueijo == null) {
	    itemIngredQueijo = new ItemIngrediente(getQueijo(), 0);
	}
	return itemIngredQueijo;
    }

    /**
     * @return the itensIngredientes
     * @throws StartupException
     */
    public static List<ItemIngrediente> getItensIngredientes() throws StartupException {
	if (itensIngredientes == null) {
	    itensIngredientes = new ArrayList<ItemIngrediente>();

	    itensIngredientes.add(getItemIngredAlface());
	    itensIngredientes.add(getItemIngredBacon());
	    itensIngredientes.add(getItemIngredHambuguerCarne());
	    itensIngredientes.add(getItemIngredOvo());
	    itensIngredientes.add(getItemIngredQueijo());
	}
	return itensIngredientes;
    }

    /**
     * Lista de todos os lanches do cardápio
     * 
     * @return the lanchesCardapio
     * @throws StartupException
     */
    public static List<Lanche> getLanchesCardapio() throws StartupException {
	if (lanchesCardapio == null) {
	    lanchesCardapio = new ArrayList<Lanche>();
	    lanchesCardapio.add(getxBacon());
	    lanchesCardapio.add(getxBurguer());
	    lanchesCardapio.add(getxEgg());
	    lanchesCardapio.add(getxEggBacon());
	}

	return lanchesCardapio;
    }

    /**
     * Se o lanche tem alface e não tem bacon, ganha 10% de desconto.
     * 
     * @return the light
     * @throws StartupException
     */
    public static Promocao getLight() throws StartupException {

	if (light == null) {
	    final Set<Ingrediente> ingredientesExcecao = new HashSet<Ingrediente>();

	    ingredientesExcecao.add(getBacon());

	    light = new Promocao("Light", getAlface());
	    light.setPercentualDesconto(BigDecimal.TEN);
	    light.setIngredientesExcecao(ingredientesExcecao);
	    light.setDescricao("Ganhe 10% de desconto nos lanches com alface e que não têm bacon.");
	}

	return light;
    }

    /**
     * A cada 3 porções de carne o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante...
     * 
     * @return the muitaCarne
     * @throws StartupException
     */
    public static Promocao getMuitaCarne() throws StartupException {
	if (muitaCarne == null) {
	    muitaCarne = new Promocao("Muita carne", getHamburguerCarne());
	    muitaCarne.setQuantidade(3);
	    muitaCarne.setQuantidadePagante(2);
	    muitaCarne.setDescricao("A cada 3 porções de carne pague apenas 2.");
	}

	return muitaCarne;
    }

    /**
     * A cada 3 porções de queijo o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante...
     * 
     * @return the muitoQueijo
     * @throws StartupException
     */
    public static Promocao getMuitoQueijo() throws StartupException {
	if (muitoQueijo == null) {
	    muitoQueijo = new Promocao("Muito queijo", getQueijo());
	    muitoQueijo.setQuantidade(3);
	    muitoQueijo.setQuantidadePagante(2);
	    muitoQueijo.setDescricao("A cada 3 porções de queijo pague apenas 2.");
	}

	return muitoQueijo;
    }

    /**
     * @return the ovo
     * @throws StartupException
     */
    public static Ingrediente getOvo() throws StartupException {
	if (ovo == null) {
	    ovo = new Ingrediente("Ovo", new BigDecimal(0.8));
	}
	return ovo;
    }

    /**
     * @return the promocoes
     * @throws StartupException
     */
    public static List<Promocao> getPromocoes() throws StartupException {
	if (promocoes == null) {
	    promocoes = new ArrayList<Promocao>();
	    promocoes.add(getLight());
	    promocoes.add(getMuitaCarne());
	    promocoes.add(getMuitoQueijo());
	}

	return promocoes;
    }

    /**
     * @return the queijo
     * @throws StartupException
     */
    public static Ingrediente getQueijo() throws StartupException {
	if (queijo == null) {
	    queijo = new Ingrediente("Queijo", new BigDecimal(1.5));
	}
	return queijo;
    }

    /**
     * @return the solicitante
     */
    public static String getSolicitante() {
	return SOLICITANTE;
    }

    /**
     * X-Bacon<br>
     * Bacon, hambúrguer de carne e queijo<br>
     * 
     * @return the xBacon
     * @throws StartupException
     */
    public static Lanche getxBacon() throws StartupException {

	if (xBacon == null) {
	    final Set<ItemIngrediente> itensIngreds = new HashSet<ItemIngrediente>();

	    itensIngreds.add(new ItemIngrediente(getBacon(), 1));
	    itensIngreds.add(new ItemIngrediente(getHamburguerCarne(), 1));
	    itensIngreds.add(new ItemIngrediente(getQueijo(), 1));

	    xBacon = new Lanche(itensIngreds, SimNao.N);
	    xBacon.setNome("X-Bacon");
	    xBacon.setDescricao("Bacon, hambúrguer de carne e queijo");
	}

	return xBacon;
    }

    /**
     * X-Burger<br>
     * Hambúrguer de carne e queijo<br>
     * 
     * @return the xBurguer
     * @throws StartupException
     */
    public static Lanche getxBurguer() throws StartupException {
	if (xBurguer == null) {
	    final Set<ItemIngrediente> itensIngreds = new HashSet<ItemIngrediente>();

	    itensIngreds.add(new ItemIngrediente(getHamburguerCarne(), 1));
	    itensIngreds.add(new ItemIngrediente(getQueijo(), 1));

	    xBurguer = new Lanche(itensIngreds, SimNao.N);
	    xBurguer.setNome("X-Burger");
	    xBurguer.setDescricao("Hambúrguer de carne e queijo");
	}

	return xBurguer;
    }

    /**
     * X-Egg<br>
     * Ovo, hambúrguer de carne e queijo<br>
     * 
     * @return the xEgg
     * @throws StartupException
     */
    public static Lanche getxEgg() throws StartupException {
	if (xEgg == null) {
	    final Set<ItemIngrediente> ingredientes = new HashSet<ItemIngrediente>();

	    ingredientes.add(new ItemIngrediente(getOvo(), 1));
	    ingredientes.add(new ItemIngrediente(getHamburguerCarne(), 1));
	    ingredientes.add(new ItemIngrediente(getQueijo(), 1));

	    xEgg = new Lanche(ingredientes, SimNao.N);
	    xEgg.setNome("X-Egg");
	    xEgg.setDescricao("Ovo, hambúrguer de carne e queijo");
	}

	return xEgg;
    }

    /**
     * X-Egg Bacon<br>
     * Ovo, bacon, hambúrguer de carne e queijo<br>
     * 
     * @return the xEggBacon
     * @throws StartupException
     */
    public static Lanche getxEggBacon() throws StartupException {

	if (xEggBacon == null) {
	    final Set<ItemIngrediente> ingredientes = new HashSet<ItemIngrediente>();

	    ingredientes.add(new ItemIngrediente(getOvo(), 1));
	    ingredientes.add(new ItemIngrediente(getBacon(), 1));
	    ingredientes.add(new ItemIngrediente(getHamburguerCarne(), 1));
	    ingredientes.add(new ItemIngrediente(getQueijo(), 1));

	    xEggBacon = new Lanche(ingredientes, SimNao.N);
	    xEggBacon.setNome("X-Egg Bacon");
	    xEggBacon.setDescricao("Ovo, bacon, hambúrguer de carne e queijo");
	}

	return xEggBacon;
    }

}
