/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.utilities;

/**
 *
 * @author Strogg
 */
public class ConstantesEJB {
    public static String ERROR_INESPERADO = "Ha ocurrido un error inesperado.";
    public static String MASDE1USER = "Hay más de un usuario con ese nombre.";
    public static String USER_PASS_INC = "Usuario y/o password incorrecto.";
    public static String USER_INACTIVO = "El usuario no está activo.";
    public static String SIN_SESION = "Ud. no tiene sesion.";
    public static String EROR_ENCRYP = "No se pudo encriptar el password.";
    public static String ENTITY_EXIST = "La entidad ya existe en la base de datos";
    public static String USER_NO_EXIST = "No existe el usuario con id: ";
    public static String PASS_INCOR = "La contraseña proporcionada es inválida";
    public static String LOG4J_PROP = "/logsTPweb/tpweb-ejb.properties";
    public static String CLIENT_NOT_EXIST = "No existe el cliente con id: ";    
    public static String CLIENT_MISMO_NOMBRE = "Ya existe un cliente con el mismo R.U.C. o C.I.";
    public static String CAJA_MISMO_NOMBRE = "Ya existe una caja con el mismo nombre";
    public static String CAJA_NO_EXIST = "No existe la caja con id:";
    public static String PROD_NO_EXIST = "No existe el producto con id: ";
    public static String PROD_MISMO_COD = "Ya existe un producto con el mismo codigo.";
    public static String PROVEEDOR_NOT_EXIST = "No existe el proveedor con id: ";    
    public static String PROVEEDOR_NOT_EXIST_RUC = "No existe el proveedor con ruc: ";    
    public static String PROVEEDOR_MISMO_RUC = "Ya existe un proovedor con el mismo R.U.C";
    public static String SIN_USUARIOS_CAJEROS = "No existen usuarios con el rol cajero";
    public static String ROL_NO_EXISTE = "No existe el rol: ";    
    public static String USUARIO_YA_POSEE_ROL = "El usuario ya tiene asignado el rol solicitado";
    public static String USUARIO_NO_POSEE_ROL = "El usuario no posee el rol solicitado para eliminar";
    
     //para el manejo de roles...
    public static final Integer ROL_ADMINISTRADOR_EJB  = 1;
    public static final Integer ROL_COMPRADOR_EJB  = 2;
    public static final Integer ROL_VENDEDOR_EJB  = 3;
    public static final Integer ROL_CAJERO_EJB  = 4;
}