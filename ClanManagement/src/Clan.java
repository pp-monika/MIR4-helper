import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.util.Scanner;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Clan {
	

	private static final String CLAN_NAME = "Rajasingh";	
	private ArrayList<Member> members;
	private ArrayList<Member> attendWar;
	
	
	Clan() {
		// clanName = "Rajasingh";
		members = new ArrayList<Member>();
		attendWar = new ArrayList<Member>();
	}
	
	
	
	public int clanSize() {
		return members.size();
	}
	
	
	public void insertMember(Member newMember) {
		
		members.add(newMember);
	}
	
	
	public void deleteMember(String name) {
		int index = exists(name);
		members.remove(index);
	}
	
	
	public void updateMemberLevel(String name, int level) {
		int index = exists(name);
		members.get(index).setLevel(level);
	}
	
	
	public void updateRank(String name, String rank) {
		
		int index = exists(name);
		
		
		try {
			members.get(index).setRank(rank);
		} catch (Exception e) {
			
		}
		
	}
	
	
	public void attendWar(String name) {
		
		int index = exists(name);
		if (index == -1) {
			System.out.println("Member doesn't exist.");
		} else {
			attendWar.add(members.get(index));
		}
		
	}
	
	
	public int exists(String name) {
		
		int index = 0;
		for (Member m : members) {
			if (m.getName().equals(name)) {
				return index;
			}
			index++;
		}
		
		return -1;
	}
	
	
	public String getMember(String name) {
		
		int index = exists(name);
		return members.get(index).to_String();
		
	}
	
	
	public int countClass(String char_class) {
		
		int count = 0;
		
		for (Member m : members) {
			if (m.getCharClass().equals(char_class)) {
				count++;
			}
		}
		
		return count;
	}
	
	
	public void printClass(String char_class) {
		
		System.out.println(char_class);
		

		for (Member m : members) {
			if (m.getCharClass().equals(char_class)) {
				m.printMember();
			}
		}
	}
	
	
	public void printClan() {
		
		if (clanSize() == 0) {
			System.out.println("No member.");
		} else {
			for (Member m : members) {
				m.printMember();
			}
		}
		
	}
	
	
	public void printClanStats() {
		
		System.out.println("CLAN STATISTICS");
		System.out.println("Total members: " + clanSize() + "\n");
		
		System.out.println("LEVEL");
		System.out.println("Min level: " + minLevel());
		System.out.println("Max level: " + maxLevel());
		System.out.println("Average level: " + averageLevel());
		
		System.out.println("Level Count");
		printLevelCount();
		
	}
	
	
	public void printLevelCount() {
		
		ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();
		levels.add(new ArrayList<Integer>());
		levels.add(new ArrayList<Integer>());
		
		for (Member m : members) {
			int index = levels.get(0).indexOf(Integer.valueOf(m.getLevel()));
			if (index == -1) {
				levels.get(0).add(m.getLevel());
				index = levels.get(0).indexOf(Integer.valueOf(m.getLevel()));
				levels.get(1).add(Integer.valueOf(0));
			}
			levels.get(1).set(index, levels.get(1).get(index) + 1);
		}
		
		System.out.print("LV    |  ");
		for (Integer lv : levels.get(0)) {
			System.out.print(lv + "\t");
		}
		
		System.out.println();
		
		System.out.print("Count |  ");
		for (Integer count : levels.get(1)) {
			System.out.print(count + "\t");
		}
		
		System.out.println();
		
		// TO DO: Sort by first column
		
	}
	
	
	public int minLevel() {
		
		int min = members.get(0).getLevel();
		
		for (Member m : members) {
			if (m.getLevel() < min) {
				min = m.getLevel();
			}
		}
		
		return min;

	}
	
	
	public int maxLevel() {
		
		int max = members.get(0).getLevel();
		
		for (Member m : members) {
			if (m.getLevel() > max) {
				max = m.getLevel();
			}
		}
		
		return max;

	}
	
	
	public double averageLevel() {
		
		double sum = 0;
		
		for (Member m : members) {
			sum += m.getLevel();
		}
		
		return (double)sum / clanSize();
	}
	
	
	
	public void exportCSV(String filename, String arrayName) {
		
		String directory = System.getProperty("user.dir");
		try {
			 System.out.println(directory + "\\" + filename);
		     Writer outFile = new FileWriter(directory + "\\" + filename, false);
		     
		     if (arrayName.equals("member")) {
		    	 
		     
			     outFile.write(CLAN_NAME + "\n");
			     outFile.write(clanSize() + "\n");
			     
			     for (Member m : members) {
			    	 outFile.write(m.formatCSV());
				 }
			     outFile.close();
			     
		     } else if (arrayName.equals("war")) {
		    	 
		    	 outFile.write("Clan Rank,IGN,Class,Level\n");
		    	 for (Member m : attendWar) {
			    	 outFile.write(m.formatCSV());
				 }
			     outFile.close();
		    	 
		     }
		     
		     System.out.println("Export successful.");
		     
		} catch (IOException e) {
			System.out.println("An error occurred when exporting.");
		    e.printStackTrace();
		}
		
	}
	
	
	public void importCSV(String directory) {
		
		System.out.println("Importing...");
		
		try {
			
			File input = new File(directory);
			Scanner content = new Scanner(input);
			System.out.println(content.nextLine());
			
			// Skip '\n' after the integer.
			content.nextLine();
			
			while (content.hasNextLine()) {
				String line = content.nextLine();
				System.out.println(line);
				String[] line_token = line.split(",");
				Member memb = new Member(line_token[0], line_token[1], line_token[2], Integer.parseInt(line_token[3]));
				insertMember(memb);
			}
			content.close();
			System.out.println("Import successful.");
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred when importing.");
		    e.printStackTrace();
		}
		
	}
	
	
}