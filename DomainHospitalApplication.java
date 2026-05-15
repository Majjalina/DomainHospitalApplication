/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package domainhospitalapplication;

import javax.swing.SwingUtilities;

/**
 *
 * @author eebej
 */
public class DomainHospitalApplication {
    
      // Main method for testing    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         SwingUtilities.invokeLater(() -> {
            LoginUI menu = new LoginUI();
            menu.setVisible(true);
        });
     
    }    
}