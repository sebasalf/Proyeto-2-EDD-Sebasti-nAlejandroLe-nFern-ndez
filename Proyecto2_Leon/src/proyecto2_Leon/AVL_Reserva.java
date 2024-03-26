/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_Leon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author sebasalf
 */
public class AVL_Reserva {
    private NodoReserva raiz;
    
    public AVL_Reserva(){
        this.raiz = null;
    }

    public NodoReserva getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoReserva raiz) {
        this.raiz = raiz;
    }
    
    public boolean isEmpty() {
        return getRaiz() == null;
    }
    
    //primera version del metodo buscar, se usa para buscar un cliente reserva a travez de su metodo, difere del metodo buscar2 en que este usa un while para recorrer el arbol
    public Reserva buscar(int cedula) {
        NodoReserva nodoActual = raiz;
        
        while (nodoActual != null) {
            if (cedula < nodoActual.getReserva().getCedula()) {
                nodoActual = nodoActual.getIzquierdo();
            } else if (cedula > nodoActual.getReserva().getCedula()) {
                nodoActual = nodoActual.getDerecho();
            } else {
                return nodoActual.getReserva();
            }
        }
        return null;
    }
    //metodo que lee el txt de reservas para crear los clientes reservas de la clase Reserva y despues los agrega al arbol balanceado
    public void initABB_Reserva(){
        AVL_Reserva Reservas = new AVL_Reserva();
        String line;
        String clientes_txt = "";
        String path = "test\\reservas.txt";
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
                        
                        int cedula = Integer.parseInt(Cliente[0].replace(".",""));
                        String primer_nombre = Cliente[1];
                        String apellido = Cliente[2];
                        String email = Cliente[3];
                        String genero = Cliente[4];
                        String tipo_hab = Cliente[5];
                        String celular = Cliente[6];
                        String[] llegada_aux = Cliente[7].split("/");
                        int[] llegada = new int[]{Integer.parseInt(llegada_aux[0]),Integer.parseInt(llegada_aux[1]),Integer.parseInt(llegada_aux[2])};
                        String[] salida_aux = Cliente[8].split("/");
                        int[] salida = new int[]{Integer.parseInt(salida_aux[0]),Integer.parseInt(salida_aux[1]),Integer.parseInt(salida_aux[2])};
         
                        Reserva nuevo_cliente = new Reserva(cedula, primer_nombre, apellido, email, genero, tipo_hab, celular, llegada, salida);
                       
                        agregar(nuevo_cliente);

                }               
            }
            br.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar base de Datos");
        }
    }
    
    //Metodo que imprime todos los apellidos de los elementos en el arbol, no se uso en el proyecto, solo se uso para las pruebas
    public void preOrden(NodoReserva raiz) {
        if (raiz != null) {
            System.out.println("[ " + raiz.getReserva().getApellido() + " ]");
            preOrden(raiz.getIzquierdo());
            preOrden(raiz.getDerecho());
        }
    }
    
    //Agregar es una funcion que llama a la version recursiva de si misma para ir agregando los clientes Reservas al arbol balanceado
    public void agregar(Reserva reservacion) {
        raiz = agregarRec(raiz, reservacion);
    }
    //buscar2 es la segunda version del metodo buscar, en esta ser usa funciones recursivas para recorrer el arbol balanceado, por lo que se asegura que la busqueda de una reserva por su numero de cedula cumpla con O(Log N) esta es la que se uso como final en el proyecto
    public Reserva buscar2(int cedula) {
        return buscarRec(raiz, cedula);
    }
    //version recursiva del metddo agregar, mantiene el balance del arbol en cada insercion con los metodos de rotacion derecha y rotacion izquiera, calculando su alturas y decidiendo cual usar a travez del factor de balance
    private NodoReserva agregarRec(NodoReserva nodo, Reserva reservacion) {
       //analiza hacia que direccion debe estar segun el numero dado
        if (nodo == null) {
            return new NodoReserva(reservacion);
        } else if (reservacion.getCedula() < nodo.getReserva().getCedula()) {
            nodo.setIzquierdo(agregarRec(nodo.getIzquierdo(), reservacion));
        } else {
            nodo.setDerecho(agregarRec(nodo.getDerecho(), reservacion));
        }
        
        int alturaIzquierdo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getAltura() : 0;
        int alturaDerecho = (nodo.getDerecho() != null) ? nodo.getDerecho().getAltura() : 0;
      
       // calcula en factor balance atravez de las alturas del sub arbol derecho e izquierdo
      
        int factorBalance = alturaIzquierdo - alturaDerecho;
        //si el factor de balnace es diferente de 1 o -1 entonces balancea el arbol en la direccion que sea necesario
        if (factorBalance > 1) {
            if (reservacion.getCedula() < nodo.getIzquierdo().getReserva().getCedula()) {
                nodo = rotacionDerecha(nodo);
            } else {
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                nodo = rotacionDerecha(nodo);
            }
        } else if (factorBalance < -1) {
            if (reservacion.getCedula() > nodo.getDerecho().getReserva().getCedula()) {
                nodo = rotacionIzquierda(nodo);
            } else {
                nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
                nodo = rotacionIzquierda(nodo);
            }
        }
        
        nodo.setAltura(Math.max(alturaIzquierdo, alturaDerecho) + 1);
       
        return nodo;
    }
    
    //parte recursiva del metodo buyscar2
    private Reserva buscarRec(NodoReserva nodo, int cedula) {
        if (nodo == null) {
            return null;
        } else if (nodo.getReserva().getCedula() == cedula) {
            return nodo.getReserva();
        } else if (cedula < nodo.getReserva().getCedula()) {
            return buscarRec(nodo.getIzquierdo(), cedula);
        } else {
            return buscarRec(nodo.getDerecho(), cedula);
        }
    }
    // metodo que hace la rpotacion izquierda del arvol segun el nodo insertado y el factor balance
    private NodoReserva rotacionIzquierda(NodoReserva nodo) {
        NodoReserva nuevoNodo = nodo.getDerecho();
        nodo.setDerecho(nuevoNodo.getIzquierdo());
        nuevoNodo.setIzquierdo(nodo);
        
        nodo.setAltura(Math.max((nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getAltura() : 0,
                                (nodo.getDerecho() != null) ? nodo.getDerecho().getAltura() : 0) + 1);
        nuevoNodo.setAltura(Math.max(nodo.getAltura(),
                                     (nuevoNodo.getDerecho() != null) ? nuevoNodo.getDerecho().getAltura() : 0) + 1);
        
        return nuevoNodo;
    }
    // metodo que hace la rpotacion derecha del arvol segun el nodo insertado y el factor balance
    private NodoReserva rotacionDerecha(NodoReserva nodo) {
        NodoReserva nuevoNodo = nodo.getIzquierdo();
        nodo.setIzquierdo(nuevoNodo.getDerecho());
        nuevoNodo.setDerecho(nodo);
        
        nodo.setAltura(Math.max((nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getAltura() : 0,
                                (nodo.getDerecho() != null) ? nodo.getDerecho().getAltura() : 0) + 1);
        nuevoNodo.setAltura(Math.max(nodo.getAltura(),
                                     (nuevoNodo.getIzquierdo() != null) ? nuevoNodo.getIzquierdo().getAltura() : 0) + 1);
        
        return nuevoNodo;
    }
    
    
    // funcion recursiva que elimina un nodo del arbol, se usa para el checkin ya que se toma la reserva y despues se convierta un elemento Estado para insertarlo en la hastable, para ello se usa esta funcion para borrar el elemento reserva asociado a ese cliente
    public void eliminar(int cedula) {
    raiz = eliminarRec(raiz, cedula);
    }
    // parte recursuva de eliminar
    private NodoReserva eliminarRec(NodoReserva nodo, int cedula) {
    if (nodo == null) {
        return null;
    } else if (cedula < nodo.getReserva().getCedula()) {
        nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), cedula));
    } else if (cedula > nodo.getReserva().getCedula()) {
        nodo.setDerecho(eliminarRec(nodo.getDerecho(), cedula));
    } else {
        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) { //el nodo no tiene hijos
            return null;
        } else if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) { // el nodo tiene un hijo
            NodoReserva nuevoNodo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();
            nodo.setReserva(nuevoNodo.getReserva());
            nodo.setIzquierdo(nuevoNodo.getIzquierdo());
            nodo.setDerecho(nuevoNodo.getDerecho());
        } else { // el nodo tiene dos hijos
            NodoReserva sucesor = sucesorInmediato(nodo.getDerecho());
            nodo.setReserva(sucesor.getReserva());
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor.getReserva().getCedula()));
        }
    }

    if (nodo != null) {
        int alturaIzquierdo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getAltura() : 0;
        int alturaDerecho = (nodo.getDerecho() != null) ? nodo.getDerecho().getAltura() : 0;
        int factorBalance = alturaIzquierdo - alturaDerecho;

        if (factorBalance > 1) { // Desbalance hacia la izquierda
            if (nodo.getIzquierdo().getDerecho() != null && (nodo.getIzquierdo().getDerecho()).getAltura() > (nodo.getIzquierdo().getIzquierdo() != null ? nodo.getIzquierdo().getIzquierdo().getAltura() : 0)) {
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
            }
            nodo = rotacionDerecha(nodo);
        } else if (factorBalance < -1) { // Desbalance hacia la derecha
            if (nodo.getDerecho().getIzquierdo() != null && (nodo.getDerecho().getIzquierdo()).getAltura() > (nodo.getDerecho().getDerecho() != null ? nodo.getDerecho().getDerecho().getAltura() : 0)) {
                nodo.setDerecho(rotacionDerecha(nodo.getDerecho().getDerecho()));
            }
            nodo = rotacionIzquierda(nodo);
        }

        // Actualizar altura
        alturaIzquierdo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getAltura() : 0;
        alturaDerecho = (nodo.getDerecho() != null) ? nodo.getDerecho().getAltura() : 0;
        nodo.setAltura(Math.max(alturaIzquierdo, alturaDerecho) + 1);
    }

    return nodo;
}
// es una funcion que calcula
private NodoReserva sucesorInmediato(NodoReserva nodo) {
    while (nodo.getIzquierdo() != null) {
        nodo = nodo.getIzquierdo();
    }
    return nodo;
}
//metodo recursivo qye transforma todas las reservas a un string con el formato colocado para mostrar la informacion de las reservas
public String reservasToString() {
    return reservasToString(raiz, "");
}

private String reservasToString(NodoReserva nodo, String cadena) {
    if (nodo == null) {
        return cadena;
    }

    cadena = reservasToString(nodo.getIzquierdo(), cadena);
    cadena += nodo.getReserva().printCliente()+ "\n";
    cadena = reservasToString(nodo.getDerecho(), cadena);

    return cadena;
}

//metodo que transforma todos los datos de reserva a unstring para reescribirlos en los txt
public String reservasToSave() {
    return reservasToSaveRec(raiz, "");
}

private String reservasToSaveRec(NodoReserva nodo, String cadena) {
    if (nodo == null) {
        return cadena;
    }

    cadena = reservasToSaveRec(nodo.getIzquierdo(), cadena);
    cadena += formatearCedula(nodo.getReserva().getCedula())+","+nodo.getReserva().getNombre()+","+nodo.getReserva().getApellido()+","+nodo.getReserva().getEmail()+","+nodo.getReserva().getGender()+","+nodo.getReserva().getTipo_habitacion()+","+nodo.getReserva().getCelular()+","+nodo.getReserva().getLlegada()[0]+"/"+nodo.getReserva().getLlegada()[1]+"/"+nodo.getReserva().getLlegada()[2]+","+nodo.getReserva().getSalida()[0]+"/"+nodo.getReserva().getSalida()[1]+"/"+nodo.getReserva().getSalida()[2]+ "\n";
    cadena = reservasToSaveRec(nodo.getDerecho(), cadena);

    return cadena;
}
//metodo que transforma las cedulas devuelta a formato con puntos decimales, durante el pryecto se utiliza cedula en valor int, pero comose guarda en los txt como valor con punto decimal se usa esta funcion [ara psarlo de vuelta
public String formatearCedula(int cedula) {
    DecimalFormat formato = new DecimalFormat("###,###,###");
    String cedulaFormateada = formato.format(cedula).replace(",", ".");
    return cedulaFormateada;
}

}
