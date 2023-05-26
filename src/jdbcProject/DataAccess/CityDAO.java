package jdbcProject.DataAccess;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CityDAO {

    private City convertRowToCity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String name = resultSet.getString("Name");
        String countryCode = resultSet.getString("CountryCode");
        String district = resultSet.getString("District");
        int population = resultSet.getInt("Population");

        return new City(id, name, countryCode, district, population);
    }
    private Connection connection;

    public CityDAO() throws Exception {

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


    //SQL SELECT * STATEMENT
    public List<City> getAllCities() throws Exception {
        Statement statement = null;
        ResultSet resultSet = null;
        List<City> list = new ArrayList<>();

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


    //SQL SEARCH STATEMENT
    public List<City> searchCities(String NameForSearch) throws Exception {
        List<City> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            NameForSearch += "%";
            preparedStatement = connection.prepareStatement("SELECT * FROM city WHERE Name LIKE ?");
            preparedStatement.setString(1, NameForSearch);
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


    //SQL INSERT STATEMENT
    public void addCity(City aCity) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO city" + " (ID, Name, CountryCode, District, Population)"
            + " values (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, aCity.getID());
            preparedStatement.setString(2, aCity.getName());
            preparedStatement.setString(3, aCity.getCountryCode());
            preparedStatement.setString(4, aCity.getDistrict());
            preparedStatement.setInt(5, aCity.getPopulation());

            preparedStatement.executeUpdate();

        }
        finally {
            preparedStatement.close();
        }
        return;
    }


    //SQL DELETE STATEMENT
    public List<City> deleteCity(int IDToDelete) throws Exception {
        List<City> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM city WHERE ID ?");
            preparedStatement.setInt(1, IDToDelete);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                City tempCity = convertRowToCity(resultSet);
                list.add(tempCity);
            }
            return list;

        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

}
