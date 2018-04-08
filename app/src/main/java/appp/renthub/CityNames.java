package appp.renthub;

/**
 * Created by pranj on 08-04-2018.
 */

public class CityNames {
    public CityNames(String cityname) {
        this.cityname = cityname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String cityname;
    public String toString(){
        return cityname;
    }
}
