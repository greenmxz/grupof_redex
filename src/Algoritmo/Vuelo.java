package Algoritmo;

public class Vuelo {

    private int originAirport;
    private int originHour;
    private int originMin;
    private int destinyAirport;
    private int destinyHour;
    private int destinyMin;
    private int capMax;
    private int capActual;
    
    public Vuelo(int originAirport, int originHour, 
            int originMin, int destinyAirport, int destinyHour, 
            int destinyMin){
        this.originAirport = originAirport;
        this.originHour = originHour;
        this.originMin = originMin;
        this.destinyAirport = destinyAirport;
        this.destinyHour = destinyHour;
        this.destinyMin = destinyMin;
//        if((originHour*60 + originMin)>(destinyHour*60 + destinyMin)){
//            this.destinyHour += 24;
//        }
        capMax = 300;
        capActual = 0;
    }

    public int getOriginAirport() {
        return originAirport;
    }
    
    public void setOriginAirport(int originAirport) {
        this.originAirport = originAirport;
    }

    public int getOriginHour() {
        return originHour;
    }

    public void setOriginHour(int originHour) {
        this.originHour = originHour;
    }

    public int getOriginMin() {
        return originMin;
    }

    public void setOriginMin(int originMin) {
        this.originMin = originMin;
    }

    public int getDestinyAirport() {
        return destinyAirport;
    }

    public void setDestinyAirport(int destinyAirport) {
        this.destinyAirport = destinyAirport;
    }

    public int getDestinyHour() {
        return destinyHour;
    }

    public void setDestinyHour(int destinyHour) {
        this.destinyHour = destinyHour;
    }

    public int getDestinyMin() {
        return destinyMin;
    }

    public void setDestinyMin(int destinyMin) {
        this.destinyMin = destinyMin;
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
    
    /* Methods */
    public void print(){
        System.out.println("Flight " + ": " +
                String.valueOf(this.getOriginAirport()) +
                " to " + String.valueOf(this.getDestinyAirport()) + " -> " +
                String.valueOf(this.getOriginHour()) + ":" +
                String.valueOf(this.getOriginMin()) + " - " +
                String.valueOf(this.getDestinyHour()) + ":" +
                String.valueOf(this.getDestinyMin()));
    }
    
}
