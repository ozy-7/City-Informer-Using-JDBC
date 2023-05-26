package jdbcProject.DataAccess;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CityTableModel extends AbstractTableModel {

    private static final int ID_COL = 0;
    private static final int NAME_COL = 1;
    private static final int COUNTRY_CODE_COL = 2;
    private static final int DISTRICT_COL = 3;
    private static final int POPULATION_COL = 4;

    private String[] columns = {"ID", "Name", "Country Code", "District", "Population"};
    private List<City> cities;


    public CityTableModel(List<City> theCities) {
        cities = theCities;
    }

    public void setModel(CityTableModel newModel) {
        this.cities = newModel.cities;
        fireTableDataChanged();
    }




    @Override
    public int getRowCount() {
        return cities.size();
    }


    @Override
    public int getColumnCount() {
        return columns.length;
    }


    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    public Class getColumnClass (int c) {
        return  getValueAt(0, c).getClass();
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        City tempCity = cities.get(rowIndex);

        switch (columnIndex) {
            case ID_COL:
                return tempCity.getID();
            case NAME_COL:
                return tempCity.getName();
            case COUNTRY_CODE_COL:
                return tempCity.getCountryCode();
            case DISTRICT_COL:
                return tempCity.getDistrict();
            case POPULATION_COL:
                return tempCity.getPopulation();
            default:
                return tempCity.getName();
        }

    }
}
