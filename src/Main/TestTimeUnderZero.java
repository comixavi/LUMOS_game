package Main;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTimeUnderZero {
	
	@Test
	public void testTimeUnderZero() throws InterruptedException {
		Main.main(null);
		
		int seconds = 60*60;
		
		Thread.sleep(1000 * seconds); // play for some time
		
		if(Game_stats.current_time < 0)
			fail("Time can't be under 0! Error!");
	}

}
