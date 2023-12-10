import java.util.*;

public class Agents{

    //initialising the class variables
    private int creds;
    private int kills;
    private int deaths;
    private String agent;
    public int team;
    private int position;

    //initialising static variables for all classes
    static int totalAgents = 0; //important for checking whether or not the user is ready to use further parts of the program

    //amount of agents in each team
    static int team1Total = 0;
    static int team2Total = 0;

    //all the agent objects 
    static Agents[] allAgents = new Agents[10];

    //names of the agents 
    static String[] allNames = new String[10];
    static String[] team1 = new String[5];
    static String[] team2 = new String[5];

    //constructor for the agent objects
    public Agents(String name, int team, int creds, int kill, int death){
        this.agent = name;
        this.team = team;
        //system for adding an agent to the team that has space if the team requested is full already
        boolean teamAdded = false;
        while (teamAdded==false){
            if (this.team == 1){
                if (team1Total==5){
                    this.team=2;
                }else{
                team1Total++;
                //adds agent names to their team string arrays
                team1[team1Total-1]=this.agent;
                this.position = team1Total-1;
                allNames[totalAgents] = this.agent;
                teamAdded=true;
                }
            }else{
                if (team2Total==5){
                    this.team=1;
                }else{
                team2Total++;
                //adds agent names to their team string arrays
                team2[team2Total-1]=this.agent;
                this.position = team2Total-1;
                allNames[totalAgents] = this.agent;
                teamAdded=true;
                }
            }
        }
        //adds all the remaining data into the corresponding variables
        this.creds = creds;
        if (kill>0){
            this.kills = kill;
        }else{
            this.kills = 0;
        }
        if (death>0){
            this.deaths = death;
        }else{
            this.deaths = 0;
        }
        totalAgents++;
    } 

    //secondary level constructor, only needing name and team to construct
    public Agents(String name, int team){
        this(name, team, 0, 0, 0);
    }

    //tertiary level constructor, only needing name to construct, with team being randomised
    public Agents(String name){
        this(name, (int)(Math.random()*2)+1, 0, 0, 0);
    }

    /*
    *method that adds credits to an agent
    *precondidtion - add is positive
    *@param - add is a int variable
    *@return - no return value but sends a print statement dictating how many creds are currently held
    */
    public void addCreds(int add){
        if (add<0){ //makes sure the user is using the correct function for the output they want
            System.out.println("Use the spend credits function to spend credits!");
        }else if(this.creds+add>9000){ 
            this.creds=9000;
            System.out.println("Transaction complete! You currently have 9000 credits.");
        }else{
            this.creds+=add;
            System.out.println("Transaction complete! You currently have " + this.creds +" credits.");
        }
    }

    /*
    *method that subtracts credits from an agent
    *precondidtion - spend is positive
    *@param - spend is a int variable
    *@return - no return value but sends a print statement dictating how many creds are currently held
    */
    public void spendCreds(int spend){
        if (this.creds-spend<0){
            System.out.println("You shouldn't spend what you don't have!");
        }else if (spend<0){ //makes sure the user is using the correct function for the output they want
            System.out.println("Use the add credits function to add credits!");
        } else{
            this.creds-=spend;
            System.out.println("Transaction complete! You currently have " + this.creds +" credits.");
        }
    }

    /*
    *method that displays balance of an agent
    *@return - integer return value with amount of credits currently held
    */
    public int getCreds(){
        return this.creds;
    }

    /*
    *method that either adds or removes deaths to an agent, depending on if user wants to subtract or add deaths
    *@param - num is a int variable
    *@return - no return value but sends a print statement dictating how many deaths are currently held
    */
    public void changeDeaths(int num){
        if (this.deaths+num>0){
            this.deaths+=num;
            System.out.println("You currently have " + this.deaths+".");
        }else{
            System.out.println("You cannot die negative times.");
        }
    }

    /*
    *method that either adds or removes kills to an agent, depending on if user wants to subtract or add kills
    *@param - num is a int variable
    *@return - no return value but sends a print statement dictating how many kills are currently held
    */
    public void changeKills(int num){
        if (this.kills+num>0){
            this.kills+=num;
            System.out.println("You currently have " + this.kills+".");
        }else{
            System.out.println("You cannot kill negative times.");
        }
    }

    /*
    *method that calculates a user's kill to death ratio, which is esentially just the users kills divided by the amount of deaths
    *@return - no return value but sends a print statement dictating what KD is currently held
    */
    public void getKD(){
        if (deaths>0){ //this is just so that you aren't dividing kills by 0 and causing a java error
            System.out.println("Your KD is "+(double)this.kills/this.deaths+".");
        } else {
            System.out.println("Your KD is infinity.");
        }
    }

    /*
    *method that changes the team for an agent the user chooses (if the team has space)
    *precondition - num is a number between 1-2, num is a team that the agent calling the method is not on
    *@param - num is an int value
    *@return - no return value but sends a print statement dictating whether or not function was successful
    */
    public void setTeam(int num){
        if (team1Total<5 && team2Total<5){
            if(this.team!=num){//checks if the user is actually changing an agent's team
                if(num==1){ //makes sure the user is adding the agent to a real team
                    team1Total++;
                    team2Total--;
                    this.team=1;
                    //adds agent names to their new team string arrays
                    team2[this.position]="";
                    team1[this.position]=this.agent;
                    System.out.println("Your agent is now on team 1.");
                }else if(num==2){
                    team2Total++;
                    team1Total--;
                    this.team=2;
                    //adds agent names to their new team string arrays
                    team1[this.position]="";
                    team2[this.position]=this.agent;
                    System.out.println("Your agent is now on team 1.");
                }else{
                    System.out.println("There are only 2 teams.");
                }
            }else{
                System.out.println("Agent is already on team "+ this.team+"!");
            }    
        }else{
            System.out.println("Requested team is full.");
        }
    }

    /*
    *method that prints the team rosters in alphabetical order
    *precondition - 10 agents have been created 
    *@return - no return value but sends a print statement dictating the 2 teams sorted in alphabetical order
    */
    public static void getTeams(){
        sortTeams();
        String teams1 = "";
        String teams2 = "";
        for (int i = 0; i<5; i++){
            teams1 += team1[i]+", ";
            teams2 += team2[i]+", ";
        }
        teams1=teams1.substring(0,teams1.length()-2); //removes extra commas and spaces at the end of the return strings
        teams2=teams2.substring(0,teams2.length()-2);
        System.out.println("Team 1 is " + teams1 + " and Team 2 is " + teams2);
    }

    /*
    *method that swaps an agent's team with another agent's team
    *precondition - 2 agents have been created
    *@param - other is an Agent object
    *@return - no return value but sends a print statement dictating whether or not function was successful
    */
    public void swapTeam(Agents other){
        String temp = "";
        if (this.team == other.team){ //checks if the user is actually changing an agents team
            System.out.println("Requested agents are on the same team.");
        }else if(this.team==1){ //makes sure the user is adding the agent to a real team
            temp = team1[this.position];
            team1[this.position] = team2[other.position];
            team2[other.position] = temp;
            this.team=2;
            other.team=1;
            System.out.println("Successful! Agents have swapped teams.");
        }else{
            temp = team2[this.position];
            team2[this.position] = team1[other.position];
            team1[other.position] = temp;
            this.team=1;
            other.team=2;
            System.out.println("Successful! Agents have swapped teams.");
        }
    }

    /*
    *method that prints out all the agents that have been created in order of what agent has been created
    *@return - no return value but sends a print statement with all agents that have been created
    */
    public static void allAgentsCreated(){
        String result = "";
        for (int i = 0; i<totalAgents; i++){
            result += ""+(i+1)+". "+allNames[i]+" ";
        }
        System.out.println(result);
    }

    /*
    *method that sorts teams in alphabetical order
    *precondition - 10 agents have been created
    *@return - no return value but team arrays have been sorted in alphabetical order
    */
    public static void sortTeams(){
        Arrays.sort(team1);
        Arrays.sort(team2);
    }
}