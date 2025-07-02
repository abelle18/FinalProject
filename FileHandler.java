import java.io.*;
import java.util.ArrayList;

public class FileHandler 
{   // saving data from current run
    public static void saveData(ArrayList<String> data, String filename) 
    {   // checked exception 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) 
        {
            for (String item : data) 
            {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) 
        {   // runs when books cannot be saved
            System.out.println("Error with saving new data: " + e.getMessage());
        }
    }
    // loading data from previous run
    public static ArrayList<String> loadData(String filename) 
    {
        ArrayList<String> data = new ArrayList<>();
        // checked exception
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                data.add(line);
            }
        } catch (IOException e) 
        {
            // runs when txt files are not created
            System.out.println("Files not created yet (first run): " + e.getMessage());
        }
        return data;
    }
}