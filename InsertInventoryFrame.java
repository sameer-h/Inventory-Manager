/* Sameer Hussain - IB Computer Science 1 - Match 13, 2018 - This program inserts info to Table 1*/

//package hussainiaproject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class InsertInventoryFrame extends JFrame implements ActionListener
{
JTextField itemNameField;
   JLabel itemLabel;
   JPanel itemPanel;
   JTextField countField;
   JLabel countLabel;
   JPanel countPanel;
   JButton insert;
   final String worked = "insert inventory worked";
   private final Color FRAME_COLOR = new Color(84,164,245);
   
   //Constructor
   
   public InsertInventoryFrame()
    {
        super();
        this.setBounds(100,200,500,500);

        this.setLayout(new FlowLayout());
        
        itemNameField = new JTextField(15);
        itemLabel = new JLabel("Item name: ");
        itemPanel = new JPanel(new FlowLayout());
        countField = new JTextField(15);
        countLabel = new JLabel("Count: ");
        countPanel = new JPanel(new FlowLayout());
        
        insert = new JButton("Insert");
        insert.addActionListener(this);
        
        itemPanel.add(itemLabel);
        itemPanel.add(itemNameField);
        this.add(itemPanel);
        countPanel.add(countLabel);
        countPanel.add(countField);
        this.add(countPanel);
        this.add(insert);
        itemPanel.setForeground(FRAME_COLOR);
        countPanel.setForeground(FRAME_COLOR);
        
        this.setVisible(true);
    }

   //Action Performed
   
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
        String itemName;
        String lName;
        String count;
        int countInt;
        String gender;
        String job;
        String query;
        JavaDb dbobj = new JavaDb("IaDb");
        Connection conn;
        int status;
        conn = dbobj.getDbConn();
        if (command.equals("Insert"))
        {
            itemName = itemNameField.getText();
            count = countField.getText();

            
            if(!count.matches(".*\\d+.*"))
                    {
                        System.out.println("Hi");
                        ErrorFrame objEF = new ErrorFrame();
                    }
            else{
           
            countInt = Integer.parseInt(count);
            
            query = "INSERT INTO Inventory (itemName, itemCount) "
                    + "VALUES (?,?)";
                
          try{
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, itemName);
                statement.setInt(2, countInt);
                status = statement.executeUpdate();
                System.out.println(worked);
            }
            catch(Exception error){
                ErrorFrame objEF = new ErrorFrame();
                error.printStackTrace();
            }
          }
        }
        
    }
}