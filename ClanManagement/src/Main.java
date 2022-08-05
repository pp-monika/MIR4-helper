import java.util.Scanner;

class Main {
	
	public static void manage(Clan clan, Scanner input, Scanner file_input) {
		
		System.out.println("MANAGE CLAN.");
			
		//Scanner input = new Scanner(System.in);
		//Scanner file_input = new Scanner(System.in);
			
		while (true) {
				
			System.out.print("Enter command: ");
			String command = input.nextLine();
				
			if (command.equals("quit")) {
				//input.close();
				//file_input.close();
				System.out.println("Exit management.");
				break;
			} else if (command.equals("count")) {
				
				System.out.print("Category: ");
				String category = input.nextLine();
				if (category.equals("all") || category.equals("a")) {
					System.out.println(clan.clanSize());
				} else {
					System.out.println(clan.countClass(category));
				}
				
					
			} else if (command.equals("print")) {
				
				System.out.print("Category: ");
				String category = input.nextLine();
				if (category.equals("all") || category.equals("a")) {
					clan.printClan();
				} else {
					clan.printClass(category);
				}
				
					
			} else if (command.equals("export")) {
				System.out.print("Filename: ");
				String filename = input.nextLine();
				clan.exportCSV(filename, "member");
					
			} else if (command.equals("stat")) {
				clan.printClanStats();
					
			} else {					
				
				if (command.equals("add")) {
					
					while(true) {
						
						System.out.print("Name: ");
						String name = input.nextLine();
						
						if (name.isEmpty()) {
							break;
						}
						
						System.out.print("Rank: ");
						String rank = input.nextLine();
							
						System.out.print("Class: ");
						String char_class = input.nextLine();
							
						System.out.print("Level: ");
						int level = input.nextInt();
							
						Member newMemb = new Member(rank, name, char_class, level);
						clan.insertMember(newMemb);
						System.out.println("Member inserted.");
						input.nextLine();
						
					}
						
				} else if (command.equals("delete")) {
					
					while (true) {
					
						System.out.print("Name: ");
						String name = input.nextLine();
						
						if (name.isEmpty()) {
							break;
						}
							
						if (clan.exists(name) > -1) {
							clan.deleteMember(name);
							System.out.println("Member deleted.");
						}
					
					}
						
				} else if (command.equals("updateLevel")) {
					
					while (true) {
					
						System.out.print("Name: ");
						String name = input.nextLine();
						
						if (name.isEmpty()) {
							break;
						}
							
						if (clan.exists(name) > -1) {
							System.out.print("Level: ");
							int level = input.nextInt();
								
							clan.updateMemberLevel(name, level);
							System.out.println("Level updated.");
								
							input.nextLine();
						}
					
					}
						
				} else if (command.equals("updateRank")) {
					
					while (true) {
					
						System.out.print("Name: ");
						String name = input.nextLine();
						
						if (name.isEmpty()) {
							break;
						}
							
						if (clan.exists(name) > -1) {					
							System.out.print("Rank: ");
							String rank = input.nextLine();
								
							clan.updateRank(name, rank);
							System.out.println("Rank inserted.");
						}
						
					}
						
				} else {
						
					System.out.println("Command does not exist.");
				}		
				
			}
				
		}
		
	}
	
	
	public static void war(Clan clan, Scanner input) {
		
		System.out.println("WAR ATTENDANCE.");
		//Scanner input = new Scanner(System.in);
		
		while (true) {
			
			System.out.print("Name: ");
			String name = input.nextLine();
			
			if (name.isEmpty()) {
				break;
			}
			
			clan.attendWar(name);
			
		}
		
		clan.exportCSV("war_attendance.txt", "war");
		System.out.println("Exit War Attendance");
		
	}
	
	
	public static void main(String[] args) {
		
		Clan clan = new Clan();
		
		System.out.println("Welcome to Rajasingh Clan Management program.");
		System.out.println("Functionality --> 1) manage, 2) war");
		
		Scanner input = new Scanner(System.in);
		Scanner file_input = new Scanner(System.in);
		
		System.out.println("\nImport your clan member CSV file");
		System.out.print("Filename: ");
		String filedir = file_input.nextLine();
		System.out.println(filedir);		
		clan.importCSV(filedir);
		
		while (true) {
			
			System.out.print("Select function: ");
			String function = input.nextLine();
		
			if (function.equals("manage")) {
				manage(clan, input, file_input);
			} else if (function.equals("war")) {
				war(clan, input);
			} else if (function.equals("quit")) {
				input.close();
				file_input.close();
				break;
			} else {
				System.out.println("Invalid function.");
			}
			
		}
		
		System.out.println("Exit program.\nGood bye.\n");
		
	}
	
	
	
	
}