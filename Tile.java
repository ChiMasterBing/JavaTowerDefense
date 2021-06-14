
import java.awt.*;
/**
* This is the Tile Class
*/
public class Tile extends Rectangle { //tile class
   public int groundId; //which ID is for the ground
   public int airId; 
   public int explosion = 0; //if the tile has an explosion on it
   /**
   * Instantiates the tile
   * @param x x position
   * @param y y position
   * @param height heigth
   * @param gid Ground Id
   * @param aid Air Id
   */
   public Tile(int x, int y, int width, int height, int gid, int aid) {
      setBounds(x,y,width,height);
      groundId = gid;
      airId = aid;
      
   }
   /**
   * Draws the Tile
   * @param g Graphics
   */
   public void draw(Graphics g) { //draws the tile based on the groundId
      
      g.drawImage(Screen.ground[groundId], x, y, width, height, null);
      g.drawRect(x,y,width,height);

   }
}
