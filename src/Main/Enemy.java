package Main;

import java.awt.Color;

// Enemy is an Entity;
public class Enemy extends Entity{
	
	// Constructor without parameters;
	// Builds like a default entity;
	Enemy() 
	{
		super();
	}
	
	// Constructor with parameters;
	// Builds with given position and own enemy color;
	Enemy(int x, int y)
	{ 
		// Default color of enemy is pink;
		super(x,y, Color.pink, Game_stats.unit);  
	}
}
