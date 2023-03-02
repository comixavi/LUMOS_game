package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// There is just a player per game;
	Player player = new Player();

	// Initialization of the game panel;
	public GamePanel()
	{	
		// The game panel needs to have access to the a
		// game_stats object to pass the player;
		Game_stats game_stats = new Game_stats();
		
		// Passing the player and local panel;
		game_stats.player = player;
		Game_stats.panel_game = this;
		
		// The player (subject) is attached to the game_stats (observer)
		// so it can update the game stats;
		player.attach(game_stats);
		// By the start of the game the player is IDLE;
		player.setState(player.IDLE);

		// Adding a key listener for the inputs;
		addKeyListener(new KeyboardsInputs(this));
		
		// Setting the panel size to the default one;
		setPanelSize();
	}	

	// The default one are static values from Game_stats (like all the other constants);
	private void setPanelSize()
	{
		setPreferredSize(new Dimension(Game_stats.game_width, Game_stats.game_height));
	}

	// If space is pressed;
	public void move_space()
	{
		if(Game_stats.jumping_tiles != 1) 
		{
			// If the player is in JUMPING state
			// and the user press space again it
			// reset the Player with default by
			// simulating a jump;
			this.player.setState(this.player.JUMPED);
		}
		else
		{
			// If the player is in IDLE make it 
			// in JUMPING state;
			this.player.setState(this.player.JUMPING);
		}
		
		// Code used for debug player position;
		//System.out.println(this.player.x_tile + " " + this.player.y_tile);
	}

	// Function for moving down;
	public void move_down()
	{
		// Set the current position in matrix to zero;
		Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] = 0; 

		// Check if the next position is not in a wall;
		if(this.player.y_tile + Game_stats.jumping_tiles <= Game_stats.number_per_height-1)
		{
			this.player.y_tile+=Game_stats.jumping_tiles;
			this.player.setState(this.player.JUMPED);
		}
		
		// Check if the next position is a enemy or a point;
		if(Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] == 'p')
			// The player collected a point;
			this.player.setState(this.player.COLLECTING_POINT);
		else if(Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] == 'e')
			// The player hit an enemy and died;
			this.player.setState(this.player.DYING);

		// If here everything is OK: set the position in matrix 
		// to 'P' meaning the player is there;
		Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] = 'P';

		/*
		
		Code for 
		
		for(int i = 0; i < Game_stats.number_per_width;i++)
		{
			for(int j = 0; j < Game_stats.number_per_height; j++)
			{
				System.out.print(Game_stats.entities_matrix[i][j]+ " ");
			}
			System.out.println(" ");
		}
		 */
	}

	// Function for moving up;
	public void move_up()
	{	
		// Same logic as move_down, only for up;
		
		// Code for debugging the position: 
		//System.out.println(this.player.x_tile + " " + this.player.y_tile);
		Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] = 0;

		if(this.player.y_tile - Game_stats.jumping_tiles >= 0)
		{
			this.player.y_tile-=Game_stats.jumping_tiles;
			this.player.setState(this.player.JUMPED);
		}

		if(Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] == 'p')
			this.player.setState(this.player.COLLECTING_POINT);
		else if(Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] == 'e')
			this.player.setState(this.player.DYING);

		Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] = 'P';

	}

	// Function for moving left;
	public void move_left()
	{	
		// Same logic as the case only for left;
		
		// Code for debugging the position:
		//System.out.println(this.player.x_tile + " " + this.player.y_tile);
		Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] = 0;

		if(this.player.x_tile - Game_stats.jumping_tiles >= 0)
		{	
			this.player.x_tile-=Game_stats.jumping_tiles;
			this.player.setState(this.player.JUMPED);
		}

		if(Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] == 'p')
			this.player.setState(this.player.COLLECTING_POINT);
		else if(Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] == 'e')
			this.player.setState(this.player.DYING);

		Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] = 'P';
	}

	// Function for moving right;
	public void move_right()
	{
		// Same logic as the case only for right;
		
		// Code for debugging the position:
		//System.out.println(this.player.x_tile + " " + this.player.y_tile);
		Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] = 0;

		if(this.player.x_tile + Game_stats.jumping_tiles <= Game_stats.number_per_width-1)
		{
			this.player.x_tile+=Game_stats.jumping_tiles;
			this.player.setState(this.player.JUMPED);
		}

		if(Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] == 'p')
			this.player.setState(this.player.COLLECTING_POINT);
		else if(Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] == 'e')
			this.player.setState(this.player.DYING);

		Game_stats.entities_matrix[this.player.x_tile][this.player.y_tile] = 'P';
	}

	// The render frame function, is called with every
	// repaint() call;
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		// The base of the table;
		g.fillRect(0,0,Game_stats.game_width, Game_stats.game_height);
		g.setColor(Color.gray);

		// Creating the position on the play table;
		for(int i = 0; i < Game_stats.number_per_width; i++)
			for(int j = 0; j < Game_stats.number_per_height; j++)
				g.fillRect(i*Game_stats.unit + (i+1)*Game_stats.offset,
						Game_stats.game_score_heigth + j*Game_stats.unit + (j+1)*Game_stats.offset,
						Game_stats.unit, Game_stats.unit);

		// Creating score and time text;
		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() * 2F);

		g.setFont(newFont);

		g.drawString("Time left: " + Game_stats.current_time, Game_stats.offset, Game_stats.game_score_heigth-Game_stats.offset);
		g.drawString("Score: " + Game_stats.current_score,
				Game_stats.offset+Game_stats.game_width - Game_stats.unit*10,
				Game_stats.game_score_heigth-Game_stats.offset);

		// Rendering the player;
		player.renderEntity(g);
		this.player.setState(this.player.IDLE);

		// Rendering enemies from enemy list;
		for(int i = 0; i < Game_stats.enemies_list.size(); i++)
		{	 
			Game_stats.enemies_list.get(i).renderEntity(g);
		} 

		// Rendering points from point list;
		for(int i = 0; i < Game_stats.points_list.size(); i++)
		{		
			Game_stats.points_list.get(i).renderEntity(g);
		}

		// Old method to do the game loop without checking for
		// time frame or update;
		// The problem with this is that it gives different 
		// speeds of movement depending on the performance;
		//repaint();

	}

}
