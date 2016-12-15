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
	public void test() {
		testGame.main(null);
		//assert(testGame.set = true);
		while(!testGame.set){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@After
	public void shipLocations(){
		//System.out.println("Successfully Compiled");
		System.out.println(testGame.getFinalCoordinates());
		}

	}
