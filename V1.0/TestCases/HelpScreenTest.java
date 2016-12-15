import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author HadiGhantous
 *
 */
public class HelpScreenTest {
	static HelpScreen testingBlock;

	/**
	 * @throws InterruptedException 
	 * @throws java.lang.Exception
	 */

	@Test
	public void testifitwillload() throws InterruptedException {
		testingBlock = new HelpScreen();
		testingBlock.main(null);
		Thread.sleep(8000);
	}
	
}
