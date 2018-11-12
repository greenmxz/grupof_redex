package Algoritmo;

public class Vuelo {
//    private static int count = 0;
//    private int identificator;
    private int originAirport;
    private int originHour;
    private int originMin;
    private int destinyAirport;
    private int destinyHour;
    private int destinyMin;
    
    public Vuelo(int originAirport, int originHour, 
            int originMin, int destinyAirport, int destinyHour, 
            int destinyMin){
//        this.identificator = count + 1;
        this.originAirport = originAirport;
        this.originHour = originHour;
        this.originMin = originMin;
        this.destinyAirport = destinyAirport;
        this.destinyHour = destinyHour;
        this.destinyMin = destinyMin;
        if((originHour*60 + originMin)>(destinyHour*60 + destinyMin)){
            this.destinyHour += 24;
        }
//        count++;
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
