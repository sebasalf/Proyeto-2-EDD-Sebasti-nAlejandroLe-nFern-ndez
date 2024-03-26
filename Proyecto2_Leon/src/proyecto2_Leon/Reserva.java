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

//Tipo de objeto reserva que se crea con los datos del txt reservas
public class Reserva {
    private int cedula;
    private String nombre;
    private String apellido;
    private String email;
    private String gender;
    private String tipo_habitacion;
    private String celular;
    private int[] llegada;
    private int[] salida;
    private Reserva next;
    
    
    public Reserva(int cedula, String nombre, String apellido, String email, String gender, String tipo_habitacion, String celular, int[] llegada, int[]salida){
        this.apellido = apellido;
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.gender = gender;
        this.tipo_habitacion = tipo_habitacion;
        this.celular = celular;
        this.llegada = llegada;
        this.salida = salida;
        this.next = null;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int[] getLlegada() {
        return llegada;
    }

    public void setLlegada(int[] llegada) {
        this.llegada = llegada;
    }

    public int[] getSalida() {
        return salida;
    }

    public void setSalida(int[] salida) {
        this.salida = salida;
    }

    public Reserva getNext() {
        return next;
    }

    public void setNext(Reserva next) {
        this.next = next;
    }
    //v1 metodo que crea un string con el formato colocado de los datos de la Reserva
    public String printCliente(){
        String str = ("Nombre: "+nombre+" "+apellido+", Cedula: "+cedula+", Email: "+email+", Genero: "+gender+", Tipo de Habitacion: "+ tipo_habitacion+", Celular: "+celular+", Llegada: "+llegada[0]+"/"+llegada[1]+"/"+llegada[2]+", Salida: "+salida[0]+"/"+salida[1]+"/"+salida[2]);
        return str;
    }
    //v2 del metodo que crea un string con el formato colocado de los datos de la Reserva, el formato es diferente
    public String printCliente2(){
        String str = ("Nombre: "+nombre+" "+apellido+"\n"+"Cedula: "+cedula+"\n"+"Email: "+email+"\n"+"Genero: "+gender+"\n"+"Tipo de Habitacion: "+ tipo_habitacion+"\n"+"Celular: "+celular+"\n"+"Llegada: "+llegada[0]+"/"+llegada[1]+"/"+llegada[2]+"\n"+"Salida: "+salida[0]+"/"+salida[1]+"/"+salida[2]);
        return str;
    }
    
}
