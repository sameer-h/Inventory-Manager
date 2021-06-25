/* Sameer Hussain - IB Computer Science 1 - Match 13, 2018 - This program updates info from Table 1*/

//package hussainiaproject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateInventoryFrame extends JFrame implements ActionListener
{
   JTextField itemNameField;
   JLabel itemLabel;
   JPanel itemPanel;
  
   JTextField countField;
   JLabel countLabel;
   JPanel countPanel;
   
   JButton update;
   private final Color FRAME_COLOR = new Color(84,164,245);
   
   //Constructor
   
   public UpdateInventoryFrame()
    {
        super();
        this.setBounds(100,200,500,500);

        this.setLayout(new FlowLayout());
        
        this.getContentPane().setBackground(FRAME_COLOR);
        
        itemNameField = new JTextField(15);
        itemLabel = new JLabel("itemName:");
        itemPanel = new JPanel(new FlowLayout());
        countField = new JTextField(15);
        countLabel = new JLabel("itemCount: ");
        countPanel = new JPanel(new FlowLayout());
        
        update = new JButton("Update");
        update.addActionListener(this);
        
        itemPanel.add(itemLabel);
        itemPanel.add(itemNameField);
        this.add(itemPanel);
        countPanel.add(countLabel);
        countPanel.add(countField);
        this.add(countPanel);
        this.add(update);
        itemPanel.setForeground(FRAME_COLOR);
        countPanel.setForeground(FRAME_COLOR);
        
        this.setVisible(true);
    }
   
   //Action Performed

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
        String fName;
        String cnt;
        int cntInt;
        String query;
        JavaDb dbobj = new JavaDb("IaDb");
        Connection conn;
        int status;
        conn = dbobj.getDbConn();
        if (command.equals("Update"))
        {
            fName = itemNameField.getText();
            cnt = countField.getText();
            
            if(!cnt.matches(".*\\d+.*"))
            {
                System.out.println("Hi");
                ErrorFrame objEF = new ErrorFrame();
                
            }
            
            else{
                
            
            cntInt = Integer.parseInt(cnt);
            query = "UPDATE Inventory " +
                    "SET itemCount=? " +
                    "WHERE itemName=?";
            try{
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setInt(1, cntInt);
                statement.setString(2, fName);
                status = statement.executeUpdate();
            }
            catch(Exception error){
                ErrorFrame objEF = new ErrorFrame();
                error.printStackTrace();
            }
          }
            
        }
        
    }
}