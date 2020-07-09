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
public class Empresa extends Cliente{
    private String ruc;
    private Nodo facturas=null;
    private class Nodo{
        public Factura fac;
        public Nodo sgte=null;
        

        public Nodo(Factura fac) {
            this.fac = fac;
        }
    }

    public Empresa(String ruc, String nombre, String codigo) {
        super(nombre, codigo);
        this.ruc = ruc;
    }

    public String getRuc() {
        return ruc;
    }
  
    
}
