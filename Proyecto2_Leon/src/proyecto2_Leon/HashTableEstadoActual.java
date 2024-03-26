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
//Clase hashtable que almacena todos los Estados clientes actuales del hotel, usando el metodo de encadenamiento
public class HashTableEstadoActual {
    private Estado[] array_estado;
    private int size;
    
    public HashTableEstadoActual(){
        this.size = 3000;
        this.array_estado = new Estado[size];
    }

    public Estado[] getArray_reservas() {
        return array_estado;
    }

    public void setArray_reservas(Estado[] array_reservas) {
        this.array_estado = array_reservas;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    //V1 de la funcion hashTable toma el tipo de objeto Estado para tomar su nombre y apellido y juntarlos para formar un index basado en el tipo de funcion hash, tranforma a ascii cada caracter del nombre para despues sumarlo al valor index, despue slo multiplica por el indice del string y lo suma index actual, el resultado se divide % con el tamano del la hastable y el resultado siempre dara un numero que se encuentra entre el tamano del array de hashtable, ese numero resultante es el index en que se encuentra
    public int hashFunction(Estado cliente){
        String cliente_nom = cliente.getNombre()+cliente.getApellido();
        int index = 0;
        for (int i = 0; i < cliente_nom.length(); i++) {
            int contador = cliente_nom.codePointAt(i);
            index += cliente_nom.codePointAt(i);
            index = index + (contador * i);
        }
        index = index % this.getSize();
        return index;
    }
    // lo mismo que la funcion anterior, hashfunction pero se le pasa de parametro los strings de nombr ey apellido dle cliente de una vez
    public int hashFunctionString(String nombre_apellido){
        String cliente_nom = nombre_apellido;
        int index = 0;
        for (int i = 0; i < cliente_nom.length(); i++) {
            int contador = cliente_nom.codePointAt(i);
            index += cliente_nom.codePointAt(i);
            index = index + (contador * i);
        }
        index = index % this.getSize();
        return index;
    }
    // v2 del metodo hashfunction investigue que para crear funciones hash que codifiquen el string ingresado puede ser muy eficiente para evitar colisiones el numero 31 ya que al parecer matematicamente tiene propiedades unicas en este tipo de funciones, esta version nos se uso pero me parece fino ponerlo por la ivestigacion, esta funcion no funciona con string muy largos
    public int hashFunction2(Estado c){
        int hash = 0;
        int p = 31;
        int m = 1000000009;
        String str = c.getNombre();
        System.out.println(str);
        for (int i = 0; i < str.length() ; i++) {
            hash = (int)((hash + (long)Math.pow(p, i) * str.charAt(i)) % m);
        }
    return hash % getSize();
    }
    //V3 de las funcion hashfuntion otra investigacion de como hacer otro tipo de funcion hash para codificar, no funciona tampoco con strings larlos y tampoco se uso en el proyecto
    public int hashFunction3(Reserva c) {
        String s = c.getNombre();
        int hashValue = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char d = s.charAt(i);
            hashValue = (hashValue * 31 + d) % getSize();
        }
        return hashValue;
    }
    public boolean isInHash(Estado c){
        boolean aux = false;
        for (int i = 0; i < this.getArray_reservas().length; i++) {
            if ( getArray_reservas()[i] != null){
                if ( getArray_reservas()[i].getNombre() == c.getNombre() && getArray_reservas()[i].getApellido() == c.getApellido()){
                    aux = true;
                }
            }
        }
        return aux;
    }
    
    //metodo que inciia todos los datos y crea los objestos Estado para la hashtbale
    public void initHashTableEstado(){
        String line;
        String clientes_txt = "";
        String path = "test\\estado.txt";
        File file = new File(path);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    clientes_txt += line + "\n";
                }
            }
            if (!"".equals(clientes_txt)) {
                String[] clientes_split = clientes_txt.split("\n");

                for (int i = 1; i < clientes_split.length; i++) {
                        String[] Cliente = clientes_split[i].split(",");
                  
                        int num_habitacion;
                        if ("".equals(Cliente[0])){
                           num_habitacion = -1;
                        } else {num_habitacion = Integer.parseInt(Cliente[0]);}
          
                        String primer_nombre = Cliente[1];
                        String apellido = Cliente[2];
                        String email = Cliente[3];
                        String genero = Cliente[4];
                        String celular = Cliente[5];
                        String[] llegada_aux = Cliente[6].split("/");
                        int[] llegada = new int[]{Integer.parseInt(llegada_aux[0]),Integer.parseInt(llegada_aux[1]),Integer.parseInt(llegada_aux[2])};
         
                        Estado nuevo_cliente = new Estado(num_habitacion, primer_nombre, apellido, email, genero, celular, llegada);
                        int index1 = hashFunction(nuevo_cliente);    
                      
                        insertEstado(nuevo_cliente, index1);
                        
                }
               
            }
            br.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar base de Datos");
        }
    }
        
    //funcion que inserta el Etado en el indice dado(calculado prefiamente con hashfunction) y como es una hashtable encadenada busca si es el ultimo de la lista para insertarlo despues de ese.
    public void insertEstado(Estado cliente, int index) {
        
        if (this.array_estado[index] == null) {
            this.array_estado[index] = cliente;
        } else {
            Estado pointer = array_estado[index];
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(cliente);
        }
    }
    //metodo que busca el Estado a travez de su nombre y apellido, busvando en el index atravez d ehashfunction y a su vez en las listas en ese index
    public Estado getEstado(String nombre, String apellido) {
        int index = hashFunctionString(nombre+apellido);
        Estado pointer = array_estado[index];
        while (pointer != null) {
            if (pointer.getNombre().equals(nombre) && pointer.getApellido().equals(apellido)) {
                return pointer;
            }
            pointer = pointer.getNext();
        }
        return null;
    }
    
    // funcion que recorre va al indice en que se encuentra guardado ese nombre y appelido y va recorriendo la lista de Estados para encontrar el que coincide y eliminarlo
    public void eliminarEstado(String nombre, String apellido) {
    int index = hashFunctionString(nombre+apellido);

        if (array_estado[index] != null) {
            Estado pointer = null;
            Estado pointer2 = array_estado[index];

            while (pointer2 != null) {
                if (pointer2.getNombre().equals(nombre) &&
                pointer2.getApellido().equals(apellido)) {
                    if (pointer == null) {
                        array_estado[index] = pointer2.getNext();
                    } else {
                        pointer.setNext(pointer2.getNext());
                    }
                    return;
                }
                pointer = pointer2;
                pointer2 = pointer2.getNext();
            }
        }
    }
    
    
}