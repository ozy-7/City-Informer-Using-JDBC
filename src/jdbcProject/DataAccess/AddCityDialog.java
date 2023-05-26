package jdbcProject.DataAccess;

import jdbcProject.CitySearchApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCityDialog extends JFrame{
    private JPanel myPanel1;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel idPanel;
    private JPanel namePanel;
    private JPanel countryCodePanel;
    private JPanel districtPanel;
    private JPanel populationPanel;
    private JPanel buttonPanel;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField countryCodeTextField;
    private JTextField districtTextField;
    private JTextField populationTextField;
    private JFrame jFrame;
    private CityDAO cityDAO;
    private CitySearchApp citySearchApp;
    private JDialog insertDialogBox;


    public AddCityDialog(CitySearchApp theCitySearchApp, CityDAO theCityDAO) {
        this();
        cityDAO = theCityDAO;
        citySearchApp = theCitySearchApp;
    }


    public AddCityDialog() {




        //INIT THE DIALOG BOX
        jFrame = new JFrame("Add city");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = jFrame.getWidth();
        int windowHeight = jFrame.getHeight();
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;
        jFrame.setLocation(x, y);
        jFrame.add(myPanel1);
        jFrame.pack();
        insertDialogBox = new JDialog(jFrame, "Add city", true);


        //SAVE BUTTON ACTION
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCity();
            }
        });
        //saveButton.setActionCommand("OK");
        //buttonPanel.add(saveButton);
        //getRootPane().setDefaultButton(saveButton);


        //CANCEL BUTTON ACTION
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                jFrame.dispose();
            }
        });
        //cancelButton.setActionCommand("Cancel");
        //buttonPanel.add(cancelButton);


    }



    //SAVE BUTTON FUNCTION
    protected void saveCity() {
        String ID = idTextField.getText();
        String Name = nameTextField.getText();
        String Countrycode = countryCodeTextField.getText();
        String District = districtTextField.getText();
        String Population = populationTextField.getText();

        City tempCity = new City(Integer.parseInt(ID), Name, Countrycode, District, Integer.parseInt(Population));

        try{
            //SAVE THE DATABASE
            cityDAO.addCity(tempCity);

            //CLOSE DIALOG BOX
            setVisible(false);
            dispose();

            //REFRESH THE LIST
            citySearchApp.refreshCities();

            //SHOW SUCCESS MESSAGE
            JOptionPane.showMessageDialog(citySearchApp,
                        "City added successfully.",
                        "City added", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {                           //OR SHOW ERROR MESSAGE IF ANY ERROR OCCURED
            JOptionPane.showMessageDialog(citySearchApp, "Error saving city: "
                                                + e.getMessage(), "Error",
                                                JOptionPane.ERROR_MESSAGE);
        }

    }




}
