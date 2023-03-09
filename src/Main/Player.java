package Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author comicsavi
 *	
 */
public class Player extends Entity{

	// The Player has a list of observers;
	private List<Observer> observers = new ArrayList<Observer>();
	
	// The Player has a state;
	private int state;
	
	// Possible states of the player: 
	// The player is IDLE, doing nothing;
	public int IDLE = 1;
	// The player just collected a point;
	public int COLLECTING_POINT = 2;
	// The player just died;
	public int DYING = 3;
	// The player is able to JUMP;
	public int JUMPING = 4;
	// The player just jumped;
	public int JUMPED = 0;

	/**
	 * 
	 * @return the current state of the player
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state 
	 * 		IDLE = 1
	 * 		COLLECTING_POINT = 2
	 * 		DYING = 3
	 * 		JUMPING = 4
	 * 		JUMPED = 5
	 */
	public void setState(int state) {
		this.state = state;
		
		// If IDLE no update is needed;
		if(this.state != this.IDLE)
			notifyAllObservers();
	}

	// Attach an observer to it;
	/**
	 * 
	 * @param observer: the observer object which will be 
	 * 	changed by the player state change 
	 */
	public void attach(Observer observer){
		observers.add(observer);		
	}

	// Update all observers;
	/**
	 * Update the observer with the change in 
	 * state; 
	 * * mainly the observer will be the
	 * game_stats object
	 */
	public void notifyAllObservers(){
		for (Observer observer : observers) {
			observer.update();
		}
	} 
	
	/**
	 *  Default position is in the center of the screen;
	 *  Default color is green;
	 *  Default size is game_stats.unit;
	 */
	Player()
	{
		super(Game_stats.number_per_width/2,Game_stats.number_per_height/2 - 1,  Color.GREEN,  Game_stats.unit);

	}

	// Generating a player with its default color and size, at the given position;
	/**
	 * 
	 * @param x spawn coordinate of player;
	 * @param y spawn coordinate of player;
	 * Default color is green;
	 * Default size is game_stats.unit;
	 */
	Player(int x, int y) {
		super(x, y,  Color.GREEN, Game_stats.unit);
	}
}
