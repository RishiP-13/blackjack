import java.util.ArrayList;
//Singular card class
public class Cards {
	private String suites;
	private String rank;
	public Cards(String suites, String rank) {
		this.suites = suites;
		this.rank = rank;
	}
	//returns card
	public String toString() {
		return suites + " of " + rank;
	}
	//return rank of card
	public String getRank() {
	  return rank;
}
	//since 
	public String getImage() {
		return "src/" + toString() + ".png";
	}
	
	}
