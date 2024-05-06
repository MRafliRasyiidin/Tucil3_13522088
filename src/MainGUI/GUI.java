package MainGUI;
import algorithm.GBFS;
import algorithm.UCS;
import algorithm.AStar;
import algorithm.Controller;
import algorithm.ReturnElement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {
    private static String algorithm = "empty";
    private Controller kamus = new Controller();

    public GUI() {
        JFrame frame = new JFrame("Word Ladder Solver");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        // Input and submit button
        JLabel startLabel = new JLabel("Start word:");
        JTextField startTextField = new JTextField(15);
        JLabel targetLabel = new JLabel("Target word:");
        JTextField targetTextField = new JTextField(15);
        JButton button = new JButton("Submit");
        button.setPreferredSize(new Dimension(100,20));
        JLabel timeOutput = new JLabel("Time elapsed: -");
        JLabel methodWarning = new JLabel("");

        // Searching method
        ButtonGroup g = new ButtonGroup();
        JRadioButton ucs = new JRadioButton("UCS");
        JRadioButton gbfs = new JRadioButton("Greedy Best First Search");
        JRadioButton astar = new JRadioButton("A*");

        ucs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAlgorithm("UCS");
            }
        });
        gbfs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAlgorithm("GBFS");
            }
        });
        astar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAlgorithm("A*");
            }
        });

        // Output
        JPanel outputPanel = new JPanel();
        JTextArea output = new JTextArea(15, 20);
        output.setEditable(false); 
        outputPanel.add(output);
        JScrollPane scrollPane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputPanel.add(scrollPane);

        g.add(ucs);
        g.add(gbfs);
        g.add(astar);

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(startLabel, gbc);  
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(startTextField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(targetLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(targetTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        frame.add(ucs, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(gbfs, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(astar, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(button, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(new JLabel(" "), gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        frame.add(new JLabel("Result:"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        frame.add(timeOutput, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        frame.add(outputPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 11;
        frame.add(methodWarning, gbc);
        

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String start = startTextField.getText();
                String target = targetTextField.getText();
                if (start.length() == 0 || target.length() == 0) {
                    methodWarning.setText("Pastikan input tidak kosong!");
                }
                else if (start.length() != target.length()) {
                    methodWarning.setText("Pastikan jumlah huruf kedua kata sama!");
                }
                else if (!kamus.isWord(start) || !kamus.isWord(target)) {
                    methodWarning.setText("Kata yang dimasukkan tidak ada dalam kamus!");
                }
                else {
                    ReturnElement result = new ReturnElement();
                    if (GUI.getAlgorithm().equals("empty")) {
                        methodWarning.setText("Pastikan untuk memilih metode pencarian!");
                    }
                    else {
                        methodWarning.setText(null);
                        if (GUI.getAlgorithm().equals("UCS")) {
                            UCS x = new UCS();
                            result = x.ucs(start, target);
                        }
                        else if (GUI.getAlgorithm().equals("GBFS")) {
                            GBFS x = new GBFS();
                            result= (x.gbfs(start, target));
                        }
                        else if (GUI.getAlgorithm().equals("A*")) {
                            AStar x = new AStar();
                            result= (x.aStar(start, target));
                        }
                        output.setText(null);
                        List<String> pathResult = result.getList();
                        for (int i = 0; i < pathResult.size(); i++) {
                            output.append(pathResult.get(i) + "\n");
                        }
                        output.setCaretPosition(0);
                        timeOutput.setText("Time elapsed: " + result.getTime() + " ms");
                    }
                }
            }
        });
        
        
        
        frame.setVisible(true);
    }
    
    public void printOutput(JTextArea output, List<String> path) {
        for (int i = 0; i < path.size(); i++) {
            output.append(path.get(i) + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI();

    });
    }

    public static String getAlgorithm() {
        return algorithm;
    }

    public static void setAlgorithm(String word) {
        algorithm = word;
    }
}
