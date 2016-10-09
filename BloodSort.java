// =========================================================================
// CS 1113 Program 5 : Blood Sort
// Semester : Fall 2016
//
// Andrew Bevelhymer
// 61049
//
// Description: Sorts list of donors based on selected info
// =========================================================================

import java.util.*;
import java.io.*;

public class BloodSort
{
  public static void main(String[] args) throws IOException
  {
    // Print info
    System.out.println("Blood donor sorting program by Andrew Bevelhymer");

    // Creating scanner
    Scanner scan = new Scanner(System.in);

    // Getting file name
    System.out.print("File to be read: ");
    String fileName = scan.nextLine();

    // Add the file extension to fileName if it doesn't have contain it
    if (!fileName.contains(".")) fileName += ".txt";

    // Getting field to sort by
    System.out.print("Field to sort by: ");
    String sortField = scan.nextLine();

    // Try/Catch so the program doesn't crash when the user enters
    // a file that doesn't exist
    try
    {
      // Creating new scanner to read in from file
      Scanner file = new Scanner(new File(fileName));

      // Creating array list of blood donors
      ArrayList<BloodDonor> donors = new ArrayList<>();

      // Another try/catch to deal with the file not having proper input
      try
      {
        // Looping through each line of input and adding those values to the donors ArrayList
        while (file.hasNext())
        {
          // Reading line from file
          String line = file.nextLine();

          // Creating array of strings
          String[] values = line.split(",");

          // Creating new BloodDonor objects with the proper values and adding them
          // to the donors ArrayList
          BloodDonor donor = new BloodDonor(Integer.parseInt(values[0]), values[1], values[2], values[3], Double.parseDouble(values[4]));
          donors.add(donor);
        }

        // Sort by idNum if that's what the user entered
        if (sortField.equalsIgnoreCase("id num") || sortField.equalsIgnoreCase("id") || sortField.equalsIgnoreCase("idnum"))
        {
          // Sort donors ArrayList using the IdComparator class
          Collections.sort(donors, new IdComparator());

          // Print the sorted list
          printTable(donors);
        }

        // Sort by lastName if that's what the user entered
        else if (sortField.equalsIgnoreCase("last") || sortField.equalsIgnoreCase("lastname") || sortField.equalsIgnoreCase("last name"))
        {
          // Sort donors ArrayList using the LastNameComparator class
          Collections.sort(donors, new LastNameComparator());

          // Print the sorted list
          printTable(donors);
        }

        // Sort by firstName if that's what the user entered
        else if (sortField.equalsIgnoreCase("first") || sortField.equalsIgnoreCase("firstname") || sortField.equalsIgnoreCase("first name"))
        {
          // Sort donors ArrayList using the FirstNameComparator class
          Collections.sort(donors, new FirstNameComparator());

          // Print the sorted list
          printTable(donors);
        }

        // Sort by bloodType if that's what the user entered
        else if (sortField.equalsIgnoreCase("type") || sortField.equalsIgnoreCase("blood type") || sortField.equalsIgnoreCase("blood") || sortField.equalsIgnoreCase("bloodtype"))
        {
          // Sort donors ArrayList using the TypeComparator class
          Collections.sort(donors, new TypeComparator());

          // Print the sorted list
          printTable(donors);
        }

        // Sort by donationTime if that's what the user entered
        else if (sortField.equalsIgnoreCase("time") || sortField.equalsIgnoreCase("donationtime") || sortField.equalsIgnoreCase("donation time"))
        {
          // Sort donors ArrayList using DonationTimeComparator class
          Collections.sort(donors, new DonationTimeComparator());

          // Print the sorted list
          printTable(donors);
        }

        // Print usage statement if the user did not provide
        // proper input
        else
        {
          usage();
        }
      }
      catch(ArrayIndexOutOfBoundsException e)
      {
        usage();
      }
    }
    catch (FileNotFoundException e)
    {
      usage();
    }
  }

  // Method to print usage statement
  public static void usage()
  {
    System.out.println();
    System.out.println("When prompted, enter a the name of the file. The file extension is optional");
    System.out.println("Then, type in the name of the field you would like to sort by [id num, last, first, type, time]");
    System.out.println();
    System.out.println("Make sure your file has the necessary values seperated by commas with no spaces. As in this:");
    System.out.println("[Id Number],[Last Name],[First Name],[Blood Type],[Donation Time]");
    System.out.println();
  }

  // Method used to print the sorted list as a table
  public static void printTable(ArrayList<BloodDonor> donors)
  {
    // Printing titles for columns
    System.out.printf("%n %7s    %-15s %-15s %-8s %-4s %n %n", "Id Num", "Last", "First", "Type", "Time");

    // Printing sorted list
    for (BloodDonor d : donors)
    {
      System.out.printf("%8d    %-15s %-15s %-8s %-4.1f %n", d.idNum, d.lastName, d.firstName, d.type, d.donationTime);
    }
  }
}
