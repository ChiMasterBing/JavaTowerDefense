
import java.awt.*; //tower class - 4 different types of towers - laser, arrow, ice, bomb
/**
* This is the tower class
*/
public class Tower extends Rectangle  {
   public int TowerId; //for which type of tower
   public int xc;
   public int yc;
   public int size = 32;
   public boolean exist = false;
   
   public int attackFrame = 0;
   public int attackSpeed = 250;
   
   public int range;
   public int[] targets = new int[4];
   /**
   * If the tower is attacking or not 
   */
   public boolean attacking;
   
   public int rx;
   public int ry;
   /**
   * Array of projectiles for each tower
   */
   public Projectile[] projectile = new Projectile[20]; //each tower has an array of 20 projectiles
   
   public Tower() {
      for (int i=0; i<projectile.length; i++) {
         projectile[i] = new Projectile();
      }
   }
   /**
   * Spawns the tower 
   */
   public void spawn(int Id, int xc, int yc) { //spawns the tower
      if (Screen.level.tiles[yc][xc].groundId == Value.grass) {
         setBounds(Screen.level.tiles[yc][xc].x, Screen.level.tiles[yc][xc].y, size, size);
         this.xc = xc;
         this.yc = yc;
         
         rx = x+size/2;
         ry = y+size/2;
         
         TowerId = Id+11;
         if (TowerId == Value.laserTower) { //sets different values based on the tower
            range = 100;         
         }
         else if (TowerId == Value.arrowTower) {
            range = 225;  
            attackSpeed = 250;    
         }
         else if (TowerId == Value.iceTower) {
            range = 125;      
         }
         else if (TowerId == Value.bombTower) {
            range = 150;
            attackSpeed = 500;
         }
         exist = true; //allows for the tower to be drawn
      } 
   }
   /**
   * Detects if any enemies are in range of the Tower 
   */
   public void detect() { //detects if any enemies are in range of the tower
      try {
         int k = Screen.enemies.length;
   
         if (attacking == true) {
            attack(); //attacks
         }
         
         for (int i=0; i<Screen.enemies.length; i++) {
            if ((Math.sqrt(Math.pow(rx - Screen.enemies[i].rx, 2)+Math.pow(ry - Screen.enemies[i].ry, 2)) <= range) && (Screen.enemies[i].exist == true)) {
               //distance formula to calculate if enemy in range
               k--;
               if ((TowerId == Value.laserTower) || (TowerId == Value.bombTower)) {
                  targets[0] = i;
                  attacking = true;
               
                  break;
               }
               else if (TowerId == Value.arrowTower) {
                  targets[0] = i;
                  for (int j=i+1; j<Screen.enemies.length; j++) {
                     if ((Math.sqrt(Math.pow(rx - Screen.enemies[j].rx, 2)+Math.pow(ry - Screen.enemies[j].ry, 2)) <= range) && (Screen.enemies[j].exist == true)) {
                        targets[1] = j; //multitargetting system using an array of targets
                     }        
                  }
                  attacking = true; //sets attacking to true if something is in range
                  break;  
               }
               else if (TowerId == Value.iceTower) {
                  targets[0] = i;
                  for (int j=i+1; j<Screen.enemies.length; j++) {
                     if ((Math.sqrt(Math.pow(rx - Screen.enemies[j].rx, 2)+Math.pow(ry - Screen.enemies[j].ry, 2)) <= range) && (Screen.enemies[j].exist == true)) {
                        targets[1] = j;
                        for (int l=j+1; l<Screen.enemies.length; l++) {
                           if ((Math.sqrt(Math.pow(rx - Screen.enemies[l].rx, 2)+Math.pow(ry - Screen.enemies[l].ry, 2)) <= range) && (Screen.enemies[l].exist == true)) {
                              targets[2] = j;
                              for (int m=l+1; m<Screen.enemies.length; m++) {
                                 if ((Math.sqrt(Math.pow(rx - Screen.enemies[m].rx, 2)+Math.pow(ry - Screen.enemies[m].ry, 2)) <= range) && (Screen.enemies[m].exist == true)) {
                                    targets[3] = j; //multitargetting system using an array of targets
                                 }        
                              }
                           }        
                        }
                     }        
                  }
                  attacking = true;
                  break;
               }
               else if (TowerId == Value.bombTower) {
                  targets[0] = i;
                  attacking = true;
                  break;  
               }        
            }
         }   
      
      
         if (k == Screen.enemies.length) { //if nothing is in range, which is what k is used for, set attacking to false
            attacking = false;
            for (int j = 0; j<targets.length; j++) {
               targets[j] = -1;
            }
         }
         for (int i=0; i<projectile.length; i++) { //moves each projectile
            if (projectile[i].exist == true) {
               
               projectile[i].move();
            }         
         }
      }
      catch (Exception e) {
      
      }
   }
   /**
   * Attacks the enemies in range of the tower 
   */
   public void attack() { //attacks
      
      try {
         if (attacking == true) {
            if (TowerId == Value.laserTower) {
               Screen.enemies[targets[0]].health =  Screen.enemies[targets[0]].health - 0.025; 
               //directly remove health for laser tower
            }
            else if (TowerId == Value.arrowTower) { 
               if (attackFrame == attackSpeed) { 
                  int i = 0;
                  while (projectile[i].exist == true) {
                     if (i >= projectile.length) {
                        i=0;
                        break;
                     }
                     i++;
                  }
                  if (targets[0] != -1) {
                     projectile[i].spawn(Value.arrow, x, y, targets[0]);    //spawns arrow for arrow tower              
                  }
                  while (projectile[i].exist == true) {
                     i++;
                     if (i >= projectile.length) {
                        i=0;
                        break;
                     }
                     i++;
                  }
                  if (targets[1] != -1) {
                     projectile[i].spawn(Value.arrow, x, y, targets[1]);
                  }
                  attackFrame = 0;  
               }  
               else {
                  attackFrame++;
               }
            
            }
            else if (TowerId == Value.iceTower) {
               if (targets[0] != -1) {
                  if (Screen.enemies[targets[0]].exist == true) {
                     //directly remove health for ice tower
                     Screen.enemies[targets[0]].health =  Screen.enemies[targets[0]].health - 0.001;   
                  }               
               }
               if (targets[1] != -1) {
                  if (Screen.enemies[targets[1]].exist == true) {
                     Screen.enemies[targets[1]].health =  Screen.enemies[targets[1]].health - 0.001;                     
                  }
               }
               if (targets[2] != -1) {
                  if (Screen.enemies[targets[2]].exist == true) { 
                     Screen.enemies[targets[2]].health =  Screen.enemies[targets[2]].health - 0.001;                              
                  }
               }
               if (targets[3] != -1) {
                  if (Screen.enemies[targets[3]].exist == true) {  
                     Screen.enemies[targets[3]].health =  Screen.enemies[targets[3]].health - 0.001;                        
                  }
               }
            }
            else if (TowerId == Value.bombTower) {         
               if (attackFrame == attackSpeed) {
                  int i = 0;        
                  while (projectile[i].exist == true) {
                     i++;
                  }
                  if (targets[0] != -1) {
                     projectile[i].spawn(Value.bomb, x, y, targets[0]);   //spawns bomb for bomb tower               
                  }         
                  attackFrame = 0;
               }
               else {
                  attackFrame++;  
               }
               
            }
         }
      }
      catch (Exception e) {
         //for index out of bounds in the late game when lag causes too many projectiles to be on the screen
      }
   }
   /**
   * Draws the tower and laser if it is a laser or ice tower
   * @param g Graphics 
   */
   public void draw(Graphics g) { //draws the tower
      if (exist == true) {
         g.drawImage(Screen.tower[TowerId-11], x, y, width, height, null);
         
      }
      try {
         for (int i=0; i<projectile.length; i++) {
            if (projectile[i].exist == true) {
               
               projectile[i].draw(g);
            }         
         }
      }
      catch (Exception e) {
      
      }
      if (attacking == true) {
         
         if (TowerId == Value.laserTower) {
            if (Screen.enemies[targets[0]].exist == true) {
               g.setColor(new Color(255,0,0));
               g.drawLine(rx,ry,Screen.enemies[targets[0]].rx, Screen.enemies[targets[0]].ry);
               //draws the laser for the laser and ice tower - which is a line
            }     
         }
         else if (TowerId == Value.iceTower) {
            g.setColor(new Color(0,0,200));
            if (targets[0] != -1) {
               
               if (Screen.enemies[targets[0]].exist == true) {
                  g.drawLine(rx,ry,Screen.enemies[targets[0]].rx, Screen.enemies[targets[0]].ry);
                  //draws the laser for the laser and ice tower  
                   
               }               
            }
            if (targets[1] != -1) {
               if (Screen.enemies[targets[1]].exist == true) {
                  g.drawLine(rx,ry,Screen.enemies[targets[1]].rx, Screen.enemies[targets[1]].ry);                       
               }
            }
            if (targets[2] != -1) {
               if (Screen.enemies[targets[2]].exist == true) {
                  g.drawLine(rx,ry,Screen.enemies[targets[2]].rx, Screen.enemies[targets[2]].ry);                      
               }
            }
            if (targets[3] != -1) {
               if (Screen.enemies[targets[3]].exist == true) {
                  g.drawLine(rx,ry,Screen.enemies[targets[3]].rx, Screen.enemies[targets[3]].ry);                      
               }
            }
         }
      }
   }   
}
