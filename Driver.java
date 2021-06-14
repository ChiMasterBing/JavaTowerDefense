
/**
* This class is run to start the program.
*/
public class Driver { //Driver - Run from here
   /**
   * The main method 
   * @param args for main method
   */
   public static void main(String args[]) {
      openMap.openMap();  //Call the openMap class to open the map file 
      Frame frame = new Frame(); //new Frame
      frame.setResizable(false); //settings for frame
      frame.setLocationRelativeTo(null);
          
   }
}
