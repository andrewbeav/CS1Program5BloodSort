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

    while (file.hasNext())
    {
      String line = file.nextLine();
      String[] values = line.split(",");
      BloodDonor donor = new BloodDonor(Integer.parseInt(values[0]), values[1], values[2], values[3], Double.parseDouble(values[4]));
      donors.add(donor);
    }
  }
}
