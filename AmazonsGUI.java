import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AmazonsGUI extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game of the Amazons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel panel = new JPanel();    
        frame.add(panel);


        JButton button1 = new JButton("Press");
        frame.getContentPane().add(button1);
        frame.setVisible(true);

        //grid();
    }
    
    public static void grid() {
        JButton[] buttons = new JButton[25];
		//setLayout(new GridLayout(3,3));
		for(int i = 0; i < 25; i++) {
            buttons[i] = new JButton();
            buttons[i].setText("-");
            buttons[i].setBackground(Color.white);
            //buttons[i].addActionListener(new ActionListener()) { }
            //add(buttons[i]); //adds this button to JPanel        
        }	
	}
}

