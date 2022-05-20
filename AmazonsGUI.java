import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AmazonsGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game of the Amazons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button1 = new JButton("Press");
        frame.getContentPane().add(button1);
        frame.setVisible(true);
    }
}

