/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Main.Game;

/**
 *
 * @author kevintjeerdsma
 */
public class ShotEntity extends Entity{
    
    private float moveSpeed = 3;
    private Game game;
    private boolean used;
    public Entity entity;
    
    public ShotEntity(Game game, String sprite, int x, int y){
        super(game.getSprite(sprite),x,y);
        
        this.game = game;
        dy = moveSpeed;
    }
    
    public void reinitialize(int x, int y){
        this.x=x;
        this.y=y;
        used = false;
    }
    
    public void move(){
        super.move();
        for (int i = 0; i < game.entities.size(); i++) {
            if(game.entities.get(i).collidesWith(this) && !game.entities.get(i).equals(this)){
                entity = game.entities.get(i);
                if(!entity.sprite.isWhiteOrBlack((int)x + (int)dx -(int)entity.x,(int)y + (int)dy-(int)entity.y)){
                    super.move();
                }     
            }
        }
    }
}
