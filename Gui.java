import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Gui extends JFrame
{
  private JLabel sortFieldTextLabel;
  private JButton getFileButton;
  private JTextField sortFieldText;
  private JButton sortButton;
  private JTextArea resultsText;
  private JFileChooser fileChooser;

  public String filePath;

  public Gui()
  {
    super("Blood Donor Sorting Progam");
    setLayout(new FlowLayout());

    getFileButton = new JButton("Select Input File");
    sortFieldText = new JTextField(10);
    sortButton = new JButton("sort");
    sortFieldTextLabel = new JLabel("Field to Sort by: ");
    resultsText = new JTextArea();

    add(getFileButton);
    add(sortFieldTextLabel);
    add(sortFieldText);
    add(sortButton);
    add(resultsText);

    ButtonEventHandler buttonHandler = new ButtonEventHandler();
    sortButton.addActionListener(buttonHandler);
    sortFieldText.addActionListener(buttonHandler);

    BrowseEventHandler browseHandler = new BrowseEventHandler();
    getFileButton.addActionListener(browseHandler);
  }

  private class BrowseEventHandler implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      JButton open = new JButton();
      fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      fileChooser.setDialogTitle("Finding file");
      if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION);
      pathName = fileChooser.getSelectedFile().getAbsolutePath();
    }
  }

  private class ButtonEventHandler implements ActionListener
  {
    public String results;
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

      if (isInputValid == true)
      {
        Font newResultsFont = new Font("monospaced", Font.PLAIN, 16);
        resultsText.setFont(newResultsFont);

        resultsText.setText(results);
      }
      else resultsText.setText(usageStatement);

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
    public String printTable(ArrayList<BloodDonor> donors)
    {
      String table = "";
      // Printing titles for columns
      table += String.format("%-8s    %-15s %-15s %-8s %-4s %n %n", "Id Num", "Last", "First", "Type", "Time");

      // Printing sorted list
      for (BloodDonor d : donors)
      {
        table += String.format("%-8d    %-15s %-15s %-8s %-4.1f %n", d.idNum, d.lastName, d.firstName, d.type, d.donationTime);
      }

      return table;
    }
  }

}
