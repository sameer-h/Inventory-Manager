/* Sameer Hussain - IB Computer Science 1 - Match 13, 2018 - This program deletes info from Table 1*/

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
import javax.swing.JTextField;



public class DeleteInventoryFrame extends JFrame implements ActionListener
{
   JTextField itemNameField;
   JLabel itemLabel;
   JButton delete;
   private final Color FRAME_COLOR = new Color(84,164,245);
   
   //Constructor
   
   public DeleteInventoryFrame()
    {
        super();
        this.setBounds(100,200,500,500);
        this.getContentPane().setBackground(FRAME_COLOR);

        this.setLayout(new FlowLayout());
        
        itemNameField = new JTextField(15);
        itemLabel = new JLabel("Item name: ");
        
        delete = new JButton("Delete");
        delete.addActionListener(this);
        
        this.add(itemLabel);
        this.add(itemNameField);
        this.add(delete);
        
        this.setVisible(true);
    }

   //Action Performed
   
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
        String itemName;
        String query;
        JavaDb dbobj = new JavaDb("IaDb");
        Connection conn;
        int status;
        conn = dbobj.getDbConn();
        if (command.equals("Delete"))
        {
            itemName = itemNameField.getText();
            query = "DELETE FROM Inventory WHERE itemName=?";
            try{
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, itemName);
                status = statement.executeUpdate();
            }
            catch(Exception error){
                ErrorFrame objEF = new ErrorFrame();
                error.printStackTrace();
            }
        }
        
    }
}

