import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class validator implements ActionListener
{
    //import the above libraries.
    private static JButton button;
    private static JLabel info;

    private static JLabel itemLabel;
    private static JTextField itemText;

    private static JLabel numberOfItemsLabel;
    private static JTextField numItemsText;

    private static  String item;
    private static int numberOfItems;

    public static String getItem()
    {
        return item;
    }
    public static int getNumberOfItems()
    {
        return numberOfItems;
    }

    public static void outputGUI()
    {
        JPanel panel = new JPanel();

        JFrame frame = new JFrame();
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("UofC inventory management");
        frame.add(panel);

        panel.setLayout(null);

        itemLabel = new JLabel("Furniture item to be purchased:");
        itemLabel.setBounds(60, 40, 300, 35);
        panel.add(itemLabel);

        itemText = new JTextField("e.g : mesh chair");
        itemText.setBounds(322,40,250,35);
        panel.add(itemText);

        numberOfItemsLabel = new JLabel("Number of items requested:");
        numberOfItemsLabel.setBounds(60,80,300,35);
        panel.add(numberOfItemsLabel);

        numItemsText = new JTextField("e.g: 2");
        numItemsText.setBounds(322,80,250, 35);
        panel.add(numItemsText);

        button = new JButton("Submit");
        button.setBounds(250, 460, 100, 25);
        button.addActionListener(new validator());
        panel.add(button);

        info = new JLabel("<html> Inventory management <br/> <br/> </html>");
        info.setBounds(50, 120, 700, 255);
        panel.add(info);

        frame.setVisible(true);
    }
    public static void main(String args[])
    {
        outputGUI();
    }

    public void actionPerformed(ActionEvent e)
    {
        item = itemText.getText();
        String numItems = numItemsText.getText();

        numberOfItems = Integer.parseInt(numItems);
        String message = item + numItems;

        //String message = "Submission successful! \nPlease close this window and open the text file created in the directory. ";
        
        info.setText(message);
    }
}