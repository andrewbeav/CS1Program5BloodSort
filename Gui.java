import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Gui extends JFrame
{
  public static String fileName;
  public static String sortField;

  private JLabel fileNameTextLabel;
  private JLabel sortFieldTextLabel;
  private JTextField fileNameText;
  private JTextField sortFieldText;
  private JButton sortButton;
  private JTextArea resultsText;

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
    add(resultsText);

    ButtonEventHandler buttonHandler = new ButtonEventHandler();
    sortButton.addActionListener(buttonHandler);
  }

  private class ButtonEventHandler implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      fileName = fileNameText.getText();
      sortField = sortFieldText.getText();

      // Add the file extension to fileName if it doesn't have contain it
      if (!fileName.contains(".")) fileName += ".txt";
    }
  }

}
