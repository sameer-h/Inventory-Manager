
//package hussainiaproject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
public class ChooseFrame extends JFrame implements ActionListener
{

    private JLabel firstPanel;
    private JLabel secondPanel;
    private JLabel thirdPanel;
    private JLabel fourthPanel;
    
    private JLabel fifthPanel;
    private JLabel sixthPanel;
    private JLabel seventhPanel;
    private JLabel eigthPanel;
    
    private JFrame ceFrame; 
    private JPanel pane;
    private JButton backButton;
    private JPanel buttonPanel;
    
    private JButton goInventory;
    private JButton goLabs;
   //Choose  Frame

    public ChooseFrame(JFrame cFrame)
            
    {
        super();
        ceFrame = cFrame;
        this.setBounds(100,200,1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        backButton = new JButton("Back");
        backButton.setSize(new Dimension(50, 50));
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        
        buttonPanel = new JPanel();
        goInventory = new JButton("Inventory");
        goLabs  = new JButton("Labs");

        firstPanel = new JLabel();
        secondPanel = new JLabel();
        thirdPanel = new JLabel();
        fourthPanel = new JLabel();
        
        fifthPanel = new JLabel();
        sixthPanel = new JLabel();
        seventhPanel = new JLabel();
        eigthPanel = new JLabel();
        
        firstPanel.setText("					Hello! Welcome to Lab Inventory Management Software.");

        secondPanel.setText("In this frame, navigate to either the 'Labs' or 'Inventory' Section. ");

        thirdPanel.setText("        Insert, Update, and Delete Items into the Inventory database!");

        fourthPanel.setText("  Create labs with item requirements and then calculate to see if it can be done!  ");
        
        
        fifthPanel.setText("   This program was created with the intent of ");

        sixthPanel.setText("   allowing the client to have a relational database management system ");

        seventhPanel.setText("   in order to keep track of their Laboratory supplies and be able to create");

        eigthPanel.setText("   labs to determine if they can be performed");
       
       //Adding components
        
        this.add(pane, BorderLayout.CENTER);
        pane.setForeground(new Color(110,136,179));
        pane.setBackground(new Color(110,136,179));
        pane.add(firstPanel);
        pane.add(secondPanel);
        pane.add(thirdPanel);
        pane.add(fourthPanel);
        firstPanel.setFont(new Font("Helvetica", Font.BOLD, 26));
        secondPanel.setFont(new Font("Helvetica", Font.BOLD, 26));
        thirdPanel.setFont(new Font("Helvetica", Font.BOLD, 26));
        fourthPanel.setFont(new Font("Helvetica", Font.BOLD, 26));
        
        fifthPanel.setFont(new Font("Helvetica", Font.BOLD, 26));
        sixthPanel.setFont(new Font("Helvetica", Font.BOLD, 26));
        seventhPanel.setFont(new Font("Helvetica", Font.BOLD, 26));
        eigthPanel.setFont(new Font("Helvetica", Font.BOLD, 26));
        
        pane.add(backButton);
        pane.add(fifthPanel);
        pane.add(sixthPanel);
        pane.add(seventhPanel);
        pane.add(eigthPanel);
        firstPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        thirdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fourthPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        fifthPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sixthPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        seventhPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eigthPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(goInventory);
        buttonPanel.add(goLabs);
        
        backButton.addActionListener(this);
        goInventory.addActionListener(this);
        goLabs.addActionListener(this);
        
        this.setVisible(false);
        
        
    }         
    
    //Action Performed
@Override
     public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
        
      
         if(command.equals("Inventory"))
        {
        	System.out.println("inventory");
                
                this.setVisible(false);
                
                MainFrame go = new MainFrame(this);
                
                go.setVisible(true);
        }
          
     
        if(command.equals("Labs"))
        {
        	System.out.println("labs"); //GOING TO OPEN INV FRAME
               
                this.setVisible(false);
                
                LabsFrame frame = new LabsFrame(this);
                frame.setVisible(true);

        }
        
        
        if(command.equals("Back"))
        {
            System.out.println("THIS IS BACKBUTTON");
            this.setVisible(false);
            HomeFrame home = new HomeFrame();
            home.setVisible(true);

        }
   
    }

    

}
