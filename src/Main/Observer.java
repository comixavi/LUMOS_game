package Main;

public abstract class Observer {
	// In our case the Observer needs to observe 
	// a player;
	protected Player player;
	
	// Every type of Observer needs to have a 
	// a update() function;
	public abstract void update();
}
