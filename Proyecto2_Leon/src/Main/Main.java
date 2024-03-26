/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Ventanas.Menu;

/**
 *
 * @author acarr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Menu window = new Menu(null, null, null, null);
        window.initDatos();
        window.setVisible(true);
        
    }
    
}
