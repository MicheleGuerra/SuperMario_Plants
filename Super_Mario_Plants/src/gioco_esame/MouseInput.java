
package gioco_esame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MouseInput implements MouseListener {
    
    public Audio music;
    
    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mousePressed(MouseEvent e) {
        
        
        int mouseX = e.getX();
        int mouseY = e.getY();
        //MENU
        if(Gioco.State == Gioco.STATE.MENU){
            //IMPOSTAZIONI
            if(mouseX >= Gioco.width/2 -60 && mouseX <= Gioco.width/2 + 35){
                if(mouseY >= Gioco.height/2 +115 && mouseY <= Gioco.height/2 +210){
                    Gioco.State = Gioco.STATE.SETTINGS;
                    
                }
            }
            //PLAY
            if(mouseX >= Gioco.width/2 -170 && mouseX <= Gioco.width/2 - 85){
                if(mouseY >= Gioco.height/2 +115 && mouseY <= Gioco.height/2 +210){
                    Gioco.State = Gioco.STATE.GAME;
                }
            }
            //EXIT
            if(mouseX >= Gioco.width/2 +50 && mouseX <= Gioco.width/2 + 140){
                if(mouseY >= Gioco.height/2 +115 && mouseY <= Gioco.height/2 +210){
                    System.exit(1);
                }
            }
        }
        //SETTINGS
        //DIFFICOLTA'
        if(Gioco.State == Gioco.STATE.SETTINGS){
            
            //media
            if(mouseX >= Gioco.width/2 -70 && mouseX <= Gioco.width/2 +120){
                if(mouseY >= Gioco.height/2 -45 && mouseY <= Gioco.height/2 +90){
                    Pioggia.number = 1;
                    Pioggia.max_speed = 50;
                    Gioco.State = Gioco.STATE.MENU;
                }
            }
            //facile
            if(mouseX >= Gioco.width/2 -500 && mouseX <= Gioco.width/2 -320){
                if(mouseY >= Gioco.height/2 -45 && mouseY <= Gioco.height/2 + 90){
                    Pioggia.number = 1;
                    Pioggia.max_speed = 35;
                    Gioco.State = Gioco.STATE.MENU;
                }
            }
            //difficile
            if(mouseX >= Gioco.width/2 +350 && mouseX <= Gioco.width/2 +540){
                if(mouseY >= Gioco.height/2 -45 && mouseY <= Gioco.height/2 +90){
                    Pioggia.number = 2;
                    Pioggia.max_speed = 50;
                    Gioco.State = Gioco.STATE.MENU;
                }
            }
            //seleziona personaggio
            if(mouseX >= 0 && mouseX <= 300){
                if(mouseY >= 668 && mouseY <= 768){
                    Gioco.State = Gioco.STATE.CHARACTER;
                }
            }
        }
        if(Gioco.State == Gioco.STATE.GAME_OVER){
            //restart
            if(mouseX >= 0 && mouseX <= 300){
                if(mouseY >= 0 && mouseY <= 150){
                    /*try {
                        Runtime.getRuntime().exec( );
                            System.exit(0);
                    } catch (Exception e) {}*/
                    System.exit(1);
                }
            }
        }
        if(Gioco.State == Gioco.STATE.WINNER){
            if(mouseX >= 0 && mouseX <= 300){
                if(mouseY >= 0 && mouseY <= 150){
                    System.exit(1);
                }
            }
        }
        if(Gioco.State == Gioco.STATE.CHARACTER){
            //luigi
            if(mouseX >= Gioco.width/2 -70 && mouseX <= Gioco.width/2 +120){
                if(mouseY >= Gioco.height/2 -45 && mouseY <= Gioco.height/2 +90){
                    Gioco.game_character = 2;
                    Gioco.State = Gioco.STATE.MENU;
                }
            }
            //mario
            if(mouseX >= Gioco.width/2 -500 && mouseX <= Gioco.width/2 -320){
                if(mouseY >= Gioco.height/2 -45 && mouseY <= Gioco.height/2 + 90){
                    Gioco.State = Gioco.STATE.MENU;
                }
            }
            //toad
            if(mouseX >= Gioco.width/2 +350 && mouseX <= Gioco.width/2 +540){
                if(mouseY >= Gioco.height/2 -45 && mouseY <= Gioco.height/2 +90){
                    Gioco.game_character = 3;
                    Gioco.State = Gioco.STATE.MENU;
                }
            }
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
    
    
    
    
}
