/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brickbreaker;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
/**
 *
 * @author Acer
 */
public class BrickBreaker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        JFrame obj = new JFrame();
        Game gameplay = new Game();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Breakout Bricks");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
        
        
        
    }
    
}
