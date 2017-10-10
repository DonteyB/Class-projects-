package project5game;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



/**
 * LoseScreen.java
 * @author Dontey Branch
 * @version 4/20/16
 */
public class LoseScreen extends BasicGameState{
    
    Image backGround;
    public int strtX = 400;
    public int strtY = 300;
        
    /**
     *
     * @param state
     */
    public LoseScreen(int state){
        
    }
    
    /**
     *
     * @param gc
     * @param stateGame
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame stateGame) throws SlickException{
        backGround = new Image("loseScreen.jpe");
    }

    /**
     *
     * @return
     */
    @Override
    public int getID() {
        return 3;
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
        g.drawImage(backGround, 100, 270);
        g.setColor(Color.white);
        g.drawString("Start (S)", 450, 700);
        g.drawString("Exit (E)", 450, 720);      
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
        if(input.isKeyDown(Input.KEY_S)){
            sbg.enterState(1);
        }
        if(input.isKeyDown(Input.KEY_E)){
            System.exit(0);
        }
        
    }
    
}
    
