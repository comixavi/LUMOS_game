package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardsInputs implements KeyListener{
	
	private GamePanel game_panel;
	
	public KeyboardsInputs(GamePanel gamePanel)
	{
		this.game_panel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		 
	}

	@Override
	// Call the correct function depending on the input of a press key;
	// For game constancy 'w' and up arrow key generate the same output;
	public void keyPressed(KeyEvent e) {
		
		//Game_stats.entities_matrix[game_panel.player.x_tile][game_panel.player.y_tile] = 0;
		
		switch(e.getKeyCode()) 
		{
		
		case KeyEvent.VK_W:
			this.game_panel.move_up();
			break;
		case KeyEvent.VK_UP:
			this.game_panel.move_up();
			break;
			
		case KeyEvent.VK_A:
			this.game_panel.move_left();
			break;
		case KeyEvent.VK_LEFT:
			this.game_panel.move_left();
			break;
			
		case KeyEvent.VK_S:
			this.game_panel.move_down();
			break;
		case KeyEvent.VK_DOWN:
			this.game_panel.move_down();
			break;
			
		case KeyEvent.VK_D:
			this.game_panel.move_right();
			break;
		case KeyEvent.VK_RIGHT:
			this.game_panel.move_right();
			break;
			
		case KeyEvent.VK_SPACE:
			this.game_panel.move_space();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
