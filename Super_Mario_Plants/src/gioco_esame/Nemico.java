
package gioco_esame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Nemico extends Thread{
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed = 10;
    BufferedImage img_enemy;
    private boolean active;
    private Gioco main;
    
    public Nemico(BufferedImage image, int width, int height, int x, int y, int speed, Gioco main){
        this.x = x;
        this.y = y;
        this. height = height;
        this.width = width;
        this.img_enemy = image;
        this.main = main;
        this.speed = speed;
        active = true;
        this.start();
    }
    
    @Override
    public void run(){
        active = true;
        while(active){
            updates();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Nemico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void updates(){
        y += speed;
    }
    
    public void draw(Graphics g){
        g.drawImage(img_enemy, x, y,width, height, main);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public Rectangle getEdges(){
        return new Rectangle(x, y, width, height);
}
}
