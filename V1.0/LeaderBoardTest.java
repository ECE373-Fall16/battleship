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
public class LeaderBoardTest {
	static LeaderBoard leader;
	public static void main(String[] args) throws Exception{
		 leader = new LeaderBoard();
		leader.main(null);
	}
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(leader.returnLeaderBoard());
	}

}
