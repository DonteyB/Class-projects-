package project5game;






import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


/**
 * Barrel.java
 * @author Dontey Branch 
 * @version 4/1/16
 */
public class Barrel {
    private float x = 500;
    private float y = 80;
    private float upSpeed;
    private float downSpeed;
    private float  xVelocity;
    private float yVelocity;
    Image barrel;

    private int size = 8;
    
    /**
     *
     * @param _upSpeed
     * @param _downSpeed
     * @param _xVelocity
     * @param _yVelocity
     * Constructor for Barrel Class.
     */
    public Barrel(float _upSpeed, float _downSpeed, float _xVelocity, float _yVelocity){
        this.upSpeed = _upSpeed;
        this.downSpeed = _downSpeed;
        this.xVelocity = _xVelocity;
        this.yVelocity = _yVelocity;
    }

    /**
     *
     * @param playerX
     * @param playerY
     * @return inside
     * @throws org.newdawn.slick.SlickException 
     * Updates the barrel and returns a boolean for the playerColision method.
     */
    public boolean update(float playerX, float playerY) throws SlickException{
        x = x + xVelocity;
        y = y + yVelocity;
        boolean inside = false;
        this.barrel = new Image("barrel.png");
        if(inLongPlatform(0, 650) || inLongPlatform(500, 650) || inFloor() || inLongPlatform(-180, 550) || inLongPlatform(455, 550) || inLongPlatform(145, 450) || inLongPlatform(700, 450) || inLongPlatform(490, 350) || inLongPlatform(-300, 350) || inLongPlatform(145, 250) || inLongPlatform(335, 150)){
            while(inLongPlatform(0, 650) || inLongPlatform(500, 650) || inFloor() || inLongPlatform(-180, 550) || inLongPlatform(455, 550) || inLongPlatform(145, 450) || inLongPlatform(700, 450) || inLongPlatform(490, 350) || inLongPlatform(-300, 350) || inLongPlatform(145, 250) || inLongPlatform(335, 150)){
                y -= 10 * .15f;
                
            }
        }
        if(playerCollision(playerX, playerY)){
            x = 500;
            y = 80;
            inside = true;
        }
        if(inLongPlatform(0, 645))
        {
            x = 500;
            y = 80;
        }
        if(x < 0)
            xVelocity = upSpeed;
        else if(x + size > 975)
            xVelocity = downSpeed;
        if(y < 0)
            yVelocity = upSpeed;
        else if(y + size > 800)
            yVelocity = downSpeed;
        paint(new Graphics());
        
        return inside;
    }
    
    /**
     *
     * @param g
     * Paints the barrel image.
     */
    public void paint(Graphics g){
        g.drawImage(barrel, x, y);
    }
    
    /**
     *
     * @param xPos
     * @param yPos
     * @return inside
     * Checks if barrel has collision with the long platform. 
     */
    public boolean inLongPlatform(int xPos, int yPos){
        boolean inside = false;
        if((x > (xPos+ 5) && x < (xPos + 488)) && (y > (yPos - 30) && y < (yPos))){
            inside = true;
        }
        return inside;
    }
     
    /**
     *
     * @return
     * Checks if barrel has collision with the floor. 
     */
    public boolean inFloor(){
        boolean inside = false;
        if((y > 720)){
                inside = true;
            }
        return inside;
    }
     
    /**
     *
     * @param playerX
     * @param playerY
     * @return
     * Checks if barrel has collision with the player. 
     */
    public boolean playerCollision(float playerX, float playerY){
        boolean inside = false;
        if((x > (playerX) && x < (playerX + 20)) && (y > (playerY - 20) && y < (playerY))){
            x = 500; 
            y = 80;
            inside = true;
        }
        return inside;
    }
}
