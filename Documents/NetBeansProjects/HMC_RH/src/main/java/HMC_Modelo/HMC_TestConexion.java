/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMC_Modelo;
import java.sql.Connection;

/**
 *
 * @author Benjamin
 */
public class HMC_TestConexion {
    
    public static void main(String[] args) {
        HMC_Conexion conexion = new HMC_Conexion();
        Connection con = conexion.obtenerConexion();
        
        if (con != null) {
            System.out.println("Conexion exitosa con Oracle");
        } else {
            System.out.println("No se pudo conectar");
        }
   
        HMC_Conexion gestor = new HMC_Conexion(); //A su ves cada metodo llama al metodo obtenerConexion para conectar con la BD

        //Compruebo el resultado de la BD con el metodo buscarEmpleadoporId
        String resultado = gestor.buscarEmpleadoporId(1);
        System.out.println("El empleado es: " + resultado);
        
        //Compruebo el acceso con el metodo validarUsuario
        boolean res = gestor.validarUsuario("1", "5512345678");
        System.out.println("El empleado es valido: " + res);
        
        //Compruebo el INSERT EN BD El email debe ser unico.
        //boolean ins = gestor.insertarEmpleado("Benjamin", "Avila", "Rivera", "dev@gmail.com", "5543232323", "19/02/1996", "19/02/2021", 25000.00, 2, 2);
        //System.out.println("El empleado se inserto: " + ins);
        
        //boolean ins2 = gestor.insertarEmpleado("Benjamin", "Avila", "Rivera", "dev3@gmail.com", "5543232323", "19/02/1996", "19/02/2021", 25000.00, 2, 2);
        //System.out.println("El empleado se inserto: " + ins2);
        

        //int idEmpleado, String NOMBRE, String APELLIDO_PATERNO, String APELLIDO_MATERNO,  String TELEFONO, double SALARIO
        //boolean up = gestor.actualizarEmpleado(24, "Benjamin", "Avila", "Rivera", "5543232323", 26000.00);
        //System.out.println("El empleado se ACTUALIZO: " + up);
        
        //Prueba de eliminacion de un Empleado, por el momento obtengo el id desde la BD ya que tiene un indice que inserta este dato de manera autonoma.
        //boolean del = gestor.eliminarEmpleado(25);
        //System.out.println("El empleado se ELIMINO: " + del);
        
    } 
    
}