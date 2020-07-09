/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farmacia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 *
 * @author Diego Sullon
 */
class RegistroEmpleados {
    int n_empleados;
    
    
    ArrayList<Empleado>empleados = new ArrayList<Empleado>();
    
    
    

    public RegistroEmpleados(Farmacia sedeFarmacia) {
        actualizarLista(sedeFarmacia);
    }
    /**
     * agrega empleado en memoria(ArrayList)
    */  
    public void agregarEmpleado_array(String dni, String cargo, String nombre, String telefono, String correo, boolean genero, float sueldo, Farmacia sedeFarmacia){
        Empleado e = new Empleado(dni,cargo,nombre,telefono,correo,genero, sueldo, sedeFarmacia);                
        empleados.add(e);        
    }
    /**
     * registra empleado en Base de datos
    */  
    public void agregarEmpleado(String dni, String cargo, String nombre, String telefono, String correo, boolean genero, float sueldo, Farmacia sedeFarmacia){
        String g=genero?"Masculino":"Femenino";
        try {
            //Se crea la conexión con la base de datos
            Connection conexion = Farmacia.getConexion();
            //modelo de sentencia
            PreparedStatement ingreso = conexion.prepareStatement("INSERT INTO empleados VALUES (?,?,?,?,?,?,?)");
            //formato para la sentencia
            ingreso.setString(1, dni);ingreso.setString(2, nombre);ingreso.setString(3, cargo);
            ingreso.setString(4, telefono);ingreso.setString(5, correo);ingreso.setString(6, g);ingreso.setFloat(7, sueldo);
            ingreso.executeUpdate();
            //cierre de conexión
            actualizarLista(sedeFarmacia);
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión2");
        }       
    }
    /**
     * elimina empleado en memoria(ArrayList)
    */  
    public void eliminarEmpleado_array(String cod){
        for(int i=0;i<empleados.size();i++){
             if(empleados.get(i).getDni().equals(cod)){
               empleados.remove(i);
             }
         }
    }
    /**
     * elimina empleado en Base de datos
    */ 
     public void eliminarEmpleado(String cod, Farmacia sedeFarmacia){
        try {
            Connection conexion = Farmacia.getConexion();
            PreparedStatement delete = conexion.prepareStatement("DELETE FROM `empleados` WHERE `empleados`.`dni` =?");
            delete.setString(1, cod);
            delete.executeUpdate();
            actualizarLista(sedeFarmacia);
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }
    }
    private void actualizarLista(Farmacia sedeFarmacia) {
        try {
            empleados.clear();
            Connection conexion = Farmacia.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM empleados");
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                boolean t=(rs.getString("genero").equalsIgnoreCase("Masculino"));
                Empleado e = new Empleado(rs.getString("dni"), rs.getString("cargo"), rs.getString("nombre"), rs.getString("telefono"), rs.getString("correo"), t, rs.getFloat("sueldo"), sedeFarmacia);
                empleados.add(e);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }
    }
   public void actualizarDatos(String cod){
   }
    public void mostrarEmpleados(){
        for(int i=0;i<empleados.size();i++){
            System.out.println(empleados.get(i).getDni());
    }
   }
    public Empleado obtenerEmpleado(String dni){
        for(int i=0;i<empleados.size();i++){
             if(empleados.get(i).getDni().equals(dni)){
               return empleados.get(i);
             }
         }
        return null;
   }
    
}
