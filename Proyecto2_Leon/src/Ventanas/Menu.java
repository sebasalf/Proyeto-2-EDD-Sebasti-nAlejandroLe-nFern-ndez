/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import java.io.File;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import proyecto2_Leon.AVL_Reserva;
import proyecto2_Leon.AVL_Historico;
import proyecto2_Leon.Estado;
import proyecto2_Leon.HashTableEstadoActual;
import proyecto2_Leon.ListaHabitacion;

/**
 *
 * @author sebasalf
 */
public class Menu extends javax.swing.JFrame {
    static HashTableEstadoActual Estado;
    static AVL_Reserva Reserva;
    static AVL_Historico Historico;
    static ListaHabitacion list_habitaciones;
    /**
     * Creates new form Menu
     */
    public Menu(HashTableEstadoActual Estado, AVL_Reserva Reserva, AVL_Historico Historico, ListaHabitacion list_habitaciones) {
        initComponents();
        this.Estado = Estado;
        this.Reserva = Reserva;
        this.Historico = Historico;
        this.list_habitaciones = list_habitaciones;

        this.setLocationRelativeTo(null); // Se utiliza para centrar la pantalla
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RegistrarUsuario = new javax.swing.JButton();
        BuscarReservación = new javax.swing.JButton();
        HistorialHabitación = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        Logo = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RegistrarUsuario.setText("Registrar Usuario");
        RegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(RegistrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 200, -1));

        BuscarReservación.setText("Buscar Reservación");
        BuscarReservación.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarReservaciónActionPerformed(evt);
            }
        });
        getContentPane().add(BuscarReservación, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 200, -1));

        HistorialHabitación.setText(" Historial de Habitación  ");
        HistorialHabitación.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistorialHabitaciónActionPerformed(evt);
            }
        });
        getContentPane().add(HistorialHabitación, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 200, -1));

        jButton6.setText("Guardar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jButton4.setText("Check-in");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jButton5.setText("Check-out");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logo.png"))); // NOI18N
        getContentPane().add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, -2, 290, -1));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarReservaciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarReservaciónActionPerformed
        this.setVisible(false);
        MenuReserva windowReserva = new MenuReserva(Estado, Reserva, Historico, list_habitaciones);
        windowReserva.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BuscarReservaciónActionPerformed

    private void HistorialHabitaciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistorialHabitaciónActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        MenuHistorico windowHistoric = new MenuHistorico(Estado, Reserva, Historico, list_habitaciones);
        windowHistoric.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_HistorialHabitaciónActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        CheckIn checkin = new CheckIn(Estado, Reserva, Historico, list_habitaciones);
        checkin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        CheckOut checkout = new CheckOut(Estado, Reserva, Historico, list_habitaciones);
        checkout.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarUsuarioActionPerformed
        this.setVisible(false);
        MenuEstado windowEstado = new MenuEstado(Estado, Reserva, Historico, list_habitaciones);
        windowEstado.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegistrarUsuarioActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        try{
            if (Reserva != null){
            String reservas = "ci,primer_nombre,segundo_nombre,email,genero,tipo_hab,celular,llegada,salida\n";
            String datosReservas = Reserva.reservasToSave();
            PrintWriter pw = new PrintWriter("test\\reservas.txt");
            pw.print(reservas+datosReservas);
            pw.close();
            
            }
            
            if(Estado != null){
                String estados = "num_hab,primer_nombre,apellido,email,genero,celular,llegada\n";
                for (int i = 0; i< Estado.getSize(); i++){
                    if (Estado.getArray_reservas()[i] != null){
                        Estado pointer = Estado.getArray_reservas()[i];
                        while (pointer != null) {
                            String num;
                            if (pointer.getNum_habitacion() == -1){
                            num = "";
                            } else{
                            num = Integer.toString(pointer.getNum_habitacion());
                            }
                            estados += num+","+pointer.getNombre()+","+pointer.getApellido()+","+pointer.getEmail()+","+pointer.getGender()+","+pointer.getCelular()+","+pointer.getLlegada()[0]+"/"+pointer.getLlegada()[1]+"/"+pointer.getLlegada()[2]+"\n";
                            pointer = pointer.getNext();
                        }
                        
                    }
                    
                }
                PrintWriter pw = new PrintWriter("test\\estado.txt");
                pw.print(estados);
                pw.close();
            }
            if(Historico != null){
                String historico = "ci,primer_nombre,apellido,email,genero,llegada,num_hab\n";
                String datosHistorico = Historico.historicToSave();
                PrintWriter pw = new PrintWriter("test\\historial.txt");
                pw.print(historico+datosHistorico);
                pw.close();
            }
        JOptionPane.showMessageDialog(null, "Guardado exitoso!");
            
            
        } catch (Exception err){
            JOptionPane.showMessageDialog(null, err);
        }       
       
    }//GEN-LAST:event_jButton6ActionPerformed

    public HashTableEstadoActual getEstado() {
        return Estado;
    }

    public void setEstado(HashTableEstadoActual Estado) {
        this.Estado = Estado;
    }

    public AVL_Reserva getReserva() {
        return Reserva;
    }

    public void setReserva(AVL_Reserva Reserva) {
        this.Reserva = Reserva;
    }

    public AVL_Historico getHistorico() {
        return Historico;
    }

    public void setHistorico(AVL_Historico Historico) {
        this.Historico = Historico;
    }

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu(Estado, Reserva, Historico, list_habitaciones).setVisible(true);

            }
        });
    }
    
    public void initDatos(){
    HashTableEstadoActual e = new HashTableEstadoActual();
    e.initHashTableEstado();
    Menu.Estado = e;
    
    AVL_Reserva r = new AVL_Reserva();
    r.initABB_Reserva();
    Menu.Reserva = r;
    
    AVL_Historico h = new AVL_Historico();
    h.initABB_Historial();
    Menu.Historico = h;
    
    ListaHabitacion l = new ListaHabitacion();
    l.initlistaHabitaciones();
    l.add_client_to_hab(Estado);
    Menu.list_habitaciones = l;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscarReservación;
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton HistorialHabitación;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton RegistrarUsuario;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    // End of variables declaration//GEN-END:variables
}
