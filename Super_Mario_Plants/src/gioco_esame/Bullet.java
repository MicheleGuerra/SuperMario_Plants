
package gioco_esame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bullet extends Thread{
    
    private boolean active;
    private int height;
    private int width;
    private int x;
    private int y;
    private BufferedImage img_bullet;
    private final int speed =  25;
    private Gioco main;
    
    public Bullet(BufferedImage image, int x, int y, int width, int height, Gioco main) {
        this.x = x;
        this.y = y;
        this. height = height;
        this.width = width;
        this.img_bullet = image;
        this.main = main;
        
        start();
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
        y -= speed;
        if(y+(height)<0) {
            this.setActive(false);
            Gioco.bullets.remove(this);
        }
    }
    
    public void draw(Graphics g){
        g.drawImage(img_bullet, x, y,width, height, main);
    }
    
    public void setActive(boolean state){
        active = state;
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
