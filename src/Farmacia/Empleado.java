
package Farmacia;

import java.util.ArrayList;



public class Empleado {
    private final String dni;
    private final String cargo;
    private String nombre;
    private String telefono;
    private String correo;
    private boolean genero;
    private float sueldo;
    private Farmacia sedeFarmacia;
    
    private Pedido pedido;//pedido temporal 
    private ArrayList<Venta>ventas = new ArrayList<Venta>();
    
    

    public Empleado(String dni, String cargo, String nombre, String telefono, String correo, boolean genero, float sueldo, Farmacia sedeFarmacia) {
        this.dni = dni;
        this.cargo = cargo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.genero = genero;
        this.sueldo = sueldo;
        this.sedeFarmacia = sedeFarmacia;
    }
    private boolean login;//detector de login
    public void registrarVenta(Venta venta){
        ventas.add(venta);
    }
    public void generarPedidoE(String ruc, String codM, RegistroClientes rClientes){
        pedido = new Pedido(this);
      
        
        pedido.obtenerCliente(ruc,true, rClientes);
      
        pedido.agregarProducto(codM);
    }
    public void generarPedidoP(String dni, String codM, RegistroClientes rClientes){
        pedido = new Pedido(this);
    
        
        pedido.obtenerCliente(dni,false,rClientes);
        
        pedido.agregarProducto(codM);
        
    }
    public void comfirmarPedido(float tiempo, boolean tipoDePago){
        pedido.realizarVenta(pedido,tiempo,tipoDePago);
    }
    public void cancelarPedido(){
    }
    public void cuadrarCaja(){
    }
    public void agregarEmpleado(String dni, String cargo, String nombre, String telefono, String correo, boolean genero, float sueldo, Farmacia sedeFarmacia){
        this.getSedeFarmacia().getR_empleados().agregarEmpleado(dni, cargo, nombre, telefono, correo, genero, sueldo, sedeFarmacia);
    }

    public Pedido getPedido() {
        return pedido;
    }
   
    /**
     * Devuelve el empleado actual
     */   
    //Getters y Setters
    public String getDni() {
        return dni;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public Farmacia getSedeFarmacia() {
        return sedeFarmacia;
    }

    public void setSedeFarmacia(Farmacia sedeFarmacia) {
        this.sedeFarmacia = sedeFarmacia;
    }
    
    
}
