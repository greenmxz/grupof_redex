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
    private ArrayList<Integer> capVuelos;
    private int[] rutaRiesgo;
    private int numAirport;
    private int numFlight;
    private int numInt = 1;
    private int originId;
    private int destinyId;
    private int limit; // 2880 = intercontinental, 1440 = no
    private int hourBegin;
    private boolean finded = false;
    private int timeToCmp;
    private int cantReruteo = 0;
    private ArrayList<Integer> forbidden;
    private int intercont;

    public TabuSearch(){
        this.listPack=new ArrayList<Paquete>();
    }
    
    public TabuSearch(TabuSearch tabu) {
        this.listAirport = (ArrayList<Aeropuerto>)tabu.listAirport.clone();
        this.listFlight = (ArrayList<Vuelo>)tabu.listFlight.clone();
        int filas=this.flightMatrix.size();
        for(int f=0;f<filas;f++){
            this.flightMatrix.add((ArrayList<Integer>)tabu.flightMatrix.get(f).clone());
        }
        this.tabuString = tabu.tabuString;
        this.routeOptimal = tabu.routeOptimal;
        this.capVuelos = tabu.capVuelos;
        this.numAirport = tabu.numAirport;
        this.numFlight = tabu.numFlight;
        this.originId = tabu.originId;
        this.destinyId = tabu.destinyId;
        this.limit = tabu.limit;
        this.hourBegin = tabu.hourBegin;
        this.timeToCmp = tabu.timeToCmp;
        this.rutaRiesgo = generateInitialRoute();
    }
    
    
    
    public ArrayList<Integer> getRouteOptimal(){
        return routeOptimal;
    }

    public DataProcessing getInputProcess() {
        return inputProcess;
    }

    public void setInputProcess(DataProcessing inputProcess) {
        this.inputProcess = inputProcess;
    }
       /**
     * @return the listAirport
     */
    public ArrayList<Aeropuerto> getListAirport() {
        return listAirport;
    }

    /**
     * @param listAirport the listAirport to set
     */
    public void setListAirport(ArrayList<Aeropuerto> listAirport) {
        this.listAirport = listAirport;
    }

    /**
     * @return the listFlight
     */
    public ArrayList<Vuelo> getListFlight() {
        return listFlight;
    }

    /**
     * @param listFlight the listFlight to set
     */
    public void setListFlight(ArrayList<Vuelo> listFlight) {
        this.listFlight = listFlight;
    }
    
    public void inputData(String nameAirport, String nameFlight, String namePack){
        inputProcess.inputData(nameAirport, nameFlight, namePack);
//        setListAirport(inputProcess.getAirportList());
//        setListFlight(inputProcess.getFlightList());
    //new
        listAirport = inputProcess.getAirportList();
        listFlight = inputProcess.getFlightList();
    ///new
        setListPack(inputProcess.getPackList());
        routeOptimal = new ArrayList<Integer>();
        numAirport = getListAirport().size();
        numFlight = getListFlight().size();
        tabuString = new ArrayList<String>();
    }
    
        public void generateFlightMatrix(){
        /* For each airport, we'll search the fights which have that airport
         * as the origin airport */
        flightMatrix = new ArrayList<ArrayList<Integer>>();
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
    
    
    public boolean validator(int codeOrigin, int codeDestiny){
        return ((codeOrigin != -1) && 
                (codeDestiny != -1));
    }
    
    public ArrayList<String> executeVCRPTabu(ArrayList<Paquete> paquetesAct){
        ArrayList<String> aux = new ArrayList<String>();
        numAirport = getListAirport().size();
        capVuelos = new ArrayList<Integer>();
        forbidden = new ArrayList<Integer>();
        int numPack=paquetesAct.size();
        for(int i=0; i<listFlight.size(); i++)
            capVuelos.add(0);
        for(int i=0; i<listAirport.size(); i++)
            forbidden.add(15);
//        for(int i=0; i<listFlight.size(); i++)
//            listFlight.get(i).print();
        setListPack(paquetesAct);
        int noAsign = 0;

        for(int iter=0; iter<numPack; iter++){
            //NO APLICA ALGORITMO A LOS QUE SE ENCUENTRAN EN EL AIRE Y A LOS QUE YA NO NECESITAN
            if ((paquetesAct.get(iter).getEstado() == 0 || (paquetesAct.get(iter).getEstado() == 1 && paquetesAct.get(iter).getNuevo() == 0)) && paquetesAct.get(iter).getEsFinal() == 0){// SOLO PARA 0 o 1 y no es final
                int origin = paquetesAct.get(iter).getOriginAirport();
                int destiny = paquetesAct.get(iter).getDestinyAirport();

//                getListPack().get(iter).print();
                if(validator(origin, destiny)){
                    String time = String.valueOf(paquetesAct.get(iter).getOriginHour()) + ":" + 
                            String.valueOf(paquetesAct.get(iter).getOriginMin());
                    if(listAirport.get(this.originId-1).getContinent() == listAirport.get(this.destinyId-1).getContinent())
                        this.intercont = 0;
                    else this.intercont = 1;
                    tabuAlgorithm(origin, destiny, time);
                    ArrayList<Integer> optimal = getRouteOptimal();
                    if(optimal.size() > 0)
                        for(int i : optimal)
                            capVuelos.set(i-1, capVuelos.get(i-1)+1);
                    String solution = generateTabuString(optimal);
//                    System.out.println("Solution " + String.valueOf(iter) + ": " + solution);
                    if(solution.equals("")){
                        noAsign++;
    //                    System.out.println(iter);
                    }else{// si hay solucion
                        if(paquetesAct.get(iter).getEstado() == 1){
                            this.cantReruteo++;
                            //System.out.println("estado -> " + paquetesAct.get(iter).getEsFinal());
                            //System.out.println("Ruta anterior -> " + paquetesAct.get(iter).getRuta());
                        }                        
                        paquetesAct.get(iter).setRuta(solution);
                        //if(paquetesAct.get(iter).getEstado() == 1)
                          //  System.out.println("Ruta actual -> " + paquetesAct.get(iter).getRuta());
                        //paquetesAct.get(iter).setEstado(0); // paquete no disponible
                    }

    //              if(solution.equals("")) System.out.println(iter);

                    aux.add(solution);
                }else{
                    System.out.println("Some airport doesn't exist!");
                }
            }
        }
        System.out.println("Cantidad de Reruteos " + this.cantReruteo);
        System.out.println("Vacíos: " + String.valueOf(noAsign));
        /*
        System.out.println("Estado: ");
        for(int i=0; i<capVuelos.size(); i++)
            if(capVuelos.get(i) > 0)
                System.out.println("Tabu: "+String.valueOf(i+1) + " : " + capVuelos.get(i));

        System.out.println("Vacíos: " + String.valueOf(noAsign));
        */
        return aux;
    }
    
    public void tabuAlgorithm(String codeOrigin, String codeDestiny, String hourBegin){
        this.originId = inputProcess.searchAirportId(codeOrigin);
        this.destinyId = inputProcess.searchAirportId(codeDestiny);
        tabuString = new ArrayList<String>();
        routeOptimal = new ArrayList<Integer>();
        rutaRiesgo = generateInitialRoute();
        if(getListAirport().get(originId-1).getContinent() ==
                getListAirport().get(destinyId-1).getContinent()){
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
    
    public void tabuAlgorithm(int codeOrigin, int codeDestiny, String hourBegin){
        this.originId = codeOrigin;
        this.destinyId = codeDestiny;
        this.finded = false;
        tabuString = new ArrayList<String>();
        routeOptimal = new ArrayList<Integer>();
        rutaRiesgo = generateInitialRoute();
        if(getListAirport().get(originId-1).getContinent() ==
                getListAirport().get(destinyId-1).getContinent()){
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
        int destiny = getListFlight().get(route[lastValid-1]-1).getDestinyAirport();
        if((lastValid > 0) && (destiny == this.destinyId)){
            if((lenghtRoute <= this.limit))
                return 1;
            else{
                if((rutaRiesgo[0] == -1) || (lenghtRoute < getRouteLenght(rutaRiesgo)))
                    rutaRiesgo = route.clone();
                return 0;
            }
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
//        System.out.print("Overload");printArray(route);
//        System.out.println(String.valueOf(recorrido) + " vs " + String.valueOf(limit));
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
        if(bestRoute[0] != -1)
            if(turnoPermitido(bestRoute))
                return bestRoute;
        return rutaRiesgo;
    }
    
    private boolean turnoPermitido(int[] bestRoute){
        // Comprobar que ningún aeropuerto esté baneado
        if(bestRoute[0] == -1)
            return false;
        if(listFlight.get(bestRoute[0]-1).getDestinyAirport() == this.destinyId)
            return true;
        ArrayList<Integer> permitidos = new ArrayList<Integer>();
        boolean ward = true;
        for(int i=0; i<bestRoute.length; i++){
            if(bestRoute[i] == -1)
                break;
//            int origin = listFlight.get(bestRoute[i]-1).getOriginAirport()-1;
            int destiny = listFlight.get(bestRoute[i]-1).getDestinyAirport()-1;
            if(forbidden.get(destiny) < 0){
                permitidos.add(1);
                ward = false;
//                break;
            }else permitidos.add(0);
        }
        // Actualizar los baneados
       for(int i=0; i<bestRoute.length; i++){
            if(bestRoute[i] == -1)
                break;
           int destiny = listFlight.get(bestRoute[i]-1).getDestinyAirport()-1;
           // Si validamos la ruta ...
           if(ward){
               // ... le descontamos a cada aeropuerto su coef. de prohibición
               if(forbidden.get(destiny) > 0)
                   forbidden.set(destiny , forbidden.get(destiny)-1);
               else
                   forbidden.set(destiny , forbidden.get(destiny)-80);
           }else{
               // Le aumentamos en 1 su coef. de prohibición
               if(permitidos.get(i) == 1)
                   if(forbidden.get(destiny) < -1)
                       forbidden.set(destiny , forbidden.get(destiny)+1);
                   else
                       forbidden.set(destiny , forbidden.get(destiny)+16);
           }
       }
       return ward;
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
    
    private ArrayList<Integer> obtainNeighbor(int currentElement,
            int[] route){
        int actualNode = this.originId - 1;
//        System.out.println(currentElement);
        if(currentElement > -1){
            actualNode = (getListFlight().get(route[currentElement]-1).getDestinyAirport())-1;
        }
        int timeToCmp = this.hourBegin, cmpTime = 0;
        ArrayList<Integer> listNeighborAL = new ArrayList<Integer>();
        listNeighborAL =
                (ArrayList<Integer>) flightMatrix.get(actualNode).clone();
        ArrayList<Integer> listSuperior = new ArrayList<Integer>();
        ArrayList<Integer> listInferior = new ArrayList<Integer>();
        
//        int[] listNeighbor = new int[listNeighborAL.size()];
//        int[] listInferior = new int[listNeighborAL.size()];
//        int[] listSuperior = new int[listNeighborAL.size()];
//        for(int i=0; i<listNeighborAL.size(); i++){
//            listNeighbor[i] = -1;
//            listInferior[i] = -1;
//            listSuperior[i] = -1;
//        }
        if(getLastMinusOne(route) > 0){
            /* Destiny */
            timeToCmp = obtainStandardHour(getListFlight().get(route[getLastMinusOne(route)-1]-1),'L');
        }
//        int iSup = 0, iInf = 0, iTop = 0;
//        
        for(int i=0; i<listNeighborAL.size(); i++){
            /* Origin */
            cmpTime = obtainStandardHour(getListFlight().get(listNeighborAL.get(i)-1),'P');
            int[] provisional = route.clone();
            provisional[getLastMinusOne(route)] = listNeighborAL.get(i);
//            if((getRouteLenght(provisional) > this.limit) ||
//                    (capVuelos.get(listNeighborAL.get(i)-1) > 280))
            if(capVuelos.get(listNeighborAL.get(i)-1) > 280)
                continue;
            if(cmpTime <= timeToCmp || ((cmpTime > timeToCmp) && (this.intercont == 1))){
                listInferior.add(listNeighborAL.get(i));
            }else{
                listSuperior.add(listNeighborAL.get(i));
            }
        }
//        int iGen = 0;
////        System.out.println("INICIO");
//        
//        for(int i=0; i<listSuperior.length; i++){
//            
//            if(listSuperior[i] == -1) break;
//            listNeighbor[iGen] = listSuperior[i];
//            iGen++;
//        }
//        for(int i=0; i<listInferior.length; i++){
//            if(listInferior[i] == -1) break;
//            listNeighbor[iGen] = listInferior[i];
//            iGen++;
//        }
//        //VECINOS
////        System.out.print(currentElement);
////        System.out.print(" ");
////        if(currentElement > -1)
////            System.out.print(String.valueOf(route[currentElement]) + " " + String.valueOf(actualNode));
////        System.out.print(" ");
////        printArray(listNeighbor);
//        return listNeighbor;
        listSuperior.addAll(listInferior);
        return listSuperior;
    }
    
    public String generateTabuString(int[] solution){
        String resultado = "";
        for(int i=0; i<solution.length; i++){
            if(solution[i] == -1) break;
            if(i>0)
                resultado += "-";
            resultado += String.valueOf(solution[i]);
        }
        return resultado;
    }
    
    public String generateTabuString(ArrayList<Integer> solution){
        String resultado = "";
        for(int i=0; i<solution.size(); i++){
            if(i>0)
                resultado += "-";
            resultado += String.valueOf(solution.get(i));
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
        ArrayList<Integer> listNeighbor;
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
            e.printStackTrace();
            return originalRoute;
        }
//        System.out.print("Llegué con ");
//        printArray(bestRoute);
        /* Introduce best movement in tabu string*/
        String newTabu = generateTabuString(bestRoute);
        tabuString.add(newTabu);
//        System.out.println("FIN");
        return bestRoute;
    }
    
    private void printArray(int[] arr){
        for(int i : arr)
            System.out.print(String.valueOf(i) + " -> ");
        System.out.println();
    }
    
    private int[] dfs(int[] route, ArrayList<Integer> neighborList, int currentLevel, ArrayList<String> tabuString){
	if((route.equals(generateInitialRoute()) == false) && (isSolution(route) == 1)
                && (isBanned(tabuString, route) == 0)){
            this.finded = true;
            return route;
        }
	else if ((currentLevel > 1) && (neighborList.isEmpty() || overload(route) == 1)){
            /* Entra si la ruta elegida está "sobrecargada" */
            String newTabu = generateTabuString(route);
            tabuString.add(newTabu);
            return generateInitialRoute();
        }
	else{
            ArrayList<Integer> auxNeighborList = (ArrayList<Integer>) neighborList.clone();
            int[] auxRoute = route.clone();
            if(currentLevel > 1)
                auxNeighborList = obtainNeighbor((currentLevel-2), auxRoute);
            if(auxNeighborList.isEmpty()){
//                System.out.print("Ruta prov:"); printArray(auxRoute);
                String newTabu = generateTabuString(auxRoute);
                tabuString.add(newTabu);
                if((isSolution(auxRoute) == 1) && (overload(auxRoute) == 0)){
                    this.finded = true;
                    return auxRoute;
                }
                else{
//                    System.out.println("Ruta mala");
                    return generateInitialRoute();
                }
            }
//            System.out.print(currentLevel);
            for(int i=0; i<auxNeighborList.size(); i++){
                auxRoute[currentLevel-1] = auxNeighborList.get(i);
//                printArray(auxRoute);
                for(int iter = currentLevel; iter<auxRoute.length; iter++)
                    auxRoute[iter] = -1;
                if(overload(auxRoute) == 1){
                    auxRoute[currentLevel-1] = -1;
                    continue;
                }
                auxRoute = dfs(auxRoute, auxNeighborList, currentLevel+1, tabuString);
//                if((isSolution(auxRoute) == 1) && (overload(auxRoute) == 0)){
//                    this.finded = true;
//                    return auxRoute;
//                }
                if(auxRoute[0] == -1){
                    auxRoute = route.clone();
                    for(int iter = currentLevel; iter<auxRoute.length; iter++)
                        auxRoute[iter] = -1;
                    continue;
                }
                if(this.finded)
                    return auxRoute;
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
            int trackTime = inputProcess.getTrackTime(getListFlight().get(flight-1));
            if(trackTime == -1)
                return (this.limit + 1);
            int waitTime = 0;
            if(i>0){
                int pastFlight = route.get(i-1);
                waitTime = inputProcess.getWaitTime(getListFlight().get(flight-1),
                        getListFlight().get(pastFlight-1));
            }else{
                int arriv = getListFlight().get(flight-1).getOriginHour()*60 +
                        getListFlight().get(flight-1).getOriginMin();
                if(arriv > this.hourBegin)
                    waitTime = arriv - this.hourBegin;
                else
                    waitTime = arriv - this.hourBegin + 1440;
                
//                waitTime = 0;
//                System.out.println(String.valueOf(arriv) + " y " + waitTime);
            }
//            System.out.println("T: " + String.valueOf(trackTime) + "W: " + String.valueOf(waitTime));
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
                aux.add(getListAirport().get(getListFlight().get(i-1).getOriginAirport()-1).getIcaoCode());
            aux.add(getListAirport().get(getListFlight().get(i-1).getDestinyAirport()-1).getIcaoCode());
        }
        return aux;
    }

    /**
     * @return the listPack
     */
    public ArrayList<Paquete> getListPack() {
        return listPack;
    }

    /**
     * @param listPack the listPack to set
     */
    public void setListPack(ArrayList<Paquete> listPack) {
        this.listPack = listPack;
    }
}