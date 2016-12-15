import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SignUpTest {

	static SignUp newSign;
	@Before
	public void setUp() throws Exception {
		PlayerDatabase.initializedb();
		newSign = new SignUp();
	}

	@Test
	public void testThoughManipulationOfNewUser() {
		newSign.newuserField.setText("StereoBeat");
		newSign.newpasswordField.setText("radiobeats");
		boolean added = PlayerDatabase.newUserForm(newSign.newuserField.getText().toLowerCase(),newSign.newpasswordField.getText());
	
		System.out.println("Was it added?: " + added);
		System.out.println(PlayerDatabase.players);
	//	fail("Not yet implemented");
	}	
	
	@Test
	public void testThoughManipulationOfTakenUser() {
		newSign.newuserField.setText("sai");
		newSign.newpasswordField.setText("jake");
		boolean added = PlayerDatabase.newUserForm(newSign.newuserField.getText().toLowerCase(),newSign.newpasswordField.getText());
	
		System.out.println("Was it added?: " + added);
		System.out.println(PlayerDatabase.players);
	//	fail("Not yet implemented");
	}

}
