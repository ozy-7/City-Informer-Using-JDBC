package jdbcProject.DataAccess;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GeneralDataAccessObject {
    private Connection connection;

    public GeneralDataAccessObject() throws Exception {

        //GET DB PROPERTIES
        Properties properties = new Properties();
        properties.load(new FileInputStream("DBProperties.txt"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String dburl = properties.getProperty("dburl");

        //CONNECT TO DATABASE
        connection = DriverManager.getConnection(dburl, user, password);
        System.out.println("DB connection successful to: " + dburl);
    }

    public List<City> getAllCities() throws Exception {
        List<City> list = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM city;");

            while (resultSet.next()) {
                City tempCity = convertRowToCity(resultSet);
                list.add(tempCity);
            }
            return list;
        }
        finally {
            statement.close();
            resultSet.close();
        }
    }

    public List<City> searchCities(String Name) throws Exception {
        List<City> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Name += "%";
            preparedStatement = connection.prepareStatement("SELECT * FROM city WHERE Name LIKE ?");
            preparedStatement.setString(1, Name);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                City tempCity = convertRowToCity(resultSet);
                list.add(tempCity);
            }
            return list;
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }
    }







    private City convertRowToCity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String name = resultSet.getString("Name");
        String countryCode = resultSet.getString("CountryCode");
        String district = resultSet.getString("District");
        int population = resultSet.getInt("Population");

        return new City(id, name, countryCode, district, population);
    }


}
