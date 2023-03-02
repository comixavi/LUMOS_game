package Main;

import java.awt.Color;

// The point is an Entity with default cyan color and default size;
public class Point extends Entity{

	// Generating a point with the given position;
	Point(int x, int y) 
	{
		super(x,y, Color.cyan, Game_stats.unit);
		//System.out.println(x+" "+y);
		
	}
}