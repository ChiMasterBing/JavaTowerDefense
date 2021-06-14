
import java.awt.*;
/**
* The Level Class - Controls the tiles and how they make up the map
*/
public class Level { 
   public int worldWidth = 16;
   public int worldHeight = 16;
   
   public int tileSize = 32;
   
   public Tile tiles[][]; //array of tiles
   public int[][] map;
   /**
   * Instantiate Level
   */
   public Level() {
      define();
   }
   /**
   * Defines each tile inside the level
   */
   public void define() { //creates the array of tiles and fills it with tile objects, based on the map array generated by openMap
      tiles = new Tile[worldHeight][worldWidth];
      
      for (int i=0;i<worldWidth;i++) {
         for (int j=0;j<worldHeight;j++) {
            tiles[i][j] = new Tile((Screen.Width/2)-((worldWidth*tileSize)/2)+(j*tileSize),(Screen.Height/2)-((worldHeight*tileSize)/2)+(i*tileSize),tileSize,tileSize,openMap.map[i][j],Value.air);
            
         }
      }
      
   }   
   /**
   * Draws the level
   * @param g Graphics
   */
   public void draw(Graphics g) { //tells each tile in the array to draw itself
      for (int i=0;i<worldWidth;i++) {
         for (int j=0;j<worldHeight;j++) {
            tiles[i][j].draw(g);
         }
      }
   }  
}