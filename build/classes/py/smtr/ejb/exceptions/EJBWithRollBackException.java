/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Strogg
 */
@ApplicationException(rollback=true)
public class EJBWithRollBackException extends Exception {

    public EJBWithRollBackException(String message) {
        super(message);
    }

    public EJBWithRollBackException() {
    }
    
}
