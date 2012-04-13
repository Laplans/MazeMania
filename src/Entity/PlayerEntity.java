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
public class PlayerEntity extends Entity{
    
    private Game game;
    private Entity entity;
    
    public PlayerEntity(Game game, String ref, int x, int y){
        super(game.getSprite(ref), x, y);
        this.game = game;
    }
    
    @Override
    public void move(long delta){
        for (int i = 0; i < game.entities.size(); i++) {
            if(game.entities.get(i).collidesWith(this) && !game.entities.get(i).equals(this)){
                entity = game.entities.get(i);
                if(entity.sprite.isWhiteOrBlack((int)x -(int)entity.x,(int)y-(int)entity.y)){
                    dx=0;
                    dy=0;
                }
            }
        }
        super.move(delta);
    }    
}
