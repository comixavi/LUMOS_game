package Main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Game implements Runnable{
	// Game window and panel;
	private GamePanel game_panel;
	private GameWindow game_window;
	
	// The logic is managed by a different thread;
	private Thread game_thread;

	static boolean started = false;

	// The target for the frames and updates per second;
	// A frame is the rending;
	// An update is the logic behind it that can change multiple times
	// between frames without affecting the current frame;
	private final int FPS_SET = 144; // frames per second
	private final int UPS_SET = FPS_SET*2; // updates per second

	// The game itself needs a panel to render and a window to place the panel;
	public Game() {
		// First we create the panel to get the dimensions and base rendering;
		game_panel = new GamePanel();
		// Place the panel to a window;
		game_window = new GameWindow(game_panel);
		
		// Initialization of the Panel;
		startPanel();
	}

	private void startPanel()
	{	
		// The start Button for the game;
		JButton start_button = new JButton("LUMOS SPRINT  Start Game!");

		// Placement of the button;
		start_button.setBounds(Game_stats.game_width/2,
				Game_stats.game_width/2, 
				Game_stats.unit*1,
				Game_stats.unit*2);
		
		// Give it color;
		start_button.setBackground(Color.DARK_GRAY);
		start_button.setForeground(Color.gray.brighter().brighter());
		
		// Adds the button to the panel;
		game_panel.add(start_button);
		
		// When pressed it destroy itself and makes the game panel to
		// get input from the keyboard, then starts the game;
		start_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				game_panel.remove(start_button);
				
				game_panel.requestFocus();
				
				startGame();
			}
		});
		

	}

	// Makes a new thread with this class
	// and start it;
	private void startGame()
	{
		game_thread = new Thread(this);		
		game_thread.start();
	}

	// The update (in this case reset) logic of the game;
	public void updateGame()
	{ 
		// Checking if the game just started;
		// If the game didn't started this means
		// that a level was passed and  we need to
		// make a new one;
		if(Game_stats.current_score != 0)
		{
			// Add the new bonus score for finishig early; 
			Game_stats.current_score += 10*Game_stats.current_time;
	
			// Adding on more point for difficulty;
			Game_stats.current_points_number++;
	
			// Adding five more point for difficulty;
			Game_stats.current_enemies_number+=5;
	
			// Setting the time to default_time_value - number_of_level_completed
			Game_stats.current_time = Game_stats.default_time - (Game_stats.current_points_number - Game_stats.default_points_number - 1);
	
			// CAPPING THE VALUES SO THE GAME DOESN'T BECOME IMPOSSIBLE - STARTS HERE //
			Game_stats.current_points_number = Math.max(Game_stats.current_points_number,
					2/10 * (Game_stats.game_width*Game_stats.game_height));
	
			Game_stats.current_enemies_number = Math.max(Game_stats.current_enemies_number,
					1/2 * (Game_stats.game_width*Game_stats.game_height));
	
			Game_stats.current_time = Math.max(Game_stats.current_time, Game_stats.default_time/2);
			
			// CAPPING THE VALUES SO THE GAME DOESN'T BECOME IMPOSSIBLE - ENDS HERE //

		}
		
		// Cleaning the matrix and lists;
		for(int i = 0; i < Game_stats.number_per_width; i++)
		{
			for(int j = 0; j < Game_stats.number_per_height; j++)
			{
				Game_stats.entities_matrix[i][j] = 0;
			}
		}

		Game_stats.enemies_list.clear();
		Game_stats.points_list.clear();

		// Placing the player at its default position;
		Game_stats.entities_matrix[game_panel.player.x_tile][game_panel.player.y_tile] = 'P';

		// Generating points;
		game_window.genereteArrayOfEntities(Game_stats.current_points_number, 'p');

		// Generating enemies;
		game_window.genereteArrayOfEntities(Game_stats.current_enemies_number, 'e');		
	} 	

	@Override
	// Run function that is called constant;
	public void run() {
		// Knowing the wanted FPS and UPS we divide one
		// second (nanoseconds) to the target to get the time;
		double timePerFrame = 1000000000.0/FPS_SET; // Time per render of frame;
		double timePerUpdate = 1000000000.0/UPS_SET; // Time per update of logic;

		// Get the time of the System;
		long previousTime = System.nanoTime();

		// Starts to count by 0;
		int frames = 0;
		int updates = 0;

		long lastCheck = 0;

		// The delta is a percent of how many frames and updates where done
		// comparing to the target (time per update/frame); 
		double deltaUpdate = 0;
		double deltaRender = 0;

		// The main game loop;
		while(true)
		{	
			// Gets the current time;
			long currentTime = System.nanoTime();

			// Updates the delta values;
			deltaUpdate += (currentTime - previousTime)/ timePerUpdate;
			deltaRender += (currentTime - previousTime)/ timePerFrame;

			// Updates the previous time with the current one;
			previousTime = currentTime;

			// If deltaUpdate is over one then a new update needs to be done;
			if(deltaUpdate >= 1)
			{
				// If the point list is empty this means that 
				// all points are collected or game just started;
				if(Game_stats.points_list.isEmpty()) 
				{	
					updateGame();
				}	

				// If time is over;
				if(Game_stats.current_time <= 0)
				{
					// Player is dead so update the state;
					game_panel.player.setState(game_panel.player.DYING);
					Game_stats.current_time = Game_stats.default_time;
					// Reset the board;
					updateGame();
				}	
				
				// One more update was Done;
				updates++;
				
				// The delta value needs to be between 0 and 1
				// so we decrease the value whit 1 (it can't
				// be more than 2);
				deltaUpdate--;
			}

			// If deltaRender is over one then a new render needs to be done;
			if(deltaRender >= 1)
			{	 
				// Do the render;
				game_panel.repaint();
				
				// The delta value needs to be between 0 and 1
				// so we decrease the value whit 1 (it can't
				// be more than 2);
				deltaRender--;
				
				//One more frame was rendered;
				frames++;
			}

			// If a second passed calculate the FPS and UPS;
			if(System.currentTimeMillis() - lastCheck >= 1000)
			{
				lastCheck = System.currentTimeMillis();

				System.out.println("FPS: " + frames + "|| UPS: "  + updates);
				Game_stats.current_time--;

				// Reset the values;
				frames = 0;
				updates = 0;
			}
		}
	}

}

