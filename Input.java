import java.util.*;
/**
 * This class is used to accept user inputs
 *
 * @author Tisa Majumder
 * @version 21st October 2018
 */
public class Input
{
    /**
     * Constructor for objects of class Input
     */
    public Input()
    {
        
    }

    /**
     * It is used to check if there is a next line
     * @return true if there is a new line
     */
     public boolean randomKey()
    {
        System.out.println("Press any key to continue....");
        Scanner console = new Scanner(System.in);
        boolean key = console.hasNextLine();
        return key;
    }

    /**
     * It is used to read the data from the console that is entered by the user
     * @return string that is entered by the user
     */
     public String getInput(String message)
    {
        System.out.println(message);
        Scanner console = new Scanner(System.in);
        String value = console.nextLine();
        return value;
    }
}
