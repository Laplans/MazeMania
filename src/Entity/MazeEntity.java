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
public class MazeEntity extends Entity {
    
    private Game game;
    
    public MazeEntity(Game game, String ref, int x, int y){
        super(game.getSprite(ref), x, y);
        
        this.game = game;
    }
    
}
