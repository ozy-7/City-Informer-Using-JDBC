package jdbcProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSide extends JFrame{
    private JTextField textField1;
    private JButton nextButton;
    private JTable table1;
    private JLabel myLabel;
    private JScrollPane myScrollPane;
    private JPanel myPanel1;
    private JPanel myPanel2;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton sortButton;
    private JButton selectButton;
    private JPanel myPanel3;

    public UserSide() {
        setTitle("City Info");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(myPanel1);
        pack();
        setVisible(true);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameLike = textField1.getText();
                System.out.println(nameLike);
            }
        });



    }
}
