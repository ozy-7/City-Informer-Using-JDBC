package jdbcProject;
import jdbcProject.DataAccess.City;
import jdbcProject.DataAccess.CityDAO;
import jdbcProject.DataAccess.AddCityDialog;
import jdbcProject.DataAccess.CityTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class CitySearchApp extends JFrame{
    private JTextField textField1;
    private JButton searchButton;
    private JTable table1;
    private JLabel myLabel;
    private JScrollPane myScrollPane;
    private JPanel myPanel1;
    private JPanel myPanel2;
    private JButton insertButton;
    /*private JButton deleteButton;
    private JButton sortButton;
    private JPanel buttonsPanel;
    private JButton selectButton;*/
    private JPanel myPanel3;



    // FOR GUI TO CONNECT WITH DATA COMING FROM MYSQL
    private CityDAO cityDAO;


    public CitySearchApp() {
        try {
            cityDAO = new CityDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = textField1.getText();
                    List<City> cities = null;

                    if(name != null && name.trim().length() > 0) {
                        cities = cityDAO.searchCities(name);
                    }
                    else {
                        cities = cityDAO.getAllCities();
                    }

                    CityTableModel cityTableModel = new CityTableModel(cities);

                    table1.setModel(cityTableModel);


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(CitySearchApp.this, "Error: " + exception, "Error", JOptionPane.ERROR_MESSAGE);
                }


            }


        });


        /*insertButton = new JButton();
        deleteButton = new JButton();
        sortButton = new JButton();*/
        setTitle("City Info");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(myPanel1);
        pack();
        setVisible(true);


        /*insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CREATE DIALOG
                AddCityDialog addCityDialog = new AddCityDialog(CitySearchApp.this, cityDAO);

                //SHOW DIALOG
                addCityDialog.setVisible(true);
            }
        });*/

    }

    public void refreshCities() {
        try {
            List<City> cities = cityDAO.getAllCities();

            //CREATE THE MODEL AND REPLACE THE OLD WITH THIS UPDATED ONE
            CityTableModel cityTableModel = new CityTableModel(cities);

            cityTableModel.setModel(cityTableModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
