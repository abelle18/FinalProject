import java.util.ArrayList;
import java.util.Scanner;

public class BookTracker 
{   // book arrays
    static ArrayList<String> toRead = new ArrayList<>();
    static String[] booksRead = new String[100];
    static int Count = 0;

    public static void main(String[] args) 
    {   // reads file
        toRead = FileHandler.loadData("reading.txt");
        ArrayList<String> fileScan = FileHandler.loadData("read.txt");
        // for each loop, count num. of books
        for (String book : fileScan) {
            if (Count < booksRead.length) 
            {
                booksRead[Count++] = book;
            }
        }
        // input from user (scanner)
        Scanner scanner = new Scanner(System.in);
        int choice;
        do 
        {
            // methods calling
            showMenu();
            choice = InputValidator.getIntInput("Choose an option (1-5): ");
            // switch statement for user selection (specific)
            switch (choice) 
            {
                case 1 -> add();
                case 2 -> markedAsRead();
                case 3 -> displayLists();
                case 4 -> remove();
                case 5 -> exit();
                default -> System.out.println("Error.");
            }
        } while (choice != 5);
    }
    // displays menu options
    static void showMenu() 
    {
        System.out.println("\nBook Tracker Menu List:");
        System.out.println("1. Add book to 'To Read' list");
        System.out.println("2. Mark book as Read");
        System.out.println("3. Display book lists");
        System.out.println("4. Remove book from 'To Read'");
        System.out.println("5. Exit");
    }
    // adds book to read
    static void add() 
    {
        System.out.print("Enter a book title: ");
        Scanner scanner = new Scanner(System.in);
        String book = scanner.nextLine();
        // checks if book exsists (title)
        if (!book.isBlank()) 
        {
            toRead.add(book);
            System.out.println("Book now in 'To Read' list.");
        } else 
        {
            System.out.println(" Invalid, Title cannot be empty.");
        }
    }
    // books marked as read 
    static void markedAsRead() 
    {
        if (toRead.isEmpty()) 
        {
            System.out.println("Your reading list is empty.");
            return;
        }
        for (int i = 0; i < toRead.size(); i++) 
        {
            System.out.println(i + ". " + toRead.get(i));
        }
        int index = InputValidator.getIntInput("Which book? (Enter index): ");
        // checked exception (try catch) marks books as read, otherwise returns error
        try 
        {
            String book = toRead.remove(index);
            if (Count < booksRead.length) 
            {
                booksRead[Count++] = book;
                System.out.println("Your Book has been marked as read.");
            } else 
            {
                System.out.println("Your Reading list is full.");
            }
        } catch (IndexOutOfBoundsException e) 
        {
            System.out.println("Wrong index entered.");
        }
    }
    // Displays books saved in list
    static void displayLists() 
    {
        // checks if books are in "to read" list
        System.out.println("\n Books you want to read:");
        if (toRead.isEmpty()) 
        {
            System.out.println("(none)");
        } else {
            for (int i = 0; i < toRead.size(); i++) 
            {
                System.out.println(i + ". " + toRead.get(i));
            }
        }
        // checks if books are in list
        System.out.println("\n Your read books:");
        if (Count == 0) 
        {
            System.out.println("(none)");
        } else 
        {
            for (int i = 0; i < Count; i++) 
            {
                System.out.println((i + 1) + ". " + booksRead[i]);
            }
        }
    }
    // removing books from lists
    static void remove() 
    {
        if (toRead.isEmpty()) 
        {
            System.out.println("Your Reading list is empty.");
            return;
        }
        for (int i = 0; i < toRead.size(); i++) 
        {
            System.out.println(i + ". " + toRead.get(i));
        }
        int index = InputValidator.getIntInput("Enter the book index to remove: ");
        // checked exception (try-catch)
        try 
        {
            toRead.remove(index);
            System.out.println("The Book has been removed.");
        } catch (IndexOutOfBoundsException e) 
        {
            System.out.println("Wrong index entered.");
        }
    }
    // exiting program
    static void exit() 
    {   // saves data to txt files
        FileHandler.saveData(toRead, "reading.txt");
        ArrayList<String> readData = new ArrayList<>();
        for (int i = 0; i < Count; i++) 
        {
            readData.add(booksRead[i]);
        }
        FileHandler.saveData(readData, "read.txt");
        System.out.println("Reading List saved. Goodbye!");
    }
}
