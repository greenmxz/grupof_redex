package Algoritmo;

import Modelo.*;
import Modelo.paquete;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

public class DataProcessing {
    private ArrayList<Aeropuerto> listAirport;
    private static int cont =0;
    public ArrayList<Aeropuerto> getListAirport() {
        return listAirport;
    }

    public void setListAirport(ArrayList<Aeropuerto> listAirport) {
        this.listAirport = listAirport;
    }
    private ArrayList<Vuelo> listFlight;
    
    private ArrayList<aeropuerto> listAirportSimu;
    private ArrayList<avionDot> listFlightSimu;
    
    private ArrayList<Paquete> listPack;
    private ArrayList<ArrayList<Paquete>> listPackXDay =  new ArrayList<ArrayList<Paquete>>();
    private ArrayList<ArrayList<ArrayList<Paquete>>> matrixPackXDay = new ArrayList<ArrayList<ArrayList<Paquete>>>();
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
//            processPack(namePack);
            //new
            File f = new File(namePack);
            String[] fileList = f.list();
            for(String str : fileList){
                 processPackNew(namePack + "/" + str);
                //processPackNew(namePack + "\\" + str);
            }
            ///new
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
                String[] arr = line.split("/s+");
                //String[] arr = line.split("\\s+");
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
//                    plannedFlg.print();
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
        ArrayList<Paquete> aux = new ArrayList<Paquete>();
        String backslash = "/";
       // String backslash = "\\";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(namePack));
            String line;
            String identificator = namePack.split(Pattern.quote(backslash))
                    [namePack.split(Pattern.quote(backslash)).length - 1]
                    .split("_")[2].substring(0, 4);
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("-");
                if(arr[1].equals("20180418"))
                    break;
                if(arr.length == 4){
                    Paquete plannedPack = new Paquete(Integer.parseInt(arr[2].split(":")[0]),
                            Integer.parseInt(arr[2].split(":")[1]),
                            searchAirportId(identificator), searchAirportId(arr[3]),
                            Integer.valueOf(arr[1].substring(6, 8)),
                            Integer.valueOf(arr[1].substring(4, 6)),
                            Integer.valueOf(arr[1].substring(0, 4)));
                    //plannedPack.print();
                    listPack.add(plannedPack);
                }
            }
            Collections.sort(listPack, new Comparator<Paquete>() {
            @Override
            public int compare(Paquete pk1, Paquete pk2)
            {
                if(pk1.getOriginYear() > pk2.getOriginYear())
                    return 1;
                else if (pk1.getOriginYear() < pk2.getOriginYear())
                    return -1;
                else{
                    if(pk1.getOriginMonth() > pk2.getOriginMonth())
                        return 1;
                    else if(pk1.getOriginMonth() < pk2.getOriginMonth())
                        return -1;
                    else{
                        if(pk1.getOriginDay() > pk2.getOriginDay())
                            return 1;
                        else if(pk1.getOriginDay() < pk2.getOriginDay())
                            return -1;
                        else{
                            if(pk1.getOriginHour() > pk2.getOriginHour())
                                return 1;
                            else if(pk1.getOriginHour() < pk2.getOriginHour())
                                return -1;
                            else{
                                if(pk1.getOriginMin() > pk2.getOriginMin())
                                    return 1;
                                else if(pk1.getOriginMin() < pk2.getOriginMin())
                                    return -1;
                                else return 0;
                            }
                        }
                    }
                }
            }
            });
//            System.out.println("Mrgmr");
//            for(int i=0; i<listPack.size();i++){
//                listPack.get(i).print();
//            }
//            System.out.println("Packs' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the flights' reading process! Check it!");
        }
    }

 
  public void processPackNew(String namePack){
      String backslash = "/";
        //String backslash = "\\";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(namePack));
            String line;
            String identificator = namePack.split(Pattern.quote(backslash))
                    [namePack.split(Pattern.quote(backslash)).length - 1]
                    .split("_")[2].substring(0, 4);
            listPack.clear();
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("-");
                
                if(arr.length == 4){
                    Paquete plannedPack = new Paquete(Integer.parseInt(arr[2].split(":")[0]),
                            Integer.parseInt(arr[2].split(":")[1]),
                            searchAirportId(identificator), searchAirportId(arr[3]),
                            Integer.valueOf(arr[1].substring(6, 8)),
                            Integer.valueOf(arr[1].substring(4, 6)),
                            Integer.valueOf(arr[1].substring(0, 4)));
                    //plannedPack.print();
                    listPack.add(plannedPack);
                }
            }
            
            Collections.sort(listPack, new Comparator<Paquete>() {
            @Override
            public int compare(Paquete pk1, Paquete pk2)
            {
                if(pk1.getOriginYear() > pk2.getOriginYear())
                    return 1;
                else if (pk1.getOriginYear() < pk2.getOriginYear())
                    return -1;
                else{
                    if(pk1.getOriginMonth() > pk2.getOriginMonth())
                        return 1;
                    else if(pk1.getOriginMonth() < pk2.getOriginMonth())
                        return -1;
                    else{
                        if(pk1.getOriginDay() > pk2.getOriginDay())
                            return 1;
                        else if(pk1.getOriginDay() < pk2.getOriginDay())
                            return -1;
                        else{
                            if(pk1.getOriginHour() > pk2.getOriginHour())
                                return 1;
                            else if(pk1.getOriginHour() < pk2.getOriginHour())
                                return -1;
                            else{
                                if(pk1.getOriginMin() > pk2.getOriginMin())
                                    return 1;
                                else if(pk1.getOriginMin() < pk2.getOriginMin())
                                    return -1;
                                else return 0;
                            }
                        }
                    }
                }
            }
            });
            
            if (listPack.size() > 0){
                ArrayList<Paquete> aux = new ArrayList<Paquete>();
                int day = listPack.get(0).getOriginDay();
                
                for (Paquete p : listPack){        
                    if (listPack.size()==1){
                        aux.add(p);
                         listPackXDay.add(aux);
                    }else{
                        if (p.getOriginDay() == day){
                            aux.add(p);
                        }else{
                            day = p.getOriginDay();
                            listPackXDay.add(aux);
                            aux = new ArrayList<Paquete>();
                            aux.add(p);
                        }
                    }
                    
                }
                matrixPackXDay.add(listPackXDay);
                listPackXDay = new ArrayList<ArrayList<Paquete>>();
            }
            
            cont ++;
//            System.out.println("Packs' reading process successful!");
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
                        temp.add(j+1);
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
//        System.out.println(String.valueOf(timeOrigin) + " " + String.valueOf(timeDestiny));
        if(timeDestiny > timeOrigin){
            if(listAirport.get(evalFlight.getOriginAirport()-1).getContinent() == 
                    listAirport.get(evalFlight.getDestinyAirport()-1).getContinent()) // Es continental
                return (timeDestiny - timeOrigin);
            else return (timeDestiny - timeOrigin + 1440);
        }
        else
            return (timeDestiny - timeOrigin + 1440);
    }
    
    public int getWaitTime(Vuelo currentFlight, Vuelo pastFlight){
        int timeDeparture = currentFlight.getOriginHour()*60 + currentFlight.getOriginMin(); //07
        int timeArrival = pastFlight.getDestinyHour()*60 + pastFlight.getDestinyMin();  //23
//        System.out.println(String.valueOf(timeDeparture) + " " + String.valueOf(timeArrival));
        if(timeDeparture > timeArrival)
            return (timeDeparture - timeArrival);
        else
            return (timeDeparture - timeArrival + 1440);
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

    public ArrayList<ArrayList<Paquete>> getListPackXDay() {
        return listPackXDay;
    }

    public void setListPackXDay(ArrayList<ArrayList<Paquete>> listPackXDay) {
        this.listPackXDay = listPackXDay;
    }

    public ArrayList<ArrayList<ArrayList<Paquete>>> getMatrixPackXDay() {
        return matrixPackXDay;
    }

    public void setMatrixPackXDay(ArrayList<ArrayList<ArrayList<Paquete>>> matrixPackXDay) {
        this.matrixPackXDay = matrixPackXDay;
    }
    
}
