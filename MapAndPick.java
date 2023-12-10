public class MapAndPick{

    //creates a static 2D array that holds all the data for the agents
    static String[][] data = new String[4][23];

    /*
    *method that adds all the data of the agents into the 2D array 
    *@return - no return value but adds all the data into data[][]
    */
    public static void addData(){
        data[0][0] = data[1][1] = data[1][13] = data[1][15] = data[1][20] = data[1][22] = "Astra";
        data[0][1] = data[1][14] = data[2][0] = "Breach";
        data[0][2] = data[1][10] = data[2][15] = data[2][20] = "Brimstone";
        data[0][3] = "Chamber";
        data[0][4] = data[1][19] = data[2][13] = data[2][22] = "Cypher";
        data[0][5] = data[2][16] = "Deadlock"; 
        data[0][6] = "Fade";
        data[0][7] = "Gekko";
        data[0][8] = "Harbor";
        data[0][9] = data[1][21] = data[2][3] = "Iso";
        data[0][10] = data[1][18] = "Jett";
        data[0][11] = data[1][3] = data[1][7] = data[2][5] = data[2][8] = "KAY/O";
        data[0][12] = data[1][16] = data[2][7] = "Killjoy";
        data[0][13] = "Neon";
        data[0][14] = data[2][1] = data[2][19] = "Omen"; 
        data[0][15] = data[1][5] = data[2][2] = data[2][9] = data[2][14] = "Pheonix"; 
        data[0][16] = data[1][12] = data[2][18] = "Raze";
        data[0][17] = "Reyna";
        data[0][18] = data[1][11] = data[1][17] = data[2][10] = "Sage";
        data[0][19] = data[2][4] = "Skye";
        data[0][20] = data[1][0] = data[1][2] = data[1][4] = data[1][6] = data[2][12] = data[2][17] = data[2][21] = "Sova"; 
        data[0][21] = data[1][8] = data[2][6] = data[2][11] = "Viper"; 
        data[0][22] = data[1][9] = "Yoru";
        data[3][4] = "Ascent";
        data[3][2] = data[3][5] = data[3][9] = data[3][20] = "Bind";
        data[3][11] = "Breeze";
        data[3][1] = "Fracture";
        data[3][0] = data[3][3] = data[3][10] = data[3][14] = "Haven";
        data[3][8] = data[3][16] = data[3][17] = "Icebox";
        data[3][12] = data[3][13] = data[3][21] = "Lotus";
        data[3][7] = "Pearl";
        data[3][18] = data[3][19] = data[3][22] = "Split";
        data[3][6] = data[3][15] = "Sunset";
    }

    /*
    *method that finds the counter for the agent the user requests on the other team based off the data from the 2D array
    *precondition - 10 agents have been created
    *precondition - name of agent is included in the data directory 
    *precondition - team is a number between 1-2
    *@param - name is a string value
    *@param - team is an int value
    *@return - no return value but sends a print statement dictating what counter picks exist on the other team for the agent the user specified
    */
    public static void findCounter(String name, int team){
        int position = findPosition(name); //checks where the agent is in the data table
        int current = 0;
        //creates 2 variables that check whether or not the other team has the counters on row 1 or row 2 or both
        boolean hasCounter1 = false;
        boolean hasCounter2 = false;

        //check  for the first row counter
        while (hasCounter1==false&&current<Agents.team1.length){
            if(team==1){
                if(Agents.team1[current].equalsIgnoreCase(data[1][position])){
                    hasCounter1=true;
                }current++;
            } else {
                if(Agents.team2[current].equalsIgnoreCase(data[1][position])){
                    hasCounter1=true;
                }current++;
            }
        } 

        current=0;
        //check for the second row counter
        while (hasCounter2==false && current<Agents.team1.length){
            if(team==1){
                if(Agents.team1[current].equalsIgnoreCase(data[2][position])){
                    hasCounter2=true;
                }current++;
            } else {
                if(Agents.team2[current].equalsIgnoreCase(data[2][position])){
                    hasCounter2=true;
                }current++;
            }
        }

        //print statements dependent on what counter the other team has
        if (hasCounter1 && hasCounter2){
            System.out.println("Team " + team + " has the following counters for " + name + ": " + data[1][position] + " and "+ data[2][position] + ".");
        } else if (hasCounter1){
            System.out.println("Team " + team + " has the following counters for " + name + ": " + data[1][position] + ".");
        } else if (hasCounter2){
            System.out.println("Team " + team + " has the following counters for " + name + ": " + data[2][position] + ".");
        } else{
            System.out.println("Team " + team + " has no counters for " + name + ".");
        }
    } 

    /*
    *method that checks what map is best for the team the user specifies
    *precondition - 10 agents have been created
    *precondition - team is a number between 1-2
    *@param - team is an int value
    *@return - no return value but sends a print statement dictating what map is best for the team the user specified 
    */
    public static void findMap(int team){
        //finds the postions of all the agents on the team requested in the 2D data array
        int agent1, agent2, agent3, agent4, agent5 = 0; 
        if (team==1){
            agent1 = findPosition(Agents.team1[0]);
            agent2 = findPosition(Agents.team1[1]);
            agent3 = findPosition(Agents.team1[2]);
            agent4 = findPosition(Agents.team1[3]);
            agent5 = findPosition(Agents.team1[4]);
        }else{
            agent1 = findPosition(Agents.team2[0]);
            agent2 = findPosition(Agents.team2[1]);
            agent3 = findPosition(Agents.team2[2]);
            agent4 = findPosition(Agents.team2[3]);
            agent5 = findPosition(Agents.team2[4]);
        }

        String[] maps = {data[3][agent1], data[3][agent2], data[3][agent3], data[3][agent4], data[3][agent5]};//creates an array for all the best maps for the agents on the team
        int maxCount = 0;
        String mostMap = "";

        //loop that counts which map is the best for the most amount of agents on the team
        for (int i = 0; i<maps.length; i++){
            int count = 0;
            for (int j = 0; j<maps.length; j++){
                if(maps[i].equals(maps[j])){
                    count++;
                }
            }
            if(count>maxCount){
                maxCount=count;
                mostMap=maps[i];
            }
        } 

        //print statements that depend on whether or not the user's requested team actually have a common best map
        if(maxCount<2){
            System.out.println("Team "+ team+ " does not have a best team.");
        } else{
            System.out.println("Team "+ team+ "'s best map is "+ mostMap+ ".");
        }
    }

    /*
    *method that finds the index of the agent specified in the 2D data array
    *precondition - name of agent is included in the data directory 
    *@param - name is a string value
    *@return - returns the index postion of the name of the agent the user specified in the 2D data array
    */
    public static int findPosition(String name){
        int current = 0;
        int position = 0;

        //loop that runs until the agent is found in the data table
        boolean found = false;
        while (found==false && current <23){ 
            if(data[0][current].equalsIgnoreCase(name)){
                position = current;
                found=true;
            }else{
                current++;
            }
        }

        //returns a value dependent on whether or not the agent is actually real, if the agent is real it displays it's location, if it isn't it returns -1
        if (found){
            return position;
        } else{
            return -1;
        }
    }

    /*
    *displays all the agents that are possible to be chosen from
    *@return - no return value but sends a print statement with all agents in the 2D data array
    */
    public static void displayAgents(){
        String result = "";
        for (int i = 0; i<23;i++){//runs through all the agents in the 2D data array and puts them into a string 
            result += ""+data[0][i]+", ";
        }
        result = result.substring(0, result.length()-2)+ "."; //removes the extra comma and space at the end of the resulting string
        System.out.println("\nAll the agents in alphabetical order is: "+ result);
    }
}