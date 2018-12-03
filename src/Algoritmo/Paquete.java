package Algoritmo;

public class Paquete {

//    private static int count = 0;
    private int identificator;
    private int originHour;
    private int originMin;
    private int originAirport;
    private int destinyAirport;
    private int originDay;
    private int originMonth;
    private int originYear;
    private String ruta = "";
    private int esFinal = 0; // 0: tiene varios pasos, 1: solo le falta un paso
    private int estado = 0; // 0: no disponible, 1: en espera, 2: en transito
    private int nuevo = 1;
    
    public Paquete(int originHour, int originMin, int originAirport,
            int destinyAirport){
        this.originHour = originHour;
        this.originMin = originMin;
        this.originAirport = originAirport;
        this.destinyAirport = destinyAirport;
    }

    public Paquete(int originHour, int originMin, int originAirport,
            int destinyAirport, int originDay, int originMonth,
            int originYear){
        this.originHour = originHour;
        this.originMin = originMin;
        this.originAirport = originAirport;
        this.destinyAirport = destinyAirport;
        this.originDay = originDay;
        this.originMonth = originMonth;
        this.originYear = originYear;
    }

    public int getIdentificator() {
        return identificator;
    }

    public void setIdentificator(int identificator) {
        this.identificator = identificator;
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
    
    public int getOriginDay() {
        return originDay;
    }

    public void setOriginDay(int originDay) {
        this.originDay = originDay;
    }

    public int getOriginMonth() {
        return originMonth;
    }

    public void setOriginMonth(int originMonth) {
        this.originMonth = originMonth;
    }

    public int getOriginYear() {
        return originYear;
    }

    public void setOriginYear(int originYear) {
        this.originYear = originYear;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public void print(){
        System.out.println("Pack " + " $ " +
                String.valueOf(this.getOriginHour()) + ":" +
                String.valueOf(this.getOriginMin()) + " route " +
                String.valueOf(this.getOriginAirport()) + " -> " +
                String.valueOf(this.getDestinyAirport()));
    }

    public int getEsFinal() {
        return esFinal;
    }

    public void setEsFinal(int esFinal) {
        this.esFinal = esFinal;
    }

    public int getNuevo() {
        return nuevo;
    }

    public void setNuevo(int nuevo) {
        this.nuevo = nuevo;
    }
    
}
