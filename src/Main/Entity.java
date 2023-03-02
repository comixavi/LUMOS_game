package Main;

import java.awt.Color;
import java.awt.Graphics;

//every Entity (player, enemy, point) has the same base;
public class Entity {
	Color color;  

	int entity_size;

	int x_tile;
	int y_tile;

	// Default constructor with no parameters;
	// Makes a blank entity with size and position 0;
	Entity()
	{
		this.entity_size = 0;

		this.x_tile = 0; 
		this.y_tile = 0;

		this.color = Color.white;
	}

	// Constructor with parameters;
	// Makes an entity with given color, size and position;
	Entity(int x, int y, Color color, int size) 
	{
		this.entity_size = Game_stats.unit;

		this.x_tile = x; 
		this.y_tile = y;

		this.color = color;

		this.entity_size = size;
	}

	// Function to render an entity at its position;
	// Needs to be given a Graphics object to render on;
	public void renderEntity(Graphics g)
	{
		// Every type on entity that extends this class 
		// has its own color;
		g.setColor(this.color);
		
		// All entity objects are squares and drawn like one;
		// Position is relative to the offsets of the play table;
		g.fillRect(this.x_tile*Game_stats.unit + (this.x_tile+1)*Game_stats.offset,
				Game_stats.game_score_heigth + this.y_tile*Game_stats.unit + (this.y_tile+1)*Game_stats.offset, 
				this.entity_size, this.entity_size);
	}
}
