/**
 * 
 */
package br.com.eas.startup.view.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eas.startup.domain.AbstractItemPedido;
import br.com.eas.startup.domain.ItemIngrediente;
import br.com.eas.startup.domain.Lanche;
import br.com.eas.startup.domain.Pedido;
import br.com.eas.startup.domain.Promocao;
import br.com.eas.startup.shared.MassaDadosUtil;
import br.com.eas.startup.shared.exception.StartupException;
import br.com.eas.startup.view.CardapioMB;
import br.com.eas.startup.view.IngredientesMB;
import br.com.eas.startup.view.PedidoMB;
import br.com.eas.startup.view.PromocoesMB;

/**
 * @author Elisangela de Assis da Silva
 */
@WebServlet(name = "startupServlet", urlPatterns = { "/startupServlet", "/jsp/pedido" })
public class StartupServlet extends HttpServlet {

    private static final String VALIDAR_PEDIDO_PARAMETER = "validarPedido";

    /**
     * 
     */
    private static final long serialVersionUID = -6483722175265047036L;

    /**
     * Construtor padrão
     */
    public StartupServlet() {
	// construtor padrão
    }

    /**
     * Atualiza as promoções selecionadas na tela de acordo com objeto na sessão, pois, ao retornar à página anterior e deselecionar uma promoção a
     * página não estava sendo atualizada com o valor correspondente.
     * 
     * @param pResp
     * @param pReq
     * @throws IOException
     * @throws ServletException
     */
    private void ajustarPromocoesSelecionadas(final HttpServletResponse pResp, final HttpServletRequest pReq)
	    throws ServletException, IOException {
	final PromocoesMB promocoesMB = (PromocoesMB) pReq.getSession().getAttribute("promo");
	final String idsPromocoesSelec = pReq.getParameter("promocoesSelecionadas");

	if (promocoesMB != null) {
	    promocoesMB.setPromocoesSelecionadas(idsPromocoesSelec);

	    // atualizo o objeto na sessão
	    pReq.getSession().setAttribute("promo", promocoesMB);
	}

	final RequestDispatcher rd = pReq.getRequestDispatcher("/jsp/pedido/finalizarPedido.jsp");
	pResp.setContentType("text/html");
	pResp.setCharacterEncoding("UTF-8");
	rd.include(pReq, pResp);
    }

    /**
     * Ajusta a quantidade de ingredientes adicionais na página que está na sessão e que posteriormente será recuperada para finalizar o pedido. Pois,
     * quando a quantidade de itens era modificada para zero, os itens continuavam com a quantidade anterior
     * 
     * @param pResp
     * @param pReq
     * @throws IOException
     * @throws ServletException
     */
    private void ajustarQtdIngredientesAdicionais(final HttpServletResponse pResp, final HttpServletRequest pReq)
	    throws ServletException, IOException {

	final IngredientesMB ingredientesMB = (IngredientesMB) pReq.getSession().getAttribute("ingred");
	final String idsItensIngredsSelec = pReq.getParameter("idItemIngredSelecionado");

	if (ingredientesMB != null) {
	    ingredientesMB.setIdItemIngredSelecionado(idsItensIngredsSelec);

	    // atualizo o objeto na sessão
	    pReq.getSession().setAttribute("ingred", ingredientesMB);
	}

	// atualizo o lanche selecionado na sessão
	final CardapioMB cardapioMB = (CardapioMB) pReq.getSession().getAttribute("massa");

	if (cardapioMB != null) {
	    pReq.getSession().setAttribute("massa", cardapioMB);
	}

	final RequestDispatcher rd = pReq.getRequestDispatcher("/jsp/pedido/pedidoPromocoes.jsp");
	pResp.setContentType("text/html");
	pResp.setCharacterEncoding("UTF-8");
	rd.include(pReq, pResp);
    }

    /**
     * Altera a quantidade de itens de acordo com o lanche selecionado
     * 
     * @param pQuantidade
     * @param pResp
     * @param pReq
     * @throws StartupException
     */
    private void alterarQuantidadeItem(final Integer pQuantidade, final HttpServletResponse pResp, final HttpServletRequest pReq)
	    throws StartupException {

	if (pQuantidade != null && pQuantidade > 0) {
	    final Pedido pedido = getPedidoSelecionado(pReq);

	    if (pedido != null) {
		// TODO: aqui cabe melhoria, pois atualmente pode-se fazer um pedido de apenas um item
		final Object[] itensPedido = pedido.getItens().toArray();
		final Lanche lanche = ((AbstractItemPedido) itensPedido[0]).getLanche();
		if (lanche != null) {
		    pedido.ajustarQtdItem(lanche, pQuantidade, MassaDadosUtil.getSolicitante());
		    writeResponse(pResp, formatarValorCurrency(pedido.getValorTotalPedido()));
		}
	    }
	}
    }

    @Override
    protected void doGet(final HttpServletRequest pReq, final HttpServletResponse pResp) {
	doPost(pReq, pResp);
    }

    @Override
    protected void doPost(final HttpServletRequest pReq, final HttpServletResponse pResp) {

	String param = pReq.getParameter(VALIDAR_PEDIDO_PARAMETER);

	if (param == null) {
	    param = (String) pReq.getSession().getAttribute(VALIDAR_PEDIDO_PARAMETER);
	}

	try {
	    if (PromocoesMB.PROMOCAO_PARAMETER.equalsIgnoreCase(param)) {
		validarPromocaoSelecionada(getLancheSelecionado(pReq), pReq);
	    } else if (PedidoMB.SALVAR_PEDIDO.equals(param)) {
		salvar(pResp, pReq);
	    } else if (PedidoMB.ALTERAR_QUANTIDADE.equals(param)) {
		alterarQuantidadeItem(getQuantidade(pReq), pResp, pReq);
	    } else if (IngredientesMB.INGREDIENTES_ADICIONAIS.equals(param)) {
		ajustarQtdIngredientesAdicionais(pResp, pReq);
	    } else if (PromocoesMB.VALIDAR_PROMOCAO_SUBMIT_PARAMETER.equalsIgnoreCase(param)) {
		ajustarPromocoesSelecionadas(pResp, pReq);
	    }
	} catch (final StartupException e) {
	    writeResponse(pResp, e.getMessage());
	} catch (final Exception e) {
	    final String logMsg = String.format("Falha inesperada ao realizar a operação solicitada. Mensagem: %s, causa: %s",
		    e.getMessage(), e.getCause());
	    writeResponse(pResp, logMsg);
	}
    }

    /**
     * Formata um dado valor para o formato decimal em moeda. Exemplo: R$ 1.014,12
     * 
     * @param pValor
     * @return valor formatado em reais
     */
    private String formatarValorCurrency(final BigDecimal pValor) {
	String valorFormatado = null;
	if (pValor != null) {
	    final DecimalFormat decFormat = new java.text.DecimalFormat("'R$ ' #,###,##0.00");
	    valorFormatado = decFormat.format(pValor);
	}
	return valorFormatado;
    }

    /**
     * @param pReq
     * @return the itensIngredsSelecionados
     * @throws StartupException
     */
    private List<ItemIngrediente> getItensIngredsSelecionados(final HttpServletRequest pReq) throws StartupException {

	final IngredientesMB ingredientesMB = (IngredientesMB) pReq.getSession().getAttribute("ingred");

	final List<ItemIngrediente> itensIngredsSelecionados = new ArrayList<ItemIngrediente>();

	if (ingredientesMB != null) {
	    for (final ItemIngrediente item : ingredientesMB.getItensIngredsSelecionados()) {
		itensIngredsSelecionados.add(new ItemIngrediente(item.getIngrediente(), item.getQuantidade()));
	    }
	}
	return itensIngredsSelecionados;
    }

    /**
     * Busca no request o id do lanche selecionado e o compara com os lanches do cardápio para então retornar o lanche selecionado
     * 
     * @param pReq
     * @return lanche selecionado
     * @throws StartupException
     */
    private Lanche getLancheSelecionado(final HttpServletRequest pReq) throws StartupException {

	final String strIdLancheSelec = ((CardapioMB) pReq.getSession().getAttribute("massa")).getIdLancheSelecionado();

	Lanche lancheSelec = null;

	if (strIdLancheSelec != null && !strIdLancheSelec.isEmpty()) {
	    final Long idLancheSelec = Long.valueOf(strIdLancheSelec);

	    if (idLancheSelec != null) {
		for (final Lanche lancheCardapio : MassaDadosUtil.getLanchesCardapio()) {
		    if (lancheCardapio.getId().equals(idLancheSelec)) {
			lancheSelec = lancheCardapio;
			break;
		    }
		}
	    }
	}

	return lancheSelec;
    }

    /**
     * Obtem da seção o pedido selecionado
     * 
     * @param pReq
     * @return pedido selecionado
     * @throws StartupException
     */
    private Pedido getPedidoSelecionado(final HttpServletRequest pReq) throws StartupException {
	return ((PedidoMB) pReq.getSession().getAttribute("pedidoMB")).getPedido();
    }

    /**
     * Busca no request os ids das promoções selecionadas
     * 
     * @param pReq
     * @return lanche selecionado
     * @throws StartupException
     */
    private List<Promocao> getPromocoesSelecionadas(final HttpServletRequest pReq) throws StartupException {

	final String[] idsPromocoes = pReq.getParameterValues("promocoesSelecionadas");

	final List<Promocao> promosSelec = new ArrayList<Promocao>(0);

	if (idsPromocoes != null && idsPromocoes.length > 0) {
	    for (final Promocao promocao : MassaDadosUtil.getPromocoes()) {
		for (final String id : idsPromocoes) {
		    if (promocao.getId().equals(Long.valueOf(id))) {
			promosSelec.add(promocao);
		    }
		}
	    }
	}

	return promosSelec;
    }

    /**
     * Busca a quantidade de lanches informada
     * 
     * @param pReq
     * @return quantidade de lanches informada
     */
    private Integer getQuantidade(final HttpServletRequest pReq) {
	final String strQuantidade = pReq.getParameter("quantidade");

	if (strQuantidade != null && !strQuantidade.isEmpty()) {
	    return Integer.valueOf(strQuantidade);
	}
	return null;
    }

    /**
     * "Salva" o pedido de acordo com as informações da tela
     * 
     * @param pResp
     * @param pReq
     * @throws StartupException
     * @throws IOException
     * @throws ServletException
     */
    private void salvar(final HttpServletResponse pResp, final HttpServletRequest pReq) throws StartupException,
	    ServletException, IOException {

	final Pedido pedido = getPedidoSelecionado(pReq);

	final StringBuilder sb = new StringBuilder();

	if (pedido != null) {
	    pReq.getSession().invalidate();
	    sb.append("Pedido salvo com sucesso!");
	} else {
	    sb.append("Erro inesperado. Não foi possível salvar o pedido!");
	}
	writeResponse(pResp, sb.toString());
    }

    /**
     * Valida se a promoção pode ser aplicada no lanche e com os ingredientes selecionados.
     * 
     * @param pLancheSelecionado
     * @param pReq
     * @throws StartupException
     */
    private void validarPromocaoSelecionada(final Lanche pLancheSelecionado, final HttpServletRequest pReq)
	    throws StartupException {

	if (pLancheSelecionado != null) {
	    // Cria um novo lanche apenas para fazer as validações

	    final Set<ItemIngrediente> listaIngredientes = new HashSet<ItemIngrediente>();
	    for (final ItemIngrediente item : pLancheSelecionado.getIngredientes()) {
		listaIngredientes.add(new ItemIngrediente(item.getIngrediente(), item.getQuantidade()));
	    }
	    final Lanche lanche = new Lanche(listaIngredientes, pLancheSelecionado.getPersonalizado());

	    final List<ItemIngrediente> ingredsSelecionados = getItensIngredsSelecionados(pReq);

	    for (final ItemIngrediente itemIngred : ingredsSelecionados) {
		lanche.adicionarItemIngrediente(itemIngred, itemIngred.getQuantidade());
	    }

	    final List<Promocao> promosSelecionadas = getPromocoesSelecionadas(pReq);

	    // TODO: aqui cabe melhoria pois o lanche aceita apenas uma promoção
	    for (final Promocao promo : promosSelecionadas) {
		lanche.setPromocao(promo);
	    }
	}
    }

    /**
     * Método que escreve uma mensagem no response
     * 
     * @param pResponse
     *            Response onde o script será escrito.
     * @param pMsgErro
     *            Conteúdo que será escrito no response
     */
    private void writeResponse(final HttpServletResponse pResponse, final String pMsgErro) {

	pResponse.setContentType("text/html");
	pResponse.setCharacterEncoding("UTF-8");

	try {
	    final OutputStream ous = pResponse.getOutputStream();
	    ous.write(pMsgErro.getBytes(Charset.forName("UTF-8")));
	    ous.flush();
	    ous.close();
	} catch (final IOException e) {
	    final StringBuilder errMsg = new StringBuilder();
	    errMsg.append("Erro ao escrever no reponse.");
	}
    }
}
