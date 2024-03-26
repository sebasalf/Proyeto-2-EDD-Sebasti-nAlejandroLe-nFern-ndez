/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_Leon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author sebasalf
 */
public class AVL_Historico {
    private NodoHistorico raiz;

    public AVL_Historico() {
        this.raiz = null;
    }

    public NodoHistorico getRaiz() {
        return raiz;
    }
    
    //es un ,etodo que devuelve un string con los datos de todos los clientes que se alojaron en esa habitacion , para ello se le ingresa su numero
    public String printHistoryRoom(int numHabitacion) {
        String str = "";
        NodoHistorico nodoClient = raiz;
        int count = 0;

        while (nodoClient != null) {
            if (nodoClient.getCliente().getRoomNumber() == numHabitacion) {
                Historico pointer = nodoClient.getCliente();
                while (pointer != null){
                count ++;
                str += "\nCliente n."+count+"\n";
                str += "Nombre: "+pointer.getFirstName()+" "+ pointer.getLastName()+"\n";
                str += "Cedula: "+pointer.getDni()+"\n";
                str += "Email: " + pointer.getEmail() +"\n";
                str += "Genero: " + pointer.getGender()+"\n";
                str += "Fecha de llegada: "+ pointer.getCheckIn()+"\n";
                pointer = (Historico) pointer.getNext();
                }
            }

            if (numHabitacion < nodoClient.getCliente().getRoomNumber()) {
                nodoClient = nodoClient.getIzquierdo();
            } else {
                nodoClient = nodoClient.getDerecho();
            }
        }
        return str;
    }
    // este metodo busca el nodo cabeza de la lista de clientes historicos que se guarda en ese nodo, dado el numero de habitacion
    public Historico buscar(int num_hab) {
        NodoHistorico nodoActual = raiz;
        
        while (nodoActual != null) {
            if (num_hab < nodoActual.getCliente().getRoomNumber()) {
                nodoActual = nodoActual.getIzquierdo();
            } else if (num_hab > nodoActual.getCliente().getRoomNumber()) {
                nodoActual = nodoActual.getDerecho();
            } else {
                return nodoActual.getCliente();
            }
        }
        return null;
    }
    
    // esta funcion sirve solo para analizar los datos del txt y contruir los clientes historicos
    public void initABB_Historial(){
        AVL_Reserva Reservas = new AVL_Reserva();
        String line;
        String historico_txt = "";
        String path = "test\\historial.txt";
        File file = new File(path);
   
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    historico_txt += line + "\n";
                }
            }
            if (!"".equals(historico_txt)) {
                String[] historico_split = historico_txt.split("\n");

                for (int i = 1; i < historico_split.length; i++) {
                    String[] Cliente = historico_split[i].split(",");
                        
                    String dni = Cliente[0].replace(".", "");
                    String firstName = Cliente[1];
                    String lastName = Cliente[2];
                    String email = Cliente[3];
                    String gender = Cliente[4];
                    String checkIn = Cliente[5];
                    int roomNumber = Integer.parseInt(Cliente[6].trim());
                    
                    Historico cliente = new Historico(dni, firstName, lastName, email, gender, checkIn, roomNumber);
                    //aqui se verifica si el numero de la habitacion ya fue esta en un nodo, si lo esta, entonces el cliente historico solo se agrega al final de la lista de clientes historicos para ese nodo habitacion
                    if (buscar(roomNumber) != null){
                        Historico C_anterior = buscar(roomNumber);
                        while (C_anterior.getNext() != null){
                            C_anterior = (Historico) C_anterior.getNext();
                        }
                        C_anterior.setNext(cliente);
                    } else {
                        agregar(cliente);}
                    
                }               
            }
            br.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar base de Datos");
        }
    }
    
    // esta es la funcion para agrgar nodos al arbol, esta usando la funcion recursiva agregarRec para recorrer el arbol y analizar en que parte debe iensertarlo, del mismo modo calcula la altura del subarbol derecho y del izquierdo para ver si hace falta las rotaciones para mantener balanceado el arbol, al balanceralo aseguramos que con cada insercion el arbol se mantenga balanceado y que por recorrido o busquyeda tle tiempo de complejidad sea O(Log N) este metodo es una variante del metodo agregar de la clase AVL_Reserva
    public void agregar(Historico historico){
        this.raiz = agregarRec(raiz, historico);
    }
    //Funcion Recursiva del metodo agregar, se encuentra en privado para que al llamarlos los metodos de en otra clase, no salga esta la version recursiva sino la version agregar normaml
    private NodoHistorico agregarRec(NodoHistorico nodo, Historico cliente_historico) {
        
        //analiza hacia que direccion debe estar segun el numero dado
        if (nodo == null) {
            return new NodoHistorico(cliente_historico);
        } else if (cliente_historico.getRoomNumber() < nodo.getCliente().getRoomNumber()) {
            nodo.setIzquierdo(agregarRec(nodo.getIzquierdo(), cliente_historico));
        } else {
            nodo.setDerecho(agregarRec(nodo.getDerecho(), cliente_historico));
        }
        
        int alturaIzquierdo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getAltura() : 0;
        int alturaDerecho = (nodo.getDerecho() != null) ? nodo.getDerecho().getAltura() : 0;
        // calcula en factor balance atravez de las alturas del sub arbol derecho e izquierdo
        int factorBalance = alturaIzquierdo - alturaDerecho;
        
        //si el factor de balnace es diferente de 1 o -1 entonces balancea el arbol en la direccion que sea necesario
        if (factorBalance > 1) {
            if (cliente_historico.getRoomNumber() < nodo.getIzquierdo().getCliente().getRoomNumber()) {
                nodo = rotacionDerecha(nodo);
            } else {
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                nodo = rotacionDerecha(nodo);
            }
        } else if (factorBalance < -1) {
            if (cliente_historico.getRoomNumber() > nodo.getDerecho().getCliente().getRoomNumber()) {
                nodo = rotacionIzquierda(nodo);
            } else {
                nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
                nodo = rotacionIzquierda(nodo);
            }
        }
        //seteea la nueva altura para el el nodo en esa posicion
        nodo.setAltura(Math.max(alturaIzquierdo, alturaDerecho) + 1);
       
        return nodo;
    }
    
    //metodo privado que realiza la rotacion izquierda para mantenerl el arbol balanceado
    private NodoHistorico rotacionIzquierda(NodoHistorico nodo) {
        NodoHistorico nuevoNodo = nodo.getDerecho();
        nodo.setDerecho(nuevoNodo.getIzquierdo());
        nuevoNodo.setIzquierdo(nodo);
        
        nodo.setAltura(Math.max((nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getAltura() : 0,
                                (nodo.getDerecho() != null) ? nodo.getDerecho().getAltura() : 0) + 1);
        nuevoNodo.setAltura(Math.max(nodo.getAltura(),
                                     (nuevoNodo.getDerecho() != null) ? nuevoNodo.getDerecho().getAltura() : 0) + 1);
        
        return nuevoNodo;
    }
    // metodo privado que realiza la rotacion derecha para mantener el arbol bnalanceado
    private NodoHistorico rotacionDerecha(NodoHistorico nodo) {
        NodoHistorico nuevoNodo = nodo.getIzquierdo();
        nodo.setIzquierdo(nuevoNodo.getDerecho());
        nuevoNodo.setDerecho(nodo);
        
        nodo.setAltura(Math.max((nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getAltura() : 0,
                                (nodo.getDerecho() != null) ? nodo.getDerecho().getAltura() : 0) + 1);
        nuevoNodo.setAltura(Math.max(nodo.getAltura(),
                                     (nuevoNodo.getIzquierdo() != null) ? nuevoNodo.getIzquierdo().getAltura() : 0) + 1);
        
        return nuevoNodo;
    }
    
    //metodo que llama a su version recursiva para transformar todos los datos del arbol en una cadena de string, va recorriendo cada nodo y tambien los elementos en las listas de historicos, se usa para reescribir en los txt
    public String historicToSave() {
        return historicToSaveRec(raiz, "");
    }

private String historicToSaveRec(NodoHistorico nodo, String cadena) {
    if (nodo == null) {
        return cadena;
    }
    Historico h = nodo.getCliente();
    cadena = historicToSaveRec(nodo.getIzquierdo(), cadena);
    while (h != null){
    cadena += formatearCedula(h.getDni())+","+h.getFirstName()+","+h.getLastName()+","+h.getEmail()+","+h.getGender()+","+h.getCheckIn()+","+h.getRoomNumber()+"\n";
    h = h.getNext();
    }
    cadena = historicToSaveRec(nodo.getDerecho(), cadena);
    return cadena;
}

//este metodo formatea las cedulas deregreso a valor con puntos decimales, ya que durante todo este proyecto se manejo las cedulas como string o int sin esos puntos decimales, se usa solo para reescribir en los txt
public String formatearCedula(String cedula) {
    boolean esNumerico = true;

    for (int i = 0; i < cedula.length(); i++) {
        if (!Character.isDigit(cedula.charAt(i))) {
            esNumerico = false;
            break;
        }
    }

    if (esNumerico) {
        int valorNumerico = Integer.parseInt(cedula);
        DecimalFormat formato = new DecimalFormat("###,###,###");
        String cedulaFormateada = formato.format(valorNumerico).replace(",", ".");
        return cedulaFormateada;
    } else {
        return cedula;
    }
    }
}
