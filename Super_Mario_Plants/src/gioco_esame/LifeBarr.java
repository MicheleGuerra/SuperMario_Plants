
package gioco_esame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class LifeBarr {
    
    private int x;
    private int y;
    public static int width = 1200;
    private int height;
    private Gioco main;
    
    BufferedImage img_barr;
    
    
    public LifeBarr(){
        
    }
    
    public LifeBarr(BufferedImage image, int x, int y,  int height, Gioco main){
        
        this.x = x;
        this.y = y;
        this.height = height;
        this.img_barr = image;
        this.main = main;
    }
    
    public void draw(Graphics g) {
        g.drawImage(img_barr, x, y, width, height, null);
    }
    
}
