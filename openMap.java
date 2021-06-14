
import java.util.*; //File reader for map file in the maps folder
import java.io.*;
/**
* openMap class  - opens up a map based on a txt file inside the maps folder
*/
public class openMap {
   public static int map[][] = new int[16][16]; //array of values which represent the map
   /**
   * open the map 
   */
   public static void openMap() {
      readFile();
      
   }
   /**
   * Converts the array of strings into an array of ints
   * @param array array of strings representing the map
   * @return returns an int array
   */
   public static int[] convertArray(String[] array) { 
      int[] temp = new int[array.length];
      for (int i = 0; i<array.length; i++) {
         temp[i] = Integer.parseInt(array[i]);
      }
      return temp;   
   }
   /**
   * Reads the file like a normal scanner
   */
   public static void readFile() { 
      Scanner infile;
      File file;
      try {
         file = new File("maps/map1.txt");
         infile = new Scanner(file);
         for (int i = 0; i<map.length; i++) {
            map[i] = convertArray(infile.nextLine().split(" ", map[i].length));          
         }
      }
      catch(FileNotFoundException e) {
         System.out.println("The file could not be found.");
         System.exit(0);
      }
      
   }
}
