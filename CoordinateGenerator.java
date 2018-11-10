import java.util.Random;
/**
 * The class randomly generates number within a given range.
 * 
 * @author Tisa Majumder
 * @version 21st October 2018
 */
public class CoordinateGenerator
{
    int minimumValue;
    int maximumValue;
    /**
     * Constructor for objects of class Random Number Generator
     */
    public CoordinateGenerator()
    {
        minimumValue = 0;
        maximumValue = 100;
    }

    /**
     * Non- Default Constructor for objects of class Random Number Generator
     * @param  min  the lowest range entered by user
     * @param max the highest range entered by user
     */
    public CoordinateGenerator(int min, int max)
    {
        minimumValue = min;
        maximumValue = max;
    }
    
    public void displayValues()
    {
        System.out.println("Minimum value: " + minimumValue);
        System.out.println("Maximum value: " + maximumValue);
    }
    
    public int getMinimumValue()
    {
        return minimumValue;
    }
    
    public int getMaximumValue()
    {
        return maximumValue;
    }
    
    /**
     * A random number generator method
     * @return  a number generated randomly between the minimum and maximum value
     */
    public int randomNumber()
    {
        Random randomGen = new Random();
        int randomNum = (int)(minimumValue + Math.random()* maximumValue);
        return randomNum;
    }
       
    public void setMinimumValue(int min)
    {
        minimumValue = min;
    }
    
    public void setMaximumValue(int max)
    {
        maximumValue = max;
    }
}
