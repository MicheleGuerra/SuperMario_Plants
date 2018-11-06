
package gioco_esame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Giocatore {
    private int x;
    private int y;
    private int width;
    private int height;
    public int number_of_enemies;
    private final int speed = 30;
    BufferedImage img_player;
    BufferedImage img_bullet;
    
    
    
    Gioco main;
    
    public Giocatore(){}
    
    public Giocatore(BufferedImage image, BufferedImage bullet, int x, int y, int width, int height, Gioco main ) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.img_player = image;
        this.img_bullet = bullet;
        y = 400;
        number_of_enemies = 0;
        this.main = main;
        
        Gioco.bullets = new ArrayList();
    }
    
    public void spara() {
        Gioco.bullets.add(new Bullet(img_bullet, x+width/2, y, 50, 70, main));
    }
    
    public void draw(Graphics g) {
        g.drawImage(img_player, x, y, width, height, null);
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
    
    public void moveRight(){
        if((x+width)<main.getWidth()){
            x+=speed;
        }
    }
    
    public void moveLeft(){
        if(x>0) {
            x-=speed;
        }
    }
    
    public Rectangle getEdges(){
        return new Rectangle(x, y, width, height);
    }
    
    public ArrayList getBullets(){
        return Gioco.bullets;
    }
    
}
