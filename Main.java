import java.util.*;

public class Main {
    public static void main(String[] args) {
        MapAndPick.addData();//calls the method that fills the 2D data array

        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Valorant Counterpick Calculator!");

        //creates the while loop that the UI runs in, which breaks when the user indicates if they want to exit
        boolean exit = false;
        while (exit == false){
            System.out.println("\nWhat would you like to do?");
            //displays all the main options that are available for the user to do (options 3 if only avaiable if they have created an agent and 4-5 is if they have 2 full teams created)
            System.out.println("0. Exit \n1. Create Agents\n2. See list of all possible Agents\n3. Agent functions\n4. Check counterpicks\n5. Find best map\n6. Autofill Teams");

            int option = scan.nextInt();

            //option that causes the loop to break and for the program to close
            if (option == 0){
                exit=true;
            } 

            //option that allows users to create agents
            else if (option == 1){
                if(Agents.totalAgents<10){ //checks if the user has made less than 10 agents, with 10 being the limit of amount of allowed agents
                    System.out.println("\nHow detailed would you like to make your Agent?");

                    //provides options of how detailed the user wants their agent to be 
                    System.out.println("1. Most detail (Recommended if using Agent functions)\n2. Less detail\n3. Least Detail\n4. Multi-Create");
                    option = scan.nextInt();

                    //most detailed option
                    if (option==1){
                        System.out.println("\nWhat's your agents name (only real agents are allowed)?");
                        String name = scan.next();

                        //makes sure the user is inputting a real agent
                        if (MapAndPick.findPosition(name)!=-1){
                            System.out.println("\nWhat team do you want your Agent to be on (1 or 2)?");
                            int team = scan.nextInt();

                            //makes sure the user is inputting a real team
                            if (team!=1&&team!=2){
                                System.out.println("\nRequested team doesn't exist, please restart creation process.");
                                back();
                            } else {
                                System.out.println("\nHow many credits does your agent have(cannot exceed 9000, whole numbers only)?");
                                int credits = scan.nextInt();

                                //makes sure the sure the user inputs an appropriate amount of credits (less than 9000 but more than 0)
                                if (credits>9000 || credits<0){
                                    System.out.println("\nCredits cannot exceed 9000 or be less than 0, please restart creation process.");
                                    back();
                                } else {
                                    System.out.println("\nHow many kills does your agent have?");
                                    int kills = scan.nextInt();

                                    //makes sure the user inputs and appropriate amount of kills (more than 0)
                                    if(kills<0){
                                        System.out.println("\nKills cannot be a negative value, please restart creation process.");
                                        back();
                                    } else {
                                        System.out.println("\nHow many deaths does your agent have?");
                                        int deaths = scan.nextInt();

                                        //makes sure the user inputs and appropriate amount of deaths (more than 0)
                                        if (deaths<0){
                                            System.out.println("\nYou cannot die negative times, please restart creation process.");
                                            back();
                                        } else {

                                            //creates the Agent object using the array of Agents created in the Agents class
                                            Agents.allAgents[Agents.totalAgents] = new Agents(name, team, credits, kills, deaths);
                                            System.out.println("\nAgent " + name + " Created!");
                                            back();
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("\nAgent requested doesn't exist, please refer to list of all possible Agents.");
                            back();
                        }

                    //less detailed option
                    }else if (option == 2){
                        System.out.println("\nWhat's your agents name (only real agents are allowed)?");
                        String name = scan.next();

                        //makes sure the user is inputting a real agent
                        if (MapAndPick.findPosition(name)!=-1){
                            System.out.println("\nWhat team do you want your Agent to be on (1 or 2)?");
                            int team = scan.nextInt();

                            //makes sure the user is inputting a real team
                            if (team!=1&&team!=2){
                                System.out.println("\nRequested team doesn't exist, please restart creation process.");
                                back();
                            } else {
                                //creates the Agent object using the array of Agents created in the Agents class
                                Agents.allAgents[Agents.totalAgents] = new Agents(name, team);
                                System.out.println("\nAgent " + name + " Created!");
                                back();
                            }
                        }     
                        else {
                            System.out.println("\nAgent requested doesn't exist, please refer to list of all possible Agents.");
                            back();
                        } 

                    //least detailed option
                    }else if (option == 3){
                        System.out.println("\nWhat's your agents name (only real agents are allowed)?");
                        String name = scan.next();

                        //makes sure the user is inputting a real agent
                        if (MapAndPick.findPosition(name)!=-1){

                            //creates the Agent object using the array of Agents created in the Agents class
                            Agents.allAgents[Agents.totalAgents] = new Agents(name);
                            System.out.println("\nAgent " + name + " Created!");
                            back();
                        } else {
                            System.out.println("\nAgent requested doesn't exist, please refer to list of all possible Agents.");
                            back();
                        }

                    //multi add option
                    } else if (option ==4){
                        System.out.println("\nHow many agents do you want to create?");
                        int amount = scan.nextInt();

                        //makes sure that the user doesn't create too many/little agents
                        if (amount<1){
                            System.out.println("\nYou have to create at least 1 agent!");
                            back();
                        } else if (amount+Agents.totalAgents>10){
                            System.out.println("\nToo many agents being created!");
                            back();
                        } else{ 
                            //runs a for loop the amount of agents the user wants to create
                            for (int i = 0; i<amount;i++){
                                System.out.println("\nWhat's Agent "+(i+1)+"'s name (only real agents are allowed)?");
                                String name = scan.next();

                                //makes sure the user is inputting a real agent
                                if (MapAndPick.findPosition(name)!=-1){
                                    System.out.println("\nWhat team do you want Agent "+(i+1)+" to be on (1 or 2)?");
                                    int team = scan.nextInt();

                                    //makes sure the user is inputting a real team
                                    if (team!=1&&team!=2){
                                        System.out.println("\nRequested team doesn't exist, please restart creation process.");
                                        back();
                                        break;
                                    } else {
                                        //creates the Agent object using the array of Agents created in the Agents class
                                        Agents.allAgents[Agents.totalAgents] = new Agents(name, team);
                                        System.out.println("\nAgent "+(i+1)+" " + name + " Created!\nPress any key to continue.");
                                        scan.next();
                                    }
                                }else {
                                    System.out.println("\nAgent requested doesn't exist, please refer to list of all possible Agents.");
                                    back();
                                    break;
                                } 
                            }
                        }
                    }else {
                        System.out.println("\nOption doesn't exist.");
                        back();
                    }
                }else{
                    System.out.println("\nYou cannot create anymore agents");
                    back();
                }    

            //option that displays all the possible agents using the displayAgents() function
            } else if (option == 2){
                MapAndPick.displayAgents();
                back();

            //option that allows user to access all the functions that modify agent 
            } else if (option == 3){
                if (Agents.totalAgents>0){//makes sure the user has at least 1 agent created
                    System.out.println("\nWhich agent would you like to analyse?");
                    Agents.allAgentsCreated(); //displays all agents created so the user knows what they can choose from
                    int agentChosen = scan.nextInt(); //allows the user to choose an agent

                    //makes sure the user chooses an agent that is actually created
                    if (agentChosen>Agents.totalAgents||agentChosen<=0){
                        System.out.println("\nAgent requested doesn't exist.");
                        back();
                    } else {
                        System.out.println("\nWhat would you like to do?");

                        //provides all the options for what functions the user may use on their agent of choice
                        System.out.println("1. Add Credits\n2. Spend Credits\n3. Check Balance\n4. Add/Remove Kills\n5. Add/Remove Deaths\n6. Get KD Ratio\n7. Change Teams\n8. Switch teams with another Agent\n9. Get current teams");
                        option = scan.nextInt();

                        //option 1 allows the user to add credits using the addCreds() function
                        if (option==1){
                            System.out.println("\nHow many credits do you want to add?");
                            int add = scan.nextInt();
                            Agents.allAgents[agentChosen-1].addCreds(add);
                            back();

                        //option 2 allows the user to spend credits using the spendCreds() function
                        } else if (option==2){
                            System.out.println("\nHow many credits do you want to spend?");
                            int spend = scan.nextInt();
                            Agents.allAgents[agentChosen-1].spendCreds(spend);
                            back();

                        //option 3 allows users to get their balance using the getCreds() function
                        } else if (option==3){
                            System.out.println("\nYou currently have "+ Agents.allAgents[agentChosen-1].getCreds()+"credits.");
                            back();

                        //option 4 allows users to change the amount of kills they have using the changeKills() function
                        } else if (option==4){
                            System.out.println("\nHow many kills do you want to add/remove?");
                            int kills = scan.nextInt();
                            Agents.allAgents[agentChosen-1].changeKills(kills);
                            back();

                        //option 5 allows users to change the amount of deaths they have using the changeDeaths() function
                        } else if (option==5){
                            System.out.println("\nHow many deaths do you want to add/remove?");
                            int deaths = scan.nextInt();
                            Agents.allAgents[agentChosen-1].changeDeaths(deaths);
                            back();

                        //option 6 returns a user's kill to death ratio using the getKD() function
                        } else if (option==6){
                            Agents.allAgents[agentChosen-1].getKD();
                            back();

                        //option 7 allows a user to change what team they have an agent on using the setTeam() function
                        } else if (option==7){
                            System.out.println("\nWhat team do you want to change to?");
                            int team = scan.nextInt();
                            Agents.allAgents[agentChosen-1].setTeam(team);
                            back();

                        //option 8 allows a user to swap to agent's positions and teams using the swapTeam() function
                        } else if (option==8){
                            if (Agents.totalAgents>=2){//makes sure the user has at least 2 agents created
                                System.out.println("\nWhich agent do you want to swap with?");
                                Agents.allAgentsCreated();
                                //allows the user to choose which agent they want to swap their original agent choice with
                                int agentChosen2 = scan.nextInt();
                                if (agentChosen2>Agents.totalAgents||agentChosen2<0){//makes sure the agent exists
                                    System.out.println("\nOption or Agent requested doesn't exist.");
                                    back();
                                } else if (agentChosen2==agentChosen){ //makes sure the agent they are swapping with is not themselves
                                    System.out.println("\nYou cannot swap teams with yourself.");
                                    back();
                                } else {
                                    //uses the swapTeam() function to swap teams of the user's first choice with their second choice
                                    Agents.allAgents[agentChosen-1].swapTeam(Agents.allAgents[agentChosen2-1]);
                                    back();
                                }
                            } else {
                                System.out.println("\nCreate more agents! You cannot swap teams with another agent if there is only 1!");
                                back();
                            }

                        //option 9 allows a user to display the 2 teans in alphabetical order
                        } else if (option==9){
                            if (Agents.totalAgents==10){ //makes sure the user has 10 agents created because otherwise it would throw an error because you cannot add a null value to a string and you cannot use the sort() function if there are null values in an array
                                Agents.getTeams();
                                back();
                            } else {
                                System.out.println("\nCreate more agents, teams are not filled.");
                                back();
                            }

                        //case scenario if user inputs and option that does not exist
                        } else{
                            System.out.println("\nOption doesn't exist.");
                        }
                    }
                }else{
                    System.out.println("\nNo agents have been created! Create an agent before attempting to use this.");
                    back();
                }

            //option 4 allows a user to choose an agent to get the counter picks for using the findCounter() function
            } else if (option == 4){
                if (Agents.totalAgents==10){//makes sure the user has 10 agents (the method will not work without 10 agents)
                    System.out.println("\nWhich agent would you like to analyse?");
                    Agents.allAgentsCreated();//displays all agents that have been created
                    int agentChosen = scan.nextInt();
                    if (agentChosen>Agents.totalAgents||agentChosen<0){//makes sure the chosen agent exists
                        System.out.println("\nAgent requested doesn't exist.");
                        back();
                    } else {
                        //uses the findCounter() function to find the counters for the team
                        if (Agents.allAgents[agentChosen-1].team==2){
                            MapAndPick.findCounter(Agents.allNames[agentChosen-1], 1);
                            back();
                        } else {
                            MapAndPick.findCounter(Agents.allNames[agentChosen-1], 2);
                            back();
                        }
                    }
                }else{
                    System.out.println("\nNot enough agents have been created! Create 10 agents before attempting to use this.");
                    back();
                }

            //option 5 allows a user to choose a team they want to find the best map for using the findMap() function
            } else if (option == 5){
                if (Agents.totalAgents==10){//makes sure the user has 10 agents (the method will not work without 10 agents)
                    System.out.println("\nWhich team would you like to analyse (1 or 2)?");

                    //makes sure the user is inputting a real team
                    int team = scan.nextInt();
                    if(team==1||team==2){
                        MapAndPick.findMap(team); //uses the findMap() function to find what map is best
                        back();
                    } else {
                        System.out.println("\nRequested team doesn't exist.");
                        back();
                    } 
                }else{
                    System.out.println("\nNo agents have been created! Create an agent before attempting to use this.");
                    back();
                }

            //option 6 allows a user to fill all empty slots on teams so they can try out the other methods
            } else if (option == 6){
                //loop fills all slots with a random agent
                for (int i = Agents.totalAgents; i<10;i++){
                    Agents.allAgents[Agents.totalAgents] = new Agents(MapAndPick.data[0][(int)(Math.random()*23)]);
                }
                System.out.println("\nAll remaining slots have been filled!");
                Agents.sortTeams(); //sorts teams so they are in alphabetical order
                back();
            //case scenario that a user inputs and option that exist
            }else{
                System.out.println("\nOption doesn't exist.");
                back();
            }
        }
    } 

    /* 
    *method that provides a buffer period before returning to the menu of options
    *@return - print statement and scanner line so user is not flooded with text
    */
    public static void back(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter any key to return to menu.");
        scan.next();
    } 
}