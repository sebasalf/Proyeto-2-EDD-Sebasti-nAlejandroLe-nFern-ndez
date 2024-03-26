/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_Leon;

/**
 *
 * @author sebasalf
 */
//nodo historico con elemento historico del AVL_historico
public class NodoHistorico {
    private Historico cliente;
    private NodoHistorico izquierdo;
    private NodoHistorico derecho;
    private int altura;
  

    public NodoHistorico(Historico cliente) {
        this.cliente = cliente;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1;
      
    }
    
    
    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    

    public Historico getCliente() {
        return cliente;
    }

    public void setCliente(Historico cliente) {
        this.cliente = cliente;
    }

    public NodoHistorico getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoHistorico izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoHistorico getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoHistorico derecho) {
        this.derecho = derecho;
    }
    
    
    
    
}