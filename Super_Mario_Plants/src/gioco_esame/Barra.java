
package gioco_esame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Barra {
    private int x;
    private int y;
    private int width;
    private int height;
    private Gioco main;
    
    BufferedImage img_barr;
    
    public Barra(){
        
    }
    
    public Barra(BufferedImage image, int x, int y, int width, int height, Gioco main){
        
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.img_barr = image;
        this.main = main;
    }
    
    public void draw(Graphics g) {
        g.drawImage(img_barr, x, y, width, height, null);
    }
    
    public Rectangle getEdges(){
        return new Rectangle(x, y, width, height);
    }
}
