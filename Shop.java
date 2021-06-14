
import java.awt.*;
/**
* This is the shop class which creates the shop GUI
*/
public class Shop { //shop that the player can use
   public static int ShopWidth = 5;
   public static int buttonSize = 50;
   public static int cellSpace = 10;
   public static int awayFromRoom = 7;
   public static int iconSize = 20;
   public static int iconSpace = 3;
   
   public static int iconTextY = 15;
   public static int itemIn = 4;
   /**
   * Held Item 
   */
   public static int heldId = -1;
   public static int realId = -1;
   public static int[] buttonId = {Value.airTowerLaser, Value.airTowerArrow, Value.airTowerIce, Value.airTowerBomb, Value.airTrashCan};
   public static int[] buttonPrice = {100, 250, 300, 500, 0};
   
   
   /**
   * Button array 
   */
   public Rectangle[] button = new Rectangle[ShopWidth];
   public Rectangle health;
   public Rectangle coins;
   
   public boolean holdsItem = false; 
   
   public Shop() {
      define();
   }
   /**
   * If the mouse is clicked, this method is called 
   * @param mouseButton which button on the mouse is clicked
   */
   public void click(int mouseButton)  { //if the mouse is clicked - do something depending on what the mouse is hovering over
      if(mouseButton == 1){
         for(int i=0; i<button.length;i++) { //this is if the mouse is over the shop buttons
            if(button[i].contains(Screen.mouse)) {
               if(buttonId[i] != Value.airAir) {
                  if(buttonId[i] == Value.airTrashCan) {
                     holdsItem = false;
                  } 
                  else {
                     heldId = buttonId[i];
                     realId = i;
                     holdsItem = true;
                  } 
               }
            }
         }       
         if(holdsItem) { //this if places the tower
            if(Screen.coin >= buttonPrice[realId]) {      
               for(int y=0; y<Screen.level.worldWidth;y++) {
                  for (int x=0; x<Screen.level.worldHeight; x++) {
                     if(Screen.level.tiles[y][x].contains(Screen.mouse)) {
                        if(Screen.level.tiles[y][x].groundId == Value.grass && Screen.level.tiles[y][x].airId == Value.air){
                           
                           Screen.level.tiles[y][x].airId = heldId;
                           Screen.coin = Screen.coin - buttonPrice[realId];
                           buttonPrice[realId] = (buttonPrice[realId] * 102)/100;
                           int i = 0;
                           while (Screen.towers[i].exist == true) {
                              i++;
                           }
                           Screen.towers[i].spawn(heldId-30,x,y);
                        }
                     }
                  }
               }
            }
         }
      }
   }
   /**
   * Shop setup 
   */
   public void define() { //sets up the shop
      for (int i=0; i<button.length; i++) {
         button[i] = new Rectangle((Screen.Width/2) - ((ShopWidth*(buttonSize+cellSpace))/2) + ((buttonSize+cellSpace)*i), (Screen.level.tiles[Screen.level.worldHeight-1] [0].y)+Screen.level.tileSize + awayFromRoom, buttonSize, buttonSize);
      }
         
      health = new Rectangle(Screen.level.tiles[0][0].x - 1, button[0].y, iconSize, iconSize);
      coins = new Rectangle(Screen.level.tiles[0][0].x - 1, button[0].y + button[0].height-iconSize, iconSize, iconSize);
   }
   /**
   * Draws the shop
   * @param g Graphics 
   */
   public void draw(Graphics g) { //draws the different buttons/assets of the shop
            
      for(int i=0;i<button.length; i++) {   
         if(button[i].contains(Screen.mouse)) {     
            g.setColor(new Color (255, 255, 255, 150));
            g.fillRect(button[i].x,button[i].y, button[i].width, button[i].height);
         }     
         g.drawImage(Screen.res[0], button[i].x, button[i].y, button[i].width, button[i].height, null);
      
         if(buttonId[i] != Value.airAir) { 
            g.drawImage(Screen.tower[i+1], button[i].x + itemIn, button[i].y + itemIn, button[i].width - (itemIn*2), button[i].height - (itemIn*2), null);
            if(buttonPrice[i] > 0) {
               g.setColor(new Color(255, 255, 255));
               g.setFont(new Font("Courier New", Font.BOLD, 12));
               g.drawString("$" + buttonPrice[i], button[i].x + itemIn, button[i].y + itemIn + 40);   //displays tower price  
            }
         }
      }
      g.drawImage(Screen.res[1], health.x, health.y, health.width, health.height, null);
      g.drawImage(Screen.res[2], coins.x, coins.y, coins.width, coins.height, null);      
      g.setFont(new Font("Courier New", Font.BOLD, 14));
      g.setColor(new Color(0, 0, 0));
      g.drawString("" + Screen.health, health.x + health.width + iconSpace, health.y + 15); //displays the health
      g.drawString("" + Screen.coin, coins.x + coins.width + iconSpace, coins.y + 15); //displays the coins
      g.drawString("Round " + Round.round, health.x + health.width + iconSpace + 400, health.y + 15); //displays the round number
      if(holdsItem) {
         g.drawImage(Screen.tower[heldId-30], Screen.mouse.x - ((button[0].width - (itemIn*2) )/2) + itemIn, Screen.mouse.y - ((button[0].width - (itemIn*2) )/2) + itemIn, button[0].width - (itemIn*2), button[0].height - (itemIn*2), null);
      }  
   }
}    
