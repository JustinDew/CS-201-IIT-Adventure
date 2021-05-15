package pack;
import java.util.Random;

public class NPC extends Student{
	int rapport = 0, rapportReq;
	boolean isFriend = false;
	public NPC(String name, String major, String housing, Player player) {
		
		Random rand = new Random();
		this.name = name;
		this.major = major;
		this.housing = housing;
		rapportReq = rand.nextInt(3) + 3;
		
		//automatically raises rapport for same housing and major as the player
		if(player.housing.equals(housing)) {
			rapport++;
		}
		if(player.major.equals(major)) {
			rapport++;
		}
		
	}
	//raises rapport
	public void raiseRapport() {
		rapport++;
	}
	//lowers rapport
	public void lowerRapport() {
		rapport--;
	}
	//sets rapport requirement
	public void setRapportReq(int num) {
		rapportReq = num;
	}
	//checks if rapport is high enough to become friends
	public boolean rapportCheck() {
		if(rapport >= rapportReq) {
			isFriend = true;
			return true;
		}else {
			return false;
		}
	}
	//returns rapport requirement
	public int getRapportReq() {
		return rapportReq;
	}
}
