import java.util.*;
/**
 * The main class the user interacts with to play the game.
 *
 * @author Tisa Majumder
 * @version 21st October 2018
 */
public class Game
{
    private ShipList playerShips;
    private ShipList computerShips;
    private final String SETTINGS_FILE = "gamesettings.txt";
    private final String RESULT_FILE = "gameoutcome.txt";

    /**
     * Default Constructor of the Game Class.
     */
    public Game()
    {
        playerShips = new ShipList();
        computerShips = new ShipList();
    }

    /**
     * Non-Default Constructor of the Game Class
     */
    public Game(ShipList newPlayerShips, ShipList newComputerShips)
    {
        playerShips = newPlayerShips;
        computerShips = newComputerShips;
    }

    /*
     * It is responsible for randomly generating computer's guess of coordinates, then checking if it matches any of the ships in the game and updating the hits. 
     * It also displays a message if computer hits or misses the ship.
     */
    private boolean compGuess()
    {
        System.out.println("");
        System.out.println("Computer to make a guess.");
        String grid = readGameSettingsData(0);
        int gridSize = Integer.parseInt(grid);
        int xPos = 0;
        int yPos = 0;
        CoordinateGenerator randomCoords = new CoordinateGenerator(1, (gridSize - 1));
        xPos = randomCoords.randomNumber();
        yPos = randomCoords.randomNumber();
        if (playerShips.gridSymbolMatch(xPos, yPos) && !playerShips.destroyedSymbol(xPos, yPos))
        {        
            playerShips.updateHits(xPos, yPos);
            System.out.println("");
            System.out.println("Computer Hit!!");
            return true;
        } else
        {
            System.out.println("");
            System.out.println("Computer Miss!!");
        }
        return false;
    }

    /*
     * It generates the computer ships for the game as per the specifications in the settings file.
     */
    private void computerShipDetails()
    {
        String totalShips = readGameSettingsData(3);
        int totalCompShips = Integer.parseInt(totalShips);
        String grid = readGameSettingsData(0);
        int gridSize = Integer.parseInt(grid);
        String name = "";
        int xPos = 0;
        int yPos = 0;
        int hitsMade = 0;
        String hits = readGameSettingsData(1);
        Boolean multipleHits = Boolean.parseBoolean(hits);
        int hitsNeeded = 0;
        System.out.println("Loading computer ship settings....");
        System.out.println("");
        for (int i = 0; i < totalCompShips; i++)
        {
            int j = i+1;
            name = "Ship " + j;
            CoordinateGenerator randomCoords = new CoordinateGenerator(1,gridSize - 1);
            do
            {
                xPos = randomCoords.randomNumber();
                yPos = randomCoords.randomNumber();
            } while (computerShips.gridSymbolMatch(xPos, yPos));
            if (multipleHits)
            {
                CoordinateGenerator randomStrength = new CoordinateGenerator(1,5);
                hitsNeeded = randomStrength.randomNumber();
            }
            else 
                hitsNeeded = 1;
            computerShips.updateShip(name, xPos, yPos, hitsMade, hitsNeeded);
        }
        System.out.println("Computer ship settings loaded....");
        System.out.println("");
    }

    /*
     * It displays the initial menu that is shown when the game starts after taking the data from the settings file.
     */
    private void display()
    {
        String grid = readGameSettingsData(0);
        String ships = readGameSettingsData(3);
        String hits = readGameSettingsData(1);
        String visibility = readGameSettingsData(2);
        Boolean visible = Boolean.parseBoolean(visibility);
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("");
        System.out.println("");
        System.out.println("Welcome to the Battleship Game -- With a Twist!!");
        System.out.println("");
        System.out.println("");
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("");
        System.out.println("The game will use the grid defined in the settings file");
        System.out.println("Playing grid size set as (" + grid + " X " + grid + ")");
        System.out.println("Maximum number of ships allowed as " + ships);
        System.out.println("Multiple hits allowed per ships set as " + hits);
        String value = visible ? "ON" : "OFF";
        System.out.println("Computer Ships Visible: " + value);
    }

    private ShipList getPlayerShips()
    {
        return playerShips;
    }

    private ShipList getComputerShips()
    {
        return computerShips;
    }

    /*
     * It calls the method of FileIO Class that reads the data entered into the file specified in the field. 
     * It obtains data as per the below order.
     * Grid Width and Height 
     * Multiple Hits Allowed  
     * Computer Ships Visible
     * No of Ships
     */
    private String readGameSettingsData(int i)
    {
        FileIO fileInput = new FileIO(SETTINGS_FILE);
        String[] settings = fileInput.readFile().trim().split(",");
        return settings[i];
    }

    /*
     * It is called to update the score.
     */
    private int scoreCheck()
    {
        int score = 0;
        score +=10;
        return score;
    }

    private void setPlayerShips(ShipList newPships)
    {
        playerShips = newPships;
    }

    private void setComputerShips(ShipList newCships)
    {
        playerShips = newCships;
    }

    /**
     * The main method that the user interacts with to start the game, it is used for displaying the grid and keeping track of the final score.
     * It calls other methods as and when required in the game. 
     */
    public void startGame()
    {
        userShipDetails();
        computerShipDetails();
        boolean contd = false;
        String grid = readGameSettingsData(0);
        int gridSize = Integer.parseInt(grid);
        String visible = readGameSettingsData(2);
        Boolean isVisible = Boolean.parseBoolean(visible);
        int gameRound = 1;
        int userScore = 0;
        int compScore = 0;
        do
        {
            if (playerShips.isHullStrengthOfAllShipsWithinLimit() && computerShips.isHullStrengthOfAllShipsWithinLimit())
            {
                System.out.println("");
                System.out.println("Beginning Round: " + gameRound);
                System.out.println("Player Score: " + userScore);
                System.out.println("Computer Score: " + compScore);
                System.out.println("Displaying the Player Grid:");
                System.out.println("");
                for (int i = 0; i < gridSize; i++)
                {
                    int gridX = i;
                    for (int j = 0; j < gridSize; j++) 
                    {
                        int gridY = j;
                        if(playerShips.gridSymbolMatch(gridX, gridY))
                        {
                            if (playerShips.destroyedSymbol(gridX, gridY))
                                System.out.print("X");
                            else if (playerShips.damagedSymbol(gridX, gridY))
                                System.out.print("D");
                            else
                                System.out.print("O");
                        }
                        else
                            System.out.print("~");
                    }
                    System.out.println("");
                }
                System.out.println("");
                System.out.println("Displaying the Computer Grid:");
                System.out.println("");

                for (int i = 0; i < gridSize; i++)
                {
                    int gridX = i;
                    for (int j = 0; j < gridSize; j++) 
                    {
                        int gridY = j;
                        if(computerShips.gridSymbolMatch(gridX, gridY))
                        {
                            if (computerShips.destroyedSymbol(gridX, gridY))
                                System.out.print("X");
                            else if (computerShips.damagedSymbol(gridX, gridY))
                                System.out.print("D");
                            else if (isVisible)
                                System.out.print("O");
                            else 
                                System.out.print("~");
                        }
                        else
                            System.out.print("~");
                    }
                    System.out.println("");
                }

                if (userGuess())
                    userScore += scoreCheck();

                if (compGuess())
                    compScore += scoreCheck();
                
                gameRound ++;
            } 
            else 
            {
                System.out.println("");
                System.out.println("Final Score");
                System.out.println("Total Rounds: " + gameRound);
                System.out.println("Player Score: " + userScore);
                System.out.println("Computer Score: " + compScore);

                if (userScore > compScore)
                {
                    System.out.println("");
                    System.out.println("Player Wins!!");
                    String score = ("Player wins. Total Rounds (" + gameRound + "). Final Player score (" + userScore + "). Final Computer Score (" + compScore + ").");
                    writeResult(score);
                }
                else 
                {
                    System.out.println("");
                    System.out.println("Computer Wins!!");
                    String score = ("Computer wins. Total Rounds (" + gameRound + "). Final Player score (" + compScore + "). Final Computer Score (" + userScore + ").");
                    writeResult(score);
                }
                contd = true;
            }
        } while (contd == false);
    }

    /*
     * It is responsible for accepting user's guess, checking if the user input matches any of the ships in the game and updating the hits. 
     * It also displays a message if player hits or misses the ship.
     */
    private boolean userGuess()
    {
        Input userInput = new Input();
        System.out.println("");
        System.out.println("Player to make a guess.");
        Validation validate = new Validation();
        int xPos = 0;
        int yPos = 0;
        int userScore = 0;
        boolean proceed = false;
        String grid = readGameSettingsData(0);
        int gridSize = Integer.parseInt(grid);
        do 
        {
            String xPosition = userInput.getInput("Enter ship x position (0-" + (gridSize - 1)+ ")"); 
            if(validate.integerCheck(xPosition))
            {
                xPos = Integer.parseInt(xPosition);
                if (validate.rangeCheck(xPos, 0, gridSize - 1))
                    proceed = true;
            }
            else
                proceed = false;
        } while(proceed == false);

        proceed = false;
        do 
        {
            String yPosition = userInput.getInput("Enter ship y position (0-" + (gridSize - 1) + ")"); 
            if(validate.integerCheck(yPosition))
            {
                yPos = Integer.parseInt(yPosition);
                if (validate.rangeCheck(yPos, 0, gridSize - 1))
                    proceed = true;
            }
            else
                proceed = false;
        } while(proceed == false);

        if (computerShips.gridSymbolMatch(xPos, yPos) && !computerShips.destroyedSymbol(xPos, yPos))
        {
            computerShips.updateHits(xPos, yPos);
            System.out.println("");
            System.out.println("Player Hit!!");
            return true;
        } else
        {
            System.out.println("");
            System.out.println("Player Miss!!");
        }
        return false;
    }

    /*
     * It generates the user ships for the game as per the specifications in the settings file.
     */
    private void userShipDetails()
    {
        display();
        String name = "";
        int xPos = 0;
        int yPos = 0;
        int hitsMade = 0;
        String hits = readGameSettingsData(1);
        Boolean multipleHits = Boolean.parseBoolean(hits);
        int hitsNeeded = 0;
        Input userInput = new Input();
        Validation validate = new Validation();
        String totalShips = readGameSettingsData(3);
        int totalUserShips = Integer.parseInt(totalShips);
        String grid = readGameSettingsData(0);
        int gridSize = Integer.parseInt(grid);
        if (userInput.randomKey())
        {
            System.out.println("Loading player settings:");
            for(int i = 0; i < totalUserShips ; i++)
            {
                int shipNumber = i+1;
                System.out.println("Please enter the details for Ship " + shipNumber +":");
                boolean proceed = false;
                do 
                {
                    name = userInput.getInput("Please enter ship name (3-15 characters)."); 
                    if(validate.validString(name, 3, 15))
                        proceed = true;
                    else
                    {
                        System.out.println("Please enter a name within the range specified.");
                        proceed = false; 
                    }
                } while(proceed == false);

                proceed = false;
                boolean shipCoordinateCheck = false;
                do 
                {
                    do
                    {
                        String xPosition = userInput.getInput("Ship x position (0-" + (gridSize - 1) + ")"); 
                        if (validate.integerCheck(xPosition))
                        {
                            xPos = Integer.parseInt(xPosition);
                            if (validate.rangeCheck(xPos, 0, gridSize - 1))
                                proceed = true;
                        }
                        else
                            proceed = false;
                    } while(proceed == false);

                    proceed = false;
                    do 
                    {
                        String yPosition = userInput.getInput("Ship y position (0-" + (gridSize - 1) + ")"); 
                        if(validate.integerCheck(yPosition))
                        {
                            yPos = Integer.parseInt(yPosition);
                            if (validate.rangeCheck(yPos, 0, gridSize - 1))
                                proceed = true;
                        }
                        else
                            proceed = false;
                    } while(proceed == false);

                    if(playerShips.gridSymbolMatch(xPos, yPos))
                    { 
                        System.out.println("Two ships can't be on the same coordinate");
                        shipCoordinateCheck = false;
                    }
                    else
                        shipCoordinateCheck = true;

                } while(shipCoordinateCheck == false);
                if (multipleHits)
                {
                    CoordinateGenerator randomStrength = new CoordinateGenerator(1,5);
                    hitsNeeded = randomStrength.randomNumber();
                }
                else 
                    hitsNeeded = 1;
                playerShips.updateShip(name, xPos, yPos, hitsMade, hitsNeeded);                  
            }
        }
    }

    /*
     * It calls the method of FileIO Class that writes the data entered into the file specified in the field.
     */
    private void writeResult(String score)
    {
        FileIO fileOutput = new FileIO(RESULT_FILE);
        fileOutput.writeFile(score);
    }
}   