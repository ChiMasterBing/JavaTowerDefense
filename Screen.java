
import javax.swing.*; //main class, controls the program
import java.awt.*;
import java.awt.image.*;
/**
* This is the screen class - the main class of the program which controls most of the functions
*/
public class Screen extends JPanel implements Runnable { //implements runnable for gameloop
   /**
   * gameloop
   */
   public Thread thread = new Thread(this); //gameloop
   public static boolean isFirst = true;
   public static int Width, Height;
   public static Level level;
   
   public static Image[] ground = new Image[12]; //The tilesets for the ground layer and the air layer.
   public static Image[] res = new Image[12]; //change number for different number of images 
   public static Image[] enemy = new Image[12];
   public static Image[] tower = new Image[12];
   public static Image[] projectile = new Image[12];
   
   /**
   * Mouse position 
   */
   public static Point mouse = new Point(0,0);
   
   public static Enemy[] enemies = new Enemy[150];
   public static Tower[] towers = new Tower[250];
   
   /**
   * health 
   */
   public static int health = 20;
   
   public static Shop shop; 
   /**
   * coins
   */
   public static int coin = 300;
   
   
   public Screen() {
      super.setDoubleBuffered(true); //helps prevent weird things from happening when repaint() is called
      thread.start();
   }
   /**
   * Setup for the Screen - such as filling up image arrays for tilesets 
   */
   public void define() {
      level = new Level();
      shop = new Shop();
      
      ActionHandler handler = new ActionHandler();
      this.addMouseListener(handler);
      this.addMouseMotionListener(handler);
      
      for (int i = 0; i<ground.length; i++) {
         ground[i] = new ImageIcon("images/ground.png").getImage();       
         ground[i] = createImage(new FilteredImageSource(ground[i].getSource(), new CropImageFilter(0, 8*i, 8, 8)));
         //Extract image from tileset.
      } //Extracting all the rest of the images
      res[0] = new ImageIcon("images/frame.png").getImage();
      res[1] = new ImageIcon("images/heart.png").getImage();
      res[2] = new ImageIcon("images/coin.png").getImage();
      enemy[0] = new ImageIcon("images/Enemy1.png").getImage();
      enemy[1] = new ImageIcon("images/Enemy4.png").getImage();
      enemy[2] = new ImageIcon("images/Enemy2R.png").getImage();
      enemy[3] = new ImageIcon("images/Enemy3R.png").getImage();
      enemy[4] = new ImageIcon("images/Enemy5.png").getImage();
      enemy[5] = new ImageIcon("images/Enemy5a.png").getImage();
      enemy[6] = new ImageIcon("images/Enemy5b.png").getImage();
      enemy[7] = new ImageIcon("images/Enemy6.png").getImage();
      tower[0] = new ImageIcon("images/air.png").getImage();
      tower[1] = new ImageIcon("images/laserTower.png").getImage();
      tower[2] = new ImageIcon("images/arrowTower.png").getImage();
      tower[3] = new ImageIcon("images/iceTower.png").getImage();
      tower[4] = new ImageIcon("images/bombTower.png").getImage();
      tower[5] = new ImageIcon("images/trash.png").getImage();
      projectile[0] = new ImageIcon("images/arrow.png").getImage();
      projectile[1] = new ImageIcon("images/bomb.png").getImage();
      projectile[2] = new ImageIcon("images/explosion1.png").getImage();
      projectile[3] = new ImageIcon("images/explosion2.png").getImage();
      projectile[4] = new ImageIcon("images/explosion3.png").getImage();
      for (int i=0; i<enemies.length; i++) {
         enemies[i] = new Enemy(i);       //fills the enemy array
      } 
      for (int i=0; i<towers.length; i++) {
         towers[i] = new Tower();       //fills the tower array
      }  
   }
   /**
   * Draws the screen using graphics
   * @param g Graphics
   */
   public void paintComponent(Graphics g) {
      if (isFirst == true) { //when the program first starts up, isFirst is true and setup commands can be run
         
         Width = this.getWidth();
         Height = this.getHeight();
         define();
         Round.start();
         
         isFirst = false;     
      }
      
   
      g.setColor(new Color(200,200,200));
      g.fillRect(0,0,getWidth(),getHeight());
      g.setColor(new Color(30,30,30));
      level.draw(g); //calls the draw functions for level, shop, enemies, and towers so the GUI is updated every frame
      shop.draw(g);
      for (int i=0; i<enemies.length; i++) {
         if (enemies[i].exist == true) {
            enemies[i].draw(g);         
         }
      }
      for (int i=0; i<towers.length; i++) {
         if (towers[i].exist == true) {
            towers[i].draw(g);         
         }
      }
      if (health == 0) {
         Image defeat = new ImageIcon("images/defeat.png").getImage();
         g.drawImage(defeat, 0, 0, 600, 675, null);         
      
      } 
      if (Round.round == 11) {
         Image defeat = new ImageIcon("images/victory.png").getImage();
         g.drawImage(defeat, 0, 0, 600, 675, null);         
      
      }     
   }
   /**
   * gameloop
   */
   public void run() { //gameloop, run() is called every millisecond
      boolean exit = false;
      while(exit == false) {
         if(isFirst == false) {          
            Round.doTick();
            
            
            for (int i=0; i<enemies.length; i++) { //move and detect are run through the gameloop instead of paintcomponent because of refresh rate causing some issues
               if (enemies[i].exist == true) {
                  enemies[i].move();  
                  enemies[i].detect();    
               }
            }
            for (int i=0; i<towers.length; i++) {
               if (towers[i].exist == true) {
                  towers[i].detect();
               }
            }
            
            
            if (health == 0 || Round.round == 11) {
               
               exit = true; //the win/lose condition
            }
            repaint();  //repaint calls paintComponent()
         }
         try {
            thread.sleep(1); //delay for every millisecond
            }
         catch (Exception e) {
            System.out.println("And exception occured");  //try catch is needed for delays
         }
      }
   }
}
