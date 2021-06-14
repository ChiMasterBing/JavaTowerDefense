
import java.awt.*;
/**
* The Projectile class
*/
public class Projectile extends Rectangle {
   private int target; //different properties of the projectile stored in variables
   /**
   * The type of projectile 
   */
   public int ProjectileId;
   /**
   * Projectile Speed 
   */
   public int moveSpeed = 15;
   public int moveFrame = 0;
   public int pSpeed = 5;
   public boolean exist = false;
   public int rx, ry;
   public int w = 10;
   public int h = 10;
   /**
   * Bomb status 
   */
   public int status;
   public int xc, yc;
   
   public Projectile() {
   
   }
   /**
   * spawns the projectile 
   * @param Id Type of projectile
   * @param x y position
   * @param y x position
   * @param target the target
   */
   public void spawn(int Id, int x, int y, int target) { //spawns the projectile
      ProjectileId = Id;
      setBounds(x+Screen.level.tileSize/2, y+Screen.level.tileSize/2, w, h);
         
      
      this.target = target; //parameter target is which enemy it is going to chase after and hit
      
      if (ProjectileId == Value.arrow) { //different types of projectiles each have different properties
         moveSpeed = 15;
         pSpeed = 10;
      }
      else if (ProjectileId == Value.bomb) {
         
         moveSpeed = 5;
         pSpeed = 5;
         
      }
      status = 0;
      exist = true; //existance = true so it can be drawn
   }
   /**
   * moves the projectile
   */
   public void move() {
      
      if (moveFrame == moveSpeed) { //how fast it moves
         if (status == 0) {            
            
            double a, b, c, d;    
            a = x - Screen.enemies[target].rx;
            b = y - Screen.enemies[target].ry;
            
            c = Math.sqrt((Math.pow(pSpeed, 2) * Math.pow(b, 2))/(Math.pow(a, 2) + Math.pow(b, 2)));
            //dervied forumla using distance formula and slope formula to decide how much the projectile moves in order to hit the target
            d = c * (a/b);
            
            if (b <= 0) {
               x = x + (int)d; //move the projectile
               y = y + (int)c;
            }   
            else {
               x = x - (int)d;
               y = y - (int)c;
            }
            moveFrame = 0;
            
            if ((Math.sqrt(Math.pow(x - Screen.enemies[target].rx, 2)+Math.pow(y - Screen.enemies[target].ry, 2)) <= 16) && (Screen.enemies[target].exist == true)) {
               //if the projectile is near the enemy then it means it hit the enemy
               if (ProjectileId == Value.arrow) {
                  Screen.enemies[target].health = Screen.enemies[target].health - 4.5; //subtracts hp from target
                  exist = false;
                  moveFrame = 0;
               }
               else if (ProjectileId == Value.bomb) {
                  xc = Screen.enemies[target].xc; 
                  x = Screen.level.tiles[yc][xc].x;
                  y = Screen.level.tiles[yc][xc].y;
                  Screen.level.tiles[yc][xc].explosion += 1;
                  
                  Screen.enemies[target].health = Screen.enemies[target].health - 5; //subtracts hp from target
                  status = 1;
                  moveSpeed = 150;
                  moveFrame = 0;  
               }
            }
            
         }
         //status is for the bombs showing which costume of the explosion
         else if (status == 1) {
            status = 2;
            moveFrame = 0;
            moveSpeed = 400;

         }
         else if (status == 2) {   
            status = 3;
            moveFrame = 0;
            moveSpeed = 250;
         }
         else if (status == 3) {
            
            Screen.level.tiles[yc][xc].explosion -= 1;
            exist = false;
            status = 0;
            moveFrame = 0;

         } 
      }
      else {       
         moveFrame++;
         
      }

   }
   /**
   * Draws the projectile
   * @param g Graphics 
   */
   public void draw(Graphics g) { //draws the projectile if it exists
      
      if (Screen.enemies[target].exist == false) {
         if (ProjectileId == Value.arrow || (ProjectileId == Value.bomb && status == 0)) {
            exist = false;
         }
         
      }
      if (exist == true && Screen.enemies[target].exist == true) { //if the projectile exists and the target is still alive
         
         if (ProjectileId == Value.arrow) {
            g.drawImage(Screen.projectile[ProjectileId-16], x, y, width, height, null);
         }
         else if (ProjectileId == Value.bomb) {
            
            if (status == 0) {   
               g.drawImage(Screen.projectile[ProjectileId-16], x, y, width, height, null);
            }
            if (status == 1) {
               g.drawImage(Screen.projectile[Value.explosion1-34], x, y, Screen.level.tileSize, Screen.level.tileSize, null);
            }
            else if (status == 2) {
               g.drawImage(Screen.projectile[Value.explosion2-34], x, y, Screen.level.tileSize, Screen.level.tileSize, null);
            }
            else if (status == 3) {
               g.drawImage(Screen.projectile[Value.explosion3-34], x, y, Screen.level.tileSize, Screen.level.tileSize, null);
            }     
         }
      }
   }
}
