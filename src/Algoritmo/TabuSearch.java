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
//    private int[] rutaRiesgo;
    private int[] bestRoute;
    private int numAirport;
    private int numFlight;
//    private int numInt = 1;
    private int originId;
    private int destinyId;
    private int limit; // 2880 = intercontinental, 1440 = no
    private int hourBegin;
    private boolean finded = false;
    private int timeToCmp;
    private int cantReruteo = 0;
    private ArrayList<Integer> forbidden;
    private ArrayList<Integer> avoider;

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
    }
    
    /* Setters & getters */
    
    public ArrayList<Integer> getRouteOptimal(){
        return routeOptimal;
    }

    public DataProcessing getInputProcess() {
        return inputProcess;
    }

    public void setInputProcess(DataProcessing inputProcess) {
        this.inputProcess = inputProcess;
    }

    public ArrayList<Aeropuerto> getListAirport() {
        return listAirport;
    }

    public void setListAirport(ArrayList<Aeropuerto> listAirport) {
        this.listAirport = listAirport;
    }

    public ArrayList<Vuelo> getListFlight() {
        return listFlight;
    }

    public void setListFlight(ArrayList<Vuelo> listFlight) {
        this.listFlight = listFlight;
    }
    
    public ArrayList<Paquete> getListPack() {
        return listPack;
    }

    public void setListPack(ArrayList<Paquete> listPack) {
        this.listPack = listPack;
    }
    
    /* Methods */
    
    /* inputData: Set simulation data bask on files data*/
    public void inputData(String nameAirport, String nameFlight, String namePack){
        inputProcess.inputData(nameAirport, nameFlight, namePack);
        listAirport = inputProcess.getAirportList();
        listFlight = inputProcess.getFlightList();
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
                Collections.sort(temp, new Comparator<Integer>() {
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
            forbidden.add(10);
//        for(int i=0; i<listFlight.size(); i++)
//            listFlight.get(i).print();
        setListPack(paquetesAct);
        int noAsign = 0;
        for(int iter=0; iter<numPack; iter++){
            //NO APLICA ALGORITMO A LOS QUE SE ENCUENTRAN EN EL AIRE Y A LOS QUE YA NO NECESITAN
            if ((paquetesAct.get(iter).getEstado() == 0 || (paquetesAct.get(iter).getEstado() == 1 && paquetesAct.get(iter).getNuevo() == 0)) && paquetesAct.get(iter).getEsFinal() == 0){// SOLO PARA 0 o 1 y no es final
                if (paquetesAct.get(iter).getIdentificator() == 836037){
                    System.out.println("AQUI BUSCA RUTA");
                }
//            if (paquetesAct.get(iter).getEstado() == 1){
                int origin = paquetesAct.get(iter).getOriginAirport();
                int destiny = paquetesAct.get(iter).getDestinyAirport();
//                if(iter % 200 == 0)
//                    System.out.println(iter);
//                getListPack().get(iter).print();
                String time = String.valueOf(paquetesAct.get(iter).getOriginHour()) + ":" + 
                        String.valueOf(paquetesAct.get(iter).getOriginMin());
                tabuAlgorithm(origin, destiny, time);
                ArrayList<Integer> optimal = new ArrayList<Integer>();
                for(int i : this.bestRoute){
                    if(i == -1) break;
                    optimal.add(i);
                }
//                ArrayList<Integer> optimal = getRouteOptimal();
//                if(optimal.isEmpty()){ // Si está vacío -> Default
//                    optimal = findDefaultSolution();
//                    noAsign++;
//                }
                if(optimal.size() > 0)
                    for(int i : optimal)
                        capVuelos.set(i-1, capVuelos.get(i-1)+1);

                String solution = generateTabuString(optimal);
                
//                System.out.println("Solution " + String.valueOf(iter) + ": " + solution + " [" + getAirportsRouteICAOStr(this.bestRoute) + ", Lenght: " + String.valueOf(getRouteLenght(optimal)) + "]");
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
            }
        }
        System.out.println("Cantidad de Reruteos " + this.cantReruteo);
        System.out.println("Vacíos: " + String.valueOf(noAsign));
//        for(int i=0; i<capVuelos.size(); i++){
//            System.out.println(String.valueOf(i+1) + ": " + capVuelos.get(i));
//        }
        /* Verificación de aeropuertos */
        int[] comprob = new int[40];
        for(int i=0; i<comprob.length; i++)
            comprob[i] = 0;
        for(int i=0; i<capVuelos.size(); i++){
            comprob[listFlight.get(i).getDestinyAirport()-1] += capVuelos.get(i);
        }
        for(int i=0; i<comprob.length; i++){
            System.out.println(String.valueOf(i+1) + ": " + comprob[i]);
        }
        return aux;
    }
    
    public void tabuAlgorithm(String codeOrigin, String codeDestiny, String hourBegin){
        this.originId = inputProcess.searchAirportId(codeOrigin);
        this.destinyId = inputProcess.searchAirportId(codeDestiny);
        tabuString = new ArrayList<String>();
        routeOptimal = new ArrayList<Integer>();
//        rutaRiesgo = generateInitialRoute();
        if(getListAirport().get(originId-1).getContinent() ==
                getListAirport().get(destinyId-1).getContinent()){
            this.limit = 1440;
        } else this.limit = 2880;
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
        avoider = new ArrayList<Integer>();
        bestRoute = generateInitialRoute();
//        rutaRiesgo = generateInitialRoute();
        if(getListAirport().get(originId-1).getContinent() ==
                getListAirport().get(destinyId-1).getContinent()){
            this.limit = 1440;
        } else this.limit = 2880;
//        System.out.println("Límite "+String.valueOf(this.limit));
        this.hourBegin = inputProcess.getFormatHour(Integer.valueOf(hourBegin.split(":")[0]),
                Integer.valueOf(hourBegin.split(":")[1]));
//        System.out.println("[["+ this.hourBegin+"]]");
        solve();
    }
    
    private int[] solve(){
        ArrayList<Integer> listNeighbor = obtainNeighbor(-1, generateInitialRoute()); // Routes
        return dfs(generateInitialRoute(), listNeighbor, 1, tabuString);
    }
    
    private int[] dfs(int[] route, ArrayList<Integer> neighborList, int currentLevel, ArrayList<String> tabuString){
        if(currentLevel > 3)
            return generateInitialRoute();
	if((route.equals(generateInitialRoute()) == false) && (isSolution(route) == 1)
                && (isBanned(tabuString, route) == 0)){
            this.finded = true;
//            System.out.println("Sol. temporal: " + generateTabuString(route) + " [" + getAirportsRouteICAOStr(route) + 
//                   ", Lenght: " + String.valueOf(getRouteLenght(route)) + "]");
            if((getRouteLenght(this.bestRoute) == 0) || (getRouteLenght(this.bestRoute) > getRouteLenght(route))){
//                System.out.println("Clonando .... ");
                this.bestRoute = route.clone();
                this.avoider.add(route[getLastMinusOne(route)-1]);
            }
//            System.out.println("Mejor ruta temporal: " + generateTabuString(this.bestRoute) + " [" + getAirportsRouteICAOStr(this.bestRoute) + 
//                   ", Lenght: " + String.valueOf(getRouteLenght(this.bestRoute)) + "]");
            return route;
//            return generateInitialRoute();
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
                String newTabu = generateTabuString(auxRoute);
                tabuString.add(newTabu);
                if((isSolution(auxRoute) == 1) && (overload(auxRoute) == 0)){
                    this.finded = true;
                    return generateInitialRoute();
                }
                else{
                    return generateInitialRoute();
                }
            }
//            System.out.print(currentLevel);
            int tamanhoVec = auxNeighborList.size();
            for(int i=0; i<tamanhoVec; i++){
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
//                else if(auxRoute.equals(generateInitialRoute()))
                if(auxRoute.equals(generateInitialRoute()))
                    auxRoute[currentLevel-1] = -1;
                    continue;
            }
//            String newTabu = generateTabuString(route);
//            tabuString.add(newTabu);
            return generateInitialRoute();
	}
    }
    
    private void printArray(int[] arr){
        for(int i : arr)
            System.out.print(String.valueOf(i) + " -> ");
        System.out.println();
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

    private int[] generateInitialRoute(){
        int[] solution = new int[5];
        for(int i=0; i<5;i++){
            solution[i] = -1;
        }
        return solution;
    }
    
    private void printArrayList(ArrayList<Integer> arr){
        for(int i=0; i<arr.size(); i++)
            System.out.print(String.valueOf(arr.get(i)) + " -> ");
        System.out.println();
    }
    
    private boolean turnoPermitido(int[] bestRoute){
        // Comprobar que ningún aeropuerto esté baneado
        if(bestRoute[0] == -1)
            return false;
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
                   if(destiny == (this.destinyId + 1))
                       forbidden.set(destiny , forbidden.get(destiny)-40);
                   else
                       forbidden.set(destiny , forbidden.get(destiny)-20);
           }else{
               // Le aumentamos en 1 su coef. de prohibición
               if(permitidos.get(i) == 1)
                   if(forbidden.get(destiny) < -1)
                       forbidden.set(destiny , forbidden.get(destiny)+1);
                   else
                       forbidden.set(destiny , forbidden.get(destiny)+11);
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

        // Creación de listas auxiliares
        ArrayList<Integer> listSuperior = new ArrayList<Integer>();
        ArrayList<Integer> listInferior = new ArrayList<Integer>();
        if(getLastMinusOne(route) > 0){
            /* Destiny */
            timeToCmp = obtainStandardHour(getListFlight().get(route[getLastMinusOne(route)-1]-1),'L');
        }
        for(int i=0; i<listNeighborAL.size(); i++){
            /* Origin */
            cmpTime = obtainStandardHour(getListFlight().get(listNeighborAL.get(i)-1),'P');
            int[] provisional = route.clone();
            provisional[getLastMinusOne(route)] = listNeighborAL.get(i);
            if( (this.avoider.contains(listNeighborAL.get(i))) ||
                    (getRouteLenght(provisional) > this.limit) ||
                    (capVuelos.get(listNeighborAL.get(i)-1) >= 300))
//            if(capVuelos.get(listNeighborAL.get(i)-1) > 300)
                continue;
//            System.out.println("(" + cmpTime + "," + listNeighborAL.get(i) + ")");
            if(cmpTime > timeToCmp){
                listSuperior.add(listNeighborAL.get(i));
            }else{
                listInferior.add(listNeighborAL.get(i));
            }
        }
        listSuperior.addAll(listInferior);
//        if(currentElement > -1)
//            for(int i=-1; i<currentElement; i++)
//                System.out.print("  ");
//        System.out.print(currentElement);
//        System.out.print(" ");
//        if(currentElement > -1)
//            System.out.print(String.valueOf(route[currentElement]) + " " + String.valueOf(actualNode+1) + " [" + timeToCmp + "]");
//        System.out.print(" ");
//        printArrayList(listSuperior);
        return listSuperior;
    }
    
    public ArrayList<Integer> findDefaultSolution(){
        ArrayList<Integer> listNeighbor = obtainNeighbor(-1, generateInitialRoute());
        for(int i : listNeighbor){
            if(listFlight.get(i-1).getDestinyAirport() == this.destinyId){
                ArrayList<Integer> response = new ArrayList<Integer>();
                response.add(i);
                return response;
            }
        }
        return new ArrayList<Integer>();
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
    
    public String getAirportsRouteICAOStr(){
        String aux = "";
        for(int i : routeOptimal){
            if(routeOptimal.get(0) == i)
                aux += getListAirport().get(getListFlight().get(i-1).getOriginAirport()-1).getIcaoCode();
            aux += "->" + getListAirport().get(getListFlight().get(i-1).getDestinyAirport()-1).getIcaoCode();
        }
        return aux;
    }
    
    public String getAirportsRouteICAOStr(int[] route){
        String aux = "";
        for(int i : route){
            if(i == -1)
                break;
            if(route[0] == i)
                aux += getListAirport().get(getListFlight().get(i-1).getOriginAirport()-1).getIcaoCode();
            aux += "->" + getListAirport().get(getListFlight().get(i-1).getDestinyAirport()-1).getIcaoCode();
        }
        return aux;
    }
}