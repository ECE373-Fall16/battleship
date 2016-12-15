import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChatClientTest {

	static MainChatGUI setup;
	static ChatClient newChat;

	@Before
	public void setUp() throws Exception {
		setup = new MainChatGUI();
		newChat = new ChatClient();
		setup.main(null);

	}

	@Test // Checks to see if ChatClient can append messages to MainChatGUI when
			// it's connected to a server
	public void testForAppending() throws InterruptedException {
		ChatClient.displayMessage("Hi there, am I visible?");
		Thread.sleep(15000);

	}

}
