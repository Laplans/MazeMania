package Main;

import Entity.Entity;
import Entity.MazeEntity;
import Entity.PlayerEntity;
import Texture.Sprite;
import Texture.TextureLoader;
import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Game{
    
    /** Game title */
    public static final String GAME_TITLE = "Maze Mania";
    
    private int width = 800;
    
    private int height = 600;
    
    private TextureLoader textureLoader;
    
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    
    private long lastLoopTime = getTime();
    
    private long lastFpsTime;
    
    private int fps;
    
    private static long timerTicksPerSecond = Sys.getTimerResolution();
    
    public static boolean gameRunning = true;
    
    private boolean fullscreen;
    
    private static boolean isApplication;
    
    private PlayerEntity player;
    
    public Game(boolean fullscreen){
        this.fullscreen = fullscreen;
        initialize();
    }
    
    public static long getTime(){
        return (Sys.getTime() * 1000) / timerTicksPerSecond;
    }
    
    public static void sleep (long duration){
        try{
            Thread.sleep((duration*timerTicksPerSecond)/1000);
        } catch(InterruptedException e){
            System.out.println("Sleep");
        }
    }
    
    public void initialize(){
        try{
            setDisplayMode();
            Display.setTitle(GAME_TITLE);
            Display.setFullscreen(fullscreen);
            Display.create();
            
            
            glEnable(GL_TEXTURE_2D);
            
            glDisable(GL_DEPTH_TEST);
            
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            
            glOrtho(0, width, height, 0, -1, 1);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            glViewport(0, 0, width, height);
            
            textureLoader = new TextureLoader();
            
            
        } catch(LWJGLException e){
            System.out.println("SOMETHING WENT TERRIBLY BAD");
            Game.gameRunning = false;
            return;
        }
        
        startGame();
    }
    
    private boolean setDisplayMode(){
        try{
            DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(width, height, -1, -1, -1, -1, 60, 60);
            
            org.lwjgl.util.Display.setDisplayMode(dm, new String[]{
                "width=" + width,
                "height=" + height,
                "freq=" + 60,
                "bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
            });
            return true;
        } catch(Exception e){
            System.out.println("No fullscreen");
        }
        return false;
    }
    
    private void startGame(){
        entities.clear();
        initEntities();
    }
    
    private void initEntities(){
        Entity mazeobject = new MazeEntity(this,"Images/Parts/11.Four_Way.jpg" ,200,200);
        entities.add(mazeobject);
        mazeobject = new MazeEntity(this,"Images/Parts/11.Four_Way.jpg" ,300,200);
        entities.add(mazeobject);
        mazeobject = new MazeEntity(this,"Images/Parts/11.Four_Way.jpg" ,200,300);
        entities.add(mazeobject);
        mazeobject = new MazeEntity(this,"Images/Parts/11.Four_Way.jpg" ,300,300);
        entities.add(mazeobject);
        player = new PlayerEntity(this,"Images/player.jpg",310,250);
        entities.add(player);
    }
    
    private void gameLoop(){
        while(Game.gameRunning){
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            
            checkInput();
            frameRendering();

            Display.update();
        }
        
        Display.destroy();
    }
    
    public void checkInput(){
        player.setVerticalMovement(0);
        player.setHorizontalMovement(0);
        
        boolean left = hasInput(Keyboard.KEY_LEFT);
        boolean right = hasInput(Keyboard.KEY_RIGHT);
        boolean down = hasInput(Keyboard.KEY_DOWN);
        boolean up = hasInput(Keyboard.KEY_UP);
        
        if((left) && (!right))player.setHorizontalMovement(-1);
        if((!left) && (right))player.setHorizontalMovement(1);
        if((up) && (!down))player.setVerticalMovement(-1);
        if((!up) && (down))player.setVerticalMovement(1);
    }
    
    public void frameRendering(){
        Display.sync(60);
        
        long delta = getTime() - lastLoopTime;
        lastLoopTime = getTime();
        lastFpsTime += delta;
        fps++;
        
        if(lastFpsTime >= 1000){
            Display.setTitle(GAME_TITLE + "(FPS: " + fps + ")");
            lastFpsTime = 0;
            fps = 0;
        }
        

        
        for(Entity entity : entities){
            entity.move();
            entity.draw();
        }
        
        
        if((Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) && isApplication){
            Game.gameRunning = false;
        }
    }
    
    public static void main(String argv[]){
        isApplication = true;
        System.out.println("Startin...");
        new Game ((argv.length > 0 && "-fullscreen".equalsIgnoreCase(argv[0]))).execute();
        System.exit(0);
    }
    
    public void execute(){
        gameLoop();
    }
    
    public Sprite getSprite(String ref){
        return new Sprite(textureLoader, ref);
    }

    private boolean hasInput(int direction) {
        switch(direction){
            case Keyboard.KEY_LEFT:
                return
                        Keyboard.isKeyDown(Keyboard.KEY_LEFT);
                
            case Keyboard.KEY_RIGHT:
                return
                        Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
            case Keyboard.KEY_DOWN:
                 return
                        Keyboard.isKeyDown(Keyboard.KEY_DOWN);
            case Keyboard.KEY_UP:
                return
                        Keyboard.isKeyDown(Keyboard.KEY_UP);
        }
        return false;
    }
}
