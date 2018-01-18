package br.com.eas.startup.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;

import br.com.eas.startup.shared.exception.StartupException;

/**
 * @author Elisangela de Assis da Silva
 */
public abstract class AbstractItemPedido implements IItemPedido {

    /**
     * Constante que identifica o item
     */
    public static final String TIPO_ITEM_CARDAPIO = "CARDAPIO";

    /**
     * Constante que identifica item do tipo personalizável
     */
    public static final String TIPO_ITEM_PERSONALIZADO = "PERSONALIZADO";

    /**
     * Constante que identifica item do tipo promocional
     */
    public static final String TIPO_ITEM_PROMOCAO = "PROMOCAO";

    private static Long ID = 1L;

    private static Long getProximoId() {
	return ID++;
    }

    private Long id;

    private Pedido pedido;

    private Lanche lanche;

    protected Integer quantidade;

    private BigDecimal valorUnitario;

    private BigDecimal valorTotalItem;

    /**
     * Construtor padrão
     */
    AbstractItemPedido() {
	// Construtor padrão
    }

    /**
     * Construtor com campos obrigatórios
     * 
     * @param pPedido
     * @param pLanche
     * @param pQuantidade
     * @throws StartupException
     */
    public AbstractItemPedido(final Pedido pPedido, final Lanche pLanche, final Integer pQuantidade) throws StartupException {
	Validate.notNull(pPedido, "O pedido é obrigatório.");
	Validate.notNull(pLanche, "O lanche é obrigatório.");
	validaObrigatoriedadeDaQuantidade(pQuantidade);
	validaCondicaoQuantidadeTemQueSerMaiorQueZero(pQuantidade);
	pedido = pPedido;
	lanche = pLanche;
	quantidade = pQuantidade;
	id = getProximoId();
	calcularValorUnitarioEValorTotalDoItem(this.lanche, this.quantidade);
    }

    /**
     * Depois que o lanche e a quantidade foram setados, é necessario calcular o valor unitario do item e o valor total do mesmo
     * 
     * @param pQuantidade
     * @param pLanche
     * @throws StartupException
     */
    private void calcularValorUnitarioEValorTotalDoItem(final Lanche pLanche, final Integer pQuantidade) throws StartupException {

	if (pLanche != null) {
	    if (valorUnitario == null) {
		valorUnitario = pLanche.getValorTotal();
	    }

	    if (valorUnitario != null && pQuantidade > 0) {
		valorTotalItem = BigDecimal.valueOf(pQuantidade).multiply(valorUnitario);
	    }
	}
    }

    /*
     * O que torna um item de pedido igual a outro é o pedido a ele associado e o lanche
     */
    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final AbstractItemPedido other = (AbstractItemPedido) obj;
	if (lanche == null) {
	    if (other.lanche != null) {
		return false;
	    }
	} else if (!lanche.equals(other.lanche)) {
	    return false;
	}
	if (pedido == null) {
	    if (other.pedido != null) {
		return false;
	    }
	} else if (!pedido.equals(other.pedido)) {
	    return false;
	}
	return true;
    }

    public Long getId() {
	return id;
    }

    /**
     * @return the lanche
     */
    public Lanche getLanche() {
	return lanche;
    }

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
	return pedido;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public BigDecimal getValorTotalItem() {
	return valorTotalItem;
    }

    public BigDecimal getValorUnitario() {
	return valorUnitario;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	// result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((lanche == null) ? 0 : lanche.hashCode());
	result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
	return result;
    }

    /**
     * 
     * @param pOutraEntidade
     * @return true se os ids forem iguais, falso em caso contrário
     */
    public boolean mesmoIdQue(final AbstractItemPedido pOutraEntidade) {
	return id.equals(pOutraEntidade.id);
    }

    /**
     * 
     */
    public boolean mesmoIdQue(final IItemPedido pOutraEntidade) {
	return id.equals(pOutraEntidade.getId());
    }

    public void setQuantidade(final Integer pQuantidade) throws StartupException {
	validaObrigatoriedadeDaQuantidade(pQuantidade);
	validaCondicaoQuantidadeTemQueSerMaiorQueZero(pQuantidade);
	quantidade = pQuantidade;
	calcularValorUnitarioEValorTotalDoItem(this.lanche, pQuantidade);
    }

    /**
     * @param pQuantidade
     * @throws StartupException
     */
    protected void validaCondicaoQuantidadeTemQueSerMaiorQueZero(final Integer pQuantidade) throws StartupException {
	if (pQuantidade <= 0) {
	    throw new StartupException("A quantidade do item tem que ser maior que ZERO!");
	}
    }

    /**
     * @param pQuantidade
     */
    protected void validaObrigatoriedadeDaQuantidade(final Integer pQuantidade) {
	Validate.notNull(pQuantidade, "A quantidade é obrigatória.");
    }
}