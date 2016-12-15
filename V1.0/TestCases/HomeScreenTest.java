import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HomeScreenTest {

	static HomeScreen home;

	@Before
	public void setUp() throws Exception {
		home = new HomeScreen();
	}

	@Test
	public void testIfLoadable() {
		home.main(null);
	}	
	
	@Test
	public void testIfGameInvokable() {
		home.GameStartEvent();
	}
	
	@Test
	public void testIfChatInvokable() {
		home.ChatEvent();
	}
	
	@Test
	public void testIfLeaderBoardInvokable() {
		home.LeaderBoardEvent();
	}
	
	@Test
	public void testIfHelpInvokable() {
		home.HelpEvent();
	}

}
