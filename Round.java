
/**
* This is the round class 
*/
public class Round { //this is where the pattern of enemies is spawned for each round
   public static int spawnTime = 0;
   public static int spawnFrame = 0;
   public static int spawnTime2 = 0;
   public static int spawnFrame2 = 0;
   public static int spawnTime3 = 0;
   public static int spawnFrame3 = 0;
   public static int time;
   public static int round = 0;
   /**
   * Directly spawns Enemy 
   * @param Id Which Enemy is being spawned
   */
   public static void directSpawn(int Id) { //directly spawns an enemy
      for (int i = 0; i<Screen.enemies.length; i++) {   
         if (Screen.enemies[i].exist == false) {
            Screen.enemies[i].spawn(Id);     
            break;
         }
      }
   }
   /**
   * spawns enemies at intervals 
   * @param Id Which Enemy is being spawned
   */
   public static void spawner(int Id) { //3 spawners to enable different enemies to be spawned at once
      if (spawnFrame >= spawnTime) {
         for (int i = 0; i<Screen.enemies.length; i++) {   
            if (Screen.enemies[i].exist == false) {
               Screen.enemies[i].spawn(Id);     
               break;
            }
         }
         spawnFrame = 0;
      }   
      else {
         spawnFrame++;
      }
   }
   public static void spawner2(int Id) { //spawner 2
      if (spawnFrame2 >= spawnTime2) {
         for (int i = 0; i<Screen.enemies.length; i++) {   
            if (Screen.enemies[i].exist == false) {
               Screen.enemies[i].spawn(Id);     
               break;
            }
         }
         spawnFrame2 = 0;
      }   
      else {
         spawnFrame2++;
      }
   }
   /**
   * starts the rounds 
   */
   public static void start() { //starts the game - screen calls this
      time = 0; //the enemies are spawned based on the round and the time
      round = 1; //set to first round

   }
   public static void spawner3(int Id) { //spawner 3
      if (spawnFrame3 >= spawnTime3) {
         for (int i = 0; i<Screen.enemies.length; i++) {   
            if (Screen.enemies[i].exist == false) {
               Screen.enemies[i].spawn(Id);     
               break;
            }
         }
         spawnFrame3 = 0;
      }   
      else {
         spawnFrame3++;
      }
   }
   /**
   * Updates times and calls different rounds 
   */
   public static void doTick() { //updates the time each frame
      if (round == 1) {
         Round1();
      }
      else if (round == 2) {
         Round2();
      }
      else if (round == 3) {
         Round3();
      }
      else if (round == 4) {
         Round4();
      }
      else if (round == 5) {
         Round5();
      }
      else if (round == 6) {
         Round6();
      }
      else if (round == 7) {
         Round7();
      }
      else if (round == 8) {
         Round8();
      }
      else if (round == 9) {
         Round9();
      }
      else if (round == 10) {
         Round10();
      }
      time++;
   }
   public static void Round1() {  //methods for each round
      //if the time falls within the range of each if statement, then certain mobs are spawned
      if (time == 1000) {
         directSpawn(0);
      }
      else if ((time > 3000) && (time < 5000)) {
         spawnTime = 1000; 
         spawner(0);
      }
      else if ((time > 5000) && (time < 10000)) {
         spawnTime = 750; 
         spawner(0);
      }
      else if ((time > 10000) && (time < 15000)) {
         spawnTime = 500; 
         spawner(0);
      }
      else if (time == 17000) {
         directSpawn(1);
      }
      
      if (time > 17000) { //detecting if all mobs are defeated after the last mob of the round is spawned
         int k = 0;
         for (int i = 0; i<Screen.enemies.length; i++) {          
            if (Screen.enemies[i].exist == false) {
               k++;    
            }
         } 
         if (k == Screen.enemies.length) {
            round = 2; //set round to 2
            time = -1000;
         }
      }
   }
   public static void Round2() {
      if (time == 1000) {
         directSpawn(1);
      }
      else if ((time > 3000) && (time < 7500)) {
         spawnTime = 500; 
         spawnTime2 = 750;
         spawner(0);
         spawner2(1);
      }
      else if ((time > 7500) && (time < 10000)) {
         spawnTime = 150; 
         spawnTime2 = 350;
         spawner(0);
         spawner2(1);
      }
      else if ((time > 10000) && (time < 15000)) {
         spawnTime = 1000; 
         spawnTime2 = 2500;
         spawner(0);
         spawner2(1);
      }
      else if (time == 17000) {
         directSpawn(2);
      }
      
      if (time > 17000) {
         int k = 0;
         for (int i = 0; i<Screen.enemies.length; i++) {          
            if (Screen.enemies[i].exist == false) {
               k++;    
            }
         } 
         if (k == Screen.enemies.length) {
            round = 3;
            time = -1500;
         }
      }
   }
   public static void Round3() {
      if ((time > 1000) && (time < 3000)) {
         spawnTime = 100;
         spawner(0); 
         
      }
      else if (time == 3000) {
         directSpawn(2);
      }
      else if ((time > 3000) && (time < 4000)) {
         spawnTime = 60; 
         spawner(0);
      }
      else if ((time > 8000) && (time < 10000)) {
         spawnTime2 = 100;
         spawner2(1);
      }
      else if ((time > 13000) && (time < 15000)) {
         spawnTime = 50; 
         spawnTime2 = 125;
         spawner(0);
         spawner2(1);
      }
      else if ((time > 17000) && (time < 19000)) {
         spawnTime = 200; 
         spawner(2);
         spawnTime2 = 125;
         spawner2(1);
         spawnTime3 = 75;
         spawner3(0);
      }
      if (time > 19000) {
         int k = 0;
         for (int i = 0; i<Screen.enemies.length; i++) {          
            if (Screen.enemies[i].exist == false) {
               k++;    
            }
         } 
         if (k == Screen.enemies.length) {
            round = 4;
            time = -1500;
         }
      }
   }
   public static void Round4() {
      if ((time > 1000) && (time < 7500)) {
         spawnTime = 250;
         spawner(0); 
         
      }
      else if ((time > 9000) && (time < 9500)) {
         spawnTime = 50; 
         spawner(1);
         spawnTime = 75;
         spawner2(2);
      }
      else if ((time > 11000) && (time < 12500)) {
         spawnTime2 = 30;
         spawner2(0);
         spawnTime = 100;
         spawner(2);
      }
      else if ((time > 16000) && (time < 18000)) {
         spawnTime = 60; 
         spawnTime2 = 100;
         spawner(0);
         spawner2(1);
         spawnTime = 150; 
         spawner3(2);
      }
      else if (time == 19000) {
         directSpawn(3);
      }
      if (time > 19000) {
         int k = 0;
         for (int i = 0; i<Screen.enemies.length; i++) {          
            if (Screen.enemies[i].exist == false) {
               k++;    
            }
         } 
         if (k == Screen.enemies.length) {
            round = 5;
            time = -2000;
         }
      }
   }
   public static void Round5() {
      if ((time > 1000) && (time < 10000)) {
         spawnTime = 1000;
         spawner(3); 
         spawnTime2 = 300;
         spawner2(2);
         spawnTime3 = 100;
         spawner3(1);
         
      }
      else if ((time > 10000) && (time < 12000)) {
         spawnTime = 25; 
         spawner(0);
      }
      else if ((time > 12500) && (time < 17500)) {
         spawnTime2 = 300;
         spawner2(3);
         spawnTime = 1500;
         spawner(1);
         spawnTime3 = 750;
         spawner3(0);
      }
      else if ((time > 18000) && (time < 22000)) {
         spawnTime = 40;
         spawner(2); 
      }
      else if ((time > 23000) && (time < 27000)) {
         spawnTime2 = 500;
         spawner2(3);
         spawnTime = 75;
         spawner(1);
         spawnTime3 = 100;
         spawner3(2); 
      }
      else if (time == 30000) {
         directSpawn(4);
      }
      if (time > 30000) {
         int k = 0;
         for (int i = 0; i<Screen.enemies.length; i++) {          
            if (Screen.enemies[i].exist == false) {
               k++;    
            }
         } 
         if (k == Screen.enemies.length) {
            round = 6;
            time = -2000;
         }
      }
   }
   public static void Round6() {
      if ((time > 1000) && (time < 5000)) {
         spawnTime = 200;
         spawner(3); 
         spawnTime2 = 375;
         spawner2(1);
      }
      else if ((time > 7500) && (time < 10000)) {
         spawnTime = 225;
         spawner(5); 
      }
      else if ((time > 12000) && (time < 12300)) {
         spawnTime = 15;
         spawner(2); 
      }
      else if ((time > 13000) && (time < 13300)) {
         spawnTime = 15;
         spawner(3); 
      }
      else if ((time > 14500) && (time < 14800)) {
         spawnTime = 15;
         spawner(1); 
      }
      else if ((time > 16000) && (time < 16200)) {
         spawnTime = 20;
         spawner(5); 
      }
      else if (time == 17000) {
         spawnFrame2 = -100;
      }
      else if ((time > 17000) && (time < 20000)) {
         spawnTime = 500;
         spawner(1); 
         spawnTime2 = 500;
         spawner2(1);
      }
      if (time > 20000) {
         int k = 0;
         for (int i = 0; i<Screen.enemies.length; i++) {          
            if (Screen.enemies[i].exist == false) {
               k++;    
            }
         } 
         if (k == Screen.enemies.length) {
            round = 7;
            time = -2000;
         }
      }
   }
   public static void Round7() {
      if ((time > 1000) && (time < 3000)) {
         spawnTime = 250;
         spawner(2); 
      }
      else if (time == 5000) {
         directSpawn(4);
      }
      else if ((time > 5000) && (time < 12000)) {
         spawnTime = 500;
         spawner(3); 
      }
      else if ((time > 13000) && (time < 17000)) {
         spawnTime = 250;
         spawner(1); 
         spawnTime2 = 750;
         spawner2(2);
      }
      else if (time == 18000) {
         directSpawn(4);
      }
      if (time > 18000) {
         int k = 0;
         for (int i = 0; i<Screen.enemies.length; i++) {          
            if (Screen.enemies[i].exist == false) {
               k++;    
            }
         } 
         if (k == Screen.enemies.length) {
            round = 8;
            time = -2000;
         }
      }
   }
   public static void Round8() {
      if (time == 1000) {
         directSpawn(7);
      }
      else if ((time > 3000) && (time < 9000)) {
         spawnTime = 1000;
         spawner(7); 
         spawnTime2 = 650;
         spawner2(5);
      }
      else if (time == 10000) {
         directSpawn(7);
      }
      else if (time == 11000) {
         directSpawn(4);
      }
      else if (time == 12000) {
         directSpawn(7);
      }
      else if ((time > 14000) && (time < 14100)) {
         spawnTime = 7;
         spawner(5);
      }
      else if ((time > 15000) && (time < 16600)) {
         spawnTime = 500;
         spawner(4);
      }
      if (time > 16600) {
         int k = 0;
         for (int i = 0; i<Screen.enemies.length; i++) {          
            if (Screen.enemies[i].exist == false) {
               k++;    
            }
         } 
         if (k == Screen.enemies.length) {
            round = 9;
            time = -2000;
         }
      }
   }
   public static void Round9() {
      if ((time > 3000) && (time < 5000)) {
         spawnTime = 250;
         spawner(0); 
         spawnTime2 = 250;
         spawner2(1);
         spawnTime3 = 250;
         spawner3(2);
      }
      else if ((time > 5000) && (time < 7000)) {
         spawnTime = 250;
         spawner(3); 
         spawnTime2 = 250;
         spawner2(5);
         spawnTime3 = 250;
         spawner3(7);
      }
      else if (time == 7500) {
         directSpawn(4);
      }
      else if ((time > 10000) && (time < 12000)) {
         spawnTime = 150;
         spawner(0); 
         spawnTime2 = 150;
         spawner2(1);
         spawnTime3 = 150;
         spawner3(2);
      }
      else if ((time > 12000) && (time < 14000)) {
         spawnTime = 150;
         spawner(3); 
         spawnTime2 = 150;
         spawner2(5);
         spawnTime3 = 150;
         spawner3(7);
      }
      else if (time == 14500) {
         directSpawn(4);
      }
      else if ((time > 16000) && (time < 18000)) {
         spawnTime = 100;
         spawner(0); 
         spawnTime2 = 100;
         spawner2(1);
         spawnTime3 = 100;
         spawner3(2);
      }
      else if ((time > 18000) && (time < 20000)) {
         spawnTime = 100;
         spawner(3); 
         spawnTime2 = 80;
         spawner2(5);
         spawnTime3 = 100;
         spawner3(7);
      }
      else if (time == 20500) {
         directSpawn(4);
      }
      else if ((time > 21000) && (time < 22000)) {
         spawnTime = 300;
         spawner(4);
         spawnTime2 = 150;
         spawner2(5); 
      }
      if (time > 22000) {
         int k = 0;
         for (int i = 0; i<Screen.enemies.length; i++) {          
            if (Screen.enemies[i].exist == false) {
               k++;    
            }
         } 
         if (k == Screen.enemies.length) {
            round = 10;
            time = -2000;
         }
      }
   }
   public static void Round10() { //You can't beat this one
      if ((time > 3000) && (time < 10000)) {
         spawnTime = 50;
         spawner(1); 
         spawnTime2 = 50;
         spawner2(5);
         spawnTime3 = 150;
         spawner3(7);
      } 
      else if ((time > 10000) && (time < 12000)) {
         spawnTime = 500;
         spawner(4); 
         spawnTime2 = 80;
         spawner2(5);
         spawnTime3 = 80;
         spawner3(7);
      }
      else if ((time > 12000) && (time < 14000)) {
         spawnTime = 250;
         spawner(4); 
         spawnTime2 = 150;
         spawner2(5);
         spawnTime3 = 500;
         spawner3(7);
      }
      if (time > 22000) {
         int k = 0;
         for (int i = 0; i<Screen.enemies.length; i++) {          
            if (Screen.enemies[i].exist == false) {
               k++;    
            }
         } 
         if (k == Screen.enemies.length) {
            round = 11;
            time = -2000;
         }
      } 
   }   
}
