/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Texture;

import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author kevintjeerdsma
 */
public class Texture {
    /** The GL target type */
    private int		target;

    /** The GL texture ID */
    private int		textureID;

    /** The height of the image */
    private int		height;

    /** The width of the image */
    private int		width;

    /** The width of the texture */
    private int		texWidth;

    /** The height of the texture */
    private int		texHeight;

    /** The ratio of the width of the image to the texture */
    private float	widthRatio;

    /** The ratio of the height of the image to the texture */
    private float	heightRatio;
    
    public Texture(int target, int textureID){
        this.target = target;
        this.textureID = textureID;
    }
    
    public void bind(){
        glBindTexture(target, textureID);
    }
    
    public void setHeight(int height){
        this.height = height;
        setHeight();
    }
    
    public void setWidth(int width){
        this.width = width;
        setWidth();
    }
    
    public int getImageHeight(){
        return height;
    }
    
    public int getImageWidth(){
        return width;
    }
    
    public float getHeight(){
        return heightRatio;
    }
    
    public float getWidth(){
        return widthRatio;
    }
    
    public void setTextureHeight(int texHeight){
        this.texHeight = texHeight;
        setHeight();
    }
    
    public void setTextureWidth(int texWidth){
        this.texWidth = texWidth;
        setWidth();
    }
    
    private void setHeight(){
        if(texHeight != 0){
            heightRatio = ((float) height) / texHeight;
        }
    }
    
    private void setWidth(){
        if(texWidth != 0){
            widthRatio = ((float) width) / texWidth;
        }
    }
}
