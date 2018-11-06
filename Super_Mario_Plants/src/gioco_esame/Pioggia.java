
package gioco_esame;

import gioco_esame.Gioco.STATE;
import static gioco_esame.Gioco.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Pioggia extends Thread {
    public static int number = 1;
    private int waiting;
    BufferedImage img_enemy;
    Gioco main;
    private boolean active;
    private ArrayList<Nemico> enemies;
    Random rand;
    public static int max_speed = 25;
    
    public Pioggia(BufferedImage image, int waiting, Gioco main) {
        this.img_enemy = image;
        this.waiting = waiting;
        this.main = main;
        enemies = new ArrayList();
        rand = new Random();
    }
    
    @Override
    public void run(){
        active = true;
        while(active){
            for(int i = 0; i<number; i++) {
                if(Gioco.State == Gioco.STATE.GAME){
                enemies.add(new Nemico(img_enemy, 100, 100, rand.nextInt(main.getWidth()), -50, rand.nextInt(max_speed)+3, main));
            }}
            try {
                Thread.sleep(waiting);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pioggia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void draw(Graphics g){
        for(int i=0; i<enemies.size(); i++){
            Nemico temp = enemies.get(i);
            temp.draw(g);
        }
    }
    
    public ArrayList getEnemies(){
        return enemies;
    }
}
