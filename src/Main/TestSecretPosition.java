package Main;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSecretPosition {
	
	/**
	 * Scenario: there is a secret position the player shouldn't 
	 * be able to go, but it does;
	 */
	@Test
	public void test() {
		int forbidden_x = Game_stats.randomInt(1, Game_stats.number_per_width-1);
		int forbidden_y = Game_stats.randomInt(1, Game_stats.number_per_height-1);
		
		Main.main(null);
		
		while(true)
		{
			System.out.println(forbidden_x + " " + forbidden_y);
			if(Game_stats.panel_game.player.x_tile == forbidden_x && 
			   Game_stats.panel_game.player.y_tile == forbidden_y)
				fail("You shall not pass!!!");
		}
	}

}
