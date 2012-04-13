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
    
    public PlayerEntity(Game game, String ref, int x, int y){
        super(game.getSprite(ref), x, y);
        this.game = game;
    }
    
    @Override
    public void move(long delta){
        super.move(delta);
    }
    
    @Override
    public void collidedWith(Entity other){
        
     
    }
    
    
}
