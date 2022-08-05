public class Member {
	
	private String name;
	private String rank;
	private String char_class;
	private int level;
	// private enum Ranks {};
	
	
	Member(String rank, String name, String char_class, int level) {
		this.name = name;
		
		try {
			setRank(rank);
			setCharClass(char_class);
		} catch (Exception e) {
			
		}
		
		this.level = level;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getrank() {
		return rank;
	}
	
	public String getCharClass() {
		return char_class;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setRank(String rank) throws Exception {
		
		if (!rank.equals("L") && !rank.equals("E") && !rank.equals("M") && !rank.equals("NM")
				&& !rank.equals("Leader") && !rank.equals("Elder") && !rank.equals("Member")
				&& !rank.equals("New Member")) {
			throw new Exception("Incorrect rank.");
		}
		
		if (rank.equals("L") || rank.equals("Leader")) {
			this.rank = "Leader";
		} else if (rank.equals("E") || rank.equals("Elder")) {
			this.rank = "Elder";
		} else if (rank.equals("M") || rank.equals("Member")) {
			this.rank = "Member";
		} else {
			this.rank = "New Member";
		}
		
		
	}
	
	public void setCharClass(String char_class) throws Exception {
		
		if (!char_class.equals("W") && !char_class.equals("L") && !char_class.equals("S") 
				&& !char_class.equals("T") && !char_class.equals("A") && !char_class.equals("Warrior")
				&& !char_class.equals("Lancer") && !char_class.equals("Sorcerer") && !char_class.equals("Taoist")
				&& !char_class.equals("Arbalist")) {
			throw new Exception("Incorrect class.");
		}
		
		if (char_class.equals("W") || char_class.equals("Warrior")) {
			this.char_class = "Warrior";
		} else if (char_class.equals("L") || char_class.equals("Lancer")) {
			this.char_class = "Lancer";
		} else if (char_class.equals("S") || char_class.equals("Sorcerer")) {
			this.char_class = "Sorcerer";
		} else if (char_class.equals("T") || char_class.equals("Taoist")) {
			this.char_class = "Taoist";
		} else {
			this.char_class = "Arbalist";
		}
	}
	
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void printMember() {
		System.out.print(to_String());
	}
	
	public String to_String() {
		return rank + "\t" + name + "\t" + char_class + "\t" + level + "\n";
	}
	
	public String formatCSV() {
		return rank + "," + name + "," + char_class + "," + level + "\n";
	}
	
	
}