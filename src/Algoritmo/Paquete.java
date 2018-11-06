package Algoritmo;

public class Paquete {
//    private static int count = 0;
//    private int identificator;
    private int originHour;
    private int originMin;
    private int originAirport;
    private int destinyAirport;
    
    public Paquete(int originHour, int originMin, int originAirport,
            int destinyAirport){
        this.originHour = originHour;
        this.originMin = originMin;
        this.originAirport = originAirport;
        this.destinyAirport = destinyAirport;
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
    
    public int getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(int originAirport) {
        this.originAirport = originAirport;
    }
    
    public void print(){
        System.out.println("Pack " + " $ " +
                String.valueOf(this.getOriginHour()) + ":" +
                String.valueOf(this.getOriginMin()) + " route " +
                String.valueOf(this.getOriginAirport()) + " -> " +
                String.valueOf(this.getDestinyAirport()));
    }
    
}
