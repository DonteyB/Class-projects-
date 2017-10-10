
package project5game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.*;

/**
 * Project5Driver.java
 * @author Dontey Branch 
 * @version 4/20/16
 * @
 */
public class Project5Driver extends StateBasedGame{

    public static String gameName = "Kong";
    public int menu = 0;
    public int play = 1;
    public int winScreen = 2;
    public int loseScreen = 3;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AppGameContainer gContainer;
        try{
            gContainer = new AppGameContainer(new Project5Driver(gameName));
            gContainer.setDisplayMode(1000, 800, false);
            gContainer.start();
        }
        catch(SlickException e){
            e.printStackTrace();
        }
        
    }

    /**
     *
     * @param name
     * @throws SlickException
     * This method adds all the states to the game.
     */
    public Project5Driver(String name) throws SlickException {
        super(name);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new WinScreen(winScreen));
        this.addState(new LoseScreen(loseScreen));
    }

    /**
     *
     * @param gc
     * @throws SlickException
     * This method inits all the states in the game.
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.getState(winScreen).init(gc, this);
        this.getState(loseScreen).init(gc, this);
        this.enterState(menu);
    }
    
}
