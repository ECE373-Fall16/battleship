import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InitialScreenTest {
	
	static InitialScreen newScreen;
	
	@Before
	public void run(){
		newScreen = new InitialScreen();
	}
	
	@Test
	public void test() {
		newScreen.main(null);
	}	
	
	@Test
	public void testLogIn() {
		newScreen.loginEvent();
	}	
	
	@Test
	public void testSignUp() {
		newScreen.signupEvent();;
	}
	


}
