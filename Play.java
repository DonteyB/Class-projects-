package project5game;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Play.java
 * @author Dontey Branch 
 * @version 4/20/16
 */
public class Play extends BasicGameState{

    Animation player, playerUp, playerDown, playerLeft, playerRight;
    Sound themeSong, death, jumpSound;
    boolean quit = false; 
    int[] duration = {200,200};
    float playerXPos = 0;
    float playerYPos = 718;
    Image longPlatformOne, longPlatformTwo, donkeyKong, girl, backGround, floor, shortPlatform, shortLadder;
    int xPosition = 0;
    int yPosition = 0;
    String mouse = "";
    Barrel barrel = new Barrel(.4f, -.4f, .4f, .4f);
    Barrel barrel2 = new Barrel(.4f, -.4f, -.4f, .4f);
    Barrel barrel3 = new Barrel(.3f, -.3f, .3f, .3f);
    Barrel barrel4 = new Barrel(.3f, -.3f, -.3f, .3f);
    int lives = 3;

    /**
     *
     * @param state
     * @throws SlickException
     */
    public Play(int state) throws SlickException{
    }

    /**
     *
     * @return
     */
    @Override
    public int getID() {
        return 1;
    }

    /**
     *
     * @param gc
     * @param sbg
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.themeSong = new Sound("themeSong.wav");
        this.death = new Sound("death.wav");
        this.jumpSound = new Sound("hitKong.wav");
        this.backGround = new Image("backGround.png");
        this.floor = new Image("2dplatform.png");
        this.longPlatformOne = new Image("longPlatform.png");
        this.longPlatformTwo = new Image("longPlatform.png");
        this.shortPlatform = new Image("shortPlatform.png");
        this.shortLadder = new Image("shortLadder.png");
        this.donkeyKong = new Image("donkeyKong.png");
        this.girl = new Image("girl.png");
        Image[] goUp = {new Image("marioBack.png"), new Image("marioBack.png")};
        Image[] goRight = {new Image("marioRight.png"), new Image("marioRight.png")};
        Image[] goLeft = {new Image("marioLeft.png"), new Image("marioLeft.png")};
        Image[] goDown = {new Image("marioFront.png"), new Image("marioFront.png")};
        playerUp = new Animation(goUp, duration, false);
        playerRight = new Animation(goRight, duration, false);
        playerLeft = new Animation(goLeft, duration, false);
        playerDown = new Animation(goDown, duration, false);
        player = playerDown;
        themeSong.loop();
    }

    /**
     *
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(floor, 0, 750, Color.red);
        g.drawImage(floor, 500, 750, Color.red);
        g.drawImage(longPlatformOne, -10, 650);
        g.drawImage(longPlatformTwo, 545, 650);
        g.drawImage(longPlatformOne, -200, 550);
        g.drawImage(longPlatformOne, 460, 550);
        g.drawImage(longPlatformOne, 150, 450);
        g.drawImage(longPlatformOne, 500, 350);
        g.drawImage(longPlatformOne, -300, 350);
        g.drawImage(longPlatformOne, 150, 250);
        g.drawImage(longPlatformOne, 340, 150);
        g.drawImage(shortLadder, 500, 650);
        g.drawImage(shortLadder, 310, 550);
        g.drawImage(shortLadder, 970, 550);
        g.drawImage(shortLadder, 664, 450);
        g.drawImage(shortLadder, 466, 350);
        g.drawImage(shortLadder, 210, 350);
        g.drawImage(shortLadder, 116, 450);
        g.drawImage(shortLadder, 655, 250);
        g.drawImage(shortLadder, 116, 250);
        g.drawImage(shortLadder, 310, 150);
        g.drawImage(longPlatformOne, 700, 450);
        g.drawImage(donkeyKong, 500, 80);
        g.drawImage(girl, 600, 100);
        player.draw(playerXPos, playerYPos, 30, 30);
        barrel.paint(g);
        barrel4.paint(g);
        barrel2.paint(g);
        barrel3.paint(g);
        g.setColor(Color.white);
        g.drawString(mouse, 0, 50);
        Input input = gc.getInput();
        if(quit==true){
            g.drawString("Resume (R)", 0, 100);
            g.drawString("Main Menu (M)", 0, 150);
            g.drawString("Quit Game (Q)", 0, 200);
            if(input.isKeyDown(Input.KEY_Q)){
                System.exit(0);
            }
            if(input.isKeyDown(Input.KEY_M)){
                sbg.enterState(0);
            }
            if(input.isKeyDown(Input.KEY_R)){
                quit = true;
            }
            if(quit == false)
                g.clear();
        }
    }

    /**
     *
     * @param gc
     * @param sbg
     * @param i
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        player = playerDown;
        if(barrel.update(playerXPos, playerYPos) || barrel2.update(playerXPos, playerYPos) || barrel3.update(playerXPos, playerYPos) || barrel4.update(playerXPos, playerYPos)){
            lives--;
            playerXPos = 0;
            playerYPos = 715;
            death.play();
            if(checkLives() == false){
                sbg.enterState(3);
                lives = 3;
            }
        }
        mouse = " Lives: " + lives;
        if(inLongPlatform(0, 650) || inLongPlatform(500, 650) || inFloor() || inLongPlatform(-180, 550) || inLongPlatform(455, 550) || inLongPlatform(145, 450) || inLongPlatform(700, 450) || inLongPlatform(490, 350) || inLongPlatform(-300, 350) || inLongPlatform(145, 250) || inLongPlatform(335, 150)){
            while(inLongPlatform(0, 650) || inLongPlatform(500, 650) || inFloor() || inLongPlatform(-180, 550) || inLongPlatform(455, 550) || inLongPlatform(145, 450) || inLongPlatform(700, 450) || inLongPlatform(490, 350) || inLongPlatform(-300, 350) || inLongPlatform(145, 250) || inLongPlatform(335, 150)){
                playerYPos -= i * .15f;
                if(input.isKeyDown(Input.KEY_SPACE)){
                    float jump = 25;
                    float gravity = .2f;
                    player = playerUp;
                      playerYPos = playerYPos - (i * 18f);
                        jump = jump - gravity;
                }
            }
        }
        else if(inShortLadder(500, 650) || inShortLadder(310, 550) || inShortLadder(958, 550) || inShortLadder(654, 450) || inShortLadder(460, 350) || inShortLadder(201, 350) || inShortLadder(116, 450) || inShortLadder(651, 250) || inShortLadder(110, 250) || inShortLadder(305, 150)){
            if(input.isKeyDown(Input.KEY_UP)){
                player = playerUp;
                playerYPos -= i * .15f;
            }
            if(input.isKeyDown(Input.KEY_DOWN)){
            player = playerDown;
            playerYPos += i * .15f;
            }
        }
        else if(inLongPlatform(0, 650) == false && inLongPlatform(500, 650) == false && inFloor() == false && inLongPlatform(-180, 550) == false && inLongPlatform(455, 550) == false && inLongPlatform(145, 450) == false && inLongPlatform(700, 450) == false && inLongPlatform(490, 350) == false && inLongPlatform(-300, 350) == false && inLongPlatform(145, 250) == false && inLongPlatform(335, 150) == false && inShortLadder(500, 650) == false && inShortLadder(310, 550) == false && inShortLadder(974, 550) == false && inShortLadder(654, 450) == false && inShortLadder(460, 350) == false && inShortLadder(201, 350) == false && inShortLadder(116, 450) == false && inShortLadder(651, 250) == false && inShortLadder(110, 250) == false){
            playerYPos += i * .15f;
        }
        if(inDonkeyKong(470, 80)){
            sbg.enterState(2);
            playerYPos = 718;
            playerXPos = 0;
            jumpSound.play();
        }
        if(input.isKeyDown(Input.KEY_LEFT)){
            player = playerLeft;
            playerXPos -= i * .15f;
            if((playerXPos > 1000 || playerXPos < 0)){
                playerXPos += i * .15f;
            }
        }
        if(input.isKeyDown(Input.KEY_RIGHT)){
            player = playerRight;
            playerXPos += i * .15f;
            if((playerXPos > 975 || playerXPos < 0)){
                playerXPos -= i * .15f;
            }
        }
        if(input.isKeyDown(Input.KEY_ESCAPE)){
            quit = true;
        }
        
    }
    
    /**
     *
     * @return inside
     * Checks if player has collision with the floor. 
     */
    public boolean inFloor(){
        boolean inside = false;
        if((playerYPos > 720)){
                inside = true;
            }
        return inside;
    }
    
    /**
     *
     * @param x
     * @param y
     * @return inside 
     * Checks if player has collision with the long platform. 
     */
    public boolean inLongPlatform(int x, int y){
        boolean inside = false;
        if((playerXPos > (x+ 5) && playerXPos < (x + 488)) && (playerYPos > (y - 30) && playerYPos < (y))){
            inside = true;
        }
        return inside;
    }
    
    /**
     *
     * @param x
     * @param y
     * @return inside 
     * Checks if player has collision with the short ladder. 
     */
    public boolean inShortLadder(int x, int y){
        boolean inside = false;
        int ladderWidth = 34;
        int ladderHeight = 100;
        if((playerXPos > x && playerXPos < (x + ladderWidth)) && playerYPos > (y - 28) && playerYPos < (y + ladderHeight)){
            inside = true;
        }
        return inside;
    }
    
    /**
     *
     * @param x
     * @param y
     * @return inside
     * Checks if player has collision with the donkey kong. 
     */
    public boolean inDonkeyKong(int x, int y){
        boolean inside = false;
        int ladderWidth = 78;
        int ladderHeight = 64;
        if((playerXPos > x && playerXPos < (x + ladderWidth)) && playerYPos > (y - 28) && playerYPos < (y + ladderHeight)){
            inside = true;
        }
        return inside;
    }
    
    /**
     *
     * @return playerXPos
     */
    public float getPlayerX(){
        return playerXPos;
    }
    
    /**
     *
     * @return playerYPos
     */
    public float getPlayerY(){
        return playerYPos;
    }
    
    /**
     *
     * @return livesLeft
     * Check to see if there are any lives left.
     */
    public boolean checkLives(){
        boolean livesLeft = true;
        if(lives <= 0)
           livesLeft = false;
        return livesLeft;
    }
}
