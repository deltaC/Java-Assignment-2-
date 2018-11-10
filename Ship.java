/**
 * This class is used to create ship objects.
 *
 * @author Tisa Majumder
 * @version 21st October 2018
 */
public class Ship
{
    private String shipName;
    private int xPos;
    private int yPos;
    private int noOfHitsMade;
    private int noOfHitsNeeded;
    /**
     * Default constructor for objects of class Ship
     */
    public Ship()
    {
        shipName = "Default ship name";
        xPos = 0;
        yPos = 0;
        noOfHitsMade = 0;
        noOfHitsNeeded = 0;
    }

    /**
     * Non-Default Constructor for objects of class Ship
     */
    public Ship(String newName, int x, int y, int hitsMade, int hitsNeeded)
    {
        shipName = newName;
        xPos = x;
        yPos = y;
        noOfHitsMade = hitsMade;
        noOfHitsNeeded = hitsNeeded;
    }

    public void displayShip()
    {
        System.out.println("");
        System.out.println(" Ship Name:" + shipName);
        System.out.println(" Ship X Pos:" + xPos);
        System.out.println(" Ship Y Pos:" + yPos);
        System.out.println(" Ship Hits Made:" + noOfHitsMade);
        System.out.println(" Ship Hits Needed:" + noOfHitsNeeded);
    }

    public String getShipName()
    {
        return shipName;
    }

    public int getxPos()
    {
        return xPos;
    }

    public int getyPos()
    {
        return yPos;
    }

    public int getNoOfHitsMade()
    {
        return noOfHitsMade;
    }

    public int getNoOfHitsNeeded()
    {
        return noOfHitsNeeded;
    }

    public void setShipName(String name)
    {
        shipName = name;
    }

    public void setxPos(int x)
    {
        xPos = x;
    }

    public void setyPos(int y)
    {
        yPos = y;
    }

    public void setNoOfHitsMade(int hitsMade)
    {
        noOfHitsMade = hitsMade;
    }

    public void setNoOfHitsNeeded(int hitsNeeded)
    {
        noOfHitsNeeded = hitsNeeded;
    }
}
