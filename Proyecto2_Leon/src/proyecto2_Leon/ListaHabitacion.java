/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_Leon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 *
 * @author sebasalf
 */
// es una lista que tiene todas las habitaciones
public class ListaHabitacion {
    private Habitacion headpiso;
  
    private int num_habitaciones;
    
    public ListaHabitacion(){
        this.headpiso = null;
        this.num_habitaciones = 0;
    }

    public Habitacion getHeadpiso() {
        return headpiso;
    }

    public void setHeadpiso(Habitacion headpiso) {
        this.headpiso = headpiso;
    }

    public int getNum_habitaciones() {
        return num_habitaciones;
    }

    public void setNum_habitaciones(int num_habitaciones) {
        this.num_habitaciones = num_habitaciones;
    }
    
    
    //lector de txt para creaer los obejtos Habitaciones e insertarlos en la lista
    public void initlistaHabitaciones(){
        String line;
        String habitaciones_txt = "";
        String path = "test\\habitaciones.txt";
        File file = new File(path);
   
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    habitaciones_txt += line + "\n";
                }
            }
            if (!"".equals(habitaciones_txt)) {
                String[] habitaciones_split = habitaciones_txt.split("\n");

                for (int i = 1; i < habitaciones_split.length; i++) {
                        String[] habitacion = habitaciones_split[i].split(",");
                        
                        int num = Integer.parseInt(habitacion[0]);
                        String tipo_habitacion = habitacion[1];
                        int piso = Integer.parseInt(habitacion[2]);
                        
                        Habitacion nueva_habitacion = new Habitacion(num, tipo_habitacion, piso);
                        agregar_habitacion(nueva_habitacion);
                        

                }               
            }
            br.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar base de Datos");
        }
        
        
    }
    //metodo para agregar una habitacion a la lista
    public void agregar_habitacion(Habitacion valor) {
        if (headpiso == null) {
            headpiso = valor;
        } else {
            Habitacion pointer = getHeadpiso();
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(valor);
        }
    }
    // metodo que busca cada elemento Estado en la hastable y le asiga ese cliente con su habitacion a el atributo cliente de Habitacion, dicho de otra forma, busca que habitaciones estan ocupada segun los datos de los clientes de la hashtable y asiga ese cliente a cada habitacion ,de este modo la habitacion sale como coupada en la base de datos
    public void add_client_to_hab(HashTableEstadoActual estado){
        Habitacion pointer = getHeadpiso();
        while (pointer != null) {
            for (int i =0; i < estado.getSize() ; i++){
                if (estado.getArray_reservas()[i] != null){
                    Estado pointer2 = estado.getArray_reservas()[i];
                    while (pointer2 != null) {
                        int num = pointer2.getNum_habitacion();
                        if (pointer2.getNum_habitacion() == pointer.getNum()){
                            pointer.setCliente_actual(pointer2);
                        }
                        pointer2 = pointer2.getNext();
                    }
                }
            }
            pointer = pointer.getNext();
        }
    }
    //Del mismo modo que la anterior busca que habitaciones estan vacias segun el tipo de habitacion que busques, viendo si el atributo cliente es diferente a null(ya que si esta ocupada tiene su objeto Estado por el metodo anteior) y asigna el numeor de la primera habitacion encontrada vacia del tipo que se ingreso
    public int buscar_habitacion(String tipo_hab){
        Habitacion pointer = getHeadpiso();
        int new_hab = 0;
        while (pointer != null){
            if (pointer.getCliente_actual() == null & pointer.getTipo().equals(tipo_hab)){
                new_hab = pointer.getNum();
                return new_hab;
            }
            pointer = pointer.getNext();
        }
        
        
        return new_hab;
    }
    // esta funcion actualiza el estado de la habitacion de numero ingresada y le asigna su hospedado Estado cliente    
    public void actualizarestadoHab(int numero_hab, Estado cliente_actual){
        Habitacion pointer = getHeadpiso();
        
        while (pointer != null){
            if (pointer.getNum() == numero_hab){
                pointer.setCliente_actual(cliente_actual);
               
            }
            pointer = pointer.getNext();
        }
         
    }
    // esta funcion setea a null el atributo cliente del numero de la habitacion ingresada, de esta manera se desocupa la habitacion
    public void desocuparHabitacion(int numero_hab){
        Habitacion pointer = getHeadpiso();
        
        while (pointer != null){
            if (pointer.getNum() == numero_hab){
                pointer.setCliente_actual(null);
               
            }
            pointer = pointer.getNext();
        }
         
    }
}
