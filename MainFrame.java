
//package hussainiaproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class MainFrame extends JFrame implements ActionListener
{
    private Object[][] inventoryData = new Object[2][2];
   // private
    private JFrame mFrame;
    private JScrollPane scrollPanel1;
    private JTable invTable;
    private JTableHeader invHeader;
    private String tableName1 = "Inventory";
    private JPanel inventoryPanel;
    private JPanel backPanel;
    private JButton displayInventory;
    private JButton updateInventory;
    private JButton deleteInventory;
    private JButton insertInventory;
    private JButton backButton;
    
    private JTable timeOutTable;
    private JTableHeader TimeOutHeader;

    private JPanel timeOutPanel;
    private JButton displayTimeOut;
    private JButton updateTimeOut;
    private JButton deleteTimeOut;
    private JButton insertTimeOut;
    private TableColumn column1;
    //private TableColumn column2;
    private String[] columnTitles1 = {"itemName", "itemCount"};
    private JLabel noteLabel;
    private JPanel notePanel = new JPanel();
            JavaDb objDb = new JavaDb("IaDb");


       //Constructor
    
    public MainFrame(JFrame homeFrame)
    {
        
        super();
        mFrame = homeFrame;
        this.setBounds(100,200,1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
 
        scrollPanel1 = new JScrollPane();
        
        //scrollPanel2 = new JScrollPane();


        backButton = new JButton("Back");
        
        
        inventoryPanel = new JPanel(new FlowLayout());
        backPanel = new JPanel(new FlowLayout());
        
        displayInventory = new JButton("Display Inventory Table");
        displayInventory.addActionListener(this);
        updateInventory = new JButton("Update Inventory Table");
        updateInventory.addActionListener(this);
        deleteInventory = new JButton("Delete Inventory Table");
        deleteInventory.addActionListener(this);
        insertInventory = new JButton("Insert Inventory Table");
        insertInventory.addActionListener(this);
        
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        
        //noteLabel = new JLabel("NOTE: TO SEE YOUR CHANGES/UPDATES, YOU WILL HAVE TO REFRESH THE DATABASE", SwingConstants.CENTER);
        //noteLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        
        inventoryPanel.add(displayInventory);
        inventoryPanel.add(updateInventory);
        inventoryPanel.add(deleteInventory);
        inventoryPanel.add(insertInventory);
        
        backPanel.add(backButton);
        
        this.add(inventoryPanel);
        this.add(backPanel);
                
        this.setVisible(false);
    }


    
    //Action Performed
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
        
        if (command.equals("Back"))
        {
            this.setVisible(false);
            ChooseFrame openFrame = new ChooseFrame(this);
            openFrame.setVisible(true);
            
        }
        
        
        if (command.equals("Display Inventory Table"))
        {
            

            inventoryData = objDb.getData(tableName1, columnTitles1);

            invTable = new JTable(inventoryData, columnTitles1);
            invHeader = new JTableHeader(); 
            invHeader = invTable.getTableHeader();
            
            column1 = invTable.getColumnModel().getColumn(0);
            column1.setPreferredWidth(50);
            column1 = invTable.getColumnModel().getColumn(1);
            column1.setPreferredWidth(50);
            /*column1 = timeInTable.getColumnModel().getColumn(2);
            column1.setPreferredWidth(50);
            column1 = timeInTable.getColumnModel().getColumn(3);
            column1.setPreferredWidth(50);
            column1 = timeInTable.getColumnModel().getColumn(4);
            column1.setPreferredWidth(50);
*/
            
            invTable.setRowHeight(30);
            invTable.setGridColor(Color.BLACK);

            this.remove(scrollPanel1);
            
            scrollPanel1 = new JScrollPane();

           
            scrollPanel1.getViewport().add(invTable);
             
            
            invTable.setFillsViewportHeight(true);
           
           
            this.add(scrollPanel1, BorderLayout.EAST);

      
            
            this.validate();
            this.repaint();
        }
        else if (command.equals("Update Inventory Table"))
        {
            UpdateInventoryFrame objUArt = new UpdateInventoryFrame();
        }
        else if (command.equals("Delete Inventory Table"))
        {
            DeleteInventoryFrame objDArt = new DeleteInventoryFrame();
        }
        else if (command.equals("Insert Inventory Table"))
        {
            InsertInventoryFrame objIArt = new InsertInventoryFrame();
        }
       
        this.validate();
        this.repaint();
        
        
    }
    

}
