// Ali Abbas Naqvi
// 7 December 2020
// Version 3
// This program creates a gamified Olympics medal table for 6 countries calculated through a virtual dice for events inputed by the user
import java.util.Random; 
import java.util.Scanner;

public class Olympics {
	
	public static void main(String[] args) {
		
		OlympicsInformation();
		
	}
	
	// User input method asking for the next event
	public static String nextEvent() {
		
		String event;
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("What is the next event?");
		event = keyboard.nextLine();
		
		return event;
		
	}
	
	// User input method asking for the total number of events
	public static int amountOfEvents() {
		
		int eventAmount;
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter the number of events: ");
		eventAmount = keyboard.nextInt();
		
		return eventAmount;
		
	}
	
	// Produces the results of the gamified Olympics medal table and prints it out
	public static void OlympicsInformation() {
		
		String eventName;
		int eventsAmount;
		int eventNumber = 1;
		
		Scanner keyboard = new Scanner(System.in);
		
		Random random = new Random();
		
		MedalTable teamTable = createTeam(6);
		
		teamTable = setCountry(teamTable, 0, "Japan");
		teamTable = setCountry(teamTable, 1, "Russia");
		teamTable = setCountry(teamTable, 2, "China");
		teamTable = setCountry(teamTable, 3, "India");
		teamTable = setCountry(teamTable, 4, "Canada");
		teamTable = setCountry(teamTable, 5, "Germany");
		
		eventsAmount = amountOfEvents();
		System.out.println();
		
		for (int i = eventsAmount; i > 0; i--) {
			
			int indexGold = 0;
			int indexSilver = 0;
			int indexBronze = 0;
			
			eventName = nextEvent();
			System.out.println();
			
			System.out.println("Event " + eventNumber + ": " + eventName);
			
			indexGold = random.nextInt(6);
			System.out.println("Gold: " + getCountry(teamTable, indexGold));
			teamTable = updateGold(teamTable, indexGold);
			
			indexSilver = random.nextInt(6);
			
			if (indexSilver == indexGold) {
				
				while (indexSilver == indexGold) {
					
					indexSilver = random.nextInt(6);
					
				}
				
			}
			
			System.out.println("Silver: " + getCountry(teamTable, indexSilver));
			teamTable = updateSilver(teamTable, indexSilver);
			
			indexBronze = random.nextInt(6);
			
			if (indexBronze == indexSilver || indexBronze == indexGold) {
				
				while (indexBronze == indexSilver || indexBronze == indexGold) {
					
					indexBronze = random.nextInt(6);
					
				}
				
			}
			
			System.out.println("Bronze: " + getCountry(teamTable, indexBronze));
			teamTable = updateBronze(teamTable, indexBronze);
			
			System.out.println();
			
			eventNumber++;
		}
		
		teamTable = calculateTotal(teamTable, 6);
		
		teamTable = insertionSort(teamTable);
		
		printMedals(teamTable, 6);
		
	}
	
	// Initializes the MedalTable ADT and the array of records Team
	public static MedalTable createTeam(int size) {
		
		MedalTable teamTable = new MedalTable();
		Team[] teams = new Team[size];
		
		int i = 0;
		
		for (int x = size; x >= 1; x--) {
			
			teams[i] = new Team();
			
			i++;
			
		}
		
		teamTable.teams = teams;
		
		return teamTable;
		
	}
	
	// Prints out the medal table with all countries included
	public static void printMedals(MedalTable teamTable, int size) {
		
		int i = 5;
		
		for (int y = size; y >= 1; y--) {
			
			System.out.println(getCountry(teamTable, i) + "     G: " + getGold(teamTable, i) + " S: " + getSilver(teamTable, i) + " B: " + getBronze(teamTable, i) + "     TOTAL: " + getTotal(teamTable, i));
			
			i--;
			
		}
		
	}
	
	// Sorts the teams array in order of lowest to highest medal totals
	public static MedalTable insertionSort(MedalTable teamTable) {
		
		for (int i = 1; i < teamTable.teams.length; i++) {
			
			int current = teamTable.teams[i].total;
			int j = i - 1;
			
			while (j >= 0 && teamTable.teams[j].total > current) {
				
				Team[] temp = new Team[1];
				temp[0] = teamTable.teams[j];
				teamTable.teams[j] = teamTable.teams[j + 1];
				teamTable.teams[j + 1] = temp[0];
				j--;
				
			}
			
		}
		
		return teamTable;
	}
	
	// Calculates the total medals earned by a specific team
	public static MedalTable calculateTotal(MedalTable teamTable, int size) {
		
		int i = 0;
		
		for (int y = size; y >= 1; y--) {
			
			int total = getGold(teamTable, i) + getSilver(teamTable, i) + getBronze(teamTable, i);
			
			teamTable.teams[i].total = total;
			
			i++;
			
		}
		
		return teamTable;
		
	}
	
	// Sets the name of the country represented by a specific team
	public static MedalTable setCountry(MedalTable teamTable, int i, String country) {
		
		teamTable.teams[i].country = country;
		
		return teamTable;
		
	}
	
	// Updates the gold medal count of a team by 1
	public static MedalTable updateGold(MedalTable teamTable, int i) {
		
		teamTable.teams[i].gold += 1;
		
		return teamTable;
		
	}
	
	// Updates the silver medal count of a team by 1
	public static MedalTable updateSilver(MedalTable teamTable, int i) {
		
		teamTable.teams[i].silver += 1;
		
		return teamTable;
		
	}
	
	// Updates the bronze medal count of a team by 1
	public static MedalTable updateBronze(MedalTable teamTable, int i) {
		
		teamTable.teams[i].bronze += 1;
		
		return teamTable;
		
	}
	
	// Returns the country name the team is representing
	public static String getCountry(MedalTable teamTable, int i) {
		
		return teamTable.teams[i].country;
		
	}
	
	// Returns the gold medals earned by a specific team
	public static int getGold(MedalTable teamTable, int i) {
		
		return teamTable.teams[i].gold;
		
	}
	
	// Returns the silver medals earned by a specific team
	public static int getSilver(MedalTable teamTable, int i) {
		
		return teamTable.teams[i].silver;
		
	}
	
	// Returns the bronze medals earned by a specific team
	public static int getBronze(MedalTable teamTable, int i) {
		
		return teamTable.teams[i].bronze;
		
	}
	
	// Returns the total medals earned by a specific team
	public static int getTotal(MedalTable teamTable, int i) {
		
		return teamTable.teams[i].total;
		
	}

}