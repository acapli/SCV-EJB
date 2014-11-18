/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.exceptions;

/**
 *
 * @author Strogg
 */
public class EJBWithOutRollBackException extends Exception {

    public EJBWithOutRollBackException(String message) {
        super(message);
    }

    public EJBWithOutRollBackException() {
    }
    
}
