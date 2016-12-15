import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LogInTest {
	
	static LogIn logger;
	@Before
	public void setUp() throws Exception {
		logger = new LogIn();
	}

	@Test
	public void testInvokeToSeeIfGUIGENERATES() {
		logger.main(null);
	}

}
