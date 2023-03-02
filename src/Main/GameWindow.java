package Main;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame main_window;
	
	// Creating the Game Window;;
	public GameWindow(GamePanel game_panel)
	{
		// Creating of the Game Window;
		main_window = new JFrame();
		
		// Setting it to close;
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Adding the game_panel;
		main_window.add(game_panel);
		
		// The size of the window is fixed;
		main_window.setResizable(false); 
		
		// Pack with the game_panel so the sizes are right;
		main_window.pack(); 
		
		// Make the window visible;
		main_window.setVisible(true);
	}
	
	// Types of entityes in a list can be:
	//               'p' for point;
	//               'e' for enemy;
	public void genereteArrayOfEntities(int length,char type)
	{
		// Generation of every one;
		for(int i = 0; i < length; i++)
		{
			// Get random positions;
			int x_aux_pozition = Game_stats.randomInt(0, Game_stats.number_per_width-1);
			int y_aux_pozition = Game_stats.randomInt(0, Game_stats.number_per_height-1);

			// Check if the position is free and if is not generate another until it is;
			while(Game_stats.entities_matrix[x_aux_pozition][y_aux_pozition] != 0) 
			{
				x_aux_pozition = Game_stats.randomInt(0, Game_stats.number_per_width-1);
				y_aux_pozition = Game_stats.randomInt(0, Game_stats.number_per_height-1);
			}

			// Depending on the type of the entity update there list;
			switch(type){
			// Point case;
			case 'p':
				Game_stats.entities_matrix[x_aux_pozition][y_aux_pozition] = 'p';
				
				Game_stats.points_list.add(new Point(x_aux_pozition,y_aux_pozition));
				
				break;
			// Enemy case;
			case 'e':
				Game_stats.entities_matrix[x_aux_pozition][y_aux_pozition] = 'e';

				Game_stats.enemies_list.add(new Enemy(x_aux_pozition,y_aux_pozition));

				break;				
			}
		}
	}
}
