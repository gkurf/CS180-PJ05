 import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.*;
 
public class Spinner {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private static int count;

    public void SwingControlDemo(){
        prepareGUI();
    }
    public static void main(String[] args){
        prepareGUI();

    }
    private static void prepareGUI(){
        JFrame mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }        
        });    
        JLabel headerLabel = new JLabel("", JLabel.CENTER);        
        JLabel statusLabel = new JLabel("",JLabel.CENTER);    
        JButton enter = new JButton("Enter Integer");
        statusLabel.setSize(350,100);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, //initial value
            1, //min
            100, //max
            1);//step
        JSpinner spinner = new JSpinner(spinnerModel);
        controlPanel.add(spinner);
        controlPanel.add(enter);
        mainFrame.setVisible(true);  
        mainFrame.setVisible(true);  

        count = 0;
        while (count == 0) {
            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
        }

        System.out.println((int)spinner.getValue());
    }
}