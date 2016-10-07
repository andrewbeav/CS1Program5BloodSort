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

    // Getting field to sort by
    System.out.print("Field to sort by: ");
    String sortField = scan.nextLine();

    // Creating new scanner to read in from file
    Scanner file = new Scanner(new File(fileName));

    // Creating array list of blood donors
    ArrayList<BloodDonor> donors = new ArrayList<>();

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
  }

  public static void usage()
  {
    System.out.println("When prompted, enter a the name of the file with the file extension. Then, type in the name of the field you would like to sort by [id num, last, first, type, time]");
  }
}
