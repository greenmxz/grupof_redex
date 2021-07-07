package Algoritmo;

public class Aeropuerto {

    private int identificator;
    private String icaoCode;
    private String officialName;
    private String country;
    private String cityId;
    private int continent;
    private int capMax;
    private int capActual;
    private double coordX;
    private double coordY;
    private String color;
    
     public Aeropuerto(){
                 capMax = 600;
        capActual = 0;
    }
    public Aeropuerto(int identificator, String icaoCode, String officialName, 
            String country, String cityId, int continent){
        this.identificator = identificator;
        this.icaoCode = icaoCode;
        this.officialName = officialName;
        this.country = country;
        this.cityId = cityId;
        this.continent = continent;
        capMax = 600;
        capActual = 0;
    }

    /* Getters & setters */
    public int getIdentificator() {
        return identificator;
    }

    public void setIdentificator(int identificator) {
        this.identificator = identificator;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    
    public int getContinent() {
        return continent;
    }

    public void setContinent(int continent) {
        this.continent = continent;
    }
    
    public int getCapMax() {
        return capMax;
    }

    public void setCapMax(int capMax) {
        this.capMax = capMax;
    }

    public int getCapActual() {
        return capActual;
    }

    public void setCapActual(int capActual) {
        this.capActual = capActual;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }    
    
    /* Methods */
    public void print(){
        System.out.print("Airport " + String.valueOf(this.getOfficialName()) +
                " (" + this.getIcaoCode() + ") - Continent: ");
        if(this.continent == 0)
            System.out.println("América del Sur");
        else
            System.out.println("Europa");
    }
    
}