/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farmacia;

/**
 *
 * @author Diego Sullon
 */
public class Boleta extends Comprobante{
    private final String codigo;
    private final String dni;
    public Boleta(Venta venta, Farmacia farmacia, String codigo, String dni) {
        super(venta, farmacia);
        this.codigo=codigo; 
        this.dni=dni;
    }
 public void imprimir(Farmacia farmacia, Venta venta){
        System.out.println(farmacia.getNombre() + "\nDirección: " +farmacia.getDireccion() + "\nRuc: " +  farmacia.getRuc()+"\nDescripción: ");
        for(int i=0;i<venta.getPedido().detalles.size();i++){
            System.out.println("\t"+venta.getPedido().detalles.get(i));
        }
        System.out.println("Total: "+venta.getPedido().getTotal());
        System.out.println("Cliente: "+venta.getPedido().getNombreC()+"\nDni: "+venta.getPedido().getDocC());
        
    }
}
