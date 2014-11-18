/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.utilities;

import java.util.Calendar;

/**
 *
 * @author Strogg
 */
public class MainPruebas {

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
    
    double d = 1.234567;
    DecimalFormat df = new DecimalFormat("#.##");
    System.out.println(Double.valueOf((df.format(d).replaceAll(",", "."))));
    }*/
    public static void main(String[] args) throws Exception {
        Calendar ahoraCal = Calendar.getInstance();
        System.out.println(ahoraCal.getTime());
        Calendar inicioCal = Calendar.getInstance();
        inicioCal.set(ahoraCal.get(Calendar.YEAR), ahoraCal.get(Calendar.MONTH), ahoraCal.get(Calendar.DATE), 0, 0, 0);
        System.out.println(inicioCal.getTime());
    }
}
