/**
 * 
 */
package br.com.eas.startup.domain;

import java.math.BigDecimal;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import br.com.eas.startup.shared.exception.StartupException;

/**
 * @author Elisangela de Assis da Silva
 */
public class Promocao {

    private static Long ID = 1L;

    private static Long getProximoId() {
	return ID++;
    }

    /**
     * Identificador único e sequencial
     */
    private Long id;

    /**
     * Nome da promoção
     */
    private String nome;

    /**
     * Descrição da promoção
     */
    private String descricao;

    /**
     * Percentual do desconto a ser concedido no pedido
     */
    private BigDecimal percentualDesconto;

    /**
     * Quantidade de ingredientes que pode ter no pedido
     */
    private Integer quantidade;

    /**
     * Quantidade de ingredientes que serão cobradas no pedido
     */
    private Integer quantidadePagante;

    /**
     * Ingrediente que poderá ter desconto
     */
    private Ingrediente ingredientePromocao;

    /**
     * Ingredientes que não poderão ter no lanche para a promoção ser aplicada
     */
    private Set<Ingrediente> ingredientesExcecao;

    /**
     * Construtor com os atributos obrigatórios
     * 
     * @param pNome
     * @param pIngrediente
     */
    public Promocao(final String pNome, final Ingrediente pIngrediente) {

	Validate.notEmpty(pNome, "O nome da promoção é obrigatório!");
	Validate.notNull(pIngrediente, "O ingrediente é obrigatório!");

	nome = pNome;
	ingredientePromocao = pIngrediente;
	id = getProximoId();
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
	return descricao;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @return the ingredientePromocao
     */
    public Ingrediente getIngredientePromocao() {
	return ingredientePromocao;
    }

    /**
     * @return the ingredientesExcecao
     */
    public Set<Ingrediente> getIngredientesExcecao() {
	return ingredientesExcecao;
    }

    /**
     * @return the nome
     */
    public String getNome() {
	return nome;
    }

    /**
     * @return the percentualDesconto
     */
    public BigDecimal getPercentualDesconto() {
	return percentualDesconto;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
	return quantidade;
    }

    /**
     * @return the quantidadePagante
     */
    public Integer getQuantidadePagante() {
	return quantidadePagante;
    }

    /**
     * @param pDescricao
     *            the descricao to set
     */
    public void setDescricao(final String pDescricao) {
	descricao = pDescricao;
    }

    /**
     * Define os ingredientes que não poderão ter no lanche para a promoção ser aplicada
     * 
     * @param pIngredientesExcecao
     *            the ingredientesExcecao to set
     * @throws StartupException
     */
    public void setIngredientesExcecao(final Set<Ingrediente> pIngredientesExcecao) throws StartupException {
	validarIngredientesExcecao(pIngredientesExcecao);
	ingredientesExcecao = pIngredientesExcecao;
    }

    /**
     * Define o percentual do desconto a ser aplicado no lanche
     * 
     * @param pPercentualDesconto
     *            the percentualDesconto to set
     * @throws StartupException
     */
    public void setPercentualDesconto(final BigDecimal pPercentualDesconto) throws StartupException {

	if (pPercentualDesconto == null || pPercentualDesconto.compareTo(BigDecimal.ZERO) < 0) {
	    throw new StartupException("O valor do desconto deve ser um valor maior ou igual a zero!");
	}

	percentualDesconto = pPercentualDesconto;
    }

    /**
     * Define a quantidade de ingredientes que pode ter no pedido para a promoção ser aplicada
     * 
     * @param pQuantidade
     *            the quantidade to set
     * @throws StartupException
     */
    public void setQuantidade(final Integer pQuantidade) throws StartupException {
	if (pQuantidade == null || pQuantidade < 0) {
	    throw new StartupException("A quantidade de ingredientes deve ser maior ou igual a zero!");
	}
	quantidade = pQuantidade;
    }

    /**
     * Define a quantidade de ingredientes que será cobrada no pedido
     * 
     * @param pQuantidadePagante
     *            the quantidadePagante to set
     * @throws StartupException
     */
    public void setQuantidadePagante(final Integer pQuantidadePagante) throws StartupException {
	if (pQuantidadePagante == null || pQuantidadePagante < 0) {
	    throw new StartupException("A quantidade de ingredientes pagantes deve ser maior ou igual a zero!");
	}
	quantidadePagante = pQuantidadePagante;
    }

    /**
     * Valida os ingredientes que não poderão ter no lanche
     * 
     * @param pIngredientesExcecao
     * @throws StartupException
     */
    private void validarIngredientesExcecao(final Set<Ingrediente> pIngredientesExcecao) throws StartupException {

	if (pIngredientesExcecao == null || pIngredientesExcecao.isEmpty()) {
	    throw new StartupException("Os ingredientes são obrigatórios!");
	}

	for (final Ingrediente ingrediente : pIngredientesExcecao) {
	    if (ingrediente == null) {
		throw new StartupException("O ingrediente não pode ser nulo!");
	    }
	}
    }
}
