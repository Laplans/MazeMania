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
    private ShotEntity shot;
    
    public PlayerEntity(Game game, String ref, int x, int y){
        super(game.getSprite(ref), x, y);
        this.game = game;
    }
    
    public void fire(){
        if(shot == null){
            System.out.println("shot!");
            shot = new ShotEntity(game,"Images/shot.jpg",(int)x+(int)dx,(int)y+(int)dy);
            game.entities.add(shot);
        }else{
            shot.reinitialize((int)x+(int)dx,(int)y+(int)dy);
        }
    }
    
    @Override
    public void move(){
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
