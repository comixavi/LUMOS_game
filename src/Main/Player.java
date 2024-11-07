package Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity{

	// The Player has a list of observers;
	private List<Observer> observers = new ArrayList<>();
	
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

	// Returning the state;
	public int getState() {
		return state;
	}

	//Setting the state;
	public void setState(int state) {
		this.state = state;
		
		// If IDLE no update is needed;
		if(this.state != this.IDLE)
			notifyAllObservers();
	}

	// Attach an observer to it;
	public void attach(Observer observer){
		observers.add(observer);		
	}

	// Update all observers;
	public void notifyAllObservers(){
		for (Observer observer : observers) {
			observer.update();
		}
	} 

	// The default position, color and size of the player;
	Player()
	{
		super(Game_stats.number_per_width/2,Game_stats.number_per_height/2 - 1,  Color.GREEN,  Game_stats.unit);

	}

	// // Generating a player with its default color and size, at the given position, non used method;
	// Player(int x, int y) {
	// 	super(x, y,  Color.GREEN, Game_stats.unit);
	// }
}
