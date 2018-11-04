package Algoritmo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataProcessing {
    private ArrayList<Aeropuerto> listAirport;
    private ArrayList<Vuelo> listFlight;
    private ArrayList<Paquete> listPack;
    private ArrayList<ArrayList<Integer>> flightMatrix = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Vuelo> listFlightSorted;
    
    public DataProcessing(){
        listAirport = new ArrayList<Aeropuerto>();
        listFlight = new ArrayList<Vuelo>();
        listPack = new ArrayList<Paquete>();
    }
    
    public ArrayList<Aeropuerto> getAirportList(){
        return listAirport;
    }
    
    public ArrayList<Vuelo> getFlightList(){
        return listFlight;
    }
    
    public ArrayList<Paquete> getPackList(){
        return listPack;
    }
    
    public ArrayList<ArrayList<Integer>> getFlightMatrix(){
        return flightMatrix;
    }
    
    public void inputData(String nameAirport, String nameFlight, String namePack){
        try{
            processAirport(nameAirport);
            processFlight(nameFlight);
            processPack(namePack);
            generateFlightMatrix();
            //printFlightMatrix();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the reading process! Check it!");
        }
    }
    
    public void processAirport(String nameAirport){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(nameAirport));
            String line;
            int continent = -1;
            boolean change = true;
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("\\s+");
                if(arr.length >=5 && change){
                    continent++;
                    change = false;
                }
                if(arr.length < 5 && !change){
                    change = true;
                }
                if(arr.length >= 5){
                    // Defining the official name of the airport
                    String nameAir = "";
                    for(int i=2; i<(arr.length-2);i++){
                        nameAir += arr[i];
                        if(i < arr.length - 3){
                            nameAir += " ";
                        }
                    }
                    Aeropuerto airpt = new Aeropuerto(Integer.parseInt(arr[0]), arr[1],
                            nameAir, arr[arr.length-2], arr[arr.length-2], continent);
                    //airpt.print();
                    listAirport.add(airpt);
                }
            }
            System.out.println("Airports' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the airports' reading process! Check it!");
        }
    }
    
    public void sortFlight(){
        listFlightSorted = (ArrayList<Vuelo>)listFlight.clone();
        Collections.sort(listFlightSorted, new Comparator<Vuelo>() {
            @Override
            public int compare(Vuelo flight1, Vuelo flight2)
            {
                Integer comp1 = flight1.getOriginHour()*60 + flight1.getOriginMin();
                Integer comp2 = flight2.getOriginHour()*60 + flight2.getOriginMin();
                return comp1.compareTo(comp2);
            }
        });
    }
    
    public void processFlight(String nameFlight){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(nameFlight));
            String line;
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("-");
                if(arr.length == 4){
                    Vuelo plannedFlg = new Vuelo(searchAirportId(arr[0]),
                            Integer.parseInt(arr[2].split(":")[0]),
                            Integer.parseInt(arr[2].split(":")[1]),
                            searchAirportId(arr[1]), Integer.parseInt(arr[3].split(":")[0]),
                            Integer.parseInt(arr[3].split(":")[1]));
                    //plannedFlg.print();
                    listFlight.add(plannedFlg);
                }
            }
            sortFlight();
            System.out.println("Flights' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the flights' reading process! Check it!");
        }
    }
    
 public void processPack(String namePack){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(namePack));
            String line;
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("-");
                if(arr[1].equals("20180418"))
                    break;
                if(arr.length == 4){
                    Paquete plannedPack = new Paquete(Integer.parseInt(arr[2].split(":")[0]),
                            Integer.parseInt(arr[2].split(":")[1]),
                            searchAirportId("SKBO"), searchAirportId(arr[3]));
                    //plannedPack.print();
                    listPack.add(plannedPack);
                }
            }
            System.out.println("Packs' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the flights' reading process! Check it!");
        }
    }
    
    public int searchAirportId(String icaoCode){
        for(int i=0; i<listAirport.size(); i++){
            if(listAirport.get(i).getIcaoCode().equals(icaoCode)){
                return listAirport.get(i).getIdentificator();
            }
        }
        return -1;
    }
    
    public void generateFlightMatrix(){
        /* For each airport, we'll search the fights which have that airport
         * as the origin airport */
        try{
            for(int i=0; i<listAirport.size();i++){
                // Create a ArrayList
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for(int j=0; j<listFlight.size();j++){
                    // Assign the relevant elements
                    if(listAirport.get(i).getIdentificator() == listFlight.get(j).getOriginAirport()){
                        temp.add(listFlight.get(j).getIdentificator());
                    }
                }
                flightMatrix.add(temp);
            }
            System.out.println("Flights' matrix generated process successful!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the flights' matrix generated process! Check it!");
        }
    }
    
    public void printFlightMatrix(){
        for(int i=0; i<listAirport.size(); i++){
            System.out.println(flightMatrix.get(i).size());
            for(int j=0; j<flightMatrix.get(i).size(); j++){
                System.out.print(flightMatrix.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }
    
    public void printPackList(){
        for(int i=0; i<listPack.size(); i++){
            System.out.println(String.valueOf(listPack.get(i).getOriginAirport()) + "->" +
                    String.valueOf(listPack.get(i).getDestinyAirport()) + " " +
                    String.valueOf(listPack.get(i).getOriginHour()) + ":" +
                    String.valueOf(listPack.get(i).getOriginMin()));
        }
    }
    
    public int getTrackTime(Vuelo evalFlight){
        int timeOrigin = evalFlight.getOriginHour()*60 + evalFlight.getOriginMin();
        int timeDestiny = evalFlight.getDestinyHour()*60 + evalFlight.getDestinyMin();
        if(timeDestiny > timeOrigin)
            return (timeDestiny - timeOrigin);
        else
            return -1;
    }
    
    public int getWaitTime(Vuelo currentFlight, Vuelo pastFlight){
        int timeDeparture = currentFlight.getOriginHour()*60 + currentFlight.getOriginMin(); //07
        int timeArrival = pastFlight.getDestinyHour()*60 + pastFlight.getDestinyMin();  //23
        if(timeDeparture > timeArrival)
            return (timeDeparture - timeArrival);
        else
            return -1;
    }
    
    public ArrayList<Integer> searchOriginList(int idSearch){       
        return flightMatrix.get(idSearch-1);
    }
    
    public void printListFlight(){
        for(Vuelo i : listFlight){
            i.print();
        }
    }
    
    public int getFormatHour(int hour, int min){
        return hour*60 + min;        
    }
}
