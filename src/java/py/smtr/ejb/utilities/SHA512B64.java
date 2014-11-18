/*
 * SHA512B64.java
 *
 * Created on 27 de marzo de 2007, 05:03 PM
 *
 * Clase creada para convertir un texto en hash (SHA) y luego pasado a 
 * Base 64
 */

package py.smtr.ejb.utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Propietario
 */ 

public final class SHA512B64 {
    public static String encriptar(String textoplano) throws IllegalStateException {
        MessageDigest md = null;

        // Instancia de generador SHA-2
        try {            
            md = MessageDigest.getInstance("SHA"); //SHA512 cambiado por motivos de tamanho
        } catch(NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage());
        }

        // Generación del hash del mensaje
        try {
            md.update(textoplano.getBytes("UTF-8"));
        } catch(UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage());
        }

        // Obtención del hash de mensaje
        byte raw[] = md.digest(); 
        
        String hash = new String(Base64Coder.encode(raw));
        return hash.substring( 0, hash.length()-1);
    }
}
