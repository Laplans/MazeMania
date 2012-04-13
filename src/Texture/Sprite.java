/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Texture;


import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author kevintjeerdsma
 */
public class Sprite {
    
    /** Texture that stores the sprite */
    private Texture texture;
    
    /** Width in pixels */
    private int width;
    
    /** Height in pixels */
    private int height;
    
    public Sprite (TextureLoader loader, String ref){
        try{
            texture = loader.getTexture(ref);
            width = texture.getImageWidth();
            height = texture.getImageHeight();
        } catch(Exception e){
            System.out.println("SPRITE");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public int getWidth(){
        return texture.getImageWidth();
    }
    
    public int getHeight(){
        return texture.getImageHeight();
    }
    
    public void draw(int x, int y){
        glPushMatrix();
        
        texture.bind();
        
        glTranslatef(x,y,0);
             
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0,0);
            glVertex2f(0,0);
            
            glTexCoord2f(0, texture.getHeight());
            glVertex2f(0, height);
            
            glTexCoord2f(texture.getWidth(), texture.getHeight());
            glVertex2f(width, height);
            
            glTexCoord2f(texture.getWidth(),0);
            glVertex2f(width, 0);   
            
        }
        glEnd();
        
        glPopMatrix();
    }
    
}
