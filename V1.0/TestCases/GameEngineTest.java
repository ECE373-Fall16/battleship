import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.*;

/**
 * 
 */

/**
 * @author HadiGhantous
 *
 */
public class GameEngineTest {

	/**
	 * @throws java.lang.Exception
	 */

	GameEngine testGame = new GameEngine();

	@Test
	public void testifrunnable() {
		testGame.main(null);
		while (!testGame.set) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(testGame.getFinalCoordinates());
		}
	}

	@Test
	public void testifTestSetAlready() {
		assert (testGame.set);
		System.out.println("Successfully Compiled");
	}
}