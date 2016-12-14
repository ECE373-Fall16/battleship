public class SwitchOptions {

public static String sendcommand(String input){

	char command = input.charAt(0);

	String executedCommand = "";
      switch(command) {
         case '0' :
        	// String username = input.split("\\.")[0];

        	 break;
         case '1' :
         case '2' :
            System.out.println("Well done");
            break;
         case '3' :
            System.out.println("You passed");
         case '4' :
            System.out.println("Better try again");
            break;
            
         case '5' :
             System.out.println("Better try again");
             break;
         default :
            System.out.println("Invalid Command");
      }
      return executedCommand;
	}
}