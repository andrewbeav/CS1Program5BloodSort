import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame
{
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
  }
}
