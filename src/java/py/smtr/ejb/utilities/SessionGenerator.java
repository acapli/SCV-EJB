/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.utilities;

import java.util.Random;

/**
 *
 * @author Strogg
 */
public class SessionGenerator {

    public static String generarSesion(int longitud) {
        String sesion = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                sesion += c;
                i++;
            }
        }
        return sesion;
    }
}
