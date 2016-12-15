import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerDatabaseTest {
	static PlayerDatabase newData; 
	@Before
	public void setUp() throws Exception {
		PlayerDatabase newData = new PlayerDatabase();
		newData.main(null);
	}	

	@Test
	public void testWithNewUserName() {
		boolean added = newData.newUserForm("dubois", "library");
		System.out.println("The New UserName Was Added: " + added);
		
		//fail("Not yet implemented");
	}
	
	@Test
	public void testWithTakenUserName() {
		boolean added = newData.newUserForm("sai", "duck");
		System.out.println("The Taken UserName Was Added: " + added);		
		//fail("Not yet implemented");
	}
	
	@Test
	public void testToPrintDatabase() {
		System.out.println(newData.players);
		
		
		//fail("Not yet implemented");
	}

}
