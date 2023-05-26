package jdbcProject.DataAccess;
public class City {
    private int ID;
    private String Name;
    private String CountryCode;
    private String District;
    private int Population;



    public City(int ID, String Name, String CountryCode, String District, int Population) {
        super();
        this.ID = ID;
        this.Name = Name;
        this.CountryCode = CountryCode;
        this.District = District;
        this.Population = Population;


    }
    @Override
    public String toString() {
        return "City{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", District='" + District + '\'' +
                ", Population='" + Population + '\'' +
                '}';
    }
    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getDistrict() {
        return District;
    }

    public int getPopulation() {
        return Population;
    }


}
