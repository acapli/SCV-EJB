/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.shared;

import java.io.Serializable;

/**
 *
 * @author Strogg
 */
public class ResponseLogin implements Serializable {

    private String nombre;
    private String sesion;        
    private Integer roles[];
    

    public ResponseLogin() {
    }

    public ResponseLogin(String nombre, String sesion) {
        this.nombre = nombre;
        this.sesion = sesion;
    }  
    
    public ResponseLogin(String nombre, String sesion, Integer roles[]){
        this.nombre = nombre;
        this.sesion = sesion;
        this.roles = roles;    
    }
    
    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 
    
    public void setRoles(Integer[] roles) {
        this.roles = roles;
    }

    public Integer[] getRoles() {
        return roles;
    }

}
