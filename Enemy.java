
import java.awt.*; 
/**
* This is the Enemy class.
*/
public class Enemy extends Rectangle { //Extending rectangle for easy implementation of graphics
   public int EnemyId;
   public int EnemyNum;
   /**
   * Enemy Size 
   */
   public int size = 32;
   public int xc;
   public int yc;
   public boolean exist = false;
   
   public int walkFrame = 0; //used to implement speed
   /**
   * Enemy Speed
   */
   public int walkSpeed = 0; //speed
   public int speed = 0; //keeps track of original speed
   
   public int tileX = 0; //the tile coordinates
   public int tileY = 0;
   
   public int rx; //the real GUI x and y values
   public int ry;
   /**
   * Enemy Health
   */
   public double health; 
   /**
   * enemy direction
   */
   public int dir = 0; 
   
   private int animationFrame = 0;  //animations 
   private int animationSpeed;
   private int animation = 0;
   /**
   * Instantiation of Enemy
   * @param num Enemy Number of the array in the screen class
   */
   public Enemy(int num) { 
      EnemyNum = num; //tells the enemy object which number it is in the screen array of enemies
   }
   /**
   * Spawns the enemy
   * @param Id the type of enemy
   */
   public void spawn(int Id) {
      EnemyId = Id; //Tells the enemy object which type of enemy it is
      if (EnemyId == 0) {
         walkSpeed = 8;
         speed = walkSpeed;
         health = 15;
      }
      if (EnemyId == 1) {
         walkSpeed = 2;
         speed = walkSpeed;
         health = 25;
      }
      if (EnemyId == 2) {
         walkSpeed = 4;
         speed = walkSpeed;
         health = 175;
      }
      if (EnemyId == 3) {
         walkSpeed = 6;
         speed = walkSpeed;
         health = 500;
      }
      else if (EnemyId == 4) {
         walkSpeed = 10;
         speed = walkSpeed;
         health = 10000;
         size = 100;
      }
      else if (EnemyId == 5) {
         walkSpeed = 1;
         speed = walkSpeed;
         health = 150; 
         animationSpeed = 35; 
         animation = 5;
      }
      else if (EnemyId == 7) {
         walkSpeed = 15;
         speed = walkSpeed;
         health = 3500;
         size = 60;
      }
      for (int i=0; i<Screen.level.tiles.length; i++) { //this is for if the enemy is larger than the tileSize
         if (EnemyId == 4 || EnemyId == 7) {
            if (Screen.level.tiles[i][0].groundId == Value.entrance) {
               xc = 0;
               yc = 1;
               rx = x+size/2;
               ry = y+size/2;
               setBounds(Screen.level.tiles[i][0].x-(size-Screen.level.tileSize)/2, Screen.level.tiles[i][0].y-(size-Screen.level.tileSize)/2, size, size);
            }
         }
         else { //if the enemy size is the tileSize
            if (Screen.level.tiles[i][0].groundId == Value.entrance) { //spawn at entrance
               setBounds(Screen.level.tiles[i][0].x, Screen.level.tiles[i][0].y, size, size); //setBounds from rectangle
               xc = 0;
               yc = 1;
               rx = x+size/2;
               ry = y+size/2;
            }
         }  
      }
      exist = true; //sets the exist value to true once the enemy is spawned so it can be drawn 
   }
   /**
   * Resets the Enemy
   */
   public void reset() { //reset the values for when the enemy "dies"
      xc = 0;
      yc = 0;
      rx = 0;
      ry = 0;
      health = 0;
      EnemyId = 0;
      dir = 0;
      tileX = 0;
      tileY = 0;
      walkFrame = 0;
      walkSpeed = 0;
      size = 32;
      exist = false;
   }
   /**
   * Moves the Enemy
   */
   public void move() { //moves the enemy
      if (exist = true) {         
         if (walkFrame >= walkSpeed) { //to implement how fast the enemy moves
            for (int i=0; i<Screen.towers.length; i++) {
               if (Screen.towers[i].TowerId == Value.iceTower && Screen.towers[i].attacking == true) {
                  int k = Screen.towers[i].targets.length;
                  for (int j=0; j<Screen.towers[i].targets.length; j++) {
                     if (Screen.towers[i].targets[j] == EnemyNum) {
                        k--;
                        walkSpeed = speed * 2; //this massive loop is to detect if the ice tower is shooting it
                        break;
                     }
                  }
                  if (k == Screen.towers[i].targets.length) {
                     walkSpeed = speed; 
                  }  
               }
            }
            health -= Screen.level.tiles[yc][xc].explosion * 0.3; //if the enemy is walking on a tile with explosion, health will be removed
            
            
            if (dir == 0) { //moves the enemy by changing x and y based on the direction it is facing
               x++;
               tileX++;
               
               if (tileX == Screen.level.tileSize) {
                  xc++;
                  tileX = 0;       
               } 
            }
            else if (dir == 90) {
               y--;
               tileY++;
               
               if (tileY == Screen.level.tileSize) {
                  yc--;
                  tileY = 0;
               }
            }
            else if (dir == 180) {
               x--;
               tileX++;
               if (tileX == Screen.level.tileSize) {
                  xc--;
                  tileX = 0;
               }
            }
            else if (dir == 360) {
               y++;
               tileY++;
               if (tileY == Screen.level.tileSize) {
                  yc++;
                  tileY = 0; 
               }
            }
            
            if (Screen.level.tiles[yc][xc].groundId == Value.topRightPath) { //if the enemy is on a corner tile, it is indicating that it has to turn
               dir = 360;                                                    //it turns by changing direction
            }  
            else if (Screen.level.tiles[yc][xc].groundId == Value.bottomRightPath) {
               dir = 180;
            }
            else if (Screen.level.tiles[yc][xc].groundId == Value.topLeftPath) {
               dir = 360; 
            }
            else if (Screen.level.tiles[yc][xc].groundId == Value.bottomLeftPath) {
               dir = 0;  
            }
            else if (Screen.level.tiles[yc][xc].groundId == Value.gem) { //if the enemy has reached the end decrease player hp
               
               if (EnemyId == 4) {
                  Screen.health -= 10; //big boss enemy removes more hp
               }
               else {
                  Screen.health--;
               }
               
               exist = false; //set exist to false so it doesnt get drawn
               reset();
            }
            walkFrame = 0;
            rx = x+size/2;
            ry = y+size/2;
         }   
         else {
            walkFrame++;
         }
      }     
   }
   /**
   * Detects if the Enemy health is below 0
   */
   public void detect() { //detects if it is dead and if it is, give the player coins
      if (health <= 0) {
         exist = false;
         if (EnemyId == 0) {
            Screen.coin += 2.5 * Math.pow(0.8, Round.round - 10);
         }
         else if (EnemyId == 1) {
            Screen.coin += 5 * Math.pow(0.8, Round.round - 10);
         }
         else if (EnemyId == 2) {
            Screen.coin += 10 * Math.pow(0.8, Round.round - 10);
         }
         else if (EnemyId == 3) {
            Screen.coin += 25 * Math.pow(0.8, Round.round - 10);
         }
         else if (EnemyId == 4) {
            Screen.coin += 1000 * Math.pow(0.8, Round.round - 10);
         }
         else if (EnemyId == 5) {
            Screen.coin += 50 * Math.pow(0.8, Round.round - 10);
         }
         else if (EnemyId == 7) {
            Screen.coin += 200 * Math.pow(0.8, Round.round - 10);
         }
         reset();
         
      }
   }
   /**
   * Draws the Enemy using java graphics
   * @param g Graphics 
   */
   public void draw(Graphics g) { //draws the enemy
      if (exist == true) {
         if (EnemyId != 5) {
         
            if ((dir == 0) || (dir == 360)) {
               g.drawImage(Screen.enemy[EnemyId], x, y, width, height, null);
            }
            else {
               g.drawImage(Screen.enemy[EnemyId], x + width, y, -width, height, null);
            }
         }
      
         else {
            if ((dir == 0) || (dir == 360)) { //animation
               g.drawImage(Screen.enemy[animation], x, y, width, height, null);
            }
            else {
               g.drawImage(Screen.enemy[animation], x + width, y, -width, height, null);
            }
            if (animationFrame == animationSpeed) {
               animationFrame = 0;
               if (animation == 6) {
                  animation = 5; //animation variable sets the costume that is drawn
               }
               else if (animation == 5) {
                  animation = 6;
               }
            }
            else {
               animationFrame++;
            }
         
         }
      }
   }
}
