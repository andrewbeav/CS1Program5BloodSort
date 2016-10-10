import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Gui extends JFrame
{
  private String[] columnTitles = {"Id Num", "Last", "First", "Blood Type", "Donation Time"};

  private JLabel fileNameTextLabel;
  private JLabel sortFieldTextLabel;
  private JTextField fileNameText;
  private JTextField sortFieldText;
  private JButton sortButton;
  private JTable resultsTable;

  public Gui()
  {
    super("Blood Donor Sorting Progam");
    setLayout(new FlowLayout());

    fileNameText = new JTextField(10);
    sortFieldText = new JTextField(10);
    sortButton = new JButton("sort");
    fileNameTextLabel = new JLabel("File name: ");
    sortFieldTextLabel = new JLabel("Field to Sort by: ");
    resultsText = new JTextArea();

    add(fileNameTextLabel);
    add(fileNameText);
    add(sortFieldTextLabel);
    add(sortFieldText);
    add(sortButton);
    add(resultsTable);

    ButtonEventHandler buttonHandler = new ButtonEventHandler();
    sortButton.addActionListener(buttonHandler);
    sortFieldText.addActionListener(buttonHandler);
    fileNameText.addActionListener(buttonHandler);
  }

  private class ButtonEventHandler implements ActionListener
  {
    public Object[][] results;
    public String fileName;
    public String sortField;

    public boolean isInputValid = true;

    public void actionPerformed(ActionEvent event)
    {
      // Creating string to hold the usage statement
      String usageStatement = String.format("Enter the name of the file and the field you would like to sort by in the appropriate field.%nThe file extension is optional.%n");
      usageStatement += String.format("Make sure your file has the necessary values seperated by commas with no spaces. As in this:%n");
      usageStatement += String.format("[Id Number],[Last Name],[First Name],[Blood Type],[Donation Time]%n");

      fileName = fileNameText.getText();
      sortField = sortFieldText.getText();

      // Add the file extension to fileName if it doesn't have contain it
      if (!fileName.contains(".")) fileName += ".txt";

      sort();

      System.out.println(results);

    }

    public void sort()
    {
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
            results = printTable(donors);
          }

          // Sort by lastName if that's what the user entered
          else if (sortField.equalsIgnoreCase("last") || sortField.equalsIgnoreCase("lastname") || sortField.equalsIgnoreCase("last name"))
          {
            // Sort donors ArrayList using the LastNameComparator class
            Collections.sort(donors, new LastNameComparator());

            // Print the sorted list
            results = printTable(donors);
          }

          // Sort by firstName if that's what the user entered
          else if (sortField.equalsIgnoreCase("first") || sortField.equalsIgnoreCase("firstname") || sortField.equalsIgnoreCase("first name"))
          {
            // Sort donors ArrayList using the FirstNameComparator class
            Collections.sort(donors, new FirstNameComparator());

            // Print the sorted list
            results = printTable(donors);
          }

          // Sort by bloodType if that's what the user entered
          else if (sortField.equalsIgnoreCase("type") || sortField.equalsIgnoreCase("blood type") || sortField.equalsIgnoreCase("blood") || sortField.equalsIgnoreCase("bloodtype"))
          {
            // Sort donors ArrayList using the TypeComparator class
            Collections.sort(donors, new TypeComparator());

            // Print the sorted list
            results = printTable(donors);
          }

          // Sort by donationTime if that's what the user entered
          else if (sortField.equalsIgnoreCase("time") || sortField.equalsIgnoreCase("donationtime") || sortField.equalsIgnoreCase("donation time"))
          {
            // Sort donors ArrayList using DonationTimeComparator class
            Collections.sort(donors, new DonationTimeComparator());

            // Print the sorted list
            results = printTable(donors);
          }

          // Print usage statement if the user did not provide
          // proper input
          else
          {
            isInputValid = false;
            usage();
          }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
          isInputValid = false;
          usage();
        }
      }
      catch (FileNotFoundException e)
      {
        isInputValid = false;
        usage();
      }
    }

    // Method to print usage statement
    public void usage()
    {
      System.out.println();
      System.out.println("When prompted, enter a the name of the file. The file extension is optional.");
      System.out.println("Then, type in the name of the field you would like to sort by [id num, last, first, type, time]");
      System.out.println();
      System.out.println("Make sure your file has the necessary values seperated by commas with no spaces. As in this:");
      System.out.println("[Id Number],[Last Name],[First Name],[Blood Type],[Donation Time]");
      System.out.println();
    }

    // Method used to print the sorted list as a table
    public Object[][] printTable(ArrayList<BloodDonor> donors)
    {
      Object[][] data = new Object[5][donors.size()];

      int i = 0;
      for (BloodDonor donor : donors)
      {
        data[i][0] = donor.idNum;
        data[i][1] = donor.lastName;
        data[i][2] = donor.firstName;
        data[i][3] = donor.type;
        data[i][4] = donor.donationTime;
        i++;
      }
      return data;
    }
  }

}
