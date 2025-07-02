import java.util.Scanner;

public class InputValidator 
{
    public static int getIntInput(String message) 
    {   // getting user input
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        String input = scanner.next();
        // try catch (checked exception)
        try 
        {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) 
        {
            System.out.println("Wrong number. retry.");
            return getIntInput(message);
        }
    }
}