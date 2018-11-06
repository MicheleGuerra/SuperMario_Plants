
package gioco_esame;

import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import static jdk.nashorn.tools.ShellFunctions.input;


public class Audio {
    
    AudioInputStream menuMusic = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/audio/remix.wav")));
    //AudioInputStream gameMusic = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/audio/super_mario_game.wav")));
    //AudioInputStream gameOverMusic = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/audio/super_mario_game_over.wav")));
    AudioInputStream goombaMusic = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/audio/goomba1.wav")));
    AudioInputStream lifeMusic = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/audio/lose_life.wav")));

    Clip menu_clip;
    //Clip game_clip;
    //Clip game_over_clip;
    Clip goomba_clip;
    Clip life_clip;

    public Audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        
        this.menu_clip = AudioSystem.getClip();
        menu_clip.open(menuMusic);
        
        //this.game_clip = AudioSystem.getClip();
        //game_clip.open(menuMusic);
        
        //this.game_over_clip = AudioSystem.getClip();
        //game_over_clip.open(menuMusic);
        
        this.goomba_clip = AudioSystem.getClip();
        goomba_clip.open(goombaMusic);
        
        this.life_clip = AudioSystem.getClip();
        life_clip.open(lifeMusic);
        
        
    }
    
    public void PlayMusicMenu(){
        this.menu_clip.setFramePosition(0);
        this.menu_clip.start();
    }
    
    public void StopMusicMenu(){
        this.menu_clip.stop();
        this.menu_clip.close();
    }
    
    /*public void PlayMusicGame(){
        this.game_clip.setFramePosition(0);
        this.game_clip.start();
    }
    
    public void StopMusicGame(){
        this.game_clip.stop();
        this.game_clip.close();
    }
    
    public void PlayMusicGameOver(){
        this.game_over_clip.setFramePosition(0);
        this.game_over_clip.start();
    }
    
    public void StopMusicGameOver(){
        this.game_over_clip.stop();
        this.game_over_clip.close();
    }*/
    
    public void PlayGoombaMusic(){
        this.goomba_clip.setFramePosition(0);
        this.goomba_clip.start();
    }
    
    public void PlayLifeMusic(){
        this.life_clip.setFramePosition(0);
        this.life_clip.start();
    }
}
