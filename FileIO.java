import java.util.*;
import java.io.*;
/**
 * The class is responsible for reading and writing data from the text file.
 *
 * @author Tisa Majumder
 * @version 21st October 2018
 */
public class FileIO
{
    private String fileName;
    /**
     * Constructor for objects of class FileIO
     */
    public FileIO()
    {
        fileName = "Default File Name";
    }

    /**
     * Non-Default Constructor for objects of class FileIO
     */
    public FileIO(String name)
    {
        fileName = name;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String newFileName)
    {
        fileName = newFileName;
    }

    /**
     * It is used to read data from a file
     */
    public String readFile()
    {
        String contents = "";
        try 
        {
            FileReader inputFile = new FileReader(fileName);
            Scanner parser = new Scanner(inputFile);
            while (parser.hasNextLine())
            {
                contents += parser.nextLine();
            }
            inputFile.close();
        } 
        catch (FileNotFoundException exception)
        {
            System.out.println(fileName + " not found.");
        }
        catch (IOException e)
        {
            System.out.println("There was some error in accessing the file.");
        }
        return contents;
    }

    
    /**
     * It is used to write data to a file
     */
    public void writeFile(String result)
    {
        try 
        {
            if (fileName.trim().length() > 0)
            {
                PrintWriter outputFile = new PrintWriter(fileName);
                outputFile.println(result);
                outputFile.close();
            }
            else
                System.out.println("Please check your filename.");
        } catch (IOException exception)
        {
            System.out.println("File doesn't exist.");
        }
    }
}

  