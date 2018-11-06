
package gioco_esame;


public class ManagerCollisions {
    
    
    
    public static boolean checkCollision(Giocatore player, Nemico enemy){
        return player.getEdges().intersects(enemy.getEdges());
    }
    
    public static boolean checkCollisionCharacter(Personaggio character, Nemico enemy){
        return character.getEdges().intersects(enemy.getEdges());
    }
    
    public static boolean checkCollisionEnemy(Bullet bullet, Nemico enemy){
        return bullet.getEdges().intersects(enemy.getEdges());
    }
    
    public static boolean checkCollisionBarr(Barra barr, Nemico enemy){
        return barr.getEdges().intersects(enemy.getEdges());
    }
}
