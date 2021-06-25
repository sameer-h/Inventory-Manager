
//package hussainiaproject;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.SwingConstants;


public class HomeFrame extends JFrame implements ActionListener
{
    private final Color FRAME_COLOR = new Color(100,100,100);
    private final Color TEXT_COLOR = new Color(11,11,11);
    private JLabel openingLabel;
    private final Font TEXT_FONT = new Font("Arial", Font.ITALIC|Font.BOLD,30); 
    private final java.net.URL MY_IMAGE = getClass().getResource("micro.jpg");
    private final ImageIcon THE_PICTURE = new ImageIcon(MY_IMAGE);
    private JLabel imageLabel;
    private JPanel homePanel;
    private JButton startButton;
    private JPanel welcomePanel;
    private JLabel descriptionLabel;
    private JButton exitButton;

    
    public HomeFrame()
            
    {
        //Setting up frame
        super();
        this.setBounds(200,200,800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(FRAME_COLOR);
        this.setLayout(new BorderLayout());
        
        
        //Declaring Frame Components
        homePanel = new JPanel(new FlowLayout());
        openingLabel = new JLabel("IB CS IA - Lab Inventory Organizer", SwingConstants.CENTER);
        welcomePanel = new JPanel(new FlowLayout());
        descriptionLabel = new JLabel("To use the program click the 'Start' button and enter your inventory items!");
        openingLabel.setFont(TEXT_FONT);
        openingLabel.setForeground(TEXT_COLOR);
        
        startButton = new JButton("Start");
        startButton.setFont(new Font(
                "Comic Sans MS", Font.ITALIC, 26));
        startButton.addActionListener(this);
        imageLabel = new JLabel(THE_PICTURE, SwingConstants.CENTER);
        
          //Configuring and adding components
        homePanel.setBackground(FRAME_COLOR);
        homePanel.add(openingLabel);
        homePanel.add(imageLabel);
        this.add(welcomePanel, BorderLayout.SOUTH);
        this.add(homePanel, BorderLayout.CENTER);
        welcomePanel.add(startButton);
        welcomePanel.add(descriptionLabel);
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        homePanel.add(exitButton);
        
        this.setVisible(true);
        
        
    }
    

public void actionPerformed(ActionEvent e)
{
    String command = e.getActionCommand();
    
    //Start button pressed
    if(command.equals("Start"))
    {
      ChooseFrame openFrame = new ChooseFrame(this);
      openFrame.setVisible(true);
      this.setVisible(false);
    }
        
    if(command.equals("Exit"))
    {
        System.exit(0);   
    }   
}
    
   public static void main(String[] args) {
       HomeFrame objHomeFrame = new HomeFrame();
   }
    
    
}
