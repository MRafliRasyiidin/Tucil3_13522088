import javax.swing.*;
import java.awt.*;

public class GUI {
    public GUI() {
        JFrame frame = new JFrame("My First Swing GUI");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel startLabel = new JLabel("Start word:");
        startLabel.set
        JTextField startTextField = new JTextField(15);
        JLabel targetLabel = new JLabel("Target word:");
        JTextField targetTextField = new JTextField("ase", 15);
        JButton button = new JButton("Submit");

        //button.addActionListener(e -> {
        //    String name = textField.getText();
        //    JOptionPane.showMessageDialog(frame, "Hello, " + name + "!");
        //});

        frame.add(startLabel);
        frame.add(targetLabel);
        frame.add(startTextField);
        frame.add(targetTextField);
        frame.add(button);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI();

    });
    }
}
