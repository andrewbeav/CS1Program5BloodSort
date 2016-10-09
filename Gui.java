import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame
{
  private JTextField fileNameText;
  private JTextField sortFieldText;
  private JButton sortButton;

  public Gui()
  {
    super("Blood Donor Sorting Progam");
    setLayout(new FlowLayout());

    fileNameText = new JTextField(10);
    sortFieldText = new JTextField(10);
    sortButton = new JButton("sort");

    add(fileNameText);
    add(sortFieldText);
    add(sortButton);
  }
}
