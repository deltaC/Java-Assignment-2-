/**
 * This class is used to perform multiple validations on the data obtained from the user
 *
 * @author Tisa Majumder
 * @version 21st October 2018
 */
public class Validation
{
    /**
     * Constructor for objects of class Validation
     */
    public Validation()
    {
    }
    
    /**
     * It is used to validate if a given string can be converted to integer
     * @param value The string entered
     * @return true if the string is valid integer and can be converted
     */
    public boolean integerCheck(String value)
    {
        int numericValue = 0;
        try 
        {
            numericValue = Integer.parseInt(value);
            return true;
        } 
        catch (NumberFormatException e)
        {
            System.out.println("Please enter a valid numeric input.");
            return false;
        }
    }

    /**
     * It is used to check if an integer is within the specified limit
     * @param value integer to be checked
     * @param min the minimum value of the integer
     * @param max the maximum value of the integer
     * @return true if the string is within the range
     */
    public boolean rangeCheck(int value, int min, int max)
    {
        if (value >= min && value <= max)
            return true;
        else
            System.out.println("Please enter a number within the range specified.");
            return false;
    }
    
    /**
     * It is used to check if the String entered by the user is within the specified limit
     * @param value The string entered
     * @param min the minimum length of the string
     * @param max the maximum length of the string
     * @return true if the string is within the range
     */
    public boolean validString(String value, int min, int max) 
    {
        int valueLength = value.trim().length();
        if ((valueLength >= min && valueLength <= max))
            return true;
        else
            return false;
    }
}
