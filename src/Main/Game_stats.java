package Main;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

// This public class has all important constants of the game;
// This is an OBSERVER of the Player because it changes by
// the state of it;
public class Game_stats extends Observer{
	// The general game panel where the image is render; 
	public static GamePanel panel_game;
	
	// CONSTANTS FOR SIZES OF THE PLAY SCREEN - STARTS HERE //
	
	public static int unit = 30; 
	public static int offset = 10; 

	public static int game_score_heigth = (unit*3)/2;

	public static int number_per_width = 35; 
	public static int number_per_height = 20;
	
	public static int default_x_position = number_per_width/2;
	public static int default_y_position = number_per_height/2;

	public static int game_width =  number_per_width*unit+(number_per_width+1)*offset;//+unit/2+offset;
	public static int game_height = game_score_heigth + number_per_height*unit+(number_per_height+1)*offset;//+unit*3/2; 
 
	// CONSTANTS FOR SIZES OF THE PLAY SCREEN - ENDS HERE //
	
	// VARIABLE THAT CHANGES WITH THE GAME AND THEIR DEFAULT VALUES - STARTS HERE //
	public static int default_score = 0; 
	public static int current_score = 0;
	
	public static int default_time = 30;
	public static int current_time = 30;

	public static int default_enemies_number = 50;
	public static int current_enemies_number = 50;

	public static int default_points_number = 5;
	public static int current_points_number = 5;
	public static int point_value = 10;
	// VARIABLE THAT CHANGES WITH THE GAME AND THEIR DEFAULT VALUES - ENDS HERE //

	// Lists of enemy and points that updates with the game;
	public static List<Enemy> enemies_list = new ArrayList<Enemy>();  
	public static List<Point> points_list = new ArrayList<Point>();  

	// Matrix with elements corresponding to the entities on table;
	// 'p' for point;
	// 'e' for enemy;
	// 'P' for player;
	public static char[][] entities_matrix = new char[number_per_width][number_per_height];
	
	// Variable that defines how many tiles is the player able to jump;
	public static int jumping_tiles = 1;

	// Function that generate a integer number between min and max;
	static public int randomInt(int min, int max)
	{
		return (int)(Math.random() * (max - min + 1) + min);
	}

	@Override
	// The update logic, of the stats, by the Player state;
	public void update() {
		// Possible Player states: 
		// IDLE
		// COLLECTING_POINT
		// DYING;
		
		// Player is becoming IDLE;
		if(this.player.getState() == this.player.IDLE)
		{
			// Nothing because it's OK to move;
		}
		
		// If the player collected a point;
		else if(this.player.getState() == this.player.COLLECTING_POINT)
		{
			for(Point point : Game_stats.points_list)
			{
				// Search for the point that was collected;
				if(point.x_tile == this.player.x_tile && point.y_tile == this.player.y_tile)
				{
					// Remove it;
					points_list.remove(point);

					// Add the points and time for collecting a Point Entity;
					Game_stats.current_score += Game_stats.point_value;
					//Game_stats.current_time += 1;

					break;
				}
			}
			// Set the Player to IDLE;
			this.player.setState(this.player.IDLE);
		}
		// If the player is DYING;
		else if(this.player.getState() == this.player.DYING)
		{
			// Change values to their default ones;
			current_enemies_number = default_enemies_number;
			current_points_number = default_points_number;
			
			current_score = default_score;
			current_time = 0; //Default time;
			
			this.player.x_tile = default_x_position;
			this.player.y_tile = default_y_position;
			
			this.player.setState(this.player.IDLE);
		}
		// If the user pressed SPACE the next move will make them to jump two tiles;
		else if(this.player.getState() == this.player.JUMPING)
		{
			jumping_tiles = 2;
			// Set the color to a darker one to show the effect;
			this.player.color = Color.GREEN.darker();
		}
		// After they JUMPED the reset the Jumping Tiles value and the color;
		else if(this.player.getState() == this.player.JUMPED)
		{
			jumping_tiles = 1;
			this.player.setState(this.player.IDLE);
			this.player.color = Color.GREEN;
		} 
	}
}
