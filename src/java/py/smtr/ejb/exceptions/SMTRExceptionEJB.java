/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.exceptions;

/**
 *
 * @author Norita
 */
public class SMTRExceptionEJB extends Exception {

    public SMTRExceptionEJB() {
    }

    public SMTRExceptionEJB(String message) {
        super(message);
    }

    public SMTRExceptionEJB(Throwable cause) {
        super(cause);
    }

}
