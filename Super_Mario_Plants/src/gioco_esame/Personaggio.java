
package gioco_esame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Personaggio extends Thread{
    private int x;
    private int y;
    private int width;
    private int height;
    private Boolean active;
    BufferedImage img_personaggio;
    private int speed = 1;
    private final int max_speed = 20;
    private Gioco main;
    public static int life;
    
    public Personaggio(BufferedImage image, int width, int height, int x, int y, Gioco main) {
        this.x = x;
        this.y = y;
        this. height = height;
        this.width = width;
        this.img_personaggio = image;
        active = true;
        this.main = main;
        life = 100;
    }
    
    @Override
    public void run() {
        active = true;
        while(active){
            updates();
            
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Personaggio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void updates() {
        Random rnd = new Random();
        if(this.x <= 0){
            speed = rnd.nextInt(max_speed)+1;
            //speed *= -1;
        } else if(this.x >= main.getWidth()-this.width){
            speed = rnd.nextInt(max_speed)+1;
            speed *= -1;
        }
        x += speed;
    }
    
    public void draw(Graphics g) {
        g.drawImage(img_personaggio, x, y, width, height, main);
    }
    
    public boolean getActive(){
        return active;
    }
    
    public void setActive(boolean valore){
        this.active = valore;
    }
    
    public void setX(int valore) {
        this.x = valore;
    }
    
    public void setY(int valore) {
        this.y = valore;
    }
    
    public void setWidth(int valore) {
        this.width = valore;
    }
    
    public void setHeight(int valore) {
        this.height = valore;
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
