/**
 * 
 */
package br.com.eas.startup.shared.exception;

/**
 * @author Elisangela de Assis da Silva
 */
public class StartupException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 8876143304062494515L;

    /**
     * Contrutor que recebe uma mensagem a ser lançada na exceção.
     * 
     * @param pMensagem
     *            Mensagem de erro.
     */
    public StartupException(final String pMensagem) {
	super(pMensagem);
    }

}
