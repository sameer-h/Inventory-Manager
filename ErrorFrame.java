//package hussainiaproject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//Frame for errors

public class ErrorFrame extends JFrame
{
    final JLabel errorLabel;
    final JLabel secondLabel;
    JPanel centerPanel;
    public ErrorFrame()
    {
        super();
        this.setBounds(100,200,500,200);
        
        this.setLayout(new BorderLayout());
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

        errorLabel = new JLabel("There's been an error in your action "
                + "with this database");
        secondLabel = new JLabel("PLEASE ONLY ENTER IN CORRECT VALUES FOR THE TEXTFIELD - NO LETTERS!");
        centerPanel.add(errorLabel);
        centerPanel.add(secondLabel);
        this.add(centerPanel, BorderLayout.CENTER);
        
        this.setVisible(true);
    }
}
