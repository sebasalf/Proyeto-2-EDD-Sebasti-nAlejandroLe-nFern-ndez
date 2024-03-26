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
//Nodo con tipo de dato Reserva del AVL_Reserva
public class NodoReserva {
    private Reserva reservacion;
    private NodoReserva izquierdo;
    private NodoReserva derecho;
    private int altura;
    
    public NodoReserva(Reserva reservacion) {
        this.reservacion = reservacion;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1;
    }
    
    public Reserva getReserva() {
        return reservacion;
    }
    
    public Reserva setReserva(Reserva nueva) {
        return this.reservacion = nueva;
    }
    
    public NodoReserva getIzquierdo() {
        return izquierdo;
    }
    
    public void setIzquierdo(NodoReserva izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    public NodoReserva getDerecho() {
        return derecho;
    }
    
    public void setDerecho(NodoReserva derecho) {
        this.derecho = derecho;
    }
    
    public int getAltura() {
        return altura;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
}
