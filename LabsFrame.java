
//package hussainiaproject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class LabsFrame extends JFrame implements ActionListener {

    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel firstPanel;
    private JPanel submitPanel;
    private JPanel calculatePanel;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable jTable1;
    private JTable jTable2;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JFrame lFrame;
    private JLabel jLabel4;
    private JTextField jTextField3;
    private JButton submitButton;
    private JButton backButton;
    private String labTableLabel;
    private ArrayList<JRadioButton> buttonArray = new ArrayList<>();
    private JButton calculateButton;
    private JPanel topPanel;
    private JPanel itemCountPanel;
    private JLabel outputLabel;
    private JLabel refreshLabel;
    

    public LabsFrame(JFrame labFrame) {
        super();
        JOptionPane.showMessageDialog(null, "In this next frame, create new Labs by entering in a lab name, submit some lab items and then Calculate!", "HELPER TOOL", JOptionPane.INFORMATION_MESSAGE);

        lFrame = labFrame;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1335, 690);
        
        
       Box tenseBox = Box.createVerticalBox();
        String[] labNames = new String[1];
        JavaDb objDb = new JavaDb("IaDb");
        
               try {
            labNames = objDb.getLabList();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LabsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jLabel1 = new JLabel();
        topPanel = new JPanel();
        jLabel2 = new JLabel();
        refreshLabel = new JLabel();
        jTextField1 = new JTextField(90);
        jLabel3 = new JLabel();
        jTextField2 = new JTextField(10);
        jButton1 = new JButton();
        jButton2 = new JButton();
        firstPanel = new JPanel();
        submitPanel = new JPanel();
        calculatePanel = new JPanel();
        itemCountPanel = new JPanel();
        jLabel4 = new JLabel();
        jTextField3 = new JTextField(10);
        submitButton = new JButton("Submit Items");
        backButton = new JButton("Back");
        calculateButton  = new JButton("Calculate");
        outputLabel = new JLabel("Output:");
        
        
         
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
       // calculatePanel.setLayout(new BoxLayout(calculatePanel, BoxLayout.LINE_AXIS));
        jLabel1.setText("Create a New Lab      Once you have finished Creating a Lab and Adding Items, refresh this Window          Choose from already created Labs down HERE");
        jLabel1.setFont(new Font("Arial", Font.ITALIC|Font.BOLD,16));
        jLabel2.setText("Name of New Lab:");
        refreshLabel.setText("Once you have finished Creating a Lab and Adding Items, refresh this Window");

        jLabel3.setText("Item for New Lab:");
        
        jLabel4.setText("Item Count for New Lab:");
        
        jButton1.setText("Enter");
        jButton1.addActionListener(this);

        jButton2.setText("Add item");
        jButton2.addActionListener(this);

        
        
        this.add(topPanel, BorderLayout.NORTH);
        topPanel.add(jLabel1);
        
        this.add(firstPanel, BorderLayout.SOUTH);
        firstPanel.add(jLabel2);
        firstPanel.add(jTextField1);
        firstPanel.add(jButton1);
        
        
        
        this.add(submitPanel, BorderLayout.CENTER);
        submitPanel.add(jLabel3);
        submitPanel.add(jTextField2);
        submitPanel.add(jLabel4);
        submitPanel.add(jTextField3);
        submitPanel.add(submitButton);
        jTextField2.setSize(10,10);
        refreshLabel.setFont(new Font("Helvetica", Font.BOLD, 16)); 
      
        for(String labName : labNames)
        {
            JRadioButton temp = new JRadioButton(labName);
            tenseBox.add(temp);
            calculatePanel.add(temp);
            buttonArray.add(temp);
        }
        
        calculatePanel.add(calculateButton);
        //calculatePanel.add(outputLabel);
    
        submitPanel.add(outputLabel, SwingConstants.SOUTH);
        calculatePanel.setBackground(new Color(141, 168, 224));
        submitPanel.setBackground(new Color(141, 168, 224));
        firstPanel.setBackground(new Color(141, 168, 224));
        itemCountPanel.setBackground(new Color(141, 168, 224));
        //calculatePanel.add(refreshLabel);
        this.add(calculatePanel, BorderLayout.EAST);
        this.add(itemCountPanel, BorderLayout.WEST);
        itemCountPanel.add(backButton);
        backButton.setPreferredSize(new Dimension(40,40));
        
        submitButton.addActionListener(this);
        backButton.addActionListener(this);
        calculateButton.addActionListener(this);
        
       
    }                       

 public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
        
        
        if(command.equals("Back"))
        {
            ChooseFrame open = new ChooseFrame(this);
            this.setVisible(false);
            open.setVisible(true);
        }
        
        JavaDb dbobj = new JavaDb("IaDb");
        Connection conn;
        int status;
        conn = dbobj.getDbConn();
        String query;
        
        
        if(command.equals("Calculate"))
        {
           String output;
           Object[][] supplies;
           String[] names;
           int [] counts;
           int [] finalCount;
           boolean enough = true;
           for(JRadioButton temp : buttonArray)
            {
                if(temp.isSelected()) {
               supplies = dbobj.suppliesNeeded(temp.getText());
               names = new String[supplies.length];
               counts = new  int[supplies.length];
               finalCount = new int[supplies.length];
                    for(int i=0; i<supplies.length; i++)
                    {
                       names[i] = (String)supplies[i][0];
                       System.out.println(supplies[i][1]);
                       counts[i] = Integer.parseInt((String) supplies[i][1]);
                    }
                    for(int i=0; i<supplies.length; i++)
                    {
                       finalCount[i] = dbobj.returnCount(names[i], "IaDb") - counts[i];
                       if(finalCount[i] < 0)
                       {
                           enough = false;
                       }
                       
                    }
                    if(enough)
                    {
                        output = "You have enough items to do this lab!";
                        outputLabel.setText("              "+output);
                        outputLabel.setFont(new Font("Helvetica", Font.ITALIC, 26));
                        this.validate();
                        this.repaint();
                              
                    }
                    else
                    {
                        output = "You do not have enough items for this lab, please restock Inventory!";
                        outputLabel.setText("              "+output);
                        outputLabel.setFont(new Font("Helvetica", Font.ITALIC, 16));
                        this.validate();
                        this.repaint();
                    }
     
                    //JOptionPane.showInternalMessageDialog(this, "Result",output, JOptionPane.PLAIN_MESSAGE);
                    System.out.println(output);
                }
            } 
           
           
        }

        if(command.equals("Enter"))
         {
          labTableLabel = jTextField1.getText().replace(" ", "_"); //make it string!!!!
          String newTable = "CREATE TABLE "+labTableLabel+" ( " +
                "itemName varchar(20), " +
                "itemCount int " +
            ")"; 
        System.out.println(newTable);
        dbobj.createTable(newTable, "IaDb");
        JOptionPane.showMessageDialog(null, "You have created a new Lab called "+labTableLabel);
        this.validate();
        this.repaint();
        
        }
        if(command.equals("Submit Items"))
        {
            String itemName = jTextField2.getText();
            String itemCount = jTextField3.getText();
            int itemInt;
            
            
            if(!itemCount.matches(".*\\d+.*"))
                    {
                        System.out.println("Hi");
                        ErrorFrame objEF = new ErrorFrame();
                    }
            else{

            itemInt = Integer.parseInt(itemCount);
            query = "INSERT INTO "+ labTableLabel + " (itemName, itemCount) "
                    + "VALUES (?,?)";
            try{
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, itemName);
                statement.setInt(2, itemInt);
                status = statement.executeUpdate();
                System.out.println("IT WORKED");
               JOptionPane.showMessageDialog(null, "You have added new item of "+itemName+" with count of "+itemCount);

            }
            catch(Exception error){
                ErrorFrame objEF = new ErrorFrame();
                error.printStackTrace();
            }
        }
            
            this.validate();
            this.repaint();
           
        
        }
    }
}
    
                  

