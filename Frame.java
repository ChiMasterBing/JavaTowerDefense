
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
* Frame class using JFrame
*/
public class Frame extends JFrame { //ordinary JFrame

   public static String title = "Tower Defense"; //names the game
   public static Dimension dimension = new Dimension(600,675);
   /**
   * Initialize the Frame
   */
   public void init() {
      this.setLayout(new GridLayout(1,1,0,0)); //basic jframe operations
      Screen screen = new Screen();
      this.add(screen);
      this.setVisible(true);
      
   
   }
   /**
   * sets different settings of the frame when it is instantiated
   */
   public Frame() {  
      this.setTitle(title); //basic jframe operations
      this.setSize(dimension);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      init();
   }
}
