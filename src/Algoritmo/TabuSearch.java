package Algoritmo;

import Controlador.PaqueteBL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.time.LocalTime;

public class TabuSearch {
    private ArrayList<Aeropuerto> listAirport;
    private ArrayList<Vuelo> listFlight;
    private ArrayList<Paquete> listPack;
    private ArrayList<ArrayList<Integer>> flightMatrix;
    DataProcessing inputProcess = new DataProcessing();
    private ArrayList<String> tabuString;
    private ArrayList<Integer> routeOptimal;
    private int numAirport;
    private int numFlight;
    private int numInt = 1000;
    private int originId;
    private int destinyId;
    private int limit; // 2880 = intercontinental, 1440 = no
    private int hourBegin;
    private boolean finded = false;
    private ArrayList<String> asignedPacks;
    private Date fechaInicio;
    private LocalTime fechaSim;
    private PaqueteBL paqueteBL;
    private int timeToCmp;
    
    public ArrayList<Integer> getRouteOptimal(){
        return routeOptimal;
    }
    
    public void inputData(String nameAirport, String nameFlight, String namePack){
        inputProcess.inputData(nameAirport, nameFlight, namePack);
        listAirport = inputProcess.getAirportList();
        listFlight = inputProcess.getFlightList();
        //listPack = new ArrayList<Paquete>();
        listPack = inputProcess.getPackList();
        paqueteBL = new PaqueteBL();
        flightMatrix = inputProcess.getFlightMatrix();
        routeOptimal = new ArrayList<Integer>();
        numAirport = listAirport.size();
        numFlight = listFlight.size();
        tabuString = new ArrayList<String>();
        asignedPacks = new ArrayList<String>();
    }
    
    public boolean validator(String codeOrigin, String codeDestiny){
        return ((inputProcess.searchAirportId(codeOrigin) != -1) && 
                (inputProcess.searchAirportId(codeDestiny) != -1));
    }
    
    public ArrayList<int[]> executeVCRPTabu(){
        ArrayList<int[]> aux = new ArrayList<int[]>();
        for(int iter=0; iter<listPack.size(); iter++){
            String origin = listAirport.get(listPack.get(iter).getOriginAirport()-1).getIcaoCode();
            String destiny = listAirport.get(listPack.get(iter).getDestinyAirport()-1).getIcaoCode();
            listPack.get(iter).print();
            if(validator(origin, destiny)){
                String time = String.valueOf(listPack.get(iter).getOriginHour()) + ":" + 
                        String.valueOf(listPack.get(iter).getOriginMin());
                tabuAlgorithm(origin, destiny, time);
                ArrayList<Integer> solution = getRouteOptimal();
                int[] sol = new int[solution.size()];
                int ii=0;
                for(int i : solution){
                    sol[ii] = i;
                    ii++;
                }
                aux.add(sol);
//                ArrayList<String> stringSol = getAirportsRouteICAO();
//                for(String i : stringSol){
//                    if(!stringSol.get(0).equals(i))
//                        System.out.print(" ->");
//                    System.out.print(i);
//                }
//                System.out.println(" \n");
            }else{
                System.out.println("Some airport doesn't exist!");
            }
        }
        return aux;
    }
    
    public void tabuAlgorithm(String codeOrigin, String codeDestiny, String hourBegin){
        this.originId = inputProcess.searchAirportId(codeOrigin);
        this.destinyId = inputProcess.searchAirportId(codeDestiny);
        tabuString = new ArrayList<String>();
        routeOptimal = new ArrayList<Integer>();
        if(listAirport.get(originId-1).getContinent() ==
                listAirport.get(destinyId-1).getContinent()){
            this.limit = 1440;
        } else this.limit = 2880;
//        System.out.println("Límite "+String.valueOf(this.limit));
        this.hourBegin = inputProcess.getFormatHour(Integer.valueOf(hourBegin.split(":")[0]),
                Integer.valueOf(hourBegin.split(":")[1]));
        int[] solution = solve();
        for(int i : solution){
            if(i == -1) break;
            routeOptimal.add(Integer.valueOf(i));
        }
    }
    
    public int isSolution(int[] route){
        int lastValid = getLastMinusOne(route);
        ArrayList<Integer> routeAL = new ArrayList<Integer>();
        if(route[0] == -1)
            return 0;
        for(int i : route){
            if(i == -1) break;
            routeAL.add(i);
        }
        int lenghtRoute = getRouteLenght(routeAL);
        int destiny = listFlight.get(route[lastValid-1]-1).getDestinyAirport();
        if((lastValid > 0) && (lenghtRoute <= this.limit) && (destiny == this.destinyId)){
            return 1;
        }
        return 0;
    }
    
    public int overload(int[] route){
        if(route[0] == -1)
            return 0;
        ArrayList<Integer> auxRoute = new ArrayList<Integer>();
        for(int i : route){
            if(i == -1) break;
            auxRoute.add(i);
        }
        int recorrido = getRouteLenght(auxRoute);
        if(recorrido <= limit){
            return 0;
        }
        return 1;
    }
    
    private int[] solve(){
        int[] route = generateInitialRoute();
        int[] bestRoute = generateInitialRoute();
        // Determinate solution
        bestRoute = findBestSolution(this.numInt, this.tabuString, route);
        return bestRoute;
    }

    private int[] generateInitialRoute(){
        int[] solution = new int[numAirport-1];
        for(int i=0; i<(numAirport-1);i++){
            solution[i] = -1;
        }
        return solution;
    }
    
    private void printArrayList(ArrayList<Integer> arr){
        for(int i=0; i<arr.size(); i++)
            System.out.print(String.valueOf(arr.get(i)) + " -> ");
        System.out.println();
    }
    
    private int[] findBestSolution(int numLoop, 
            ArrayList<String> tabuString, int[] route){
        int[] bestRoute = route.clone();
        for(int i=0; i<numLoop; i++){
            int[] auxBestRoute = localSearch(tabuString, bestRoute);
            if(auxBestRoute[0] == -1) break;
            if(auxBestRoute[0] == -10) continue;
            else bestRoute = auxBestRoute.clone();
        }
        return bestRoute;
    }
    
    private int getLastMinusOne(int[] arr){
        int i=0;
        for(i=0; i<arr.length; i++){
            if(arr[i] == -1) break;
        }
        return i;
    }
    
    private int obtainStandardHour(Vuelo plannedFlg, char typeQuery){
        if(typeQuery == 'L'){
            if(plannedFlg.getDestinyHour() < plannedFlg.getOriginHour()){
                return (plannedFlg.getDestinyHour()+24) * 60 + plannedFlg.getDestinyMin();
            }else
                return plannedFlg.getDestinyHour() * 60 + plannedFlg.getDestinyMin();
        }else{
            return plannedFlg.getOriginHour() * 60 + plannedFlg.getOriginMin();
        }
    }
    
    private int[] obtainNeighbor(int currentElement,
            int[] route){
        int actualNode = this.originId - 1;
        if(currentElement > -1){
            actualNode = (listFlight.get(route[currentElement]-1).getDestinyAirport())-1;
        }
        int timeToCmp = this.hourBegin, cmpTime = 0;
        
        ArrayList<Integer> listNeighborAL = new ArrayList<Integer>();
        listNeighborAL =
                (ArrayList<Integer>) flightMatrix.get(actualNode).clone();
        Collections.sort(listNeighborAL, new Comparator<Integer>() {
            @Override
            public int compare(Integer flight1, Integer flight2)
            {
                Integer comp1 = listFlight.get(flight1-1).getOriginHour()*60 +
                        listFlight.get(flight1-1).getOriginMin();
                Integer comp2 = listFlight.get(flight2-1).getOriginHour()*60 +
                        listFlight.get(flight2-1).getOriginMin();
                return comp1.compareTo(comp2);
            }
        });
        int[] listNeighbor = new int[listNeighborAL.size()];
        int[] listInferior = new int[listNeighborAL.size()];
        int[] listSuperior = new int[listNeighborAL.size()];
        for(int i=0; i<listNeighborAL.size(); i++){
            listNeighbor[i] = -1;
            listInferior[i] = -1;
            listSuperior[i] = -1;
        }
        if(getLastMinusOne(route) > 0){
            /* Destiny */
            timeToCmp = obtainStandardHour(listFlight.get(route[getLastMinusOne(route)-1]-1),'L');
        }
        int iSup = 0, iInf = 0;
        for(int i=0; i<listNeighbor.length; i++){
            /* Origin */
            cmpTime = obtainStandardHour(listFlight.get(listNeighborAL.get(i)-1),'P');
            
            if(cmpTime > timeToCmp){
                listSuperior[iSup] = listNeighborAL.get(i);
                iSup++;
            }else{
                listInferior[iInf] = listNeighborAL.get(i);
                iInf++;
            }
        }
        int iGen = 0;
//        System.out.println("INICIO");
        for(int i=0; i<listSuperior.length; i++){
            if(listSuperior[i] == -1) break;
            listNeighbor[iGen] = listSuperior[i];
            iGen++;
//            System.out.println(String.valueOf(listFlight.get(listSuperior[i]-1).getOriginHour()) +
//                    ":" + listFlight.get(listSuperior[i]-1).getOriginMin());
        }
        for(int i=0; i<listInferior.length; i++){
            if(listInferior[i] == -1) break;
            listNeighbor[iGen] = listInferior[i];
            iGen++;
//            System.out.println(String.valueOf(listFlight.get(listInferior[i]-1).getOriginHour()) +
//                    ":" + listFlight.get(listInferior[i]-1).getOriginMin());
        }
//        System.out.println("FIN");
        return listNeighbor;
    }
    
    public String generateTabuString(int[] solution){
        String resultado = "";
        for(int i=0; i<solution.length; i++){
            if(solution[i] == -1) break;
            if(i>0)
                resultado += "-" + String.valueOf(solution[i]);
            else
                resultado += String.valueOf(solution[i]);
        }
        return resultado;
    }
    
    public int isBanned(ArrayList<String> tabuString, int[] solution){
        String searchString = generateTabuString(solution);
        for(int i=0; i<tabuString.size(); i++){
            if(searchString.equals(tabuString.get(i)))
                return 1;
        }
        return 0;
    }
    
    private int[] localSearch(ArrayList<String> tabuString,
            int[] originalRoute){
        
        int bestFitness = 0;
        int[] bestRoute = originalRoute.clone();
        int[] listNeighbor;
        int[] neighborRoute = generateInitialRoute();
        
        if(!finded)
            bestFitness = (int) Integer.MAX_VALUE;
        else{
            finded = false;
            ArrayList<Integer> auxBestRoute = new ArrayList<Integer>();
            for(int i=0; i<bestRoute.length; i++){
                if(bestRoute[i] == -1) break;
                auxBestRoute.add(bestRoute[i]);
            }
            bestFitness = (int) getRouteLenght(auxBestRoute);
        }
        try{
            listNeighbor = obtainNeighbor(-1, neighborRoute); // Routes
            neighborRoute = dfs(neighborRoute, listNeighbor, 1, tabuString);
            // Get fitness of local
            if(neighborRoute[0] != -1){
                ArrayList<Integer> nSolution = new ArrayList<Integer>();
                for(int iter : neighborRoute){
                    if(iter == -1) break;
                    nSolution.add(Integer.valueOf(iter));
                }

                int costoActual=getRouteLenght(nSolution);
                if((costoActual > 0) && (costoActual<bestFitness)){
                    bestRoute = neighborRoute.clone();
                    bestFitness=costoActual;
                }else if(costoActual > 0){
                    return bestRoute;
                }
            }else{
                int[] voider = new int[1];
                voider[0] = -1;
                return voider;
            }
        }catch(Exception e){
            System.out.println("Error abc" + e.getMessage());
            return originalRoute;
        }
        System.out.print("Llegué con ");
        printArray(bestRoute);
        /* Introduce best movement in tabu string*/
        String newTabu = generateTabuString(bestRoute);
        tabuString.add(newTabu);
        System.out.println("FIN");
        return bestRoute;
    }
    
    private void printArray(int[] arr){
        for(int i : arr)
            System.out.print(String.valueOf(i) + " -> ");
        System.out.println();
    }
    
    private int[] dfs(int[] route, int[] neighborList, int currentLevel, ArrayList<String> tabuString){
	if((route.equals(generateInitialRoute()) == false) && (isSolution(route) == 1)
                && (isBanned(tabuString, route) == 0)){
            this.finded = true;
            return route;
        }
	else if ((currentLevel > 1) && (neighborList.length == 0 || overload(route) == 1)){
            /* Entra si la ruta elegida está "sobrecargada" */
            String newTabu = generateTabuString(route);
            tabuString.add(newTabu);
            return generateInitialRoute();
        }
	else{
            int[] auxNeighborList = neighborList.clone();
            int[] auxRoute = route.clone();
            if(currentLevel > 1)
                auxNeighborList = obtainNeighbor((currentLevel-2), auxRoute);
            if(getLastMinusOne(auxNeighborList) == 0){
                String newTabu = generateTabuString(auxRoute);
                tabuString.add(newTabu);
                return generateInitialRoute();
            }
            for(int i=0; i<getLastMinusOne(auxNeighborList); i++){
                auxRoute[currentLevel-1] = auxNeighborList[i]; //error
//                printArray(auxRoute);
                for(int iter = currentLevel; iter<auxRoute.length; iter++)
                    auxRoute[iter] = -1;
                if(overload(auxRoute) == 1){
                    auxRoute[currentLevel-1] = -1;
                    continue;
                }
                auxRoute = dfs(auxRoute, auxNeighborList, currentLevel+1, tabuString);
                if(auxRoute[0] == -1){
                    auxRoute = route.clone();
                    for(int iter = currentLevel; iter<auxRoute.length; iter++)
                        auxRoute[iter] = -1;
                    continue;
                }
                if (this.finded){
                    return auxRoute;
                }
                else if(auxRoute.equals(generateInitialRoute()))
                    auxRoute[currentLevel-1] = -1;
                    continue;
            }
            String newTabu = generateTabuString(route);
            tabuString.add(newTabu);
            return generateInitialRoute();
	}
    }
    
    public int getLenghtOptimal(){
        return getRouteLenght(routeOptimal);
    }
    
    public int getRouteLenght(ArrayList<Integer> route){
        if(route.isEmpty()){
            return 0;
        }
        int sum = 0;
        int[] auxRoute = new int[route.size()];
        for(int i = 0; i < route.size(); i++){
            auxRoute[i] = route.get(i);
        }
        int limite = getLastMinusOne(auxRoute);
        for(int i = 0; i<limite; i++){
            int flight = route.get(i);
            int trackTime = inputProcess.getTrackTime(listFlight.get(flight-1));
            if(trackTime == -1)
                return (this.limit + 1);
            int waitTime = 0;
            if(i>0){
                int pastFlight = route.get(i-1);
                waitTime = inputProcess.getWaitTime(listFlight.get(flight-1),
                        listFlight.get(pastFlight-1));
            }else{
                waitTime = inputProcess.getFormatHour(listFlight.get(flight-1).getOriginHour(),
                        listFlight.get(flight-1).getOriginMin()) - this.hourBegin;
            }
            sum += trackTime + waitTime;
        }
        return sum;
    }
    
    public int getRouteLenght(int[] route){
        ArrayList<Integer> auxRoute = new ArrayList<Integer>();
        for(int i=0; i<route.length; i++){
            if(route[i] == -1) break;
            auxRoute.add(route[i]);
        }
        return getRouteLenght(auxRoute);
    }
    
    public void printListFlight(){
        inputProcess.printListFlight();
    }
    
    public void printPackList(){
        inputProcess.printPackList();
    }
    
    public ArrayList<String> getAirportsRouteICAO(){
        ArrayList<String> aux = new ArrayList<String>();
        for(int i : routeOptimal){
            if(routeOptimal.get(0) == i)
                aux.add(listAirport.get(listFlight.get(i-1).getOriginAirport()-1).getIcaoCode());
            aux.add(listAirport.get(listFlight.get(i-1).getDestinyAirport()-1).getIcaoCode());
        }
        return aux;
    }
}