package pack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	static Player player;
	static boolean correctChoice = false, choiceMade = false;
	static int majorChoice, housingChoice, ans;
	static String name, major, housing;
	static Scanner sc = new Scanner(System.in);
	static Scanner sc1, sc2;
	static ArrayList<String> namesList, majorsList, housingList;
	static ArrayList<NPC> friendsList, mathClassMates, historyClassMates, humanitiesClassMates;
	static ArrayList<Integer> historyTestDays, mathTestDays;
	static Time time;
	static Course math, history, humanities;
	static double totalScore;
	
	static Random rand;
	
	public static void main(String[] args) throws IOException {
		
		File names = new File("Names.txt");
		File majors = new File("Majors.txt"); 
		
		rand = new Random();
		
		sc1 = new Scanner(names);
		sc2 = new Scanner(majors);
		
		namesList = new ArrayList<>();
		majorsList = new ArrayList<>();  
		housingList = new ArrayList<>();
		friendsList = new ArrayList<>();
		mathClassMates = new ArrayList<>();
		historyClassMates = new ArrayList<>();
		humanitiesClassMates = new ArrayList<>();
		historyTestDays = new ArrayList<>();
		mathTestDays = new ArrayList<>();
		
		//adds the days for testing
		historyTestDays.add(2);
		historyTestDays.add(11);
		historyTestDays.add(18);
		historyTestDays.add(25);
		historyTestDays.add(32);
		historyTestDays.add(39);
		historyTestDays.add(46);
		mathTestDays.add(8);
		mathTestDays.add(15);
		mathTestDays.add(22);
		mathTestDays.add(29);
		mathTestDays.add(36);
		mathTestDays.add(45);
		
		time = new Time();
		
		//puts names and majors from txt files into ArrayLists
		for(int i = 0; i < 100; i++) {
			namesList.add(sc1.nextLine());
		}
		for(int i = 0; i < 18; i++) {
			majorsList.add(sc2.nextLine());
		}
		sc1.close();
		sc2.close();
		
		//adds three housing options to housingList
		housingList.add("Mccormick Student Village");
		housingList.add("Kacek Hall");
		housingList.add("Jeanne and John Rowe Village");
		
		history = new Course("History", "Harrison", (rand.nextInt(6) + 2), 3);
		humanities = new Course("Humanities", "Martinez", (rand.nextInt(6) + 2), 2);
		math = new Course("Math", "Boyle", (rand.nextInt(6) + 2), 3);

		//main part of the game
		setup();
		time.setTime(8);
		while(time.getDayNum() < 12) {
			if(time.getDay().equals("Monday") || time.getDay().equals("Wednesday")) {
				System.out.println("You wake up bright and early at 8. It is " + time.getDay() +" today. You have Math class soon. Will you attend?\n----------\n1. Yes\n2. No");
				if(playerAnswer(2) == 1){
					player.attendClass(math, math.hours, mathTestDays.get(0), time);
					if(time.getDayNum() == mathTestDays.get(0)) {
						mathTestDays.remove(0);
						System.out.println(math.grade);
					}
					if(mathClassMates.size() == 0) {
						createNPCMath();
					}else {
						if(rand.nextBoolean()) {
							createNPCMath();
						}
					}
					System.out.println("Would you like to talk with a classmate?\n----------\n1. Yes \n2. No");
					if(playerAnswer(2) == 1) {
						System.out.println("Which classmate would you like to talk to?");
						int i;
						for(i = 0; i < mathClassMates.size(); i++) {
							System.out.println((i+1) + "." + mathClassMates.get(i).getName() + mathClassMates.get(i).getRapportReq()+ "\n");
						}
						talkToStudent(mathClassMates.get(playerAnswer(i)-1));
					}
				}else {
					if(time.getDayNum() == mathTestDays.get(0)) {
						System.out.println("You missed a test. Do you want to ask your professor to take the test late?\n----------\n1. Yes\n2. No");
						if(playerAnswer() == 1) {
							if(math.prof.rapport >= math.prof.rapportReq) {
								System.out.println("Professor " + math.prof.getName() + " allowed you to take the test again.");
								math.test(math.hours*4 + rand.nextInt(7), 10);
							}else {
								System.out.println("Professor " + math.prof.getName() + " did not allow you to take the test");
								mathTestDays.remove(0);
								math.totalPoints+=10;
								math.grade = math.currentPoints/math.totalPoints;
							}
						}else {
							mathTestDays.remove(0);
							math.totalPoints+=10;
							math.grade = math.currentPoints/math.totalPoints;
						}
					}
				}
				freeTime(time);
			}else if(time.getDay().equals("Tuesday") || time.getDay().equals("Thursday")) {
				System.out.println("You wake up bright and early at 8. It is " + time.getDay() +" today. You have History class soon. Will you attend?\n----------\n1. Yes\n2. No");
				if(playerAnswer(2) == 1){
					player.attendClass(history, history.hours, historyTestDays.get(0), time);
					if(time.getDayNum() == historyTestDays.get(0)) {
						historyTestDays.remove(0);
					}
					if(historyClassMates.size() == 0) {
						createNPCHistory();
					}else {
						if(rand.nextBoolean()) {
							createNPCHistory();
						}
					}
					System.out.println("Would you like to talk with a classmate?\n----------\n1. Yes \n2. No");
					if(playerAnswer(2) == 1) {
						System.out.println("Which classmate would you like to talk to?");
						int i;
						for(i = 0; i < historyClassMates.size(); i++) {
							System.out.println((i+1) + "." + historyClassMates.get(i).getName() + "\n");
						}
						talkToStudent(historyClassMates.get(playerAnswer(i)-1));
					}
				}else {
					System.out.println("You missed a test. Do you want to ask your professor to take the test late?\n----------\n1. Yes\n2. No");
					if(time.getDayNum() == historyTestDays.get(0)) {
						if(playerAnswer() == 1) {
							if(history.prof.rapport >= history.prof.rapportReq) {
								System.out.println("Professor " + history.prof.getName() + " allowed you to take the test again.");
								history.test(history.hours*4 + rand.nextInt(7), 10);
							}else {
								System.out.println("Professor " + history.prof.getName() + " did not allow you to take the test");
								historyTestDays.remove(0);
								history.totalPoints+=10;
								history.grade = history.currentPoints/history.totalPoints;
							}
						}else {
							if(time.getDayNum() == historyTestDays.get(0)) {
								System.out.println("You missed a test. Do you want to ask your professor to take the test late?\n----------\n1. Yes\n2. No");
								if(playerAnswer() == 1) {
									if(history.prof.rapport >= history.prof.rapportReq) {
										System.out.println("Professor " + history.prof.getName() + " allowed you to take the test again.");
										history.test(history.hours*4 + rand.nextInt(7), 10);
									}else {
										System.out.println("Professor " + history.prof.getName() + " did not allow you to take the test");
										System.out.println("You missed the test and recieve the score of 0%");
										historyTestDays.remove(0);
										history.totalPoints+=10;
										history.grade = history.currentPoints/history.totalPoints;
									}
								}else {
									System.out.println("You missed the test and recieve the score of 0%");
									historyTestDays.remove(0);
									history.totalPoints+=10;
									history.grade = history.currentPoints/history.totalPoints;
								}
							}
						}
					}
				}
				
				freeTime(time);			
			}else if(time.getDay().equals("Friday")) {
				humanities.totalClasses++;
				System.out.println("You wake up bright and early at 8. It is " + time.getDay() +" today. You have Humanities class soon. Will you attend?\n----------\n1. Yes\n2. No");
				if(playerAnswer(2) == 1){
					player.attendClass(humanities, humanities.hours, time);
					if(humanitiesClassMates.size() == 0) {
						createNPCHumanities();
					}else {
						if(rand.nextBoolean()) {
							createNPCHumanities();
						}
					}
					System.out.println("Would you like to talk with a classmate?\n----------\n1. Yes \n2. No");
					if(playerAnswer(2) == 1) {
						System.out.println("Which classmate would you like to talk to?");
						int i;
						for(i = 0; i < humanitiesClassMates.size(); i++) {
							System.out.println((i+1)+ "." + humanitiesClassMates.get(i).getName() + "\n");
						}
						talkToStudent(humanitiesClassMates.get(playerAnswer(i)-1));
					}
				}
				freeTime(time);	

			}else {
				System.out.println("You wake up bright and early at 8am. It is " + time.getDay() +" today.");
				freeTime(time);
			}
		}
		humanities.grade = humanities.attendence/humanities.totalClasses;
		totalScore = math.grade + humanities.grade + history.grade + friendsList.size();
		printStudentInfo(player);
		System.out.println("Score: " + totalScore);
		
		
	}
	
	
	
	
//-------------------------METHODS--------------------------
	
	//ran at the start of the game to setup the player info
	public static void setup() {
		System.out.println("What is your name?");
		name = sc.nextLine();
		//prints all of the options for majors
		for(int i = 0; i < 18; i++) {
			System.out.println(i + 1 + ". " + majorsList.get(i));
		}
		//makes sure that you input a number that corresponds to the a major
		while(!correctChoice) {
			System.out.println("----------\nChoose your major by typing the number.");
			try {
				majorChoice = Integer.parseInt(sc.nextLine());
				major = majorsList.get(majorChoice-1);
				correctChoice = true;
				System.out.println("Your major is " + major + "\n");
			}catch(Exception e) {
				System.out.println("That is not an option. Try again.");
				correctChoice = false;
			}
		}
		//prints all housing options
		for(int i = 0; i < 3; i++) {
			System.out.println(i + 1 + ". " + housingList.get(i));
		}
		//makes sure that you input a number that corresponds to a housing hall
		correctChoice = false;
		while(!correctChoice) {
			System.out.println("----------\nChoose your housing by typing the number.");
			try {
				housingChoice = Integer.parseInt(sc.nextLine());
				housing = housingList.get(housingChoice-1);
				correctChoice = true;
				System.out.println("You chose " + housing + "\n");
			}catch(Exception e) {
				System.out.println("That is not an option. Try again.");
				correctChoice = false;
			}
		}
		player = new Player(name, major, housing);
		printStudentInfo(player);

	}
	//prints the student info
	public static void printStudentInfo(Player player) {
		System.out.println("STUDENT INFO\nNAME: " + player.name + "\nMAJOR: " + player.major + "\nHOUSING: " + player.housing);
	}
	
	//creates new NPC for each course
	public static void createNPCMath() {
		int nameChosen = rand.nextInt(namesList.size());
		mathClassMates.add(new NPC(namesList.get(nameChosen), majorsList.get(rand.nextInt(2)), housingList.get(rand.nextInt(2)), player));
		namesList.remove(nameChosen);
		System.out.println("You met " + mathClassMates.get(mathClassMates.size() - 1).getName() + " today!");
	}
	public static void createNPCHistory() {
		int nameChosen = rand.nextInt(namesList.size());
		historyClassMates.add(new NPC(namesList.get(nameChosen), majorsList.get(rand.nextInt(2)), housingList.get(rand.nextInt(2)), player));
		namesList.remove(nameChosen);
		System.out.println("You met " + historyClassMates.get(historyClassMates.size() - 1).getName() + " today!");
	}
	public static void createNPCHumanities() {
		int nameChosen = rand.nextInt(namesList.size());
		humanitiesClassMates.add(new NPC(namesList.get(nameChosen), majorsList.get(rand.nextInt(2)), housingList.get(rand.nextInt(2)), player));
		namesList.remove(nameChosen);
		System.out.println("You met " + humanitiesClassMates.get(humanitiesClassMates.size() - 1).getName() + " today!");
	}
	
	//adds an existing npc to friendsList
	public static void addFriend(NPC npc) {
		if(npc.rapport >= npc.rapportReq) {
			friendsList.add(npc);
		}
	}
	//used for the player to talk to a student after class
	public static void talkToStudent(NPC npc) {
		if(npc.isFriend) {
			System.out.println("You talked with your friend, " + npc.name + " after class.");
			npc.rapport++;
		}else {
			switch(rand.nextInt(4)) {
			case 1:
				System.out.println("You approached " + npc.name + ", but they were busy.");
				break;
			default:
				System.out.println("You talked with " + npc.name + " after class.");
				npc.rapport++;
				if(npc.rapportCheck()) {
					friendsList.add(npc);
					System.out.println("You are now friends with " + npc.name + ". They have been added to you contacts.");
				}
			}
		}
		System.out.println("You went back to your dorm.");
	}
	//used for player option selection with a maximum number of choices
	public static int playerAnswer(int numChoices) {
		correctChoice = false;
		while(!correctChoice) {
			try {
				ans = Integer.parseInt(sc.nextLine());
				if(ans > numChoices || ans <= 0) {
					System.out.println("That is not an option. Please try again.");
					correctChoice = false;
				}else {
					correctChoice = true;
				}
			}catch(Exception e) {
				System.out.println("That is not an option. Please try again.");
				correctChoice = false;
			}
		}
		return ans;
	}
	//used for player answer that is not option based like how many hours a given action will take
	public static int playerAnswer() {
		correctChoice = false;
		while(!correctChoice) {
			try {
				ans = Integer.parseInt(sc.nextLine());
				correctChoice = true;
			}catch(Exception e) {
				System.out.println("That is not an option. Please try again.");
				correctChoice = false;
			}
		}
		return ans;
	}
	//used to selecting an action during the player's free time.
	public static void freeTime(Time time) {
		while(true) {
			System.out.println("You have free time. What will you do?\n----------\n1. Play video games\n2. Study\n3. Sleep\n4. Hangout with a friend");
			switch(playerAnswer(4)) {
			case 1:
				System.out.println("How many hours would you like to play?\n----------");
				player.playVideoGames(playerAnswer(), time);
				break;
			case 2:
				System.out.println("Which class do you want to study for?\n---------\n1. Math\n2. History\n3. Humanities");
				switch(playerAnswer(3)) {
				case 1:
					System.out.println("How long will you study for?");
					player.study(playerAnswer(), math, time);
					break;
				case 2:
					System.out.println("How long will you study for?");
					player.study(playerAnswer(), history, time);
					break;
				case 3:
					System.out.println("How long will you study for?");
					player.study(playerAnswer(), humanities, time);
					break;
				}
				break;
			case 3:
				if(time.getTime() >= 22 || time.getTime() < 8) {
					player.sleep(time);
					return;
				}else {
					System.out.println("You're not tired yet.");
					break;
				}
			case 4:
				if(friendsList.size() > 0) {
					System.out.print("Which friend would you like to contact?\n----------\n" );
					int i;
					for(i = 0; i < friendsList.size(); i++) {
						System.out.println((i+1) + "." + friendsList.get(i).getName() + "\n");
					}
					player.hangoutWithFriend(friendsList.get(playerAnswer(i)-1), time);
				}else {
					System.out.println("You currently do not have any friends in your contacts.");
				}
				break;
			}
		}	
	}
}
