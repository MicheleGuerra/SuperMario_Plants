package gioco_esame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Gioco extends Canvas implements KeyListener, Runnable, MouseMotionListener, MouseListener {

    public static final int width = 1366;
    public static final int height = 768;
    public static final String game_name = "Super Mario Plants";
    public static int game_character = 1;

    BufferedImage background = null;
    BufferedImage background_menu = null;
    BufferedImage background_game_over = null;
    BufferedImage background_setting = null;
    BufferedImage background_win = null;
    BufferedImage background_characters = null;
    BufferedImage character = null;
    BufferedImage character1 = null;
    BufferedImage character2 = null;
    BufferedImage player = null;
    BufferedImage enemy = null;
    BufferedImage bullet = null;
    BufferedImage barra = null;
    BufferedImage life_barr = null;
    BufferedImage bonus = null;

    public static boolean activeGame = false;
    private Personaggio obj_character;
    private Personaggio obj_character1;
    private Personaggio obj_character2;
    private Giocatore obj_player;
    private Pioggia pioggia;
    private Giocatore obj_bullet;
    private Barra obj_barr;
    private LifeBarr obj_lifeBarr;
    //private Bonus obj_bonus;

    private int goombaNumber = 1;

    public Audio music;

    public static ArrayList<Bullet> bullets;

    public static enum STATE {
        MENU,
        GAME,
        SETTINGS,
        CHARACTER,
        GAME_OVER,
        WINNER
    };

    public static STATE State = STATE.MENU;

    public Gioco() {
        loadResurces();
        startGame();
    }

    public static void main(String[] args) {

        Gioco game = new Gioco();

        JFrame game_panel = new JFrame(game_name);
        Dimension panel_dimension = new Dimension(width, height);
        game_panel.setPreferredSize(panel_dimension);
        game_panel.setMaximumSize(panel_dimension);
        game_panel.setResizable(false);

        game_panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game_panel.add(game);
        game.addKeyListener(game);
        game.addMouseListener(game);
        game.addMouseMotionListener(game);
        game.addMouseListener(new MouseInput());

        game_panel.pack();
        game_panel.setVisible(true);

        Thread thread_game = new Thread(game);
        thread_game.start();
    }

    private void startGame() {

        try {
            this.music = new Audio();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Gioco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Gioco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Gioco.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj_player = new Giocatore(player, bullet, 0, 510, 80, 115, this);
        obj_barr = new Barra(barra, 0, 758, 1366, 143, this);

        obj_character = new Personaggio(character, 70, 100, 0, 635, this);
        obj_character1 = new Personaggio(character1, 70, 100, 0, 635, this);
        obj_character2 = new Personaggio(character2, 70, 100, 0, 635, this);

        obj_lifeBarr = new LifeBarr(life_barr, -400, -40, 150, this);
        //obj_bonus = new Bonus(bonus, 250, 50, 100, 100, this);
        obj_character.start();
        obj_character1.start();
        obj_character2.start();

        pioggia = new Pioggia(enemy, 700, this);
        pioggia.start();

        this.music.PlayMusicMenu();

    }

    private void loadResurces() {

        ImageLoader loader = new ImageLoader();

        background = loader.loadImage("/immagini/sfondomario.jpg");
        background_menu = loader.loadImage("/immagini/menustartmod.png");
        background_game_over = loader.loadImage("/immagini/GameOver1.jpg");
        background_setting = loader.loadImage("/immagini/menudifficolta.png");
        background_win = loader.loadImage("/immagini/winner_panel.jpg");
        background_characters = loader.loadImage("/immagini/menupersonaggi.png");
        character = loader.loadImage("/immagini/mario.png");
        character1 = loader.loadImage("/immagini/luigi.png");
        character2 = loader.loadImage("/immagini/toad.png");
        player = loader.loadImage("/immagini/pianta.png");
        enemy = loader.loadImage("/immagini/goomba.png");
        bullet = loader.loadImage("/immagini/fuoco.png");
        barra = loader.loadImage("/immagini/barra.png");
        life_barr = loader.loadImage("/immagini/lifeBarr.png");
        //bonus = loader.loadImage("/immagini/fungo.png");
        System.out.println("risorse caricate");
    }

    private void draw() {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            createBufferStrategy(4);
            return;
        }
        Graphics g = buffer.getDrawGraphics();
        if (State == STATE.MENU) {
            g.drawImage(background_menu, 0, 0, this);
        } else if (State == STATE.GAME) {
            g.drawImage(background, 0, 0, width, height, this);

            /*if(this.obj_player.number_of_enemies == 200){
                obj_bonus.draw(g);
            }*/
            if (game_character == 1) {
                obj_character.draw(g);
            }

            if (game_character == 2) {
                obj_character1.draw(g);
            }

            if (game_character == 3) {
                obj_character2.draw(g);
            }

            obj_player.draw(g);
            obj_barr.draw(g);
            pioggia.draw(g);
            obj_lifeBarr.draw(g);

            Font font1 = new Font("arial", Font.BOLD, 14);
            g.setFont(font1);
            //g.drawString("VITA:"+obj_character.life, 25, 25);
            if (this.obj_player.number_of_enemies >= 0 && this.obj_player.number_of_enemies <= 10) {
                g.drawString("GOOMBA MANGIATI: " + obj_player.number_of_enemies + "    PRINCIPIANTE!", 20, 60);
            }
            if (this.obj_player.number_of_enemies >= 11 && this.obj_player.number_of_enemies <= 20) {
                g.drawString("GOOMBA MANGIATI: " + obj_player.number_of_enemies + "    STAI MIGLIORANDO!", 20, 60);
            }
            if (this.obj_player.number_of_enemies >= 21 && this.obj_player.number_of_enemies <= 30) {
                g.drawString("GOOMBA MANGIATI: " + obj_player.number_of_enemies + "    KILLER JUNIOR!", 20, 60);
            }
            if (this.obj_player.number_of_enemies >= 31 && this.obj_player.number_of_enemies <= 50) {
                g.drawString("GOOMBA MANGIATI: " + obj_player.number_of_enemies + "    KILLER!", 20, 60);
            }
            if (this.obj_player.number_of_enemies >= 51) {
                g.drawString("GOOMBA MANGIATI: " + obj_player.number_of_enemies + "    PROFESSIONE: DIVORATORE DI GOOMBA!", 20, 60);
            }
            if (this.obj_player.number_of_enemies >= 100) {
                State = STATE.WINNER;
            }

            //PER SPARARE!!!!!!!!!!!!!!!!!!!!!!!!
            /*if(bullets != null){
                for(Bullet p : bullets){
                    p.draw(g);
                }
            }*/
        } else if (State == STATE.SETTINGS) {
            g.drawImage(background_setting, 0, 0, width, height, this);
        } else if (State == STATE.WINNER) {
            g.drawImage(background_win, 0, 0, width, height, this);
        } else if (State == STATE.CHARACTER) {
            g.drawImage(background_characters, 0, 0, width, height, this);
        }

        if (!activeGame) {

            /*g.setColor(Color.gray);
                g.clearRect(0, 0, width, height);
                g.setColor(Color.red);
                g.drawString("HAI PERSO", 360, 640);*/
            g.drawImage(background_game_over, 0, 0, width, height, this);
            State = STATE.GAME_OVER;

        }

        g.dispose();
        buffer.show();
    }

    private void update() {
        if (State == STATE.GAME) {
            ArrayList<Nemico> enemies = pioggia.getEnemies();
            for (Nemico goomba : enemies) {
                if (ManagerCollisions.checkCollision(obj_player, goomba)) {
                    enemies.remove(goomba);
                    this.music.PlayGoombaMusic();
                    this.obj_player.number_of_enemies += 1;
                    break;
                }/////controllo per i 3 personaggi
                if (game_character == 1) {
                    if (ManagerCollisions.checkCollisionCharacter(obj_character, goomba)) {
                        enemies.remove(goomba);
                        this.music.PlayLifeMusic();
                        this.obj_character.life -= 30;
                        LifeBarr.width -= 180;
                        break;
                    }
                }
                if (game_character == 2) {
                    if (ManagerCollisions.checkCollisionCharacter(obj_character1, goomba)) {
                        enemies.remove(goomba);
                        this.music.PlayLifeMusic();
                        this.obj_character1.life -= 30;
                        LifeBarr.width -= 180;
                        break;
                    }
                }
                if (game_character == 3) {
                    if (ManagerCollisions.checkCollisionCharacter(obj_character2, goomba)) {
                        enemies.remove(goomba);
                        this.music.PlayLifeMusic();
                        this.obj_character2.life -= 30;
                        LifeBarr.width -= 180;
                        break;
                    }
                }

                if (ManagerCollisions.checkCollisionBarr(obj_barr, goomba)) {
                    enemies.remove(goomba);
                    if (game_character == 1) {
                        this.obj_character.life -= 5;
                        LifeBarr.width -= 30;
                        break;
                    }

                    if (game_character == 2) {
                        this.obj_character1.life -= 5;
                        LifeBarr.width -= 30;
                        break;
                    }

                    if (game_character == 3) {
                        this.obj_character2.life -= 5;
                        LifeBarr.width -= 30;
                        break;
                    }
                }
                /*bullets = obj_player.getBullets();
            for(Bullet p : bullets){
                if(ManagerCollisions.checkCollisionEnemy(p, ufo)){
                    enemies.remove(ufo);
                    break;
            }
                break;
        }*/

                if (checkDefeat()) {
                    this.activeGame = false;
                    draw();
                }
            }

        }
    }

    private boolean checkDefeat() {
        if (game_character == 1) {
            if (obj_character.life <= 0) {
                return true;
            }
            return false;
        }
        if (game_character == 2) {
            if (obj_character1.life <= 0) {
                return true;
            }
            return false;
        }
        if (game_character == 3) {
            if (obj_character2.life <= 0) {
                return true;
            }
            return false;
        }
        return checkDefeat();
    }

    @Override
    public void run() {
        activeGame = true;
        while (activeGame) {
            update();
            draw();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_LEFT:
                obj_player.moveLeft();
                break;

            case KeyEvent.VK_RIGHT:
                obj_player.moveRight();
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int position = (e.getPoint().x) - (obj_player.getWidth() / 2);

        if (position >= 0 && position + obj_player.getWidth() <= width) {
            obj_player.setX(position);
            //System.out.println("mouse moved");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        obj_player.spara();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
