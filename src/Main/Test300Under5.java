package Main;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test300Under5 {

	/**
	 * 
	 * @throws InterruptedException
	 * Scope: do 300 points in under 5 seconds
	 */
	@Test
	public void testDo300Points() throws InterruptedException {
		Main.main(null);
		
		int seconds = 5;
		
		Thread.sleep(1000 * seconds); // this pauses the thread and let's the game play 
		
		if(Game_stats.current_score < 300)
			fail("Task failed, go and play more!");
	}
	


}
