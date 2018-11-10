import java.util.*;
/**
 * This class is responsible for storing the array list of both Player and Computer's Ships.
 *
 * @author Tisa Majumder
 * @version 21st October 2018
 */
public class ShipList
{
    ArrayList<Ship> ships;
    /**
     * Default constructor for objects of class Shiplist
     */
    public ShipList()
    {
        ships = new ArrayList<Ship>();
    }
    
    /**
     * Non-Default constructor for objects of class Shiplist
     */
    public ShipList(ArrayList<Ship> newShips)
    {
        ships = newShips;
    }
    
    public ArrayList<Ship> getShips()
    {
        return ships;
    }
    
    /**
     * It is used to check if the ship has been damaged i.e hit atleast once.
     * @param gridX the x coordinate of the ship
     * @param gridY the y coordinate of the ship
     * return true if the ship has been hit atlest once
     */
    public boolean damagedSymbol(int gridX, int gridY)
    {
        for (int i = 0; i < ships.size(); i++)
        {
            int x = ships.get(i).getxPos();
            int y = ships.get(i).getyPos();
            if ( x == gridX && y == gridY )
            {
                int hitsMade = ships.get(i).getNoOfHitsMade();
                if (hitsMade > 0)
                    return true;
            }
        }
        return false;
    }

    /**
     * It is used to check if the ship has been destroyed i.e hit as many times as needed.
     * @param gridX the x coordinate of the ship
     * @param gridY the y coordinate of the ship
     * return true if the ship has been completely destroyed
     */
    public boolean destroyedSymbol(int gridX, int gridY)
    {
        for (int i = 0; i < ships.size(); i++)
        {
            int x = ships.get(i).getxPos();
            int y = ships.get(i).getyPos();
            if ( x == gridX && y == gridY )
            {
                int hitsMade = ships.get(i).getNoOfHitsMade();
                int hitsNeeded = ships.get(i).getNoOfHitsNeeded();
                if (hitsMade == hitsNeeded)
                    return true;
            }
        }
        return false;
    }
        
    /**
     * It is used to match if the coordinates accepted as arguments matches any of the ships' coordinates
     * @param gridX the x coordinate of the ship
     * @param gridY the y coordinate of the ship
     * return true if the coordinate entered matches with a ship from the list
     */
    public boolean gridSymbolMatch(int gridX, int gridY)
    {
        for (int i = 0; i < ships.size(); i++)
        {
            int x = ships.get(i).getxPos();
            int y = ships.get(i).getyPos();
            if ( x == gridX && y == gridY )
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * It is used to check if the ships are within their hull strength limit
     * return true if the number of hits needed for all the ships matches the no of hits made
     */
    public boolean isHullStrengthOfAllShipsWithinLimit()
    {
        int count = 0;
        int size = ships.size();
        for (int i = 0; i < ships.size(); i++)
        {
            int j = ships.get(i).getNoOfHitsMade();
            int k = ships.get(i).getNoOfHitsNeeded();
            if ( j >= k )
                count ++;
        }
        if (count == size)
            return false;
        return true;
    }
    
    public void setShips(ArrayList<Ship> newShips)
    {
        ships = newShips;
    }

    /**
     * It is called to update the number of hits made for a ship
     * @param gridX the x coordinate of the ship
     * @param gridY the y coordinate of the ship
     */
    public void updateHits(int gridX, int gridY)
    {
       for (int i = 0; i < ships.size(); i++)
        {
            int x = ships.get(i).getxPos();
            int y = ships.get(i).getyPos();
            if ( x == gridX && y == gridY )
            {
                int j = ships.get(i).getNoOfHitsMade() + 1;
                ships.get(i).setNoOfHitsMade(j);
            }
        }
    }
    
    /**
     * It is called to add new ships to the Shiplist 
     * @param name the name of the Ship
     * @param x the x coordinate of the ship
     * @param y the y coordinate of the ship
     * @param hitsMade the number of hits made
     * @param hitsNeeded the number of hits needed
     */
    public void updateShip(String name, int x, int y, int hitsMade, int hitsNeeded)
    {
        Ship newShip = new Ship(name, x, y, hitsMade, hitsNeeded);
        ships.add(newShip);
    }
}