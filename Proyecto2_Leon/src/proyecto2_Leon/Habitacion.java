/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_Leon;

/**
 *
 * @author sebasalf
 */
//clase habitacion con los datos de cada habitacion
public class Habitacion {
    private int num;
    private String tipo;
    private int piso;
    private Estado cliente_actual;
    private Habitacion next;
    
    
    public Habitacion(int num, String tipo, int piso){
        this.num = num;
        this.tipo = tipo;
        this.piso = piso;
        this.cliente_actual = null;
        this.next = null;
 
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public Estado getCliente_actual() {
        return cliente_actual;
    }

    public void setCliente_actual(Estado cliente_actual) {
        this.cliente_actual = cliente_actual;
    }

    public Habitacion getNext() {
        return next;
    }

    public void setNext(Habitacion next) {
        this.next = next;
    }
    
    
    
    
}
