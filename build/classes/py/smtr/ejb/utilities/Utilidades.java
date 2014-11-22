/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.utilities;

/**
 *
 * @author Strogg
 */
public class Utilidades {
    public static String leftPad(String value, String symbol, int count) {
        String pad = value;

        for (int i=pad.length(); i<count; i++)
            pad = symbol+pad;

        return pad;
    }
}
