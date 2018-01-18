package br.com.eas.startup.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import br.com.eas.startup.shared.SimNao;
import br.com.eas.startup.shared.exception.StartupException;

/**
 * @author Elisangela de Assis da Silva
 */
public class Lanche {

    private static Long ID = 1L;

    private static Long getProximoId() {
	return ID++;
    }

    /**
     * Identificador unico e sequencial
     */
    private Long id;

    /**
     * Nome do lanche
     */
    private String nome;

    /**
     * descrição do conteúdo do lanche
     */
    private String descricao;

    /**
     * Armazena todos os ingredientes do lanche
     */
    private Set<ItemIngrediente> itensIngredientes;

    /**
     * Indica se o lanche tem ingredientes personalizados
     */
    private SimNao indPersonalizado;

    /**
     * Indica se o lanche tem alguma promocao
     */
    private SimNao indPromocao;

    /**
     * Promoção cadastrada para o lanche
     */
    private Promocao promocao;

    /**
     * Valor dos itens do lanche
     */
    private BigDecimal valorItens;

    /**
     * Valor total do lanche incluindo promocao
     */
    private BigDecimal valorTotal;

    /**
     * Cria um lanche com a lista de ingredientes previamente definida
     * 
     * @param pItensIngredientes
     * @param pPersonalizado
     */
    public Lanche(final Set<ItemIngrediente> pItensIngredientes, final SimNao pPersonalizado) {
	Validate.notEmpty(pItensIngredientes, "Os ingredientes são obrigatórios!");

	itensIngredientes = pItensIngredientes;
	indPersonalizado = pPersonalizado;
	id = getProximoId();
    }

    /**
     * Adiciona um ingrediente ao lanche<br>
     * Se o ingrediente já existe no lanche, então, altera-se a sua quantidade. Em caso contrário o item será adicionado com a quantidade informada<br>
     * 
     * @param pItemIngrediente
     * @param pQuantidade
     * @throws StartupException
     */
    public void adicionarItemIngrediente(final ItemIngrediente pItemIngrediente, final Integer pQuantidade)
	    throws StartupException {
	Validate.notNull(pItemIngrediente, "O ingrediente é obrigatório");
	validarObrigatoriedadeQuantidade(pQuantidade);

	if (!itensIngredientes.contains(pItemIngrediente)) {
	    itensIngredientes.add(pItemIngrediente);
	} else {
	    for (final ItemIngrediente ingred : itensIngredientes) {
		final Integer qtdAjustada = ingred.getQuantidade() + pQuantidade;
		if (ingred.equals(pItemIngrediente)) {
		    ingred.setQuantidade(qtdAjustada);
		}
	    }
	}
    }

    /**
     * Calcula a quantidade que deve ser adicionada ao lanche para aplicar a promoção.<br>
     * Usa o resto da divisão entre a qtd que está no lanche e a qtd cadastrada da promoção<br>
     * 
     * @param pPromocao
     * @return quantidade a ser ajustada no lanche
     */
    private Integer calcularQtdAjusteIngredienteLancheDevidoPromocao(final Promocao pPromocao) {

	Integer qtdAjuste = 0;

	if (pPromocao.getQuantidade() != null && pPromocao.getQuantidade() > 0) {
	    final Ingrediente ingredPromocao = pPromocao.getIngredientePromocao();

	    for (final ItemIngrediente item : getIngredientes()) {
		if (item.getIngrediente().equals(ingredPromocao)) {
		    final Integer resto = item.getQuantidade() % pPromocao.getQuantidade();
		    final Integer quociente = item.getQuantidade() / pPromocao.getQuantidade();

		    if (quociente == 0 && resto > 0) {
			qtdAjuste = pPromocao.getQuantidade() - resto;
		    }
		}
	    }
	}
	return qtdAjuste;
    }

    /**
     * Calcula a quantidade que deve ser paga aplicando-se a promocao sobre um determinado lanche e ingrediente.<br>
     * A quantidade pagante é determinada pela fórmula:<br>
     * qtdPagante = resto + quociente * pQtdIngredPagantePromocao<br>
     * 
     * @param pQtdIngredLanche
     * @param pQtdIngredPromocao
     * @param pQtdIngredPagantePromocao
     */
    private Integer calcularQtdIngredPagantePromocao(final Integer pQtdIngredLanche, final Integer pQtdIngredPromocao,
	    final Integer pQtdIngredPagantePromocao) {

	Integer qtdPagante, resto, quociente = 0;

	resto = pQtdIngredLanche % pQtdIngredPromocao;
	quociente = pQtdIngredLanche / pQtdIngredPromocao;
	qtdPagante = resto + (quociente * pQtdIngredPagantePromocao);

	return qtdPagante;
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
     * @return se o lanche tem promocao
     */
    public SimNao getIndPromocao() {
	return indPromocao;
    }

    /**
     * @return the ingredientes
     */
    public Set<ItemIngrediente> getIngredientes() {
	return Collections.unmodifiableSet(itensIngredientes);
    }

    /**
     * @return the nome
     */
    public String getNome() {
	return nome;
    }

    /**
     * @return se o lanche � personalizado
     */
    public SimNao getPersonalizado() {
	return indPersonalizado;
    }

    /**
     * @return the promocao
     */
    public Promocao getPromocao() {
	return promocao;
    }

    /**
     * Busca a quantidade de um dado ingrediente no lanche
     * 
     * @param pItemIngrediente
     * @return a quantidade de um ingrediente
     */
    public Integer getQtdDoItemIngrediente(final ItemIngrediente pItemIngrediente) {
	Validate.notNull(pItemIngrediente);

	Integer quantidade = 0;
	for (final ItemIngrediente ingred : itensIngredientes) {
	    if (pItemIngrediente.equals(ingred)) {
		quantidade = ingred.getQuantidade();
		break;
	    }
	}
	return quantidade;
    }

    /**
     * Calcula o valor total do lanche através da soma de todos os ingredientes
     * 
     * @return valor total do lanche
     * @throws StartupException
     */
    public BigDecimal getValorTotal() throws StartupException {

	valorItens = BigDecimal.ZERO;
	valorTotal = BigDecimal.ZERO;

	for (final ItemIngrediente ingrediente : itensIngredientes) {
	    valorItens = valorItens.add(ingrediente.getValorTotal());
	}

	if (promocao != null && SimNao.S == indPromocao) {

	    if (isIngredienteNoLanche(promocao.getIngredientePromocao())) {
		validaIngredientesExcecao(promocao);

		BigDecimal valorDesconto = BigDecimal.ZERO;

		// Promoção light
		if (promocao.getPercentualDesconto() != null && promocao.getPercentualDesconto().compareTo(BigDecimal.ZERO) == 1)
		{
		    valorDesconto = (valorItens.multiply(promocao.getPercentualDesconto())).divide(BigDecimal.valueOf(100));
		}

		// Promoção Muita Carne ou Muito Queijo
		if (promocao.getQuantidade() != null && promocao.getQuantidade() > 0) {

		    Integer qtdIngredPagante = 0;

		    // Busco a qtd de ingredientes a ser paga quando aplicada a promocao e então concede-se desconto sobre o ingrediente
		    for (final ItemIngrediente itemIngrediente : itensIngredientes) {

			if (itemIngrediente.getIngrediente().equals(promocao.getIngredientePromocao())) {
			    qtdIngredPagante = calcularQtdIngredPagantePromocao(itemIngrediente.getQuantidade(),
				    promocao.getQuantidade(), promocao.getQuantidadePagante());
			    valorDesconto = itemIngrediente.getValorTotal().subtract(
				    itemIngrediente.getValorUnitario().multiply(BigDecimal.valueOf(qtdIngredPagante)));
			    break;
			}
		    }
		}
		valorTotal = valorItens.subtract(valorDesconto);
	    }
	} else {
	    valorTotal = valorItens;
	}

	valorTotal.setScale(2, RoundingMode.HALF_EVEN);
	return valorTotal;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    /**
     * @return true se o lanche já possuir o ingrediente
     */
    private boolean isIngredienteNoLanche(final Ingrediente pIngrediente) {
	boolean valida = false;

	for (final ItemIngrediente itemIngred : itensIngredientes) {
	    if (itemIngred.getIngrediente().equals(pIngrediente)) {
		valida = true;
		break;
	    }
	}

	return valida;
    }

    /**
     * @param pDescricao
     *            the descricao to set
     */
    public void setDescricao(final String pDescricao) {
	Validate.notEmpty(pDescricao, "A descrição é obrigatória!");
	descricao = pDescricao;
    }

    /**
     * @param pNome
     *            the nome to set
     */
    public void setNome(final String pNome) {
	Validate.notEmpty(pNome, "O nome é obrigatório!");
	nome = pNome;
    }

    /**
     * Define a promocao que o lanche pertence
     * 
     * @param pPromocao
     *            the promocao to set
     * @throws StartupException
     */
    public void setPromocao(final Promocao pPromocao) throws StartupException {

	if (pPromocao == null) {
	    throw new StartupException("A promocao é obrigatória!");
	}

	validaAplicacaoPromocao(pPromocao);
	validaIngredientesExcecao(pPromocao);

	final Integer qtdAjusteIngrediente = calcularQtdAjusteIngredienteLancheDevidoPromocao(pPromocao);

	// ajusta a quantidade do item ingrediente
	if (qtdAjusteIngrediente > 0) {
	    ItemIngrediente itemLancheAjuste = null;
	    for (final ItemIngrediente item : getIngredientes()) {
		if (item.getIngrediente().equals(pPromocao.getIngredientePromocao())) {
		    itemLancheAjuste = item;
		    break;
		}
	    }
	    adicionarItemIngrediente(itemLancheAjuste, qtdAjusteIngrediente);
	}

	promocao = pPromocao;
	indPromocao = SimNao.S;
    }

    /**
     * A promoção só poderá ser aplicada se o lanche possuir o ingrediente da promoção. Se não possuir o ingrediente uma exceção será lançada.
     * 
     * @param pPromocao
     * @throws StartupException
     */
    private void validaAplicacaoPromocao(final Promocao pPromocao) throws StartupException {
	if (!isIngredienteNoLanche(pPromocao.getIngredientePromocao())) {
	    throw new StartupException("A promoção não pode ser aplicada, pois, o lanche não possui o ingrediente promocional!");
	}
    }

    /**
     * Valida ingredientes que não podem ter para ser aplicada a promocao
     * 
     * @param pPromocao
     * 
     * @throws StartupException
     */
    public void validaIngredientesExcecao(final Promocao pPromocao) throws StartupException {

	Validate.notNull(pPromocao, "A promoção é obrigatória!");

	if (pPromocao.getIngredientesExcecao() != null) {
	    boolean valida = true;
	    for (final Ingrediente ingredienteExcecao : pPromocao.getIngredientesExcecao()) {
		if (isIngredienteNoLanche(ingredienteExcecao)) {
		    valida = false;
		    break;
		}
	    }

	    if (!valida) {
		throw new StartupException(
			"Não é possível aplicar esta promoção porque o lanche possui um ingrediente não permitido pela promoção!");
	    }
	}
    }

    /**
     * Valida a obrigatoriedade da quantidade
     * 
     * @param pQuantidade
     * @throws StartupException
     */
    private void validarObrigatoriedadeQuantidade(final Integer pQuantidade) throws StartupException {
	if (pQuantidade == null || pQuantidade < 0) {
	    throw new StartupException("A quantidade do item é obrigatória!");
	}
    }
}
