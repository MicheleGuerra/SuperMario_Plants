
package gioco_esame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class ImageLoader {
    BufferedImage image;
    
    public BufferedImage loadImage(String position){
        try {
            image = ImageIO.read(getClass().getResource(position));
        } catch (IOException ex) {
            System.out.println("Immagine alla posizione:"+position+"caricata correttamente");
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
}
