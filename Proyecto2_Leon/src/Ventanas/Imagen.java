/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author sebasalf
 */

public class Imagen extends javax.swing.JPanel {
    
    int x, y;

    public Imagen(JPanel jPanel1) {
        this.x = jPanel1.getWidth();
        this.y = jPanel1.getHeight();
        this.setSize(x, y);
    }

    @Override
    public void paint(Graphics graf) {
        ImageIcon Img = new ImageIcon(getClass().getResource("/Imagenes/Logo.png"));
        graf.drawImage(Img.getImage(), 0, 0, x, y, null);
    }    

}